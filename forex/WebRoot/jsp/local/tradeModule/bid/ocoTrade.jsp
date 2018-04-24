<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib uri="webwork" prefix="ww"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script language="javascript" type="text/javascript"
	src="resources/calendar/js/My97DatePicker/WdatePicker.js"></script>
	<script src="resources/ponyjs/jquery.js" type="text/javascript"></script>
	<script type="text/javascript" src="jsp/local/tradeModule/bid/js/bidtrade.js"></script>
	<script type="text/javascript" language="javascript">
		$(function(){
			$('input:radio').click(function () {
					this.blur();   
					this.focus(); 
			});
			if('<ww:property value="#request.tradeDirection"/>' == 0){
				document.getElementById("tradeDirect").innerHTML = "I Sell ";
				document.getElementById("tradeDirSpan1").innerHTML = "I Sell ";
				document.getElementById("tradeDirSpan2").innerHTML = "I Sell ";
				document.getElementById("priceLabel").innerHTML = "<ww:property value='#request.bidPrice' />";
			}
			else{
				document.getElementById("tradeDirect").innerHTML = "I Buy ";
				document.getElementById("tradeDirSpan1").innerHTML = "I Buy ";
				document.getElementById("tradeDirSpan2").innerHTML = "I Buy ";
				document.getElementById("priceLabel").innerHTML = "<ww:property value='#request.askPrice' />";
			}
		});
		function selectTradeType(){
			var temp = document.getElementsByName("tradeType");
			for (var i=0;i<temp.length;i++){
				if (temp[i].checked)
					var value = temp[i].value;
			}
			var trade = document.getElementById("tradeDirection").value;
			switch(value){
				case "0":
					window.location.href = "stopLossTradePageInit.action?direct="+trade+"&ccy1="+'<ww:property value="#request.ccy1"/>'+"&ccy2="+'<ww:property value="#request.ccy2"/>'+"&bid="+'<ww:property value="#request.bidPrice"/>'+"&ask="+'<ww:property value="#request.askPrice"/>';
					break;
				case "1":
					window.location.href = "takeProfitTradePageInit.action?direct="+trade+"&ccy1="+'<ww:property value="#request.ccy1"/>'+"&ccy2="+'<ww:property value="#request.ccy2"/>'+"&bid="+'<ww:property value="#request.bidPrice"/>'+"&ask="+'<ww:property value="#request.askPrice"/>';
					break;
				case "2":
					window.location.href = "ocoTradePageInit.action?direct="+trade+"&ccy1="+'<ww:property value="#request.ccy1"/>'+"&ccy2="+'<ww:property value="#request.ccy2"/>'+"&bid="+'<ww:property value="#request.bidPrice"/>'+"&ask="+'<ww:property value="#request.askPrice"/>';
					break;
				case "3":
					window.location.href = "marketBreakoutTradePageInit.action?direct="+trade+"&ccy1="+'<ww:property value="#request.ccy1"/>'+"&ccy2="+'<ww:property value="#request.ccy2"/>'+"&bid="+'<ww:property value="#request.bidPrice"/>'+"&ask="+'<ww:property value="#request.askPrice"/>';
					break;
			}
		}
		function changeTrade(){
			var trade = document.getElementById("tradeDirect").innerHTML;
			if (trade == "I Buy "){
				document.getElementById("tradeDirect").innerHTML = "I Sell ";
				document.getElementById("tradeDirSpan1").innerHTML = "I Sell ";
				document.getElementById("tradeDirSpan2").innerHTML = "I Sell ";
				document.getElementById("tradeDirection").value = "0";
				document.getElementById("priceLabel").innerHTML = "<ww:property value='#request.bidPrice' />";
			}
			else{
				document.getElementById("tradeDirect").innerHTML = "I Buy ";
				document.getElementById("tradeDirSpan1").innerHTML = "I Buy ";
				document.getElementById("tradeDirSpan2").innerHTML = "I Buy ";
				document.getElementById("tradeDirection").value = "1";
				document.getElementById("priceLabel").innerHTML = "<ww:property value='#request.askPrice' />";
			}
		}
	</script>
	<style type="text/css">
		#top{margin: 0; padding:3px 5px; height: 24px; width:790px; line-height: 24px; background-image: url("images/b.png"); color: white; text-align: center; margin-top: 5px;}
		#container{margin: 0;padding: 0; height: 345px; width: 800px !important; width:790px; background-color:#E5F2F8; margin-top: 5px;}
		#wrapOne{margin: 0;padding: 20px 0 0 0; height: 70px; width: 800px !important; width:790px;  margin-bottom: 3px; text-align: center;}
		#wrapTwo{margin: 0;padding: 0; height: 120px; width: 720px !important; width:710px;  margin-bottom: 3px; padding-top: 20px; padding-left: 80px;}
		#wrapThree{margin: 0;padding: 0; height: 100px; width: 800px !important; width:790px; margin-bottom: 3px; }
		label { font-size: 36px; font-weight: bolder; color: red;}
		.part{margin: 0;padding: 10px 0 0 80px; height: 90px; width:318px;  float: left; margin-right: 2px;}
		.button1{width: 120px; height: 36px;vertical-align:text-bottom; margin: 0 20px 0 20px;}
		.button2{width: 120px; height:24px;}
		span{font-weight: bolder; font-size: 18px; margin-right: 20px;}
	</style>
  </head>
  
  <body>
  <form action="" method="post" name="ocoForm">
    <div id="top">
  		<input type="radio" name="tradeType" onchange="selectTradeType()" value="0" <ww:if test="#request.radioValue == 0">checked</ww:if>>Stop Loss
  		<input type="radio" name="tradeType" style="margin-left: 25px;" onchange="selectTradeType()" value="1" <ww:if test="#request.radioValue == 1">checked</ww:if>>Take Profit
  		<input type="radio" name="tradeType" style="margin-left: 25px;" onchange="selectTradeType()" value="2" <ww:if test="#request.radioValue == 2">checked</ww:if>>OCO
  		<input type="radio" name="tradeType" style="margin-left: 25px;" onchange="selectTradeType()" value="3" <ww:if test="#request.radioValue == 3">checked</ww:if>>Market Breakout
  	</div>
  	<div id="container">
  		<div id="wrapOne">
  			<label id="tradeDirect"></label> <ww:property value="#request.ccy1"/> vs <ww:property value="#request.ccy2"/>
  			<input type="button" value="FLIP" class="button1" onclick="changeTrade()">
  			<label>Price:</label><label id="priceLabel"></label>
  		</div>
  		<div id="wrapTwo">
  			<span style="font-weight: bolder; font-size: 18px;" id="tradeDirSpan1"></span><ww:property value="#request.ccy1"/>
  			Amount:<input type="text" size="10" name="ocoModel.tAmount" id="tAmount" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">&nbsp;&nbsp;
  			Price:<input type="text" size="10" name="ocoModel.tPrice" id="tPrice">&nbsp;&nbsp;
  			<span>Take Profit</span>
  			<br><br>
  			<span style="font-weight: bolder; font-size: 18px;" id="tradeDirSpan2"></span><ww:property value="#request.ccy1"/>
  			Amount:<input type="text" size="10" name="ocoModel.sAmount" id="sAmount" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">&nbsp;&nbsp;
  			Price:<input type="text" size="10" name="ocoModel.sPrice" id="sPrice">&nbsp;&nbsp;
  			<span>Stop Loss</span>
  			<br><br>
  			Monitor Type:
  			<select name="ocoModel.monitorPrice">
  				<option value="BID">MONITOR BID PRICE</option>
  				<option value="ASK">MONITOR ASK PRICE</option>
  			</select>
  		</div>
  		<div id="wrapThree">
  			<div class="part">
  				<span style="font-weight: bolder; font-size: 18px;">GOOD FROM</span><br>
  				<input type="radio" name="timeStart" id="rOne" checked="checked" onclick="return checkOne();" style="margin-left: 30px;">ACTIVATION<br>
  				<input type="radio" name="timeStart" id="rTwo" onclick="return checkOne();" dataType="date" size="10" style="margin-left: 30px;"/>
  				Time: <img onClick="WdatePicker({el:'d1'})" style="display: none;" src="resources/calendar/image/Button.gif" id="imgid1"align="absMiddle" border="0" />
  				<input id="d1" style="display: none;" type="text" dataType="date" size="10" name="ocoModel.activeTime" />
  			</div>
  			<div class="part">
  				<span style="font-weight: bolder; font-size: 18px;">GOOD TILL</span><br>
  				<input type="radio" name="timeEnd" id="rThree" checked="checked" onclick="return checkTwo();" style="margin-left: 30px;">CANCELLED<br>
  				<input type="radio" name="timeEnd" id="rFour" onclick="return checkTwo();" dataType="date" size="10" style="margin-left: 30px;" />
  				Time: <img onClick="WdatePicker({el:'d2'})" style="display: none;" src="resources/calendar/image/Button.gif" id="imgid2"align="absMiddle" border="0" />
  				<input id="d2" style="display: none;" type="text" dataType="date" size="10" name="ocoModel.cancelTime" />
  			</div>
  		</div>
  	</div>
  	<div id="top">
  		<input type="button" value="ACTIVE" class="button2" style="margin-right: 100px;" onclick="ocoTradeToAction()">
  		<input type="button" value="DELETE" class="button2" onclick="selectBackAction()">
  		<input type="hidden" id="tradeDirection" name="ocoModel.tradeDirection" value="<ww:property value='#request.tradeDirection'/>">
  		<input type="hidden" name="ocoModel.ccy1" value="<ww:property value='#request.ccy1'/>">
  		<input type="hidden" name="ocoModel.ccy2" value="<ww:property value='#request.ccy2'/>" id="ccyHidden">
  	</div>
  </form>

  </body>
</html>
