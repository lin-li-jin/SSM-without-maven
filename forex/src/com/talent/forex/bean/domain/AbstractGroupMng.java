package com.talent.forex.bean.domain;

/**
 * AbstractGroupMng entity provides the base persistence definition of the
 * GroupMng entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractGroupMng implements java.io.Serializable {

	// Fields

	private Integer id;
	private String groupId;
	private String parentGroup;
	private String groupType;

	// Constructors

	/** default constructor */
	public AbstractGroupMng() {
	}

	/** full constructor */
	public AbstractGroupMng(String groupId, String parentGroup, String groupType) {
		this.groupId = groupId;
		this.parentGroup = parentGroup;
		this.groupType = groupType;
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

	public String getParentGroup() {
		return this.parentGroup;
	}

	public void setParentGroup(String parentGroup) {
		this.parentGroup = parentGroup;
	}

	public String getGroupType() {
		return this.groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

}