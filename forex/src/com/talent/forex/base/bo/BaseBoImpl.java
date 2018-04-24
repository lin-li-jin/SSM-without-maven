package com.talent.forex.base.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.criterion.MatchMode;

import com.talent.exception.BoException;
import com.talent.forex.base.dao.BaseDao;
import com.talent.forex.base.model.EcPage;

/**
 * »ù´¡bo
 * 
 * @author fengronghua
 * @version 2012-5-10
 */
public abstract class BaseBoImpl extends com.talent.base.BaseBo {


	protected abstract BaseDao getDao();

	public Object findByIdQuery(Serializable id) {
		return getDao().getBeanById(id);
	}

	public Collection findAllQuery() {
		return getDao().getAll();
	}
	
	public Object getBeanByParamsQuery(String hqlName,ArrayList paraList){
		return getDao().getBeanByParams(hqlName, paraList);
	}
    
    public Object getBeanByBeanQuery(Serializable bean, MatchMode mode){
    	return getDao().getBeanByBean(bean, mode);
    }

	public Collection findBeansByBeanQuery(Serializable bean, MatchMode matchModel) {
		return getDao().getBeansByBean(bean, matchModel);
	}

	public Collection findByHQLQuery(String hql, ArrayList paraList) {
		return getDao().getBeansByParams(hql, paraList);
	}

	public void objAdd(Serializable bean) {
		getDao().makePersistent(bean, false);
	}

	public void objDel(Serializable id) {
		Serializable bean = getDao().getBeanById(id);
		getDao().makeTransient(bean);
	}

	public void objModify(Serializable bean) {
		getDao().makePersistent(bean, true);
	}

	public Collection findPageByBeanQuery(Serializable bean, MatchMode matchModel,
			EcPage ecPage) throws BoException {
		return getDao().getBeansByBean(bean, matchModel, ecPage);
	}

	public Collection findPageByHQLQuery(String hql, ArrayList paraList,
			EcPage ecPage) {
		return getDao().getBeansByParams(hql, paraList, ecPage);
	}
}
