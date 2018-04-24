package com.talent.forex.bean.domain;

/**
 * StopLossInfo entity. @author MyEclipse Persistence Tools
 */
public class StopLossInfo extends AbstractStopLossInfo implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public StopLossInfo() {
	}

	/** minimal constructor */
	public StopLossInfo(String tranType, String tranNo, String userNum,
			String weCcy, String anaCcy, String amount, String direction,
			String price, String monitorPrice, String goodFrom,
			String goodTill, String statue, String createDatetime) {
		super(tranType, tranNo, userNum, weCcy, anaCcy, amount, direction,
				price, monitorPrice, goodFrom, goodTill, statue, createDatetime);
	}

	/** full constructor */
	public StopLossInfo(String tranType, String tranNo, String userNum,
			String weCcy, String anaCcy, String amount, String direction,
			String date, String time, String price, String monitorPrice,
			String goodFrom, String goodTill, String statue,
			String createDatetime, String LAmount) {
		super(tranType, tranNo, userNum, weCcy, anaCcy, amount, direction,
				date, time, price, monitorPrice, goodFrom, goodTill, statue,
				createDatetime,LAmount);
	}

}
