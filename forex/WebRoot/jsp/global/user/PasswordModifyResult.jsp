<%@ page contentType="text/html;charset=GBK" %>
<%@ taglib prefix="ww" uri="webwork" %>
<html xmlns:v>

<head>
	<title>�����޸Ľ��</title>
	<link rel="stylesheet" href="resources/css/talent.css" type="text/css" />
	<meta http-equiv="content-type" content="text/html; charset=GBK" />		
</head>

<body>
	<jsp:include page="../public/Result.jsp"/>	
	
	<br>
	
	<center>
		<input type="button" class="button" value="�� ��" onClick="window.history.go(-2)">&nbsp;&nbsp;
		<input type="button" class="button" value="�����޸�" onClick="window.location='passwordModifyInit.action'">
	</center>					
</body>

</html>