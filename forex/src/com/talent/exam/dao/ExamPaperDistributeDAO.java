package com.talent.exam.dao;

import java.util.List;

import com.talent.exam.domain.ExamPaperDistribute;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * ExamPaperDistribute entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.talent.exam.domain.ExamPaperDistribute
 * @author MyEclipse Persistence Tools
 */
public class ExamPaperDistributeDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(ExamPaperDistributeDAO.class);
	// property constants
	public static final String CLASS_PAPER_STATUS = "classPaperStatus";
	public static final String CREATE_DATE = "createDate";
	public static final String TILL_DATE = "tillDate";

	public void save(ExamPaperDistribute transientInstance) {
		log.debug("saving ExamPaperDistribute instance");
		try {
			getSession().save(transientInstance);
			getSession().flush();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ExamPaperDistribute persistentInstance) {
		log.debug("deleting ExamPaperDistribute instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ExamPaperDistribute findById(Integer id) {
		log.debug("getting ExamPaperDistribute instance with id: " + id);
		try {
			ExamPaperDistribute instance = (ExamPaperDistribute) getSession()
					.get("com.talent.exam.domain.ExamPaperDistribute", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}



	public List findByProperty(String propertyName, Object value) {
		log.debug("finding ExamPaperDistribute instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ExamPaperDistribute as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByClassPaperStatus(Object classPaperStatus) {
		return findByProperty(CLASS_PAPER_STATUS, classPaperStatus);
	}

	public List findByCreateDate(Object createDate) {
		return findByProperty(CREATE_DATE, createDate);
	}

	public List findByTillDate(Object tillDate) {
		return findByProperty(TILL_DATE, tillDate);
	}

	public List findAll() {
		log.debug("finding all ExamPaperDistribute instances");
		try {
			String queryString = "from ExamPaperDistribute";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ExamPaperDistribute merge(ExamPaperDistribute detachedInstance) {
		log.debug("merging ExamPaperDistribute instance");
		try {
			ExamPaperDistribute result = (ExamPaperDistribute) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ExamPaperDistribute instance) {
		log.debug("attaching dirty ExamPaperDistribute instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ExamPaperDistribute instance) {
		log.debug("attaching clean ExamPaperDistribute instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}