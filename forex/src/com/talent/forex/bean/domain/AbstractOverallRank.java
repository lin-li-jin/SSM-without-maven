package com.talent.forex.bean.domain;



/**
 * AbstractOverallRank entity provides the base persistence definition of the OverallRank entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractOverallRank  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String userNum;
     private String name;
     private String group;
     private String rank;
     private String cnyRank;
     private String forRank;
     private String marginRank;
     private String amtRank;
     private String qtyRank;
     private String rateRank;


    // Constructors

    /** default constructor */
    public AbstractOverallRank() {
    }

	/** minimal constructor */
    public AbstractOverallRank(String userNum, String name, String group) {
        this.userNum = userNum;
        this.name = name;
        this.group = group;
    }
    
    /** full constructor */
    public AbstractOverallRank(String userNum, String name, String group, String rank, String cnyRank, String forRank, String marginRank, String amtRank, String qtyRank, String rateRank) {
        this.userNum = userNum;
        this.name = name;
        this.group = group;
        this.rank = rank;
        this.cnyRank = cnyRank;
        this.forRank = forRank;
        this.marginRank = marginRank;
        this.amtRank = amtRank;
        this.qtyRank = qtyRank;
        this.rateRank = rateRank;
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

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return this.group;
    }
    
    public void setGroup(String group) {
        this.group = group;
    }

    public String getRank() {
        return this.rank;
    }
    
    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getCnyRank() {
        return this.cnyRank;
    }
    
    public void setCnyRank(String cnyRank) {
        this.cnyRank = cnyRank;
    }

    public String getForRank() {
        return this.forRank;
    }
    
    public void setForRank(String forRank) {
        this.forRank = forRank;
    }

    public String getMarginRank() {
        return this.marginRank;
    }
    
    public void setMarginRank(String marginRank) {
        this.marginRank = marginRank;
    }

    public String getAmtRank() {
        return this.amtRank;
    }
    
    public void setAmtRank(String amtRank) {
        this.amtRank = amtRank;
    }

    public String getQtyRank() {
        return this.qtyRank;
    }
    
    public void setQtyRank(String qtyRank) {
        this.qtyRank = qtyRank;
    }

    public String getRateRank() {
        return this.rateRank;
    }
    
    public void setRateRank(String rateRank) {
        this.rateRank = rateRank;
    }
   








}