package com.talent.exam.domain;


/**
 * Created by ���� on www.haixiangzhene.xyz
 * 2017/9/24.
 */
public class ExamSwap {
    private int examSwapNo;
    private String accTypeNo;
    private int examNo;
    private String direction;
    private String acc;
    private String accAmount;
    private String spot;
    private String startDate;
    private String maturityDate;
    private String fixedType;
    private int fixedRate;
    private int cBasis;
    private int fBasis;
    private int frequency;
    private int libor;
    private int providerNo;
    private int point;
    private int step;
    private String examScore;
    private String userType;
    private ExamContent examContentByExamNo;

    public int getExamSwapNo() {
        return examSwapNo;
    }

    public void setExamSwapNo(int examSwapNo) {
        this.examSwapNo = examSwapNo;
    }

    public String getAccTypeNo() {
        return accTypeNo;
    }

    public void setAccTypeNo(String accTypeNo) {
        this.accTypeNo = accTypeNo;
    }

    public int getExamNo() {
        return examNo;
    }

    public void setExamNo(int examNo) {
        this.examNo = examNo;
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

    public String getSpot() {
        return spot;
    }

    public void setSpot(String spot) {
        this.spot = spot;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(String maturityDate) {
        this.maturityDate = maturityDate;
    }

    public String getFixedType() {
        return fixedType;
    }

    public void setFixedType(String fixedType) {
        this.fixedType = fixedType;
    }

    public int getFixedRate() {
        return fixedRate;
    }

    public void setFixedRate(int fixedRate) {
        this.fixedRate = fixedRate;
    }

    public int getcBasis() {
        return cBasis;
    }

    public void setcBasis(int cBasis) {
        this.cBasis = cBasis;
    }

    public int getfBasis() {
        return fBasis;
    }

    public void setfBasis(int fBasis) {
        this.fBasis = fBasis;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getLibor() {
        return libor;
    }

    public void setLibor(int libor) {
        this.libor = libor;
    }

    public int getProviderNo() {
        return providerNo;
    }

    public void setProviderNo(int providerNo) {
        this.providerNo = providerNo;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
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

        ExamSwap examSwap = (ExamSwap) o;

        if (examSwapNo != examSwap.examSwapNo) return false;
        if (examNo != examSwap.examNo) return false;
        if (fixedRate != examSwap.fixedRate) return false;
        if (cBasis != examSwap.cBasis) return false;
        if (fBasis != examSwap.fBasis) return false;
        if (frequency != examSwap.frequency) return false;
        if (libor != examSwap.libor) return false;
        if (providerNo != examSwap.providerNo) return false;
        if (point != examSwap.point) return false;
        if (step != examSwap.step) return false;
        if (accTypeNo != null ? !accTypeNo.equals(examSwap.accTypeNo) : examSwap.accTypeNo != null) return false;
        if (direction != null ? !direction.equals(examSwap.direction) : examSwap.direction != null) return false;
        if (acc != null ? !acc.equals(examSwap.acc) : examSwap.acc != null) return false;
        if (accAmount != null ? !accAmount.equals(examSwap.accAmount) : examSwap.accAmount != null) return false;
        if (spot != null ? !spot.equals(examSwap.spot) : examSwap.spot != null) return false;
        if (startDate != null ? !startDate.equals(examSwap.startDate) : examSwap.startDate != null) return false;
        if (maturityDate != null ? !maturityDate.equals(examSwap.maturityDate) : examSwap.maturityDate != null)
            return false;
        if (fixedType != null ? !fixedType.equals(examSwap.fixedType) : examSwap.fixedType != null) return false;
        if (examScore != null ? !examScore.equals(examSwap.examScore) : examSwap.examScore != null) return false;
        if (userType != null ? !userType.equals(examSwap.userType) : examSwap.userType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = examSwapNo;
        result = 31 * result + (accTypeNo != null ? accTypeNo.hashCode() : 0);
        result = 31 * result + examNo;
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        result = 31 * result + (acc != null ? acc.hashCode() : 0);
        result = 31 * result + (accAmount != null ? accAmount.hashCode() : 0);
        result = 31 * result + (spot != null ? spot.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (maturityDate != null ? maturityDate.hashCode() : 0);
        result = 31 * result + (fixedType != null ? fixedType.hashCode() : 0);
        result = 31 * result + fixedRate;
        result = 31 * result + cBasis;
        result = 31 * result + fBasis;
        result = 31 * result + frequency;
        result = 31 * result + libor;
        result = 31 * result + providerNo;
        result = 31 * result + point;
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
