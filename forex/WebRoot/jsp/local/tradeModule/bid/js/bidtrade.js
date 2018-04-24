function checkOne(){
			var r1=document.getElementById("rOne");
			var r2=document.getElementById("rTwo");
			var img1=document.getElementById("imgid1");
			var d1=document.getElementById("d1");
			if(r1.checked){
				d1.style.display ="none";
				d1.value = "";
				img1.style.display ="none";
			}else if(r2.checked){
				d1.style.display ="inline";
				img1.style.display ="inline";
			}
		}

function checkTwo(){
	var r3=document.getElementById("rThree");
	var r4=document.getElementById("rFour");
	var img2=document.getElementById("imgid2");
	var d2=document.getElementById("d2");
	if(r3.checked){
		d2.style.display ="none";
		d2.value = "";
		img2.style.display ="none";
	}else if(r4.checked){
		d2.style.display ="inline";
		img2.style.display ="inline";
	}
}

/*function selectTradeType(){
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
}*/

/*function changeTrade(){
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
}*/

function selectBackAction(){
	if (document.getElementById("ccyHidden").value == "CNY")
		window.location.href = "CNYPageInit.action";
	else
		window.location.href = "foreignPageInit.action";
}

function marketBreakoutTradeToAction(){
	document.marketBreakoutForm.action = "marketOneTradeRecordAdd.action";
	marketBreakoutTradeValidate();
}

function marketBreakoutTradeValidate(){
	var sAmount = document.getElementById("sAmount").value;
	var sPrice = document.getElementById("sPrice").value;
	var SAmount = document.getElementById("SAmount").value;
	var SPrice = document.getElementById("SPrice").value;
	var temp1 = document.getElementsByName("timeStart");
	var temp2 = document.getElementsByName("timeEnd");
	if (sAmount == '' || sAmount%100 != 0){
		alert("请输入第一个100整数倍的Stop Loss交易金额！");
		return false;
	}
	if (sPrice == '' || isNaN(sPrice)){
		alert("请输入第一个数字格式的Stop Loss价格！");
		return false;
	}
	if (SAmount == '' || SAmount%100 != 0){
		alert("请输入第二个100整数倍的Stop Loss交易金额！");
		return false;
	}
	if (SPrice == '' || isNaN(SPrice)){
		alert("请输入第二个数字格式的Stop Loss价格！");
		return false;
	}
	if (temp1[1].checked){
		var startTime = document.getElementById("d1").value;
		if (startTime == ''){
			alert("请输入激活时间！");
			return false;
		}else{
			var flag = validTimeSize(startTime,'');
			if (flag == false)
				return false;
		}
	}
	if (temp2[1].checked){
		var endTime = document.getElementById("d2").value;
		if (endTime == ''){
			alert("请输入过期时间！");
			return false;
		}
		else{
			flag = validTimeSize(startTime,endTime);
			if (flag == false)
				return false;
		}
	}
	if (typeof(startTime) != "undefined" && typeof(endTime) != "undefined")
	{
		flag = validTimeSize(startTime,endTime);
		if (flag == false)
			return false;
	}
	document.marketBreakoutForm.submit();
}

