package com.talent.forex.bean.domain;



/**
 * BTranFlowMapping entity. @author MyEclipse Persistence Tools
 */
public class BTranFlowMapping extends AbstractBTranFlowMapping implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public BTranFlowMapping() {
    }

	/** minimal constructor */
    public BTranFlowMapping(String userNum) {
        super(userNum);        
    }
    
    /** full constructor */
    public BTranFlowMapping(String userNum, String marginSpotQty, String marginSpotAmt, String marginOptionQty, String marginOptionAmt, Double amount, Integer count) {
        super(userNum, marginSpotQty, marginSpotAmt, marginOptionQty, marginOptionAmt, amount, count);        
    }
   
}
