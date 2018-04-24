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
 * Created by 吴樟 on www.haixiangzhene.xyz
 * 2017/9/24.
 * 1.分配试卷
 */
public class ExamAction extends ForexBaseAction{

    private static Logger logger=Logger.getLogger(ExamAction.class);
    /**
     * @param pageId 标明某次考试
     */
    private Integer pageId;

    /**
     * @param examPaperMessage 分配试卷信息
     */
    private ExamPaperMessage examPaperMessage;

    private ExamBo examBo;
    
    /**
     * 转跳到收发卷页面
     * @return
     */
    public String testInit(){
    	return "success";
    }


    /**
     * 分发试卷
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
     * 查看可分配试卷
     */
    public void queryDistributedPaper(){
        List<PaperResult> paperResults =examBo.queryDistributedPaper();
        ExamResult<List<PaperResult>> examResult=new ExamResult<List<PaperResult>>();
        if (paperResults!=null && paperResults.isEmpty()){
            examResult.setCode(ResultConst.EMPTY);
            examResult.setError("暂未查询到试卷");
        }else {
            examResult.setCode(ResultConst.SUCCESS);
            examResult.setError("检索到试卷:"+paperResults.size());
            examResult.setMessage(paperResults);
        }
        flushJSON(examResult);
    }

    /**
     * 查看答题情况
     */
    public void queryPageStatus(){
        ExamResult<Collection<StudentExamStatus>> examResult=
                new ExamResult<Collection<StudentExamStatus>>();
        try {
            Collection<StudentExamStatus> studentExamStatuses=
                    examBo.queryExamSubmissionStatus(pageId);
            examResult.setMessage(studentExamStatuses);
            examResult.setCode(ResultConst.SUCCESS);
            examResult.setError("检索到学生本次的考试的答题情况");
        } catch (IllegalAccessException e) {
            logger.error("检索答题情况:"+e.getMessage());
            e.printStackTrace();
            examResult.setError("检索学生答题情况出现故障");
            examResult.setCode(ResultConst.FAIL);
        }finally {
            flushJSON(examResult);
        }
    }

    /**
     * 结算成绩
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
