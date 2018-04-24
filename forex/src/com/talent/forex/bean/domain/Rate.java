package com.talent.forex.bean.domain;

/**
 * Rate entity. @author MyEclipse Persistence Tools
 */
public class Rate extends AbstractRate implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Rate() {
	}

	/** full constructor */
	public Rate(String accType, String sourceCcy, String targetCcy,
			String direction, String rate) {
		super(accType, sourceCcy, targetCcy, direction, rate);
	}

}
