var nextfield="";
function keyHandle(){
	if(event){
		if(event.keyCode==13){
			if(nextfield!="" && document.all(nextfield)){
				document.all(nextfield).focus();
				return;
			}
		}
	}
	return;
}

function gotoPage(page)
{
	this.location = page;
}

function hiddenQueryCondit(){
	if(document.all("hiddenQueryCheckBox").checked){
		document.all("queryConditDiv").style.display = "none";
		document.all("queryButtonDiv").style.display = "none";
	}
	else{
		document.all("queryConditDiv").style.display = "block";
		document.all("queryButtonDiv").style.display = "block";
	}
}

function initHiddenQueryCondit(listSize){
	if(listSize > 0){
		document.all("hiddenQueryCheckBox").checked =  true;
		document.all("queryConditDiv").style.display = "none";
		document.all("queryButtonDiv").style.display = "none";
	}
	else {
		document.all("hiddenQueryCheckBox").checked =  false;
		document.all("queryConditDiv").style.display = "block";
		document.all("queryButtonDiv").style.display = "block";
	}
}

function DP(){
	if(window.print){
		var origHtml = document.body.innerHTML;
		var printHtml = document.all("printReport").innerHTML;
		document.body.innerHTML = printHtml;
		window.print();
		document.body.innerHTML = origHtml;
	}
}

function showAdvanceQueryCondit(){
	if(document.all("showAdvanceQueryConditBn").value == "高级查询"){
		document.all("showAdvanceQueryConditBn").value = "隐藏高级查询";
		document.all("divisionDiv").style.display = "block";
		document.all("advanceQueryConditDiv").style.display = "block";
	}else{
		document.all("showAdvanceQueryConditBn").value = "高级查询";
		document.all("divisionDiv").style.display = "none";
		document.all("advanceQueryConditDiv").style.display = "none";		
	}
}

function selectAllItem(arr,bt,btSelVal,btUnSelVal){
	if(bt.value == btSelVal){
		bt.value = btUnSelVal;
		if((typeof arr.length) == "undefined"){
			arr.checked = true;
		}
		else{
			for(i=0; i<arr.length; i++){
				arr[i].checked = true;
			}
		}
	}
	else {
		bt.value = btSelVal;
		if((typeof arr.length) == "undefined"){
			arr.checked = false;
		}
		else{
			for(i=0; i<arr.length; i++){
				arr[i].checked = false;
			}
		}
	}
}

function selectItemCheck(arr,msg){
	var count = 0;
	if((typeof arr.length) == "undefined"){
		if(arr.checked){
			count ++;
		}
	}
	else{
		for(i=0; i<arr.length; i++){
			if(arr[i].checked){
				count ++;
				break;
			}
		}
	}
	
	if(count == 0){
		alert(msg);
		return false;
	}
	
	return true;
}

function getTodayFmtStr(){
	var dt = new Date();
	var year = dt.getFullYear();
	var month = dt.getMonth() + 1;
	var date = dt.getDate();
	
	if(month < 10){
		month = "0" + month;
	}
	
	if(date < 10){
		date = "0" + date;
	}
	
	return year + "-" + month + "-" + date;	
}
 
//ggm 20140223
/*
function processTip() {
	document.body.innerHTML= "<div id='processStatTip'><div id='processStatTipBorder'><ul><li><img src='resources/image/loading.gif' />&nbsp;&nbsp;系统处理中，请稍候 . . . . . .	</li></div></div>";
}
*/
function processTip() {
	document.body.innerHTML= "<div id='processStatTip'><div id='processStatTipBorder'><ul><li><img src='/forex/resources/image/loading.gif' />&nbsp;&nbsp;系统处理中，请稍候 . . . . . .	</li></div></div>";
}


