package com.talent.forex.bean.domain;

/**
 * AbstractOcoInfo entity provides the base persistence definition of the
 * OcoInfo entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractOcoInfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String tranType;
	private String tranNo;
	private String userNum;
	private String weCcy;
	private String anaCcy;
	private String direction;
	private String TAmount;
	private String TPrice;
	private String SAmount;
	private String SPrice;
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
	public AbstractOcoInfo() {
	}

	/** minimal constructor */
	public AbstractOcoInfo(String tranType, String tranNo, String userNum,
			String weCcy, String anaCcy, String direction, String TAmount,
			String TPrice, String SAmount, String SPrice, String monitorPrice,
			String goodFrom, String goodTill, String statue,
			String createDatetime) {
		this.tranType = tranType;
		this.tranNo = tranNo;
		this.userNum = userNum;
		this.weCcy = weCcy;
		this.anaCcy = anaCcy;
		this.direction = direction;
		this.TAmount = TAmount;
		this.TPrice = TPrice;
		this.SAmount = SAmount;
		this.SPrice = SPrice;
		this.monitorPrice = monitorPrice;
		this.goodFrom = goodFrom;
		this.goodTill = goodTill;
		this.statue = statue;
		this.createDatetime = createDatetime;
	}

	/** full constructor */
	public AbstractOcoInfo(String tranType, String tranNo, String userNum,
			String weCcy, String anaCcy, String direction, String TAmount,
			String TPrice, String SAmount, String SPrice, String date,
			String time, String monitorPrice, String goodFrom, String goodTill,
			String statue, String createDatetime, String amount, String price,
			String LAmount) {
		this.tranType = tranType;
		this.tranNo = tranNo;
		this.userNum = userNum;
		this.weCcy = weCcy;
		this.anaCcy = anaCcy;
		this.direction = direction;
		this.TAmount = TAmount;
		this.TPrice = TPrice;
		this.SAmount = SAmount;
		this.SPrice = SPrice;
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

	public String getTAmount() {
		return this.TAmount;
	}

	public void setTAmount(String TAmount) {
		this.TAmount = TAmount;
	}

	public String getTPrice() {
		return this.TPrice;
	}

	public void setTPrice(String TPrice) {
		this.TPrice = TPrice;
	}

	public String getSAmount() {
		return this.SAmount;
	}

	public void setSAmount(String SAmount) {
		this.SAmount = SAmount;
	}

	public String getSPrice() {
		return this.SPrice;
	}

	public void setSPrice(String SPrice) {
		this.SPrice = SPrice;
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