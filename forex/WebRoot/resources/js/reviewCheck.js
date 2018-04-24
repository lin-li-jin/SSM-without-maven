/**
* Amendment No : 
* Modify    By : sunyan
* Date         : 2013-09-10
* Description   : 公共方法验证
*/
function checkRadio(i){
	
	var result2="";
	var result="";
	var errs=$("#errs").val();
	var err2=$("#errIdArr").val();
	var errId=$("#check_"+i).val();
	obj=document.getElementById(errId);
	cName=obj.getAttribute("cName");
	
	if($("#check_"+i).attr("checked")==true){
		errs=errs+"   "+cName;
		$("#errs").val(errs);
		if(err2==null||err2==""){
			err2=errId;
		}else{
			err2=err2+"$$"+errId;
		}
		$("#errIdArr").val(err2);
	}else{
		var erArr=err2.split("$$");
		for(var j=0;j<erArr.length;j++){
			if(erArr[j]!=errId){
				var obj2=document.getElementById(erArr[j]);
				var cName2=obj2.getAttribute("cName");
				result=result+"   "+cName2;
				if(result2==null||result2==""){
					result2=erArr[j];
				}else{
					result2=result2+"$$"+erArr[j];
				}
			}
		}
		document.getElementById("errs").value=result;
		document.getElementById("errIdArr").value=result2;
	}
}


function hidText(size,post,operFlag,errs){
	var status="";
	if(post=='REV_GRP'||post=='APR_GRP'){//如果为复核人员
		for(var i=1;i<=size;i++){
			var id="#check_"+i;
			$(id).css("display","none");
		}
		$("input[@type=text]").each(function (){
			$(this).attr("readOnly",true);
		});	
		$("textArea").each(function (){
			$(this).attr("readOnly",true);
		});	
		$("select").each(function (){
			$(this).attr("readOnly",true);
		});	
		//$(this).attr("readOnly",true);
		$("#jingban").css("display","none");
		$("#ps").css("display","none");
		$("#reasonWords").attr("readOnly",false);
		if(post=='APR_GRP'){
			$("#review").css("display","none");
		}if(post=='REV_GRP'){
			$("#approve").css("display","none");
		}
		
		//$("#status1").attr("disabled",true);
	}else if(post=='HAN_GRP'){
		for(var i=1;i<=size;i++){
			var id="#check_"+i;
			$(id).css("display","none");
		}
		$("#review").css("display","none");
		$("#approve").css("display","none");
		$("#errsAll").css("display","none");
		$("#check").css("display","none");
		//如果为复核退回状态，则显示退回理由，及不符合项
		if(null!=operFlag&&operFlag!=""&&operFlag=='T'){
			$("input[@type=text]").each(function (){
				$(this).attr("readOnly",true);
			});
			$("textArea").each(function (){
				$(this).attr("readOnly",true);
			});	
			$("select").each(function (){
				$(this).attr("readOnly",true);
			});	
			var arr=errs.split("$$");
			for(var j=0;j<arr.length;j++){
				//$("#"+arr[j]).css("border","2px solid red");
				$("#"+arr[j]).css("background-color","#FFB6C1");
				$("#"+arr[j]).attr("readOnly",false);
			}
		}else{
			$("#ps").css("display","none");
			$("#reason").css("display","none");
		}
		
	}
	
}

	
/**复核通过**/
function reviewSuccall(acName,finishStatus){
	/***var tranNo=$("#tranNo").val();
	var taskId=$("#taskId").val();
	//var url="reviewSucc.action?tranNo="+tranNo;
	var url=acName+".action?tranNo="+tranNo+"&taskId="+taskId;
	window.location=url;*/
	
	//var errIdArr=trim($("#errIdArr").val());
	//var reasonWords=trim($("#reasonWords").val());
	var tranNo=$("#tranNo").val();
	var taskId=$("#taskId").val();
	//var url="reviewSucc.action?tranNo="+tranNo;
	var url=acName+".action?tranNo="+tranNo+"&taskId="+taskId;
	//if(errIdArr!=""){
		//alert("请勿勾选不符合项！");
		//return;
	//}
	//if(reasonWords!=""){
		//alert("请勿填写退回理由！");
		//return;
	//}
	if(acName=='irReviewReturnSucc'){
		var action = "getSwiftReturnInfo.action?r="+Math.random()+"&tranNo="+tranNo+"&swiftNo="+"MT196";
		var rtVal = openDialog(action,'');
		if(rtVal=="1"){
			var url=acName+".action?tranNo="+tranNo+"&taskId="+taskId;
			window.location=url;
		}
	}else{
		var url=acName+".action?tranNo="+tranNo+"&taskId="+taskId+"&finishStatus="+finishStatus;
		window.location=url;
	}
	
}


function insertSwift(swiftNo){
	var action='insertSwiftInit.action?r='+Math.random()+'&swiftNo='+swiftNo;
	var rtVal = openWindow(action,'');
	$('#swiftValue').val(rtVal);
	
}


function openWindow( url, args ){
	var height = 500;
	var width = 800;
	//alert(val+"|"+txt)
	var style = window.event ? 
				"Width="+width+"px,height="+height+"px,scrollbars=yes,resizable=yes" :
				(function(){
					var left = ( $(document).width()-width )/2;
					var top = ( $(document).height() - height )/2;
					return "Width="+width+"px,height="+height+",screenX="+left+"px,screenY="+top+"px,scrollbars=yes,resizable=yes";
				})();
	
	//var rtVal = window.showModalDialog(url, args ,style);
	var rtVal = window.open(url, args ,style);//2013-12-14 GUOGM
	return rtVal ? rtVal:'';
}

