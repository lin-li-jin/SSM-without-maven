package com.talent.forex.bean.domain;

/**
 * OneClickInfo entity. @author MyEclipse Persistence Tools
 */
public class OneClickInfo extends AbstractOneClickInfo implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public OneClickInfo() {
	}

	/** minimal constructor */
	public OneClickInfo(String tranType, String tranNo, String userNum,
			String weCcy, String anaCcy, String amount, String direction,
			String date, String time, String price, String promptDate,
			String status, String createDatetime, String LAmount) {
		super(tranType, tranNo, userNum, weCcy, anaCcy, amount, direction,
				date, time, price, promptDate, status, createDatetime, LAmount);
	}

	/** full constructor */
	public OneClickInfo(String tranType, String tranNo, String userNum,
			String weCcy, String anaCcy, String amount, String direction,
			String date, String time, String anaInstitution,
			String weInstitution, String price, String promptDate,
			String status, String createDatetime, String LAmount) {
		super(tranType, tranNo, userNum, weCcy, anaCcy, amount, direction,
				date, time, anaInstitution, weInstitution, price, promptDate,
				status, createDatetime, LAmount);
	}

	public String getOwnName() {
		return "一口价交易";
	}

	public String getRealPromptDate() {
		String date = this.getPromptDate();
		return date.substring(0, 4) + "-" + date.substring(4, 6) + "-"
				+ date.substring(6, 8);
	}
}
