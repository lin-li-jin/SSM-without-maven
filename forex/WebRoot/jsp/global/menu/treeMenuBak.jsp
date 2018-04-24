<%@ page contentType="text/html;charset=GBK"%>
<%@ page import="com.talent.auth.bean.model.*"%>
<%@ page import="com.talent.menu.*"%>

<%
	UserModel user = (UserModel) session.getAttribute("loginUserModel");
	String sMenuFile = "/menu.xml";
	
%>
<!-- out.write(new MenuApp().genMenu(user.getPermissions(),user.getPostName(),sMenuFile)); -->
<html>
	<head>
		<title></title>
		<link type="text/css" href="css/tree.css" rel="stylesheet">
		<script language=jscript>
		function ChangeStatus(o)
		{
			if (o.parentNode)
			{
				if (o.parentNode.className == "Opened")
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
	</head>
	<body style="{background-color:#ffffff;}">
	<form method="post" name="treeMenuForm">
		<div class="TreeMenu" id="CategoryTree">
		<%out.write(new MenuApp().genMenu(user.getPermissions(),"",sMenuFile)); %>
		</div>
	</form>
	</body>
</html>