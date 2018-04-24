package com.talent.forex.init;

import javax.servlet.ServletContext;

import com.talent.forex.constant.ApplicationNameConst;
import com.talent.forex.util.ForwardUtil;

public class ForwardTableInit implements Init {

	@Override
	public void init(ServletContext context) {
		context.setAttribute(ApplicationNameConst.FORWARD_UTIL, ForwardUtil.getInstance());

	}

}
