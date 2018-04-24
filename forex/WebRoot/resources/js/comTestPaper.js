var exercisesId = "";				//题目id
var exercisesIdSelectedNCE = "";	//非综合题已选题目
var exercisesIdSelectedCE = "";     //综合题已选题目
var NCEList = "";					//非综合题已选列表，用于输出数据
var CEList = "";					//综合题已选列表，用于输出数据
var totalPointsPercent = 0;			//当前题目总分百分比
var percent;						//本次所需题目的分数百分比
var totalPoints;					//试卷的总分
var totalCount;						//试卷的总题数
var pointsSum = 0;					//当前所选题目总分
var serialId = 0;					//题目序号
var totalTime;						//试卷的总时间
var timeSum = 0;					//当前的总时间
var pattern = /^\d+$/;				//用于匹配数字的正则表达式
var asmList = "";					//考核点列表
var courseName = "";				//课程名称

function rootQuery(target) {
	$("#rootAsm").show();
}
$(document).ready(function () {
	$("#rootAsm").hide();
	$("#asm1").find("tr").remove();
	NCEList = $("input[@name=NCEList]").val();
	CEList = $("input[@name=CEList]").val();
	if($("input[@name=totalTime]").val() == "")
	{
		$("input[@name=totalTime]").attr("value", 0);
	}
	if($("input[@name=totalPointsPercent]").val() == "")
	{
		$("input[@name=totalPointsPercent]").attr("value", 0);
	}
	if($("input[@name=serialId]").val() == "")
	{
		$("input[@name=serialId]").attr("value", 0);
	}
	if($("input[@name=courseName]").val() == "")
	{
		var temp = $("select[@name=courseId] option[@selected]").text();
		$("input[@name=courseName]").attr("value", temp);
	}
	if($("input[@name=exercisesIdSelectedNCE]").val() == "")
	{
		$("input[@name=exercisesIdSelectedNCE]").attr("value", "");
	}
	pointsSum = 0;
	alert("当前总分是" + pointsSum);
	if(NCEList != "")
	{
		var list = NCEList.split("@");
		$("#asm1").append("<tr><th>序号</th><th>考核点</th><th>题目</th><th>时间</th><th>分数</th><th>操作</th></tr>");
		idvalue = list[0].split(",");
		namevalue = list[1].split(",");
		exercisevalue = list[2].split(",");
		timevalue = list[3].split(",");
		pointsvalue = list[4].split(",");
		for (var i = 0; i < idvalue.length; i++) 
		{
			$("#asm1").append("<tr><td>" + idvalue[i] + "</td>" + "<td>" + namevalue[i] + "</td>" + "<td>" + exercisevalue[i] + "</td>" + "<td>" + timevalue[i] + "</td>" + "<td>" + pointsvalue[i] + "</td>" + "<td><input type='button' id='exercises_" + idvalue[i]  +  "' value='修改分数' onClick='pointsModify(this.id)'></td></tr>");
			pointsSum = pointsSum + parseInt(pointsvalue[i]);
			timeSum  = timeSum + parseInt(timevalue[i].split("min")[0]);
			alert("当前总分是" + pointsSum);
		}
	}
	if(CEList != "")
	{
		$("#asm1").append("<tr><th>序号</th><th>考核点</th><th>题目</th><th>时间</th><th>分数</th><th>操作</th></tr>");
		var list = CEList.split("@");
		idvalue = list[0].split(",");
		namevalue = list[1].split(",");
		exercisevalue = list[2].split(",");
		timevalue = list[3].split(",");
		pointsvalue = list[4].split(",");
		for (var i = 0; i < idvalue.length; i++) 
		{
			$("#asm1").append("<tr><td>" + idvalue[i] + "</td>" + "<td>" + namevalue[i] + "</td>" + "<td>" + exercisevalue[i] + "</td>" + "<td>" + timevalue[i] + "</td>" + "<td>" + pointsvalue[i] + "</td>" + "<td>" + "<input type='button' id='exercises_" + idvalue[i]  +  "' value='修改分数' onClick='pointsModify(this.id)'></td></tr>");
			pointsSum = pointsSum + parseInt(pointsvalue[i]);
			timeSum = timeSum + parseInt(timevalue[i].split("min")[0]);
			alert("当前总分是" + pointsSum);
		}
	}
	alert("当前总分是" + pointsSum);
});

