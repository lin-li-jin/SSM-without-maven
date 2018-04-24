<%@ page contentType="text/html;charset=GBK" %>
<%@ taglib prefix="ww" uri="webwork" %>
<html>
<%
	String subjectPath=request.getContextPath();
%>
<head>
	<title>Forward����</title>
	<link rel="stylesheet" type="text/css" href="<%=subjectPath%>/resources/calendar/js/My97DatePicker/skin/WdatePicker.css"/>

	<script src="<%=subjectPath%>/resources/js/jquery-1.8.3.js" type="text/javascript"></script>
	<script src="<%=subjectPath%>/resources/js/jquery.validate.js" type="text/javascript"></script>
	<script src="<%=subjectPath%>/resources/js/additional-methods.js"/>
	<script src="<%=subjectPath%>/resources/ponycss/jquery_validate.css" type="text/javascript"></script>
	<script src="<%=subjectPath%>/resources/js/jquery.easyui.min.js" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css" href="<%=subjectPath%>/resources/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=subjectPath%>/resources/css/exam/stop_loss.css">
	<script type="text/javascript" src="<%=subjectPath%>/resources/calendar/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=subjectPath%>/resources/js/exam/forward.js" charset="GBK"></script>
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
				var Acc = $("#item2_Acc").val();
				var forwardAccMount = $("#item4_forwardAccMount").val();
				var forwardValueDate1 = $("#item6_goodtill").val();
				var forwardValueDate = parseInt(forwardValueDate1.replace(/[^0-9]/ig,""));
				var forwardPrice = $("#item1_basis").val();
				var forwardProviderNo = $("#item5_provider").val();
				var DirectionScore = $("#item3_directionscore").val();
				var AccScore = $("#item2_AccScore").val();
				var forwardAccMountScore = $("#item4_forwardAccMountScore").val();
				var forwardValueDateScore = $("#item6_goodtillscore").val();
				var forwardPriceScore = $("#item1_basisScore").val();
				var forwardProviderNoScore = $("#item5_providerscore").val();
				
				var item={
					"item.examContent":examContent,
					"item.accTypeNo":accTypeNo,
				    "item.examNo":"",
				    "item.type":type,
				    "item.Direction":Direction,
				    "item.Acc":Acc,
				    "item.forwardAccMount":forwardAccMount,
				    "item.forwardValueDate":forwardValueDate, // ����ʱ��
				    "item.forwardPrice":forwardPrice,  //forward ���׻���
				    "item.forwardProviderNo":forwardProviderNo, //���׶��ַ��� 
				    "item.forwardScore.directionScore":DirectionScore,
				    "item.forwardScore.accScore":AccScore,
				    "item.forwardScore.accAmountScore":forwardAccMountScore,
				    "item.forwardScore.valueDateScore":forwardValueDateScore,
				    "item.forwardScore.priceScore":forwardPriceScore,  //���׻���
				    "item.forwardScore.providerNoScore":forwardProviderNoScore};
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
		�������ͣ�forward����
	</div>
	���:
	<div style="border: solid 4px #148213;width: 400px;">
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
			<div style="margin-top: 3px;margin-left: 2px;margin-right: 2px;height: 40px;line-height: 40px;">
				<input type="checkbox" name="checkbox1" value="checkbox" onchange="haha(this,1)"> ���׻��� &nbsp;&nbsp;&nbsp;
				<div style="display: inline">
					<label for="item1_basisScore" >���� : </label>
					<input type="input" name="item1_basisScore" id="item1_basisScore" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
				</div>
				<div style="display: inline">
					<label for="item1_basis">��ȷ�� : </label>
					<input type="input" name="item1_basis" id="item1_basis" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
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
			<div  style="margin-top: 3px;margin-left: 2px;margin-right: 2px;height: 40px;line-height: 40px;">
				<input type="checkbox" name="accMountcheckbox" value="checkbox" onchange="haha(this,4)"> ���׽�� &nbsp;&nbsp;&nbsp;

				<div style="display: inline">
					<label for="item4_forwardAccMountScore">���� : </label>
				<input type="input" readonly="readonly" name="item4_forwardAccMountScore" id="item4_forwardAccMountScore" style="width: 60px;"> &nbsp;&nbsp;&nbsp;
				</div>

				<div style="display: inline">
					<label for="item4_forwardAccMount">��ȷ�� :</label>
				 <input id="item4_forwardAccMount" readonly name="item4_forwardAccMount" type="input"  style="width: 60px;"> &nbsp;&nbsp;&nbsp;
				</div>
			</div>
		</form>
		<form id="form5">
			<div style="margin-top: 3px;margin-left: 2px;margin-right: 2px;height: 40px;line-height: 40px;">
				<input type="checkbox" name="checkbox1" value="checkbox" onchange="haha(this,5)"> ���׶��ֺ� &nbsp;&nbsp;&nbsp;
				<div style="display: inline">
					<label for="item5_providerscore" >���� : </label>
					<input type="input" name="item5_providerscore" id="item5_providerscore" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
				</div>
				<div style="display: inline">
					<label for="item5_provider">��ȷ�� : </label>
					<input type="input" name="item5_provider" id="item5_provider" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
				</div>
			</div>
		</form>
		<form id="form6">
			<div style="margin-top: 3px;margin-left: 2px;margin-right: 2px;height: 40px;line-height: 40px;">
				<input type="checkbox" name="checkbox1" value="checkbox" onchange="haha(this,6)"> ����ʱ�� &nbsp;&nbsp;&nbsp;
				<div style="display: inline">
					<label for="item6_goodtillscore" >���� :</label>
					<input type="input" name="item6_goodtillscore" id="item6_goodtillscore" style="width: 60px;" readonly> &nbsp;&nbsp;&nbsp;
				</div>
				<div style="display: inline">
					<label for="item6_goodtill" >��ȷ�� : <img src="<%=subjectPath%>/resources/calendar/image/Button.gif" onclick="WdatePicker({el:'item6_goodtill'})" > &nbsp;&nbsp;&nbsp;</label>
					<input type="text" id="item6_goodtill" datatype="date" name="item6_goodtill" readonly>
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