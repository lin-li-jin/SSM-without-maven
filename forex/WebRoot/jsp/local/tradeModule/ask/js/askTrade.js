function subtraction(obj){
	var val;
	if(obj.id=="btn_bid_one"){
		val = document.getElementById("bid_one");
	}
	else if(obj.id=="btn_bid_three"){
		val = document.getElementById("bid_two");
	}
	else if(obj.id=="btn_bid_five"){
		val = document.getElementById("bid_three");
	}
	else if(obj.id=="btn_bid_seven"){
		val = document.getElementById("bid_four");
	}
	else if(obj.id=="btn_bid_nine"){
		val = document.getElementById("bid_five");
	}
	else if(obj.id=="btn_ask_one"){
		val = document.getElementById("ask_one");
	}
	else if(obj.id=="btn_ask_three"){
		val = document.getElementById("ask_two");
	}
	else if(obj.id=="btn_ask_five"){
		val = document.getElementById("ask_three");
	}
	else if(obj.id=="btn_ask_seven"){
		val = document.getElementById("ask_four");
	}
	else if(obj.id=="btn_ask_nine"){
		val = document.getElementById("ask_five");
	}
	else if(obj.id=="bid_down"){
		val = document.getElementById("bid_price");
	}
	else if(obj.id=="ask_down"){
		val = document.getElementById("ask_price");
	}
	val.value = (window.parseFloat(val.value) - 0.000100000000000001).toString().substring(0, 6);
}
function addition(obj){
	var val;
	if(obj.id=="btn_bid_two"){
		val = document.getElementById("bid_one");
	}
	else if(obj.id=="btn_bid_four"){
		val = document.getElementById("bid_two");
	}
	else if(obj.id=="btn_bid_six"){
		val = document.getElementById("bid_three");
	}
	else if(obj.id=="btn_bid_eight"){
		val = document.getElementById("bid_four");
	}
	else if(obj.id=="btn_bid_ten"){
		val = document.getElementById("bid_five");
	}
	else if(obj.id=="btn_ask_two"){
		val = document.getElementById("ask_one");
	}
	else if(obj.id=="btn_ask_four"){
		val = document.getElementById("ask_two");
	}
	else if(obj.id=="btn_ask_six"){
		val = document.getElementById("ask_three");
	}
	else if(obj.id=="btn_ask_eight"){
		val = document.getElementById("ask_four");
	}
	else if(obj.id=="btn_ask_ten"){
		val = document.getElementById("ask_five");
	}
	else if(obj.id=="bid_up"){
		val = document.getElementById("bid_price");
	}
	else if(obj.id=="ask_up"){
		val = document.getElementById("ask_price");		
	}
	val.value = (window.parseFloat(val.value) + 0.00010000001).toString().substring(0, 6);
}
function displayOne(value){
	if(document.getElementById("opponentOne").value == '' || document.getElementById("opponentOne").value == null){
		alert("请选择对手方！");
	}else{
		if(value == 1){//如果是买，则bid部分被激活
			document.getElementById("bid_one").disabled = false;
			document.getElementById("btn_bid_one").disabled = false;
			document.getElementById("btn_bid_two").disabled = false;			
		}else{//如果是卖，则ask部分被激活
			document.getElementById("ask_one").disabled = false;
			document.getElementById("btn_ask_one").disabled = false;
			document.getElementById("btn_ask_two").disabled = false;
		}
		//accept、argue被禁用，send按钮被激活
		document.getElementById("acceptOne").disabled = true;
		document.getElementById("argueOne").disabled = true;
		document.getElementById("sendOne").disabled = false;
	}
}
function displayTwo(value){
	if(document.getElementById("opponentTwo").value=='' || document.getElementById("opponentTwo").value == null){
		alert("请选择对手方！");
	}else{
		if(value == 1){//如果是买，则bid部分被激活
			document.getElementById("bid_two").disabled = false;
			document.getElementById("btn_bid_three").disabled = false;
			document.getElementById("btn_bid_four").disabled = false;			
		}else{//如果是卖，则ask部分被激活
			document.getElementById("ask_two").disabled = false;
			document.getElementById("btn_ask_three").disabled = false;
			document.getElementById("btn_ask_four").disabled = false;
		}
		//accept、argue被禁用，send按钮被激活
		document.getElementById("acceptTwo").disabled = true;
		document.getElementById("argueTwo").disabled = true;
		document.getElementById("sendTwo").disabled = false;
	}
}
function displayThree(value){
	if(document.getElementById("opponentThree").value=='' || document.getElementById("opponentThree").value == null){
		alert("请选择对手方！");
	}else{
		if(value == 1){//如果是买，则bid部分被激活
			document.getElementById("bid_three").disabled = false;
			document.getElementById("btn_bid_five").disabled = false;
			document.getElementById("btn_bid_six").disabled = false;			
		}else{//如果是卖，则ask部分被激活
			document.getElementById("ask_three").disabled = false;
			document.getElementById("btn_ask_five").disabled = false;
			document.getElementById("btn_ask_six").disabled = false;
		}
		//accept、argue被禁用，send按钮被激活
		document.getElementById("acceptThree").disabled = true;
		document.getElementById("argueThree").disabled = true;
		document.getElementById("sendThree").disabled = false;
	}
}
function displayFour(value){
	if(document.getElementById("opponentFour").value=='' || document.getElementById("opponentFour").value == null){
		alert("请选择对手方！");
	}else{
		if(value == 1){//如果是买，则bid部分被激活
			document.getElementById("bid_four").disabled = false;
			document.getElementById("btn_bid_seven").disabled = false;
			document.getElementById("btn_bid_eight").disabled = false;			
		}else{//如果是卖，则ask部分被激活
			document.getElementById("ask_four").disabled = false;
			document.getElementById("btn_ask_seven").disabled = false;
			document.getElementById("btn_ask_eight").disabled = false;
		}
		//accept、argue被禁用，send按钮被激活
		document.getElementById("acceptFour").disabled = true;
		document.getElementById("argueFour").disabled = true;
		document.getElementById("sendFour").disabled = false;
	}
}
function displayFive(value){
	if(document.getElementById("opponentFive").value=='' || document.getElementById("opponentFive").value == null){
		alert("请选择对手方！");
	}else{
		if(value == 1){//如果是买，则bid部分被激活
			document.getElementById("bid_five").disabled = false;
			document.getElementById("btn_bid_nine").disabled = false;
			document.getElementById("btn_bid_ten").disabled = false;			
		}else{//如果是卖，则ask部分被激活
			document.getElementById("ask_five").disabled = false;
			document.getElementById("btn_ask_nine").disabled = false;
			document.getElementById("btn_ask_ten").disabled = false;
		}
		//accept、argue被禁用，send按钮被激活
		document.getElementById("acceptFive").disabled = true;
		document.getElementById("argueFive").disabled = true;
		document.getElementById("sendFive").disabled = false;
	}
}
function subtractionByPoint(obj){
	var price=document.getElementById("price").value;
	if(obj.id=="btn_bid_one"){
		var val = document.getElementById("bid_one");
		val.value = window.parseInt(val.value) - 1;
		var dd=(window.parseFloat(price))+window.parseFloat(val.value)/10000;
		dd=window.parseFloat((Math.round(dd*10000))/10000);
		document.getElementById("price11").innerHTML = dd+"";
	}
	else if(obj.id=="btn_bid_three"){
		var val = document.getElementById("bid_two");
		val.value = window.parseInt(val.value) - 1;
		var dd=(window.parseFloat(price))+window.parseFloat(val.value)/10000;
		dd=window.parseFloat((Math.round(dd*10000))/10000);
		document.getElementById("price12").innerHTML = dd+"";
	}
	else if(obj.id=="btn_bid_five"){
		var val = document.getElementById("bid_three");
		val.value = window.parseInt(val.value) - 1;
		var dd=(window.parseFloat(price))+window.parseFloat(val.value)/10000;
		dd=window.parseFloat((Math.round(dd*10000))/10000);
		document.getElementById("price13").innerHTML = dd+"";
	}
	else if(obj.id=="btn_bid_seven"){
		var val = document.getElementById("bid_four");
		val.value = window.parseInt(val.value) - 1;
		var dd=(window.parseFloat(price))+window.parseFloat(val.value)/10000;
		dd=window.parseFloat((Math.round(dd*10000))/10000);
		document.getElementById("price14").innerHTML = dd+"";
	}
	else if(obj.id=="btn_bid_nine"){
		var val = document.getElementById("bid_five");
		val.value = window.parseInt(val.value) - 1;
		var dd=(window.parseFloat(price))+window.parseFloat(val.value)/10000;
		dd=window.parseFloat((Math.round(dd*10000))/10000);
		document.getElementById("price15").innerHTML = dd+"";
	}
	else if(obj.id=="btn_ask_one"){
		var val = document.getElementById("ask_one");
		val.value = window.parseInt(val.value) - 1;
		var dd=(window.parseFloat(price))+window.parseFloat(val.value)/10000;
		dd=window.parseFloat((Math.round(dd*10000))/10000);
		document.getElementById("price21").innerHTML = dd+"";
	}
	else if(obj.id=="btn_ask_three"){
		var val = document.getElementById("ask_two");
		val.value = window.parseInt(val.value) - 1;
		var dd=(window.parseFloat(price))+window.parseFloat(val.value)/10000;
		dd=window.parseFloat((Math.round(dd*10000))/10000);
		document.getElementById("price22").innerHTML = dd+"";
	}
	else if(obj.id=="btn_ask_five"){
		var val = document.getElementById("ask_three");
		val.value = window.parseInt(val.value) - 1;
		var dd=(window.parseFloat(price))+window.parseFloat(val.value)/10000;
		dd=window.parseFloat((Math.round(dd*10000))/10000);
		document.getElementById("price23").innerHTML = dd+"";
	}
	else if(obj.id=="btn_ask_seven"){
		var val = document.getElementById("ask_four");
		val.value = window.parseInt(val.value) - 1;
		var dd=(window.parseFloat(price))+window.parseFloat(val.value)/10000;
		dd=window.parseFloat((Math.round(dd*10000))/10000);
		document.getElementById("price24").innerHTML = dd+"";
	}
	else if(obj.id=="btn_ask_nine"){
		var val = document.getElementById("ask_five");
		val.value = window.parseInt(val.value) - 1;
		var dd=(window.parseFloat(price))+window.parseFloat(val.value)/10000;
		dd=window.parseFloat((Math.round(dd*10000))/10000);
		document.getElementById("price25").innerHTML = dd+"";
	}
}
function additionByPoint(obj){
	var price=document.getElementById("price").value;
	if(obj.id=="btn_bid_two"){
		var val = document.getElementById("bid_one");
		val.value = window.parseInt(val.value) + 1;
		var dd=(window.parseFloat(price))+window.parseFloat(val.value)/10000;
		dd=window.parseFloat((Math.round(dd*10000))/10000);
		document.getElementById("price11").innerHTML = dd+"";
	}
	else if(obj.id=="btn_bid_four"){
		var val = document.getElementById("bid_two");
		val.value = window.parseInt(val.value) + 1;
		var dd=(window.parseFloat(price))+window.parseFloat(val.value)/10000;
		dd=window.parseFloat((Math.round(dd*10000))/10000);
		document.getElementById("price12").innerHTML = dd+"";
	}
	else if(obj.id=="btn_bid_six"){
		var val = document.getElementById("bid_three");
		val.value = window.parseInt(val.value) + 1;
		var dd=(window.parseFloat(price))+window.parseFloat(val.value)/10000;
		dd=window.parseFloat((Math.round(dd*10000))/10000);
		document.getElementById("price13").innerHTML = dd+"";
	}
	else if(obj.id=="btn_bid_eight"){
		var val = document.getElementById("bid_four");
		val.value = window.parseInt(val.value) + 1;
		var dd=(window.parseFloat(price))+window.parseFloat(val.value)/10000;
		dd=window.parseFloat((Math.round(dd*10000))/10000);
		document.getElementById("price14").innerHTML = dd+"";
	}
	else if(obj.id=="btn_bid_ten"){
		var val = document.getElementById("bid_five");
		val.value = window.parseInt(val.value) + 1;
		var dd=(window.parseFloat(price))+window.parseFloat(val.value)/10000;
		dd=window.parseFloat((Math.round(dd*10000))/10000);
		document.getElementById("price15").innerHTML = dd+"";
	}
	else if(obj.id=="btn_ask_two"){
		var val = document.getElementById("ask_one");
		val.value = window.parseInt(val.value) + 1;
		var dd=(window.parseFloat(price))+window.parseFloat(val.value)/10000;
		dd=window.parseFloat((Math.round(dd*10000))/10000);
		document.getElementById("price21").innerHTML = dd+"";
	}
	else if(obj.id=="btn_ask_four"){
		var val = document.getElementById("ask_two");
		val.value = window.parseInt(val.value) + 1;
		var dd=(window.parseFloat(price))+window.parseFloat(val.value)/10000;
		dd=window.parseFloat((Math.round(dd*10000))/10000);
		document.getElementById("price22").innerHTML = dd+"";
	}
	else if(obj.id=="btn_ask_six"){
		var val = document.getElementById("ask_three");
		val.value = window.parseInt(val.value) + 1;
		var dd=(window.parseFloat(price))+window.parseFloat(val.value)/10000;
		dd=window.parseFloat((Math.round(dd*10000))/10000);
		document.getElementById("price23").innerHTML = dd+"";
	}
	else if(obj.id=="btn_ask_eight"){
		var val = document.getElementById("ask_four");
		val.value = window.parseInt(val.value) + 1;
		var dd=(window.parseFloat(price))+window.parseFloat(val.value)/10000;
		dd=window.parseFloat((Math.round(dd*10000))/10000);
		document.getElementById("price24").innerHTML = dd+"";
	}
	else if(obj.id=="btn_ask_ten"){
		var val = document.getElementById("ask_five");
		val.value = window.parseInt(val.value) + 1;
		var dd=(window.parseFloat(price))+window.parseFloat(val.value)/10000;
		dd=window.parseFloat((Math.round(dd*10000))/10000);
		document.getElementById("price25").innerHTML = dd+"";
	}
}
