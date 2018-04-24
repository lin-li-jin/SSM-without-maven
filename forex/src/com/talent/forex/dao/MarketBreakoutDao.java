package com.talent.forex.dao;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

import com.talent.forex.bean.domain.MarketBreakoutInfo;
import com.talent.hibernate.base.AbstractDaoImpl;
import com.talent.hibernate.base.DaoException;
/*
 * Amendment No.: FOEXAS005
 * Create By    : lzc
 * Description  : MarketBreakout交易
 * Modify Date  : 2014-07-24
 */
public class MarketBreakoutDao extends AbstractDaoImpl {

	private static final String BEAN_PACKAGE_NAME = "com.talent.forex.bean.domain.MarketBreakoutInfo";

	public MarketBreakoutDao() {
	}

	public MarketBreakoutInfo getBeanById(Long id) throws DaoException {
		return (MarketBreakoutInfo) getBeanById(BEAN_PACKAGE_NAME, id, false);
	}

	public Collection getAll() throws DaoException {
		return getAll(BEAN_PACKAGE_NAME);
	}
	
	public void deleteByParams(String hqlName,ArrayList paraList){
		super.deleteOrUpdateByParams(hqlName, paraList);
	}
	
	public MarketBreakoutInfo getBeanByParams(String hqlName,ArrayList paraList) throws DaoException{
		return (MarketBreakoutInfo) getProtoBeanByParams(hqlName,paraList);
	}
	
	@Override
	public Collection getBeansByParams(String hqlName,ArrayList paraList) throws DaoException{
		
		return  super.getBeansByParams(hqlName,paraList);
	}
	
	public MarketBreakoutInfo getBeanByBean(MarketBreakoutInfo bean, MatchMode mode) throws DaoException{
		return (MarketBreakoutInfo) getProtoBeanByBean(bean, mode);
	}
	
	public MarketBreakoutDao setOrder(Order order){
		return (MarketBreakoutDao) super.addOrder(order);
	}
	
	public Collection getBeansByBean(MarketBreakoutInfo bean, MatchMode mode)
		throws DaoException {
		return super.getBeansByBean(bean, mode);
	}
	
	/**
	 * 添加一条交易
	 */
	public void marketBreakoutInfoAdd(MarketBreakoutInfo m){
		this.makePersistent(m, false);
	}
}
