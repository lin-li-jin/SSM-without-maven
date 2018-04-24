package com.talent.exam.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import com.talent.exam.domain.ExamSpot;



/**
 * A data access object (DAO) providing persistence and search support for
 * ExamSpot entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.talent.exam.dao.ExamSpot
 * @author MyEclipse Persistence Tools
 */
public class ExamSpotDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(ExamSpotDAO.class);
	// property constants
	public static final String ACC_TYPE_NO = "accTypeNo";
	public static final String DIRECTION = "direction";
	public static final String ACC = "acc";
	public static final String ACC_AMOUNT = "accAmount";
	public static final String PRICE = "price";
	public static final String PROVIDER_NO = "providerNo";
	public static final String STEP = "step";

	public void save(ExamSpot transientInstance) {
		log.debug("saving ExamSpot instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ExamSpot persistentInstance) {
		log.debug("deleting ExamSpot instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ExamSpot findById(java.lang.Integer id) {
		log.debug("getting ExamSpot instance with id: " + id);
		try {
			ExamSpot instance = (ExamSpot) getSession().get(
					"com.talent.exam.domain.ExamSpot", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}


	public List findByProperty(String propertyName, Object value) {
		log.debug("finding ExamSpot instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ExamSpot as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByAccTypeNo(Object accTypeNo) {
		return findByProperty(ACC_TYPE_NO, accTypeNo);
	}

	public List findByDirection(Object direction) {
		return findByProperty(DIRECTION, direction);
	}

	public List findByAcc(Object acc) {
		return findByProperty(ACC, acc);
	}

	public List findByAccAmount(Object accAmount) {
		return findByProperty(ACC_AMOUNT, accAmount);
	}

	public List findByPrice(Object price) {
		return findByProperty(PRICE, price);
	}

	public List findByProviderNo(Object providerNo) {
		return findByProperty(PROVIDER_NO, providerNo);
	}

	public List findByStep(Object step) {
		return findByProperty(STEP, step);
	}

	public List findAll() {
		log.debug("finding all ExamSpot instances");
		try {
			String queryString = "from ExamSpot";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ExamSpot merge(ExamSpot detachedInstance) {
		log.debug("merging ExamSpot instance");
		try {
			ExamSpot result = (ExamSpot) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ExamSpot instance) {
		log.debug("attaching dirty ExamSpot instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ExamSpot instance) {
		log.debug("attaching clean ExamSpot instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}