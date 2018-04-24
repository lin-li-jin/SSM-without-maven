package com.talent.forex.base.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.opensymphony.webwork.ServletActionContext;
import com.talent.base.BaseAction;
import com.talent.forex.base.model.EcPage;

/**
 * ∑÷“≥°Äaction
 * @author fengronghua
 * @version 2012-5-15
 */
@SuppressWarnings("serial")
public class BasePageAction extends BaseAction {

	public EcPage getEcPageModel(String tableId) {
		EcPage ecPageModel = new EcPage(ServletActionContext.getRequest(),tableId); 
		return ecPageModel;
	}
	
	public EcPage getEcPageModel() {
		return getEcPageModel(null);
	}
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
