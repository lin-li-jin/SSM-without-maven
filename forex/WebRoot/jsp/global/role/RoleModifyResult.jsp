<%@ page contentType="text/html;charset=GBK" %>
<%@ taglib prefix="ww" uri="webwork" %>
<html xmlns:v>

<head>
	<title>角色修改结果</title>
	<link rel="stylesheet" href="resources/css/talent.css" type="text/css" />
	<meta http-equiv="content-type" content="text/html; charset=GBK" />		
</head>

<body>
	<jsp:include page="../public/Result.jsp"/>	
	
	<br>
	
	<center>
		<input type="button" class="button" value="返回列表" onClick="window.location='rolesListQuery.action'">&nbsp;&nbsp;
		<input type="button"  class="button"
				<ww:if test="#request.tipModel.processCode == '00'"> value="继续修改" onClick="window.location='roleModifyInit.action?roles.rolesId=<ww:property value='#session.roles.rolesId' />'"</ww:if>
				<ww:else> value="重新修改" onClick="window.history.back()"</ww:else>
		>
	</center>					
</body>

</html>