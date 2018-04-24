package com.talent.exam.domain;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by ���� on www.haixiangzhene.xyz
 * 2017/9/24.
 */
public class ExamPaper {
    private int paperNo;
    private byte[] paperTitle;
    private int tId;
    private String paperExamContent;
    private Collection<ExamStuAnswer> examStuAnswersByPaperNo;

    public int getPaperNo() {
        return paperNo;
    }

    public void setPaperNo(int paperNo) {
        this.paperNo = paperNo;
    }

    public byte[] getPaperTitle() {
        return paperTitle;
    }

    public void setPaperTitle(byte[] paperTitle) {
        this.paperTitle = paperTitle;
    }

    public String getPaperExamContent() {
        return paperExamContent;
    }

    public void setPaperExamContent(String paperExamContent) {
        this.paperExamContent = paperExamContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExamPaper examPaper = (ExamPaper) o;

        if (paperNo != examPaper.paperNo) return false;
        if (tId != examPaper.tId) return false;
        if (!Arrays.equals(paperTitle, examPaper.paperTitle)) return false;
        if (paperExamContent != null ? !paperExamContent.equals(examPaper.paperExamContent) : examPaper.paperExamContent != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = paperNo;
        result = 31 * result + Arrays.hashCode(paperTitle);
        result = 31 * result + (paperExamContent != null ? paperExamContent.hashCode() : 0);
        result = 31 * result + tId;
        return result;
    }

    public Collection<ExamStuAnswer> getExamStuAnswersByPaperNo() {
        return examStuAnswersByPaperNo;
    }

    public void setExamStuAnswersByPaperNo(Collection<ExamStuAnswer> examStuAnswersByPaperNo) {
        this.examStuAnswersByPaperNo = examStuAnswersByPaperNo;
    }

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }
}
