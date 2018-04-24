<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib uri="extremecomponents" prefix="ec"%>
<%@ taglib uri="webwork" prefix="ww"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>forward远期询价交易对手方页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script language="javascript" type="text/javascript"
	src="resources/calendar/js/My97DatePicker/WdatePicker.js"></script>
	<style type="text/css">
		#all{margin: 0;padding: 0; height: auto; width: 900px;}
		#top{margin: 0; padding:3px 5px; height: 24px; width:890px; line-height: 24px; 
		background-image: url("images/b.png"); color: white; text-align: left; }
		#container{margin: 0;padding: 0; height: auto; width: 900px; background-color: #E5F2F8;
		margin-top: 5px; margin-bottom: 5px;}
		#wrapOne{margin: 0;padding: 0; height: 70px; width: 900px;  margin-bottom: 3px; 
		line-height: 70px; text-align: center; }
		label { font-size: 24px; font-weight: bolder; color: red;} 
		.modules{margin: 0;padding: 10px 0px 10px 40px;; height: 20px; width: 860px; 
		margin-top: 2px; line-height: 20px;}
		.xiegan{font-family:Arial;font-weight:bolder;font-style:normal;text-decoration:none;
		color:#333333;font-size: 18px; }
		.button1{width: 80px;}
		.button2{ width: 80px; height: 24px;}
	</style>
	<link rel="stylesheet" href="resources/css/extremecomponents.css"	type="text/css" />
	<script src="resources/ponyjs/jquery.js" type="text/javascript"></script>
	
	<script type="text/javascript" language="javascript">
	function submitByForward(obj){
		if(obj.id=="send"){
			var direction = '<ww:property value="#request.direction"/>';
			var tranNo = document.getElementById("tranNo").value;
			var provider = document.getElementById("provider").value;
			var point = 0;
			if(direction == 0){
				point = document.getElementById("ask_point").value;
				/* document.getElementById("price").value = document.getElementById("ask_price").innerHTML;
				document.getElementById("point").value = document.getElementById("ask_point").value; */
			}else{
				point = document.getElementById("bid_point").value;
				/* document.getElementById("price").value = document.getElementById("bid_price").innerHTML;
				document.getElementById("point").value = document.getElementById("bid_point").value; */
			}
			//send按钮被禁用
			//document.getElementById("send").disabled = true;
			window.location.href = "sendByForward.action?tranNo="+tranNo+"&provider="+provider+"&point="+point;
			/* document.form1.action = "sendByForward.action";
			document.form1.submit(); */
		}	
		else if(obj.id=="close"){
			document.form1.action = "closeByForward.action";
			document.form1.submit();
		}
	}
	$(function(){
		$('input:radio').click(function () {
			this.blur();   
			this.focus(); 
		});
		var price = '<ww:property value="#request.price"/>';
		var point = '<ww:property value="#request.bean.point"/>';
		var dd = window.parseFloat(price) + window.parseFloat(point)/10000;
		dd=window.parseFloat((Math.round(dd*10000))/10000);
		if('<ww:property value="#request.direction"/>' == 0){
			document.getElementById("direction").innerHTML = "I Sell ";
			//ask部分按钮被激活
			document.getElementById("ask_up").disabled = false;
			document.getElementById("ask_down").disabled = false;
			document.getElementById("ask_point").disabled = false;			
			document.getElementById("ask_point").value = point;
			document.getElementById("ask_price").innerHTML = dd+"";			
		}
		else{
			document.getElementById("direction").innerHTML = "I Buy ";
			//bid部分按钮被激活
			document.getElementById("bid_up").disabled = false;
			document.getElementById("bid_down").disabled = false;
			document.getElementById("bid_point").disabled = false;
			document.getElementById("bid_point").value = point;
			document.getElementById("bid_price").innerHTML = dd+"";
		}
	});
	function subByPoint(obj){
		var price='<ww:property value="#request.bean.price"/>';
		if(obj.id=="bid_down"){
			var val = document.getElementById("bid_point");
			val.value = window.parseInt(val.value) - 1;
			var dd=(window.parseFloat(price))+window.parseFloat(val.value)/10000;
			dd=window.parseFloat((Math.round(dd*10000))/10000);
			document.getElementById("bid_price").innerHTML = dd+"";
		}else if(obj.id=="ask_down"){
			var val = document.getElementById("ask_point");
			val.value = window.parseInt(val.value) - 1;
			var dd=(window.parseFloat(price))+window.parseFloat(val.value)/10000;
			dd=window.parseFloat((Math.round(dd*10000))/10000);
			document.getElementById("ask_price").innerHTML = dd+"";
		}
	}
	function addByPoint(obj){
		var price='<ww:property value="#request.bean.price"/>';
		if(obj.id=="bid_up"){
			var val = document.getElementById("bid_point");
			val.value = window.parseInt(val.value) + 1;
			var dd=(window.parseFloat(price))+window.parseFloat(val.value)/10000;
			dd=window.parseFloat((Math.round(dd*10000))/10000);
			document.getElementById("bid_price").innerHTML = dd+"";
		}else if(obj.id=="ask_up"){
			var val = document.getElementById("ask_point");
			val.value = window.parseInt(val.value) + 1;
			var dd=(window.parseFloat(price))+window.parseFloat(val.value)/10000;
			dd=window.parseFloat((Math.round(dd*10000))/10000);
			document.getElementById("ask_price").innerHTML = dd+"";
		}
	}
	</script>
  </head>
  
  <body>
    <div id="all">
    	<form id="form1" name="form1" method="post">
	    	<div id="top">
	    	<span style="margin-right: 150px;" >远期询价交易</span>
	  		<span style="margin-left: 400px;" >Times:</span><span><ww:property value="#request.bean.times"/></span>
	    	</div>
	    	<div id="container">
	    		<div id="wrapOne">
		  			<label><span id="direction"></span><ww:property value="#request.weCcy"/> vs <ww:property value="#request.anaCcy"/></label>
		  			<label style="margin-left:50px;">Amount:</label>
		  			<input type="text" size="15" disabled="disabled" value='<ww:property value="#request.bean.amount"/>'>
		  			<label style="margin-left:50px;">Price:</label><label><ww:property value="#request.price"/></label>
	  			</div>
	  			<div class="modules">
					<span style="margin-left: 20px; margin-right: 17px;">Value Date:</span>
					<input disabled="disabled" type="text" size="15" value='<ww:property value="#request.valueDate"/>'/>
	  			</div>
	  			<div class="modules">
	  				<span style="margin-left: 20px; margin-right: 130px;">INITIATOR</span>
	  				<span>Bid Swap Pts</span>
	  				<span class="xiegan" style="margin: 0px 10px;">/</span>
	  				<span>Ask Swap Pts</span>
	  			</div>
	  			
	  			<div class="modules">
		  				<input type="text" size="13" disabled="disabled" value='<ww:property value="#request.bean.provider"/>'>
		  				<input id="bid_down" type="button" width="20px;" value="-" onclick="subByPoint(this)" disabled="disabled">
				  		<input id="bid_point" type="text" size="10" disabled="disabled">
				  		<input id="bid_up" type="button" width="20px;" value="+" onclick="addByPoint(this)" disabled="disabled">
		  				<span id="bid_price"><ww:property value="#request.bean.price"/></span>
		  				<span class="xiegan">/</span>
		  				<span id="ask_price"><ww:property value="#request.bean.price"/></span>
		  				<input id="ask_down" type="button" width="20px;" value="-" onclick="subByPoint(this)" disabled="disabled">
				  		<input id="ask_point" type="text" size="10" disabled="disabled">
				  		<input id="ask_up" type="button" width="20px;" value="+" onclick="addByPoint(this)" disabled="disabled">
		  				<!-- <input type="button" value="Accept" class="button1"> -->
		  				<input id="send" name="send" type="button" value="Send" class="button1" onclick="submitByForward(this)">
				  		<input id="close" name="close" type="button" value="Close" class="button1" onclick="submitByForward(this)">
		  		</div>
	  			<div>
		  			<br>
		  			<span style="width: 150px; margin-left: 40px;">交易状态：</span><input disabled="disabled" type="text" value="ARGUING">
		  		</div>
	    	</div>
	    	
	    	<div id="top">
		  	</div>
	  		<input id="tranNo" type="hidden" name="tranNo" value='<ww:property value="#request.bean.tranNo"/>'>
    		<input id="provider" type="hidden" name="provider" value='<ww:property value="#request.bean.provider"/>'>
    		<input type="hidden" name="price" id="price">
    		<input type="hidden" name="point" id="point">
    	</form>
    </div>
  </body>
</html>
