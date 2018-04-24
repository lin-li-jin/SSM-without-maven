package com.talent.forex.modules.trade_mng;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import com.talent.base.BaseBo;
import com.talent.forex.bean.model.AccountInfoModel;
import com.talent.forex.bean.model.AccountModel;
import com.talent.forex.bean.model.TradeAccountModel;
import com.talent.forex.bean.model.TradeFlowModel;
import com.talent.forex.dao.AccInfoDao;
import com.talent.forex.util.RateUtil;

public class AccountStatementBo extends BaseBo{
	private AccInfoDao accInfoDao;
	
	//存放以交易员号为key的HashMap
	
	public AccountStatementBo()
	{
		
	}
	
	public Collection accInfoQuery(TradeFlowModel tradeFlowModel){
		ArrayList paraList=new ArrayList();
		if("".equals(tradeFlowModel.getGroupType())||tradeFlowModel.getGroupType()==null){
			paraList.add("%%");
			paraList.add("%%");
		}
		else if("O".equals(tradeFlowModel.getGroupType())){
			if("".equals(tradeFlowModel.getGroupOne())||tradeFlowModel.getGroupOne()==null){
				paraList.add("%%");
			}else{
				paraList.add(tradeFlowModel.getGroupOne());
			}
			paraList.add("%%");
		}
		else if("T".equals(tradeFlowModel.getGroupType())){
			if("".equals(tradeFlowModel.getGroupOne())||tradeFlowModel.getGroupOne()==null){
				paraList.add("%%");
			}else{
				paraList.add(tradeFlowModel.getGroupOne());
			}
			if("".equals(tradeFlowModel.getGroupTwo())||tradeFlowModel.getGroupTwo()==null){
				paraList.add("%%");
			}else{
				paraList.add(tradeFlowModel.getGroupTwo());
			}
		}
		
		if("".equals(tradeFlowModel.getUserNumber())||tradeFlowModel.getUserNumber()==null){
			paraList.add("%%");
		}else{
			paraList.add(tradeFlowModel.getUserNumber());
			//paraList.add("%%");
		}
		return accInfoDao.getBeansByParams("getAccountInfoModel", paraList);
	}
	
	public Collection getAccInfoUserNum(){
		ArrayList paraList=new ArrayList();
		Collection c=accInfoDao.getBeansByParams("getAccInfoUserNum", paraList);
		return c;
	}
	
