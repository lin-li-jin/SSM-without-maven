<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.talent.auth.bean.model.*"%>
<%@ page import="com.talent.forex.util.*"%>
<!-- forex130012 BEGIN -->
<%
	UserModel user = (UserModel)session.getAttribute("loginUserModel");
	System.out.println("准备打印树形菜单信息......！");
	System.out.println(user);
	System.out.println(user.getPostName());
	String sMenuFile=sMenuFile = "/handlemenu.xml";;
	String postName = user.getPostName();
	if(postName.equals("HAN_GRP")){
		sMenuFile = "/handlemenu.xml";
	}
	if(postName.equals("REV_GRP")){
		sMenuFile = "/checkmenu.xml";
	}
	if(postName.equals("APR_GRP")){
		sMenuFile = "/authorizemenu.xml";
	}
	if(postName.equals("FUL_GRP")){
		sMenuFile = "/fullmenu.xml";
	}
	String menu=(String)session.getAttribute("menu");
%>
<!-- forex130012 END -->
<!-- out.write(new MenuApp().genMenu(user.getPermissions(),user.getPostName(),sMenuFile)); -->
<html>
	<head>
		<title></title>
		<link type="text/css" href="css/tree.css" rel="stylesheet">
		<script src="../../../resources/ponyjs/jquery.js" type="text/javascript"></script>
		<script language=javascript>
		function ChangeStatus(o)
		{
			//alert(o);
			var tmpJibie="";
			var myStatus="";
			var i=0;
			var maxOpen=0;
			myStatus=o.parentNode.className;
			//alert (myStatus);
			//alert(o.parentNode.getAttribute("cascade"));
			if (o.parentNode&&o.parentNode.getAttribute("cascade"))
			{
				tmpJibie=o.parentNode.getAttribute("cascade");
				
				$("li [@isParent='yes'][@cascade="+tmpJibie+"]").each(function(dat){
					var nowStatus=$(this).attr("class");
					if(nowStatus=="Opened"&&myStatus=="Closed"){
						i++;
					}
				});
			}
			//alert(i);
			
			if (o.parentNode&&o.parentNode.getAttribute("cascade"))
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
//		function toKnow(){
//			var url="/ec/knowledge/index.jhtml";
//			window.open(url,"_top");
//		}
		</script>
		<script language=javascript>
	$(document).ready(function(){
			  $(".TreeMenu>ul>li>ul>li>a").addClass("l1");//为一级菜单加样式
			  $(".TreeMenu>ul>li>ul>li>ul>li>.l2").addClass("cl2");//为二级菜单加样式 
			  $(".TreeMenu>ul>li>ul>li>ul>li>a").addClass("cl20");
			  $(".TreeMenu>ul>li>ul>li>ul>li>ul>li>.l3").addClass("cl3");//为二级菜单加样式 
			}); 

		</script>
    
	</head>
	<body style="{background-color:#ffffff;}">
	<form method="post" name="treeMenuForm">
		<!-- <div style="position:absolute;width:20px;height:10px;z-index:999;"><img src='../../../resources/image/frame/mainMenuTop.jpg'></div> -->
		<div class="TreeMenu" id="CategoryTree">
		<!-- forex130012 BEGIN -->
		<%//out.write(new TreeMenu().genMenu(user.getPermissions(),user.getName(),sMenuFile,menu)); %>
		<%out.write(new TreeMenu().genMenu(user.getName(),sMenuFile,menu)); %>
		<!-- forex130012 END -->
		<!--  	<div style="position:absolute;bottom:10px;">
				<a href="javascript:toKnow()"><img src='../../../resources/image/frame/know.png' border="0"></a>
			</div>
	-->
		</div>
		
	</form>
	</body>
</html>