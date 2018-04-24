<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">    
    <title>千里佳缘--用户注册</title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <style type="text/css" media="screen">
    .red_box{
      height: 350px;
      width: 100%;
      background: #dc587c;
    }
    .login_box{
      width: 550px;
      height: 400px;
      margin: -250px auto 5px;
      border:1px solid #dce1e6;
      background: #fff;
    }
    .login_box>h1{
      text-align: center;
      color: #dc587c;
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
      background: #dc587c;
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
    .login_box select{
      height: 35px;
      width: 290px;
      border-radius: 8px;
      margin: 5px auto 15px;
      display: block;
    }


  </style>
  
  <body>
    <div class="red_box">
    </div>
    <div class="login_box">
      <h1>用户注册页面</h1>
      <form action = "<%= basePath%>servlet/register" method = "post">
      <input name = "username" type="text" placeholder="用户名"/>
      <input name = "password" type="password" placeholder="密码"/>
      <select name="userType" >
        <option value="1">普通用户</option>
        <option value="0">管理员</option>        
      </select>
      <input  type="submit" value="注册"/>
      <a href="<%= basePath%>servlet/login">已有账号?前往登录</a>
      </form>
    </div>  
  
  </body>
</html>