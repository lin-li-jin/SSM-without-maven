function importSubmitCheck(formName){
	var fileName = $("#fileArray").val();
	if(null==fileName||fileName==""){
		alert('�����������ȷ���ļ�·����');
		return;
	}
	var a=fileName.substr(fileName.length-4,4);
	//if(fileName.indexOf(".xls")<0){
	if(a!=".xls"){
		alert('������ļ���ʽ����ȷ��');
		return;
	}
	//if(!check(formName)) {
	//	alert("");
	//	return;
	//}
	document.forms[formName].submit();	
	processTip();
}