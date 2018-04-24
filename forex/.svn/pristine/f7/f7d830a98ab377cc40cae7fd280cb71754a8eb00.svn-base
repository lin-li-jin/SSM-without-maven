package com.talent.exam.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import com.talent.exam.domain.ExamStopLoss;

/**
 * A data access object (DAO) providing persistence and search support for
 * ExamStopLoss entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.talent.exam.dao.ExamStopLoss
 * @author MyEclipse Persistence Tools
 */
public class ExamStopLossDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(ExamStopLossDAO.class);
	// property constants
	public static final String ACC_TYPE_NO = "accTypeNo";
	public static final String DIRECTION = "direction";
	public static final String ACC = "acc";
	public static final String ACC_AMOUNT = "accAmount";
	public static final String MONITOR_PRICE = "monitorPrice";
	public static final String GOOD_FROM = "goodFrom";
	public static final String GODD_TILL = "goddTill";
	public static final String STEP = "step";
	public static final String PRICE = "price";

	public void save(ExamStopLoss transientInstance) {
		log.debug("saving ExamStopLoss instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ExamStopLoss persistentInstance) {
		log.debug("deleting ExamStopLoss instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ExamStopLoss findById(java.lang.Integer id) {
		log.debug("getting ExamStopLoss instance with id: " + id);
		try {
			ExamStopLoss instance = (ExamStopLoss) getSession().get(
					"com.talent.exam.domain.ExamStopLoss", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}


	public List findByProperty(String propertyName, Object value) {
		log.debug("finding ExamStopLoss instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ExamStopLoss as model where model."
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

	public List findByMonitorPrice(Object monitorPrice) {
		return findByProperty(MONITOR_PRICE, monitorPrice);
	}

	public List findByGoodFrom(Object goodFrom) {
		return findByProperty(GOOD_FROM, goodFrom);
	}

	public List findByGoddTill(Object goddTill) {
		return findByProperty(GODD_TILL, goddTill);
	}

	public List findByStep(Object step) {
		return findByProperty(STEP, step);
	}

	public List findByPrice(Object price) {
		return findByProperty(PRICE, price);
	}

	public List findAll() {
		log.debug("finding all ExamStopLoss instances");
		try {
			String queryString = "from ExamStopLoss";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ExamStopLoss merge(ExamStopLoss detachedInstance) {
		log.debug("merging ExamStopLoss instance");
		try {
			ExamStopLoss result = (ExamStopLoss) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ExamStopLoss instance) {
		log.debug("attaching dirty ExamStopLoss instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ExamStopLoss instance) {
		log.debug("attaching clean ExamStopLoss instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}