function openDialog( url, args ){
	var height = 500;
	var width = 800;
	//alert(val+"|"+txt)
	var style = window.event ? 
				"dialogWidth:"+width+"px;dialogHeight:"+height+"px;status:no;center:yes;scroll:yes;minimize:yes;maximize:yes;" :
				(function(){
					var left = ( $(document).width()-width )/2;
					var top = ( $(document).height() - height )/2;
					return "dialogWidth:"+width+"px;dialogHeight:"+height+";dialogLeft:"+left+"px;dialogTop:"+top+"px;status:no;center:yes;scroll:no;minimize:yes;maximize:yes;";
				})();
	
	var rtVal = window.showModalDialog(url, args ,style);
	//var rtVal = window.open(url, args ,style);//2013-12-14 GUOGM
	return rtVal ? rtVal:'';
}

function openDialog( url, args ,height,width){
	//var height = 500;
	//var width = 600;
	//alert(val+"|"+txt)
	var style = window.event ? 
				"dialogWidth:"+width+"px;dialogHeight:"+height+"px;status:no;center:yes;scroll:yes;minimize:yes;maximize:yes;" : 
				(function(){
					var left = ( $(document).width()-width )/2;
					var top = ( $(document).height() - height )/2;
					return "dialogWidth:"+width+"px;dialogHeight:"+height+";dialogLeft:"+left+"px;dialogTop:"+top+"px;status:no;center:yes;scroll:no;minimize:yes;maximize:yes;";
				})();
	
	var rtVal = window.showModalDialog(url, args ,style);
	//var rtVal = window.open(url, args ,style);//2013-12-14 GUOGM
	return rtVal ? rtVal:'';
}

function changeToAmount(amount){
	var len=amount.length;
	var count=len/3;
	var s="";var i=0;
	for(;i<count-1;i++){
		s=","+amount.substring(len-(3+3*i),len-(3*i))+s;
	}
	s=amount.substring(0,len-(3*i))+s;
	return s;
}
/**
 * 格式化金额
 * @param val----金额
 */
function fmoney(val)
{
	if(val==""||null==val){
		return "0.00";
	}else{
		var s = val;
		var n = 2;
		//parseFloat,解析一个字符串，并返回一个浮点数
		//toFixed,四舍五入为指定小数位数的数字
	  	s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
	  	var l = s.split(".")[0].split("").reverse();
	  	var r = s.split(".")[1];
	  	var t = "";
	  	for(var i = 0; i < l.length; i ++ )
	  	{
	  		t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
	  	}
	  	return t.split("").reverse().join("") + "." + r;
	}
	
}

/**
 * 格式化金额
 * @param val----金额
 */
function fmoneyFourDecimal(val)
{
	if(val==""||null==val){
		return "0.0000";
	}else{
		var s = val;
		var n = 4;
		//parseFloat,解析一个字符串，并返回一个浮点数
		//toFixed,四舍五入为指定小数位数的数字
	  	s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
	  	var l = s.split(".")[0].split("").reverse();
	  	var r = s.split(".")[1];
	  	var t = "";
	  	for(var i = 0; i < l.length; i ++ )
	  	{
	  		t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
	  	}
	  	return t.split("").reverse().join("") + "." + r;
	}
	
} 

function makeAmount(size){
	for(var i=1;i<=size;i++){
		var id="#check_"+i;
		if(i!=size){
			var value=$(id).val();
			$("#"+value).keydown(function(event){
				if(event.keyCode==13){
					var id2="#check_"+i+1;
					$("#"+$(id2).val()).focus();
				}
			});
		}
	}
}

function ifAmount(amount,id){
	var zhengInt="1234567890";
	var len=amount.length;
	for(var i=0;i<=len-1;i++){
		var a=amount.substring(i,i+1);
		if((i==0&&a=="0")||(zhengInt.indexOf(a)<0)){
			alert("请输入正确的金额！");
			$("#"+id).focus();
			return false;
		}
	}
	return true;
}

function getAmount(amount){
	var amt=$("#"+amount).val();
	//if(ifAmount(amt,amount)){
		//document.getElementById(amount).value=changeToAmount(amt);
		document.getElementById(amount).value=fmoney(amt);
	//}
	
}


function getFeeAmount(amount,fee,type){
	var amt=$("#"+amount).val();
	if(amt!=null&&""!=amt){
		$.post("getFeeByAmt.action",{amount:amt,tranType:type},
			function(dat){
			if(dat!=null&&dat!=""){
				document.getElementById(amount).value=fmoney(amt);
				document.getElementById(fee).value="";
				document.getElementById(fee).value=dat;		
			}else{
				document.getElementById(fee).value="";	
			}
		});
	}
}

function getBankAddr(bank,bankName,bankAddrId){
	var bankNo=$("#"+bank).val();
	if(bankNo!=null&&""!=bankNo){
		$.post("getBankInfoQuery.action",{bankNo:bankNo},
			function(dat){
			if(dat!=null&&dat!=""){
				var arr=dat.split("$$");
				document.getElementById(bankName).value=arr[0];	
				document.getElementById(bankAddrId).value=arr[1];
			}else{
				document.getElementById(bankAddrId).value="";	
			}
		});
	}
}
//ddeaosn 
function getBankName(bank,bankName){
	var bankNo=$("#"+bank).val();
	if(bankNo!=null&&""!=bankNo){
		$.post("getBankInfoQuery.action",{bankNo:bankNo},
			function(dat){
			if(dat!=null&&dat!=""){
				var arr=dat.split("$$");
				document.getElementById(bankName).value=arr[0];	
			}else{
				document.getElementById(bankName).value="";	
			}
		});
	}
}

