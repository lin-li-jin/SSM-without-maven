package com.talent.auth.dao;

import com.talent.auth.bean.domain.TeacherClass;
import com.talent.hibernate.base.AbstractDaoImpl;
import org.hibernate.criterion.MatchMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * teacher_class表
 * Created by 吴樟 on www.haixiangzhene.xyz
 * 2017/8/16.
 */
public class TeachClassDao extends AbstractDaoImpl {

    /*根据教师id查*/
    public TeacherClass getBySId(Integer tId){
        TeacherClass teacherClass=new TeacherClass();
        teacherClass.settId(tId);
        return (TeacherClass) getProtoBeanByBean(teacherClass, MatchMode.EXACT);
    }

    /*根据班级id查*/
    public TeacherClass getByClassId(Integer teachId){
        TeacherClass teacherClass=new TeacherClass();
        teacherClass.setClassId(teachId);
        return (TeacherClass) getProtoBeanByBean(teacherClass,MatchMode.EXACT);
    }

    /*批量插入实体数据*/
    public void insertStuClass(Collection<TeacherClass> teachClasses){
        batchInsert(teachClasses);
    }

    /*插入单个实体*/
    public void insertSingle(TeacherClass teachClass){
        ArrayList<TeacherClass> classes=new ArrayList<TeacherClass>();
        classes.add(teachClass);
        insertStuClass(classes);
    }

    /*批量删除实体*/
    public void delete(Collection<TeacherClass> teachClasses){
        batchDelete(teachClasses);
    }

    /*删除单个实体*/
    public void deleteSingle(TeacherClass teachClass){
        ArrayList<TeacherClass> classes=new ArrayList<TeacherClass>();
        classes.add(teachClass);
        batchDelete(classes);
    }

    /*单个实体查询*/
    public TeacherClass getBeanBySingle(Serializable bean, MatchMode mode){
        Collection<TeacherClass> teacherClasses= getBeansByBean(bean,mode);
        if (teacherClasses!=null&&!teacherClasses.isEmpty()){
            Iterator<TeacherClass> teacherClass=teacherClasses.iterator();
            return teacherClass.next();
        }
        return null;
    }




}
