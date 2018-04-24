//更新班级
function addElement() 
{
	var op = $("#grade").find("option:selected");
		op.clone().appendTo($("#gradeList"));
		op.remove();
}

function addStuNum() 
{
	var op = $("#studentNum").find("option:selected");
	op.clone().appendTo($("#stuNumList"));
	op.remove();
}
function removeStuNum(){
	var op = $("#stuNumList").find("option:selected");
		op.clone().appendTo( $("#studentNum") );
		op.remove();
}

function addAllStuNum(){
	var count =$("#studentNum").find("option").length;
	$("select[@name='studentNum'] option").each(function(){
	 	$(this).clone().appendTo($("#stuNumList"));
	 	$(this).remove();
	});
}

function removeAllStuNum(){
	var count =$("#stuNumList").find("option").length;
	$("select[@name='stuNumList'] option").each(function(){
	 	$(this).clone().appendTo($("#studentNum"));
	 	$(this).remove();
	});
}

function removeElement(){
	var op = $("#gradeList").find("option:selected");
		op.clone().appendTo( $("#grade") );
		op.remove();
}

function paperForChange(){
	var sid=$("#paperFor2").val();
	if(sid=="10"){
		$("#selectExpPro").attr("disabled",false);
		$("#selectExpRep").attr("disabled",true);
	
	}else if(sid=="11"||sid=="12"){
		$("#selectExpPro").attr("disabled",true);
		$("#selectExpRep").attr("disabled",false);
	}

}

//提交表单
function assignSubmit(sourse)
{	
	
	 var tpId = $("select[@name=testPaperId]").val();
	 if(tpId==null||tpId==""){
	 	alert("没有选择分配试卷!");
	 	return;
	 }
	 
	 $("select[@name=gradeList]").find("option").each(
	 	function(){
	 		$(this).attr("selected",true);
	 	}
	 );
	 
	var year = $("input[@name=year]").val();
	if(year = null || year == "")
	{
		alert("试卷考试学年不能为空!");
		return;
	}
	var gradeList1 = $("select[@name=gradeList]").val();
	//alert(gradeList1);
	if(gradeList1 == null || gradeList1 == "")
	{
		alert("分配班级不能为空!");
		return;
	}
	var tp=parseInt($("input[@name=paperScorePercent]").val());
	var attitude=parseInt($("input[@name=attitudeScorePercent]").val());
	var daliy=parseInt($("input[@name=usuallyScorePercent]").val());
	var total=tp+attitude+daliy;
	if(total!=100){
		alert("比例划分的和不等于100！");
		return;
	}
		
	var sid=$("#paperFor2").val();
	var eid="";
	
	if(sid=="10"){
		eid=$("#selectExpPro").val();	
		if(eid==""){
			alert("请选择实验项目");
			return;
		}
	}
	if(sid=="11"||sid=="12"){
		eid=$("#selectExpRep").val()
		if(eid==""){
			alert("请选择实验报告");
			return;
		}	
	}
	var dd = $("#" + sourse);
	dd.submit();
}

//重置表单
function cancel()
{
	$("#gradeList").empty();
}

//设置考试开始学年
function setStartYear(sourse, target)
{
	var startYear = $("#" + sourse).val();
	var pattern = /^\d{4}$/;
	if(pattern.test(startYear))
	{
		var endYear = parseInt(startYear) + 1;
		$("#" + target).attr("value", endYear);
	}
	else
	{
		alert("考试开始学年必须是四位数字，请重新输入");
		$("#" + sourse).attr("value", "");
		$("#" + sourse).focus();
	}	
}
