package com.talent.forex.extremeTblCell;

import com.opensymphony.webwork.ServletActionContext;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: Talent Information Solutions Ltd.
 * </p>
 * 
 * @author zhouyf
 *
 */
public class EcPageModelFactory {
	
	public static EcPageModelFactory instance = null;
	
	
	public EcPageModelFactory() {
	}
	
	public static EcPageModelFactory getInstance() {
		if(instance == null) 
			return new EcPageModelFactory();
		else
			return instance;
	}

	/**
	 * tableId 为 extremeTable中的id
	 * pageSize 为一页显示的数量，要和extremeTable中的rowsDisplayed保持一致
	 * @param tableId
	 * @param pageSize
	 * @return
	 */
	public EcPageModel getEcPageModel(String tableId, int pageSize) {
		EcPageModel ecPageModel = new EcPageModel(ServletActionContext.getRequest(),tableId, pageSize); 
		return ecPageModel;
	}
}
