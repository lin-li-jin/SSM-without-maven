package com.talent.forex.core;


import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;

public class ConfigCache
{
  static final String cacheName = "ConfigCache";
  private static Logger log = Logger.getLogger(ConfigCache.class);

  static { 
	    try
	    {
	    	 CacheManager manager = CacheManager.getInstance();
	         Cache c = manager.getCache("ConfigCache");
		     CoreDAO dao = new CoreDAO();
		     List<Sysconfigs> list = dao.query("from Sysconfigs");
		     if ((list != null) && (list.size() > 0)) {
		        System.out.println("加载了个" + list.size() + "配置");
		        for (Sysconfigs cfg : list) {
			          Element e = new Element(String.valueOf(cfg.getCkey()), cfg.getCval());
			          Element e2 = new Element(String.valueOf(cfg.getId()), cfg.getCval());
			          c.put(e);
			          c.put(e2);
			
			          Element e3 = new Element("E_USER_NUMS", Integer.valueOf(CacheUtil.USER_NUMS()));
			          c.put(e3);
			          Element e4 = new Element("E_QUESTION_NUMS", Integer.valueOf(CacheUtil.QUESTION_NUMS()));
			          c.put(e4);
		        }
		     }
	    }catch (Exception e){
	    	log.error("加载配置信息出错..");
	    }
  }


  public static String getValueByKey(String key)
  {
    if (key == null) return null;
    String cfg = null;
    
    CacheManager manager = null;
    Cache c = null;
    Element result = null;
    try{
	    manager = CacheManager.getInstance();
	    c = manager.getCache("ConfigCache");
	    result = c.get(key);
    }catch(CacheException e){
    	log.error("加载配置ByKey出错："+e.getMessage());
    }
    if (result == null) {
      System.out.println("缓存没有,加载...ConfigCache:" + key);
      CoreDAO dao = new CoreDAO();
      List list = dao.query("from Sysconfigs c where c.ckey='" + key + "'");
      if ((list != null) && (list.size() > 0)) {
	        Sysconfigs ccfg = (Sysconfigs)list.get(0);
	        Element e = new Element(String.valueOf(ccfg.getCkey()), ccfg.getCval());
	        c.put(e);
	        cfg = ccfg.getCval();
      }
    }
    else {
      cfg = (String)result.getValue();
    }

    return cfg;
  }

  public static String getValueById(String id)
  {
    if (id == null) return null;
    String cfg = null;

    CacheManager manager = null;
    Cache c = null;
    Element result = null;
    try{
	    manager = CacheManager.getInstance();
	    c = manager.getCache("ConfigCache");
	    result = c.get(id);
    }catch (CacheException e) {
		log.error("获取配置ById出错："+e.getMessage());
	}
    
    if (result == null) {
	      System.out.println("缓存没有,加载...ConfigCache:" + id);
	      CoreDAO dao = new CoreDAO();
	      Sysconfigs ccfg = (Sysconfigs)dao.findById("Sysconfigs", id);
	      if (ccfg != null) {
	        Element e = new Element(String.valueOf(ccfg.getId()), ccfg.getCval());
	        c.put(e);
	        cfg = ccfg.getCval();
	      }
    }
    else {
    	cfg = (String)result.getValue();
    }

    return cfg;
  }

  public static void updateCache(String key)
  {
	try{
	    CacheManager manager = CacheManager.getInstance();
	    Cache c = manager.getCache("ConfigCache");
	    c.remove(key);
	}catch (CacheException e) {
		log.error("更新配置信息出错"+e.getMessage());
	}
  }
}
