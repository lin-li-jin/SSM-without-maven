package com.talent.forex.bean.domain;

/**
 * GroupMng entity. @author MyEclipse Persistence Tools
 */
public class GroupMng extends AbstractGroupMng implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public GroupMng() {
	}

	/** full constructor */
	public GroupMng(String groupId, String parentGroup, String groupType) {
		super(groupId, parentGroup, groupType);
	}

}
