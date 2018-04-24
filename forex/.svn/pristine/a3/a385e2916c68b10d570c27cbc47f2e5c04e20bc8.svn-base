package com.talent.forex.modules.rateFactory;

import java.util.Collection;

import org.apache.log4j.Logger;

import com.talent.forex.util.GetRateUtil;


public class RateReceive {
	private static Logger logger = Logger.getLogger(GetRateUtil.class
			.getName());
	private static RateReceive instance = new RateReceive();
	private Rate rate;
	
	public RateReceive() {
		super();
		// TODO Auto-generated constructor stub
		rate = new RandomRate();
	}
	
	public static RateReceive getInstance() {
		return instance;
	}
	
	public Collection getRmbJingJiaRate(){
		return rate.getRmbJingJiaRate();
	}
	
	public Collection getRmbXunJiaRate(){
		return rate.getRmbXunJiaRate();
	}

	public Collection getForJingJiaRate(){
		return rate.getForJingJiaRate();
	}
	
	public Collection getForXunJiaRate(){
		return rate.getForXunJiaRate();
	}
	 
	public Collection getMarginRate(){
		return rate.getMarginRate();
	}
	
	public Collection getLibor(){
		return rate.getLibor();
	}
}