function getCustInfo(custNo,custName,custAddr){
	var custNoVal=$("#"+custNo).val();
	if(custNoVal!=null&&""!=custNoVal){
		$.post("getCustInfoQuery.action",{custNo:custNoVal},
			function(dat){
			if(dat!=null&&dat!=""){
				var arr=dat.split("$$");
				document.getElementById(custName).value=arr[0];	
				document.getElementById(custAddr).value=arr[1];		
			}else{
				alert("在我行没有该收款人相关信息！");
				document.getElementById(custName).value="";	
				document.getElementById(custAddr).value="";	
				$("#"+custNo).val("");
			}
		});
	}
}



//***********************************************************//
//*************************分割线****************************//
//***********************************************************//
function postIdCheck(postName,errWords){
	if(postName == 'HAN_GRP'){
		$("[type='checkbox']").css("visibility","hidden");
		$("#errWords").css("visibility","hidden");
		$("#errReason").css("visibility","hidden");
		if(errWords != ''){
			var errWordsArr = new Array();
			errWordsArr = errWords.split("$$");
			$("[type='checkbox']").each(function(){
				var index = -1;
				var value = $(this).val();
				for(var i=0;i<errWordsArr.length;i++){
					if(value == errWordsArr[i]){
						index = i;
						break;
					}
				}
				if(index == -1){
					$("#"+value).attr("readOnly",true);
				}
				else{
					if($("#"+value).attr("class")=="Wdate"){
						//当时间需要修改时，为元素绑定时间选择函数
						$("#"+value).focus(function(){
			 				WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'});
			 			});
					}
					$("#"+value).css("background","yellow");
				}
			});
		}
		else{
			$("[class='Wdate']").focus(function(){
				WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'});
			});
		}
	}
	else{
		$("input").attr("readOnly",true);
		$("select").click(function(){alert("Forbit changing!");return false;});//禁止选择
		$("[type='radio']").click(function(){return false;});//禁止选择
		$("textarea").not("#errReason").attr("readOnly",true);
		$("[class='Wdate']").removeAttr("onFocus");//解除日期选择函数绑定
		$("[type='checkbox']").css("visibility","hidden");//GUOGM 2013-12-24
	}
}

/**
 * 获取银行信息
 * @param bankNo----银行行号对应元素Id
 * @param position----显示银行信息对应元素Id
 */
function getBankMessage(bankNo,bankName,bankAdd){
	var number = $("#"+bankNo).val();
	if(number!=""){
		$.post("getBankMsg.action",{bankNo:number},
  				function(dat){
  			if(dat!=""){
  				if(dat=="same"){
  					alert("不能为本行行号！");
  					return;
  				}
  				var bankInfo = new Array();
  				bankInfo = dat.split("$$");
  				$("#"+bankName).val(bankInfo[0]);
  				$("#"+bankAdd).html(bankInfo[1]);
  			}else{
  				$("#"+bankNo).val("");
  				alert("无此银行信息，请确认银行编号是否无误！");
  			}
  		});
	}
}

function getCustMoreMessage(custNo,custName,custAdd,contact,tel,creditLimit){
	var cust = $("#"+custNo).val();
	if(cust!=""){
		$.post("getCustMoreMsg.action",{custNo:cust},
		function(dat){
		if(dat!=""){
			var bankInfo = new Array();
			bankInfo = dat.split("$$");
			$("#"+custName).val(bankInfo[0]);
			$("#"+custAdd).html(bankInfo[1]);
			$("#"+contact).val(bankInfo[2]);
			$("#"+tel).val(bankInfo[3]);
			$("#"+creditLimit).val(bankInfo[4]);
		}else{
			$("#"+custNo).val("");
			alert("无此客户信息，请确认客户编号是否无误！");
		}
		});
	}
}
//ddeason 保函查询，多了一个授信额度参数域
function getLgCustMessage(custNo,custName,custAdd,tel,creditLimit){
	var cust = $("#"+custNo).val();
	if(cust!=""){
		$.post("getCustMoreMsg.action",{custNo:cust},
		function(dat){
		if(dat!=""){
			var bankInfo = new Array();
			bankInfo = dat.split("$$");
			$("#"+custName).val(bankInfo[0]);
			$("#"+custAdd).html(bankInfo[1]);
		//	$("#"+contact).val(bankInfo[2]);
			$("#"+tel).val(bankInfo[3]);
			$("#"+creditLimit).val(bankInfo[4]);
		}else{
			$("#"+custNo).val("");
			alert("无此客户信息，请确认客户编号是否无误！");
		}
		});
	}
}
function getCustMessage(custNo,custName,custAdd){
	var cust = $("#"+custNo).val();
	if(cust!=""){
		$.post("getCustMsg.action",{custNo:cust},
  				function(dat){
  			if(dat!=""){
  				var bankInfo = new Array();
  				bankInfo = dat.split("$$");
  				$("#"+custName).val(bankInfo[0]);
  				$("#"+custAdd).html(bankInfo[1]);
  			}else{
  				$("#"+custNo).val("");
  				alert("无此客户信息，请确认客户编号是否无误！");
  			}
  		});
	}
}

