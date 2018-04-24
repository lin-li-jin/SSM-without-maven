package com.talent.forex.dao;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

import com.talent.forex.bean.domain.CTranFlowMapping;
import com.talent.hibernate.base.AbstractDaoImpl;
import com.talent.hibernate.base.DaoException;
import com.talent.hibernate.util.HibernateUtil;

public class CTranFlowMappingDao extends AbstractDaoImpl{
	
	private static final String BEAN_PACKAGE_NAME = "com.talent.forex.bean.domain.CTranFlowMapping";

	public CTranFlowMappingDao() {
	}

	public CTranFlowMapping getBeanById(Long id) throws DaoException {
		return (CTranFlowMapping) getBeanById(BEAN_PACKAGE_NAME, id, false);
	}

	public Collection getAll() throws DaoException {
		return getAll(BEAN_PACKAGE_NAME);
	}
	
	public void deleteByParams(String hqlName,ArrayList paraList){
		super.deleteOrUpdateByParams(hqlName, paraList);
	}
	
	public CTranFlowMapping getBeanByParams(String hqlName,ArrayList paraList) throws DaoException{
		return (CTranFlowMapping) getProtoBeanByParams(hqlName,paraList);
	}
	
	@Override
	public Collection getBeansByParams(String hqlName,ArrayList paraList) throws DaoException{
		
		return  super.getBeansByParams(hqlName,paraList);
	}
	
	public CTranFlowMapping getBeanByBean(CTranFlowMapping bean, MatchMode mode) throws DaoException{
		return (CTranFlowMapping) getProtoBeanByBean(bean, mode);
	}
	
	public CTranFlowMappingDao setOrder(Order order){
		return (CTranFlowMappingDao) super.addOrder(order);
	}
	
	public Collection getBeansByBean(CTranFlowMapping bean, MatchMode mode)
		throws DaoException {
	return super.getBeansByBean(bean, mode);
	}
	
	public void updateBean(CTranFlowMapping bean) throws DaoException{
		super.updateBean(bean);
		HibernateUtil.getSession().flush();
	}

}
