package com.talent.forex.bean.domain;



/**
 * AbstractGroupSysParam entity provides the base persistence definition of the GroupSysParam entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractGroupSysParam  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String groupNum;
     private String WAmount;
     private String WQuantity;
     private String WRate;
     private String WCnyAcc;
     private String WForAcc;
     private String WMarginAcc;
     private String accRankNum;
     private String overallRankNum;


    // Constructors

    /** default constructor */
    public AbstractGroupSysParam() {
    }

	/** minimal constructor */
    public AbstractGroupSysParam(String groupNum, String WAmount, String WQuantity, String WRate, String WCnyAcc, String WForAcc, String WMarginAcc) {
        this.groupNum = groupNum;
        this.WAmount = WAmount;
        this.WQuantity = WQuantity;
        this.WRate = WRate;
        this.WCnyAcc = WCnyAcc;
        this.WForAcc = WForAcc;
        this.WMarginAcc = WMarginAcc;
    }
    
    /** full constructor */
    public AbstractGroupSysParam(String groupNum, String WAmount, String WQuantity, String WRate, String WCnyAcc, String WForAcc, String WMarginAcc, String accRankNum, String overallRankNum) {
        this.groupNum = groupNum;
        this.WAmount = WAmount;
        this.WQuantity = WQuantity;
        this.WRate = WRate;
        this.WCnyAcc = WCnyAcc;
        this.WForAcc = WForAcc;
        this.WMarginAcc = WMarginAcc;
        this.accRankNum = accRankNum;
        this.overallRankNum = overallRankNum;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupNum() {
        return this.groupNum;
    }
    
    public void setGroupNum(String groupNum) {
        this.groupNum = groupNum;
    }

    public String getWAmount() {
        return this.WAmount;
    }
    
    public void setWAmount(String WAmount) {
        this.WAmount = WAmount;
    }

    public String getWQuantity() {
        return this.WQuantity;
    }
    
    public void setWQuantity(String WQuantity) {
        this.WQuantity = WQuantity;
    }

    public String getWRate() {
        return this.WRate;
    }
    
    public void setWRate(String WRate) {
        this.WRate = WRate;
    }

    public String getWCnyAcc() {
        return this.WCnyAcc;
    }
    
    public void setWCnyAcc(String WCnyAcc) {
        this.WCnyAcc = WCnyAcc;
    }

    public String getWForAcc() {
        return this.WForAcc;
    }
    
    public void setWForAcc(String WForAcc) {
        this.WForAcc = WForAcc;
    }

    public String getWMarginAcc() {
        return this.WMarginAcc;
    }
    
    public void setWMarginAcc(String WMarginAcc) {
        this.WMarginAcc = WMarginAcc;
    }

    public String getAccRankNum() {
        return this.accRankNum;
    }
    
    public void setAccRankNum(String accRankNum) {
        this.accRankNum = accRankNum;
    }

    public String getOverallRankNum() {
        return this.overallRankNum;
    }
    
    public void setOverallRankNum(String overallRankNum) {
        this.overallRankNum = overallRankNum;
    }
   








}