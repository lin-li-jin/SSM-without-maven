<%@ page contentType="text/html;charset=GBK" %> 
<%@ taglib prefix="ww" uri="webwork" %>
<%@ taglib uri="extremecomponents" prefix="ec" %>

<html>
<head>
<script language="JavaScript" src="jsp/global/role/js/Role.js"></script>
<script language="JavaScript" src="resources/js/Common.js"></script>
<link rel="stylesheet" href="../../../resources/css/talent.css" type="text/css" />
<link rel="stylesheet" href="../../../resources/css/talent_query.css" type="text/css" media="screen, print" />		
<link rel="stylesheet" href="../../../resources/css/talent_tips.css" type="text/css" media="screen, print" />
<link rel="stylesheet" href="../../../resources/css/extremecomponents.css" type="text/css" />
<title>角色增加</title>
	
</head>



<body>
<div id="body">
	<%--<jsp:include page="../public/TipsBar.jsp"/>
	--%><FIELDSET style="WIDTH:90%" align="center">
		<LEGEND>
			<div style="width:300px;">	 
				<div class="curved">
					<b class="b1"></b><b class="b2"></b><b class="b3"></b><b class="b4"></b>
						<div class="boxcontent">
							<font  style="{size:3}" color="#000000">角色增加</font>
						</div>
					<b class="b4"></b><b class="b3"></b><b class="b2"></b><b class="b1"></b>
				</div>					
			</div>
		</LEGEND>
		
		<div class="content left">	
			<div>
				<label class="right">角色名称：</label>
				<span class="left">
					<input type="text" name="roleName" isNeed="y" cName="角色名称" len="50" maxLength="50" size="60" onFocus="nextfield='rolesLevel'" value="<ww:property value='#session.roles.name'/>">
				</span>				
			</div>
			<div>
				<label class="right">角色级别：</label>
				<span class="left">
					<input type="text" name="rolesLevel" isNeed="y" cName="角色级别" dataType="zhengInt" len="50" maxLength="50" size="60" onFocus="nextfield='roleDescr'" value="<ww:property value='#session.roles.rolesLevel'/>">
				</span>				
			</div>
			<div>
				<label class="right">角色描述：</label>
				<span class="left">
					<input type="text" name="roleDescr" cName="角色描述" len="100" maxLength="100" size="60" value="<ww:property value='#session.roles.descr'/>">
				</span>				
			</div>
			<div>
				<label class="right floatLeft" style="margin-top: 6px">权限列表：</label>
				<span class="left">
					<ec:table 
						items="permissionsList"
						var="permission" 
						showPagination="false"
						showStatusBar="false"
						>
															
						<ec:row>
							<ec:column property="select" title="选择" headerStyle="{text-align:center}" />
							<ec:column property="name" title="名称" headerStyle="{text-align:center}" />
							<ec:column property="descr" title="描述" headerStyle="{text-align:center}"/>
						</ec:row>
					</ec:table>	
				</span>				
			</div>						
		</div>			
	</FIELDSET>				
	
	<br />
	<br />
	<center>
			<input class="button" type="button" name="submitBt" value="提 交" onclick="roleSubmit('roleAddForm')">
			<input class="button" type="button" name="backBt" value="返 回" onclick="window.location='rolesListQuery.action'">
	</center>
	<br />
	<br />
	
	<form name="roleAddForm" method="post" action="roleAdd.action">
		<input type="hidden" name="roles.scope" value="ALL">
		<input type="hidden" name="roles.name">
		<input type="hidden" name="roles.rolesLevel">
		<input type="hidden" name="roles.descr">
		<input type="hidden" name="permIds">
	</form>	
	</div>
</body>
