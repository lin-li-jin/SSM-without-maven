<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">    
    <title>千里佳缘--出错啦</title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
   <style type="text/css" media="screen">
    p{
      color: red;
      font-size: 16px;
      text-align: center;
      margin-top: 30px;
    }
  </style>
  
  <body>
  <p>
    <% out.println("错误信息是：" + session.getAttribute("error_message")); %>
  </p>
  </body>
</html>
