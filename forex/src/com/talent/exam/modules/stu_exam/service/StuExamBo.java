package com.talent.exam.modules.stu_exam.service;

import com.talent.auth.bean.domain.TClass;
import com.talent.auth.bean.model.UserModel;
import com.talent.auth.dao.TClassDao;
import com.talent.auth.login.LoginConst;
import com.talent.base.BaseBo;
import com.talent.exam.constant.ExamConst;
import com.talent.exam.dao.*;
import com.talent.exam.domain.*;
import com.talent.exam.modules.stu_exam.model.ListenModelConfig;
import com.talent.exam.util.StringUtil;
import com.talent.forex.bean.model.StopAndProfitModel;
import com.talent.forex.util.GetDateTimeUtil;
import com.talent.hibernate.util.HibernateUtil;
import com.talent.tools.WebWorkUtil;
import org.hibernate.criterion.MatchMode;

import java.util.List;

/**
 * Created by 吴樟 on www.haixiangzhene.xyz
 * 2017/9/25.
 */
public class StuExamBo extends BaseBo {

    private ExamStopLossDAO examStopLossDAO;
    private ExamForwardDAO examForwardDAO;
    private ExamStuAnswerDAO examStuAnswerDAO;
    private ExamTakeProfitDAO examTakeProfitDAO;
    private ExamOcoDAO examOcoDAO;
    private ExamMarketBreakoutDAO examMarketBreakoutDAO;
    private ExamOneClickDAO examOneClickDAO;
    private ExamSpotDAO examSpotDAO;
    private ExamSwapDAO examSwapDAO;

    private ExamPaperDAO examPaperDAO;
    private TClassDao tClassDao;

    private static StringUtil stringUtil=new StringUtil();

    private ExamPaperDistributeDAO examPaperDistributeDAO;

    /**
     * 开始答题
     * 1.答题状态是否已经开启
     */
    public boolean startExam(Integer paperId) throws IllegalAccessException {
        if(!setUpPaper(paperId)){
            return false;
        }
        Object obj=WebWorkUtil.sessionGet("ListenExamConfig");
        if (obj!=null && obj instanceof ListenModelConfig){ //已经设置过
            ListenModelConfig config= (ListenModelConfig) obj;
            if (config.isOnAnswer()){ //正在考试状态
                return false;
            }else { //已经完成考试,则重新开始考试
                config.setPaperId(paperId);
                config.setOnAnswer(true);
                config.setSubjectId(-1);
                return true;
            }
        }else { //暂未设置过,则直接设置
            ListenModelConfig config=new ListenModelConfig();
            config.setOnAnswer(true);
            config.setPaperId(paperId);
            config.setSubjectId(-1);
            WebWorkUtil.sessionPut("ListenExamConfig",config);
            return true;
        }
    }

    /**
     * 1.判断是否已被分配试卷
     */
    public boolean setUpPaper(Integer paperId) throws IllegalAccessException {
        ExamPaperDistribute examPaperDistribute=new ExamPaperDistribute();
        TClass tClass=new TClass();
        UserModel userModel = (UserModel)WebWorkUtil.sessionGet(LoginConst.LOGIN_USER_MODEL);
        tClass.setClassNo(userModel.getClassNo());
        tClass=tClassDao.getBeanByBean(tClass, MatchMode.EXACT);
        if (tClass==null)
            throw new NullPointerException("the class is not exits");
        examPaperDistribute.setPaperNo(paperId);
        examPaperDistribute.settClassByClassId(tClass);
        examPaperDistribute.setClassPaperStatus(ExamConst.USE);
        List<ExamPaperDistribute> examPaper=examPaperDistributeDAO.findByExample(examPaperDistribute);
        if (examPaper!=null && examPaper.size()==1) {
            logger.info("开始考试，当前试卷正常");
            return true;
        }else {
            String error="开始考试，当前试卷不正常，老师并未分配过该试卷或者已收卷";
            logger.error(error);
            throw new RuntimeException(error);
        }
    }

    /**
     * 设置当前答题
     */
    public String setUpSubject(Integer subjectId) throws IllegalAccessException {
        Object obj=WebWorkUtil.sessionGet("ListenExamConfig");
        String message="";
        if (obj!=null){
            ListenModelConfig config= (ListenModelConfig) obj;
            if (config.isOnAnswer() && isLegal(config.getPaperId(),subjectId)){
                config.setSubjectId(subjectId);
                message="成功进入题号"+subjectId;
            }else {
                message="设置当前答题出现问题,可能由于暂时不在答题环境或者该试卷并不存在该题";
            }
        }else {
            ListenModelConfig config=new ListenModelConfig();
            config.setOnAnswer(false);
            config.setPaperId(-1);
            config.setSubjectId(-1);
            WebWorkUtil.sessionPut("ListenExamConfig",config);
            message="当前不在考试状态，请进入考试状态后重试";
        }
        return message;
    }

