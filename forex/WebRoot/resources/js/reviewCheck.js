/**
* Amendment No : 
* Modify    By : sunyan
* Date         : 2013-09-10
* Description   : ����������֤
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
	if(post=='REV_GRP'||post=='APR_GRP'){//���Ϊ������Ա
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
		//���Ϊ�����˻�״̬������ʾ�˻����ɣ�����������
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

	
/**����ͨ��**/
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
		//alert("����ѡ�������");
		//return;
	//}
	//if(reasonWords!=""){
		//alert("������д�˻����ɣ�");
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
 * ��ʽ�����
 * @param val----���
 */
function fmoney(val)
{
	if(val==""||null==val){
		return "0.00";
	}else{
		var s = val;
		var n = 2;
		//parseFloat,����һ���ַ�����������һ��������
		//toFixed,��������Ϊָ��С��λ��������
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
 * ��ʽ�����
 * @param val----���
 */
function fmoneyFourDecimal(val)
{
	if(val==""||null==val){
		return "0.0000";
	}else{
		var s = val;
		var n = 4;
		//parseFloat,����һ���ַ�����������һ��������
		//toFixed,��������Ϊָ��С��λ��������
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
			alert("��������ȷ�Ľ�");
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
				alert("������û�и��տ��������Ϣ��");
				document.getElementById(custName).value="";	
				document.getElementById(custAddr).value="";	
				$("#"+custNo).val("");
			}
		});
	}
}



//***********************************************************//
//*************************�ָ���****************************//
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
						//��ʱ����Ҫ�޸�ʱ��ΪԪ�ذ�ʱ��ѡ����
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
		$("select").click(function(){alert("Forbit changing!");return false;});//��ֹѡ��
		$("[type='radio']").click(function(){return false;});//��ֹѡ��
		$("textarea").not("#errReason").attr("readOnly",true);
		$("[class='Wdate']").removeAttr("onFocus");//�������ѡ������
		$("[type='checkbox']").css("visibility","hidden");//GUOGM 2013-12-24
	}
}

/**
 * ��ȡ������Ϣ
 * @param bankNo----�����кŶ�ӦԪ��Id
 * @param position----��ʾ������Ϣ��ӦԪ��Id
 */
function getBankMessage(bankNo,bankName,bankAdd){
	var number = $("#"+bankNo).val();
	if(number!=""){
		$.post("getBankMsg.action",{bankNo:number},
  				function(dat){
  			if(dat!=""){
  				if(dat=="same"){
  					alert("����Ϊ�����кţ�");
  					return;
  				}
  				var bankInfo = new Array();
  				bankInfo = dat.split("$$");
  				$("#"+bankName).val(bankInfo[0]);
  				$("#"+bankAdd).html(bankInfo[1]);
  			}else{
  				$("#"+bankNo).val("");
  				alert("�޴�������Ϣ����ȷ�����б���Ƿ�����");
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
			alert("�޴˿ͻ���Ϣ����ȷ�Ͽͻ�����Ƿ�����");
		}
		});
	}
}
//ddeason ������ѯ������һ�����Ŷ�Ȳ�����
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
			alert("�޴˿ͻ���Ϣ����ȷ�Ͽͻ�����Ƿ�����");
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
  				alert("�޴˿ͻ���Ϣ����ȷ�Ͽͻ�����Ƿ�����");
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
  				alert("�޴˿ͻ���Ϣ����ȷ�Ͽͻ�����Ƿ�����");
  			}
  		});
	}
}

/**
 * ����˻���Ч��
 * @param accNo----�˻��Ŷ�ӦԪ��Id
 * @param bankNo----�����к�
 */
function getAccMessage(accNo){
	var acc = $("#"+accNo).val();
	if(acc!=""){
		$.post("getAccMsg.action",{accNo:acc},
  				function(dat){
  			if(dat!=""){
  			}else{
  				$("#"+accNo).val("");
  				alert("û�д��˻���Ϣ��");
  			}
  		});
	}
}


