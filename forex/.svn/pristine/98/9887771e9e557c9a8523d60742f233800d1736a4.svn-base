<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="ww" uri="webwork"%>
<html>
	<head>
		<script language="JavaScript" src="resources/js/check.js"></script>
		<script language="JavaScript" src="resources/js/Common.js"></script>
		<script type="text/javascript" src="resources/js/validate.js"></script>
		<script src="resources/ponyjs/jquery.js" type="text/javascript"></script>
		<script src="resources/ponyjs/jquery_validate.js"
			type="text/javascript"></script>
		<script src="resources/ponyjs/jquery.ui.draggable.js"
			type="text/javascript"></script>
		<script src="resources/ponyjs/jquery.alerts.js"
			type="text/javascript"></script>
		<!-- <link rel="stylesheet" href="resources/css/style.css" type="text/css" /> -->	
		<link rel="stylesheet" href="/forex/resources/css/font_styleY.css" type="text/css" media="screen, print" />
		<title>排名参数设置</title>
		<style type="text/css">
			#top{margin: 0; padding:3px 5px; height: 24px; width:1200px; line-height: 24px; background-image: url("images/b.png"); color: white;}
		</style>
		<script language="JavaScript">
			$(document).ready(function(){
				$("#groupTwoSpanA").css("visibility","hidden");
				$("#groupTwoSpanO").css("visibility","hidden");
			});
			function changeTab(tabIndex){
				if(tabIndex=="0"){
					window.location.href = "qryParamMngInit.action";
				}
				if(tabIndex=="1"){
					window.location.href = "stuDataInputInit.action";
				}
				if(tabIndex=="2"){
					window.location.href = "pwResetAaccUnlockInit.action";
				}
				if(tabIndex=="3"){
					window.location.href = "accParamSetInit.action";
				}
			}
			function changeGroupType(group,groupTwoSpan){
				var groupType = $("#"+group).val();
					if(groupType=="O"){
						$("#"+groupTwoSpan).css("visibility","hidden");
					}
					else if(groupType=="T"){
						$("#"+groupTwoSpan).css("visibility","visible");
					}
					else{
						$("#"+groupTwoSpan).css("visibility","hidden");
					}
			}
			function genGroupTwo(groupOneId,groupTwoId){
				var groupOne = $("#"+groupOneId+" option:selected").val();
				$("#"+groupTwoId).empty();//清空前一类型的选项
					$.post("getGroupTwos.action",{groupOne:groupOne},
				function(dat){
					var groupTwoList = new Array();
					groupTwoList = dat.split("$$");
					for(var i=-1;i<groupTwoList.length;i++){
						var newOption;
						if(i==-1){
							newOption = new Option("","");
						}else{
							newOption = new Option(groupTwoList[i],groupTwoList[i]);
						}
						if(groupTwoId=="groupTwoA"){
							document.accParamForm.groupTwoA.options.add(newOption);
						}
						if(groupTwoId=="groupTwoO"){
							document.overallParamForm.groupTwoO.options.add(newOption);
						}
					}
				});	
			}
			
			function ajaxGetParam(type){
				var action='';
				var idA='';
				var idB='';
				var idC='';
				var idD='';
				var groupNum='';
				if(type=='groupTypeA'){
					idA='WAmount';
					idB='WQuantity';
					idC='WRate';
					idD='accRankNum';
					var groupType = $("#groupTypeA").val();
					if(groupType == "O"){
						groupNum = $("#groupOneA").val();
					}
					if(groupType == "T"){
						groupNum = $("#groupTwoA").val();
					}
					action='accQryParamQuery.action?groupNum='+groupNum+'&paramType=groupTypeA';
				}else{
					idA='WCnyAcc';
					idB='WForAcc';
					idC='WMarginAcc';
					idD='overallRankNum';
					var groupType = $("#groupTypeO").val();
					if(groupType == "O"){
						groupNum = $("#groupOneO").val();
					}
					if(groupType == "T"){
						groupNum = $("#groupTwoO").val();
					}
					action='accQryParamQuery.action?groupNum='+groupNum;
				}
				if(groupNum==""||groupNum==null){
					alert("请选择需要查询参数的组号！");
					return;
				}
				$.post(action,'',function(data){
					  var obj = eval('(' + data + ')');
					  //alert(obj.id+"--"+obj.WAmount+"--"+obj.WQuantity+"--"+obj.WRate);
					  if(obj!=null){
						  if(type=='groupTypeA'){
							  $("#"+idA).val(obj.WAmount);
							  $("#"+idB).val(obj.WQuantity);
							  $("#"+idC).val(obj.WRate);
							  $("#"+idD).val(obj.accRankNum);
						  }else{
							  $("#"+idA).val(obj.WCnyAcc);
							  $("#"+idB).val(obj.WForAcc);
							  $("#"+idC).val(obj.WMarginAcc);
							  $("#"+idD).val(obj.overallRankNum);
						  }
					  }else{
						  alert("无该组号记录");
					  }
				});
			}
			
			function resetData(type){
				//alert(type);
				if(type=='groupTypeA'){
					$("#WAmount").val("");
					$("#WQuantity").val("");
					$("#WRate").val("");
					$("#accRankNum").val("");
				}else{
					$("#WCnyAcc").val("");
					$("#WForAcc").val("");
					$("#WMarginAcc").val("");
					$("#overallRankNum").val("");
				}
			}
			
			function formsubmit(groupTypeId,groupOneId,groupTwoId,formName){
				if(groupTypeId=="groupTypeA"){
					var WAmount=$("#WAmount").val();
					var WQuantity=$("#WQuantity").val();
					var WRate=$("#WRate").val();
					if(WAmount==""||WQuantity==""||WRate==""){
						alert("权重参数不能为空");
						return "";
					}
					else if((parseInt(WAmount)+parseInt(WQuantity)+parseInt(WRate))!=100){
						alert("权重参数总和不为100%");
						return "";
					}
				}else{
					var WCnyAcc=$("#WCnyAcc").val();
					var WForAcc=$("#WForAcc").val();
					var WMarginAcc=$("#WMarginAcc").val();
					if(WCnyAcc==""||WForAcc==""||WMarginAcc==""){
						alert("权重参数不能为空");
						return "";
					}
					else if((parseInt(WCnyAcc)+parseInt(WForAcc)+parseInt(WMarginAcc))!=100){
						alert("权重参数总和不为100%");
						return "";
					}
				}
				var groupType = $("#"+groupTypeId).val();
				var groupNum = "";
				if(groupType == "O"){
					groupNum = $("#"+groupOneId).val();
				}
				if(groupType == "T"){
					groupNum = $("#"+groupTwoId).val();
				}
				if(groupNum==""||groupNum==null){
					alert("请选择需要设置参数的一级组号！");
					return;
				}else{
					if(formName == "accParamForm"){
						$("#groupNumA").val(groupNum);
						document.all(formName).action = "accQryParamModify.action";
					}
					if(formName == "overallParamForm"){
						$("#groupNumO").val(groupNum);
						document.all(formName).action = "overAllQryParamModify.action";
					}
					document.all(formName).submit();
				}
			}
		</script>
	</head>
	<body>
		<div id="body">
		<div id="top">
	    		<span style="margin-left: 10px;">账户管理:</span>
		    </div>
		<div>
			<input type="button" value="排名参数设置" onclick="changeTab('0')">
			<input type="button" value="学生信息导入" onclick="changeTab('1')">
			<input type="button" value="密码重置与账户解锁" onclick="changeTab('2')">
			<input type="button" value="交易员账户管理" onclick="changeTab('3')">
		</div>
			<form name="accParamForm" action="">
				<div>
					组别：
					<select id="groupTypeA" name="groupTypeA" onChange="changeGroupType('groupTypeA','groupTwoSpanA')">
						<option value="O">一级组</option>
						<option value="T">二级组</option>
					</select>
				</div>
				<div>
					<span>
					一级组
					<select id="groupOneA" name="groupOneA" onChange="genGroupTwo('groupOneA','groupTwoA')">
						<option value=""></option>
						<ww:iterator value="#request.groupOneList">
							<option value='<ww:property value="groupId"/>'><ww:property value="groupId"/></option>
						</ww:iterator>
					</select>
					</span>
					<span id="groupTwoSpanA">
					二级组
					<select id="groupTwoA" name="groupTwoA">
					</select>
					</span>
					<input id="button" type="button" value="搜  索" onclick="ajaxGetParam('groupTypeA')"/>
				</div>
				<div>
					账户实时排名参数设定：
				</div>
				<div>
					交易金额权重：<input type="text" size="10" name="paramModel.WAmount" id="WAmount">%
					交易笔数权重：<input type="text" size="10" name="paramModel.WQuantity" id="WQuantity">%
					盈亏率权重：<input type="text" size="10" name="paramModel.WRate" id="WRate">%
				</div>
				<div>
					排名可见情况：本组交易员可见前
					<select name="paramModel.accRankNum" id="accRankNum">
						<option value=""></option>
						<option value="0">0</option>
						<option value="5">5</option>
						<option value="10">10</option>
						<option value="200">ALL</option>
					</select>
					名交易员的交易排名情况
					<input id="button" type="button" onClick="formsubmit('groupTypeA','groupOneA','groupTwoA','accParamForm')" value="确  定" />
					<input id="button" type="button" value="重  置" onclick="resetData('groupTypeA')"/>
				</div>
				<input type="hidden" id="groupNumA" name="paramModel.groupNum">
			</form>
			<hr>
			<form name="overallParamForm" action="">
				<div>
					组别：
					<select id="groupTypeO" name="groupTypeO" onChange="changeGroupType('groupTypeO','groupTwoSpanO')">
						<option value="O">一级组</option>
						<option value="T">二级组</option>
					</select>
				</div>
				<div>
					<span>
					一级组
					<select id="groupOneO" name="groupOneO" onChange="genGroupTwo('groupOneO','groupTwoO')">
						<option value=""></option>
						<ww:iterator value="#request.groupOneList">
							<option value='<ww:property value="groupId"/>'><ww:property value="groupId"/></option>
						</ww:iterator>
					</select>
					</span>
					<span id="groupTwoSpanO">
					二级组
					<select name="groupTwoO" id="groupTwoO">
					</select>
					</span>
					<input id="button" type="button" value="搜  索" onclick="ajaxGetParam('groupTypeO')"/>
				</div>
				<div>
					账户综合排名参数设定：
				</div>
				<div>
					人民币外币交易账户权重：<input type="text" size="10" name="paramModel.WCnyAcc" id="WCnyAcc">%
				</div>
				<div>
					外币对交易账户权重：<input type="text" size="10" name="paramModel.WForAcc" id="WForAcc">%
				</div>
				<div>
					保证金交易账户权重：<input type="text" size="10" name="paramModel.WMarginAcc" id="WMarginAcc">%
				</div>
				<div>
					排名可见情况：本组交易员可见前
					<select name="paramModel.overallRankNum" id="overallRankNum">
						<option value=""></option>
						<option value="0">0</option>
						<option value="5">5</option>
						<option value="10">10</option>
						<option value="200">ALL</option>
					</select>
					名交易员的交易排名情况
					<input id="button" type="button" onClick="formsubmit('groupTypeO','groupOneO','groupTwoO','overallParamForm')" value="确  定" />
					<input id="button" type="button" value="重  置" onclick="resetData('groupTypeO')"/>
				</div>
				<input type="hidden" id="groupNumO" name="paramModel.groupNum">
			</form>
		</div>
	</body>
</HTML>
