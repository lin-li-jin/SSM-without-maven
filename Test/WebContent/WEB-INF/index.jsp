<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function open(url){
	document.getElementById("mainFrame").src = url;
}

function largePayUnpack(){
	if(confirm('请确认C:\\pay目录下存在报文文件')){
		open('laygePay/unpack.action');
	}
}
</script>
<style type="text/css">
a{
	margin-left: 20px;
}
</style>
</head>
<body>
欢迎您！${loginUser.username}<br>
<div style="height: 60px;">
	<a href="javascript:open('studentList.action');">单表增删改查</a>
	<a href="javascript:open('laygePay/toInput.action');">大额交易录入组包</a>
	<a href="javascript:largePayUnpack();">大额交易拆包</a>
</div>
<div>
<iframe id="mainFrame" width="100%" height="600px" frameborder="no" border="0" marginwidth="0" 
	marginheight="0" scrolling="auto"  src=""></iframe>
</div>
</body>
</html>
