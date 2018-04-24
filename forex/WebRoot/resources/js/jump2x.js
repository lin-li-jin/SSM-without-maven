/**
	Amendment No:EFES13002
	Modify By   :SUNYAN
	Date        :2013-05-31
	Description :efes登录改造
*/
/**********************考核群组后，不能进入业务系统*********************/
function cannotIn(){
	alert("现在是考核时间，请进入考核系统进行操作");
	return;
}
/**********************选择群组初始化*********************/
function chioseGroup(){
 var url = "choiseGroupInit.action?sysClient=";
		location.href = url;
}
/**********************注销群组*********************/
function cancellGroup(){
  var message = window.confirm("确定注销群组？" );
    if(message){   
        var url = "cancellGroup.action?sysClient=";
		location.href = url;
		return ;	
    }
    else{
       return;
    }

}
/**********************登录*********************/
function  login(){
//	alert("执行登录");
	var password =$("#passwordLogin").val();
	var userId =$("#userIdLogin").val();
	var veryCode = $("#veryCode").val();
	if("" == password || ""==userId){
		//alert("请输入登录号和密码!");
		$("#tips").text("请输入登录号和密码!");
		$("#userIdLogin").focus();
		return;
	}
			
			$.post("login.action",{userId:userId,password:password, "veryCode":veryCode},
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
				 		else if(version =="1"){
				 			location.href = "/ems/mainPage1.jsp";	 
				 		}else if(version =="2"){
				 			location.href = "/ems/mainPage2.jsp";	 
				 		}else if(version =="3"){
				 			location.href = "/ems/mainPage3.jsp";	 
				 		}else if(version =="4"){
				 			processTip();
				 			location.href = "/ems/emsIndex.jsp";
				 		}
					 }else{
						//alert(dat);
						//错误提示信息
						$("#tips").text(flag);
						if( version > 2 ){
							$("#getCode").show();
							$("#veryCode").show();
							document.getElementById('getCode').src="validateImg.action?"+Math.ceil(Math.random()*1000);
						}
						//alert(version)
					}
				}
				);

}
/**********************登录检查*********************/
function checkLogin(){
//登录检查
	var userId =$("#userId").val(); 
	if(userId==null || userId == "" ){
		alert("请先登录！");
		return ;
	}else{
		return "true";
	}
}
/**********************检查是否选择了群组*********************/
function checkGroup(sysClient)
{
//	alert("测试群组");
//检查是否已选择群组,没有就跳转到选择群组界面
	var loginflag = checkLogin();
	var groupId=$("#groupId").val(); 
	if(loginflag=="true"){
		if(groupId==null || groupId == "" ){
			//alert("请先选择群组号！");
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
//显示群组的详细信息
function getGroupDtl(){
	var groupId = $('#groupId2').val();
	$.post("getGroupDtl.action",{groupId:groupId},
				function(dat){
				//alert(dat);
				var s =dat.split("$$");
				$("#scorFor").attr("value",s[1]);
					if(s[0]==null || s[0]==""){
						alert("系统出现错误，查询不到群组信息！");
					}else{
						$("#groupDtlDiv").html(s[0]);
					}
				});	
}


/**********************选择了群组后 自动启动客户端*********************/
//没有选择考核入口前的 js：
//function toclient(){
//alert($("#sysClient").val());
//将session中的sysClient 清空，防止每次跳转到该页面时都弹出下面的窗口
//$.post("sysClientDel.action",{},function(dat){});	
//var sysClient=$("#sysClient").val();
//var scorFor="";
//scorFor = $("#scorFor").val();
//document.getElementById("sysClient").value = "";
//var desc ="";
//if(sysClient!= ""){
//		if("ecbs"==sysClient ){
//			 desc ="柜面业务";
//		}else if("eias"==sysClient){
//			 desc ="票据业务";
//		}
//		else if("efes"==sysClient){
//			 desc ="跨行业务";
//		}else if("ees"==sysClient){
//			 desc ="考核系统";
//		}
//var message = window.confirm("是否现在跳转到"+desc+"?");
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
  //选择考核入口
function toclient(){
//alert($("#sysClient").val());
//将session中的sysClient 清空，防止每次跳转到该页面时都弹出下面的窗口
$.post("sysClientDel.action",{},function(dat){});	
var sysClient=$("#sysClient").val();
var scorFor="";
scorFor = $("#scorFor").val();
document.getElementById("sysClient").value = "";
var desc ="";
if(sysClient!= "" && scorFor!="1"){
		if("ecbs"==sysClient ){
			 desc ="柜面业务";
		}else if("eias"==sysClient){
			 desc ="票据业务";
		}
		else if("efes"==sysClient){
			 desc ="跨行业务";
		}else if("ees"==sysClient){
			 desc ="考核系统";
		}
 var message = window.confirm("是否现在跳转到"+desc+"?");
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
		var message = window.confirm("是否现在跳转到考核系统?");
		 if(message){
			//url="choiseEes3.jsp";//新样式的版本
			//window.parent.top.location=url;
			jump2EesByKey();//2011-11-29
			
		}
		
	}
  }


/**********************启动客户端*********************/
function callLocalExec(flag)
{	
	//var groupId=$("#groupId").val();
	//if(groupId==""||null==groupId){
	//	alert("请选择相应群组！");retrun;
	//}
	//var groupflag = checkGroup(flag);
	
	//if(groupflag == "true"){	
		var eiasPath = "\"C:\\EIAS\\bin\\EIAS.exe\"";//票据地址
		var fntrunPath = "\"C:\\fntrun\\autologin.bat\"";//综合会计地址
		//var fntrunPath = "\"C:\\fntrun\\run.bat\"";//
		var path2Run ;
		//票据需要选择群组，群组必须是已组成核心情景包和票据情情基本的
		if(flag=='eias'){
			var areaNo=$("#areaId").val();
			if(areaNo == "" ||areaNo ==null ){
				alert("此群组不能进入票据系统！");
				return;
			}else{
				changeEIASConfigFile();
				path2Run=eiasPath;
			}
		}
		if(flag=='ecbs'){
		var dbId=$("#dbId").val();
		if(dbId == "" ||dbId ==null ){
				alert("此群组不能进入综合会计核算系统！");
				return;
			}else{
				changeConfigFile();		//修改配置文件
				changeRunFile();//自动登录使用
				path2Run=fntrunPath;	
			}
			
		}
		var myShell = new ActiveXObject("WScript.Shell");
		myShell.Run(path2Run);
		myShell = null;
	//}
}// end of callLocalExec(flag)

/**********************修改柜面文件*********************/
function changeConfigFile()
{
   var dbname = $("#dbId").val(); 
   var groupId=$("#groupId").val(); 
   var tuxedoIP= $("#tuxedoIP").val();
  //var tuxedoIP= "192.168.0.52";
   var runModel = $("#runModel").val(); 
   var runModelDescr;
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
	//关闭文件
	fWrite.Close();
}//end of changeConfigFile()

//修改登录使用的exe文件 ：autologin.bat
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
 			//str2Write= "cd C:\\fntrun\\bin \r\n"+'start "CS" front.exe -l1 -U'+ecbsOper+" -P888888 -S1";	//Alpha Liang
 			str2Write= "C: \r\n" + "cd C:\\fntrun\\bin \r\n"+'start "CS" front.exe -l1 -U'+ecbsOper+" -P888888 -S1";	//Alpha Liang
 		}
 		else{
 				//str2Write= "cd C:\\fntrun\\bin \r\n" +'start "CS" front.exe -l1 -U'+ecbsOper+" -B"+ecbsOperCash+" -P888888 -S1";	//Alpha Liang
 			str2Write= "C: \r\n" + "cd C:\\fntrun\\bin \r\n" +'start "CS" front.exe -l1 -U'+ecbsOper+" -B"+ecbsOperCash+" -P888888 -S1";	//Alpha Liang
  		}
  	}
     	
  	}
 	fRead.Close();
 	var fWrite= fso.CreateTextFile("c:\\fntrun\\autologin.bat", true);
 	fWrite.WriteLine(str2Write);
	fWrite.Close();
}

