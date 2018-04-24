package com.talent.forex.dao;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

import com.talent.forex.bean.domain.GroupSysParam;
import com.talent.hibernate.base.AbstractDaoImpl;
import com.talent.hibernate.base.DaoException;

public class GroupSysParamDao extends AbstractDaoImpl{
	
	private static final String BEAN_PACKAGE_NAME = "com.talent.isas.bean.domain.GroupSysParam";

	public GroupSysParamDao() {
	}

	public GroupSysParam getBeanById(Long id) throws DaoException {
		return (GroupSysParam) getBeanById(BEAN_PACKAGE_NAME, id, false);
	}

	public Collection getAll() throws DaoException {
		return getAll(BEAN_PACKAGE_NAME);
	}
	
	public void deleteByParams(String hqlName,ArrayList paraList){
		super.deleteOrUpdateByParams(hqlName, paraList);
	}
	
	public GroupSysParam getBeanByParams(String hqlName,ArrayList paraList) throws DaoException{
		return (GroupSysParam) getProtoBeanByParams(hqlName,paraList);
	}
	
	@Override
	public Collection getBeansByParams(String hqlName,ArrayList paraList) throws DaoException{
		
		return  super.getBeansByParams(hqlName,paraList);
	}
	
	public GroupSysParam getBeanByBean(GroupSysParam bean, MatchMode mode) throws DaoException{
		return (GroupSysParam) getProtoBeanByBean(bean, mode);
	}
	
	public GroupSysParamDao setOrder(Order order){
		return (GroupSysParamDao) super.addOrder(order);
	}
	
	public Collection getBeansByBean(GroupSysParam bean, MatchMode mode)
		throws DaoException {
	return super.getBeansByBean(bean, mode);
	}


}