//格式化金额（theObj.value的值为分）
function formatNum(theObj){ 

if(theObj.value.length != 0) {
var Criteria2=/[^0-9\-]/;		//数字及“-” 


msgName = "金额";
str = "注意！";
var A2=Criteria2.exec(theObj.value.substr(0));
if(A2)
{
	alert(str + A2[0] + "不是一个合法的字符!\n"+msgName+"只能是阿拉伯数字、负号组成。");
	theObj.focus();
	return false;
}


var r=null;
if (str.indexOf("-")!=-1)
{
	if (str.indexOf("-")!=0)
	{
		alert(msg + str + "不是一个合法的字符!\n"+msgName+"的负号只能放第一位！")
		theObj.focus();
		return false;
	}
	r=str.indexOf("-")+1;
	if (str.indexOf("-",r)!=-1)
	{
		alert(msg + str + "不是一个合法的字符!\n"+msgName+"的负号只能有一个！") 
		theObj.focus();
		return false;
	}  
}	

theObj.value = theObj.value.substring(0,theObj.value.length-2) + "." + 
								theObj.value.substring(theObj.value.length-2,theObj.value.length) ;
var digit = theObj.value.indexOf("."); // 取得小数点的位置 
var int = theObj.value.substr(0,digit); // 取得小数中的整数部分
var i; 
var mag = new Array(); 
var word; 

if (theObj.value.indexOf(".") == -1) { // 整数时 
	i = theObj.value.length; // 整数的个数 
	while(i > 0) { 
		word = theObj.value.substring(i,i-3); // 每隔3位截取一组数字 
		i-= 3; 
		mag.unshift(word); // 分别将截取的数字压入数组 
	} 
	theObj.value = mag; 
} 
else{ // 小数时 
	i = int.length; // 除小数外，整数部分的个数 
	while(i > 0) { 
		word = int.substring(i,i-3); // 每隔3位截取一组数字 
		i-= 3; 
		mag.unshift(word); 
	} 
theObj.value = mag + theObj.value.substring(digit); 
} 

}
}


function Arabia_to_Chinese(Num,obj){
   for(i=Num.length-1;i>=0;i--)
   {
    Num = Num.replace(",","")//替换tomoney()中的“,”
    Num = Num.replace(" ","")//替换tomoney()中的空格
   }
   Num = Num.replace("￥","")//替换掉可能出现的￥字符
   if(isNaN(Num)) { //验证输入的字符是否为数字
    alert("请检查小写金额是否正确");
    return;
   }
   //---字符处理完毕，+GEY国w@8jIN0Kx!开始转换，转换采用前后两部分分别转换---//
   part = String(Num).split(".");
   newchar = ""; 
   //小数点前进行转化
   for(i=part[0].length-1;i>=0;i--){
   if(part[0].length > 10){ alert("位数过大，无法计算");return "";}//若数量超过拾亿单位，提示
    tmpnewchar = ""
    perchar = part[0].charAt(i);
    switch(perchar){
    case "0": tmpnewchar="零" + tmpnewchar ;break;
    case "1": tmpnewchar="壹" + tmpnewchar ;break;
    case "2": tmpnewchar="贰" + tmpnewchar ;break;
    case "3": tmpnewchar="叁" + tmpnewchar ;break;
    case "4": tmpnewchar="肆" + tmpnewchar ;break;
    case "5": tmpnewchar="伍" + tmpnewchar ;break;
    case "6": tmpnewchar="陆" + tmpnewchar ;break;
    case "7": tmpnewchar="柒" + tmpnewchar ;break;
    case "8": tmpnewchar="捌" + tmpnewchar ;break;
    case "9": tmpnewchar="玖" + tmpnewchar ;break;
    }
    switch(part[0].length-i-1){
    case 0: tmpnewchar = tmpnewchar +"元" ;break;
    case 1: if(perchar!=0)tmpnewchar= tmpnewchar +"拾" ;break;
    case 2: if(perchar!=0)tmpnewchar= tmpnewchar +"佰" ;break;
    case 3: if(perchar!=0)tmpnewchar= tmpnewchar +"仟" ;break;
    case 4: tmpnewchar= tmpnewchar +"万" ;break;
    case 5: if(perchar!=0)tmpnewchar= tmpnewchar +"拾" ;break;
    case 6: if(perchar!=0)tmpnewchar= tmpnewchar +"佰" ;break;
    case 7: if(perchar!=0)tmpnewchar= tmpnewchar +"仟" ;break;
    case 8: tmpnewchar= tmpnewchar +"亿" ;break;
    case 9: tmpnewchar= tmpnewchar +"拾" ;break;
    }
    newchar = tmpnewchar + newchar;
   }
   //小数点之后进行转化
   if(Num.indexOf(".")!=-1){
   if(part[1].length > 2) {
    alert("小数点之后只能保留两位,系统将自动截段");
    part[1] = part[1].substr(0,2)
    }
   for(i=0;i<part[1].length;i++){
    tmpnewchar = ""
    perchar = part[1].charAt(i)
    switch(perchar){
    case "0": tmpnewchar="零" + tmpnewchar ;break;
    case "1": tmpnewchar="壹" + tmpnewchar ;break;
    case "2": tmpnewchar="贰" + tmpnewchar ;break;
    case "3": tmpnewchar="叁" + tmpnewchar ;break;
    case "4": tmpnewchar="肆" + tmpnewchar ;break;
    case "5": tmpnewchar="伍" + tmpnewchar ;break;
    case "6": tmpnewchar="陆" + tmpnewchar ;break;
    case "7": tmpnewchar="柒" + tmpnewchar ;break;
    case "8": tmpnewchar="捌" + tmpnewchar ;break;
    case "9": tmpnewchar="玖" + tmpnewchar ;break;
    }
    if(i==0)tmpnewchar =tmpnewchar + "角";
    if(i==1)tmpnewchar = tmpnewchar + "分";
    newchar = newchar + tmpnewchar;
   }
   }
   //替换所有无用汉字
   while(newchar.search("零零") != -1)
    newchar = newchar.replace("零零", "零");
   newchar = newchar.replace("零亿", "亿");
   newchar = newchar.replace("亿万", "亿");
   newchar = newchar.replace("零万", "万");
   newchar = newchar.replace("零元", "元");
   newchar = newchar.replace("零角", "");
   newchar = newchar.replace("零分", "");

   if (newchar.charAt(newchar.length-1) == "元" || newchar.charAt(newchar.length-1) == "角")
    newchar = newchar+"整"
   obj.innerText = newchar;
  }

