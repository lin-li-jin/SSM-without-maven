package com.talent.forex.advice;
// package com.talent.ees.advice;
//
// import java.text.MessageFormat;
// import java.util.Date;
//
// import org.aopalliance.intercept.MethodInterceptor;
// import org.aopalliance.intercept.MethodInvocation;
// import org.apache.log4j.Logger;
// import org.hibernate.criterion.MatchMode;
//
// import com.talent.auth.bean.model.UserModel;
// import com.talent.auth.login.LoginConst;
// import com.talent.base.BaseBo;
// import com.talent.cbms.bean.domain.EventReg;
// import com.talent.cbms.bean.domain.OperationLog;
// import com.talent.ees.dao.EventRegDao;
// import com.talent.ees.dao.OperationLogDao;
// import com.talent.ees.exception.CommonLogException;
// import com.talent.exception.BoException;
// import com.talent.hibernate.util.HibernateUtil;
// import com.talent.hibernate.util.TransactionNestUtil;
// import com.talent.tools.WebWorkUtil;
//
// public class CommonLogAdvice implements MethodInterceptor {
//
// private Logger logger = Logger.getLogger(getClass().getName());
//
// private OperationLogDao operationLogDao;
//
// private EventRegDao eventRegDao;
//
// public Object invoke(MethodInvocation invocation) throws Throwable {
// Object result = null;
//
// String methodName = invocation.getMethod().getName();
// try {
// logger.debug("common log begin");
//
// result = invocation.proceed();
//
// successLog(methodName);
// logger.debug("common log end");
// } catch (CommonLogException e) {
// logger.error(e.getMessage());
// BoException exception = new BoException(e.getMessage());
// exception.setExceptionType("CommonLogException");
// throw exception;
// } catch (Exception e) {
// logger.error(e.getMessage());
// try {
// failLog(methodName);
// } catch (Exception ex) {
// logger.error(ex.getMessage());
// BoException exception = new BoException(e.getMessage());
// exception.setExceptionType("CommonLogException");
// e = exception;
// }
// throw e;
// }
//
// return result;
// }
//
// public void successLog(String methodName) {
// String sfName = null;
// String ref = "";
// try {
// sfName = HibernateUtil.getThreadCurrentName();
// if(!sfName.equals("hibernate/sessionFactory/cbms")){
// HibernateUtil.switchSessionFactory("hibernate/sessionFactory/cbms");
// }else{
// HibernateUtil.beginTransaction();
// }
//			
// ref = TransactionNestUtil.reference();
//
// EventReg eventRegSearch = new EventReg();
// eventRegSearch.setFunctionName(methodName);
// EventReg eventReg = eventRegDao.getBeanByBean(eventRegSearch,
// MatchMode.EXACT);
//
// if (eventReg != null) {
// UserModel user = ((UserModel) WebWorkUtil
// .sessionGet(LoginConst.LOGIN_USER_MODEL));
//
// log(eventReg, user.getId(), "0");
// }
//			
// TransactionNestUtil.releaseRef(ref);
// if(!TransactionNestUtil.isReference()){
// HibernateUtil.commitTransaction();
// }
// } catch (Exception e) {
// e.printStackTrace();
// TransactionNestUtil.releaseRef(ref);
// if(!TransactionNestUtil.isReference()){
// HibernateUtil.rollbackTransaction();
// }
// throw new CommonLogException(e.getMessage());
// } finally {
// if(sfName != null){
// HibernateUtil.switchSessionFactoryNoTs(sfName);
// }
//			
// if(!TransactionNestUtil.isReference()){
// HibernateUtil.closeSession();
// }
// }
// }
//
// public void failLog(String methodName) {
// String sfName = null;
// String ref = "";
// try {
// sfName = HibernateUtil.getThreadCurrentName();
// if(!sfName.equals("hibernate/sessionFactory/cbms")){
// HibernateUtil.switchSessionFactory("hibernate/sessionFactory/cbms");
// }else{
// HibernateUtil.beginTransaction();
// }
// ref = TransactionNestUtil.reference();
//
// EventReg eventRegSearch = new EventReg();
// eventRegSearch.setFunctionName(methodName);
// EventReg eventReg = eventRegDao.getBeanByBean(eventRegSearch,
// MatchMode.EXACT);
//
// /**
// * failLog:1表示需要记日志
// */
// if (eventReg != null && eventReg.getFailLog().equals("1")) {
// UserModel user = ((UserModel) WebWorkUtil
// .sessionGet(LoginConst.LOGIN_USER_MODEL));
//				
// String userId = "";
// /*用户登录失败*/
// if(user == null && methodName.equals("loginDone")){
// userId = ((String [])WebWorkUtil.requestGet("userInfo.userId"))[0];
// }else{
// userId = user.getId();
// }
//				
// log(eventReg, userId, "1");
// }
//
// TransactionNestUtil.releaseRef(ref);
// if(!TransactionNestUtil.isReference()){
// HibernateUtil.commitTransaction();
// }
// } catch (Exception e) {
// TransactionNestUtil.releaseRef(ref);
// if(!TransactionNestUtil.isReference()){
// HibernateUtil.rollbackTransaction();
// }
// throw new CommonLogException(e.getMessage());
// } finally {
// if(sfName != null){
// HibernateUtil.switchSessionFactoryNoTs(sfName);
// }
//			
// if(!TransactionNestUtil.isReference()){
// HibernateUtil.closeSession();
// }
// }
// }
//
// /**
// * result:0表示成功，1表示失败
// *
// * @param operObject
// * @param puserId
// * @param suserId
// * @param result
// */
// public void log(EventReg eventReg, String userId, String result) {
// OperationLog operLogBean = new OperationLog();
// operLogBean.setEventId(eventReg.getEventId());
// operLogBean.setUserId(userId);
// operLogBean.setResult(result);
// operLogBean.setTime(new Date());
// operLogBean.setNotes(MessageFormat.format(eventReg.getNotes(),
// BaseBo.getLogParams().toArray()));
// BaseBo.getLogParams().clear();
// operationLogDao.makePersistent(operLogBean, false);
// }
//
// public OperationLogDao getOperationLogDao() {
// return operationLogDao;
// }
//
// public void setOperationLogDao(OperationLogDao operationLogDao) {
// this.operationLogDao = operationLogDao;
// }
//
// public EventRegDao getEventRegDao() {
// return eventRegDao;
// }
//
// public void setEventRegDao(EventRegDao eventRegDao) {
// this.eventRegDao = eventRegDao;
// }
// }
