<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ taglib prefix="ww" uri="webwork"%>
<%@ taglib uri="extremecomponents" prefix="ec"%>
<!-- 
/*
 * Amendment No.: forex130012
 * Modify By    : guogm	
 * Description  : ²Ëµ¥
 * Modify Date  : 2013-8-13
 * 
 */
 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="/forex/resources/css/style.css" type="text/css"
			media="screen, print" />
	<link rel="stylesheet" href="/forex/resources/css/talent.css" type="text/css"
			media="screen, print" />
	<script src="../../../resources/ponyjs/jquery.js" type="text/javascript"></script>
	<script language=javascript>
		$(document).ready(function(){
			$.post("getMainNoden.action",{},
				function(dat){
				var mainNodeList = new Array();
				mainNodeList = dat.split("$$");
				for(var i=0;i<mainNodeList.length;i++){
					if(i%3==0){
						$(".nav").append("<br><br>");
					}
					$(".nav").append("<input class=\"button\" type=\"button\" onclick=\"changeMenu('"+i+"')\" value=\""+mainNodeList[i]+"\" title=\""+mainNodeList[i]+"\">");
				}
			});
		});
		function changeMenu(index){
			$.get("changeMenu.action?index="+index,{},function(){
			window.parent.frames["treeMenu"].location.reload();});
		};
	</script>
	<style type="text/css" rel="stylesheet">
		body
		{
			background:#e9f0ff;
		}
		.nav
		{
			width:100%;
			text-align:center;
		}
		.nav input
		{
			height:25px;
			margin-left:10px;
			cursor:hand;
		}
	</style>
  </head>
  
  <body>
    <div class="nav">
    </div>
  </body>
</html>
