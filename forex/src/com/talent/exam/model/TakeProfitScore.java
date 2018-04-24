package com.talent.exam.model;

/**
 * Created by 吴樟 on www.haixiangzhene.xyz
 * 2017/9/14.
 */
public class TakeProfitScore {

    private int DirectionScore;  //买卖方向分数
    private int AccScore;  //买卖币种分数

    private int takeProfitAccMountScore;; //take profit买卖数量
    private int takeProfitPriceScore;; //take profit价格
    private int takeProfitGoodFromScore;; //take profit Good From
    private int takeProfitGoodTillScore;; //take profit Good Till


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

    public Integer getTakeProfitAccMountScore() {
        return takeProfitAccMountScore;
    }

    public void setTakeProfitAccMountScore(Integer takeProfitAccMountScore) {
        this.takeProfitAccMountScore = takeProfitAccMountScore;
    }

    public Integer getTakeProfitPriceScore() {
        return takeProfitPriceScore;
    }

    public void setTakeProfitPriceScore(Integer takeProfitPriceScore) {
        this.takeProfitPriceScore = takeProfitPriceScore;
    }

    public Integer getTakeProfitGoodFromScore() {
        return takeProfitGoodFromScore;
    }

    public void setTakeProfitGoodFromScore(Integer takeProfitGoodFromScore) {
        this.takeProfitGoodFromScore = takeProfitGoodFromScore;
    }

    public Integer getTakeProfitGoodTillScore() {
        return takeProfitGoodTillScore;
    }

    public void setTakeProfitGoodTillScore(Integer takeProfitGoodTillScore) {
        this.takeProfitGoodTillScore = takeProfitGoodTillScore;
    }
}
