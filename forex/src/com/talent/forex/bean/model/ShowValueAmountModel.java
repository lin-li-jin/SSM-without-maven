package com.talent.forex.bean.model;

import com.talent.forex.util.FormatParamUtil;

/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:��������Ϊ���ڱ�֤����ҳ����ʾ�˻��������������= ����Ļ���
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
