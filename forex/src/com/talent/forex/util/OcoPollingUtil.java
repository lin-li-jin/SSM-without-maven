package com.talent.forex.util;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.hibernate.criterion.MatchMode;

import com.talent.forex.bean.domain.AccInfo;
import com.talent.forex.bean.domain.CTranFlowMapping;
import com.talent.forex.bean.domain.OcoInfo;
import com.talent.forex.bean.domain.WTranFlowMapping;
import com.talent.forex.dao.AccInfoDao;
import com.talent.forex.dao.CTranFlowMappingDao;
import com.talent.forex.dao.OcoDao;
import com.talent.forex.dao.WTranFlowMappingDao;
import com.talent.hibernate.util.HibernateUtil;
import com.talent.hibernate.util.TransactionNestUtil;

//stoploss��ѯ
public class OcoPollingUtil {
	
	private static Logger logger = Logger.getLogger(OcoPollingUtil.class);
	
	/**
	 * �ж��Ƿ�ﵽoco��ִ���������������ִ��
	 */
	public static void ocoCheck(){
		System.out.println("----------oco��ѯ��ִ��һ��-------------");
		OcoDao ocoDao = new OcoDao();
		String rsf=null;
		try {
			rsf = TransactionNestUtil.reference();
			OcoInfo s = new OcoInfo();
			s.setStatue("A");
			ArrayList<OcoInfo> list =(ArrayList<OcoInfo>)ocoDao.getBeansByBean(s, MatchMode.ANYWHERE);
			for(int i=0;i<list.size();i++){
				if(DateCompareUtil.dateCompare(list.get(i).getGoodFrom(), list.get(i).getGoodTill()).equals("execute")){
					executeOco(list.get(i)); 
				}else if(DateCompareUtil.dateCompare(list.get(i).getGoodFrom(), list.get(i).getGoodTill()).equals("invalid")){
					setOcoInvalid(list.get(i));
				}
			}
			TransactionNestUtil.releaseRef(rsf);
		} catch (Exception e) {
			TransactionNestUtil.releaseRef(rsf);
			if (!TransactionNestUtil.isReference()) {
				HibernateUtil.rollbackTransaction();
			}
			logger.error("oco��ѯ����" + e.getMessage());
		} finally {
			if (!TransactionNestUtil.isReference()) {
				HibernateUtil.closeSession();			
			}
		}
	}
	
	
	/**
	 * ִ��һ��oco �ڲ�����
	 * @param bean
	 */
	public static void executeOco(OcoInfo bean){
		if(bean.getDirection().equals("0")){
			if(bean.getMonitorPrice().equals("BID")){
				if(RateUtil.getRateByCCy(bean.getTranType(),bean.getWeCcy()+bean.getAnaCcy()).getBidValue().equals(bean.getSPrice())){
					//System.out.println(RateUtil.getRateByCCy(bean.getTranType(),bean.getWeCcy()+bean.getAnaCcy()).getBidValue());
					//System.out.println(bean.getPrice()); 
					modifyOcoInfo(bean,"S");
				}
			}else{
				if(RateUtil.getRateByCCy(bean.getTranType(),bean.getWeCcy()+bean.getAnaCcy()).getAskValue().equals(bean.getSPrice())){
					//return;
					modifyOcoInfo(bean,"S");
				}
			}
		}else{
			if(bean.getMonitorPrice().equals("BID")){
				if(RateUtil.getRateByCCy(bean.getTranType(),bean.getAnaCcy()+bean.getWeCcy()).getBidValue().equals(bean.getSPrice())){
					//System.out.println(RateUtil.getRateByCCy(bean.getTranType(),bean.getAnaCcy()+bean.getWeCcy()).getBidValue());
					//System.out.println(bean.getPrice()); 
					//return;
					modifyOcoInfo(bean,"S");
				}
			}else{
				if(RateUtil.getRateByCCy(bean.getTranType(),bean.getAnaCcy()+bean.getWeCcy()).getAskValue().equals(bean.getSPrice())){
					//return;
					modifyOcoInfo(bean,"S");
				}
			}
		}
		
		if(bean.getDirection().equals("0")){
			if(RateUtil.getRateByCCy(bean.getTranType(),bean.getWeCcy()+bean.getAnaCcy()).getBidValue().equals(bean.getTPrice())){
				//System.out.println(RateUtil.getRateByCCy(bean.getTranType(),bean.getWeCcy()+bean.getAnaCcy()).getBidValue());
				//System.out.println(bean.getPrice()); 
				//return;
				modifyOcoInfo(bean,"T");
			}
		}else{
			if(RateUtil.getRateByCCy(bean.getTranType(),bean.getAnaCcy()+bean.getWeCcy()).getAskValue().equals(bean.getTPrice())){
				//System.out.println(RateUtil.getRateByCCy(bean.getTranType(),bean.getAnaCcy()+bean.getWeCcy()).getAskValue());
				//System.out.println(bean.getPrice()); 
				//return;
				modifyOcoInfo(bean,"T");
			}
		}
		
		
	
			
	}
	
