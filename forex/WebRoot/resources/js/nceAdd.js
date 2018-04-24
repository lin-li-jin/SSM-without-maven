//----------------------------------------------------------------------------------------
var grobal_childId="";
var grobal_nceModelId="";
var grobal_pkgNum="";


function showLayer() { document.getElementById("lightBox").style.display = "block"; }
function hideLayer() { document.getElementById("lightBox").style.display = "none"; }


//取出日志
function modelOnClick(){
		var bannerValue = $('input[@name=radioGroup]:checked').val();
		var pkgNum =$("#pkgNum").val();
		if(bannerValue==undefined||bannerValue==NaN)return false;
		grobal_nceModelId=bannerValue;
		grobal_pkgNum = pkgNum;
		$.post("getMoTxInfoLog.action",{queryId:grobal_childId,pkgNum:grobal_pkgNum},function(dat){
			//alert(dat);
			if(dat=="empty"){
				//alert("值为空，请重新选择！");
				$("#hiddenkebian").empty();
				return;
			}else{					
				$("#hiddenkebian").html(dat);
			}								
		});
}
//翻页查询日志
function getPagnationByPrcscod(prcscod,pkgNum,pageNo,pageSize){
	if(NaN==prcscod||""==prcscod)return;
	if(NaN==pageSize||""==pageSize)return;
	if(NaN==pageNo||""==pageNo)return;
	$.post("getPagnationByPrcscod.action",{prcscod:prcscod,pkgNum:pkgNum,pageSize:pageSize,pageNo:pageNo}
		,function(dat){
		//alert(dat);
		if(dat=="empty"){
			$("#hiddenkebian").empty();
			return;
		}else{					
			$("#hiddenkebian").html(dat);
		}								
	});
}
function updateNce(nceId){
	$("#lightBoxWrapper").html();
	var content=$("#nce_"+nceId).html();
	var htmls="";
    htmls+="<textarea nceId='"+nceId+"' style='font-size:12px;color:#008B8B;' id='nce_content' cols='135' rows='5'>"+content+"</textarea>";
    $("#lightBoxWrapper").html(htmls);
	showLayer();
	$("#nce_content").focus();
}
function outLayerSubmit(){
	var nceId =$("#nce_content").attr("nceId");
	var content=$("#nce_content").val();
	if(nceId==""||content==""){
		alert("提交错误");
		return ;
	}
	
	$.post("updateNceExercisesContent.action",{nceId:nceId,content:window.encodeURI(content)},function(dat){
		if(dat=="success"){
			$("#nce_"+nceId).html(content);
			alert("修改题目描述成功");
		}else{
			alert("提交错误");	
		}
	});
	hideLayer();
}

function allcheck(){
	if("true"==$("#selectAllButton").attr("selectFlag")){
		$("input[@name=asmMoTxInfo][@type=checkbox]").each(function(){
			$(this).attr("checked",true);
			});
			$("#selectAllButton").attr("selectFlag","false")
			//$("#selectAllButton").html("取消全选");
			$("#selectAllButton").val("取消全选");
		}else{
			$("input[@name=asmMoTxInfo][@type=checkbox]").each(function(){
				$(this).attr("checked",false);
				});
			$("#selectAllButton").attr("selectFlag","true")
			//$("#selectAllButton").html("全选");
			$("#selectAllButton").val("全选");
			}
}

function submitCheck(){		
	var localNceModelId=grobal_nceModelId;
	var localChildId=grobal_childId;
	var localLogId="";
	$("input[@name=asmMoTxInfo][@type=checkbox][@checked]").each(function(){
		localLogId=localLogId+$(this).val()+"$$";
		//alert(localLogId);	
	});
	//alert(localLogId);
	if(undefined==localLogId||NaN==localLogId||""==localLogId){
		alert("没有选择可变域！");		
		return;
	}
	$.post("nceExercisesAdd.action",{nceModelId:localNceModelId,
		childId:localChildId,logId:localLogId,pkgNum:grobal_pkgNum},function(dat){
		if(dat==null||dat==NaN){
			
		}else{
			var resultArr=dat.split("@@");
			if(resultArr[0]=="success"){
				$("#hiddenDiv1").css("display","block");
				$("#hiddenDiv").css("display","block");
				$("#hiddenDiv").empty();
				var succ=0;
				for(var i=1;i<resultArr.length;i++){
					if(resultArr[i].indexOf("$$")!=-1){
						succ++;
						var nceArr =resultArr[i].split("$$");
						var htmls="";
						htmls+="<span id='nce_"+nceArr[0]+"'>"+nceArr[1];
						htmls+="</span>&nbsp;&nbsp;<img onmouseover=\"this.style.cursor='hand'\" onmouseout=\"this.style.cursor='normal'\"";
						htmls+="src='/ees/images/Pencil.png' alt='修改文字' nceId='"+nceArr[0]+"' width='16px' heigth='16px'  onclick='updateNce(this.nceId)'>";
						$("#hiddenDiv").append("<br/>");
						$("#hiddenDiv").append(htmls);
					}else{			
						$("#hiddenDiv").append("<br/>");
						$("#hiddenDiv").append("题目生成，匹配失败……");
					}
				}
				alert("生成"+succ+"道试题,加入非综合题题库！");									
			}else{
				alert("提交有误，生成题目不成功！");
			}
		}
	});
}

//childId1 是 asmPointNum 依据考核点获取 题目模板
function selectChild(cid){			
	var childId1=$("#"+cid).val();
	//alert(childId1);
	if(childId1==''||childId1==NaN)return;
	grobal_childId=childId1;
	$.post("nceModelByAsmPointNum.action",{childId:childId1},function(dat){
		//alert(dat);
		if(dat=="empty"){
			alert("此考核点没有对应的题目模板，请重新选择！");
			$("#NCEModel").empty();
			$("#hiddenDiv").empty();
			$("#hiddenkebian").empty();
			return;
		}else{
			if(dat.indexOf("@@")<0){
				$("#NCEModel").empty();
				$("#hiddenDiv").empty();
				$("#hiddenkebian").empty();
				var data=dat.split("$$");
				var modelId=data[0];
				//alert(modelId);
				var model=data[1];
				
				$("#NCEModel").append("<input type='radio' onclick= \"modelOnClick()\" name='radioGroup' value='"+modelId+"'>"+model+"</input>&nbsp;&nbsp;&nbsp;");
				$("#NCEModel").append("<br/>");
			}else{
				$("#NCEModel").empty();
				var tem=dat.split("@@");
				//alert(dat);
				for(var i=0;i<tem.length;i++){
					var infoValue=tem[i];
					//alert(infoValue);
					var nceModel=infoValue.split("$$");
					var modelId=nceModel[0];
					////alert(modelId);
					var model=nceModel[1];
					$("#NCEModel").append("<input type='radio' name='radioGroup' onclick= \"modelOnClick()\" value='"+modelId+"'>"+model+"</input>&nbsp;&nbsp;&nbsp;");
					$("#NCEModel").append("<br/>");
				}
			}
		}
	});
}

