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

//stoploss��ѯ
public class StopLossPollingUtil {
	
	private static Logger logger = Logger.getLogger(StopLossPollingUtil.class);
	
	/**
	 * �ж��Ƿ�ﵽstopLoss��ִ���������������ִ��
	 */
	public static void stopLossCheck(){
		System.out.println("----------stop��ѯ��ִ��һ��-------------");
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
			logger.error("stopLoss��ѯ����" + e.getMessage());
		} finally {
			if (!TransactionNestUtil.isReference()) {
				HibernateUtil.closeSession();			
			}
		}
	}
	
	
	/**
	 * ִ��һ��stopLoss �ڲ�����
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
		Double amount = Double.parseDouble(bean.getAmount());//Stop Loss���׵�ʵ�ʽ��׽��
		
		
		//weCcy���ֶ�Ӧ���˻����     = ���-ʵ�ʽ��׽��
		//anaCcy���ֶ�Ӧ���˻����   = ���+ʵ�ʽ��׽��*�۸�
		Double amt =0.0;
		if (bean.getDirection().equals("0")){
			if(bean.getMonitorPrice().equals("BID")){
				amt = Double.parseDouble(bean.getAmount()) * Double.parseDouble(bean.getPrice());//�һ����ʺ���һ���ֽ��
			}else{
				amt = Double.parseDouble(bean.getAmount()) * Double.parseDouble(RateUtil.getRateByCCy(bean.getTranType(),bean.getWeCcy()+bean.getAnaCcy()).getBidValue());//�һ����ʺ���һ���ֽ��
			}
			weAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(weCcyMoney-amount));
			anaAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(anaCcyMoney+amt));
		}
		else{
			if(bean.getMonitorPrice().equals("BID")){
				amt = Double.parseDouble(bean.getAmount()) * Double.parseDouble(RateUtil.getRateByCCy(bean.getTranType(),bean.getAnaCcy()+bean.getWeCcy()).getAskValue());//�һ����ʺ���һ���ֽ��
			}else{
				amt = Double.parseDouble(bean.getAmount()) * Double.parseDouble(bean.getPrice());//�һ����ʺ���һ���ֽ��
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
			ctf.setStopLossQty(String.valueOf(Integer.parseInt(ctf.getStopLossQty()) + 1));
			ctf.setStopLossAmt(FormatParamUtil.getAmountAndPriceFmtByDouble(Double.parseDouble(ctf.getStopLossAmt()) + amount));
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
			//Stop Loss���״�����1��Stop Loss�������ӽ��׽�� �� �ܽ��״�����һ���ܽ������ӽ��׽��
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
		stopLossDao.batchUpdate(list2);
		accInfoDao.batchUpdate(list3);
			
	}
	
	/**
	 * ���stoploss���˽�������   ��ѽ������ó�invalid,����update��������ˮ���У���Ӧ�������͵Ľ�����+0�����״���+1
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
		
		//2.��������ҽ�����ˮ��c_tran_flow_mapping
		if (bean.getTranType().equals("C")){      
			
			CTranFlowMapping c = new CTranFlowMapping();
			c.setUserNum(bean.getUserNum());
			CTranFlowMapping ctf = cTranFlowMappingDao.getBeanByBean(c, MatchMode.ANYWHERE);
			//Stop Loss���״�����1��Stop Loss�������ӽ��׽�� �� �ܽ��״�����һ���ܽ������ӽ��׽��
			ctf.setStopLossQty(String.valueOf(Integer.parseInt(ctf.getStopLossQty()) + 1));
			ctf.setCount(ctf.getCount() + 1);
			ArrayList list1 = new ArrayList();
			list1.add(ctf);
			cTranFlowMappingDao.batchUpdate(list1);
		}
		else{//������ҽ�����ˮ��w_tran_flow_mapping
			WTranFlowMapping w = new WTranFlowMapping();
			w.setUserNum(bean.getUserNum());
			WTranFlowMapping wtf = wTranFlowMappingDao.getBeanByBean(w, MatchMode.ANYWHERE);
			//Stop Loss���״�����1��Stop Loss�������ӽ��׽�� �� �ܽ��״�����һ���ܽ������ӽ��׽��
			wtf.setStopLossQty(String.valueOf(Integer.parseInt(wtf.getStopLossQty()) + 1));
			wtf.setCount(wtf.getCount() + 1);
			ArrayList list1 = new ArrayList();
			list1.add(wtf);
			wTranFlowMappingDao.batchUpdate(list1);
		}
	}
}
