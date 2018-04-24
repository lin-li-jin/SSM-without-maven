<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib prefix="ww" uri="webwork"%>
<%@ taglib uri="extremecomponents" prefix="ec"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
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
		<link rel="stylesheet" href="resources/css/extremecomponents.css"
			type="text/css" />
		<script src="resources/ponyjs/jquery.js" type="text/javascript"></script>
    <title>My JSP 'adminFlowPage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="resources/css/extremecomponents.css"	type="text/css" />
	<script src="resources/ponyjs/jquery.js" type="text/javascript"></script>
	<style>
		#all{ margin: 0; padding:0; width: 1003px; height: auto;}
		#top{margin: 0; padding:3px 5px; height: 24px; width:840px; line-height: 24px; background-image: url("images/b.png"); color: white;}
		#button{margin: 0; padding: 0; height: 30px; width: 840px;border-bottom:1px solid black; margin-top: 10px}
		.modules{margin: 0; padding: 0; height:30px; width:840px;margin-top: 10px}
		.modules label {  font-size: 16px; color: black; font-weight: bolder;}
		.modules span {font-size: 14px; margin-left: 15px;}
		#ectable{margin: 40px 0px 0px 0px; padding: 0px; width: 850px; height: auto; }
	</style>
	<script type="text/javascript" language="javascript">
	$(function(){
		$('input:radio').click(function () {
			this.blur();   
			this.focus(); 
		});
		getGroupTwo();
	});
	function getGroupTwo(){
		var groupOne = $("#groupOne"+" option:selected").val();
		$("#groupTwo").empty();//清空前一类型的选项
			$.post("getGroupTwoList.action",{groupOne:groupOne},
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
				document.form1.groupTwo.options.add(newOption);
				if('<ww:property value="#request.groupTwo" />' != ""){
					if('<ww:property value="#request.groupTwo" />' == (groupTwoList[i])){
						$("#groupTwo").val(groupTwoList[i]);
						getStudents();
					}
				}
			}
		});
		if('<ww:property value="#request.groupTwo" />' == ""){
			getStudentByGroupOne();
		}
	}
	function getStudents(){
		var groupTwo = $("#groupTwo"+" option:selected").val();
		if(groupTwo!=""&&groupTwo!=null){
			$("#userNum").empty();//清空前一类型的选项
			$("#rankingRange").empty();
			$.post("getStudentList.action",{groupTwo:groupTwo},
			function(dat1){
				var studentList = new Array();
				studentList = dat1.split("$$");
				for(var i=-1;i<studentList.length;i++){
					var newOption;
					if(i==-1){
						newOption = new Option("","");
					}else{
						newOption = new Option(studentList[i],studentList[i]);
					}
					document.form1.userNum.options.add(newOption);
					if('<ww:property value="#request.userNumber" />' != ""){
						if('<ww:property value="#request.userNumber" />' == (studentList[i])){
							$("#userNum").val(studentList[i]);
							getRankingRange();
						}
					}
				}
			});
		}else{
			getStudentByGroupOne();
		}
	}
	function getStudentByGroupOne(){
		var groupOne = $("#groupOne"+" option:selected").val();
		$("#userNum").empty();//清空前一类型的选项
		$("#rankingRange").empty();
			$.post("getStudentListByGroupOne.action",{groupOne:groupOne},
		function(dat2){
			var studentList = new Array();
			studentList = dat2.split("$$");
			for(var i=-1;i<studentList.length;i++){
				var newOption;
				if(i==-1){
					newOption = new Option("","");
				}else{
					newOption = new Option(studentList[i],studentList[i]);
				}
				document.form1.userNum.options.add(newOption);
			}
		});	
	}
	function getRankingRange(){
		var userNum = $("#userNum"+" option:selected").val();
		if(userNum==""||userNum==null){
			$("#rankingRange").empty();
		}else{
			$("#rankingRange").empty();//清空前一类型的选项
			var newOption1 =new Option("一级组内","One");
			var newOption2 =new Option("二级组内","Two");
			document.form1.rankingRange.options.add(newOption1);
			document.form1.rankingRange.options.add(newOption2);
			if('<ww:property value="#request.rankingRange" />' != ""){
				if('<ww:property value="#request.rankingRange" />' == "One"){
					$("#rankingRange").val("一级组内");
				}else{
					$("#rankingRange").val("二级组内");
				}
			}
		}
	}
	</script>
  </head>
  
  <body>
	  	<div id="all">
	  		<div id="top">
	    		<span style="margin-left: 10px;">admin账户实时排名:</span>
		    </div>
		    <div id="button">
		    	<input type="button" value="账户交易流水统计" style="margin-left: 15px; height: 25px" onclick="window.location.href='accountFlowPageInit.action'">
		    	<input type="button" value="账户损益情况" style="margin-left: 50px; width: 150px; height: 25px" onclick="window.location.href ='accountStatementPageInit.action'">
		    	<!-- <input type="button" value="账户排名管理" style="margin-left: 50px; width: 150px; height: 25px" onclick="window.location.href='accountNumberPageInit.action'"> -->
		    </div>
		    <div class="modules">
		    	<input type="radio" name="rad" style="margin-left: 15px;" checked="checked">账户实时排名
		    	<input type="radio" name="rad" style="margin-left: 10px;" onchange="window.location.href='accountSynthesizePageInit.action'">账户综合排名
		    </div>
		    <div class="modules">
				<form name="form1" action="accountNumberCurrentSearch.action" method="post">
			    	<span>交易类型:</span>
			    	<select name="tradeType" style="margin-left: 5px;width:115px">
				    	<ww:iterator value="#request.tradeTypeList">
				    		<option value="<ww:property value='id.codeVal' />"<ww:if test="#request.tradeType == id.codeVal">selected</ww:if>>
				    			<ww:property value="descr"/>
				    		</option>
				    	</ww:iterator>
				    </select>
				    <span>一级组:</span>
				    <select id="groupOne" style="margin-left: 5px;width:100px" name="groupOne" onChange="getGroupTwo()">
						<ww:iterator value="#request.groupOneList">
							<option value="<ww:property value='groupId'/>"<ww:if test="#request.groupOne == groupId">selected</ww:if>>
							<ww:property value="groupId"/>
							</option>
						</ww:iterator>
				    </select>
				    <span>二级组:</span>
				    <select id="groupTwo" style="margin-left: 5px;width:100px" name="groupTwo" onChange="getStudents()">
					  </select>
				    <span>交易员编号:</span>
				    <select id="userNum" style="margin-left: 5px;width:100px" name="userNumber" onchange="getRankingRange()">
					  </select>&nbsp;<br><br>
				    <span>排名方式:</span>
				    <select name="rankingType" style="margin-left: 6px;width:115px">
				    	<option value="up" <ww:if test="#request.rankingType == 'up'">selected</ww:if>>升序</option>
				    	<option value="down" <ww:if test="#request.rankingType == 'down'">selected</ww:if>>降序</option>
				    </select>
				    <span>排名范围:</span>
				    <select id="rankingRange" name="rankingRange" style="width:80px">
				    </select>&nbsp;
				    
				    <input type="submit" value="搜索" style="margin-left: 20px;width:100px">
			    </form>
		    </div>
		    <div id="ectable">
		   		<ec:table items="accountNumberList" var="c" action=""
						border="" cellpadding="0" cellspacing="0" style="eXtremeTable" >
						<ec:export view="xls" fileName="交易列表.xls" imageName="xls"
						viewResolver="xls" tooltip="导出 Excel" />
						<ec:row>
							<ec:column property="userNum" title="交易员编号" width="100"
								headerStyle="text-align:center" style="text-align:left" viewsAllowed="html" >
							</ec:column>
							<ec:column property="name" title="交易员名称" width="100"
								headerStyle="text-align:center" style="text-align:left" viewsAllowed="html" >
							</ec:column>
							<ec:column property="tradeType" title="账户类型" width="100"
								headerStyle="text-align:center" style="text-align:left" viewsAllowed="html" >
							</ec:column>
							<ec:column property="ccy" title="账户币种" width="100"
								headerStyle="text-align:center" style="text-align:left"/>
							<ec:column property="tradeAmountRank" title="交易金额排名" width="100"
								headerStyle="tex t-align:center" style="text-align:left"/>
							<ec:column property="tradeNumberRank" title="交易次数排名" width="100"
								headerStyle="text-align:center" style="text-align:left"/>
							<ec:column property="rateRank" title="盈亏率排名" width="100"
								headerStyle="text-align:center" style="text-align:left"/>
							<ec:column property="accountRank" title="账户实时排名" width="100"
								headerStyle="text-align:center" style="text-align:left"/> 
					</ec:row>
				</ec:table>
    		</div>
	  	</div>
  </body>
</html>
