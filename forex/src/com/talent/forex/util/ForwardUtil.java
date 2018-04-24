package com.talent.forex.util;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.talent.forex.bean.domain.OtcForwardInfo;

public class ForwardUtil {

	private static Logger logger = Logger.getLogger(ForwardUtil.class.getName());
	private static ForwardUtil instance = new ForwardUtil();
	private static Hashtable table = null;
	
	public ForwardUtil(){}
	
	public static ForwardUtil getInstance() {
		if(table == null){
			table = new Hashtable();
		}
		return instance;
	}
	
	public Hashtable getTable() {
		return table;
	}
	
	public void setTable(Hashtable table) {
		ForwardUtil.table = table;
	}
	
	public static void refresh() {
		table = null;
		getInstance();
	}
	
	public void forwardAdd(OtcForwardInfo bean){
		String key = bean.getTranNo() + bean.getUserNum();
		table.put(key, bean);
	}
	
	public OtcForwardInfo getForwardByParams(String tranNo, String userNum){
		return (OtcForwardInfo) table.get(tranNo + userNum);
	}
	
	public void forwardDestroy(String tranNo, String userNum){
		table.remove(tranNo + userNum);
	}
	
	public List<OtcForwardInfo> getForwardListByUserNum(String userNum){
		List<OtcForwardInfo> beans = new ArrayList<OtcForwardInfo>();
		Iterator it = table.keySet().iterator();
		while(it.hasNext()){
			String key = (String) it.next();
			OtcForwardInfo bean = (OtcForwardInfo) table.get(key);
			if(bean.getUserNum().equals(userNum) && bean.getIsInit().equals("0") && bean.getStatue().equals("R")){
				beans.add(bean);
			}
		}
		
		return beans;
	}
}
