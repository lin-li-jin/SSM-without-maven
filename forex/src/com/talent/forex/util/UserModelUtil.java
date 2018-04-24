package com.talent.forex.util;

import com.talent.auth.bean.model.UserModel;
import com.talent.auth.login.LoginConst;
import com.talent.forex.constant.SysParamNameConst;
import com.talent.tools.WebWorkUtil;

public class UserModelUtil {
	public static UserModel getUser(){
		UserModel user = (UserModel)WebWorkUtil.sessionGet(LoginConst.LOGIN_USER_MODEL);
		return user;
	}
	
//	public static String getFixedSchoolNum(){
//		UserModel user = getUser();
//		return SysParamNameConst
//							.SUPER_MANAGER_SCHOOLNUM.equals(user.getSchoolNum()) ? "%" : user.getSchoolNum();
//	}
	
}