/**********************修改票据文件*********************/
function changeEIASConfigFile(){
//改写票据地区号 alert("调到了!");
//	var areaNo ='<ww:property value="#session.userModel.areaId"/>';
	var areaNo=$("#areaId").val();
    
    var fso = new ActiveXObject("Scripting.FileSystemObject");
    var fRead = fso.OpenTextFile("c:\\EIAS\\Bin\\Eias.ini",1);
    var str2Write = "";
    while (!fRead.AtEndOfStream){ 
    	var eachLine =fRead.ReadLine();  	
     	if(eachLine.charAt(0)=='A'){
			str2Write += "AreaNo="+areaNo+"\n";
     	}else
            str2Write += eachLine+"\n";    		
     		//continue;
     }
    fRead.Close();
    var fWrite= fso.CreateTextFile("c:\\EIAS\\Bin\\Eias.ini", true);
    fWrite.WriteLine(str2Write);
	//关闭文件
	fWrite.Close();	
}

/**********************联行*********************/
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

/** EFES13002 start */
/**********************联行*********************/
function jumpToEfes(){
	var dbId=$("#dbId").val();
	var ecbsOper=$("#ecbsOper").val();
	var branch=$("#branch").val();
	//var url=efesUrl+"login.action?userModel.id="+userId+"&userModel.groupId="+groupId+"&userModel.password="+password;
	//alert(dbId+"oper:"+ecbsOper+"branch:"+branch);
	//var url="/efes/"+"login.action?userModel.oper="+ecbsOper+"&userModel.branch="+branch+"&userModel.sidId="+dbId;
	//alert(url);
	//window.open(url);
	return;
	
}//end of jump2Efes()
/** EFES13002 end */

