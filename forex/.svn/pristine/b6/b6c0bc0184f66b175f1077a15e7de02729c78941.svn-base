package com.talent.forex.init;

import javax.servlet.ServletContext;

import com.talent.forex.constant.ApplicationNameConst;
import com.talent.forex.util.SpotUtil;

public class SpotTableInit implements Init {

	@Override
	public void init(ServletContext context) {
		context.setAttribute(ApplicationNameConst.SPOT_UTIL, SpotUtil.getInstance());

	}

}
