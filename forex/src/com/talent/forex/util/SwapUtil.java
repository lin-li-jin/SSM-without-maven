package com.talent.forex.util;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.talent.forex.bean.domain.OtcSwapInfo;

public class SwapUtil {

	private static Logger logger = Logger.getLogger(ForwardUtil.class.getName());
	private static SwapUtil instance = new SwapUtil();
	private static Hashtable table = null;
	
	public SwapUtil(){}
	
	public static SwapUtil getInstance() {
		if(table == null){
			table = new Hashtable();
		}
		return instance;
	}
	
	public static Hashtable getTable() {
		return table;
	}
	
	public static void setTable(Hashtable table) {
		SwapUtil.table = table;
	}
	
	public static void refresh() {
		table = null;
		getInstance();
	}
	
	public void swapAdd(OtcSwapInfo bean){
		String key = bean.getTranNo() + bean.getUserNum();
		table.put(key, bean);
	}
	
	public OtcSwapInfo getSwapByParams(String tranNo, String userNum){
		return (OtcSwapInfo) table.get(tranNo + userNum);
	}
	
	public void swapDestroy(String tranNo, String userNum){
		table.remove(tranNo + userNum);
	}
	
	public List<OtcSwapInfo> getSwapListByUserNum(String userNum){
		List<OtcSwapInfo> beans = new ArrayList<OtcSwapInfo>();
		Iterator it = table.keySet().iterator();
		while(it.hasNext()){
			String key = (String) it.next();
			OtcSwapInfo bean = (OtcSwapInfo) table.get(key);
			if(bean.getUserNum().equals(userNum) && bean.getIsInit().equals("0") && bean.getStatue().equals("R")){
				beans.add(bean);
			}
		}
		
		return beans;
	}
}