//非综合题查询中间考核点
function middleQuery(sourse, target1, target2, target3) {
	var pid;
	pid = $("#" + sourse).val();
	$("#rootAsm").find("input[@type=radio]").attr("checked", false);
	$("#" + sourse).attr("checked", true);
	$("#" + target1).find("option").remove();
	$("#" + target1).append("<option value=''>&nbsp;</option>");
	$("#" + target2).find("option").remove();
	$("#" + target3).find("tr").remove();
	$("#" + target3).append("<tr><th>选择</th><th>考核点</th><th>题目</th><th>时间</th><th>分数</th></tr>");
	$.post("comTPMiddleNCEQuery.action", {sysNum:pid}, function (dat) {
		var tem = dat.split("@");
		idvalue = tem[0].split(",");
		namevalue = tem[1].split(",");
		for (var n = 0; n < idvalue.length; n++) {
			$("#" + target1).append("<option value='" + idvalue[n] + "'>" + namevalue[n] + "</option>");
		}
		$("#" + target1).removeAttr("disabled");
		$("#" + target2).attr("disabled", true);
	});
}

//非综合题查询叶子考核点
function leafQuery(sourse, target1, target2) {
	var pid;
	pid = $("#" + sourse).val();
	$("#" + target1).find("option").remove();
	$("#" + target1).append("<option value=''>&nbsp;</option>");
	$("#" + target2).find("tr").remove();
	$("#" + target2).append("<tr><th>选择</th><th>考核点</th><th>题目</th><th>时间</th><th>分数</th></tr>");
	$.post("comTPLeafNCEQuery.action", {parentId:pid}, function (dat) {
		var tem = dat.split("@");
		idvalue = tem[0].split(",");
		namevalue = tem[1].split(",");
		for (var n = 0; n < idvalue.length; n++) {
			$("#" + target1).append("<option value='" + idvalue[n] + "'>" + namevalue[n] + "</option>");
		}
		$("#" + target1).removeAttr("disabled");
	});
}



//非综合题考核点对应试题查询
function asmQuery(sourse, target) {
	var pid;
	pid = $("#" + sourse).val();
	$("#" + target).find("tr").remove();
	$("#" + target).append("<tr><th>序号</th><th>考核点</th><th>题目</th><th>时间</th><th>分数</th></tr>");
	$.post("comTPNCEQuery.action", {asmPointId:pid}, function (dat) {
		var tem = dat.split("@");
		idvalue = tem[0].split(",");
		if (!idvalue[0]) {
			alert("没有对应的子考核点");
			return;
		}
		namevalue = tem[1].split(",");
		exercisevalue = tem[2].split(",");
		timevalue = tem[3].split(",");
		pointsvalue = tem[4].split(",");
		for (var n = 0; n < idvalue.length; n++) {
			$("#" + target).append("<tr><td><input type='checkbox' id='checkbox_" + idvalue[n] + "' value='" + idvalue[n] + "' onClick='checkboxClick(this.id)'/></td>" + "<td>" + namevalue[n] + "</td>" + "<td>" + exercisevalue[n] + "</td>" + "<td>" + timevalue[n] + "</td>" + "<td>" + pointsvalue[n] + "</td></tr>");
		}
		exercisesIdSelectedNCE = $("input[@name=exercisesIdSelectedNCE]").val();
		if(exercisesIdSelectedNCE != "")
		{
			var temp = exercisesIdSelectedNCE.split(",");
			for (i = 0; i < temp.length; i++) 
			{
				$("#checkbox_" + temp[i]).attr("checked", true);
				$("#checkbox_" + temp[i]).attr("disabled", true);
			}
		}
	});
}

