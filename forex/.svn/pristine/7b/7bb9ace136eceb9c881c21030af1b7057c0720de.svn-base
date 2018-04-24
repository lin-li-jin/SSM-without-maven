package com.talent.forex.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

import com.talent.forex.bean.model.TipModel;
import com.talent.base.BaseBo;
import com.talent.exception.BoException;
import com.talent.forex.constant.SessionNameConst;
import com.talent.tools.WebWorkUtil;

public class TipAdvice implements MethodInterceptor {

	private Logger logger = Logger.getLogger(getClass().getName());

	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object result = null;
		String tip = "";
		String processCode = "";
		Exception exception = null;
		try {
			result = invocation.proceed();
			processCode = "00";
			tip = invocation.getMethod().getName() + ".success";
		} catch (Exception e) {
			logger.error("--------buildBo have exception:"+e.getMessage());
			exception = e;
			processCode = "10";
			if (e instanceof BoException)
				tip = ((BoException) e).getExceptionType();
			else {
				String exceptionName = e.getClass().getName();
				exceptionName = exceptionName.substring(exceptionName.lastIndexOf(".") + 1);
				tip = exceptionName;
			}
			logger.error("--------buildBo have exception:"+ tip);
		} finally {
			TipModel model = new TipModel();
			model.setProcessCode(processCode);//00,10
			model.setTip(tip);
			model.setException(exception);
			model.setParams(BaseBo.getTipParams());
			BaseBo.getTipParams().clear();
			WebWorkUtil.requestPut(SessionNameConst.TIP_MODEL, model);
		}
		return result;
	}

}
