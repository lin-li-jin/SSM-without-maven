<%@ page contentType="text/html;charset=GBK" %>
<%@ taglib prefix="ww" uri="webwork" %>
<html>
<%
	String subjectPath=request.getContextPath();
%>
<head>
	<title>market breakout交易</title>
	<link rel="stylesheet" type="text/css" href="<%=subjectPath%>/resources/calendar/js/My97DatePicker/skin/WdatePicker.css"/>

	<script src="<%=subjectPath%>/resources/js/jquery-1.8.3.js" type="text/javascript"></script>
	<script src="<%=subjectPath%>/resources/js/jquery.validate.js" type="text/javascript"></script>
	<script src="<%=subjectPath%>/resources/js/additional-methods.js"/>
	<script src="<%=subjectPath%>/resources/ponycss/jquery_validate.css" type="text/javascript"></script>
	<script src="<%=subjectPath%>/resources/js/jquery.easyui.min.js" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css" href="<%=subjectPath%>/resources/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=subjectPath%>/resources/css/exam/stop_loss.css">
	<script type="text/javascript" src="<%=subjectPath%>/resources/calendar/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=subjectPath%>/resources/js/exam/market_breakout.js" charset="GBK"></script>
	<script type="text/javascript">
		$(function(){
			$("#add").click(function(){
				var examContent = $("#examContent").val();
				var accTypeNo = "<ww:property value='#request.accType'/>";
				var type = "<ww:property value='#request.accDetail'/>";
				var Direction = $("#item2_direction").val();
				if(Direction == "买"){
					Direction = 0;
				}else if(Direction == "卖"){
					Direction = 1;
				}else{
					Direction = "";
				}
				var Acc = $("#item1_Acc").val();
				var mbktStopLossAccMount1 = $("#item3_marketsoAccMount").val();
				var mbktStopLossPrice1 = $("#item4_price").val();
				var mbktStopLossAccMount2 = $("#item5_marketstAccMount").val();
				var mbktStopLossPrice2 = $("#item6_stPrice").val();
				var mbkStopLossAccmonitorPrice = $("#item7_accmonitorprice").val();
				var mbkGoodFrom1 = $("#item8_goodfrom").val();
				var mbkGoodFrom = parseInt(mbkGoodFrom1.replace(/[^0-9]/ig,""));
				var mbkGoodTill1 = $("#item9_goodtill").val();
				var mbkGoodTill = parseInt(mbkGoodTill1.replace(/[^0-9]/ig,""));
				var DirectionScore = $("#item2_directionscore").val();
				var AccScore = $("#item1_AccScore").val();
				var mbktStopLossAccMount1Score = $("#item3_marketsoAccMountScore").val();
				var mbktStopLossPrice1Score = $("#item4_pricescore").val();
				var mbktStopLossAccMount2Score = $("#item5_marketstAccMountScore").val();
				var mbktStopLossPrice2Score = $("#item6_stPricescore").val();
				var mbkStopLossAccmonitorPriceScore = $("#item7_accmonitorpricescore").val();
				var mbkGoodFromScore = $("#item8_goodfromscore").val();
				var mbkGoodTillScore = $("#item9_goodtillscore").val();
				var item={
					"item.examContent":examContent,
				    "item.accTypeNo":accTypeNo,
				    "item.examNo":"",
				    "item.type":type,
				    "item.Direction":Direction,
	                "item.Acc":Acc,
					"item.mbktStopLossAccMount1":mbktStopLossAccMount1,
				    "item.mbktStopLossPrice1":mbktStopLossPrice1,
	                "item.mbktStopLossAccMount2":mbktStopLossAccMount2,
	                "item.mbktStopLossPrice2":mbktStopLossPrice2,
		            "item.mbkStopLossAccmonitorPrice":mbkStopLossAccmonitorPrice,
	                "item.mbkGoodFrom":mbkGoodFrom,
			        "item.mbkGoodTill":mbkGoodTill,
				    "item.marketBreakoutScore.directionScore":DirectionScore,
				    "item.marketBreakoutScore.accScore":AccScore,
				    "item.marketBreakoutScore.stopLossAmount1Score":mbktStopLossAccMount1Score,
				    "item.marketBreakoutScore.stopLossPrice1Score":mbktStopLossPrice1Score,
				    "item.marketBreakoutScore.stopLossAmount2Score":mbktStopLossAccMount2Score,
				    "item.marketBreakoutScore.stopLossPrice2Score":mbktStopLossPrice2Score,
				    "item.marketBreakoutScore.monitorPriceScore":mbkStopLossAccmonitorPriceScore,
				    "item.marketBreakoutScore.goodFromScore":mbkGoodFromScore,
				    "item.marketBreakoutScore.goodTillScore":mbkGoodTillScore}
				$.ajax({
					type:"post",
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
		交易类型：market breakout交易
	</div>
	题干:
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
			<input type="checkbox" name="accmonitorPrice_checkbox" value="checkbox" onchange="haha(this,1)"> 交易币种 &nbsp;&nbsp;&nbsp;
			<div style="display: inline">
				<label for="item1_AccScore"> 分数 : </label>
				<input type="input" name="item1_AccScore" id="item1_AccScore" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
			</div>
			<div style="display: inline">
				<label for="item1_Acc">正确答案 : </label>
			<input type="input" name="item1_Acc" id="item1_Acc" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
			</div>
		</div>
		</form>
		<form id="form2">
			<div style="margin-top: 3px;margin-left: 2px;margin-right: 2px;height: 40px;line-height: 40px;">
				<input type="checkbox" name="checkbox1" value="checkbox" onchange="haha(this,2)"> 交易方向 &nbsp;&nbsp;&nbsp;
				<div style="display: inline">
					<label for="item2_directionscore"> 分数 : </label>
					<input type="input" id="item2_directionscore" name="item2_directionscore" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
				</div>
				<div style="display: inline">
					<label for="item2_direction"> 正确答案 :</label>
					<input type="input" id="item2_direction" name="item3_direction" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
				</div>
			</div>
		</form>
		<form id="form3">
			<div  style="margin-top: 3px;margin-left: 2px;margin-right: 2px;height: 40px;line-height: 40px;">
				<input type="checkbox" name="accMountcheckbox" value="checkbox" onchange="haha(this,3)"> stop loss1交易金额 &nbsp;&nbsp;&nbsp;

				<div style="display: inline">
					<label for="item3_marketsoAccMountScore">分数 : </label>
				<input type="input" readonly="readonly" name="item3_marketsoAccMountScore" id="item3_marketsoAccMountScore" style="width: 60px;"> &nbsp;&nbsp;&nbsp;
				</div>

				<div style="display: inline">
					<label for="item3_marketsoAccMount">正确答案 :</label>
				 <input id="item3_marketsoAccMount" readonly name="item3_marketsoAccMount" type="input"  style="width: 60px;"> &nbsp;&nbsp;&nbsp;
				</div>
			</div>
		</form>
		<form id="form4">
			<div style="margin-top: 3px;margin-left: 2px;margin-right: 2px;height: 40px;line-height: 40px;">
				<input type="checkbox" name="checkbox1" value="checkbox" onchange="haha(this,4)">stop loss1交易价格 &nbsp;&nbsp;&nbsp;
				<div style="display: inline">
					<label for="item4_soPricescore" >分数 : </label>
					<input type="input" name="item4_soPricescore" id="item4_pricescore" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
				</div>
				<div style="display: inline">
					<label for="item4_soPrice">正确答案 : </label>
					<input type="input" name="item4_soPrice" id="item4_soPrice" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
				</div>
			</div>
		</form>
		<form id="form5">
			<div  style="margin-top: 3px;margin-left: 2px;margin-right: 2px;height: 40px;line-height: 40px;">
				<input type="checkbox" name="accMountcheckbox" value="checkbox" onchange="haha(this,5)"> stop loss2交易金额 &nbsp;&nbsp;&nbsp;

				<div style="display: inline">
					<label for="item5_marketstAccMountScore">分数 : </label>
				<input type="input" readonly="readonly" name="item5_marketstAccMountScore" id="item5_marketstAccMountScore" style="width: 60px;"> &nbsp;&nbsp;&nbsp;
				</div>

				<div style="display: inline">
					<label for="item5_marketstAccMount">正确答案 :</label>
				 <input id="item5_marketstAccMount" readonly name="item5_marketstAccMount" type="input"  style="width: 60px;"> &nbsp;&nbsp;&nbsp;
				</div>
			</div>
		</form>
		<form id="form6">
			<div style="margin-top: 3px;margin-left: 2px;margin-right: 2px;height: 40px;line-height: 40px;">
				<input type="checkbox" name="checkbox1" value="checkbox" onchange="haha(this,6)"> stop loss2交易价格 &nbsp;&nbsp;&nbsp;
				<div style="display: inline">
					<label for="item6_stPricescore" >分数 : </label>
					<input type="input" name="item6_stPricescore" id="item6_stPricescore" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
				</div>
				<div style="display: inline">
					<label for="item6_stPrice">正确答案 : </label>
					<input type="input" name="item6_stPrice" id="item6_stPrice" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
				</div>
			</div>
		</form>
		<form id="form7">
			<div style="margin-top: 3px;margin-left: 2px;margin-right: 2px;height: 40px;line-height: 40px;">
				<input type="checkbox" name="checkbox1" value="checkbox" onchange="enableCheck(this,7)"> 模拟类型 &nbsp;&nbsp;&nbsp;
				<div style="display: inline">
					<label for="item7_accmonitorpricescore">分数:</label>
					<input type="input" name="item5_accmonitorpricescore" id="item7_accmonitorpricescore" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
				</div>
				<div style="display: inline">
					<label for="item7_accmonitorprice" >正确答案 :</label>
					<select name="item7_accmonitorprice" id="item7_accmonitorprice" disabled="disabled">
						<option value="ASK">ASK</option>
						<option value="BID">BID</option>
					</select> &nbsp;&nbsp;&nbsp;
				</div>
			</div>
		</form>
		<form id="form8">
			<div style="margin-top: 3px;margin-left: 2px;margin-right: 2px;height: 40px;line-height: 40px;">
				<input type="checkbox" name="checkbox1" value="checkbox" onchange="haha(this,8)"> 开始时间 &nbsp;&nbsp;&nbsp;
				<div style="display: inline">
					<label for="item8_goodfromscore" >分数 : </label>
					<input type="input" name="item8_goodfromscore" id="item8_goodfromscore" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
				</div>
				<div style="display: inline">
					<label for="item_goodfrom" >正确答案 : <img src="<%=subjectPath%>/resources/calendar/image/Button.gif" onclick="WdatePicker({el:'item_goodfrom'})" > &nbsp;&nbsp;&nbsp;</label>
					<input type="text" id="item_goodfrom" datatype="date" name="item_goodfrom" readonly>
				</div>
			</div>
		</form>
		<form id="form9">
			<div style="margin-top: 3px;margin-left: 2px;margin-right: 2px;height: 40px;line-height: 40px;">
				<input type="checkbox" name="checkbox1" value="checkbox" onchange="haha(this,9)"> 交割时间 &nbsp;&nbsp;&nbsp;
				<div style="display: inline">
					<label for="item9_goodtillscore" >分数 :</label>
					<input type="input" name="item9_goodtillscore" id="item9_goodtillscore" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
				</div>
				<div style="display: inline">
					<label for="item_goodtill" >正确答案 : <img src="<%=subjectPath%>/resources/calendar/image/Button.gif" onclick="WdatePicker({el:'item_goodtill'})" > &nbsp;&nbsp;&nbsp;</label>
					<input type="text" id="item_goodtill" datatype="date" name="item_goodtill" readonly>
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