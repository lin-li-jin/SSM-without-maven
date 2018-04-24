package com.talent.forex.advice;

import java.util.HashMap;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

import com.talent.tools.WebWorkUtil;

public class FlashAdvice implements MethodInterceptor {

	private Logger logger = Logger.getLogger(getClass().getName());

	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object result = null;
		String tip = "";
		String processCode = "";
		Exception exception = null;
		try {
			result = invocation.proceed();
			//processCode = "00";
			//tip = invocation.getMethod().getName() + ".success";
			String str=invocation.getMethod().toString();
			String method=invocation.getMethod().getName();
			String[] clazz=str.split(" ");
			String c=null;
			for (String i:clazz){
				if (i.indexOf(method)>=0) {
					c=i.substring(0,i.indexOf("("));
					break;
				}
			}
			if (c!=null) {
				HashMap<String,String> map=new HashMap<String,String>();
				map.put("com.talent.forex.modules.TT.TTBo.ttRemittApprove","../../../resources/swf/tt1-1(after).swf");
				map.put("com.talent.forex.modules.TT.TTBo.ttStopPayApprove","../../../resources/swf/tt1-2(after).swf");
				map.put("com.talent.forex.modules.TT.TTBo.ttReturnApprove","../../../resources/swf/tt1-3(after).swf");
				map.put("com.talent.forex.modules.inRemittance.InRemittanceBo.reviewSuccDone","../../../resources/swf/tt2-1(after).swf");
				map.put("com.talent.forex.modules.inRemittance.InRemittanceBo.reviewStopPayDone","../../../resources/swf/tt2-2(after).swf");
				map.put("com.talent.forex.modules.inRemittance.InRemittanceBo.irReviewReSuccDone","../../../resources/swf/tt2-3(after).swf");
				if (map.containsKey(c)) {
					WebWorkUtil.requestPut("endFlashName",map.get(c));
				}
			}
		} catch (Exception e) {
			
			throw e;
		} finally {
			
		}
		return result;
	}

}
