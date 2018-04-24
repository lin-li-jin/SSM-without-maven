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
				module = "人民币交易模块";
			}
			else if(methodName.equals("foreignAllTradeRecordListQuery")){
				module = "外币对交易模块";
			}
			else if(methodName.equals("cashAllTradeRecordListQuery")){
				module = "保证金交易模块";
			}
//			else if(methodName.equals("accountFlowAllTradeRecordListQuery")){
//				module = "交易管理";
//			}
			
			if(module.equals("") || module == null){
				
			}
			else{
				OperationLogUtil.recordOperation(module);
//				//设置用户
//				bean.setUserNum(UserModelUtil.getUser().getUserId());
//				//设置方法类型
//				bean.setType(type);
//				//设置日期时间
//				bean.setDate(GetDateTimeUtil.getCurrentDate().substring(0, 8));
//				bean.setTime(GetDateTimeUtil.getCurrentTime().substring(0, 4));
//				//TransactionDao transactionDao = new TransactionDao();
//				//transactionDao.add(bean);
//				
//				//transactionDao.addBean(bean);
//				
//				//明：注释掉这里就好了，感觉是session混乱了，试一下用spring注入的dao操作数据库
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
