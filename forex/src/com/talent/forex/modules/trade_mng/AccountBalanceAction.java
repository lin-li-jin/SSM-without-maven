package com.talent.forex.modules.trade_mng;

import java.util.Collection;

import com.talent.forex.core.ForexBaseAction;
/*
 * Amendment No.: FOEXAS023
 * Create By    : lzc
 * Description  : �˻�������
 * Modify Date  : 2014-08-05
 */
public class AccountBalanceAction extends ForexBaseAction {

	private AccountBalanceManageBo accountBalanceManageBo;
	
	/**
	 * ҳ���ʼ���������û��˻�������Ϣ
	 */
	public String pageInit(){
		Collection<?> collection = accountBalanceManageBo.accountBalanceQuery();
		if (collection.size() > 0){
			requestPut("accountBalanceList",collection);
		}
		return SUCCESS;
	}

	public AccountBalanceManageBo getAccountBalanceManageBo() {
		return accountBalanceManageBo;
	}

	public void setAccountBalanceManageBo(
			AccountBalanceManageBo accountBalanceManageBo) {
		this.accountBalanceManageBo = accountBalanceManageBo;
	}
	
}