//非综合题提交
function submit1(target) {
	if (exercisesId == "") {
		alert("空操作，请选择要添加的试题");
		return;
	}
	var courseId;
	var courseName;
	var totalPoints;
	var totalCount;
	var scorePercent;
	courseId = $("select[@name=courseId] option[@selected]").val();
	totalPoints = $("input[@name=totalPoints]").val();
	totalCount = $("input[@name=totalCount]").val();
	scorePercent = $("input[@name=scorePercent]").val();
	totalTime = $("input[@name=totalTime]").val();
	serialId = $("input[@name=serialId]").val();
	totalPointsPercent = $("input[@name=totalPointsPercent]").val();
	courseName = $("select[@name=courseId] option[@selected]").text();
	totalPointsPercent = parseInt(totalPointsPercent) + parseInt(scorePercent);
	if (totalPointsPercent > 100) {
		totalPointsPercent = parseInt(totalPointsPercent) - parseInt(scorePercent);
		alert("总百分比超过100%，请重新输入");
	} 
	else 
	{
		if (exercisesIdSelectedNCE == "") 
		{
			exercisesIdSelectedNCE = exercisesId;
		} 
		else 
		{
			exercisesIdSelectedNCE = exercisesIdSelectedNCE + "," + exercisesId;
		}
		$("input[@name=exercisesIdSelectedNCE]").attr("value", exercisesIdSelectedNCE);
		$.post("comTPNCEAdd.action", {exercisesId:exercisesId, courseId:courseId, courseName:courseName,
		totalPoints:totalPoints, totalCount:totalCount, scorePercent:scorePercent, serialId:serialId,
		exercisesIdSelectedNCE:exercisesIdSelectedNCE}, function (dat) {
			var tem = dat.split("@");
			idvalue = tem[0].split(",");
			namevalue = tem[1].split(",");
			exercisevalue = tem[2].split(",");
			timevalue = tem[3].split(",");
			pointsvalue = tem[4].split(",");
			for (var n = 0; n < idvalue.length; n++) {
				$("#" + target).append("<tr><td>" + idvalue[n] + "</td>" + "<td>" + namevalue[n] + "</td>" + "<td>" + exercisevalue[n] + "</td>" + "<td>" + timevalue[n] + "</td>" + "<td>" + pointsvalue[n] + "</td>" + "<td><a href='testPaperCheckDone.action'><input type='button' value='\u5220\u9664'/></a>" + "<a href='testPaperCheckDone.action'><input type='button' value='\u4fee\u6539\u5206\u6570'/></a></td></tr>");
				totalTime = parseInt(totalTime) + parseInt(timevalue[n].split("min")[0]);
				pointsSum = pointsSum + parseInt(pointsvalue[n]);
				timeSum = timeSum + parseInt(timevalue[n].split("min")[0]);
				serialId ++;
			}
			var temp = exercisesIdSelectedNCE.split(",");
			for (i = 0; i < temp.length; i++) {
				$("#checkbox_" + temp[i]).attr("checked", true);
				$("#checkbox_" + temp[i]).attr("disabled", true);
			}
			exercisesId = "";
			$("input[@name=courseName]").attr("value", courseName);
			$("input[@name=totalPointsPercent]").attr("value", totalPointsPercent);
			$("input[@name=serialId]").attr("value", serialId);
			$("input[@name=totalTime]").attr("value", totalTime);
		});
	}
}

//非综合题选择试题
function checkboxClick(sourse) {
	var temp = parseInt(serialId) + 1;
	if (temp > totalCount) {
		alert("已选题目超过预设的最大题目数");
		//alert(totalCount);
		//alert(temp);
		$("#" + sourse).attr("checked", false);
		return;
	}
	if (exercisesId == "") {
		exercisesId = $("#" + sourse).val();
	} else {
		var temp = exercisesId.split(",");
		var flag = 0;
		exercisesId = "";
		for (i = 0; i < temp.length; i++) {
			var str = temp[i];
			if (str != $("#" + sourse).val()) {
				if (str != "") {
					exercisesId = exercisesId + str + ",";
				}
			} else {
				flag = 1;
			}
		}
		if (flag == 0) {
			exercisesId = exercisesId + $("#" + sourse).val();
		}
	}
}


