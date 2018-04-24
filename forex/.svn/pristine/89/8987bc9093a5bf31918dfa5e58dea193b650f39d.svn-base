package com.talent.forex.dao;

import java.util.ArrayList;
import java.util.Collection;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

import com.talent.forex.bean.domain.MarginEnlarge;
import com.talent.forex.bean.domain.MarginEnlarge;
import com.talent.hibernate.base.AbstractDaoImpl;
import com.talent.hibernate.base.DaoException;
/**
 * Amendment No.: FOEXAS013
 * Create By    : atggdsaiDong
 * Description  : 保证金远期交易
 * Modify Date  : 2014-07-29
 */
public class MarginEnlargeDao extends AbstractDaoImpl{
	
	
	private static final String BEAN_PACKAGE_NAME = "com.talent.forex.bean.domain.MarginEnlarge";

	public MarginEnlargeDao() {
	}

	public MarginEnlarge getBeanById(Long id) throws DaoException {
		return (MarginEnlarge) getBeanById(BEAN_PACKAGE_NAME, id, false);
	}

	public Collection getAll() throws DaoException {
		return getAll(BEAN_PACKAGE_NAME);
	}
	
	public void deleteByParams(String hqlName,ArrayList paraList){
		super.deleteOrUpdateByParams(hqlName, paraList);
	}
	
	public MarginEnlarge getBeanByParams(String hqlName,ArrayList paraList) throws DaoException{
		return (MarginEnlarge) getProtoBeanByParams(hqlName,paraList);
	}
	
	@Override
	public Collection getBeansByParams(String hqlName,ArrayList paraList) throws DaoException{
		
		return  super.getBeansByParams(hqlName,paraList);
	}
	
	public MarginEnlarge getBeanByBean(MarginEnlarge bean, MatchMode mode) throws DaoException{
		return (MarginEnlarge) getProtoBeanByBean(bean, mode);
	}
	
	public MarginEnlargeDao setOrder(Order order){
		return (MarginEnlargeDao) super.addOrder(order);
	}
	
	public Collection getBeansByBean(MarginEnlarge bean, MatchMode mode)
		throws DaoException {
	return super.getBeansByBean(bean, mode);
	}

}
