package com.talent.forex.modules.trade.bid;

import java.util.Date;

import com.talent.forex.bean.domain.OneClickInfo;
import com.talent.forex.core.ForexBaseAction;
import com.talent.forex.util.FormatParamUtil;
import com.talent.forex.util.GetDateTimeUtil;
import com.talent.forex.util.UserModelUtil;
/*
 * Amendment No.: FOEXAS001
 * Create By    : lzc
 * Description  : 一口价交易
 * Modify Date  : 2014-07-24
 */
public class OneClickTradeAction extends ForexBaseAction {

	private String direct;
	private String ccy1;
	private String ccy2;
	private String amount;
	private String price;
	
	private OneClickManageBo oneClickManageBo;
	public String pageInit(){
		return SUCCESS;
	}
	
	/**
	 * 添加交易记录
	 */
	public String oneTradeRecordAdd(){
		OneClickInfo o = oneClickManageBo.oneClickInfoAdd(direct, ccy1, ccy2, amount, price);
		if (o == null){
			if (ccy2.equals("CNY"))
				return "error";
			return "foreign";
		}
		requestPut("oneClickInfo", o);
		return SUCCESS;
	}

	public String getDirect() {
		return direct;
	}

	public void setDirect(String direct) {
		this.direct = direct;
	}

	public String getCcy1() {
		return ccy1;
	}

	public void setCcy1(String ccy1) {
		this.ccy1 = ccy1;
	}

	public String getCcy2() {
		return ccy2;
	}

	public void setCcy2(String ccy2) {
		this.ccy2 = ccy2;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public OneClickManageBo getOneClickManageBo() {
		return oneClickManageBo;
	}

	public void setOneClickManageBo(OneClickManageBo oneClickManageBo) {
		this.oneClickManageBo = oneClickManageBo;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
}
