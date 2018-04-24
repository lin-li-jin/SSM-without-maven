
var idsure = new Array();
var idvalue ;
function del(n){
	    var message = window.confirm("确认删除该群组?" + n);
	    if(message){
	       window.location="groupInfoDel.action?groupInfo.groupNum=" + n;
	       processTip();  
	    }
	    else{
	       return;
	    }
	} 

//新增群组时，选择学生
function selectChange(parent1,child2,number){
	var pid=$("#"+parent1).val();
	$.post("orginfoChildQuery.action",
			{supCode:pid,flag:number},
			function(dat){
				var tem=dat.split("@@");
				idvalue=tem[0].split(",");
				namevalue=tem[1].split(",");
				$("#"+child2).html("<option value=''>&nbsp;</option>");
				for(var n=0;n<idvalue.length;n++){							
					$("#"+child2).append("<option value='"+idvalue[n]+"'>"+namevalue[n]+"</option>");			
				}
				//("#"+child2).("disabled");
				}
	);	
}


function selectChange2(parent1,parent2,child2,number){
	
	var pid=$("#"+parent1).val();
	var pid2=$("#"+parent2).val();
	$.post("orginfoChildQuery.action",
			{supCode:pid,supCode2:pid2,flag:number},
			function(dat){
				var tem=dat.split("@@");
				idvalue=tem[0].split(",");
				namevalue=tem[1].split(",");
				$("#"+child2).html("<option value=''>&nbsp;</option>");
				for(var n=0;n<idvalue.length;n++){							
					$("#"+child2).append("<option value='"+idvalue[n]+"'>"+namevalue[n]+"</option>");			
				}
				//("#"+child2).("disabled");
				}
	);	
}
//具体到某一个班的学生
function stuQuery(parent1,child2,child3,child4){
			idvalue=NaN;
			namevalue=NaN;
			var childId1=$("#"+parent1).val();//系别
			var childId2=$("#"+child2).val();//专业
			var childId3=$("#"+child3).val();//年级
			var childId4=$("#"+child4).val();//班级
			
			
			if(child4=='')return;
			$.post("stuQuery4Group.action",
				{departments:childId1,major:childId2,grade:childId3,classes:childId4},
				function(dat){
					var tem=$.trim(dat).split("@@");
					var htmls="";
					//alert(dat);
					idvalue=tem[0].split(",");
					namevalue=tem[1].split(",");
					idvalue_length=tem.length;			
					for(var n=0;n<idvalue.length;n++){
						$("#listStudent").append("<input type='checkbox' name='checkname_"+idvalue[n]+"' id='checkbox_"+idvalue[n]+"' value='"+idvalue[n]+"'>"+namevalue[n]+"</input>&nbsp;");
					}
					$("#listStudent").append("<br/>");
			
			//$("#listSbutton").html("<input class='button' type='button' value='全选' onClick='checkboxall()' />&nbsp;<input class='button' type='button' value='清除全部选项'onClick='removeAll()'/>&nbsp;<input id='sureStu2GroupButton' class='button' type='button' value='加入群组' onClick='sureStu2Group()' />");			
			$("#listSbutton").html("<input class='button' type='button' value='全选' onClick='checkboxall()' />&nbsp;<input class='button' type='button' value='清除全部选项'onClick='removeAll()'/>");			
			
		//$("#listSbutton2").html("<input class='button' type='button' value='删除选中学生' onClick='deleteOne2()'/>");		
	
			});

			
//			$("#addStudent").html("<h2 style='color:red'>学生姓名：</h2><br>");
		
}

//提交表单
function submitForm(formName,attrId){

	var scorFor=$("#scorFor").val();
	var alias=$("#alias").val();
	var descr=$("#descr").val();
	if(scorFor==""||scorFor==null){
		alert("请选择群组用途");
		return;	
	}
	if(alias==""||alias==null){
		alert("请输入群组别名");
		return;	
	}
	if(descr==""||descr==null){
		alert("请输入描述");
		return;	
	}
           var groupSelected = "";
	       //$("#addStudent").find("input[@type=checkbox]").each(
	       $("#listStudent").find("input[@type=checkbox][@checked]").each(     
       	   function(){				
		  	 if(groupSelected == ""){
				groupSelected = $(this).val();
		  		}else {
		    		groupSelected = groupSelected  + "," + $(this).val();
		   		}
	       }
	       );
	     // alert(groupSelected);
	       document.getElementById(attrId).value= groupSelected;
	      // $("#"+attrId).val(groupSelected);

	       
	       document.forms[formName].submit();    
	       processTip();  
}
//清除所有选择的学生
function removeAll(){
			$("input[@type='checkbox']").each(function(){   
         	$(this).attr("checked",false);}); 
//			$("#listModel").html("<h2 style='color:red'>学生姓名：</h2><br>");			
			idvalue=NaN;
			nameValue=NaN;
}
//把学生添加到群组中 ,没用到
function sureStu2Group(){

		if(idvalue.length<1){
				alert("没有学生被选中");
				return;
			}
			for(var n=0;n<idvalue.length;n++){
				if($("#checkbox_"+idvalue[n]).attr("checked")==true){
						idsure.push(idvalue[n]);
//						$("#addStudent").append(productSelect("xuhaoSeId_"+idvalue[n],"xuhaoSeNa_"+namevalue[n]));
						$("#addStudent").append("<span id="+idvalue[n]+"><input type='checkbox' name='xuhaoChBoxNa_"+idvalue[n]+"' id='xuhaoChBoxId_"+idvalue[n]+"' value='"+idvalue[n]+"'><span id='label_"+idvalue[n]+"'> "+namevalue[n]+"</span> </input></span><br>");
				}
			}
}

//清除选中的所有学生
function removeAll2(){
//			$("#addStudent").html("<h2 style='color:red'>学生姓名：</h2><br>");
			idsure=NaN;
			idsure=new Array();
		}
		 
//删除选中的其中一个学生 
function deleteOne2(){
			 $("#addStudent").find("input[@type=checkbox][@checked]").each(
			 function(){
					var id = $(this).val();
					$("#"+id).remove();
				}
			);	
		}
//全选
function checkboxall(){   
   				 $("input[@type='checkbox']").each(function(){   
         		 $(this).attr("checked",true);   
    		}); 	
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
		
//检查群组号是否已经存在，群组号必须是唯一的
function checkGroupNum(){
	var groupNum  = $("#groupNum").val();
	$.post("checkGroupNum.action",{groupNum:groupNum},
				function(dat){
					 if(dat == "success"){
					}else{
						alert("群组号已存在，请重新输入！");
					}
				}
				);

}		