function getMoreCustMessage(custNo,custName,custAdd,custPhone){
	var cust = $("#"+custNo).val();
	if(cust!=""){
		$.post("getCustMoreMsg.action",{custNo:cust},
  				function(dat){
  			if(dat!=""){
  				var bankInfo = new Array();
  				bankInfo = dat.split("$$");
  				$("#"+custName).val(bankInfo[0]);
  				$("#"+custAdd).html(bankInfo[1]);
  				$("#"+custPhone).val(bankInfo[2]);
  			}else{
  				$("#"+custNo).val("");
  				alert("无此客户信息，请确认客户编号是否无误！");
  			}
  		});
	}
}

/**
 * 检测账户有效性
 * @param accNo----账户号对应元素Id
 * @param bankNo----银行行号
 */
function getAccMessage(accNo){
	var acc = $("#"+accNo).val();
	if(acc!=""){
		$.post("getAccMsg.action",{accNo:acc},
  				function(dat){
  			if(dat!=""){
  			}else{
  				$("#"+accNo).val("");
  				alert("没有此账户信息！");
  			}
  		});
	}
}


/**
 * 检测账户有效性---出口信用证-收汇入账
 * @param accNo----账户号对应元素Id
 * @param bankNo----银行行号
 */
function getAccMessage5(accNo,payMeth){
	var acc = $("#"+accNo).val();
	var payMeth = $("#"+payMeth).val();
	var accCcy="";
	if(payMeth=="ORIGINAL"){
		accCcy= $("#lcCur").val();
	}else if(payMeth=="REM"){
		accCcy="CNY";
	}
	if(acc!=""){
		$.post("getAccMsg.action",{accNo:acc},
  				function(dat){
  			if(dat!=""){
  				if(dat!="CNY"&&accCcy=="CNY"){
  					$("#"+accNo).val("");
  	  				alert("入账账号只能为人民币账号！");
  				}
  			}else{
  				$("#"+accNo).val("");
  				alert("没有此账户信息！");
  			}
  		});
	}
}

function getAccMessage2(accNo,accCcy){
	var acc = $("#"+accNo).val();
	if(acc!=""){
		$.post("getAccMsg2.action",{accNo:acc},
  				function(dat){
  			if(dat!=""){
  				$("#"+accCcy).val(dat);
  			}else{
  				$("#"+accNo).val("");
  				alert("没有此账户信息！");
  			}
  		});
	}
}

function getAccMessage3(accNo,accName,accCcy){
	var acc = $("#"+accNo).val();
	if(acc!=""){
		$.post("getAccMsg3.action",{accNo:acc},
  				function(dat){
  			if(dat!=""){
  				var accInfo = new Array();
  				accInfo = dat.split("$$");
  				$("#"+accName).val(accInfo[0]);
  				$("#"+accCcy).val(accInfo[1]);
  			}else{
  				$("#"+accNo).val("");
  				alert("没有此账户信息！");
  			}
  		});
	}
}

function getAccMessage4(accNo){
	var acc = $("#"+accNo).val();
	if(acc!=""){
		$.post("getAccMsg.action",{accNo:acc},
  				function(dat){
  			if(dat!=""){
  				if(dat!="CNY"){
  					$("#"+accNo).val("");
  	  				alert("扣费账号只能为人民币账号！");
  				}
  			}else{
  				$("#"+accNo).val("");
  				alert("没有此账户信息！");
  			}
  		});
	}
}

function getAccMessage5(accNo,accCcy,bankNote){
	var acc = $("#"+accNo).val();
	if(acc!=""){
		$.post("getAccMsg4.action",{accNo:acc},
  				function(dat){
  			if(dat!=""){
  				var accInfo = new Array();
  				accInfo = dat.split("$$");
  				$("#"+accCcy).val(accInfo[0]);
  				if("C"==accInfo[1]){
  					$("input[name='"+bankNote+"'][value=1]").attr("checked",true); 
  				}
  				if("R"==accInfo[1]){
  					$("input[name='"+bankNote+"'][value=0]").attr("checked",true); 
  				}
  			}else{
  				$("#"+accNo).val("");
  				alert("没有此账户信息！");
  			}
  		});
	}
}

function getCreditMessage(creditNo){
	var credit = $("#"+creditNo).val();
	if(acc!=""){
		$.post("getCreditMsg.action",{creditNo:credit},
  				function(dat){
  			if(dat!=""){
  			}else{
  				$("#"+accNo).val("");
  				alert("没有此信用证号信息！");
  			}
  		});
	}
}

/**
 * 获取交易信息
 * @param tranNo----交易编号对应元素Id
 */
function getTranMessage(tranNo,tranType){
	var tran = $("#"+tranNo).val();
	if(tran!=""){
		$.post("getTranMsg.action",{tranNo:tran,tranType:tranType},
  				function(dat){
			if(dat!=""){
				if("S"!=dat){
					$("#"+tranNo).val("");
					alert("此交易无法进行止付操作！");
				}
			}else{
				$("#"+tranNo).val("");
				alert("没有此交易信息！");
			}
		});
	}else{
		alert("请输入交易编号！");
	}
}

/**
 * 获取交易信息
 * @param tranNo----交易编号对应元素Id
 */
function getTtStopPayInfo(tranNo){
	var tran = $("#"+tranNo).val();
	if(tran!=""){
		$.post("getTtStopPayInfo.action",{tranNo:tran},
  				function(dat){
			if(dat!=""){
				if("S"!=dat){
					$("#"+tranNo).val("");
					alert("此交易无法进行止付操作！");
				}
			}else{
				$("#"+tranNo).val("");
				alert("没有此交易信息！");
			}
		});
	}else{
		alert("请输入交易编号！");
	}
}