    /**
     * 题目编号是否合法
     */
    public boolean isLegal(Integer paperId,Integer subjectId){
        ExamPaper examPaper=examPaperDAO.findById(paperId);
        String subjectIds=examPaper.getPaperExamContent();
        if (subjectIds!=null) {
            String[] subject = subjectIds.split(",");
            for (String sub:subject){
                Integer subj=Integer.valueOf(sub);
                if (subj.equals(subjectId))
                    return true;
            }
        }
        return false;
    }

    /**
     * 考试配置获取
     */
    private ListenModelConfig getExamConfig(){
        Object obj=WebWorkUtil.sessionGet("ListenExamConfig");
        if (obj==null || ! (obj instanceof ListenModelConfig))
            throw new IllegalArgumentException("考试配置throw");
        ListenModelConfig config= (ListenModelConfig) obj;
        return config;
    }

    /**
     * 监听swap交易
     */
    public void listenSwapExchange(ExamSwap examSwap){
        ListenModelConfig config=getExamConfig();
        ExamContent examContent=new ExamContent();
        examContent.setExamNo(config.getSubjectId());
        examSwap.setExamContentByExamNo(examContent);
        examSwap.setStep(1);
        examSwap.setExamScore("");
        examSwap.setUserType(ExamConst.STUDENT);
        examSwapDAO.save(examSwap);
        HibernateUtil.getSession().flush();
    }

    /**
     * 监听spot交易
     */
    public void listenSpotExchange(ExamSpot examSpot){
        ListenModelConfig config=getExamConfig();
        ExamContent examContent=new ExamContent();
        examContent.setExamNo(config.getSubjectId());
        examSpot.setExamContentByExamNo(examContent);
        examSpot.setStep(1);
        examSpot.setUserType(ExamConst.STUDENT);
        examSpot.setExamScore("");
        examSpotDAO.save(examSpot);
        HibernateUtil.getSession().flush();
    }

    /**
     * 监听one click交易
     */
    public void listenOneClickExhange(ExamOneClick examOneClick){
        ListenModelConfig config=getExamConfig();
        ExamContent examContent=new ExamContent();
        examContent.setExamNo(config.getSubjectId());
        examOneClick.setExamContentByExamNo(examContent);
        examOneClick.setExamScore("");
        examOneClick.setStep(1);
        examOneClick.setUserType(ExamConst.STUDENT);
        examOneClickDAO.save(examOneClick);
        HibernateUtil.getSession().flush();
    }

    /**
     * 监听market breakout交易
     */
    public void listenMKExchange(ExamMarketBreakout examMarketBreakout){
        ListenModelConfig config=getExamConfig();
        ExamContent examContent=new ExamContent();
        examContent.setExamNo(config.getSubjectId());
        examMarketBreakout.setExamContentByExamNo(examContent);
        examMarketBreakout.setExamScore("");
        examMarketBreakout.setStep(1);
        examMarketBreakout.setUserType(ExamConst.STUDENT);
        examMarketBreakoutDAO.save(examMarketBreakout);
        HibernateUtil.getSession().flush();
    }

    /**
     * 监听oco交易
     */
    public void listenOcoExchange(ExamOco examOco){
        ListenModelConfig config=getExamConfig();
        ExamContent examContent=new ExamContent();
        examContent.setExamNo(config.getSubjectId());
        examOco.setExamContentByExamNo(examContent);
        examOco.setStep(1);
        examOco.setUserType(ExamConst.STUDENT);
        examOco.setExamScore("");
        examOcoDAO.save(examOco);
        HibernateUtil.getSession().flush();
    }

    /**
     * 监听take profit交易
     */
    public void listenTakeProfitExchange(ExamTakeProfit examTakeProfit){
        ListenModelConfig config=getExamConfig();
        ExamContent examContent=new ExamContent();
        examContent.setExamNo(config.getSubjectId());
        examTakeProfit.setExamContentByExamNo(examContent);
        examTakeProfit.setStep(1);
        examTakeProfit.setUserType(ExamConst.STUDENT);
        examTakeProfit.setExamScore("");
        examTakeProfitDAO.save(examTakeProfit);
        HibernateUtil.getSession().flush();
    }

    /**
     * 监听远期Forward交易
     */
    public void listtenForwardExchange(ExamForward examForward){
        ListenModelConfig config=getExamConfig();
        ExamContent examContent=new ExamContent();
        examContent.setExamNo(config.getSubjectId());
        examForward.setExamContentByExamNo(examContent);
        examForward.setStep(1);
        examForward.setExamScore("");
        examForward.setUserType(ExamConst.STUDENT);
        examForwardDAO.save(examForward);
        HibernateUtil.getSession().flush();
    }


