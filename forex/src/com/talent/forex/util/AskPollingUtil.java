package com.talent.forex.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.MatchMode;

import com.talent.exception.BoException;
import com.talent.forex.bean.domain.AccInfo;
import com.talent.forex.bean.domain.CTranFlowMapping;
import com.talent.forex.bean.domain.OtcForwardInfo;
import com.talent.forex.bean.domain.OtcSwapInfo;
import com.talent.forex.bean.domain.WTranFlowMapping;
import com.talent.forex.bean.model.LiborModel;
import com.talent.forex.dao.AccInfoDao;
import com.talent.forex.dao.CTranFlowMappingDao;
import com.talent.forex.dao.OtcForwardDao;
import com.talent.forex.dao.OtcSwapDao;
import com.talent.forex.dao.WTranFlowMappingDao;
import com.talent.forex.modules.rateFactory.RateReceive;
import com.talent.hibernate.util.HibernateUtil;
import com.talent.hibernate.util.TransactionNestUtil;

public class AskPollingUtil {
	
	private static Logger logger = Logger.getLogger(AskPollingUtil.class);
	
	/**
	 * 这个方法是被定时器轮询执行  如果交易的日期与服务器系统日期符合则执行
	 */
	//远期交易成功后的操作
	public static void checkForwardValueDate() {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~forward轮询开始啦！！~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		OtcForwardDao otcForwardDao = new OtcForwardDao();
		AccInfoDao accInfoDao = new AccInfoDao();
		WTranFlowMappingDao wTranFlowMappingDao = new WTranFlowMappingDao();
		CTranFlowMappingDao cTranFlowMappingDao = new CTranFlowMappingDao();
		String rsf=null;
		try {
			rsf = TransactionNestUtil.reference();
			OtcForwardInfo otcForwardInfo = new OtcForwardInfo();
			otcForwardInfo.setStatue("A");
			otcForwardInfo.setValueDate(GetDateTimeUtil.getCurrentDate());
			List<OtcForwardInfo> list = (List<OtcForwardInfo>)otcForwardDao.getBeansByBean(otcForwardInfo, MatchMode.ANYWHERE);
			
			//因为updateBean方法无法使用   只能通过批量提交了
			List<OtcForwardInfo> forwardListToUpdate = new ArrayList<OtcForwardInfo>();
			List<CTranFlowMapping> cTranListToUpdate = new ArrayList<CTranFlowMapping>();
			List<WTranFlowMapping> wTranListToUpdate = new ArrayList<WTranFlowMapping>();
			List<AccInfo> accListToUpdate = new ArrayList<AccInfo>();
			for (int i = 0; i < list.size(); i++) {
				OtcForwardInfo bean = list.get(i);
				// 1.更新账户表acc_info,先得到交易币种的账户bean
				// weCcy卖出货币 anaCcy是买入货币
				AccInfo accInfo = new AccInfo();
				accInfo.setUserNum(bean.getUserNum());
				accInfo.setAccType(bean.getTranType());
				accInfo.setCcy(bean.getWeCcy());
				AccInfo weAccInfo = accInfoDao.getBeanByBean(accInfo,MatchMode.ANYWHERE);

				AccInfo accInfo2 = new AccInfo();
				accInfo2.setUserNum(bean.getUserNum());
				accInfo2.setAccType(bean.getTranType());
				accInfo2.setCcy(bean.getAnaCcy());
				AccInfo anaAccInfo = accInfoDao.getBeanByBean(accInfo2,MatchMode.ANYWHERE);

				Double weCcyMoney = Double.parseDouble(weAccInfo.getAmount());// weCcy币种对应的账户余额
				Double anaCcyMoney = Double.parseDouble(anaAccInfo.getAmount());// anaCcy币种对应的账户余额
				
				Double price = Double.parseDouble(bean.getPrice()) + Double.parseDouble(bean.getPoint()) / 10000;// 远期交易的价格
				Double amt = Double.parseDouble(bean.getAmount());// 远期交易的实际交易金额

				if (bean.getDirection().equals("1")) {
					// 买
					// weCcy币种对应的账户金额 = 余额-实际交易金额*价格
					// anaCcy币种对应的账户金额 = 余额+实际交易金额
					if(weCcyMoney - amt * price < 0){
						logger.error("用户：" + bean.getUserNum() + " 添加掉期询价交易失败！账户"+ accInfo.getCcy() +"金额不足");
						BoException be = new BoException("otcSwapTradeAdd");
						if (accInfo.getAccType().equals("C"))
							be.setExceptionType("人民币账户"+ weAccInfo.getCcy() +"金额不足,掉期询价交易易提交失败！");
						else
							be.setExceptionType("外币账户"+ weAccInfo.getCcy() +"金额不足,掉期询价交易提交失败！");
						throw be;
					}
					weAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(weCcyMoney - amt * price));
					anaAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(anaCcyMoney + amt));
				} else {
					// 卖
					// weCcy币种对应的账户金额 = 余额-实际交易金额
					// anaCcy币种对应的账户金额 = 余额+实际交易金额*价格
					if(weCcyMoney - amt < 0){
						logger.error("用户：" + bean.getUserNum() + " 添加掉期询价交易失败！账户"+ accInfo.getCcy() +"金额不足");
						BoException be = new BoException("otcSwapTradeAdd");
						if (accInfo.getAccType().equals("C"))
							be.setExceptionType("人民币账户"+ weAccInfo.getCcy() +"金额不足,掉期询价交易易提交失败！");
						else
							be.setExceptionType("外币账户"+ weAccInfo.getCcy() +"金额不足,掉期询价交易提交失败！");
						throw be;
					}
					weAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(weCcyMoney - amt));
					anaAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(anaCcyMoney + price * amt));
				}

				// 2.更新交易流水表
				if(list.get(i).getTranType().equals("C")){
					CTranFlowMapping c = new CTranFlowMapping();
					c.setUserNum(bean.getUserNum());
					CTranFlowMapping cTranFlowMapping = cTranFlowMappingDao.getBeanByBean(c, MatchMode.ANYWHERE);
					// 远期交易次数加1，远期交易量加交易金额 ， 总交易次数加一，总交易量加交易金额
					cTranFlowMapping.setOtcForwartQty((Integer.parseInt(cTranFlowMapping.getOtcForwartQty()) + 1) + "");
					cTranFlowMapping.setOtcForwartAmt(FormatParamUtil.getAmountAndPriceFmtByDouble(Double.parseDouble(cTranFlowMapping.getOtcForwartAmt()) + amt));
					cTranFlowMapping.setCount(cTranFlowMapping.getCount() + 1);
					cTranListToUpdate.add(cTranFlowMapping);
				}else {
					WTranFlowMapping w = new WTranFlowMapping();
					w.setUserNum(bean.getUserNum());
					WTranFlowMapping wTranFlowMapping = wTranFlowMappingDao.getBeanByBean(w, MatchMode.ANYWHERE);
					// 远期交易次数加1，远期交易量加交易金额 ， 总交易次数加一，总交易量加交易金额
					wTranFlowMapping.setOtcForwartQty((Integer.parseInt(wTranFlowMapping.getOtcForwartQty()) + 1) + "");
					wTranFlowMapping.setOtcForwartAmt(FormatParamUtil.getAmountAndPriceFmtByDouble(Double.parseDouble(wTranFlowMapping.getOtcForwartAmt()) + amt));
					wTranFlowMapping.setCount(wTranFlowMapping.getCount() + 1);
					wTranListToUpdate.add(wTranFlowMapping);
				}
				bean.setStatue("D");
				// 4.把相关的bean更新到对应的表
				forwardListToUpdate.add(bean);
				accListToUpdate.add(weAccInfo);
				accListToUpdate.add(anaAccInfo);
			}
			//批量提交
			if(forwardListToUpdate.size() > 0){
				otcForwardDao.batchUpdate(forwardListToUpdate);
			}
			if(cTranListToUpdate.size() > 0){
				cTranFlowMappingDao.batchUpdate(cTranListToUpdate);
			}
			if(wTranListToUpdate.size() > 0){
				wTranFlowMappingDao.batchUpdate(wTranListToUpdate);
			}
			if(accListToUpdate.size() > 0){
				accInfoDao.batchUpdate(accListToUpdate);
			}
			TransactionNestUtil.releaseRef(rsf);
		} catch (Exception e) {
			TransactionNestUtil.releaseRef(rsf);
			if (!TransactionNestUtil.isReference()) {
				HibernateUtil.rollbackTransaction();
			}
			logger.error("远期交易轮询出错！" + e.getMessage());
		} finally {
			if (!TransactionNestUtil.isReference()) {
				HibernateUtil.closeSession();			
			}
		}
	}
	/**
	 * 这个方法是被定时器轮询执行  如果交易的日期与服务器系统日期符合则执行
	 */
	//掉期交易中的近端交易
	public static void checkSwapStartDate(){
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~swap近端交易轮询开始啦！！~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		OtcSwapDao otcSwapDao = new OtcSwapDao();
		AccInfoDao accInfoDao = new AccInfoDao();
		WTranFlowMappingDao wTranFlowMappingDao = new WTranFlowMappingDao();
		CTranFlowMappingDao cTranFlowMappingDao = new CTranFlowMappingDao();
		String rsf = null;
		try {
			rsf = TransactionNestUtil.reference();
			OtcSwapInfo otcSwapInfo = new OtcSwapInfo();
			otcSwapInfo.setStatue("A");
			String today = GetDateTimeUtil.getCurrentDate();
			otcSwapInfo.setStartDate(today);
			otcSwapInfo.setDate(today);
			List<OtcSwapInfo> list = (List<OtcSwapInfo>)otcSwapDao.getBeansByBean(otcSwapInfo, MatchMode.ANYWHERE);
			
			//因为updateBean方法无法使用   只能通过批量提交了
			ArrayList<OtcSwapInfo> swapListToUpdate = new ArrayList<OtcSwapInfo>();
			ArrayList<CTranFlowMapping> cTranListToUpdate = new ArrayList<CTranFlowMapping>();
			ArrayList<WTranFlowMapping> wTranListToUpdate = new ArrayList<WTranFlowMapping>();
			ArrayList<AccInfo> accListToUpdate = new ArrayList<AccInfo>();
			for (int i = 0; i < list.size(); i++) {
				OtcSwapInfo bean = list.get(i);
				// 1.更新账户表acc_info,先得到交易币种的账户bean
				// weCcy卖出货币 anaCcy是买入货币
				AccInfo accInfo = new AccInfo();
				accInfo.setUserNum(bean.getUserNum());
				accInfo.setAccType(bean.getTranType());
				accInfo.setCcy(bean.getWeCcy());
				AccInfo weAccInfo = accInfoDao.getBeanByBean(accInfo,MatchMode.ANYWHERE);

				AccInfo accInfo2 = new AccInfo();
				accInfo2.setUserNum(bean.getUserNum());
				accInfo2.setAccType(bean.getTranType());
				accInfo2.setCcy(bean.getAnaCcy());
				AccInfo anaAccInfo = accInfoDao.getBeanByBean(accInfo2,MatchMode.ANYWHERE);

				Double weCcyMoney = Double.parseDouble(weAccInfo.getAmount());// 获取weCcy币种对应的账户余额
				Double anaCcyMoney = Double.parseDouble(anaAccInfo.getAmount());// 获取anaCcy币种对应的账户余额
				Double price = Double.parseDouble(bean.getPrice()) + Double.parseDouble(bean.getPoint()) / 10000;// 掉期交易的价格(约定价格)
				Double amt = Double.parseDouble(bean.getCAmount());// 掉期交易的实际交易金额

				//2.交换本金
				if (bean.getDirection().equals("1")) {
					// 买
					// weCcy币种对应的账户金额 = 余额-实际交易金额*价格
					// anaCcy币种对应的账户金额 = 余额+实际交易金额
					if(weCcyMoney - amt * price < 0){
						logger.error("用户：" + bean.getUserNum() + " 添加掉期询价交易失败！账户"+ accInfo.getCcy() +"金额不足");
						BoException be = new BoException("otcSwapTradeAdd");
						if (accInfo.getAccType().equals("C"))
							be.setExceptionType("人民币账户"+ weAccInfo.getCcy() +"金额不足,掉期询价交易易提交失败！");
						else
							be.setExceptionType("外币账户"+ weAccInfo.getCcy() +"金额不足,掉期询价交易提交失败！");
						throw be;
					}
					weAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(weCcyMoney - amt * price));
					anaAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(anaCcyMoney + amt));
				} else {
					// 卖
					// weCcy币种对应的账户金额 = 余额-实际交易金额
					// anaCcy币种对应的账户金额 = 余额+实际交易金额*价格
					if(weCcyMoney - amt < 0){
						logger.error("用户：" + bean.getUserNum() + " 添加掉期询价交易失败！账户"+ accInfo.getCcy() +"金额不足");
						BoException be = new BoException("otcSwapTradeAdd");
						if (accInfo.getAccType().equals("C"))
							be.setExceptionType("人民币账户"+ weAccInfo.getCcy() +"金额不足,掉期询价交易易提交失败！");
						else
							be.setExceptionType("外币账户"+ weAccInfo.getCcy() +"金额不足,掉期询价交易提交失败！");
						throw be;
					}
					weAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(weCcyMoney - amt));
					anaAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(anaCcyMoney + price * amt));
				}

				// 3.更新交易流水表
				if(list.get(i).getTranType().equals("C")){
					CTranFlowMapping c = new CTranFlowMapping();
					c.setUserNum(bean.getUserNum());
					CTranFlowMapping cTranFlowMapping = cTranFlowMappingDao.getBeanByBean(c, MatchMode.ANYWHERE);
					// 远期交易次数加1，远期交易量加交易金额 ， 总交易次数加一，总交易量加交易金额
					cTranFlowMapping.setOtcSwapQty((Integer.parseInt(cTranFlowMapping.getOtcSwapQty()) + 1) + "");
					cTranFlowMapping.setOtcSwapAmt(FormatParamUtil.getAmountAndPriceFmtByDouble(Double.parseDouble(cTranFlowMapping.getOtcSwapAmt()) + amt));
					cTranFlowMapping.setCount(cTranFlowMapping.getCount() + 1);
					cTranListToUpdate.add(cTranFlowMapping);
				}else {
					WTranFlowMapping w = new WTranFlowMapping();
					w.setUserNum(bean.getUserNum());
					WTranFlowMapping wTranFlowMapping = wTranFlowMappingDao.getBeanByBean(w, MatchMode.ANYWHERE);
					// 远期交易次数加1，远期交易量加交易金额 ， 总交易次数加一，总交易量加交易金额
					wTranFlowMapping.setOtcSwapQty((Integer.parseInt(wTranFlowMapping.getOtcSwapQty()) + 1) + "");
					wTranFlowMapping.setOtcSwapAmt(FormatParamUtil.getAmountAndPriceFmtByDouble(Double.parseDouble(wTranFlowMapping.getOtcSwapAmt()) + amt));
					wTranFlowMapping.setCount(wTranFlowMapping.getCount() + 1);
					wTranListToUpdate.add(wTranFlowMapping);
				}
				
				//3.设置下一个付息周期
				int frequency = Integer.parseInt(bean.getFrequency());
				bean.setDate(getNextFrequency(today, frequency));
				
				// 4.把相关的bean更新到对应的表
				swapListToUpdate.add(bean);
				accListToUpdate.add(weAccInfo);
				accListToUpdate.add(anaAccInfo);
			}
			//批量提交
			if(swapListToUpdate.size() > 0){
				otcSwapDao.batchUpdate(swapListToUpdate);
			}
			if(cTranListToUpdate.size() > 0){
				cTranFlowMappingDao.batchUpdate(cTranListToUpdate);
			}
			if(wTranListToUpdate.size() > 0){
				wTranFlowMappingDao.batchUpdate(wTranListToUpdate);
			}
			if(accListToUpdate.size() > 0){
				accInfoDao.batchUpdate(accListToUpdate);
			}
			TransactionNestUtil.releaseRef(rsf);
		} catch (Exception e) {
			TransactionNestUtil.releaseRef(rsf);
			if (!TransactionNestUtil.isReference()) {
				HibernateUtil.rollbackTransaction();
			}
			logger.error("掉期交易近端交易轮询出错！" + e.getMessage());
		} finally {
			if (!TransactionNestUtil.isReference()) {
				HibernateUtil.closeSession();			
			}
		}
	}
	
	public static String getNextFrequency(String today, int frequency){
		int year = Integer.parseInt(today.substring(0, 4));
		int month = Integer.parseInt(today.substring(4, 6));
		int day = Integer.parseInt(today.substring(6, 8));
		switch(frequency){
		case 0://隔夜
			if(day == getDaysByMonth(month)){
				if(month == 12){
					year++;
					month = 1;
					day = 1;
				}
				else{
					month++;
					day = 1;
				}
			}
			else{
				day++;
			}
			break;
		case 1://一周
			if(day + 7 >= getDaysByMonth(month)){
				if(month == 12){
					year++;
					month = 1;
					day = 7 - (getDaysByMonth(month) - day);
				}
				else{
					month++;
					day = 7 - (getDaysByMonth(month) - day);
				}
			}
			else{
				day = day + 7;
			}
			break;
		case 2://一个月
			if(month == 12){
				year++;
				month = 1;
			}
			else{
				month++;
			}
			break;
		case 3://两个月
			if(month > 10){
				year++;
				month = month + 2 - 12;
			}
			else{
				month = month + 2;
			}
			break;
		case 4://一季度
			if(month > 9){
				year++;
				month = month + 3 - 12;
			}
			else{
				month = month + 3;
			}
			break;
		case 5://半年
			if(month > 6){
				year++;
				month = month + 6 - 12;
			}
			else{
				month = month + 6;
			}
			break;
		case 6://一年
			year++;
			break;
		}
		String m = month + "";
		String d = day + "";
		if(month < 10){
			m = "0" + m;
		}
		if(day < 10){
			d = "0" + d;
		}
		String date = year + m +d;
		return date;
	}
	
	public static int getDaysByMonth(int month){
		int day = 0;
		switch(month){
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			day = 31;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			day = 30;
			break;
		case 2:
			day = 28;
			break;
		}
		return day;
	}
	
	/**
	 * 这个方法是被定时器轮询执行  如果交易的日期与服务器系统日期符合则执行
	 */
	//掉期交易中的支付周期交易
	public static void checkSwapFrequencyDate(){
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~swap支付周期交易轮询开始啦！！~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		OtcSwapDao otcSwapDao = new OtcSwapDao();
		AccInfoDao accInfoDao = new AccInfoDao();
		String rsf = null;
		try {
			rsf = TransactionNestUtil.reference();
			OtcSwapInfo otcSwapInfo = new OtcSwapInfo();
			otcSwapInfo.setStatue("A");
			String today = GetDateTimeUtil.getCurrentDate();
			otcSwapInfo.setDate(today);
			List<OtcSwapInfo> list = (List<OtcSwapInfo>)otcSwapDao.getBeansByBean(otcSwapInfo, MatchMode.ANYWHERE);
			
			//因为updateBean方法无法使用   只能通过批量提交了
			ArrayList<OtcSwapInfo> swapListToUpdate = new ArrayList<OtcSwapInfo>();
			ArrayList<AccInfo> accListToUpdate = new ArrayList<AccInfo>();
			for (int i = 0; i < list.size(); i++) {
				OtcSwapInfo bean = list.get(i);
				// 1.更新账户表acc_info,先得到交易币种的账户bean
				// weCcy卖出货币 anaCcy是买入货币
				AccInfo accInfo = new AccInfo();
				accInfo.setUserNum(bean.getUserNum());
				accInfo.setAccType(bean.getTranType());
				accInfo.setCcy(bean.getWeCcy());
				AccInfo weAccInfo = accInfoDao.getBeanByBean(accInfo,MatchMode.ANYWHERE);

				AccInfo accInfo2 = new AccInfo();
				accInfo2.setUserNum(bean.getUserNum());
				accInfo2.setAccType(bean.getTranType());
				accInfo2.setCcy(bean.getAnaCcy());
				AccInfo anaAccInfo = accInfoDao.getBeanByBean(accInfo2,MatchMode.ANYWHERE);

				Double weCcyMoney = Double.parseDouble(weAccInfo.getAmount());// 获取weCcy币种对应的账户余额
				Double anaCcyMoney = Double.parseDouble(anaAccInfo.getAmount());// 获取anaCcy币种对应的账户余额
				Double price = Double.parseDouble(bean.getPrice()) + Double.parseDouble(bean.getPoint()) / 10000;// 掉期交易的价格(约定价格)
				Double amt = Double.parseDouble(bean.getCAmount());// 掉期交易的实际交易金额

				//2.计算周期利息
				double receiveRate = 0d;
				double payRate = 0d;
				if(bean.getFixedType().equals("0")){//receive weccy fixedRate,pay anaccy floatingRate
					//对于receive weccy fixedRate
					double rRate = Double.parseDouble(bean.getReceiveRate()) / 100;
					double basis1 = (double)Integer.parseInt(bean.getCBasis()) / 10000;
					receiveRate = rRate + basis1;
					
					//对于pay anaccy floatingRate
					double libor = 0d;
					List<LiborModel> liborModels = (List<LiborModel>)GetRateUtil.getInstance().getLibor();
					for(LiborModel liborModel : liborModels){
						if(liborModel.getCcy().equals(bean.getAnaCcy())){
							int liborType = Integer.parseInt(bean.getLibor());
							switch(liborType){
							case 0:
								libor = Double.parseDouble(liborModel.getOneDay());
								break;
							case 1:
								libor = Double.parseDouble(liborModel.getOneWeek());
								break;
							case 2:
								libor = Double.parseDouble(liborModel.getOneMonth());
								break;
							case 3:
								libor = Double.parseDouble(liborModel.getTwoMonth());
								break;
							case 4:
								libor = Double.parseDouble(liborModel.getThreeMonth());
								break;
							case 5:
								libor = Double.parseDouble(liborModel.getSixMonth());
								break;
							case 6:
								libor = Double.parseDouble(liborModel.getTwelveMonth());
								break;
							}
							break;
						}
					}
					double basis2 = (double)Integer.parseInt(bean.getFBasis()) / 10000;
					payRate = libor / 100 + basis2;
				}
				else{//pay anaccy fixedRate,receive weccy floatingRate
					//对于pay anaccy fixedRate
					double pRate = Double.parseDouble(bean.getPayRate()) / 100;
					double basis1 = (double)Integer.parseInt(bean.getCBasis()) / 10000;
					payRate = pRate + basis1;
					
					//对于receive weccy floatingRate
					double libor = 0d;
					List<LiborModel> liborModels = (List<LiborModel>)GetRateUtil.getInstance().getLibor();
					for(LiborModel liborModel : liborModels){
						if(liborModel.getCcy().equals(bean.getWeCcy())){
							int liborType = Integer.parseInt(bean.getLibor());
							switch(liborType){
							case 0:
								libor = Double.parseDouble(liborModel.getOneDay());
								break;
							case 1:
								libor = Double.parseDouble(liborModel.getOneWeek());
								break;
							case 2:
								libor = Double.parseDouble(liborModel.getOneMonth());
								break;
							case 3:
								libor = Double.parseDouble(liborModel.getTwoMonth());
								break;
							case 4:
								libor = Double.parseDouble(liborModel.getThreeMonth());
								break;
							case 5:
								libor = Double.parseDouble(liborModel.getSixMonth());
								break;
							case 6:
								libor = Double.parseDouble(liborModel.getTwelveMonth());
								break;
							}
							break;
						}
					}
					double basis2 = (double)Integer.parseInt(bean.getFBasis()) / 10000;
					receiveRate = libor / 100 + basis2;
				}
				int frequency = Integer.parseInt(bean.getFrequency());
				int days = 0;
				switch(frequency){
				case 0:
					days = 1;
					break;
				case 1:
					days = 7;
					break;
				case 2:
					days = 30;
					break;
				case 3:
					days = 60;
					break;
				case 4:
					days = 90;
					break;
				case 5:
					days = 180;
					break;
				case 6:
					days = 360;
					break;
				}
				if(bean.getDirection().equals("1")){//买
					double receiveInterest = amt * price * receiveRate * days / 360;
					weAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(weCcyMoney + receiveInterest));
					
					double payInterest = amt * payRate * days / 360;
					if(anaCcyMoney - payInterest < 0){
						logger.error("用户：" + bean.getUserNum() + " 添加掉期询价交易失败！账户"+ accInfo.getCcy() +"金额不足");
						BoException be = new BoException("otcSwapTradeAdd");
						if (accInfo.getAccType().equals("C"))
							be.setExceptionType("人民币账户"+ weAccInfo.getCcy() +"金额不足,掉期询价交易提交失败！");
						else
							be.setExceptionType("外币账户"+ weAccInfo.getCcy() +"金额不足,掉期询价交易提交失败！");
						throw be;
					}
					anaAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(anaCcyMoney - payInterest));
				}
				else{//卖
					double receiveInterest = amt * receiveRate * days / 360;
					weAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(weCcyMoney + receiveInterest));
					
					double payInterest = amt * price * payRate * days / 360;
					if(anaCcyMoney - payInterest < 0){
						logger.error("用户：" + bean.getUserNum() + " 添加掉期询价交易失败！账户"+ accInfo.getCcy() +"金额不足");
						BoException be = new BoException("otcSwapTradeAdd");
						if (accInfo.getAccType().equals("C"))
							be.setExceptionType("人民币账户"+ weAccInfo.getCcy() +"金额不足,掉期询价交易提交失败！");
						else
							be.setExceptionType("外币账户"+ weAccInfo.getCcy() +"金额不足,掉期询价交易提交失败！");
						throw be;
					}
					anaAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(anaCcyMoney - payInterest));
				}
				
				//3.设置下一个付息周期
				String nextDay = getNextFrequency(today, frequency);
				String maturityDate = bean.getMaturityDate();
				int nd = Integer.parseInt(nextDay);
				int md = Integer.parseInt(maturityDate);
				if(nd < md){//下一个利息支付日不能超过结息日
					bean.setDate(nextDay);
				}
				
				// 4.把相关的bean更新到对应的表
				swapListToUpdate.add(bean);
				accListToUpdate.add(weAccInfo);
				accListToUpdate.add(anaAccInfo);
			}
			//批量提交
			if(swapListToUpdate.size() > 0){
				otcSwapDao.batchUpdate(swapListToUpdate);
			}
			if(accListToUpdate.size() > 0){
				accInfoDao.batchUpdate(accListToUpdate);
			}
			TransactionNestUtil.releaseRef(rsf);
		} catch (Exception e) {
			TransactionNestUtil.releaseRef(rsf);
			if (!TransactionNestUtil.isReference()) {
				HibernateUtil.rollbackTransaction();
			}
			logger.error("掉期交易周期支付交易轮询出错！" + e.getMessage());
		} finally {
			if (!TransactionNestUtil.isReference()) {
				HibernateUtil.closeSession();			
			}
		}
	}
	
	/**
	 * 这个方法是被定时器轮询执行  如果交易的日期与服务器系统日期符合则执行
	 */
	//掉期交易中的远端交易！
	public static void checkSwapMaturityDate(){
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~swap远端交易轮询开始啦！！~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		OtcSwapDao otcSwapDao = new OtcSwapDao();
		AccInfoDao accInfoDao = new AccInfoDao();
		String rsf=null;
		try {
			rsf = TransactionNestUtil.reference();
			OtcSwapInfo otcSwapInfo = new OtcSwapInfo();
			otcSwapInfo.setStatue("A");
			otcSwapInfo.setMaturityDate(GetDateTimeUtil.getCurrentDate());
			List<OtcSwapInfo> list = (List<OtcSwapInfo>)otcSwapDao.getBeansByBean(otcSwapInfo, MatchMode.ANYWHERE);
			
			//因为updateBean方法无法使用   只能通过批量提交了
			ArrayList<OtcSwapInfo> swapListToUpdate = new ArrayList<OtcSwapInfo>();
			ArrayList<AccInfo> accListToUpdate = new ArrayList<AccInfo>();
			for (int i = 0; i < list.size(); i++) {
				OtcSwapInfo bean = list.get(i);
				// 1.更新账户表acc_info,先得到交易币种的账户bean
				// weCcy卖出货币 anaCcy是买入货币
				AccInfo accInfo = new AccInfo();
				accInfo.setUserNum(bean.getUserNum());
				accInfo.setAccType(bean.getTranType());
				accInfo.setCcy(bean.getAnaCcy());
				AccInfo anaAccInfo = accInfoDao.getBeanByBean(accInfo,MatchMode.ANYWHERE);

				AccInfo accInfo2 = new AccInfo();
				accInfo2.setUserNum(bean.getUserNum());
				accInfo2.setAccType(bean.getTranType());
				accInfo2.setCcy(bean.getWeCcy());
				AccInfo weAccInfo = accInfoDao.getBeanByBean(accInfo2,MatchMode.ANYWHERE);

				Double weCcyMoney = Double.parseDouble(weAccInfo.getAmount());// weCcy币种对应的账户余额
				Double anaCcyMoney = Double.parseDouble(anaAccInfo.getAmount());// anaCcy币种对应的账户余额
				Double price = Double.parseDouble(bean.getPrice()) + Double.parseDouble(bean.getPoint()) / 10000;// 掉期交易的价格(约定价格)
				Double amt = Double.parseDouble(bean.getFAmount());// 掉期交易的实际交易金额

				//2.换回本金
				if (bean.getDirection().equals("0")) {//交易方向跟近端的相反！
					// 卖
					// weCcy币种对应的账户金额 = 余额+实际交易金额*价格
					// anaCcy币种对应的账户金额 = 余额-实际交易金额
					if(anaCcyMoney - amt * price < 0){
						logger.error("用户：" + bean.getUserNum() + " 添加掉期询价交易失败！账户"+ accInfo.getCcy() +"金额不足");
						BoException be = new BoException("otcSwapTradeAdd");
						if (accInfo.getAccType().equals("C"))
							be.setExceptionType("人民币账户"+ anaAccInfo.getCcy() +"金额不足,掉期询价交易易提交失败！");
						else
							be.setExceptionType("外币账户"+ anaAccInfo.getCcy() +"金额不足,掉期询价交易提交失败！");
						throw be;
					}
					anaAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(anaCcyMoney - amt * price));
					weAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(weCcyMoney + amt));
				} else {
					// 买
					// weCcy币种对应的账户金额 = 余额+实际交易金额*价格
					// anaCcy币种对应的账户金额 = 余额-实际交易金额
					if(anaCcyMoney - amt < 0){
						logger.error("用户：" + bean.getUserNum() + " 添加掉期询价交易失败！账户"+ accInfo.getCcy() +"金额不足");
						BoException be = new BoException("otcSwapTradeAdd");
						if (accInfo.getAccType().equals("C"))
							be.setExceptionType("人民币账户"+ anaAccInfo.getCcy() +"金额不足,掉期询价交易易提交失败！");
						else
							be.setExceptionType("外币账户"+ anaAccInfo.getCcy() +"金额不足,掉期询价交易提交失败！");
						throw be;
					}
					anaAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(anaCcyMoney - amt));
					weAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(weCcyMoney + price * amt));
				}
				bean.setStatue("D");

				// 4.把相关的bean更新到对应的表
				swapListToUpdate.add(bean);
				accListToUpdate.add(anaAccInfo);
				accListToUpdate.add(weAccInfo);
			}
			//批量提交
			if(swapListToUpdate.size() > 0){
				otcSwapDao.batchUpdate(swapListToUpdate);
			}
			if(accListToUpdate.size() > 0){
				accInfoDao.batchUpdate(accListToUpdate);
			}
			TransactionNestUtil.releaseRef(rsf);
		} catch (Exception e) {
			TransactionNestUtil.releaseRef(rsf);
			if (!TransactionNestUtil.isReference()) {
				HibernateUtil.rollbackTransaction();
			}
			logger.error("掉期交易远端交易轮询出错！" + e.getMessage());
		} finally {
			if (!TransactionNestUtil.isReference()) {
				HibernateUtil.closeSession();			
			}
		}
	}
}
