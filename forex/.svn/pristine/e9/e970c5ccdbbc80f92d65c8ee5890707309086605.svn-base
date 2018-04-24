package com.talent.forex.dao;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

import com.talent.forex.bean.domain.TakeProfitInfo;
import com.talent.hibernate.base.AbstractDaoImpl;
import com.talent.hibernate.base.DaoException;
/*
 * Amendment No.: FOEXAS003
 * Create By    : lzc
 * Description  : 止盈交易
 * Modify Date  : 2014-07-24
 */
public class TakeProfitDao extends AbstractDaoImpl {

	private static final String BEAN_PACKAGE_NAME = "com.talent.forex.bean.domain.TakeProfitInfo";

	public TakeProfitDao() {
	}

	public TakeProfitInfo getBeanById(Long id) throws DaoException {
		return (TakeProfitInfo) getBeanById(BEAN_PACKAGE_NAME, id, false);
	}

	public Collection getAll() throws DaoException {
		return getAll(BEAN_PACKAGE_NAME);
	}
	
	public void deleteByParams(String hqlName,ArrayList paraList){
		super.deleteOrUpdateByParams(hqlName, paraList);
	}
	
	public TakeProfitInfo getBeanByParams(String hqlName,ArrayList paraList) throws DaoException{
		return (TakeProfitInfo) getProtoBeanByParams(hqlName,paraList);
	}
	
	@Override
	public Collection getBeansByParams(String hqlName,ArrayList paraList) throws DaoException{
		
		return  super.getBeansByParams(hqlName,paraList);
	}
	
	public TakeProfitInfo getBeanByBean(TakeProfitInfo bean, MatchMode mode) throws DaoException{
		return (TakeProfitInfo) getProtoBeanByBean(bean, mode);
	}
	
	public TakeProfitDao setOrder(Order order){
		return (TakeProfitDao) super.addOrder(order);
	}
	
	public Collection getBeansByBean(TakeProfitInfo bean, MatchMode mode)
		throws DaoException {
		return super.getBeansByBean(bean, mode);
	}
	
	/**
	 * 添加一条Take Profit交易记录
	 */
	public void takeProfitInfoAdd(TakeProfitInfo t){
		this.makePersistent(t, false);
	}
}
