package com.talent.forex.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import org.hibernate.criterion.MatchMode;
import com.talent.forex.bean.domain.MarginForwardInfo;
import com.talent.forex.bean.domain.SysParam;
import com.talent.hibernate.base.AbstractDaoImpl;
import com.talent.hibernate.base.DaoException;
/**
 * Amendment No.: FOEXAS013
 * Create By    : atggdsaiDong
 * Description  : 保证金远期交易
 * Modify Date  : 2014-07-28
 */
public class MarginForwardInfoDao extends AbstractDaoImpl{
	
	public MarginForwardInfoDao() {
	}
	
	
	private static final String BEAN_PACKAGE_NAME = "com.talent.forex.bean.domain.MarginForwardInfo";



	public MarginForwardInfo getBeanById(Long id) throws DaoException {
		return (MarginForwardInfo) getBeanById(BEAN_PACKAGE_NAME, id, false);
	}

	public Collection getAll() throws DaoException {
		return getAll(BEAN_PACKAGE_NAME);
	}
	
	public void deleteByParams(String hqlName,ArrayList paraList){
		super.deleteOrUpdateByParams(hqlName, paraList);
	}
	
	public MarginForwardInfo getBeanByParams(String hqlName,ArrayList paraList) throws DaoException{
		return (MarginForwardInfo) getProtoBeanByParams(hqlName,paraList);
	}
	
	@Override
	public Collection getBeansByParams(String hqlName,ArrayList paraList) throws DaoException{
		return  super.getBeansByParams(hqlName,paraList);
	}
	
	public MarginForwardInfo getBeanByBean(MarginForwardInfo bean, MatchMode mode) throws DaoException{
		return (MarginForwardInfo) getProtoBeanByBean(bean, mode);
	}
	

	
	public Collection getBeansByBean(MarginForwardInfo bean, MatchMode mode)
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
