<%@ page language="java" import="java.util.*" pageEncoding="GBk"%>
<html> 
<head> 
<title></title> 
<script type="text/javascript">
function to(){
	//var url1="/eems/jsp/global/login/Login.jsp";
	//window.parent.top.location=url1;
	var url1="/ec";
	
	if(confirm('登录超时，请重新到实验管理系统中跳转到考核系统中！此页面将被关闭，是否继续？'))
	{window.parent.top.location=url1;}
	//window.parent.close();}
	//else{return;}
}
</script>
</head> 
<body  onload="to()">
</body> 
</html> 