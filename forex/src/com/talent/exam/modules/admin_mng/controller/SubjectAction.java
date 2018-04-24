package com.talent.exam.modules.admin_mng.controller;

import com.talent.auth.bean.domain.Users;
import com.talent.exam.constant.ResultConst;
import com.talent.exam.domain.ExamContent;
import com.talent.exam.modules.admin_mng.model.*;
import com.talent.exam.modules.admin_mng.service.SubjectBo;
import com.talent.forex.bean.domain.AnalogueMag;
import com.talent.forex.core.ForexBaseAction;

import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 知识点维护
 * Created by 吴樟 on www.haixiangzhene.xyz
 * 2017/9/11.
 */
public class SubjectAction extends ForexBaseAction {

    private final static Logger logger=Logger.getLogger(SubjectAction.class);

    /**
     * @param examRule 规则
     */
    private ExamRule examRule;

    /**
     * @param item 封装题目
     */
    private SubjectItem item;

    /**
     * @param examId 题目编号
     */
    private Integer examId;

    /**
     * @param subjectType 0:全部交易 1:人民币 2:外币对交易 3:保证金交易
     */
    private Integer subjectType;

    private SubjectBo subjectBo;
    
    private String accType;
    
    private String accDetail;
    /**
     * 知识点维护首页
     * @return
     */
    public String initExamMng(){
    	
    	return "success";
    }
    /**
     * 转跳到题目添加页面
     * @return
     */
    public String addExamPage(){
    	requestPut("accType", accType);
    	requestPut("accDetail", accDetail);
    	String result = null;
    	if("C".equals(accType) || "W".equals(accType)){
    		if("oneclick".equals(accDetail)){
    			result = "one click";
    		}else if("stoploss".equals(accDetail)){
    			result = "stop loss";
    		}else if("takeprofit".equals(accDetail)){
    			result = "take profit";
    		}else if("marketbreakout".equals(accDetail)){
    			result = "market breakout";
    		}else if("oco".equals(accDetail)){
    			result = "oco";
    		}else if("forward".equals(accDetail)){
    			result = "forward";
    		}else if("swap".equals(accDetail)){
    			result = "swap";
    		}else{
    			result = "spot";
    		}
    	}else{
    		if("marginspot".equals(accDetail)){
    			result = "margin spot";
    		}else if("marginforward".equals(accDetail)){
    			result = "margin forward";
    		}else{
    			result = "margin option";
    		}
    	}
		return result;
    	
    }
    /**
     * 转跳到组卷首页
     * @return
     */
    public String testAssembly(){
    	return "success";
    }
    /**
     * 转跳到收发卷页面
     * @return
     */
    public String testInit(){
    	return "success";
    }
    /**
     * 转跳到成绩计算首页
     * @return
     */
    public String gradeInit(){
    	return "success";
    }

    /**
     * 根据subjectType查询
     */
    public void queryBySubjectType() throws IllegalAccessException {
        List<ExamContent> examItemLists=subjectBo.queryByExchangeType(subjectType);
        ExamResult<List<ExamContentItem>> result = new ExamResult<List<ExamContentItem>>();
        if (examItemLists!=null && !examItemLists.isEmpty()) {
            List<ExamContentItem> examContentItems = new ArrayList<ExamContentItem>();
            Iterator examItem=examItemLists.iterator();
            while (examItem.hasNext()){
                ExamContent examContent= (ExamContent) examItem.next();
                ExamContentItem examContentItem=new ExamContentItem();
                examContentItem.setExamNo(examContent.getExamNo());
                examContentItem.setAccType(examContent.getExamAccTypeByAccTypeNo().getAccTypeNo());
                try {
                    String s=new String(examContent.getExamContent(),"utf-8");
                    examContentItem.setExamContent(s);
                    logger.info(s);
                } catch (UnsupportedEncodingException e) {
                    logger.warn("字符串转换格式出错"+e.getMessage());
                    e.printStackTrace();
                }
                examContentItems.add(examContentItem);
            }
            result.setCode(1);
            result.setError("subjectType查询成功");
            result.setMessage(examContentItems);
        }else {
            result.setCode(0);
            result.setError("查询数据为空");
            result.setMessage(null);
        }
        flushJSON(result);
    }

    /**
     * 新增题目
     */
    public void addnewExchange(){
        subjectBo.addnewExchange(item);
        ExamResult<List<ExamContentItem>> result = new ExamResult<List<ExamContentItem>>();
        result.setCode(ResultConst.SUCCESS);
        result.setError("新增题目成功");
        flushJSON(result);
    }

    /**
     * 组卷
     * 1.组卷规则:
     * 考试题目类型  类型比例  题目总数
     */
    public void managePaper(){
        try {
            List<List<ExamContentResult>> exam=subjectBo.managePaper(examRule);
            ExamResult<List<List<ExamContentResult>>> examResult=new ExamResult<List<List<ExamContentResult>>>();
            examResult.setError("组卷成功");
            examResult.setCode(ResultConst.SUCCESS);
            examResult.setMessage(exam);
            flushJSON(examResult);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("组卷系统出错");
        }
    }

    /**
     * 删除题目
     * 传入参数题目id
     */
    public void deleteSubject(){
    	System.out.println(examId);
        boolean success=subjectBo.deleteById(examId);
        ExamResult examResult=new ExamResult();
        examResult.setCode(0);
        examResult.setError("删除成功");
        flushJSON(examResult);
    }
    
    public Integer getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(Integer subjectType) {
        this.subjectType = subjectType;
    }

    public SubjectBo getSubjectBo() {
        return subjectBo;
    }

    public void setSubjectBo(SubjectBo subjectBo) {
        this.subjectBo = subjectBo;
    }

    public SubjectItem getItem() {
        return item;
    }

    public void setItem(SubjectItem item) {
        this.item = item;
    }

    public ExamRule getExamRule() {
        return examRule;
    }

    public void setExamRule(ExamRule examRule) {
        this.examRule = examRule;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public String getAccDetail() {
		return accDetail;
	}

	public void setAccDetail(String accDetail) {
		this.accDetail = accDetail;
	}
}
