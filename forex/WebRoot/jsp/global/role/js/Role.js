function roleSubmit(formName){
	if(!roleCheck()){
		return;
	}
	
	document.all("roles.name").value = document.all("roleName").value;
	document.all("roles.rolesLevel").value = document.all("rolesLevel").value;
	document.all("roles.descr").value = document.all("roleDescr").value;
	document.forms[formName].submit();
	processTip();
}

function roleDel(){
	if(!confirm('确定删除该角色？')){
		return;
	}
	
	document.roleDelForm.submit();
	processTip();
}

function roleCheck(){
	if(document.all("roleName").value == null || document.all("roleName").value == ""){
		alert("角色名称不能为空！");
	   	return false;
	}
	
	if(document.all("roleName").value.length > document.all("roleName").len){
		alert("角色名称超长，最多允许" + document.all("roleName").len + "个字符！");
		return false;
	}
	
	if(document.all("rolesLevel").value == null || document.all("rolesLevel").value == ""){
		alert("角色级别不能为空！");
	   	return false;
	}
	
	if(document.all("roleDescr").value.length > document.all("roleDescr").len){
		alert("角色描述超长，最多允许" + document.all("roleName").len + "个字符！");
		return false;
	}
	
	var arr = document.all("permission");
	var count = 0 ;
	var permIds = document.all("permIds");
	
	if((typeof arr.length) == "undefined"){
		if(arr.checked){
			count ++;
			permIds.value = arr.value;
		}
	}else{
		for(i=0; i<arr.length - 1; i++){
			obj = arr[i];
			if(obj.checked){
				count ++;
				permIds.value = permIds.value + obj.value + ",";
			}
		}
		
		if(arr[arr.length -1].checked){
			permIds.value = permIds.value + arr[arr.length -1].value;
			count ++;
		}
	}
	
	if(count == 0){
		alert("请选择要给新角色分配的权限！");
		return false;
	}
	
	return true;
}