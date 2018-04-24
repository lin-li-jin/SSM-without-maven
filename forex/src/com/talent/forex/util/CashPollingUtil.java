package com.talent.forex.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

import com.talent.forex.bean.domain.*;
import org.apache.log4j.Logger;
import org.hibernate.criterion.MatchMode;

import com.talent.auth.bean.domain.Users;
import com.talent.auth.dao.UsersDao;
import com.talent.forex.dao.AccInfoDao;
import com.talent.forex.dao.BTranFlowMappingDao;
import com.talent.forex.dao.MarginForwardInfoDao;
import com.talent.forex.dao.MarginOptionInfoDao;
import com.talent.hibernate.util.HibernateUtil;
import com.talent.hibernate.util.TransactionNestUtil;

/**
 *
 * @author atggdsaiDong
 *
 */
public class CashPollingUtil {
	
	private static Logger logger = Logger.getLogger(CashPollingUtil.class);
	
	/**
	 * ��������Ǳ���ʱ����ѯִ��  �����֤��Զ�ڽ��׵������������ϵͳ���ڷ�����ִ��Զ��
	 */
	@SuppressWarnings("unchecked")
	public static void checkForwardValueDate() {
		System.out.println("----------��֤��Զ����ѯ��ִ��һ��-------------");
		MarginForwardInfoDao marginForwardInfoDao = new MarginForwardInfoDao();
		String rsf=null;
		try {
			rsf = TransactionNestUtil.reference();
			//��ѯ��ǰ�Ƿ���Զ�ڽ��׿���ִ��
			MarginForwardInfo m = new MarginForwardInfo();
			m.setStatue("A");
			m.setValueDate(GetDateTimeUtil.getCurrentDate());
			ArrayList<MarginForwardInfo> list =(ArrayList<MarginForwardInfo>)marginForwardInfoDao.getBeansByBean(m, MatchMode.ANYWHERE);
			for(int i=0;i<list.size();i++){
				executeForward(list.get(i),true);//���÷���ִ��һ��Զ�ڽ���
			}
			TransactionNestUtil.releaseRef(rsf);
		} catch (Exception e) {
			TransactionNestUtil.releaseRef(rsf);
			if (!TransactionNestUtil.isReference()) {
				HibernateUtil.rollbackTransaction();
			}
			logger.error("��֤��Զ����ѯ������" + e.getMessage());
		} finally {
			if (!TransactionNestUtil.isReference()) {
				HibernateUtil.closeSession();			
			}
		}
	}
	
	/**
	 * ��������Ǳ���ʱ����ѯִ��  �����֤����Ȩ���׵������������ϵͳ���ڷ��������Ȩ���׵�״̬����Ϊ������  
	 * ����Ա�ڽ������ѡ��ִ��
	 */
	@SuppressWarnings("unchecked")
	public static void checkOptionValueDate(){
		System.out.println("----------��֤����Ȩ��ѯ��ִ��һ��-------------");
		MarginOptionInfoDao marginOptionInfoDao = new MarginOptionInfoDao();
		String rsf=null;
		try {
			rsf = TransactionNestUtil.reference();
			ArrayList<MarginOptionInfo> listToUpdate = new ArrayList<MarginOptionInfo>();
			
			//start ��״̬ΪA�ĸպõ��˽������ڵ����ó�P
			MarginOptionInfo m = new MarginOptionInfo();
			m.setStatue("A");
			m.setMaturity(GetDateTimeUtil.getCurrentDate());
			ArrayList<MarginOptionInfo> list =(ArrayList<MarginOptionInfo>)marginOptionInfoDao.getBeansByBean(m, MatchMode.ANYWHERE);
			for(int i=0 ; i<list.size(); i++){
				MarginOptionInfo bean = list.get(i);
				bean.setStatue("P");
				listToUpdate.add(bean);
			}
			//end ��״̬ΪA�ĸպõ��˽������ڵ����ó�P
			
			//start ��״̬ΪP�Ĺ��˽������ڵ����ó�I
			MarginOptionInfo n = new MarginOptionInfo();
			n.setStatue("P");
			ArrayList<MarginOptionInfo> list1 =(ArrayList<MarginOptionInfo>)marginOptionInfoDao.getBeansByBean(n, MatchMode.ANYWHERE);
			for(int i=0 ; i<list1.size(); i++){
				if(!list1.get(i).getMaturity().equals(GetDateTimeUtil.getCurrentDate())){
					MarginOptionInfo bean1 = list1.get(i);
					bean1.setStatue("I");
					listToUpdate.add(bean1);
				}
			}
			//end ��״̬ΪP�Ĺ��˽������ڵ����ó�I
			
			marginOptionInfoDao.batchUpdate(listToUpdate);
			TransactionNestUtil.releaseRef(rsf);
		} catch (Exception e) {
			TransactionNestUtil.releaseRef(rsf);
			if (!TransactionNestUtil.isReference()) {
				HibernateUtil.rollbackTransaction();
			}
			logger.error("��֤����Ȩ��ѯ������" + e.getMessage());
		} finally {
			if (!TransactionNestUtil.isReference()) {
				HibernateUtil.closeSession();			
			}
		}
	}
	
