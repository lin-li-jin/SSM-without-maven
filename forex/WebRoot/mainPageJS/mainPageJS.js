/**
  * Amendment No : GJSY130016
  * Modify By : SUNYAN
  * Date : 2013-07-11
  * Description : Ʊ��ϵͳ��λ����
*/
/**
 * AmendmentNo: CORE130001
 * AmendmentBy: duzhichen
 * AmendmentDate:2013.7.29
 * AmendmentDescr: �����Զ�����ģ��
 */


/**********************����Ⱥ��󣬲��ܽ���ҵ��ϵͳ*********************/
function cannotIn(){
	alert("�����ǿ���ʱ�䣬����뿼��ϵͳ���в���");
	return;
}
/**********************ѡ��Ⱥ���ʼ��*********************/
function chioseGroup(){
 var url = "choiseGroupInit.action?sysClient=";
		location.href = url;
}
/**********************ע��Ⱥ��*********************/
function cancellGroup(){
  var message = window.confirm("ȷ��ע��Ⱥ�飿" );
    if(message){   
        var url = "cancellGroup.action?sysClient=";
		location.href = url;
		return ;	
    }
    else{
       return;
    }

}
/**********************��¼*********************/
function  login(){
//	alert("ִ�е�¼");
	var password =$("#passwordLogin").val();
	var userId =$("#userIdLogin").val();
	if("" == password || ""==userId){
		alert("�������¼�ź�����!");
		return;
	}
			
			$.post("login.action",{userId:userId,password:password},
				function(dat){
				var tem=dat.split("@");
				var flag=tem[0];
				var version=tem[1];
					 if(flag == "success"){
					 	if(version=="pwd"){
					 		location.href = "userPwdModifyInit.action";
					 	}
					 	else if(version=="pwdStu"){
					 		location.href = "userPwdModifyInit2.action";
					 	}
				 		else if(version =="login"){
				 			location.href = "index.jsp";	 
				 		}
					 }else{
						alert(dat);
					}
				}
				);

}
/**********************��¼���*********************/
function checkLogin(){
//��¼���
	var userId =$("#userId").val(); 
	if(userId==null || userId == "" ){
		alert("���ȵ�¼��");
		return ;
	}else{
		return "true";
	}
}
/**********************����Ƿ�ѡ����Ⱥ��*********************/
function checkGroup(sysClient)
{
//	alert("����Ⱥ��");
//����Ƿ���ѡ��Ⱥ��,û�о���ת��ѡ��Ⱥ�����
	var loginflag = checkLogin();
	var groupId=$("#groupId").val(); 
	if(loginflag=="true"){
		if(groupId==null || groupId == "" ){
			//alert("����ѡ��Ⱥ��ţ�");
		var url = "choiseGroupInit.action?sysClient="+sysClient;
	//	var url="jump2ems.action";
		location.href = url;
		return ;	
	}else{
		return "true";
	}
}else{
	return "false";	
}
}//end of checkGroup()
//��ʾȺ�����ϸ��Ϣ
function getGroupDtl(){
	var groupId = $('#groupId2').val();
	$.post("getGroupDtl.action",{groupId:groupId},
				function(dat){
				//alert(dat);
				var s =dat.split("$$");
				$("#scorFor").attr("value",s[1]);
					if(s[0]==null || s[0]==""){
						alert("ϵͳ���ִ��󣬲�ѯ����Ⱥ����Ϣ��");
					}else{
						$("#groupDtlDiv").html(s[0]);
					}
				});	
}


/**********************ѡ����Ⱥ��� �Զ������ͻ���*********************/
//û��ѡ�񿼺����ǰ�� js��
//function toclient(){
//alert($("#sysClient").val());
//��session�е�sysClient ��գ���ֹÿ����ת����ҳ��ʱ����������Ĵ���
//$.post("sysClientDel.action",{},function(dat){});	
//var sysClient=$("#sysClient").val();
//var scorFor="";
//scorFor = $("#scorFor").val();
//document.getElementById("sysClient").value = "";
//var desc ="";
//if(sysClient!= ""){
//		if("ecbs"==sysClient ){
//			 desc ="����ҵ��";
//		}else if("eias"==sysClient){
//			 desc ="Ʊ��ҵ��";
//		}
//		else if("efes"==sysClient){
//			 desc ="����ҵ��";
//		}else if("ees"==sysClient){
//			 desc ="����ϵͳ";
//		}
//var message = window.confirm("�Ƿ�������ת��"+desc+"?");
// if(message){
//		if("ecbs"==sysClient || "eias"==sysClient){
//			callLocalExec(sysClient);
//		}else if("efes"==sysClient){
//			jump2Efes();
//		}else if("ees"==sysClient){
//			jump2Ees();
//		}
//	}
//	}else {
//		return;	
//	}
//  }
  //ѡ�񿼺����
