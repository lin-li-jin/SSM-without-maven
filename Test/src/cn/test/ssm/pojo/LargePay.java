package cn.test.ssm.pojo;

/**
 * Copyright © 2018 Talent Information Solutions Ltd. All rights reserved.
 * 
 * @ClassName: LargePay
 * @Description: TODO(大额贷记业务信息pojo)
 * @author: GK
 * @version V1.0
 */
public class LargePay {
	private String txnType;// 业务类型编码
	private String tranType;// 业务种类编码
	private String priority;// 业务优先级
	private String endToEndId;// 行内流水号
	private String curency;// 币种
	private String amt;// 金额
	private String ugFlag;// 客户/金融机构
	private String payerBrnchBankId;// 付款行行号
	private String payerBrnchBankName;// 付款行行名
	private String payerSttlmBankId;// 付款行清算行号
	private String payerSttlmBankName;// 付款行清算行名
	private String payerAcctBankId;// 付款人开户行行号
	private String payerAcctBankName;// 付款人开户行行名
	private String payerAcctNo;// 付款人账号
	private String payerName;// 付款人名称
	private String payerAddr;// 付款人地址
	private String payeeAcctBankId;// 收款行开户行行号
	private String payeeAcctBankName;// 收款行开户行行名
	private String payeeBrnchBankId;// 收款行行号
	private String payeeBrnName;// 收款行行名
	private String payeeSttlmBankId;// 收款行清算行号
	private String payeeAcctNo;// 收款人账号
	private String payeeName;// 收款人名称
	private String payeeAddr;// 收款人地址
	private String rmk;// 备注
	private String addtn;// 附言

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	public String getTranType() {
		return tranType;
	}

	public void setTranType(String tranType) {
		this.tranType = tranType;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getEndToEndId() {
		return endToEndId;
	}

	public void setEndToEndId(String endToEndId) {
		this.endToEndId = endToEndId;
	}

	public String getCurency() {
		return curency;
	}

	public void setCurency(String curency) {
		this.curency = curency;
	}

	public String getAmt() {
		return amt;
	}

	public void setAmt(String amt) {
		this.amt = amt;
	}

	public String getUgFlag() {
		return ugFlag;
	}

	public void setUgFlag(String ugFlag) {
		this.ugFlag = ugFlag;
	}

	public String getPayerBrnchBankId() {
		return payerBrnchBankId;
	}

	public void setPayerBrnchBankId(String payerBrnchBankId) {
		this.payerBrnchBankId = payerBrnchBankId;
	}

	public String getPayerBrnchBankName() {
		return payerBrnchBankName;
	}

	public void setPayerBrnchBankName(String payerBrnchBankName) {
		this.payerBrnchBankName = payerBrnchBankName;
	}

	public String getPayerSttlmBankId() {
		return payerSttlmBankId;
	}

	public void setPayerSttlmBankId(String payerSttlmBankId) {
		this.payerSttlmBankId = payerSttlmBankId;
	}

	public String getPayerSttlmBankName() {
		return payerSttlmBankName;
	}

	public void setPayerSttlmBankName(String payerSttlmBankName) {
		this.payerSttlmBankName = payerSttlmBankName;
	}

	public String getPayerAcctBankId() {
		return payerAcctBankId;
	}

	public void setPayerAcctBankId(String payerAcctBankId) {
		this.payerAcctBankId = payerAcctBankId;
	}

	public String getPayerAcctBankName() {
		return payerAcctBankName;
	}

	public void setPayerAcctBankName(String payerAcctBankName) {
		this.payerAcctBankName = payerAcctBankName;
	}

	public String getPayerAcctNo() {
		return payerAcctNo;
	}

	public void setPayerAcctNo(String payerAcctNo) {
		this.payerAcctNo = payerAcctNo;
	}

	public String getPayerName() {
		return payerName;
	}

	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}

	public String getPayerAddr() {
		return payerAddr;
	}

	public void setPayerAddr(String payerAddr) {
		this.payerAddr = payerAddr;
	}

	public String getPayeeAcctBankId() {
		return payeeAcctBankId;
	}

	public void setPayeeAcctBankId(String payeeAcctBankId) {
		this.payeeAcctBankId = payeeAcctBankId;
	}

	public String getPayeeAcctBankName() {
		return payeeAcctBankName;
	}

	public void setPayeeAcctBankName(String payeeAcctBankName) {
		this.payeeAcctBankName = payeeAcctBankName;
	}

	public String getPayeeBrnchBankId() {
		return payeeBrnchBankId;
	}

	public void setPayeeBrnchBankId(String payeeBrnchBankId) {
		this.payeeBrnchBankId = payeeBrnchBankId;
	}

	public String getPayeeBrnName() {
		return payeeBrnName;
	}

	public void setPayeeBrnName(String payeeBrnName) {
		this.payeeBrnName = payeeBrnName;
	}

	public String getPayeeSttlmBankId() {
		return payeeSttlmBankId;
	}

	public void setPayeeSttlmBankId(String payeeSttlmBankId) {
		this.payeeSttlmBankId = payeeSttlmBankId;
	}

	public String getPayeeAcctNo() {
		return payeeAcctNo;
	}

	public void setPayeeAcctNo(String payeeAcctNo) {
		this.payeeAcctNo = payeeAcctNo;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	public String getPayeeAddr() {
		return payeeAddr;
	}

	public void setPayeeAddr(String payeeAddr) {
		this.payeeAddr = payeeAddr;
	}

	public String getRmk() {
		return rmk;
	}

	public void setRmk(String rmk) {
		this.rmk = rmk;
	}

	public String getAddtn() {
		return addtn;
	}

	public void setAddtn(String addtn) {
		this.addtn = addtn;
	}

}