	public Collection accInfoQuery(String accountNo){
		ArrayList paraList=new ArrayList();
		paraList.add(accountNo);
		return accInfoDao.getBeansByParams("getCorreAccountInfoModel", paraList);
	}
	public HashMap formatAccountInfo(Collection cc)
	{
		AccountModel accModel=new AccountModel();//交易员总账户，包括CWB
		HashMap formatAccInfoHaspMap = new HashMap();
		ArrayList<AccountInfoModel> accountInfo = (ArrayList<AccountInfoModel>)cc;
		for(AccountInfoModel eachAccountInfo : accountInfo)
		{
			if(null==formatAccInfoHaspMap.get(eachAccountInfo.getUserNum()))
			{
				AccountModel initAccount = new AccountModel();//初始化交易员总账户model
				//设置交易员总账户的交易员号
				initAccount.setAccount(eachAccountInfo.getUserNum());
				if(eachAccountInfo.getAccType().equals("C"))
				{
					TradeAccountModel tempTradeAccountModel = initTradeAccoutModel("C", "人民币账户", eachAccountInfo);
//					tempTradeAccountModel.setRate_USD_TO_CNY(rate.getRate());
					initAccount.setTradeAccountC(tempTradeAccountModel);
				}
				if(eachAccountInfo.getAccType().equals("W"))
				{
					TradeAccountModel tempTradeAccountModel = initTradeAccoutModel("W", "外币账户", eachAccountInfo);
					initAccount.setTradeAccountW(tempTradeAccountModel);
				}
				if(eachAccountInfo.getAccType().equals("B"))
				{
					TradeAccountModel tempTradeAccountModel = initTradeAccoutModel("B", "保证金账户", eachAccountInfo);
					initAccount.setTradeAccountB(tempTradeAccountModel);
				}
				//以交易员号为Key,AccountModel为value,put进HashMap
				formatAccInfoHaspMap.put(eachAccountInfo.getUserNum(), initAccount);
			}
			//交易员号为key的hash表的value存在
			else
			{
				//System.out.println("hash表存在相应的value");
				accModel = (AccountModel) formatAccInfoHaspMap.get(""+eachAccountInfo.getUserNum());
			
				if(eachAccountInfo.getAccType().equals("C"))
				{
					if(null==accModel.getTradeAccountC())
					{
						TradeAccountModel tempTradeAccountModel = initTradeAccoutModel("C", "人民币账户", eachAccountInfo);
//						tempTradeAccountModel.setRate_USD_TO_CNY(rate.getRate());
						accModel.setTradeAccountC(tempTradeAccountModel);
					}else{
						//可能初始金额还没有获取
						//String a=updateBalance(accModel,tempAccountInfo);
						//System.out.println(a+"123");
						accModel.getTradeAccountC().setAccountBalance(updateBalance(accModel,eachAccountInfo));
					}
				}
				if(eachAccountInfo.getAccType().equals("W"))
				{
					if(null==accModel.getTradeAccountW()){
						TradeAccountModel tempTradeAccountModel=initTradeAccoutModel("W","外币账户",eachAccountInfo);
						accModel.setTradeAccountW(tempTradeAccountModel);
					}else{
						//可能初始金额还没有获取
						accModel.getTradeAccountW().setAccountBalance(updateBalance(accModel,eachAccountInfo));
					}
				}
				if(eachAccountInfo.getAccType().equals("B"))
				{
					if(null==accModel.getTradeAccountB()){
						TradeAccountModel tempTradeAccountModel=initTradeAccoutModel("B","保证金账户",eachAccountInfo);
						accModel.setTradeAccountB(tempTradeAccountModel);
					}else{
						//可能初始金额还没有获取
						accModel.getTradeAccountB().setAccountBalance(updateBalance(accModel,eachAccountInfo));
					}
				}
			}
		}
		return formatAccInfoHaspMap;
	}
	
