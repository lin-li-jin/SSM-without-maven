package com.talent.forex.dao;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

import com.talent.forex.bean.domain.AccInfo;
import com.talent.hibernate.base.AbstractDaoImpl;
import com.talent.hibernate.base.DaoException;
import com.talent.hibernate.util.HibernateUtil;

public class AccInfoDao extends AbstractDaoImpl{
	
	private static final String BEAN_PACKAGE_NAME = "com.talent.forex.bean.domain.AccInfo";

	public AccInfoDao() {
	}

	public AccInfo getBeanById(Integer id) throws DaoException {
		return (AccInfo) getBeanById(BEAN_PACKAGE_NAME, id, false);
	}

	public Collection getAll() throws DaoException {
		return getAll(BEAN_PACKAGE_NAME);
	}
	
	public void deleteByParams(String hqlName,ArrayList paraList){
		super.deleteOrUpdateByParams(hqlName, paraList);
	}
	
	public AccInfo getBeanByParams(String hqlName,ArrayList paraList) throws DaoException{
		return (AccInfo) getProtoBeanByParams(hqlName,paraList);
	}
	
	@Override
	public Collection getBeansByParams(String hqlName,ArrayList paraList) throws DaoException{
		
		return  super.getBeansByParams(hqlName,paraList);
	}
	
	public AccInfo getBeanByBean(AccInfo bean, MatchMode mode) throws DaoException{
		return (AccInfo) getProtoBeanByBean(bean, mode);
	}
	
	public AccInfoDao setOrder(Order order){
		return (AccInfoDao) super.addOrder(order);
	}
	
	public Collection getBeansByBean(AccInfo bean, MatchMode mode)
		throws DaoException {
	return super.getBeansByBean(bean, mode);
	}

	public void updateBean(AccInfo bean) throws DaoException{
		super.updateBean(bean);
		HibernateUtil.getSession().flush();
	}

	public double getInitAmt(String userNum, String type, String ccy){
		Session session = HibernateUtil.getSession();
		
		String hql = "from AccInfo where USER_NUM=:userNum and ACC_TYPE=type and CCY=:ccy";
		Query query = session.createQuery(hql);
		query.setString("userNum", userNum);
		query.setString("type", type);
		query.setString("ccy", ccy);
		
		AccInfo accInfo = (AccInfo)query.list().get(0);
		
		return Double.parseDouble(accInfo.getOriginalAmt());
	}
}