/**
 * 必输项检测
 * @param eleArray----必输项元素数组
 */
function mustInput(){
	var times=0;//同一radio进入次数
	var first=false;
	var second=false;
	var mustInput = $("[inputType='mustInput']");
	for(var i=0;i<mustInput.length;i++){
		if($(mustInput[i]).attr("type")=="radio"){//判断radio是否有选择
			if(times==0){
				var boolCheck=mustInput[i].checked;
				if(boolCheck){
					first=true;
				}
				times++;
			}else{
				var boolCheck=mustInput[i].checked;
				if(boolCheck){
					second=true;
				}
				times=0;
				if(!(first||second)){
					alert("请选择"+$(mustInput[i]).attr("cName")+"！");
					return false;
				}
			}
		}else if($(mustInput[i]).attr("tagName")=="TEXTAREA"){//判断textarea是否有输入
			if($(mustInput[i]).html==""){
			alert("请输入"+$(mustInput[i]).attr("cName")+"！");
			return false;
			}
		}else{
			if($(mustInput[i]).val()==""){
			alert("请输入"+$(mustInput[i]).attr("cName")+"！");
			return false;
			}
		}
	}
	return true;
}

/**
 * 设置不可操作项，系统自动录入
 */
function systemInput(){
	$("[inputType='sysInput']").each(function(){
//		if($(this).attr("tagName")=="TEXTAREA"){
//			if($(this).html()==""||$(this).html()==null){
//				$(this).html("系统自动录入!");	
//			}
//		}else{
//			if($(this).val()==""||$(this).val()==null){
//				$(this).val("系统自动录入!");
//			}
//		}
		$(this).css("background","#E8E8E8");
		$(this).attr("readOnly",true);
	});
}

/**
	 * 录入提交检查
	 * @param formName----表单名
	 * @param actionName----action名
	 * @param btnType----操作类型
	 */
function signSubmit(formName,actionName,tranType){
	if(tranType=="RESET"){
		var l = (formName.split("")).reverse();
		var action = "";
		for(var i = l.length-1; i > 3; i -- )
	  	{
			action += l[i];
	  	}
		window.location=action+"Init.action";
	}else{
		var check = mustInput();
		if(check==true){
			if(tranType=="REMITT"){
				var amount1 = $("#originalAmount").val();
				var amount2 = $("#exAmount").val();
				var targetAmount = $("#remittAmount").val();
				var checkAmount = checkAmountSum(amount1,amount2,targetAmount);
				if(checkAmount==true){
					document.all(formName).action = actionName+".action?tranType="+tranType;
					document.all(formName).submit();
					processTip();
				}else{
					alert("汇款金额有误！");
				}
			}else{
				document.all(formName).action = actionName+".action?tranType="+tranType;
				document.all(formName).submit();
				processTip();
			}
		}
	}
}

/**
 * 退回提交检查
 * @param formName----表单名
 * @param actionName----action名
 * @param btnType----操作类型
 */
function returnSubmit(formName,actionName,tranType){
	var txterrWords=$("#txterrWords").val();
	var errReason=$("#errReason").val();
	if(null==txterrWords||txterrWords==""){
		alert("请勾选不符合项！");
		return;
	}
	if(null==errReason||errReason==""){
		alert("退回理由不能为空！");
		return;
	}
	document.all(formName).action = actionName+".action?tranType="+tranType;
	document.all(formName).submit();
	processTip();
}

/**
 * 审批提交检查
 * @param formName----表单名
 * @param actionName----action名
 * @param btnType----操作类型
 */
function approveSubmit(formName,actionName,tranType){
	var txterrWords=$("#txterrWords").val();
	var errReason=$("#errReason").val();
	if(null!=txterrWords&&txterrWords!=""){
		alert("错误标识不为空，不能提交信息！");
		return;
	}
	if(null!=errReason&&errReason!=""){
		alert("退回理由不为空，不能提交信息！");
		return;
	}
	document.all(formName).action = actionName+".action?tranType="+tranType;
	document.all(formName).submit();
	processTip();
}

/**
 * 两个同币种金额相加
 * @param amount1
 * @param amount2
 * @param resultAmount
 */
function getAmountSum(amount1,amount2){
	if(amount1==""){
		amount1 = "0";
	}
	if(amount2==""){
		amount2="0";
	}
	amount1 = parseFloat(amount1.replace(/[^\d\.-]/g, ""));
	amount2 = parseFloat(amount2.replace(/[^\d\.-]/g, ""));
	var totalAmount = amount1+amount2;
	totalAmount=fmoney(totalAmount);
	return totalAmount;
}

function getAmountSum2(amount1,amount2,totalAmt){
	if(amount1==""){
		amount1 = "0";
	}
	if(amount2==""){
		amount2="0";
	}
	amount1 = parseFloat(amount1.replace(/[^\d\.-]/g, ""));
	amount2 = parseFloat(amount2.replace(/[^\d\.-]/g, ""));
	totalAmt = parseFloat(totalAmt.replace(/[^\d\.-]/g, ""));
	var balance = totalAmt-amount1-amount2;
	return fmoney(balance);
}

function checkAmountSum(amount1,amount2,targetAmount){
	if(amount1==""){
		amount1 = "0";
	}
	if(amount2==""){
		amount2="0";
	}
	amount1 = parseFloat(amount1.replace(/[^\d\.-]/g, ""));
	amount2 = parseFloat(amount2.replace(/[^\d\.-]/g, ""));
	targetAmount = parseFloat(targetAmount.replace(/[^\d\.-]/g, ""));
	var totalAmount = amount1+amount2;
	if(targetAmount == totalAmount){
		return true;
	}else{
		return false;
	}
}