	/**
	 * ���ڱ�֤����˵   �ȰѸ������ֶ�ת��USD�ټ������
	 */
	@SuppressWarnings("unchecked")
	public static void checkCashBalance(){
		System.out.println("----------��֤���˻������ѯ��ִ��һ��-------------");
		AccInfoDao accInfoDao = new AccInfoDao();
		String rsf=null;
		try {
			rsf = TransactionNestUtil.reference();

			//����õ������û�  ��ΪҪ��ѯÿ���û��ı�֤���˻�
			Users u = new Users();
			u.setUserType("S");
			ArrayList<Users> usersList = (ArrayList<Users>)(new UsersDao().getBeansByBean(u, MatchMode.ANYWHERE)); 
			for(int i=0; i<usersList.size(); i++){
				//start �õ���֤���˻���ԭʼ���,ԭʼ����Ǵ���USD�˻���
				AccInfo usd = new AccInfo();
				usd.setCcy("USD");
				usd.setAccType("B");
				usd.setUserNum(usersList.get(i).getUserNum());
				Double beginUsd = Double.parseDouble(accInfoDao.getBeanByBean(usd, MatchMode.ANYWHERE).getOriginalAmt());
				//end �õ���֤���˻���ԭʼ���,ԭʼ����Ǵ���USD�˻���
				
				AccInfo accInfo = new AccInfo();
				accInfo.setUserNum(usersList.get(i).getUserNum());
				accInfo.setAccType("B");
				//�����û������˻�����B�õ���ǰ�û���֤���˻��������ֵ�һ��List,�������list�����������ת����usd�ĺ�
				ArrayList<AccInfo> accInfoList = (ArrayList<AccInfo>)(accInfoDao.getBeansByBean(accInfo, MatchMode.ANYWHERE));
				Double lastSum = 0.0;//��ǰ�û���������ת����USD���ܺ�
				for(int j=0; j<accInfoList.size(); j++){
					Double a = Double.parseDouble(accInfoList.get(j).getAmount());
					Double b = RateUtil.getRateByCcy("B", accInfoList.get(j).getCcy());
					System.out.println(a+b);
					lastSum = lastSum + Double.parseDouble(accInfoList.get(j).getAmount()) * RateUtil.getRateByCcy("B", accInfoList.get(j).getCcy());
				}
				if(lastSum+findMostLoss(usersList.get(i).getUserNum(),false)<beginUsd*0.25){
					//�˻��������25%   �����˻�
					for(int k=0; k<accInfoList.size();k++){
						accInfoList.get(k).setActiveDate("--------");
					}
					accInfoDao.batchUpdate(accInfoList);
					
					//start ����˻��������20% ��Ҫ�ѳֲֵ��п�������װ���ǰ�г��۸�ƽ��
					if(lastSum+findMostLoss(usersList.get(i).getUserNum(),false)<beginUsd*0.2){
						findMostLoss(usersList.get(i).getUserNum(),true);
					}
					//end ����˻��������20% ��Ҫ�ѳֲֵ��п�������װ���ǰ�г��۸�ƽ��
					
				}else{
					//���ڵ���25%  ����
					//���ж��û��Ƿ���//ֻҪһ�������Ǳ��������Ƕ�������
					if(accInfoList.get(0).getActiveDate().equals("--------")){
						for(int k=0; k<accInfoList.size();k++){
							accInfoList.get(k).setActiveDate(GetDateTimeUtil.getCurrentDate());
						}
						accInfoDao.batchUpdate(accInfoList);
					}
				}
			}
			TransactionNestUtil.releaseRef(rsf);
		} catch (Exception e) {
			TransactionNestUtil.releaseRef(rsf);
			if (!TransactionNestUtil.isReference()) {
				HibernateUtil.rollbackTransaction();
			}
			logger.error("��֤���˻������ѯ������" + e.getMessage());
		} finally {
			if (!TransactionNestUtil.isReference()) {
				HibernateUtil.closeSession();			
			}
		}
	}

	
	
