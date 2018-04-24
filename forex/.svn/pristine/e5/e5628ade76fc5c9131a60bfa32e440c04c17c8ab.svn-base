package com.talent.forex.core;

import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.talent.exam.constant.ResultConst;
import com.talent.exam.exception.JSONException;
import com.talent.exam.modules.admin_mng.model.ExamResult;
import net.sf.json.JSONArray;

import com.opensymphony.webwork.ServletActionContext;
import com.talent.base.BaseAction;
import net.sf.json.JsonConfig;
import net.sf.json.processors.DefaultValueProcessor;
import net.sf.json.util.CycleDetectionStrategy;
import net.sf.json.util.PropertyFilter;

public class ForexBaseAction extends BaseAction {
	//字符串分割符1级
	public final static String SFT="$$";
	//字符串分隔符2级
	public final static String BFT="@@";
	//随机数
	private String rand;

	//返回json对象
	protected void flushJSON(ExamResult<?> result){
		PrintWriter writer = null;
		try {
			HttpServletResponse httpServletResponse = ServletActionContext.getResponse();
			httpServletResponse.setContentType(ResultConst.JSON);
			writer = httpServletResponse.getWriter();
			JsonConfig jsonConfig= new JsonConfig();
			JsonConfig config = new JsonConfig();
			config.setJsonPropertyFilter(new PropertyFilter()
			{
				@Override
				public boolean apply(Object source, String name, Object value)
				{
					return value == null;
				}
			});
			JSONArray jsonArray=JSONArray.fromObject(result,config);
			String resultMessage=jsonArray.toString();
			writer.write(resultMessage);
			writer.flush();
		}catch (Exception e){
			logger.warning("flush json throw exception:"+e.getMessage());
			throw new JSONException(e.getMessage(),e.getCause());
		}finally {
			if (writer!=null)
				writer.close();
		}
	}


	
	//异步传输一个字符串
	protected void processText(String txt, String contentType) {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType(contentType);
			PrintWriter out = response.getWriter();
			out.print(txt);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String jsonHttpResponse(String s){
		processText(s,"application/json;charset=utf-8");
		return null;
	}

	//异步返回
	public String xmlHttpResponse(String s){
		processText(s,"text/plain;charset=GBK");
		return null;
	}
	
	
	/***
	 * 传递一个字符串
	 */
	public String outXmlHttpResponse(String s){
		if(null==s||"".equals(s)){
			xmlHttpResponse("fail$$请求提交不成功");
			return null;
		}else{
			return xmlHttpResponse(s);
		}
	}
	
	/**
	 * 异步传递一个分页到页面
	 */
	 public void echo(String data, Map mapInfo, 
			  HttpServletRequest request, HttpServletResponse response)
	  {
		    PrintWriter out = null;
		    response.setContentType("text/html");
		    response.setCharacterEncoding("GBK");
		    try {
		      out = response.getWriter();
		      request.setCharacterEncoding("GBK");
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
	
	/***
	 * 去除后面的$$、@@
	 * */
	public static StringBuilder trimFT(StringBuilder builder ){
		if(builder.length()>2&&(builder.indexOf(SFT)!=-1||builder.indexOf(BFT)!=-1)){
			builder.deleteCharAt(builder.length() - 1);
			builder.deleteCharAt(builder.length() - 1);
		}
		return builder;
	}


	/***
	 * 异步输出成功结果
	 * JSON格式数据
	 * @param listMap
	 */
	public void flushSuccessJSON(Collection<?> listMap){
		JSONArray jsonArray = JSONArray.fromObject(listMap);
		StringBuilder builder  = new  StringBuilder();
		builder.append("{\"result\":\"success\",\"msg\":\"success\",\"dataList\":");
		builder.append(jsonArray.toString());
		builder.append("}");
		//logger.warn(builder.toString());
		jsonHttpResponse(builder.toString());
	}
	/***
	 * 异步输出JSON格式数据
	 * 报错误消息
	 */
	public void flushFailJSON(String failMsg){
		StringBuilder builder  = new  StringBuilder();
		builder.append("{\"result\":\"fail\",\"msg\":\""); 
		builder.append(failMsg);
		builder.append("\",\"dataList\":\"null\"");
		builder.append("}");
		//logger.warn(builder.toString());
		jsonHttpResponse(builder.toString());
	}
	public void setRand(String rand){
		this.rand=rand;
	}
	public String getRand(){
		return this.rand;
	}
	
}
