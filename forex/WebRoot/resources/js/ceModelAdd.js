var childId_global;
		var sysId_global="B";
		var idvalue;
		var namevalue;
		var idArr_global;
		var nameArr_global;
		var idvalue_length;
		var idsure=new Array();
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
		function selectChild(cid){
			idvalue=NaN;
			namevalue=NaN;
			var childId1=$("#"+cid).val();
			childId_global=childId1;
			if(childId1=='')return;
			$("#listModel").html("请选择题目模板:</br>");
			$.post("ceMinModelByAsmPointNum_Ajax.action",
				{childId:childId1},
				function(dat){
					var tem=$.trim(dat).split("@@");
					var htmls="";
					//alert(dat);
					idvalue=$.trim(tem[0]).split("$$");
					namevalue=$.trim(tem[1]).split("$$");
					idvalue_length=tem.length;			
					for(var n=0;n<idvalue.length;n++){
						$("#listModel").append("<input type='checkbox' name='checkname_"+idvalue[n]+"' id='checkbox_"+idvalue[n]+"' value='"+idvalue[n]+"'>"+namevalue[n]+"</input><br/>");
					}
			});
		};
		
		
		
		function removeAll(){
			$("#listModel").html("请选择题目模板:</br>");
			idvalue=null;
			nameValue=null;
		}
			
		function sureModel(){
			if(idvalue.length<1){
				alert("没有任何模板被选中");
				return;
			}
			for(var n=0;n<idvalue.length;n++){
				if($("#checkbox_"+idvalue[n]).attr("checked")==true){
					idsure.push(idvalue[n]);
					$("#addXuhao").append("<div id='span_"+idvalue[n]+"' >");
					$("#addXuhao").append(productSelect("xuhaoSeId_"+idvalue[n],"xuhaoSeNa_"+namevalue[n]));
					$("#addXuhao").append("<input type='checkbox' name='xuhaoChBoxNa_"+idvalue[n]+"' id='xuhaoChBoxId_"+idvalue[n]+"' value='"+idvalue[n]+"'><span id='label_"+idvalue[n]+"'> "+namevalue[n]+"</span> </input><br/>");
					$("#addXuhao").append("</div>");
				}
			}
		}
				
		function removeAll2(){
			$("#addXuhao").html("小题序号：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;小题模板<br/>");
			idsure=null;
			idsure=new Array();
		}
		 
		 
		function deleteOne2(){
			if(idsure.length>0){
				for(var n=0;n<idsure.length;n++){
					if($("#xuhaoChBoxId_"+idsure[n]).attr("checked")==true){
						$("#span_"+idsure[n]).remove();
					}
				}
			}	
		}
		
	removeArray=function(rindex,arr){ //该方法用于delete指定索引处的元素，并将容器的容量减一
        try{
            if(rindex>=this.size || rindex<0){
                throw "the ArraysIndexOutOfException: "+rindex;
            }
        }catch(e){
            
            alert(e);
            return;
        }
        
        
        var nextindex=0;
        var recordvalue=new Array(arr.length-1);
        
        for(var index1=0;index1<arr.length;index1++){
            if(index1==rindex){
                continue;
            }
            recordvalue[nextindex]=this.value[index1];
            ++nextindex
        }
        
       return recordvalue;  
    }
		
		
		function sureModel2(){
			$("#fenzhong").attr("disabled",false);
			$("#miao").attr("disabled",false);
			$("#nanyi").attr("disabled",false);
			$("#timuName").attr("disabled",false);
			$("#submit").attr("disabled",false);
			$("#back").attr("disabled",false);
			var cankaoTime="";
			var tmp="";
			if(idsure.length>0){
				for(var i=0;i<idsure.length;i++){
					tmp+=idsure[i];
					if(i<idsure.length-1){
						tmp+="$$";
					}
				}
			}else{
				alert("你没有任何选择！");
			}
			if(tmp!=""){	
				$.post("allModelTimeQuery_Ajax.action",{allModelId:window.encodeURI(tmp)},
					function(dat){
						if(dat!=''&&dat!=NaN){
							$("#cankaoshijian").html(dat);
						}
					}
				);
			}
		}
		
		
		function formsubmit(){
			if(idsure.length<1){
				alert("你没有选择任何模板!");
				return;
			}
			var ltime=parseInt($("#fenzhong").val())*60+parseInt($("#miao").val());
			var lmName=$("#timuName").val();
			lmName=window.encodeURI(lmName);
			var lxh="";
			var lnceId="";
			for(var i=0;i<idsure.length;i++){
				if($("#xuhaoSeId_"+idsure[i]).val()!=''){
					lxh+=$("#xuhaoSeId_"+idsure[i]).val();
					lnceId+=idsure[i];
					if(i<idsure.length-1){
						lxh+="$$";
						lnceId+="$$";
					}
				}else{
					alert("没有为综合题的小题安排序号！");
					return;
				}
			}
			var llevels=$("#nanyi").val();
			if(llevels==NaN || llevels==""){
				return;
			}
			var multipointId_ = $("#multipointId").val();
			if(!multipointId_){
				alert("没有选择综合题类别");
			}
			$.post("ceModelAdd_Ajax.action",
			   {	limTime:ltime,
					modelName:lmName,
					levels:llevels,
					xuhaoArr:lxh,
					nceIdArr:lnceId,
					multipointId:multipointId_
			    },function(dat){
					if(dat=="success"){
						alert("生成综合题模板成功！");
						window.location="ceModelAddPage.action";
					}else{
						alert("Error!");
					}
				}
			);
		}
		
		
		
		function productSelect(ids,names){
			var part1="<select name='"
			var part2="' id='";
			var part3="' ><option>序号</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option>";
			var part4="<option value='4'>4</option><option value='5'>5</option>";
			var part5="<option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option></select>";
			var allPart=part1+names+part2+ids+part3+part4+part5;
			return allPart;
		};
		
		
		function back1(){
			window.location.reload();
		};	