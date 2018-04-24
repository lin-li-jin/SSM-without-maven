package com.talent.forex.util;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.hibernate.criterion.MatchMode;

import com.talent.forex.bean.domain.AccInfo;
import com.talent.forex.bean.domain.CTranFlowMapping;
import com.talent.forex.bean.domain.StopLossInfo;
import com.talent.forex.bean.domain.WTranFlowMapping;
import com.talent.forex.dao.AccInfoDao;
import com.talent.forex.dao.CTranFlowMappingDao;
import com.talent.forex.dao.StopLossDao;
import com.talent.forex.dao.WTranFlowMappingDao;
import com.talent.hibernate.util.HibernateUtil;
import com.talent.hibernate.util.TransactionNestUtil;

//stoploss轮询
public class StopLossPollingUtil {
	
	private static Logger logger = Logger.getLogger(StopLossPollingUtil.class);
	
	/**
	 * 判断是否达到stopLoss的执行条件，如果是则执行
	 */
	public static void stopLossCheck(){
		System.out.println("----------stop轮询已执行一遍-------------");
		StopLossDao stopLossDao = new StopLossDao();
		String rsf=null;
		try {
			rsf = TransactionNestUtil.reference();
			StopLossInfo s = new StopLossInfo();
			s.setStatue("A");
			ArrayList<StopLossInfo> list =(ArrayList<StopLossInfo>)stopLossDao.getBeansByBean(s, MatchMode.ANYWHERE);
			for(int i=0;i<list.size();i++){
				if(DateCompareUtil.dateCompare(list.get(i).getGoodFrom(), list.get(i).getGoodTill()).equals("execute")){
					executeStopLoss(list.get(i)); 
				}else if(DateCompareUtil.dateCompare(list.get(i).getGoodFrom(), list.get(i).getGoodTill()).equals("invalid")){
					setStopLossInvalid(list.get(i));
				}
			}
			TransactionNestUtil.releaseRef(rsf);
		} catch (Exception e) {
			TransactionNestUtil.releaseRef(rsf);
			if (!TransactionNestUtil.isReference()) {
				HibernateUtil.rollbackTransaction();
			}
			logger.error("stopLoss轮询出错！" + e.getMessage());
		} finally {
			if (!TransactionNestUtil.isReference()) {
				HibernateUtil.closeSession();			
			}
		}
	}
	
	
	/**
	 * 执行一条stopLoss 内部调用
	 * @param bean
	 */
	public static void executeStopLoss(StopLossInfo bean){
		if(bean.getDirection().equals("0")){
			if(bean.getMonitorPrice().equals("BID")){
				if(!RateUtil.getRateByCCy(bean.getTranType(),bean.getWeCcy()+bean.getAnaCcy()).getBidValue().equals(bean.getPrice())){
					//System.out.println(RateUtil.getRateByCCy(bean.getTranType(),bean.getWeCcy()+bean.getAnaCcy()).getBidValue());
					//System.out.println(bean.getPrice()); 
					return;
				}
			}else{
				if(!RateUtil.getRateByCCy(bean.getTranType(),bean.getWeCcy()+bean.getAnaCcy()).getAskValue().equals(bean.getPrice())){
					return;
				}
			}
		}else{
			if(bean.getMonitorPrice().equals("BID")){
				if(!RateUtil.getRateByCCy(bean.getTranType(),bean.getAnaCcy()+bean.getWeCcy()).getBidValue().equals(bean.getPrice())){
					//System.out.println(RateUtil.getRateByCCy(bean.getTranType(),bean.getAnaCcy()+bean.getWeCcy()).getBidValue());
					//System.out.println(bean.getPrice()); 
					return;
				}
			}else{
				if(!RateUtil.getRateByCCy(bean.getTranType(),bean.getAnaCcy()+bean.getWeCcy()).getAskValue().equals(bean.getPrice())){
					return;
				}
			}
		}
		
		
		StopLossDao stopLossDao = new StopLossDao();
		AccInfoDao accInfoDao = new AccInfoDao();
		CTranFlowMappingDao cTranFlowMappingDao = new CTranFlowMappingDao();
		WTranFlowMappingDao wTranFlowMappingDao = new WTranFlowMappingDao();
		//1.更新账户表acc_info,先得到两个要交易的币种的账户bean
		//weCcy卖出货币  anaCcy是买入货币
		AccInfo a1 = new AccInfo();
		a1.setUserNum(bean.getUserNum());
		a1.setAccType(bean.getTranType());
		a1.setCcy(bean.getWeCcy());
		AccInfo weAccInfo = accInfoDao.getBeanByBean(a1, MatchMode.ANYWHERE);
		
		AccInfo a2 = new AccInfo();
		a2.setUserNum(bean.getUserNum());
		a2.setAccType(bean.getTranType());
		a2.setCcy(bean.getAnaCcy());
		AccInfo anaAccInfo = accInfoDao.getBeanByBean(a2, MatchMode.ANYWHERE);
		
		Double weCcyMoney = Double.parseDouble(weAccInfo.getAmount());//weCcy币种对应的账户余额
		Double anaCcyMoney = Double.parseDouble(anaAccInfo.getAmount());//anaCcy币种对应的账户余额
		Double amount = Double.parseDouble(bean.getAmount());//Stop Loss交易的实际交易金额
		
		
		//weCcy币种对应的账户金额     = 余额-实际交易金额
		//anaCcy币种对应的账户金额   = 余额+实际交易金额*价格
		Double amt =0.0;
		if (bean.getDirection().equals("0")){
			if(bean.getMonitorPrice().equals("BID")){
				amt = Double.parseDouble(bean.getAmount()) * Double.parseDouble(bean.getPrice());//兑换汇率后另一币种金额
			}else{
				amt = Double.parseDouble(bean.getAmount()) * Double.parseDouble(RateUtil.getRateByCCy(bean.getTranType(),bean.getWeCcy()+bean.getAnaCcy()).getBidValue());//兑换汇率后另一币种金额
			}
			weAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(weCcyMoney-amount));
			anaAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(anaCcyMoney+amt));
		}
		else{
			if(bean.getMonitorPrice().equals("BID")){
				amt = Double.parseDouble(bean.getAmount()) * Double.parseDouble(RateUtil.getRateByCCy(bean.getTranType(),bean.getAnaCcy()+bean.getWeCcy()).getAskValue());//兑换汇率后另一币种金额
			}else{
				amt = Double.parseDouble(bean.getAmount()) * Double.parseDouble(bean.getPrice());//兑换汇率后另一币种金额
			}
			weAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(weCcyMoney-amt));
			anaAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(anaCcyMoney+amount));
		}
		
		//2.更新人民币交易流水表c_tran_flow_mapping
		if (bean.getTranType().equals("C")){
			CTranFlowMapping c = new CTranFlowMapping();
			c.setUserNum(bean.getUserNum());
			CTranFlowMapping ctf = cTranFlowMappingDao.getBeanByBean(c, MatchMode.ANYWHERE);
			//Stop Loss交易次数加1，Stop Loss交易量加交易金额 ， 总交易次数加一，总交易量加交易金额
			ctf.setStopLossQty(String.valueOf(Integer.parseInt(ctf.getStopLossQty()) + 1));
			ctf.setStopLossAmt(FormatParamUtil.getAmountAndPriceFmtByDouble(Double.parseDouble(ctf.getStopLossAmt()) + amount));
			ctf.setCount(ctf.getCount() + 1);
			ctf.setAmount(ctf.getAmount() + amount);
			ArrayList list1 = new ArrayList();
			list1.add(ctf);
			cTranFlowMappingDao.batchUpdate(list1);
		}
		else{//更新外币交易流水表w_tran_flow_mapping
			WTranFlowMapping w = new WTranFlowMapping();
			w.setUserNum(bean.getUserNum());
			WTranFlowMapping wtf = wTranFlowMappingDao.getBeanByBean(w, MatchMode.ANYWHERE);
			//Stop Loss交易次数加1，Stop Loss交易量加交易金额 ， 总交易次数加一，总交易量加交易金额
			wtf.setStopLossQty(String.valueOf(Integer.parseInt(wtf.getStopLossQty()) + 1));
			wtf.setStopLossAmt(FormatParamUtil.getAmountAndPriceFmtByDouble(Double.parseDouble(wtf.getStopLossAmt()) + amount));
			wtf.setCount(wtf.getCount() + 1);
			wtf.setAmount(wtf.getAmount() + amount);
			ArrayList list1 = new ArrayList();
			list1.add(wtf);
			wTranFlowMappingDao.batchUpdate(list1);
		}
		
		//3.更新bean的交易状态为完成
		bean.setStatue("D");
		bean.setLAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(amt));
		
		//4.把相关的bean更新到对应的表
		
		ArrayList list2 = new ArrayList();
		ArrayList list3 = new ArrayList();
		list2.add(bean);
		list3.add(weAccInfo);
		list3.add(anaAccInfo);
		stopLossDao.batchUpdate(list2);
		accInfoDao.batchUpdate(list3);
			
	}
	
	/**
	 * 如果stoploss过了交易日期   则把交易设置成invalid,并且update到交易流水表中，对应交易类型的交易量+0；交易次数+1
	 * @param bean
	 */
	public static void  setStopLossInvalid(StopLossInfo bean){
		CTranFlowMappingDao cTranFlowMappingDao = new CTranFlowMappingDao();
		WTranFlowMappingDao wTranFlowMappingDao = new WTranFlowMappingDao();
		StopLossDao stopLossDao = new StopLossDao();
		bean.setStatue("I");
		ArrayList list = new ArrayList();
		list.add(bean);
		stopLossDao.batchUpdate(list);
		
		//2.更新人民币交易流水表c_tran_flow_mapping
		if (bean.getTranType().equals("C")){      
			
			CTranFlowMapping c = new CTranFlowMapping();
			c.setUserNum(bean.getUserNum());
			CTranFlowMapping ctf = cTranFlowMappingDao.getBeanByBean(c, MatchMode.ANYWHERE);
			//Stop Loss交易次数加1，Stop Loss交易量加交易金额 ， 总交易次数加一，总交易量加交易金额
			ctf.setStopLossQty(String.valueOf(Integer.parseInt(ctf.getStopLossQty()) + 1));
			ctf.setCount(ctf.getCount() + 1);
			ArrayList list1 = new ArrayList();
			list1.add(ctf);
			cTranFlowMappingDao.batchUpdate(list1);
		}
		else{//更新外币交易流水表w_tran_flow_mapping
			WTranFlowMapping w = new WTranFlowMapping();
			w.setUserNum(bean.getUserNum());
			WTranFlowMapping wtf = wTranFlowMappingDao.getBeanByBean(w, MatchMode.ANYWHERE);
			//Stop Loss交易次数加1，Stop Loss交易量加交易金额 ， 总交易次数加一，总交易量加交易金额
			wtf.setStopLossQty(String.valueOf(Integer.parseInt(wtf.getStopLossQty()) + 1));
			wtf.setCount(wtf.getCount() + 1);
			ArrayList list1 = new ArrayList();
			list1.add(wtf);
			wTranFlowMappingDao.batchUpdate(list1);
		}
	}
}
