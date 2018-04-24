 package com.talent.auth.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.interceptor.Interceptor;
import com.talent.auth.bean.model.UserModel;
import com.talent.auth.login.LoginConst;
import com.talent.auth.util.SingleLoginConst;
import com.talent.forex.constant.SysParamNameConst;
import com.talent.tools.WebWorkUtil;

public class IsLoginInterceptor implements Interceptor {

	public void destroy() {

	}

	public void init() {

	}

	public String intercept(ActionInvocation invocation) throws Exception {
		 String result = null;
		 String actionName = invocation.getAction().getClass().getName();
		 HttpServletRequest req = WebWorkUtil.getRequest();
		 HttpServletResponse rep = WebWorkUtil.getResponse();
		UserModel user = (UserModel)req.getSession().getAttribute(LoginConst.LOGIN_USER_MODEL);
		
		 if (user==null||(!user.isLoginFlag())) {
			 if (actionName.equals("com.talent.auth.login.LoginAction")) {
				 result = invocation.invoke();//如果是验证登录的action就放过
			 } else {
				 req.getRequestDispatcher(SysParamNameConst.LOGIN_PAGE).forward(req,rep);
			 }
		 } 
		else{
				result = invocation.invoke();
		}
		return result;
	}
	

}
