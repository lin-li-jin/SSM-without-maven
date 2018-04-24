package com.talent.forex.util;

import java.util.Properties;

public class PropertyConfig {

	private static Properties config = new Properties();
	private static PropertyConfig instance = new PropertyConfig();

	private PropertyConfig(){
		try{
			config.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("/message_zh_CN.properties"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
 
	public static PropertyConfig getInstance(){
		return instance;
	}
	
	public Properties getConfig(){
		return config;
	}
	
	public Object getConfigValue(String configKey){
		return config.get(configKey);
	}
}
