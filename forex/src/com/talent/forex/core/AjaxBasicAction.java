package com.talent.forex.core;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.opensymphony.webwork.ServletActionContext;
import com.talent.base.BaseAction;

public class AjaxBasicAction  extends BaseAction{

	protected void processText(String txt,String contentType){
		 try {
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType(contentType);
				PrintWriter out = response.getWriter();
				out.print(txt);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	

}