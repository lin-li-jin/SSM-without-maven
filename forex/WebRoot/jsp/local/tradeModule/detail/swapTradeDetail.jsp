<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib prefix="ww" uri="webwork"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>Swap���׵�</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="resources/ponyjs/jquery.js" type="text/javascript"></script>
	<style type="text/css">
		#top{margin: 0; padding:3px 5px; height: 24px; width:820px; line-height: 24px; 
		background-image: url("images/b.png"); color: white; text-align: center; float: left;}
		
		#container{margin: 0;padding: 0; height: 500px; width: 830px !important; width:820px; background-color: #E5F2F8; 
		margin-top: 5px;  margin-bottom: 5px;  float: left;}
		
		.modules{margin: 0px; padding:0px 0px 0px 50px !important; width: 205px !important; 
		padding:0px 50px; width: 255px; height: 250px;  margin-right: 10px; float: left;}
		
		.modules2{margin: 0px; padding:0px 0px 0px 20px !important; width: 215px !important; 
		padding:0px 20px; width: 265px; height: 300px;  margin-right: 10px; float: left;  }
		
		.modules1left{margin:0 ;padding: 5px 0 0 40px; height: 175px; width: 350px; float:left;
		 border-top:1px solid black;}
		 .modules1right{margin: 0;padding: 5px 0 0 40px; height: 175px; width: 350px;  float:left;
		 border-top:1px solid black;}
		 .modules1{margin: 0;padding: 0;; height: 178px; width: 830px !important; width:820px; 
		margin-top: 2px; line-height: 20px;}
	</style> 
	
	<script type="text/javascript">
		$(document).ready(function(){
			var fixedType = '<ww:property value="#request.tradeCard.fixedType"/>';
			if(fixedType == 0){//I Receive fixedRate, pay floatingRate
				document.getElementById("fixedType").value = "Receive";
				document.getElementById("floatingType").value = "Pay";
				document.getElementById("CCY1").value = '<ww:property value="#request.tradeCard.weCcy"/>';
				document.getElementById("CCY2").value = '<ww:property value="#request.tradeCard.anaCcy"/>';
				document.getElementById("fixedRate").value = '<ww:property value="#request.tradeCard.receiveRate"/>';
			}
			else{
				document.getElementById("fixedType").value = "Pay";
				document.getElementById("floatingType").value = "Receive";
				document.getElementById("CCY2").value = '<ww:property value="#request.tradeCard.weCcy"/>';
				document.getElementById("CCY1").value = '<ww:property value="#request.tradeCard.anaCcy"/>';
				document.getElementById("fixedRate").value = '<ww:property value="#request.tradeCard.payRate"/>';
			}
			var frequency = '<ww:property value="#request.tradeCard.frequency"/>';
			switch(frequency){
			case "0":
				document.getElementById("frequency").value = "��ҹ";
				break;
			case "1":
				document.getElementById("frequency").value = "һ��";
				break;
			case "2":
				document.getElementById("frequency").value = "һ����";
				break;
			case "3":
				document.getElementById("frequency").value = "������";
				break;
			case "4":
				document.getElementById("frequency").value = "һ����";
				break;
			case "5":
				document.getElementById("frequency").value = "����";
				break;
			case "6":
				document.getElementById("frequency").value = "һ��";
				break;
			}
			var libor = '<ww:property value="#request.tradeCard.libor"/>';
			switch(libor){
			case "0":
				document.getElementById("libor").value = "��ҹ";
				break;
			case "1":
				document.getElementById("libor").value = "1��";
				break;
			case "2":
				document.getElementById("libor").value = "1����";
				break;
			case "3":
				document.getElementById("libor").value = "2����";
				break;
			case "4":
				document.getElementById("libor").value = "3����";
				break;
			case "5":
				document.getElementById("libor").value = "6����";
				break;
			case "6":
				document.getElementById("libor").value = "12����";
				break;
			}
		});
		$(function(){
			if ('<ww:property value="#request.tradeCard.tradeDirection"/>' == 0){
				document.getElementById("ccy").innerHTML = '<ww:property value="#request.tradeCard.weCcy"/>' + "/" + '<ww:property value="#request.tradeCard.anaCcy"/>';
				document.getElementById("label1").innerHTML = '<ww:property value="#request.tradeCard.weCcy"/>' + "���׽�";
				document.getElementById("label2").innerHTML = '<ww:property value="#request.tradeCard.anaCcy"/>' + "���׽�";
			}
			else{
				document.getElementById("ccy").innerHTML = '<ww:property value="#request.tradeCard.anaCcy"/>' + "/" + '<ww:property value="#request.tradeCard.weCcy"/>';
				document.getElementById("label1").innerHTML = '<ww:property value="#request.tradeCard.anaCcy"/>' + "���׽�";
				document.getElementById("label2").innerHTML = '<ww:property value="#request.tradeCard.weCcy"/>' + "���׽�";
			}
		});
	
	</script>
  </head>
  
  <body>
  	<div id="top">ѯ�۽��׵�</div>
  	<form action="">
  		<div id="container">
  			<div id="">
		    	<div class="modules2">
		    		<br><br>
		    		<label>������ţ�</label><span><ww:property value="#request.tradeCard.tradeNo"/></span><br><br><br>
		    		<label>�ύʱ�䣺</label><span><ww:property value="#request.tradeCard.realCreateDate"/></span><br><br><br>
		    		<label>Start Date��</label><span><ww:property value="#request.tradeCard.startedDate"/></span><br><br><br>
		    		<label>���׼۸�</label><span><ww:property value="#request.tradeCard.finalPrice"/></span><br><br><br>
	    			<label>ӯ���</label><span><ww:property value="#request.tradeCard.proAndLoss"/></span>
		    	</div>
		    	<div class="modules2">
		    	    <br><br>
		    		<label>�������ͣ�</label><span><ww:property value="#request.tradeCard.tradeType"/></span><br><br><br>
		    		<label>���ַ�������</label><span><ww:property value="#request.tradeCard.anaInstitution"/></span><br><br><br>
		    		<label>Maturity Date��</label><span><ww:property value="#request.tradeCard.maturityDate"/></span><br><br><br>
    				<label>ʵʱ�۸�</label><span><ww:property value="#request.tradeCard.currentPrice"/></span><br><br><br>
		    		<label>ӯ���ʣ�</label><span><ww:property value="#request.tradeCard.proAndLossRate"/> %</span>
		    	</div>
		    	<div class="modules">
		    	    <br><br>
		    		<label>���׷���</label><span><ww:property value="#request.tradeCard.realDirection"/></span><br><br><br>
		    		<label>����������</label><span><ww:property value="#request.tradeCard.weInstitution"/></span><br><br><br>
		    		<label>����״̬��</label><span><ww:property value="#request.tradeCard.realStatus"/></span>
		    	</div>
	    	</div>
	    	<div class="modules1">
	  				<div class="modules1left">
	  					<span style="color:red;">1st Leg</span><br>
	  					<span>I </span>
	  					<input id="fixedType" type="text" disabled="disabled" size="7">
	  					<input id="CCY1" type="text" disabled="disabled" size="5"/>
	  					<span style="font-size: 18px; font-weight: bolder;">@</span>
	  					<input id="fixedRate" size="10" disabled="disabled"/>
	  					<span style="font-size: 18px; font-weight: bolder;">%</span>
	  					<br><br>
	  					<span>Amount:</span>
	  					<input disabled="disabled" type="text" size="15" value='<ww:property value="#request.tradeCard.cAmount"/>'>
	  					<br><br>
	  					
	  					<span>Basis:</span>
	  					<input disabled="disabled" type="text" size="15" value='<ww:property value="#request.tradeCard.cBasis"/>'>
	  					<br><br>
	  					
	  					<span>Payment Frequency:</span>
	  					<input id="frequency" disabled="disabled" type="text" size="6">
	  					
	  					<br><br>
	  				</div>
	  				<div class="modules1right">
	  					<span style="color:red;">2nd Leg</span><br>
	  					<span>I </span>
	  					<input id="floatingType" type="text" disabled="disabled" size="7">
	  					<input id="CCY2" type="text" disabled="disabled" size="5"/>
	  					<span style="font-size: 18px; font-weight: bolder;">@</span>
	  					<input id="floatingRate" size="10" disabled="disabled"  value="Floating"/>
	  					<span style="font-size: 18px; font-weight: bolder;">%</span>
	  					<br><br>
	  					<span>Amount:</span>
	  					<input disabled="disabled" type="text" size="15" value='<ww:property value="#request.tradeCard.fAmount"/>'>
	  					<br><br>
	  					
	  					<span>Libor:</span>
	  					<input id="libor" disabled="disabled" type="text" size="6">
	  					<br><br>
	  					
	  					<span>Basis:</span>
	  					<input disabled="disabled" type="text" size="15" value='<ww:property value="#request.tradeCard.fBasis"/>'>
	  					<br><br>
	  				</div>
	  		</div>
    		<div id="top"><input type="button" value="ȷ��" style="width: 120px; height:24px;" onclick="history.back();"></div>
    	</div>
    </form>
  </body>
</html>
