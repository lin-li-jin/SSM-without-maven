
var idsure = new Array();
var idvalue ;
function del(n){
	    var message = window.confirm("ȷ��ɾ����Ⱥ��?" + n);
	    if(message){
	       window.location="groupInfoDel.action?groupInfo.groupNum=" + n;
	       processTip();  
	    }
	    else{
	       return;
	    }
	} 

//����Ⱥ��ʱ��ѡ��ѧ��
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
//���嵽ĳһ�����ѧ��
function stuQuery(parent1,child2,child3,child4){
			idvalue=NaN;
			namevalue=NaN;
			var childId1=$("#"+parent1).val();//ϵ��
			var childId2=$("#"+child2).val();//רҵ
			var childId3=$("#"+child3).val();//�꼶
			var childId4=$("#"+child4).val();//�༶
			
			
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
			
			//$("#listSbutton").html("<input class='button' type='button' value='ȫѡ' onClick='checkboxall()' />&nbsp;<input class='button' type='button' value='���ȫ��ѡ��'onClick='removeAll()'/>&nbsp;<input id='sureStu2GroupButton' class='button' type='button' value='����Ⱥ��' onClick='sureStu2Group()' />");			
			$("#listSbutton").html("<input class='button' type='button' value='ȫѡ' onClick='checkboxall()' />&nbsp;<input class='button' type='button' value='���ȫ��ѡ��'onClick='removeAll()'/>");			
			
		//$("#listSbutton2").html("<input class='button' type='button' value='ɾ��ѡ��ѧ��' onClick='deleteOne2()'/>");		
	
			});

			
//			$("#addStudent").html("<h2 style='color:red'>ѧ��������</h2><br>");
		
}

//�ύ��
function submitForm(formName,attrId){

	var scorFor=$("#scorFor").val();
	var alias=$("#alias").val();
	var descr=$("#descr").val();
	if(scorFor==""||scorFor==null){
		alert("��ѡ��Ⱥ����;");
		return;	
	}
	if(alias==""||alias==null){
		alert("������Ⱥ�����");
		return;	
	}
	if(descr==""||descr==null){
		alert("����������");
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
//�������ѡ���ѧ��
function removeAll(){
			$("input[@type='checkbox']").each(function(){   
         	$(this).attr("checked",false);}); 
//			$("#listModel").html("<h2 style='color:red'>ѧ��������</h2><br>");			
			idvalue=NaN;
			nameValue=NaN;
}
//��ѧ����ӵ�Ⱥ���� ,û�õ�
function sureStu2Group(){

		if(idvalue.length<1){
				alert("û��ѧ����ѡ��");
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

//���ѡ�е�����ѧ��
function removeAll2(){
//			$("#addStudent").html("<h2 style='color:red'>ѧ��������</h2><br>");
			idsure=NaN;
			idsure=new Array();
		}
		 
//ɾ��ѡ�е�����һ��ѧ�� 
function deleteOne2(){
			 $("#addStudent").find("input[@type=checkbox][@checked]").each(
			 function(){
					var id = $(this).val();
					$("#"+id).remove();
				}
			);	
		}
//ȫѡ
function checkboxall(){   
   				 $("input[@type='checkbox']").each(function(){   
         		 $(this).attr("checked",true);   
    		}); 	
 }


	
function productSelect(ids,names){
			var part1="<select name='"
			var part2="' id='";
			var part3="' ><option>���</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option>";
			var part4="<option value='4'>4</option><option value='5'>5</option>";
			var part5="<option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option></select>";
			var allPart=part1+names+part2+ids+part3+part4+part5;
			return allPart;
		};
		
//���Ⱥ����Ƿ��Ѿ����ڣ�Ⱥ��ű�����Ψһ��
function checkGroupNum(){
	var groupNum  = $("#groupNum").val();
	$.post("checkGroupNum.action",{groupNum:groupNum},
				function(dat){
					 if(dat == "success"){
					}else{
						alert("Ⱥ����Ѵ��ڣ����������룡");
					}
				}
				);

}		