/**
 * 根据源币种与目标币种获取对应买入汇率
 * @param sourceCcy
 * @param targetCcy
 * @param valueRate
 */
function getValueRate(sourceCcy,targetCcy,valueRate){
	var varSourceCcy = $("#"+sourceCcy).val();
	var varTargetCcy = $("#"+targetCcy).val();
	if(varSourceCcy!=""){
		if(varTargetCcy!=""){
			$.post("getValueRate.action",{sourceCcy:varSourceCcy,targetCcy:varTargetCcy},
					function(dat){
				if(dat!=""){
					$("#"+valueRate).val(dat);
				}
			});
		}else{
			alert("请输入"+$("#"+targetCcy).attr("cName")+"！");
			return;
		}
	}else{
		alert("请输入"+$("#"+sourceCcy).attr("cName")+"！");
		return;
	}
}

/**
 * 根据源币种与目标币种获取对应卖出汇率
 * @param sourceCcy
 * @param targetCcy
 * @param valueRate
 */
function getSaleValueRate(sourceCcy,targetCcy,valueRate){
	var varSourceCcy = $("#"+sourceCcy).val();
	var varTargetCcy = $("#"+targetCcy).val();
	if(varSourceCcy!=""){
		if(varTargetCcy!=""){
			$.post("getSaleValueRate.action",{sourceCcy:varSourceCcy,targetCcy:varTargetCcy},
					function(dat){
				if(dat!=""){
					$("#"+valueRate).val(dat);
				}
			});
		}else{
			alert("请输入"+$("#"+targetCcy).attr("cName")+"！");
			return;
		}
	}else{
		alert("请输入"+$("#"+sourceCcy).attr("cName")+"！");
		return;
	}
}

/**
 * 用于汇出汇款中计算售出外汇金额
 * @param sourceCcy1
 * @param sourceAmt1
 * @param sourceCcy2
 * @param SourceAmt2
 * @param targetCcy
 * @param targetAmt
 * @param resultAmount
 */
//function getAmountSum2(sourceCcy1,sourceAmt1,sourceCcy2,sourceAmt2,targetCcy,targetAmt,resultAmount){
//	var targetAmt1 = parseFloat(getMoneyExchange(sourceCcy1,sourceAmt1,targetCcy));
//	var targetAmt2 = parseFloat(getMoneyExchange(sourceCcy2,sourceAmt2,targetCcy));
//	if(targetAmt1!=null&&targetAmt2!=null){
//		totalAmt = targetAmt1+targetAmt2;
//		$("#"+resultAmount).val(totalAmt.toFixed(2));
//	}
//}

/**
 * 货币转换，买入汇率
 * @param sourceCcy
 * @param sourceAmt
 * @param targetCcy
 */
function getMoneyExchange(sourceCcy,sourceAmt,targetCcy,targetAmt){
	if(sourceCcy==""||sourceAmt==""||targetCcy==""){
		return 0;
	}else{
		//sourceAmt = parseFloat(sourceAmt.replace(/[^\d\.-]/g, ""));
		$.post("getMoneyExchange.action",{sourceCcy:sourceCcy,sourceAmt:sourceAmt,targetCcy:targetCcy},
				function(dat){
			if(dat!=""){
				$(targetAmt).val(fmoney(dat));
			}
		});
	}
}

/**
 * 货币转换，卖出汇率
 * @param sourceCcy
 * @param sourceAmt
 * @param targetCcy
 */
function getMoneyNeed(sourceCcy,targetCcy,targetAmt,sourceAmt){
	if(sourceCcy==""||targetAmt==""||targetCcy==""){
		return 0;
	}else{
		//sourceAmt = parseFloat(sourceAmt.replace(/[^\d\.-]/g, ""));
		$.post("getMoneyNeed.action",{sourceCcy:sourceCcy,targetCcy:targetCcy,targetAmt:targetAmt},
				function(dat){
			if(dat!=""){
				$(sourceAmt).val(fmoney(dat));
			}
		});
	}
}

function getFeeNum(feeType,amount,fee){
	var amt;
	if(amount=="0"){
		amt="0";
	}else{
		amt = $("#"+amount).val();
	}
	if(amt==""){
		return;
	}else{
		amt = parseFloat(amt.replace(/[^\d\.-]/g, ""));
		$.post("getFeeNum.action",{feeType:feeType,amount:amt},
				function(dat){
			if(dat!=""){
				$("#"+fee).val(fmoney(dat));
			}
		});
	}
}

function getActuPaymentAmt(totalAmt,feeType,amount,actuPaymentAmt){
	var amt;
	if(amount=="0"){
		amt="0";
	}else{
		amt = $("#"+amount).val();
	}
	if(amt==""){
		return;
	}else{
		amt = parseFloat(amt.replace(/[^\d\.-]/g, ""));
		$.post("getFeeNum.action",{feeType:feeType,amount:amt},
				function(dat){
			if(dat!=""){
				var tAmt = parseFloat(totalAmt.replace(/[^\d\.-]/g, ""));
				var datAmt = parseFloat(dat.replace(/[^\d\.-]/g, ""));
				var actuAmt = tAmt - datAmt;
				$("#"+actuPaymentAmt).val(fmoney(actuAmt));
			}
		});
	}
}

