//���°༶
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

//�ύ��
function assignSubmit(sourse)
{	
	
	 var tpId = $("select[@name=testPaperId]").val();
	 if(tpId==null||tpId==""){
	 	alert("û��ѡ������Ծ�!");
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
		alert("�Ծ���ѧ�겻��Ϊ��!");
		return;
	}
	var gradeList1 = $("select[@name=gradeList]").val();
	//alert(gradeList1);
	if(gradeList1 == null || gradeList1 == "")
	{
		alert("����༶����Ϊ��!");
		return;
	}
	var tp=parseInt($("input[@name=paperScorePercent]").val());
	var attitude=parseInt($("input[@name=attitudeScorePercent]").val());
	var daliy=parseInt($("input[@name=usuallyScorePercent]").val());
	var total=tp+attitude+daliy;
	if(total!=100){
		alert("�������ֵĺͲ�����100��");
		return;
	}
		
	var sid=$("#paperFor2").val();
	var eid="";
	
	if(sid=="10"){
		eid=$("#selectExpPro").val();	
		if(eid==""){
			alert("��ѡ��ʵ����Ŀ");
			return;
		}
	}
	if(sid=="11"||sid=="12"){
		eid=$("#selectExpRep").val()
		if(eid==""){
			alert("��ѡ��ʵ�鱨��");
			return;
		}	
	}
	var dd = $("#" + sourse);
	dd.submit();
}

//���ñ�
function cancel()
{
	$("#gradeList").empty();
}

//���ÿ��Կ�ʼѧ��
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
		alert("���Կ�ʼѧ���������λ���֣�����������");
		$("#" + sourse).attr("value", "");
		$("#" + sourse).focus();
	}	
}