/**
 * ����˻���Ч��---��������֤-�ջ�����
 * @param accNo----�˻��Ŷ�ӦԪ��Id
 * @param bankNo----�����к�
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
  	  				alert("�����˺�ֻ��Ϊ������˺ţ�");
  				}
  			}else{
  				$("#"+accNo).val("");
  				alert("û�д��˻���Ϣ��");
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
  				alert("û�д��˻���Ϣ��");
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
  				alert("û�д��˻���Ϣ��");
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
  	  				alert("�۷��˺�ֻ��Ϊ������˺ţ�");
  				}
  			}else{
  				$("#"+accNo).val("");
  				alert("û�д��˻���Ϣ��");
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
  				alert("û�д��˻���Ϣ��");
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
  				alert("û�д�����֤����Ϣ��");
  			}
  		});
	}
}

/**
 * ��ȡ������Ϣ
 * @param tranNo----���ױ�Ŷ�ӦԪ��Id
 */
function getTranMessage(tranNo,tranType){
	var tran = $("#"+tranNo).val();
	if(tran!=""){
		$.post("getTranMsg.action",{tranNo:tran,tranType:tranType},
  				function(dat){
			if(dat!=""){
				if("S"!=dat){
					$("#"+tranNo).val("");
					alert("�˽����޷�����ֹ��������");
				}
			}else{
				$("#"+tranNo).val("");
				alert("û�д˽�����Ϣ��");
			}
		});
	}else{
		alert("�����뽻�ױ�ţ�");
	}
}

/**
 * ��ȡ������Ϣ
 * @param tranNo----���ױ�Ŷ�ӦԪ��Id
 */
function getTtStopPayInfo(tranNo){
	var tran = $("#"+tranNo).val();
	if(tran!=""){
		$.post("getTtStopPayInfo.action",{tranNo:tran},
  				function(dat){
			if(dat!=""){
				if("S"!=dat){
					$("#"+tranNo).val("");
					alert("�˽����޷�����ֹ��������");
				}
			}else{
				$("#"+tranNo).val("");
				alert("û�д˽�����Ϣ��");
			}
		});
	}else{
		alert("�����뽻�ױ�ţ�");
	}
}

/**
 * ��������
 * @param eleArray----������Ԫ������
 */
function mustInput(){
	var times=0;//ͬһradio�������
	var first=false;
	var second=false;
	var mustInput = $("[inputType='mustInput']");
	for(var i=0;i<mustInput.length;i++){
		if($(mustInput[i]).attr("type")=="radio"){//�ж�radio�Ƿ���ѡ��
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
					alert("��ѡ��"+$(mustInput[i]).attr("cName")+"��");
					return false;
				}
			}
		}else if($(mustInput[i]).attr("tagName")=="TEXTAREA"){//�ж�textarea�Ƿ�������
			if($(mustInput[i]).html==""){
			alert("������"+$(mustInput[i]).attr("cName")+"��");
			return false;
			}
		}else{
			if($(mustInput[i]).val()==""){
			alert("������"+$(mustInput[i]).attr("cName")+"��");
			return false;
			}
		}
	}
	return true;
}

/**
 * ���ò��ɲ����ϵͳ�Զ�¼��
 */
function systemInput(){
	$("[inputType='sysInput']").each(function(){
//		if($(this).attr("tagName")=="TEXTAREA"){
//			if($(this).html()==""||$(this).html()==null){
//				$(this).html("ϵͳ�Զ�¼��!");	
//			}
//		}else{
//			if($(this).val()==""||$(this).val()==null){
//				$(this).val("ϵͳ�Զ�¼��!");
//			}
//		}
		$(this).css("background","#E8E8E8");
		$(this).attr("readOnly",true);
	});
}

/**
	 * ¼���ύ���
	 * @param formName----����
	 * @param actionName----action��
	 * @param btnType----��������
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
					alert("���������");
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
 * �˻��ύ���
 * @param formName----����
 * @param actionName----action��
 * @param btnType----��������
 */
function returnSubmit(formName,actionName,tranType){
	var txterrWords=$("#txterrWords").val();
	var errReason=$("#errReason").val();
	if(null==txterrWords||txterrWords==""){
		alert("�빴ѡ�������");
		return;
	}
	if(null==errReason||errReason==""){
		alert("�˻����ɲ���Ϊ�գ�");
		return;
	}
	document.all(formName).action = actionName+".action?tranType="+tranType;
	document.all(formName).submit();
	processTip();
}

/**
 * �����ύ���
 * @param formName----����
 * @param actionName----action��
 * @param btnType----��������
 */
