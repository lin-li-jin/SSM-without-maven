package com.talent.forex.bean.domain;



/**
 * OverallRank entity. @author MyEclipse Persistence Tools
 */
public class OverallRank extends AbstractOverallRank implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public OverallRank() {
    }

	/** minimal constructor */
    public OverallRank(String userNum, String name, String group) {
        super(userNum, name, group);        
    }
    
    /** full constructor */
    public OverallRank(String userNum, String name, String group, String rank, String cnyRank, String forRank, String marginRank, String amtRank, String qtyRank, String rateRank) {
        super(userNum, name, group, rank, cnyRank, forRank, marginRank, amtRank, qtyRank, rateRank);        
    }
   
}
