package com.talent.forex.modules.teach_mng.tClassMng;


import com.talent.auth.bean.domain.TClass;
import com.talent.auth.bean.domain.TeacherClass;
import com.talent.auth.bean.domain.Users;
import com.talent.auth.bean.model.UserModel;

import com.talent.auth.dao.TClassDao;
import com.talent.auth.dao.TeachClassDao;
import com.talent.auth.dao.UsersDao;
import com.talent.forex.constant.SysParamNameConst;
import com.talent.forex.modules.admin_mng.BaseMessage;
import com.talent.forex.util.PageBean;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by 吴樟 on www.haixiangzhene.xyz
 * 2017/8/17.
 */
public class ClassMngBo {



    private TeachClassDao teachClassDao;

    private TClassDao tClassDao;
    
    private UsersDao usersDao;


	/**
     * 1.如果班级未建立，则建立班级，否则，则提示班级已经建立
     * 2.如果未绑定班级，则绑定班级，否则，提示班级已绑定过
     * @param userModel
     * @param tClass
     * @return
     */
    public BaseMessage insertClass(UserModel userModel, TClass tClass){
        BaseMessage message=new BaseMessage();
        //验证老师身份
        if (!SysParamNameConst.TEACHER.equals(userModel.getUserType())){
            StringBuilder sb=new StringBuilder();
            sb.append("只允许老师新增班级!");
            message.setMessage(sb.toString());
            message.setSuccess(false);
            return message;
        }
        //验证数据
        if (tClass==null || tClass.getClassNo().trim().equals("") || tClass.getClassName()==null
                || tClass.getClassName().trim().equals("")){
            StringBuilder sb=new StringBuilder();
            sb.append("班级不合法，请检查后重新输入");
            message.setSuccess(false);
            message.setMessage(sb.toString());
            return message;
        }
        StringBuilder sb=new StringBuilder();
        tClass.setClassNo(tClass.getClassNo().trim());
        //查询班级classNo是否已存在,如果存在则返回新增失败
        TClass tClass1=new TClass();
        tClass1.setClassNo(tClass.getClassNo());
        TClass tClass2=tClassDao.getBeanByBean(tClass1, MatchMode.EXACT);
        if (tClass2!=null){
            sb.append("该班级已经建立");
        }else {
            tClassDao.insertSingle(tClass);
            sb.append("班级"+tClass.getClassNo()+"创建成功!");
        }
        tClass2=tClassDao.getBeanByBean(tClass1,MatchMode.EXACT);
        //查询是否已和该班级绑定过
        TeacherClass teacherClass=new TeacherClass();
        teacherClass.setClassId(tClass2.getClassId());
        teacherClass.settId(userModel.getuId());
        TeacherClass teacher=teachClassDao.getBeanBySingle(teacherClass,MatchMode.EXACT);
        if (teacher!=null){
            sb.append("已经绑定过该班级!");
        }else {
            teachClassDao.insertSingle(teacherClass);
            sb.append("绑定班级成功!");
        }
        message.setMessage(sb.toString());
        message.setSuccess(true);
        return message;
    }

    /**
     * 查询班级基本信息
     * @param pageBean
     * @param tId
     */
    public void queryClass(String classNo, PageBean pageBean, int tId){
        TClassMessage tClassMessage=new TClassMessage();
        Long tClassSize = tClassDao.getTClassSize(tId);//查询总条数
        pageBean.setTotal(tClassSize.intValue());//把总条数封装进pageBean
        List<TClass> tClasses=tClassDao.queryByPage(pageBean.getCurrentPage() ,pageBean.getPageSize(),tId,classNo);//查询当前页数据
        tClassMessage.setSuccess(true);
        tClassMessage.setMessage("数据返回成功");
        pageBean.setRows(tClasses);//把当前页数据封装进pageBean
    }
    
    /**
     *根据班级classNo查询班级中的学生
     * @param classNo
     * @param pageBean
     */
    public void getStudentsByclassNo(String classNo, PageBean pageBean){
//        TClassMessage message=new TClassMessage();
//        if (classNo==null || classNo.trim().equals("")){
//            StringBuilder sb=new StringBuilder();
//            sb.append("班级号不合法，请检查后再传!");
//            message.setSuccess(false);
//            message.setMessage(sb.toString());
//            return message;
//        }
//        //根据班级号查询所在的班级id
//        Long classId=tClassDao.getIdByclassNo(classNo.trim());
//        //根据班级id检索班级的学生
//        StudentClass studentClass=new StudentClass();
//        studentClass.setClassId(classId.intValue());
//        List<Users> stus= (List<Users>) stuClassDao.getBeansByBean(studentClass,MatchMode.EXACT);
//        message.setMessage("成功检索到班级信息");
//        message.setResult(stus);
//        return message;
    	DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
    	detachedCriteria.add(Restrictions.eq("groupOne", classNo));
    	detachedCriteria.add(Restrictions.eq("userType", "S"));
    	usersDao.pageQuery(pageBean);

    }



    public TeachClassDao getTeachClassDao() {
        return teachClassDao;
    }

    public void setTeachClassDao(TeachClassDao teachClassDao) {
        this.teachClassDao = teachClassDao;
    }

    public TClassDao gettClassDao() {
        return tClassDao;
    }

    public void settClassDao(TClassDao tClassDao) {
        this.tClassDao = tClassDao;
    }


    public UsersDao getUsersDao() {
		return usersDao;
	}

	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}
}
