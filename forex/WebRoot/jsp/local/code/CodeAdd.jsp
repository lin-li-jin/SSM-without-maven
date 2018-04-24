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
<title>��������</title>
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
			<legend class="mylegend">��������</legend>
					<!--<table width="100%" border="0" class="query_table">-->
			<div class="content left">
				<div>
					<label class="right">�������ͣ�</label>
					<span class="left"">
						<select name="codeTable.id.codeType" cName="��������" isNeed="y" >
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
					<label class="right"">����ֵ��</label>
					<span class="left"">
						<input type="text" name="codeTable.id.codeVal" cName="����ֵ" len="50"  isNeed="y" 
							maxLength="50"><font color="red">*</font>
					</span>		
				</div>
				<div>
					<label class="right"">����������</label>
					<span class="left"">
					<input type="text" name="codeTable.descr" cName="��������" isNeed="y" len="60" maxLength="60" size="60" ><font color="red">*</font>
					</span>				
				</div>
				<div>
					<label class="right"">��Ч���ڣ�</label>
					<span class="left"">
						<input type="text" size="11" name="codeTable.activeDate" 
							cName="��Ч����" isNeed="y" dataType="date" readonly="readonly" 
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
		<input class="button" type="button" name="addBt" value="ȷ ��" onclick="submitForm()">
		<input class="button" type="button" name="backBt" value="�� ��" onclick="window.history.back()">
	</center>
	<br />			
	<br />
	</div>	
</body>
</html>