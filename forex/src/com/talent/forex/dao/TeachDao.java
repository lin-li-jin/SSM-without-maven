package com.talent.forex.dao;

import com.talent.auth.bean.domain.Users;
import com.talent.forex.util.PageBean;
import com.talent.hibernate.base.AbstractDaoImpl;
import com.talent.hibernate.base.DaoException;
import com.talent.hibernate.util.HibernateUtil;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 教师
 * Created by 吴樟 on www.haixiangzhene.xyz
 * 2017/8/15.
 */
public class TeachDao extends AbstractDaoImpl {

    @Override
    public Collection getBeansByParams(String hqlName, ArrayList paraList) throws DaoException {
        return super.getBeansByParams(hqlName, paraList);
    }

    @Override
    public Collection getBeansByBean(Serializable bean, MatchMode mode) throws DaoException {
        return super.getBeansByBean(bean, mode);
    }

    public void selectByExample(Users users){
        StringBuilder sb=new StringBuilder();
        sb.append("FROM Users where");
        Field[] field=users.getClass().getDeclaredFields();
        try {
            for (int i = 0; i < field.length; i++) {
                String name = field[i].getName();
                name = name.substring(0, 1).toUpperCase() + name.substring(1);
                String type = field[i].getGenericType().toString();
                if (type.equals("class java.lang.String")) {
                    Method m = users.getClass().getMethod("get" + name);
                    String value = (String) m.invoke(users);
                    if (value!=null){
                        sb.append(" "+name+"="+value);
                    }
                }
                if (type.equals("class java.lang.Integer")) {
                    Method m = users.getClass().getMethod("get" + name);
                    Integer value = (Integer) m.invoke(users);
                    if (value!=null){
                        sb.append(" "+name+"="+value);
                    }
                }

            }
        }catch (NoSuchMethodException ex){

        }catch (IllegalAccessException ex){

        }catch (InvocationTargetException ex){

        }
        Session session=HibernateUtil.getSession();
        session.createQuery("FROM Users where");
    }
    /**
     * 根据离线查询对象DetachedCriteria查询分页数据
     * @return
     */
	public void pageQuery(PageBean pageBean) {

		Criteria criteria = pageBean.getDetachedCriteria().getExecutableCriteria(HibernateUtil.getSession());
		//设置查询总条数
		criteria.setProjection(Projections.rowCount());
		List<Integer> countList = criteria.list();
		if(countList!=null&&countList.size()>0){
			pageBean.setTotal(countList.get(0));
		}
		//清楚统计查询条件
		criteria.setProjection(null);
		//设置分页查询条件
		criteria.setFirstResult( (pageBean.getCurrentPage()-1)*pageBean.getPageSize());
		criteria.setMaxResults(pageBean.getPageSize());
		List teachers = criteria.list();
		//将总条数放进list中一起返回
		pageBean.setRows(teachers);
	}

}
