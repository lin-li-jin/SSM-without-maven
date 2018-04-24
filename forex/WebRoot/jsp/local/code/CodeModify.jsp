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
<title>�����޸�</title>
	
</head>



<body>
<div id="body">
	<form name="codeModifyForm" method="post" action="codeModify.action">	
	<fieldset class="query_container" >
		<legend class="mylegend">�����޸�</legend>
		<!--<table width="100%" border="0" class="query_table">-->
		<div class="content left expandMargin">
			<div>
				<label class="right">�������ͣ�</label>
				<span class="left">
					<ww:property value='#request.codeTable.codeTypeDescr'/>
				</span>
			</div>
			<div>	
				<label class="right">����ֵ��</label>
				<span class="left">
					<ww:property value='#request.codeTable.id.codeVal'/>
				</span>				
			</div>
			<div>
				<label class="right">����������</label>
				<span class="left">
					<input type="text" name="codeTable.descr" cName="��������" isNeed="y" len="60" maxLength="60" size="60" value="<ww:property value='#request.codeTable.descr'/>"><font color="red">*</font>
				</span>				
			</div>
			<div>
				<label class="right">��Ч���ڣ�</label>
				<span class="left">
					<input type="text" size="11" name="codeTable.activeDate" cName="��Ч����" isNeed="y" dataType="date" value="<ww:property value='#request.codeTable.activeDateFmt'/>"/>
					<a onClick="return showCalendar('codeTable.activeDate', 'y-mm-dd');" href="#">
						<img src="resources/calendar/image/Button.gif" id="imgid" align="absMiddle" border="0" /><font color="red">*</font>
					</a>
				</span>
			</div>
			<div>	
				<label class="right">ʧЧ���ڣ�</label>
				<span class="left">
					<input type="text" size="11" name="codeTable.closeDate" cName="ʧЧ����" dataType="date" value="<ww:property value='#request.codeTable.closeDateFmt'/>"/>
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
		<input class="button" type="button" name="modifyBt" value="ȷ ��" onclick="submitForm()">
		<input class="button" type="button" name="backBt" value="�� ��" onclick="window.history.back()">
	</center>
	<br />			
	<br />
	</div>	
</body>
</html>