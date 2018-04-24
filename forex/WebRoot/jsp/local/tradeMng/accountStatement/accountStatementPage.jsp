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
		#all{ margin: 0; padding:0; width: 900px; height: auto;}
		#top{margin: 0; padding:3px 5px; height: 24px; width:1200px; line-height: 24px; background-image: url("images/b.png"); color: white;}
		#top2{margin: 0; padding:3px 5px; height: 24px; width:840px; line-height: 24px; text-align: center;}
		#button{margin: 0; padding: 0; height: 30px; width: 840px;border-bottom:1px solid black; margin-top: 10px}
		.modules{margin: 0; padding: 0; height:auto; width:840px;margin-top: 10px}
		.modules span {font-size: 14px; margin-left: 15px;}
		#ectable{margin: 0; padding: 0; width: 1000px; height: auto; }
	</style>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#groupTwoSpanA").css("visibility","hidden");
		});
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
						document.cashForm.groupTwoA.options.add(newOption);
					}
				}
			});	
		}
		function tijiao(){
			//document.cashForm.action = "searchAccount.action";
			document.cashForm.submit();
		}
		
		
	</script>
  </head>
  
  <body>
	  	<div id="all">
	  		<div id="top">
	    		<span style="margin-left: 10px;">账户损益情况:</span>
		    </div>
		    <div id="button">
		    	<input type="button" value="账户交易流水统计" style="margin-left: 15px; height: 25px" onclick="window.location.href ='accountFlowPageInit.action'">
		    	<input type="button" value="账户损益情况" style="margin-left: 50px; width: 150px; height: 25px" onclick="window.location.href ='accountStatementPageInit.action'">
		    	<input type="button" value="账户排名管理" style="margin-left: 50px; width: 150px; height: 25px" onclick="window.location.href='accountNumberPageInit.action'">
		    </div>
		    <div class="modules">
		    	<form name="cashForm" action="accoutStatementQuery.action" method="post">
		    		<span>账户类别:</span>
		    		<select id="tradeType" name="tradeFlowModel.tradeType" style="width:120px;">
		    			<option value="ALL">所有账户</option>
			    		<option value="C" >人民币账户</option>
			    		<option value="W" >外币账户</option>
			    		<option value="B" >保证金账户</option>
				    </select>
				    &nbsp;
				    <span>交易员号:</span>
		    		<select id="userNumber" name="tradeFlowModel.userNumber" style="width:120px;">
		    			<option value="">所有交易员</option>
		    			<ww:iterator value="#request.usersList">
		    				<option value="<ww:property value='userNum'/>"><ww:property value="userNum"/></option>
		    			</ww:iterator>
				    </select>
				    &nbsp;
		    		
		    		<%--<span>组别:</span>
		    		<select id="groupType" name="tradeFlowModel.groupOne">
			    		<option value="1001" >1001</option>
			    		<option value="11154" >11154</option>
				    </select>
				    --%>
				    <br><br>
				<div>
					<span>组别:</span>
					<select id="groupTypeA" name="tradeFlowModel.groupType" onChange="changeGroupType('groupTypeA','groupTwoSpanA')">
						<option value="O">一级组</option>
						<option value="T">二级组</option>
					</select>
					<span>
					一级组
					<select id="groupOneA" name="tradeFlowModel.groupOne" onChange="genGroupTwo('groupOneA','groupTwoA')">
						<option value=""></option>
						<ww:iterator value="#request.groupOneList">
							<option value='<ww:property value="groupId"/>'><ww:property value="groupId"/></option>
						</ww:iterator>
					</select>
					</span>
					<span id="groupTwoSpanA">
					二级组
					<select id="groupTwoA" name="tradeFlowModel.groupTwo">
					</select>
					</span>
					<input id="button1" type="button" value="搜索" style="margin-left: 80px;width:100px" onclick="tijiao()">   
				</div>
				    
				
			    </form>
			    
		    </div>
		    
		   <div id="ectable">
		   		<ww:if test="#request.accountModelList != null">
			<ec:table items="accountModelList" var="accountModel" action="accountStatementPageInit.action">
				<ec:export view="xls" fileName="账户损益情况列表.xls" imageName="xls"
					viewResolver="xls" tooltip="导出 Excel" />

				<ec:row>
					<ec:column property="account" title="交易号"
						headerStyle="text-align:center"
						style="text-align:left;width:100px" />
					<ec:column property="stringAccountType" title="账户类别"
						headerStyle="text-align:center"
						style="text-align:left;width:100px" /> 
					
					<%-- <ec:column property="xxxxx" title="各账户金额"
						headerStyle="text-align:center"
						style="text-align:left;width:100px" >
						<ww:if test="#request.accountC==1">c=${accountModel.tradeAccountC.exactAccountBalance}<br/></ww:if>
						<ww:if test="#request.accountW==1">w=${accountModel.tradeAccountW.exactAccountBalance}<br/></ww:if>
						<ww:if test="#request.accountB==1">b=${accountModel.tradeAccountB.exactAccountBalance}</ww:if>
					</ec:column> --%>
					<ec:column property="stringExactAccountBalance" title="各账户金额"
						headerStyle="text-align:center"
						style="text-align:left;width:100px" />
					<%-- <ec:column property="xxxxxx" title="各账户初始金额"
						headerStyle="text-align:center"
						style="text-align:left;width:100px" >
						<ww:if test="#request.accountC==1">c=${accountModel.tradeAccountC.exactInitBalance}<br/></ww:if>
						<ww:if test="#request.accountW==1">w=${accountModel.tradeAccountW.exactInitBalance}<br/></ww:if>
						<ww:if test="#request.accountB==1">b=${accountModel.tradeAccountB.exactInitBalance}</ww:if>
					</ec:column> --%>
					<ec:column property="stringExactInitBalance" title="各账户初始金额"
						headerStyle="text-align:center"
						style="text-align:left;width:100px" />
					
						<%-- <ec:column property="xxxx" title="各账户盈亏金额"
						headerStyle="text-align:center"
						style="text-align:left;width:150px" >
						<ww:if test="#request.accountC==1">c=${accountModel.tradeAccountC.stringProfitAndLossBalance}<br/></ww:if>
						<ww:if test="#request.accountW==1">w=${accountModel.tradeAccountW.stringProfitAndLossBalance}<br/></ww:if>
						<ww:if test="#request.accountB==1">b=${accountModel.tradeAccountB.stringProfitAndLossBalance}</ww:if>
					</ec:column> --%>
					<ec:column property="stringSelfProfitAndLossBalance" title="各账户盈亏金额"
						headerStyle="text-align:center"
						style="text-align:left;width:100px" />
					
				
				                    	
					<ec:column property="stringSelfProfitAndLoss" title="各账户盈亏率"
						headerStyle="text-align:center"
						style="text-align:left;width:100px" />
					
					<%-- <ec:column property="tempAccountBalance" title="所有账户总金额"
						headerStyle="text-align:center"
						style="text-align:left;width:100px" />
					<ec:column property="tempAccountInitBalance" title="所有账户初始总金额"
						headerStyle="text-align:center"
						style="text-align:left;width:80px" />	
					<ec:column property="stringSumProfitAndLoss" title="总盈亏率"
						headerStyle="text-align:center"
						style="text-align:left;width:100px" /> --%>
		
			</ec:row>
			</ec:table>
			<br />
			<br />
		</ww:if>
		   		
		   		
		   	
    		</div>
	  	</div>
  </body>
</html>