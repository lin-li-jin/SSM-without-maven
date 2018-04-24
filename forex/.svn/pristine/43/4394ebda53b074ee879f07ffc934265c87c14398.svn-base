package com.talent.forex.dao;


import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

import com.talent.forex.bean.domain.OtcForwardInfo;
import com.talent.forex.bean.domain.OtcSpotInfo;
import com.talent.hibernate.base.AbstractDaoImpl;
import com.talent.hibernate.base.DaoException;
import com.talent.hibernate.util.HibernateUtil;
/*
 * Amendment No.: FOEXAS007
 * Create By    : 陈整队
 * Description  : 远期询价交易
 * Modify Date  : 2014-07-24
 */
public class OtcForwardDao extends AbstractDaoImpl{
	
	private static final String BEAN_PACKAGE_NAME = "com.talent.forex.bean.domain.OtcForwardInfo";

	public OtcForwardDao(){
	}
	
	public OtcForwardInfo getBeanById(Long id) throws DaoException {
		return (OtcForwardInfo) getBeanById(BEAN_PACKAGE_NAME, id, false);
	}

	public Collection getAll() throws DaoException {
		return getAll(BEAN_PACKAGE_NAME);
	}
	
	public void deleteByParams(String hqlName,ArrayList paraList){
		super.deleteOrUpdateByParams(hqlName, paraList);
	}
	
	public OtcForwardInfo getBeanByParams(String hqlName,ArrayList paraList) throws DaoException{
		return (OtcForwardInfo) getProtoBeanByParams(hqlName,paraList);
	}
	
	@Override
	public Collection getBeansByParams(String hqlName,ArrayList paraList) throws DaoException{		
		return  super.getBeansByParams(hqlName,paraList);
	}
	
	public OtcForwardInfo getBeanByBean(OtcForwardInfo bean, MatchMode mode) throws DaoException{
		return (OtcForwardInfo) getProtoBeanByBean(bean, mode);
	}
	
	public OtcForwardDao setOrder(Order order){
		return (OtcForwardDao) super.addOrder(order);
	}
	
	public Collection getBeansByBean(OtcForwardInfo bean, MatchMode mode)throws DaoException {
		return super.getBeansByBean(bean, mode);
	}
	
	public void addBean(OtcForwardInfo bean)throws DaoException{
		super.makePersistent(bean, false);
	}
	
	public void updateBean(OtcForwardInfo bean)throws DaoException{
		super.updateBean(bean);
	}
	
	public void deleteBean(Collection beans)throws DaoException{
		super.batchDelete(beans);
	}
	
	public OtcForwardInfo getBeanByBean2(String tranNo, String provider) throws DaoException{
		Session session = HibernateUtil.getSession();
		
		String hql = "from OtcForwardInfo where TRAN_NO=:tranNo and PROVIDER=:provider";
		Query query = session.createQuery(hql);
		query.setString("tranNo", tranNo);
		query.setString("provider", provider);
		OtcForwardInfo bean = (OtcForwardInfo)query.list().get(0);
		
		//session.flush();
		session.clear();
		return bean;
	}
}