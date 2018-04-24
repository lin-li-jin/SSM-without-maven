package com.talent.exam.dao;

import java.util.List;

import com.talent.exam.domain.ExamPaper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * ExamPaper entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.talent.exam.domain.ExamPaper
 * @author MyEclipse Persistence Tools
 */
public class ExamPaperDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(ExamPaperDAO.class);
	// property constants
	public static final String PAPER_TITLE = "paperTitle";
	public static final String PAPER_EXAM_CONTENT = "paperExamContent";

	public void save(ExamPaper transientInstance) {
		log.debug("saving ExamPaper instance");
		try {
			getSession().save(transientInstance);
			getSession().flush();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ExamPaper persistentInstance) {
		log.debug("deleting ExamPaper instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ExamPaper findById(Integer id) {
		log.debug("getting ExamPaper instance with id: " + id);
		try {
			ExamPaper instance = (ExamPaper) getSession().get(
					"com.talent.exam.domain.ExamPaper", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}



	public List findByProperty(String propertyName, Object value) {
		log.debug("finding ExamPaper instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ExamPaper as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByPaperTitle(Object paperTitle) {
		return findByProperty(PAPER_TITLE, paperTitle);
	}

	public List findByPaperExamContent(Object paperExamContent) {
		return findByProperty(PAPER_EXAM_CONTENT, paperExamContent);
	}

	public List findAll() {
		log.debug("finding all ExamPaper instances");
		try {
			String queryString = "from ExamPaper";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ExamPaper merge(ExamPaper detachedInstance) {
		log.debug("merging ExamPaper instance");
		try {
			ExamPaper result = (ExamPaper) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ExamPaper instance) {
		log.debug("attaching dirty ExamPaper instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ExamPaper instance) {
		log.debug("attaching clean ExamPaper instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}