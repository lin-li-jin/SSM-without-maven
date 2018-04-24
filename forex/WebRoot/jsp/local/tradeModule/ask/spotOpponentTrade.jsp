<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib uri="extremecomponents" prefix="ec"%>
<%@ taglib uri="webwork" prefix="ww"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>spot待处理交易页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
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
		margin-top: 2px; line-height: 20px; }
		.xiegan{font-family:Arial;font-weight:bolder;font-style:normal;text-decoration:none;
		color:#333333;font-size: 18px; }
		.button1{width: 80px;}
		.button2{ width: 80px; height: 24px;}
	</style>
	<link rel="stylesheet" href="resources/css/extremecomponents.css"	type="text/css" />
	<script src="resources/ponyjs/jquery.js" type="text/javascript"></script>
	<script src="/forex/jsp/local/tradeModule/ask/js/askTrade.js" type="text/javascript"></script>
	
	<script type="text/javascript" language="javascript">
	function submitBySpot(obj){
		if(obj.id=="send"){
			var direction = '<ww:property value="#request.direction"/>';
			var tranNo = document.getElementById("tranNo").value;
			var provider = document.getElementById("provider").value;
			var price = 0;
			if(direction == 0){
				price = document.getElementById("ask_price").value;
				//禁用ask部分按钮
				document.getElementById("ask_up").disabled = true;
				document.getElementById("ask_down").disabled = true;
				document.getElementById("ask_price").disabled = true;
			}else{
				price = document.getElementById("bid_price").value;
				//禁用bid部分按钮
				document.getElementById("bid_up").disabled = true;
				document.getElementById("bid_down").disabled = true;
				document.getElementById("bid_price").disabled = true;
			}
			//send按钮被禁用
			document.getElementById("send").disabled = true;
			window.location.href = "sendBySpot.action?tranNo="+tranNo+"&provider="+provider+"&price="+price;
			/* document.form1.action = "sendBySpot.action?tranNo="+tranNo+"&provider="+provider+"&price="+price;
			document.form1.submit(); */
		}	
		else if(obj.id=="close"){
			document.form1.action = "closeBySpot.action";
			document.form1.submit();
		}
	}
	$(function(){
		$('input:radio').click(function () {
			this.blur();   
			this.focus(); 
		});
		if('<ww:property value="#request.direction"/>' == 0){
			document.getElementById("direction").innerHTML = "I Sell ";
			//ask部分按钮被激活
			document.getElementById("ask_up").disabled = false;
			document.getElementById("ask_down").disabled = false;
			document.getElementById("ask_price").disabled = false;
			document.getElementById("ask_price").value = '<ww:property value="#request.price"/>';
		}
		else{
			document.getElementById("direction").innerHTML = "I Buy ";
			//bid部分按钮被激活
			document.getElementById("bid_up").disabled = false;
			document.getElementById("bid_down").disabled = false;
			document.getElementById("bid_price").disabled = false;
			document.getElementById("bid_price").value = '<ww:property value="#request.price"/>';
		}
	});
	</script>
  </head>
  
  <body>
    <div id="all">
    	<form id="form1" name="form1" method="post">
		    	<div id="top">
		    	<span style="margin-right: 150px;" >即期询价交易</span>
		  		<span style="margin-left: 400px;" >Times:</span><span><ww:property value="#request.times"/></span>
		  		
		    	</div>
    			<div id="container">
		    		<div id="wrapOne">
			  			<label><span id="direction"></span><ww:property value="#request.weCcy"/> vs <ww:property value="#request.anaCcy"/></label>
			  			<label style="margin-left:50px;">Amount:</label>
			  			<input type="text" size="15" disabled="disabled" value='<ww:property value="#request.bean.amount"/>'>
			  			<label style="margin-left:50px;">Price:</label><label><ww:property value="#request.price"/></label>
		  			</div>
	  				<div class="modules">
		  				<span style="margin-left: 30px; margin-right: 140px;">Initiator</span>
		  				<span>Bid</span>
		  				<span class="xiegan" style="margin: 0px 65px;">/</span>
		  				<span>Ask</span>
		  			</div>
			  		<div class="modules">
			  			<input type="text" width="20px" disabled="disabled" value='<ww:property value="#request.bean.provider"/>'>
			  			<input id="bid_down" type="button" width="20px;" value="-" onclick="subtraction(this)" disabled="disabled">
			  			<input id="bid_price" type="text" size="10" disabled="disabled">
			  			<input id="bid_up" type="button" width="20px;" value="+" onclick="addition(this)" disabled="disabled">
			  			<span class="xiegan">/</span>
			  			<input id="ask_down" type="button" width="20px;" value="-" onclick="subtraction(this)" disabled="disabled">
			  			<input id="ask_price" type="text" size="10" disabled="disabled">
			  			<input id="ask_up" type="button" width="20px;" value="+" onclick="addition(this)" disabled="disabled">
			  			<input id="send" name="send" type="button" value="Send" class="button1" onclick="submitBySpot(this)">
			  			<input id="close" name="close" type="button" value="Close" class="button1" onclick="submitBySpot(this)">
			  		</div>
  				<div>
  					<br>
	  				<span style="width: 150px; margin-left: 40px;">交易状态：</span><input id="statue" disabled="disabled" type="text" value="ARGUING">
  				</div>
    		</div>
    	
    		<div id="top">
    		</div>
    		<input id="tranNo" type="hidden" name="tranNo" value='<ww:property value="#request.bean.tranNo"/>'>
    		<input id="provider" type="hidden" name="provider" value='<ww:property value="#request.bean.provider"/>'>
    		<input type="hidden" name="price" id="price">
    	</form>
    </div>
  </body>
</html>
