package com.talent.forex.bean.model;

public class AccountInfoModel {
	
	String userNum;//����Ա��
	String accNO; //�˻���
	String accType;//�˻�����
	String cCY;//����
	String OriginalAmt; //��ʼ���
	String Amount; //���ֽ��
	//String Rate;//����
	
//	public String getRate() {
//		return Rate;
//	}

	public AccountInfoModel(String userNum) {
		this.userNum = userNum;
	}


	public AccountInfoModel(String userNum, String accNO, String accType,
			String cCY, String originalAmt, String amount) {
		super();
		this.userNum = userNum;
		this.accNO = accNO;
		this.accType = accType;
		this.cCY = cCY;
		OriginalAmt = originalAmt;
		Amount = amount;
		//Rate = rate;
	}




//	public void setRate(String rate) {
//		Rate = rate;
//	}




	public AccountInfoModel()
	{
		
	}
	
	
	
	
	public String getUserNum() {
		return userNum;
	}
	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}
	public String getAccNO() {
		return accNO;
	}
	public void setAccNO(String accNO) {
		this.accNO = accNO;
	}
	public String getAccType() {
		return accType;
	}
	public void setAccType(String accType) {
		this.accType = accType;
	}
	public String getcCY() {
		return cCY;
	}
	public void setcCY(String cCY) {
		this.cCY = cCY;
	}
	public String getOriginalAmt() {
		return OriginalAmt;
	}
	public void setOriginalAmt(String originalAmt) {
		OriginalAmt = originalAmt;
	}
	public String getAmount() {
		return Amount;
	}
	public void setAmount(String amount) {
		Amount = amount;
	}
}
