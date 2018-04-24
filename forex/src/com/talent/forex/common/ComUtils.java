package com.talent.forex.common;

import java.io.File;
import java.io.FilenameFilter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 共用辅助方法
 * 
 * 包含一些零散的便捷方法。
 * 
 * @author cyq
 * 
 */
public class ComUtils {
	public static final String JSESSION_COOKIE = "JSESSIONID";
	public static final String JSESSION_URL = "jsessionid";
	/**
	 * 日期格式：0 yyyMMddHHmmss
	 * */
	public static final DateFormat format0= new SimpleDateFormat("yyyyMMddHHmmss");
	/**
	 * 日期格式：1 yyyyMMdd
	 * */
	public static final DateFormat formatI = new SimpleDateFormat("yyyyMMdd");
	
	/**
	 * 日期格式：2 yyyy-MM-dd
	 * */
	public static final DateFormat formatII = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 日期格式：3 MM-dd HH:mm
	 * */
	public static final DateFormat formatIII = new SimpleDateFormat("MM-dd HH:mm");
	/**
	 * 日期格式：4  MM-dd
	 * */
	public static final DateFormat formatIV = new SimpleDateFormat("MM-dd");
	/**
	 * 日期格式：5 yyyy-MM-dd HH:mm:ss
	 * */
	public static final DateFormat formatV = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 日期格式：6 yyyyMMdd
	 * */
	public static final DateFormat formatVI = new SimpleDateFormat("yyyyMMdd");
	
	/**
	 * 日期格式：7 HHmmss
	 * */
	public static final DateFormat formatVII = new SimpleDateFormat("HHmmss");

	/**
	 * 获得当前时间。由于freemarker的日期必须有具体类型，所以使用timestamp。
	 */
	public static java.sql.Timestamp now() {
		return new java.sql.Timestamp(System.currentTimeMillis());
	}

	/**
	 * 格式化日期。yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public static String dateFormat(Date date) {
		return formatI.format(date);
	}

	/**
	 * 格式化日期。yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return 
	 */
	public static String dataFormatWhole(Date date) {
		return formatV.format(date);
	}
	
	/**
	 * <p>转换日期格式 </p>
	 * <p>date：要转换的日期字符串</p>
	 * <p>日期格式style: </p>
	 * <p>1、6:yyyyMMdd </p>
	 * <p>4: MM-dd</p>
	 * <p>3: MM-dd HH:mm</p>
	 * <p>2: yyyy-MM-dd </p>
	 * <p>0: yyyMMddHHmmss </p>
	 * <p>7: HHmmss</p>
	 * <p>default: 5 yyyy-MM-dd HH:mm:ss</p>
	 * */
	public static String formatDate(Date date, int style) {
		if (date == null) {
			return "";
		}
		return switchDateFormat(style).format(date);
	}
	
	

	
	/**
	 * <p>转换日期格式 </p>
	 * <p>date：要转换的日期字符串</p>
	 * <p>fromStyle：参照日期格式的注释</p>
	 * <p>targetStyle：参照日期格式的注释</p>
	 * <p>日期格式style: </p>
	 * <p>1、6:yyyyMMdd </p>
	 * <p>4: MM-dd</p>
	 * <p>3: MM-dd HH:mm</p>
	 * <p>2: yyyy-MM-dd </p>
	 * <p>0: yyyyMMddHHmmss </p>
	 * <p>7: HHmmss</p>
	 * <p>default: 5 yyyy-MM-dd HH:mm:ss</p>
	 * */
	
	public static  String convertDateStyle(String date ,int fromStyle ,int targetStyle){
		return convertDateFormat(date,switchDateFormat(fromStyle),switchDateFormat(targetStyle));	
	}
	
//	public static void main(String [] s){
//		System.out.println(convertDateStyle("20110103081127",0,5));
//	}
	/**
	 * 转换日期格式
	 * */
	private static String convertDateFormat(String date,DateFormat fromStyle,DateFormat targetStyle){
		try{
			Date d = fromStyle.parse(date.trim()); 
			return targetStyle.format(d);
		}catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
	}
	/**
	 * <p> 获取日期格式 </p>
	 * <p> params: date:时间一般是new Date() </p>
	 * <p> style: int如下：</p> 
	 * <p> 1、6:yyyyMMdd </p>
	 * <p> 4: MM-dd</p>
	 * <p> 3: MM-dd HH:mm</p>
	 * <p> 2: yyyy-MM-dd </p>
	 * <p> 0: yyyMMddHHmmss </p>
	 * <p>  7: HHmmss</p>
	 * <p> default: 5 yyyy-MM-dd HH:mm:ss</p>
	 * */
	private static DateFormat switchDateFormat(int style){
		
		switch (style) {
		case 0:
			return format0;
		case 2:
			return formatII;
		case 3:
			return formatIII;
		case 4:
			return formatIV;
		case 5:
			return formatV;
		case 6:
			return formatVI;		
		case 7:
			return  formatVII;
		default:
			return formatV;
		}
	}
	/**
	 * 过滤一些文件，剩下都是目录结构路径
	 */
	public static FilenameFilter DIR_FILE_FILTER = new FilenameFilter() {
		public boolean accept(File dir, String name) {
			if (dir.isDirectory()) {
				return true;
			} else {
				return false;
			}
		}
	};
	/**
	 * 将秒转化成时间描述
	 */
	public static String dscrTime(int time){
		if(time<0)return null;
		StringBuilder sb = new StringBuilder();
		if(time>60*60){
			sb.append(time/60/60);
			sb.append("小时");
		}
		if(time>60){
			sb.append(time/60);
			sb.append("分");
		}
		sb.append(time%60);
		sb.append("秒");
		return sb.toString();
	}
}
