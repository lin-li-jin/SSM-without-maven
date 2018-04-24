package com.talent.forex.bean.domain;

/**
 * AbstractMarketBreakoutInfo entity provides the base persistence definition of
 * the MarketBreakoutInfo entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractMarketBreakoutInfo implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private String tranType;
	private String tranNo;
	private String userNum;
	private String weCcy;
	private String anaCcy;
	private String direction;
	private String s1Amount;
	private String s1Price;
	private String s2Amount;
	private String s2Price;
	private String date;
	private String time;
	private String monitorPrice;
	private String goodFrom;
	private String goodTill;
	private String statue;
	private String createDatetime;
	private String amount;
	private String price;
	private String LAmount;

	// Constructors

	/** default constructor */
	public AbstractMarketBreakoutInfo() {
	}

	/** minimal constructor */
	public AbstractMarketBreakoutInfo(String tranType, String tranNo,
			String userNum, String weCcy, String anaCcy, String direction,
			String s1Amount, String s1Price, String s2Amount, String s2Price,
			String monitorPrice, String goodFrom, String goodTill,
			String statue, String createDatetime) {
		this.tranType = tranType;
		this.tranNo = tranNo;
		this.userNum = userNum;
		this.weCcy = weCcy;
		this.anaCcy = anaCcy;
		this.direction = direction;
		this.s1Amount = s1Amount;
		this.s1Price = s1Price;
		this.s2Amount = s2Amount;
		this.s2Price = s2Price;
		this.monitorPrice = monitorPrice;
		this.goodFrom = goodFrom;
		this.goodTill = goodTill;
		this.statue = statue;
		this.createDatetime = createDatetime;
	}

	/** full constructor */
	public AbstractMarketBreakoutInfo(String tranType, String tranNo,
			String userNum, String weCcy, String anaCcy, String direction,
			String s1Amount, String s1Price, String s2Amount, String s2Price,
			String date, String time, String monitorPrice, String goodFrom,
			String goodTill, String statue, String createDatetime,
			String amount, String price, String LAmount) {
		this.tranType = tranType;
		this.tranNo = tranNo;
		this.userNum = userNum;
		this.weCcy = weCcy;
		this.anaCcy = anaCcy;
		this.direction = direction;
		this.s1Amount = s1Amount;
		this.s1Price = s1Price;
		this.s2Amount = s2Amount;
		this.s2Price = s2Price;
		this.date = date;
		this.time = time;
		this.monitorPrice = monitorPrice;
		this.goodFrom = goodFrom;
		this.goodTill = goodTill;
		this.statue = statue;
		this.createDatetime = createDatetime;
		this.amount = amount;
		this.price = price;
		this.LAmount = LAmount;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTranType() {
		return this.tranType;
	}

	public void setTranType(String tranType) {
		this.tranType = tranType;
	}

	public String getTranNo() {
		return this.tranNo;
	}

	public void setTranNo(String tranNo) {
		this.tranNo = tranNo;
	}

	public String getUserNum() {
		return this.userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public String getWeCcy() {
		return this.weCcy;
	}

	public void setWeCcy(String weCcy) {
		this.weCcy = weCcy;
	}

	public String getAnaCcy() {
		return this.anaCcy;
	}

	public void setAnaCcy(String anaCcy) {
		this.anaCcy = anaCcy;
	}

	public String getDirection() {
		return this.direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getS1Amount() {
		return this.s1Amount;
	}

	public void setS1Amount(String s1Amount) {
		this.s1Amount = s1Amount;
	}

	public String getS1Price() {
		return this.s1Price;
	}

	public void setS1Price(String s1Price) {
		this.s1Price = s1Price;
	}

	public String getS2Amount() {
		return this.s2Amount;
	}

	public void setS2Amount(String s2Amount) {
		this.s2Amount = s2Amount;
	}

	public String getS2Price() {
		return this.s2Price;
	}

	public void setS2Price(String s2Price) {
		this.s2Price = s2Price;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getMonitorPrice() {
		return this.monitorPrice;
	}

	public void setMonitorPrice(String monitorPrice) {
		this.monitorPrice = monitorPrice;
	}

	public String getGoodFrom() {
		return this.goodFrom;
	}

	public void setGoodFrom(String goodFrom) {
		this.goodFrom = goodFrom;
	}

	public String getGoodTill() {
		return this.goodTill;
	}

	public void setGoodTill(String goodTill) {
		this.goodTill = goodTill;
	}

	public String getStatue() {
		return this.statue;
	}

	public void setStatue(String statue) {
		this.statue = statue;
	}

	public String getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(String createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getAmount() {
		return this.amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getLAmount() {
		return this.LAmount;
	}

	public void setLAmount(String LAmount) {
		this.LAmount = LAmount;
	}

}