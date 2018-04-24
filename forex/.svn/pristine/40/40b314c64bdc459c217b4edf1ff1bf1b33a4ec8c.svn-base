package com.talent.forex.lang;

import java.io.PrintWriter;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.webwork.ServletActionContext;
import com.talent.auth.bean.model.UserModel;
import com.talent.auth.login.LoginConst;

/*
 * Amendment No.: forex130011
 * Modify By    : Alpha Liang
 * Description  : 多国语言支持模块，更新session里的语言设置
 * Modify Date  : 2013-6-28
 * 
 */

public class ChangeLangAction extends com.talent.forex.core.ForexBaseAction  {

	public String changeLang() {
		 UserModel userModel = (UserModel) sessionGet(LoginConst.LOGIN_USER_MODEL);
		if (userModel != null) {
			String lang = userModel.getLang();
			if ("en".equals(lang)) {
				userModel.setLang("cn");
			} else if ("cn".equals(lang)) {
				userModel.setLang("en");
			} else {
				userModel.setLang("en");
			}
		}
		return SUCCESS;
	}
	public String changeLanguage(){
		Locale locale  = Locale.CHINA;;
		 UserModel userModel = (UserModel) sessionGet(LoginConst.LOGIN_USER_MODEL);
			if (userModel != null) {
				String lang = userModel.getLang();
				if ("en".equals(lang)) {
					userModel.setLang("cn");
					 locale = Locale.CHINA;
				} else if ("cn".equals(lang)) {
					userModel.setLang("en");
					 locale = Locale.US;
				} else {
					userModel.setLang("en");
					 locale = Locale.US;
				}
			}
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");  
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest  request = ServletActionContext.getRequest();
		Map<String,String> map = request.getParameterMap();
		JSONObject data = new JSONObject();
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			 for (String key : map.keySet()) {
					data.put(key, context.getMessage(key, null,"",locale));
				  }
			out.println(data.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return SUCCESS;
	}
}
