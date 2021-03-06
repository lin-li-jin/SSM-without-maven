package com.talent.forex.common;

import java.util.Map;

import com.opensymphony.webwork.util.WebWorkTypeConverter;

/**
 * Boolean类型转换
 * 
 * 处理默认类型转换只能为true或false，不能为null的问题。
 * 
 * 
 */
public class BooleanConverter extends WebWorkTypeConverter {

	@Override
	@SuppressWarnings("unchecked")
	public Object convertFromString(Map context, String[] values, Class toClass) {
		String value = values[0];
		if (value == null) {
			return null;
		}
		if ("false".equalsIgnoreCase(value) || "0".equals(value)) {
			return false;
		} else if ("true".equalsIgnoreCase(value) || "1".equals(value)) {
			return true;
		} else {
			return null;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public String convertToString(Map context, Object o) {
		Boolean value = (Boolean) o;
		return String.valueOf(value);
	}

}
