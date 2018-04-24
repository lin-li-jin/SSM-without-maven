package com.talent.forex.dao;


import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

import com.talent.forex.bean.domain.OtcSpotInfo;
import com.talent.hibernate.base.AbstractDaoImpl;
import com.talent.hibernate.base.DaoException;
import com.talent.hibernate.util.HibernateUtil;

/*
 * Amendment No.: FOEXAS006
 * Create By    : 陈整队
 * Description  : 即期询价交易
 * Modify Date  : 2014-07-24
 */
public class OtcSpotDao extends AbstractDaoImpl{

	private static final String BEAN_PACKAGE_NAME = "com.talent.forex.bean.domain.OtcSpotInfo";

	public OtcSpotDao(){
	}
	
	public OtcSpotInfo getBeanById(Long id) throws DaoException {
		return (OtcSpotInfo) getBeanById(BEAN_PACKAGE_NAME, id, false);
	}

	public Collection getAll() throws DaoException {
		return getAll(BEAN_PACKAGE_NAME);
	}
	
	public void deleteByParams(String hqlName,ArrayList paraList){
		super.deleteOrUpdateByParams(hqlName, paraList);
	}
	
	public OtcSpotInfo getBeanByParams(String hqlName,ArrayList paraList) throws DaoException{
		return (OtcSpotInfo) getProtoBeanByParams(hqlName,paraList);
	}
	
	@Override
	public Collection getBeansByParams(String hqlName,ArrayList paraList) throws DaoException{		
		return  super.getBeansByParams(hqlName,paraList);
	}
	
	public OtcSpotInfo getBeanByBean(OtcSpotInfo bean, MatchMode mode) throws DaoException{
		return (OtcSpotInfo) getProtoBeanByBean(bean, mode);
	}
	
	public OtcSpotDao setOrder(Order order){
		return (OtcSpotDao) super.addOrder(order);
	}
	
	public Collection getBeansByBean(OtcSpotInfo bean, MatchMode mode)throws DaoException {
		return super.getBeansByBean(bean, mode);
	}
	public void addBean(OtcSpotInfo bean)throws DaoException{
		super.makePersistent(bean, false);
	}
	public void updateBean(OtcSpotInfo bean)throws DaoException{
		super.updateBean(bean);
	}
	public void deleteBean(Collection beans)throws DaoException{
		super.batchDelete(beans);
	}
	public OtcSpotInfo getBeanByBean2(String tranNo, String provider) throws DaoException{
		Session session = HibernateUtil.getSession();
		
		String hql = "from OtcSpotInfo where TRAN_NO=:tranNo and PROVIDER=:provider";
		Query query = session.createQuery(hql);
		query.setString("tranNo", tranNo);
		query.setString("provider", provider);
		OtcSpotInfo bean = (OtcSpotInfo)query.list().get(0);
		
		//session.flush();
		session.clear();
		return bean;
	}
	
}