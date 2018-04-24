package com.talent.forex.lang;

import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

/*
 * Amendment No.: forex130011
 * Modify By    : Alpha Liang
 * Description  : 多国语言支持模块笔,从文件中读取相应字段的值
 * Modify Date  : 2013-6-28
 * 
 */

@SuppressWarnings("unchecked")
public class Resource {

	private static final String HEAD_FILE = "label"; // 属性文件名的前缀

	private static final String LAST_FILE = ".properties"; // 属性文件名的后缀

	private static final String FILE_PATH = "/lang/"; // 定义属性文件存放的目录
	
	public Properties prop_en = null;
	
	public Properties prop_cn = null;

	public Resource() {

	}

	/* 以下是根据传入的属性文件中的"键",而得到与区域与语言设置相对应的"值" */

	public String srcStr(String disStr, String localStr) {

		String ret = "";

		try {
			Locale locale = null;
			
			if (localStr == null || localStr.equals("")) {
				locale = Locale.getDefault(); // 获取系统的区域与语言默认设置
			} else {
				if (localStr.equals("en")) {
					locale = Locale.US;
				} else if (localStr.equals("cn")) {
					locale = Locale.CHINA;
				} else {
					locale = Locale.US;	//默认是英文
				}
			}
			
			if (locale.toString().equals(Locale.US.toString())) {
				
				if (prop_en == null) {
					prop_en = loadProperties(locale);
				}
				ret = prop_en.getProperty(disStr);
			} else if (locale.toString().equals(Locale.CHINA.toString())) {
				if (prop_cn == null) {
					prop_cn = loadProperties(locale);
				}
				ret = prop_cn.getProperty(disStr);
			}

			return ret;

		} catch (Exception e) {

			e.printStackTrace();

			return disStr;

		}

	}
	
	private Properties loadProperties(Locale locale) throws IOException {
		
		String baseName = new StringBuffer()

		.append(HEAD_FILE).append("_").append(locale.toString())

		.append(LAST_FILE).toString(); // 根据local属性,前缀以及后缀生成文件名

		String fileName = new StringBuffer(FILE_PATH).append(baseName)

		.toString(); // 获取文件的完整路径
		
		Properties prop = new Properties();
		
		prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName));
		
		return prop;
	}

}
