<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib uri="webwork" prefix="ww"%>
<%@ page language="java" import="java.lang.Double" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>spot即期询价交易页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="resources/js/jquery.form.js"></script>
	<script src="resources/ponyjs/jquery.js" type="text/javascript"></script>
	<script src="/forex/jsp/local/tradeModule/ask/js/askTrade.js" type="text/javascript"></script>
	<style type="text/css">
		#all{margin: 0;padding: 0; height: auto; width: 900px;}
		#top{margin: 0; padding:3px 5px; height: 24px; width:890px; line-height: 24px; 
		background-image: url("images/b.png"); color: white; text-align: center; }
		#container{margin: 0;padding: 0; height: auto; width: 900px !important; width:890px; background-color: #E5F2F8;
		margin-top: 5px; margin-bottom: 5px;}
		#wrapOne{margin: 0;padding: 0; height: 70px; width: 900px !important; width:890px;  margin-bottom: 3px; 
		line-height: 70px; text-align: center; padding-top: 0px !important;padding-top: 20px;}
		label { font-size: 24px; font-weight: bolder; color: red;} 
		.modules{margin: 0;padding: 10px 0px 10px 40px; height: 20px; width: 860px !important; width:850px;
		margin-top: 2px; line-height: 20px; }
		.xiegan{font-family:Arial;font-weight:bolder;font-style:normal;text-decoration:none;color:#333333;font-size: 18px; }
		.xiegan1{font-family:Arial;font-weight:bolder;font-style:normal;text-decoration:none;color:#333333;font-size: 18px;
		margin: 0px 65px !important; margin: 0px 55px; }
		.provider{margin-left: 30px; margin-right: 140px !important; margin-right: 115px;}
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
				document.getElementById("bid_one").value=document.getElementById("price").value;
				document.getElementById("bid_one").disabled=false;
				document.getElementById("btn_bid_one").disabled=false;
				document.getElementById("btn_bid_two").disabled=false;
			}
			else{//如果是卖，则ask部分被激活
				document.getElementById("ask_one").disabled=false;
				document.getElementById("ask_one").value=document.getElementById("price").value;
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
				document.getElementById("bid_two").value=document.getElementById("price").value;
				document.getElementById("bid_two").disabled=false;
				document.getElementById("btn_bid_three").disabled=false;
				document.getElementById("btn_bid_four").disabled=false;
			}
			else{//如果是卖，则ask部分被激活
				document.getElementById("ask_two").disabled=false;
				document.getElementById("ask_two").value=document.getElementById("price").value;
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
				document.getElementById("bid_three").value=document.getElementById("price").value;
				document.getElementById("bid_three").disabled=false;
				document.getElementById("btn_bid_five").disabled=false;
				document.getElementById("btn_bid_six").disabled=false;
			}
			else{//如果是卖，则ask部分被激活
				document.getElementById("ask_three").disabled=false;
				document.getElementById("ask_three").value=document.getElementById("price").value;
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
				document.getElementById("bid_four").value=document.getElementById("price").value;
				document.getElementById("bid_four").disabled=false;
				document.getElementById("btn_bid_seven").disabled=false;
				document.getElementById("btn_bid_eight").disabled=false;
			}
			else{//如果是卖，则ask部分被激活
				document.getElementById("ask_four").disabled=false;
				document.getElementById("ask_four").value=document.getElementById("price").value;
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
				document.getElementById("bid_five").value=document.getElementById("price").value;
				document.getElementById("bid_five").disabled=false;
				document.getElementById("btn_bid_nine").disabled=false;
				document.getElementById("btn_bid_ten").disabled=false;
			}
			else{//如果是卖，则ask部分被激活
				document.getElementById("ask_five").disabled=false;
				document.getElementById("ask_five").value=document.getElementById("price").value;
				document.getElementById("btn_ask_nine").disabled=false;
				document.getElementById("btn_ask_ten").disabled=false;
			}
			//send按钮被激活
			document.getElementById("sendFive").disabled = false;
		}
	}
	function validateByOpponentOne(){
		var direction = '<ww:property value="#request.tradeDirection"/>';
		var price = 0;
		if(direction == 1){
			price = document.getElementById("bid_one").value;
		}
		else{
			price = document.getElementById("ask_one").value;
		}
		var opponentOne = document.getElementById("opponentOne").value;
		if (opponentOne == ''){
			alert("请输入第一个对手方");
			return true;
		}
		if(opponentOne != ''){
			if (price == '' || isNaN(price)){
				alert("请输入数字格式的价格！");
				return true;
			}
		}
		return false;
	}
	function validateByOpponentTwo(){
		var direction = '<ww:property value="#request.tradeDirection"/>';
		var price = 0;
		if(direction == 1){
			price = document.getElementById("bid_two").value;
		}
		else{
			price = document.getElementById("ask_two").value;
		}
		var opponentOne = document.getElementById("opponentTwo").value;
		if (opponentOne == ''){
			alert("请输入第二个对手方");
			return true;
		}
		if(opponentOne != ''){
			if (price == '' || isNaN(price)){
				alert("请输入数字格式的价格！");
				return true;
			}
		}
		return false;
	}
	function validateByOpponentThree(){
		var direction = '<ww:property value="#request.tradeDirection"/>';
		var price = 0;
		if(direction == 1){
			price = document.getElementById("bid_three").value;
		}
		else{
			price = document.getElementById("ask_three").value;
		}
		var opponentOne = document.getElementById("opponentThree").value;
		if (opponentOne == ''){
			alert("请输入第三个对手方");
			return true;
		}
		if(opponentOne != ''){
			if (price == '' || isNaN(price)){
				alert("请输入数字格式的价格！");
				return true;
			}
		}
		return false;
	}
	function validateByOpponentFour(){
		var direction = '<ww:property value="#request.tradeDirection"/>';
		var price = 0;
		if(direction == 1){
			price = document.getElementById("bid_four").value;
		}
		else{
			price = document.getElementById("ask_four").value;
		}
		var opponentOne = document.getElementById("opponentFour").value;
		if (opponentOne == ''){
			alert("请输入第四个对手方");
			return true;
		}
		if(opponentOne != ''){
			if (price == '' || isNaN(price)){
				alert("请输入数字格式的价格！");
				return true;
			}
		}
		return false;
	}
	function validateByOpponentFive(){
		var direction = '<ww:property value="#request.tradeDirection"/>';
		var price = 0;
		if(direction == 1){
			price = document.getElementById("bid_five").value;
		}
		else{
			price = document.getElementById("ask_five").value;
		}
		var opponentOne = document.getElementById("opponentFive").value;
		if (opponentOne == ''){
			alert("请输入第五个对手方");
			return true;
		}
		if(opponentOne != ''){
			if (price == '' || isNaN(price)){
				alert("请输入数字格式的价格！");
				return true;
			}
		}
		return false;
	}
	function selectTradeType(){
		var temp = document.getElementsByName("tradeType");
		var value = "";
		for (var i=0;i<temp.length;i++){
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
	function withdrawBySpot(){
		var tranNo1 = document.getElementById("tranNo1").value;
		var tranNo2 = document.getElementById("tranNo2").value;
		var tranNo3 = document.getElementById("tranNo3").value;
		var tranNo4 = document.getElementById("tranNo4").value;
		var tranNo5 = document.getElementById("tranNo5").value;
		$.post("spotTradeWithdraw.action",{tranNo1:tranNo1,tranNo2:tranNo2,tranNo3:tranNo3,tranNo4:tranNo4,tranNo5:tranNo5},
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
		}
		else{
			document.getElementById("direction").innerHTML = "I Buy ";
		}
	});
	
	function spotBySend(value){
		var opponent = '';
		var price = '';
		var direct = '<ww:property value="#request.tradeDirection"/>';
		var tranNo = '';
		var which;
		if (validateByAmount())//检验amount是否为空
			return false;
		if(value == 1){//第一个send按钮
			if(validateByOpponentOne())//检验对方手是否为空
				return false;
			opponent = document.getElementById("opponentOne").value;
			tranNo = document.getElementById("tranNo1").value;
			which = 1;
			if(direct == 1){
				price = document.getElementById("bid_one").value;
			}
			else{
				price = document.getElementById("ask_one").value;
			}
		}
		else if(value == 2){
			if(validateByOpponentTwo())
				return false;
			opponent = document.getElementById("opponentTwo").value;
			tranNo = document.getElementById("tranNo2").value;
			which = 2;
			if(direct == 1){
				price = document.getElementById("bid_two").value;
			}
			else{
				price = document.getElementById("ask_two").value;
			}	
		}
		else if(value == 3){
			if(validateByOpponentThree())
				return false;
			opponent = document.getElementById("opponentThree").value;
			tranNo = document.getElementById("tranNo3").value;
			which = 3;
			if(direct == 1){
				price = document.getElementById("bid_three").value;
			}				
			else{
				price = document.getElementById("ask_three").value;
			}	
		}
		else if(value == 4){
			if(validateByOpponentFour())
				return false;
			opponent = document.getElementById("opponentFour").value;
			tranNo = document.getElementById("tranNo4").value;
			which = 4;
			if(direct == 1){
				price = document.getElementById("bid_four").value;
			}			
			else{
				price = document.getElementById("ask_four").value;
			}
		}
		else if(value == 5){
			if(validateByOpponentFive())
				return false;
			opponent = document.getElementById("opponentFive").value;
			tranNo = document.getElementById("tranNo5").value;
			which = 5;
			if(direct == 1){
				price = document.getElementById("bid_five").value;
			}
			else{
				price = document.getElementById("ask_five").value;
			}
		}
		var amount = document.getElementById("amount").value;
		var ccy1 = '<ww:property value="#request.ccy1" />';
		var ccy2 = '<ww:property value="#request.ccy2" />';
		$.post("spotTradeSend.action",{tranNo:tranNo,direct:direct,opponent:opponent,ccy1:ccy1,ccy2:ccy2,amount:amount,price:price},
		function(dat){
			if(dat == "fail"){
				alert("余额不足！交易失败！");
			}
			else{
				if(document.getElementById("amount").disabled == false){
					document.getElementById("amount").disabled = true;//禁用，不能再次修改交易金额
				}
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
					timename1 = setInterval("checkPrice1();",5000);
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
					timename2 = setInterval("checkPrice2();",5000);
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
					timename3 = setInterval("checkPrice3();",5000);
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
					timename4 = setInterval("checkPrice4();",5000);
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
					timename5 = setInterval("checkPrice5();",5000);
					break;	
				}
			}
		});	
		//修改交易状态
		document.getElementById("status").value = 'PROCESSING';
	}
	
	function checkPrice1(){
		var tranNo = document.getElementById("tranNo1").value;
		var opponent = document.getElementById("opponentOne").value;
		var direction = '<ww:property value="#request.tradeDirection"/>';
		if(opponent != null && opponent != ''){
			if(tranNo != null && tranNo != ''){
				$.post("spotTradeCheckPrice.action",{tranNo:tranNo,opponent:opponent},
				function(dat){
					if(dat == "C"){
						//删除交易记录
						$.post("spotTradeDel.action",{tranNo:tranNo,opponent:opponent},
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
						//修改价格
						var price = dat;
						var oldPrice;
						var flag = 0;
						if(direction == 1){
							oldPrice = document.getElementById("bid_one").value;
							if(price != oldPrice){
								document.getElementById("bid_one").value = price;//修改价格
								flag = 1;
							}
						}
						else if(direction == 0){
							oldPrice = document.getElementById("ask_one").value;
							if(price != oldPrice){
								document.getElementById("ask_one").value = price;
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
	function checkPrice2(){
		var tranNo = document.getElementById("tranNo2").value;
		var opponent = document.getElementById("opponentTwo").value;
		var direction = '<ww:property value="#request.tradeDirection"/>';
		if(opponent != null && opponent != ''){
			if(tranNo != null && tranNo != ''){
				$.post("spotTradeCheckPrice.action",{tranNo:tranNo,opponent:opponent},
				function(dat){
					if(dat == "C"){
						//删除交易记录
						$.post("spotTradeDel.action",{tranNo:tranNo,opponent:opponent},
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
						var price = dat;
						var oldPrice;
						var flag = 0;
						if(direction == 1){
							oldPrice = document.getElementById("bid_two").value;
							if(price != oldPrice){
								document.getElementById("bid_two").value = price;//修改价格
								flag = 1;
							}
						}
						else if(direction == 0){
							oldPrice = document.getElementById("ask_two").value;
							if(price != oldPrice){
								document.getElementById("ask_two").value = price;
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
	function checkPrice3(){
		var tranNo = document.getElementById("tranNo3").value;
		var opponent = document.getElementById("opponentThree").value;
		var direction = '<ww:property value="#request.tradeDirection"/>';
		if(opponent != null && opponent != ''){
			if(tranNo != null && tranNo != ''){
				$.post("spotTradeCheckPrice.action",{tranNo:tranNo,opponent:opponent},
				function(dat){
					if(dat == "C"){
						//删除交易记录
						$.post("spotTradeDel.action",{tranNo:tranNo,opponent:opponent},
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
						var price = dat;
						var oldPrice;
						var flag = 0;
						if(direction == 1){
							oldPrice = document.getElementById("bid_three").value;
							if(price != oldPrice){
								document.getElementById("bid_three").value = price;//修改价格
								flag = 1;
							}
						}
						else if(direction == 0){
							oldPrice = document.getElementById("ask_three").value;
							if(price != oldPrice){
								document.getElementById("ask_three").value = price;
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
	function checkPrice4(){
		var tranNo = document.getElementById("tranNo4").value;
		var opponent = document.getElementById("opponentFour").value;
		var direction = '<ww:property value="#request.tradeDirection"/>';
		if(opponent != null && opponent != ''){
			if(tranNo != null && tranNo != ''){
				$.post("spotTradeCheckPrice.action",{tranNo:tranNo,opponent:opponent},
				function(dat){
					if(dat == "C"){
						//删除交易记录
						$.post("spotTradeDel.action",{tranNo:tranNo,opponent:opponent},
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
						var price = dat;
						var oldPrice;
						var flag = 0;
						if(direction == 1){
							oldPrice = document.getElementById("bid_four").value;
							if(price != oldPrice){
								document.getElementById("bid_four").value = price;//修改价格
								flag = 1;
							}
						}
						else if(direction == 0){
							oldPrice = document.getElementById("ask_four").value;
							if(price != oldPrice){
								document.getElementById("ask_four").value = price;
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
	function checkPrice5(){
		var tranNo = document.getElementById("tranNo5").value;
		var opponent = document.getElementById("opponentFive").value;
		var direction = '<ww:property value="#request.tradeDirection"/>';
		if(opponent != null && opponent != ''){
			if(tranNo != null && tranNo != ''){
				$.post("spotTradeCheckPrice.action",{tranNo:tranNo,opponent:opponent},
				function(dat){
				if(dat == "C"){
						//删除交易记录
						$.post("spotTradeDel.action",{tranNo:tranNo,opponent:opponent},
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
						var price = dat;
						var oldPrice;
						var flag = 0;
						if(direction == 1){
							oldPrice = document.getElementById("bid_five").value;
							if(price != oldPrice){
								document.getElementById("bid_five").value = price;//修改价格
								flag = 1;
							}
						}
						else if(direction == 0){
							oldPrice = document.getElementById("ask_five").value;
							if(price != oldPrice){
								document.getElementById("ask_five").value = price;
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
	
	function spotByAccept(obj){
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
		var price;
		var direct = '<ww:property value="#request.tradeDirection"/>';
		if (validateByAmount())
			return false;
		if(obj.id=="acceptOne"){
			if(validateByOpponentOne())
				return false;
			opponent = document.getElementById("opponentOne").value;
			tranNo = document.getElementById("tranNo1").value;
			
			if(direct == 1){
				price = document.getElementById("bid_one").value;
			}			
			else{
				price = document.getElementById("ask_one").value;
			}
		}else if(obj.id=="acceptTwo"){
			if(validateByOpponentTwo())
				return false;
			opponent = document.getElementById("opponentTwo").value;
			tranNo = document.getElementById("tranNo2").value;
			
			if(direct == 1)
				price = document.getElementById("bid_two").value;
			else
				price = document.getElementById("ask_two").value;
		}else if(obj.id=="acceptThree"){
			if(validateByOpponentThree())
				return false;
			opponent = document.getElementById("opponentThree").value;
			tranNo = document.getElementById("tranNo3").value;
			
			if(direct == 1)
				price = document.getElementById("bid_three").value;
			else
				price = document.getElementById("ask_three").value;
		}else if(obj.id=="acceptFour"){
			if(validateByOpponentFour())
				return false;
			opponent = document.getElementById("opponentFour").value;
			tranNo = document.getElementById("tranNo4").value;
			
			if(direct == 1)
				price = document.getElementById("bid_four").value;
			else
				price = document.getElementById("ask_four").value;
		}else if(obj.id=="acceptFive"){
			if(validateByOpponentFive())
				return false;
			opponent = document.getElementById("opponentFive").value;
			tranNo = document.getElementById("tranNo5").value;
			
			if(direct == 1)
				price = document.getElementById("bid_five").value;
			else
				price = document.getElementById("ask_five").value;
		}
		//var amount = document.getElementById("amount").value;
		//var ccy1 = '<ww:property value="#request.ccy1" />';
		//var ccy2 = '<ww:property value="#request.ccy2" />';
		$.post("spotTradeAccept.action",{tranNo1:tranNo1,tranNo2:tranNo2,tranNo3:tranNo3,tranNo4:tranNo4,tranNo5:tranNo5,tranNo:tranNo,opponent:opponent,price:price},
		function(dat){
			alert("交易完成！");
			window.close();
		});
	}
		
		function submitAll() {
			//submit之后，全部非空对方手的操作按钮都要被禁用
			var i = 0;
			if (validateByAmount()) {//检验交易金额
				return false;
			}
			if (document.getElementById("opponentOne").value != '') {
				if (validateByOpponentOne()) {//检验发给对方手的价格是否为空
					return false;
				}
				else{
					spotBySend(1);
					i++;
				}
			}
			if (document.getElementById("opponentTwo").value != '') {
				if (validateByOpponentTwo()) {
					return false;
				}
				else{
					spotBySend(2);	
					i++;
				}
			}
			if (document.getElementById("opponentThree").value != '') {
				if (validateByOpponentThree()) {
					return false;
				}
				else{
					spotBySend(3);
					i++;
				}
			}
			if (document.getElementById("opponentFour").value != '') {
				if (validateByOpponentFour()) {
					return false;
				}
				else{
					spotBySend(4);	
					i++;				
				}
			}
			if (document.getElementById("opponentFive").value != '') {
				if (validateByOpponentFive()) {
					return false;
				}
				else{
					spotBySend(5);		
					i++;			
				}				
			}
			if (i > 0) {
			
			} else {
				alert("请选择对手方进行交易！");
			}
		}
		
		function validateByAmount(){
			var amount = document.getElementById("amount").value;
			if (amount == '' || amount%100 != 0){
				alert("请输入100整数倍的交易金额！");
				return true;
			}else{
				return false;
			}
		}
		
	</script>
  </head>
  
  <body>
  	<form  action="" id="form1" name="form1" method="post">
	    <div id="all">
	    	<div id="top">
	  			<input type="radio" name="tradeType" onclick="selectTradeType()" value="0" <ww:if test="#request.radioValue == 0">checked</ww:if>>Spot
	  			<input type="radio" name="tradeType" style="margin-left: 40px;" onclick="selectTradeType()" value="1" <ww:if test="#request.radioValue == 1">checked</ww:if>>Forward
	  			<input type="radio" name="tradeType" style="margin-left: 40px;" onclick="selectTradeType()" value="2" <ww:if test="#request.radioValue == 2">checked</ww:if>>Swap
	    	</div>
	    	<div id="container">
	    		<div id="wrapOne">
		  			<label><span id="direction"></span><ww:property value="#request.ccy1"/> vs <ww:property value="#request.ccy2"/></label>
		  			<input type="hidden" name="spotTradeModel.direction" value='<ww:property value="#request.tradeDirection"/>'>
		  			<input type="hidden" name="spotTradeModel.weCcy" value='<ww:property value="#request.ccy1"/>'>
		  			<input type="hidden" name="spotTradeModel.anaCcy" value='<ww:property value="#request.ccy2"/>'>
		  			<label style="margin-left:50px;">Amount:</label>
		  			<input name="spotTradeModel.amount" id="amount" type="text" size="15" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
		  			<label style="margin-left:50px;">Price:</label><label><ww:property value="#request.price"/></label>
		  			<input type="hidden" value='<ww:property value="#request.price"/>' id="price"> 
	  			</div>
	  			<div class="modules">
	  				<span class="provider">Provider</span>
	  				<span>Bid</span>
	  				<span class="xiegan1" style="">/</span>
	  				<span>Ask</span>
	  			</div>
	  			
	  			<div class="modules">
	  				<!-- 第一对手方 -->
	  				<select id="opponentOne" name="spotTradeModel.opponentOne" class="opponents" style="width: 130px; margin-right: 30px;" onchange="showOpponentOne()">
	  					<option></option>
	  					<ww:iterator value="#request.opponentList" status="index">
							<option value="<ww:property value="userNum"/>" <ww:if test="%{userNum==model.belong}">selected</ww:if>><ww:property value="name"/></option>
						</ww:iterator>
	  				</select>
	  				<input id="btn_bid_one" type="button" width="20px;" value="-" onclick="subtraction(this)" disabled="disabled">
	  				<input name="spotTradeModel.bidOne" id="bid_one" type="text" size="10" disabled="disabled">
	  				<input id="btn_bid_two" type="button" width="20px;" value="+" onclick="addition(this)" disabled="disabled">
	  				<span class="xiegan">/</span>
	  				<input id="btn_ask_one" type="button" width="20px;" value="-" onclick="subtraction(this)" disabled="disabled">
	  				<input name="spotTradeModel.askOne" id="ask_one" type="text" size="10" disabled="disabled">
	  				<input id="btn_ask_two" type="button" width="20px;" value="+" onclick="addition(this)" disabled="disabled">
	  				<input id="acceptOne" name="accept"  type="button" value="Accept" class="button1" onclick="spotByAccept(this)" disabled="disabled">
	  				<input id="argueOne" type="button" value="Argue" class="button1" onclick="displayOne('<ww:property value="#request.tradeDirection"/>')" disabled="disabled">
	  				<input type="button" id="sendOne" value="Send" class="button1" name="send" onclick="spotBySend(1)" disabled="disabled">
	  			</div>
	  			<div class="modules">
	  				<!-- 第二对手方 -->
	  				<select id="opponentTwo" name="spotTradeModel.opponentTwo" class="opponents" style="width: 130px; margin-right: 30px;" onchange="showOpponentTwo()">
	  					<option></option>
	  					<ww:iterator value="#request.opponentList" status="index">
							<option value="<ww:property value="userNum"/>" <ww:if test="%{userNum==model.belong}">selected</ww:if>><ww:property value="name"/></option>
						</ww:iterator>
	  				</select>
	  				<input id="btn_bid_three" type="button" width="20px;" value="-" onclick="subtraction(this)" disabled="disabled">
	  				<input name="spotTradeModel.bidTwo" id="bid_two" type="text" size="10" disabled="disabled">
	  				<input id="btn_bid_four" type="button" width="20px;" value="+" onclick="addition(this)" disabled="disabled">
	  				<span class="xiegan">/</span>
	  				<input id="btn_ask_three" type="button" width="20px;" value="-" onclick="subtraction(this)" disabled="disabled">
	  				<input name="spotTradeModel.askTwo" id="ask_two" type="text" size="10" disabled="disabled">
	  				<input id="btn_ask_four" type="button" width="20px;" value="+" onclick="addition(this)" disabled="disabled">
	  				<input id="acceptTwo" type="button" name="accept" value="Accept" class="button1" onclick="spotByAccept(this)" disabled="disabled">
	  				<input id="argueTwo" type="button" value="Argue" class="button1" onclick="displayTwo('<ww:property value="#request.tradeDirection"/>')" disabled="disabled">
	  				<input type="button" id="sendTwo" value="Send" class="button1" name="send" onclick="spotBySend(2)" disabled="disabled">
	  			</div>
	  			<div class="modules">
	  				<!-- 第三对手方 -->
	  				<select id="opponentThree" name="spotTradeModel.opponentThree" class="opponents" style="width: 130px; margin-right: 30px;" onchange="showOpponentThree()">
	  					<option></option>
	  					<ww:iterator value="#request.opponentList" status="index">
							<option value="<ww:property value="userNum"/>" <ww:if test="%{userNum==model.belong}">selected</ww:if>><ww:property value="name"/></option>
						</ww:iterator>
	  				</select>
	  				<input id="btn_bid_five" type="button" width="20px;" value="-" onclick="subtraction(this)" disabled="disabled">
	  				<input name="spotTradeModel.bidThree" id="bid_three" type="text" size="10" disabled="disabled">
	  				<input id="btn_bid_six" type="button" width="20px;" value="+" onclick="addition(this)" disabled="disabled">
	  				<span class="xiegan">/</span>
	  				<input id="btn_ask_five" type="button" width="20px;" value="-" onclick="subtraction(this)" disabled="disabled">
	  				<input name="spotTradeModel.askThree" id="ask_three" type="text" size="10" disabled="disabled">
	  				<input id="btn_ask_six" type="button" width="20px;" value="+" onclick="addition(this)" disabled="disabled">
	  				<input id="acceptThree" type="button" name="accept" value="Accept" class="button1" onclick="spotByAccept(this)" disabled="disabled">
	  				<input id="argueThree" type="button" value="Argue" class="button1" onclick="displayThree('<ww:property value="#request.tradeDirection"/>')" disabled="disabled">
	  				<input type="button" id="sendThree" value="Send" class="button1" name="send" onclick="spotBySend(3)" disabled="disabled">
	  			</div>
	  			<div class="modules">
	  				<!-- 第四对手方 -->
	  				<select id="opponentFour" name="spotTradeModel.opponentFour" class="opponents" style="width: 130px; margin-right: 30px;" onchange="showOpponentFour()">
	  					<option></option>
	  					<ww:iterator value="#request.opponentList" status="index">
							<option value="<ww:property value="userNum"/>" <ww:if test="%{userNum==model.belong}">selected</ww:if>><ww:property value="name"/></option>
						</ww:iterator>
	  				</select>
	  				<input id="btn_bid_seven" type="button" width="20px;" value="-" onclick="subtraction(this)" disabled="disabled">
	  				<input name="spotTradeModel.bidFour" id="bid_four" type="text" size="10" disabled="disabled">
	  				<input id="btn_bid_eight" type="button" width="20px;" value="+" onclick="addition(this)" disabled="disabled">
	  				<span class="xiegan">/</span>
	  				<input id="btn_ask_seven" type="button" width="20px;" value="-" onclick="subtraction(this)" disabled="disabled">
	  				<input name="spotTradeModel.askFour" id="ask_four" type="text" size="10" disabled="disabled">
	  				<input id="btn_ask_eight" type="button" width="20px;" value="+" onclick="addition(this)" disabled="disabled">
	  				<input id="acceptFour" type="button" name="accept" value="Accept" class="button1" onclick="spotByAccept(this)" disabled="disabled">
	  				<input id="argueFour" type="button" value="Argue" class="button1" onclick="displayFour('<ww:property value="#request.tradeDirection"/>')" disabled="disabled">
	  				<input type="button" id="sendFour" value="Send" class="button1" name="send" onclick="spotBySend(4)" disabled="disabled">
	  			</div>
	  			<div class="modules">
	  				<!-- 第五对手方 -->
	  				<select id="opponentFive" name="spotTradeModel.opponentFive" class="opponents" style="width: 130px; margin-right: 30px;" onchange="showOpponentFive()">
	  					<option></option>
	  					<ww:iterator value="#request.opponentList" status="index">
							<option value="<ww:property value="userNum"/>" <ww:if test="%{userNum==model.belong}">selected</ww:if>><ww:property value="name"/></option>
						</ww:iterator>
	  				</select>
	  				<input id="btn_bid_nine" type="button" width="20px;" value="-" onclick="subtraction(this)" disabled="disabled">
	  				<input name="spotTradeModel.bidFive" id="bid_five" type="text" size="10" disabled="disabled">
	  				<input id="btn_bid_ten" type="button" width="20px;" value="+" onclick="addition(this)" disabled="disabled">
	  				<span class="xiegan">/</span>
	  				<input id="btn_ask_nine" type="button" width="20px;" value="-" onclick="subtraction(this)" disabled="disabled">
	  				<input name="spotTradeModel.askFive" id="ask_five" type="text" size="10" disabled="disabled" >
	  				<input id="btn_ask_ten" type="button" width="20px;" value="+" onclick="addition(this)" disabled="disabled">
	  				<input id="acceptFive" type="button" name="accept" value="Accept" class="button1" onclick="spotByAccept(this)" disabled="disabled">
	  				<input id="argueFive" type="button" value="Argue" class="button1" onclick="displayFive('<ww:property value="#request.tradeDirection"/>')" disabled="disabled">
	  				<input type="button" id="sendFive" value="Send" class="button1" name="send" onclick="spotBySend(5)" disabled="disabled">
	  			</div>
	  				  			
	  			<div>
	  				<br>
	  				<span style="width: 150px; margin-left: 40px;">交易状态：</span><input id="status" disabled="disabled" type="text">
	  			</div>
	    	</div>
	    	
	    	<div id="top">
				<input id="submit" name="submit" type="button" value="submit" style="margin-right: 150px;" class="button2"  onclick="submitAll()">
				<input id="withdraw" name="withdraw" type="button" value="withdraw" class="button2" onclick="withdrawBySpot()">
				<input type="hidden" id="tranNo1" name="spotTradeModel.tranNo1" value="">
				<input type="hidden" id="tranNo2" name="spotTradeModel.tranNo2" value="">
				<input type="hidden" id="tranNo3" name="spotTradeModel.tranNo3" value="">
				<input type="hidden" id="tranNo4" name="spotTradeModel.tranNo4" value="">
				<input type="hidden" id="tranNo5" name="spotTradeModel.tranNo5" value="">
	    	</div>
	    </div>
    </form>
  </body>
</html>
