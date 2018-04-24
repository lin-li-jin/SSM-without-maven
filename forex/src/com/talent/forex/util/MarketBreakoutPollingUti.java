package com.talent.forex.util;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.hibernate.criterion.MatchMode;

import com.talent.forex.bean.domain.AccInfo;
import com.talent.forex.bean.domain.CTranFlowMapping;
import com.talent.forex.bean.domain.MarketBreakoutInfo;
import com.talent.forex.bean.domain.WTranFlowMapping;
import com.talent.forex.dao.AccInfoDao;
import com.talent.forex.dao.CTranFlowMappingDao;
import com.talent.forex.dao.MarketBreakoutDao;
import com.talent.forex.dao.WTranFlowMappingDao;
import com.talent.hibernate.util.HibernateUtil;
import com.talent.hibernate.util.TransactionNestUtil;

//MarkerBreakout轮询
public class MarketBreakoutPollingUti {
	
	private static Logger logger = Logger.getLogger(MarketBreakoutPollingUti.class);
	
	/**
	 * 判断是否达到MarketBreakout的执行条件，如果是则执行
	 */
	public static void stopLossCheck(){
		System.out.println("----------MarketBreakout轮询已执行一遍-------------");
		MarketBreakoutDao marketbreakoutDao = new MarketBreakoutDao();
		String rsf=null;
		try {
			rsf = TransactionNestUtil.reference();
			MarketBreakoutInfo s = new MarketBreakoutInfo();
			s.setStatue("A");
			ArrayList<MarketBreakoutInfo> list =(ArrayList<MarketBreakoutInfo>)marketbreakoutDao.getBeansByBean(s, MatchMode.ANYWHERE);
			for(int i=0;i<list.size();i++){
				if(DateCompareUtil.dateCompare(list.get(i).getGoodFrom(), list.get(i).getGoodTill()).equals("execute")){
					executeMarketbreakout(list.get(i)); 
				}else if(DateCompareUtil.dateCompare(list.get(i).getGoodFrom(), list.get(i).getGoodTill()).equals("invalid")){
					setMarketBreakoutInvalid(list.get(i));
				}
			}
			TransactionNestUtil.releaseRef(rsf);
		} catch (Exception e) {
			TransactionNestUtil.releaseRef(rsf);
			if (!TransactionNestUtil.isReference()) {
				HibernateUtil.rollbackTransaction();
			}
			logger.error("MarketBreakout轮询出错！" + e.getMessage());
		} finally {
			if (!TransactionNestUtil.isReference()) {
				HibernateUtil.closeSession();			
			}
		}
	}
	
	
	/**
	 * 执行一条MarkerBreakout 内部调用
	 * @param bean
	 */
	public static void executeMarketbreakout(MarketBreakoutInfo bean){
		String MonitorPrice=bean.getMonitorPrice();
        String[] MonitorPriceArray = MonitorPrice.split("/");
       /* for (int i = 0; i < MonitorPriceArray.length; i++) {
            System.out.println(MonitorPriceArray[i]);
        }*/
        
		//s1price
		if(bean.getDirection().equals("0")){
			if(MonitorPriceArray[0].equals("B")){
				if(RateUtil.getRateByCCy(bean.getTranType(),bean.getWeCcy()+bean.getAnaCcy()).getBidValue().equals(bean.getS1Price())){
					/*System.out.println(RateUtil.getRateByCCy(bean.getTranType(),bean.getWeCcy()+bean.getAnaCcy()).getBidValue());
					System.out.println(bean.getPrice()); 
					return;*/
					executeMarketBreeakout(bean,MonitorPriceArray[0],bean.getS1Price(),bean.getS1Amount());
				}
			}else{
				if(RateUtil.getRateByCCy(bean.getTranType(),bean.getWeCcy()+bean.getAnaCcy()).getAskValue().equals(bean.getS1Price())){

					executeMarketBreeakout(bean,MonitorPriceArray[0],bean.getS1Price(),bean.getS1Amount());
				}
			}
		}else{
			if(bean.getMonitorPrice().equals("B")){
				if(RateUtil.getRateByCCy(bean.getTranType(),bean.getAnaCcy()+bean.getWeCcy()).getBidValue().equals(bean.getS1Price())){
					/*System.out.println(RateUtil.getRateByCCy(bean.getTranType(),bean.getAnaCcy()+bean.getWeCcy()).getBidValue());
					System.out.println(bean.getPrice()); 
					return;*/
					executeMarketBreeakout(bean,MonitorPriceArray[0],bean.getS1Price(),bean.getS1Amount());
				}
			}else{
				if(RateUtil.getRateByCCy(bean.getTranType(),bean.getAnaCcy()+bean.getWeCcy()).getAskValue().equals(bean.getS1Price())){
					//return;
					executeMarketBreeakout(bean,MonitorPriceArray[0],bean.getS1Price(),bean.getS1Amount());
				}
			}
		}
		
		//s2price
		if(bean.getDirection().equals("0")){
			if(MonitorPriceArray[1].equals("B")){
				if(RateUtil.getRateByCCy(bean.getTranType(),bean.getWeCcy()+bean.getAnaCcy()).getBidValue().equals(bean.getS2Price())){
					/*System.out.println(RateUtil.getRateByCCy(bean.getTranType(),bean.getWeCcy()+bean.getAnaCcy()).getBidValue());
					System.out.println(bean.getPrice()); 
					return;*/
					executeMarketBreeakout(bean,MonitorPriceArray[1],bean.getS2Price(),bean.getS2Amount());
				}
			}else{
				if(RateUtil.getRateByCCy(bean.getTranType(),bean.getWeCcy()+bean.getAnaCcy()).getAskValue().equals(bean.getS2Price())){
					//return;
					executeMarketBreeakout(bean,MonitorPriceArray[1],bean.getS2Price(),bean.getS2Amount());
				}
			}
		}else{
			if(bean.getMonitorPrice().equals("B")){
				if(RateUtil.getRateByCCy(bean.getTranType(),bean.getAnaCcy()+bean.getWeCcy()).getBidValue().equals(bean.getS2Price())){
					/*System.out.println(RateUtil.getRateByCCy(bean.getTranType(),bean.getAnaCcy()+bean.getWeCcy()).getBidValue());
					System.out.println(bean.getPrice()); 
					return;*/
					executeMarketBreeakout(bean,MonitorPriceArray[1],bean.getS2Price(),bean.getS2Amount());
				}
			}else{
				if(RateUtil.getRateByCCy(bean.getTranType(),bean.getAnaCcy()+bean.getWeCcy()).getAskValue().equals(bean.getS2Price())){
					//return;
					executeMarketBreeakout(bean,MonitorPriceArray[1],bean.getS2Price(),bean.getS2Amount());
				}
			}
		}
		
	}
		
		
		
		
		
		
		
