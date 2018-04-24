 /**
 * Amendment No : forex130012
 * Modify    By : GUOGM
 * Date         : 2013-08-13
 * Description  : 汇出汇款
 */

		/**
	 	 * 根据用户的角色，设置元素可操作性
	 	 * @param postName----岗位名称
	 	 * @param errWords----错误标识
	 	 */
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
	 						var tagName = $("#"+value).attr("tagName");
	 						if(tagName=="SELECT"){
	 							//禁止选择，实现原理是所选项改为默认项
	 							$("#"+value).focus(function(){
	 								this.si=this.selectedIndex;
	 							});
	 							$("#"+value).change(function(){
	 								this.selectedIndex=this.si;
	 							});
	 						}else if($("#"+value).attr("type")=="radio"){
	 						}else{
	 							$("#"+value).attr("readonly",true);
	 						}
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
	 			$("input").attr("readonly",true);
	 			$("select").attr("disabled",true);
	 			$("[type='radio']").attr("disabled",true);
	 			$("textarea").not("#errReason").attr("readonly",true);
	 			$("[class='Wdate']").focus(function(){});//解除日期选择函数绑定
	 		}
	 	}
		
	 	/**
	 	 * 录入提交检查
	 	 * @param formName----表单名
	 	 * @param actionName----action名
	 	 * @param btnType----操作类型
	 	 */
		function signSubmit(formName,actionName,tranType){
			if(tranType=="RESET"){
				if(formName=="ttRemittanceForm"){
					window.location="ttRemittanceInit.action";
				}
				if(formName=="ttReturnForm"){
					window.location="ttReturnInit.action";
				}
				if(formName=="ttStopPayForm"){
					window.location="ttStopPayInit.action";
				}
			}else{
				var check = mustInput();
				if(check==true){
					document.all(formName).action = actionName+".action?tranType="+tranType;
					document.all(formName).submit();
					processTip();
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
	 	 * 打开新窗口
	 	 * @param url----窗口地址
	 	 * @param name----窗口名
	 	 * @param iWidth----窗口宽度
	 	 * @param iHeight----窗口高度
	 	 */
   		function openwindow(url,name,iWidth,iHeight){
   			var url; //转向网页的地址;
    		var name; //网页名称，可为空;
    		var iWidth; //弹出窗口的宽度;
    		var iHeight; //弹出窗口的高度;
    		var iTop = (window.screen.availHeight-30-iHeight)/2; //获得窗口的垂直位置;
    		var iLeft = (window.screen.availWidth-10-iWidth)/2; //获得窗口的水平位置;
    		var logWindow = window.open(url,name,'resizable=yes, menubar=no,status=yes,width='+iWidth+',height='+iHeight+',top='+iTop+',left='+iLeft);
    		logWindow.focus();                   //弹出窗口获得焦点
    		return false;
   		}
   		
   		/**
	 	 * 设置不可操作项，系统自动录入
	 	 */
   		function systemInput(){
   			$("[inputType='sysInput']").each(function(){
   				if($(this).attr("tagName")=="TEXTAREA"){
   					$(this).html("系统自动录入!");
   					
   				}else{
   					$(this).val("系统自动录入!");
   				}
   				$(this).attr("readonly",true);
   			});
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
   	   					var bankInfo = new Array();
   	   					bankInfo = dat.split("$$");
   	   					$("#"+bankName).val(bankInfo[0]);
   	   					$("#"+bankAdd).val(bankInfo[1]);
   	   				}else{
   	   				$("#"+position).html("无此银行信息，请确认银行编号是否无误！");
   	   				}
   	   			});
   			}
   		}
   		
   		/**
	 	 * 获取账户信息
	 	 * @param accNo----账户号对应元素Id
	 	 * @param bankNo----银行行号
	 	 * @param position----显示账户信息对应元素Id
	 	 */
   		function getCustMessage(custNo,bankNo,custName,custAdd){
   			var number = $("#"+custNo).val();
   			if(number!=""){
   				$.post("getCustMsg.action",{custNo:number,bankNo:bankNo},
   	   					function(dat){
   	   				if(dat!=""){
   	   					var bankInfo = new Array();
   	   					bankInfo = dat.split("$$");
   	   					$("#"+custName).val(bankInfo[0]);
   	   					$("#"+custAdd).val(bankInfo[1]);
   	   				}else{
   	   				$("#"+position).html("无此客户信息，请确认账户编号是否无误！");
   	   				}
   	   			});
   			}
   		}
   		
   		/**
	 	 * 检测账户有效性
	 	 * @param accNo----账户号对应元素Id
	 	 * @param bankNo----银行行号
	 	 */
   		function getAccMessage(accNo,bankNo){
   			var number = $("#"+accNo).val();
   			if(number!=""){
   				$.post("getAccMsg.action",{accNo:number,bankNo:bankNo},
   	   					function(dat){
   	   				if(dat!=""){
   	   				}else{
   	   					alert("此账户号无效！");
   	   				}
   	   			});
   			}
   		}
   		
   		/**
	 	 * 获取交易信息
	 	 * @param tranNo----交易编号对应元素Id
	 	 */
   		function getTranMessage(tranNo){
   			var tran = $("#"+tranNo).val();
   			if(tran!=""){
   				$.post("getRemittanceMsg.action",{tranNo:tran},
   	   					function(dat){
   					if(dat!=""){
   						if("S"!=dat){
   							alert("此交易无法进行止付操作！");
   						}
   					}else{
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
   							alert("此交易无法进行止付操作！");
   						}
   					}else{
   						alert("没有此交易信息！");
   					}
   				});
   			}else{
   				alert("请输入交易编号！");
   			}
   		}
   		
   		/**
	 	 * 格式化金额
	 	 * @param val----金额
	 	 */
   		function fmoney(val)
   		{
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
   		  	if(s=="NaN"){
   		  		return "";
   		  	}else{
   		  		return t.split("").reverse().join("") + "." + r;
   		  	}
   		}
   		
   		/**
	 	 * 必输项检测
	 	 * @param eleArray----必输项元素数组
	 	 */
   		function mustInput(){
   			var times=0;//同一radio进入次数
   			var mustInput = $("[inputType='mustInput']");
   			for(var i=0;i<mustInput.length;i++){
   				if($(mustInput[i]).attr("type")=="radio"){//判断radio是否有选择
   					if(times==0){
						times++;
						var checked=false;
						var radio = $("#"+$(mustInput[i]).attr("id"));
						for(var j=0;j<radio.length;j++){
							if(radio[j].checked==true) checked=true;
						}
						if(checked==false){
							alert("请选择"+$(mustInput[i]).attr("cName")+"！");
							return false;
						}
					}
					else{
						times=0;
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