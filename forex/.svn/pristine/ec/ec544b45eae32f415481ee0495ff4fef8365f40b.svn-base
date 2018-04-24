<%@ page contentType="text/html;charset=GBK" %>
<%@ taglib prefix="ww" uri="webwork" %>
<html xmlns:v>

<head>
	<title>修改密码结果</title>
	<link rel="stylesheet" href="../../../resources/css/talent.css" type="text/css" />
	<script src="/eems/resources/ponyjs/jquery.js" type="text/javascript"></script>
		<script src="/eems/resources/ponyjs/jquery_validate.js"
			type="text/javascript"></script>
		<script src="/eems/resources/ponyjs/jquery.ui.draggable.js"
			type="text/javascript"></script>
		<script src="/eems/resources/ponyjs/jquery.alerts.js"
			type="text/javascript"></script>
	<meta http-equiv="content-type" content="text/html; charset=GBK" />		
	<script type="text/javascript">
	  $(function(){
	  $("#body").keydown(function(event){
      	//parent.location='/eems/logout.action'
      	$.post("logout.action",{},function(){
		//window.parent.parent.focus();
		//window.parent.close();
		//window.open("/ec","top");
		window.parent.location="/ec";
	});
     });
   });
   function back(){
	$.post("logout.action",{},function(){
		window.parent.location="/ec";
	});
}
   
   </script>
</head>

<body id="body">
	<jsp:include page="Result.jsp"/>	
	<br>
	<center>
				<input type="button" class="button" value="重新登陆" onClick="back()">
	</center>					
</body>

</html>