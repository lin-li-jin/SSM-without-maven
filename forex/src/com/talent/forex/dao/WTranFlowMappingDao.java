package com.talent.forex.dao;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

import com.talent.forex.bean.domain.WTranFlowMapping;
import com.talent.hibernate.base.AbstractDaoImpl;
import com.talent.hibernate.base.DaoException;
import com.talent.hibernate.util.HibernateUtil;

public class WTranFlowMappingDao extends AbstractDaoImpl{
	
	private static final String BEAN_PACKAGE_NAME = "com.talent.forex.bean.domain.WTranFlowMapping";

	public WTranFlowMappingDao() {
	}

	public WTranFlowMapping getBeanById(Long id) throws DaoException {
		return (WTranFlowMapping) getBeanById(BEAN_PACKAGE_NAME, id, false);
	}

	public Collection getAll() throws DaoException {
		return getAll(BEAN_PACKAGE_NAME);
	}
	
	public void deleteByParams(String hqlName,ArrayList paraList){
		super.deleteOrUpdateByParams(hqlName, paraList);
	}
	
	public WTranFlowMapping getBeanByParams(String hqlName,ArrayList paraList) throws DaoException{
		return (WTranFlowMapping) getProtoBeanByParams(hqlName,paraList);
	}
	
	@Override
	public Collection getBeansByParams(String hqlName,ArrayList paraList) throws DaoException{
		
		return  super.getBeansByParams(hqlName,paraList);
	}
	
	public WTranFlowMapping getBeanByBean(WTranFlowMapping bean, MatchMode mode) throws DaoException{
		return (WTranFlowMapping) getProtoBeanByBean(bean, mode);
	}
	
	public WTranFlowMappingDao setOrder(Order order){
		return (WTranFlowMappingDao) super.addOrder(order);
	}
	
	public Collection getBeansByBean(WTranFlowMapping bean, MatchMode mode)
		throws DaoException {
	return super.getBeansByBean(bean, mode);
	}
	
	public void updateBean(WTranFlowMapping bean) throws DaoException{
		super.updateBean(bean);
		HibernateUtil.getSession().flush();
	}

}
