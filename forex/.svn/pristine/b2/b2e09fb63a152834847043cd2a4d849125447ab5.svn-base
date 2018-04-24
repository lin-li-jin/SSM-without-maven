package com.talent.auth.login;

import com.talent.auth.bean.domain.Users;
import com.talent.auth.bean.model.UserModel;
import com.talent.auth.dao.UsersDao;
import com.talent.auth.util.SessionFacade;
import com.talent.base.BaseBo;
import com.talent.exception.BoException;
import com.talent.forex.common.Md5PwdEncoder;
import com.talent.tools.CaptchaUtil;
import com.talent.tools.WebWorkUtil;
import org.hibernate.criterion.MatchMode;

import java.util.ArrayList;

/*
 * Amendment No.: forex130012
 * Modify By    : sunyan	
 * Description  : 用户权限
 * Modify Date  : 2013-8-12
 * 
 */
@SuppressWarnings("static-access")
//forex130012 BEGIN
public class LoginBo extends BaseBo {

	private UsersDao usersDao;

	/**
	 * 用户登录,依据实验管理系统传来的参数 ：登录号、群组号
	 * @param userModel
	 * @return
	 * @throws Exception
	 */
	// 用于判断，用户类型是：1：教师；2：学生	
	@SuppressWarnings("unchecked")
	public UserModel loginDone(UserModel userModel) throws BoException {
		Users bean=new Users();
		bean.setUserNum(userModel.getUserId());
		//forex130012 BEGIN
		bean.setPassword(userModel.getPassword());
		//forex130012 END
		Users user=null;
		try {
			user = usersDao.getBeanByBean(bean,MatchMode.EXACT);
		} catch (NullPointerException e) {
			logger.warn("该用户不存在"+userModel.getUserId());
		}
		
		/* 第一步，查找该用户的岗位 */
		if (user != null) {			
			/* userModel2 真正的是放到session中，userModel只是用于封装界面数据 */
				userModel.setuId(user.getId());
				userModel.setName(user.getName());
				userModel.setLoginFlag(true);
				userModel.setUserId(user.getUserNum());
				userModel.setGroupOneId(user.getGroupOne());
				userModel.setGroupTwoId(user.getGroupTwo());
				userModel.setUserType(user.getUserType());
				userModel.setStatus(user.getStatus());
				ArrayList<String> permissionsList = new ArrayList<String>();
				permissionsList.add(user.getUserType());
				userModel.setPermissions(permissionsList);

				/*首次登陆检查*/
				if(null==user.getLoginTime()||0==user.getLoginTime()){
					userModel.setPwdModifyFlag(true);
					user.setLoginTime(1);
					usersDao.updateBean(user);
				}
				else{
					userModel.setPwdModifyFlag(false);
					int time=user.getLoginTime();
					user.setLoginTime(time+1);
					usersDao.updateBean(user);
				}
		} else {
			/* 此用户不存在，抛出异常 */
			loginFalseCheck(userModel);//登陆错误检查
			userModel = null;
		}
		WebWorkUtil.sessionPut(LoginConst.LOGIN_USER_MODEL, userModel);
		return userModel;
	}
	
	/**
	 * 输出登陆识别码
	 *
	 */
	public void changeChallenge() {
		UserModel user = SessionFacade.getUserModel(WebWorkUtil.getSession()
				.getId());
		user.setCaptcha(CaptchaUtil.getInstance().getNextImageCaptcha());
		try {
			CaptchaUtil.getInstance().writeCaptchaImage(
					WebWorkUtil.getResponse().getOutputStream(),
					user.getCaptcha().getImageChallenge());
		} catch (Exception e) {

		}
	}

	/**
	 * 修改密码
	 * @param userModel
	 * @param newPassword
	 *
	 */
	public void loginPwdModify(UserModel userModel, String newPassword){
		 Md5PwdEncoder md5 = new Md5PwdEncoder();
		newPassword=md5.encodePassword(newPassword);
		String userId = userModel.getUserId();
		String userType = userModel.getUserType();
		Users users = new Users();
		users.setUserNum(userId);
		users.setUserType(userModel.getUserType());
		users = usersDao.getBeanByBean(users, MatchMode.EXACT);
		if(users != null){
			users.setPassword(newPassword);
			usersDao.updateBean(users);
		}
	}
	
	//登陆错误操作，登陆错误5次锁住账号
	public void  loginFalseCheck(UserModel userModel){
		Users users = new Users();
		users.setUserNum(userModel.getUserId());
		users = usersDao.getBeanByBean(users, MatchMode.EXACT);
		if(users!=null){
			int falseTime = (Integer)users.getFalseTime();
			if(falseTime>=5){
				users.setStatus("0");
				usersDao.updateBean(users);
			}
			else{
				falseTime++;
				users.setFalseTime(falseTime);
				usersDao.updateBean(users);
			}
		}
	}

	public UsersDao getUsersDao() {
		return usersDao;
	}


	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}
	
}

//forex130012 END