function getOcRemainCcy(targetCcy,reduceCcy,reduceAmt,totalCcy,totalAmt){
	var targetCcy =$("#"+targetCcy).val();
	var reduceCcy=$("#"+reduceCcy).val();
	var reduceAmt=$("#"+reduceAmt).val();
	var totalCcy=$("#"+totalCcy).val();
	var totalAmt=$("#"+totalAmt).val();
	
	if(null==reduceCcy||""==reduceCcy){
		return;
	}
	if(null==reduceAmt||""==reduceAmt){
		return;
	}
	if(null==totalCcy||""==totalCcy){
		return;
	}
	if(null==totalAmt||""==totalAmt){
		return;
	}
	$.post("getOcRemainAmt.action",{targetCcy:targetCcy,
									reduceCcy:reduceCcy,reduceAmt:reduceAmt,
									totalCcy:totalCcy,totalAmt:totalAmt},
			function(dat){
			if(dat!=""){
				if(dat!="0"){
					$("#settleAmt").val(fmoney(dat));
				}
				
			}
	});
}

function getFeeNumByAmount(feeType,amount,ccy,fee){
	var amt;
	if(amount=="0"){
		amt="0";
	}else{
		amt = $("#"+amount).val();
	}
	if(amt==""){
		return;
	}else{
		amt = parseFloat(amt.replace(/[^\d\.-]/g, ""));
		var sourceCcy = $("#"+ccy).val();
		$.post("getFeeNumByAmount.action",{feeType:feeType,amount:amt,sourceCcy:sourceCcy},
				function(dat){
			if(dat!=""){
				$("#"+fee).val(fmoney(dat));
			}
		});
	}
}

function getReturnAmount(feeType,amount,fee,Amount,reAmount){
	var amt;
	if(amount=="0"){
		amt="0";
	}else{
		amt = $("#"+amount).val();
	}
	if(amt==""){
		return;
	}else{
		amt = parseFloat(amt.replace(/[^\d\.-]/g, ""));
		$.post("getFeeNum.action",{feeType:feeType,amount:amt},
				function(dat){
			if(dat!=""){
				var fee = fmoney(dat);
				var irAmount = $("#"+Amount).val();
				if(fee==""||fee==null){
					fee="0";
				}
				if(irAmount==""||irAmount==null){
					irAmount="0";
				}
				fee = parseFloat((fee + "").replace(/[^\d\.-]/g, ""));
				irAmount = parseFloat((irAmount + "").replace(/[^\d\.-]/g, ""));
				var returnAmount = fmoney(irAmount-fee);
				$("#"+reAmount).val(returnAmount);
			}
		});
	}
}

function getCNYTeleFeeNum(sourseCcy,sourseAmount,targetId,feeType){
	var sourseCcy= $("#"+sourseCcy).val();
	var sourseAmount= $("#"+sourseAmount).val();
	sourseAmount = parseFloat(sourseAmount.replace(/[^\d\.-]/g, ""));
	$.post("getMoneyExchange.action",{sourceCcy:sourseCcy,sourceAmt:sourseAmount,targetCcy:"CNY"},
		function(dat){
		if(dat!=""){
			$.post("getFeeNum.action",{feeType:feeType,amount:dat},
					function(dat){
				if(dat!=""){
					$("#"+targetId).val(fmoney(dat));
				}
			});
		}
	});
}

function getTeleFeeNum(remittMode,fee){
	var remittmode = $("#"+remittMode).val();
	if(remittmode==""||remittmode==null){
		return;
	}else{
		if(remittmode=="T"){
			getFeeNum('telecharge','0',fee);
		}
		if(remittmode=="D"){
			getFeeNum('mailcharge','0',fee);
		}
	}
}

function getFeeNum2(remittMode,fee){
	if(remittMode=="T"){
		getFeeNum('telecharge','0',fee);
	}
	if(remittMode=="D"){
		getFeeNum('mailcharge','0',fee);
	}
	
}

function getTotalAmount(childAmt1,childAmt2,targetAmt){
	var childAmt1=$("#"+childAmt1).val();
	var childAmt2=$("#"+childAmt2).val();
	childAmt1 = parseFloat(childAmt1.replace(/[^\d\.-]/g, ""));
	childAmt2 = parseFloat(childAmt2.replace(/[^\d\.-]/g, ""));
	var totalAmt=childAmt1+childAmt2;
	$("#"+targetAmt).val(fmoney(totalAmt));
}

