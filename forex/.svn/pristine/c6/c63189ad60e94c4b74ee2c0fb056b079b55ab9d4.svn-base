package com.talent.forex.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

import com.talent.hibernate.base.AbstractDaoImpl;
import com.talent.hibernate.base.DaoException;

public class ForeignTradeDao extends AbstractDaoImpl {

	@Override
	public Collection<?> getBeansByParams(String hqlName,ArrayList paraList) throws DaoException{
		return super.getBeansByParams(hqlName,paraList);
	}
	
	public Collection getAll() throws DaoException {
		return getAll();
	}
	
	public void deleteByParams(String hqlName,ArrayList paraList){
		super.deleteOrUpdateByParams(hqlName, paraList);
	}
	
	public Object getBeanByParams(String hqlName,ArrayList paraList) throws DaoException{
		return getProtoBeanByParams(hqlName,paraList);
	}
	
	public Object getBeanByBean(Object bean, MatchMode mode) throws DaoException{
		return getProtoBeanByBean((Serializable) bean, mode);
	}
	
	public ForeignTradeDao setOrder(Order order){
		return (ForeignTradeDao) super.addOrder(order);
	}
	
	public Collection getBeansByBean(Object bean, MatchMode mode)
		throws DaoException {
		return super.getBeansByBean((Serializable)bean, mode);
	}
	
}
