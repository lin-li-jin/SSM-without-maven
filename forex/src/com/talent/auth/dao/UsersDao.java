package com.talent.auth.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.talent.forex.util.PageBean;
import com.talent.hibernate.util.HibernateUtil;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;

import com.talent.auth.bean.domain.Users;
import com.talent.hibernate.base.AbstractDaoImpl;
import com.talent.hibernate.base.DaoException;


public class UsersDao extends AbstractDaoImpl {

	private static final String BEAN_PACKAGE_NAME = "com.talent.auth.bean.domain.Users";

	public UsersDao() {
	}

	public Users getBeanById(Long id) throws DaoException {
		return (Users) getBeanById(BEAN_PACKAGE_NAME, id, false);
	}

	public Collection getAll() throws DaoException {
		return getAll(BEAN_PACKAGE_NAME);
	}
	
	public Collection getBeansByBean(Users bean, MatchMode mode)
			throws DaoException {
		return super.getBeansByBean(bean, mode);
	}

	public Users getBeanByParams(String hqlName, ArrayList paraList)
			throws DaoException {
		return (Users) getProtoBeanByParams(hqlName, paraList);
	}

	public Users getBeanByBean(Users bean, MatchMode mode)
			throws DaoException {
		return (Users) getProtoBeanByBean(bean, mode);
	}

	//�ṩ���뵥��ʵ��
	public void insertSingle(Users bean){
		ArrayList<Users> users=new ArrayList<Users>();
		users.add(bean);
		batchInsert(users);
	}

	//����ĳ��ѧ��
	public void updataUserNum(Users stu){
		Session session=HibernateUtil.getSession();
		session.merge(stu);
	}
	/**
	 * ��ҳ��ѯѧ��
	 * @param pageBean
	 */
	public void pageQuery(PageBean pageBean) {
		
		Criteria criteria = pageBean.getDetachedCriteria().getExecutableCriteria(HibernateUtil.getSession());
		//���ò�ѯ������
		criteria.setProjection(Projections.rowCount());
		List<Integer> count = criteria.list();
		if(count!=null&&count.size()>0){
			pageBean.setTotal(count.get(0));
		}
		//���ͳ�Ʋ�ѯ����
		criteria.setProjection(null);
		//���÷�ҳ��ѯ����
		criteria.setFirstResult( (pageBean.getCurrentPage()-1)*pageBean.getPageSize());
		criteria.setMaxResults(pageBean.getPageSize());
		List students = criteria.list();
		//���������Ž�list��һ�𷵻�
		pageBean.setRows(students);
		
	}
}
