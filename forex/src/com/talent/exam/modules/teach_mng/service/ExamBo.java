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
 * Created by ���� on www.haixiangzhene.xyz
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
     * �Զ������Ծ�
     * 1.�鿴�Ƿ��Ѿ��վ������δ�վ���������վ�ģ��
     * 2.�������ģ�ɸѡ����ʦ��ע�Ĵ𰸵㣻���ڴ𰸽��бȶ�
     * 3.�����𰸼��ȶԽ����д��db ==> �Ƚϱ� dispacher table��������
     */
    public void autoCorrect(Integer pageId) throws IllegalAccessException {
        ExamPaperDistribute examPaperDistribute=
            examPaperDistributeDAO.findById(pageId);
        if (!examPaperDistribute.getClassPaperStatus().equals(ExamConst.DONE)){
            takeUpPaper(pageId);
            logger.info("it have take up the paper");
        }
        //����
        //��ȡ�Ծ���Ŀ
        ExamPaper examPaper=
                examPaperDAO.findById(examPaperDistribute.getPaperNo());
        String examContent=examPaper.getPaperExamContent();
        String[] examIds=examContent.split(",");
        //���һ������д���
    }



    /**
     * ����ģ��
     * ����ʹ���̳߳��е�һ���̸߳����ֽ�������
     * 1.����Զ�ڽ���
     */
    public String correctForward(ExamForward correct, ExamForward stuAnswer)
            throws NoSuchFieldException, IllegalAccessException {
        String score=correct.getExamScore();
        ForwardScore forwardScore=new ForwardScore();
        ForwardScore forwardScore1=stringUtil.FromString(score,forwardScore);
        if(forwardScore1.getDirectionScore()!=0 &&
                correct.getDirection().equals(stuAnswer.getDirection()))    //!=0��������Ҫ�Ƚ���
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
     * �վ�
     * @param pageId ĳ�ο���
     * 1.�޸��Ծ�״̬D
     * 2.�������ڰ༶��ѧ�����ύ�����
     * 3.������δ�ύ�ģ���ǿ���ύ��������д�뵽exam_stu_answer����
     */
    public void takeUpPaper(Integer pageId) throws IllegalAccessException {
        //��ȡ�༶No
        ExamPaperDistribute examPaperDistribute=
                examPaperDistributeDAO.findById(pageId);
        //�鿴״̬
        //1.��ѯ�ôβ���
        ExamStuAnswer examStuAnswer=new ExamStuAnswer();
        examStuAnswer.setExamPaperDistributeByPaperId(examPaperDistribute);
        List<ExamStuAnswer> answers=
                examStuAnswerDAO.findByExample(examStuAnswer);
        for (ExamStuAnswer answer:answers){
            if (!ExamConst.ANSWERDONE.equals(answer.getPaperExamStatus())){
                answer.setPaperExamStatus(ExamConst.FORCE); //δ���ǿ���վ�
                examStuAnswerDAO.save(answer);
            }
        }
        //�����վ�ʱ��
        examPaperDistribute.setTillDate(GetDateTimeUtil.getCurrentDate());
        examPaperDistribute.setClassPaperStatus(ExamConst.DONE);
        examPaperDistributeDAO.save(examPaperDistribute);
    }


    /**
     * �鿴ĳ�ο���ѧ���ύ״��
     * @param pageId ĳ�ο���
     * ����δ�վ����վ���Ĳ�ѯ
     */
    public Collection<StudentExamStatus> queryExamSubmissionStatus(Integer pageId) throws IllegalAccessException {
        //��ȡ�༶No
        ExamPaperDistribute examPaperDistribute=
                examPaperDistributeDAO.findById(pageId);
        String classNo=
                examPaperDistribute.gettClassByClassId().getClassNo();
        //�鿴���ڰ༶������ѧ��
        Collection<Users> students=queryAllStuInClass(classNo);
        //�鿴״̬
        //1.��ѯ�ôβ���
        ExamStuAnswer examStuAnswer=new ExamStuAnswer();
        examStuAnswer.setExamPaperDistributeByPaperId(examPaperDistribute);
        List<ExamStuAnswer> answers=
                examStuAnswerDAO.findByExample(examStuAnswer);
        Collection<StudentExamStatus> studentExamStatuses=new LinkedList<StudentExamStatus>();
        //��ѯ����ѧ����,ʱ�临�Ӷ�n^2
        for (Users users:students){
            StudentExamStatus studentExamStatus=new StudentExamStatus();
            studentExamStatus.setStuName(users.getName());
            boolean has=false;
            for (ExamStuAnswer answer:answers){
                if (users.getId().intValue()==answer.getUsersByStuId().getId().intValue()){
                    //��ѯ״̬
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
            //����û�У����ж�Ϊδ���
            if (!has){
                studentExamStatus.setExamStatus(ExamConst.NOTDOMS); // N
            }
            studentExamStatuses.add(studentExamStatus);
        }
        return studentExamStatuses;
    }




    /**
     * �����Ծ�
     * �༶��  �Ծ���
     * ���䵽ѧ��
     */
    public DistributeMessage distributePaper(ExamPaperMessage examPaperMessage){
        TClass tClass=null;
        Integer id=examPaperMessage.getClassId();
        tClass=tClassDao.getBeanById(id);
        DistributeMessage message=new DistributeMessage();
        if (tClass==null){
            logger.info("the class id is not exits");
            message.setMessage("�ð༶�Ų�����");
            message.setCode(ResultConst.CLASSNOTEXISTS);
            return message;
        }
        ExamPaper examPaper=null;
        if((examPaper=examPaperDAO.findById(examPaperMessage.getClassId()))==null){
            logger.info("the exam paper is not exits");
            message.setMessage("���Ծ�������");
            message.setCode(ResultConst.PAPERNOTEXISTS);
            return message;
        }
        ExamPaperDistribute examPaperDistribute=new ExamPaperDistribute();
        examPaperDistribute.settClassByClassId(tClass);
        examPaperDistribute.setCreateDate(GetDateTimeUtil.getCurrentDate());
        //�ַ��Ծ�ʱĬ����use״̬
        examPaperDistribute.setClassPaperStatus(ExamConst.USE);
        examPaperDistribute.setPaperNo(examPaperMessage.getPaperId());
        examPaperDistribute.setTillDate(ExamConst.TILLTIME);
        try {
            examPaperDistributeDAO.save(examPaperDistribute);
        }catch (Exception ex){
            message.setCode(ResultConst.FAIL);
            message.setMessage("�ַ��Ծ���������");
            return message;
        }
        //2
        //1.��ѯ����ѧ��
        Collection<Users> users=queryAllStuInClass(tClass.getClassNo());
        //2.����ѧ���������Ĭ�ϳ�ʼ����״̬ΪN
        initStuAnswer(users,examPaper);
        message.setMessage("�����Ծ��ɹ�");
        message.setCode(ResultConst.SUCCESS);
        return message;
    }

    //��ʼ�������
    public void initStuAnswer(Collection<Users> users,ExamPaper examPaper){
        ExamStuAnswer examStuAnswer=new ExamStuAnswer();
        for (Users user:users) {
            examStuAnswer.setUsersByStuId(user);
            examStuAnswer.setPaperGrade(0); //Ĭ�Ϸ���0
            examStuAnswer.setPaperExamStatus(ExamConst.NOTREAD);//��ʼ����״̬
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
     * �鿴�ɷ����Ծ�
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