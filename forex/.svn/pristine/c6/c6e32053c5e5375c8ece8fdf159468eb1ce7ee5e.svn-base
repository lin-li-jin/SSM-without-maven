package com.talent.forex.util;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.hibernate.criterion.MatchMode;

import com.talent.forex.bean.domain.AccInfo;
import com.talent.forex.bean.domain.CTranFlowMapping;
import com.talent.forex.bean.domain.TakeProfitInfo;
import com.talent.forex.bean.domain.WTranFlowMapping;
import com.talent.forex.dao.AccInfoDao;
import com.talent.forex.dao.CTranFlowMappingDao;
import com.talent.forex.dao.TakeProfitDao;
import com.talent.forex.dao.WTranFlowMappingDao;
import com.talent.hibernate.util.HibernateUtil;
import com.talent.hibernate.util.TransactionNestUtil; 

public class TakeProfitPollingUtil {
	private static Logger logger = Logger.getLogger(TakeProfitPollingUtil.class);
	
	/**
	 * 判断是否达到takeprofit的执行条件，如果是则执行
	 */
	public static void takeProfitCheck(){
		System.out.println("----------takeProfit轮询已执行一遍-------------");
		TakeProfitDao takeProfitDao = new TakeProfitDao();
		String rsf=null;
		try {
			rsf = TransactionNestUtil.reference();
			TakeProfitInfo t = new TakeProfitInfo();
			t.setStatue("A");
			ArrayList<TakeProfitInfo> list =(ArrayList<TakeProfitInfo>)takeProfitDao.getBeansByBean(t, MatchMode.ANYWHERE);
			for(int i=0;i<list.size();i++){
				if(DateCompareUtil.dateCompare(list.get(i).getGoodFrom(), list.get(i).getGoodTill()).equals("execute")){ 
					excuteTakeProfit(list.get(i)); 
				}else if(DateCompareUtil.dateCompare(list.get(i).getGoodFrom(), list.get(i).getGoodTill()).equals("invalid")){
					setTakeProfitInvalid(list.get(i));
				}
			}
			TransactionNestUtil.releaseRef(rsf);
		} catch (Exception e) {
			TransactionNestUtil.releaseRef(rsf);
			if (!TransactionNestUtil.isReference()) {
				HibernateUtil.rollbackTransaction();
			}
			logger.error("takeProfit轮询出错！" + e.getMessage());
		} finally {
			if (!TransactionNestUtil.isReference()) {
				HibernateUtil.closeSession();			
			}
		}
	}
	
	
	/**
	 * 执行一条Take Profit交易
	 * 功能：在Take Profit交易表把这条Take Profit交易的交易状态改成DONE,更新账户交易统计表,账户表
	 */
	public static void excuteTakeProfit(TakeProfitInfo bean){

		
		if(bean.getDirection().equals("0")){
			if(!RateUtil.getRateByCCy(bean.getTranType(),bean.getWeCcy()+bean.getAnaCcy()).getBidValue().equals(bean.getPrice())){
				//System.out.println(RateUtil.getRateByCCy(bean.getTranType(),bean.getWeCcy()+bean.getAnaCcy()).getBidValue());
				//System.out.println(bean.getPrice()); 
				return;
			}
		}else{
			if(!RateUtil.getRateByCCy(bean.getTranType(),bean.getAnaCcy()+bean.getWeCcy()).getAskValue().equals(bean.getPrice())){
				//System.out.println(RateUtil.getRateByCCy(bean.getTranType(),bean.getAnaCcy()+bean.getWeCcy()).getAskValue());
				//System.out.println(bean.getPrice()); 
				return;
			}

		}
		
		TakeProfitDao takeProfitDao = new TakeProfitDao();
		AccInfoDao accInfoDao = new AccInfoDao();
		CTranFlowMappingDao cTranFlowMappingDao = new CTranFlowMappingDao();
		WTranFlowMappingDao wTranFlowMappingDao = new WTranFlowMappingDao();
	
		//1.更新账户表acc_info,先得到保证金里的两个要交易的币种的账户bean
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
		Double amount = Double.parseDouble(bean.getAmount());//Take Profit交易的实际交易金额
		Double amt = Double.parseDouble(bean.getAmount()) * Double.parseDouble(bean.getPrice());//兑换汇率后另一币种金额
		
		//weCcy币种对应的账户金额     = 余额-实际交易金额
		//anaCcy币种对应的账户金额   = 余额+实际交易金额*价格
		if (bean.getDirection().equals("0")){
			weAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(weCcyMoney-amount));
			anaAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(anaCcyMoney+amt));
		}
		else{
			weAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(weCcyMoney-amt));
			anaAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(anaCcyMoney+amount));
		}
		
		//2.更新人民币交易流水表c_tran_flow_mapping
		if (bean.getTranType().equals("C")){
			CTranFlowMapping c = new CTranFlowMapping();
			c.setUserNum(bean.getUserNum());
			CTranFlowMapping ctf = cTranFlowMappingDao.getBeanByBean(c, MatchMode.ANYWHERE);
			//Take Profit交易次数加1，Take Profit交易量加交易金额 ， 总交易次数加一，总交易量加交易金额
			ctf.setTakeProfitQty(String.valueOf(Integer.parseInt(ctf.getTakeProfitQty()) + 1));
			ctf.setTakeProfitAmt(FormatParamUtil.getAmountAndPriceFmtByDouble(Double.parseDouble(ctf.getTakeProfitAmt()) + amount));
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
			//Take Profit交易次数加1，Take Profit交易量加交易金额 ， 总交易次数加一，总交易量加交易金额
			wtf.setTakeProfitQty(String.valueOf(Integer.parseInt(wtf.getTakeProfitQty()) + 1));
			wtf.setTakeProfitAmt(FormatParamUtil.getAmountAndPriceFmtByDouble(Double.parseDouble(wtf.getTakeProfitAmt()) + amount));
			wtf.setCount(wtf.getCount() + 1);
			wtf.setAmount(wtf.getAmount() + amount);
			ArrayList list1 = new ArrayList();
			list1.add(wtf);
			cTranFlowMappingDao.batchUpdate(list1);
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
		takeProfitDao.batchUpdate(list2);
		accInfoDao.batchUpdate(list3);


	}
	
	
	/**
	 * 如果takeProfit过了交易日期   则把交易设置成invalid,并且update到交易流水表中，对应交易类型的交易量+0；交易次数+1
	 * @param bean
	 */
	public static void  setTakeProfitInvalid(TakeProfitInfo bean){
		CTranFlowMappingDao cTranFlowMappingDao = new CTranFlowMappingDao();
		WTranFlowMappingDao wTranFlowMappingDao = new WTranFlowMappingDao();
		TakeProfitDao takeProfitDao = new TakeProfitDao();
		bean.setStatue("I");
		ArrayList list = new ArrayList();
		list.add(bean);
		takeProfitDao.batchUpdate(list);
		
		//2.更新人民币交易流水表c_tran_flow_mapping
		if (bean.getTranType().equals("C")){
			CTranFlowMapping c = new CTranFlowMapping();
			c.setUserNum(bean.getUserNum());
			CTranFlowMapping ctf = cTranFlowMappingDao.getBeanByBean(c, MatchMode.ANYWHERE);
			//take profit交易次数加1，Stop Loss交易量加交易金额 ， 总交易次数加一，总交易量加交易金额
			ctf.setTakeProfitQty(String.valueOf(Integer.parseInt(ctf.getTakeProfitQty()) + 1));
			ctf.setCount(ctf.getCount() + 1);
			ArrayList list1 = new ArrayList();
			list1.add(ctf);
			cTranFlowMappingDao.batchUpdate(list1);
		}
		else{//更新外币交易流水表w_tran_flow_mapping
			WTranFlowMapping w = new WTranFlowMapping();
			w.setUserNum(bean.getUserNum());
			WTranFlowMapping wtf = wTranFlowMappingDao.getBeanByBean(w, MatchMode.ANYWHERE);
			//take profit交易次数加1，Stop Loss交易量加交易金额 ， 总交易次数加一，总交易量加交易金额
			wtf.setTakeProfitQty(String.valueOf(Integer.parseInt(wtf.getTakeProfitQty()) + 1));
			wtf.setCount(wtf.getCount() + 1);
			ArrayList list1 = new ArrayList();
			list1.add(wtf);
			cTranFlowMappingDao.batchUpdate(list1);
		}
	}
}
