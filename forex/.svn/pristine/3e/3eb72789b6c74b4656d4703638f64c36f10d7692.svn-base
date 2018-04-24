package com.talent.forex.config;

import java.util.Properties;

public class SysConfig {

	private static Properties config = new Properties();
	private static SysConfig instance = new SysConfig();

	private SysConfig() {
		try {
			config.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("/rebssConfig.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SysConfig getInstance() {
		return instance;
	}

	public Properties getConfig() {
		return config;
	}

}
