package com.talent.exam.model;

/**
 * Created by 吴樟 on www.haixiangzhene.xyz
 * 2017/9/14.
 */
public class OcoScore {

    private int DirectionScore;  //买卖方向分数
    private int AccScore;  //买卖币种分数

    private int takeProfitAmountScore;
    private int takeProfitPriceScore;
    private int stopLossAmountScore;
    private int stopLossPriceScore;
    private int monitorPriceScore;
    private int goodFromScore;
    private int goodTillScore;

    public Integer getDirectionScore() {
        return DirectionScore;
    }

    public void setDirectionScore(Integer directionScore) {
        DirectionScore = directionScore;
    }

    public Integer getAccScore() {
        return AccScore;
    }

    public void setAccScore(Integer accScore) {
        AccScore = accScore;
    }

    public Integer getTakeProfitAmountScore() {
        return takeProfitAmountScore;
    }

    public void setTakeProfitAmountScore(Integer takeProfitAmountScore) {
        this.takeProfitAmountScore = takeProfitAmountScore;
    }

    public Integer getTakeProfitPriceScore() {
        return takeProfitPriceScore;
    }

    public void setTakeProfitPriceScore(Integer takeProfitPriceScore) {
        this.takeProfitPriceScore = takeProfitPriceScore;
    }

    public Integer getStopLossAmountScore() {
        return stopLossAmountScore;
    }

    public void setStopLossAmountScore(Integer stopLossAmountScore) {
        this.stopLossAmountScore = stopLossAmountScore;
    }

    public Integer getStopLossPriceScore() {
        return stopLossPriceScore;
    }

    public void setStopLossPriceScore(Integer stopLossPriceScore) {
        this.stopLossPriceScore = stopLossPriceScore;
    }

    public Integer getMonitorPriceScore() {
        return monitorPriceScore;
    }

    public void setMonitorPriceScore(Integer monitorPriceScore) {
        this.monitorPriceScore = monitorPriceScore;
    }

    public Integer getGoodFromScore() {
        return goodFromScore;
    }

    public void setGoodFromScore(Integer goodFromScore) {
        this.goodFromScore = goodFromScore;
    }

    public Integer getGoodTillScore() {
        return goodTillScore;
    }

    public void setGoodTillScore(Integer goodTillScore) {
        this.goodTillScore = goodTillScore;
    }
}
