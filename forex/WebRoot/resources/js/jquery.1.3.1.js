function stuOneExer(){
			this.serialId =	$("#serialId").val();		  
			this.redo =	$("#redo").val();		      
			this.exercisesId =	$("#exercisesId").val();	 
			this.sysNum =	$("#sysNum").val();		    
			this.testPaperId =	$("#testPaperId").val();	  
			this.exerType =	$("#exerType").val();			  
			this.limTiime=	$("#limTiime").val();		  
			this.point  =	$("#point").val();	       
			this.logId   =	$("#logId").val();
			this.finishStatus = $("#finishStatus").val();
	   		this.haveFirstEias=$("#haveFirstEias").val();
	   		this.haveFirstFnturn=$("#haveFirstFnturn").val();
	   		this.haveNum=$("#haveNum").val();
	   		this.nowSerialId=$("#nowSerialId").val();
	   		this.startFlag=$("#startFlag").val();
	   		this.endFlag=$("#endFlag").val();
	   		this.msg=$("#msgLabel").val();	      
	}

	function writeParentDocument(id,label){
		var ele=window.opener.document.getElementById(id);
		ele.innerHTML=label;
	}
	
	$(document).ready(function(){
		var model = new stuOneExer();
		
		//alert(model.sysNum);
		if(parseInt(model.serialId)==1){
			$("#preview").attr("disabled", true);
		}
		if(parseInt(model.serialId)==parseInt(model.haveNum)){
			$("#next").attr("disabled", true);
		}
		if(model.finishStatus=="y"){
			$("#begin").val("我要重做");
		}
		if(model.msg!=""){
			$("#msgStatic").html(model.msg);
		}
		if(model.nowSerialId!="0"&&model.nowSerialId!=""){
			if(model.serialId==model.nowSerialId){
				if(model.startFlag=="true"){
				 	$("#begin").attr("disabled", true);
				 	$("#finish").attr("disabled", false);
				 	$("#msg").html("已经开始");
				}
				return;
			}else{
				alert("你正在做的第"+model.nowSerialId+"题还没有提交！请先提交完以后再做其他题目！");
				var serialId=parseInt(model.nowSerialId);
	   			window.location = "exerInit.action?model.testPaperId="+model.testPaperId+"&model.serialId="+serialId;
				$("#begin").attr("disabled", true);
				$("#finish").attr("disabled", true);
				writeParentDocument("tpexe_"+model.nowSerialId,"已经开始");
				return;
			}
		}
		//if(model.serialId==""&&model.exercisesId==""&&model.sysNum==""){
		//	alert("收卷时间已到");
		//	openWindow();
		//	return;
		//} 
		if(model.finishStatus!="y"&&confirm("马上开始？")){
			begin();
		}
	});
	   			
   		
	function begin()
	{
    	var model2 = new stuOneExer(); 
   		if(model2.finishStatus=="y"){
   			var re = confirm("如果重做，你的以前的答题记录将会删除，你需要重新答题，否则视作放弃答题! 点击\"确定\"开始重做， 点击\"取消\"你的答题记录不变。");
   			if(!re){return ;}
   		}			
		
		
		$.post("/eems/exerBegin.action",{"model.exercisesId":model2.exercisesId,"model.sysNum":model2.sysNum,
			 "model.testPaperId":model2.testPaperId,
			 "model.serialId":model2.serialId,
			 "model.logId":model2.logId,
			 "model.redo":model2.redo
			 },function(dat){
				 	if(dat.indexOf("fail")!=-1){
						dat=dat.replace("fail$$","");
						alert(dat+"缺少本学生的操作员号！");
						$("#msg").html(dat);
					}else{
						if(model2.sysNum=="B"&&model2.haveFirstFnturn!="true"){
						haveFirstFnturn=true;
						//callLocalExec("fnturn");
						$("#msg").html("已经开始");
							//alert("---------");
						}
						if(model2.sysNum=="10"&&model2.haveFirstEias!="true"){
								haveFirstEias=true;
								callLocalExec("eias");
								//alert("---------");
						}
						writeParentDocument("tpexe_"+model2.serialId,"已经开始");
						$("#begin").attr("disabled", true);	
						$("#finish").attr("disabled", false);
				   }				
			 }
		);
   	
   }
   function openWindow(){
   		var myUrl="/eems/login.action?reset=false";
   		window.resizeTo(screen.availWidth,screen.availHeight);
   		window.location=myUrl; 
   		window.focus();
   }
	function finish()
	{
		var model2 = new stuOneExer();
		$.post("/eems/exerSubmit.action",{"model.exercisesId":model2.exercisesId,"model.sysNum":model2.sysNum,"model.redo":model2.redo,
			"model.testPaperId":model2.testPaperId,"model.serialId":model2.serialId,"model.logId":model2.logId},
			function(dat){
				$("#finish").attr("disabled", true);
				alert("试题提交成功，请点击下一题，继续答题");
				$("#msg").html("试题提交成功，请点击下一题，继续答题");
				writeParentDocument("tpexe_"+model2.serialId,"已完成");
				//window.opener.location.reload();
				return;
		}); 	 			
   	}
	
	
   
   function cancel(sourse)
   {
   		$("#" + sourse).attr("disabled", true);
   		var exercisesId;
   		
   		var testPaperId=$("#testPaperId").val();
   		exercisesId = $("#exercisesId").attr("value");
   		sysNum =$("#sysNum").attr("value");	
   		
		$.post("/eems/exerCancel.action", {exercisesId:exercisesId}, function(dat){
			alert("试题完成，请继续考核。");
			return;
		});
   }
   
   function preview()
   {	
   		var model2 = new stuOneExer();
   		var serialId=parseInt(model2.serialId)-1; 		
   		window.location = "/eems/exerInit.action?model.testPaperId="+model2.testPaperId+"&model.serialId="+serialId;	
   }
   
   function next()
   {	
   		var model2 = new stuOneExer();
   		var serialId=parseInt(model2.serialId)+1;
   		window.location = "/eems/exerInit.action?model.testPaperId="+model2.testPaperId+"&model.serialId="+serialId;
   }
   
   function back()
   {
   		var myUrl="/eems/login.action";
   		window.resizeTo(screen.availWidth,screen.availHeight);
   		window.location=myUrl; 
   		window.focus();
   }