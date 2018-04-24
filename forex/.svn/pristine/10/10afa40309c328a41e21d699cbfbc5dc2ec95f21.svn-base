<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ page import="com.talent.auth.bean.model.*"%>
<%@ page import="com.talent.menu.*"%>
<%@ page import="com.talent.forex.util.*" %>

<%
	UserModel user = (UserModel) session.getAttribute("loginUserModel");
	String sMenuFile = "/menu.xml";
	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <!-- 
   Amendment No : GJSY130009
   Modify By : CHENYUZHONG
   Date : 2013-07-18
   Description :修改ems功能菜单样式
    -->
  <head>
     <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">-->
	
	<link rel="stylesheet" href="../../../resources/css/styleY2.css" type="text/css" media="screen, print" />
	<link rel="stylesheet" href="../../../resources/css/font_styleY.css" type="text/css" media="screen, print" />
	
		<script src="../../../resources/ponyjs/jquery.js" type="text/javascript"></script>
		<script type="text/javascript">
		function ChangeStatus(o)
		{	
			var tmpJibie="";
			var myStatus="";
			var i=0;
			var maxOpen=0;
			myStatus=o.parentNode.className;
			if (o.parentNode&&o.parentNode.cascade)
			{
				tmpJibie=o.parentNode.cascade;
				$("li [@isParent='yes'][@cascade="+tmpJibie+"]").each(function(dat){
					var nowStatus=$(this).attr("class");
					if(nowStatus=="Opened"&&myStatus=="Closed"){
						i++;
					}
				});
			}
			//alert(i);
			
			if (o.parentNode&&o.parentNode.cascade)
			{
				if(i>=maxOpen){
					var kkk=0;
					$("li [@isParent='yes'][@cascade="+tmpJibie+"]").each(function(dat){
						var nowStatus2=$(this).attr("class");
						
						if(kkk==0&&nowStatus2=="Opened"&&myStatus=="Closed"){
							//alert("kkk");
							$(this).attr("class","Closed");
							kkk=kkk+1;
						}
						
					});
					
				}
			}

			if (o.parentNode)
			{
				if (myStatus == "Opened")
				{
					o.parentNode.className = "Closed";
				}
				else
				{
					o.parentNode.className = "Opened";
				}
			}
		}
		
		function submitForm(url) {
			document.all("treeMenuForm").action=url;
			document.all("treeMenuForm").submit();
		}
		</script>
		<script type="text/javascript">
			$(document).ready(function(){
			  $(".TreeMenu>ul>li>ul>li>a").addClass("l1");//为一级菜单加样式
			  $(".TreeMenu>ul>li>ul>li>ul>li>.l2").addClass("cl2");//为二级菜单加样式 
			  $(".TreeMenu>ul>li>ul>li>ul>li>a").addClass("cl20");
			  $(".TreeMenu>ul>li>ul>li>ul>li>ul>li>.l3").addClass("cl3");//为二级菜单加样式 
			});
		var dynamicLoading = {
		    css: function(path){
				if(!path || path.length === 0){
					throw new Error('argument "path" is required !');
				}
				var head = document.getElementsByTagName('head')[0];
		        var link = document.createElement('link');
		        link.href = path;
		        link.rel = 'stylesheet';
		        link.type = 'text/css';
		        head.appendChild(link);
		    },
	    js: function(path){
			if(!path || path.length === 0){
				throw new Error('argument "path" is required !');
			}
			var head = document.getElementsByTagName('head')[0];
	        var script = document.createElement('script');
	        script.src = path;
	        script.type = 'text/javascript';
	        head.appendChild(script);
	    }
		}
		
		function flag(){
			 var OsObject = "";
			 dynamicLoading.css("css/tree2.css");
 //  		if(navigator.userAgent.indexOf("MSIE")>0) {
//  			  	dynamicLoading.css("css/tree2.css"); 
 // 		 }
//		   else if(isFirefox=navigator.userAgent.indexOf("Firefox")>0){
//		       dynamicLoading.css("css/tree_ff.css");
//		   }
//		  else if(isOpera=navigator.userAgent.indexOf("Opera")>=0){
//		         dynamicLoading.css("css/tree_ff.css");
//		   }
//		  else if(isOpera=navigator.userAgent.indexOf("Chrome")>=0){
//		         dynamicLoading.css("css/tree_chrome.css");
//		   }
//		   else{
//		   		dynamicLoading.css("css/tree_ff.css");
//		   }
		 }
		</script>
	 </head>
  <!-- 
    GJSY130009  begin 
     <body onload="flag()" background="/ems/jsp/global/menu/css/img/menubg.jpg">
   	   <div style="position:absolute;left:0px;top:0px;width:180px;height:538px;">
   -->
    <body style="overflow-x:hidden" onload="flag()">
     <div style="position:absolute;left:0px;top:10px;width:180px;height:538px;">
	 <!-- GJSY130009  end -->
		<div id="u2_container" class="u2_container" style="position:absolute;top:-5px;left:-15px;>
		 <form method="post" name="treeMenuForm">
				
				<div class="TreeMenu" id="CategoryTree">
				<%out.write(new TreeMenu().genMenu(user.getPermissions(),"",sMenuFile)); %>
				</div>
		</form> 
	</div>
	</div>
  </body>
</html>