/**********************考核*********************/

//2011-11-29 单一入口
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
		
		//修改柜面客户端的IP地址
		changeConfigIP(tuxedoIP);	
		//如果是学生进入考核系统，需要检查是否已选择群组
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
				alert("请选择相关群组！");
				return;
			}
			else if(scorFor!="E"){
				alert("此群组不能进入考核系统！");
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
//		//如果是学生进入考核系统，需要检查是否已选择群组
//			groupFlag = checkGroup("ees");
//			if(groupFlag == "true"){
//			//return false; //考核分开入口【20110615】
//			alert("请从考核入口进入！");//考核分开入口【20110916】
//			return ; //考核分开入口【20110916】
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
/**********************实验管理*********************/
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
/**********************知识库*********************/
function jump2Ekss(){
	var ekssUrl=$("#ekssUrl").val();
	window.open(ekssUrl);
	return;
}//end of jump2Ekss()

/******************获取知识库连接*************************/
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


/**********************考核系统--成绩查询*********************/

function  stuTestDetailQuery(){
	var eesUrl=$("#eesUrl").val();
	var url=eesUrl+"studentScoreQueryInit.action";
	window.open(url);
}//end of stuTestDetailQuery

/**********************考核系统--考核回顾*********************/

function  stuTestReviewQuery(){
	var eesUrl=$("#eesUrl").val();
	var url=eesUrl+"stuTestReviewQuery.action";
	window.open(url);
}//end of stuTestReviewQuery()
/**********************信贷系统*********************/
function jump2bcms(){
var bcmsUrl=$("#bcmsUrl").val();
	var loginFlag = checkLogin();
		if(loginFlag =="true"){
		window.open(bcmsUrl);
		}
	return;
}//end of jump2bcms()
/**********************国际结算系统*********************/
function jump2btits(){
var url=$("#btitsUrl").val();
	var loginFlag = checkLogin();
		if(loginFlag =="true"){
		window.open(url);
		}
	return;
}//end of jump2bcms()

//2011-11-29
//修改柜面客户端ip地址
function changeConfigIP(ip){
 var dbname = $("#dbId").val(); 
   var groupId=$("#groupId").val(); 
   var runModel = $("#runModel").val(); 
   var runModelDescr ;
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
	//关闭文件
	fWrite.Close();

}