var nextfield = ""; 
function keyHandle(){
	if(event){
		if(event.keyCode == 13){
			if(nextfield != "" && document.all(nextfield)){
				document.all(nextfield).focus();
				return;
			}
		}
	}
	return;
}

function userAddSubmit(formName){
	if(!check(formName)){
		return;
	}
	
	if(!roleSelectCheck()){
		return;
	}
	
	document.forms[formName].submit();
	processTip();
}

function userModifySubmit(formName){
	if(!check(formName)){
		return;
	}
	
	if(!roleSelectCheck()){
		return;
	}
	
	document.forms[formName].submit();
	processTip();
}

function userQuery(formName){
	if(!check(formName)){
		return;
	}
	
	document.forms[formName].submit();
	processTip();
}

function userAuthSubmit(formName){
	if(!check(formName)){
		return;
	}
	
	document.forms[formName].submit();
	processTip();
}

function passwordModifySubmit(formName){
	if(!check(formName)){
		return;
	}
	
	if(document.all("userInfo.password").value != document.all("newPassword").value){
		alert("新密码两次输入不匹配!");
		return;
	}
	
	document.forms[formName].submit();
	processTip();
}

function userSelect(userInfoId, status, authStatus, onlineStatus, modifyUser, currentUser){
	/*冻结和注销不能修改用户*/
	if(status != "C" && status != "D"){
		document.all("modifyBt").disabled = false;
	}else{
		document.all("modifyBt").disabled = true;
	}
	
	/*状态为正常可重置密码、冻结用户、注销*/
	if(status == "B"){
		document.all("resetPwdBt").disabled = false;
		document.all("freezeBt").disabled = false;
		document.all("cancelBt").disabled = false;
	}else{
		document.all("resetPwdBt").disabled = true;
		document.all("freezeBt").disabled = true;
		document.all("cancelBt").disabled = true;
	}
	
	/*状态为冻结的解冻按钮可用*/
	if(status == "C"){
		document.all("thawBt").disabled = false;
	}else{
		document.all("thawBt").disabled = true;
	}
	
	/*授权状态为待授权的授权按钮可用*/
	if(authStatus == "A" && modifyUser != currentUser){
		document.all("authBt").disabled = false;
	}else{
		document.all("authBt").disabled = true;
	}
	
	/*用户在线状态为已登陆，强制退出按钮可用*/
	if(onlineStatus == "true"){
		document.all("logoutUserBt").disabled = false;
	}else{
		document.all("logoutUserBt").disabled = true;
	}
	
	document.userModifyForm.elements["userInfo.userInfoId"].value = userInfoId;
}

function userModifyInit(formName){
	document.forms[formName].action = "userModifyInit.action";
	document.forms[formName].submit();
	processTip();
}

function userPwdReset(formName){
	if(!confirm('确定重置该用户密码？')){
		return;
	}
	document.forms[formName].elements["userInfo.authOperType"].value = "D";
	document.forms[formName].action = "userManageDone.action";
	document.forms[formName].submit();
	processTip();
}

function userFreeze(formName){
	if(!confirm('确定冻结该用户？')){
		return;
	}
	document.forms[formName].elements["userInfo.authOperType"].value = "B";
	document.forms[formName].action = "userManageDone.action";
	document.forms[formName].submit();
	processTip();
}

function userThaw(formName){
	if(!confirm('确定解冻该用户？')){
		return;
	}
	document.forms[formName].elements["userInfo.authOperType"].value = "E";
	document.forms[formName].action = "userManageDone.action";
	document.forms[formName].submit();
	processTip();
}

function userCancel(formName){
	if(!confirm('确定注销该用户？')){
		return;
	}
	document.forms[formName].elements["userInfo.authOperType"].value = "C";
	document.forms[formName].action = "userManageDone.action";
	document.forms[formName].submit();
	processTip();
}

function logoutUser(formName){
	if(!confirm('确定强制退出该用户？')){
		return;
	}
	document.forms[formName].elements["userInfo.authOperType"].value = "G";
	document.forms[formName].action = "userManageDone.action";
	document.forms[formName].submit();
	processTip();
}

function userAuthInit(formName){
	document.forms[formName].action = "userAuthInit.action";
	document.forms[formName].submit();
	processTip();
}

function roleSelectCheck(){
	var arr = document.all("roles");
	var count = 0 ;
	var rolesIds = document.all("rolesIds");
	
	if((typeof arr.length) == "undefined"){
		if(arr.checked){
			count ++;
			rolesIds.value = arr.value;
		}
	}else{
		for(i=0; i<arr.length - 1; i++){
			obj = arr[i];
			if(obj.checked){
				count ++;
				rolesIds.value = rolesIds.value + obj.value + ",";
			}
		}
		
		if(arr[arr.length -1].checked){
			rolesIds.value = rolesIds.value + arr[arr.length -1].value;
			count ++;
		}
	}
	
	if(count == 0){
		alert("请选择要给新用户分配的角色！");
		return false;
	}
	
	return true;
}