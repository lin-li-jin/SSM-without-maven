package com.talent.forex.init;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

public class ForexInit extends HttpServlet {

	Logger log = Logger.getLogger(ForexInit.class);
	private static final long serialVersionUID = 1994360445247550240L;

	public void init() throws ServletException {
		ServletContext context = this.getServletConfig().getServletContext();
		Enumeration paramNames = getInitParameterNames();
		String initObjName = null;
		while (paramNames.hasMoreElements()) {
			try {
				initObjName = getInitParameter((String) paramNames.nextElement());
				Init initObj = (Init) Class.forName(initObjName).newInstance();
				initObj.init(context);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(initObjName + " init fail!");
			}
		}

	}

}
