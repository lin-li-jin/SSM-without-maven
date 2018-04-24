package com.talent.auth.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.interceptor.Interceptor;
import com.talent.auth.bean.model.TipModel;
import com.talent.forex.constant.SessionNameConst;
import com.talent.tools.WebWorkUtil;

public class PermCtrlInterceptor implements Interceptor {

	private String permission;// 检查是否有相应权限

	private final static String REQUEST_URL = "requestUrl";

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void init() {
		// TODO Auto-generated method stub

	}

	public String intercept(ActionInvocation invocation) throws Exception {
		String result = null;
		// String actionName = invocation.getAction().getClass().getName();
		HttpServletRequest req = WebWorkUtil.getRequest();
		HttpServletResponse rep = WebWorkUtil.getResponse();

		// UserModel userModel = (UserModel)
		// req.getSession().getAttribute(LoginConst.LOGIN_USER_MODEL);

		/* 如果用户没有相应权限则重定向到登录页面 */
		// if (getPermission() != null
		// && !userModel.getPermissions().containsKey(getPermission())) {
		/* 设置提示信息 */
		TipModel model = new TipModel();
		model.setProcessCode("10");
		model.setTip("NoPermissionException");
		req.setAttribute(SessionNameConst.TIP_MODEL, model);

		// String query = req.getQueryString();
		// String url = req.getRequestURL().toString();
		// url = query == null ? url : url + "?" + query;

		/* 保存原请求url到cookies，登陆成功后重定向到该连接 */
		// Cookie cookie = new Cookie(REQUEST_URL, url);
		// cookie.setPath("/");
		// rep.addCookie(cookie);
		// SessionFacade.setUserLogout(userModel);
		// req.getRequestDispatcher("/jsp/global/login/Login.jsp").forward(req,
		// rep);
		// }
		// else {
		// result = invocation.invoke();
		/* 如果是登录action，并且登录成功，还需要重定向到相应的url */
		// if (actionName.equals("com.talent.auth.login.LoginAction")
		// && result.equals("success")) {
		// String url = null;
		/* 从cookies里获取旧请求url */
		// Cookie[] cookies = req.getCookies();
		// if (cookies != null) {
		// for (int i = 0; i < cookies.length; i++) {
		// Cookie cookie = cookies[i];
		// if (REQUEST_URL.equals(cookie.getName())) {
		// url = cookie.getValue();
		// break;
		// }
		// }
		// }
		// url = url == null || url.equals("") ? req.getContextPath()
		// : url;
		/* 如果登录成功且旧的请求url不为空，则重定向旧url，否则重定向到主页 */
		// Cookie cookie = new Cookie(REQUEST_URL, "");
		// cookie.setPath("/");
		// rep.addCookie(cookie);
		// rep.sendRedirect(url);
		// }
		// }
		result = invocation.invoke();
		
		return result;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

}
