package com.talent.forex.util;

import java.io.File;
import java.io.FilenameFilter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;

import com.talent.exception.BoException;
import com.talent.tools.TimeUtil;
public class GetDateTimeUtil {
	private static Logger logger = Logger.getLogger(GetDateTimeUtil.class.getName());
	/**
	 * 取当前日期
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getCurrentYear(){
		Date date = new Date();		
		String year = Integer.toString(date.getYear()+1900);
			return year;
	}
	
	/**
	 * 取当前的时间
	 * @return
	 */
	public static String getCurrentDateTime(){
		try{
			return formatDate(new Date(),0);
		}catch(Exception e){
			BoException be = new BoException("getCurrentDateTime");
			be.setExceptionType("取当前的时间失败！");
			throw be;
		}
	}
	
	/**
	 * 取当前的时间
	 * @return
	 */
	public static String getCurrentDateTimeToMinute(){
		try{
			return formatDate(new Date(),8);
		}catch(Exception e){
			BoException be = new BoException("getCurrentDateTime");
			be.setExceptionType("取当前的时间失败！");
			throw be;
		}
	}
	
	/**
	 * 取当前日期
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getCurrentDate(){
		Date date = new Date();		
		String year = Integer.toString(date.getYear()+1900);
		String month = Integer.toString(date.getMonth()+1);
		if(month.length() == 1)
			month = "0"+month;
		String day = Integer.toString(date.getDate());
		if(day.length() == 1)
			day = "0" + day;
			return year+month+day;
		/*String date = TimeUtil.dateFormat(new Date(),"yyyyMMdd");
		System.out.println(date);
		return date;*/
	}
	/**
	 * 取当前时间
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getCurrentTime(){
		Date date1 = new Date();
		String hour = Integer.toString(date1.getHours());
		if(hour.length() == 1){
			hour = "0" + hour;
		}
		String minute = Integer.toString(date1.getMinutes());
		if(minute.length() == 1){
			minute = "0" + minute;
		}
		String second = Integer.toString(date1.getSeconds());
		if(second.length() == 1){
			second = "0" + second;
		}
		return hour + minute + second;
		/*String date = TimeUtil.dateFormat(new Date(),"hhmmss");
		System.out.println(date);
		return date;*/
	}	
	/**
	 * 时间格式化
	 * @return
	 * 20100819100900  --> Aug 19 2010 10:09:00:000AM
	 */
	public String getDateTimeFomart(String dateTime){
		Date date=new Date(Integer.parseInt(dateTime.substring(0,4))-1900,
				Integer.parseInt(dateTime.substring(4,6)),
				Integer.parseInt(dateTime.substring(6,8)),
				Integer.parseInt(dateTime.substring(8,10)),
				Integer.parseInt(dateTime.substring(10,12)),
				Integer.parseInt(dateTime.substring(12,14)));
		SimpleDateFormat sdf=new SimpleDateFormat("MMM MM yyyy hh:mm:ss:SSSa ",Locale.ENGLISH);
		return sdf.format(date).toString();		
	}
	
	
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
	 * 日期格式：8 yyyMMddHHmm
	 * */
	public static final DateFormat formatVIII = new SimpleDateFormat("yyyyMMddHHmm");

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
		try{
			if (date == null) {
				return "";
			}
			return switchDateFormat(style).format(date);
		}catch(Exception e){
			BoException be = new BoException("formatDate");
			be.setExceptionType("formatDate failed！");
			throw be;
		}
	}
	
	

	
	/**
	 * <p>转换日期格式 </p>
	 * <p>date：要转换的日期字符串</p>
	 * <p>fromStyle：参照日期格式的注释</p>
	 * <p>targetStyle：参照日期格式的注释</p>
	 * <p>日期格式style: </p>
	 * <p>6:yyyyMMdd </p>
	 * <p>4: MM-dd</p>
	 * <p>3: MM-dd HH:mm</p>
	 * <p>2: yyyy-MM-dd </p>
	 * <p>0: yyyyMMddHHmmss </p>
	 * <p>7: HHmmss</p>
	 * <p>default: 5 yyyy-MM-dd HH:mm:ss</p>
	 * */
	public static  String convertDateStyle(String date ,int fromStyle ,int targetStyle){
		try{
//			return convertDateFormat(date,switchDateFormat(fromStyle),switchDateFormat(targetStyle));  //ddeason 20140225屏蔽此函数，这个函数可能导致并发double parse异常
			return TimeUtil.changeStrTimeFormat(date,switchDateFormatToStr(fromStyle),switchDateFormatToStr(targetStyle));
		}catch(Exception e){
			BoException be = new BoException("convertDateStyle");
			be.setExceptionType("转换日期格式 失败！");
			throw be;
		}
	}
	public static String switchDateFormatToStr(int style){
		String result="";
		switch (style){
		case 5:result="yyyy-MM-dd HH:mm:ss";break;
		case 7:result="HHmmss";break;
		case 6:result="yyyyMMdd";break;
		case 4:result="MM-dd";break;
		case 3:result="MM-dd HH:mm";break;
		case 2:result="yyyy-MM-dd";break;
		case 0:result="yyyyMMddHHmmss";break;
		default:result="yyyyMMddHHmmss";
		}
		return result;
	}
	
