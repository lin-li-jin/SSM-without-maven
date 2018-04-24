package com.talent.forex.util;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description: 依据学号取学生 系别 专业 年级 班级等信息
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: Talent Information Solutions Ltd.
 * </p>
 * 
 * @author zhenzhen
 *
 */
public class GetCfgMsgUtil {
	private static Properties props = new Properties();
	private static Logger logger = Logger.getLogger(GetCfgMsgUtil.class.getName());
	static{
		try {
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("/syscfg.properties"));
		} catch (IOException e) {
			logger.error("读取系统配置文件syscfg.properties出错：");
			logger.error(e.getMessage(), e);
		}
	}
	
	public static String getEesName(){
		return props.getProperty("ees.name");
	}
	
//	public static String getSysVersion() throws IOException {
//		String version= props.getProperty(SysParamConst.VERSION);
//		return version;
//	}
}
