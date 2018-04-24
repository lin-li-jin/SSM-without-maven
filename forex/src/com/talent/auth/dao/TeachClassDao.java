package com.talent.auth.dao;

import com.talent.auth.bean.domain.TeacherClass;
import com.talent.hibernate.base.AbstractDaoImpl;
import org.hibernate.criterion.MatchMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * teacher_class��
 * Created by ���� on www.haixiangzhene.xyz
 * 2017/8/16.
 */
public class TeachClassDao extends AbstractDaoImpl {

    /*���ݽ�ʦid��*/
    public TeacherClass getBySId(Integer tId){
        TeacherClass teacherClass=new TeacherClass();
        teacherClass.settId(tId);
        return (TeacherClass) getProtoBeanByBean(teacherClass, MatchMode.EXACT);
    }

    /*���ݰ༶id��*/
    public TeacherClass getByClassId(Integer teachId){
        TeacherClass teacherClass=new TeacherClass();
        teacherClass.setClassId(teachId);
        return (TeacherClass) getProtoBeanByBean(teacherClass,MatchMode.EXACT);
    }

    /*��������ʵ������*/
    public void insertStuClass(Collection<TeacherClass> teachClasses){
        batchInsert(teachClasses);
    }

    /*���뵥��ʵ��*/
    public void insertSingle(TeacherClass teachClass){
        ArrayList<TeacherClass> classes=new ArrayList<TeacherClass>();
        classes.add(teachClass);
        insertStuClass(classes);
    }

    /*����ɾ��ʵ��*/
    public void delete(Collection<TeacherClass> teachClasses){
        batchDelete(teachClasses);
    }

    /*ɾ������ʵ��*/
    public void deleteSingle(TeacherClass teachClass){
        ArrayList<TeacherClass> classes=new ArrayList<TeacherClass>();
        classes.add(teachClass);
        batchDelete(classes);
    }

    /*����ʵ���ѯ*/
    public TeacherClass getBeanBySingle(Serializable bean, MatchMode mode){
        Collection<TeacherClass> teacherClasses= getBeansByBean(bean,mode);
        if (teacherClasses!=null&&!teacherClasses.isEmpty()){
            Iterator<TeacherClass> teacherClass=teacherClasses.iterator();
            return teacherClass.next();
        }
        return null;
    }




}
