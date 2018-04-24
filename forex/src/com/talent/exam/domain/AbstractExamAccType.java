package com.talent.exam.domain;

import java.util.HashSet;
import java.util.Set;


public abstract class AbstractExamAccType implements java.io.Serializable {

	// Fields

	private String accTypeNo;
	private String accType;
	private Set examContents = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractExamAccType() {
	}

	/** minimal constructor */
	public AbstractExamAccType(String accType) {
		this.accType = accType;
	}

	/** full constructor */
	public AbstractExamAccType(String accType, Set examContents) {
		this.accType = accType;
		this.examContents = examContents;
	}

	// Property accessors

	public String getAccTypeNo() {
		return this.accTypeNo;
	}

	public void setAccTypeNo(String accTypeNo) {
		this.accTypeNo = accTypeNo;
	}

	public String getAccType() {
		return this.accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public Set getExamContents() {
		return this.examContents;
	}

	public void setExamContents(Set examContents) {
		this.examContents = examContents;
	}

}