package com.talent.forex.bean.model;

public class ActiveTradeDetailModel {

	private String tradeNo;// 交易编号
	private String tradeType;// 交易类型
	private String weCcy;// 本方货币
	private String anaCcy;// 对手方货币
	private String aSum;// 交易金额
	private String aPrice;// 交易价格
	private String bSum;// 交易金额
	private String bPrice;// 交易价格
	private String price;//实时价格
	private String monitorPrice; //监控价格
	private String tradeDirection;// 交易方向
	private String activeTime;// 交易生效时间
	private String cancelTime;// 交易过期时间

	public ActiveTradeDetailModel(String tradeNo, String tradeType,
			String weCcy, String anaCcy, String aSum, String aPrice,
			String bSum, String bPrice, String price, String monitorPrice,
			String tradeDirection, String activeTime, String cancelTime) {
		super();
		this.tradeNo = tradeNo;
		this.tradeType = tradeType;
		this.weCcy = weCcy;
		this.anaCcy = anaCcy;
		this.aSum = aSum;
		this.aPrice = aPrice;
		this.bSum = bSum;
		this.bPrice = bPrice;
		this.price = price;
		this.monitorPrice = monitorPrice;
		this.tradeDirection = tradeDirection;
		this.activeTime = activeTime;
		this.cancelTime = cancelTime;
	}

	public String getRealMonitorPrice(){
		if (this.monitorPrice.equals("BID"))
			return "MONITOR BID PRICE";
		else if (this.monitorPrice.equals("ASK"))
			return "MONITOR ASK PRICE";
		else if (this.monitorPrice.equals("B/B"))
			return "MONITOR BID/BID PRICE";
		else if (this.monitorPrice.equals("B/A"))
			return "MONITOR BID/ASK PRICE";
		else if (this.monitorPrice.equals("A/A"))
			return "MONITOR ASK/ASK PRICE";
		else
			return "MONITOR BID/ASK PRICE";
	}
	
	public String getRealActiveTime(){
		if (!this.activeTime.equals("99999999"))
			return this.activeTime.substring(0, 4) + "-" + this.activeTime.substring(4, 6) + "-" + this.activeTime.substring(6, 8);
		return "''";
	}
	
	public String getRealCancelTime(){
		if (!this.cancelTime.equals("99999999"))
			return this.cancelTime.substring(0, 4) + "-" + this.cancelTime.substring(4, 6) + "-" + this.cancelTime.substring(6, 8);
		return "''";
	}
	
	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
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

	public String getaSum() {
		return aSum;
	}

	public void setaSum(String aSum) {
		this.aSum = aSum;
	}

	public String getaPrice() {
		return aPrice;
	}

	public void setaPrice(String aPrice) {
		this.aPrice = aPrice;
	}

	public String getbSum() {
		return bSum;
	}

	public void setbSum(String bSum) {
		this.bSum = bSum;
	}

	public String getbPrice() {
		return bPrice;
	}

	public void setbPrice(String bPrice) {
		this.bPrice = bPrice;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getMonitorPrice() {
		return monitorPrice;
	}

	public void setMonitorPrice(String monitorPrice) {
		this.monitorPrice = monitorPrice;
	}

	public String getTradeDirection() {
		return tradeDirection;
	}

	public void setTradeDirection(String tradeDirection) {
		this.tradeDirection = tradeDirection;
	}

	public String getActiveTime() {
		return activeTime;
	}

	public void setActiveTime(String activeTime) {
		this.activeTime = activeTime;
	}

	public String getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(String cancelTime) {
		this.cancelTime = cancelTime;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	
}
