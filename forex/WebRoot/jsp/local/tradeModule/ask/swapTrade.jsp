<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib uri="webwork" prefix="ww"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>swap掉期询价交易页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script language="javascript" type="text/javascript"
	src="resources/calendar/js/My97DatePicker/WdatePicker.js"></script>
	<script src="resources/ponyjs/jquery.js" type="text/javascript"></script>
	<script src="/forex/jsp/local/tradeModule/ask/js/askTrade.js" type="text/javascript"></script>
	<style type="text/css">
		#all{margin: 0;padding: 0; height: auto; width: 900px;}
		#top{margin: 0; padding:3px 5px; height: 24px; width:890px; line-height: 24px; 
		background-image: url("images/b.png"); color: white; text-align: center; }
		#container{margin: 0;padding: 0; height: auto; width: 900px !important; width:890px; background-color: #E5F2F8;
		margin-top: 5px; margin-bottom: 5px;}
		#wrapOne{margin: 0;padding: 0; height: 50px; width: 900px !important; width:890px;  margin-bottom: 3px; 
		line-height: 50px; text-align: center;}
		label { font-size: 24px; font-weight: bolder; color: red;} 
		.modules{margin: 0;padding: 10px 0px 10px 40px;; height: 20px; width: 860px !important; width:850px; 
		margin-top: 2px; line-height: 20px; }
		.modules1{margin: 0;padding: 0;; height: 178px; width: 900px !important; width:890px; 
		margin-top: 2px; line-height: 20px; border-bottom: 1px solid black; border-top:1px solid black; }
		.modules1left{margin:0 ;padding: 5px 0 0 40px; height: 175px; width: 410px; float:left;
		 }
		 .modules1right{margin: 0;padding: 5px 0 0 40px; height: 175px; width: 410px;  float:left;
		 }
		.xiegan{font-family:Arial;font-weight:bolder;font-style:normal;text-decoration:none;
		color:#333333;font-size: 18px; }
		.xiegan1{font-family:Arial;font-weight:bolder;font-style:normal;text-decoration:none;color:#333333;font-size: 18px;
		margin: 0px 5px !important; margin: 0px 5px; }
		.provider{margin-left: 30px; margin-right: 160px !important; margin-right: 120px;}
		.button1{width: 80px;}
		.button2{ width: 80px; height: 24px;}
	</style>
	<script type="text/javascript" language="javascript">
	function showOpponentOne(){
		var direction = '<ww:property value="#request.tradeDirection"/>';
		if(document.getElementById("opponentOne").value == ''){
			if(direction == 1){//如果是买，则bid部分被禁用，并且price被清空
				if(document.getElementById("bid_one").disabled == false){
					document.getElementById("bid_one").value = '';
					document.getElementById("bid_one").disabled = true;
					document.getElementById("btn_bid_one").disabled = true;
					document.getElementById("btn_bid_two").disabled = true;
				}
			}
			else{//如果是买，则ask部分被禁用，并且price被清空
				if(document.getElementById("ask_one").disabled == false){
					document.getElementById("ask_one").value = '';
					document.getElementById("ask_one").disabled = true;
					document.getElementById("btn_ask_one").disabled = true;
					document.getElementById("btn_ask_two").disabled = true;
				}
			}
			if(document.getElementById("sendOne").disabled == false){
				document.getElementById("sendOne").disabled = true;
			}
			alert("请选择对方手！");
		}
		else{
			if(direction == 1){//如果是买，则bid部分被激活
				document.getElementById("bid_one").value=document.getElementById("point").value;
				document.getElementById("bid_one").disabled=false;
				document.getElementById("btn_bid_one").disabled=false;
				document.getElementById("btn_bid_two").disabled=false;
			}
			else{//如果是卖，则ask部分被激活
				document.getElementById("ask_one").disabled=false;
				document.getElementById("ask_one").value=document.getElementById("point").value;
				document.getElementById("btn_ask_one").disabled=false;
				document.getElementById("btn_ask_two").disabled=false;
			}
			//send按钮被激活
			document.getElementById("sendOne").disabled = false;
		}
	}
	function showOpponentTwo(){
		var direction = '<ww:property value="#request.tradeDirection"/>';
		if(document.getElementById("opponentTwo").value == ''){
			if(direction == 1){//如果是买，则bid部分被禁用，并且price被清空
				if(document.getElementById("bid_two").disabled == false){
					document.getElementById("bid_two").value = '';
					document.getElementById("bid_two").disabled = true;
					document.getElementById("btn_bid_three").disabled = true;
					document.getElementById("btn_bid_four").disabled = true;
				}
			}
			else{//如果是买，则ask部分被禁用，并且price被清空
				if(document.getElementById("ask_two").disabled == false){
					document.getElementById("ask_two").value = '';
					document.getElementById("ask_two").disabled = true;
					document.getElementById("btn_ask_three").disabled = true;
					document.getElementById("btn_ask_four").disabled = true;
				}
			}
			if(document.getElementById("sendTwo").disabled == false){
				document.getElementById("sendTwo").disabled = true;
			}
			alert("请选择对方手！");
		}
		else{
			if(direction == 1){//如果是买，则bid部分被激活
				document.getElementById("bid_two").value=document.getElementById("point").value;
				document.getElementById("bid_two").disabled=false;
				document.getElementById("btn_bid_three").disabled=false;
				document.getElementById("btn_bid_four").disabled=false;
			}
			else{//如果是卖，则ask部分被激活
				document.getElementById("ask_two").disabled=false;
				document.getElementById("ask_two").value=document.getElementById("point").value;
				document.getElementById("btn_ask_three").disabled=false;
				document.getElementById("btn_ask_four").disabled=false;
			}
			//send按钮被激活
			document.getElementById("sendTwo").disabled = false;
		}
	}
	function showOpponentThree(){
		var direction = '<ww:property value="#request.tradeDirection"/>';
		if(document.getElementById("opponentThree").value == ''){
			if(direction == 1){//如果是买，则bid部分被禁用，并且price被清空
				if(document.getElementById("bid_three").disabled == false){
					document.getElementById("bid_three").value = '';
					document.getElementById("bid_three").disabled = true;
					document.getElementById("btn_bid_five").disabled = true;
					document.getElementById("btn_bid_six").disabled = true;
				}
			}
			else{//如果是买，则ask部分被禁用，并且price被清空
				if(document.getElementById("ask_three").disabled == false){
					document.getElementById("ask_three").value = '';
					document.getElementById("ask_three").disabled = true;
					document.getElementById("btn_ask_five").disabled = true;
					document.getElementById("btn_ask_six").disabled = true;
				}
			}
			if(document.getElementById("sendThree").disabled == false){
				document.getElementById("sendThree").disabled = true;
			}
			alert("请选择对方手！");
		}
		else{
			if(direction == 1){//如果是买，则bid部分被激活
				document.getElementById("bid_three").value=document.getElementById("point").value;
				document.getElementById("bid_three").disabled=false;
				document.getElementById("btn_bid_five").disabled=false;
				document.getElementById("btn_bid_six").disabled=false;
			}
			else{//如果是卖，则ask部分被激活
				document.getElementById("ask_three").disabled=false;
				document.getElementById("ask_three").value=document.getElementById("point").value;
				document.getElementById("btn_ask_five").disabled=false;
				document.getElementById("btn_ask_six").disabled=false;
			}
			//send按钮被激活
			document.getElementById("sendThree").disabled = false;
		}
	}
	function showOpponentFour(){
		var direction = '<ww:property value="#request.tradeDirection"/>';
		if(document.getElementById("opponentFour").value == ''){
			if(direction == 1){//如果是买，则bid部分被禁用，并且price被清空
				if(document.getElementById("bid_four").disabled == false){
					document.getElementById("bid_four").value = '';
					document.getElementById("bid_four").disabled = true;
					document.getElementById("btn_bid_seven").disabled = true;
					document.getElementById("btn_bid_eight").disabled = true;
				}
			}
			else{//如果是买，则ask部分被禁用，并且price被清空
				if(document.getElementById("ask_four").disabled == false){
					document.getElementById("ask_four").value = '';
					document.getElementById("ask_four").disabled = true;
					document.getElementById("btn_ask_seven").disabled = true;
					document.getElementById("btn_ask_eight").disabled = true;
				}
			}
			if(document.getElementById("sendFour").disabled == false){
				document.getElementById("sendFour").disabled = true;
			}
			alert("请选择对方手！");
		}
		else{
			if(direction == 1){//如果是买，则bid部分被激活
				document.getElementById("bid_four").value=document.getElementById("point").value;
				document.getElementById("bid_four").disabled=false;
				document.getElementById("btn_bid_seven").disabled=false;
				document.getElementById("btn_bid_eight").disabled=false;
			}
			else{//如果是卖，则ask部分被激活
				document.getElementById("ask_four").disabled=false;
				document.getElementById("ask_four").value=document.getElementById("point").value;
				document.getElementById("btn_ask_seven").disabled=false;
				document.getElementById("btn_ask_eight").disabled=false;
			}
			//send按钮被激活
			document.getElementById("sendFour").disabled = false;
		}
	}
	function showOpponentFive(){
		var direction = '<ww:property value="#request.tradeDirection"/>';
		if(document.getElementById("opponentFive").value == ''){
			if(direction == 1){//如果是买，则bid部分被禁用，并且price被清空
				if(document.getElementById("bid_five").disabled == false){
					document.getElementById("bid_five").value = '';
					document.getElementById("bid_five").disabled = true;
					document.getElementById("btn_bid_nine").disabled = true;
					document.getElementById("btn_bid_ten").disabled = true;
				}
			}
			else{//如果是买，则ask部分被禁用，并且price被清空
				if(document.getElementById("ask_five").disabled == false){
					document.getElementById("ask_five").value = '';
					document.getElementById("ask_five").disabled = true;
					document.getElementById("btn_ask_nine").disabled = true;
					document.getElementById("btn_ask_ten").disabled = true;
				}
			}
			if(document.getElementById("sendFive").disabled == false){
				document.getElementById("sendFive").disabled = true;
			}
			alert("请选择对方手！");
		}
		else{
			if(direction == 1){//如果是买，则bid部分被激活
				document.getElementById("bid_five").value=document.getElementById("point").value;
				document.getElementById("bid_five").disabled=false;
				document.getElementById("btn_bid_nine").disabled=false;
				document.getElementById("btn_bid_ten").disabled=false;
			}
			else{//如果是卖，则ask部分被激活
				document.getElementById("ask_five").disabled=false;
				document.getElementById("ask_five").value=document.getElementById("point").value;
				document.getElementById("btn_ask_nine").disabled=false;
				document.getElementById("btn_ask_ten").disabled=false;
			}
			//send按钮被激活
			document.getElementById("sendFive").disabled = false;
		}
	}
	function validateByOpponentOne(){
		var direction = '<ww:property value="#request.tradeDirection"/>';
		var point = 0;
		if(direction == 1){
			point = document.getElementById("bid_one").value;
		}
		else{
			point = document.getElementById("ask_one").value;
		}
		var opponent = document.getElementById("opponentOne").value;
		if (opponent == ''){
			alert("请输入第一个对手方");
			return true;
		}
		if(opponent != ''){
			if (point == '' || isNaN(point)){
				alert("请输入数字格式的基本点！");
				return true;
			}
			else{
				if(point.toString().indexOf(".") > -1){
					alert("基本点不能是小数！");
					return true;
				}
			}
		}
		return false;
	}
	function validateByOpponentTwo(){
		var direction = '<ww:property value="#request.tradeDirection"/>';
		var point = 0;
		if(direction == 1){
			point = document.getElementById("bid_two").value;
		}
		else{
			point = document.getElementById("ask_two").value;
		}
		var opponent = document.getElementById("opponentTwo").value;
		if (opponent == ''){
			alert("请输入第二个对手方");
			return true;
		}
		if(opponent != ''){
			if (point == '' || isNaN(point)){
				alert("请输入数字格式的基本点！");
				return true;
			}
			else{
				if(point.toString().indexOf(".") > -1){
					alert("基本点不能是小数！");
					return true;
				}
			}
		}
		return false;
	}
	function validateByOpponentThree(){
		var direction = '<ww:property value="#request.tradeDirection"/>';
		var point = 0;
		if(direction == 1){
			point = document.getElementById("bid_three").value;
		}
		else{
			point = document.getElementById("ask_three").value;
		}
		var opponent = document.getElementById("opponentThree").value;
		if (opponent == ''){
			alert("请输入第三个对手方");
			return true;
		}
		if(opponent != ''){
			if (point == '' || isNaN(point)){
				alert("请输入数字格式的基本点！");
				return true;
			}
			else{
				if(point.toString().indexOf(".") > -1){
					alert("基本点不能是小数！");
					return true;
				}
			}
		}
		return false;
	}
	function validateByOpponentFour(){
		var direction = '<ww:property value="#request.tradeDirection"/>';
		var point = 0;
		if(direction == 1){
			point = document.getElementById("bid_four").value;
		}
		else{
			point = document.getElementById("ask_four").value;
		}
		var opponent = document.getElementById("opponentFour").value;
		if (opponent == ''){
			alert("请输入第四个对手方");
			return true;
		}
		if(opponent != ''){
			if (point == '' || isNaN(point)){
				alert("请输入数字格式的基本点！");
				return true;
			}
			else{
				if(point.toString().indexOf(".") > -1){
					alert("基本点不能是小数！");
					return true;
				}
			}
		}
		return false;
	}
	function validateByOpponentFive(){
		var direction = '<ww:property value="#request.tradeDirection"/>';
		var point = 0;
		if(direction == 1){
			point = document.getElementById("bid_five").value;
		}
		else{
			point = document.getElementById("ask_five").value;
		}
		var opponent = document.getElementById("opponentFive").value;
		if (opponent == ''){
			alert("请输入第五个对手方");
			return true;
		}
		if(opponent != ''){
			if (point == '' || isNaN(point)){
				alert("请输入数字格式的基本点！");
				return true;
			}
			else{
				if(point.toString().indexOf(".") > -1){
					alert("基本点不能是小数！");
					return true;
				}
			}
		}
		return false;
	}
	function selectTradeType(){
		var temp = document.getElementsByName("tradeType");
		var value = "0";
		for (var i = 0; i < temp.length; i++){
			if (temp[i].checked)
				value = temp[i].value;
		}
		switch(value){
			case "0":
				window.location.href = "spotTradePageInit.action?direct="+'<ww:property value="#request.tradeDirection"/>'+"&ccy1="+'<ww:property value="#request.ccy1"/>'+"&ccy2="+'<ww:property value="#request.ccy2"/>'+"&price="+'<ww:property value="#request.price"/>';
				break;
			case "1":
				window.location.href = "forwardTradePageInit.action?direct="+'<ww:property value="#request.tradeDirection"/>'+"&ccy1="+'<ww:property value="#request.ccy1"/>'+"&ccy2="+'<ww:property value="#request.ccy2"/>'+"&price="+'<ww:property value="#request.price"/>';
				break;
			case "2":
				window.location.href = "swapTradePageInit.action?direct="+'<ww:property value="#request.tradeDirection"/>'+"&ccy1="+'<ww:property value="#request.ccy1"/>'+"&ccy2="+'<ww:property value="#request.ccy2"/>'+"&price="+'<ww:property value="#request.price"/>';
				break;
		}
	}
	function withdrawBySwap(){
		var tranNo1 = document.getElementById("tranNo1").value;
		var tranNo2 = document.getElementById("tranNo2").value;
		var tranNo3 = document.getElementById("tranNo3").value;
		var tranNo4 = document.getElementById("tranNo4").value;
		var tranNo5 = document.getElementById("tranNo5").value;
		$.post("swapTradeWithdraw.action",{tranNo1:tranNo1,tranNo2:tranNo2,tranNo3:tranNo3,tranNo4:tranNo4,tranNo5:tranNo5},
		function(dat){
			window.close();
		});
	}
	$(function(){
		$(".opponents").each(function(i){
			$(this).attr("xh",i);
		});
		$(".opponents").change(function(i){
		    var val = $(this).val();
		    var object = $(this);
		    var flag = false;
		    $(".opponents[xh!="+$(this).attr("xh")+"]").each(function(){
		    	 
		         if(val == $(this).val() && $(this).val() != "")
		         {
		              alert("该值已经存在！请重新选择");
		              object.val("");
		              flag = true;
		         }
		    });
		    if(flag){
		    	//alert(this.id);
		    	banned(this.id);
		    }
		});
	});
	
	function banned(objId){
		var direction = '<ww:property value="#request.tradeDirection"/>';
		if(objId == "opponentOne"){
			//禁用整一行
			//document.getElementById("opponentOne").disabled = true;
			if(direction == 1){//买
				document.getElementById("bid_one").value = "";
				document.getElementById("bid_one").disabled = true;
				document.getElementById("btn_bid_one").disabled = true;
				document.getElementById("btn_bid_two").disabled = true;
			}
			else{
				document.getElementById("ask_one").value = "";
				document.getElementById("ask_one").disabled = true;
				document.getElementById("btn_ask_one").disabled = true;
				document.getElementById("btn_ask_two").disabled = true;
			}
			document.getElementById("sendOne").disabled = true;
		}
		if(objId == "opponentTwo"){
			//禁用整一行
			//document.getElementById("opponentTwo").disabled = true;
			if(direction == 1){//买
				document.getElementById("bid_two").value = "";
				document.getElementById("bid_two").disabled = true;
				document.getElementById("btn_bid_three").disabled = true;
				document.getElementById("btn_bid_four").disabled = true;
			}
			else{
				document.getElementById("ask_two").value = "";
				document.getElementById("ask_two").disabled = true;
				document.getElementById("btn_ask_three").disabled = true;
				document.getElementById("btn_ask_four").disabled = true;
			}
			document.getElementById("sendTwo").disabled = true;
		}
		if(objId == "opponentThree"){
			//禁用整一行
			//document.getElementById("opponentThree").disabled = true;
			if(direction == 1){//买
				document.getElementById("bid_three").value = "";
				document.getElementById("bid_three").disabled = true;
				document.getElementById("btn_bid_five").disabled = true;
				document.getElementById("btn_bid_six").disabled = true;
			}
			else{
				document.getElementById("ask_three").value = "";
				document.getElementById("ask_three").disabled = true;
				document.getElementById("btn_ask_five").disabled = true;
				document.getElementById("btn_ask_six").disabled = true;
			}
			document.getElementById("sendThree").disabled = true;
		}
		if(objId == "opponentFour"){
			//禁用整一行
			//document.getElementById("opponentFour").disabled = true;
			if(direction == 1){//买
				document.getElementById("bid_four").value = "";
				document.getElementById("bid_four").disabled = true;
				document.getElementById("btn_bid_seven").disabled = true;
				document.getElementById("btn_bid_eight").disabled = true;
			}
			else{
				document.getElementById("ask_four").value = "";
				document.getElementById("ask_four").disabled = true;
				document.getElementById("btn_ask_seven").disabled = true;
				document.getElementById("btn_ask_eight").disabled = true;
			}
			document.getElementById("sendFour").disabled = true;
		}
		if(objId == "opponentFive"){
			//禁用整一行
			//document.getElementById("opponentFive").disabled = true;
			if(direction == 1){//买
				document.getElementById("bid_five").value = "";
				document.getElementById("bid_five").disabled = true;
				document.getElementById("btn_bid_nine").disabled = true;
				document.getElementById("btn_bid_ten").disabled = true;
			}
			else{
				document.getElementById("ask_five").value = "";
				document.getElementById("ask_five").disabled = true;
				document.getElementById("btn_ask_nine").disabled = true;
				document.getElementById("btn_ask_ten").disabled = true;
			}
			document.getElementById("sendFive").disabled = true;
		}
	}
	$(function(){
		$('input:radio').click(function () {
			this.blur();   
			this.focus(); 
		});
		if('<ww:property value="#request.tradeDirection"/>' == 0){
			document.getElementById("direction").innerHTML = "I Sell ";
			document.getElementById("CCY1").value = "<ww:property value="#request.ccy1"/>";
			document.getElementById("CCY2").value = "<ww:property value="#request.ccy2"/>";
		}
		else{
			document.getElementById("direction").innerHTML = "I Buy ";
			document.getElementById("CCY1").value = "<ww:property value="#request.ccy2"/>";
			document.getElementById("CCY2").value = "<ww:property value="#request.ccy1"/>";
		}
	});
	
	function validateByAmount(){
		var amount = document.getElementById("amount").value;
		if (amount == '' || amount%100 != 0){
			alert("请输入100整数倍的交易金额！");
			return true;
		}else{
			return false;
		}
	}
	function valiByAmount(){
		var camount = document.getElementById("cAmount").value;
		var famount = document.getElementById("fAmount").value;
		if (camount == '' || camount%100 != 0){
			alert("请输入100整数倍的近端交易金额！");
			return true;
		}
		else if(famount == '' || famount%100 != 0){
			alert("请输入100整数倍的远端交易金额！");
			return true;
		}
		else{
			return false;
		}
	}
	
	function validateDate() {
		var startTime = document.getElementById("d1").value;
		var endTime = document.getElementById("d2").value;
		var syear = startTime.substring(0, 4);
		var smonth = startTime.substring(5, 7);
		var sday = startTime.substring(8, 10);
		var eyear = endTime.substring(0, 4);
		var emonth = endTime.substring(5, 7);
		var eday = endTime.substring(8, 10);
		var date = getCurrentDate();
		if(startTime == '' || startTime == null){
			alert("起息日期不能为空！");
			return true;
		}
		else{
			if(isNaN(syear) || isNaN(smonth) || isNaN(sday)){
				alert("起息日期格式不正确！");
				return true;
			}
			else{
				if (startTime < date){
					alert("起息日期不能小于当前日期！");
					return true;
				}
				else if(startTime == date){
					alert("起息日期不能是今天！");
					return true;
				}
			}
		}
		if(endTime == '' || endTime == null){
			alert("结息日期不能为空！");
			return true;
		}
		else{
			if(isNaN(eyear) || isNaN(emonth) || isNaN(eday)){
				alert("结息日期格式不正确！");
				return true;
			}
			else{
				if (endTime < date){
					alert("结息日期不能小于当前日期！");
					return true;
				}
				else if(endTime == date){
					alert("结息日期不能是今天！");
					return true;
				}
			}
		}
		if (startTime != '' && endTime != ''){
			if (startTime > endTime) {
				alert("起息日期不能大于结息日期！", this);
				return true;
			} 
		}
		return false;
	}
	function getCurrentDate(){
		var year = new Date().getFullYear();
		var month = new Date().getMonth()+1;
		var day = new Date().getDate();
		if (month < 10)
			month = "0" + month;
		if (day < 10)
			day = "0" + day;
		return year + "-" + month + "-" + day;
	}
	function getLibor(flag){
		var libor = "0";
		var value = document.getElementById("liborSelection").value;
		if(flag == 1){
			switch(value){
			case "0":
				libor = "<ww:property value="#request.liborModel.oneDay"/>";
				break;
			case "1":
				libor = "<ww:property value="#request.liborModel.oneWeek"/>";
				break;
			case "2":
				libor = "<ww:property value="#request.liborModel.oneMonth"/>";
				break;
			case "3":
				libor = "<ww:property value="#request.liborModel.twoMonth"/>";
				break;
			case "4":
				libor = "<ww:property value="#request.liborModel.threeMonth"/>";
				break;
			case "5":
				libor = "<ww:property value="#request.liborModel.sixMonth"/>";
				break;
			case "6":
				libor = "<ww:property value="#request.liborModel.twelveMonth"/>";
				break;
			}
		}
		else if(flag == 2){
			switch(value){
			case "0":
				libor = "<ww:property value="#request.liborModel2.oneDay"/>";
				break;
			case "1":
				libor = "<ww:property value="#request.liborModel2.oneWeek"/>";
				break;
			case "2":
				libor = "<ww:property value="#request.liborModel2.oneMonth"/>";
				break;
			case "3":
				libor = "<ww:property value="#request.liborModel2.twoMonth"/>";
				break;
			case "4":
				libor = "<ww:property value="#request.liborModel2.threeMonth"/>";
				break;
			case "5":
				libor = "<ww:property value="#request.liborModel2.sixMonth"/>";
				break;
			case "6":
				libor = "<ww:property value="#request.liborModel2.twelveMonth"/>";
				break;
			}
		}
		return libor;
	}
	function synchronize(obj){
		if(obj.id == "cAmount"){
			document.getElementById("fAmount").value = obj.value;
		}
		else if(obj.id == "fAmount"){
			document.getElementById("cAmount").value = obj.value;
		}
	}
	function changeDirection(){
		var selection = document.getElementById("directionSelection").value;
	  	var direction = '<ww:property value="#request.tradeDirection"/>';
	  	if(selection == 0){
	  		document.getElementById("secondLeg").value = "Pay";
	  		if(direction == "0"){
	  			document.getElementById("CCY1").value = "<ww:property value="#request.ccy1"/>";
	  			document.getElementById("CCY2").value = "<ww:property value="#request.ccy2"/>";
	  		}
	  		else{
	  			document.getElementById("CCY1").value = "<ww:property value="#request.ccy2"/>";
	  			document.getElementById("CCY2").value = "<ww:property value="#request.ccy1"/>";
	  		}
	  	}
	  	else if(selection == 1){
	  		document.getElementById("secondLeg").value = "Receive";
	  		if(direction == "0"){
	  			document.getElementById("CCY1").value = "<ww:property value="#request.ccy2"/>";
	  			document.getElementById("CCY2").value = "<ww:property value="#request.ccy1"/>";
	  		}
	  		else{
	  			document.getElementById("CCY1").value = "<ww:property value="#request.ccy1"/>";
	  			document.getElementById("CCY2").value = "<ww:property value="#request.ccy2"/>";
	  		}
	  	}
	}
	  	function becomeSpot(){
	  		var checkbox = document.getElementsByName("isSpot");
	  		for(var i = 0; i < checkbox.length; i++){
	  			if(checkbox[0].checked){
	  				document.getElementById("d1").value = getAnotherDay(getCurrentDate());
	  				document.getElementById("d1").disabled = true;
	  			}
	  			else{
	  				document.getElementById("d1").value = "";
	  				document.getElementById("d1").disabled = false;
	  			}
	  		}
			/* if (checkbox.checked){
				document.getElementById("d1").disabled = true;									
			}
			else{
				document.getElementById("d1").disabled = false;
			} */
	  	}
	  	
	  	function getAnotherDay(today){
	  		var year = window.parseInt(today.substring(0,4));
			var month = window.parseInt(today.substring(5,7));
			var day = window.parseInt(today.substring(8,10));
			if(day == getDaysByMonth(month)){
				if(month == 12){
					year++;
					month = 1;
					day = 1;
				}
				else{
					month++;
					day = 1;
				}
			}
			else{
				day++;
			}
			var m = month + "";
			if(month < 10){
				m = "0" + m;
			}
			var d = day + "";
			if(day < 10){
				d = "0" + d;
			}
			return year + "-" + m + "-" + d;
	  	}
	  	function swapBySend(value){
	  		var opponent = '';
			var point = 0;
			var direct = '<ww:property value="#request.tradeDirection"/>';
			var tranNo = '';
			var which = 0;
			if(valiByAmount() || validateDate() || validateBasis() || validateFrequency()){//验证日期、金额、固定利率、basis是否已经填写，并且验证付息周期的正确性
				return false;
			}
	  		if(value == 1){
				if(validateByOpponentOne()){
					return false;
				}
				opponent = document.getElementById("opponentOne").value;
				tranNo = document.getElementById("tranNo1").value;
				which = 1;
				if(direct == 1){
					point = document.getElementById("bid_one").value;
				}
				else{
					point = document.getElementById("ask_one").value;		
				}
			}
			else if(value == 2){
				if(validateByOpponentTwo())//检验对方手是否为空
					return false;
				opponent = document.getElementById("opponentTwo").value;
				tranNo = document.getElementById("tranNo2").value;
				which = 2;
				if(direct == 1){
					point = document.getElementById("bid_two").value;
				}
				else{
					point = document.getElementById("ask_two").value;			
				}
			}	
			else if(value == 3){
				if(validateByOpponentThree())//检验对方手是否为空
					return false;
				opponent = document.getElementById("opponentThree").value;
				tranNo = document.getElementById("tranNo3").value;
				which = 3;
				if(direct == 1){
					point = document.getElementById("bid_three").value;
				}
				else{
					point = document.getElementById("ask_three").value;			
				}
			}
			else if(value == 4){
				if(validateByOpponentFour())//检验对方手是否为空
					return false;
				opponent = document.getElementById("opponentFour").value;
				tranNo = document.getElementById("tranNo4").value;
				which = 4;
				if(direct == 1){
					point = document.getElementById("bid_four").value;
				}
				else{
					point = document.getElementById("ask_four").value;			
				}
			}
			else if(value == 5){
				if(validateByOpponentFive())//检验对方手是否为空
					return false;
				opponent = document.getElementById("opponentFive").value;
				tranNo = document.getElementById("tranNo5").value;
				which = 5;
				if(direct == 1){
					point = document.getElementById("bid_five").value;
				}
				else{
					point = document.getElementById("ask_five").value;
				}
			}
			var startTime = document.getElementById("d1").value;
			var endTime = document.getElementById("d2").value;
			var amount = document.getElementById("cAmount").value;
			var ccy1 = '<ww:property value="#request.ccy1"/>';
			var ccy2 = '<ww:property value="#request.ccy2"/>';
			var price = '<ww:property value="#request.price"/>';
			var basis1 = document.getElementById("basis1").value;
			var basis2 = document.getElementById("basis2").value;
			var fixedType = document.getElementById("directionSelection").value;
			var fixedRate = document.getElementById("fixedRate").value;
			var libor = document.getElementById("liborSelection").value;
			/* var libor = 0;
			var floatingCcy = document.getElementById("CCY2").value;
			if(floatingCcy == ccy1){
				libor = getLibor(1);
			}
			else if(floatingCcy == ccy2){
				libor = getLibor(2);
			} */
			var frequency = document.getElementById("frequency").value;
			/* var frequency;
			switch(fValue){
				case "0":
				frequency = "隔夜";
				break;
				case "1":
				frequency = "一周";
				break;
				case "2":
				frequency = "一个月";
				break;
				case "3":
				frequency = "两个月";
				break;
				case "4":
				frequency = "一季度";
				break;
				case "5":
				frequency = "半年";
				break;
				case "6":
				frequency = "一年";
				break;
			} */
	  		
			$.post("swapTradeSend.action",{tranNo:tranNo,startTime:startTime,endTime:endTime,direct:direct,ccy1:ccy1,ccy2:ccy2,price:price,opponent:opponent,amount:amount,basis1:basis1,basis2:basis2,fixedType:fixedType,fixedRate:fixedRate,libor:libor,frequency:frequency,point:point},
			function(dat){
				if(dat == "fail"){
					alert("余额不足！交易失败！");
				}
				else{
					//禁用一切页面元素
			  		document.getElementById("isSpot").disabled = true;
			  		document.getElementById("d1").disabled = true;
			  		document.getElementById("d2").disabled = true;
			  		document.getElementById("directionSelection").disabled = true;
			  		document.getElementById("fixedRate").disabled = true;
			  		document.getElementById("cAmount").disabled = true;
			  		document.getElementById("fAmount").disabled = true;
			  		document.getElementById("basis1").disabled = true;
			  		document.getElementById("basis2").disabled = true;
			  		document.getElementById("liborSelection").disabled = true;
			  		document.getElementById("frequency").disabled = true;
					alert("与第"+which+"个对手方交易提交成功！");
					switch(which){
					case 1:
						//provider被禁用
						document.getElementById("opponentOne").disabled = true;
						if(direct == 1){
							//bid部分被禁用
							document.getElementById("bid_one").disabled = true;
							document.getElementById("btn_bid_one").disabled = true;
							document.getElementById("btn_bid_two").disabled = true;
						}
						else{
							//ask部分被禁用
							document.getElementById("ask_one").disabled = true;
							document.getElementById("btn_ask_one").disabled = true;
							document.getElementById("btn_ask_two").disabled = true;
						}
						//accept,argue和send按钮被禁用
						document.getElementById("acceptOne").disabled = true;
						document.getElementById("argueOne").disabled = true;
						document.getElementById("sendOne").disabled = true;
						document.getElementById("tranNo1").value = dat;
						timename1 = setInterval("checkPoint1();",5000);
						break;
					case 2:
						//provider被禁用
						document.getElementById("opponentTwo").disabled = true;
						if(direct == 1){
							//bid部分被禁用
							document.getElementById("bid_two").disabled = true;
							document.getElementById("btn_bid_three").disabled = true;
							document.getElementById("btn_bid_four").disabled = true;
						}
						else{
							//ask部分被禁用
							document.getElementById("ask_two").disabled = true;
							document.getElementById("btn_ask_three").disabled = true;
							document.getElementById("btn_ask_four").disabled = true;
						}
						//accept,argue和send按钮被禁用
						document.getElementById("acceptTwo").disabled = true;
						document.getElementById("argueTwo").disabled = true;
						document.getElementById("sendTwo").disabled = true;
						document.getElementById("tranNo2").value = dat;
						timename2 = setInterval("checkPoint2();",5000);
						break;
					case 3:
						//provider被禁用
						document.getElementById("opponentThree").disabled = true;
						if(direct == 1){
							//bid部分被禁用
							document.getElementById("bid_three").disabled = true;
							document.getElementById("btn_bid_five").disabled = true;
							document.getElementById("btn_bid_six").disabled = true;
						}				
						else{
							//ask部分被禁用
							document.getElementById("ask_three").disabled = true;
							document.getElementById("btn_ask_five").disabled = true;
							document.getElementById("btn_ask_six").disabled = true;
						}
						//accept,argue和send按钮被禁用
						document.getElementById("acceptThree").disabled = true;
						document.getElementById("argueThree").disabled = true;
						document.getElementById("sendThree").disabled = true;
						document.getElementById("tranNo3").value = dat;
						timename3 = setInterval("checkPoint3();",5000);
						break;
					case 4:
						//provider被禁用
						document.getElementById("opponentFour").disabled = true;
						if(direct == 1){
							//bid部分被禁用
							document.getElementById("bid_four").disabled = true;
							document.getElementById("btn_bid_seven").disabled = true;
							document.getElementById("btn_bid_eight").disabled = true;
						}			
						else{
							//ask部分被禁用
							document.getElementById("ask_four").disabled = true;
							document.getElementById("btn_ask_seven").disabled = true;
							document.getElementById("btn_ask_eight").disabled = true;
						}
						//accept,argue和send按钮被禁用
						document.getElementById("acceptFour").disabled = true;
						document.getElementById("argueFour").disabled = true;
						document.getElementById("sendFour").disabled = true;
						document.getElementById("tranNo4").value = dat;
						timename4 = setInterval("checkPoint4();",5000);
						break;	
					case 5:
						//provider被禁用
						document.getElementById("opponentFive").disabled = true;
						if(direct == 1){
							//bid部分被禁用
							document.getElementById("bid_five").disabled = true;
							document.getElementById("btn_bid_nine").disabled = true;
							document.getElementById("btn_bid_ten").disabled = true;
						}
						else{
							//ask部分被禁用
							document.getElementById("ask_five").disabled = true;
							document.getElementById("btn_ask_nine").disabled = true;
							document.getElementById("btn_ask_ten").disabled = true;
						}
						//accept,argue和send按钮被禁用
						document.getElementById("acceptFive").disabled = true;
						document.getElementById("argueFive").disabled = true;
						document.getElementById("sendFive").disabled = true;
						document.getElementById("tranNo5").value = dat;
						timename5 = setInterval("checkPoint5();",5000);
						break;
					}
				}
			});
			//修改交易状态
			document.getElementById("status").value = 'PROCESSING';
	  	}
	  	function checkPoint1(){
			var tranNo = document.getElementById("tranNo1").value;
			var opponent = document.getElementById("opponentOne").value;
			var direction = '<ww:property value="#request.tradeDirection"/>';
			if(opponent != null && opponent != ''){
				if(tranNo != null && tranNo != ''){
					$.post("swapTradeCheckPoint.action",{tranNo:tranNo,opponent:opponent},
					function(dat){
						if(dat == "C"){
						//删除交易记录
						$.post("swapTradeDel.action",{tranNo:tranNo,opponent:opponent},
						function(flag){
							if(flag == 1 || flag == "1"){
								alert("第一对手方终止了交易！");
								//停止js定时器
								clearInterval(timename1);
								//中止交易，整行清空
								document.getElementById("opponentOne").disabled = false;
								document.getElementById("opponentOne").options.selectedIndex = 0;
								if(direction == 1){
									document.getElementById("bid_one").value = "";
									document.getElementById("bid_one").disabled = true;
									document.getElementById("btn_bid_one").disabled = true;
									document.getElementById("btn_bid_two").disabled = true;
								}
								else if(direction == 0){
									document.getElementById("ask_one").value = "";
									document.getElementById("ask_one").disabled = true;
									document.getElementById("btn_ask_one").disabled = true;
									document.getElementById("btn_ask_two").disabled = true;
								}
								document.getElementById("acceptOne").disabled = true;
								document.getElementById("argueOne").disabled = true;
								document.getElementById("sendOne").disabled = true;
								//如果用户仍想进行交易，则清空交易编号！
								document.getElementById("tranNo1").value = "";
							}
						});
					}
					else if(dat == "F"){
						//同意价格
						alert("第一对手方同意您的价格了！");
						//停止js定时器
						clearInterval(timename1);
						document.getElementById("acceptOne").disabled = false;
					}
					else{
						var point = dat;
						var oldPoint;
						var flag = 0;
						if(direction == 1){
							oldPoint = document.getElementById("bid_one").value;
							if(point != oldPoint){
								document.getElementById("bid_one").value = point;//修改基本点
								flag = 1;
							}
						}
						else if(direction == 0){
							oldPoint = document.getElementById("ask_one").value;
							if(point != oldPoint){
								document.getElementById("ask_one").value = point;
								flag = 1;
							}
						}
						if(flag == 1){
							//停止js定时器
							clearInterval(timename1);
							//accept和argue按钮被激活
							document.getElementById("acceptOne").disabled = false;
							document.getElementById("argueOne").disabled = false;
						}
					}
					});
				}				
			}
		}
		function checkPoint2(){
			var tranNo = document.getElementById("tranNo2").value;
			var opponent = document.getElementById("opponentTwo").value;
			var direction = '<ww:property value="#request.tradeDirection"/>';
			if(opponent != null && opponent != ''){
				if(tranNo != null && tranNo != ''){
					$.post("swapTradeCheckPoint.action",{tranNo:tranNo,opponent:opponent},
					function(dat){
						if(dat == "C"){
						//删除交易记录
						$.post("swapTradeDel.action",{tranNo:tranNo,opponent:opponent},
						function(flag){
							if(flag == 1 || flag == "1"){
								alert("第二对手方终止了交易！");
								//停止js定时器
								clearInterval(timename2);
								//中止交易，整行清空
								document.getElementById("opponentTwo").disabled = false;
								document.getElementById("opponentTwo").options.selectedIndex = 0;
								if(direction == 1){
									document.getElementById("bid_two").value = "";
									document.getElementById("bid_two").disabled = true;
									document.getElementById("btn_bid_three").disabled = true;
									document.getElementById("btn_bid_four").disabled = true;
								}
								else if(direction == 0){
									document.getElementById("ask_two").value = "";
									document.getElementById("ask_two").disabled = true;
									document.getElementById("btn_ask_three").disabled = true;
									document.getElementById("btn_ask_four").disabled = true;
								}
								document.getElementById("acceptTwo").disabled = true;
								document.getElementById("argueTwo").disabled = true;
								document.getElementById("sendTwo").disabled = true;
								//如果用户仍想进行交易，则清空交易编号！
								document.getElementById("tranNo2").value = "";
							}
						});
					}
					else if(dat == "F"){
						//同意价格
						alert("第二对手方同意您的价格了！");
						//停止js定时器
						clearInterval(timename2);
						document.getElementById("acceptTwo").disabled = false;
					}
					else{
						var point = dat;
						var oldPoint;
						var flag = 0;
						if(direction == 1){
							oldPoint = document.getElementById("bid_two").value;
							if(point != oldPoint){
								document.getElementById("bid_two").value = point;//修改价格
								flag = 1;
							}
						}
						else if(direction == 0){
							oldPoint = document.getElementById("ask_two").value;
							if(point != oldPoint){
								document.getElementById("ask_two").value = point;
								flag = 1;
							}
						}
						if(flag == 1){
							//停止js定时器
							clearInterval(timename2);
							//accept和argue按钮被激活
							document.getElementById("acceptTwo").disabled = false;
							document.getElementById("argueTwo").disabled = false;
						}
					}
					});
				}				
			}	
		}
		function checkPoint3(){
			var tranNo = document.getElementById("tranNo3").value;
			var opponent = document.getElementById("opponentThree").value;
			var direction = '<ww:property value="#request.tradeDirection"/>';
			if(opponent != null && opponent != ''){
				if(tranNo != null && tranNo != ''){
					$.post("swapTradeCheckPoint.action",{tranNo:tranNo,opponent:opponent},
					function(dat){
						if(dat == "C"){
						//删除交易记录
						$.post("swapTradeDel.action",{tranNo:tranNo,opponent:opponent},
						function(flag){
							if(flag == 1 || flag == "1"){
								alert("第三对手方终止了交易！");
								//停止js定时器
								clearInterval(timename3);
								//中止交易，整行清空
								document.getElementById("opponentThree").disabled = false;
								document.getElementById("opponentThree").options.selectedIndex = 0;
								if(direction == 1){
									document.getElementById("bid_three").value = "";
									document.getElementById("bid_three").disabled = true;
									document.getElementById("btn_bid_five").disabled = true;
									document.getElementById("btn_bid_six").disabled = true;
								}
								else if(direction == 0){
									document.getElementById("ask_three").value = "";
									document.getElementById("ask_three").disabled = true;
									document.getElementById("btn_ask_five").disabled = true;
									document.getElementById("btn_ask_six").disabled = true;
								}
								document.getElementById("acceptThree").disabled = true;
								document.getElementById("argueThree").disabled = true;
								document.getElementById("sendThree").disabled = true;
								//如果用户仍想进行交易，则清空交易编号！
								document.getElementById("tranNo3").value = "";
							}
						});
					}
					else if(dat == "F"){
						//同意价格
						alert("第三对手方同意您的价格了！");
						//停止js定时器
						clearInterval(timename3);
						document.getElementById("acceptThree").disabled = false;
					}
					else{
						var point = dat;
						var oldPoint;
						var flag = 0;
						if(direction == 1){
							oldPoint = document.getElementById("bid_three").value;
							if(point != oldPoint){
								document.getElementById("bid_three").value = point;//修改价格
								flag = 1;
							}
						}
						else if(direction == 0){
							oldPoint = document.getElementById("ask_three").value;
							if(point != oldPoint){
								document.getElementById("ask_three").value = point;
								flag = 1;
							}
						}
						if(flag == 1){
							//停止js定时器
							clearInterval(timename3);
							//accept和argue按钮被激活
							document.getElementById("acceptThree").disabled = false;
							document.getElementById("argueThree").disabled = false;
						}
					}
					});
				}				
			}
		}
		function checkPoint4(){
			var tranNo = document.getElementById("tranNo4").value;
			var opponent = document.getElementById("opponentFour").value;
			var direction = '<ww:property value="#request.tradeDirection"/>';
			if(opponent != null && opponent != ''){
				if(tranNo != null && tranNo != ''){
					$.post("swapTradeCheckPoint.action",{tranNo:tranNo,opponent:opponent},
					function(dat){
						if(dat == "C"){
							//删除交易记录
							$.post("swapTradeDel.action",{tranNo:tranNo,opponent:opponent},
							function(flag){
								if(flag == 1 || flag == "1"){
									alert("第四对手方终止了交易！");
									//停止js定时器
									clearInterval(timename4);
									//中止交易，整行清空
									document.getElementById("opponentFour").disabled = false;
									document.getElementById("opponentFour").options.selectedIndex = 0;
									if(direction == 1){
										document.getElementById("bid_four").value = "";
										document.getElementById("bid_four").disabled = true;
										document.getElementById("btn_bid_seven").disabled = true;
										document.getElementById("btn_bid_eight").disabled = true;
									}
									else if(direction == 0){
										document.getElementById("ask_four").value = "";
										document.getElementById("ask_four").disabled = true;
										document.getElementById("btn_ask_seven").disabled = true;
										document.getElementById("btn_ask_eight").disabled = true;
									}
									document.getElementById("acceptFour").disabled = true;
									document.getElementById("argueFour").disabled = true;
									document.getElementById("sendFour").disabled = true;
									//如果用户仍想进行交易，则清空交易编号！
									document.getElementById("tranNo4").value = "";
								}
							});
						}
						else if(dat == "F"){
							//同意价格
							alert("第四对手方同意您的价格了！");
							//停止js定时器
							clearInterval(timename4);
							document.getElementById("acceptFour").disabled = false;
						}
						else{
							var point = dat;
							var oldPoint;
							var flag = 0;
							if(direction == 1){
								oldPoint = document.getElementById("bid_four").value;
								if(point != oldPoint){
									document.getElementById("bid_four").value = point;//修改价格
									flag = 1;
								}
							}
							else if(direction == 0){
								oldPoint = document.getElementById("ask_four").value;
								if(point != oldPoint){
									document.getElementById("ask_four").value = point;
									flag = 1;
								}
							}
							if(flag == 1){
								//停止js定时器
								clearInterval(timename4);
								//accept和argue按钮被激活
								document.getElementById("acceptFour").disabled = false;
								document.getElementById("argueFour").disabled = false;
							}
						}
					});
				}				
			}
		}
		function checkPoint5(){
			var tranNo = document.getElementById("tranNo5").value;
			var opponent = document.getElementById("opponentFive").value;
			var direction = '<ww:property value="#request.tradeDirection"/>';
			if(opponent != null && opponent != ''){
				if(tranNo != null && tranNo != ''){
					$.post("swapTradeCheckPoint.action",{tranNo:tranNo,opponent:opponent},
					function(dat){
						if(dat == "C"){
						//删除交易记录
						$.post("swapTradeDel.action",{tranNo:tranNo,opponent:opponent},
						function(flag){
							if(flag == 1 || flag == "1"){
								alert("第五对手方终止了交易！");
								//停止js定时器
								clearInterval(timename5);
								//中止交易，整行清空
								document.getElementById("opponentFive").disabled = false;
								document.getElementById("opponentFive").options.selectedIndex = 0;
								if(direction == 1){
									document.getElementById("bid_five").value = "";
									document.getElementById("bid_five").disabled = true;
									document.getElementById("btn_bid_nine").disabled = true;
									document.getElementById("btn_bid_ten").disabled = true;
								}
								else if(direction == 0){
									document.getElementById("ask_five").value = "";
									document.getElementById("ask_five").disabled = true;
									document.getElementById("btn_ask_nine").disabled = true;
									document.getElementById("btn_ask_ten").disabled = true;
								}
								document.getElementById("acceptFive").disabled = true;
								document.getElementById("argueFive").disabled = true;
								document.getElementById("sendFive").disabled = true;
								//如果用户仍想进行交易，则清空交易编号！
								document.getElementById("tranNo5").value = "";
							}
						});
					}
					else if(dat == "F"){
						//同意价格
						alert("第五对手方同意您的价格了！");
						//停止js定时器
						clearInterval(timename5);
						document.getElementById("acceptFive").disabled = false;
					}
					else{
						var point = dat;
						var oldPoint;
						var flag = 0;
						if(direction == 1){
							oldPoint = document.getElementById("bid_five").value;
							if(point != oldPoint){
								document.getElementById("bid_five").value = point;//修改价格
								flag = 1;
							}
						}
						else if(direction == 0){
							oldPoint = document.getElementById("ask_five").value;
							if(point != oldPoint){
								document.getElementById("ask_five").value = point;
								flag = 1;
							}
						}
						if(flag == 1){
							//停止js定时器
							clearInterval(timename5);
							//accept和argue按钮被激活
							document.getElementById("acceptFive").disabled = false;
							document.getElementById("argueFive").disabled = false;
						}
					}
					});
				}				
			}
		}
	  	function swapByAccept(obj){
			var tranNo1 = document.getElementById("tranNo1").value;
			var tranNo2 = document.getElementById("tranNo2").value;
			var tranNo3 = document.getElementById("tranNo3").value;
			var tranNo4 = document.getElementById("tranNo4").value;
			var tranNo5 = document.getElementById("tranNo5").value;
			//终止所有的定时器
			if(tranNo1 != null && tranNo1 != ""){
				clearInterval(timename1);
			}
			if(tranNo2 != null && tranNo2 != ""){
				clearInterval(timename2);
			}
			if(tranNo3 != null && tranNo3 != ""){
				clearInterval(timename3);
			}
			if(tranNo4 != null && tranNo4 != ""){
				clearInterval(timename4);
			}
			if(tranNo5 != null && tranNo5 != ""){
				clearInterval(timename5);
			}
			var tranNo;
			var opponent;
			var point;
			var direct = '<ww:property value="#request.tradeDirection"/>';
			if(valiByAmount() || validateDate() || validateBasis() || validateFrequency())
				return false;
			if(obj.id=="acceptOne"){
				if(validateByOpponentOne())
					return false;
				opponent = document.getElementById("opponentOne").value;
				tranNo = document.getElementById("tranNo1").value;
				if(direct == 1)
					point = document.getElementById("bid_one").value;
				else
					point = document.getElementById("ask_one").value;
			}
			else if(obj.id=="acceptTwo"){
				if(validateByOpponentTwo())
					return false;
				opponent = document.getElementById("opponentTwo").value;
				tranNo = document.getElementById("tranNo2").value;
				if(direct == 1)
					point = document.getElementById("bid_two").value;
				else
					point = document.getElementById("ask_two").value;
			}
			else if(obj.id=="acceptThree"){
				if(validateByOpponentThree())
					return false;
				opponent = document.getElementById("opponentThree").value;
				tranNo = document.getElementById("tranNo3").value;
				if(direct == 1)
					point = document.getElementById("bid_three").value;
				else
					point = document.getElementById("ask_three").value;
			}
			else if(obj.id=="acceptFour"){
				if(validateByOpponentFour())
					return false;
				opponent = document.getElementById("opponentFour").value;
				tranNo = document.getElementById("tranNo4").value;
				if(direct == 1)
					point = document.getElementById("bid_four").value;
				else
					point = document.getElementById("ask_four").value;
			}
			else if(obj.id=="acceptFive"){
				if(validateByOpponentFive())
					return false;
				opponent = document.getElementById("opponentFive").value;
				tranNo = document.getElementById("tranNo5").value;
				if(direct == 1)
					point = document.getElementById("bid_five").value;
				else
					point = document.getElementById("ask_five").value;
			}
			$.post("swapTradeAccept.action",{tranNo1:tranNo1,tranNo2:tranNo2,tranNo3:tranNo3,tranNo4:tranNo4,tranNo5:tranNo5,tranNo:tranNo,point:point,opponent:opponent},
			function(dat){
				alert("交易完成！");
				window.close();
			});
		}
	  	function accept(obj){
	  		if(obj.id=="acceptOne"){
				if(valiByAmount()){
					return false;
				}
				else if(validateByOpponentOne()){
					return false;
				}
				document.form1.action="swapTradeAcceptOne.action";
				document.form1.submit();
			}
			else if(obj.id=="acceptTwo"){
				if(valiByAmount()){
					return false;
				}
				else if(validateByOpponentTwo()){
					return false;
				}
				document.form1.action="swapTradeAcceptTwo.action";
				document.form1.submit();
			}
			else if(obj.id=="acceptThree"){
				if(valiByAmount()){
					return false;
				}
				else if(validateByOpponentThree()){
					return false;
				}
				document.form1.action="swapTradeAcceptThree.action";
				document.form1.submit();
			}
			else if(obj.id=="acceptFour"){
				if(valiByAmount()){
					return false;
				}
				else if(validateByOpponentFour()){
					return false;
				}
				document.form1.action="swapTradeAcceptFour.action";
				document.form1.submit();
			}
			else if(obj.id=="acceptFive"){
				if(valiByAmount()){
					return false;
				}
				else if(validateByOpponentFive()){
					return false;
				}
				document.form1.action="swapTradeAcceptFive.action";
				document.form1.submit();
			}
	  	}
	  	function submitAll(){
	  		//submit之后，全部非空对方手的操作按钮都要被禁用
			var i = 0;
			if(valiByAmount() || validateDate() || validateBasis() || validateFrequency()){//验证日期、金额、固定利率、basis是否已经填写，并且验证支付周期的正确性
				return false;
			}
			if (document.getElementById("opponentOne").value != '') {
				if (validateByOpponentOne()) {//检验发给对方手的价格是否为空
					return false;
				}
				else{
					swapBySend(1);
					i++;
				}
			}
			if (document.getElementById("opponentTwo").value != '') {
				if (validateByOpponentTwo()) {
					return false;
				}
				else{
					swapBySend(2);	
					i++;				
				}
			}
			if (document.getElementById("opponentThree").value != '') {
				if (validateByOpponentThree()) {
					return false;
				}
				else{
					swapBySend(3);
					i++;
				}				
			}
			if (document.getElementById("opponentFour").value != '') {
				if (validateByOpponentFour()) {
					return false;
				}
				else{
					swapBySend(4);	
					i++;				
				}
			}
			if (document.getElementById("opponentFive").value != '') {
				if (validateByOpponentFive()) {
					return false;
				}
				else{
					swapBySend(5);		
					i++;			
				}				
			}
			if (i > 0) {
			
			} else {
				alert("请选择对手方进行交易！");
			}	  	
	  	}
	  	
		function validateBasis(){
			var fixedRate = document.getElementById("fixedRate").value;
			var basis1 = document.getElementById("basis1").value;
			var basis2 = document.getElementById("basis2").value;
			if(fixedRate == null || fixedRate == ''){
				alert("请输入固定利率!");
				return true;
			}
			else if(isNaN(fixedRate)){
				alert("请输入整数形式的固定利率!");
				return true;
			}
			if(basis1 == null || basis1 == ''){
				alert("请输入近端basis!");
				return true;
			}
			else if(isNaN(basis1)){
				alert("请输入整数形式的近端basis!");
				return true;
			}
			if(basis2 == null || basis2 == ''){
				alert("请输入远端basis!");
				return true;
			}
			else if(isNaN(basis2)){
				alert("请输入整数形式的远端basis!");
				return true;
			}
			return false;
		}
		function validateFrequency(){
			var frequency = document.getElementById("frequency").value;
			var startTime = document.getElementById("d1").value;
			var endTime = document.getElementById("d2").value;
			var sYear = window.parseInt(startTime.substring(0,4));
			var sMonth = window.parseInt(startTime.substring(5,7));
			var sDay = window.parseInt(startTime.substring(8,10));
			var dYear = window.parseInt(endTime.substring(0,4));
			var dMonth = window.parseInt(endTime.substring(5,7));
			var dDay = window.parseInt(endTime.substring(8,10));
			switch(frequency){
			case "0":
				//判断年
				if(dYear < sYear){
					alert("起息日的年份与结息日的年份错误！");
					return true;
				}
				else if(dYear == sYear){
					//判断月
					if(dMonth < sMonth){
						alert("起息日的月份与结息日的月份错误！");
						return true;
					}
					else if(dMonth == sMonth){
						//判断日
						if(dDay < sDay || dDay == sDay){
							alert("结息日与起息日的间隔不能少于一天！");
							return true;
						}
					}
				}
				break;
			case "1":
				//判断年
				if(dYear < sYear){
					alert("起息日的年份与结息日的年份错误！");
					return true;
				}
				else if(dYear == sYear){
					//判断月
					if(dMonth < sMonth){
						alert("起息日的月份与结息日的月份错误！");
						return true;
					}
					else if(dMonth == sMonth){
						//判断日
						if(dDay - sDay < 7){
							alert("结息日与起息日的间隔不能少于一周！");
							return true;
						}
					}
					else if(dMonth - sMonth == 1){
						if(getDaysByMonth(sMonth) - sDay + dDay < 7){
							alert("结息日与起息日的间隔不能少于一周！");
							return true;
						}
					}
				}
				else if(dYear - sYear == 1){
					if(12 - sMonth + dMonth == 1){
						if(getDaysByMonth(sMonth) - sDay + dDay < 7){
							alert("结息日与起息日的间隔不能少于一周！");
							return true;
						}
					}
				}
				break;
			case "2":
				//判断年
				if(dYear < sYear){
					alert("起息日的年份与结息日的年份错误！");
					return true;
				}
				else if(dYear == sYear){
					//判断月
					if(dMonth - sMonth < 1){
						alert("结息日与起息日的间隔不能少于一个月！");
						return true;
					}
					else if(dMonth - sMonth == 1){
						//判断日
						if(dDay < sDay){
							alert("结息日与起息日的间隔不能少于一个月！");
							return true;
						}
					}
				}
				else if(dYear - sYear == 1){
					if(12 - sMonth + dMonth == 1){
						if(dDay < sDay){
							alert("结息日与起息日的间隔不能少于一个月！");
							return true;
						}
					}
				}
				break;
			case "3":
				//判断年
				if(dYear < sYear){
					alert("起息日的年份与结息日的年份错误！");
					return true;
				}
				else if(dYear == sYear){
					//判断月
					if(dMonth - sMonth < 2){
						alert("结息日与起息日的间隔不能少于两个月！");
						return true;
					}
					else if(dMonth - sMonth == 2){
						//判断日
						if(dDay < sDay){
							alert("结息日与起息日的间隔不能少于两个月！");
							return true;
						}
					}
				}
				else if(dYear - sYear == 1){
					if(12 - sMonth + dMonth < 2){
						alert("结息日与起息日的间隔不能少于两个月！");
						return true;
					}
					else if(12 - sMonth + dMonth == 2){
						if(dDay < sDay){
							alert("结息日与起息日的间隔不能少于两个月！");
							return true;
						}
					}
				}
				break;
			case "4":
				//判断年
				if(dYear < sYear){
					alert("起息日的年份与结息日的年份错误！");
					return true;
				}
				else if(dYear == sYear){
					//判断月
					if(dMonth - sMonth < 3){
						alert("结息日与起息日的间隔不能少于三个月！");
						return true;
					}
					else if(dMonth - sMonth == 3){
						//判断日
						if(dDay < sDay){
							alert("结息日与起息日的间隔不能少于三个月！");
							return true;
						}
					}
				}
				else if(dYear - sYear == 1){
					if(12 - sMonth + dMonth < 3){
						alert("结息日与起息日的间隔不能少于三个月！");
						return true;
					}
					else if(12 - sMonth + dMonth == 3){
						if(dDay < sDay){
							alert("结息日与起息日的间隔不能少于三个月！");
							return true;
						}
					}
				}
				break;
			case "5":
				//判断年
				if(dYear < sYear){
					alert("起息日的年份与结息日的年份错误！");
					return true;
				}
				else if(dYear == sYear){
					//判断月
					if(dMonth - sMonth < 6){
						alert("结息日与起息日的间隔不能少于半年！");
						return true;
					}
					else if(dMonth - sMonth == 6){
						//判断日
						if(dDay < sDay){
							alert("结息日与起息日的间隔不能少于半年！");
							return true;
						}
					}
				}
				else if(dYear - sYear == 1){
					if(12 - sMonth + dMonth < 6){
						alert("结息日与起息日的间隔不能少于半年！");
						return true;
					}
					else if(12 - sMonth + dMonth == 6){
						if(dDay < sDay){
							alert("结息日与起息日的间隔不能少于半年！");
							return true;
						}
					}
				}
				break;
			case "6":
				//判断年
				if(dYear < sYear){
					alert("起息日的年份与结息日的年份错误！");
					return true;
				}
				else if(dYear == sYear){
					alert("结息日与起息日的间隔不能少于一年！");
					return true;
				}
				else if(dYear - sYear == 1){
					if(dMonth < sMonth){
						alert("结息日与起息日的间隔不能少于一年！");
						return true;
					}
					else if(dMonth == sMonth){
						if(dDay < sDay){
							alert("结息日与起息日的间隔不能少于一年！");
							return true;
						}
					}
				}
				break;
			}
			return false;
		}
		function getDaysByMonth(month){
			var day = 0;
			switch(month){
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				day = 31;
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				day = 30;
				break;
			case 2:
				day = 28;
				break;
			}
			return day;
		}
	</script>
  </head>
  <body>
	  <form name="form1" id="form1" method="post" target="id_iframe">
	    <div id="all">
	    	<div id="top">
	  			<input type="radio" name="tradeType" onclick="selectTradeType()" value="0" <ww:if test="#request.radioValue == 0">checked</ww:if>>Spot
	  			<input type="radio" name="tradeType" style="margin-left: 40px;" onclick="selectTradeType()" value="1" <ww:if test="#request.radioValue == 1">checked</ww:if>>Forward
	  			<input type="radio" name="tradeType" style="margin-left: 40px;" onclick="selectTradeType()" value="2" <ww:if test="#request.radioValue == 2">checked</ww:if>>Swap
	    	</div>
	    	<div id="container">
	    		<div id="wrapOne">
		  			<label><span id="direction"></span><ww:property value="#request.ccy1"/> vs <ww:property value="#request.ccy2"/></label>
		  			<input type="hidden" name="swapTradeModel.direction" value='<ww:property value="#request.tradeDirection"/>'>
		  			<input type="hidden" name="swapTradeModel.weCcy" value='<ww:property value="#request.ccy1"/>'>
		  			<input type="hidden" name="swapTradeModel.anaCcy" value='<ww:property value="#request.ccy2"/>'>
		  			<label style="margin-left:50px;">Price:</label><label><ww:property value="#request.price"/></label>
		  			<input type="hidden" name="swapTradeModel.price" value='<ww:property value="#request.price"/>'> 
		  			<input type="hidden" value="0" id="point">
		  			<input type="hidden" value='<ww:property value="#request.price"/>' id="price">
	  			</div>
	  			<div class="modules">
	  				<span><input name="isSpot" id="isSpot" type="checkbox" value="spot" onclick="becomeSpot()" />spot</span>
					<span style="margin-left: 40px; margin-right: 20px;">Start Date:</span><input name="swapTradeModel.startDate" id="d1" type="text" dataType="date" size="15" />
					 <img onClick="WdatePicker({el:'d1'})"src="resources/calendar/image/Button.gif" id="imgid1" align="absMiddle" border="0" />
	  				
	  				<span style="margin-left: 40px; margin-right: 17px;">Maturity  Date:</span><input name="swapTradeModel.endDate" id="d2" type="text" dataType="date" size="15" />
	  				 <img onClick="WdatePicker({el:'d2'})"src="resources/calendar/image/Button.gif" id="imgid1" align="absMiddle" border="0" />
	  				
	  			</div>
	  			
	  			<div class="modules1">
	  				<div class="modules1left">
	  					<span style="color:red;">1st Leg</span><br>
	  					<span>I </span>
	  					<select id="directionSelection" name="swapTradeModel.fixedType" onchange="changeDirection()">
	  						<option value="0">Receive</option>
	  						<option value="1">Pay</option>
	  					</select>
	  					
	  					<input id="CCY1" disabled="disabled" size="5" type="text"/>
	  					<span style="font-size: 18px; font-weight: bolder;">@</span>
	  					<input name="swapTradeModel.fixedRate" id="fixedRate" size="10" value=""/>
	  					<span style="font-size: 18px; font-weight: bolder;">%</span>
	  						
	  					<br><br>
	  					<span>Amount:</span>
	  					<input id="cAmount" type="text" size="15"  name="swapTradeModel.cAmount" onkeyup="this.value=this.value.replace(/\D/g,'');synchronize(this);" onafterpaste="this.value=this.value.replace(/\D/g,'')">
	  					<br><br>
	  					
	  					<span>Basis:</span>
	  					<input style="margin-left: 8px;" id="basis1" type="text" size="15">
	  					<!-- <select id="basisSelection" name="swapTradeModel.cRateType" onchange="changeLibor()">
	  						<option value="0">隔夜</option>
	  						<option value="1">一周</option>
	  						<option value="2">一个月</option>
	  						<option value="3">两个月</option>
	  						<option value="4">三个月</option>
	  						<option value="5">六个月</option>
	  						<option value="6">十二个月</option>
	  					</select><span style="margin-left: 3px;">Libor</span> -->
	  					<br><br>
	  					
	  					<span>Pay Frequency:</span>
	  					<select name="swapTradeModel.frequency" id="frequency">
	  						<option value="0">隔夜</option>
	  						<option value="1">一周</option>
	  						<option value="2">一个月</option>
	  						<option value="3">两个月</option>
	  						<option value="4">一季度</option>
	  						<option value="5">半年</option>
	  						<option value="6">一年</option>
	  					</select>
	  					<br><br>
	
	  				</div>
	  				<div class="modules1right">
	  					<span style="color:red;">2nd Leg</span><br>
	  					<span>I </span>
	  					<input type="text" disabled="disabled" value="Pay" size="9" id="secondLeg">
	  					
	  					<input id="CCY2" disabled="disabled" size="5" type="text"/>
	  					<span style="font-size: 18px; font-weight: bolder;">@</span>
	  					<input id="floatingRate" disabled="disabled" value="Floating" size="10" name="swapTradeModel.floatingRate"/>
	  					<span style="font-size: 18px; font-weight: bolder;">%</span>	
	  					<br><br>
	  					<span>Amount:</span>
	  					<input id="fAmount" type="text" size="15" name="swapTradeModel.fAmount" onkeyup="this.value=this.value.replace(/\D/g,'');synchronize(this);" onafterpaste="this.value=this.value.replace(/\D/g,'')">
	  					<br><br>			
	  					<span>Libor:</span>
	  					<%--<select name="swapTradeModel.libor">
	  						<option value="0"><ww:property value="#request.liborModel.oneDay"/></option>
	  						<option value="1"><ww:property value="#request.liborModel.oneWeek"/></option>
	  						<option value="2"><ww:property value="#request.liborModel.oneMonth"/></option>
	  						<option value="3"><ww:property value="#request.liborModel.twoMonth"/></option>
	  						<option value="4"><ww:property value="#request.liborModel.threeMonth"/></option>
	  						<option value="5"><ww:property value="#request.liborModel.sixMonth"/></option>
	  						<option value="6"><ww:property value="#request.liborModel.twelveMonth"/></option>
	  					</select>
	  					--%><select id="liborSelection" style="margin-left: 8px;" name="swapTradeModel.libor">
	  						<option value="0">隔夜</option>
	  						<option value="1">1周</option>
	  						<option value="2">1个月</option>
	  						<option value="3">2个月</option>
	  						<option value="4">3个月</option>
	  						<option value="5">6个月</option>
	  						<option value="6">12个月</option>
	  					</select>
	  					<!-- <input style="margin-left: 8px;" id="libor" type="text" size="10" disabled="disabled"> -->
	  					<br><br>
	  					<span>Basis:</span>
	  					<input style="margin-left: 8px;" id="basis2" type="text" size="15">
	  					<!-- <select name="swapTradeModel.fFrequency">
	  						<option value="0">隔夜</option>
	  						<option value="1">一周</option>
	  						<option value="2">一个月</option>
	  						<option value="3">两个月</option>
	  						<option value="4">三个月</option>
	  						<option value="5">六个月</option>
	  						<option value="6">十二个月</option>
	  					</select> -->
	  					<br><br>
	  				</div>
	  			</div>
	  		
	  			<div class="modules">
	  				<span class="provider">Provider</span>
	  				<span style="font-size: 12px;">Bid Swap Pts</span>
	  				<span class="xiegan1">/</span>
	  				<span style="font-size: 12px;">Ask Swap Pts</span>
	  			</div>
	  			
	  			<div class="modules">
	  				<!-- 第一对手方 -->
		  				<select id="opponentOne" name="swapTradeModel.opponentOne" class="opponents" style="width: 100px; margin-right: 30px;" onchange="showOpponentOne()">
		  					<option></option>
		  					<ww:iterator value="#request.opponentList" status="index">
								<option value="<ww:property value="userNum"/>" <ww:if test="%{id==model.belong}">selected</ww:if>><ww:property value="name"/></option>
							</ww:iterator>
		  				</select>
		  				<input id="btn_bid_one" type="button" width="20px;" value="-" disabled="disabled" onclick="subtractionByPoint(this)">
		  				<input name="swapTradeModel.bidOne" id="bid_one" type="text" disabled="disabled" size="7">
		  				<input id="btn_bid_two" type="button" width="20px;" value="+" disabled="disabled" onclick="additionByPoint(this)">
		  				<span id="price11"><ww:property value="#request.price"/></span>
		  				<span class="xiegan">/</span>
		  				<span id="price21"><ww:property value="#request.price"/></span>
		  				<input id="btn_ask_one" type="button" width="20px;" value="-" disabled="disabled" onclick="subtractionByPoint(this)">
		  				<input name="swapTradeModel.askOne" id="ask_one" type="text" disabled="disabled" size="7">
		  				<input id="btn_ask_two" type="button" width="20px;" value="+" disabled="disabled" onclick="additionByPoint(this)">
		  				<input type="button" value="Accept" class="button1" id="acceptOne" name="accept"  onclick="swapByAccept(this)" disabled="disabled">
		  				<input type="button" value="Argue" class="button1" id="argueOne" onclick="displayOne('<ww:property value="#request.tradeDirection"/>')" disabled="disabled">
		  				<input type="button" value="Send" class="button1" id="sendOne" name="send" onclick="swapBySend(1)" disabled="disabled">
		  		</div>
		  		<div class="modules">
		  			<!-- 第二对手方 -->
		  				<select id="opponentTwo" name="swapTradeModel.opponentTwo" class="opponents" style="width: 100px; margin-right: 30px;" onchange="showOpponentTwo()">
		  					<option></option>
		  					<ww:iterator value="#request.opponentList" status="index">
								<option value="<ww:property value="userNum"/>" <ww:if test="%{id==model.belong}">selected</ww:if>><ww:property value="name"/></option>
							</ww:iterator>
		  				</select>
		  				<input id="btn_bid_three" type="button" width="20px;" value="-" disabled="disabled" onclick="subtractionByPoint(this)">
		  				<input name="swapTradeModel.bidTwo" id="bid_two" type="text" disabled="disabled" size="7">
		  				<input id="btn_bid_four" type="button" width="20px;" value="+" disabled="disabled" onclick="additionByPoint(this)">
		  				<span id="price12"><ww:property value="#request.price"/></span>
		  				<span class="xiegan">/</span>
		  				<span id="price22"><ww:property value="#request.price"/></span>
		  				<input id="btn_ask_three" type="button" width="20px;" value="-" disabled="disabled" onclick="subtractionByPoint(this)">
		  				<input name="swapTradeModel.askTwo" id="ask_two" type="text" disabled="disabled" size="7" >
		  				<input id="btn_ask_four" type="button" width="20px;" value="+" disabled="disabled" onclick="additionByPoint(this)">
		  				<input type="button" value="Accept" class="button1" id="acceptTwo" name="accept"  onclick="swapByAccept(this)" disabled="disabled">
		  				<input type="button" value="Argue" class="button1" id="argueTwo" onclick="displayTwo('<ww:property value="#request.tradeDirection"/>')" disabled="disabled">
		  				<input type="button" value="Send" class="button1" id="sendTwo" name="send" onclick="swapBySend(2)" disabled="disabled">
		  		</div>
		  		<div class="modules">
		  			<!-- 第三对手方 -->
		  				<select id="opponentThree" name="swapTradeModel.opponentThree" class="opponents" style="width: 100px; margin-right: 30px;" onchange="showOpponentThree()">
		  					<option></option>
		  					<ww:iterator value="#request.opponentList" status="index">
								<option value="<ww:property value="userNum"/>" <ww:if test="%{id==model.belong}">selected</ww:if>><ww:property value="name"/></option>
							</ww:iterator>
		  				</select>
		  				<input id="btn_bid_five" type="button" width="20px;" value="-" disabled="disabled" onclick="subtractionByPoint(this)">
		  				<input name="swapTradeModel.bidThree" id="bid_three" type="text" disabled="disabled" size="7">
		  				<input id="btn_bid_six" type="button" width="20px;" value="+" disabled="disabled" onclick="additionByPoint(this)">
		  				<span id="price13"><ww:property value="#request.price"/></span>
		  				<span class="xiegan">/</span>
		  				<span id="price23"><ww:property value="#request.price"/></span>
		  				<input id="btn_ask_five" type="button" width="20px;" value="-" disabled="disabled" onclick="subtractionByPoint(this)">
		  				<input name="swapTradeModel.askThree" id="ask_three" type="text" disabled="disabled" size="7">
		  				<input id="btn_ask_six" type="button" width="20px;" value="+" disabled="disabled" onclick="additionByPoint(this)">
		  				<input type="button" value="Accept" class="button1" id="acceptThree" name="accept"  onclick="swapByAccept(this)" disabled="disabled">
		  				<input type="button" value="Argue" class="button1" id="argueThree" onclick="displayThree('<ww:property value="#request.tradeDirection"/>')" disabled="disabled">
		  				<input type="button" value="Send" class="button1" id="sendThree" name="send" onclick="swapBySend(3)" disabled="disabled">
		  		</div>
		  		<div class="modules">
		  			<!-- 第四对手方 -->
		  				<select id="opponentFour" name="swapTradeModel.opponentFour" class="opponents" style="width: 100px; margin-right: 30px;" onchange="showOpponentFour()">
		  					<option></option>
		  					<ww:iterator value="#request.opponentList" status="index">
								<option value="<ww:property value="userNum"/>" <ww:if test="%{id==model.belong}">selected</ww:if>><ww:property value="name"/></option>
							</ww:iterator>
		  				</select>
		  				<input id="btn_bid_seven" type="button" width="20px;" value="-" disabled="disabled" onclick="subtractionByPoint(this)">
		  				<input name="swapTradeModel.bidFour" id="bid_four" type="text" disabled="disabled" size="7">
		  				<input id="btn_bid_eight" type="button" width="20px;" value="+" disabled="disabled" onclick="additionByPoint(this)">
		  				<span id="price14"><ww:property value="#request.price"/></span>
		  				<span class="xiegan">/</span>
		  				<span id="price24"><ww:property value="#request.price"/></span>
		  				<input id="btn_ask_seven" type="button" width="20px;" value="-" disabled="disabled" onclick="subtractionByPoint(this)">
		  				<input name="swapTradeModel.askFour" id="ask_four" type="text" disabled="disabled" size="7" >
		  				<input id="btn_ask_eight" type="button" width="20px;" value="+" disabled="disabled" onclick="additionByPoint(this)">
		  				<input type="button" value="Accept" class="button1" id="acceptFour" name="accept"  onclick="swapByAccept(this)" disabled="disabled">
		  				<input type="button" value="Argue" class="button1" id="argueFour" onclick="displayFour('<ww:property value="#request.tradeDirection"/>')" disabled="disabled">
		  				<input type="button" value="Send" class="button1" id="sendFour" name="send" onclick="swapBySend(4)" disabled="disabled">
		  		</div>
		  		<div class="modules">
		  			<!-- 第五对手方 -->
		  				<select id="opponentFive" name="swapTradeModel.opponentFive" class="opponents" style="width: 100px; margin-right: 30px;" onchange="showOpponentFive()">
		  					<option></option>
		  					<ww:iterator value="#request.opponentList" status="index">
								<option value="<ww:property value="userNum"/>" <ww:if test="%{id==model.belong}">selected</ww:if>><ww:property value="name"/></option>
							</ww:iterator>
		  				</select>
		  				<input id="btn_bid_nine" type="button" width="20px;" value="-" disabled="disabled" onclick="subtractionByPoint(this)">
		  				<input name="swapTradeModel.bidFive" id="bid_five" type="text" disabled="disabled" size="7">
		  				<input id="btn_bid_ten" type="button" width="20px;" value="+" disabled="disabled" onclick="additionByPoint(this)">
		  				<span  id="price15"><ww:property value="#request.price"/></span>
		  				<span class="xiegan">/</span>
		  				<span  id="price25"><ww:property value="#request.price"/></span>
		  				<input id="btn_ask_nine" type="button" width="20px;" value="-" disabled="disabled" onclick="subtractionByPoint(this)">
		  				<input name="swapTradeModel.askFive" id="ask_five" type="text" size="7" disabled="disabled">
		  				<input id="btn_ask_ten" type="button" width="20px;" value="+" disabled="disabled" onclick="additionByPoint(this)">
		  				<input type="button" value="Accept" class="button1" id="acceptFive" name="accept"  onclick="swapByAccept(this)" disabled="disabled">
		  				<input type="button" value="Argue" class="button1" id="argueFive" onclick="displayFive('<ww:property value="#request.tradeDirection"/>')" disabled="disabled">
		  				<input type="button" value="Send" class="button1" id="sendFive" name="send" onclick="swapBySend(5)" disabled="disabled">
		  		</div>
		  		<div>
			  		<br>
			  		<span style="width: 150px; margin-left: 40px;">交易状态：</span><input id="status" value="" disabled="disabled" type="text">
			  	</div>
	    	</div>
	    	<div id="top">
				<input id="submit" name="submit" type="button" value="submit" style="margin-right: 150px;" class="button2" onclick="submitAll()">
				<input id="with" name="with" type="button" value="withdraw" class="button2" onclick="withdrawBySwap()">
				<input type="hidden" id="tranNo1" name="swapTradeModel.tranNo1" value="">
				<input type="hidden" id="tranNo2" name="swapTradeModel.tranNo2" value="">
				<input type="hidden" id="tranNo3" name="swapTradeModel.tranNo3" value="">
				<input type="hidden" id="tranNo4" name="swapTradeModel.tranNo4" value="">
				<input type="hidden" id="tranNo5" name="swapTradeModel.tranNo5" value="">
	    	</div>
	    </div>
    </form>
    <iframe id="id_iframe" name="id_iframe" style="display:none;"></iframe>
  </body>
</html>
