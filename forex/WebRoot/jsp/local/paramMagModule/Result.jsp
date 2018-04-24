<%@ page contentType="text/html;charset=GBK" %>
<%@ taglib prefix="ww" uri="webwork" %>
<html xmlns:v>
<!-- 
/**
 * Amendment No : ISAS130012
 * Modify    By : ggm
 * Date         : 2013-08-13
 * Description  : 汇出汇款
 */
 -->
<head>
	<title>操作结果页面</title>
	<link rel="stylesheet" href="resources/css/talent.css" type="text/css" />
	<script src="/forex/resources/ponyjs/jquery.js" type="text/javascript"></script>
		<script src="/forex/resources/ponyjs/jquery_validate.js" type="text/javascript"></script>
	<meta http-equiv="content-type" content="text/html; charset=GBK" />		
</head>
<script type="text/javascript">
			$(document).ready(function(){ 
//			var oper="<ww:property value='#session.oper'/>";
//			var tranType="<ww:property value='#session.tranType'/>";
//			if (oper!="approve"){
///			  $("#swf").hide();
//			}
			///alert(oper);
			//alert(tranType);
//			if (tranType=="STOPPAY") {
//				$("#paramMoave").attr("value","resources/swf/tt1-2(after).swf");
//				$("#embed").attr("src","resources/swf/tt1-2(after).swf");
//			} else if(tranType=="RETURN") {
//				$("#paramMoave").attr("value","resources/swf/tt1-3(after).swf");
//				$("#embed").attr("src","resources/swf/tt1-3(after).swf");			
//			}
//			  setTimeout("slideup()",11000);//毫秒为单位
			});
			function showSwf(){
			   $("#swf").find("object").slideDown();
			   var timer = setTimeout("slideup()",11000);
			   var movie=document.getElementById("player").play();
			}
			function slideup(){
			  $("#swf").find("object").slideUp();
			}
		</script>

<body>
	<!--  
	<div id='swf' style="float:left;">
		<img width="25" height="25" src="/isas/resources/image/Add Square.png"	onmouseover="showSwf();">
		<br/>
		<object id="player" style="border:5px solid #ccc"  classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0" width="200" height="100">
		        <param id="paramMoave" name="movie" value="resources/swf/tt1-1(after).swf">
		        <param name="wmode" value="transparent">
		        <embed id="embed" src="resources/swf/tt1-1(after).swf" width="675" height="500" type="application/x-shockwave-flash" />
		</object>
	</div>-->
	<jsp:include page="../../global/public/Result.jsp"/>
	
	<br>
	
	<center>
		<input type="button" class="button"	 value="返回" onClick="window.location='homePageInit.action'">
	</center>					
</body>

</html>