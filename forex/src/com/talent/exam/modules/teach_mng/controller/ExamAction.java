package com.talent.exam.modules.teach_mng.controller;

import com.talent.exam.constant.ResultConst;
import com.talent.exam.modules.admin_mng.model.ExamResult;
import com.talent.exam.modules.teach_mng.model.DistributeMessage;
import com.talent.exam.modules.teach_mng.model.ExamPaperMessage;
import com.talent.exam.modules.teach_mng.model.PaperResult;
import com.talent.exam.modules.teach_mng.model.StudentExamStatus;
import com.talent.exam.modules.teach_mng.service.ExamBo;
import com.talent.forex.core.ForexBaseAction;
import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.List;

/**
 * Created by ���� on www.haixiangzhene.xyz
 * 2017/9/24.
 * 1.�����Ծ�
 */
public class ExamAction extends ForexBaseAction{

    private static Logger logger=Logger.getLogger(ExamAction.class);
    /**
     * @param pageId ����ĳ�ο���
     */
    private Integer pageId;

    /**
     * @param examPaperMessage �����Ծ���Ϣ
     */
    private ExamPaperMessage examPaperMessage;

    private ExamBo examBo;
    
    /**
     * ת�����շ���ҳ��
     * @return
     */
    public String testInit(){
    	return "success";
    }


    /**
     * �ַ��Ծ�
     * @param examPaperMessage
     */
    public void distributePaper(){
        ExamResult<String> result=new ExamResult<String>();
        DistributeMessage message=examBo.distributePaper(examPaperMessage);
        result.setError(message.getMessage());
        result.setCode(message.getCode());
        flushJSON(result);
    }

    /**
     * �鿴�ɷ����Ծ�
     */
    public void queryDistributedPaper(){
        List<PaperResult> paperResults =examBo.queryDistributedPaper();
        ExamResult<List<PaperResult>> examResult=new ExamResult<List<PaperResult>>();
        if (paperResults!=null && paperResults.isEmpty()){
            examResult.setCode(ResultConst.EMPTY);
            examResult.setError("��δ��ѯ���Ծ�");
        }else {
            examResult.setCode(ResultConst.SUCCESS);
            examResult.setError("�������Ծ�:"+paperResults.size());
            examResult.setMessage(paperResults);
        }
        flushJSON(examResult);
    }

    /**
     * �鿴�������
     */
    public void queryPageStatus(){
        ExamResult<Collection<StudentExamStatus>> examResult=
                new ExamResult<Collection<StudentExamStatus>>();
        try {
            Collection<StudentExamStatus> studentExamStatuses=
                    examBo.queryExamSubmissionStatus(pageId);
            examResult.setMessage(studentExamStatuses);
            examResult.setCode(ResultConst.SUCCESS);
            examResult.setError("������ѧ�����εĿ��ԵĴ������");
        } catch (IllegalAccessException e) {
            logger.error("�����������:"+e.getMessage());
            e.printStackTrace();
            examResult.setError("����ѧ������������ֹ���");
            examResult.setCode(ResultConst.FAIL);
        }finally {
            flushJSON(examResult);
        }
    }

    /**
     * ����ɼ�
     */


    public ExamPaperMessage getExamPaperMessage() {
        return examPaperMessage;
    }

    public void setExamPaperMessage(ExamPaperMessage examPaperMessage) {
        this.examPaperMessage = examPaperMessage;
    }

    public ExamBo getExamBo() {
        return examBo;
    }

    public void setExamBo(ExamBo examBo) {
        this.examBo = examBo;
    }


}
