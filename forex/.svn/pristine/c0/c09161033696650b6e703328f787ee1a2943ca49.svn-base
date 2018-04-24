<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   <%
	String root = getServletContext().getRealPath("/");

	String path1 = "resources\\fileModel\\";
	String name = "excelFiles.rar";
	
	//System.out.println("-------"+root+path1+name);
	response.setContentType("text/plain");
	response.addHeader("Content-Disposition", "attachment; filename=\"" + name + "\"");

	try
	{
		response.reset();
		out.clear();
		out = pageContext.pushBody();
		java.io.OutputStream os = response.getOutputStream();
		java.io.FileInputStream fis = new java.io.FileInputStream(root + path1 + name);		
		byte[] b = new byte[1024];
		int i = 0;
		while ( (i = fis.read(b)) > 0 ) 
		{
			os.write(b, 0, i);
		}
	fis.close();
	os.flush();
	os.close();
	os=null;
	}catch ( Exception e ){}
%>
  </body>
</html>
