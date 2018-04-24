package com.talent.forex.tag;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.talent.auth.bean.model.UserModel;
import com.talent.auth.login.LoginConst;
import com.talent.forex.lang.Resource;

/*
 * Amendment No.: forex130011
 * Modify By    : Alpha Liang
 * Description  : �������֧��ģ�飬�Զ����ǩ
 * Modify Date  : 2013-6-28
 * 
 */

public class TalentLabelTag extends TagSupport {

	public String name = null;
	
	public Resource rs = null;
	
	public TalentLabelTag() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int doEndTag() {
		JspWriter out = pageContext.getOut();
		
		UserModel userModel = (UserModel) pageContext.getSession().getAttribute(LoginConst.LOGIN_USER_MODEL);
		
		if (rs == null) {
			rs = new Resource();
		}
		
		String lang = null;
		if (userModel != null) {
			lang = userModel.getLang();
		}
		
		String label = rs.srcStr(name, lang);
		
		try {
			out.print(label);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this.EVAL_PAGE;
	}

}
