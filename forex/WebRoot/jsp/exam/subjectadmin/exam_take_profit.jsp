<%@ page contentType="text/html;charset=GBK" %>
<%@ taglib prefix="ww" uri="webwork" %>
<html>
<%
	String subjectPath=request.getContextPath();
%>
<head>
	<title>take profit交易</title>
	<link rel="stylesheet" type="text/css" href="<%=subjectPath%>/resources/calendar/js/My97DatePicker/skin/WdatePicker.css"/>

	<script src="<%=subjectPath%>/resources/js/jquery-1.8.3.js" type="text/javascript"></script>
	<script src="<%=subjectPath%>/resources/js/jquery.validate.js" type="text/javascript"></script>
	<script src="<%=subjectPath%>/resources/js/additional-methods.js"/>
	<script src="<%=subjectPath%>/resources/ponycss/jquery_validate.css" type="text/javascript"></script>
	<script src="<%=subjectPath%>/resources/js/jquery.easyui.min.js" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css" href="<%=subjectPath%>/resources/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=subjectPath%>/resources/css/exam/stop_loss.css">
	<script type="text/javascript" src="<%=subjectPath%>/resources/calendar/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=subjectPath%>/resources/js/exam/take_profit.js" charset="GBK"></script>
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
				var takeProfitAccMount = $("#item1_takeProfitAccMount").val();				
				var takeProfitPrice = $("#item4_price").val();				
				var takeProfitGoodFrom1 = $("#item5_goodfrom").val();
				var takeProfitGoodFrom = parseInt(takeProfitGoodFrom1.replace(/[^0-9]/ig,""));				
				var takeProfitGoodTill1 = $("#item6_goodtill").val();
				var takeProfitGoodTill = parseInt(takeProfitGoodTill1.replace(/[^0-9]/ig,""));					
				var DirectionScore = $("#item3_directionscore").val();				
				var AccScore = $("#item2_AccScore").val();				
				var takeProfitAccMountScore = $("#item1_takeProfitAccMountScore").val();				
				var takeProfitGoodFromScore = $("#item5_goodfromscore").val();				
				var takeProfitGoodTillScore = $("#item6_goodtillscore").val();				
				var takeProfitPriceScore = $("#item4_pricescore").val();				
				var item={
				    "item.examContent":examContent,
				    "item.accTypeNo":accTypeNo,
				    "item.examNo":"",
				    "item.type":type,
				    "item.Direction":Direction,
	                "item.Acc":Acc,
					"item.takeProfitAccMount":takeProfitAccMount,
	                "item.takeProfitPrice":takeProfitPrice,
	                "item.takeProfitGoodFrom":takeProfitGoodFrom,
        			"item.takeProfitGoodTill":takeProfitGoodTill,
				    "item.takeProfitScore.DirectionScore":DirectionScore,
				    "item.takeProfitScore.AccScore":AccScore,
				    "item.takeProfitScore.takeProfitAccMountScore":takeProfitAccMountScore,
				    "item.takeProfitScore.takeProfitGoodFromScore":takeProfitGoodFromScore,
				    "item.takeProfitScore.takeProfitGoodTillScore":takeProfitGoodTillScore,
				    "item.takeProfitScore.takeProfitPriceScore":takeProfitPriceScore};
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
	<div>交易类型：take profit交易</div>
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
			<div  style="margin-top: 3px;margin-left: 2px;margin-right: 2px;height: 40px;line-height: 40px;">
				<input type="checkbox" name="accMountcheckbox" value="checkbox" onchange="haha(this,1)"> 交易金额 &nbsp;&nbsp;&nbsp;

				<div style="display: inline">
					<label for="item1_takeProfitAccMountScore">分数 : </label>
				<input type="input" readonly="readonly" name="item1_takeProfitAccMountScore" id="item1_takeProfitAccMountScore" style="width: 60px;"> &nbsp;&nbsp;&nbsp;
				</div>

				<div style="display: inline">
					<label for="item1_takeProfitAccMount">正确答案 :</label>
				 <input id="item1_takeProfitAccMount" readonly name="item1_takeProfitAccMount" type="input"  style="width: 60px;"> &nbsp;&nbsp;&nbsp;
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
			<div style="margin-top: 3px;margin-left: 2px;margin-right: 2px;height: 40px;line-height: 40px;">
				<input type="checkbox" name="checkbox1" value="checkbox" onchange="haha(this,4)"> 交易价格 &nbsp;&nbsp;&nbsp;
				<div style="display: inline">
					<label for="item4_pricescore" >分数 : </label>
					<input type="input" name="item4_pricescore" id="item4_pricescore" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
				</div>
				<div style="display: inline">
					<label for="item4_price">正确答案 : </label>
					<input type="input" name="item4_price" id="item4_price" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
				</div>
			</div>
		</form>
		<form id="form5">
			<div style="margin-top: 3px;margin-left: 2px;margin-right: 2px;height: 40px;line-height: 40px;">
				<input type="checkbox" name="checkbox1" value="checkbox" onchange="haha(this,5)"> 开始时间 &nbsp;&nbsp;&nbsp;
				<div style="display: inline">
					<label for="item5_goodfromscore" >分数 : </label>
					<input type="input" name="item5_goodfromscore" id="item5_goodfromscore" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
				</div>
				<div style="display: inline">
					<label for="item5_goodfrom" >正确答案 : <img src="<%=subjectPath%>/resources/calendar/image/Button.gif" onclick="WdatePicker({el:'item5_goodfrom'})" > &nbsp;&nbsp;&nbsp;</label>
					<input type="text" id="item5_goodfrom" datatype="date" name="item5_goodfrom" readonly>
				</div>
			</div>
		</form>
		<form id="form6">
			<div style="margin-top: 3px;margin-left: 2px;margin-right: 2px;height: 40px;line-height: 40px;">
				<input type="checkbox" name="checkbox1" value="checkbox" onchange="haha(this,6)"> 交割时间 &nbsp;&nbsp;&nbsp;
				<div style="display: inline">
					<label for="item6_goodtillscore" >分数 :</label>
					<input type="input" name="item6_goodtillscore" id="item6_goodtillscore" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
				</div>
				<div style="display: inline">
					<label for="item6_goodtill" >正确答案 : <img src="<%=subjectPath%>/resources/calendar/image/Button.gif" onclick="WdatePicker({el:'item6_goodtill'})" > &nbsp;&nbsp;&nbsp;</label>
					<input type="text" id="item6_goodtill" datatype="date" name="item6_goodtill" readonly>
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