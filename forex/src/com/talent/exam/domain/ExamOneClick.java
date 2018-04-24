package com.talent.exam.domain;

/**
 * Created by Œ‚’¡ on www.haixiangzhene.xyz
 * 2017/9/13.
 */
public class ExamOneClick {
    private int examOneClickNo;
    private String accTypeNo;
    private String direction;
    private String acc;
    private String accAmount;
    private int step;
    private String examScore;
    private String userType;
    private ExamContent examContentByExamNo;

    public int getExamOneClickNo() {
        return examOneClickNo;
    }

    public void setExamOneClickNo(int examOneClickNo) {
        this.examOneClickNo = examOneClickNo;
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

        ExamOneClick that = (ExamOneClick) o;

        if (examOneClickNo != that.examOneClickNo) return false;
        if (step != that.step) return false;
        if (accTypeNo != null ? !accTypeNo.equals(that.accTypeNo) : that.accTypeNo != null) return false;
        if (direction != null ? !direction.equals(that.direction) : that.direction != null) return false;
        if (acc != null ? !acc.equals(that.acc) : that.acc != null) return false;
        if (accAmount != null ? !accAmount.equals(that.accAmount) : that.accAmount != null) return false;
        if (examScore != null ? !examScore.equals(that.examScore) : that.examScore != null) return false;
        if (userType != null ? !userType.equals(that.userType) : that.userType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = examOneClickNo;
        result = 31 * result + (accTypeNo != null ? accTypeNo.hashCode() : 0);
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        result = 31 * result + (acc != null ? acc.hashCode() : 0);
        result = 31 * result + (accAmount != null ? accAmount.hashCode() : 0);
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
