package com.talent.forex.core;

import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CoreDAO
{
  private int pagesize = 10;
  private Session session;
  private static Logger log = Logger.getLogger(CoreDAO.class);

  
  public boolean save(Object o)
  {
    boolean isok = false;
    try {
      this.session = HibernateSessionFactory.getSession();
      Transaction ts = this.session.beginTransaction();
      this.session.save(o);
      ts.commit();
      isok = ts.wasCommitted();
      this.session.close();
    } catch (Exception e) {
      log.error("插入异常:CoreDAO.save(Obejct o) == " + e.getMessage());
    }
    return isok;
  }

  public boolean update(Object o)
  {
    boolean isok = false;
    try {
      this.session = HibernateSessionFactory.getSession();
      Transaction ts = this.session.beginTransaction();
      this.session.update(o);
      ts.commit();
      isok = ts.wasCommitted();
      this.session.close();
    } catch (Exception e) {
      log.error("修改异常:CoreDAO.update(Obejct o) == " + e.getMessage());
    }
    return isok;
  }

  public boolean batchSave(List<Object> list)
  {
    boolean isok = false;
    try {
      this.session = HibernateSessionFactory.getSession();
      Transaction ts = this.session.beginTransaction();
      for (int i = 0; i < list.size(); ++i) {
        Object obj = list.get(i);
        this.session.save(obj);
        if (i % 10 == 0) {
          this.session.flush();
          this.session.clear();
        }
      }
      ts.commit();
      isok = ts.wasCommitted();
      this.session.close();
    } catch (Exception e) {
      log.error("批量插入异常:CoreDAO.batchSave(List<Object> list) == " + e.getMessage());
    }
    return isok;
  }

  public boolean delete(Object o)
  {
    boolean isok = false;
    try {
      this.session = HibernateSessionFactory.getSession();
      Transaction ts = this.session.beginTransaction();
      this.session.delete(o);
      ts.commit();
      isok = ts.wasCommitted();
      this.session.close();
    } catch (Exception e) {
      log.error("删除异常:CoreDAO.delete(Object o) == " + e.getMessage());
    }

    return isok;
  }

  public boolean batchDelete(String ObjName, String[] ids)
  {
    boolean isok = false;
    try {
      this.session = HibernateSessionFactory.getSession();
      Transaction ts = this.session.beginTransaction();

      String strids = "";
      for (int i = 0; i < ids.length; ++i) strids = strids + ids[i] + ",";
      if (strids.endsWith(",")) strids = strids.substring(0, strids.length() - 1);
      List list = this.session.createQuery("from " + ObjName + " o where o.id in(" + strids + ")").list();
      if ((list == null) || (list.size() < 1)) return false;
      Object obj;
      for (Iterator localIterator = list.iterator(); localIterator.hasNext(); this.session.delete(obj)){
    	  obj = localIterator.next();
      }

      this.session.flush();
      this.session.clear();
      ts.commit();
      isok = ts.wasCommitted();
      this.session.close();
    } catch (Exception e) {
      log.error("批量删除异常:CoreDAO.batchDelete(String ObjName,String[] ids) == " + e.getMessage());
    }
    return isok;
  }

  public boolean batchDeleteByList(String ObjName, List<Object> list)
  {
    boolean isok = false;
    if ((list == null) || (list.size() < 1)) return true;
    try {
      this.session = HibernateSessionFactory.getSession();
      Transaction ts = this.session.beginTransaction();
      Object obj;
      for (Iterator localIterator = list.iterator(); localIterator.hasNext(); this.session.delete(obj)){
    	  obj = localIterator.next();
      }

      this.session.flush();
      this.session.clear();
      ts.commit();
      isok = ts.wasCommitted();
      this.session.close();
    } catch (Exception e) {
      log.error("批量删除异常:CoreDAO.batchDelete(String ObjName,List<Object> list) == " + e.getMessage());
    }
    return isok;
  }

  public List query(String hql)
  {
    List list = null;
    try {
      this.session = HibernateSessionFactory.getSession();
      list = this.session.createQuery(hql).list();
      this.session.close();
    } catch (Exception e) {
      log.error("查询异常:CoreDAO.query(String hql) == " + e.getMessage());
    }
    return list;
  }

  public List query(String hql, int pagesize)
  {
    List list = null;
    try {
      this.session = HibernateSessionFactory.getSession();
      list = this.session.createQuery(hql).setFirstResult(0).setMaxResults(pagesize).list();
      this.session.close();
    } catch (Exception e) {
      log.error("查询异常:CoreDAO.query(String hql,int pagesize) == " + e.getMessage());
    }
    return list;
  }

  public Object findById(String ObjName, String id)
  {
    Object obj = null;
    try {
      this.session = HibernateSessionFactory.getSession();
      List list = this.session.createQuery("from " + ObjName + " o where o.id=" + id).list();
      if ((list != null) && (list.size() > 0)) {
        obj = list.get(0);
      }
      this.session.close();
    } catch (Exception e) {
      log.error("加载异常:CoreDAO.findById(String ObjName,String id) == " + e.getMessage());
    }
    return obj;
  }

  public List finAll(HttpServletRequest request, HttpServletResponse response, String hql)
  {
    List list = null;
    try {
	      PagerDAO pager = new PagerDAO(request, response, this.session, hql);
	      pager.setPageSize(this.pagesize);
	      list = pager.getList();
	      request.setAttribute("foot", pager.PageFooter());
	      request.setAttribute("rows", pager.getTotalRows());
	      request.setAttribute("pages", pager.getTotalPages());
	      request.setAttribute("nowpage", pager.getNowPage());
    } catch (Exception e) {
    	log.error("分页异常:CoreDAO.finAll(HttpServletRequest request,HttpServletResponse response,String hql) == " + e.getMessage());
    }
    return list;
  }

  public Connection getConnection()
  {
    Connection conn = null;
    this.session = HibernateSessionFactory.getSession();
    conn = this.session.connection();
    return conn;
  }

//  public List query(String sql, Class clazz)
//  {
//    List list = null;
//    try {
//      this.session = HibernateSessionFactory.getSession();
//      Transaction ts = this.session.beginTransaction();
//      list = this.session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(clazz)).list();
//      ts.commit();
//      this.session.close();
//    } catch (Exception e) {
//      log.error("无映射查询:query(String sql,Object obj) == " + e.getMessage());
//    }
//    return list;
//  }
  
  public int getPagesize()
  {
    return this.pagesize;
  }

  public void setPagesize(int pagesize) {
    this.pagesize = pagesize;
  }

}