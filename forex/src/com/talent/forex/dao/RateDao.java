package com.talent.forex.dao;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

import com.talent.forex.bean.domain.OtcSpotInfo;
import com.talent.forex.bean.domain.Rate;
import com.talent.hibernate.base.AbstractDaoImpl;
import com.talent.hibernate.base.DaoException;
import com.talent.hibernate.util.HibernateUtil;

public class RateDao extends AbstractDaoImpl{
	
	private static final String BEAN_PACKAGE_NAME = "com.talent.forex.bean.domain.Rate";

	public RateDao() {
	}
	
	public void addBean(Rate bean)throws DaoException{
		super.makePersistent(bean, false);
	}

	public Rate getBeanById(Long id) throws DaoException {
		return (Rate) getBeanById(BEAN_PACKAGE_NAME, id, false);
	}

	public Collection getAll() throws DaoException {
		return getAll(BEAN_PACKAGE_NAME);
	}
	
	public void deleteByParams(String hqlName,ArrayList paraList){
		super.deleteOrUpdateByParams(hqlName, paraList);
	}
	
	public Rate getBeanByParams(String hqlName,ArrayList paraList) throws DaoException{
		return (Rate) getProtoBeanByParams(hqlName,paraList);
	}
	
	@Override
	public Collection getBeansByParams(String hqlName,ArrayList paraList) throws DaoException{
		
		return  super.getBeansByParams(hqlName,paraList);
	}
	
	public Rate getBeanByBean(Rate bean, MatchMode mode) throws DaoException{
		return (Rate) getProtoBeanByBean(bean, mode);
	}
	
	public RateDao setOrder(Order order){
		return (RateDao) super.addOrder(order);
	}
	
	public Collection getBeansByBean(Rate bean, MatchMode mode)
		throws DaoException {
	return super.getBeansByBean(bean, mode);
	}

	public void updateBean(Rate bean) throws DaoException{
		super.updateBean(bean);
	}
	
	public Rate getBeanByParams(String accType, String src, String tar, String direction){
		Session session = HibernateUtil.getSession();
		
		String hql = "from Rate where ACC_TYPE=:accType and SOURCE_CCY=:src and TARGET_CCY=:tar and DIRECTION=:direction";
		Query query = session.createQuery(hql);
		query.setString("accType", accType);
		query.setString("src", src);
		query.setString("tar", tar);
		query.setString("direction", direction);
		Rate bean = (Rate)query.list().get(0);
		
		return bean;
	}

}
