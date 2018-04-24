package com.talent.forex.bean.domain;

/**
 * AbstractOperationLog entity provides the base persistence definition of the
 * OperationLog entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractOperationLog implements java.io.Serializable {

	// Fields

	private Integer id;
	private String userNum;
	private String module;
	private String date;
	private String time;

	// Constructors

	/** default constructor */
	public AbstractOperationLog() {
	}

	/** minimal constructor */
	public AbstractOperationLog(String userNum) {
		this.userNum = userNum;
	}

	/** full constructor */
	public AbstractOperationLog(String userNum, String module, String date,
			String time) {
		this.userNum = userNum;
		this.module = module;
		this.date = date;
		this.time = time;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserNum() {
		return this.userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public String getModule() {
		return this.module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}