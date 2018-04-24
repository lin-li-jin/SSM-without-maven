package com.talent.forex.util;


public class JspStrParamUtil {
	
	/**
	 * 把空的参数改为null
	 * @param param
	 * @return
	 */
	public static String getFixedParam( String param ){
		if( null == param ){
			return param;
		}
		return param.length()==0?null:param;
	}
	
	/**
	 * 去掉空格
	 * @param param
	 * @return
	 */
	public static String getFixedStrList( String param ){
		return param.replaceAll(" ", "");
	}
	
	/**
	 * 把空格和null改成%
	 * @param param
	 * @return
	 */
	public static String getFixedQryStr( String param ){
		return param == null || param.length()==0 ? "%" : "%"+param+"%";
	}
	
}
