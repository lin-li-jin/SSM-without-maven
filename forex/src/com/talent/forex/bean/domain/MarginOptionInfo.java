package com.talent.forex.bean.domain;

/**
 * MarginOptionInfo entity. @author MyEclipse Persistence Tools
 */
public class MarginOptionInfo extends AbstractMarginOptionInfo implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public MarginOptionInfo() {
	}

	/** minimal constructor */
	public MarginOptionInfo(String tranType, String tranNo, String userNum,
			String accNo, String weCcy, String anaCcy, String optionType,
			String accAmount, String maturity, String dealAmt, String price,
			String premium, String statue, String createDatetime,
			String direction) {
		super(tranType, tranNo, userNum, accNo, weCcy, anaCcy, optionType,
				accAmount, maturity, dealAmt, price, premium, statue,
				createDatetime, direction);
	}

	/** full constructor */
	public MarginOptionInfo(String tranType, String tranNo, String userNum,
			String accNo, String valueDate, String weCcy, String anaCcy,
			String optionType, String accAmount, String accBanlance,
			String maturity, String dealAmt, String price, String premium,
			String statue, String createDatetime, String LAmount,
			String direction) {
		super(tranType, tranNo, userNum, accNo, valueDate, weCcy, anaCcy,
				optionType, accAmount, accBanlance, maturity, dealAmt, price,
				premium, statue, createDatetime, LAmount, direction);
	}

}
