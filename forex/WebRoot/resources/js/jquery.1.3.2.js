	
	function checkLoginParam(){
		var userModel_dbId_ = $("#userModel_dbId_id").val(); 
		var userModel_operator_ = $("#userModel_operator_id").val(); 
		var userModel_groupId_ = $("#userModel_groupId_id").val();
		var userModel_boxNo_ = $("#userModel_boxNo_id").val(); 
		if(userModel_dbId_=""||
			 userModel_operator_ ==""||
			 userModel_groupId_==""){
			 //userModel_boxNo_ ==""
			//$("#errorMsg_id").html("登录用户有错，缺少本学生考核情景包相关信息，请确定有选择考核情景包，如果有请联系管理员！");
			//alert("登录用户有错，缺少本学生考核情景包相关信息，请确定有选择考核情景包，如果有请联系管理员！");	 
		}
	}
	function checkSrvParam(){
		var userModel_srvKey_ = $("#userModel_srvKey_id").val(); 
		//if(userModel_srvKey_=""){
		//$("#errorMsg_id").html("登录用户有错，缺少本学生考核服务器信息，如果有请联系管理员！");
			//alert("登录用户有错，缺少本学生考核服务器信息，如果有请联系管理员！");	
		//}
	}
	//检查 考核入口（没应用）
//	function checkFntrunWSNADDR(){
//		var port_1_ip=$("#entryPort_1_id").val();
//		var port_2_ip=$("#entryPort_2_id").val();
//		var userModel_entryPort_=$("#userModel_entryPort_id").val();
//		if(port_1_ip==""||port_2_ip==""){
//			alert("配置入口参数有误！请联系管理员");
//			return ;
//		}
//		
//		if(userModel_entryPort_==""){
//			alert("考核入口有误，请退出并关闭所有窗口重新登录！");
//			return ;
//		}
//		
//		var local_wsnaddr="";
//		var checkResult=false;
//		if(userModel_entryPort_=="1"){
//			local_wsnaddr=port_1_ip+":9009\",";
//		}else if (userModel_entryPort_=="2"){
//			local_wsnaddr=port_2_ip+":9009\",";		
//		}
//		
//		var fso = new ActiveXObject("Scripting.FileSystemObject");
//    	var fRead = fso.OpenTextFile("c:\\fntrun\\bin\\sysdef.lua",1);
// 
//   	 	while (!fRead.AtEndOfStream){
//	    	var eachLine =fRead.ReadLine();
//	     	
//	     	if( eachLine.indexOf("--WSNADDR =")<0 && eachLine.indexOf("-- WSNADDR =")<0 
//	     		&& eachLine.indexOf("WSNADDR =")>0 && eachLine.lastIndexOf(local_wsnaddr)>0){
//	     			checkResult=true;
//	     			break;
//	     	}
//		}
//		fRead.Close();
//		
//		if(!checkResult){
//			$("#errorMsg_id").html("考核系统登录入口有误！请联系管理员！");
//			alert("考核系统登录入口有误！请联系管理员！");
//		}
//	}
	
	function init(){
		var nowSerialId_= $("#nowSerialId").val();
		if(nowSerialId_&&nowSerialId_!="0"){
			$("#tpexe_"+nowSerialId_).html("已经开始");
		}
	}
	
	$(document).ready(function(){
		init();
		checkLoginParam()
		checkSrvParam();
		//checkFntrunWSNADDR();
		
	});
	function comeInExercises(tpId,sId,sysNum){
		 		var sessionId=$("#sessionId").val();
   		var myUrl = "/eems/exerInit.action?model.testPaperId="+tpId+"&model.serialId="+sId;
		var sss=window.open(myUrl,"aaa","scrollbars=yes,status=yes, toolbar=no, menubar=no,location=no,resizable=yes,top=0,left=0,width=750,height=260,channelmode=no");
		sss.focus();   	
   	}
   	
   	function callLocalExec(flag)
	{	
		var eiasPath = "\"C:\\EIAS\\bin\\EIAS.exe\"";//票据地址
		//var fntrunPath = "\"C:\\fntrun\\run.bat\"";//综合会计地址\
		var fntrunPath = "\"C:\\fntrun\\autologin.bat\"";//综合会计地址
		var path2Run ;

		if(flag=='eias')
			path2Run=eiasPath;
		if(flag=='fnturn'){
			//path2Run=fntrunPath;
			changeConfigFile();		//修改配置文件
			changeRunFile();//自动登录使用
			path2Run=fntrunPath;
			
		}	
		var myShell = new ActiveXObject("WScript.Shell");
		myShell.Run(path2Run);
		myShell = null;
		
	}
/**********************修改柜面文件*********************/
function changeConfigFile()
{
 var dbname = $("#userModel_dbId_id").val(); 
   var groupId=$("#userModel_groupId_id").val(); 
   var tuxedoIP= $("#tuxedoIP").val();
  //var tuxedoIP= "192.168.0.52";
    //暂时屏蔽，柜面没有使用考核模式
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
     	//屏蔽修改tuxedoIp 2011-09-06 多入口需要开2011-0-15
     	if( eachLine.indexOf("--WSNADDR =")<0 && eachLine.indexOf("-- WSNADDR =")<0 
     	&& eachLine.indexOf("WSNADDR =")>0 && eachLine.lastIndexOf(":9009\",")>0){
			str2Write += "	WSNADDR = \""+tuxedoIP+":9009\","+"\n";			
     	}else 
     	
     	if(eachLine.indexOf("g_var.GROUPID")>0){
     		str2Write += "	g_var.GROUPID = \""+groupId+"\";"+"\n";
     	}else if(eachLine.indexOf("g_var.DBSID")>0){
     		str2Write += "	g_var.DBSID = \""+dbname+"\";"+"\n";
     	}
     	else if(eachLine.indexOf("g_var.DBSID")>0){
     		str2Write += "	g_var.RUNMODE = \""+runModelDescr+"\";"+"\n";
     	}
     	else{
            str2Write += eachLine+"\n";    	
            }	
     		//continue;
     }
    fRead.Close();
    var fWrite= fso.CreateTextFile("c:\\fntrun\\bin\\sysdef.lua", true);
    fWrite.WriteLine(str2Write);
	//关闭文件
	fWrite.Close();
}//end of changeConfigFile()

//修改登录使用的exe文件 ：autologin.bat
function changeRunFile()
{
   	var dbname = $("#userModel_dbId_id").val(); 
 	var ecbsOper = $("#userModel_operator_id").val(); 
 	var ecbsOperCash = $("#userModel_boxNo_id").val(); 
 	var fso = new ActiveXObject("Scripting.FileSystemObject");
  	var fRead = fso.OpenTextFile("c:\\fntrun\\autologin.bat",1);
 	var str2Write="";
	while (!fRead.AtEndOfStream){
	var eachLine =fRead.ReadLine();
	
 	if(eachLine.indexOf("front")>=0){
 		if(null==ecbsOperCash||""==ecbsOperCash||"null"==ecbsOperCash){
 			str2Write= "cd C:\\fntrun\\bin \r\n"+'start "CS" front.exe -l1 -U'+ecbsOper+" -P888888 -S1";
 		}
 		else{
 			str2Write= "cd C:\\fntrun\\bin \r\n" +'start "CS" front.exe -l1 -U'+ecbsOper+" -B"+ecbsOperCash+" -P888888 -S1";
  		}
  	}
     	
  	}
 	fRead.Close();
 	var fWrite= fso.CreateTextFile("c:\\fntrun\\autologin.bat", true);
 	fWrite.WriteLine(str2Write);
	fWrite.Close();
}
	