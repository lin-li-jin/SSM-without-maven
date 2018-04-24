<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ page import="com.talent.auth.bean.model.*"%>
<%@ page import="com.talent.auth.bean.domain.Menu"%>
<%@ page import="com.talent.forex.util.TreeMenuUtil"%>
<!-- 
/*
 * Amendment No.: forex130012
 * Modify By    : guogm	
 * Description  : 菜单
 * Modify Date  : 2013-8-13
 * 
 */
 -->
<%
	UserModel user = (UserModel)session.getAttribute("loginUserModel");
	System.out.println("准备打印一级菜单信息......！");
	System.out.println(user);
	System.out.println(user.getPostName());
	TreeMenuUtil treemenuutil=new TreeMenuUtil();
	Menu menu = new Menu();
	menu.setPostName(user.getPostName());
	List<String> mainnodename =new ArrayList<String>();
	List<Menu> nodelist =new ArrayList<Menu>();
	nodelist=treemenuutil.getPostMenulist(menu);
	for(int i=0;i<nodelist.size();i++)
	{
		if(nodelist.get(i).getPid() == 0)
		{
			mainnodename.add(nodelist.get(i).getCaption());
		}
	}
%>
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
			<%
				int size=mainnodename.size();
				for(int i=0;i<size;i++)
				{
					out.println("$('.nav').append('<input class=\"button\" type=\"button\" index=\""+i+"\" value=\""+mainnodename.get(i)+"\" title=\""+mainnodename.get(i)+"\">')");
				}
			%>
			
			$('.nav input').click(function changeMenu(){
				$.get("changeMenu.action?index="+$(this).attr("index"),{},function(){
				window.parent.frames["treeMenu"].location.reload();});
			});
			
		});
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
