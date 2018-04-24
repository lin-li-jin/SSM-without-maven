package com.talent.forex.bean.domain;

/**
 * AbstractMarginEnlarge entity provides the base persistence definition of the
 * MarginEnlarge entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractMarginEnlarge implements java.io.Serializable {

	// Fields

	private Integer id;
	private String groupId;
	private String enlarge;

	// Constructors

	/** default constructor */
	public AbstractMarginEnlarge() {
	}

	/** full constructor */
	public AbstractMarginEnlarge(String groupId, String enlarge) {
		this.groupId = groupId;
		this.enlarge = enlarge;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGroupId() {
		return this.groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getEnlarge() {
		return this.enlarge;
	}

	public void setEnlarge(String enlarge) {
		this.enlarge = enlarge;
	}

}