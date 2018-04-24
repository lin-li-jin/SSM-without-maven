package com.talent.forex.bean.model;

public class LiborModel {
	private String Ccy;
	private String oneDay;
	private String oneWeek;
	private String oneMonth;
	private String twoMonth;
	private String threeMonth;
	private String sixMonth;
	private String twelveMonth;
	
	public LiborModel() {
	}

	public String getCcy() {
		return Ccy;
	}

	public void setCcy(String ccy) {
		Ccy = ccy;
	}

	public String getOneDay() {
		return oneDay;
	}

	public void setOneDay(String oneDay) {
		this.oneDay = oneDay;
	}

	public String getOneWeek() {
		return oneWeek;
	}

	public void setOneWeek(String oneWeek) {
		this.oneWeek = oneWeek;
	}

	public String getOneMonth() {
		return oneMonth;
	}

	public void setOneMonth(String oneMonth) {
		this.oneMonth = oneMonth;
	}

	public String getTwoMonth() {
		return twoMonth;
	}

	public void setTwoMonth(String twoMonth) {
		this.twoMonth = twoMonth;
	}

	public String getThreeMonth() {
		return threeMonth;
	}

	public void setThreeMonth(String threeMonth) {
		this.threeMonth = threeMonth;
	}

	public String getSixMonth() {
		return sixMonth;
	}

	public void setSixMonth(String sixMonth) {
		this.sixMonth = sixMonth;
	}

	public String getTwelveMonth() {
		return twelveMonth;
	}

	public void setTwelveMonth(String twelveMonth) {
		this.twelveMonth = twelveMonth;
	}

}
