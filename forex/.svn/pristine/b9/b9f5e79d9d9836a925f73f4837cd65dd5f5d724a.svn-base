package com.talent.forex.modules.trade_mng;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import com.talent.forex.bean.model.AccountModel;
import com.talent.forex.bean.model.TradeFlowModel;
import com.talent.forex.constant.SysParamNameConst;
import com.talent.forex.core.ForexBaseAction;
import com.talent.forex.modules.teach_mng.paramMng.ParamMngBo;
import com.talent.forex.util.UserModelUtil;

public class AccountStatementAction extends ForexBaseAction
{
	TradeFlowModel tradeFlowModel=new TradeFlowModel();
	AccountStatementBo accountStatementBo;
	HashMap hashMap=null;
	Collection accinfoColl=null;
	private ParamMngBo paramMngBo;

	public String accountStatementPageInit()
	{
		//requestPut("accountModelList", accountStatementBo.getAccountModelList());
		
		requestPut("usersList",accountStatementBo.getAccInfoUserNum());
		requestPut("groupOneList",paramMngBo.getGroupListDone(SysParamNameConst.GROUP_ONE));
		accinfoColl=accountStatementBo.accInfoQuery(tradeFlowModel);//获取账户详情
		hashMap=accountStatementBo.formatAccountInfo(accinfoColl);//账户金额规格化并且转换成CNY或USD
		Collection c=hashMap.values();
		Iterator iter=c.iterator();
		AccountModel tempAccountModel;
		while(iter.hasNext()){
			tempAccountModel=new AccountModel();
			tempAccountModel= (AccountModel) iter.next();
			tempAccountModel.setAccountB("1");
			tempAccountModel.setAccountC("1");
			tempAccountModel.setAccountW("1");
		}
		requestPut("accountModelList",c);
		return SUCCESS;
	}

	public String stuaccountStatementPageInit()
	{
		String accountNo = UserModelUtil.getUser().getUserId();
		accinfoColl = accountStatementBo.accInfoQuery(accountNo);
		hashMap = accountStatementBo.formatAccountInfo(accinfoColl);
		Collection c = hashMap.values();
		Iterator iter = c.iterator();
		AccountModel tempAccountModel;
		while(iter.hasNext()){
			tempAccountModel=new AccountModel();
			tempAccountModel= (AccountModel) iter.next();
			tempAccountModel.setAccountB("1");
			tempAccountModel.setAccountC("1");
			tempAccountModel.setAccountW("1");
		}
		requestPut("accountModelList",c);
		return SUCCESS;
	}
	
	
	public String accoutStatementQuery()
	{
		requestPut("usersList",accountStatementBo.getAccInfoUserNum());
		requestPut("groupOneList",paramMngBo.getGroupListDone(SysParamNameConst.GROUP_ONE));
		accinfoColl=accountStatementBo.accInfoQuery(tradeFlowModel);
		hashMap=accountStatementBo.formatAccountInfo(accinfoColl);
		Collection c=hashMap.values();
		Iterator iter=c.iterator();
		AccountModel tempAccountModel;
		if(tradeFlowModel.getTradeType()==null||tradeFlowModel.getTradeType().equals("ALL")){
			while(iter.hasNext()){
				tempAccountModel=new AccountModel();
				tempAccountModel=(AccountModel) iter.next();
				tempAccountModel.setAccountB("1");
				tempAccountModel.setAccountC("1");
				tempAccountModel.setAccountW("1");
			}
		}
		else if(tradeFlowModel.getTradeType().equals("C")){
			while(iter.hasNext()){
				tempAccountModel=new AccountModel();
				tempAccountModel=(AccountModel) iter.next();
				tempAccountModel.setAccountB("0");
				tempAccountModel.setAccountC("1");
				tempAccountModel.setAccountW("0");
			}
		}
		else if(tradeFlowModel.getTradeType().equals("B")){
			while(iter.hasNext()){
				tempAccountModel=new AccountModel();
				tempAccountModel=(AccountModel) iter.next();
				tempAccountModel.setAccountB("1");
				tempAccountModel.setAccountC("0");
				tempAccountModel.setAccountW("0");
			}
		}
		else if(tradeFlowModel.getTradeType().equals("W")){
			while(iter.hasNext()){
				tempAccountModel=new AccountModel();
				tempAccountModel=(AccountModel) iter.next();
				tempAccountModel.setAccountB("0");
				tempAccountModel.setAccountC("0");
				tempAccountModel.setAccountW("1");
			}
		}
		
		requestPut("accountModelList",c);
		return SUCCESS;
	}

	
	
	
	
	
	
	public AccountStatementBo getAccountStatementBo() {
		return accountStatementBo;
	}

	public void setAccountStatementBo(AccountStatementBo accountStatementBo) {
		this.accountStatementBo = accountStatementBo;
	}

	public TradeFlowModel getTradeFlowModel() {
		return tradeFlowModel;
	}

	public void setTradeFlowModel(TradeFlowModel tradeFlowModel) {
		this.tradeFlowModel = tradeFlowModel;
	}

	public ParamMngBo getParamMngBo() {
		return paramMngBo;
	}

	public void setParamMngBo(ParamMngBo paramMngBo) {
		this.paramMngBo = paramMngBo;
	}

}
