<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ taglib prefix="ww" uri="webwork"%>
<%@ taglib uri="extremecomponents" prefix="ec"%>

<html>
	<head>
		<script language="JavaScript" src="resources/js/check.js"></script>
		<script language="JavaScript" src="resources/js/Common.js"></script>		
		<script language="JavaScript" src="/forex/resources/calendar/js/Calendar.js"></script>
		<link rel="stylesheet" href="/forex/resources/css/talent.css" type="text/css" />
		<link rel="stylesheet" href="/forex/resources/css/talent_query.css"
			type="text/css" media="screen, print" />
		<link rel="stylesheet" href="/forex/resources/css/talent_tips.css"
			type="text/css" media="screen, print" />
		<link href="/forex/resources/calendar/css/calendar-blue.css" rel="stylesheet" />
		<link rel="stylesheet" href="/forex/resources/css/extremecomponents.css"
			type="text/css" />
		<link href="/forex/jsp/local/itemProcess/css/ItemProcess.css"
			rel="stylesheet" />
		<title>代码管理</title>
		<script type="text/javascript">
			function submitForm() {
				document.all("codeQueryForm").submit();
			}
			
		</script>
	</head>
	<body>
		<div id="body">
			<form name="codeQueryForm" action="codeQuery.action" method="post">
				<fieldset class="query_container" >
				<legend class="mylegend">代码查询</legend>
					<div class="content left" style="width:100%">
						<div>
							<div>
								<label class="right">代码类型：</label>
								<span class="left">
									<select name="codeTable.id.codeType">
										<option value=""></option>
										<ww:iterator
											value="#application.codeTypeTableUtil.codeTypeList">
											<option value="<ww:property value='codeType' />"
											<ww:if test="codeTable.id.codeType == codeType">selected</ww:if>>
												<ww:property value="descr" />
											</option>
										</ww:iterator>
									</select>
								</span>
								<label class="right" style="margin-left: 112px">代码值：</label>
								<span class="left">
									<input type="text" name="codeTable.id.codeVal" cName="代码值" len="50" value="<ww:property value='codeTable.id.codeVal'/>"
										maxLength="50">
								</span>
							</div>
							<div>
								<label class="right">生效日期：</label>
								<span class="left">
									<input type="text" size="11" name="codeTable.activeDateSliceModel.beginDate" 
										value="<ww:property value='codeTable.activeDateSliceModel.beginDate'/>" cName="开始日期" dataType="date"/>
									<a onClick="return showCalendar('codeTable.activeDateSliceModel.beginDate', 'y-mm-dd');" href="#">
										<img src="/forex/resources/calendar/image/Button.gif" id="imgid" align="absMiddle" border="0" />
									</a>
									至
									<input type="text" size="11" name="codeTable.activeDateSliceModel.endDate"
										value="<ww:property value='codeTable.activeDateSliceModel.endDate'/>" cName="结束日期" dataType="date"/>
									<a onClick="return showCalendar('codeTable.activeDateSliceModel.endDate', 'y-mm-dd');" href="#">
										<img src="/forex/resources/calendar/image/Button.gif" id="imgid" align="absMiddle" border="0" />
									</a>
								</span>
								<label class="right">失效日期：</label>
								<span class="left">
									<input type="text" size="11" name="codeTable.closeDateSliceModel.beginDate" 
										value="<ww:property value='codeTable.closeDateSliceModel.beginDate'/>" cName="开始日期" dataType="date"/>
									<a onClick="return showCalendar('codeTable.closeDateSliceModel.beginDate', 'y-mm-dd');" href="#">
										<img src="/forex/resources/calendar/image/Button.gif" id="imgid" align="absMiddle" border="0" />
									</a>
									至
									<input type="text" size="11" name="codeTable.closeDateSliceModel.endDate"
										value="<ww:property value='codeTable.closeDateSliceModel.endDate'/>" cName="结束日期" dataType="date"/>
									<a onClick="return showCalendar('codeTable.closeDateSliceModel.endDate', 'y-mm-dd');" href="#">
										<img src="/forex/resources/calendar/image/Button.gif" id="imgid" align="absMiddle" border="0" />
									</a>
								</span>
							</div>
						</div>
						<div class="center">
							<input type="button" class="button" value="查 询" onClick="submitForm()" />
								&nbsp;
								<!-- <input type="reset" class="button" name="resetBt" value="重 置">
								&nbsp;-->
							<input type="button" class="button" name="addBt" value="增 加"
								onclick="window.location='codeAddInit.action'">
						</div>
					</div>
				</FIELDSET>
			</form>

			<ww:if test="#request.codeList != null">
				<ec:table items="codeList" var="codeTable"
					action="ecCodeQuery.action">
					<ec:export view="xls" fileName="代码列表.xls" imageName="xls"
						viewResolver="xls" tooltip="导出 Excel" />

					<ec:row>
						<ec:column property="codeTypeDescr" title="代码类型"
							headerStyle="{text-align:center}" style="{text-align:left;width:100px}" />
						<ec:column property="codeVal" title="代码值"
							headerStyle="{text-align:center}" style="{text-align:left;width:100px}" />
						<ec:column property="descr" title="代码描述" 
							headerStyle="{text-align:center}" style="{text-align:left;width:200px}" />
						<ec:column property="activeDateFmt" title="生效日期"
							headerStyle="{text-align:center}" style="{text-align:left;width:100px}" />
						<ec:column property="closeDateFmt" title="失效日期"
							headerStyle="{text-align:center}" style="{text-align:left;width:100px}" />
						<ec:column property="operation" title="操作"  sortable="false"
							headerStyle="{text-align:center}" style="{width:150px}"viewsAllowed="html" />
					</ec:row>
				</ec:table>
				<br/><br/>
			</ww:if>
		</div>
	</body>
</html>