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

//MarkerBreakout��ѯ
public class MarketBreakoutPollingUti {
	
	private static Logger logger = Logger.getLogger(MarketBreakoutPollingUti.class);
	
	/**
	 * �ж��Ƿ�ﵽMarketBreakout��ִ���������������ִ��
	 */
	public static void stopLossCheck(){
		System.out.println("----------MarketBreakout��ѯ��ִ��һ��-------------");
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
			logger.error("MarketBreakout��ѯ����" + e.getMessage());
		} finally {
			if (!TransactionNestUtil.isReference()) {
				HibernateUtil.closeSession();			
			}
		}
	}
	
	
	/**
	 * ִ��һ��MarkerBreakout �ڲ�����
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
		//1.�����˻���acc_info,�ȵõ�����Ҫ���׵ı��ֵ��˻�bean
		//weCcy��������  anaCcy���������
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
		
		Double weCcyMoney = Double.parseDouble(weAccInfo.getAmount());//weCcy���ֶ�Ӧ���˻����
		Double anaCcyMoney = Double.parseDouble(anaAccInfo.getAmount());//anaCcy���ֶ�Ӧ���˻����
		Double amount = Double.parseDouble(getamount);//Stop Loss���׵�ʵ�ʽ��׽��
		
		
		//weCcy���ֶ�Ӧ���˻����     = ���-ʵ�ʽ��׽��
		//anaCcy���ֶ�Ӧ���˻����   = ���+ʵ�ʽ��׽��*�۸�
		Double amt =0.0;
		if (bean.getDirection().equals("0")){
			if(monitorprice.equals("B")){
				amt = Double.parseDouble(getamount) * Double.parseDouble(price);//�һ����ʺ���һ���ֽ��
			}else{
				amt = Double.parseDouble(getamount) * Double.parseDouble(RateUtil.getRateByCCy(bean.getTranType(),bean.getWeCcy()+bean.getAnaCcy()).getBidValue());//�һ����ʺ���һ���ֽ��
			}
			weAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(weCcyMoney-amount));
			anaAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(anaCcyMoney+amt));
		}
		else{
			if(monitorprice.equals("B")){
				amt = Double.parseDouble(getamount) * Double.parseDouble(RateUtil.getRateByCCy(bean.getTranType(),bean.getAnaCcy()+bean.getWeCcy()).getAskValue());//�һ����ʺ���һ���ֽ��
			}else{
				amt = Double.parseDouble(getamount) * Double.parseDouble(price);//�һ����ʺ���һ���ֽ��
			}
			weAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(weCcyMoney-amt));
			anaAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(anaCcyMoney+amount));
		}
		
		//2.��������ҽ�����ˮ��c_tran_flow_mapping
		if (bean.getTranType().equals("C")){
			CTranFlowMapping c = new CTranFlowMapping();
			c.setUserNum(bean.getUserNum());
			CTranFlowMapping ctf = cTranFlowMappingDao.getBeanByBean(c, MatchMode.ANYWHERE);
			//Stop Loss���״�����1��Stop Loss�������ӽ��׽�� �� �ܽ��״�����һ���ܽ������ӽ��׽��
			ctf.setMarketBreakoutQty(String.valueOf(Integer.parseInt(ctf.getMarketBreakoutQty()) + 1));
			ctf.setMarketBreakoutAmt(FormatParamUtil.getAmountAndPriceFmtByDouble(Double.parseDouble(ctf.getMarketBreakoutAmt()) + amount));
			ctf.setCount(ctf.getCount() + 1);
			ctf.setAmount(ctf.getAmount() + amount);
			ArrayList list1 = new ArrayList();
			list1.add(ctf);
			cTranFlowMappingDao.batchUpdate(list1);
		}
		else{//������ҽ�����ˮ��w_tran_flow_mapping
			WTranFlowMapping w = new WTranFlowMapping();
			w.setUserNum(bean.getUserNum());
			WTranFlowMapping wtf = wTranFlowMappingDao.getBeanByBean(w, MatchMode.ANYWHERE);
			//MarkerBreakout���״�����1��MarkerBreakout�������ӽ��׽�� �� �ܽ��״�����һ���ܽ������ӽ��׽��
			wtf.setStopLossQty(String.valueOf(Integer.parseInt(wtf.getStopLossQty()) + 1));
			wtf.setStopLossAmt(FormatParamUtil.getAmountAndPriceFmtByDouble(Double.parseDouble(wtf.getStopLossAmt()) + amount));
			wtf.setCount(wtf.getCount() + 1);
			wtf.setAmount(wtf.getAmount() + amount);
			ArrayList list1 = new ArrayList();
			list1.add(wtf);
			wTranFlowMappingDao.batchUpdate(list1);
		}
		
		//3.����bean�Ľ���״̬Ϊ���
		bean.setStatue("D");
		bean.setLAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(amt));
		
		//4.����ص�bean���µ���Ӧ�ı�
		
		ArrayList list2 = new ArrayList();
		ArrayList list3 = new ArrayList();
		list2.add(bean);
		list3.add(weAccInfo);
		list3.add(anaAccInfo);
		marketbreakoutDao.batchUpdate(list2);
		accInfoDao.batchUpdate(list3);
			
	}
	
	/**
	 * ���MarkerBreakout���˽�������   ��ѽ������ó�invalid,����update��������ˮ���У���Ӧ�������͵Ľ�����+0�����״���+1
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
		
		//2.��������ҽ�����ˮ��c_tran_flow_mapping
		if (bean.getTranType().equals("C")){      
			
			CTranFlowMapping c = new CTranFlowMapping();
			c.setUserNum(bean.getUserNum());
			CTranFlowMapping ctf = cTranFlowMappingDao.getBeanByBean(c, MatchMode.ANYWHERE);
			//MarkerBreakout���״�����1��Stop Loss�������ӽ��׽�� �� �ܽ��״�����һ���ܽ������ӽ��׽��
			ctf.setMarketBreakoutQty(String.valueOf(Integer.parseInt(ctf.getMarketBreakoutQty()) + 1));
			ctf.setCount(ctf.getCount() + 1);
			ArrayList list1 = new ArrayList();
			list1.add(ctf);
			cTranFlowMappingDao.batchUpdate(list1);
		}
		else{//������ҽ�����ˮ��w_tran_flow_mapping
			WTranFlowMapping w = new WTranFlowMapping();
			w.setUserNum(bean.getUserNum());
			WTranFlowMapping wtf = wTranFlowMappingDao.getBeanByBean(w, MatchMode.ANYWHERE);
			//MarkerBreakout���״�����1��MarkerBreakout�������ӽ��׽�� �� �ܽ��״�����һ���ܽ������ӽ��׽��
			wtf.setMarketBreakoutQty(String.valueOf(Integer.parseInt(wtf.getMarketBreakoutQty()) + 1));
			wtf.setCount(wtf.getCount() + 1);
			ArrayList list1 = new ArrayList();
			list1.add(wtf);
			wTranFlowMappingDao.batchUpdate(list1);
		}
	}
}
