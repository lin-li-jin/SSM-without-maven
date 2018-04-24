package com.talent.exam.domain;

/**
 * Created by ���� on www.haixiangzhene.xyz
 * 2017/9/13.
 */
public class ExamOco {
    private int examOcoNo;
    private String direction;
    private String acc;
    private String takeProfitAmount;
    private String takeProfitPrice;
    private String stopLossAmount;
    private String stopLossPrice;
    private String monitorPrice;
    private String goodFrom;
    private String goodTill;
    private int step;
    private String examScore;
    private String userType;
    private ExamContent examContentByExamNo;
    private String accTypeNo;

    public int getExamOcoNo() {
        return examOcoNo;
    }

    public void setExamOcoNo(int examOcoNo) {
        this.examOcoNo = examOcoNo;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getAcc() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc = acc;
    }

    public String getTakeProfitAmount() {
        return takeProfitAmount;
    }

    public void setTakeProfitAmount(String takeProfitAmount) {
        this.takeProfitAmount = takeProfitAmount;
    }

    public String getTakeProfitPrice() {
        return takeProfitPrice;
    }

    public void setTakeProfitPrice(String takeProfitPrice) {
        this.takeProfitPrice = takeProfitPrice;
    }

    public String getStopLossAmount() {
        return stopLossAmount;
    }

    public void setStopLossAmount(String stopLossAmount) {
        this.stopLossAmount = stopLossAmount;
    }

    public String getStopLossPrice() {
        return stopLossPrice;
    }

    public void setStopLossPrice(String stopLossPrice) {
        this.stopLossPrice = stopLossPrice;
    }

    public String getMonitorPrice() {
        return monitorPrice;
    }

    public void setMonitorPrice(String monitorPrice) {
        this.monitorPrice = monitorPrice;
    }

    public String getGoodFrom() {
        return goodFrom;
    }

    public void setGoodFrom(String goodFrom) {
        this.goodFrom = goodFrom;
    }

    public String getGoodTill() {
        return goodTill;
    }

    public void setGoodTill(String goodTill) {
        this.goodTill = goodTill;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getExamScore() {
        return examScore;
    }

    public void setExamScore(String examScore) {
        this.examScore = examScore;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExamOco examOco = (ExamOco) o;

        if (examOcoNo != examOco.examOcoNo) return false;
        if (step != examOco.step) return false;
        if (direction != null ? !direction.equals(examOco.direction) : examOco.direction != null) return false;
        if (acc != null ? !acc.equals(examOco.acc) : examOco.acc != null) return false;
        if (takeProfitAmount != null ? !takeProfitAmount.equals(examOco.takeProfitAmount) : examOco.takeProfitAmount != null)
            return false;
        if (takeProfitPrice != null ? !takeProfitPrice.equals(examOco.takeProfitPrice) : examOco.takeProfitPrice != null)
            return false;
        if (stopLossAmount != null ? !stopLossAmount.equals(examOco.stopLossAmount) : examOco.stopLossAmount != null)
            return false;
        if (stopLossPrice != null ? !stopLossPrice.equals(examOco.stopLossPrice) : examOco.stopLossPrice != null)
            return false;
        if (monitorPrice != null ? !monitorPrice.equals(examOco.monitorPrice) : examOco.monitorPrice != null)
            return false;
        if (goodFrom != null ? !goodFrom.equals(examOco.goodFrom) : examOco.goodFrom != null) return false;
        if (goodTill != null ? !goodTill.equals(examOco.goodTill) : examOco.goodTill != null) return false;
        if (examScore != null ? !examScore.equals(examOco.examScore) : examOco.examScore != null) return false;
        if (userType != null ? !userType.equals(examOco.userType) : examOco.userType != null) return false;
        if (accTypeNo != null ? !accTypeNo.equals(examOco.accTypeNo) : examOco.accTypeNo != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = examOcoNo;
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        result = 31 * result + (acc != null ? acc.hashCode() : 0);
        result = 31 * result + (takeProfitAmount != null ? takeProfitAmount.hashCode() : 0);
        result = 31 * result + (takeProfitPrice != null ? takeProfitPrice.hashCode() : 0);
        result = 31 * result + (stopLossAmount != null ? stopLossAmount.hashCode() : 0);
        result = 31 * result + (stopLossPrice != null ? stopLossPrice.hashCode() : 0);
        result = 31 * result + (monitorPrice != null ? monitorPrice.hashCode() : 0);
        result = 31 * result + (goodFrom != null ? goodFrom.hashCode() : 0);
        result = 31 * result + (goodTill != null ? goodTill.hashCode() : 0);
        result = 31 * result + step;
        result = 31 * result + (examScore != null ? examScore.hashCode() : 0);
        result = 31 * result + (userType != null ? userType.hashCode() : 0);
        result = 31 * result + (accTypeNo != null ? accTypeNo.hashCode() : 0);
        return result;
    }

    public ExamContent getExamContentByExamNo() {
        return examContentByExamNo;
    }

    public void setExamContentByExamNo(ExamContent examContentByExamNo) {
        this.examContentByExamNo = examContentByExamNo;
    }

    public String getAccTypeNo() {
        return accTypeNo;
    }

    public void setAccTypeNo(String accTypeNo) {
        this.accTypeNo = accTypeNo;
    }
}
