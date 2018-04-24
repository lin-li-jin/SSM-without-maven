package com.talent.forex.bean.domain;

/**
 * AbstractOtcForwardInfo entity provides the base persistence definition of the
 * OtcForwardInfo entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractOtcForwardInfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String tranType;
	private String tranNo;
	private String userNum;
	private String provider;
	private String direction;
	private String valueDate;
	private String weCcy;
	private String anaCcy;
	private String point;
	private String amount;
	private String price;
	private String date;
	private String time;
	private String statue;
	private String createDatetime;
	private String LAmount;
	private String isInit;
	private String times;

	// Constructors

	/** default constructor */
	public AbstractOtcForwardInfo() {
	}

	/** minimal constructor */
	public AbstractOtcForwardInfo(String tranType, String tranNo,
			String userNum, String provider, String direction,
			String valueDate, String weCcy, String anaCcy, String point,
			String amount, String price, String statue, String createDatetime,
			String isInit) {
		this.tranType = tranType;
		this.tranNo = tranNo;
		this.userNum = userNum;
		this.provider = provider;
		this.direction = direction;
		this.valueDate = valueDate;
		this.weCcy = weCcy;
		this.anaCcy = anaCcy;
		this.point = point;
		this.amount = amount;
		this.price = price;
		this.statue = statue;
		this.createDatetime = createDatetime;
		this.isInit = isInit;
	}

	/** full constructor */
	public AbstractOtcForwardInfo(String tranType, String tranNo,
			String userNum, String provider, String direction,
			String valueDate, String weCcy, String anaCcy, String point,
			String amount, String price, String date, String time,
			String statue, String createDatetime, String LAmount,
			String isInit, String times) {
		this.tranType = tranType;
		this.tranNo = tranNo;
		this.userNum = userNum;
		this.provider = provider;
		this.direction = direction;
		this.valueDate = valueDate;
		this.weCcy = weCcy;
		this.anaCcy = anaCcy;
		this.point = point;
		this.amount = amount;
		this.price = price;
		this.date = date;
		this.time = time;
		this.statue = statue;
		this.createDatetime = createDatetime;
		this.LAmount = LAmount;
		this.isInit = isInit;
		this.times = times;
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

	public String getProvider() {
		return this.provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getDirection() {
		return this.direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getValueDate() {
		return this.valueDate;
	}

	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
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

	public String getPoint() {
		return this.point;
	}

	public void setPoint(String point) {
		this.point = point;
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

	public String getIsInit() {
		return this.isInit;
	}

	public void setIsInit(String isInit) {
		this.isInit = isInit;
	}

	public String getTimes() {
		return this.times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

}