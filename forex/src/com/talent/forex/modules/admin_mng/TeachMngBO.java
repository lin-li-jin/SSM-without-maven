package com.talent.forex.modules.admin_mng;

import com.opensymphony.webwork.ServletActionContext;
import com.talent.auth.bean.domain.Users;
import com.talent.auth.util.SessionFacade;
import com.talent.base.BaseBo;
import com.talent.forex.common.Md5PwdEncoder;
import com.talent.forex.constant.SysParamNameConst;
import com.talent.forex.dao.TeachDao;
import com.talent.forex.util.PageBean;
import com.talent.hibernate.util.HibernateUtil;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 吴樟 on www.haixiangzhene.xyz
 * 2017/8/15.
 */
public class TeachMngBO extends BaseBo{

    private static final Logger log = Logger.getLogger(TeachMngBO.class);

    private TeachDao teachDao;

    /**
     * 插入单个教师数据
     * @throws UnsupportedEncodingException 
     */
    public BaseMessage insertTeacher(Users teacher) throws UnsupportedEncodingException{
    	String name =new String(teacher.getName().getBytes("ISO-8859-1"),"UTF-8");
    	teacher.setName(name);
        ArrayList<Users> teaches=new ArrayList<Users>();
        teaches.add(teacher);
        return batchInsertTeacher(teaches);
    }

    /**
     * 批量插入教师数据
     * @param teachers
     */
    public BaseMessage batchInsertTeacher(Collection teachers)  {
        BaseMessage message=new BaseMessage();
        if (teachers!=null) {
            Iterator ite=teachers.iterator();
            ArrayList<Users> teaches=new ArrayList<Users>();
            //查询老师是否存在
            while (ite.hasNext()){
                Users teacher= (Users) ite.next();
                //查询账号是否已被导入
                Users bean=new Users();
                bean.setUserNum(teacher.getUserNum());
                bean.setUserType(SysParamNameConst.TEACHER);
                Collection collection=teachDao.getBeansByBean(bean,MatchMode.EXACT);
                if (collection!=null&&!collection.isEmpty()){
                    StringBuilder sb=new StringBuilder();
                    sb.append("已存在该账号记录，请检查后再次添加");
                    message.setMessage(sb.toString());
                    message.setSuccess(false);
                    return message;
                }
            }
            Iterator it=teachers.iterator();
            while (it.hasNext()) {
                Users techer= (Users) it.next();
                Md5PwdEncoder md5 = new Md5PwdEncoder();
                techer.setPassword(md5.encodePassword(techer.getPassword()));
                techer.setPost("P");
                techer.setUserType("T");
                techer.setStatus("1");
                teaches.add(techer);
            }
            teachDao.batchInsert(teaches);
        }
        StringBuilder sb=new StringBuilder();
        sb.append("添加数据成功");
        message.setSuccess(true);
        message.setMessage(sb.toString());
        return message;
    }

    /**
     * 查询教师信息
     * 即查询条件
     * @return
     */
    public Collection<Users> selectTeacher(String userNum){
        Users users=new Users();
        users.setUserNum(userNum);
        users.setUserType("T");
        return teachDao.getBeansByBean(users, MatchMode.EXACT);
    }



    public TeachDao getTeachDao() {
        return teachDao;
    }

    public void setTeachDao(TeachDao teachDao) {
        this.teachDao = teachDao;
    }

    /**
     * 分页查询教师
     * @param userNum
     * @param pageBean
     * @return
     */
	public void pageQuery(String userNum, PageBean pageBean) {
		DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
		if(userNum!=null&&!"".equals(userNum)){
			detachedCriteria.add(Restrictions.like("userNum", "%"+userNum+"%"));
		}
		detachedCriteria.add(Restrictions.eq("userType", "T"));
		teachDao.pageQuery(pageBean);
	}
}
