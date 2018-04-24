<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ taglib prefix="ww" uri="webwork"%>
<%@ taglib uri="extremecomponents" prefix="ec"%>
<html xmlns:v>

<!--
 * Amendment No : forex130012
 * Modify By : guogm
 * Date : 2013-08-14
 * Decription : 汇出汇款
 -->
<head>
	<title>境外汇款业务演示</title>
	<link rel="stylesheet" href="/forex/resources/css/style.css" type="text/css"
			media="screen, print" />
	<link rel="stylesheet" href="/forex/resources/css/talent.css" type="text/css"
			media="screen, print" />
	<script src="../../../resources/ponyjs/jquery.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#btnContent").css("display","none");
			var action='<ww:property value="#session.action"/>';
			$("#btnContent").append("<input class=\"button\" type=\"button\" value=\"填写表单\" onclick=\"jump('"+action+"')\">");
			var timer = setTimeout(showButton,7000);//毫秒为单位
		});
		function jump(action){
			var url = "showContent.action?action="+action;
			window.location = url;
			processTip();
		}
		function showButton(){
			$("#btnContent").css("display","block");
		}
	</script>
</head>

<body>
	<center>
	<br>
	<br>
	<object style="border:5px solid #ccc" type="application/x-shockwave-flash" data="Internation02.swf" width="675" height="500" id="dreamdu">
			<param name="movie" value="Internation02.swf" />
			<param name="quality" value="high" />
	</object>
	<br>
	<br>
	<div id="btnContent">
	</div>
	</center>
</body>

</html>