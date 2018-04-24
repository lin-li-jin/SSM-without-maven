package com.talent.forex.modules.rateFactory;

import java.util.Collection;

/**
 * 
 * <p>
 * Title:买卖价抽象类
 * </p>
 * <p>
 * Description:定义买卖价抽象类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: Talent Information Solutions Ltd.
 * </p>
 * 
 * @author gary
 *
 */
public abstract class Rate {

	public Rate() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public abstract void refresh();
	
	public abstract void genRmbJingJiaRate();
	
	public abstract void genRmbXunJiaRate();

	public abstract void genForJingJiaRate();
	
	public abstract void genForXunJiaRate();
	
	public abstract void genMarginRate();
	
	public abstract void genLibor();
	
	public abstract Collection getRmbJingJiaRate();
	
	public abstract Collection getRmbXunJiaRate();

	public abstract Collection getForJingJiaRate();
	
	public abstract Collection getForXunJiaRate();
	
	public abstract Collection getMarginRate();
	
	public abstract Collection getLibor();
}
