//非综合题采分点专用
var childId_global;
		var sysId_global="B";
		var idvalue_length;
		var tempId;
		var dataArr;
		var limTime_grobal;
			
function selectChild(cid){						
			var childId1=$("#"+cid).val();
			childId_global=childId1;
			if(childId1=='')return;
			$("#listModel").html("模板：<br/>");
			$.post("nceModelByAsmPointNum_Ajax.action",{childId:childId1},function(dat){
				var tem=$.trim(dat).split("@@");
				var htmls="";
				$("#listModel").empty();
				//alert(dat);
				idvalue=$.trim(tem[0]).split("$$");
				namevalue=$.trim(tem[1]).split("$$");
				idvalue_length=tem.length;
				htmls+="<table border='0'>"
				for(var n=0;n<idvalue.length;n++){
					htmls+="<tr><td width='3%'><input type='radio' onclick='importMp(this.eid)' name='model_radio' id='model_";
					htmls+=idvalue[n];
					htmls+="' eid='";
					htmls+=idvalue[n];
					htmls+="' /></td><td width='97%' >";
					htmls+=namevalue[n];
					htmls+="</td></tr>";
				}
				htmls+="</table>";
				$("#listModel").append(htmls);
			});
		};
		
		
function importMp(eid){
			tempId = eid;
			//alert(tempId);
			if(NaN==tempId||undefined==tempId||""==tempId){
					alert("没有选择模板！");
					return;
			}
			$.post("nceModelById_Ajax.action",{modelId:tempId},function(dat){
				var tem=$.trim(dat).split("@@");
				var htmls="";
				$("#cfdEle").html(htmls);
				//alert(dat);
				var part1=$.trim(tem[0]).split("$$");
				var part2=$.trim(tem[1]).split("$$");
				var part3=$.trim(tem[2]).split("$$");
				dataArr=part2;
				limTime_grobal=part1[1];
				if(idvalue_length=tem.length>2){
					$("#cfdTime").val(part1[1]+"秒钟");
					for(var n=0;n<part2.length;n++){
						$("#cfdEle").append("<input checked='true' type='checkbox' name='check'"+"' id='check_"+part2[n]+"'>"+part3[n]+"</input>&nbsp;&nbsp;");
						if((n+1)>4 && (n+1)%4==0){
							$("#cfdEle").append("<br/>");
						}
					}
				}
				$("#mpSet").show();
			});
		}
		
function back(){
			window.location="nceModelMpAddPage.action";
		}
		
function nceModelMpForm(){
			var dataString="";
			var timeLocal="";
			var rangLocal="";
			timeLocal=$("#timePersent").val();
			rangLocal=$("#valuePersent").val();
			if(NaN==dataArr||undefined==dataArr||dataArr.length<1){alert("提交失败！");return;}
			if(NaN==timeLocal||""==timeLocal){alert("提交失败！");return;}
			if(NaN==rangLocal||""==rangLocal){alert("提交失败！");return;}
			for(var n=0;n<dataArr.length;n++){
				if($("#check_"+dataArr[n]).attr("checked")==true){
					dataString+=dataArr[n];
					dataString+="$$";
				}
			}
			var txt = '已经成功生成“非综合题采分点模板”。';
			dataString=dataString.substring(0,dataString.lastIndexOf("$$"));
			$.post("nceMpModelSave_Ajax.action",
				{limTime:limTime_grobal,
				 timeGradePercent:timeLocal,
				 rangGradePercent:rangLocal,
				 data:dataString,
				 modelId:tempId
				},function(dat){
					//alert(dat);
					if(dat=="success"){
						jConfirm(txt, '生成采分点模板成功', function(r) {
							if(r){
								location.href = "nceModelMpAddPage.action";
							}else{
								location.href="nceModelAddPage.action";
							}
						});	
					}else{
						alert("生成采分点模板不成功!");
					}
				});
		}
