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

/*session����*/
public class SessionFacade {

	private static Logger logger = Logger.getLogger(SessionFacade.class);
	// �̰߳�ȫ��
	private static Hashtable<String, UserModel> sessionTb = new Hashtable<String, UserModel>();

	public static void setUserLogin(UserModel user) {
		/* �û���¼��Ҫ�Ѿɵ��û���Ϣ��session����� ,��������дcookie*/
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
			logger.warn("�ɵ�session�˳�3:" + sessionId);
		}
	}

	/**
	 * ǿ���û��˳�
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

		/* �ȴӿͻ��˶�ȡcookie��ȡ�Ѿ���½��������½�����û�id */
		String cookieUserId = null;
		Cookie[] cookies = WebWorkUtil.getRequest().getCookies();
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			if (SingleLoginConst.USER_COOKIE_NAME.equals(cookie.getName())) {
				cookieUserId = cookie.getValue();
				logger.warn("cookie�е�UserId->" + cookieUserId);
				break;
			}
		}

		/* �ж�ͬһ̨���Ƿ��������û���½ */
		if (cookieUserId != null
				&& !cookieUserId.equals("")
				&& !d.equals(cookieUserId)
				&& UserConfig.getInstance()
						.getConfigValue(UserConfigKey.SINGLE_MACHINE_LOGIN)
						.toString()
						.equals(SingleLoginConst.SINGLE_MACHINE_LOGIN_TRUE)) {// 0
			logger.warn("�û���Ϊ" + cookieUserId + "�Ѿ���½��������ͬһ̨�������������û���½!");
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
	 * ��session����ʱ���ô˷��� ��sessionHashTable�������<sessionId,user����> Session������
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
		logger.warn("�µ�session����:" + sessionId);
	}

	/**
	 * ����sessionId��session������û���Ϣ���Ҳ����򴴽�һ��
	 * 
	 * @param sessionId
	 * @return
	 */
	public static UserModel getUserModel(String sessionId) {
		UserModel user = (UserModel) sessionTb.get(sessionId);
		/* ����������ֹͣ�������������Զ����¼���sessionId������ʱӦ�õ����ݶ�û���ˣ������Ҫ���´��� */
		if (user == null) {
			createSession(sessionId);// ����һ��Session
			user = (UserModel) sessionTb.get(sessionId);
			if (user.getCaptcha() == null) {
				user.setCaptcha(CaptchaUtil.getInstance().getNextImageCaptcha());
			}
		}
		return user;
	}

	/**
	 * �����û�id��session������û���Ϣ
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
	 * ����userId��session���ҵ�UserModel��������ɾ��
	 * 
	 * @param userId
	 */
	private static void removeUserFromSession(String userId) {
		UserModel user = getUserModelByUserId(userId);
		if (user != null) {
			sessionTb.remove(user.getSessionId());
			// logger.warn("session��HashTableɾ��:"+user.getSessionId());
		}
	}

	/**
	 * ����userId��session���ҵ�UserModel��������ɾ��
	 * 
	 * @param userId
	 */
	private static void putUserModelToTable(String sessionId, UserModel user) {
		synchronized (sessionTb) {
			sessionTb.put(sessionId, user);
			logger.warn("session���뵽HashTable:" + sessionId);
		}
	}

	/**
	 * ����userId��session���ҵ�UserModel��������ɾ��
	 * 
	 * @param userId
	 */
	private static void removeUserFromSessionBySessionId(String sessionId) {
		synchronized (sessionTb) {
			sessionTb.remove(sessionId);
			logger.warn("�ɵ�session��HashTable�Ƴ�:" + sessionId);
		}
	}

	public static Hashtable getSessionTb() {
		return sessionTb;
	}

	public static void setSessionTb(Hashtable sessionTb) {
		SessionFacade.sessionTb = sessionTb;
	}

}
