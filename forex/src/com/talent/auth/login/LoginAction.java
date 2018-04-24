package com.talent.auth.login;

import com.talent.auth.bean.model.UserModel;
import com.talent.auth.dao.UsersDao;
import com.talent.forex.common.Md5PwdEncoder;
import com.talent.forex.util.SysParamUtil;
import com.talent.tools.WebWorkUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/*
 * Amendment No.: forex130011
 * Modify By    : Alpha Liang
 * Description  : 多国语言支持模块，登录时设置语言为英文
 * Modify Date  : 2013-6-28
 * 
 */
/*
 * Amendment No.: forex130012
 * Modify By    : sunyan	
 * Description  : 用户权限
 * Modify Date  : 2013-8-12
 * 
 */

@SuppressWarnings("unchecked")
public class LoginAction extends com.talent.forex.core.ForexBaseAction {
	private UsersDao usersDao;
	public UsersDao getUsersDao() {
		return usersDao;
	}


	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	private UserModel userModel = new UserModel();
	private LoginBo loginBo;
	private String reset;
	private String addr;
	private Logger logger=Logger.getLogger(LoginAction.class);
	private String newPassword;
	private String newName;
	private String password;
	private String userId;
	public String getReset() {
		return reset;
	}

	public void setReset(String reset) {
		this.reset = reset;
	}
	
	/**
	 * 登录
	 * @return
	 */
	public String login() {
	
		String addr2=(String)sessionGet("addr");
		
		requestPut("reset",this.reset);
		if(null!=addr&&!("").equals(addr)&&null==addr2){
			sessionPut("addr",addr);
		}
		//从session中获取登录的用户对象:如果不为空,进行下一步判断;
		UserModel oldUserModel = (UserModel)sessionGet(LoginConst.LOGIN_USER_MODEL);
		if(oldUserModel!=null){
//			boolean flag=(!oldUserModel.getUserType().equals("S")&&null!=oldUserModel.getActionName()
//					&&!oldUserModel.getActionName().equals(addr))&&addr2.equals("0");
//			if(flag){
			boolean flag=(!"S".equals(oldUserModel.getUserType())&&null!=oldUserModel.getActionName()
					&&!addr.equals(oldUserModel.getActionName()))&&"0".equals(addr2);
			if(flag){
			if(oldUserModel.getBankNo().equals(userModel.getBankNo()) && oldUserModel.getUserId().equals(userModel.getUserId())){
				sessionPut("addr",addr);
				sessionPut("falseTime","0");//错误登陆次数
				return SUCCESS;
			}
			}
//			}
			
		}
		//是否有输入内容
		if(userModel.getUserId()==null||userModel.getPassword()==null)
		{
			sessionPut("checkResult","false");
			return ERROR;
		}
		try{
			
			//密码加密 add by zhouyf 2015.08.18 md5
			Md5PwdEncoder md5 = new Md5PwdEncoder();
			
			userModel.setPassword(md5.encodePassword(userModel.getPassword()));
			
			userModel = loginBo.loginDone(userModel);
			if(userModel==null)
			{
				sessionPut("checkResult","false");
				return ERROR;
			}
			else{
				if("1".equals(userModel.getStatus())){
					String pwdFirst= SysParamUtil.getCharValueByName("pwd_modify_first");//首次登陆是否需要强制修改密码：0，不需要，1，需要
					if(userModel.isPwdModifyFlag()&&pwdFirst.equals("1")){
						return "input";// 修改密码
					}
					else{
						return SUCCESS;// 首页
					}
				}
				else{
					sessionPut("checkResult","notActive");
					return ERROR;
				}
			}
		}catch (Exception e) {

			e.printStackTrace();
			sessionPut("checkResult","false");
			return ERROR;
		}
		
	}
	public String notYetInit(){
		return SUCCESS;
	}
	/**
	 * 退出
	 * @return
	 */
	public String logout(){
		UserModel userModel = (UserModel) sessionGet(LoginConst.LOGIN_USER_MODEL);
		if(userModel==null)return SUCCESS;
		//loginBo.logoutDone(userModel);
		sessionPut(LoginConst.LOGIN_USER_MODEL, null);
		HttpSession session = WebWorkUtil.getSession();
		Enumeration session_enum=session.getAttributeNames();
		while(session_enum.hasMoreElements()){
			String string=(String)session_enum.nextElement();
			if(StringUtils.isNotBlank(string)){
				logger.warn(string);
				session.removeAttribute(string);
			}
		}
		requestPut(LoginConst.LOGOUT_TIP_MODEL, null);
		return SUCCESS;
	}
	public String closeAddr(){
		UserModel userModel = (UserModel) sessionGet(LoginConst.LOGIN_USER_MODEL);
		userModel.setActionName((String) sessionGet("addr"));
		sessionPut(LoginConst.LOGIN_USER_MODEL, userModel);
		sessionPut("addr","0");
		return null;
	}
	
	/**
	 * 修改密码初始化
	 */
	public String userPwdModifyInit(){
		return SUCCESS;
	}
	/**
	 * 修改密码初始化--学生
	 */
	public String userPwdModifyInit2(){
		return SUCCESS;
	}
	/**
	 * 修改密码
	 */
	public String loginPwdModify(){
		UserModel userModel = (UserModel) sessionGet(LoginConst.LOGIN_USER_MODEL);
		loginBo.loginPwdModify(userModel,newPassword);
		return SUCCESS;
	}

	public String changeChallenge() {
		loginBo.changeChallenge();
		return null;
	}

	public void setLoginBo(LoginBo loginBo) {
		this.loginBo = loginBo;
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

	public LoginBo getLoginBo() {
		return loginBo;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
