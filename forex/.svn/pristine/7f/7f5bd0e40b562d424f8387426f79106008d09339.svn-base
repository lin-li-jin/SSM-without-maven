package com.talent.forex.bean.domain;



/**
 * AbstractMarginForwardInfo entity provides the base persistence definition of the MarginForwardInfo entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractMarginForwardInfo  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String tranType;
     private String tranNo;
     private String userNum;
     private String valueDate;
     private String weCcy;
     private String anaCcy;
     private String account;
     private String accAmount;
     private String accBalance;
     private String dealAmt;
     private String price;
     private String statue;
     private String createDatetime;
     private String LAmount;
     private String direction;

     private String statusDescr;
     private String dirDescr;

    // Constructors

    /** default constructor */
    public AbstractMarginForwardInfo() {
    }

	/** minimal constructor */
    public AbstractMarginForwardInfo(String tranType, String tranNo, String userNum, String valueDate, String weCcy, String anaCcy, String account, String accAmount, String dealAmt, String price, String statue, String createDatetime, String direction) {
        this.tranType = tranType;
        this.tranNo = tranNo;
        this.userNum = userNum;
        this.valueDate = valueDate;
        this.weCcy = weCcy;
        this.anaCcy = anaCcy;
        this.account = account;
        this.accAmount = accAmount;
        this.dealAmt = dealAmt;
        this.price = price;
        this.statue = statue;
        this.createDatetime = createDatetime;
        this.direction = direction;
    }
    
    /** full constructor */
    public AbstractMarginForwardInfo(String tranType, String tranNo, String userNum, String valueDate, String weCcy, String anaCcy, String account, String accAmount, String accBalance, String dealAmt, String price, String statue, String createDatetime, String LAmount, String direction) {
        this.tranType = tranType;
        this.tranNo = tranNo;
        this.userNum = userNum;
        this.valueDate = valueDate;
        this.weCcy = weCcy;
        this.anaCcy = anaCcy;
        this.account = account;
        this.accAmount = accAmount;
        this.accBalance = accBalance;
        this.dealAmt = dealAmt;
        this.price = price;
        this.statue = statue;
        this.createDatetime = createDatetime;
        this.LAmount = LAmount;
        this.direction = direction;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getTranType() {
        return this.tranType;
    }
    
    public void setTranType(String tranType) {
        this.tranType = tranType;
    }

    public String getTranNo() {
        return this.tranNo;
    }
    
    public void setTranNo(String tranNo) {
        this.tranNo = tranNo;
    }

    public String getUserNum() {
        return this.userNum;
    }
    
    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getValueDate() {
        return this.valueDate;
    }
    
    public void setValueDate(String valueDate) {
        this.valueDate = valueDate;
    }

    public String getWeCcy() {
        return this.weCcy;
    }
    
    public void setWeCcy(String weCcy) {
        this.weCcy = weCcy;
    }

    public String getAnaCcy() {
        return this.anaCcy;
    }
    
    public void setAnaCcy(String anaCcy) {
        this.anaCcy = anaCcy;
    }

    public String getAccount() {
        return this.account;
    }
    
    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccAmount() {
        return this.accAmount;
    }
    
    public void setAccAmount(String accAmount) {
        this.accAmount = accAmount;
    }

    public String getAccBalance() {
        return this.accBalance;
    }
    
    public void setAccBalance(String accBalance) {
        this.accBalance = accBalance;
    }

    public String getDealAmt() {
        return this.dealAmt;
    }
    
    public void setDealAmt(String dealAmt) {
        this.dealAmt = dealAmt;
    }

    public String getPrice() {
        return this.price;
    }
    
    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatue() {
        return this.statue;
    }
    


	public void setStatue(String statue) {
        this.statue = statue;
    }

    public String getCreateDatetime() {
        return this.createDatetime;
    }
    
    public void setCreateDatetime(String createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getLAmount() {
        return this.LAmount;
    }
    
    public void setLAmount(String LAmount) {
        this.LAmount = LAmount;
    }

    public String getDirection() {
        return this.direction;
    }
    
    public void setDirection(String direction) {
        this.direction = direction;
    }

	public String getStatusDescr() {
		if(statue.equals("A")){
			return "有效";
		}else if(statue.equals("D")){
			return "完成";
		}else if(statue.equals("I")){
			return "无效";
		}else if(statue.equals("P")){
			return "处理中";
		}else {
			return "取消";
		}
	}

	public void setStatusDescr(String statusDescr) {
		this.statusDescr = statusDescr;
	}

	public String getDirDescr() {
		if(direction.equals("1")){
			return "买";
		}else{
			return "卖";
		}
	}

	public void setDirDescr(String dirDescr) {
		this.dirDescr = dirDescr;
	}
   

	








}