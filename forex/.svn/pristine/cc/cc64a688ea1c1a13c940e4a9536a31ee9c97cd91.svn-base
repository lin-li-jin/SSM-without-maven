package com.talent.forex.dao;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

import com.talent.forex.bean.domain.CodeTable;
import com.talent.forex.bean.domain.SysParam;
import com.talent.hibernate.base.AbstractDaoImpl;
import com.talent.hibernate.base.DaoException;

public class CodeTableDao extends AbstractDaoImpl{
	
	private static final String BEAN_PACKAGE_NAME = "com.talent.forex.bean.domain.CodeTable";

	public CodeTableDao() {
	}

	public CodeTable getBeanById(Long id) throws DaoException {
		return (CodeTable) getBeanById(BEAN_PACKAGE_NAME, id, false);
	}

	public Collection getAll() throws DaoException {
		return getAll(BEAN_PACKAGE_NAME);
	}
	
	public void deleteByParams(String hqlName,ArrayList paraList){
		super.deleteOrUpdateByParams(hqlName, paraList);
	}
	
	public SysParam getBeanByParams(String hqlName,ArrayList paraList) throws DaoException{
		return (SysParam) getProtoBeanByParams(hqlName,paraList);
	}
	
	@Override
	public Collection getBeansByParams(String hqlName,ArrayList paraList) throws DaoException{
		
		return  super.getBeansByParams(hqlName,paraList);
	}
	
	public CodeTable getBeanByBean(CodeTable bean, MatchMode mode) throws DaoException{
		return (CodeTable) getProtoBeanByBean(bean, mode);
	}
	
	public CodeTableDao setOrder(Order order){
		return (CodeTableDao) super.addOrder(order);
	}
	
	public Collection getBeansByBean(CodeTable bean, MatchMode mode)
		throws DaoException {
	return super.getBeansByBean(bean, mode);
	}


}
