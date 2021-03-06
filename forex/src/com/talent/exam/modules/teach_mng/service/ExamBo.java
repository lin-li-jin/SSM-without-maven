package com.talent.exam.modules.teach_mng.service;

import com.talent.auth.bean.domain.TClass;
import com.talent.auth.bean.domain.Users;
import com.talent.auth.dao.TClassDao;
import com.talent.auth.dao.UsersDao;
import com.talent.base.BaseBo;
import com.talent.exam.constant.ExamConst;
import com.talent.exam.constant.ResultConst;
import com.talent.exam.dao.ExamForwardDAO;
import com.talent.exam.dao.ExamPaperDAO;
import com.talent.exam.dao.ExamPaperDistributeDAO;
import com.talent.exam.dao.ExamStuAnswerDAO;
import com.talent.exam.domain.ExamForward;
import com.talent.exam.domain.ExamPaper;
import com.talent.exam.domain.ExamPaperDistribute;
import com.talent.exam.domain.ExamStuAnswer;
import com.talent.exam.model.ForwardScore;
import com.talent.exam.modules.teach_mng.model.DistributeMessage;
import com.talent.exam.modules.teach_mng.model.ExamPaperMessage;
import com.talent.exam.modules.teach_mng.model.PaperResult;
import com.talent.exam.modules.teach_mng.model.StudentExamStatus;
import com.talent.exam.util.StringUtil;
import com.talent.forex.constant.SysParamNameConst;
import com.talent.forex.util.GetDateTimeUtil;
import org.apache.log4j.Logger;
import org.hibernate.criterion.MatchMode;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by 吴樟 on www.haixiangzhene.xyz
 * 2017/9/24.
 */
public class ExamBo extends BaseBo{

    private static Logger logger= Logger.getLogger(ExamBo.class);

    private static ExecutorService correctService= Executors.newFixedThreadPool(6);

    private static StringUtil stringUtil=new StringUtil();

    private ExamPaperDistributeDAO examPaperDistributeDAO;
    private ExamForwardDAO examForwardDAO;
    private TClassDao tClassDao;
    private ExamPaperDAO examPaperDAO;
    private ExamStuAnswerDAO examStuAnswerDAO;
    private UsersDao usersDao;


    /**
     * 自动批改试卷
     * 1.查看是否已经收卷，如果未收卷，则调用收卷模块
     * 2.进入批改，筛选出老师关注的答案点；并于答案进行比对
     * 3.并将答案及比对结果回写到db ==> 比较表 dispacher table、分数表
     */
    public void autoCorrect(Integer pageId) throws IllegalAccessException {
        ExamPaperDistribute examPaperDistribute=
            examPaperDistributeDAO.findById(pageId);
        if (!examPaperDistribute.getClassPaperStatus().equals(ExamConst.DONE)){
            takeUpPaper(pageId);
            logger.info("it have take up the paper");
        }
        //批改
        //获取试卷题目
        ExamPaper examPaper=
                examPaperDAO.findById(examPaperDistribute.getPaperNo());
        String examContent=examPaper.getPaperExamContent();
        String[] examIds=examContent.split(",");
        //针对一道题进行处理
    }



    /**
     * 批改模块
     * 采用使用线程池中的一个线程负责种交易类型
     * 1.批改远期交易
     */
    public String correctForward(ExamForward correct, ExamForward stuAnswer)
            throws NoSuchFieldException, IllegalAccessException {
        String score=correct.getExamScore();
        ForwardScore forwardScore=new ForwardScore();
        ForwardScore forwardScore1=stringUtil.FromString(score,forwardScore);
        if(forwardScore1.getDirectionScore()!=0 &&
                correct.getDirection().equals(stuAnswer.getDirection()))    //!=0则属于需要比较项
            forwardScore.setDirectionScore(forwardScore1.getDirectionScore());
        if (forwardScore1.getAccScore()!=0 &&
                correct.getAcc().equals(stuAnswer.getAcc()))
            forwardScore.setAccScore(forwardScore1.getAccScore());
        if (forwardScore1.getAccAmountScore()!=0 &&
                correct.getAccAmount().equals(stuAnswer.getAccAmount()))
            forwardScore.setAccAmountScore(forwardScore1.getAccAmountScore());
        if (forwardScore1.getPriceScore()!=0 &&
                correct.getPrice().equals(stuAnswer.getPrice()))
            forwardScore.setPriceScore(forwardScore1.getPriceScore());
        if (forwardScore1.getProviderNoScore()!=0 &&
                correct.getProviderNo()==stuAnswer.getProviderNo())
            forwardScore.setProviderNoScore(forwardScore1.getProviderNoScore());
        if (forwardScore1.getValueDateScore()!=0 &&
                correct.getValueDate().equals(stuAnswer.getValueDate()))
            forwardScore.setValueDateScore(forwardScore1.getValueDateScore());
        String stuScore=stringUtil.FromBean(forwardScore);
        return stuScore;
    }


