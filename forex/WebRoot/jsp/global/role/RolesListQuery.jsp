<%@ page contentType="text/html;charset=GBK" %> 
<%@ taglib prefix="ww" uri="webwork" %>
<%@ taglib uri="extremecomponents" prefix="ec" %>

<html>
<head>

<link rel="stylesheet" href="../../../resources/css/talent.css" type="text/css" media="screen, print" />
<link rel="stylesheet" href="../../../resources/css/extremecomponents.css" type="text/css" />
<title>角色管理</title>
	
</head>



<body>
<div id="body">

	<%--<jsp:include page="../public/TipsBar.jsp"/>
	--%><form>
		<div>
			<a href="roleAddInit.action" > 
			    <img src="jsp/global/role/image/ABOUTUS.GIF" alt="创建新角色" height="40" width="40" border="0"/><br>
			    <b>创建新角色</b>
		    </a>
		</div>
	</form>	
				
	<ec:table 
		items="rolesList"
		var="role" 
		action="ecRolesListQuery.action" 
		>
		<ec:export
			view="xls" 
			fileName="角色列表.xls" 
			imageName="xls"
			viewResolver="xls"
			tooltip="导出 Excel"/>
											
		<ec:row>
			<ec:column property="name" title="名称" headerStyle="{text-align:center}" style="{text-align:left}" />
			<ec:column property="rolesLevel" title="级别" headerStyle="{text-align:center}" style="{text-align:right}" />
			<ec:column property="descr" title="描述" headerStyle="{text-align:center}" style="{text-align:left}" />
			<ec:column property="operation" title="操作" headerStyle="{text-align:center}" viewsAllowed="html" />
		</ec:row>
	</ec:table>	
	</div>
</body>
