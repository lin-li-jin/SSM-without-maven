package com.talent.exam.domain;

/**
 * AbstractExamStopLoss entity provides the base persistence definition of the
 * ExamStopLoss entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractExamStopLoss implements java.io.Serializable {

	// Fields

	private Integer examStopLossNo;
	private ExamContent examContent;
	private String accTypeNo;
	private String direction;
	private String acc;
	private String accAmount;
	private String monitorPrice;
	private String goodFrom;
	private String goddTill;
	private Integer step;

	// Constructors

	/** default constructor */
	public AbstractExamStopLoss() {
	}

	/** full constructor */
	public AbstractExamStopLoss(ExamContent examContent, String accTypeNo,
			String direction, String acc, String accAmount,
			String monitorPrice, String goodFrom, String goddTill, Integer step) {
		this.examContent = examContent;
		this.accTypeNo = accTypeNo;
		this.direction = direction;
		this.acc = acc;
		this.accAmount = accAmount;
		this.monitorPrice = monitorPrice;
		this.goodFrom = goodFrom;
		this.goddTill = goddTill;
		this.step = step;
	}

	// Property accessors

	public Integer getExamStopLossNo() {
		return this.examStopLossNo;
	}

	public void setExamStopLossNo(Integer examStopLossNo) {
		this.examStopLossNo = examStopLossNo;
	}

	public ExamContent getExamContent() {
		return this.examContent;
	}

	public void setExamContent(ExamContent examContent) {
		this.examContent = examContent;
	}

	public String getAccTypeNo() {
		return this.accTypeNo;
	}

	public void setAccTypeNo(String accTypeNo) {
		this.accTypeNo = accTypeNo;
	}

	public String getDirection() {
		return this.direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getAcc() {
		return this.acc;
	}

	public void setAcc(String acc) {
		this.acc = acc;
	}

	public String getAccAmount() {
		return this.accAmount;
	}

	public void setAccAmount(String accAmount) {
		this.accAmount = accAmount;
	}

	public String getMonitorPrice() {
		return this.monitorPrice;
	}

	public void setMonitorPrice(String monitorPrice) {
		this.monitorPrice = monitorPrice;
	}

	public String getGoodFrom() {
		return this.goodFrom;
	}

	public void setGoodFrom(String goodFrom) {
		this.goodFrom = goodFrom;
	}

	public String getGoddTill() {
		return this.goddTill;
	}

	public void setGoddTill(String goddTill) {
		this.goddTill = goddTill;
	}

	public Integer getStep() {
		return this.step;
	}

	public void setStep(Integer step) {
		this.step = step;
	}

}