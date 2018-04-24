package com.talent.auth.bean.domain;

import com.talent.exam.domain.ExamPaperDistribute;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 班级表实体类
 */

public class TClass implements java.io.Serializable {

	// Fields

	private Integer classId;
	private String classNo;
	private String className;
	private Collection<ExamPaperDistribute> examPaperDistributesByClassId;

	// Constructors

	public TClass() {
	}

	public TClass(Integer classId) {
		this.classId = classId;
	}
	
	public TClass(Integer classId, String classNo, String className) {
		super();
		this.classId = classId;
		this.classNo = classNo;
		this.className = className;
	}

	// Property accessors

	public Integer getClassId() {
		return this.classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassNo() {
		return classNo;
	}

	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}


	public Collection<ExamPaperDistribute> getExamPaperDistributesByClassId() {
		return examPaperDistributesByClassId;
	}

	public void setExamPaperDistributesByClassId(Collection<ExamPaperDistribute> examPaperDistributesByClassId) {
		this.examPaperDistributesByClassId = examPaperDistributesByClassId;
	}
}