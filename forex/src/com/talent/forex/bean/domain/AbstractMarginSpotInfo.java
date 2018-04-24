package com.talent.forex.bean.domain;

/**
 * Created by Œ‚’¡ on www.haixiangzhene.xyz
 * 2017/8/28.
 */
public class AbstractMarginSpotInfo {




    private Integer id;
    private String tranType;
    private String tranNo;
    private String userNum;
    private String weCcy;
    private String anaCcy;
    private String account;
    private String accAmount;
    private String dealAmt;
    private String price;
    private String statue;
    private String createDatatime;
    private String direction;
    private String accrual;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType;
    }

    public String getTranNo() {
        return tranNo;
    }

    public void setTranNo(String tranNo) {
        this.tranNo = tranNo;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getWeCcy() {
        return weCcy;
    }

    public void setWeCcy(String weCcy) {
        this.weCcy = weCcy;
    }

    public String getAnaCcy() {
        return anaCcy;
    }

    public void setAnaCcy(String anaCcy) {
        this.anaCcy = anaCcy;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccAmount() {
        return accAmount;
    }

    public void setAccAmount(String accAmount) {
        this.accAmount = accAmount;
    }

    public String getDealAmt() {
        return dealAmt;
    }

    public void setDealAmt(String dealAmt) {
        this.dealAmt = dealAmt;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatue() {
        return statue;
    }

    public void setStatue(String statue) {
        this.statue = statue;
    }

    public String getCreateDatatime() {
        return createDatatime;
    }

    public void setCreateDatatime(String createDatatime) {
        this.createDatatime = createDatatime;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getAccrual() {
        return accrual;
    }

    public void setAccrual(String accrual) {
        this.accrual = accrual;
    }
}
