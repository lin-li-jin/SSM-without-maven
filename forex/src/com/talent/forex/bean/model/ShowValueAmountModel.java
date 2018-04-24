package com.talent.forex.bean.model;

import com.talent.forex.util.FormatParamUtil;

/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:这是类是为了在保证金交易页面显示账户可用余额，可用余额= 扩大的货币
 * 
 * @author atggdsaiDong
 *
 */

public class ShowValueAmountModel {

	private String returnAmount;
	private String ccy;
	
	
	public ShowValueAmountModel(String returnAmount,String ccy){
		this.returnAmount = returnAmount;
		this.ccy = ccy;
	}

	public String getReturnAmount() {
		return returnAmount;
	}

	public void setReturnAmount(String returnAmount) {
		this.returnAmount = returnAmount;
	}

	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}


}