    /**
     * 收卷
     * @param pageId 某次考试
     * 1.修改试卷状态D
     * 2.查找所在班级的学生，提交的情况
     * 3.存在暂未提交的，则强制提交，并将其写入到exam_stu_answer表中
     */
    public void takeUpPaper(Integer pageId) throws IllegalAccessException {
        //获取班级No
        ExamPaperDistribute examPaperDistribute=
                examPaperDistributeDAO.findById(pageId);
        //查看状态
        //1.查询该次测试
        ExamStuAnswer examStuAnswer=new ExamStuAnswer();
        examStuAnswer.setExamPaperDistributeByPaperId(examPaperDistribute);
        List<ExamStuAnswer> answers=
                examStuAnswerDAO.findByExample(examStuAnswer);
        for (ExamStuAnswer answer:answers){
            if (!ExamConst.ANSWERDONE.equals(answer.getPaperExamStatus())){
                answer.setPaperExamStatus(ExamConst.FORCE); //未完成强制收卷
                examStuAnswerDAO.save(answer);
            }
        }
        //设置收卷时间
        examPaperDistribute.setTillDate(GetDateTimeUtil.getCurrentDate());
        examPaperDistribute.setClassPaperStatus(ExamConst.DONE);
        examPaperDistributeDAO.save(examPaperDistribute);
    }


    /**
     * 查看某次考试学生提交状况
     * @param pageId 某次考试
     * 兼容未收卷及收卷后的查询
     */
    public Collection<StudentExamStatus> queryExamSubmissionStatus(Integer pageId) throws IllegalAccessException {
        //获取班级No
        ExamPaperDistribute examPaperDistribute=
                examPaperDistributeDAO.findById(pageId);
        String classNo=
                examPaperDistribute.gettClassByClassId().getClassNo();
        //查看所在班级的所有学生
        Collection<Users> students=queryAllStuInClass(classNo);
        //查看状态
        //1.查询该次测试
        ExamStuAnswer examStuAnswer=new ExamStuAnswer();
        examStuAnswer.setExamPaperDistributeByPaperId(examPaperDistribute);
        List<ExamStuAnswer> answers=
                examStuAnswerDAO.findByExample(examStuAnswer);
        Collection<StudentExamStatus> studentExamStatuses=new LinkedList<StudentExamStatus>();
        //查询所有学生中,时间复杂度n^2
        for (Users users:students){
            StudentExamStatus studentExamStatus=new StudentExamStatus();
            studentExamStatus.setStuName(users.getName());
            boolean has=false;
            for (ExamStuAnswer answer:answers){
                if (users.getId().intValue()==answer.getUsersByStuId().getId().intValue()){
                    //查询状态
                    if (ExamConst.ANSWERDONE.equals(answer.getPaperExamStatus())){  // D
                        studentExamStatus.setExamStatus(ExamConst.DONEMS);

                    }else if (ExamConst.NOTREAD.equals(answer.getPaperExamStatus())){   // n
                        studentExamStatus.setExamStatus(ExamConst.NOTDOMS);
                    }else if (ExamConst.ANSWERACTIVE.equals(answer.getPaperExamStatus())) { //a
                        studentExamStatus.setExamStatus(ExamConst.DOINGMS);
                    }
                    has=true;
                    break;
                }
            }
            //假如没有，则判断为未完成
            if (!has){
                studentExamStatus.setExamStatus(ExamConst.NOTDOMS); // N
            }
            studentExamStatuses.add(studentExamStatus);
        }
        return studentExamStatuses;
    }




