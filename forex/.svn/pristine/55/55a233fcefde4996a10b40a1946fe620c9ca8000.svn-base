package com.talent.forex.extremeTblCell;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.cell.AbstractCell;
import org.extremecomponents.table.core.TableModel;

import com.talent.tools.FormatUtil;

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
public class CurrencyFenCell extends AbstractCell {
	protected String getCellValue(TableModel model, Column column) {
		String curr = column.getPropertyValueAsString();
		String result = null;
		try {
		if(curr != null && !curr.trim().equals(""))
			result =  FormatUtil.amountFormat(Double.valueOf(curr).doubleValue()/100);
		else 
			result =  "";
		}catch(Exception e) {
			
		}
		return result;
	}
}
