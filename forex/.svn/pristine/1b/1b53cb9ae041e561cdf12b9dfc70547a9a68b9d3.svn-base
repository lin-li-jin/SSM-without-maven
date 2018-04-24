package com.talent.forex.dao;


import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

import com.talent.forex.bean.domain.AnalogueMag;
import com.talent.hibernate.base.AbstractDaoImpl;
import com.talent.hibernate.base.DaoException;
/*
 * Amendment No.: FOEXAS006
 * Create By    : 陈整队
 * Description  : 即期询价交易
 * Modify Date  : 2014-07-24
 */
public class AnalogueMagDao extends AbstractDaoImpl{
	
	private static final String BEAN_PACKAGE_NAME = "com.talent.forex.bean.domain.AnalogueMag";

	public AnalogueMagDao(){
	}
	
	public AnalogueMag getBeanById(Long id) throws DaoException {
		return (AnalogueMag) getBeanById(BEAN_PACKAGE_NAME, id, false);
	}

	public Collection getAll() throws DaoException {
		return getAll(BEAN_PACKAGE_NAME);
	}
	
	public void deleteByParams(String hqlName,ArrayList paraList){
		super.deleteOrUpdateByParams(hqlName, paraList);
	}
	
	public AnalogueMag getBeanByParams(String hqlName,ArrayList paraList) throws DaoException{
		return (AnalogueMag) getProtoBeanByParams(hqlName,paraList);
	}
	
	@Override
	public Collection getBeansByParams(String hqlName,ArrayList paraList) throws DaoException{		
		return  super.getBeansByParams(hqlName,paraList);
	}
	
	public AnalogueMag getBeanByBean(AnalogueMag bean, MatchMode mode) throws DaoException{
		return (AnalogueMag) getProtoBeanByBean(bean, mode);
	}
	
	public AnalogueMagDao setOrder(Order order){
		return (AnalogueMagDao) super.addOrder(order);
	}
	
	public Collection getBeansByBean(AnalogueMag bean, MatchMode mode)
		throws DaoException {
	return super.getBeansByBean(bean, mode);
	}
}