function validTimeSize(startTime, endTime) {
	var date = getCurrentDate();
	if(startTime != ''){
		if (startTime < date){
			alert("激活日期不能小于当前日期！");
			return false;
		}
	}
	if (endTime != ''){
		if (endTime < date){
			alert("过期日期不能小于当前日期！");
			return false;
		}
	}
	if (startTime != '' && endTime != ''){
		if (startTime > endTime) {
			alert("激活日期不能大于过期日期！", this);
			return false;
		} 
	}
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

function ocoTradeToAction(){
	document.ocoForm.action = "ocoOneTradeRecordAdd.action";
	ocoTradeValidate();
}

function ocoTradeValidate(){
	var tAmount = document.getElementById("tAmount").value;
	var tPrice = document.getElementById("tPrice").value;
	var sAmount = document.getElementById("sAmount").value;
	var sPrice = document.getElementById("sPrice").value;
	var temp1 = document.getElementsByName("timeStart");
	var temp2 = document.getElementsByName("timeEnd");
	if (tAmount == '' || tAmount%100 != 0){
		alert("请输入100整数倍的Take Profit交易金额！");
		return false;
	}
	if (tPrice == '' || isNaN(tPrice)){
		alert("请输入数字格式的Take Profit价格！");
		return false;
	}
	if (sAmount == '' || sAmount%100 != 0){
		alert("请输入100整数倍的Stop Loss交易金额！");
		return false;
	}
	if (sPrice == '' || isNaN(sPrice)){
		alert("请输入数字格式的Stop Loss价格！");
		return false;
	}
	if (temp1[1].checked){
		var startTime = document.getElementById("d1").value;
		if (startTime == ''){
			alert("请输入激活时间！");
			return false;
		}else{
			var flag = validTimeSize(startTime,'');
			if (flag == false)
				return false;
		}
	}
	if (temp2[1].checked){
		var endTime = document.getElementById("d2").value;
		if (endTime == ''){
			alert("请输入过期时间！");
			return false;
		}
		else{
			flag = validTimeSize('',endTime);
			if (flag == false)
				return false;
		}
	}
	if (typeof(startTime) != "undefined" && typeof(endTime) != "undefined")
	{
		flag = validTimeSize(startTime,endTime);
		if (flag == false)
			return false;
	}
	document.ocoForm.submit();
}

function backToAction(){
	if (document.getElementById("hidden").value == "CNY")
		window.location.href="CNYOneClickPageInit.action";
	else
		window.location.href="foreignOneClickPageInit.action";
}

/*function changeTrade2(){
	var trade = document.getElementById("tradeDirect");
	if (trade.innerHTML == "I Buy "){
		trade.innerHTML = "I Sell ";
		document.getElementById("tradeDirSpan").innerHTML = "I Sell ";
		document.getElementById("tradeDirection").value = "0";
		document.getElementById("priceLabel").innerHTML = "<ww:property value='#request.bidPrice' />";
	}
	else{
		trade.innerHTML = "I Buy ";
		document.getElementById("tradeDirSpan").innerHTML = "I Buy ";
		document.getElementById("tradeDirection").value = "1";
		document.getElementById("priceLabel").innerHTML = "<ww:property value='#request.askPrice' />";
	}
}*/

function selectSubmitAction(){
	var temp = document.getElementsByName("tradeType");
	if (temp[0].checked){
		document.profitAndLossForm.action = "stopLossOneTradeRecordAdd.action";
		profitAndLossValidate();
	}
	else{
		document.profitAndLossForm.action = "takeProfitOneTradeRecordAdd.action";
		profitAndLossValidate();
	}
}
function profitAndLossValidate(){
	var amount = document.getElementById("amount").value;
	var price = document.getElementById("price").value;
	var temp1 = document.getElementsByName("timeStart");
	var temp2 = document.getElementsByName("timeEnd");
	if (amount == '' || amount%100 != 0){
		alert("请输入100整数倍交易金额！");
		return false;
	}
	if (price == '' || isNaN(price)){
		alert("请输入数字格式的价格！");
		return false;
	}
	if (temp1[1].checked){
		var startTime = document.getElementById("d1").value;
		if (startTime == ''){
			alert("请输入激活时间！");
			return false;
		}else{
			var flag = validTimeSize(startTime,'');
			if (flag == false)
				return false;
		}
	}
	if (temp2[1].checked){
		var endTime = document.getElementById("d2").value;
		if (endTime == ''){
			alert("请输入过期时间！");
			return false;
		}
		else{
			flag = validTimeSize('',endTime);
			if (flag == false)
				return false;
		}
	}
	if (typeof(startTime) != "undefined" && typeof(endTime) != "undefined")
	{
		flag = validTimeSize(startTime,endTime);
		if (flag == false)
			return false;
	}
	document.profitAndLossForm.submit();
}