//综合题中间考核点查询
function middleQuery1(sourse, target1, target2) {
	var pid;
	pid = $("#" + sourse).val();
	$("#rootAsm1").find("input[@type=radio]").attr("checked", false);
	$("#" + sourse).attr("checked", true);
	$("#" + target1).find("option").remove();
	$("#" + target1).append("<option value=''>&nbsp;</option>");
	$("#" + target2).find("tr").remove();
	$.post("comTPMiddleNCEQuery.action", {sysNum:pid}, function (dat) {
		var tem = dat.split("@");
		idvalue = tem[0].split(",");
		namevalue = tem[1].split(",");
		for (var n = 0; n < idvalue.length; n++) {
			$("#" + target1).append("<option value='" + idvalue[n] + "'>" + namevalue[n] + "</option>");
		}
		$("#" + target1).removeAttr("disabled");
		$("#" + target2).attr("disabled", true);
	});
}

//综合题叶子考核点查询
function leafQuery1(sourse, target1) {
	var pid;
	pid = $("#" + sourse).val();
	$("#" + target1).find("option").remove();
	$("#" + target1).attr("disabled", false);
	$.post("comTPLeafNCEQuery.action", {parentId:pid}, function (dat) {
		var tem = dat.split("@");
		idvalue = tem[0].split(",");
		namevalue = tem[1].split(",");
		for (var n = 0; n < idvalue.length; n++) 
		{
			$("#" + target1).append("<option id='leaf_" + idvalue[n] + "' value='" + idvalue[n] + "'>" + namevalue[n] + "</option>");
		}
		var temp = asmList.split(",");
		for(var i = 0; i <temp.length; i ++)
		{
			$("#" + target1).find("#leaf_" + temp[i]).attr("disabled", true);
		}
	});
}

//综合题组合叶子考核点--增加
function addCEAsm(sourse, target)
{
	$("#" + sourse).find("option:selected").appendTo($("#" + target));
}

//综合题组合叶子考核点--删除
function deleteCEAsm(sourse, target)
{
	$("#" + sourse).find("option:selected").appendTo($("#" + target));
}

//查询综合题
function CEQuery(sourse, target)
{
	$("#" + target).find("tr").remove();
	var levels = $("select[@name=levels]").val();
	courseName = $("input[@name=courseName]").val();
	$("#" + sourse).find("option").each(
	function(dat){
		if(asmList == "")
		{
		asmList = $(this).val();
		}
		else
		{
			asmList = asmList  + "," + $(this).val();
		}
	});
	$.post("comTPCEQuery.action", {asmList:asmList, levels:levels}, function (dat) {
		var title = dat.split("%")[1];
		var titleList = title.split(",");
		var ce = dat.split("%")[0].split("#");
		for(var i = 0; i < ce.length; i ++)
		{
			var nce = ce[i].split("@");
			var idList = nce[0].split(",");
			var SerialIdList = nce[1].split(",");
			var modelList = nce[2].split(",");
			var timeList = nce[3].split(",");
			var time = 0;
			if(ce[i] != "")
			{
				$("#" + target).append("<tr><td><input type='checkbox' id='checkbox_" + idList[i] + "' value='" + idList[i] + "'/></td><td>" + titleList[i] + 
				"</td></tr>");
			}
			
			
			for(var j = 0; j < idList.length; j ++)
			{
				$("#" + target).append("<tr><td>&nbsp;</td><td>(" + SerialIdList[j] + ")" + modelList[j] + "</td></tr>");
				time = time + parseInt(timeList[j].split("min")[0]);
			}
			$("#" + target).append("<tr><td>时间</td><td>" + time + "min</td></tr>");
			$("#" + target).append("<tr><td>&nbsp;</td></tr>");
		}
		exercisesIdSelectedCE = $("input[@name=exercisesIdSelectedCE]").val();
		var temp = exercisesIdSelectedCE.split(",");
		for (i = 0; i < temp.length; i++) 
		{
			$("#" + target).find("#checkbox_" + temp[i]).attr("checked", true);
			$("#" + target).find("#checkbox_" + temp[i]).attr("disabled", true);
		}
		asmList = "";
	});
}

