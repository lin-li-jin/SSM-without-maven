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
	 * �ƹ�Template,ֱ��������ݵļ�㺯��.
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
	 * ֱ������ַ���.
	 */
	protected String renderText(String text) {
		return render(text, "text/plain;charset=gbk");
	}

	/**
	 * ֱ�����HTML.
	 */
	protected String renderHtml(String html) {
		return render(html, "text/html;charset=gbk");
	}

	/**
	 * ֱ�����XML.
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
