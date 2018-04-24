package com.talent.forex.dao;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

import com.talent.forex.bean.domain.OcoInfo;
import com.talent.hibernate.base.AbstractDaoImpl;
import com.talent.hibernate.base.DaoException;
/*
 * Amendment No.: FOEXAS004
 * Create By    : lzc
 * Description  : 自动替代单交易
 * Modify Date  : 2014-07-24
 */
public class OcoDao extends AbstractDaoImpl {

	private static final String BEAN_PACKAGE_NAME = "com.talent.forex.bean.domain.OcoInfo";

	public OcoDao() {
	}

	public OcoInfo getBeanById(Long id) throws DaoException {
		return (OcoInfo) getBeanById(BEAN_PACKAGE_NAME, id, false);
	}

	public Collection getAll() throws DaoException {
		return getAll(BEAN_PACKAGE_NAME);
	}
	
	public void deleteByParams(String hqlName,ArrayList paraList){
		super.deleteOrUpdateByParams(hqlName, paraList);
	}
	
	public OcoInfo getBeanByParams(String hqlName,ArrayList paraList) throws DaoException{
		return (OcoInfo) getProtoBeanByParams(hqlName,paraList);
	}
	
	@Override
	public Collection getBeansByParams(String hqlName,ArrayList paraList) throws DaoException{
		
		return  super.getBeansByParams(hqlName,paraList);
	}
	
	public OcoInfo getBeanByBean(OcoInfo bean, MatchMode mode) throws DaoException{
		return (OcoInfo) getProtoBeanByBean(bean, mode);
	}
	
	public OcoDao setOrder(Order order){
		return (OcoDao) super.addOrder(order);
	}
	
	public Collection getBeansByBean(OcoInfo bean, MatchMode mode)
		throws DaoException {
		return super.getBeansByBean(bean, mode);
	}
	
	/**
	 * 添加一条OCO交易记录
	 */
	public void ocoInfoAdd(OcoInfo o){
		this.makePersistent(o, false);
	}
}
