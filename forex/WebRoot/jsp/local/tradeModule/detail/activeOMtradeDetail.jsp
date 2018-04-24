<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib uri="webwork" prefix="ww"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>OMtrade!</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script language="javascript" type="text/javascript"
	src="resources/calendar/js/My97DatePicker/WdatePicker.js"></script>
	<script src="resources/ponyjs/jquery.js" type="text/javascript"></script>
	<style type="text/css">
		#top{margin: 0; padding:3px 5px; height: 24px; width:790px; line-height: 24px; background-image: url("images/b.png"); color: white; text-align: center; margin-top: 5px;}
		#container{margin: 0;padding: 0; height: 345px;width: 800px !important; width:790px; background-color:#E5F2F8; margin-top: 5px; }
		#wrapOne{margin: 0;padding: 0; height: 100px; width: 800px !important; width:790px;  margin-bottom: 3px; line-height: 100px; text-align: center;}
		#wrapTwo{margin: 0;padding: 0; height: 120px; width: 720px !important; width:710px;  margin-bottom: 3px; padding-top: 20px; padding-left: 80px;}
		#wrapThree{margin: 0;padding: 0; height: 100px; width: 800px !important; width:790px; margin-bottom: 3px; }
		label { font-size: 36px; font-weight: bolder; color: red;}
		.part{margin: 0;padding: 10px 0 0 80px; height: 90px; width:318px;  float: left; margin-right: 2px;}
		.button1{width: 120px; height: 36px;vertical-align:text-bottom; margin: 0 20px 0 20px;}
		.button2{width: 120px; height:24px;}
		span{font-weight: bolder; font-size: 18px; margin-right: 20px;}
	</style>
	
	<script type="text/javascript">
		/* $(document).ready(
			function(){
　　				var time = <ww:property value="#request.tradeCard.realCancelTime"/>;
				var button = document.getElementsByName("timeEnd");
				if(time == "9999-99-99"){
					document.getElementsByName("timeEnd")[0].checked = true;
				}
				else{
					document.getElementById("d2").innerHTML = time;
				}
			}
		); */
		$(function(){
			if (<ww:property value="#request.tradeCard.tradeDirection" /> == 0){
				document.getElementById("tradeDirect").innerHTML = "I Sell " + '<ww:property value="#request.tradeCard.weCcy" />' + " vs " + '<ww:property value="#request.tradeCard.anaCcy" />';
				document.getElementById("tSpan").innerHTML = "I Sell " + '<ww:property value="#request.tradeCard.weCcy" />';
				document.getElementById("sSpan").innerHTML = "I Sell " + '<ww:property value="#request.tradeCard.weCcy" />';
			}
			else{
				document.getElementById("tradeDirect").innerHTML = "I Buy " + '<ww:property value="#request.tradeCard.anaCcy" />' + " vs " + '<ww:property value="#request.tradeCard.weCcy" />';
				document.getElementById("tSpan").innerHTML = "I Buy " + '<ww:property value="#request.tradeCard.anaCcy" />';
				document.getElementById("sSpan").innerHTML = "I Buy " + '<ww:property value="#request.tradeCard.anaCcy" />';
			}
			if (<ww:property value="#request.tradeCard.realActiveTime" /> == ''){
				document.getElementsByName("timeStart")[0].checked = true;
			}
			else{
				document.getElementsByName("timeStart")[1].checked = true;
				document.getElementById("d1").style.display ="inline";
			}
			if (<ww:property value="#request.tradeCard.realCancelTime" /> == ''){
				document.getElementsByName("timeEnd")[0].checked = true;
			}
			else{
				document.getElementsByName("timeEnd")[1].checked = true;
				document.getElementById("d2").style.display ="inline";
			}
		});
		function confirmCancel() 
		{
	    	if(confirm('确定要取消此交易吗?')) 
	    	{ 
	    		window.location.href = '<ww:property value="#request.table"/>'+"Cancel.action?tradeNo="+'<ww:property value="#request.tradeCard.tradeNo"/>';
	       		return true; 
	    	} 
	   		return false; 
		}
	</script>
  </head>
  
  <body>
  <form action="">
    <div id="top">
  		<span><ww:property value="#request.tradeCard.tradeType" /></span>
  	</div>
  	<div id="container">
  		<div id="wrapOne">
  			<label id="tradeDirect"></label>&nbsp;&nbsp;&nbsp;&nbsp;
  			<!-- <label>Price:</label><label><ww:property value="#request.tradeCard.price" /></label> -->
  		</div>
  		<div id="wrapTwo">
  			<span id="tSpan"></span>
  			Amount:<input type="text" disabled="disabled" size="10" value="<ww:property value='#request.tradeCard.aSum'/>">&nbsp;&nbsp;
  			Price:<input disabled="disabled" type="text" size="10" value="<ww:property value='#request.tradeCard.aPrice'/>">&nbsp;&nbsp;
  			<span>Take Profit</span>
  			<br><br>
  			<span id="sSpan"></span>
  			Amount:<input type="text" disabled="disabled" size="10" value="<ww:property value='#request.tradeCard.bSum'/>">&nbsp;&nbsp;
  			Price:<input disabled="disabled" type="text" size="10" value="<ww:property value='#request.tradeCard.bPrice'/>">&nbsp;&nbsp;
  			<span>Stop Loss</span>
  			<br><br>
  			Monitor Type:<input type="text" disabled="disabled" size="20" value="<ww:property value='#request.tradeCard.realMonitorPrice'/>">
  		</div>
  		<div id="wrapThree">
  			<div class="part">
  				<span style="font-weight: bolder; font-size: 18px;">GOOD FROM</span><br>
  				<input type="radio" name="timeStart" id="rOne" disabled="disabled" onclick="<!-- return checkOne(); -->" style="margin-left: 30px;">ACTIVATION<br>
  				<input type="radio" name="timeStart" id="rTwo" onclick="<!-- return checkOne(); -->" dataType="date" size="10" style="margin-left: 30px;" disabled="disabled"/>
  				Time: <img onClick="WdatePicker({el:'d1'})" style="display: none;" src="resources/calendar/image/Button.gif" id="imgid1"align="absMiddle" border="0" />
  				<input id="d1" style="display: none;" type="text" dataType="date" size="10" value="<ww:property value='#request.tradeCard.realActiveTime'/>" disabled="disabled"/>
  			</div>
  			<div class="part">
  				<span style="font-weight: bolder; font-size: 18px;">GOOD TILL</span><br>
  				<input type="radio" name="timeEnd" id="rThree" disabled="disabled" onclick="<!-- return checkTwo(); -->" style="margin-left: 30px;">CANCELED<br>
  				<input type="radio" name="timeEnd" id="rFour" onclick="<!-- return checkTwo(); -->" dataType="date" size="10" style="margin-left: 30px;" disabled="disabled"/>
  				Time: <img onClick="WdatePicker({el:'d2'})" style="display: none;" src="resources/calendar/image/Button.gif" id="imgid2"align="absMiddle" border="0" />
  				<input id="d2" style="display: none;" type="text" dataType="date" size="10" value="<ww:property value='#request.tradeCard.realCancelTime'/>" disabled="disabled"/>
  			</div>
  		</div>
  	</div>
  	<div id="top">
  		<input type="button" value="取消交易" class="button2" style="margin-right: 100px;" onclick="return confirmCancel();">
  		<input type="button" value="关闭" class="button2" onclick="history.back();">
  	</div>
  </form>

  </body>
</html>
