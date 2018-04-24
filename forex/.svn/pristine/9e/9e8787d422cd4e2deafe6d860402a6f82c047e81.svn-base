package com.talent.exam.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import com.talent.exam.domain.ExamSwap;


public class ExamSwapDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(ExamSwapDAO.class);
	// property constants
	public static final String ACC_TYPE_NO = "accTypeNo";
	public static final String DIRECTION = "direction";
	public static final String ACC = "acc";
	public static final String ACC_AMOUNT = "accAmount";
	public static final String SPOT = "spot";
	public static final String START_DATE = "startDate";
	public static final String MATURITY_DATE = "maturityDate";
	public static final String FIXED_TYPE = "fixedType";
	public static final String FIXED_RATE = "fixedRate";
	public static final String CBASIS = "cbasis";
	public static final String FBASIS = "fbasis";
	public static final String FREQUENCY = "frequency";
	public static final String LIBOR = "libor";
	public static final String PROVIDER_NO = "providerNo";
	public static final String POINT = "point";
	public static final String STEP = "step";
	public static final String EXAM_SCORE = "examScore";
	public static final String USER_TYPE = "userType";

	public void save(ExamSwap transientInstance) {
		log.debug("saving ExamSwap instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ExamSwap persistentInstance) {
		log.debug("deleting ExamSwap instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ExamSwap findById(java.lang.Integer id) {
		log.debug("getting ExamSwap instance with id: " + id);
		try {
			ExamSwap instance = (ExamSwap) getSession().get(
					"com.talent.exam.dao.ExamSwap", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}


	public List findByProperty(String propertyName, Object value) {
		log.debug("finding ExamSwap instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ExamSwap as model where model."
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

	public List findBySpot(Object spot) {
		return findByProperty(SPOT, spot);
	}

	public List findByStartDate(Object startDate) {
		return findByProperty(START_DATE, startDate);
	}

	public List findByMaturityDate(Object maturityDate) {
		return findByProperty(MATURITY_DATE, maturityDate);
	}

	public List findByFixedType(Object fixedType) {
		return findByProperty(FIXED_TYPE, fixedType);
	}

	public List findByFixedRate(Object fixedRate) {
		return findByProperty(FIXED_RATE, fixedRate);
	}

	public List findByCbasis(Object cbasis) {
		return findByProperty(CBASIS, cbasis);
	}

	public List findByFbasis(Object fbasis) {
		return findByProperty(FBASIS, fbasis);
	}

	public List findByFrequency(Object frequency) {
		return findByProperty(FREQUENCY, frequency);
	}

	public List findByLibor(Object libor) {
		return findByProperty(LIBOR, libor);
	}

	public List findByProviderNo(Object providerNo) {
		return findByProperty(PROVIDER_NO, providerNo);
	}

	public List findByPoint(Object point) {
		return findByProperty(POINT, point);
	}

	public List findByStep(Object step) {
		return findByProperty(STEP, step);
	}

	public List findByExamScore(Object examScore) {
		return findByProperty(EXAM_SCORE, examScore);
	}

	public List findByUserType(Object userType) {
		return findByProperty(USER_TYPE, userType);
	}

	public List findAll() {
		log.debug("finding all ExamSwap instances");
		try {
			String queryString = "from ExamSwap";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ExamSwap merge(ExamSwap detachedInstance) {
		log.debug("merging ExamSwap instance");
		try {
			ExamSwap result = (ExamSwap) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ExamSwap instance) {
		log.debug("attaching dirty ExamSwap instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ExamSwap instance) {
		log.debug("attaching clean ExamSwap instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}