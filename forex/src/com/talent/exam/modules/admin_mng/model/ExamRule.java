package com.talent.exam.modules.admin_mng.model;


import java.util.HashMap;

/**
 * ������
 * Created by ���� on www.haixiangzhene.xyz
 * 2017/9/14.
 */
public class ExamRule {

    //��ʽ���� "C",3
    private HashMap<String,String[]> titleScale;
    private Integer titleSum;
    private String paperTitle;

    public HashMap<String, String[]> getTitleScale() {
        return titleScale;
    }

    public void setTitleScale(HashMap<String, String[]> titleScale) {
        this.titleScale = titleScale;
    }

    public Integer getTitleSum() {
        return titleSum;
    }

    public void setTitleSum(Integer titleSum) {
        this.titleSum = titleSum;
    }

    public String getPaperTitle() {
        return paperTitle;
    }

    public void setPaperTitle(String paperTitle) {
        this.paperTitle = paperTitle;
    }
}
