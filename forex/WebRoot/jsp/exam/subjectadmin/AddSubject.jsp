<%@ page contentType="text/html;charset=GBK" %>
<%@ taglib prefix="ww" uri="webwork" %>
<html>
<%
	String subjectPath=request.getContextPath();
%>
<head>
	<title>���ģ��Ͷ��ϵͳ</title>
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
				if(Direction == "��"){
					Direction = 0;
				}else if(Direction == "��"){
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
						alert("���ӳɹ�");
					},
					error:function(){
						alert("����ʧ�ܣ�")
					}
				});
			});
		});

	</script>
</head>
<body>

<div>
	<div>
		�������ͣ�stop loss����
	</div>
	
	���:
	<div style="border: solid 4px #148213;width: 400px;">
		<!-- stop loss���� -->
		<!--
			1.��Ŀ����
		 -->
		<div style="margin: 3px 4px;" class="stop_loss">
			<textarea style="width: 280px;height: 180px;" id="examContent">

			</textarea>
		</div>
	</div>
	��ֽ���Ҫ��:
	<div style="border: solid 4px #148213;width:600px;">
		<form id="form1">
			<div  style="margin-top: 3px;margin-left: 2px;margin-right: 2px;height: 40px;line-height: 40px;">
				<input type="checkbox" name="accMountcheckbox" value="checkbox" onchange="haha(this,1)"> ���׽�� &nbsp;&nbsp;&nbsp;

				<div style="display: inline">
					<label for="item1_stopLossAccMountScore">���� : </label>
				<input type="input" readonly="readonly" name="item1_stopLossAccMountScore" id="item1_stopLossAccMountScore" style="width: 60px;"> &nbsp;&nbsp;&nbsp;
				</div>

				<div style="display: inline">
					<label for="item1_stopLossAccMount">��ȷ�� :</label>
				 <input id="item1_stopLossAccMount" readonly name="item1_stopLossAccMount" type="input"  style="width: 60px;"> &nbsp;&nbsp;&nbsp;
				</div>
			</div>
		</form>
		<form id="form2">
		<div style="margin-top: 3px;margin-left: 2px;margin-right: 2px;height: 40px;line-height: 40px;">
			<input type="checkbox" name="accmonitorPrice_checkbox" value="checkbox" onchange="haha(this,2)"> ���ױ��� &nbsp;&nbsp;&nbsp;
			<div style="display: inline">
				<label for="item2_AccScore"> ���� : </label>
				<input type="input" name="item2_AccScore" id="item2_AccScore" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
			</div>
			<div style="display: inline">
				<label for="item2_Acc">��ȷ�� : </label>
			<input type="input" name="item2_Acc" id="item2_Acc" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
			</div>
		</div>
		</form>
		<form id="form3">
			<div style="margin-top: 3px;margin-left: 2px;margin-right: 2px;height: 40px;line-height: 40px;">
				<input type="checkbox" name="checkbox1" value="checkbox" onchange="haha(this,3)"> ���׷��� &nbsp;&nbsp;&nbsp;
				<div style="display: inline">
					<label for="item3_directionscore"> ���� : </label>
					<input type="input" id="item3_directionscore" name="item3_directionscore" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
				</div>
				<div style="display: inline">
					<label for="item3_direction"> ��ȷ�� :</label>
					<input type="input" id="item3_direction" name="item3_direction" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
				</div>
			</div>
		</form>
		<form id="form4">
			<div style="margin-top: 3px;margin-left: 2px;margin-right: 2px;height: 40px;line-height: 40px;">
				<input type="checkbox" name="checkbox1" value="checkbox" onchange="haha(this,4)"> ���׼۸� &nbsp;&nbsp;&nbsp;
				<div style="display: inline">
					<label for="item4_pricescore" >���� : </label>
					<input type="input" name="item4_pricescore" id="item4_pricescore" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
				</div>
				<div style="display: inline">
					<label for="item4_price">��ȷ�� : </label>
					<input type="input" name="item4_price" id="item4_price" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
				</div>
			</div>
		</form>
		<form id="form5">
			<div style="margin-top: 3px;margin-left: 2px;margin-right: 2px;height: 40px;line-height: 40px;">
				<input type="checkbox" name="checkbox1" value="checkbox" onchange="enableCheck(this,5)"> ģ������ &nbsp;&nbsp;&nbsp;
				<div style="display: inline">
					<label for="item5_accmonitorpricescore">����:</label>
					<input type="input" name="item5_accmonitorpricescore" id="item5_accmonitorpricescore" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
				</div>
				<div style="display: inline">
					<label for="item5_accmonitorprice" >��ȷ�� :</label>
					<select name="item5_accmonitorprice" id="item5_accmonitorprice" disabled="disabled">
						<option value="ASK">ASK</option>
						<option value="BID">BID</option>
					</select> &nbsp;&nbsp;&nbsp;
				</div>
			</div>
		</form>
		<form id="form6">
			<div style="margin-top: 3px;margin-left: 2px;margin-right: 2px;height: 40px;line-height: 40px;">
				<input type="checkbox" name="checkbox1" value="checkbox" onchange="haha(this,6)"> ��ʼʱ�� &nbsp;&nbsp;&nbsp;
				<div style="display: inline">
					<label for="item6_goodfromscore" >���� : </label>
					<input type="input" name="item6_goodfromscore" id="item6_goodfromscore" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
				</div>
				<div style="display: inline">
					<label for="item6_goodfrom" >��ȷ�� : <img src="<%=subjectPath%>/resources/calendar/image/Button.gif" onclick="WdatePicker({el:'item6_goodfrom'})" > &nbsp;&nbsp;&nbsp;</label>
					<input type="text" id="item6_goodfrom" datatype="date" name="item6_goodfrom" readonly>
				</div>
			</div>
		</form>
		<form id="form7">
			<div style="margin-top: 3px;margin-left: 2px;margin-right: 2px;height: 40px;line-height: 40px;">
				<input type="checkbox" name="checkbox1" value="checkbox" onchange="haha(this,7)"> ����ʱ�� &nbsp;&nbsp;&nbsp;
				<div style="display: inline">
					<label for="item7_goodtillscore" >���� :</label>
					<input type="input" name="item7_goodtillscore" id="item7_goodtillscore" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
				</div>
				<div style="display: inline">
					<label for="item7_goodtill" >��ȷ�� : <img src="<%=subjectPath%>/resources/calendar/image/Button.gif" onclick="WdatePicker({el:'item7_goodtill'})" > &nbsp;&nbsp;&nbsp;</label>
					<input type="text" id="item7_goodtill" datatype="date" name="item7_goodtill" readonly>
				</div>
			</div>
		</form>
		<div style="margin-top: 3px;margin-left: 2px;margin-right: 2px;height: 40px;line-height: 40px;">
			�ܷ� : <input type="input" name="item." style="width: 60px;"> &nbsp;&nbsp;&nbsp;
		</div>

	</div>
	<div>
		<button id="add">����</button>
	</div>
</div>

</body>

</html>