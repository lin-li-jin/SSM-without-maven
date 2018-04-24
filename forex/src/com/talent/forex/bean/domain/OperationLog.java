package com.talent.forex.bean.domain;

/**
 * OperationLog entity. @author MyEclipse Persistence Tools
 */
public class OperationLog extends AbstractOperationLog implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public OperationLog() {
	}

	/** minimal constructor */
	public OperationLog(String userNum) {
		super(userNum);
	}

	/** full constructor */
	public OperationLog(String userNum, String module, String date, String time) {
		super(userNum, module, date, time);
	}

}
