<%@ page contentType="text/html;charset=GBK" %>
<%@ taglib prefix="ww" uri="webwork" %>
<html xmlns:v>

<head>
	<title>角色删除结果</title>
	<link rel="stylesheet" href="resources/css/talent.css" type="text/css" />
	<meta http-equiv="content-type" content="text/html; charset=GBK" />		
</head>

<body>
	<jsp:include page="../public/Result.jsp"/>	
	
	<br>
	
	<center>
		<input type="button" class="button" value="返回列表" onClick="window.location='rolesListQuery.action'">
		<ww:if test="#request.tipModel.processCode != '00'">
			&nbsp;&nbsp;
			<input type="button" class="button" value="重新撤销" onClick="window.location='roleDelInit.action?roles.rolesId=<ww:property value='#session.roles.rolesId' />'">
		</ww:if>
	</center>					
</body>

</html>