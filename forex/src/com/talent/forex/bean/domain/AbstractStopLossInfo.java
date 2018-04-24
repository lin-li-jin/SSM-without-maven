package com.talent.forex.bean.domain;

/**
 * AbstractStopLossInfo entity provides the base persistence definition of the
 * StopLossInfo entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractStopLossInfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String tranType;
	private String tranNo;
	private String userNum;
	private String weCcy;
	private String anaCcy;
	private String amount;
	private String direction;
	private String date;
	private String time;
	private String price;
	private String monitorPrice;
	private String goodFrom;
	private String goodTill;
	private String statue;
	private String createDatetime;
	private String LAmount;

	// Constructors

	/** default constructor */
	public AbstractStopLossInfo() {
	}

	/** minimal constructor */
	public AbstractStopLossInfo(String tranType, String tranNo, String userNum,
			String weCcy, String anaCcy, String amount, String direction,
			String price, String monitorPrice, String goodFrom,
			String goodTill, String statue, String createDatetime) {
		this.tranType = tranType;
		this.tranNo = tranNo;
		this.userNum = userNum;
		this.weCcy = weCcy;
		this.anaCcy = anaCcy;
		this.amount = amount;
		this.direction = direction;
		this.price = price;
		this.monitorPrice = monitorPrice;
		this.goodFrom = goodFrom;
		this.goodTill = goodTill;
		this.statue = statue;
		this.createDatetime = createDatetime;
	}

	/** full constructor */
	public AbstractStopLossInfo(String tranType, String tranNo, String userNum,
			String weCcy, String anaCcy, String amount, String direction,
			String date, String time, String price, String monitorPrice,
			String goodFrom, String goodTill, String statue,
			String createDatetime, String LAmount) {
		this.tranType = tranType;
		this.tranNo = tranNo;
		this.userNum = userNum;
		this.weCcy = weCcy;
		this.anaCcy = anaCcy;
		this.amount = amount;
		this.direction = direction;
		this.date = date;
		this.time = time;
		this.price = price;
		this.monitorPrice = monitorPrice;
		this.goodFrom = goodFrom;
		this.goodTill = goodTill;
		this.statue = statue;
		this.createDatetime = createDatetime;
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

	public String getAmount() {
		return this.amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getDirection() {
		return this.direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
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

	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
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

	public String getLAmount() {
		return this.LAmount;
	}

	public void setLAmount(String LAmount) {
		this.LAmount = LAmount;
	}

}