	/**
	 * OCO���ݲ�ͬ���ͽ��в�����SΪstop loss �� TΪtake profit��
	 * @param bean
	 * @param tranType
	 */
	public static void modifyOcoInfo(OcoInfo bean,String tranType){
		OcoDao ocoDao = new OcoDao();
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
		Double amount = 0.0;
		if(tranType.equals("T")){
			amount = Double.parseDouble(bean.getTAmount());//���׵�ʵ�ʽ��׽��(����Ϊtake profit)
		}
		else if(tranType.equals("S")){
			amount = Double.parseDouble(bean.getSAmount());//���׵�ʵ�ʽ��׽��(����Ϊstop loss)
		}
		
		//weCcy���ֶ�Ӧ���˻����     = ���-ʵ�ʽ��׽��
		//anaCcy���ֶ�Ӧ���˻����   = ���+ʵ�ʽ��׽��*�۸�
		Double amt =0.0;
		if(tranType.equals("T")){
			amt = Double.parseDouble(bean.getTAmount()) * Double.parseDouble(bean.getTPrice());//�һ����ʺ���һ���ֽ��
			if (bean.getDirection().equals("0")){
				weAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(weCcyMoney-amount));
				anaAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(anaCcyMoney+amt));
			}
			else{
				weAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(weCcyMoney-amt));
				anaAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(anaCcyMoney+amount));
			}
		}
		else if(tranType.equals("S")){
			if (bean.getDirection().equals("0")){
				if(bean.getMonitorPrice().equals("BID")){
					amt = Double.parseDouble(bean.getSAmount()) * Double.parseDouble(bean.getSPrice());//�һ����ʺ���һ���ֽ��
				}else{
					amt = Double.parseDouble(bean.getSAmount()) * Double.parseDouble(RateUtil.getRateByCCy(bean.getTranType(),bean.getWeCcy()+bean.getAnaCcy()).getBidValue());//�һ����ʺ���һ���ֽ��
				}
				weAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(weCcyMoney-amount));
				anaAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(anaCcyMoney+amt));
			}
			else{
				if(bean.getMonitorPrice().equals("BID")){
					amt = Double.parseDouble(bean.getSAmount()) * Double.parseDouble(RateUtil.getRateByCCy(bean.getTranType(),bean.getAnaCcy()+bean.getWeCcy()).getAskValue());//�һ����ʺ���һ���ֽ��
				}else{
					amt = Double.parseDouble(bean.getSAmount()) * Double.parseDouble(bean.getSPrice());//�һ����ʺ���һ���ֽ��
				}
				weAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(weCcyMoney-amt));
				anaAccInfo.setAmount(FormatParamUtil.getAmountAndPriceFmtByDouble(anaCcyMoney+amount));
			}
		}
		
		
		
		//2.��������ҽ�����ˮ��c_tran_flow_mapping
		if (bean.getTranType().equals("C")){
			CTranFlowMapping c = new CTranFlowMapping();
			c.setUserNum(bean.getUserNum());
			CTranFlowMapping ctf = cTranFlowMappingDao.getBeanByBean(c, MatchMode.ANYWHERE);
			//Oco���״�����1��Oco�������ӽ��׽�� �� �ܽ��״�����һ���ܽ������ӽ��׽��
			ctf.setOcoQty(String.valueOf(Integer.parseInt(ctf.getOcoQty()) + 1));
			ctf.setOcoAmt(FormatParamUtil.getAmountAndPriceFmtByDouble(Double.parseDouble(ctf.getOcoAmt()) + amount));
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
			//Oco���״�����1��Oco�������ӽ��׽�� �� �ܽ��״�����һ���ܽ������ӽ��׽��
			wtf.setOcoQty(String.valueOf(Integer.parseInt(wtf.getOcoQty()) + 1));
			wtf.setOcoAmt(FormatParamUtil.getAmountAndPriceFmtByDouble(Double.parseDouble(wtf.getOcoAmt()) + amount));
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
		ocoDao.batchUpdate(list2);
		accInfoDao.batchUpdate(list3);
	}
	
	/**
	 * ���stoploss���˽�������   ��ѽ������ó�invalid,����update��������ˮ���У���Ӧ�������͵Ľ�����+0�����״���+1
	 * @param bean
	 */
	public static void  setOcoInvalid(OcoInfo bean){
		CTranFlowMappingDao cTranFlowMappingDao = new CTranFlowMappingDao();
		WTranFlowMappingDao wTranFlowMappingDao = new WTranFlowMappingDao();
		OcoDao ocoDao = new OcoDao();
		bean.setStatue("I");
		ArrayList list = new ArrayList();
		list.add(bean);
		ocoDao.batchUpdate(list);
		
		//2.��������ҽ�����ˮ��c_tran_flow_mapping
		if (bean.getTranType().equals("C")){      
			
			CTranFlowMapping c = new CTranFlowMapping();
			c.setUserNum(bean.getUserNum());
			CTranFlowMapping ctf = cTranFlowMappingDao.getBeanByBean(c, MatchMode.ANYWHERE);
			//Stop Loss���״�����1��Stop Loss�������ӽ��׽�� �� �ܽ��״�����һ���ܽ������ӽ��׽��
			ctf.setOcoQty(String.valueOf(Integer.parseInt(ctf.getOcoQty()) + 1));
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
			wtf.setOcoQty(String.valueOf(Integer.parseInt(wtf.getOcoQty()) + 1));
			wtf.setCount(wtf.getCount() + 1);
			ArrayList list1 = new ArrayList();
			list1.add(wtf);
			wTranFlowMappingDao.batchUpdate(list1);
		}
	}
}