function toclient(){
//alert($("#sysClient").val());
//��session�е�sysClient ��գ���ֹÿ����ת����ҳ��ʱ����������Ĵ���
$.post("sysClientDel.action",{},function(dat){});	
var sysClient=$("#sysClient").val();
var scorFor="";
scorFor = $("#scorFor").val();
document.getElementById("sysClient").value = "";
var desc ="";
if(sysClient!= "" && scorFor!="1"){
		if("ecbs"==sysClient ){
			 desc ="����ҵ��";
		}else if("eias"==sysClient){
			 desc ="Ʊ��ҵ��";
		}
		else if("efes"==sysClient){
			 desc ="����ҵ��";
		}else if("ees"==sysClient){
			 desc ="����ϵͳ";
		}
 var message = window.confirm("�Ƿ�������ת��"+desc+"?");
  if(message){
		if("ecbs"==sysClient || "eias"==sysClient){
			callLocalExec(sysClient);
		}else if("efes"==sysClient){
			jump2Efes();
		}else if("ees"==sysClient){
			jump2Ees();
		}
	}
	}else if(scorFor=="1"){
		var message = window.confirm("�Ƿ�������ת������ϵͳ?");
		 if(message){
			//url="choiseEes3.jsp";//����ʽ�İ汾
			//window.parent.top.location=url;
			jump2EesByKey();//2011-11-29
			
		}
		
	}
  }

/**************���ӵ����Ļ���************************************/
function jumpZxjf(){
	var scorFor=$("#scorFor").val();
	var groupId=$("#groupId").val();
	if(groupId==""||null==groupId){
		alert("��ѡ����ӦȺ�飡");return;
	}
	var dbId=$("#dbId").val();
	if(dbId == "" ||dbId ==null ||scorFor!="R"){
		alert("��Ⱥ�鲻�ܽ������Ļ���ϵͳ��");
		return;
	}
	var zxjfUrl=$("#zxjfUrl").val();
	var zxjfUser=$("#zxjfUser").val();
	var url=zxjfUrl+"j_username="+zxjfUser+"&j_password=888888"+"&dbName="+dbId;
	window.open(url);
	
}

/**********************�����ͻ���*********************/
function callLocalExec(flag)
{	
	var groupId=$("#groupId").val();
	if(groupId==""||null==groupId){
		alert("��ѡ����ӦȺ�飡");return;
	}
	//var groupflag = checkGroup(flag);
	
	//if(groupflag == "true"){	
		var eiasPath = "\"C:\\EIAS\\bin\\EIAS.exe\"";//Ʊ�ݵ�ַ
		var fntrunPath = "\"C:\\fntrun\\autologin.bat\"";//�ۺϻ�Ƶ�ַ
		//var fntrunPath = "\"C:\\fntrun\\run.bat\"";//
		var path2Run ;
		//Ʊ����Ҫѡ��Ⱥ�飬Ⱥ�����������ɺ����龰����Ʊ�����������
		//GJSY130016 BEGIN
		//if(flag=='eia"C:/Documents and Settings/All Users/����ʼ���˵�/����/Lotus Ӧ�ó���/Lotus Notes.lnk"s'){
		if(flag=='eias'){
		//GJSY130016 END
		var areaNo=$("#areaId").val();
			if(areaNo == "" ||areaNo ==null ){
				alert("��Ⱥ�鲻�ܽ���Ʊ��ϵͳ��");
				return;
			}else{
				changeEIASConfigFile();
				path2Run=eiasPath;
			}
		}
		if(flag=='ecbs'){
		var dbId=$("#dbId").val();
		if(dbId == "" ||dbId ==null ){
				alert("��Ⱥ�鲻�ܽ����ۺϻ�ƺ���ϵͳ��");
				return;
			}else{
				changeConfigFile();		//�޸������ļ�
				changeRunFile();//�Զ���¼ʹ��
			path2Run=fntrunPath;	
			}
			
		}
		var myShell = new ActiveXObject("WScript.Shell");
		myShell.Run(path2Run);
		myShell = null;
	//}
}// end of callLocalExec(flag)

