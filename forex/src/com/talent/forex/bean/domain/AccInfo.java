package com.talent.forex.bean.domain;



/**
 * AccInfo entity. @author MyEclipse Persistence Tools
 */
public class AccInfo extends AbstractAccInfo implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public AccInfo() {
    }

	/** minimal constructor */
    public AccInfo(String userNum, String accno, String accType, String ccy, String originalAmt, String amount, String createDate) {
        super(userNum, accno, accType, ccy, originalAmt, amount, createDate);        
    }
    
    /** full constructor */
    public AccInfo(String userNum, String accno, String accType, String ccy, String originalAmt, String amount, String activeDate, String createDate) {
        super(userNum, accno, accType, ccy, originalAmt, amount, activeDate, createDate);        
    }
   
    public String getRealAccType(){
    	if (this.getAccType().equals("C"))
    		return "人民币交易账户";
    	if (this.getAccType().equals("W"))
    		return "外币对交易账户";
    	return "保证金交易账户";
    }

}