	private String updateBalance(AccountModel accModel,AccountInfoModel tempAccountInfo){
		String sum = null;
		
		if(tempAccountInfo.getAccType().equals("B")){
			double current;
			double originalAmt = Double.parseDouble(tempAccountInfo.getOriginalAmt());
			if(originalAmt == 0)
			{
				double rate = RateUtil.getRateByCcy("B", tempAccountInfo.getcCY());
				if(tempAccountInfo.getcCY().equals("JPY") || tempAccountInfo.getcCY().equals("CAD")){
					rate = 1 / rate;
				}
				current = Double.parseDouble(formatDecimal(tempAccountInfo.getAmount(), rate));
				//accModel.getTradeAccountB().setInitBalance(formatDecimal(tempAccountInfo.getOriginalAmt(),tempAccountInfo.getRate()));
			}
			else{
				accModel.getTradeAccountB().setInitBalance("500000.0000");
				current = Double.parseDouble(tempAccountInfo.getAmount());
				//current = Double.parseDouble(formatDecimal(tempAccountInfo.getAmount(), tempAccountInfo.getRate()));
			}
			sum = "" + (Double.parseDouble(accModel.getTradeAccountB().getAccountBalance()) + current);
			//sum = formatDecimal(sum, 1);
		}
		if(tempAccountInfo.getAccType().equals("C")){
			double current;
			double originalAmt = Double.parseDouble(tempAccountInfo.getOriginalAmt());
			if(originalAmt == 0)
			{
				current = Double.parseDouble(formatDecimal(tempAccountInfo.getAmount(), RateUtil.getRateByCcy("C", tempAccountInfo.getcCY())));
				//accModel.getTradeAccountC().setInitBalance(formatDecimal(tempAccountInfo.getOriginalAmt(),tempAccountInfo.getRate()));
			}
			else{
				accModel.getTradeAccountC().setInitBalance("2000000.0000");
				current = Double.parseDouble(tempAccountInfo.getAmount());
				//current = Double.parseDouble(formatDecimal(tempAccountInfo.getAmount(),tempAccountInfo.getRate()));
			}
			sum = "" + (Double.parseDouble(accModel.getTradeAccountC().getAccountBalance()) + current);
			//System.out.println("sum为"+sum);
			//System.out.println("sum1··为"+Double.parseDouble(formatDecimal(tempAccountInfo.getAmount(),tempAccountInfo.getRate())));
			//System.out.println("tempAccountInfo.getAmount()的值："+tempAccountInfo.getAmount()+"tempAccountInfo.getARate()的值："+tempAccountInfo.getRate());
			//System.out.println("sum2为"+accModel.getTradeAccountC().getAccountBalance());
			//sum = formatDecimal(sum, 1);
			//System.out.println(tempAccountInfo.getcCY() + "~~~~~~~~~~~~~~~~~~~~C~~~~~~~~~~~~~~~~~~~~~~~current~~~~~~~~~~~~~~~~~~~~~~~" + current);
		}
		if(tempAccountInfo.getAccType().equals("W")){
			double current;
			double originalAmt = Double.parseDouble(tempAccountInfo.getOriginalAmt());
			if(originalAmt == 0)
			{
				double rate = RateUtil.getRateByCcy("W", tempAccountInfo.getcCY());
				if(tempAccountInfo.getcCY().equals("JPY") || tempAccountInfo.getcCY().equals("CAD")){
					rate = 1 / rate;
				}
				current = Double.parseDouble(formatDecimal(tempAccountInfo.getAmount(), rate));
				//current = Double.parseDouble(formatDecimal(tempAccountInfo.getAmount(), RateUtil.getRateByCcy("W", tempAccountInfo.getcCY())));
				//accModel.getTradeAccountW().setInitBalance(formatDecimal(tempAccountInfo.getOriginalAmt(),tempAccountInfo.getRate()));
			}
			else{
				accModel.getTradeAccountW().setInitBalance("1000000.0000");
				current = Double.parseDouble(tempAccountInfo.getAmount());
				//current = Double.parseDouble(formatDecimal(tempAccountInfo.getAmount(),tempAccountInfo.getRate()));
			}
			sum = "" + (Double.parseDouble(accModel.getTradeAccountW().getAccountBalance()) + current);
			//sum = formatDecimal(sum, 1);
			//System.out.println(tempAccountInfo.getcCY() + "~~~~~~~~~~~~~~~~~~~~W~~~~~~~~~~~~~~~~~~~~~~~current~~~~~~~~~~~~~~~~~~~~~~~" + current);
		}
		
		return sum;
	}
	
