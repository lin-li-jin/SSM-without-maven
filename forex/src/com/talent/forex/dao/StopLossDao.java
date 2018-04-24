package com.talent.forex.dao;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

import com.talent.forex.bean.domain.StopLossInfo;
import com.talent.hibernate.base.AbstractDaoImpl;
import com.talent.hibernate.base.DaoException;
/*
 * Amendment No.: FOEXAS002
 * Create By    : lzc
 * Description  : 止亏交易
 * Modify Date  : 2014-07-24
 */
public class StopLossDao extends AbstractDaoImpl {
	
	private static final String BEAN_PACKAGE_NAME = "com.talent.forex.bean.domain.StopLossInfo";

	public StopLossDao() {
	}

	public StopLossInfo getBeanById(Long id) throws DaoException {
		return (StopLossInfo) getBeanById(BEAN_PACKAGE_NAME, id, false);
	}

	public Collection getAll() throws DaoException {
		return getAll(BEAN_PACKAGE_NAME);
	}
	
	public void deleteByParams(String hqlName,ArrayList paraList){
		super.deleteOrUpdateByParams(hqlName, paraList);
	}
	
	public StopLossInfo getBeanByParams(String hqlName,ArrayList paraList) throws DaoException{
		return (StopLossInfo) getProtoBeanByParams(hqlName,paraList);
	}
	
	@Override
	public Collection getBeansByParams(String hqlName,ArrayList paraList) throws DaoException{
		return  super.getBeansByParams(hqlName,paraList);
	}
	
	public StopLossInfo getBeanByBean(StopLossInfo bean, MatchMode mode) throws DaoException{
		return (StopLossInfo) getProtoBeanByBean(bean, mode);
	}
	
	public StopLossDao setOrder(Order order){
		return (StopLossDao) super.addOrder(order);
	}
	
	public Collection getBeansByBean(StopLossInfo bean, MatchMode mode)
		throws DaoException {
		return super.getBeansByBean(bean, mode);
	}
	
	/**
	 * 添加一条Stop Loss交易记录
	 */
	public void stopLossInfoSave(StopLossInfo s){
		this.makePersistent(s, false);
	}
}
