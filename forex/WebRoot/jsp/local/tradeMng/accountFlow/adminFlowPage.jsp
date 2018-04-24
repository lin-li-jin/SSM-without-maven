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
	<style>
		#all{ margin: 0; padding:0; width: 1203px; height: auto;}
		#top{margin: 0; padding:3px 5px; height: 24px; width:1200px; line-height: 24px; background-image: url("images/b.png"); color: white;}
		#top2{margin: 0; padding:3px 5px; height: 24px; width:840px; line-height: 24px; text-align: center;}
		#button{margin: 0; padding: 0; height: 30px; width: 840px;border-bottom:1px solid black; margin-top: 10px}
		.modules{margin: 0; padding: 0; height:auto; width:840px;margin-top: 10px}
		.modules span {font-size: 14px; margin-left: 15px;}
		#ectable{margin: 0; padding: 0; width: 1200px; height: auto; }
	</style>
	<script type="text/javascript">
		$(document).ready(function(){
			changeGroupType();		
		});
		function changeGroupType(){
			var groupType = $("#groupType").val();
				if(groupType=="O"){
					getStudentByClass();
					$("#groupOneSpan").css("display","none");
					$("#groupTwoSpan").css("display","none");
					$("#userNumSpan").css("display","none");
					$("#button2").css("display","none");
					
					$("#queryAllClass").css("display","inline");
					$("#button1").css("display","inline");
				}
				else if(groupType=="T"){
					getGroupTwo();
					
					$("#groupOneSpan").css("display","inline");
					$("#groupTwoSpan").css("display","inline");
					$("#userNumSpan").css("display","inline");
					$("#button2").css("display","inline");
					
					$("#queryAllClass").css("display","none");
					$("#button1").css("display","none");
					
				}
				else{
				}
				
		}
		function getGroupTwo(){
			var groupOne = $("#groupOne"+" option:selected").val();
			$("#groupTwo").empty();//清空前一类型的选项,重新选一级组要把原来的二级组清空
				$.post("getGroupTwos.action",{groupOne:groupOne},
			function(dat){
				var groupTwoList = new Array();
				groupTwoList = dat.split("$$");
				for(var i=0;i<groupTwoList.length;i++){
					var newOption;
					if(i==-1){
						newOption = new Option("","");
					}else{
						newOption = new Option(groupTwoList[i],groupTwoList[i]);
					}
					document.cashForm.groupTwo.options.add(newOption);
					//START 这里是为了在查询完跳转后二级组仍然能被选中
					if('<ww:property value="#request.tradeFlowModel.groupTwo" />' != ""){
						if('<ww:property value="#request.tradeFlowModel.groupTwo" />' == (groupTwoList[i])){
							$("#groupTwo").val(groupTwoList[i]);
							getStudent();
						}
					}
					//END 这里是为了在查询完跳转后二级组仍然能被选中
				}
			});	
		}
		
		function getStudent(){
			var groupTwo = $("#groupTwo"+" option:selected").val();
			$("#userNum").empty();//清空前一类型的选项
				$.post("getStudents.action",{groupTwo:groupTwo},
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
					document.cashForm.userNum.options.add(newOption);
					//START 这里是为了在查询完跳转后学号还能被选中
					if('<ww:property value="#request.tradeFlowModel.userNumber" />' != ""){
						if('<ww:property value="#request.tradeFlowModel.userNumber" />' == (studentList[i])){
							$("#userNum").val(studentList[i]);
						}
					}
					//end 这里是为了在查询完跳转后学号还能被选中
				}
			});	

		}
		
		
		function getStudentByClass(){
			var classNo = $("#classNo"+" option:selected").val();
			$("#userNum1").empty();//清空前一类型的选项
				$.post("getStudentsByGroupOne.action",{classNo:classNo},
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
					document.cashForm.userNum1.options.add(newOption);
					//START 这里是为了在查询完跳转后学号仍然被选中
					if('<ww:property value="#request.tradeFlowModel.userNum" />' != ""){
						if('<ww:property value="#request.tradeFlowModel.userNum" />' == (studentList[i])){
							$("#userNum1").val(studentList[i]);
						}
					}
					//end 这里是为了在查询完跳转后学号仍然被选中
				}
			});	
		}
		
		function tijiao(){
			document.cashForm.action = "tradeRecordOne.action";
			document.cashForm.submit();
		}
		
		function tijiao1(){
			document.cashForm.action = "tradeRecordTwo.action";
			document.cashForm.submit();
		}
	</script>
  </head>
  
  <body>
	  	<div id="all">
	  		<div id="top">
	    		<span style="margin-left: 10px;">账户管理:</span>
		    </div>
		    <div id="button">
		    	<input type="button" value="账户交易流水统计" style="margin-left: 15px; height: 25px" onclick="window.location.href ='accountFlowPageInit.action'">
		    	<input type="button" value="账户损益情况" style="margin-left: 50px; width: 150px; height: 25px" onclick="window.location.href ='accountStatementPageInit.action'">
		    	<!-- <input type="button" value="账户排名管理" style="margin-left: 50px; width: 150px; height: 25px" onclick="window.location.href='accountNumberPageInit.action'"> -->
		    </div>
		    <div class="modules">
		    	<form name="cashForm" action="" method="post">
		    		<span>交易类型:</span>
		    		<select id="tradeType" name="tradeFlowModel.tradeType" style="width:120px;">
			    		<option value="C" <ww:if test='#request.tradeFlowModel.tradeType=="C"'>selected</ww:if>>人民币外币交易</option>
			    		<option value="W" <ww:if test='#request.tradeFlowModel.tradeType=="W"'>selected</ww:if>>外币对交易</option>
			    		<option value="B" <ww:if test='#request.tradeFlowModel.tradeType=="B"'>selected</ww:if>>保证金交易</option>
				    </select>
				    &nbsp;
		    		
		    		<span>查询方式:</span>
		    		<select id="groupType" name="tradeFlowModel.groupType" onclick="changeGroupType();">
			    		<option value="O" <ww:if test='#request.tradeFlowModel.groupType=="O"'>selected</ww:if>>查询整个班级学生</option>
			    		<option value="T" <ww:if test='#request.tradeFlowModel.groupType=="T"'>selected</ww:if>>查询班级某个分组</option>
				    </select>
				    <br><br>
				    <span id="queryAllClass">
				    	班级编号:
				    	<select id="classNo" name="tradeFlowModel.classNo" onclick="getStudentByClass()" style="width:120px;">
				    		<!--  <option value=""></option>-->
							<ww:iterator value="#request.groupOneList">
							<option value='<ww:property value="groupId"/>' <ww:if test="#request.tradeFlowModel.classNo==groupId">selected</ww:if>><ww:property value="groupId"/></option>
							</ww:iterator>
				    	</select>
				    	&nbsp;&nbsp;&nbsp;&nbsp;
				    	交易员编号:
				    	<select id="userNum1" name="tradeFlowModel.userNum" style="width: 115px;">
				    		
					    </select>
				    </span>
				    <input id="button1" type="button" value="搜索" style="margin-left: 80px;width:100px" onclick="tijiao()">   

				    <span id="groupOneSpan">一级组:&nbsp;&nbsp;
				    <select id="groupOne" name="tradeFlowModel.groupOne" onclick="getGroupTwo()" style="width:120px;">
						<!--  <option value=""></option>-->
						<ww:iterator value="#request.groupOneList">
							<option value='<ww:property value="groupId"/>' <ww:if test="#request.tradeFlowModel.groupOne==groupId">selected</ww:if>><ww:property value="groupId"/></option>
						</ww:iterator>
				    </select>&nbsp;
				    </span>
				    
				    <span id="groupTwoSpan">&nbsp;&nbsp;二级组:&nbsp;&nbsp;
					    <select id="groupTwo" name="tradeFlowModel.groupTwo" onclick="getStudent()" style="width: 115px;">
					   </select>&nbsp;
				    </span>
				    <span id="userNumSpan">交易员代码:
					    <select id="userNum" name="tradeFlowModel.userNumber" style="width: 100px;">
					    </select>
				    </span>
				    <input id="button2" type="button" value="搜索" style="margin-left: 80px;width:100px" onclick="tijiao1()">
			    </form>
		    </div>
		    
		   <div id="ectable">
		   		<ww:if test="#request.listB!=null">
		   			<div id="top2">
	    				<span style="text-align: center; color: red; font-size: 16px; font-weight: bolder;">保证金交易</span>
		    		</div>
		   			<ec:table items="listB" var="c" action=""
					border="" cellpadding="0" cellspacing="0" style="eXtremeTable" width="860px">
							<ec:row>
								<ec:column property="userNumLink" title="交易员" width="70"
									headerStyle="text-align:center" style="text-align:left" viewsAllowed="html" >
									<script type="text/javascript">
									document.write('<a href="getTradeListByUserNum.action?userNum='+'${c.userNum }'+'">'+'${c.userNum}'+'</a>');
									</script>
								</ec:column>
								<ec:column property="marginSpotQty" title="远期交易次数" width="100"
									headerStyle="text-align:center" style="text-align:left" viewsAllowed="html" >
								</ec:column>
								<ec:column property="marginSpotAmt" title="远期交易金额" width="160"
									headerStyle="text-align:center" style="text-align:right;"/>
								<ec:column property="marginOptionQty" title="期权交易次数" width="100"
									headerStyle="tex t-align:center" style="text-align:left"/>
								<ec:column property="marginOptionAmt" title="期权交易金额" width="100"
									headerStyle="text-align:center" style="text-align:right;"/>
							</ec:row>
					</ec:table>	
		   		</ww:if>
		   		
		   		<ww:if test="#request.listC!=null">
		   			<div id="top2">
	    				<span style="text-align: center; color: red; font-size: 16px; font-weight: bolder;">人民币外币交易</span>
		    		</div>
		   			<ec:table items="listC" var="c" action=""
					border="" cellpadding="0" cellspacing="0" style="eXtremeTable" >
							<ec:row>
								
								<ec:column property="userNumLink" title="交易员" width="70"
									headerStyle="text-align:center" style="text-align:left" viewsAllowed="html" >
									<script type="text/javascript">
									document.write('<a href="getTradeListByUserNum.action?userNum='+'${c.userNum }'+'">'+'${c.userNum}'+'</a>');
									</script>
								</ec:column>
								
								<ec:column property="oneClickQty" title="一口价交易量" 
									headerStyle="text-align:center" style="text-align:left" />
								<ec:column property="oneClickAmt" title="一口价交易金额" 
									headerStyle="text-align:center" style="text-align:right;"/>
								<ec:column property="stopLossQty" title="止损交易量" 
									headerStyle="tex t-align:center" style="text-align:left"/>
								<ec:column property="stopLossAmt" title="止损交易金额" 
									headerStyle="text-align:center" style="text-align:right;"/>
								
								<ec:column property="takeProfitQty" title="止盈交易量" 
									headerStyle="text-align:center" style="text-align:left"/>
								<ec:column property="takeProfitAmt" title="止盈交易金额" 
									headerStyle="text-align:center" style="text-align:right;"/>
								<ec:column property="ocoQty" title="oco交易量" 
									headerStyle="text-align:center" style="text-align:left"/>
								<ec:column property="ocoAmt" title="oco交易金额" 
									headerStyle="text-align:center" style="text-align:right;"/>
								<ec:column property="marketBreakoutQty" title="mkb交易量" 
									headerStyle="text-align:center" style="text-align:left"/>
								<ec:column property="marketBreakoutAmt" title="mkb交易金额" 
									headerStyle="text-align:center" style="text-align:right;"/>
								<ec:column property="otcSpotQty" title="即期询价交易量" 
									headerStyle="text-align:center" style="text-align:left"/>
								<ec:column property="otcSpotAmt" title="即期询价交易金额" 
									headerStyle="text-align:center" style="text-align:right;"/>
								<ec:column property="otcForwartQty" title="远期询价交易量" 
									headerStyle="text-align:center" style="text-align:left"/>
								<ec:column property="otcForwartAmt" title="远期询价交易金额" 
									headerStyle="text-align:center" style="text-align:right;"/>
								<ec:column property="otcSwapQty" title="掉期询价交易量" 
									headerStyle="text-align:center" style="text-align:left"/>
								<ec:column property="otcSwapAmt" title="掉期询价交易金额" 
									headerStyle="text-align:center" style="text-align:right;"/>
							</ec:row>
					</ec:table>	
		   		</ww:if>
		   		
		   		
		   		<ww:if test="#request.listW!=null">
		   			<div id="top2">
	    				<span style="text-align: center; color: red; font-size: 16px; font-weight: bolder;">外币对交易</span>
		    		</div>
		   			<ec:table items="listW" var="c" action=""
					border="" cellpadding="0" cellspacing="0" style="eXtremeTable" >
							<ec:row>
								<ec:column property="userNumLink" title="交易员" width="70"
									headerStyle="text-align:center" style="text-align:left" viewsAllowed="html" >
									<script type="text/javascript">
									document.write('<a href="getTradeListByUserNum.action?userNum='+'${c.userNum }'+'">'+'${c.userNum}'+'</a>');
									</script>
								</ec:column>
								<ec:column property="oneClickQty" title="一口价交易量" 
									headerStyle="text-align:center" style="text-align:left" />
								<ec:column property="oneClickAmt" title="一口价交易金额" 
									headerStyle="text-align:center" style="text-align:right;"/>
								<ec:column property="stopLossQty" title="止损交易量" 
									headerStyle="tex t-align:center" style="text-align:left"/>
								<ec:column property="stopLossAmt" title="止损交易金额" 
									headerStyle="text-align:center" style="text-align:right;"/>
								
								<ec:column property="takeProfitQty" title="止盈交易量" 
									headerStyle="text-align:center" style="text-align:left"/>
								<ec:column property="takeProfitAmt" title="止盈交易金额" 
									headerStyle="text-align:center" style="text-align:right;"/>
								<ec:column property="ocoQty" title="oco交易量" 
									headerStyle="text-align:center" style="text-align:left"/>
								<ec:column property="ocoAmt" title="oco交易金额" 
									headerStyle="text-align:center" style="text-align:right;"/>
								<ec:column property="marketBreakoutQty" title="mkb交易量" 
									headerStyle="text-align:center" style="text-align:left"/>
								<ec:column property="marketBreakoutAmt" title="mkb交易金额" 
									headerStyle="text-align:center" style="text-align:right;"/>
								<ec:column property="otcSpotQty" title="即期询价交易量" 
									headerStyle="text-align:center" style="text-align:left"/>
								<ec:column property="otcSpotAmt" title="即期询价交易金额" 
									headerStyle="text-align:center" style="text-align:right;"/>
								<ec:column property="otcForwartQty" title="远期询价交易量" 
									headerStyle="text-align:center" style="text-align:left"/>
								<ec:column property="otcForwartAmt" title="远期询价交易金额" 
									headerStyle="text-align:center" style="text-align:right;"/>
								<ec:column property="otcSwapQty" title="掉期询价交易量" 
									headerStyle="text-align:center" style="text-align:left"/>
								<ec:column property="otcSwapAmt" title="掉期询价交易金额" 
									headerStyle="text-align:center" style="text-align:right;"/>
							</ec:row>
					</ec:table>	
		   		</ww:if>
    		</div>
	  	</div>
  </body>
</html>
