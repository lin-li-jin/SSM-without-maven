package com.talent.exam.modules.stu_exam.controller;

import com.talent.exam.constant.ResultConst;
import com.talent.exam.modules.admin_mng.model.ExamResult;
import com.talent.exam.modules.stu_exam.service.StuExamBo;
import com.talent.forex.core.ForexBaseAction;

/**
 * Created by ���� on www.haixiangzhene.xyz
 * 2017/9/25.
 * 1.��������״̬
 * 2.���ô������
 */
public class StuExamAction extends ForexBaseAction{

    private StuExamBo stuExamBo;

    /**
    * @param subjectId ���
     * */
    private Integer subjectId;

    /**
     * @param paperId �Ծ����
     */
    private Integer paperId;

    /**
     * ��������
     */
    public void startExam() throws IllegalAccessException {
        boolean success=stuExamBo.startExam(paperId);
        ExamResult result=new ExamResult();
        if (success){
            result.setCode(ResultConst.SUCCESS);
            result.setError("��ʼ����");
        }else {
            result.setCode(ResultConst.FAIL);
            result.setError("���ڴ���״̬�����ڽ��������...");
        }
        flushJSON(result);
    }

    /**
     * ����
     */
    public void retryDo(){

    }

    /**
     * ���ô������
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