<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="ww" uri="webwork"%>
<%@ taglib uri="extremecomponents" prefix="ec"%>
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
		<link rel="stylesheet" href="resources/css/extremecomponents.css"
			type="text/css" />
		<title>密码重置与账户解锁</title>
		<style type="text/css">
			#top{margin: 0; padding:3px 5px; height: 24px; width:1200px; line-height: 24px; background-image: url("images/b.png"); color: white;}
		</style>
		<script language="JavaScript">
			$(document).ready(function(){
				$("#groupTwoSpan").css("visibility","hidden");
				
				$("[type='checkbox']").click(function(){
				var values = "";
				$(":checkbox[checked]").each(function () {
					if($(this).attr("id")!="allSelect"){
						values += $(this).val() + "||";
					}
            	});
            	var length;
            	length = values.length;
            	values = values.substring(0, length-2);
            	$("#strUsers").val(values);
				});
				
				$("#allSelect").click(function(){
					var selected = $(this).attr("checked");
					if(selected){
						$("[type='checkbox']").attr("checked",true);
						var values = "";
						$(":checkbox[checked]").each(function () {
							if($(this).attr("id")!="allSelect"){
								values += $(this).val() + "||";
							}
            			});
            			var length;
            			length = values.length;
            			values = values.substring(0, length-2);
            			$("#strUsers").val(values);
					}
					else{
						$("[type='checkbox']").attr("checked",false);
						$("#strUsers").val("");
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
						if(groupTwoId=="groupTwo"){
							document.pwResetForm.groupTwo.options.add(newOption);
						}
					}
				});	
			}
			function formsubmit(){
				document.all("pwResetForm").action = "pwResetGetUserList.action";
				document.all("pwResetForm").submit();
			}
			function batchPwReset(){
				var userInfos = $("#strUsers").val();
				window.location.href = "resetUserPw.action?strUsers="+userInfos;
			}
			function getSelectedInfo(){
				
			}
		</script>
	</head>
	<body>
		<div id="body">
		<div id="top">
	    		<span style="margin-left: 10px;">密码重置与账户解锁:</span>
		    </div>
		<div>
			<input type="button" value="排名参数设置" onclick="changeTab('0')">
			<input type="button" value="学生信息导入" onclick="changeTab('1')">
			<input type="button" value="密码重置与账户解锁" onclick="changeTab('2')">
			<input type="button" value="交易员账户管理" onclick="changeTab('3')">
		</div>
		<form name="pwResetForm" action="">
				<div>
					组别：
					<select id="groupType" name="groupType" onChange="changeGroupType('groupType','groupTwoSpan')">
						<option value="O">一级组</option>
						<option value="T">二级组</option>
					</select>
				</div>
				<div>
					<span>
					一级组
					<select id="groupOne" name="paramModel.groupOne" onChange="genGroupTwo('groupOne','groupTwo')">
						<option value=""></option>
						<ww:iterator value="#request.groupOneList">
							<option value='<ww:property value="groupId"/>'
							<ww:if test="#request.paramModel.groupOne==groupId">selected</ww:if>
							><ww:property value="groupId"/></option>
						</ww:iterator>
					</select>
					</span>
					<span id="groupTwoSpan">
					二级组
					<select id="groupTwo" name="paramModel.groupTwo">
					</select>
					</span>
					<span>
						交易员
						<input type="text" size="10" name="paramModel.userNum" id="userNum">
					</span>
					<input id="button" type="button" onclick="formsubmit('groupType','groupOne','groupTwo','pwResetForm')" value="搜  索" />
				</div>
				<input type="hidden" id="strUsers" name="strUsers">
			</form>
			<ww:if test="#request.userList != null">
			<ec:table items="userList" var="userModel" action="pwResetGetUserList.action"
				border="" cellpadding="0" cellspacing="0" style="eXtremeTable" > 
				<ec:row>
					<ec:column property="selectedBox" title=" " width="100"
						headerStyle="text-align:center" style="text-align:center"/>
					<ec:column property="userId" title="交易员号" width="100"
						headerStyle="text-align:center" style="text-align:left" viewsAllowed="html" >
					</ec:column>
					<ec:column property="name" title="交易员名称" width="160"
						headerStyle="text-align:center" style="text-align:left"/> 
					<ec:column property="statusDescr" title="账户状态" width="100"
						headerStyle="text-align:center" style="text-align:left"/>
					<ec:column property="pwResetOper" title="密码重置" width="100"
						headerStyle="text-align:center" style="text-align:center"/>
					<ec:column property="unlockOper" title="账户解锁" width="100"
						headerStyle="text-align:center" style="text-align:center"/>
				</ec:row>
			</ec:table>
			<hr> 
			<div> 
				<div style="width:100px;display:inline-block;float:left;text-align:center"><input type="checkbox" id="allSelect"/>批量处理</div>
				<div style="width:100px;display:inline-block;float:right;text-align:center"><input type="button" value="账户解锁"/></div>
				<div style="width:100px;display:inline-block;float:right;text-align:center"><input type="button" onclick="batchPwReset()" value="密码重置"/></div>
			</div> 
			</ww:if>
		</div>
	</body>
</HTML>