//综合题重置查询条件
function resetCEQuery(target1, target2, target3)
{ 
	$("#" + target1).find("option").remove();
	$("#" + target1).attr("disabled", true);
	$("#" + target2).find("tr").remove();
	$("#" + target3).find("tr").remove();
	asmList = "";
}

//综合题试题全选
function selectALL(target)
{
	$("#" + target).find("input[@type=checkbox]").each(
	function (dat)
	{
		if(this.disabled)
		{
			return;
		}
		this.checked = true;
	});
}

//综合题试题全选
function cancelSelectAll(target)
{
	$("#" + target).find("input[@type=checkbox]").each(
	function (dat)
	{
		if(this.disabled)
		{
			return;
		}
		this.checked = false;
	});
}

//综合题提交
function submit2(target1)
{
	var courseId = $("input[@name=courseId]").val();
	var serialId = $("input[@name=serialId]").val();
	var totalCount = $("input[@name=totalCount]").val();
	var totalPoints = $("input[@name=totalPoints]").val();
	var scorePercent = $("input[@name=scorePercent]").val();
	var totalPointsPercent = $("input[@name=totalPointsPercent]").val();
	var temp = serialId;
	totalPointsPercent = parseInt(totalPointsPercent) + parseInt(scorePercent);
	if(totalPointsPercent > 100)
	{
		alert("总分超过预设的百分比");
		totalPointsPercent = parseInt(totalPointsPercent) - parseInt(scorePercent);
		return;
	}
	var flag = 0;
	$("#" + target1).find("input[@type=checkbox][@checked]").each(
	function(dat)
	{
		if(this.disabled == true)
		{
			return;
		}
		flag = 1;
		temp ++;
		if(temp > totalCount)
		{
			alert("已选题目数量超出预定的题目数");
			exercisesId = "";
			return;
		}
		
		if(exercisesId == "")
		{
			exercisesId = $(this).val();
		}
		else
		{
			exercisesId = exercisesId  + "," + $(this).val();
		}
	});
	if(temp > totalCount)
	{
		alert("已选题目数量超出预定的题目数");
		return;
	}
	if(flag == 0)
	{
		alert("没有选择任何题目，请选择题目后再提交");
		return;
	}
	if (exercisesIdSelectedCE == "") 
	{
		exercisesIdSelectedCE = exercisesId;
	} 
	else 
	{
		exercisesIdSelectedCE = exercisesIdSelectedCE + "," + exercisesId;
	}
	var temp1 = exercisesIdSelectedCE.split(",");
	for (i = 0; i < temp1.length; i++) 
	{
		$("#" + target1).find("#checkbox_" + temp1[i]).attr("checked", true);
		$("#" + target1).find("#checkbox_" + temp1[i]).attr("disabled", true);
	}
	
	$.post("comTPCEAdd.action", {exercisesId:exercisesId, serialId:serialId, courseId:courseId,
	 courseName:courseName, totalPoints:totalPoints, totalCount:totalCount, scorePercent:scorePercent,
	 exercisesIdSelectedCE:exercisesIdSelectedCE},function (dat) {
		serialId = temp;
		exercisesId = "";
		$("input[@name=serialId]").attr("value", temp);
		if(confirm("综合题增加成功，是否继续添加综合题？")) 
		{
		}
		else 
		{
			location.href = "comTPByUserAddInit.action";
		}
	});
}

