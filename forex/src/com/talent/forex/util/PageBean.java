package com.talent.forex.util;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;


/**
 * 分页查询的分页类
 */
public class PageBean {
	
	//当前页数
	private int currentPage;
	//每页显示条数
	private int pageSize;
	//离线查询对象
	private DetachedCriteria detachedCriteria;
	//总条数
	private int total;
	//当前页显示的数据
	private List rows;
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public DetachedCriteria getDetachedCriteria() {
		return detachedCriteria;
	}
	public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
		this.detachedCriteria = detachedCriteria;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	
	
}
