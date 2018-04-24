package com.talent.forex.bean.domain;



/**
 * MarginForwardInfo entity. @author MyEclipse Persistence Tools
 */
public class MarginForwardInfo extends AbstractMarginForwardInfo implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public MarginForwardInfo() {
    }

	/** minimal constructor */
    public MarginForwardInfo(String tranType, String tranNo, String userNum, String valueDate, String weCcy, String anaCcy, String account, String accAmount, String dealAmt, String price, String statue, String createDatetime, String direction) {
        super(tranType, tranNo, userNum, valueDate, weCcy, anaCcy, account, accAmount, dealAmt, price, statue, createDatetime, direction);        
    }
    
    /** full constructor */
    public MarginForwardInfo(String tranType, String tranNo, String userNum, String valueDate, String weCcy, String anaCcy, String account, String accAmount, String accBalance, String dealAmt, String price, String statue, String createDatetime, String LAmount, String direction) {
        super(tranType, tranNo, userNum, valueDate, weCcy, anaCcy, account, accAmount, accBalance, dealAmt, price, statue, createDatetime, LAmount, direction);        
    }
   
}