function unFormatNum(theObj) {
	var reg=/[(,+)(.+)]/;		//
	alert(theObj.value.replace(reg,""));
}

function ChangeAmountFormat(obj) {
	
	var amountVal = obj.value;
	amountVal = amountVal.replace(/(^\s*)|(\s*$)/g, "");
	
	if (amountVal == '') {
		amountVal = '';
	}
	
	amountVal = amountVal.replace(/,|(\.)/g,"");
	
	
	if (amountVal.length >2) {
		amountVal = amountVal.substring(0,amountVal.length-2) + amountVal.substring(amountVal.length-2,amountVal.length)
		
	}
	else if (amountVal.length == 2){
		amountVal = "0." + amountVal;
	}
	else if (amountVal.length == 1){
		amountVal = "0.0" + amountVal;
	}
	else if (amountVal.length == 0){
		amountVal = '';
	}	

	/*
	//var amount = new Number(amountVal);
	var amount = parseFloat(amountVal) /100;
	//amount =  new Number(amount / 100);
	amountVal = amount.toString();
		
	if (amountVal.indexOf(".") <0) {
		amountVal = amountVal + ".00";
	}
	*/
	//alert(amountVal);
	
	obj.value = amountVal;
	
}


//半角转换为全角函数
function ToDBC(txtstring) { 
	var tmp = ""; 
	for(var i=0;i<txtstring.length;i++) { 
		if(txtstring.charCodeAt(i)==32) { 
			tmp= tmp+ String.fromCharCode(12288); 
		} 
		else if(txtstring.charCodeAt(i)<127) { 
			tmp= tmp + String.fromCharCode(txtstring.charCodeAt(i)+65248); 
		}
		else{
			tmp = tmp + String.fromCharCode(txtstring.charCodeAt(i));
		} 

	} 
	
	return tmp; 
} 

//全角转换为半角函数 
function ToCDB(str) { 
	var tmp = ""; 
	for(var i=0;i<str.length;i++) { 
		if(str.charCodeAt(i)>65248&&str.charCodeAt(i)<65375) { 
			tmp += String.fromCharCode(str.charCodeAt(i)-65248); 
		} 
		else { 
			tmp += String.fromCharCode(str.charCodeAt(i)); 
		} 
	}
	 
	return tmp 
} 

