package com.talent.forex.bean.model;

import java.util.Collection;

/**
 * 学生数据导入的返回数据model
 */
public class StuInputResultModel {
	private String result;
	private Collection stuCol;
	
	public StuInputResultModel(String result, Collection stuCol) {
		super();
		this.result = result;
		this.stuCol = stuCol;
	}

	public StuInputResultModel() {
		super();
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Collection getStuCol() {
		return stuCol;
	}

	public void setStuCol(Collection stuCol) {
		this.stuCol = stuCol;
	}
	
	
	
}
