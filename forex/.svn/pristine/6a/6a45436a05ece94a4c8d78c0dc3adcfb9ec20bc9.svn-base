package com.talent.forex.util;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.talent.forex.bean.domain.OtcSpotInfo;

public class SpotUtil {

	private static Logger logger = Logger.getLogger(SpotUtil.class.getName());
	private static SpotUtil instance = new SpotUtil();
	private static Hashtable table = null;
	
	public SpotUtil(){}
	
	public static SpotUtil getInstance() {
		if(table == null){
			table = new Hashtable();
		}
		return instance;
	}
	
	public Hashtable getTable() {
		return table;
	}
	
	public void setTable(Hashtable table) {
		SpotUtil.table = table;
	}
	
	public static void refresh() {
		table = null;
		getInstance();
	}
	
	public void spotAdd(OtcSpotInfo bean){
		String key = bean.getTranNo() + bean.getUserNum();
		table.put(key, bean);
	}
	
	public OtcSpotInfo getSpotByParams(String tranNo, String userNum){
		return (OtcSpotInfo) table.get(tranNo + userNum);
	}
	
	public void spotDestroy(String tranNo, String userNum){
		table.remove(tranNo + userNum);
	}
	
	public List<OtcSpotInfo> getSpotListByUserNum(String userNum){
		List<OtcSpotInfo> beans = new ArrayList<OtcSpotInfo>();
		Iterator it = table.keySet().iterator();
		while(it.hasNext()){
			String key = (String) it.next();
			OtcSpotInfo bean = (OtcSpotInfo) table.get(key);
			if(bean.getUserNum().equals(userNum) && bean.getIsInit().equals("0") && bean.getStatue().equals("R")){
				beans.add(bean);
			}
		}
		
		return beans;
	}
}
