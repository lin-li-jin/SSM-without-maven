package com.talent.forex.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateCompareUtil {

	public static String dateCompare(String startDate, String endDate){
		//前台已经判断   endDate一定会大于startDate，而且startDate一定是大于或者等于今天
		int start = Integer.parseInt(startDate);
		int end  = Integer.parseInt(endDate);
		int now = Integer.parseInt(GetDateTimeUtil.getCurrentDate());
		
		if(now>=start &&now<=end){
			return "execute";
		}else if(end<now){
			return "invalid";
		}else {
			return "wait";
		}
//		boolean result = false;
//		
//		Date start = convertStringToDate(startDate);
//		Date end = convertStringToDate(endDate);
//		Date now = convertStringToDate(GetDateTimeUtil.getCurrentDate());
//		
//		if(endDate.equals("99999999")){
//			if(now.after(start) || now.equals(start)){
//				result = true;
//			}
//		}
//		else{
//			if(now.after(start) && now.before(end)){//s<n<e
//				result = true;
//			}
//			if(start.equals(now) && start.before(end)){//s=n<e
//				result = true;
//			}
//			if(start.before(end) && end.equals(now)){//s<n=e
//				result = true;
//			}
//			if(start.equals(now) && end.equals(now)){//s=n=e
//				result = true;
//			}
//		}
//		return result;
	}
	
	public static Date convertStringToDate(String dateStr){
		SimpleDateFormat sdf =   new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