function formatDecimal(aDecimal, aComma, aDecNumber, aHandleNegFlag, aShowNegInRedFlag) {
// aDecimal - the decimal you want to format
// aComma - defines if the output format uses comma as separation (0: not use, otherwise: use)
// aDecNumber - defines how many decimal places (if aDecNumber is negative, don't format the part of decimal)
// aHandleNegFlag - defines how to handle the negative value (0: don't handle the negative value specially, otherwise: use '(1234)' instead of '-1234')
// aShowRegInNedFlag = defines whether to show negative value in red (0: don't show red, otherwise: show)
	var dpIndex, eIndex
	var zero = ""
	var temp = ""
	var sign = "+"
	var partOfInt, partOfDec
	var extra = ""
	var count = 0

    // Change to string
	aDecimal = aDecimal + ""

	if (aDecimal == "" || aDecimal == null) {
		return aDecimal
	}

	// determind the last character is not a number
	// if yes, backup it and remove it from the decimal temparary(e.g. -1234.123412% -> -1234.123412)
	if (isNaN(aDecimal.substring(aDecimal.length - 1, aDecimal.length))) {
		extra = aDecimal.substring(aDecimal.length - 1, aDecimal.length)
		aDecimal = aDecimal.substring(0, aDecimal.length - 1)
	}

	// determind the first character is negative sign
	// if yes, backup it and remove it from the decimal temparary(e.g. -1234.123412 -> 1234.123412)
	if (aDecimal.substring(0, 1) == "-") {
		sign = "-"
		aDecimal = aDecimal.substring(1, aDecimal.length)
	}

	// determind the decimal uses the scientific expression
	// if yes, get the times of 10 and remove the scientific expression(e.g. 123.12341234E12 -> 123.12341234)
	eIndex = aDecimal.indexOf("E")
	if (eIndex != -1) {
		count = parseInt(aDecimal.substring(eIndex + 1, aDecimal.length))
		aDecimal = aDecimal.substring(0, eIndex)
	}

	// get the decimal place
	dpIndex = aDecimal.indexOf(".")

	if (dpIndex == -1) {
	    // the decimal is and integer, seperate the decimal to int part and decimal part
	    // e.g. 12341234 -> partOfInt = "12341234", partOfDec = ""
		partOfInt = aDecimal
		partOfDec = ""
		dpIndex = aDecimal.length
	}else {
	    // the decimal is and decimal, seperate the decimal to int part and decimal part
	    // e.g. 12341.234 -> partOfInt = "12341", partOfDec = "234"
		partOfInt = aDecimal.substring(0, dpIndex)
		partOfDec = aDecimal.substring(dpIndex + 1, aDecimal.length)
	}

	// If the decimal uses the secientific expression, adjust the decimal
	if (count > 0) {
	    // restruct the decimal from int part and decimal part
	    // e.g. partOfInt = "1", partOfInt = "2341234" -> 12341234
		temp = partOfInt + partOfDec
		if (partOfDec.length < count) {
		    // times the decimal and zero
		    // e.g. 12341234 count = 10 (original decimal is 1.2341234E10) -> partOfInt = "12341234000" partOfDec = ""
		    partOfInt = temp
			for (var k = 1; k <= count - partOfDec.length; k++) {
				partOfInt = partOfInt + "0"
			}
			partOfDec = ""
		}else {
		    // move the decimal place
		    // e.g.12341234 count = 4(original decimal is 1.2341234E4) -> partOfInt = "12341" partOfDec = "234"
			partOfInt = temp.substring(0, count + 1)
			partOfDec = temp.substring(count + 1, temp.length)
		}
		// get the decimal place again
		dpIndex = partOfInt.length
	}

	// handle rounding
	if (aDecNumber >= 0) {
	    if (partOfDec.length > aDecNumber) {
	        if (parseInt(partOfDec.substring(aDecNumber, aDecNumber + 1)) > 4) {
	            if (aDecNumber == 0) {
	                partOfInt = (parseInt(partOfInt) + 1) + ""
	                dpIndex = partOfInt.length
	            }else {
	                temp = ""
        	        for (var l = 1; l < aDecNumber; l++) {
        	            temp = temp + "0"
        	        }

        	        var tempDecimal = parseFloat(partOfInt + "." + partOfDec) + parseFloat("0." + temp + "1") + ""
        	        dpIndex = tempDecimal.indexOf(".")
	                partOfInt = tempDecimal.substring(0, dpIndex)
                    partOfDec = tempDecimal.substring(dpIndex + 1, tempDecimal.length)
                }
            }
        }
    }


	// format part of integer
	if (aComma != 0) {
		var commaCount = dpIndex / 3
		if (dpIndex % 3 == 0) {
			commaCount = commaCount - 1
		}

		for (var j = 1; j <= commaCount; j++) {
			partOfInt = partOfInt.substring(0, (dpIndex + j - 4 * j)) + "," + partOfInt.substring((dpIndex + j - 4 * j), partOfInt.length)
		}
	}

	// format part of decimal
	if (aDecNumber >= 0) {
		if (partOfDec.length < aDecNumber) {
			for (var i = aDecNumber - partOfDec.length; i > 0; i--) {
				partOfDec = partOfDec + "0"
			}
		}else {
			partOfDec = partOfDec.substring(0, aDecNumber)
		}
	}

	if (aDecNumber > 0 || (aDecNumber < 0 && partOfDec != "")) {
		aDecimal = partOfInt + "." + partOfDec
	}else {
		aDecimal = partOfInt
	}

	if (extra != "") {
		aDecimal = aDecimal + " " + extra
	}

	if (sign == "-") {
		if (aHandleNegFlag != 0) {
			aDecimal = "(" + aDecimal + ")"
		}else {
			aDecimal = sign + aDecimal
		}

		if (aShowNegInRedFlag != 0) {
			aDecimal = '<font color="#ff0000">' + aDecimal + "</font>"
		}
	}

	return aDecimal
}
	