	private TradeAccountModel initTradeAccoutModel(String type, String accoutName, AccountInfoModel tempAccountInfo){
		TradeAccountModel tempTradeAccountModel = new TradeAccountModel();
		tempTradeAccountModel.setTradeAccount(tempAccountInfo.getAccNO());
		tempTradeAccountModel.setTradeAccountType(type);
		tempTradeAccountModel.setTradeAccountTypeDescribe(accoutName);
		double originalAmt = Double.parseDouble(tempAccountInfo.getOriginalAmt());
		if(originalAmt == 0){}
		else{
			//获得相应交易账户的初始化金额
			//tempTradeAccountModel.setInitBalance(formatDecimal(tempAccountInfo.getOriginalAmt(),tempAccountInfo.getRate()));
			tempTradeAccountModel.setInitBalance(tempAccountInfo.getOriginalAmt());
		}
		tempTradeAccountModel.setInitBalance(tempAccountInfo.getOriginalAmt());//modified
		//System.out.println("tempAccountInfo.getRate()的值："+tempAccountInfo.getRate());
		
		//System.out.println(""+formatDecimal(tempAccountInfo.getAmount(),tempAccountInfo.getRate()));
		
		tempTradeAccountModel.setAccountBalance(tempAccountInfo.getAmount());
		//tempTradeAccountModel.setAccountBalance(formatDecimal(tempAccountInfo.getAmount(),tempAccountInfo.getRate()));
		return tempTradeAccountModel;
	}
	//String i=""+0.0000;
	//double s=Double.parseDouble(i);	
	/**
	 * 
	 * *汇率得到相应的美金*/
	public String formatDecimal(String amount, String tempRateA)
	{
		
		double tempAccount = Double.parseDouble(amount);
		
		double tempRate = Double.parseDouble(tempRateA);
		double tempDouble = tempAccount * tempRate;
		long sss = (long)(tempDouble * 10000);
		double ssss = (double)sss / 10000;
		double a = ssss;
		//double a=4.543234324;
		String str1 = String.valueOf(a);
		//char c1= str1.charAt(str1.indexOf(".") + 1);
	//	System.out.println(str1);
		//System.out.println("位置是："+str1.indexOf("."));
		//System.out.println(c1);
		//截取小数点后面的小数
		  String s2=str1.substring(str1.indexOf(".")+1,(str1.length())); 
		  if(s2.length()==1)
		  {
			  str1=str1+"000";
			  return str1;
		  }
		  if(s2.length()==2)
		  {
			  str1=str1+"00";
			  return str1;
		  }
		  if(s2.length()==3)
		  {
			  str1=str1+"0";
			  return str1;
		  }
		  if(s2.length()==4)
		  {
			  
			  return str1;
		  }
		  else
		  {
			  return str1;
		  }
	}
	/**
	 * 
	 * *汇率得到相应的美金*/
	public String formatDecimal(double tempAccount, String tempRateA)
	{
		double tempRate=Double.parseDouble(tempRateA);
		double tempDouble=tempAccount*tempRate;
		int sss=(int)(tempDouble*10000);
		double ssss=(double)sss/10000;
		double a=ssss;
		//double a=4.543234324;
		String str1 = String.valueOf(a);
		//char c1= str1.charAt(str1.indexOf(".") + 1);
	//	System.out.println(str1);
		//System.out.println("位置是："+str1.indexOf("."));
		//System.out.println(c1);
		//截取小数点后面的小数
		  String s2=str1.substring(str1.indexOf(".")+1,(str1.length())); 
		  if(s2.length()==1)
		  {
			  str1=str1+"000";
			  return str1;
		  }
		  if(s2.length()==2)
		  {
			  str1=str1+"00";
			  return str1;
		  }
		  if(s2.length()==3)
		  {
			  str1=str1+"0";
			  return str1;
		  }
		  if(s2.length()==4)
		  {
			  
			  return str1;
		  }
		  else
		  {
			  return str1;
		  }
	}
	
	/**
	 * 通过汇率计算金额
	 * @param amount 当前货币的金额
	 * @param rate 汇率
	 * @return
	 */
	public String formatDecimal(String amount, double rate)
	{
		double tempAccount = Double.parseDouble(amount);
		double tempDouble = tempAccount * rate;
		long sss = (long)(tempDouble * 10000);
		double ssss = (double)sss / 10000;
		double a = ssss;
		//double a=4.543234324;
		String str1 = String.valueOf(a);
		//char c1= str1.charAt(str1.indexOf(".") + 1);
	//	System.out.println(str1);
		//System.out.println("位置是："+str1.indexOf("."));
		//System.out.println(c1);
		//截取小数点后面的小数
		  String s2=str1.substring(str1.indexOf(".")+1,(str1.length())); 
		  if(s2.length()==1)
		  {
			  str1=str1+"000";
			  return str1;
		  }
		  if(s2.length()==2)
		  {
			  str1=str1+"00";
			  return str1;
		  }
		  if(s2.length()==3)
		  {
			  str1=str1+"0";
			  return str1;
		  }
		  if(s2.length()==4)
		  {
			  
			  return str1;
		  }
		  else
		  {
			  return str1;
		  }
	}

	public AccInfoDao getAccInfoDao() {
		return accInfoDao;
	}

	public void setAccInfoDao(AccInfoDao accInfoDao) {
		this.accInfoDao = accInfoDao;
	}

}
