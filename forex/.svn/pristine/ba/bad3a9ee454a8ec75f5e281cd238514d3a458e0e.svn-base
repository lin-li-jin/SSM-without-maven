package com.talent.auth.util;

import java.util.Properties;

public class UserConfig {

	private static Properties config = new Properties();
	private static UserConfig instance = new UserConfig();

	private UserConfig() {
		try {
			config.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("/com/talent/auth/userConfig.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static UserConfig getInstance() {
		return instance;
	}

	public Properties getConfig() {
		return config;
	}

	public Object getConfigValue(String configKey) {
		return config.get(configKey);
	}
}