/**********************�޸Ĺ����ļ�*********************/
function changeConfigFile()
{
   var dbname = $("#dbId").val(); 
   var groupId=$("#groupId").val(); 
   var tuxedoIP= $("#tuxedoIP").val();
  //var tuxedoIP= "192.168.0.52";
   var runModel = $("#runModel").val(); 
   var runModelDescr;
   //��ʱ���Σ�����û��ʹ�ÿ���ģʽ
 	//if(runModel=="1"){
 	//	runModelDescr="EXAM";	
 	//}else{
 	//	runModelDescr="EXEC";
 	//}
    var fso = new ActiveXObject("Scripting.FileSystemObject");
    var fRead = fso.OpenTextFile("c:\\fntrun\\bin\\sysdef.lua",1);
    var str2Write = "";
    while (!fRead.AtEndOfStream){
    	var eachLine =fRead.ReadLine();
     	//�����޸�tuxedoIp 2011-09-06 �������Ҫ��2011-0-15
     	if( eachLine.indexOf("--WSNADDR =")<0 && eachLine.indexOf("-- WSNADDR =")<0 
     	&& eachLine.indexOf("WSNADDR =")>0 && eachLine.lastIndexOf(":9009\",")>0){
			str2Write += "	WSNADDR = \""+tuxedoIP+":9009\","+"\n";			
     	}else 
     	
     	if(eachLine.indexOf("g_var.GROUPID")>0){
     		str2Write += "	g_var.GROUPID = \""+groupId+"\";"+"\n";
     	}else if(eachLine.indexOf("g_var.DBSID")>0){
     		str2Write += "	g_var.DBSID = \""+dbname+"\";"+"\n";
     	}
     	//else if(eachLine.indexOf("g_var.DBSID")>0){
     		//str2Write += "	g_var.RUNMODE = \""+runModelDescr+"\";"+"\n";
     	//}
     	else{
            str2Write += eachLine+"\n";    	
            }	
     		//continue;
     }
    fRead.Close();
    var fWrite= fso.CreateTextFile("c:\\fntrun\\bin\\sysdef.lua", true);
    fWrite.WriteLine(str2Write);
	//�ر��ļ�
	fWrite.Close();
}//end of changeConfigFile()

//�޸ĵ�¼ʹ�õ�exe�ļ� ��autologin.bat
function changeRunFile()
{
  	var dbname = $("#dbId").val(); 
 	var ecbsOper = $("#ecbsOper").val(); 
 	var ecbsOperCash = $("#operCash").val(); 
 	var fso = new ActiveXObject("Scripting.FileSystemObject");
  	var fRead = fso.OpenTextFile("c:\\fntrun\\autologin.bat",1);
 	var str2Write="";
	while (!fRead.AtEndOfStream){
	var eachLine =fRead.ReadLine();
	
 	if(eachLine.indexOf("front")>=0){
 		if(null==ecbsOperCash||""==ecbsOperCash||"null"==ecbsOperCash){
 			str2Write= "C: \r\n" + 
 		// CORE130001 start
 			"cd c:\\fntrun \r\n" +
 			"call update.bat \r\n" +
 		//  CORE130001 end
 			"cd C:\\fntrun\\bin \r\n"+
 			'start "CS" front.exe -l1 -U'+ecbsOper+" -P888888 -S1";
 		}
 		else{
 				str2Write= "C: \r\n" + 
 				// CORE130001 start
 	 			"cd c:\\fntrun \r\n" +
 	 			"call update.bat \r\n" +
 	 			// CORE130001 end
 	 			"cd C:\\fntrun\\bin \r\n" +
 	 			'start "CS" front.exe -l1 -U'+ecbsOper+" -B"+ecbsOperCash+" -P888888 -S1";
  		}
  	}
     	
  	}

 	fRead.Close();
 	var fWrite= fso.CreateTextFile("c:\\fntrun\\autologin.bat", true);
 	fWrite.WriteLine(str2Write);
	fWrite.Close(); 
	changeUpdateUrl();// CORE130001
}
/**
 * �޸�update.bat�е�url��
 * Amendment No: CORE130001
 * Amendment By: duzhichen
 * Amendment descr: �Զ�����ģ��
 * Amendment date�� 2013.7.27
 */
