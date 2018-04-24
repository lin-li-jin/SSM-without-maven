package com.talent.forex.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class CharsetFilter implements Filter {
	
	private String encoding = "GBK";
	private static Logger log=Logger.getLogger(CharsetFilter.class);
	private static String fireFox="application/x-www-form-urlencoded; charset=UTF-8";
	private static String ie="application/x-www-form-urlencoded";
	
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		String contentType=req.getContentType();
		if (req.getHeader("X-Requested-With") != null && req.getHeader("X-Requested-With")
								.equalsIgnoreCase("XMLHttpRequest")) {
			request.setCharacterEncoding("GBK");
		} else {
		     request.setCharacterEncoding("GBK");
		}	
		chain.doFilter(request, response);//
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		String encode = filterConfig.getInitParameter("encoding");
		if(encode != null){
			encoding = encode;
		}
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

}
