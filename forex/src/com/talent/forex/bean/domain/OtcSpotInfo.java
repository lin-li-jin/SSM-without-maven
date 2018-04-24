package com.talent.forex.bean.domain;

/**
 * OtcSpotInfo entity. @author MyEclipse Persistence Tools
 */
public class OtcSpotInfo extends AbstractOtcSpotInfo implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public OtcSpotInfo() {
	}

	/** minimal constructor */
	public OtcSpotInfo(String tranType, String tranNo, String userNum,
			String provider, String isInit, String direction, String weCcy,
			String anaCcy, String amount, String price, String statue,
			String createDatetime) {
		super(tranType, tranNo, userNum, provider, isInit, direction, weCcy,
				anaCcy, amount, price, statue, createDatetime);
	}

	/** full constructor */
	public OtcSpotInfo(String tranType, String tranNo, String userNum,
			String provider, String isInit, String direction, String weCcy,
			String anaCcy, String amount, String price, String date,
			String time, String statue, String createDatetime, String LAmount,
			String times) {
		super(tranType, tranNo, userNum, provider, isInit, direction, weCcy,
				anaCcy, amount, price, date, time, statue, createDatetime,
				LAmount, times);
	}

}
