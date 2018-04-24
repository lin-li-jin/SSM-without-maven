package com.talent.forex.bean.model;

public class TranFlowMappingModel {

	private String userNum;
	private String name;
	private String tradeType;
	private String ccy;
 
    private double tradeAmount;//账户交易总金额
    private int tradeNumber;//账户交易总次数
    private double rate;//账户交易盈亏率
    private double account;//账户交易权重
    
    private int tradeAmountRank;//交易金额排名
    private int tradeNumberRank;//交易次数排名
    private int rateRank;//盈亏率排名
    private int accountRank;//账户排名
    
    private int CRank;
    private int WRank;
    private int BRank;
    private double CRate;
    private double WRate;
    private double BRate;
    
	public TranFlowMappingModel() {
		super();
	}

	public TranFlowMappingModel(String userNum, String name, String tradeType,
			String ccy, double tradeAmount, int tradeNumber) {
		super();
		this.userNum = userNum;
		this.name = name;
		this.tradeType = tradeType;
		this.ccy = ccy;
		this.tradeAmount = tradeAmount;
		this.tradeNumber = tradeNumber;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public double getTradeAmount() {
		return tradeAmount;
	}

	public void setTradeAmount(double tradeAmount) {
		this.tradeAmount = tradeAmount;
	}

	public int getTradeNumber() {
		return tradeNumber;
	}

	public void setTradeNumber(int tradeNumber) {
		this.tradeNumber = tradeNumber;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getAccount() {
		return account;
	}

	public void setAccount(double account) {
		this.account = account;
	}

	public int getTradeAmountRank() {
		return tradeAmountRank;
	}

	public void setTradeAmountRank(int tradeAmountRank) {
		this.tradeAmountRank = tradeAmountRank;
	}

	public int getTradeNumberRank() {
		return tradeNumberRank;
	}

	public void setTradeNumberRank(int tradeNumberRank) {
		this.tradeNumberRank = tradeNumberRank;
	}

	public int getRateRank() {
		return rateRank;
	}

	public void setRateRank(int rateRank) {
		this.rateRank = rateRank;
	}

	public int getAccountRank() {
		return accountRank;
	}

	public void setAccountRank(int accountRank) {
		this.accountRank = accountRank;
	}

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public int getCRank() {
		return CRank;
	}

	public void setCRank(int cRank) {
		CRank = cRank;
	}

	public int getWRank() {
		return WRank;
	}

	public void setWRank(int wRank) {
		WRank = wRank;
	}

	public int getBRank() {
		return BRank;
	}

	public void setBRank(int bRank) {
		BRank = bRank;
	}

	public double getCRate() {
		return CRate;
	}

	public void setCRate(double cRate) {
		CRate = cRate;
	}

	public double getWRate() {
		return WRate;
	}

	public void setWRate(double wRate) {
		WRate = wRate;
	}

	public double getBRate() {
		return BRate;
	}

	public void setBRate(double bRate) {
		BRate = bRate;
	}

}
