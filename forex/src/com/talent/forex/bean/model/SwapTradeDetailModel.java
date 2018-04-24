package com.talent.forex.bean.model;

import com.talent.forex.util.FormatParamUtil;
import com.talent.forex.util.RateUtil;

public class SwapTradeDetailModel {

	private String tradeType;// 交易类型
	private String tradeNo;// 交易编号
	private String weInstitution;// 本方机构
	private String anaInstitution;// 对手方机构
	private String weCcy;// 本方货币
	private String anaCcy;// 对手方货币
	private String startDate;//开始交易的日期
	private String endDate;//到期交易的日期
	private String tradeDirection;// 交易方向
	private String price;//交易的价格
	private String point;//交易的基本点
	private String cAmount;//近端交易的金额
	private String fAmount;//远端交易的金额
	private String cBasis;//近端交易的掉期点
	private String fBasis;//远端交易的掉期点
	private String fixedType;//固定利率的类型
	private String receiveRate;//接受利率
	private String payRate;//支付利率
	private String libor;//利率的类型
	private String frequency;//近端付息类型
	private String date;//同意交易的日期
	private String time;//同意交易的时间
	private String status;// 交易状态
	private String createDate;// 交易提交日期
	private String currentPrice;//实时价格
	private String proAndLoss;//盈亏额
	private String proAndLossRate;//盈亏率

	public SwapTradeDetailModel(String tradeType, String tradeNo,
			String weInstitution, String anaInstitution, String weCcy,
			String anaCcy, String startDate, String endDate,
			String tradeDirection, String price, String point, String cAmount,
			String fAmount, String cBasis, String fBasis, String fixedType,
			String receiveRate, String payRate, String libor, String frequency,
			String date, String time, String status, String createDate) {
		super();
		this.tradeType = tradeType;
		this.tradeNo = tradeNo;
		this.weInstitution = weInstitution;
		this.anaInstitution = anaInstitution;
		this.weCcy = weCcy;
		this.anaCcy = anaCcy;
		this.startDate = startDate;
		this.endDate = endDate;
		this.tradeDirection = tradeDirection;
		this.price = price;
		this.point = point;
		this.cAmount = cAmount;
		this.fAmount = fAmount;
		this.cBasis = cBasis;
		this.fBasis = fBasis;
		this.fixedType = fixedType;
		this.receiveRate = receiveRate;
		this.payRate = payRate;
		this.libor = libor;
		this.frequency = frequency;
		this.date = date;
		this.time = time;
		this.status = status;
		this.createDate = createDate;
	}
	public String getRealDirection() {
		if (this.tradeDirection.equals("0"))
			return "卖";
		else
			return "买";
	}
	public String getRealStatus() {
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
	
	public String getRealCreateDate() {
		return this.createDate.substring(0, 4) + "-"
				+ this.createDate.substring(4, 6) + "-"
				+ this.createDate.substring(6, 8) + " "
				+ this.createDate.substring(8, 10) + ":"
				+ this.createDate.substring(10, 12);
	}

	public String getStartedDate() {
		return this.startDate.substring(0, 4) + "-"
				+ this.startDate.substring(4, 6) + "-"
				+ this.startDate.substring(6, 8);
	}
	
	public String getMaturityDate() {
		return this.endDate.substring(0, 4) + "-"
				+ this.endDate.substring(4, 6) + "-"
				+ this.endDate.substring(6, 8);
	}
	
	public String getFinalPrice(){
		double finalPrice = Double.parseDouble(price) + Double.parseDouble(point) / 10000 + 0.000000000000000001;
		String price = finalPrice + "";
		int index = price.indexOf(".");
		return price.substring(0, index + 5);
	}
	
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
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
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getTradeDirection() {
		return tradeDirection;
	}
	public void setTradeDirection(String tradeDirection) {
		this.tradeDirection = tradeDirection;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public String getcAmount() {
		return cAmount;
	}
	public void setcAmount(String cAmount) {
		this.cAmount = cAmount;
	}
	public String getfAmount() {
		return fAmount;
	}
	public void setfAmount(String fAmount) {
		this.fAmount = fAmount;
	}
	public String getcBasis() {
		return cBasis;
	}
	public void setcBasis(String cBasis) {
		this.cBasis = cBasis;
	}
	public String getfBasis() {
		return fBasis;
	}
	public void setfBasis(String fBasis) {
		this.fBasis = fBasis;
	}
	public String getFixedType() {
		return fixedType;
	}
	public void setFixedType(String fixedType) {
		this.fixedType = fixedType;
	}
	public String getReceiveRate() {
		return receiveRate;
	}
	public void setReceiveRate(String receiveRate) {
		this.receiveRate = receiveRate;
	}
	public String getPayRate() {
		return payRate;
	}
	public void setPayRate(String payRate) {
		this.payRate = payRate;
	}
	public String getLibor() {
		return libor;
	}
	public void setLibor(String libor) {
		this.libor = libor;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
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
		if(tradeType.equals("掉期询价交易")){
			BA = "A";
		}
		if(type.equals("CNY")){
			rate = RateUtil.getCNYRateByCcy(BA, ccy, tradeDirection);
		}
		else{
			rate = RateUtil.getForRateByCcy(ccy, tradeDirection);
		}
		if(tradeDirection.equals("0")){
			pal = (Double.parseDouble(getFinalPrice()) - rate) * Double.parseDouble(cAmount);
		}
		else{
			pal = (rate - Double.parseDouble(getFinalPrice())) * Double.parseDouble(cAmount);
		}
		proAndLoss = pal + "";
		return FormatParamUtil.getAmountAndPriceFmt(proAndLoss);
	}
	public void setProAndLoss(String proAndLoss) {
		this.proAndLoss = proAndLoss;
	}
	public String getProAndLossRate() {
		double palr = 0d;
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
		if(tradeType.equals("掉期询价交易")){
			BA = "A";
		}
		if(type.equals("CNY")){
			rate = RateUtil.getCNYRateByCcy(BA, ccy, tradeDirection);
		}
		else{
			rate = RateUtil.getForRateByCcy(ccy, tradeDirection);
		}
		double price = Double.parseDouble(getFinalPrice());
		if(tradeDirection.equals("0")){
			palr = (price - rate) / price * 100;
		}
		else{
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
		if(tradeType.equals("掉期询价交易")){
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
