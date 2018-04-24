		var childId_global;
		var sysId_global="B";
		var idvalue;
		var namevalue;
		var idvalue_no;
		var idvalue_no1;
		var namevalue_no;
		var namevalue_no1;
		var idvalue_length;
		var idvalue_length1;
		var nexthref="";
		var backhref="";
		var dataArr;
		
		//系统选择		
		function radioChange2(){
			var chk = $('input[@name=choose][@checked]').val();
			if(chk=='hexin'){
			//柜面
				sysId_global="B";
				$("#parent1").attr("disabled",false);
				$("#parent2").attr("disabled",true);
				$("#select12").attr("disabled",false);
				$("#select121").attr("disabled",false);
				$("#select22").attr("disabled",true);
			}else{
			//票据
				sysId_global="C";
				$("#parent2").attr("disabled",false);
				$("#parent1").attr("disabled",true);
				$("#select22").attr("disabled",false);
				$("#select121").attr("disabled",true);
				$("#select12").attr("disabled",true);
			}
		}
//**********************************************************************组 非综合题模板 begin//			
		function selectChild(cid){						
			var childId1=$("#"+cid).val();
			childId_global=childId1;
			if(childId1=='')return;
			$("#yaosu").html("题目的采分点：<br/>");
			$("#timuxianshi").html("题目上显示要素：<br/>");
			$.post("asmElementsByAsmPointNum_Ajax.action",{childId:childId1,rand:Math.round(Math.random()*10000)},function(dat){
				//alert(dat);
				var tem1=$.trim(dat).split("&:");
				var tem=$.trim(tem1[0]).split("@@");
				var htmls="";
				var defSelect;
				idvalue=$.trim(tem[0]).split("$$");				
				dataArr=idvalue;
				
				namevalue=$.trim(tem[1]).split("$$");
				idvalue_length=tem.length;
				idvalue_length1=tem1.length;
				if(tem.length>3){
					idvalue_no=tem[2].split("$$");
					namevalue_no=tem[3].split("$$");
				}
				
				if(tem1.length>1){
					idvalue_no1=tem1[1].split("$$");
					namevalue_no1=tem1[2].split("$$");
					defSelect = tem1[3].split("$$");
					for(var n=0;n<idvalue_no1.length;n++){							
						$("#timuxianshi").append("<input type='checkbox' name='"+idvalue_no1[n]+"' id='tm_"+idvalue_no1[n]+"'>"+namevalue_no1[n]+"</input>&nbsp;&nbsp;&nbsp;");
						if((n+1)%6==0 && n!=0){
							$("#timuxianshi").append("<br/>");
						}
					}
				}
				
				for(var n=0;n<idvalue.length;n++){				
					$("#yaosu").append("<input type='checkbox' name='"+idvalue[n]+"' id='"+idvalue[n]+"'>"+namevalue[n]+"</input>&nbsp;&nbsp;&nbsp;");
					if((n+1)%6==0 && n!=0){
						$("#yaosu").append("<br/>");
					}
				}
				
				if(tem.length>3){
					for(var n=0;n<idvalue_no.length;n++){							
						$("#xiangguanyaosu").append("<input type='checkbox' name='"+idvalue_no[n]+"' id='"+idvalue_no[n]+"'>"+namevalue_no[n]+"</input>&nbsp;&nbsp;&nbsp;");
						if((n+1)%6==0 && n!=0){
							$("#xiangguanyaosu").append("<br/>");
						}
					}
				}
				//alert(defSelect);
				if(defSelect.length>0){
					for(var n=0;n<defSelect.length;n++){
						$("#"+defSelect[n]).attr("checked",true);
						$("#tm_"+defSelect[n]).attr("checked",true);
					}
				}
			});
		}
		
		//组非综合题模板，确定模板要素
		function sureyaosu(){			
			var caifen="";
			var xianshi="";
			var xianshiId="";
			var tmp=false;
			//alert("idvalue_length"+idvalue_length);
			$("#content1").html('');
			if(idvalue_length>1){
				for(var i=0;i<idvalue.length;i++){
					tmp=$("#"+idvalue[i]).attr("checked");
					if(tmp==true){
						caifen+=namevalue[i]+": ["+namevalue[i]+"] ,";
					}
				}
			}
			if(caifen==""){
				alert("提交失败！");
				return;
			}
			if(idvalue_length1>2){
				for(var i=0;i<idvalue_no1.length;i++){					
					tmp=$("#tm_"+idvalue_no1[i]).attr("checked");
					if(tmp==true){
						xianshi+=namevalue_no1[i]+":["+namevalue_no1[i]+"],";
						xianshiId=xianshiId+"$$"+idvalue_no1[i];
					}
				}
			}
			if(xianshiId.indexOf("$$")!=-1){
				xianshiId=xianshiId.substring(2,xianshiId.length);
				//alert(xianshiId);
				$.post("modelDescrition.action",{xianshiId:xianshiId,rand:Math.round(Math.random()*10000)},function(dat){
					if(dat.indexOf("fail$$")!=-1){
						alert(dat.replaceAll("fail$$",""));
					}else{
						if(dat.indexOf("&noModel&$$")!=-1){
						var sArr = dat.split("$$");
						$("#content1").val(sArr[1]);
						//	$("#content1").val(dat.replaceAll("&noModel&$$",""));
						}else{
							if(dat.indexOf("@@")!=-1){
								var sArr = dat.split("@@");
								var htmls= "";
								for (var i=0;i<sArr.length;i++){
									var descr = sArr[i].split("$$");
									htmls +='<input type="radio" name="groupDescr" value="'+descr[0]+'" id="des_'+descr[0]+'" label2="'+descr[1]+'" >'+descr[1]+'</input><br/>'; 
								}
								//alert(htmls);
								$("#lightBoxWrapper").html(htmls);
								showLayer();
							}else{
								var sArr = dat.split("$$");
								$("#content1").val(sArr[1]);
							}
						}
					}
				}); 
			}
			$("#content1").attr("disabled",false);
			$("#surec1").attr("disabled",false);	
		}
		
		function back(){
			window.location.reload();
		};
		
		
		var txt = '已经成功生成“非综合题模板”,点击“确定”跳转到（非综合题模板采分点）操作界面。<br/>点击“取消”继续生成（非综合题模板）。';
		
		
		//确定模板描述
		function surecontent1(){
			var temp="";
			temp=$("#content1").val();
			if(temp==""){
				alert("空的题目模板！");
				return ;
			}
			$("#content2").attr("value",temp);
			$("#content2").attr("disabled",false);
			$("#surec2").attr("disabled",false);
		}
		//确定模板
		function surecontent2(){
			//$("#fenzhong").attr("disabled",false);
			//$("#miao").attr("disabled",false);
			//$("#timePersent").attr("disabled",false);
			//$("#fenzhong").attr("value",0);
			//$("#miao").attr("value",0);
			//$("#timePersent").attr("value",0);
			$("#nanyi").attr("disabled",false);
		}
		//提交
		function formsubmit(){
			var asmSysId_local=sysId_global;
			var asmPointId_local=childId_global;
			var model_local;
			var limTime_local=0;
			var levels_local;
			
			var idString='';
			var valueString='';
			model_local=$("#content2").val();
			levels_local=$("#nanyi").val();
			var miao_temp=0;
			var fen_temp=0;
			
			//20120814 begin
			var dataString="";
			var timeLocal=0;
			var rangLocal=100;
			if(NaN==$("#timePersent").val() || undefined==$("#timePersent").val()){
			}else{
				timeLocal=$("#timePersent").val();
				rangLocal=100-timeLocal;
			}
			
			if(NaN==dataArr||undefined==dataArr||dataArr.length<1){alert("提交失败！");return;}
			for(var n=0;n<dataArr.length;n++){
				if($("#check_"+dataArr[n]).attr("checked")==true){
					dataString+=dataArr[n];
					dataString+="$$";
				}
			}
			var txt = '已经成功生成“非综合题模板”。';
			dataString=dataString.substring(0,dataString.lastIndexOf("$$"));
			//20120814 end
			/*
			if(NaN==$("#miao").val()||undefined==$("#miao").val()){
				
			}else{
				miao_temp=parseInt($("#miao").val());
			}		
			if(NaN==$("#fenzhong").val()||undefined==$("#fenzhong").val()){
				
			}else{
				fen_temp=parseInt($("#fenzhong").val());
			}	
			limTime_local=parseInt(fen_temp*60+miao_temp);*/
			if(model_local==''||levels_local=='')
			{
				alert("请检查是否全部都已输入！");
				return ;
			}	
			var idvalue_all=idvalue+idvalue_no;
			if(idvalue_length>1){
				for(var i=0;i<idvalue.length;i++){
					idString+=idvalue[i];
					valueString+=$("#"+idvalue[i]).attr("checked");
					if(i!=idvalue.length-1){
						idString+=",";
						valueString+=",";
					}
				}
			}
			if(idvalue_length>2){
				idString+=",";
				valueString+=",";
				for(var i=0;i<idvalue_no.length;i++){
					idString+=idvalue_no[i];					
					valueString+=$("#"+idvalue_no[i]).attr("checked");
					if(i!=idvalue_no.length-1){
						idString+=",";
						valueString+=",";
					}
				}
			}
			idString+="@@";
			idString+=valueString;
			//alert(idString);//4
			//var data1="asmSysId="+asmSysId_local+"&asmPointId="+asmPointId_local+"&model_1="+model_local+"&limTime="+limTime_local+"&levels="+levels_local+"&mpElements="+idString;
			//data1=encodeURI(data1); 
			//alert(data);
			$.ajax({
				url: "nceModelSave_Ajax.action",
				type: "POST",
				global:false,				
				data: {
				asmSysId:asmSysId_local,
				asmPointId:asmPointId_local,
				model_1:window.encodeURI(model_local),
				limTime:limTime_local,
				levels:levels_local,
				mpElements:window.encodeURI(idString),
				rand:Math.round(Math.random()*10000),
				 timeGradePercent:timeLocal,
				 rangGradePercent:rangLocal,
				 data:dataString
				},
				success: function(dat){
					if(dat=="success"){
						alert(txt);
						location.href="nceModelAddInitPage.action";
						/*jConfirm(txt, '生成模板成功', function(r) {
						
						if(r){
							location.href = "nceModelMpAddPage.action";
						}else{
							location.href="nceModelAddInitPage.action";
						}
						});	*/
					}else{
						alert("提交失败！");
					}
				}
			});
		}
		
		function onLayerSubmit(){
			var descr ="";
			descr=$("input[@type=radio][@name=groupDescr][@checked]").val();
			if(descr==""){
				alert("选择错误");
			}else{
				//alert(descr);
				var ht= $("#des_"+descr).attr("label2");
				$("#content1").val(ht)
				hideLayer();
			}
		}		
function showLayer() { document.getElementById("lightBox").style.display = "block"; }
function hideLayer() { document.getElementById("lightBox").style.display = "none"; }
