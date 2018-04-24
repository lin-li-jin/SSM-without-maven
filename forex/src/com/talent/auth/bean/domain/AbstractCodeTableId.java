package com.talent.auth.bean.domain;

/**
 * AbstractCodeTableId entity provides the base persistence definition of the
 * CodeTableId entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractCodeTableId implements java.io.Serializable {

	// Fields

	private String codeType;
	private String codeVal;

	// Constructors

	/** default constructor */
	public AbstractCodeTableId() {
	}

	/** full constructor */
	public AbstractCodeTableId(String codeType, String codeVal) {
		this.codeType = codeType;
		this.codeVal = codeVal;
	}

	// Property accessors

	public String getCodeType() {
		return this.codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getCodeVal() {
		return this.codeVal;
	}

	public void setCodeVal(String codeVal) {
		this.codeVal = codeVal;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractCodeTableId))
			return false;
		AbstractCodeTableId castOther = (AbstractCodeTableId) other;

		return ((this.getCodeType() == castOther.getCodeType()) || (this
				.getCodeType() != null && castOther.getCodeType() != null && this
				.getCodeType().equals(castOther.getCodeType())))
				&& ((this.getCodeVal() == castOther.getCodeVal()) || (this
						.getCodeVal() != null && castOther.getCodeVal() != null && this
						.getCodeVal().equals(castOther.getCodeVal())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getCodeType() == null ? 0 : this.getCodeType().hashCode());
		result = 37 * result
				+ (getCodeVal() == null ? 0 : this.getCodeVal().hashCode());
		return result;
	}

}