    /**
     * 分配试卷
     * 班级号  试卷号
     * 分配到学生
     */
    public DistributeMessage distributePaper(ExamPaperMessage examPaperMessage){
        TClass tClass=null;
        Integer id=examPaperMessage.getClassId();
        tClass=tClassDao.getBeanById(id);
        DistributeMessage message=new DistributeMessage();
        if (tClass==null){
            logger.info("the class id is not exits");
            message.setMessage("该班级号不存在");
            message.setCode(ResultConst.CLASSNOTEXISTS);
            return message;
        }
        ExamPaper examPaper=null;
        if((examPaper=examPaperDAO.findById(examPaperMessage.getClassId()))==null){
            logger.info("the exam paper is not exits");
            message.setMessage("该试卷不存在");
            message.setCode(ResultConst.PAPERNOTEXISTS);
            return message;
        }
        ExamPaperDistribute examPaperDistribute=new ExamPaperDistribute();
        examPaperDistribute.settClassByClassId(tClass);
        examPaperDistribute.setCreateDate(GetDateTimeUtil.getCurrentDate());
        //分发试卷时默认是use状态
        examPaperDistribute.setClassPaperStatus(ExamConst.USE);
        examPaperDistribute.setPaperNo(examPaperMessage.getPaperId());
        examPaperDistribute.setTillDate(ExamConst.TILLTIME);
        try {
            examPaperDistributeDAO.save(examPaperDistribute);
        }catch (Exception ex){
            message.setCode(ResultConst.FAIL);
            message.setMessage("分发试卷出现问题");
            return message;
        }
        //2
        //1.查询所有学生
        Collection<Users> users=queryAllStuInClass(tClass.getClassNo());
        //2.插入学生答题表，默认初始答题状态为N
        initStuAnswer(users,examPaper);
        message.setMessage("分配试卷成功");
        message.setCode(ResultConst.SUCCESS);
        return message;
    }

    //初始化答题卷
    public void initStuAnswer(Collection<Users> users,ExamPaper examPaper){
        ExamStuAnswer examStuAnswer=new ExamStuAnswer();
        for (Users user:users) {
            examStuAnswer.setUsersByStuId(user);
            examStuAnswer.setPaperGrade(0); //默认分数0
            examStuAnswer.setPaperExamStatus(ExamConst.NOTREAD);//初始答题状态
            examStuAnswer.setExamPaperByPaperNo(examPaper);
            examStuAnswer.setStuAnswer("");
            examStuAnswer.setStuAnswerTable("");
            examStuAnswerDAO.save(examStuAnswer);
        }
    }

    public Collection<Users> queryAllStuInClass(String classNo){
        Users users=new Users();
        users.setGroupOne(classNo);
        users.setUserType(SysParamNameConst.STUDENT);
        Collection<Users> userses= usersDao.getBeansByBean(users, MatchMode.EXACT);
        return userses;
    }

    /**
     * 查看可分配试卷
     */
    public List<PaperResult> queryDistributedPaper(){
        List<ExamPaper> examPapers=examPaperDAO.findAll();
        List<PaperResult> paperResults=new LinkedList<PaperResult>();
        for (ExamPaper examPaper:examPapers){
            PaperResult paperResult=new PaperResult();
            try {
                paperResult.setPaperTitle(new String(examPaper.getPaperTitle(),"utf-8"));
                paperResult.setPaperNo(examPaper.getPaperNo());
            } catch (UnsupportedEncodingException e) {
                logger.error("unsupport encoding...");
                e.printStackTrace();
            }
            paperResults.add(paperResult);
        }
        return paperResults;
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

    public ExamPaperDAO getExamPaperDAO() {
        return examPaperDAO;
    }

    public void setExamPaperDAO(ExamPaperDAO examPaperDAO) {
        this.examPaperDAO = examPaperDAO;
    }

    public ExamStuAnswerDAO getExamStuAnswerDAO() {
        return examStuAnswerDAO;
    }

    public void setExamStuAnswerDAO(ExamStuAnswerDAO examStuAnswerDAO) {
        this.examStuAnswerDAO = examStuAnswerDAO;
    }

    public UsersDao getUsersDao() {
        return usersDao;
    }

    public void setUsersDao(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    public ExamForwardDAO getExamForwardDAO() {
        return examForwardDAO;
    }

    public void setExamForwardDAO(ExamForwardDAO examForwardDAO) {
        this.examForwardDAO = examForwardDAO;
    }
}
