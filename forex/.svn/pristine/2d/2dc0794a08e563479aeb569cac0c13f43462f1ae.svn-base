<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib prefix="ww" uri="webwork"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>询价交易单</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="resources/ponyjs/jquery.js" type="text/javascript"></script>
	<style type="text/css">
		#top{margin: 0; padding:3px 5px; height: 24px; width:820px; line-height: 24px; 
		background-image: url("images/b.png"); color: white; text-align: center; float: left;}
		
		#container{margin: 0;padding: 0; height: 300px; width: 830px !important; width:820px; background-color: #E5F2F8; 
		margin-top: 5px;  margin-bottom: 5px;  float: left;}
		
		.modules{margin: 0px; padding:0px 0px 0px 50px !important; width: 205px !important; 
		padding:0px 50px; width: 255px; height: 250px;  margin-right: 10px; float: left; }
		
		.modules2{margin: 0px; padding:0px 0px 0px 50px !important; width: 215px !important; 
		padding:0px 0px 0px  20px; width: 265px; height: 250px;  margin-right: 10px; float: left; }
	</style> 
	
	<script type="text/javascript">
		$(function(){
			if ('<ww:property value="#request.tradeCard.tradeDirection"/>' == 0){
				document.getElementById("ccy").innerHTML = '<ww:property value="#request.tradeCard.weCcy"/>' + "/" + '<ww:property value="#request.tradeCard.anaCcy"/>';
				document.getElementById("label1").innerHTML = '<ww:property value="#request.tradeCard.weCcy"/>' + "交易金额：";
				document.getElementById("label2").innerHTML = '<ww:property value="#request.tradeCard.anaCcy"/>' + "交易金额：";
			}
			else{
				document.getElementById("ccy").innerHTML = '<ww:property value="#request.tradeCard.anaCcy"/>' + "/" + '<ww:property value="#request.tradeCard.weCcy"/>';
				document.getElementById("label1").innerHTML = '<ww:property value="#request.tradeCard.anaCcy"/>' + "交易金额：";
				document.getElementById("label2").innerHTML = '<ww:property value="#request.tradeCard.weCcy"/>' + "交易金额：";
			}
		});
	
	</script>
  </head>
  
  <body>
  	<div id="top">询价交易单</div>
  	<form action="">
  		<div id="container">
	    	<div class="modules2">
	    		<br><br>
	    		<label>交易序号：</label><span><ww:property value="#request.tradeCard.tradeNo"/></span><br><br><br>
	    		<label id="label1"></label><span><ww:property value="#request.tradeCard.amount"/></span><br><br><br>
	    		<label>提交时间：</label><span><ww:property value="#request.tradeCard.realCreateDate"/></span><br><br><br>
	    		<label>交易状态：</label><span><ww:property value="#request.tradeCard.realStatus"/></span><br><br><br>
    			<label>实时价格：</label><span><ww:property value="#request.tradeCard.currentPrice"/></span>
	    	</div>
	    	<div class="modules2">
	    	    <br><br>
	    		<label>交易类型：</label><span><ww:property value="#request.tradeCard.tradeType"/></span><br><br><br>
	    		<label id="label2"></label><span><ww:property value="#request.tradeCard.tradeSum"/></span><br><br><br>
	    		<label>交易时间：</label><span><ww:property value="#request.tradeCard.realTradeDate"/> <ww:property value="#request.tradeCard.realTime"/></span><br><br><br>
	    		<label>对手方机构：</label><span><ww:property value="#request.tradeCard.anaInstitution"/></span><br><br><br>
	    		<label>盈亏额：</label><span><ww:property value="#request.tradeCard.proAndLoss"/></span>
	    	</div>
	    	<div class="modules">
	    	    <br><br>
	    		<label>交易币种：</label><span id="ccy"></span><br><br><br>
	    		<label>交易价格：</label><span><ww:property value="#request.tradeCard.tradePrice"/></span><br><br><br>
	    		<label>交易方向：</label><span><ww:property value="#request.tradeCard.realDirection"/></span><br><br><br>
	    		<label>本方机构：</label><span><ww:property value="#request.tradeCard.weInstitution"/></span><br><br><br>
	    		<label>盈亏率：</label><span><ww:property value="#request.tradeCard.proAndLossRate"/> %</span>
	    	</div>
    	</div>
    
    	<div id="top"><input type="button" value="确认" style="width: 120px; height:24px;" onclick="history.back();"></div>
  	</form>

  </body>
</html>
