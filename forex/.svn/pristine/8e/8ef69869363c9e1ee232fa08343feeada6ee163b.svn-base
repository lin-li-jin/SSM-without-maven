<%@ page contentType="text/html;charset=GBK" %> 
<%@ taglib prefix="ww" uri="webwork" %>
<%@ taglib uri="extremecomponents" prefix="ec" %>

<html>
<head>
<script language="JavaScript" src="resources/js/Common.js"></script>
<link rel="stylesheet" href="/forex/resources/css/talent.css" type="text/css" />
<link rel="stylesheet" href="/forex/resources/css/talent_query.css" type="text/css" media="screen, print" />		
<link rel="stylesheet" href="/forex/resources/css/talent_tips.css" type="text/css" media="screen, print" />
<link rel="stylesheet" href="/forex/resources/css/extremecomponents.css" type="text/css" />
<link href="jsp/local/itemProcess/css/ItemProcess.css" rel="stylesheet" />
<script type="text/javascript">
		function submitForm() {
			if(confirm("确认删除？")){
				document.all("codeDelForm").submit();
			}
		}			
</script>
<style type="text/css">
	div.content div{
		margin-top: 20px;
	}
</style>
<title>代码删除</title>
	
</head>



<body>
<div id="body">
	<!--<fieldset class="query_container" >-->
	<FIELDSET style="WIDTH:100%" align="center">
		<legend class="mylegend">代码删除</legend>
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
					<ww:property value='#request.codeTable.descr'/>
				</span>				
			</div>
			<div>
				<label class="right">生效日期：</label>
				<span class="left">
					<ww:property value='#request.codeTable.activeDateFmt'/>
				</span>
			</div>
			<div>	
				<label class="right">失效日期：</label>
				<span class="left">
					<ww:property value='#request.codeTable.closeDateFmt'/>
				</span>				
			</div>			
		</div>			
	 </FIELDSET>			
	<br />
	<br />
	<center>
		<input class="button" type="button" name="delBt" value="确 定" onclick="submitForm()">
		<input class="button" type="button" name="backBt" value="返 回" onclick="window.history.back()">
	</center>
	<br />			
	<br />
	<form name="codeDelForm" method="post" action="codeDel.action">
		<input type="hidden" name="codeTable.id.codeType" value="<ww:property value='#request.codeTable.id.codeType'/>">
		<input type="hidden" name="codeTable.id.codeVal" value="<ww:property value='#request.codeTable.id.codeVal'/>">
		<input type="hidden" name="codeTable.descr" value="<ww:property value='#request.codeTable.descr'/>">
		<input type="hidden" name="codeTable.activeDate" value="<ww:property value='#request.codeTable.activeDate'/>">
		<input type="hidden" name="codeTable.closeDate" value="<ww:property value='#request.codeTable.closeDate'/>">
	</form>	
	</div>	
</body>
</html>