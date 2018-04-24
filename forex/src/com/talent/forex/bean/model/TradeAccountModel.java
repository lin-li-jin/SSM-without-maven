package com.talent.forex.bean.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class TradeAccountModel {

	String tradeAccount;  //交易账户
	String tradeAccountType="M";//C/W/B
	String tradeAccountTypeDescribe;//人民币账户/外币账户/保证金账户
	String accountBalance="0";   //账户现有余额
	String initBalance="0";//账户初始金额
	String profitAndLossBalance;//盈亏金额
	String ProfitAndLossRate;//盈亏率
	
	

	
	public TradeAccountModel()
	{
		
	}
	
	public String getStringProfitAndLossBalance(){
		double value = getTempProfitAndLossBalance();
//		if(tradeAccountType.equals("C")){
//			value=Double.parseDouble(rate_USD_TO_CNY)*value;
//		}
		if(value == 0){
			return "0.0000";
		}
		DecimalFormat df = new DecimalFormat("##.0000");
		return df.format(value);
	}
	
	public double getTempProfitAndLossBalance(){
		return Double.parseDouble(this.accountBalance) - Double.parseDouble(this.initBalance);
	}
	
	public String getExactAccountBalance(){
		double value=Double.parseDouble(getAccountBalance());
//		if(tradeAccountType.equals("C")){
//			value=Double.parseDouble(rate_USD_TO_CNY)*value;
//		}
		if(value == 0){
			return "0.0000";
		}
		DecimalFormat df = new DecimalFormat("##.0000");
		return df.format(value);
	}
	
	public String getExactInitBalance(){
		double value = Double.parseDouble(getInitBalance());
//		if(tradeAccountType.equals("C")){
//			value=Double.parseDouble(rate_USD_TO_CNY)*value;
//		}
		if(value == 0){
			return "0.0000";
		}
		DecimalFormat df = new DecimalFormat("##.0000");
		return df.format(value);
	}
	
	public String getTempProfitAndLossRate(){
		if(initBalance.equals("0")){
			return "100%";
		}
		double value = getTempProfitAndLossBalance() / (Double.parseDouble(initBalance));
		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMaximumFractionDigits(2);
		return nf.format(value);
	}
	
	public String getTradeAccount() {
		return tradeAccount;
	}
	public void setTradeAccount(String tradeAccount) {
		this.tradeAccount = tradeAccount;
	}
	public String getTradeAccountType() {
		return tradeAccountType;
	}
	public void setTradeAccountType(String tradeAccountType) {
		this.tradeAccountType = tradeAccountType;
	}
	public String getTradeAccountTypeDescribe() {
		return tradeAccountTypeDescribe;
	}
	public void setTradeAccountTypeDescribe(String tradeAccountTypeDescribe) {
		this.tradeAccountTypeDescribe = tradeAccountTypeDescribe;
	}
	
	public String getAccountBalance() {
		return accountBalance;
	}



	public void setAccountBalance(String accountBalance) {
		this.accountBalance = accountBalance;
	}



	public String getInitBalance() {
		return initBalance;
	}



	public void setInitBalance(String initBalance) {
		this.initBalance = initBalance;
	}



	public String getProfitAndLossBalance() {
		return profitAndLossBalance;
	}



	public void setProfitAndLossBalance(String profitAndLossBalance) {
		this.profitAndLossBalance = profitAndLossBalance;
	}



	public String getProfitAndLossRate() {
		return ProfitAndLossRate;
	}
	public void setProfitAndLossRate(String profitAndLossRate) {
		ProfitAndLossRate = profitAndLossRate;
	}

	
}
