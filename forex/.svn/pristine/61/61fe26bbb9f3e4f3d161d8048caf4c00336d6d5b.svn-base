package com.talent.forex.dao;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

import com.talent.forex.bean.domain.Seq;
import com.talent.hibernate.base.AbstractDaoImpl;
import com.talent.hibernate.base.DaoException;

public class SeqDao extends AbstractDaoImpl{
	
	private static final String BEAN_PACKAGE_NAME = "com.talent.isas.bean.domain.Seq";

	public SeqDao() {
	}

	public Seq getBeanById(Long id) throws DaoException {
		return (Seq) getBeanById(BEAN_PACKAGE_NAME, id, false);
	}

	public Collection getAll() throws DaoException {
		return getAll(BEAN_PACKAGE_NAME);
	}
	
	public void deleteByParams(String hqlName,ArrayList paraList){
		super.deleteOrUpdateByParams(hqlName, paraList);
	}
	
	public Seq getBeanByParams(String hqlName,ArrayList paraList) throws DaoException{
		return (Seq) getProtoBeanByParams(hqlName,paraList);
	}
	
	@Override
	public Collection getBeansByParams(String hqlName,ArrayList paraList) throws DaoException{
		
		return  super.getBeansByParams(hqlName,paraList);
	}
	
	public Seq getBeanByBean(Seq bean, MatchMode mode) throws DaoException{
		return (Seq) getProtoBeanByBean(bean, mode);
	}
	
	public SeqDao setOrder(Order order){
		return (SeqDao) super.addOrder(order);
	}
	
	public Collection getBeansByBean(Seq bean, MatchMode mode)
		throws DaoException {
	return super.getBeansByBean(bean, mode);
	}


}
