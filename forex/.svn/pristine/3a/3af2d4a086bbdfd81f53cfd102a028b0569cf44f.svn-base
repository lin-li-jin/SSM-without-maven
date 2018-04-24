package com.talent.forex.dao;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

import com.talent.forex.bean.domain.OtcSpotInfo;
import com.talent.forex.bean.domain.OtcSwapInfo;
import com.talent.hibernate.base.AbstractDaoImpl;
import com.talent.hibernate.base.DaoException;
import com.talent.hibernate.util.HibernateUtil;
/*
 * Amendment No.: FOEXAS008
 * Create By    : 陈整队
 * Description  : 掉期询价交易
 * Modify Date  : 2014-07-24
 */
public class OtcSwapDao extends AbstractDaoImpl{
	
	private static final String BEAN_PACKAGE_NAME = "com.talent.forex.bean.domain.OtcSwapInfo";

	public OtcSwapDao(){
		
	}
	public OtcSwapInfo getBeanById(Long id) throws DaoException {
		return (OtcSwapInfo) getBeanById(BEAN_PACKAGE_NAME, id, false);
	}

	public Collection getAll() throws DaoException {
		return getAll(BEAN_PACKAGE_NAME);
	}
	
	public void deleteByParams(String hqlName,ArrayList paraList){
		super.deleteOrUpdateByParams(hqlName, paraList);
	}
	
	public OtcSwapInfo getBeanByParams(String hqlName,ArrayList paraList) throws DaoException{
		return (OtcSwapInfo) getProtoBeanByParams(hqlName,paraList);
	}
	
	@Override
	public Collection getBeansByParams(String hqlName,ArrayList paraList) throws DaoException{		
		return  super.getBeansByParams(hqlName,paraList);
	}
	
	public OtcSwapInfo getBeanByBean(OtcSwapInfo bean, MatchMode mode) throws DaoException{
		return (OtcSwapInfo) getProtoBeanByBean(bean, mode);
	}
	
	public OtcSwapDao setOrder(Order order){
		return (OtcSwapDao) super.addOrder(order);
	}
	
	public Collection getBeansByBean(OtcSwapInfo bean, MatchMode mode)throws DaoException {
		return super.getBeansByBean(bean, mode);
	}
	
	public void addBean(OtcSwapInfo bean)throws DaoException{
		super.makePersistent(bean, false);
	}
	
	public void updateBean(OtcSwapInfo bean)throws DaoException{
		super.updateBean(bean);
	}
	
	public void deleteBean(Collection beans)throws DaoException{
		super.batchDelete(beans);
	}
	
	public OtcSwapInfo getBeanByBean2(String tranNo, String provider) throws DaoException{
		Session session = HibernateUtil.getSession();
		
		String hql = "from OtcSwapInfo where TRAN_NO=:tranNo and PROVIDER=:provider";
		Query query = session.createQuery(hql);
		query.setString("tranNo", tranNo);
		query.setString("provider", provider);
		OtcSwapInfo bean = (OtcSwapInfo)query.list().get(0);
		
		//session.flush();
		session.clear();
		return bean;
	}
	
	public List<OtcSwapInfo> getBeansByUserNum(String userNum){
		Session session = HibernateUtil.getSession();
		
		String hql = "from OtcSwapInfo where USER_NUM=:userNum order by CREATE_DATETIME desc";
		Query query = session.createQuery(hql);
		query.setString("userNum", userNum);
		List<OtcSwapInfo> list = query.list();
		
		return list;
	}
}