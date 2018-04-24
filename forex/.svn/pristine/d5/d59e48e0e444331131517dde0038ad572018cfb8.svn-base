<%@ page contentType="text/html;charset=GBK" %>
<%@ taglib prefix="ww" uri="webwork" %>
<html xmlns:v>

<head>
	<title>用户增加结果</title>
	<link rel="stylesheet" href="resources/css/talent.css" type="text/css" />
	<meta http-equiv="content-type" content="text/html; charset=GBK" />		
</head>

<body>
	<jsp:include page="../public/Result.jsp"/>	
	
	<br>
	
	<center>
		<input type="button" class="button" value="返回列表" onClick="window.location='userReQuery.action'">&nbsp;&nbsp;
		<input type="button" class="button"
			   <ww:if test="#request.tipModel.processCode == '00'"> value="继续增加" onClick="window.location='userAddInit.action'"</ww:if>
			   <ww:else> value="重新增加" onClick="window.history.back()"</ww:else>
		>
	</center>					
</body>

</html>