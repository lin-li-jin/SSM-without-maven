package com.talent.forex.dao;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

import com.talent.forex.bean.domain.OneClickInfo;
import com.talent.hibernate.base.AbstractDaoImpl;
import com.talent.hibernate.base.DaoException;
/*
 * Amendment No.: FOEXAS001
 * Create By    : lzc
 * Description  : 一口价交易
 * Modify Date  : 2014-07-24
 */
public class OneClickDao extends AbstractDaoImpl {

	private static final String BEAN_PACKAGE_NAME = "com.talent.forex.bean.domain.OneClickInfo";

	public OneClickDao() {
	}

	public OneClickInfo getBeanById(Long id) throws DaoException {
		return (OneClickInfo) getBeanById(BEAN_PACKAGE_NAME, id, false);
	}

	public Collection getAll() throws DaoException {
		return getAll(BEAN_PACKAGE_NAME);
	}
	
	public void deleteByParams(String hqlName,ArrayList paraList){
		super.deleteOrUpdateByParams(hqlName, paraList);
	}
	
	public OneClickInfo getBeanByParams(String hqlName,ArrayList paraList) throws DaoException{
		return (OneClickInfo) getProtoBeanByParams(hqlName,paraList);
	}
	
	@Override
	public Collection getBeansByParams(String hqlName,ArrayList paraList) throws DaoException{
		return  super.getBeansByParams(hqlName,paraList);
	}
	
	public OneClickInfo getBeanByBean(OneClickInfo bean, MatchMode mode) throws DaoException{
		return (OneClickInfo) getProtoBeanByBean(bean, mode);
	}
	
	public OneClickDao setOrder(Order order){
		return (OneClickDao) super.addOrder(order);
	}
	
	public Collection getBeansByBean(OneClickInfo bean, MatchMode mode)
		throws DaoException {
		return super.getBeansByBean(bean, mode);
	}
	
	/**
	 * 添加一条一口价交易记录
	 */
	public void oneClickInfoAdd(OneClickInfo o){
		this.makePersistent(o, false);
	}
}
