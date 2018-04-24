package com.talent.forex.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.criterion.MatchMode;

import com.talent.forex.bean.domain.MarginOptionInfo;
import com.talent.forex.bean.domain.SysParam;
import com.talent.hibernate.base.AbstractDaoImpl;
import com.talent.hibernate.base.DaoException;

public class MarginOptionInfoDao extends AbstractDaoImpl{
	
	
	private static final String BEAN_PACKAGE_NAME = "com.talent.forex.bean.domain.MarginOptionInfo";



	public MarginOptionInfo getBeanById(Long id) throws DaoException {
		return (MarginOptionInfo) getBeanById(BEAN_PACKAGE_NAME, id, false);
	}

	public Collection getAll() throws DaoException {
		return getAll(BEAN_PACKAGE_NAME);
	}
	
	public void deleteByParams(String hqlName,ArrayList paraList){
		super.deleteOrUpdateByParams(hqlName, paraList);
	}
	
	public MarginOptionInfo getBeanByParams(String hqlName,ArrayList paraList) throws DaoException{
		return (MarginOptionInfo) getProtoBeanByParams(hqlName,paraList);
	}
	
	@Override
	public Collection getBeansByParams(String hqlName,ArrayList paraList) throws DaoException{
		
		return  super.getBeansByParams(hqlName,paraList);
	}
	
	public MarginOptionInfo getBeanByBean(MarginOptionInfo bean, MatchMode mode) throws DaoException{
		return (MarginOptionInfo) getProtoBeanByBean(bean, mode);
	}
	

	
	public Collection getBeansByBean(MarginOptionInfo bean, MatchMode mode)
		throws DaoException {
	return super.getBeansByBean(bean, mode);
	}

	@Override
	public void makePersistent(Serializable bean, boolean flag)
			throws DaoException {
		// TODO Auto-generated method stub
		super.makePersistent(bean, flag);
	}
}
