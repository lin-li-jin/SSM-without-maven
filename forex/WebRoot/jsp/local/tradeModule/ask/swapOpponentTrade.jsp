<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib uri="extremecomponents" prefix="ec"%>
<%@ taglib uri="webwork" prefix="ww"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>swap询价掉期交易对手方页面</title>
    
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
		background-image: url("images/b.png"); color: white; text-align: center; }
		#container{margin: 0;padding: 0; height: auto; width: 900px; background-color: #E5F2F8;
		margin-top: 5px; margin-bottom: 5px;}
		#wrapOne{margin: 0;padding: 0; height: 50px; width: 900px;  margin-bottom: 3px; 
		line-height: 50px; text-align: center;}
		label { font-size: 24px; font-weight: bolder; color: red;} 
		.modules{margin: 0;padding: 10px 0px 10px 40px;; height: 20px; width: 860px; 
		margin-top: 2px; line-height: 20px; }
		.modules1{margin: 0;padding: 0;; height: 178px; width: 900px; 
		margin-top: 2px; line-height: 20px; border-bottom: 1px solid black; border-top:1px solid black; }
		.modules1left{margin:0 ;padding: 5px 0 0 40px; height: 175px; width: 410px; float:left;
		 }
		 .modules1right{margin: 0;padding: 5px 0 0 40px; height: 175px; width: 410px;  float:left;
		 }
		.xiegan{font-family:Arial;font-weight:bolder;font-style:normal;text-decoration:none;
		color:#333333;font-size: 18px; }
		.button1{width: 80px;}
		.button2{ width: 80px; height: 24px;}
	</style>
	<link rel="stylesheet" href="resources/css/extremecomponents.css"	type="text/css" />
	<script src="resources/ponyjs/jquery.js" type="text/javascript"></script>
	<script type="text/javascript" language="javascript">
	function submitBySwap(obj){
		if(obj.id=="send"){
			var direction = '<ww:property value="#request.direction"/>';
			var tranNo = document.getElementById("tranNo").value;
			var provider = document.getElementById("provider").value;
			var point = 0;
			if(direction == 0){
				point = document.getElementById("ask_point").value;
			}else{
				point = document.getElementById("bid_point").value;
			}
			//send按钮被禁用
			document.getElementById("send").disabled = true;
			window.location.href = "sendBySwap.action?tranNo="+tranNo+"&provider="+provider+"&point="+point;
		}	
		else if(obj.id=="close"){
			document.form1.action = "closeBySwap.action";
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
		}
		else{
			document.getElementById("direction").innerHTML = "I Buy ";
			//bid部分按钮被激活
			document.getElementById("bid_up").disabled = false;
			document.getElementById("bid_down").disabled = false;
			document.getElementById("bid_point").disabled = false;
			document.getElementById("bid_point").value = point;
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
	    	<span style="margin-right: 150px;" >掉期询价交易</span>
	  		<span style="margin-left: 400px;" >Times:</span><span><ww:property value="#request.times"/></span>
	  		
	    	</div>
	    	<div id="container">
	    		<div id="wrapOne">
		  			<label ><span id="direction"></span><ww:property value="#request.weCcy"/> vs <ww:property value="#request.anaCcy"/></label>
		  			<label style="margin-left:50px;">Price:</label><label><ww:property value="#request.bean.price"/></label>
	  			</div>
	  			<div class="modules">
					<span style="margin-left: 40px; margin-right: 20px;">Start Date:</span>
					<input id="d1" type="text" size="15" disabled="disabled" value='<ww:property value="#request.startDate"/>'/>
	  				
	  				<span style="margin-left: 40px; margin-right: 17px;">Maturity  Date:</span>
	  				<input id="d2" type="text" size="15" disabled="disabled" value='<ww:property value="#request.maturityDate"/>'/>
	  			</div>
	  			
	  			<div class="modules1">
	  				<div class="modules1left">
	  					<span style="color:red;">1st Leg</span><br>
	  					<span>I <ww:property value="#request.fixedType"/></span>
	  					<input type="text" size="5" disabled="disabled" value='<ww:property value="#request.receiveCcy"/>'>
	  					<span style="font-size: 18px; font-weight: bolder;">@</span>
	  					<input type="text" size="10" disabled="disabled" value='<ww:property value="#request.fixedRate"/>'>
	  					<span style="font-size: 18px; font-weight: bolder;">%</span>
	  					<br><br>
	  					
	  					
	  					<span>Amount:</span>
	  					<input type="text" size="10" disabled="disabled" value='<ww:property value="#request.bean.cAmount"/>'>
	  					<br><br>
	  					
	  					<span>Basis:</span>
	  					<input type="text" size="10" disabled="disabled" value='<ww:property value="#request.bean.cBasis"/>'>
	  					<br><br>
	  					
	  					<span>Payment Frequency:</span>
	  					<input type="text" size="10" disabled="disabled" value='<ww:property value="#request.frequency"/>'>
	  					<br><br>
	
	  				</div>
	  				<div class="modules1right">
	  					<span style="color:red;">2nd Leg</span><br>
	  					<span>I <ww:property value="#request.floatingType"/></span>
	  					<input type="text" size="5" disabled="disabled" value='<ww:property value="#request.payCcy"/>'>
	  					<span style="font-size: 18px; font-weight: bolder;">@</span>
	  					<input type="text" size="10" disabled="disabled" value="Floating">
	  					<br><br>
	  					
	  					
	  					<span>Amount:</span>
	  					<input type="text" size="10" disabled="disabled" value='<ww:property value="#request.bean.fAmount"/>'>
	  					<br><br>
	  					
	  					<span>Libor:</span>
	  					<input type="text" size="10" disabled="disabled" value='<ww:property value="#request.libor"/>'>
	  					<br><br>
	  					
	  					<span>Basis:</span>
	  					<input type="text" size="10" disabled="disabled" value='<ww:property value="#request.bean.fBasis"/>'>
	  					<br><br>
	  				</div>
	  			</div>
	  			 <div class="modules">
	  				<span style="margin-left: 20px; margin-right: 140px;">INITIATOR</span>
	  				<span>Bid Swap Pts</span>
	  				<span class="xiegan" style="margin: 0px 10px;">/</span>
	  				<span>Ask Swap Pts</span>
	  			</div>
	  			
	  					<div class="modules">
			  				<input id="provider" type="text" size="13" disabled="disabled" value='<ww:property value="#request.bean.provider"/>'>
			  				<input id="bid_down" type="button" width="20px;" value="-" onclick="subByPoint(this)" disabled="disabled">
					  		<input id="bid_point" type="text" size="10" disabled="disabled">
					  		<input id="bid_up" type="button" width="20px;" value="+" onclick="addByPoint(this)" disabled="disabled">
			  				<span id="bid_price"><ww:property value="#request.bidPrice"/></span>
			  				<span class="xiegan">/</span>
			  				<span id="ask_price"><ww:property value="#request.askPrice"/></span>
			  				<input id="ask_down" type="button" width="20px;" value="-" onclick="subByPoint(this)" disabled="disabled">
					  		<input id="ask_point" type="text" size="10" disabled="disabled">
					  		<input id="ask_up" type="button" width="20px;" value="+" onclick="addByPoint(this)" disabled="disabled">
			  				<input id="send" name="send" type="button" value="Send" class="button1" onclick="submitBySwap(this)">
					  		<input id="close" name="close" type="button" value="Close" class="button1" onclick="submitBySwap(this)">
			  			</div>
		  			<div>
			  			<br>
			  			<span style="width: 150px; margin-left: 40px;">交易状态：</span><input id="status" disabled="disabled" type="text" value="ARGUING">
			  		</div>
	    	</div>
	    	
	    	<div id="top">
	    	</div>
	    	<input id="tranNo" type="hidden" name="tranNo" value='<ww:property value="#request.bean.tranNo"/>'>
	    	<input type="hidden" name="provider" value='<ww:property value="#request.bean.provider"/>'>
	    	<input type="hidden" name="price" id="price">
	   		<input type="hidden" name="point" id="point">
   		</form>
    </div>
  </body>
</html>
