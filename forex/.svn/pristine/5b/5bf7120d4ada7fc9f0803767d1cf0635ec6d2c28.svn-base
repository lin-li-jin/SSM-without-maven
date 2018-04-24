<%@ page contentType="text/html;charset=GBK"%>
<%@ taglib prefix="ww" uri="webwork"%>
<!-- forex130011 Start -->
<%@ taglib prefix="tt" uri="talent-tag"%>
<!-- forex130011 End -->
<html>
	<head>
	<title>水平伸缩条</title>
	<link rel="stylesheet" href="../../../resources/css/talent.css" type="text/css" />
	<script src="../../../resources/ponyjs/jquery.js" type="text/javascript"></script>

<style type="text/css">
<!--
body {
	background-color:#e9f0ff;
-->
</style>
<script type="text/javascript">
function showBtn_onclick() {
	if(showBtn.src.indexOf("up")!=-1){
		parent.global.rows="0px,20px,*"; 
		showBtn.src="../../../resources/image/division/down.jpg";
		showBtn.alt="向下展开";
	}else{
		parent.global.rows="61px,20px,*,35px";
		showBtn.src="../../../resources/image/division/up.jpg";
		showBtn.alt="向上收缩";
	}
}
function back(){
	$.post("logout.action",{},function(){
		//window.parent.parent.focus();
		//window.parent.close();
		//window.open("/ec","top");
		window.parent.location="/forex";
	});
}

function changeLang(){
	$.post("changeLang.action?locale=en_US",{},function(){
		window.location.reload();
	});
}

function getAll()
{
	var paramString = "{";
	var $param = $(window.parent.frames["center"].document).find("span[id]");
	$.each($param,function(){
		paramString+=$(this).attr("id")+":";
		paramString+="\""+$(this).attr("id")+"\",";
	});
	if (paramString != '{') {
		paramString=paramString.substr(0,paramString.length-1); 
	} 
	paramString+="}";
	paramString =  eval("(" + paramString + ")");
	return paramString;
}	
function changeLanguage(){
	var paramString = getAll();
	$.post("changeLanguage.action",paramString,
	 function(data){
	 var dataObj=eval("("+data+")");
	   $.each(dataObj,function(name,val){
	    	$(window.parent.frames["center"].document).find("span#"+name).html(val);
	    	/*
	    	$("input[@type=button]").each(function (){
				//$(this).attr("readOnly",true);
			
				if($(this).attr("id",name)==true){
					$(this).attr("value",val);
				}
			});*/	
	    });
	   window.location.reload();
	   });
}
</script>
</head>
<script language="JavaScript">
document.oncontextmenu = function() { return false;} ;
</script>
	<body topMargin="0" leftmargin="0" rightmargin="0" bottommargin="0">

			<div style="margin-top:1px;margin-bottom:1px;border-
								bottom:1px solid #ffffff; ">
				<div title="登录用户 " style="float:left;margin-left: 15px;">
					<tt:label name="title_userName"/>：
					<ww:property value="#session.loginUserModel.name"/>
		        	[<ww:property value="#session.loginUserModel.userId"/><!--
&nbsp;<ww:property value="#session.loginUserModel.unitName"/>-->]
				</div>
				<div  style="float:right">
					<!-- forex130011 Start -->
						<!-- <a href="#" onClick="changeLanguage();" value="语言转换"
title="语言转换"><tt:label name="title_changeLang"/></a> -->
					<!-- forex130011 End -->
					<a href="#" onclick="back();" title="退出"><tt:label name="title_logout"/></a>
					&nbsp;&nbsp;
				</div>
			</div>
	</body>
</html>
