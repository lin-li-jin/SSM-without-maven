package com.talent.forex.dao;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

import com.talent.forex.bean.domain.GroupMng;
import com.talent.hibernate.base.AbstractDaoImpl;
import com.talent.hibernate.base.DaoException;

public class GroupMngDao extends AbstractDaoImpl{
	
	private static final String BEAN_PACKAGE_NAME = "com.talent.isas.bean.domain.GroupMng";

	public GroupMngDao() {
	}

	public GroupMng getBeanById(Long id) throws DaoException {
		return (GroupMng) getBeanById(BEAN_PACKAGE_NAME, id, false);
	}

	public Collection getAll() throws DaoException {
		return getAll(BEAN_PACKAGE_NAME);
	}
	
	public void deleteByParams(String hqlName,ArrayList paraList){
		super.deleteOrUpdateByParams(hqlName, paraList);
	}
	
	public GroupMng getBeanByParams(String hqlName,ArrayList paraList) throws DaoException{
		return (GroupMng) getProtoBeanByParams(hqlName,paraList);
	}
	
	@Override
	public Collection getBeansByParams(String hqlName,ArrayList paraList) throws DaoException{
		
		return  super.getBeansByParams(hqlName,paraList);
	}
	
	public GroupMng getBeanByBean(GroupMng bean, MatchMode mode) throws DaoException{
		return (GroupMng) getProtoBeanByBean(bean, mode);
	}
	
	public GroupMngDao setOrder(Order order){
		return (GroupMngDao) super.addOrder(order);
	}
	
	public Collection getBeansByBean(GroupMng bean, MatchMode mode)
		throws DaoException {
	return super.getBeansByBean(bean, mode);
	}


}
