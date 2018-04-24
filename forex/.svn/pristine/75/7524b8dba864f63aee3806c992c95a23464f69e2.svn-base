var childId_global;
		var sysId_global="B";
		var idvalue_length;
		
		function radioChange2(){
			var chk = $('input[@name=choose][@checked]').val();
			if(chk=='hexin'){
				sysId_global="B";
				$("#parent1").attr("disabled",false);
				$("#parent2").attr("disabled",true);
				$("#select12").attr("disabled",false);
				$("#select22").attr("disabled",true);
			}else{
				sysId_global="C";
				$("#parent2").attr("disabled",false);
				$("#parent1").attr("disabled",true);
				$("#select22").attr("disabled",false);
				$("#select12").attr("disabled",true);
			}
		};
		
		function selectChild(cid){						
			var childId1=$("#"+cid).val();
			window.location.href="nceExercisesQuery.action?childId="+childId1;
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
		function showLayer() { 
			document.getElementById("lightBox").style.display = "block"; 
			$("#popupCover_id").css("height", document.body.scrollHeight+"px");
			
			//$("#popupCover_id").css("hi","middle");
			//$("#lightBox_id").css("position","absolute");
			//$("#lightBox_id").css("top","500px");
			//$("#lightBox_id").css("vertical-align","middle");
			//$("#lightBoxMaxHeight").css("vertical-align","middle");
			$("#lightBoxContent_id").css("position","absolute");
			
			
			// $(window).scroll(function(){
			// 	var yScol=document.documentElement.scrollTop; 
			// 	$("#lightBoxContent_id").css("top",(document.documentElement.clientHeight/2)+yScol+"px");
			// });
			$("#lightBoxContent_id").css("top",(document.documentElement.clientHeight/2)+"px");
			var xPX=parseInt($("#lightBoxContent_id").width());			
			var sPX=parseInt(document.body.scrollWidth);
			//alert(xPX);alert(sPX);
			$("#lightBoxContent_id").css("left",(sPX-xPX)/2+"px");
			
		}
		function hideLayer() { 
			document.getElementById("lightBox").style.display = "none"; 
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
		
		function deleteNce(nceId){
			jConfirm("确定要删除题目?", "", function(r) {
				if(r){
					$.post("nceExercisesDelete.action",{nceId:nceId},function(dat){
						if(dat=="success"){
							alert("删除成功");
							window.location.reload();
						}else{
							dat=dat.replace("fail$$","");
							alert(dat);	
						}
					});
				}else{return;}
			});	
			
		}