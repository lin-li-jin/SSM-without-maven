package com.talent.forex.core;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.talent.tools.WebWorkUtil;

public class CoreAjaxAction {
	private static final Logger log = Logger.getLogger(CoreAjaxAction.class);

	/**
	 * 绕过Template,直接输出内容的简便函数.
	 */
	protected String render(String text, String contentType) {
		try {
			HttpServletResponse response = WebWorkUtil.getResponse();
			response.setContentType(contentType);
			response.getWriter().write(text);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 直接输出字符串.
	 */
	protected String renderText(String text) {
		return render(text, "text/plain;charset=gbk");
	}

	/**
	 * 直接输出HTML.
	 */
	protected String renderHtml(String html) {
		return render(html, "text/html;charset=gbk");
	}

	/**
	 * 直接输出XML.
	 */
	protected String renderXML(String xml) {
		return render(xml, "text/xml;charset=UTF-8");
	}

	protected Map<String, Object> jsonRoot = new HashMap<String, Object>();

	public Map<String, Object> getJsonRoot() {
		return jsonRoot;
	}

	public void setJsonRoot(Map<String, Object> jsonRoot) {
		this.jsonRoot = jsonRoot;
	}

}
