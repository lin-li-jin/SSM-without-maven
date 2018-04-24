<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function validForm(){
	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;
	
	if(null == username || "" == username){
		alert("请输入用户名");
		document.getElementById("username").focus();
		return false;
	}
	
	if(null == password || "" == password){
		alert("请输入密码");
		document.getElementById("password").focus();
		return false;
	}
	
	return true;
}
</script>
<style type="text/css">
.align-center {
	margin: 0 auto; /* 居中 这个是必须的，，其它的属性非必须 */
	width: 400px; /* 给个宽度 顶到浏览器的两边就看不出居中效果了 */
	background: #99ffff; /* 背景色 */
	text-align: center; /* 文字等内容居中 */
}
</style>
</head>
<body>
	<div class="align-center">
		<form name="loginForm" id="loginForm" method="post" action="login.action" onsubmit="return validForm();">
			<table>
				<tr>
					<td style="text-align: right;width: 40%"><strong>用户名</strong>：</td>
					<td style="text-align: left;"><input type="text" name="username" id="username" value="root"/></td>
				</tr>
				<tr>
					<td style="text-align: right;width: 40%"><strong>密 码</strong>：</td>
					<td style="text-align: left;"><input type="password" name="password" id="password" value="123456"/></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" id="subBtn" value="登录"></td>
				</tr>
			</table>
		</form>
		<div style="color: red">${info}</div>
	</div>
</body>
</html>
