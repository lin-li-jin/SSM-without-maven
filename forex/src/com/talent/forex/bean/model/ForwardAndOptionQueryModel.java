package com.talent.forex.bean.model;

/**
 * Title:
 * Description:���model������ѯ��֤��Զ�ڽ��׺���Ȩ�������ݱ�������ֶ����ֲ�һ��������ͬһ��ectable������
 * �����ĳ�ͳһ��־���Ǹ�TradeRecordModel�ˣ����model��ʱ�ò��ˣ����ǵ������model��hql��仹ע�������ݿ����ӳ���ļ���
 * Copyright: Copyright (c) 2008

 * Company: Talent Information Solutions Ltd. 
 * @author atggdsaiDONG
 *
 */
public class ForwardAndOptionQueryModel {
	// Fields
	private String tranType;
	private String tranNo;
	private String valueDate;
	private String weCcy;
	private String anaCcy;
	private String accAmount;
	private String price;
	private String statue;
	private String tranDescr;
	
	public ForwardAndOptionQueryModel(){}
	
	public ForwardAndOptionQueryModel(String tranType, String tranNo,
			String valueDate, String weCcy, String anaCcy, String accAmount,
			String price, String statue) {
		super();
		this.tranType = tranType;
		this.tranNo = tranNo;
		this.valueDate = valueDate;
		this.weCcy = weCcy;
		this.anaCcy = anaCcy;
		this.accAmount = accAmount;
		this.price = price;
		this.statue = statue;
	}
	public String getTranType() {
		return tranType;
	}
	public void setTranType(String tranType) {
		this.tranType = tranType;
	}
	public String getTranNo() {
		return tranNo;
	}
	public void setTranNo(String tranNo) {
		this.tranNo = tranNo;
	}
	public String getValueDate() {
		return valueDate;
	}
	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}
	public String getWeCcy() {
		return weCcy;
	}
	public void setWeCcy(String weCcy) {
		this.weCcy = weCcy;
	}
	public String getAnaCcy() {
		return anaCcy;
	}
	public void setAnaCcy(String anaCcy) {
		this.anaCcy = anaCcy;
	}
	public String getAccAmount() {
		return accAmount;
	}
	public void setAccAmount(String accAmount) {
		this.accAmount = accAmount;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getStatue() {
		return statue;
	}


	public void setStatue(String statue) {
		this.statue = statue;
	}
	
	public String getTranDescr() {
		if(tranNo.startsWith("FC")){
			return "Զ�ڽ���";
		}else if(tranNo.startsWith("MO")){
			return "��Ȩ����";
		}else{
			return "";
		}
	}

}