function approveSubmit(formName,actionName,tranType){
	var txterrWords=$("#txterrWords").val();
	var errReason=$("#errReason").val();
	if(null!=txterrWords&&txterrWords!=""){
		alert("�����ʶ��Ϊ�գ������ύ��Ϣ��");
		return;
	}
	if(null!=errReason&&errReason!=""){
		alert("�˻����ɲ�Ϊ�գ������ύ��Ϣ��");
		return;
	}
	document.all(formName).action = actionName+".action?tranType="+tranType;
	document.all(formName).submit();
	processTip();
}

/**
 * ����ͬ���ֽ�����
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
 * ����Դ������Ŀ����ֻ�ȡ��Ӧ�������
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
			alert("������"+$("#"+targetCcy).attr("cName")+"��");
			return;
		}
	}else{
		alert("������"+$("#"+sourceCcy).attr("cName")+"��");
		return;
	}
}

/**
 * ����Դ������Ŀ����ֻ�ȡ��Ӧ��������
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
			alert("������"+$("#"+targetCcy).attr("cName")+"��");
			return;
		}
	}else{
		alert("������"+$("#"+sourceCcy).attr("cName")+"��");
		return;
	}
}

/**
 * ���ڻ������м����۳������
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
 * ����ת�����������
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
 * ����ת������������
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
	var numberValue=new String(Math.round(numberValue*100)); // ���ֽ��  
	var chineseValue=""; // ת����ĺ��ֽ��  
	var String1 = "��Ҽ��������½��ƾ�"; // ��������  
	var String2 = "��Ǫ��ʰ��Ǫ��ʰ��Ǫ��ʰԪ�Ƿ�"; // ��Ӧ��λ  
	var len=numberValue.length; // numberValue ���ַ�������  
	var Ch1; // ���ֵĺ������  
	var Ch2; // ����λ�ĺ��ֶ���  
	var nZero=0; // ����������������ֵ�ĸ���  
	var String3; // ָ��λ�õ���ֵ  
	if(len>15){  
		alert("�������㷶Χ");  
		return "";  
	}  
	if (numberValue==0){  
		chineseValue = "��Ԫ��";  
		return chineseValue;  
	}  
	String2 = String2.substr(String2.length-len, len); // ȡ����Ӧλ����STRING2��ֵ  
	for(var i=0; i<len; i++){  
		String3 = parseInt(numberValue.substr(i, 1),10); // ȡ����ת����ĳһλ��ֵ  
		if ( i != (len - 3) && i != (len - 7) && i != (len - 11) && i !=(len - 15) ){  
			if ( String3 == 0 ){  
				Ch1 = "";  
				Ch2 = "";  
				nZero = nZero + 1;  
			}  
			else if ( String3 != 0 && nZero != 0 ){  
				Ch1 = "��" + String1.substr(String3, 1);  
				Ch2 = String2.substr(i, 1);  
				nZero = 0;  
			}  
			else{  
				Ch1 = String1.substr(String3, 1);  
				Ch2 = String2.substr(i, 1);  
				nZero = 0;  
			}  
		}  
		else{ // ��λ�����ڣ��ڣ���Ԫλ�ȹؼ�λ  
			if( String3 != 0 && nZero != 0 ){  
				Ch1 = "��" + String1.substr(String3, 1);  
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
			if( i == (len - 11) || i == (len - 3)){ // �����λ����λ��Ԫλ�������д��  
				Ch2 = String2.substr(i, 1);  
			}  
		}  
		chineseValue = chineseValue + Ch1 + Ch2;  
	}  
	if ( String3 == 0 ){ // ���һλ���֣�Ϊ0ʱ�����ϡ�����  
		chineseValue = chineseValue + "��";  
	}  
	return chineseValue;  
}

//���س�ֱ�ӽ�����һ��Ԫ��
window.document.onkeydown = function(){
	if(event.keyCode == 13){
		event.keyCode=9;
	}
};

function ifEnglist(str){
	var Criteria1=/[^a-zA-Z0-9\-\=\_\~\$\%\^\&\*\+\|\!\@\{\}\-\`\_\:\\\.\;\?\,\#\/\[\]\(\)\x20]/;    //��Сд��ĸ������
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