    /**
     * 监听stop loss交易
     */
    public void listenStopLossExchange(StopAndProfitModel stopAndProfitModel){
        ExamStopLoss examStopLoss=new ExamStopLoss();
        examStopLoss.setAcc(stopAndProfitModel.getCcy1());
        examStopLoss.setUserType(ExamConst.STUDENT);
        examStopLoss.setExamScore("");
        ExamContent examContent=new ExamContent();
        ListenModelConfig config=getExamConfig();
        examContent.setExamNo(config.getSubjectId());   //题号
        examStopLoss.setExamContentByExamNo(examContent);
        examStopLoss.setStep(1);
        if ("CNY".equals(stopAndProfitModel.getCcy2()))
            examStopLoss.setAccTypeNo("C");
        if (stopAndProfitModel.getActiveTime().equals("")){
            examStopLoss.setGoodFrom(GetDateTimeUtil.getCurrentDate());
        }else {
            examStopLoss.setGoodFrom(stopAndProfitModel.getActiveTime());
        }
        if (stopAndProfitModel.getCancelTime().equals("")){
            examStopLoss.setGoddTill(ExamConst.TILLTIME);
        }else {
            examStopLoss.setGoddTill(stopAndProfitModel.getCancelTime());
        }
        examStopLoss.setMonitorPrice(stopAndProfitModel.getMonitorPrice());
        examStopLoss.setPrice(stopAndProfitModel.getPrice());
        examStopLoss.setAccAmount(stopAndProfitModel.getAmount());
        examStopLoss.setDirection(stopAndProfitModel.getTradeDirection());
        examStopLossDAO.save(examStopLoss);
        logger.info("监听stop loss...");
        HibernateUtil.getSession().flush();
    }


    public ExamStopLossDAO getExamStopLossDAO() {
        return examStopLossDAO;
    }

    public void setExamStopLossDAO(ExamStopLossDAO examStopLossDAO) {
        this.examStopLossDAO = examStopLossDAO;
    }

    public ExamStuAnswerDAO getExamStuAnswerDAO() {
        return examStuAnswerDAO;
    }

    public void setExamStuAnswerDAO(ExamStuAnswerDAO examStuAnswerDAO) {
        this.examStuAnswerDAO = examStuAnswerDAO;
    }

    public ExamPaperDAO getExamPaperDAO() {
        return examPaperDAO;
    }

    public void setExamPaperDAO(ExamPaperDAO examPaperDAO) {
        this.examPaperDAO = examPaperDAO;
    }

    public ExamPaperDistributeDAO getExamPaperDistributeDAO() {
        return examPaperDistributeDAO;
    }

    public void setExamPaperDistributeDAO(ExamPaperDistributeDAO examPaperDistributeDAO) {
        this.examPaperDistributeDAO = examPaperDistributeDAO;
    }

    public TClassDao gettClassDao() {
        return tClassDao;
    }

    public void settClassDao(TClassDao tClassDao) {
        this.tClassDao = tClassDao;
    }

    public ExamForwardDAO getExamForwardDAO() {
        return examForwardDAO;
    }

    public void setExamForwardDAO(ExamForwardDAO examForwardDAO) {
        this.examForwardDAO = examForwardDAO;
    }

    public ExamTakeProfitDAO getExamTakeProfitDAO() {
        return examTakeProfitDAO;
    }

    public void setExamTakeProfitDAO(ExamTakeProfitDAO examTakeProfitDAO) {
        this.examTakeProfitDAO = examTakeProfitDAO;
    }

    public ExamOcoDAO getExamOcoDAO() {
        return examOcoDAO;
    }

    public void setExamOcoDAO(ExamOcoDAO examOcoDAO) {
        this.examOcoDAO = examOcoDAO;
    }

    public ExamMarketBreakoutDAO getExamMarketBreakoutDAO() {
        return examMarketBreakoutDAO;
    }

    public void setExamMarketBreakoutDAO(ExamMarketBreakoutDAO examMarketBreakoutDAO) {
        this.examMarketBreakoutDAO = examMarketBreakoutDAO;
    }

    public ExamOneClickDAO getExamOneClickDAO() {
        return examOneClickDAO;
    }

    public void setExamOneClickDAO(ExamOneClickDAO examOneClickDAO) {
        this.examOneClickDAO = examOneClickDAO;
    }

    public ExamSpotDAO getExamSpotDAO() {
        return examSpotDAO;
    }

    public void setExamSpotDAO(ExamSpotDAO examSpotDAO) {
        this.examSpotDAO = examSpotDAO;
    }

    public ExamSwapDAO getExamSwapDAO() {
        return examSwapDAO;
    }

    public void setExamSwapDAO(ExamSwapDAO examSwapDAO) {
        this.examSwapDAO = examSwapDAO;
    }
}
