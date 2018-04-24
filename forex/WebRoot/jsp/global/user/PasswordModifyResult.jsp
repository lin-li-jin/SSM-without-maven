<%@ page contentType="text/html;charset=GBK" %>
<%@ taglib prefix="ww" uri="webwork" %>
<html xmlns:v>

<head>
	<title>密码修改结果</title>
	<link rel="stylesheet" href="resources/css/talent.css" type="text/css" />
	<meta http-equiv="content-type" content="text/html; charset=GBK" />		
</head>

<body>
	<jsp:include page="../public/Result.jsp"/>	
	
	<br>
	
	<center>
		<input type="button" class="button" value="返 回" onClick="window.history.go(-2)">&nbsp;&nbsp;
		<input type="button" class="button" value="重新修改" onClick="window.location='passwordModifyInit.action'">
	</center>					
</body>

</html>