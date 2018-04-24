package com.talent.forex.util;
/*
 * Create By    : lzc
 * Description  : 交易计算
 * Modify Date  : 2014-08-08
 */
public class CalculateUtil {

	/**
	 * 计算一个货币对的交易金额，根据汇率价格与用户提供的交易金额获得兑换后金额
	 */
	public static String getAmountAfterTrade(String amount, String price){
		amount = FormatParamUtil.getAmountAndPriceFmt(amount);
		price = FormatParamUtil.getAmountAndPriceFmt(price);
		double sum = Double.parseDouble(amount);
		double rate = Double.parseDouble(price);
		return FormatParamUtil.getAmountAndPriceFmtByDouble(sum * rate);
	}
}
