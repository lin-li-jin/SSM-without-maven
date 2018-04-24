<%@ page contentType="text/html;charset=GBK" %>
<%@ taglib prefix="ww" uri="webwork" %>
<html xmlns:v>

<head>
	<title>用户修改结果</title>
	<link rel="stylesheet" href="resources/css/talent.css" type="text/css" />
	<meta http-equiv="content-type" content="text/html; charset=GBK" />		
</head>

<body>
	<jsp:include page="../public/Result.jsp"/>	
	

	<center>
		<input type="button" class="button" value="返回列表" onClick="window.location='userReQuery.action'">&nbsp;&nbsp;
		<input type="button" class="button"
			   <ww:if test="#request.tipModel.processCode == '00'"> value="继续修改" onClick="window.location='userModifyInit.action?userInfo.userInfoId=<ww:property value='#session.userInfoModel.userInfoId' />'"</ww:if>
			   <ww:else> value="重新修改" onClick="window.history.back()"</ww:else>
		>
	</center>					
</body>

</html>