function atoc(numberValue){  
	var numberValue=new String(Math.round(numberValue*100)); // 数字金额  
	var chineseValue=""; // 转换后的汉字金额  
	var String1 = "零壹贰叁肆伍陆柒捌玖"; // 汉字数字  
	var String2 = "万仟佰拾亿仟佰拾万仟佰拾元角分"; // 对应单位  
	var len=numberValue.length; // numberValue 的字符串长度  
	var Ch1; // 数字的汉语读法  
	var Ch2; // 数字位的汉字读法  
	var nZero=0; // 用来计算连续的零值的个数  
	var String3; // 指定位置的数值  
	if(len>15){  
		alert("超出计算范围");  
		return "";  
	}  
	if (numberValue==0){  
		chineseValue = "零元整";  
		return chineseValue;  
	}  
	String2 = String2.substr(String2.length-len, len); // 取出对应位数的STRING2的值  
	for(var i=0; i<len; i++){  
		String3 = parseInt(numberValue.substr(i, 1),10); // 取出需转换的某一位的值  
		if ( i != (len - 3) && i != (len - 7) && i != (len - 11) && i !=(len - 15) ){  
			if ( String3 == 0 ){  
				Ch1 = "";  
				Ch2 = "";  
				nZero = nZero + 1;  
			}  
			else if ( String3 != 0 && nZero != 0 ){  
				Ch1 = "零" + String1.substr(String3, 1);  
				Ch2 = String2.substr(i, 1);  
				nZero = 0;  
			}  
			else{  
				Ch1 = String1.substr(String3, 1);  
				Ch2 = String2.substr(i, 1);  
				nZero = 0;  
			}  
		}  
		else{ // 该位是万亿，亿，万，元位等关键位  
			if( String3 != 0 && nZero != 0 ){  
				Ch1 = "零" + String1.substr(String3, 1);  
				Ch2 = String2.substr(i, 1);  
				nZero = 0;  
			}  
			else if ( String3 != 0 && nZero == 0 ){  
				Ch1 = String1.substr(String3, 1);  
				Ch2 = String2.substr(i, 1);  
				nZero = 0;  
			}  
			else if( String3 == 0 && nZero >= 3 ){  
				Ch1 = "";  
				Ch2 = "";  
				nZero = nZero + 1;  
			}  
			else{  
				Ch1 = "";  
				Ch2 = String2.substr(i, 1);  
				nZero = nZero + 1;  
			}  
			if( i == (len - 11) || i == (len - 3)){ // 如果该位是亿位或元位，则必须写上  
				Ch2 = String2.substr(i, 1);  
			}  
		}  
		chineseValue = chineseValue + Ch1 + Ch2;  
	}  
	if ( String3 == 0 ){ // 最后一位（分）为0时，加上“整”  
		chineseValue = chineseValue + "整";  
	}  
	return chineseValue;  
}

//按回车直接进入下一个元素
window.document.onkeydown = function(){
	if(event.keyCode == 13){
		event.keyCode=9;
	}
};

function ifEnglist(str){
	var Criteria1=/[^a-zA-Z0-9\-\=\_\~\$\%\^\&\*\+\|\!\@\{\}\-\`\_\:\\\.\;\?\,\#\/\[\]\(\)\x20]/;    //大小写字母及数字
	if(Criteria1.exec(str)){
		return false;
	}else{
		return true;
	}
	
}

function getSE_Rate_RMBAmount(accCcy,CNYCcy,amount,valueRate,RMBAmount){
	getValueRate(accCcy,CNYCcy,valueRate);
	
	var sourceCcy = $("#"+accCcy).val();
	var sourceAmt = $("#"+amount).val();
	var targetCcy = $("#"+CNYCcy).val();
	var jRMBAmount = $("#"+RMBAmount);
	getMoneyExchange(sourceCcy,sourceAmt,targetCcy,jRMBAmount);
}

function getSA_Rate_RMBAmount(accCcy,CNYCcy,amount,valueRate,RMBAmount){
	getSaleValueRate(CNYCcy,accCcy,valueRate);
	
	var targetCcy = $("#"+accCcy).val();
	var targetAmt = $("#"+amount).val();
	var sourceCcy = $("#"+CNYCcy).val();
	var jRMBAmount = $("#"+RMBAmount);
	getMoneyNeed(sourceCcy,targetCcy,targetAmt,jRMBAmount);
}

function getAccnoAmt(remittAmount,feeType,amount,fee,accnoAmt){
	var amt;
	if(amount=="0"){
		amt="0";
	}else{
		amt = $("#"+amount).val();
	}
	if(amt==""){
		return;
	}else{
		amt = parseFloat(amt.replace(/[^\d\.-]/g, ""));
		$.post("getFeeNum.action",{feeType:feeType,amount:amt},
				function(dat){
			if(dat!=""){
				var remittAmt = $("#"+remittAmount).val();
				remittAmt = parseFloat(remittAmt.replace(/[^\d\.-]/g, ""));
				var fee = parseFloat(dat.replace(/[^\d\.-]/g, ""));
				$("#"+accnoAmt).val(fmoney(remittAmt-fee));
			}
		});
	}
}

function getAccnoAmtByCcy(remittAmount,feeType,amount,fee,accnoAmt,Ccy){
	var amt;
	if(amount=="0"){
		amt="0";
	}else{
		amt = $("#"+amount).val();
	}
	if(amt==""){
		return;
	}else{
		var sourceCcy = $("#"+Ccy).val();
		amt = parseFloat(amt.replace(/[^\d\.-]/g, ""));
		$.post("getFeeNumByAmountCcy.action",{feeType:feeType,amount:amt,sourceCcy:sourceCcy},
				function(dat){
			if(dat!=""){
				var remittAmt = $("#"+remittAmount).val();
				remittAmt = parseFloat(remittAmt.replace(/[^\d\.-]/g, ""));
				var fee = parseFloat(dat.replace(/[^\d\.-]/g, ""));
				$("#"+accnoAmt).val(fmoney(remittAmt-fee));
			}
		});
	}
}

function getFeeNumByCcy(feeType,amount,fee,Ccy){
	var amt;
	if(amount=="0"){
		amt="0";
	}else{
		amt = $("#"+amount).val();
	}
	if(amt==""){
		return;
	}else{
		var targetCcy = $("#"+Ccy).val();
		amt = parseFloat(amt.replace(/[^\d\.-]/g, ""));
		$.post("getFeeNumByAmountCcy.action",{feeType:feeType,amount:amt,targetCcy:targetCcy},
				function(dat){
			if(dat!=""){
				$("#"+fee).val(fmoney(dat));
			}
		});
	}
}





