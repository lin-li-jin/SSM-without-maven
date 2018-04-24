package com.talent.forex.util;

import com.talent.exception.BoException;



public class GetFixWordUtil {
	
	public static String getFixWords(String word){
		try{
			String result="";
			if(null!=word&&!("").equals(word)){
				result=word;
			}
			return result;
		}catch(Exception e){
			BoException be = new BoException("getFixWords");
			be.setExceptionType("fix words failed£¡");
			throw be;
		}
	}
	
	public static String getLikeWords(String word){
		try{
			String result="";
			if(null!=word&&!("").equals(word)){
				result="%"+word+"%";
			}else{
				result="%%";
			}
			return result;
		}catch(Exception e){
			BoException be = new BoException("getFixWords");
			be.setExceptionType("fix words failed£¡");
			throw be;
		}
	}
	
}
