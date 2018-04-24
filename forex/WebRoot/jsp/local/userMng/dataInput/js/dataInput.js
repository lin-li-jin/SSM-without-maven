function importSubmitCheck(formName){
	var fileName = $("#fileArray").val();
	if(null==fileName||fileName==""){
		alert('请点击“浏览”确定文件路径！');
		return;
	}
	var a=fileName.substr(fileName.length-4,4);
	//if(fileName.indexOf(".xls")<0){
	if(a!=".xls"){
		alert('导入的文件格式不正确！');
		return;
	}
	//if(!check(formName)) {
	//	alert("");
	//	return;
	//}
	document.forms[formName].submit();	
	processTip();
}