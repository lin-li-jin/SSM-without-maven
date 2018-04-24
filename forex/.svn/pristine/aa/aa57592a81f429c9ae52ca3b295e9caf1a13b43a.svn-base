package com.talent.forex.bean.model;

import com.talent.forex.util.FormatParamUtil;
import com.talent.forex.util.RateUtil;

public class DoneTradeDetailModel {

	private String tradeNo;// 交易编号
	private String tradeType;// 交易类型
	private String weCcy;// 本方货币
	private String anaCcy;// 对手方货币
	private String amount;// 交易金额
	private String tradePrice;// 交易价格
	private String tradeDate;// 交易日期
	private String tradeTime;// 交易时间
	private String tradeDirection;// 交易方向
	private String weInstitution;// 本方机构
	private String anaInstitution;// 对手方机构
	private String createDate;// 交易提交日期
	private String status;// 交易状态
	private String currentPrice;//实时价格
	private String tradeSum;// 交易兑换后金额
	private String proAndLoss;//盈亏额
	private String proAndLossRate;//盈亏率

	public DoneTradeDetailModel(String tradeNo, String tradeType, String weCcy,
			String anaCcy, String amount, String tradePrice,
			String tradeDate, String tradeTime, String tradeDirection,
			String weInstitution, String anaInstitution, String createDate,
			String status, String tradeSum) {
		super();
		this.tradeNo = tradeNo;
		this.tradeType = tradeType;
		this.weCcy = weCcy;
		this.anaCcy = anaCcy;
		this.amount = amount;
		this.tradePrice = tradePrice;
		this.tradeDate = tradeDate;
		this.tradeTime = tradeTime;
		this.tradeDirection = tradeDirection;
		this.weInstitution = weInstitution;
		this.anaInstitution = anaInstitution;
		this.createDate = createDate;
		this.status = status;
		this.tradeSum = tradeSum;
	}

	public String getRealTime() {
		return this.tradeTime.substring(0, 2) + ":"
				+ this.tradeTime.substring(2, 4);
	}

	public String getRealDirection() {
		if (this.tradeDirection.equals("0"))
			return "卖";
		else
			return "买";
	}

	public String getRealTradeDate() {
		return this.tradeDate.substring(0, 4) + "-"
				+ this.tradeDate.substring(4, 6) + "-"
				+ this.tradeDate.substring(6, 8);
	}

	public String getRealCreateDate() {
		return this.createDate.substring(0, 4) + "-"
				+ this.createDate.substring(4, 6) + "-"
				+ this.createDate.substring(6, 8) + " "
				+ this.createDate.substring(8, 10) + ":"
				+ this.createDate.substring(10, 12);
	}

	public String getRealStatus() {
		if(tradeType.equals("一口价交易")){
			return "完成";
		}
		if (this.status.equals("D"))
			return "完成";
		else if (this.status.equals("I"))
			return "无效";
		else if (this.status.equals("C"))
			return "取消";
		else if (this.status.equals("R"))
			return "协商中";
		else if (this.status.equals("P"))
			return "处理中";
		else if (this.status.equals("A"))
			return "有效";
		return "";
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
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

	public String getTradeSum() {
		return tradeSum;
	}

	public void setTradeSum(String tradeSum) {
		this.tradeSum = tradeSum;
	}

	public String getTradePrice() {
		return tradePrice;
	}

	public void setTradePrice(String tradePrice) {
		this.tradePrice = tradePrice;
	}

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getTradeDirection() {
		return tradeDirection;
	}

	public void setTradeDirection(String tradeDirection) {
		this.tradeDirection = tradeDirection;
	}

	public String getWeInstitution() {
		return weInstitution;
	}

	public void setWeInstitution(String weInstitution) {
		this.weInstitution = weInstitution;
	}

	public String getAnaInstitution() {
		return anaInstitution;
	}

	public void setAnaInstitution(String anaInstitution) {
		this.anaInstitution = anaInstitution;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCurrentPrice() {
		currentPrice = String.valueOf(getCurrentRate());
		return currentPrice;
	}

	public void setCurrentPrice(String currentPrice) {
		this.currentPrice = currentPrice;
	}

	public String getProAndLoss() {
		double pal = 0d;
		double rate = getCurrentRate();
		if(tradeDirection.equals("0")){
			pal = (Double.parseDouble(tradePrice) - rate) * Double.parseDouble(amount);
		}
		else if(tradeDirection.equals("1")){
			pal = (rate - Double.parseDouble(tradePrice)) * Double.parseDouble(amount);
		}
		proAndLoss = pal + "";
		return FormatParamUtil.getAmountAndPriceFmt(proAndLoss);
	}

	public void setProAndLoss(String proAndLoss) {
		this.proAndLoss = proAndLoss;
	}

	public String getProAndLossRate() {
		double palr = 0d;
		double rate = getCurrentRate();
		double price = Double.parseDouble(tradePrice);
		if(tradeDirection.equals("0")){
			palr = (price - rate) / price * 100;
		}
		else if(tradeDirection.equals("1")){
			palr = (rate - price) / price * 100;
		}
		proAndLossRate = palr + "";
		return FormatParamUtil.getAmountAndPriceFmt(proAndLossRate);
	}

	public void setProAndLossRate(String proAndLossRate) {
		this.proAndLossRate = proAndLossRate;
	}
	
	public double getCurrentRate(){
		String type = "";
		double rate = 0d;
		String BA = "B";
		String ccy = "";
		if(weCcy.indexOf("CNY") != -1){
			type = "CNY";
			ccy = anaCcy;
		}
		else if(anaCcy.indexOf("CNY") != -1){
			type = "CNY";
			ccy = weCcy;
		}
		else{
			if(weCcy.indexOf("USD") != -1){
				ccy = anaCcy;
			}
			else if(anaCcy.indexOf("USD") != -1){
				ccy = weCcy;
			}
		}
		if(tradeType.equals("即期询价交易")){
			BA = "A";
		}
		if(type.equals("CNY")){
			rate = RateUtil.getCNYRateByCcy(BA, ccy, tradeDirection);
		}
		else{
			rate = RateUtil.getForRateByCcy(ccy, tradeDirection);
		}
		return rate;
	}
	
}
