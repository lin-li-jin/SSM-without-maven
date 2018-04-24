<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib uri="webwork" prefix="ww"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>forward远期询价交易页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
		#all{margin: 0;padding: 0; height: auto; width: 900px;}
		#top{margin: 0; padding:3px 5px; height: 24px; width:890px; line-height: 24px; 
		background-image: url("images/b.png"); color: white; text-align: center; }
		#container{margin: 0;padding: 0; height: auto; width: 900px !important; width:890px; background-color: #E5F2F8;
		margin-top: 5px; margin-bottom: 5px;}
		#wrapOne{margin: 0;padding: 0; height: 70px; width: 870px !important;width: 890px;  margin-bottom: 3px; 
		line-height: 70px; text-align: center; padding-top: 0px !important;padding-top: 20px;}
		label { font-size: 24px; font-weight: bolder; color: red;} 
		.modules{margin: 0;padding: 10px 0px 10px 40px; height: 20px; width: 860px; 
		margin-top: 2px; line-height: 20px; }
		.xiegan{font-family:Arial;font-weight:bolder;font-style:normal;text-decoration:none;
		color:#333333;font-size: 18px; }
		.xiegan1{font-family:Arial;font-weight:bolder;font-style:normal;text-decoration:none;color:#333333;font-size: 18px;
		margin: 0px 5px !important; margin: 0px 50px; }
		.provider{margin-left: 30px; margin-right: 140px !important; margin-right: 100px;}
		.button1{width: 80px;}
		.button2{ width: 80px; height: 24px;}
	</style>
	<script language="javascript" type="text/javascript" src="resources/calendar/js/My97DatePicker/WdatePicker.js"></script>
	<script src="/forex/jsp/local/tradeModule/ask/js/askTrade.js" type="text/javascript"></script>
	<script src="resources/ponyjs/jquery.js" type="text/javascript"></script>
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
				document.getElementById("bid_one").value = document.getElementById("point").value;
				document.getElementById("bid_one").disabled = false;
				document.getElementById("btn_bid_one").disabled = false;
				document.getElementById("btn_bid_two").disabled = false;
			}
			else{//如果是卖，则ask部分被激活
				document.getElementById("ask_one").disabled = false;
				document.getElementById("ask_one").value = document.getElementById("point").value;
				document.getElementById("btn_ask_one").disabled = false;
				document.getElementById("btn_ask_two").disabled = false;
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
	function selectTradeType(){
		var temp = document.getElementsByName("tradeType");
		var value = "0";
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
	function withdrawByForward(){
		var tranNo1 = document.getElementById("tranNo1").value;
		var tranNo2 = document.getElementById("tranNo2").value;
		var tranNo3 = document.getElementById("tranNo3").value;
		var tranNo4 = document.getElementById("tranNo4").value;
		var tranNo5 = document.getElementById("tranNo5").value;
		$.post("forwardTradeWithdraw.action",{tranNo1:tranNo1,tranNo2:tranNo2,tranNo3:tranNo3,tranNo4:tranNo4,tranNo5:tranNo5},
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
	
	function forwardBySend(value){
		var opponent;
		var point;
		var direct = '<ww:property value="#request.tradeDirection"/>';
		var tranNo;
		var which;
		if(validateByAmount() || validDate())//检验交易金额和约定日期
			return false;
		if(value == 1){//第一个send按钮
			if(validateByOpponentOne())//检验对方手是否为空
				return false;
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
		var amount = document.getElementById("amount").value;
		var ccy1 = '<ww:property value="#request.ccy1" />';
		var ccy2 = '<ww:property value="#request.ccy2" />';
		var price = '<ww:property value="#request.price"/>';
		var valueDate = document.getElementById("date").value;
			$.post("forwardTradeSend.action",{tranNo:tranNo,point:point,valueDate:valueDate,direct:direct,opponent:opponent,ccy1:ccy1,ccy2:ccy2,amount:amount,price:price},
		function(dat){
			if(dat == "fail"){
				alert("余额不足！交易失败！");
			}
			else{
				//禁用交易金额和日期
				if(document.getElementById("amount").disabled == false ){
					document.getElementById("amount").disabled = true;
				}
				if(document.getElementById("date").disabled == false){
					document.getElementById("date").disabled = true;
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
				$.post("forwardTradeCheckPoint.action",{tranNo:tranNo,opponent:opponent},
				function(dat){
					if(dat == "C"){
						//删除交易记录
						$.post("forwardTradeDel.action",{tranNo:tranNo,opponent:opponent},
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
				$.post("forwardTradeCheckPoint.action",{tranNo:tranNo,opponent:opponent},
				function(dat){
					if(dat == "C"){
						//删除交易记录
						$.post("forwardTradeDel.action",{tranNo:tranNo,opponent:opponent},
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
				$.post("forwardTradeCheckPoint.action",{tranNo:tranNo,opponent:opponent},
				function(dat){
					if(dat == "C"){
						//删除交易记录
						$.post("forwardTradeDel.action",{tranNo:tranNo,opponent:opponent},
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
				$.post("forwardTradeCheckPoint.action",{tranNo:tranNo,opponent:opponent},
				function(dat){
					if(dat == "C"){
						//删除交易记录
						$.post("forwardTradeDel.action",{tranNo:tranNo,opponent:opponent},
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
				$.post("forwardTradeCheckPoint.action",{tranNo:tranNo,opponent:opponent},
				function(dat){
					if(dat == "C"){
						//删除交易记录
						$.post("forwardTradeDel.action",{tranNo:tranNo,opponent:opponent},
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
	function forwardByAccept(obj){
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
		if(validateByAmount()||validDate())
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
		$.post("forwardTradeAccept.action",{tranNo1:tranNo1,tranNo2:tranNo2,tranNo3:tranNo3,tranNo4:tranNo4,tranNo5:tranNo5,tranNo:tranNo,point:point,opponent:opponent},
		function(dat){
			alert("交易完成！");
			window.close();
		});	
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
	/* function forwardBySubmit() {
		//submit之后，全部非空对方手的操作按钮都要被禁用
		var direct = '<ww:property value="#request.tradeDirection"/>';
		var i = 0;
		if (validateByAmount()||validDate()) {//检验交易金额和交割日期
			return false;
		}
		if (document.getElementById("opponentOne").value != '') {
				if (validateByOpponentOne()) {//检验发给对方手的价格是否为空
					return false;
				}
				else{
					if(document.getElementById("opponentOne").disabled == true){//如果provider已经被禁用
						return false;
					}
					else{
						//provider被禁用
						document.getElementById("opponentOne").disabled = true;
						if(direct == 1){
							point = document.getElementById("bid_one").value;
							//bid部分被禁用
							document.getElementById("bid_one").disabled = true;
							document.getElementById("btn_bid_one").disabled = true;
							document.getElementById("btn_bid_two").disabled = true;
						}
						else{
							point = document.getElementById("ask_one").value;
							//ask部分被禁用
							document.getElementById("ask_one").disabled = true;
							document.getElementById("btn_ask_one").disabled = true;
							document.getElementById("btn_ask_two").disabled = true;			
						}
						//send按钮被禁用
						document.getElementById("sendOne").disabled = true;
						i++;
					}					
				}
			}
			if (document.getElementById("opponentTwo").value != '') {
				if (validateByOpponentTwo()) {
					return false;
				}
				else{
					if(document.getElementById("opponentTwo").disabled == true){//如果provider已经被禁用
						return false;
					}
					else{
						//provider被禁用
						document.getElementById("opponentTwo").disabled = true;
						if(direct == 1){
							point = document.getElementById("bid_two").value;
							//bid部分被禁用
							document.getElementById("bid_two").disabled = true;
							document.getElementById("btn_bid_three").disabled = true;
							document.getElementById("btn_bid_four").disabled = true;
						}
						else{
							point = document.getElementById("ask_two").value;
							//ask部分被禁用
							document.getElementById("ask_two").disabled = true;
							document.getElementById("btn_ask_three").disabled = true;
							document.getElementById("btn_ask_four").disabled = true;			
						}
						//send按钮被禁用
						document.getElementById("sendTwo").disabled = true;
						i++;
					}					
				}
			}
			if (document.getElementById("opponentThree").value != '') {
				if (validateByOpponentThree()) {
					return false;
				}
				else{
					if(document.getElementById("opponentThree").disabled == true){//如果provider已经被禁用
						return false;
					}
					else{
						//provider被禁用
						document.getElementById("opponentThree").disabled = true;
						if(direct == 1){
							point = document.getElementById("bid_three").value;
							//bid部分被禁用
							document.getElementById("bid_three").disabled = true;
							document.getElementById("btn_bid_five").disabled = true;
							document.getElementById("btn_bid_six").disabled = true;
						}
						else{
							point = document.getElementById("ask_three").value;
							//ask部分被禁用
							document.getElementById("ask_three").disabled = true;
							document.getElementById("btn_ask_five").disabled = true;
							document.getElementById("btn_ask_six").disabled = true;			
						}
						//send按钮被禁用
						document.getElementById("sendThree").disabled = true;
						i++;
					}					
				}				
			}
			if (document.getElementById("opponentFour").value != '') {
				if (validateByOpponentFour()) {
					return false;
				}
				else{
					if(document.getElementById("opponentFour").disabled == true){//如果provider已经被禁用
						return false;
					}
					else{
						//provider被禁用
						document.getElementById("opponentFour").disabled = true;
						if(direct == 1){
							point = document.getElementById("bid_four").value;
							//bid部分被禁用
							document.getElementById("bid_four").disabled = true;
							document.getElementById("btn_bid_seven").disabled = true;
							document.getElementById("btn_bid_eight").disabled = true;
						}
						else{
							point = document.getElementById("ask_four").value;
							//ask部分被禁用
							document.getElementById("ask_four").disabled = true;
							document.getElementById("btn_ask_seven").disabled = true;
							document.getElementById("btn_ask_eight").disabled = true;			
						}
						//send按钮被禁用
						document.getElementById("sendFour").disabled = true;
						i++;
					}					
				}
			}
			if (document.getElementById("opponentFive").value != '') {
				if (validateByOpponentFive()) {
					return false;
				}
				else{
					if(document.getElementById("opponentFive").disabled == true){//如果provider已经被禁用
						return false;
					}
					else{
						//provider被禁用
						document.getElementById("opponentFive").disabled = true;
						if(direct == 1){
							point = document.getElementById("bid_five").value;
							//bid部分被禁用
							document.getElementById("bid_five").disabled = true;
							document.getElementById("btn_bid_nine").disabled = true;
							document.getElementById("btn_bid_ten").disabled = true;
						}
						else{
							point = document.getElementById("ask_five").value;
							//ask部分被禁用
							document.getElementById("ask_five").disabled = true;
							document.getElementById("btn_ask_nine").disabled = true;
							document.getElementById("btn_ask_ten").disabled = true;			
						}
						//send按钮被禁用
						document.getElementById("sendFive").disabled = true;
						i++;
					}					
				}				
			}
		if (i > 0) {
			document.form1.action = "forwardTradeAllOpponent.action?direct="+'<ww:property value="#request.tradeDirection"/>'+"&ccy1="+'<ww:property value="#request.ccy1"/>'+"&ccy2="+'<ww:property value="#request.ccy2"/>'+"&price="+'<ww:property value="#request.price"/>';
			document.form1.submit();
		} else {
			alert("请选择对手方进行交易！");
		}
	} */
	function submitAll() {
			//submit之后，全部非空对方手的操作按钮都要被禁用
			var i = 0;
			if (validateByAmount() || validDate()) {//检验交易金额
				return false;
			}
			if (document.getElementById("opponentOne").value != '') {
				if (validateByOpponentOne()) {//检验发给对方手的价格是否为空
					return false;
				}
				else{
					forwardBySend(1);
					i++;
				}
			}
			if (document.getElementById("opponentTwo").value != '') {
				if (validateByOpponentTwo()) {
					return false;
				}
				else{
					forwardBySend(2);	
					i++;				
				}
			}
			if (document.getElementById("opponentThree").value != '') {
				if (validateByOpponentThree()) {
					return false;
				}
				else{
					forwardBySend(3);
					i++;
				}				
			}
			if (document.getElementById("opponentFour").value != '') {
				if (validateByOpponentFour()) {
					return false;
				}
				else{
					forwardBySend(4);	
					i++;				
				}
			}
			if (document.getElementById("opponentFive").value != '') {
				if (validateByOpponentFive()) {
					return false;
				}
				else{
					forwardBySend(5);		
					i++;			
				}				
			}
			if (i > 0) {
			
			} else {
				alert("请选择对手方进行交易！");
			}
		}
	
	function validDate() {
		var valueDate = document.getElementById("date").value;
		var now = getCurrentDate();
		var year = valueDate.substring(0, 4);
		var month = valueDate.substring(5, 7);
		var day = valueDate.substring(8, 10);
		if(valueDate == '' || valueDate == null){
			alert("交易日期不能为空！");
			return true;
		}
		else{
			if(isNaN(year) || isNaN(month) || isNaN(day)){
				alert("交易日期格式不正确！");
				return true;
			}
			else{
				if(valueDate < now){
					alert("交易日期不能小于当前日期！");
					return true;
				}
				else if(valueDate == now){
					alert("交易日期不能是今天！");
					return true;
				}
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
	</script>
  </head>
  
  <body>
  	<form id="form1" name="form1" method="post">
  	  <div id="all">
    	<div id="top">
  		<input type="radio" name="tradeType" onclick="selectTradeType()" value="0" <ww:if test="#request.radioValue == 0">checked</ww:if>>Spot
  		<input type="radio" name="tradeType" style="margin-left: 40px;" onclick="selectTradeType()" value="1" <ww:if test="#request.radioValue == 1">checked</ww:if>>Forward
  		<input type="radio" name="tradeType" style="margin-left: 40px;" onclick="selectTradeType()" value="2" <ww:if test="#request.radioValue == 2">checked</ww:if>>Swap
    	</div>
    	<div id="container">
    		<div id="wrapOne">
	  			<label><span id="direction"></span><ww:property value="#request.ccy1"/> vs <ww:property value="#request.ccy2"/></label>
		  		<input type="hidden" name="forwardTradeModel.direction" value='<ww:property value="#request.tradeDirection"/>'>
		  		<input type="hidden" name="forwardTradeModel.weCcy" value='<ww:property value="#request.ccy1"/>'>
		  		<input type="hidden" name="forwardTradeModel.anaCcy" value='<ww:property value="#request.ccy2"/>'>
	  			<label style="margin-left:50px;">Amount:</label>
	  			<input name="forwardTradeModel.amount" id="amount" type="text" size="15" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
	  			<label style="margin-left:50px;">Price:</label><label><ww:property value="#request.price"/></label>
		  		<input type="hidden" name="forwardTradeModel.price" value='<ww:property value="#request.price"/>'> 
		  		<input type="hidden" value="0" id="point">
		  		<input type="hidden" value='<ww:property value="#request.price"/>' id="price">
  			</div>
  			<div class="modules">
				<span style="margin-left: 20px; margin-right: 17px;">Value Date:</span><input name="forwardTradeModel.date" id="date" type="text" dataType="date" size="15"/>
				 <img onClick="WdatePicker({el:'date'})"src="resources/calendar/image/Button.gif" id="imgid1"align="absMiddle" border="0" />
  			</div>
  			<div class="modules">
  				<span class="provider">Provider</span>
  				<span style="font-size: 12px;">Bid Forward Pts</span>
  				<span class="xiegan1" style="margin: 0px 10px;">/</span>
  				<span style="font-size: 12px;">Ask Forward Pts</span>
  			</div>
  			
  			<div class="modules">
  					<!-- 第一对方手 -->
	  				<select id="opponentOne" name="forwardTradeModel.opponentOne" class="opponents" style="width: 100px; margin-right: 30px;" onchange="showOpponentOne()">
	  				<option></option>
	  					<ww:iterator value="#request.opponentList" status="index">
							<option value="<ww:property value="userNum"/>" <ww:if test="%{id==model.belong}">selected</ww:if>><ww:property value="name"/></option>
						</ww:iterator>
	  				</select>
	  				<input id="btn_bid_one" type="button" width="20px;" value="-" disabled="disabled" onclick="subtractionByPoint(this)">
	  				<input name="forwardTradeModel.bidOne" id="bid_one" type="text" size="7" disabled="disabled">
	  				<input id="btn_bid_two" type="button" width="20px;" value="+" disabled="disabled" onclick="additionByPoint(this)">
	  				<span id="price11"><ww:property value="#request.price"/></span>
	  				<span class="xiegan">/</span>
	  				<span id="price21"><ww:property value="#request.price"/></span>
	  				<input id="btn_ask_one" type="button" width="20px;" value="-" disabled="disabled" onclick="subtractionByPoint(this)">
	  				<input name="forwardTradeModel.askOne" id="ask_one" type="text" size="7" disabled="disabled" >
	  				<input id="btn_ask_two" type="button" width="20px;" value="+" disabled="disabled" onclick="additionByPoint(this)">
	  				<input type="button" value="Accept" class="button1" id="acceptOne" name="accept"  onclick="forwardByAccept(this)" disabled="disabled">
	  				<input id="argueOne" type="button" value="Argue" class="button1" onclick="displayOne('<ww:property value="#request.tradeDirection"/>')" disabled="disabled">
	  				<input type="button" value="Send" class="button1" id="sendOne" name="send" onclick="forwardBySend(1)" disabled="disabled">
	  		</div>
	  		<div class="modules">
	  				<!-- 第二对方手 -->
	  				<select id="opponentTwo" name="forwardTradeModel.opponentTwo" class="opponents" style="width: 100px; margin-right: 30px;" onchange="showOpponentTwo()">
	  					<option></option>
	  					<ww:iterator value="#request.opponentList" status="index">
							<option value="<ww:property value="userNum"/>" <ww:if test="%{id==model.belong}">selected</ww:if>><ww:property value="name"/></option>
						</ww:iterator>
	  				</select>
	  				<input id="btn_bid_three" type="button" width="20px;" value="-" disabled="disabled" onclick="subtractionByPoint(this)">
	  				<input name="forwardTradeModel.bidTwo" id="bid_two" type="text" size="7" disabled="disabled">
	  				<input id="btn_bid_four" type="button" width="20px;" value="+" disabled="disabled" onclick="additionByPoint(this)">
	  				<span id="price12"><ww:property value="#request.price"/></span>
	  				<span class="xiegan">/</span>
	  				<span id="price22"><ww:property value="#request.price"/></span>
	  				<input id="btn_ask_three" type="button" width="20px;" value="-" disabled="disabled" onclick="subtractionByPoint(this)">
	  				<input name="forwardTradeModel.askTwo" id="ask_two" type="text" size="7" disabled="disabled" >
	  				<input id="btn_ask_four" type="button" width="20px;" value="+" disabled="disabled" onclick="additionByPoint(this)">
	  				<input type="button" value="Accept" class="button1" id="acceptTwo" name="accept"  onclick="forwardByAccept(this)" disabled="disabled">
	  				<input id="argueTwo" type="button" value="Argue" class="button1" onclick="displayTwo('<ww:property value="#request.tradeDirection"/>')" disabled="disabled">
	  				<input type="button" value="Send" class="button1" id="sendTwo" name="send" onclick="forwardBySend(2)" disabled="disabled">
	  		</div>
	  		<div class="modules">
	  				<!-- 第三对方手 -->
	  				<select id="opponentThree" name="forwardTradeModel.opponentThree" class="opponents" style="width: 100px; margin-right: 30px;" onchange="showOpponentThree()">
	  				<option></option>
	  					<ww:iterator value="#request.opponentList" status="index">
							<option value="<ww:property value="userNum"/>" <ww:if test="%{id==model.belong}">selected</ww:if>><ww:property value="name"/></option>
						</ww:iterator>
	  				</select>
	  				<input id="btn_bid_five" type="button" width="20px;" value="-" disabled="disabled" onclick="subtractionByPoint(this)">
	  				<input name="forwardTradeModel.bidThree" id="bid_three" type="text" size="7" disabled="disabled">
	  				<input id="btn_bid_six" type="button" width="20px;" value="+" disabled="disabled" onclick="additionByPoint(this)">
	  				<span id="price13"><ww:property value="#request.price"/></span>
	  				<span class="xiegan">/</span>
	  				<span id="price23"><ww:property value="#request.price"/></span>
	  				<input id="btn_ask_five" type="button" width="20px;" value="-" disabled="disabled" onclick="subtractionByPoint(this)">
	  				<input name="forwardTradeModel.askThree" id="ask_three" type="text" size="7" disabled="disabled">
	  				<input id="btn_ask_six" type="button" width="20px;" value="+" disabled="disabled" onclick="additionByPoint(this)">
	  				<input type="button" value="Accept" class="button1" id="acceptThree" name="accept"  onclick="forwardByAccept(this)" disabled="disabled">
	  				<input id="argueThree" type="button" value="Argue" class="button1" onclick="displayThree('<ww:property value="#request.tradeDirection"/>')" disabled="disabled">
	  				<input type="button" value="Send" class="button1" id="sendThree" name="send" onclick="forwardBySend(3)" disabled="disabled">
	  		</div>
	  		<div class="modules">
	  				<!-- 第四对方手 -->
	  				<select id="opponentFour" name="forwardTradeModel.opponentFour" class="opponents" style="width: 100px; margin-right: 30px;" onchange="showOpponentFour()">
	  				<option></option>
	  					<ww:iterator value="#request.opponentList" status="index">
							<option value="<ww:property value="userNum"/>" <ww:if test="%{id==model.belong}">selected</ww:if>><ww:property value="name"/></option>
						</ww:iterator>
	  				</select>
	  				<input id="btn_bid_seven" type="button" width="20px;" value="-" disabled="disabled" onclick="subtractionByPoint(this)">
	  				<input name="forwardTradeModel.bidFour" id="bid_four" type="text" size="7" disabled="disabled">
	  				<input id="btn_bid_eight" type="button" width="20px;" value="+" disabled="disabled" onclick="additionByPoint(this)">
	  				<span id="price14"><ww:property value="#request.price"/></span>
	  				<span class="xiegan">/</span>
	  				<span id="price24"><ww:property value="#request.price"/></span>
	  				<input id="btn_ask_seven" type="button" width="20px;" value="-" disabled="disabled" onclick="subtractionByPoint(this)">
	  				<input name="forwardTradeModel.askFour" id="ask_four" type="text" size="7" disabled="disabled">
	  				<input id="btn_ask_eight" type="button" width="20px;" value="+" disabled="disabled" onclick="additionByPoint(this)">
	  				<input type="button" value="Accept" class="button1" id="acceptFour" name="accept"  onclick="forwardByAccept(this)" disabled="disabled">
	  				<input id="argueFour" type="button" value="Argue" class="button1" onclick="displayFour('<ww:property value="#request.tradeDirection"/>')" disabled="disabled">
	  				<input type="button" value="Send" class="button1" id="sendFour" name="send" onclick="forwardBySend(4)" disabled="disabled">
	  		</div>
	  		<div class="modules">
	  				<!-- 第五对方手 -->
	  				<select id="opponentFive" name="forwardTradeModel.opponentFive" class="opponents" style="width: 100px; margin-right: 30px;" onchange="showOpponentFive()">
	  				<option></option>
	  					<ww:iterator value="#request.opponentList" status="index">
							<option value="<ww:property value="userNum"/>" <ww:if test="%{id==model.belong}">selected</ww:if>><ww:property value="name"/></option>
						</ww:iterator>
	  				</select>
	  				<input id="btn_bid_nine" type="button" width="20px;" value="-" disabled="disabled" onclick="subtractionByPoint(this)">
	  				<input name="forwardTradeModel.bidFive" id="bid_five" type="text" size="7" disabled="disabled">
	  				<input id="btn_bid_ten" type="button" width="20px;" value="+" disabled="disabled" onclick="additionByPoint(this)">
	  				<span id="price15"><ww:property value="#request.price"/></span>
	  				<span class="xiegan">/</span>
	  				<span id="price25"><ww:property value="#request.price"/></span>
	  				<input id="btn_ask_nine" type="button" width="20px;" value="-" disabled="disabled" onclick="subtractionByPoint(this)">
	  				<input name="forwardTradeModel.askFive" id="ask_five" type="text" size="7" disabled="disabled">
	  				<input id="btn_ask_ten" type="button" width="20px;" value="+" disabled="disabled" onclick="additionByPoint(this)">
	  				<input type="button" value="Accept" class="button1" id="acceptFive" name="accept"  onclick="forwardByAccept(this)" disabled="disabled">
	  				<input id="argueFive" type="button" value="Argue" class="button1" onclick="displayFive('<ww:property value="#request.tradeDirection"/>')" disabled="disabled">
	  				<input type="button" value="Send" class="button1" id="sendFive" name="send" onclick="forwardBySend(5)" disabled="disabled">
	  		</div>
	  			
  			<div>
	  			<br>
	  			<span style="width: 150px; margin-left: 40px;">交易状态：</span><input id="status" value="" disabled="disabled" type="text">
	  		</div>
    	</div>
    	
    	<div id="top">
			<input id="submit" name="submit" type="button" value="submit" style="margin-right: 150px;" class="button2" onclick="submitAll()">
			<input id="withdraw" name="withdraw" type="button" value="withdraw" class="button2" onclick="withdrawByForward()">
			<input type="hidden" id="tranNo1" name="forwardTradeModel.tranNo1" value="">
			<input type="hidden" id="tranNo2" name="forwardTradeModel.tranNo2" value="">
			<input type="hidden" id="tranNo3" name="forwardTradeModel.tranNo3" value="">
			<input type="hidden" id="tranNo4" name="forwardTradeModel.tranNo4" value="">
			<input type="hidden" id="tranNo5" name="forwardTradeModel.tranNo5" value="">
    	</div>
    </div>
  	</form>
  </body>
</html>
