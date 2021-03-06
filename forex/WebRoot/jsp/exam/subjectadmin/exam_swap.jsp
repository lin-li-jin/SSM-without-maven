<%@ page contentType="text/html;charset=GBK" %>
<%@ taglib prefix="ww" uri="webwork" %>
<html>
<%
	String subjectPath=request.getContextPath();
%>
<head>
	<title>Swap交易</title>
	<link rel="stylesheet" type="text/css" href="<%=subjectPath%>/resources/calendar/js/My97DatePicker/skin/WdatePicker.css"/>

	<script src="<%=subjectPath%>/resources/js/jquery-1.8.3.js" type="text/javascript"></script>
	<script src="<%=subjectPath%>/resources/js/jquery.validate.js" type="text/javascript"></script>
	<script src="<%=subjectPath%>/resources/js/additional-methods.js"/>
	<script src="<%=subjectPath%>/resources/ponycss/jquery_validate.css" type="text/javascript"></script>
	<script src="<%=subjectPath%>/resources/js/jquery.easyui.min.js" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css" href="<%=subjectPath%>/resources/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=subjectPath%>/resources/css/exam/stop_loss.css">
	<script type="text/javascript" src="<%=subjectPath%>/resources/calendar/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=subjectPath%>/resources/js/exam/swap.js" charset="GBK"></script>
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
				var swapAccAmount = $("#item4_swapAccMount").val();
				var swapSpot = $("#item8_isSpot").val();
				var swapStartDate1 = $("#item6_goodfrom").val();
				var swapStartDate = parseInt(swapStartDate1.replace(/[^0-9]/ig,""));
				var swapMaturityDate1 = $("#item7_goodtill").val();
				var swapMaturityDate = parseInt(swapMaturityDate1.replace(/[^0-9]/ig,""));
				var swapFixedType = $("#item9_fixedType").val();
				var swapFixedRate = $("#item10_fixedRate").val();
				var swapCbasis = $("#item11_cBasic").val();
				var swapFbasis = $("#item12_fBasic").val();
				var swapFrequency = $("#item13_Frequency").val();
				var swapLibor = $("#item14_Libor").val();
				var swapProviderNo = $("#item5_provider").val();
				var swapPoint = $("#item1_basis").val();
				var directionScore = $("#item3_directionscore").val();
				var accScore = $("#item2_AccScore").val();
				var swapAccAmountScore = $("#item4_swapAccMountScore").val();
				var swapSpotScore = $("#item8_isSpotscore").val();
				var swapStartDateScore = $("#item6_goodfromscore").val();
				var swapMaturityDateScore = $("#item7_goodtillscore").val();
				var swapFixedTypeScore = $("#item9_fixedTypescore").val();
				var swapFixedRateScore = $("#item10_fixedRatescore").val();
				var swapCbasisScore = $("#item11_cBasicscore").val();
				var swapFbasisScore = $("#item12_fBasicscore").val();
				var swapFrequencyScore = $("#item13_Frequencyscore").val();
				var swapLiborScore = $("#item14_Liborscore").val();
				var swapProviderNoScore = $("#item5_providerscore").val();
				var swapPointScore = $("#item1_basisScore").val();
				var item={
				    "item.examContent":examContent,
				    "item.accTypeNo":accTypeNo,
				    "item.examNo":"",
				    "item.type":type,
				    "item.Direction":Direction,
	                "item.Acc":Acc,
					"item.swapAccAmount":swapAccAmount,
		            "item.swapSpot":swapSpot, 
		            "item.swapStartDate":swapStartDate,  
		            "item.swapMaturityDate":swapMaturityDate, //交易对手方号 
		            "item.swapFixedType":swapFixedType,
		            "item.swapFixedRate":swapFixedRate,
		            "item.swapCbasis":swapCbasis,
		            "item.swapFbasis":swapFbasis,
		            "item.swapFrequency":swapFrequency,
		            "item.swapLibor":swapLibor,
		            "item.swapProviderNo":swapProviderNo,
		            "item.swapPoint":swapPoint,

				    "item.swapScore.directionScore":directionScore,
				    "item.swapScore.accScore":accScore,
				    "item.swapScore.accAmountScore":swapAccAmountScore,
				    "item.swapScore.spotScore":swapSpotScore,
				    "item.swapScore.startDateScore":swapStartDateScore,  
				    "item.swapScore.maturityDateScore":swapMaturityDateScore,
				    "item.swapScore.fixedTypeScore":swapFixedTypeScore,
				    "item.swapScore.fixedRateScore":swapFixedRateScore,
				    "item.swapScore.cbasisScore":swapCbasisScore,
				    "item.swapScore.fbasisScore":swapFbasisScore,
				    "item.swapScore.frequencyScore":swapFrequencyScore,
				    "item.swapScore.liborScore":swapLiborScore,
				    "item.swapScore.providerNoScore":swapProviderNoScore,
				    "item.swapScore.pointScore":swapPointScore
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
	<div>交易类型：swap 交易</div>
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
					<label for="item4_swapAccMountScore">分数 : </label>
				<input type="input" readonly="readonly" name="item4_swapAccMountScore" id="item4_swapAccMountScore" style="width: 60px;"> &nbsp;&nbsp;&nbsp;
				</div>

				<div style="display: inline">
					<label for="item4_swapAccMount">正确答案 :</label>
				 <input id="item4_swapAccMount" readonly name="item4_swapAccMount" type="input"  style="width: 60px;"> &nbsp;&nbsp;&nbsp;
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
		<form id="form8">
			<div style="margin-top: 3px;margin-left: 2px;margin-right: 2px;height: 40px;line-height: 40px;">
				<input type="checkbox" name="checkbox1" value="checkbox" onchange="enableCheck(this,8)"> 是否为即期对远期掉期交易 &nbsp;&nbsp;&nbsp;
				<div style="display: inline">
					<label for="item8_isSpotscore">分数:</label>
					<input type="input" name="item8_isSpotscore" id="item8_isSpotscore" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
				</div>
				<div style="display: inline">
					<label for="item8_isSpot" >正确答案 :</label>
					<select name="item8_isSpot" id="item8_isSpot" disabled="disabled">
						<option value="Y">是</option>
						<option value="N">否</option>
					</select> &nbsp;&nbsp;&nbsp;
				</div>
			</div>
		</form>
		<form id="form9">
			<div style="margin-top: 3px;margin-left: 2px;margin-right: 2px;height: 40px;line-height: 40px;">
				<input type="checkbox" name="checkbox1" value="checkbox" onchange="enableCheck(this,9)"> 固定利率类型 &nbsp;&nbsp;&nbsp;
				<div style="display: inline">
					<label for="item9_fixedTypescore">分数:</label>
					<input type="input" name="item9_fixedTypescore" id="item9_fixedTypescore" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
				</div>
				<div style="display: inline">
					<label for="item9_fixedType" >正确答案 :</label>
					<select name="item9_fixedType" id="item9_fixedType" disabled="disabled">
						<option value="receive">receive</option>
						<option value="pay">pay</option>
					</select> &nbsp;&nbsp;&nbsp;
				</div>
			</div>
		</form>
		<form id="form10">
			<div style="margin-top: 3px;margin-left: 2px;margin-right: 2px;height: 40px;line-height: 40px;">
				<input type="checkbox" name="checkbox1" value="checkbox" onchange="haha(this,10)"> 固定利率 &nbsp;&nbsp;&nbsp;
				<div style="display: inline">
					<label for="item10_fixedRatescore"> 分数 : </label>
					<input type="input" id="item10_fixedRatescore" name="item10_fixedRatescore" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
				</div>
				<div style="display: inline">
					<label for="item10_fixedRate"> 正确答案 :</label>
					<input type="input" id="item10_fixedRate" name="item10_fixedRate" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
				</div>
			</div>
		</form>
		<form id="form11">
			<div style="margin-top: 3px;margin-left: 2px;margin-right: 2px;height: 40px;line-height: 40px;">
				<input type="checkbox" name="checkbox1" value="checkbox" onchange="haha(this,11)"> 近端掉期点 &nbsp;&nbsp;&nbsp;
				<div style="display: inline">
					<label for="item11_cBasicscore"> 分数 : </label>
					<input type="input" id="item11_cBasicscore" name="item11_cBasicscore" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
				</div>
				<div style="display: inline">
					<label for="item11_cBasic"> 正确答案 :</label>
					<input type="input" id="item11_cBasic" name="item11_cBasic" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
				</div>
			</div>
		</form>
		<form id="form12">
			<div style="margin-top: 3px;margin-left: 2px;margin-right: 2px;height: 40px;line-height: 40px;">
				<input type="checkbox" name="checkbox1" value="checkbox" onchange="haha(this,12)"> 远端掉期点 &nbsp;&nbsp;&nbsp;
				<div style="display: inline">
					<label for="item12_fBasicscore"> 分数 : </label>
					<input type="input" id="item12_fBasicscore" name="item12_fBasicscore" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
				</div>
				<div style="display: inline">
					<label for="item12_fBasic"> 正确答案 :</label>
					<input type="input" id="item12_fBasic" name="item12_fBasic" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
				</div>
			</div>
		</form>
		<form id="form13">
			<div style="margin-top: 3px;margin-left: 2px;margin-right: 2px;height: 40px;line-height: 40px;">
				<input type="checkbox" name="checkbox1" value="checkbox" onchange="enableCheck(this,13)"> 近端付息类型 &nbsp;&nbsp;&nbsp;
				<div style="display: inline">
					<label for="item13_Frequencyscore">分数:</label>
					<input type="input" name="item13_Frequencyscore" id="item13_Frequencyscore" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
				</div>
				<div style="display: inline">
					<label for="item13_Frequency" >正确答案 :</label>
					<select name="item13_Frequency" id="item13_Frequency" disabled="disabled">
						<option value="0">隔夜</option>
						<option value="1">一周</option>
						<option value="2">一月</option>
						<option value="3">二月</option>
						<option value="4">一季度</option>
						<option value="5">半年</option>
						<option value="6">一年</option>
					</select> &nbsp;&nbsp;&nbsp;
				</div>
			</div>
		</form>
		<form id="form14">
			<div style="margin-top: 3px;margin-left: 2px;margin-right: 2px;height: 40px;line-height: 40px;">
				<input type="checkbox" name="checkbox1" value="checkbox" onchange="enableCheck(this,14)"> 利率类型 &nbsp;&nbsp;&nbsp;
				<div style="display: inline">
					<label for="item14_Liborscore">分数:</label>
					<input type="input" name="item14_Liborscore" id="item14_Liborscore" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
				</div>
				<div style="display: inline">
					<label for="item14_Libor" >正确答案 :</label>
					<select name="item14_Libor" id="item14_Libor" disabled="disabled">
						<option value="0">隔夜</option>
						<option value="1">一周</option>
						<option value="2">一月</option>
						<option value="3">二月</option>
						<option value="4">一季度</option>
						<option value="5">半年</option>
						<option value="6">一年</option>
					</select> &nbsp;&nbsp;&nbsp;
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