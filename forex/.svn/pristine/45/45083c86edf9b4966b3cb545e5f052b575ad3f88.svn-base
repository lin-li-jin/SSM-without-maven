package com.talent.exam.domain;


import com.talent.auth.bean.domain.Users;


/**
 * Created by Œ‚’¡ on www.haixiangzhene.xyz
 * 2017/9/24.
 */
public class ExamStuAnswer {
    private int stuAnswerNo;
    private String paperExamStatus;
    private String stuAnswer;
    private String stuAnswerTable;
    private int paperGrade;
    private ExamPaper examPaperByPaperNo;
    private Users usersByStuId;
    private ExamPaperDistribute examPaperDistributeByPaperId;

    public int getStuAnswerNo() {
        return stuAnswerNo;
    }

    public void setStuAnswerNo(int stuAnswerNo) {
        this.stuAnswerNo = stuAnswerNo;
    }

    public String getPaperExamStatus() {
        return paperExamStatus;
    }

    public void setPaperExamStatus(String paperExamStatus) {
        this.paperExamStatus = paperExamStatus;
    }

    public String getStuAnswer() {
        return stuAnswer;
    }

    public void setStuAnswer(String stuAnswer) {
        this.stuAnswer = stuAnswer;
    }

    public String getStuAnswerTable() {
        return stuAnswerTable;
    }

    public void setStuAnswerTable(String stuAnswerTable) {
        this.stuAnswerTable = stuAnswerTable;
    }

    public int getPaperGrade() {
        return paperGrade;
    }

    public void setPaperGrade(int paperGrade) {
        this.paperGrade = paperGrade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExamStuAnswer that = (ExamStuAnswer) o;

        if (stuAnswerNo != that.stuAnswerNo) return false;
        if (paperGrade != that.paperGrade) return false;
        if (paperExamStatus != null ? !paperExamStatus.equals(that.paperExamStatus) : that.paperExamStatus != null)
            return false;
        if (stuAnswer != null ? !stuAnswer.equals(that.stuAnswer) : that.stuAnswer != null) return false;
        if (stuAnswerTable != null ? !stuAnswerTable.equals(that.stuAnswerTable) : that.stuAnswerTable != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = stuAnswerNo;
        result = 31 * result + (paperExamStatus != null ? paperExamStatus.hashCode() : 0);
        result = 31 * result + (stuAnswer != null ? stuAnswer.hashCode() : 0);
        result = 31 * result + (stuAnswerTable != null ? stuAnswerTable.hashCode() : 0);
        result = 31 * result + paperGrade;
        return result;
    }

    public ExamPaper getExamPaperByPaperNo() {
        return examPaperByPaperNo;
    }

    public void setExamPaperByPaperNo(ExamPaper examPaperByPaperNo) {
        this.examPaperByPaperNo = examPaperByPaperNo;
    }


    public Users getUsersByStuId() {
        return usersByStuId;
    }

    public void setUsersByStuId(Users usersByStuId) {
        this.usersByStuId = usersByStuId;
    }

    public ExamPaperDistribute getExamPaperDistributeByPaperId() {
        return examPaperDistributeByPaperId;
    }

    public void setExamPaperDistributeByPaperId(ExamPaperDistribute examPaperDistributeByPaperId) {
        this.examPaperDistributeByPaperId = examPaperDistributeByPaperId;
    }
}