//	public static void main(String [] s){
//		System.out.println(convertDateStyle("20110103081127",0,5));
//	}
	/**
	 * 转换日期格式
	 * */
	private static String convertDateFormat(String date,DateFormat fromStyle,DateFormat targetStyle){
		try{
			if( null == date || date.length()==0 ){
				return "";
			}
			Date d = fromStyle.parse(date); 
			return targetStyle.format(d);
		}catch (ParseException e) {
			logger.error(e.getMessage(), e);
			return "";
		}
	}
	/**
	 * <p> 获取日期格式 </p>
	 * <p> params: date:时间一般是new Date() </p>
	 * <p> style: int如下：</p> 
	 * <p> 6:yyyyMMdd </p>
	 * <p> 4: MM-dd</p>
	 * <p> 3: MM-dd HH:mm</p>
	 * <p> 2: yyyy-MM-dd </p>
	 * <p> 0: yyyMMddHHmmss </p>
	 *	<p>  7: HHmmss</p>
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
		case 8:
			return formatVIII;
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
//	public static String dscrTime(int time){
//		// time --》秒
//		if(time<0)return null;
//		StringBuilder sb = new StringBuilder();
//		if(time>60*60){
//			sb.append(time/60/60);
//			sb.append("小时");
//		}
//		if(time>60){
//			sb.append(time/60);
//			sb.append("分");
//		}
//		sb.append(time%60);
//		sb.append("秒");
//		return sb.toString();
//	}
	
	 public static String dscrTime(int second){
		  int h = 0;
		  int d = 0;
		  int s = 0;
		  int temp = second%3600;
		       if(second>3600 ||second==3600 ){
		         h= second/3600;
		              if(temp!=0){
		         if(temp>60){
		         d = temp/60;
		      if(temp%60!=0){
		         s = temp%60;
		      }
		      }else{
		         s = temp;
		      }
		     }
		    }else{
		        d = second/60;
		     if(second%60!=0){
		        s = second%60;
		     }
		    }

		   return h+"时"+d+"分"+s+"秒";
		 }
	 
	 /**
	  * 日期转换yyyyMMdd --> yyyy-MM-dd
	  */
	 public static String dateTransFmt(String date){
		  return  date.substring(0,4)+"-"+date.substring(4,6)+"-"+date.substring(6,8);
		
	 }
	 
	 /**
	 * 日期转换yyyy-MM-dd --> yyyyMMdd
	 */
	public static String dateTransFmt2(String date){
		 String[] str = date.split("-");
		 return str[0] + str[1] + str[2];
	 }
	 
	 public static String getDayOfWeek(String date){
		 Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		
		try {
			cal.setTime(format.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int result = cal.get(Calendar.DAY_OF_WEEK);
		String[] text= {"星期天","星期一","星期二","星期三","星期四","星期五","星期六"};
		return text[result-1];
	 }
	 
	 public static String fmtTransFree( String date, String from, String to ){
		 SimpleDateFormat format = new SimpleDateFormat(from);
		 try {
			Date d = format.parse(date);
			format.applyPattern(to);
			return format.format(d);
		 } catch (ParseException e) {
			e.printStackTrace();
		 }
		 return null;
	 }
	 
	 /**
	  * 字符串的日期格式的计算
	  * @param sDate	格式为“yyyy-MM-dd”
	  * @param bDate	格式为“yyyy-MM-dd”
	  * @return
	  */
	 public static String getDaysBetween(String sDate,String bDate){
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
	     Calendar cal = Calendar.getInstance();    
	     try {
	    	 cal.setTime(sdf.parse(sDate));
	    	 long time1 = cal.getTimeInMillis();
	    	 cal.setTime(sdf.parse(bDate));
	    	 long time2 = cal.getTimeInMillis();
	    	 if(time1>time2){
	    		 return "";
	    	 }else{
	    		 long between_days=(time2-time1)/(1000*3600*24);
		    	 return String.valueOf(Integer.parseInt(String.valueOf(between_days))); 
	    	 }
	     } catch (ParseException e) {
	    	 // TODO Auto-generated catch block
	    	 e.printStackTrace();
	     }
	     return "";
	 }
	 
	 /**
	  * 日期加减
	  * @param Date
	  * @param days
	  * @return
	  */
	 public static String getDates(String Date,int days){
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    	try {
	    	    Date myDate = formatter.parse(Date);
	    	    Calendar c = Calendar.getInstance();
	    	    c.setTime(myDate);
	    	    c.add(Calendar.DATE, days);
	    	    myDate = c.getTime();
	    	    return formatter.format(myDate);
	    	} catch (ParseException e1) {
	    	    e1.printStackTrace();
	    	}
	    	return null;
	 }
}
