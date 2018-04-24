package com.talent.forex.dao;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

import com.talent.forex.bean.domain.AccInfo;
import com.talent.hibernate.base.AbstractDaoImpl;
import com.talent.hibernate.base.DaoException;

public class CompletePriceTranInfoDao extends AbstractDaoImpl {
	
	private static final String BEAN_PACKAGE_NAME = "com.talent.forex.bean.view.CompletePriceTranInfo";

	public CompletePriceTranInfoDao() {
	}

	public AccInfo getBeanById(Long id) throws DaoException {
		return (AccInfo) getBeanById(BEAN_PACKAGE_NAME, id, false);
	}

	public Collection getAll() throws DaoException {
		return getAll(BEAN_PACKAGE_NAME);
	}
	
	public void deleteByParams(String hqlName,ArrayList paraList){
		super.deleteOrUpdateByParams(hqlName, paraList);
	}
	
	public AccInfo getBeanByParams(String hqlName,ArrayList paraList) throws DaoException{
		return (AccInfo) getProtoBeanByParams(hqlName,paraList);
	}
	
	@Override
	public Collection getBeansByParams(String hqlName, ArrayList paraList) throws DaoException{
		
		return super.getBeansByParams(hqlName,paraList);
	}
	
	public AccInfo getBeanByBean(AccInfo bean, MatchMode mode) throws DaoException{
		return (AccInfo) getProtoBeanByBean(bean, mode);
	}
	
	public CompletePriceTranInfoDao setOrder(Order order){
		return (CompletePriceTranInfoDao) super.addOrder(order);
	}
	
	public Collection getBeansByBean(AccInfo bean, MatchMode mode)
		throws DaoException {
	return super.getBeansByBean(bean, mode);
	}

	public void updateBean(AccInfo bean) throws DaoException{
		super.updateBean(bean);
	}

}