function changeUpdateUrl(){
	var localObj = window.location;
	var contextPath = localObj.pathname.split("/")[1];
	var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;
	var fso = new ActiveXObject("Scripting.FileSystemObject");
    var fRead = fso.OpenTextFile("c:\\fntrun\\update.bat",1);
    var str2Write = "";
    while (!fRead.AtEndOfStream){
    	var eachLine =fRead.ReadLine();
     	if( eachLine.indexOf("\"url=")>0) {
     		str2Write +="set \"url="+basePath+"/resources/update/\""+"\r\n";
     		//alert("++++"+str2Write);
     	}
     	else{
            str2Write += eachLine+"\r\n";    	
            }	
     		//continue;
     }
    fRead.Close();
    var fWrite= fso.CreateTextFile("c:\\fntrun\\update.bat", true);
    fWrite.WriteLine(str2Write);
	//�ر��ļ�
	fWrite.Close();
}
// CORE130001 end
/**********************�޸�Ʊ���ļ�*********************/
function changeEIASConfigFile(){
//��дƱ�ݵ����� alert("������!");
//	var areaNo ='<ww:property value="#session.userModel.areaId"/>';
	 var eiasIP=$("#eiasIP").val();
	//GJSY130016 BEGIN
	 var areaNo=$("#areaId").val();
	 var eiasOper=$("#eiasOper").val();
	//GJSY130016 END
    if(null==eiasIP||eiasIP==""){
    	alert("�޷���ȡ���ݿ����ӣ�����ϵ��ؼ�����Ա��");
    	return;
    }
    var fso = new ActiveXObject("Scripting.FileSystemObject");
    var fRead = fso.OpenTextFile("c:\\EIAS\\Bin\\Eias.ini",1);
    var str2Write = "";
     while (!fRead.AtEndOfStream){ 
    	var eachLine =fRead.ReadLine();
    	//GJSY130016 BEGIN
     	if(eachLine.indexOf("HostName")>=0){
     		str2Write += "HostName="+eiasIP+"\n";
     	}
     	//if(eachLine.charAt(0)=='A'){
			//str2Write += "AreaNo="+"\n";
     	//}
     	//GJSY130016 END
     	else if(eachLine.indexOf("AreaNo")>=0){
			str2Write += "AreaNo="+areaNo+"\n";
     	}
     	//GJSY130016 BEGIN
     	else if(eachLine.indexOf("UserID")>=0&&eachLine.indexOf("DBUserID")<0){
     		str2Write += "UserID="+eiasOper+"\n";
     	}
     	else if(eachLine.indexOf("UserPwd")>=0){
     		str2Write += "UserPwd=888888"+"\n";
     	}
     	else{
            str2Write += eachLine+"\n";    		
     		//continue;
     	}
     }
    fRead.Close();
    var fWrite= fso.CreateTextFile("c:\\EIAS\\Bin\\Eias.ini", true);
    fWrite.WriteLine(str2Write);
	//�ر��ļ�
	fWrite.Close();	
}
/**********************����*********************/
function jump2Efes(){
	
	var flag1 = checkGroup("efes");
	//alert("flag1:"+flag1);
	if(flag1 == "true"){
	var userId = $("#userId").val(); 
    var groupId=$("#groupId").val(); 
    var password=$("#password").val();
    var efesUrl=$("#efesUrl").val();
	
//	var url="http://56.16.72.94:8080/efes/login.action?userModel.id="+userId+"&userModel.groupId="+groupId+"&userModel.password="+password;
	var url=efesUrl+"login.action?userModel.id="+userId+"&userModel.groupId="+groupId+"&userModel.password="+password;
	window.open(url);
	//location.href = url;
	return;
	}
}//end of jump2Efes()

/**********************����*********************/

