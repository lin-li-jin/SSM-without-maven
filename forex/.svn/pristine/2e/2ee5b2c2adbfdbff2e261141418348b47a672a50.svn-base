package com.talent.exam.domain;

/**
 * Created by Œ‚’¡ on www.haixiangzhene.xyz
 * 2017/9/13.
 */
public class ExamSpot {
    private int examSpotNo;
    private String accTypeNo;
    private String direction;
    private String acc;
    private String accAmount;
    private String price;
    private int providerNo;
    private int step;
    private String examScore;
    private String userType;
    private ExamContent examContentByExamNo;

    public int getExamSpotNo() {
        return examSpotNo;
    }

    public void setExamSpotNo(int examSpotNo) {
        this.examSpotNo = examSpotNo;
    }

    public String getAccTypeNo() {
        return accTypeNo;
    }

    public void setAccTypeNo(String accTypeNo) {
        this.accTypeNo = accTypeNo;
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

    public String getAccAmount() {
        return accAmount;
    }

    public void setAccAmount(String accAmount) {
        this.accAmount = accAmount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getProviderNo() {
        return providerNo;
    }

    public void setProviderNo(int providerNo) {
        this.providerNo = providerNo;
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

        ExamSpot examSpot = (ExamSpot) o;

        if (examSpotNo != examSpot.examSpotNo) return false;
        if (providerNo != examSpot.providerNo) return false;
        if (step != examSpot.step) return false;
        if (accTypeNo != null ? !accTypeNo.equals(examSpot.accTypeNo) : examSpot.accTypeNo != null) return false;
        if (direction != null ? !direction.equals(examSpot.direction) : examSpot.direction != null) return false;
        if (acc != null ? !acc.equals(examSpot.acc) : examSpot.acc != null) return false;
        if (accAmount != null ? !accAmount.equals(examSpot.accAmount) : examSpot.accAmount != null) return false;
        if (price != null ? !price.equals(examSpot.price) : examSpot.price != null) return false;
        if (examScore != null ? !examScore.equals(examSpot.examScore) : examSpot.examScore != null) return false;
        if (userType != null ? !userType.equals(examSpot.userType) : examSpot.userType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = examSpotNo;
        result = 31 * result + (accTypeNo != null ? accTypeNo.hashCode() : 0);
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        result = 31 * result + (acc != null ? acc.hashCode() : 0);
        result = 31 * result + (accAmount != null ? accAmount.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + providerNo;
        result = 31 * result + step;
        result = 31 * result + (examScore != null ? examScore.hashCode() : 0);
        result = 31 * result + (userType != null ? userType.hashCode() : 0);
        return result;
    }

    public ExamContent getExamContentByExamNo() {
        return examContentByExamNo;
    }

    public void setExamContentByExamNo(ExamContent examContentByExamNo) {
        this.examContentByExamNo = examContentByExamNo;
    }
}
