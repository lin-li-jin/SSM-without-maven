package com.talent.forex.util;

import java.text.DecimalFormat;

public class FormatParamUtil {

	/**
	 * 将金额格式化为：amount 或 amount.xxxx 
	 * 将价格格式化为：price.xxxx
	 */
	public static String getAmountAndPriceFmt(String s) {
		String param = "";
		int index = s.indexOf(".");
		if (index < 0) {
			param = s+".0000";
		} else {
			int i = s.length() - index - 1;
			if (i >= 4)
				param = s.substring(0, index + 5);
			else {
				param = s;
				for (int j = i; j < 4; j++) {
					param += "0";
				}
			}
		}
		return param;
	}

	/**
	 * 格式化double为4位小数
	 * @param d
	 * @return
	 */
	public static String formatDouble(double d){
		DecimalFormat formatter = new DecimalFormat("#.####");
		return formatter.format(d);
	}
	
	
	/**
	 * !!!这里有个bug  如果传进来的double的整数位超过15位的话 格式无法转换  15位已经是万亿的数值了
	 *传一个double的值,如果double有用到科学计数法，先格式化成不用科学计数法的格式 
	 *再调用上面的方法设置小数点
	 * @param d
	 * @return
	 */
	public static String getAmountAndPriceFmtByDouble(double d){
		java.text.NumberFormat nf = java.text.NumberFormat.getInstance();   
		nf.setGroupingUsed(false);  
		return getAmountAndPriceFmt(nf.format(d));
		
	}
	
	public static String getShowAmountFmt(String s){
		String[] param = s.split("\\.");
		System.out.println(param.length);
		if (param[1].equals("0000"))
			return param[0];
		return s;
	}
}
