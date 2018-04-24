package com.talent.forex.bean.domain;

/**
 * AbstractSeq entity provides the base persistence definition of the Seq
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractSeq implements java.io.Serializable {

	// Fields

	private String tranType;
	private Double seqNo;

	// Constructors

	/** default constructor */
	public AbstractSeq() {
	}

	/** full constructor */
	public AbstractSeq(Double seqNo) {
		this.seqNo = seqNo;
	}

	// Property accessors

	public String getTranType() {
		return this.tranType;
	}

	public void setTranType(String tranType) {
		this.tranType = tranType;
	}

	public Double getSeqNo() {
		return this.seqNo;
	}

	public void setSeqNo(Double seqNo) {
		this.seqNo = seqNo;
	}

}