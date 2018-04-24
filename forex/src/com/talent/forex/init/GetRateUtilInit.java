package com.talent.forex.init;

import javax.servlet.ServletContext;

import com.talent.forex.util.GetRateUtil;

public class GetRateUtilInit implements Init {

	public void init(ServletContext context) {
		context.setAttribute(com.talent.forex.constant.CodeNameConst.RATE_UTIL,
				GetRateUtil.getInstance());
	}

}