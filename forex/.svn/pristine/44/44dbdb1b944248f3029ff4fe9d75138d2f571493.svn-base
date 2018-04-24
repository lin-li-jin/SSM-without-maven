<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib prefix="ww" uri="webwork"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>保证金远期交易单</title>
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
		padding:0px 0px 0px  20px; width: 255px; height: 250px;  margin-right: 10px; float: left; }
		
		.modules2{margin: 0px; padding:0px 0px 0px 50px !important; width: 215px !important; 
		padding:0px 0px 0px  20px; width: 265px; height: 250px;  margin-right: 10px; float: left;  }
	</style> 
	

  </head>
  
  <body>
  	<div id="top">保证金远期交易单</div>
  	<form action="">
  		<div id="container">
    	<div class="modules2">
    		<br><br>
    		<label>交易序号：</label><span><ww:property value="#request.forwardDetail.tranNo"/></span><br><br><br>
    		<label id="label1">账户交易金额:</label><span><ww:property value="#request.forwardDetail.accAmount"/></span><br><br><br>
    		<label>本方机构：</label><span><ww:property value="#request.forwardDetail.account"/></span><br><br><br>
    		<label>交易状态：</label><span><ww:property value="#request.realStatus"/></span><br><br><br>
    		<label>盈亏额：</label><span><ww:property value="#request.profitAndLoss"/></span>
    	</div>
    	<div class="modules2">
    	    <br><br>
    		<label>交易类型：</label><span>远期交易</span><br><br><br>
    		<label id="label2">交易放大倍数:</label><span><ww:property value="#request.enlarge"/></span><br><br><br>
    		<label>交易日期：</label><span><ww:property value="#request.createDatetime"/> <ww:property value=""/></span><br><br><br>
    		<label>交易价格：</label><span><ww:property value="#request.forwardDetail.price"/></span><br><br><br>
    		<label>盈亏率：</label><span><ww:property value="#request.profitAndLossRate"/> %</span>
    	</div>
    	<div class="modules">
    	    <br><br>
    		<label>交易币种：</label><ww:property value="#request.forwardDetail.weCcy"/>/<ww:property value="#request.forwardDetail.anaCcy"/><br><br><br>
    		<label>虚盘交易金额：</label><span><ww:property value="#request.forwardDetail.dealAmt"/></span><br><br><br>
    		<label>交割日期：</label><span><ww:property value="#request.valueDate"/></span><br><br><br>
    		<label>实时价格：</label><span><ww:property value="#request.currentPrice"/></span>
    	</div>
    	</div>
    
    	<div id="top">
	    	<input type="button" value="确认" style="width: 120px; height:24px;" onclick="history.back();">
    	</div>
  	</form>

  </body>
</html>