	/**
	 * ���ص�ǰ�û���Զ�ڵĽ��׵����̽��ӯ���ܶ�
	 * ���flagΪtrue��ѿ������Ľ���ƽ��    ���Ϊfalse������Ǽ������
	 * ��ֵ���ڻ���ڳ�ʼ��֤���20%ʱ��ϵͳ�����г��۸񽫴Ӹ��˻��п������ĳֲֵ���ʼ���Զ�ǿƽ
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static double findMostLoss(String userNum,boolean flag){
		System.out.println("----------��ѯ���û��б�֤���������һ��Զ�ڽ���-------------");
		Double result = 0.0;
		MarginForwardInfoDao mDao = new MarginForwardInfoDao();
		String rsf=null;
		try {
			rsf = TransactionNestUtil.reference();
			//�õ���ǰ�û���������Ч��Զ�ڽ���
			MarginForwardInfo m = new MarginForwardInfo();
			m.setUserNum(userNum);
			m.setStatue("A");
			ArrayList<MarginForwardInfo> list = (ArrayList<MarginForwardInfo>)mDao.getBeansByBean(m, MatchMode.ANYWHERE);
			Double profit[] = new Double[list.size()];//����һ������������ÿһ��Զ�ڽ��׵�ӯ����������
			Hashtable<Double, Integer> table = new Hashtable<Double, Integer>();//����hashtable�����Ӧӯ�����±�
			for(int i=0; i<list.size();i++){
				MarginForwardInfo each = list.get(i);
				Double amount = Double.parseDouble(each.getDealAmt());//�õ����̽��׽��
				Double originPrice = Double.parseDouble(each.getPrice());//��ǰ���ɽ��׵ļ۸�
				Double currentPrice = RateUtil.getMarginRateByCcy(each.getAnaCcy() + each.getWeCcy(), each.getDirection());//�õ���ǰ�������ӵı���ת�ؼ��ٱ��֣��ļ۸�
				//��������ͬ   ����ӯ��������һ��
				if(each.getDirection().equals("1")){
					profit[i] = amount*currentPrice-amount*originPrice;
					table.put(profit[i], i);
				}else{
					profit[i]=amount*originPrice*currentPrice-amount;
					table.put(profit[i], i);
				}
			}
			Arrays.sort(profit);//��С��������  
			//����flag�ж��Ƿ�ִ��ƽ��
			if(flag){
				executeForward(list.get(table.get(profit[0])),false);
			}
			//������ӯ��
			for(Double i: profit){   
                //System.out.println(i);   
                result = result +i;
			}  
			TransactionNestUtil.releaseRef(rsf);
		} catch (Exception e) {
			TransactionNestUtil.releaseRef(rsf);
			if (!TransactionNestUtil.isReference()) {
				HibernateUtil.rollbackTransaction();
			}
			logger.error("��ѯ���û��б�֤���������һ��Զ�ڽ��ף�" + e.getMessage());
		} finally {
			if (!TransactionNestUtil.isReference()) {
				HibernateUtil.closeSession();			
			}
		}
		
		return result;
		
	}
	
	
	/**
	 * ִ��һ��Զ�ڽ���  �ڲ�����
	 * ����flagΪture��������ִ��     false������ǿ��ƽ��   ��ͬ��־��ִ���꽻�׺����õĽ���״̬��ͬ
	 * @param bean
	 */
	public static void executeForward(MarginForwardInfo bean,boolean flag){
		MarginForwardInfoDao marginForwardInfoDao = new MarginForwardInfoDao();
		AccInfoDao accInfoDao = new AccInfoDao();
		BTranFlowMappingDao bTranFlowMappingDao = new BTranFlowMappingDao();
		
		//��ΪupdateBean�����޷�ʹ��   ֻ��ͨ�������ύ��
		ArrayList<MarginForwardInfo> marginListToUpdate = new ArrayList<MarginForwardInfo>();
		ArrayList<BTranFlowMapping> bTranListToUpdate = new ArrayList<BTranFlowMapping>();
		ArrayList<AccInfo> accListToUpdate = new ArrayList<AccInfo>();
		
		//1.�����˻���acc_info,�ȵõ���֤���������Ҫ���׵ı��ֵ��˻�bean
		//weCcy��������  anaCcy���������
		AccInfo a1 = new AccInfo();
		a1.setUserNum(bean.getUserNum());
		a1.setAccType("B");
		a1.setCcy(bean.getWeCcy());
		AccInfo weAccInfo = accInfoDao.getBeanByBean(a1, MatchMode.ANYWHERE);
		
		AccInfo a2 = new AccInfo();
		a2.setUserNum(bean.getUserNum());
		a2.setAccType("B");
		a2.setCcy(bean.getAnaCcy());
		AccInfo anaAccInfo = accInfoDao.getBeanByBean(a2, MatchMode.ANYWHERE);
		
		//acc_info�����µĽ���õ���ʵ�ʽ��������̽��,�������̽�������ӯ��Ҫ���㵽ʵ�ʽ����ȥ
		Double weCcyMoney = Double.parseDouble(weAccInfo.getAmount());//weCcy���ֶ�Ӧ���˻����
		//Double anaCcyMoney = Double.parseDouble(anaAccInfo.getAmount());//anaCcy���ֶ�Ӧ���˻����
		Double price = Double.parseDouble(bean.getPrice());//Զ�ڽ��׵ļ۸�
		//Double amt = Double.parseDouble(bean.getAccAmount());//Զ�ڽ��׵�ʵ�ʽ��׽��
		Double deal_amt = Double.parseDouble(bean.getDealAmt());//Զ�ڽ��׵����̽��׽��
		Double currentPrice = RateUtil.getMarginRateByCcy(bean.getAnaCcy() + bean.getWeCcy(), bean.getDirection());//�õ���ǰ�������ӵı���ת�ؼ��ٱ��֣��ļ۸�
		Double profit = 0.0;
		if(bean.getDirection().equals("1")){
			//��
			//weCcy���ֶ�Ӧ���˻����=���+���̽��׵�����
			profit = deal_amt*currentPrice-deal_amt*price;	//���׵�����
			double accrual=deal_amt*0.0010; //�����н�����Ϣ
			double charge=profit-accrual-Double.valueOf(bean.getAccAmount());	//������������ȥ���ɵĽ��
			weAccInfo.setAmount(FormatParamUtil.formatDouble(weCcyMoney+charge));
		}
		//2.���±�֤������ˮ��b_tran_flow_mapping,���½���õ������̽��
		BTranFlowMapping b = new BTranFlowMapping();
		b.setUserNum(bean.getUserNum());
		BTranFlowMapping bTranFlowMapping = bTranFlowMappingDao.getBeanByBean(b, MatchMode.ANYWHERE);
		//Զ�ڽ��״�����1��Զ�ڽ����������̽��׽�� �� �ܽ��״�����һ���ܽ����������̽��׽��
		bTranFlowMapping.setMarginSpotQty((Integer.parseInt(bTranFlowMapping.getMarginSpotQty())+1)+"");
		bTranFlowMapping.setMarginSpotAmt(FormatParamUtil.getAmountAndPriceFmtByDouble(Double.parseDouble(bTranFlowMapping.getMarginSpotAmt())+deal_amt));  
		bTranFlowMapping.setCount(bTranFlowMapping.getCount()+1);
		bTranFlowMapping.setAmount(bTranFlowMapping.getAmount()+deal_amt);
		
		//3.����bean�Ľ���״̬Ϊ���
		if(flag==true){
			bean.setStatue("D");//����ִ��
		}else{
			bean.setStatue("E");//ǿ��ƽ��
		}
		
		
		//4.����ص�bean���µ���Ӧ�ı�
		marginListToUpdate.add(bean);
		bTranListToUpdate.add(bTranFlowMapping);
		accListToUpdate.add(weAccInfo);
		accListToUpdate.add(anaAccInfo);
		
		//�����ύ
		marginForwardInfoDao.batchUpdate(marginListToUpdate);
		bTranFlowMappingDao.batchUpdate(bTranListToUpdate);
		accInfoDao.batchUpdate(accListToUpdate);
	}
}