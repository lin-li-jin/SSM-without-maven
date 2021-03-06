<%@ page contentType="text/html;charset=GBK" %>
<%@ taglib prefix="ww" uri="webwork" %>
<html>
<%
	String subjectPath=request.getContextPath();
%>
<head>
	<title>外汇模拟投资系统</title>
	<link rel="stylesheet" type="text/css" href="<%=subjectPath%>/resources/calendar/js/My97DatePicker/skin/WdatePicker.css"/>

	<script src="<%=subjectPath%>/resources/js/jquery-1.8.3.js" type="text/javascript"></script>
	<script src="<%=subjectPath%>/resources/js/jquery.validate.js" type="text/javascript"></script>
	<script src="<%=subjectPath%>/resources/js/additional-methods.js"/>
	<script src="<%=subjectPath%>/resources/ponycss/jquery_validate.css" type="text/javascript"></script>
	<script src="<%=subjectPath%>/resources/js/jquery.easyui.min.js" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css" href="<%=subjectPath%>/resources/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=subjectPath%>/resources/css/exam/stop_loss.css">
	<script type="text/javascript" src="<%=subjectPath%>/resources/calendar/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=subjectPath%>/resources/js/exam/stop_loss.js"></script>
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
				var acc = $("#item2_Acc").val();
				var stopLossAccMount = $("#item1_stopLossAccMount").val();
				var stopLossPrice = $("#item4_price").val();
				var stopLossAccmonitorPrice = $("#item5_accmonitorprice").val();
				var stopLossGoodFrom1 = $("#item6_goodfrom").val();
				var stopLossGoodFrom = parseInt(stopLossGoodFrom1.replace(/[^0-9]/ig,""));
				var stopLossGoodTill1 = $("#item7_goodtill").val();
				var stopLossGoodTill = parseInt(stopLossGoodTill1.replace(/[^0-9]/ig,""));
				var DirectionScore = $("#item3_directionscore").val();
				var AccScore = $("#item2_AccScore").val();
				var stopLossAccMountScore = $("#item1_stopLossAccMountScore").val();
				var stopLossAccmonitorPriceScore = $("#item5_accmonitorpricescore").val();
				var stopLossGoodFromScore = $("#item6_goodfromscore").val();
				var stopLossGoodTillScore = $("#item7_goodtillscore").val();
				var stopLossPriceScore = $("#item4_pricescore").val();
				var item={
					"item.examContent":examContent,
				    "item.accTypeNo":accTypeNo,
				    "item.examNo":"",
				    "item.type":type,
				    "item.Direction":Direction,
	                "item.Acc":acc,
					"item.stopLossAccMount":stopLossAccMount,
				    "item.stopLossPrice":stopLossPrice,
				    "item.stopLossAccmonitorPrice":stopLossAccmonitorPrice,
	                "item.stopLossGoodFrom":stopLossGoodFrom,
			        "item.stopLossGoodTill":stopLossGoodTill,
				    "item.stopLossScore.DirectionScore":DirectionScore,
				    "item.stopLossScore.AccScore":AccScore,
				    "item.stopLossScore.stopLossAccMountScore":stopLossAccMountScore,
				    "item.stopLossScore.stopLossAccmonitorPriceScore":stopLossAccmonitorPriceScore,
				    "item.stopLossScore.stopLossGoodFromScore":stopLossGoodFromScore,
				    "item.stopLossScore.stopLossGoodTillScore":stopLossGoodTillScore,
				    "item.stopLossScore.stopLossPriceScore":stopLossPriceScore};
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
	<div>
		交易类型：stop loss交易
	</div>
	
	题干:
	<div style="border: solid 4px #148213;width: 400px;">
		<!-- stop loss交易 -->
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
					<label for="item1_stopLossAccMountScore">分数 : </label>
				<input type="input" readonly="readonly" name="item1_stopLossAccMountScore" id="item1_stopLossAccMountScore" style="width: 60px;"> &nbsp;&nbsp;&nbsp;
				</div>

				<div style="display: inline">
					<label for="item1_stopLossAccMount">正确答案 :</label>
				 <input id="item1_stopLossAccMount" readonly name="item1_stopLossAccMount" type="input"  style="width: 60px;"> &nbsp;&nbsp;&nbsp;
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
				<input type="checkbox" name="checkbox1" value="checkbox" onchange="enableCheck(this,5)"> 模拟类型 &nbsp;&nbsp;&nbsp;
				<div style="display: inline">
					<label for="item5_accmonitorpricescore">分数:</label>
					<input type="input" name="item5_accmonitorpricescore" id="item5_accmonitorpricescore" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
				</div>
				<div style="display: inline">
					<label for="item5_accmonitorprice" >正确答案 :</label>
					<select name="item5_accmonitorprice" id="item5_accmonitorprice" disabled="disabled">
						<option value="ASK">ASK</option>
						<option value="BID">BID</option>
					</select> &nbsp;&nbsp;&nbsp;
				</div>
			</div>
		</form>
		<form id="form6">
			<div style="margin-top: 3px;margin-left: 2px;margin-right: 2px;height: 40px;line-height: 40px;">
				<input type="checkbox" name="checkbox1" value="checkbox" onchange="haha(this,6)"> 开始时间 &nbsp;&nbsp;&nbsp;
				<div style="display: inline">
					<label for="item6_goodfromscore" >分数 : </label>
					<input type="input" name="item6_goodfromscore" id="item6_goodfromscore" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
				</div>
				<div style="display: inline">
					<label for="item6_goodfrom" >正确答案 : <img src="<%=subjectPath%>/resources/calendar/image/Button.gif" onclick="WdatePicker({el:'item6_goodfrom'})" > &nbsp;&nbsp;&nbsp;</label>
					<input type="text" id="item6_goodfrom" datatype="date" name="item6_goodfrom" readonly>
				</div>
			</div>
		</form>
		<form id="form7">
			<div style="margin-top: 3px;margin-left: 2px;margin-right: 2px;height: 40px;line-height: 40px;">
				<input type="checkbox" name="checkbox1" value="checkbox" onchange="haha(this,7)"> 交割时间 &nbsp;&nbsp;&nbsp;
				<div style="display: inline">
					<label for="item7_goodtillscore" >分数 :</label>
					<input type="input" name="item7_goodtillscore" id="item7_goodtillscore" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
				</div>
				<div style="display: inline">
					<label for="item7_goodtill" >正确答案 : <img src="<%=subjectPath%>/resources/calendar/image/Button.gif" onclick="WdatePicker({el:'item7_goodtill'})" > &nbsp;&nbsp;&nbsp;</label>
					<input type="text" id="item7_goodtill" datatype="date" name="item7_goodtill" readonly>
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