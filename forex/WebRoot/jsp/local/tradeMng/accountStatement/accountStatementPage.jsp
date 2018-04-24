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
			$("#"+groupTwoId).empty();//���ǰһ���͵�ѡ��
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
	    		<span style="margin-left: 10px;">�˻��������:</span>
		    </div>
		    <div id="button">
		    	<input type="button" value="�˻�������ˮͳ��" style="margin-left: 15px; height: 25px" onclick="window.location.href ='accountFlowPageInit.action'">
		    	<input type="button" value="�˻��������" style="margin-left: 50px; width: 150px; height: 25px" onclick="window.location.href ='accountStatementPageInit.action'">
		    	<input type="button" value="�˻���������" style="margin-left: 50px; width: 150px; height: 25px" onclick="window.location.href='accountNumberPageInit.action'">
		    </div>
		    <div class="modules">
		    	<form name="cashForm" action="accoutStatementQuery.action" method="post">
		    		<span>�˻����:</span>
		    		<select id="tradeType" name="tradeFlowModel.tradeType" style="width:120px;">
		    			<option value="ALL">�����˻�</option>
			    		<option value="C" >������˻�</option>
			    		<option value="W" >����˻�</option>
			    		<option value="B" >��֤���˻�</option>
				    </select>
				    &nbsp;
				    <span>����Ա��:</span>
		    		<select id="userNumber" name="tradeFlowModel.userNumber" style="width:120px;">
		    			<option value="">���н���Ա</option>
		    			<ww:iterator value="#request.usersList">
		    				<option value="<ww:property value='userNum'/>"><ww:property value="userNum"/></option>
		    			</ww:iterator>
				    </select>
				    &nbsp;
		    		
		    		<%--<span>���:</span>
		    		<select id="groupType" name="tradeFlowModel.groupOne">
			    		<option value="1001" >1001</option>
			    		<option value="11154" >11154</option>
				    </select>
				    --%>
				    <br><br>
				<div>
					<span>���:</span>
					<select id="groupTypeA" name="tradeFlowModel.groupType" onChange="changeGroupType('groupTypeA','groupTwoSpanA')">
						<option value="O">һ����</option>
						<option value="T">������</option>
					</select>
					<span>
					һ����
					<select id="groupOneA" name="tradeFlowModel.groupOne" onChange="genGroupTwo('groupOneA','groupTwoA')">
						<option value=""></option>
						<ww:iterator value="#request.groupOneList">
							<option value='<ww:property value="groupId"/>'><ww:property value="groupId"/></option>
						</ww:iterator>
					</select>
					</span>
					<span id="groupTwoSpanA">
					������
					<select id="groupTwoA" name="tradeFlowModel.groupTwo">
					</select>
					</span>
					<input id="button1" type="button" value="����" style="margin-left: 80px;width:100px" onclick="tijiao()">   
				</div>
				    
				
			    </form>
			    
		    </div>
		    
		   <div id="ectable">
		   		<ww:if test="#request.accountModelList != null">
			<ec:table items="accountModelList" var="accountModel" action="accountStatementPageInit.action">
				<ec:export view="xls" fileName="�˻���������б�.xls" imageName="xls"
					viewResolver="xls" tooltip="���� Excel" />

				<ec:row>
					<ec:column property="account" title="���׺�"
						headerStyle="text-align:center"
						style="text-align:left;width:100px" />
					<ec:column property="stringAccountType" title="�˻����"
						headerStyle="text-align:center"
						style="text-align:left;width:100px" /> 
					
					<%-- <ec:column property="xxxxx" title="���˻����"
						headerStyle="text-align:center"
						style="text-align:left;width:100px" >
						<ww:if test="#request.accountC==1">c=${accountModel.tradeAccountC.exactAccountBalance}<br/></ww:if>
						<ww:if test="#request.accountW==1">w=${accountModel.tradeAccountW.exactAccountBalance}<br/></ww:if>
						<ww:if test="#request.accountB==1">b=${accountModel.tradeAccountB.exactAccountBalance}</ww:if>
					</ec:column> --%>
					<ec:column property="stringExactAccountBalance" title="���˻����"
						headerStyle="text-align:center"
						style="text-align:left;width:100px" />
					<%-- <ec:column property="xxxxxx" title="���˻���ʼ���"
						headerStyle="text-align:center"
						style="text-align:left;width:100px" >
						<ww:if test="#request.accountC==1">c=${accountModel.tradeAccountC.exactInitBalance}<br/></ww:if>
						<ww:if test="#request.accountW==1">w=${accountModel.tradeAccountW.exactInitBalance}<br/></ww:if>
						<ww:if test="#request.accountB==1">b=${accountModel.tradeAccountB.exactInitBalance}</ww:if>
					</ec:column> --%>
					<ec:column property="stringExactInitBalance" title="���˻���ʼ���"
						headerStyle="text-align:center"
						style="text-align:left;width:100px" />
					
						<%-- <ec:column property="xxxx" title="���˻�ӯ�����"
						headerStyle="text-align:center"
						style="text-align:left;width:150px" >
						<ww:if test="#request.accountC==1">c=${accountModel.tradeAccountC.stringProfitAndLossBalance}<br/></ww:if>
						<ww:if test="#request.accountW==1">w=${accountModel.tradeAccountW.stringProfitAndLossBalance}<br/></ww:if>
						<ww:if test="#request.accountB==1">b=${accountModel.tradeAccountB.stringProfitAndLossBalance}</ww:if>
					</ec:column> --%>
					<ec:column property="stringSelfProfitAndLossBalance" title="���˻�ӯ�����"
						headerStyle="text-align:center"
						style="text-align:left;width:100px" />
					
				
				                    	
					<ec:column property="stringSelfProfitAndLoss" title="���˻�ӯ����"
						headerStyle="text-align:center"
						style="text-align:left;width:100px" />
					
					<%-- <ec:column property="tempAccountBalance" title="�����˻��ܽ��"
						headerStyle="text-align:center"
						style="text-align:left;width:100px" />
					<ec:column property="tempAccountInitBalance" title="�����˻���ʼ�ܽ��"
						headerStyle="text-align:center"
						style="text-align:left;width:80px" />	
					<ec:column property="stringSumProfitAndLoss" title="��ӯ����"
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