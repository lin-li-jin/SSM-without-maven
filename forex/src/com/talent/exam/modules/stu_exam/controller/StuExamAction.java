package com.talent.exam.modules.stu_exam.controller;

import com.talent.exam.constant.ResultConst;
import com.talent.exam.modules.admin_mng.model.ExamResult;
import com.talent.exam.modules.stu_exam.service.StuExamBo;
import com.talent.forex.core.ForexBaseAction;

/**
 * Created by 吴樟 on www.haixiangzhene.xyz
 * 2017/9/25.
 * 1.开启答题状态
 * 2.设置答题题号
 */
public class StuExamAction extends ForexBaseAction{

    private StuExamBo stuExamBo;

    /**
    * @param subjectId 题号
     * */
    private Integer subjectId;

    /**
     * @param paperId 试卷编号
     */
    private Integer paperId;

    /**
     * 开启答题
     */
    public void startExam() throws IllegalAccessException {
        boolean success=stuExamBo.startExam(paperId);
        ExamResult result=new ExamResult();
        if (success){
            result.setCode(ResultConst.SUCCESS);
            result.setError("开始答题");
        }else {
            result.setCode(ResultConst.FAIL);
            result.setError("处于答题状态，正在进入答题中...");
        }
        flushJSON(result);
    }

    /**
     * 重做
     */
    public void retryDo(){

    }

    /**
     * 设置答题题号
     */
    public void setUpSubject() {
        ExamResult result=new ExamResult();
        try {
            String message=stuExamBo.setUpSubject(subjectId);
            result.setCode(ResultConst.SUCCESS);
            result.setError(message);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        flushJSON(result);
    }


    public StuExamBo getStuExamBo() {
        return stuExamBo;
    }

    public void setStuExamBo(StuExamBo stuExamBo) {
        this.stuExamBo = stuExamBo;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }
}
