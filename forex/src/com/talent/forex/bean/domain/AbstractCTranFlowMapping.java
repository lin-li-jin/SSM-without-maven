package com.talent.forex.bean.domain;



/**
 * AbstractCTranFlowMapping entity provides the base persistence definition of the CTranFlowMapping entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractCTranFlowMapping  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String userNum;
     private String oneClickQty;
     private String oneClickAmt;
     private String stopLossQty;
     private String stopLossAmt;
     private String takeProfitQty;
     private String takeProfitAmt;
     private String ocoQty;
     private String ocoAmt;
     private String marketBreakoutQty;
     private String marketBreakoutAmt;
     private String otcSpotQty;
     private String otcSpotAmt;
     private String otcForwartQty;
     private String otcForwartAmt;
     private String otcSwapQty;
     private String otcSwapAmt;
     private Double amount;
     private Integer count;


    // Constructors

    /** default constructor */
    public AbstractCTranFlowMapping() {
    }

	/** minimal constructor */
    public AbstractCTranFlowMapping(String userNum) {
        this.userNum = userNum;
    }
    
    /** full constructor */
    public AbstractCTranFlowMapping(String userNum, String oneClickQty, String oneClickAmt, String stopLossQty, String stopLossAmt, String takeProfitQty, String takeProfitAmt, String ocoQty, String ocoAmt, String marketBreakoutQty, String marketBreakoutAmt, String otcSpotQty, String otcSpotAmt, String otcForwartQty, String otcForwartAmt, String otcSwapQty, String otcSwapAmt, Double amount, Integer count) {
        this.userNum = userNum;
        this.oneClickQty = oneClickQty;
        this.oneClickAmt = oneClickAmt;
        this.stopLossQty = stopLossQty;
        this.stopLossAmt = stopLossAmt;
        this.takeProfitQty = takeProfitQty;
        this.takeProfitAmt = takeProfitAmt;
        this.ocoQty = ocoQty;
        this.ocoAmt = ocoAmt;
        this.marketBreakoutQty = marketBreakoutQty;
        this.marketBreakoutAmt = marketBreakoutAmt;
        this.otcSpotQty = otcSpotQty;
        this.otcSpotAmt = otcSpotAmt;
        this.otcForwartQty = otcForwartQty;
        this.otcForwartAmt = otcForwartAmt;
        this.otcSwapQty = otcSwapQty;
        this.otcSwapAmt = otcSwapAmt;
        this.amount = amount;
        this.count = count;
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

    public String getOneClickQty() {
        return this.oneClickQty;
    }
    
    public void setOneClickQty(String oneClickQty) {
        this.oneClickQty = oneClickQty;
    }

    public String getOneClickAmt() {
        return this.oneClickAmt;
    }
    
    public void setOneClickAmt(String oneClickAmt) {
        this.oneClickAmt = oneClickAmt;
    }

    public String getStopLossQty() {
        return this.stopLossQty;
    }
    
    public void setStopLossQty(String stopLossQty) {
        this.stopLossQty = stopLossQty;
    }

    public String getStopLossAmt() {
        return this.stopLossAmt;
    }
    
    public void setStopLossAmt(String stopLossAmt) {
        this.stopLossAmt = stopLossAmt;
    }

    public String getTakeProfitQty() {
        return this.takeProfitQty;
    }
    
    public void setTakeProfitQty(String takeProfitQty) {
        this.takeProfitQty = takeProfitQty;
    }

    public String getTakeProfitAmt() {
        return this.takeProfitAmt;
    }
    
    public void setTakeProfitAmt(String takeProfitAmt) {
        this.takeProfitAmt = takeProfitAmt;
    }

    public String getOcoQty() {
        return this.ocoQty;
    }
    
    public void setOcoQty(String ocoQty) {
        this.ocoQty = ocoQty;
    }

    public String getOcoAmt() {
        return this.ocoAmt;
    }
    
    public void setOcoAmt(String ocoAmt) {
        this.ocoAmt = ocoAmt;
    }

    public String getMarketBreakoutQty() {
        return this.marketBreakoutQty;
    }
    
    public void setMarketBreakoutQty(String marketBreakoutQty) {
        this.marketBreakoutQty = marketBreakoutQty;
    }

    public String getMarketBreakoutAmt() {
        return this.marketBreakoutAmt;
    }
    
    public void setMarketBreakoutAmt(String marketBreakoutAmt) {
        this.marketBreakoutAmt = marketBreakoutAmt;
    }

    public String getOtcSpotQty() {
        return this.otcSpotQty;
    }
    
    public void setOtcSpotQty(String otcSpotQty) {
        this.otcSpotQty = otcSpotQty;
    }

    public String getOtcSpotAmt() {
        return this.otcSpotAmt;
    }
    
    public void setOtcSpotAmt(String otcSpotAmt) {
        this.otcSpotAmt = otcSpotAmt;
    }

    public String getOtcForwartQty() {
        return this.otcForwartQty;
    }
    
    public void setOtcForwartQty(String otcForwartQty) {
        this.otcForwartQty = otcForwartQty;
    }

    public String getOtcForwartAmt() {
        return this.otcForwartAmt;
    }
    
    public void setOtcForwartAmt(String otcForwartAmt) {
        this.otcForwartAmt = otcForwartAmt;
    }

    public String getOtcSwapQty() {
        return this.otcSwapQty;
    }
    
    public void setOtcSwapQty(String otcSwapQty) {
        this.otcSwapQty = otcSwapQty;
    }

    public String getOtcSwapAmt() {
        return this.otcSwapAmt;
    }
    
    public void setOtcSwapAmt(String otcSwapAmt) {
        this.otcSwapAmt = otcSwapAmt;
    }

    public Double getAmount() {
        return this.amount;
    }
    
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getCount() {
        return this.count;
    }
    
    public void setCount(Integer count) {
        this.count = count;
    }
   








}