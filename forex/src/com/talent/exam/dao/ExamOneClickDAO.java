package com.talent.exam.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import com.talent.exam.domain.ExamOneClick;


public class ExamOneClickDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(ExamOneClickDAO.class);
	// property constants
	public static final String ACC_TYPE_NO = "accTypeNo";
	public static final String DIRECTION = "direction";
	public static final String ACC = "acc";
	public static final String ACC_AMOUNT = "accAmount";
	public static final String STEP = "step";

	public void save(ExamOneClick transientInstance) {
		log.debug("saving ExamOneClick instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ExamOneClick persistentInstance) {
		log.debug("deleting ExamOneClick instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ExamOneClick findById(java.lang.Integer id) {
		log.debug("getting ExamOneClick instance with id: " + id);
		try {
			ExamOneClick instance = (ExamOneClick) getSession().get(
					"com.talent.exam.domain.ExamOneClick", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}



	public List findByProperty(String propertyName, Object value) {
		log.debug("finding ExamOneClick instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ExamOneClick as model where model."
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

	public List findByStep(Object step) {
		return findByProperty(STEP, step);
	}

	public List findAll() {
		log.debug("finding all ExamOneClick instances");
		try {
			String queryString = "from ExamOneClick";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ExamOneClick merge(ExamOneClick detachedInstance) {
		log.debug("merging ExamOneClick instance");
		try {
			ExamOneClick result = (ExamOneClick) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ExamOneClick instance) {
		log.debug("attaching dirty ExamOneClick instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ExamOneClick instance) {
		log.debug("attaching clean ExamOneClick instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}