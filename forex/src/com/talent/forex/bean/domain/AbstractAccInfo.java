package com.talent.forex.bean.domain;



/**
 * AbstractAccInfo entity provides the base persistence definition of the AccInfo entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractAccInfo  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String userNum;
     private String accno;
     private String accType;
     private String ccy;
     private String originalAmt;
     private String amount;
     private String activeDate;
     private String createDate;


    // Constructors

    /** default constructor */
    public AbstractAccInfo() {
    }

	/** minimal constructor */
    public AbstractAccInfo(String userNum, String accno, String accType, String ccy, String originalAmt, String amount, String createDate) {
        this.userNum = userNum;
        this.accno = accno;
        this.accType = accType;
        this.ccy = ccy;
        this.originalAmt = originalAmt;
        this.amount = amount;
        this.createDate = createDate;
    }
    
    /** full constructor */
    public AbstractAccInfo(String userNum, String accno, String accType, String ccy, String originalAmt, String amount, String activeDate, String createDate) {
        this.userNum = userNum;
        this.accno = accno;
        this.accType = accType;
        this.ccy = ccy;
        this.originalAmt = originalAmt;
        this.amount = amount;
        this.activeDate = activeDate;
        this.createDate = createDate;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserNum() {
        return this.userNum;
    }
    
    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getAccno() {
        return this.accno;
    }
    
    public void setAccno(String accno) {
        this.accno = accno;
    }

    public String getAccType() {
        return this.accType;
    }
    
    public void setAccType(String accType) {
        this.accType = accType;
    }

    public String getCcy() {
        return this.ccy;
    }
    
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getOriginalAmt() {
        return this.originalAmt;
    }
    
    public void setOriginalAmt(String originalAmt) {
        this.originalAmt = originalAmt;
    }

    public String getAmount() {
        return this.amount;
    }
    
    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getActiveDate() {
        return this.activeDate;
    }
    
    public void setActiveDate(String activeDate) {
        this.activeDate = activeDate;
    }

    public String getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
   

}