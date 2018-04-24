package com.talent.forex.util;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.talent.exception.BoException;
import com.talent.forex.bean.domain.OperationLog;
import com.talent.forex.dao.OperationLogDao;
import com.talent.hibernate.util.HibernateUtil;
import com.talent.hibernate.util.TransactionNestUtil;

public class OperationLogUtil {

	private static Logger logger = Logger.getLogger(SequenceUtil.class);
	
	private OperationLogDao operationLogDao;
	
	public static void recordOperation(String module){
		String rsf=null;
		try {
			rsf = TransactionNestUtil.reference();
			
			OperationLog bean = new OperationLog();
			//设置用户
			bean.setUserNum(UserModelUtil.getUser().getUserId());
			//设置方法类型
			bean.setModule(module);
			//设置日期时间
			bean.setDate(GetDateTimeUtil.getCurrentDate().substring(0, 8));
			bean.setTime(GetDateTimeUtil.getCurrentTime().substring(0, 4));
			OperationLogDao operationLogDao = new OperationLogDao();
			operationLogDao.addBean(bean);
			
//			Session session = HibernateUtil.getSession();
//			session.save(bean);
//			String hql  = "insert into OperationLog(USER_NUM,MODULE,DATE,TIME) values(:userNum,:module,:date,:time)";
//			Query query=session.createQuery(hql);
//			query.setString("userNum", UserModelUtil.getUser().getUserId());
//			query.setString("module", module);
//			query.setString("date", GetDateTimeUtil.getCurrentDate().substring(0, 8));
//			query.setString("time", GetDateTimeUtil.getCurrentTime().substring(0, 4));
//			query.setParameter(0, UserModelUtil.getUser().getUserId());
//			query.setParameter(1, module);
//			query.setParameter(2, GetDateTimeUtil.getCurrentDate().substring(0, 8));
//			query.setParameter(3, GetDateTimeUtil.getCurrentTime().substring(0, 4));
			
//			Transaction transaction = session.beginTransaction();
//			session.persist(bean);
//			transaction.commit();
			//query.executeUpdate();
			
			//query.list();
			
//			Query query  =  session.createQuery(hql); 
//			query.setLockMode( "seq" ,LockMode.UPGRADE);  // 加锁  
//			List seqList  =  query.list();
//			Seq seq=(Seq) seqList.get(0);
//			
//			result= seq.getSeqNo()+1;
//			
//			if(length <(String.valueOf(result).length()-2)){//1.0 长度为3 -2（.0）
//				result=0;
//			}
////			s.setSeqNo(result);
////			ArrayList c=new ArrayList();
////			c.add(s);
////			seqDao.batchUpdate(c);
////			seqDao.updateBean(s);
//			hql= "update Seq set seqNo="+result+" where tranType='"+ sequenceName+"'";
//			query  =  session.createQuery(hql); 
//			query.executeUpdate();*/
			TransactionNestUtil.releaseRef(rsf);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			TransactionNestUtil.releaseRef(rsf);
			if (!TransactionNestUtil.isReference()) {
				HibernateUtil.rollbackTransaction();
			}
			logger.error("Record operation log fail! failType:[" + "]" + e.getMessage());
			BoException be = new BoException("recordOperation");
			throw be;
		} finally {
			if (!TransactionNestUtil.isReference()) {
				HibernateUtil.closeSession();	
				logger.warn("close session -----OperationUtil");
			}
		}
	}

	public OperationLogDao getOperationLogDao() {
		return operationLogDao;
	}

	public void setOperationLogDao(OperationLogDao operationLogDao) {
		this.operationLogDao = operationLogDao;
	}
}
