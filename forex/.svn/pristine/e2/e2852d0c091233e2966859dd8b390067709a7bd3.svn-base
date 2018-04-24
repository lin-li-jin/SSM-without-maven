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

	//提供插入单个实体
	public void insertSingle(Users bean){
		ArrayList<Users> users=new ArrayList<Users>();
		users.add(bean);
		batchInsert(users);
	}

	//更新某个学生
	public void updataUserNum(Users stu){
		Session session=HibernateUtil.getSession();
		session.merge(stu);
	}
	/**
	 * 分页查询学生
	 * @param pageBean
	 */
	public void pageQuery(PageBean pageBean) {
		
		Criteria criteria = pageBean.getDetachedCriteria().getExecutableCriteria(HibernateUtil.getSession());
		//设置查询总条数
		criteria.setProjection(Projections.rowCount());
		List<Integer> count = criteria.list();
		if(count!=null&&count.size()>0){
			pageBean.setTotal(count.get(0));
		}
		//清楚统计查询条件
		criteria.setProjection(null);
		//设置分页查询条件
		criteria.setFirstResult( (pageBean.getCurrentPage()-1)*pageBean.getPageSize());
		criteria.setMaxResults(pageBean.getPageSize());
		List students = criteria.list();
		//将总条数放进list中一起返回
		pageBean.setRows(students);
		
	}
}
