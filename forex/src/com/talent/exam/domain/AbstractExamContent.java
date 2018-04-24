package com.talent.exam.domain;


public abstract class AbstractExamContent implements java.io.Serializable {

	// Fields

	private Integer examNo;
	private ExamAccType examAccType;
	private String examContent;

	// Constructors

	/** default constructor */
	public AbstractExamContent() {
	}

	/** full constructor */
	public AbstractExamContent(ExamAccType examAccType, String examContent) {
		this.examAccType = examAccType;
		this.examContent = examContent;
	}

	// Property accessors

	public Integer getExamNo() {
		return this.examNo;
	}

	public void setExamNo(Integer examNo) {
		this.examNo = examNo;
	}

	public ExamAccType getExamAccType() {
		return this.examAccType;
	}

	public void setExamAccType(ExamAccType examAccType) {
		this.examAccType = examAccType;
	}

	public String getExamContent() {
		return this.examContent;
	}

	public void setExamContent(String examContent) {
		this.examContent = examContent;
	}

}