<%@ page contentType="text/html;charset=GBK" %> 
<%@ taglib prefix="ww" uri="webwork" %>
<%@ taglib uri="extremecomponents" prefix="ec" %>

<html>
<head>
<script language="JavaScript" src="/forex/resources/js/check.js"></script>
<script language="JavaScript" src="/forex/resources/js/Common.js"></script>
<script language="JavaScript" src="/forex/resources/calendar/js/Calendar.js"></script>
<link rel="stylesheet" href="/forex/resources/css/talent.css" type="text/css" />
<link rel="stylesheet" href="/forex/resources/css/talent_query.css" type="text/css" media="screen, print" />		
<link rel="stylesheet" href="/forex/resources/css/talent_tips.css" type="text/css" media="screen, print" />
<link rel="stylesheet" href="/forex/resources/css/extremecomponents.css" type="text/css" />
<link href="/forex/resources/calendar/css/calendar-blue.css" rel="stylesheet" />
<link href="/forex/jsp/local/itemProcess/css/ItemProcess.css" rel="stylesheet" />
<script type="text/javascript">
		function submitForm() {
			if(!check("codeModifyForm")){
				return;
			}
			document.all("codeModifyForm").submit();
		}
			
</script>
<title>代码修改</title>
	
</head>



<body>
<div id="body">
	<form name="codeModifyForm" method="post" action="codeModify.action">	
	<fieldset class="query_container" >
		<legend class="mylegend">代码修改</legend>
		<!--<table width="100%" border="0" class="query_table">-->
		<div class="content left expandMargin">
			<div>
				<label class="right">代码类型：</label>
				<span class="left">
					<ww:property value='#request.codeTable.codeTypeDescr'/>
				</span>
			</div>
			<div>	
				<label class="right">代码值：</label>
				<span class="left">
					<ww:property value='#request.codeTable.id.codeVal'/>
				</span>				
			</div>
			<div>
				<label class="right">代码描述：</label>
				<span class="left">
					<input type="text" name="codeTable.descr" cName="代码描述" isNeed="y" len="60" maxLength="60" size="60" value="<ww:property value='#request.codeTable.descr'/>"><font color="red">*</font>
				</span>				
			</div>
			<div>
				<label class="right">生效日期：</label>
				<span class="left">
					<input type="text" size="11" name="codeTable.activeDate" cName="生效日期" isNeed="y" dataType="date" value="<ww:property value='#request.codeTable.activeDateFmt'/>"/>
					<a onClick="return showCalendar('codeTable.activeDate', 'y-mm-dd');" href="#">
						<img src="resources/calendar/image/Button.gif" id="imgid" align="absMiddle" border="0" /><font color="red">*</font>
					</a>
				</span>
			</div>
			<div>	
				<label class="right">失效日期：</label>
				<span class="left">
					<input type="text" size="11" name="codeTable.closeDate" cName="失效日期" dataType="date" value="<ww:property value='#request.codeTable.closeDateFmt'/>"/>
					<a onClick="return showCalendar('codeTable.closeDate', 'y-mm-dd');" href="#">
						<img src="resources/calendar/image/Button.gif" id="imgid" align="absMiddle" border="0" />
					</a>
				</span>			
			</div>			
		</div>
		<input type="hidden" name="codeTable.id.codeType" value="<ww:property value='#request.codeTable.id.codeType' />">
		<input type="hidden" name="codeTable.id.codeVal" value="<ww:property value='#request.codeTable.id.codeVal' />">
	</FIELDSET>		
	</form>					
	<br />
	<br />
	<center>
		<input class="button" type="button" name="modifyBt" value="确 定" onclick="submitForm()">
		<input class="button" type="button" name="backBt" value="返 回" onclick="window.history.back()">
	</center>
	<br />			
	<br />
	</div>	
</body>
</html>