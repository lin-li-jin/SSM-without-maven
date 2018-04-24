<%@ page contentType="text/html;charset=GBK" %>
<%@ taglib prefix="ww" uri="webwork" %>
<html>
<%
	String subjectPath=request.getContextPath();
%>
<head>
	<title>Spot交易</title>
	<link rel="stylesheet" type="text/css" href="<%=subjectPath%>/resources/calendar/js/My97DatePicker/skin/WdatePicker.css"/>

	<script src="<%=subjectPath%>/resources/js/jquery-1.8.3.js" type="text/javascript"></script>
	<script src="<%=subjectPath%>/resources/js/jquery.validate.js" type="text/javascript"></script>
	<script src="<%=subjectPath%>/resources/js/additional-methods.js"/>
	<script src="<%=subjectPath%>/resources/ponycss/jquery_validate.css" type="text/javascript"></script>
	<script src="<%=subjectPath%>/resources/js/jquery.easyui.min.js" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css" href="<%=subjectPath%>/resources/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=subjectPath%>/resources/css/exam/stop_loss.css">
	<script type="text/javascript" src="<%=subjectPath%>/resources/calendar/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=subjectPath%>/resources/js/exam/spot.js" charset="GBK"></script>
	<script type="text/javascript">
		$(function(){
			$("#add").click(function(){
				var examContent = $("#examContent").val();
				var accTypeNo = "<ww:property value='#request.accType'/>";
				var type = "<ww:property value='#request.accDetail'/>";
				var Direction = $("#item3_direction").val();
				if(Direction == "买"){
					Direction = 0;
				}else if(Direction == "卖"){
					Direction = 1;
				}else{
					Direction = "";
				}
				var Acc = $("#item2_Acc").val();
				var spotAccMount = $("#item4_spotAccMount").val();
				var spotPrice = $("#item1_basis").val();
				var spotProviderNo = $("#item5_provider").val();
				var DirectionScore = $("#item3_directionscore").val();
				var AccScore = $("#item2_AccScore").val();
				var spotAccMountScore = $("#item4_spotAccMountScore").val();
				var spotPriceScore = $("#item1_basisScore").val();
				var spotProviderNoScore = $("#item5_providerscore").val();
				
				var item={
				    "item.examContent":examContent,
				    "item.accTypeNo":accTypeNo,
				    "item.examNo":"",
				    "item.type":type,
				    "item.Direction":Direction,
	                "item.Acc":Acc,
					"item.spotAccMount":spotAccMount,
		            "item.spotPrice":spotPrice,  //spot 交易基点
		            "item.spotProviderNo":spotProviderNo, //交易对手方号 				
				    "item.spotScore.directionScore":DirectionScore,
				    "item.spotScore.accScore":AccScore,
				    "item.spotScore.accAmountScore":spotAccMountScore,
				    "item.spotScore.priceScore":spotPriceScore,  //交易基点
				    "item.spotScore.providerNoScore":spotProviderNoScore
				    
				    };
				$.ajax({
					type:"get",
					data:item,
					url:"/forex/addnewExchange.action",
					dataType:"json",
					success:function(result){
						alert("添加成功");
					},
					error:function(){
						alert("添加失败！")
					}
				});
			});
		});
	</script>
</head>
<body>

<div>
	<div>交易类型:Spot 交易</div>
	题干：
	<div style="border: solid 4px #148213;width: 400px;">
		<!--
			1.题目内容
		 -->
		<div style="margin: 3px 4px;" class="stop_loss">
			<textarea style="width: 280px;height: 180px;" id="examContent">

			</textarea>
		</div>
	</div>
	算分交易要素:
	<div style="border: solid 4px #148213;width:600px;">
		<form id="form1">
			<div style="margin-top: 3px;margin-left: 2px;margin-right: 2px;height: 40px;line-height: 40px;">
				<input type="checkbox" name="checkbox1" value="checkbox" onchange="haha(this,1)"> 交易基点 &nbsp;&nbsp;&nbsp;
				<div style="display: inline">
					<label for="item1_basisScore" >分数 : </label>
					<input type="input" name="item1_basisScore" id="item1_basisScore" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
				</div>
				<div style="display: inline">
					<label for="item1_basis">正确答案 : </label>
					<input type="input" name="item1_basis" id="item1_basis" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
				</div>
			</div>
		</form>
		<form id="form2">
		<div style="margin-top: 3px;margin-left: 2px;margin-right: 2px;height: 40px;line-height: 40px;">
			<input type="checkbox" name="accmonitorPrice_checkbox" value="checkbox" onchange="haha(this,2)"> 交易币种 &nbsp;&nbsp;&nbsp;
			<div style="display: inline">
				<label for="item2_AccScore"> 分数 : </label>
				<input type="input" name="item2_AccScore" id="item2_AccScore" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
			</div>
			<div style="display: inline">
				<label for="item2_Acc">正确答案 : </label>
			<input type="input" name="item2_Acc" id="item2_Acc" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
			</div>
		</div>
		</form>
		<form id="form3">
			<div style="margin-top: 3px;margin-left: 2px;margin-right: 2px;height: 40px;line-height: 40px;">
				<input type="checkbox" name="checkbox1" value="checkbox" onchange="haha(this,3)"> 交易方向 &nbsp;&nbsp;&nbsp;
				<div style="display: inline">
					<label for="item3_directionscore"> 分数 : </label>
					<input type="input" id="item3_directionscore" name="item3_directionscore" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
				</div>
				<div style="display: inline">
					<label for="item3_direction"> 正确答案 :</label>
					<input type="input" id="item3_direction" name="item3_direction" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
				</div>
			</div>
		</form>
		<form id="form4">
			<div  style="margin-top: 3px;margin-left: 2px;margin-right: 2px;height: 40px;line-height: 40px;">
				<input type="checkbox" name="accMountcheckbox" value="checkbox" onchange="haha(this,4)"> 交易金额 &nbsp;&nbsp;&nbsp;

				<div style="display: inline">
					<label for="item4_spotAccMountScore">分数 : </label>
				<input type="input" readonly="readonly" name="item4_spotAccMountScore" id="item4_spotAccMountScore" style="width: 60px;"> &nbsp;&nbsp;&nbsp;
				</div>

				<div style="display: inline">
					<label for="item4_spotAccMount">正确答案 :</label>
				 <input id="item4_spotAccMount" readonly name="item4_spotAccMount" type="input"  style="width: 60px;"> &nbsp;&nbsp;&nbsp;
				</div>
			</div>
		</form>
		<form id="form5">
			<div style="margin-top: 3px;margin-left: 2px;margin-right: 2px;height: 40px;line-height: 40px;">
				<input type="checkbox" name="checkbox1" value="checkbox" onchange="haha(this,5)"> 交易对手号 &nbsp;&nbsp;&nbsp;
				<div style="display: inline">
					<label for="item5_providerscore" >分数 : </label>
					<input type="input" name="item5_providerscore" id="item5_providerscore" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
				</div>
				<div style="display: inline">
					<label for="item5_provider">正确答案 : </label>
					<input type="input" name="item5_provider" id="item5_provider" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
				</div>
			</div>
		</form>
		
		<div style="margin-top: 3px;margin-left: 2px;margin-right: 2px;height: 40px;line-height: 40px;">
			总分 : <input type="input" name="item." style="width: 60px;"> &nbsp;&nbsp;&nbsp;
		</div>

	</div>
	<div>
		<button id="add">添加</button>
	</div>
</div>

</body>

</html>