//2011-11-29 ��һ���
function jump2EesByKey(){

	var url =null;
	var userType = $("#userType").val(); 
	var userId = $("#userId").val(); 
    var groupId=$("#groupId").val(); 
    var password=$("#password").val();
    var dbId=$("#dbId").val(); 
    var areaId=$("#areaId").val();
    var ecbsOper=$("#ecbsOper").val();
    var eiasOper=$("#eiasOper").val();
	var eesUrl=$("#eesUrl").val();
	var operCash=$("#operCash").val();
	var srvKey=$("#srvKey").val();
	var tuxedoIP=$("#tuxedoIP").val();
	var scorFor=$("#scorFor").val();
	var loginFlag = checkLogin();
	if(loginFlag =="true"){
		if(userType=="S"){
		
		//�޸Ĺ���ͻ��˵�IP��ַ
		changeConfigIP(tuxedoIP);	
		//�����ѧ�����뿼��ϵͳ����Ҫ����Ƿ���ѡ��Ⱥ��
			//groupFlag = checkGroup("ees");
			if(null!=groupId&&groupId!=""&&scorFor=="E"){
			//if(groupFlag == "true"){
			eesLogout();
				url=eesUrl+"login.action?userModel.userId="+userId+"&userModel.groupId="+groupId+"&userModel.password="+password
					+"&userModel.dbId="+dbId+"&userModel.areaId="+areaId+"&userModel.operator="+ecbsOper+"&userModel.eiasOper="+eiasOper
					//+"&userModel.entryPort="+p[1]
					+"&userModel.boxNo="+operCash+"&userModel.srvKey="+srvKey;
				window.open(url);
				return;
			}else if(null==groupId||groupId==""){
				alert("��ѡ�����Ⱥ�飡");
				return;
			}
			else if(scorFor!="E"){
				alert(scorFor);
				alert("��Ⱥ�鲻�ܽ��뿼��ϵͳ��");
				return;
			}
		}else if (userType=="T"||userType=="M") {
			url=eesUrl+"login.action?userModel.userId="+userId+"&userModel.password="+password;	
			window.open(url);
			return;
		}				
	}
	return;
}//end of jump2EesByKey()

function teaEesByKey(actionName){
	var url =null;
	var userType = $("#userType").val(); 
	var userId = $("#userId").val(); 
    var password=$("#password").val();
   	var eesUrl=$("#eesUrl").val();
	var loginFlag = checkLogin();
	if(loginFlag =="true"){
		if (userType=="T"||userType=="M") {
			url=eesUrl+"login.action?userModel.userId="+userId+"&userModel.password="+password+"&addr="+actionName;	
			window.open(url);
			return;
		}				
	}
	return;
}
function jump2Ees(){
jump2EesByKey();
//var url =null;
//	var userType = $("#userType").val(); 
//	var userId = $("#userId").val(); 
//    var groupId=$("#groupId").val(); 
//    var password=$("#password").val();
//    var dbId=$("#dbId").val(); 
//    var areaId=$("#areaId").val();
//    var ecbsOper=$("#ecbsOper").val();
//    var eiasOper=$("#eiasOper").val();
//	var eesUrl=$("#eesUrl").val();
//	var operCash=$("#operCash").val();
//	var srvKey=$("#srvKey").val();
//	var loginFlag = checkLogin();
//	if(loginFlag =="true"){
//		if(userType=="S"){
//
//		//�����ѧ�����뿼��ϵͳ����Ҫ����Ƿ���ѡ��Ⱥ��
//			groupFlag = checkGroup("ees");
//			if(groupFlag == "true"){
//			//return false; //���˷ֿ���ڡ�20110615��
//			alert("��ӿ�����ڽ��룡");//���˷ֿ���ڡ�20110916��
//			return ; //���˷ֿ���ڡ�20110916��
//			eesLogout();
//				url=eesUrl+"login.action?userModel.userId="+userId+"&userModel.groupId="+groupId+"&userModel.password="+password
//					+"&userModel.dbId="+dbId+"&userModel.areaId="+areaId+"&userModel.operator="+ecbsOper+"&userModel.eiasOper="+eiasOper+"&userModel.boxNo="+operCash
//					+"&userModel.srvKey="+srvKey;
//				window.open(url);
//				return;
//			}else{
//				return;
//			}
//		}else if (userType=="T") {
//			url=eesUrl+"login.action?userModel.userId="+userId+"&userModel.password="+password;	
//			window.open(url);
//			return;
//		}				
//	}
//	return;
}//end of jump2Ees()

function eesLogout(){
$.post("/ees/logout.action",function(dat){	
});
}
/**********************ʵ�����*********************/
function jump2Ems(){
	var loginFlag = checkLogin();
		if(loginFlag =="true"){
		//var url="emsIndex.jsp";
		//window.open(url);
		var url = "jump2ems.action";
		location.href = url;
		}
	return;
}//end of jump2Ems()
/**********************֪ʶ��*********************/
function jump2Ekss(){
	var ekssUrl=$("#ekssUrl").val();
	window.open(ekssUrl);
	return;
}//end of jump2Ekss()

