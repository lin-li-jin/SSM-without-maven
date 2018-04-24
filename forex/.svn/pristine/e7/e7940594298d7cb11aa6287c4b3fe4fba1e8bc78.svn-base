<%@ page contentType="text/html;charset=GBK" %>
<%@ taglib prefix="ww" uri="webwork" %>
<html xmlns:v>

<head>
	<title>新增学生信息结果</title>
	<link rel="stylesheet" href="resources/css/talent.css" type="text/css" />
	<meta http-equiv="content-type" content="text/html; charset=GBK" />		
</head>

<body>
	<jsp:include page="../../../global/public/Result.jsp"/>	
	
	<br>
	
	<center>
				<input type="button" class="button"
					<ww:if test="#request.tipModel.processCode == '00'"> value="返回查询" onClick="window.location='studentInfoQueryInit.action'"</ww:if>
					<ww:else> value="重新增加" onClick="window.history.back()"</ww:else>
				>
	</center>					
</body>

</html>