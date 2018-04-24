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
		<title>交易员账户管理</title>
		<style type="text/css">
			#top{margin: 0; padding:3px 5px; height: 24px; width:1200px; line-height: 24px; background-image: url("images/b.png"); color: white;}
		</style>
		<script language="JavaScript">
			$(document).ready(function(){
				$("#groupTwoSpanA").css("visibility","hidden");
				$("#groupTwoSpanO").css("visibility","hidden");
				$("#groupTwoSpanC").css("visibility","hidden");
				
				$("#anaGroupDiv").css("visibility","hidden");
				$("input[name='paramModel.analogueType']").click(function(){
					var analogueType = $("input[name='paramModel.analogueType']:checked").val();
					if(analogueType=="1"){
						$("#anaGroupDiv").css("visibility","visible");
					}else{
						$("#anaGroupDiv").css("visibility","hidden");
					}
				});
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
							document.anaMngForm.groupTwoA.options.add(newOption);
						}
						if(groupTwoId=="groupTwoO"){
							document.anaMngForm.groupTwoO.options.add(newOption);
						}
						if(groupTwoId=="groupTwoC"){
							document.accParamSerForm.groupTwoC.options.add(newOption);
						}
					}
				});	
			}
			
			function formSubmit(){
				var analogueType = $("input[name='paramModel.analogueType']:checked").val();
				//检查发起组
				var initGroupisOK = groupCheck('groupTypeA','groupOneA','groupTwoA','initGroup');
				if(initGroupisOK == false){
					alert("请选择相应组别！");
					return;
				}
				if(analogueType == "0"){//组内成员互为对手方
					document.all("anaMngForm").action = "setAnalogue.action";
					document.all("anaMngForm").submit();
				}
				else if(analogueType == "1"){//与其他成员互为对手方
					//检查对手方组
					var anaGroupisOK = groupCheck('groupTypeO','groupOneO','groupTwoO','anaGroup');
					if(anaGroupisOK == false){
						alert("请选择相应组别！");
						return;
					}
					
					//检查组别是否相同
					if(($("#initGroup").val())==($("#anaGroup").val())){
						alert("请选择不同组别！");
					}
					document.all("anaMngForm").action = "setAnalogue.action";
					document.all("anaMngForm").submit();
				}
				else{
					alert("请选择成为对手方方式！");
				}
			}
			
			function groupCheck(groupTypeId,groupOneId,groupTwoId,groupNumId)
			{
				var groupType = $("#"+groupTypeId).val();
				var groupNum = "";
				if(groupType == "O"){
					groupNum = $("#"+groupOneId).val();
					$("#"+groupTwoId).val("");
				}
				if(groupType == "T"){
					groupNum = $("#"+groupTwoId).val();
				}
				if(groupNum==""||groupNum==null){
					$("#"+groupNumId).val("");
					return false;
				}
				else{
					$("#"+groupNumId).val(groupNum);
					return true;
				}
			}
			
			function getAnaGroup(){
				var groupNum="";
				var groupTypeC=$("#groupTypeC").val();
				if(groupTypeC=="O"){
					groupNum=$("#groupOneC").val();
				}
				else if(groupTypeC=="T"){
					groupNum=$("#groupTwoC").val();
				}
				if(groupNum==""||groupNum==null){
					alert("请选择需要查询参数的组号！");
					return;
				}
				var action="getAnaGroup.action?groupNum="+groupNum;
				$.post(action,"",function(data){
					if(data=="-1"){
						$("#getAnaGroupInfo").empty();
						$("#getAnaGroupInfo").append("<div><br/>该组组内成员互为对手方</div>")
					}else{
						$("#getAnaGroupInfo").empty();
						$("#getAnaGroupInfo").append("<div><br/>该组与"+data+"组互为对手方</div>")
					}
				});
			}
		</script>
	</head>
	<body>
		<div id="body">
		<div id="top">
	    		<span style="margin-left: 10px;">交易员账户管理:</span>
		    </div>
		<div>
			<input type="button" value="排名参数设置" onclick="changeTab('0')">
			<input type="button" value="学生信息导入" onclick="changeTab('1')">
			<input type="button" value="密码重置与账户解锁" onclick="changeTab('2')">
			<input type="button" value="交易员账户管理" onclick="changeTab('3')">
		</div>
		<form name="anaMngForm" action="">
			<div>
				交易员对手方管理
			</div>
			<hr>
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
				</div>
			<div>
				<input type="radio" value="0" name="paramModel.analogueType"/>组内成员互为对手方
				<input type="radio" value="1" name="paramModel.analogueType"/>与其他成员互为对手方
			</div>
			<div id="anaGroupDiv">
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
				</div>
			</div>
				<input id="button" type="button" onClick="formSubmit()" value="保  存" />
				<input type="hidden" id="initGroup" name="paramModel.initGroup">
				<input type="hidden" id="anaGroup" name="paramModel.anaGroup">
		</form>
		<hr>
		<form name="accParamSerForm" action="">
				<div>
					组别：
					<select id="groupTypeC" name="groupTypeC" onChange="changeGroupType('groupTypeC','groupTwoSpanC')">
						<option value="O">一级组</option>
						<option value="T">二级组</option>
					</select>
				</div>
				<div>
					<span>
					一级组
					<select id="groupOneC" name="groupOneC" onChange="genGroupTwo('groupOneC','groupTwoC')">
						<option value=""></option>
						<ww:iterator value="#request.groupOneList">
							<option value='<ww:property value="groupId"/>'><ww:property value="groupId"/></option>
						</ww:iterator>
					</select>
					</span>
					<span id="groupTwoSpanC">
					二级组
					<select name="groupTwoC" id="groupTwoC">
					</select>
					</span>
					<input id="button" type="button" value="搜  索" onclick="getAnaGroup()"/>
				</div>
				<div id="getAnaGroupInfo"></div>
			</form>
		</div>
	</body>
</HTML>
