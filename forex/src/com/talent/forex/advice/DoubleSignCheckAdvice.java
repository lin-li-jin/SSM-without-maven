package com.talent.forex.advice;
// package com.talent.ees.advice;
//
// import java.lang.reflect.Method;
// import java.util.ArrayList;
// import java.util.Collection;
// import java.util.Iterator;
//
// import org.apache.log4j.Logger;
// import org.hibernate.criterion.MatchMode;
// import org.springframework.aop.MethodBeforeAdvice;
//
// import com.talent.auth.bean.domain.Roles;
// import com.talent.auth.bean.domain.UserInfo;
// import com.talent.auth.bean.model.UserModel;
// import com.talent.auth.dao.UserInfoDao;
// import com.talent.auth.user.UserConst;
// import com.talent.auth.util.SessionFacade;
// import com.talent.ees.bean.model.DoubleSignModel;
// import com.talent.exception.BoException;
// import com.talent.hibernate.util.HibernateUtil;
// import com.talent.hibernate.util.TransactionNestUtil;
// import com.talent.tools.CryptUtil;
// import com.talent.tools.WebWorkUtil;
//
// public class DoubleSignCheckAdvice implements MethodBeforeAdvice {
//
// private Logger logger = Logger.getLogger(getClass().getName());
//	
// private UserInfoDao userInfoDao;
//
// public void before(Method m, Object[] args, Object target) throws Throwable {
// logger.debug("double sign check begin");
// String sfName = null;
// String ref = "";
// try {
// DoubleSignModel doubleSignModel = new DoubleSignModel();
// if(WebWorkUtil.requestGet("doubleSign.userId") != null)
// doubleSignModel.setUserId(((String[]) WebWorkUtil
// .requestGet("doubleSign.userId"))[0]);
// if(WebWorkUtil.requestGet("doubleSign.password") != null)
// doubleSignModel.setPasswd(((String[]) WebWorkUtil
// .requestGet("doubleSign.password"))[0]);
//			
// if(doubleSignModel.getUserId() != null){
// UserModel userModel = (UserModel)
// SessionFacade.getUserModel(WebWorkUtil.getSession().getId());
// if(userModel.getId().equals(doubleSignModel.getUserId())){
// BoException exception = new BoException("double sign check fail,user can't be
// the same!");
// exception.setExceptionType("DoubleSignException.sameUserId");
// throw exception;
// }
//				
// sfName = HibernateUtil.getThreadCurrentName();
// if(!sfName.equals("hibernate/sessionFactory/cbms")){
// HibernateUtil.switchSessionFactoryNoTs("hibernate/sessionFactory/cbms");
// }
// ref = TransactionNestUtil.reference();
//				
// UserInfo searchBean = new UserInfo();
// searchBean.setUserId(doubleSignModel.getUserId());
// searchBean.setPassword(CryptUtil.md5Crypt(doubleSignModel.getPasswd()));
// UserInfo userInfo = userInfoDao.getBeanByBean(searchBean,MatchMode.EXACT);
// if(userInfo == null){
// BoException exception = new BoException("double sign check fail,user not
// exist!");
// exception.setExceptionType("DoubleSignException.userNotExist");
// throw exception;
// }
//				
// if(!userInfo.getStatus().equals(UserConst.AVAILABILITY)){
// BoException exception = new BoException("double sign check fail,user not
// avaliable!");
// exception.setExceptionType("DoubleSignException.userUnavailable");
// throw exception;
// }
//				
// ArrayList paramList = new ArrayList();
// paramList.add(userInfo.getUserId());
// Collection rolesList = userInfoDao.getBeansByParams("getRolesByUserId",
// paramList);
// int doubleSignUserLevel = 999;
// if(rolesList != null){
// Iterator it = rolesList.iterator();
// while(it.hasNext()){
// Roles roles = (Roles) it.next();
// int rolesLevel = Integer.parseInt(roles.getRolesLevel());
// if(doubleSignUserLevel > rolesLevel){
// doubleSignUserLevel = rolesLevel;
// }
// }
// }
//				
// int myUserLevel = Integer.parseInt( userModel.getRolesLevel());
//				
// if(myUserLevel < doubleSignUserLevel){
// BoException exception = new BoException("double sign check fail,userType is
// too low!");
// exception.setExceptionType("DoubleSignException.userTypeFail");
// throw exception;
// }
//				
// TransactionNestUtil.releaseRef(ref);
// }
//
// } catch (Exception e) {
// logger.error(e.getMessage());
// TransactionNestUtil.releaseRef(ref);
// throw e;
// } finally{
// if(sfName != null){
// HibernateUtil.switchSessionFactoryNoTs(sfName);
// }
//			
// if(!TransactionNestUtil.isReference()){
// HibernateUtil.closeSession();
// }
// }
//
// logger.debug("double sign check end");
// }
//
// public UserInfoDao getUserInfoDao() {
// return userInfoDao;
// }
//
// public void setUserInfoDao(UserInfoDao userInfoDao) {
// this.userInfoDao = userInfoDao;
// }
// }
