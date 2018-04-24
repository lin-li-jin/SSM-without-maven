package com.talent.forex.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

import com.talent.forex.util.OperationLogUtil;
import com.talent.hibernate.util.HibernateUtil;
import com.talent.hibernate.util.TransactionNestUtil;

public class TransactionAdvice implements MethodInterceptor {

	private Logger logger = Logger.getLogger(getClass().getName());
	
	//private TransactionDao transactionDao = new TransactionDao();
	
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object result = null;
		String rs = null;
		String methodName = null;
		long startTime = System.currentTimeMillis();
		String threadNum = String.valueOf(startTime);
		try {
			
			HibernateUtil.beginTransaction();
			rs = TransactionNestUtil.reference();
			logger.warn("--transaction begin--ID:" + threadNum);
			result = invocation.proceed();
			methodName = invocation.getMethod().getName();
			//System.out.println("****************************************"+methodName+"****************************************");
			String module = "";
			if(methodName.equals("CNYAllTradeRecordListQuery")){
				module = "����ҽ���ģ��";
			}
			else if(methodName.equals("foreignAllTradeRecordListQuery")){
				module = "��ҶԽ���ģ��";
			}
			else if(methodName.equals("cashAllTradeRecordListQuery")){
				module = "��֤����ģ��";
			}
//			else if(methodName.equals("accountFlowAllTradeRecordListQuery")){
//				module = "���׹���";
//			}
			
			if(module.equals("") || module == null){
				
			}
			else{
				OperationLogUtil.recordOperation(module);
//				//�����û�
//				bean.setUserNum(UserModelUtil.getUser().getUserId());
//				//���÷�������
//				bean.setType(type);
//				//��������ʱ��
//				bean.setDate(GetDateTimeUtil.getCurrentDate().substring(0, 8));
//				bean.setTime(GetDateTimeUtil.getCurrentTime().substring(0, 4));
//				//TransactionDao transactionDao = new TransactionDao();
//				//transactionDao.add(bean);
//				
//				//transactionDao.addBean(bean);
//				
//				//����ע�͵�����ͺ��ˣ��о���session�����ˣ���һ����springע���dao�������ݿ�
//				Session session = HibernateUtil.getSession();
//				Transaction transaction = session.beginTransaction();
//				session.save(bean);
//				transaction.commit();
			}
			
			TransactionNestUtil.releaseRef(rs);
			if (!TransactionNestUtil.isReference()) {
				HibernateUtil.commitTransaction();
			}
		} catch (Exception e) {
			TransactionNestUtil.releaseRef(rs);
			if (!TransactionNestUtil.isReference()) {
				HibernateUtil.rollbackTransaction();
			}
			logger.error("--TransactionAdvice Exception--"+e.getMessage());
			logger.error(this,e);
//			e.printStackTrace();
			throw e;
		} finally {
			if (!TransactionNestUtil.isReference()) {
				HibernateUtil.closeSession();
				long endTime = System.currentTimeMillis();
				double usingTime = (Double.valueOf(endTime-startTime))/1000;
				logger.warn("--transaction close--ID:" + threadNum + " methodName:" + methodName + " usingTime:" + usingTime + "s");
				if(usingTime > 3) {
					logger.warn("******************ALERT:" + methodName + "Very slow,Exceed 3seconds******************");
				}
				
			}
			
		}
		return result;
	}

}