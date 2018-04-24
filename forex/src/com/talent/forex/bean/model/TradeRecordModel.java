package com.talent.forex.bean.model;

public class TradeRecordModel {

	private int id;//记录id
	private String tradeNo;//交易编号
	private String tableName;//对应实体名称
	private String tradeType;//交易类型
	private String currencyBuy;//买入货币
	private String currencySell;//卖出货币
	private String tradeSum;//交易金额
	private String tradePrice;//交易价格
	private String tradeDate;//交易（交割）时间
	private String tradeStatus;//交易状态
	private String provider;//对手方

	public TradeRecordModel(){}

	public TradeRecordModel(int id, String tradeNo, String tableName,
			String tradeType, String currencyBuy, String currencySell,
			String tradeSum, String tradePrice, String tradeDate,
			String tradeStatus, String provider) {
		super();
		this.id = id;
		this.tradeNo = tradeNo;
		this.tableName = tableName;
		this.tradeType = tradeType;
		this.currencyBuy = currencyBuy;
		this.currencySell = currencySell;
		this.tradeSum = tradeSum;
		this.tradePrice = tradePrice;
		this.tradeDate = tradeDate;
		this.tradeStatus = tradeStatus;
		this.provider = provider;
	}

	public String getRealStatus(){
		if (this.getTradeStatus().equals("D"))
			return "完成";
		else if (this.getTradeStatus().equals("A"))
			return "有效";
		else if (this.getTradeStatus().equals("I"))
			return "无效";
		else if (this.getTradeStatus().equals("C"))
			return "取消";
		else if (this.getTradeStatus().equals("P"))
			return "处理中";
		else if (this.getTradeStatus().equals("E"))
			return "平仓";
		else
			return "协商中";
	}
	
	public String getPromptDate(){
		String date = this.getTradeDate();
		return date.substring(0,4) + "-" + date.substring(4, 6) + "-" + date.substring(6,8);
	}
	
	public String getTableName() {
		if (this.tradeType.equals("OC"))
			return "OneClickInfo";
		if (this.tradeType.equals("SL"))
			return "StopLossInfo";
		if (this.tradeType.equals("TP"))
			return "TakeProfitInfo";
		if (this.tradeType.equals("OO"))
			return "OcoInfo";
		if (this.tradeType.equals("MB"))
			return "MarketBreakoutInfo";
		if (this.tradeType.equals("ST"))
			return "OtcSpotInfo";
		if (this.tradeType.equals("FD"))
			return "OtcForwardInfo";
		if (this.tradeType.equals("SP"))
			return "OtcSwapInfo";
		if (this.tradeType.equals("FC"))
			return "MarginForwardInfo";
		if (this.tradeType.equals("MO"))
			return "MarginOptionInfo";
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public String getTradeType() {
		if (this.tradeType.equals("OC"))
			return "一口价交易";
		if (this.tradeType.equals("SL"))
			return "Stop Loss交易";
		if (this.tradeType.equals("TP"))
			return "Take Profit交易";
		if (this.tradeType.equals("OO"))
			return "OCO交易";
		if (this.tradeType.equals("MB"))
			return "MarketBreakout交易";
		if (this.tradeType.equals("ST"))
			return "即期询价交易";
		if (this.tradeType.equals("FD"))
			return "远期询价交易";
		if (this.tradeType.equals("SP"))
			return "掉期询价交易";
		if (this.tradeType.equals("FC"))
			return "保证金远期交易";
		if (this.tradeType.equals("MO"))
			return "保证金期权交易";
		if (this.tradeType.equals("MS"))
			return "保证金即期交易";
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getCurrencyBuy() {
		return currencyBuy;
	}
	public void setCurrencyBuy(String currencyBuy) {
		this.currencyBuy = currencyBuy;
	}
	public String getCurrencySell() {
		return currencySell;
	}
	public void setCurrencySell(String currencySell) {
		this.currencySell = currencySell;
	}
	public String getTradeSum() {
		return tradeSum;
	}
	public void setTradeSum(String tradeSum) {
		this.tradeSum = tradeSum;
	}
	public String gettradePrice() {
		return tradePrice;
	}
	public void settradePrice(String tradePrice) {
		this.tradePrice = tradePrice;
	}
	public String getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}
	public String getTradeStatus() {
		return tradeStatus;
	}
	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTradePrice() {
		return tradePrice;
	}

	public void setTradePrice(String tradePrice) {
		this.tradePrice = tradePrice;
	}
}
