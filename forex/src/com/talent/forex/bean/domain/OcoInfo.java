package com.talent.forex.bean.domain;

/**
 * OcoInfo entity. @author MyEclipse Persistence Tools
 */
public class OcoInfo extends AbstractOcoInfo implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public OcoInfo() {
	}

	/** minimal constructor */
	public OcoInfo(String tranType, String tranNo, String userNum,
			String weCcy, String anaCcy, String direction, String TAmount,
			String TPrice, String SAmount, String SPrice, String monitorPrice,
			String goodFrom, String goodTill, String statue,
			String createDatetime) {
		super(tranType, tranNo, userNum, weCcy, anaCcy, direction, TAmount,
				TPrice, SAmount, SPrice, monitorPrice, goodFrom, goodTill,
				statue, createDatetime);
	}

	/** full constructor */
	public OcoInfo(String tranType, String tranNo, String userNum,
			String weCcy, String anaCcy, String direction, String TAmount,
			String TPrice, String SAmount, String SPrice, String date,
			String time, String monitorPrice, String goodFrom, String goodTill,
			String statue, String createDatetime, String amount, String price, String LAmount) {
		super(tranType, tranNo, userNum, weCcy, anaCcy, direction, TAmount,
				TPrice, SAmount, SPrice, date, time, monitorPrice, goodFrom,
				goodTill, statue, createDatetime, amount, price, LAmount);
	}

}
