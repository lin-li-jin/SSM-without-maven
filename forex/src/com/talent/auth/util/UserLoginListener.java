package com.talent.auth.util;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class UserLoginListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent event) {
		SessionFacade.createSession(event.getSession().getId());
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		SessionFacade.setUserLogOutAfterTimeOut(event.getSession().getId());
	}

}