/******************��ȡ֪ʶ������*************************/
function jump2Ekms(filename){
	var url=$("#ekssUrl").val();
	if(filename==""){
			window.open(url);
		}
		else{
			window.open(url+filename+"/index.htm");
		}

	//$.post("getEkmsUrl.action",function(dat){	
	//	var url = dat;
	//	if(filename==""){
	//		window.open(url);
	//	}
	//	else{
	//		window.open(url+filename+"/index.htm");
	//	}
	//});
	return;
}


/**********************����ϵͳ--�ɼ���ѯ*********************/

function  stuTestDetailQuery(){
	var eesUrl=$("#eesUrl").val();
	var url=eesUrl+"studentScoreQueryInit.action";
	window.open(url);
}//end of stuTestDetailQuery

/**********************����ϵͳ--���˻ع�*********************/

function  stuTestReviewQuery(){
	var eesUrl=$("#eesUrl").val();
	var url=eesUrl+"stuTestReviewQuery.action";
	window.open(url);
}//end of stuTestReviewQuery()
/**********************�Ŵ�ϵͳ*********************/
function jump2bcms(){
var bcmsUrl=$("#bcmsUrl").val();
	var loginFlag = checkLogin();
		if(loginFlag =="true"){
		window.open(bcmsUrl);
		}
	return;
}//end of jump2bcms()
/**********************���ʽ���ϵͳ*********************/
function jump2btits(){
var url=$("#btitsUrl").val();
	var loginFlag = checkLogin();
		if(loginFlag =="true"){
		window.open(url);
		}
	return;
}//end of jump2bcms()


/**********************Ʊ��ϵͳ*********************/
function jumpToEias(){
	changeEIASConfigFile();
	//if(!changeEIASConfigFile()){
	//	return;
	//}
	var eiasPath = "\"C:\\EIAS\\bin\\EIAS.exe\"";//Ʊ�ݵ�ַ
	var fntrunPath = "\"C:\\EIAS\\run.bat\"";//�ۺϻ�Ƶ�ַ
	var myShell = new ActiveXObject("WScript.Shell");
	myShell.Run(eiasPath);
	myShell = null;
}

//2011-11-29
//�޸Ĺ���ͻ���ip��ַ
function changeConfigIP(ip){
 var dbname = $("#dbId").val(); 
   var groupId=$("#groupId").val(); 
   var runModel = $("#runModel").val(); 
   var runModelDescr ;
 	   //��ʱ���Σ�����û��ʹ�ÿ���ģʽ
 	//if(runModel=="1"){
 	//	runModelDescr="EXAM";	
 	//}else{
 	//	runModelDescr="EXEC";
 	//}	
    var fso = new ActiveXObject("Scripting.FileSystemObject");
    var fRead = fso.OpenTextFile("c:\\fntrun\\bin\\sysdef.lua",1);
    var str2Write = "";
    while (!fRead.AtEndOfStream){
    	var eachLine =fRead.ReadLine();
    	
     	if( eachLine.indexOf("--WSNADDR =")<0 && eachLine.indexOf("-- WSNADDR =")<0 
     	&& eachLine.indexOf("WSNADDR =")>0 && eachLine.lastIndexOf(":9009\",")>0){
			str2Write += "	WSNADDR = \""+ip+":9009\","+"\n";			
     	}else if(eachLine.indexOf("g_var.GROUPID")>0){
			str2Write += "	g_var.GROUPID = \""+groupId+"\";"+"\n";
     	}else if(eachLine.indexOf("g_var.DBSID")>0){
     		str2Write += "	g_var.DBSID = \""+dbname+"\";"+"\n";
     	}
     	//else if(eachLine.indexOf("g_var.DBSID")>0){
     	//	str2Write += "	g_var.RUNMODE = \""+runModelDescr+"\";"+"\n";
     	//}
     	else{
            str2Write += eachLine+"\n";    	
            }	
     }
    fRead.Close();
    var fWrite= fso.CreateTextFile("c:\\fntrun\\bin\\sysdef.lua", true);
    fWrite.WriteLine(str2Write);
	//�ر��ļ�
	fWrite.Close();

}
