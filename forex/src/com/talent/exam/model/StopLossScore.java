package com.talent.exam.model;

/**
 * Created by 吴樟 on www.haixiangzhene.xyz
 * 2017/9/14.
 */
public class StopLossScore {

    private int DirectionScore;  //买卖方向分数
    private int AccScore;  //买卖币种分数

    private int stopLossAccMountScore; //stop loss买卖数量

    private int stopLossAccmonitorPriceScore;  //模拟类型
    private int stopLossGoodFromScore; //stop loss Good From
    private int stopLossGoodTillScore; //stop loss Good Till
    private int stopLossPriceScore; //stop loss价格

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

    public Integer getStopLossAccMountScore() {
        return stopLossAccMountScore;
    }

    public void setStopLossAccMountScore(Integer stopLossAccMountScore) {
        this.stopLossAccMountScore = stopLossAccMountScore;
    }

    public Integer getStopLossPriceScore() {
        return stopLossPriceScore;
    }

    public void setStopLossPriceScore(Integer stopLossPriceScore) {
        this.stopLossPriceScore = stopLossPriceScore;
    }

    public Integer getStopLossAccmonitorPriceScore() {
        return stopLossAccmonitorPriceScore;
    }

    public void setStopLossAccmonitorPriceScore(Integer stopLossAccmonitorPriceScore) {
        this.stopLossAccmonitorPriceScore = stopLossAccmonitorPriceScore;
    }

    public Integer getStopLossGoodFromScore() {
        return stopLossGoodFromScore;
    }

    public void setStopLossGoodFromScore(Integer stopLossGoodFromScore) {
        this.stopLossGoodFromScore = stopLossGoodFromScore;
    }

    public Integer getStopLossGoodTillScore() {
        return stopLossGoodTillScore;
    }

    public void setStopLossGoodTillScore(Integer stopLossGoodTillScore) {
        this.stopLossGoodTillScore = stopLossGoodTillScore;
    }
}