function getTotalPoints() {
	totalPoints = $("input[@name=totalPoints]").val();
}
function changeTotalPoints() {
	var temp = $("input[@name=totalPoints]").val();
	if (pattern.test(temp)) {
		if (parseInt(pointsSum) <= parseInt(temp)) {
			totalPointsPercent = parseInt(totalPoints) / parseInt(temp) * 100;
			alert("当前的分数百分比" + totalPointsPercent);
			totalPoints = temp;
		} else {
			alert("非法输入，总分必须大于已选题目的总分");
			$("input[@name=totalPoints]").attr("value", totalPoints);
		}
	} else {
		alert("非法输入，总分必须是0-100之间的整数");
		$("input[@name=totalPoints]").attr("value", totalPoints);
	}
}
function getTotalCount() {
	totalCount = $("input[@name=totalCount]").val();
}
function changeTotalCount() {
	var temp = $("input[@name=totalCount]").val();
	if (pattern.test(temp)) {
		if (parseInt(serialId) <= parseInt(temp)) {
			totalPointsPercent = parseInt(totalPoints) / parseInt(totalPoints);
			totalPoints = temp;
		} else {
			alert("题数必须大于已选题目的数量");
			$("input[@name=totalCount]").attr("value", totalCount);
		}
	} else {
		alert("非法输入，题数必须是整数");
		$("input[@name=totalCount]").attr("value", totalCount);
	}
}
function getTotalPointsPercent() {
	percent = $("input[@name=scorePercent]").val();
}
function changeTotalPointsPercent() {
	var temp = $("input[@name=scorePercent]").val();
	if (pattern.test(temp)) {
		if (temp > 100) {
			alert("分数百分比必须小于100%");
			$("input[@name=scorePercent]").attr("value", percent);
		}
	} else {
		alert("分数百分比必须是0-100之间的整数");
		$("input[@name=scorePercent]").attr("value", percent);
	}
	
}

function getTotalTime()
{
	totalTime = $("input[@name=totalTime]").val();
}

function changeTotalTime() {
	var temp = $("input[@name=totalTime]").val();
	alert("当前总时间" + timeSum);
	if (pattern.test(temp)) {
		alert("" + timeSum);
		if (parseInt(temp) < timeSum) {
			alert("总时间必须大于已选题目所需的最少时间");
			$("input[@name=totalTime]").attr("value", totalTime);
		}
	} else {
		alert("总时间必须是整数");
		$("input[@name=totalTime]").attr("value", totalTime);
	}
}

//试卷预览
function preview()
{
	
	if($("input[@name=courseName]").val() == "")
	{
		var courseName = $("select[@name=courseId] option[@selected]").text();
		$("input[@name=courseName]").attr("value", courseName);
	}
	courseName = $("input[@name=courseName]").val();
	var totalCount = $("input[@name=totalCount]").val();
	var totalPoints = $("input[@name=totalPoints]").val();
	$.post("testPaperPreviewQuery.action", {
	 courseName:courseName, totalPoints:totalPoints, totalCount:totalCount},function (dat) {
	 totalTime = 0;
	 window.location = "/ees/jsp/local/comTestPaper/testPaperPreview.jsp"
	 });
}

//修改试题的分数
function pointsModify(sourse)
{
	var points = $("#" + sourse).parent().parent().find("td").eq(4).text();
	alert($("#" + sourse).parent().parent());
	var serialId = $("#" + sourse).parent().parent().find("td").eq(0).text();
	alert($("#" + sourse).parent().parent().html());
	var temp = prompt("请输入分数", points);
	totalPoints = $("input[@name=totalPoints]").val();
	if(temp != points)
	{	
		if(parseInt(pointsSum) - parseInt(points) + parseInt(temp) <= parseInt(totalPoints))
		{
			pointsSum = parseInt(pointsSum) - parseInt(points) + parseInt(temp);
			totalPointsPercent = parseInt(pointsSum) / parseInt(totalPoints) * parseInt(100);
			$("input[@name=totalPointsPercent]").attr("value", totalPointsPercent);
			alert("当前总百分比" + totalPointsPercent);
			$("#" + sourse).parent().prev().eq(0).text(temp);
			$.post("pointsModify.action", {
	 		points:temp, serialId:serialId},function (dat) {
	 		});
		}
	}
}


