<%@ page contentType="text/html;charset=GBK" %> 
<%@ taglib prefix="ww" uri="webwork" %>
<%@ taglib uri="extremecomponents" prefix="ec" %>
<html>
<head>
<script language="JavaScript" src="resources/js/check.js"></script>
<script language="JavaScript" src="resources/js/Common.js"></script>
<script language="JavaScript" src="/forex/resources/calendar/js/Calendar.js"></script>
<script src="/eems/resources/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<link rel="stylesheet" href="/forex/resources/css/talent.css" type="text/css" />
<link rel="stylesheet" href="/forex/resources/css/talent_query.css" type="text/css" media="screen, print" />		
<link rel="stylesheet" href="/forex/resources/css/talent_tips.css" type="text/css" media="screen, print" />
<link rel="stylesheet" href="/forex/resources/css/extremecomponents.css" type="text/css" />
<link href="/forex/resources/calendar/css/calendar-blue.css" rel="stylesheet" />
<link href="/forex/jsp/local/itemProcess/css/ItemProcess.css" rel="stylesheet" />
<title>代码增加</title>
<script type="text/javascript">
	function submitForm() {
		if(!check("codeAddForm")){
			return;
		}
		document.all("codeAddForm").submit();
	}
			
</script>
</head>
<body>
<div id="body">
	<form name="codeAddForm" method="post" action="codeAdd.action">
		<fieldset class="query_container" >
			<legend class="mylegend">代码增加</legend>
					<!--<table width="100%" border="0" class="query_table">-->
			<div class="content left">
				<div>
					<label class="right">代码类型：</label>
					<span class="left"">
						<select name="codeTable.id.codeType" cName="代码类型" isNeed="y" >
							<option value=""></option>
							<ww:iterator
								value="#application.codeTypeTableUtil.codeTypeList">
								<option value="<ww:property value='codeType' />">
									<ww:property value="descr" />
								</option>
							</ww:iterator>
						</select><font color="red">*</font>
					</span>
				</div>
				<div>	
					<label class="right"">代码值：</label>
					<span class="left"">
						<input type="text" name="codeTable.id.codeVal" cName="代码值" len="50"  isNeed="y" 
							maxLength="50"><font color="red">*</font>
					</span>		
				</div>
				<div>
					<label class="right"">代码描述：</label>
					<span class="left"">
					<input type="text" name="codeTable.descr" cName="代码描述" isNeed="y" len="60" maxLength="60" size="60" ><font color="red">*</font>
					</span>				
				</div>
				<div>
					<label class="right"">生效日期：</label>
					<span class="left"">
						<input type="text" size="11" name="codeTable.activeDate" 
							cName="生效日期" isNeed="y" dataType="date" readonly="readonly" 
							onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
						<!-- 
						<a onClick="return showCalendar('codeTable.activeDate', 'y-mm-dd');" href="#">
							<img src="resources/calendar/image/Button.gif" id="imgid" align="absMiddle" border="0" /><font color="red">*</font>
						</a>
						-->
					</span>
				</div>		
			</div>
		</FIELDSET>
		</form>				
	<br />
	<br />
	<center>
		<input class="button" type="button" name="addBt" value="确 定" onclick="submitForm()">
		<input class="button" type="button" name="backBt" value="返 回" onclick="window.history.back()">
	</center>
	<br />			
	<br />
	</div>	
</body>
</html>