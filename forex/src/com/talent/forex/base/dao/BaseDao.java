package com.talent.forex.base.dao;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import org.apache.commons.beanutils.BeanMap;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.type.ComponentType;
import org.hibernate.type.StringType;


import com.talent.hibernate.base.AbstractDaoImpl;
import com.talent.hibernate.base.DaoException;
import com.talent.hibernate.util.HibernateUtil;
import com.talent.forex.base.model.EcPage;

/**
 * »ù´¡¡€dao
 * @author fengronghua
 * @version 2012-5-9
 */
public abstract class BaseDao extends AbstractDaoImpl{
	
	protected abstract String getBeanPackageName();
	
	protected abstract Class getEntityClass();
	
	public Collection getAll() throws DaoException {
		return getAll(getBeanPackageName());
	}
	
	public Serializable getBeanById(Serializable id) throws DaoException {
		return getBeanById(getBeanPackageName(), id, false);
	}
	
	public Serializable getBeanByIdForUpdate(Serializable id) throws DaoException {
		return getBeanById(getBeanPackageName(), id, true);
	}
	
	public Serializable getBeanByBean(Serializable bean, MatchMode mode) throws DaoException{
		return getProtoBeanByBean(bean, mode);
	}
	
	public BaseDao setOrder(Order order){
		return (BaseDao)super.addOrder(order);
	}
	
	public Collection getBeansByBean(Serializable bean, MatchMode matchModel,
			EcPage ecPage) throws DaoException {

		Collection collection = null;
		Session session = HibernateUtil.getSession();
		try {
			Example example = Example.create(bean).excludeNone()
					.excludeZeroes().enableLike(matchModel);
			Criteria criteria = session.createCriteria(bean.getClass()).add(
					example);
			appendIdToQuery(bean, criteria, matchModel);
			
			int totalCount = ((Integer) criteria.setProjection(
					Projections.rowCount()).uniqueResult()).intValue();

			ecPage.setTotalRows(totalCount);
			criteria.setProjection(null);
			if (!ecPage.isAll()) {
				criteria.setFirstResult(ecPage.getRowStart());
				criteria.setMaxResults(ecPage.getPageSize());
			}

		    if (ecPage.getOrderMap() != null && !ecPage.getOrderMap().isEmpty()) {   
		        Set orderNameSet = ecPage.getOrderMap().keySet();   
		        Iterator it = orderNameSet.iterator();
		        while(it.hasNext()) {
		        	String name = (String)it.next();
		        	 if ("asc".equalsIgnoreCase((String)ecPage.getOrderMap().get(name))) {   
			                criteria.addOrder(Order.asc(name));   
			            } else if ("desc".equalsIgnoreCase((String)ecPage.getOrderMap().get(name))){   
			                criteria.addOrder(Order.desc(name));   
			            }   
		        }
		    } else {
		    	Vector orderVector = getOrderVector();
		    	Vector orderFinalVector = new Vector();
		    	
		    	HashSet orderSet = new HashSet();
		    	Iterator it = orderVector.iterator();
		    	while(it.hasNext()) {
		    		Order o = (Order) it.next();
		    		if(!orderSet.contains(o.toString()))  {
		    			orderSet.add(o.toString());
		    			orderFinalVector.add(o);
		    		}

		    	}
				for (Iterator setIt = orderFinalVector.iterator(); setIt.hasNext(); criteria.addOrder((Order) setIt.next()));
				orderVector.clear();
				orderFinalVector.clear();
		    }
			collection = criteria.list();
		} catch (Exception ex) {
			throw new DaoException("Get match records by bean fail!"
					+ ex.getMessage());
		}
		return collection;
	}
	
    /**
     * @param hqlName
     * @param paraList
     * @param ecPage
     * @return
     * @throws DaoException
     */
    public Collection getBeansByParams(String hqlName, ArrayList paraList,
    		EcPage ecPage)
			throws DaoException {
		Collection collection = null;
		int paraSize = paraList.size();
		try {
			Query q = HibernateUtil.getSession().getNamedQuery(hqlName);
			for (int i = 0; i < paraSize; i++)
				q.setParameter(i, paraList.get(i));
			if (!ecPage.isAll()) {
				q.setFirstResult(ecPage.getRowStart());
				q.setMaxResults(ecPage.getPageSize());
			}
			
			collection = q.list();
			
			
		} catch (Exception ex) {
			throw new DaoException("Get match records by hqlName fail!"
					+ ex.getMessage());
		}
		return collection;
	}
    
	public Serializable getBeanByParams(String hqlName,ArrayList paraList) throws DaoException{
		return getProtoBeanByParams(hqlName,paraList);
	}
    

	/**
	 * @param bean
	 * @param criteria
	 * @param mode
	 * @throws Exception
	 */
	private void appendIdToQuery(Serializable bean, Criteria criteria,
			MatchMode mode) throws Exception {
		ClassMetadata cm = HibernateUtil.getSessionFactory().getClassMetadata(
				bean.getClass());
		String idName = cm.getIdentifierPropertyName();
		org.hibernate.type.Type idType = cm.getIdentifierType();
		BeanMap beanMap = new BeanMap(bean);
		Object idValue = beanMap.get(idName);
		if (idValue != null) {
			Class idClass = idValue.getClass();
			if (idType instanceof ComponentType) {
				Method methods[] = idClass.getMethods();
				try {
					for (int i = 0; i < methods.length; i++)
						if (methods[i].getName().toUpperCase().indexOf("GET") != -1
								&& methods[i].invoke(idValue, null) != null
								&& !methods[i].getName().equals("getClass")) {
							String propertyName = methods[i].getName()
									.substring(3);
							propertyName = propertyName.toLowerCase()
									.substring(0, 1)
									+ propertyName.substring(1);
							if (methods[i].getReturnType().getName().equals(
									"java.lang.String"))
								criteria.add(Restrictions.like(idName + "."
										+ propertyName, methods[i].invoke(
										idValue, null).toString(), mode));
							else
								criteria.add(Restrictions.eq(idName + "."
										+ propertyName, methods[i].invoke(
										idValue, null)));
						}

				} catch (Exception ex) {
					ex.printStackTrace();
					throw ex;
				}
			} else if (idType instanceof StringType)
				criteria.add(Restrictions.like(idName, idValue.toString(), mode));
			else
				criteria.add(Restrictions.eq(idName, idValue));
		}
	}
}