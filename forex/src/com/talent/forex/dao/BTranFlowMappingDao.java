package com.talent.forex.dao;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;


import com.talent.forex.bean.domain.BTranFlowMapping;
import com.talent.hibernate.base.AbstractDaoImpl;
import com.talent.hibernate.base.DaoException;

public class BTranFlowMappingDao extends AbstractDaoImpl{
	
	private static final String BEAN_PACKAGE_NAME = "com.talent.forex.bean.domain.BTranFlowMapping";

	public BTranFlowMappingDao() {
	}

	public BTranFlowMapping getBeanById(Long id) throws DaoException {
		return (BTranFlowMapping) getBeanById(BEAN_PACKAGE_NAME, id, false);
	}

	public Collection getAll() throws DaoException {
		return getAll(BEAN_PACKAGE_NAME);
	}
	
	public void deleteByParams(String hqlName,ArrayList paraList){
		super.deleteOrUpdateByParams(hqlName, paraList);
	}
	
	public BTranFlowMapping getBeanByParams(String hqlName,ArrayList paraList) throws DaoException{
		return (BTranFlowMapping) getProtoBeanByParams(hqlName,paraList);
	}
	
	@Override
	public Collection getBeansByParams(String hqlName,ArrayList paraList) throws DaoException{
		
		return  super.getBeansByParams(hqlName,paraList);
	}
	
	public BTranFlowMapping getBeanByBean(BTranFlowMapping bean, MatchMode mode) throws DaoException{
		return (BTranFlowMapping) getProtoBeanByBean(bean, mode);
	}
	
	public BTranFlowMappingDao setOrder(Order order){
		return (BTranFlowMappingDao) super.addOrder(order);
	}
	
	public Collection getBeansByBean(BTranFlowMapping bean, MatchMode mode)
		throws DaoException {
	return super.getBeansByBean(bean, mode);
	}
	
	public void updateBean(BTranFlowMapping bean) throws DaoException{
		super.updateBean(bean);
	}

}
