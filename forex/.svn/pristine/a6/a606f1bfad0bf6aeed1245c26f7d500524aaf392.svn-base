package com.talent.forex.bean.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;


public class AccountModel {
	String account; //交易员号
	TradeAccountModel tradeAccountC=new TradeAccountModel();//C交易账户
	TradeAccountModel tradeAccountW=new TradeAccountModel();//W交易账户
	TradeAccountModel tradeAccountB=new TradeAccountModel();//B交易账户
	//String SumProfitAndLoss;//交易员总盈亏情况
	//标识个账户是否显示
	String accountC;
	String accountB;
	String accountW;

	public AccountModel()
	{
		
	}
	
//	public String getTempAccountBalance(){
//		double d=Double.parseDouble(tradeAccountC.getAccountBalance())+Double.parseDouble(tradeAccountB.getAccountBalance())+Double.parseDouble(tradeAccountW.getAccountBalance());
//		DecimalFormat df = new DecimalFormat("##.0000");
//		return df.format(d);
//	}
//	public Double getTempAccountInitBalance(){
//		return Double.parseDouble(tradeAccountC.getInitBalance())+Double.parseDouble(tradeAccountB.getInitBalance())+Double.parseDouble(tradeAccountW.getInitBalance());
//	}
	//总盈亏率
	/*public String getStringSumProfitAndLoss(){
		double value=(Double.parseDouble(getTempAccountBalance())-getTempAccountInitBalance())/getTempAccountInitBalance();
		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMaximumFractionDigits(2);
		return nf.format(value);
	}*/
	
	//各账户盈亏率
	public StringBuffer getStringSelfProfitAndLoss(){
		StringBuffer str=new StringBuffer("");
		if(accountC.equals("1"))
		{
			str.append("c="+tradeAccountC.getTempProfitAndLossRate()+"<br/>");
		}
		if(accountW.equals("1"))
		{
			str.append("w="+tradeAccountW.getTempProfitAndLossRate()+"<br/>");
		}
		if(accountB.equals("1"))
		{
			str.append("b="+tradeAccountB.getTempProfitAndLossRate()+"<br/>");
		}
	/*	<ww:if test="#request.accountC==1">c=${accountModel.tradeAccountC.tempProfitAndLossRate}<br/></ww:if>
		<ww:if test="#request.accountW==1">w=${accountModel.tradeAccountW.tempProfitAndLossRate}<br/></ww:if>
		<ww:if test="#request.accountB==1">b=${accountModel.tradeAccountB.tempProfitAndLossRate}</ww:if>*/
		return str;
	}
	
	//各账户初始化金额
	public StringBuffer getStringExactInitBalance(){
		StringBuffer str=new StringBuffer("");
		if(accountC.equals("1"))
		{
			str.append("c="+tradeAccountC.getExactInitBalance()+"<br/>");
		}
		if(accountW.equals("1"))
		{
			str.append("w="+tradeAccountW.getExactInitBalance()+"<br/>");
		}
		if(accountB.equals("1"))
		{
			str.append("b="+tradeAccountB.getExactInitBalance()+"<br/>");
		}
	/*	<ww:if test="#request.accountC==1">c=${accountModel.tradeAccountC.exactInitBalance}<br/></ww:if>
		<ww:if test="#request.accountW==1">w=${accountModel.tradeAccountW.exactInitBalance}<br/></ww:if>
		<ww:if test="#request.accountB==1">b=${accountModel.tradeAccountB.exactInitBalance}</ww:if>*/
		return str;
	}
	
	//各账户实时金额
		public StringBuffer getStringExactAccountBalance(){
			StringBuffer str=new StringBuffer("");
			if(accountC.equals("1"))
			{
				str.append("c="+tradeAccountC.getExactAccountBalance()+"<br/>");
			}
			if(accountW.equals("1"))
			{
				str.append("w="+tradeAccountW.getExactAccountBalance()+"<br/>");
			}
			if(accountB.equals("1"))
			{
				str.append("b="+tradeAccountB.getExactAccountBalance()+"<br/>");
			}
			return str;
		}
	
		//各账户盈亏金额stringAccountType
		public StringBuffer getStringSelfProfitAndLossBalance(){
			StringBuffer str=new StringBuffer("");
			if(accountC.equals("1"))
			{
				str.append("c="+tradeAccountC.getStringProfitAndLossBalance()+"<br/>");
			}
			if(accountW.equals("1"))
			{
				str.append("w="+tradeAccountW.getStringProfitAndLossBalance()+"<br/>");
			}
			if(accountB.equals("1"))
			{
				str.append("b="+tradeAccountB.getStringProfitAndLossBalance()+"<br/>");
			}
			return str;
		}
		
		//获得各账户类型
		public StringBuffer getStringAccountType(){
			StringBuffer str=new StringBuffer("");
			if(accountC.equals("1"))
			{
				str.append("人民币账户<br/>");
			}
			if(accountW.equals("1"))
			{
				str.append("外币账户<br/>");
			}
			if(accountB.equals("1"))
			{
				str.append("保证金账户<br/>");
			}
			return str;
		}	
		
		
		
		
//	public String getAccountBalance(){
//		DecimalFormat df = new DecimalFormat("##.0000");
//		return df.format(getTempAccountBalance());
//	}
//	public String getAccountInitBalance(){
//		DecimalFormat df = new DecimalFormat("##.0000");
//		return df.format(getTempAccountInitBalance());
//	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
//	public String getSumProfitAndLoss() {
//		return SumProfitAndLoss;
//	}
//	public void setSumProfitAndLoss(String sumProfitAndLoss) {
//		SumProfitAndLoss = sumProfitAndLoss;
//	}
	public TradeAccountModel getTradeAccountC() {
		return tradeAccountC;
	}



	public void setTradeAccountC(TradeAccountModel tradeAccountC) {
		this.tradeAccountC = tradeAccountC;
	}



	public TradeAccountModel getTradeAccountW() {
		return tradeAccountW;
	}



	public void setTradeAccountW(TradeAccountModel tradeAccountW) {
		this.tradeAccountW = tradeAccountW;
	}



	public TradeAccountModel getTradeAccountB() {
		return tradeAccountB;
	}



	public void setTradeAccountB(TradeAccountModel tradeAccountB) {
		this.tradeAccountB = tradeAccountB;
	}
	
	public String getAccountC() {
		return accountC;
	}

	public void setAccountC(String accountC) {
		this.accountC = accountC;
	}

	public String getAccountB() {
		return accountB;
	}

	public void setAccountB(String accountB) {
		this.accountB = accountB;
	}

	public String getAccountW() {
		return accountW;
	}

	public void setAccountW(String accountW) {
		this.accountW = accountW;
	}



}