function unformatDecimal(str)
{
	if (str==null||str=="")
	{
		return "";
	}

	str = replaceStr(str, ",", "");
	
	var dotIndex = str.indexOf(".");
	if( -1 != dotIndex )
	{

		var preChars = str.substr(0,dotIndex);
		var lastChars = "0" + str.substr(dotIndex);
		
		if( parseFloat(lastChars,10) > 0 )
		{ 
		lastChars = lastChars.toString(10);
		dotIndex = lastChars.indexOf(".");
		lastChars = lastChars.substr(dotIndex);
		str = preChars + lastChars ;
		}
	}
	else
	{
	
		var noneZeroIndex = 0;
		
			for( var i = 0; i < str.length; i++ )
			{
				if( str.charAt(i) != '0' )
				{
					noneZeroIndex = i;
					break;
				}
			}

	str = str.substr(noneZeroIndex);
	} 
	return str;
} 

function replaceStr(aStr, aFind, aReplacement) {
	if (aStr == null || aFind == null || aReplacement == null) {
		return null;
	}

	aStr = "" + aStr
	aFind = "" + aFind
	aReplacement = "" + aReplacement

	var temp = ""
	for (var index = aStr.indexOf(aFind); index != -1; index = aStr.indexOf(aFind)) {
		if (index != 0) {
			temp = temp + aStr.substring(0, index) + aReplacement
		}else {
			temp = temp + aReplacement
		}
		aStr = aStr.substring(index + aFind.length, aStr.length)
	}
	return temp + aStr
}
function ajaxLodingTip(){
	var style = "position:absolute;"+
				"left:0px;"+
				"top:0px;"+
				"background-color: #97CBFF;"+
				"width:100%;"+
				"height:100%;"+
				"filter:alpha(opacity=30);"+
				"-moz-opacity:0.3;"+
				"opacity: 0.3;"+
				"z-Index:100;";

	var html = "<div class='processTip' style='"+style+"'></div>"+
			   "<div class='processTip' style='margin-left:30%;'><ul style='position:relative;'><li><img src='resources/image/loading.gif' />&nbsp;&nbsp;数据加载中，请稍候 . . . . . .	</li></ul>"+
			   "</div>";
	$("body").append(html);
}

function removeLodingTip(){
	$('.processTip').remove();
}