package com.talent.forex.base.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.criterion.MatchMode;

import com.talent.forex.base.model.EcPage;



/**
 * ����bo
 * @author fengronghua
 * @version 2012-5-10
 */
public interface BaseBo {
	
	public void objAdd(Serializable bean);  
	  
    public void objModify(Serializable bean);  
  
    public void objDel(Serializable id);  
  
    public Object findByIdQuery(Serializable id);
    
	public Object getBeanByParamsQuery(String hqlName,ArrayList paraList);
    
    public Object getBeanByBeanQuery(Serializable bean, MatchMode mode);
    
    public Collection findAllQuery();
    
    public Collection findBeansByBeanQuery(Serializable bean, MatchMode matchModel); 
    
    public Collection findPageByBeanQuery(Serializable bean, MatchMode matchModel, EcPage ecPage); 
  
    public Collection findByHQLQuery(String hql, ArrayList paraList);
    
    public Collection findPageByHQLQuery(String hql, ArrayList paraList, EcPage ecPage);
}
