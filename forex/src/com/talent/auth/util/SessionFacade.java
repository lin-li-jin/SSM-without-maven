package com.talent.auth.util;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.Cookie;

import org.apache.log4j.Logger;

import com.talent.auth.bean.model.SingleLoginCheckResultModel;
import com.talent.auth.bean.model.UserModel;
import com.talent.auth.bean.model.UserModel;
import com.talent.auth.login.LoginConst;
import com.talent.tools.CaptchaUtil;
import com.talent.tools.WebWorkUtil;

/*session正面*/
public class SessionFacade {

	private static Logger logger = Logger.getLogger(SessionFacade.class);
	// 线程安全的
	private static Hashtable<String, UserModel> sessionTb = new Hashtable<String, UserModel>();

	public static void setUserLogin(UserModel user) {
		/* 用户登录，要把旧的用户信息从session里清掉 ,并且重新写cookie*/
		//removeUserFromSession(user.getUserId());
		removeUserFromSessionBySessionId(user.getSessionId());
		Cookie cookie = new Cookie(SingleLoginConst.USER_COOKIE_NAME,
				user.getUserId());
		cookie.setMaxAge(SingleLoginConst.COOKIE_TIME);
		cookie.setPath(WebWorkUtil.getRequest().getContextPath());
		WebWorkUtil.getResponse().addCookie(cookie);
		putUserModelToTable(WebWorkUtil.getSession().getId(), user);
	}

	public static void setUserLogout(UserModel user) {
		Cookie cookie = new Cookie(SingleLoginConst.USER_COOKIE_NAME,
				user.getUserId());
		cookie.setMaxAge(SingleLoginConst.COOKIE_TIME);
		cookie.setPath(WebWorkUtil.getRequest().getContextPath());
		WebWorkUtil.getResponse().addCookie(cookie);
		WebWorkUtil.getSession().removeAttribute(LoginConst.LOGIN_USER_MODEL);
		removeUserFromSessionBySessionId(user.getSessionId());
	}

	public static void setUserLogOutAfterTimeOut(String sessionId) {
		synchronized (sessionTb) {
			sessionTb.remove(sessionId);
			logger.warn("旧的session退出3:" + sessionId);
		}
	}

	/**
	 * 强制用户退出
	 * 
	 * @param userId
	 */
	public static void forceUserLogout(String userId) {
		removeUserFromSession(userId);
	}

	// check
	public static SingleLoginCheckResultModel singleLoginCheck(String d) {
		SingleLoginCheckResultModel model = new SingleLoginCheckResultModel();
		String loginUserId = "";
		String checkFailReason = "";
		boolean result = true;

		/* 先从客户端读取cookie获取已经登陆或曾经登陆过的用户id */
		String cookieUserId = null;
		Cookie[] cookies = WebWorkUtil.getRequest().getCookies();
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			if (SingleLoginConst.USER_COOKIE_NAME.equals(cookie.getName())) {
				cookieUserId = cookie.getValue();
				logger.warn("cookie中的UserId->" + cookieUserId);
				break;
			}
		}

		/* 判断同一台机是否有两个用户登陆 */
		if (cookieUserId != null
				&& !cookieUserId.equals("")
				&& !d.equals(cookieUserId)
				&& UserConfig.getInstance()
						.getConfigValue(UserConfigKey.SINGLE_MACHINE_LOGIN)
						.toString()
						.equals(SingleLoginConst.SINGLE_MACHINE_LOGIN_TRUE)) {// 0
			logger.warn("用户名为" + cookieUserId + "已经登陆，不能在同一台机器上有两个用户登陆!");
			result = false;
			loginUserId = cookieUserId;
			checkFailReason = SingleLoginConst.SINGLE_MACHINE_LOGIN_FAIL_DESCR;
		}

		model.setResult(result);
		model.setLoginUserId(loginUserId);
		model.setCheckFailReason(checkFailReason);
		return model;
	}

	public static boolean challengeCheck(String challenge) {
		boolean result = true;

		UserModel user = getUserModel(WebWorkUtil.getSession().getId());
		if (UserConfig.getInstance().getConfigValue(UserConfigKey.USE_CAPTCHA)
				.toString().equals("1")) {
			result = user.getCaptcha().validateResponse(challenge)
					.booleanValue();
		}

		return result;
	}

	/**
	 * 新session创建时调用此方法 往sessionHashTable里面添加<sessionId,user对象> Session监听器
	 * 
	 * @param sessionId
	 */
	public static void createSession(String sessionId) {
		UserModel user = new UserModel();
		user.setUserId("");
		user.setLoginFlag(false);
		user.setSessionId(sessionId);

		synchronized (sessionTb) {
			sessionTb.put(sessionId, user);
		}
		logger.warn("新的session进入:" + sessionId);
	}

	/**
	 * 根据sessionId从session里查找用户信息，找不到则创建一个
	 * 
	 * @param sessionId
	 * @return
	 */
	public static UserModel getUserModel(String sessionId) {
		UserModel user = (UserModel) sessionTb.get(sessionId);
		/* 服务器正常停止后再启动，会自动重新加载sessionId，但这时应用的数据都没有了，因此需要重新创建 */
		if (user == null) {
			createSession(sessionId);// 创造一个Session
			user = (UserModel) sessionTb.get(sessionId);
			if (user.getCaptcha() == null) {
				user.setCaptcha(CaptchaUtil.getInstance().getNextImageCaptcha());
			}
		}
		return user;
	}

	/**
	 * 根据用户id从session里查找用户信息
	 * 
	 * @param userId
	 * @return
	 */
	public static UserModel getUserModelByUserId(String userId) {
		UserModel model = null;
		Set keys = sessionTb.keySet();
		if (keys != null) {
			Iterator it = keys.iterator();
			while (it.hasNext()) {
				String sessionId = (String) it.next();
				UserModel user = (UserModel) sessionTb.get(sessionId);
				if (user != null && user.getUserId().equals(userId)) {
					model = user;
					break;
				}
			}
		}
		return model;
	}

	/**
	 * 根据userId从session里找到UserModel，并把它删除
	 * 
	 * @param userId
	 */
	private static void removeUserFromSession(String userId) {
		UserModel user = getUserModelByUserId(userId);
		if (user != null) {
			sessionTb.remove(user.getSessionId());
			// logger.warn("session从HashTable删除:"+user.getSessionId());
		}
	}

	/**
	 * 根据userId从session里找到UserModel，并把它删除
	 * 
	 * @param userId
	 */
	private static void putUserModelToTable(String sessionId, UserModel user) {
		synchronized (sessionTb) {
			sessionTb.put(sessionId, user);
			logger.warn("session加入到HashTable:" + sessionId);
		}
	}

	/**
	 * 根据userId从session里找到UserModel，并把它删除
	 * 
	 * @param userId
	 */
	private static void removeUserFromSessionBySessionId(String sessionId) {
		synchronized (sessionTb) {
			sessionTb.remove(sessionId);
			logger.warn("旧的session从HashTable移除:" + sessionId);
		}
	}

	public static Hashtable getSessionTb() {
		return sessionTb;
	}

	public static void setSessionTb(Hashtable sessionTb) {
		SessionFacade.sessionTb = sessionTb;
	}

}
