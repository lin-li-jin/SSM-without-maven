package com.talent.forex.bean.domain;

/**
 * MarketBreakoutInfo entity. @author MyEclipse Persistence Tools
 */
public class MarketBreakoutInfo extends AbstractMarketBreakoutInfo implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public MarketBreakoutInfo() {
	}

	/** minimal constructor */
	public MarketBreakoutInfo(String tranType, String tranNo, String userNum,
			String weCcy, String anaCcy, String direction, String s1Amount,
			String s1Price, String s2Amount, String s2Price,
			String monitorPrice, String goodFrom, String goodTill,
			String statue, String createDatetime) {
		super(tranType, tranNo, userNum, weCcy, anaCcy, direction, s1Amount,
				s1Price, s2Amount, s2Price, monitorPrice, goodFrom, goodTill,
				statue, createDatetime);
	}

	/** full constructor */
	public MarketBreakoutInfo(String tranType, String tranNo, String userNum,
			String weCcy, String anaCcy, String direction, String s1Amount,
			String s1Price, String s2Amount, String s2Price, String date,
			String time, String monitorPrice, String goodFrom, String goodTill,
			String statue, String createDatetime, String amount, String price, String LAmount) {
		super(tranType, tranNo, userNum, weCcy, anaCcy, direction, s1Amount,
				s1Price, s2Amount, s2Price, date, time, monitorPrice, goodFrom,
				goodTill, statue, createDatetime, amount, price, LAmount);
	}

}
