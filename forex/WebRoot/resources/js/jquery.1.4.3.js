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
		
		//系统选择		
		function radioChange(){
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
		//考核点选择
		function selectChange(id,targetId){
			var pid;		
			pid=$("#"+id).val();
			$.post("asmPointChildQuery_ajax.action",
				{parentId:pid,rand:Math.round(Math.random()*10000)},
				function(dat){
					var tem=dat.split("@@");
					idvalue=tem[0].split("$$");
					namevalue=tem[1].split("$$");
					$("#"+targetId).html("<option value=''>&nbsp;</option>")
					for(var n=0;n<idvalue.length;n++){							
						$("#"+targetId).append("<option value='"+idvalue[n]+"'>"+namevalue[n]+"</option>");			
					}
					$("#"+targetId).removeAttr("disabled");
				}
			);	
		 };