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
 * ֪ʶ��ά��
 * Created by ���� on www.haixiangzhene.xyz
 * 2017/9/11.
 */
public class SubjectAction extends ForexBaseAction {

    private final static Logger logger=Logger.getLogger(SubjectAction.class);

    /**
     * @param examRule ����
     */
    private ExamRule examRule;

    /**
     * @param item ��װ��Ŀ
     */
    private SubjectItem item;

    /**
     * @param examId ��Ŀ���
     */
    private Integer examId;

    /**
     * @param subjectType 0:ȫ������ 1:����� 2:��ҶԽ��� 3:��֤����
     */
    private Integer subjectType;

    private SubjectBo subjectBo;
    
    private String accType;
    
    private String accDetail;
    /**
     * ֪ʶ��ά����ҳ
     * @return
     */
    public String initExamMng(){
    	
    	return "success";
    }
    /**
     * ת������Ŀ���ҳ��
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
     * ת���������ҳ
     * @return
     */
    public String testAssembly(){
    	return "success";
    }
    /**
     * ת�����շ���ҳ��
     * @return
     */
    public String testInit(){
    	return "success";
    }
    /**
     * ת�����ɼ�������ҳ
     * @return
     */
    public String gradeInit(){
    	return "success";
    }

    /**
     * ����subjectType��ѯ
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
                    logger.warn("�ַ���ת����ʽ����"+e.getMessage());
                    e.printStackTrace();
                }
                examContentItems.add(examContentItem);
            }
            result.setCode(1);
            result.setError("subjectType��ѯ�ɹ�");
            result.setMessage(examContentItems);
        }else {
            result.setCode(0);
            result.setError("��ѯ����Ϊ��");
            result.setMessage(null);
        }
        flushJSON(result);
    }

    /**
     * ������Ŀ
     */
    public void addnewExchange(){
        subjectBo.addnewExchange(item);
        ExamResult<List<ExamContentItem>> result = new ExamResult<List<ExamContentItem>>();
        result.setCode(ResultConst.SUCCESS);
        result.setError("������Ŀ�ɹ�");
        flushJSON(result);
    }

    /**
     * ���
     * 1.������:
     * ������Ŀ����  ���ͱ���  ��Ŀ����
     */
    public void managePaper(){
        try {
            List<List<ExamContentResult>> exam=subjectBo.managePaper(examRule);
            ExamResult<List<List<ExamContentResult>>> examResult=new ExamResult<List<List<ExamContentResult>>>();
            examResult.setError("���ɹ�");
            examResult.setCode(ResultConst.SUCCESS);
            examResult.setMessage(exam);
            flushJSON(examResult);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("���ϵͳ����");
        }
    }

    /**
     * ɾ����Ŀ
     * ���������Ŀid
     */
    public void deleteSubject(){
    	System.out.println(examId);
        boolean success=subjectBo.deleteById(examId);
        ExamResult examResult=new ExamResult();
        examResult.setCode(0);
        examResult.setError("ɾ���ɹ�");
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
