package com.talent.auth.bean.domain;

/**
 * ��ʦ�༶�м��(teacher_class)��ʵ����
 */

public class TeacherClass implements java.io.Serializable {

	private Integer tId;
	private Integer classId;

	public TeacherClass() {
	}

	public TeacherClass(Integer tId, Integer classId) {
		this.tId = tId;
		this.classId = classId;
	}

	public Integer gettId() {
		return tId;
	}

	public void settId(Integer tId) {
		this.tId = tId;
	}
	
	public Integer getClassId() {
		return this.classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

}