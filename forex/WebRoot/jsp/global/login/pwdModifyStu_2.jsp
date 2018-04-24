<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="ww" uri="webwork"%>
<%@ taglib uri="extremecomponents" prefix="ec"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title></title>
		<script src="../../../resources/js/batchOper.js" type="text/javascript"></script>
		<script src="../../../resources/ponyjs/jquery.js" type="text/javascript"></script>
		<script src="../../../resources/ponyjs/jquery_validate.js"
			type="text/javascript"></script>
		<script src="../../../resources/ponyjs/jquery.ui.draggable.js"
			type="text/javascript"></script>
		<script src="../../../resources/ponyjs/jquery.alerts.js"
			type="text/javascript"></script>
			<script src="../../../resources/js/linkedSelect.js"
			type="text/javascript"></script>
			<script src="../../../resources/js/Common.js"
			type="text/javascript"></script>
			<script src="../../../jsp/local/userMng/userInfoMng/js/userInfoMng.js"
			type="text/javascript"></script>
			<script src="../../../resources/js/userInfoMng.js"
			type="text/javascript"></script>
			<link rel="stylesheet" href="/forex/resources/css/talent.css" type="text/css" media="screen, print" />	
		<link rel="stylesheet" href="/forex/resources/css/talent_query.css" type="text/css" media="screen, print" />
		<link rel="stylesheet" href="../../../resources/css/style.css" type="text/css" />
		<script language="javascript">
  		function checkForm(){
  			var newPasswd=$("#newPasswd").val();
  			var confirmPasswd=$("#confirmPasswd").val();
  			var oldPwd2=$("#oldPwd2").val();
  			var oldPwd1=$("#oldPwd1").val();
  			var newName=$("#newName").val();
  			if(isChinese(newName)==false){
  				alert("用户姓名只能为中文或英文!");
  				return;
  			}
  			if(null==oldPwd2||oldPwd2==""){
  				alert("原密码值不能为空！");
  				return;
  			}
  			if(null==newPasswd||newPasswd==""){
  				alert("密码值不能为空！");
  				return;
  			}
  			if(null==confirmPasswd||confirmPasswd==""){
  				alert("确认密码值不能为空！");
  				return;
  			}
  			if(oldPwd1!=oldPwd2){
  				alert("原密码值输入错误！");
  				return;
  			}
   			if(newPasswd!= confirmPasswd){
		 		 alert("新密码两次输入的值不一致!");
		  		return;
			}
			var form = document.forms.pwdModifyForm;
			form.submit();

  		}
		
	  $(function(){
	  $("#newName").focus();
   
     $("#newName").keydown(function(event){
       
         if(event.keyCode==13){
           
           $("#oldPwd2").focus();
         }
     });
      $("#oldPwd2").keydown(function(event){
       
         if(event.keyCode==13){
           
           $("#newPasswd").focus();
         }
     });
      $("#newPasswd").keydown(function(event){
       
         if(event.keyCode==13){
           
           $("#confirmPasswd").focus();
         }
     });
   	 $("#confirmPasswd").keydown(function(event){
       
         if(event.keyCode==13){
           checkForm();
          
         }
     });
   });
  	</script>
	</head>

	<body>
		<div id="body">
			<input type="hidden" id="oldPwd1" value="<ww:property value="#session.userPwd"/>">
			<div id="pwdModifyDiv">
				<form name="pwdModifyForm" action="passwordModify.action" method="post">
					<FIELDSET align="center">
						<LEGEND>修改密码</LEGEND>
						<div class="content left">
							<ww:if test='true== #session.loginUserModel.pwdModifyFlag'>
							<div>
							<span style="color:red;font-size:12px;">第一次登陆，请修改密码</span>
							</div>
							</ww:if>					
						<div>
								<label class="right">姓名:</label>
								<span class="left"><input type="text" id="newName" name="newName" size="12" value="<ww:property value="#session.loginUserModel.name"/>" tabIndex="1"></span>
							</div>
							<div>
								<label class="right">学号:</label>
								<span class="left"><input type="text" value="<ww:property value="#session.loginUserModel.userId"/>" size="12" readonly></span>
							</div>
							<div>
								<label class="right"><font color="red">*</font>原密码:</label>
								<span class="left"><input type="password" id="oldPwd2" name="oldPwd2" size="12" maxlength="8" tabIndex="2"></span>
							</div>
							<div>
								<label class="right"><font color="red">*</font>新密码:</label>
								<span class="left"><input type="password" id="newPasswd" name="newPasswd" isNeed="y" cName="新密码" size="12" maxlength="8" tabIndex="3"></span>
							</div>
							<div>
								<label class="right"><font color="red">*</font>确认新密码:</label>
								<span class="left"><input type="password" id="confirmPasswd" name="confirmPasswd" isNeed="y" cName="确认新密码" size="12" maxlength="8" tabIndex="4"></span>
							</div>
						</div>
						<div id="queryButtonDiv" style="padding-left:90px;">
							<input class="button" id="confirmPwd" type="button" value="确认修改" onclick="checkForm()" tabIndex="5">
						</div>
					</FIELDSET>
				</form>
			</div>
		</div>
	</body>
</html>
