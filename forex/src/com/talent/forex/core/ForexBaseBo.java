package com.talent.forex.core;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.talent.auth.bean.model.UserModel;
import com.talent.auth.login.LoginConst;
import com.talent.base.BaseBo;
import com.talent.tools.WebWorkUtil;

public class ForexBaseBo extends BaseBo {
	//字符串分割符1级
	public final static String SFT="$$";
	//字符串分隔符2级
	public final static String BFT="@@";
	
	public final static Boolean TRUE=Boolean.TRUE;
	public final static Boolean FALSE=Boolean.FALSE;
	
	/*去除后面的$$、@@*/
	public static StringBuilder trimFT(StringBuilder builder ){
		if(builder.length()>2&&(builder.indexOf(SFT)!=-1||builder.indexOf(BFT)!=-1)){
			builder.deleteCharAt(builder.length() - 1);
			builder.deleteCharAt(builder.length() - 1);
		}
		return builder;
	}
	
	public static UserModel getUserModel(){
		return  (UserModel)WebWorkUtil.sessionGet(LoginConst.LOGIN_USER_MODEL);
	}
	/**
	 * 异步方法调用
	 * data:json list数据
	 * MapInfo 分页数据
	 * */
	 public void echo(String data, Map mapInfo, 
			  HttpServletRequest request, HttpServletResponse response)
	  {
		    PrintWriter out = null;
		    response.setContentType("text/html");
		    response.setCharacterEncoding("UTF-8");
		    try {
		      out = response.getWriter();
		      request.setCharacterEncoding("UTF-8");
		    } catch (Exception e) {
		    	System.out.println("ajax发生异常:" + e.getMessage());
		    }
		
		    if (mapInfo != null) {
			      String totalrows = (String)mapInfo.get("totalrows");
			      String perpage = (String)mapInfo.get("perpage");
			      String nowpage = (String)mapInfo.get("nowpage");
			      String totalpages = (String)mapInfo.get("totalpages");
			
			      String info = "\"info\":[";
			      info = info + "{\"totalrows\":\"" + totalrows + "\",\"perpage\":\"" + perpage + "\",\"nowpage\":\"" + nowpage + "\",\"totalpages\":\"" + totalpages + "\"}";
			      info = info + "]";
			
			      data = "{" + info + ",\"datalist\":" + data + "}";
		    } else {
		    	data = "{\"datalist\":" + data + "}";
		    }
		
		    out.println(data);
		    out.flush();
		    out.close();
	  }
}
