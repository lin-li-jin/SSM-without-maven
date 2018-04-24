package com.talent.auth.router;

import com.talent.forex.constant.SysParamNameConst;
import com.talent.forex.core.AjaxBasicAction;
import com.talent.forex.util.UserModelUtil;

public class IndexAction extends AjaxBasicAction {
	
	private final static String STUDENT = "student";
	private final static String TEACHER = "teacher";
	
	public String doRoute(){
		String userType = UserModelUtil.getUser().getUserType();
		if( SysParamNameConst.STUDENT.equals( userType ) ){
			return STUDENT;
		}
		return SUCCESS;
	}
}
