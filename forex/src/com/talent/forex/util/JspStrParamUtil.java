package com.talent.forex.util;


public class JspStrParamUtil {
	
	/**
	 * �ѿյĲ�����Ϊnull
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
	 * ȥ���ո�
	 * @param param
	 * @return
	 */
	public static String getFixedStrList( String param ){
		return param.replaceAll(" ", "");
	}
	
	/**
	 * �ѿո��null�ĳ�%
	 * @param param
	 * @return
	 */
	public static String getFixedQryStr( String param ){
		return param == null || param.length()==0 ? "%" : "%"+param+"%";
	}
	
}
