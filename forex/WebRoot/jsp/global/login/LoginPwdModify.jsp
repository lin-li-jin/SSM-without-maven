<%@ page contentType="text/html;charset=GBK"%>
<%@ taglib prefix="ww" uri="webwork" %>

<html>

<head>
<title>��½�����޸�</title>
<script language="JavaScript" src="resources/js/check.js"></script>
<link rel="stylesheet" href="/forex/resources/css/talent.css" type="text/css" media="screen, print" />	
<link rel="stylesheet" href="/forex/resources/css/talent_query.css" type="text/css" media="screen, print" />		
<link rel="stylesheet" href="/forex/resources/css/talent_tips.css" type="text/css" media="screen, print" />					
<meta http-equiv=Pragma content=no-cache>
<script language="JavaScript">
<!--
var nextfield="";
function keyHandle(){
	if(event){
		if(event.keyCode==13){
			if(nextfield!="" && document.all(nextfield)){
				document.all(nextfield).focus();
				return;
			}
		}
	}
	return;
}

function goPage(sPage){
	this.location = sPage;
}

function submitForm()
{
	if(!check("loginPwdModifyForm")){
		return;
	}
	
	if(document.getElementById("confirmPasswd").value != document.getElementById("newPassword").value){
	  alert("���������������ֵ��һ��!");
	  return;
	}

	document.loginPwdModifyForm.submit();
}
-->
</SCRIPT>

</head>

<body  topmargin="0" leftmargin="0" class="main" onload='javascript:document.all("oldPassword").focus();'>
<div id="body">
<form method="post" action="loginPwdModify.action" name="loginPwdModifyForm">
		<FIELDSET align="center">
			<LEGEND>
				<div style="width:200px;">	 
				<div class="curved">
					<b class="b1"></b><b class="b2"></b><b class="b3"></b><b class="b4"></b>
						<div class="boxcontent">
							<font  style="{size:3}" color="#000000">������</font>
						</div>
					<b class="b4"></b><b class="b3"></b><b class="b2"></b><b class="b1"></b>
				</div>					
			</div>					
			</LEGEND>
			<div id="queryConditDiv" class="content center marginCenter">
				<div>
					<label class="right">ԭ����:</label>
					<span class="left">
						<input type="password" id="oldPassword" name="oldPassword" isNeed="y" 
								cName="ԭ����" size="12" maxlength="8" 
								onFocus="this.select();nextfield='newPassword'">
					</span>
				</div>
				<div><hr/></div>			
				<div>
					<label class="right">������:</label>
					<span class="left">
						<input type="password" id="newPassword" name="newPassword" isNeed="y" 
								cName="������" size="12" maxlength="8" 
								onFocus="this.select();nextfield='confirmPasswd'">
					</span>
				</div>
				<div>
					<label class="right">ȷ��������:</label>
					<span class="left">
						<input type="password" id="confirmPasswd" name="confirmPasswd" isNeed="y" 
								cName="ȷ��������" size="12" maxlength="8" 
								onFocus="this.select();nextfield='submitBt'">
					</span>
				</div>
				<div id="queryButtonDiv" style="padding-left: 50px">
					<input type="hidden" name="userInfo.pwdModifyFlag" value="true">
					<input type="hidden" name="userInfo.userId" value="<ww:property value="#session.loginUserModel.id"/>">
					<input type="button" class="button" value="�� ��" onclick= "submitForm();" name="submitBt">&nbsp;&nbsp;
					<input type="button" class="button" value="�� ��" onclick="window.location = 'logout.action'">
				</div>	
			</div>	

   		</FIELDSET>
</form>	
<div class="tips">
	<b class="t1"></b><b class="t2 d1"></b><b class="t3 d1"></b><b class="t4 d1"></b>
	<div class="t d1">
		<span style="vertical-align:top;float:left;margin-right: 40px;">
			<img src="resources/image/icon_message.gif" />
		</span>
		<span style="{vertical-align:top}">
			<OL>
				<li>��������Ϊ��С��6λ��ĸ�����ֻ�ϴ�������Ҫ���ڼ��䣬����������<br>		
			</OL>
		</span>
	</div>
	<b class="t4t d1"></b><b class="t3t d1"></b><b class="t2t d1"></b><b class="t1t"></b>
</div>
</div>
</body>
</html>
