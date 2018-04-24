package com.talent.forex.bean.model;

/*
 * 这个Model是用来从jsp页面接收参数用的
 * 在增加一条保证金远期交易的时候把页面传回来的参数封装成一个model
 * create by atggdsaiDong
 */
public class MarginForwardInfoModel {

	private String valueDate;
	private String accountAmount;
	private String factor;
	private String dealAmount;
	private String price;
	private String account;
	private String weCcy;
	private String anaCcy;
	private String direction;//判断买卖方向的   后来改了数据表加的一个字段

	public MarginForwardInfoModel(){}
	public String getValueDate() {
		return valueDate;
	}
	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}
	public String getAccountAmount() {
		return accountAmount;
	}
	public void setAccountAmount(String accountAmount) {
		this.accountAmount = accountAmount;
	}
	public String getFactor() {
		return factor;
	}
	public void setFactor(String factor) {
		this.factor = factor;
	}
	public String getDealAmount() {
		return dealAmount;
	}
	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getWeCcy() {
		return weCcy;
	}
	public void setWeCcy(String weCcy) {
		this.weCcy = weCcy;
	}
	public String getAnaCcy() {
		return anaCcy;
	}
	public void setAnaCcy(String anaCcy) {
		this.anaCcy = anaCcy;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
}
