<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="ww" uri="webwork" %>
<html xmlns:v>

<head>
	<title>��Ϣ¼����</title>
	<link rel="stylesheet" href="resources/css/rebss.css" type="text/css" />
	<link rel="stylesheet" href="resources/css/style.css" type="text/css" />
	<meta http-equiv="content-type" content="text/html; charset=GBK" />		
</head>

<body>
	<jsp:include page="../../../global/public/Result.jsp"/>	
	
	<br>
	<div>
	<center>
	<input type="button" class="button"
			   <ww:if test="#request.tipModel.processCode == '00'"> value="�����ϴ�" onClick="window.history.back()"</ww:if>
			   <ww:else> value="�����ϴ�" onClick="window.history.back()"</ww:else>
		>
	</center>
	</div>					
</body>

</html>