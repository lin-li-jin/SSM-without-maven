<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ taglib prefix="ww" uri="webwork"%>
<%@ taglib uri="extremecomponents" prefix="ec"%>

<!-- create by zjh  2010.8.31 -->

<HTML>
	<HEAD>
		<title>学生信息导入结果</title>
		<link rel="stylesheet" href="resources/css/style.css" type="text/css"
			media="screen, print" />
		<link rel="stylesheet" href="resources/css/extremecomponents.css"
			type="text/css" />
		<meta http-equiv=Pragma content=no-cache>
		<script languange="javascript">			
			
		</script>

	</HEAD>

<body id="body">
	<div id="body">
		<FIELDSET align="center">
			<LEGEND>学生信息导入结果</LEGEND>
			<span style="font-size:25px;color="red"">导入成功！</span>
		</FIELDSET>
		<div style="position:abosolute;text-align:left; font-size:14px;margin-top:10px;margin-left:2px;">
			<span style="color:red">以下为导入学生信息列表，请核对！</span>
		</div>
		<ww:if test="#session.userList != null">
			<ec:table items="userList" var="user" width="100%"
				action="stuQueryTable.action" border="" cellpadding="5"
				cellspacing="2" style="eXtremeTable">
				<ec:export view="xls" fileName="学生信息.xls" imageName="xls"
				 viewResolver="xls" tooltip="导出 Excel"/>
					<ec:row>
						<ec:column property="userNum" title="学号" escapeAutoFormat="true"
								   headerStyle="{text-align:center}" style="{text-align:center}" />
						<ec:column property="userName" title="姓名" sortable="false"
									headerStyle="{text-align:center}" style="{text-align:center}" />
						<ec:column property="groupOne" title="一级组" sortable="false"
									headerStyle="{text-align:center}" style="{text-align:center}" />
						<ec:column property="groupTwo" title="二级组" sortable="false"
									headerStyle="{text-align:center}" style="{text-align:center}" />
					</ec:row>
			</ec:table>
		</ww:if>
		<div>
		<center>
			<input type="button" class="button"
			 value="重新上传" onClick="window.location='stuDataInputInit.action'">
		</center>
	</div>	
	</div>
</body>
</HTML>

