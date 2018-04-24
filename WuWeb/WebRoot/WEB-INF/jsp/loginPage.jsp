<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">    
    <title>千里佳缘--用户登录</title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <style type="text/css" media="screen">
    .green_box{
      height: 350px;
      width: 100%;
      background: #00b38a;
    }
    .login_box{
      width: 350px;
      height: 300px;
      margin: -150px auto 5px;
      border:1px solid #dce1e6;
      background: #fff;
    }
    .login_box>h1{
      text-align: center;
    }
    .login_box input{
      width: 290px;
      height: 45px;
      margin: 20px auto;
      display: block;
      border-radius: 8px;
    }
    .login_box input[type="submit"]{
      height: 35px;
      line-height: 35px;
      width: 70px;
      margin: 5px auto;
      background: #00b38a;
      color: #fff;
      display: block;
      border-radius: 8px;
      text-align: center;
    }
    .login_box a{
      text-align: center;
      font-size: 12px;
      color: #bf5858;
      display: block;
    }


  </style>
  
  <body>
    <div class="green_box">
    </div>
    <div class="login_box">
      <h1>用户登录页面</h1>
      <form action = "<%= basePath%>servlet/login" method = "post">
      <input name = "username" type="text" placeholder="用户名"/>
      <input name = "password" type="password" placeholder="密码"/>
      <input  type="submit" value="登录"/>
      <a href="<%= basePath%>servlet/register">还没注册?前往注册</a>
      </form>
    </div>  
  
  </body>
</html>