	public static void executeMarketBreeakout(MarketBreakoutInfo bean,String monitorprice,String price,String getamount)
	{	
		
		MarketBreakoutDao marketbreakoutDao = new MarketBreakoutDao();
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
		Double amount = Double.parseDouble(getamount);//Stop Loss交易的实际交易金额
		
		
		//weCcy币种对应的账户金额     = 余额-实际交易金额
		//anaCcy币种对应的账户金额   = 余额+实际交易金额*价格
		Double amt =0.0;
		if (bean.getDirection().equals("0")){
			if(monitorprice.equals("B")){
				amt = Double.parseDouble(getamount) * Double.parseDouble(price);//兑换汇率后另一币种金额
			}else{
				amt = Double.parseDouble(getamount) * Double.parseDouble(RateUtil.getRateByCCy(bean.getTranType(),bean.getWeCcy()+bean.getAnaCcy()).getBidValue());//兑换汇率后另一币种金额
			}
			weAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(weCcyMoney-amount));
			anaAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(anaCcyMoney+amt));
		}
		else{
			if(monitorprice.equals("B")){
				amt = Double.parseDouble(getamount) * Double.parseDouble(RateUtil.getRateByCCy(bean.getTranType(),bean.getAnaCcy()+bean.getWeCcy()).getAskValue());//兑换汇率后另一币种金额
			}else{
				amt = Double.parseDouble(getamount) * Double.parseDouble(price);//兑换汇率后另一币种金额
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
			ctf.setMarketBreakoutQty(String.valueOf(Integer.parseInt(ctf.getMarketBreakoutQty()) + 1));
			ctf.setMarketBreakoutAmt(FormatParamUtil.getAmountAndPriceFmtByDouble(Double.parseDouble(ctf.getMarketBreakoutAmt()) + amount));
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
			//MarkerBreakout交易次数加1，MarkerBreakout交易量加交易金额 ， 总交易次数加一，总交易量加交易金额
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
		marketbreakoutDao.batchUpdate(list2);
		accInfoDao.batchUpdate(list3);
			
	}
	
	/**
	 * 如果MarkerBreakout过了交易日期   则把交易设置成invalid,并且update到交易流水表中，对应交易类型的交易量+0；交易次数+1
	 * @param bean
	 */
	public static void  setMarketBreakoutInvalid(MarketBreakoutInfo bean){
		CTranFlowMappingDao cTranFlowMappingDao = new CTranFlowMappingDao();
		WTranFlowMappingDao wTranFlowMappingDao = new WTranFlowMappingDao();
		MarketBreakoutDao marketbreakoutDao = new MarketBreakoutDao();
		bean.setStatue("I");
		ArrayList list = new ArrayList();
		list.add(bean);
		marketbreakoutDao.batchUpdate(list);
		
		//2.更新人民币交易流水表c_tran_flow_mapping
		if (bean.getTranType().equals("C")){      
			
			CTranFlowMapping c = new CTranFlowMapping();
			c.setUserNum(bean.getUserNum());
			CTranFlowMapping ctf = cTranFlowMappingDao.getBeanByBean(c, MatchMode.ANYWHERE);
			//MarkerBreakout交易次数加1，Stop Loss交易量加交易金额 ， 总交易次数加一，总交易量加交易金额
			ctf.setMarketBreakoutQty(String.valueOf(Integer.parseInt(ctf.getMarketBreakoutQty()) + 1));
			ctf.setCount(ctf.getCount() + 1);
			ArrayList list1 = new ArrayList();
			list1.add(ctf);
			cTranFlowMappingDao.batchUpdate(list1);
		}
		else{//更新外币交易流水表w_tran_flow_mapping
			WTranFlowMapping w = new WTranFlowMapping();
			w.setUserNum(bean.getUserNum());
			WTranFlowMapping wtf = wTranFlowMappingDao.getBeanByBean(w, MatchMode.ANYWHERE);
			//MarkerBreakout交易次数加1，MarkerBreakout交易量加交易金额 ， 总交易次数加一，总交易量加交易金额
			wtf.setMarketBreakoutQty(String.valueOf(Integer.parseInt(wtf.getMarketBreakoutQty()) + 1));
			wtf.setCount(wtf.getCount() + 1);
			ArrayList list1 = new ArrayList();
			list1.add(wtf);
			wTranFlowMappingDao.batchUpdate(list1);
		}
	}
}
