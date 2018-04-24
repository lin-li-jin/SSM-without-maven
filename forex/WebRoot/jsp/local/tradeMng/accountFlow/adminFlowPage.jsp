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
			$("#groupTwo").empty();//���ǰһ���͵�ѡ��,����ѡһ����Ҫ��ԭ���Ķ��������
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
					//START ������Ϊ���ڲ�ѯ����ת���������Ȼ�ܱ�ѡ��
					if('<ww:property value="#request.tradeFlowModel.groupTwo" />' != ""){
						if('<ww:property value="#request.tradeFlowModel.groupTwo" />' == (groupTwoList[i])){
							$("#groupTwo").val(groupTwoList[i]);
							getStudent();
						}
					}
					//END ������Ϊ���ڲ�ѯ����ת���������Ȼ�ܱ�ѡ��
				}
			});	
		}
		
		function getStudent(){
			var groupTwo = $("#groupTwo"+" option:selected").val();
			$("#userNum").empty();//���ǰһ���͵�ѡ��
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
					//START ������Ϊ���ڲ�ѯ����ת��ѧ�Ż��ܱ�ѡ��
					if('<ww:property value="#request.tradeFlowModel.userNumber" />' != ""){
						if('<ww:property value="#request.tradeFlowModel.userNumber" />' == (studentList[i])){
							$("#userNum").val(studentList[i]);
						}
					}
					//end ������Ϊ���ڲ�ѯ����ת��ѧ�Ż��ܱ�ѡ��
				}
			});	

		}
		
		
		function getStudentByClass(){
			var classNo = $("#classNo"+" option:selected").val();
			$("#userNum1").empty();//���ǰһ���͵�ѡ��
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
					//START ������Ϊ���ڲ�ѯ����ת��ѧ����Ȼ��ѡ��
					if('<ww:property value="#request.tradeFlowModel.userNum" />' != ""){
						if('<ww:property value="#request.tradeFlowModel.userNum" />' == (studentList[i])){
							$("#userNum1").val(studentList[i]);
						}
					}
					//end ������Ϊ���ڲ�ѯ����ת��ѧ����Ȼ��ѡ��
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
	    		<span style="margin-left: 10px;">�˻�����:</span>
		    </div>
		    <div id="button">
		    	<input type="button" value="�˻�������ˮͳ��" style="margin-left: 15px; height: 25px" onclick="window.location.href ='accountFlowPageInit.action'">
		    	<input type="button" value="�˻��������" style="margin-left: 50px; width: 150px; height: 25px" onclick="window.location.href ='accountStatementPageInit.action'">
		    	<!-- <input type="button" value="�˻���������" style="margin-left: 50px; width: 150px; height: 25px" onclick="window.location.href='accountNumberPageInit.action'"> -->
		    </div>
		    <div class="modules">
		    	<form name="cashForm" action="" method="post">
		    		<span>��������:</span>
		    		<select id="tradeType" name="tradeFlowModel.tradeType" style="width:120px;">
			    		<option value="C" <ww:if test='#request.tradeFlowModel.tradeType=="C"'>selected</ww:if>>�������ҽ���</option>
			    		<option value="W" <ww:if test='#request.tradeFlowModel.tradeType=="W"'>selected</ww:if>>��ҶԽ���</option>
			    		<option value="B" <ww:if test='#request.tradeFlowModel.tradeType=="B"'>selected</ww:if>>��֤����</option>
				    </select>
				    &nbsp;
		    		
		    		<span>��ѯ��ʽ:</span>
		    		<select id="groupType" name="tradeFlowModel.groupType" onclick="changeGroupType();">
			    		<option value="O" <ww:if test='#request.tradeFlowModel.groupType=="O"'>selected</ww:if>>��ѯ�����༶ѧ��</option>
			    		<option value="T" <ww:if test='#request.tradeFlowModel.groupType=="T"'>selected</ww:if>>��ѯ�༶ĳ������</option>
				    </select>
				    <br><br>
				    <span id="queryAllClass">
				    	�༶���:
				    	<select id="classNo" name="tradeFlowModel.classNo" onclick="getStudentByClass()" style="width:120px;">
				    		<!--  <option value=""></option>-->
							<ww:iterator value="#request.groupOneList">
							<option value='<ww:property value="groupId"/>' <ww:if test="#request.tradeFlowModel.classNo==groupId">selected</ww:if>><ww:property value="groupId"/></option>
							</ww:iterator>
				    	</select>
				    	&nbsp;&nbsp;&nbsp;&nbsp;
				    	����Ա���:
				    	<select id="userNum1" name="tradeFlowModel.userNum" style="width: 115px;">
				    		
					    </select>
				    </span>
				    <input id="button1" type="button" value="����" style="margin-left: 80px;width:100px" onclick="tijiao()">   

				    <span id="groupOneSpan">һ����:&nbsp;&nbsp;
				    <select id="groupOne" name="tradeFlowModel.groupOne" onclick="getGroupTwo()" style="width:120px;">
						<!--  <option value=""></option>-->
						<ww:iterator value="#request.groupOneList">
							<option value='<ww:property value="groupId"/>' <ww:if test="#request.tradeFlowModel.groupOne==groupId">selected</ww:if>><ww:property value="groupId"/></option>
						</ww:iterator>
				    </select>&nbsp;
				    </span>
				    
				    <span id="groupTwoSpan">&nbsp;&nbsp;������:&nbsp;&nbsp;
					    <select id="groupTwo" name="tradeFlowModel.groupTwo" onclick="getStudent()" style="width: 115px;">
					   </select>&nbsp;
				    </span>
				    <span id="userNumSpan">����Ա����:
					    <select id="userNum" name="tradeFlowModel.userNumber" style="width: 100px;">
					    </select>
				    </span>
				    <input id="button2" type="button" value="����" style="margin-left: 80px;width:100px" onclick="tijiao1()">
			    </form>
		    </div>
		    
		   <div id="ectable">
		   		<ww:if test="#request.listB!=null">
		   			<div id="top2">
	    				<span style="text-align: center; color: red; font-size: 16px; font-weight: bolder;">��֤����</span>
		    		</div>
		   			<ec:table items="listB" var="c" action=""
					border="" cellpadding="0" cellspacing="0" style="eXtremeTable" width="860px">
							<ec:row>
								<ec:column property="userNumLink" title="����Ա" width="70"
									headerStyle="text-align:center" style="text-align:left" viewsAllowed="html" >
									<script type="text/javascript">
									document.write('<a href="getTradeListByUserNum.action?userNum='+'${c.userNum }'+'">'+'${c.userNum}'+'</a>');
									</script>
								</ec:column>
								<ec:column property="marginSpotQty" title="Զ�ڽ��״���" width="100"
									headerStyle="text-align:center" style="text-align:left" viewsAllowed="html" >
								</ec:column>
								<ec:column property="marginSpotAmt" title="Զ�ڽ��׽��" width="160"
									headerStyle="text-align:center" style="text-align:right;"/>
								<ec:column property="marginOptionQty" title="��Ȩ���״���" width="100"
									headerStyle="tex t-align:center" style="text-align:left"/>
								<ec:column property="marginOptionAmt" title="��Ȩ���׽��" width="100"
									headerStyle="text-align:center" style="text-align:right;"/>
							</ec:row>
					</ec:table>	
		   		</ww:if>
		   		
		   		<ww:if test="#request.listC!=null">
		   			<div id="top2">
	    				<span style="text-align: center; color: red; font-size: 16px; font-weight: bolder;">�������ҽ���</span>
		    		</div>
		   			<ec:table items="listC" var="c" action=""
					border="" cellpadding="0" cellspacing="0" style="eXtremeTable" >
							<ec:row>
								
								<ec:column property="userNumLink" title="����Ա" width="70"
									headerStyle="text-align:center" style="text-align:left" viewsAllowed="html" >
									<script type="text/javascript">
									document.write('<a href="getTradeListByUserNum.action?userNum='+'${c.userNum }'+'">'+'${c.userNum}'+'</a>');
									</script>
								</ec:column>
								
								<ec:column property="oneClickQty" title="һ�ڼ۽�����" 
									headerStyle="text-align:center" style="text-align:left" />
								<ec:column property="oneClickAmt" title="һ�ڼ۽��׽��" 
									headerStyle="text-align:center" style="text-align:right;"/>
								<ec:column property="stopLossQty" title="ֹ������" 
									headerStyle="tex t-align:center" style="text-align:left"/>
								<ec:column property="stopLossAmt" title="ֹ���׽��" 
									headerStyle="text-align:center" style="text-align:right;"/>
								
								<ec:column property="takeProfitQty" title="ֹӯ������" 
									headerStyle="text-align:center" style="text-align:left"/>
								<ec:column property="takeProfitAmt" title="ֹӯ���׽��" 
									headerStyle="text-align:center" style="text-align:right;"/>
								<ec:column property="ocoQty" title="oco������" 
									headerStyle="text-align:center" style="text-align:left"/>
								<ec:column property="ocoAmt" title="oco���׽��" 
									headerStyle="text-align:center" style="text-align:right;"/>
								<ec:column property="marketBreakoutQty" title="mkb������" 
									headerStyle="text-align:center" style="text-align:left"/>
								<ec:column property="marketBreakoutAmt" title="mkb���׽��" 
									headerStyle="text-align:center" style="text-align:right;"/>
								<ec:column property="otcSpotQty" title="����ѯ�۽�����" 
									headerStyle="text-align:center" style="text-align:left"/>
								<ec:column property="otcSpotAmt" title="����ѯ�۽��׽��" 
									headerStyle="text-align:center" style="text-align:right;"/>
								<ec:column property="otcForwartQty" title="Զ��ѯ�۽�����" 
									headerStyle="text-align:center" style="text-align:left"/>
								<ec:column property="otcForwartAmt" title="Զ��ѯ�۽��׽��" 
									headerStyle="text-align:center" style="text-align:right;"/>
								<ec:column property="otcSwapQty" title="����ѯ�۽�����" 
									headerStyle="text-align:center" style="text-align:left"/>
								<ec:column property="otcSwapAmt" title="����ѯ�۽��׽��" 
									headerStyle="text-align:center" style="text-align:right;"/>
							</ec:row>
					</ec:table>	
		   		</ww:if>
		   		
		   		
		   		<ww:if test="#request.listW!=null">
		   			<div id="top2">
	    				<span style="text-align: center; color: red; font-size: 16px; font-weight: bolder;">��ҶԽ���</span>
		    		</div>
		   			<ec:table items="listW" var="c" action=""
					border="" cellpadding="0" cellspacing="0" style="eXtremeTable" >
							<ec:row>
								<ec:column property="userNumLink" title="����Ա" width="70"
									headerStyle="text-align:center" style="text-align:left" viewsAllowed="html" >
									<script type="text/javascript">
									document.write('<a href="getTradeListByUserNum.action?userNum='+'${c.userNum }'+'">'+'${c.userNum}'+'</a>');
									</script>
								</ec:column>
								<ec:column property="oneClickQty" title="һ�ڼ۽�����" 
									headerStyle="text-align:center" style="text-align:left" />
								<ec:column property="oneClickAmt" title="һ�ڼ۽��׽��" 
									headerStyle="text-align:center" style="text-align:right;"/>
								<ec:column property="stopLossQty" title="ֹ������" 
									headerStyle="tex t-align:center" style="text-align:left"/>
								<ec:column property="stopLossAmt" title="ֹ���׽��" 
									headerStyle="text-align:center" style="text-align:right;"/>
								
								<ec:column property="takeProfitQty" title="ֹӯ������" 
									headerStyle="text-align:center" style="text-align:left"/>
								<ec:column property="takeProfitAmt" title="ֹӯ���׽��" 
									headerStyle="text-align:center" style="text-align:right;"/>
								<ec:column property="ocoQty" title="oco������" 
									headerStyle="text-align:center" style="text-align:left"/>
								<ec:column property="ocoAmt" title="oco���׽��" 
									headerStyle="text-align:center" style="text-align:right;"/>
								<ec:column property="marketBreakoutQty" title="mkb������" 
									headerStyle="text-align:center" style="text-align:left"/>
								<ec:column property="marketBreakoutAmt" title="mkb���׽��" 
									headerStyle="text-align:center" style="text-align:right;"/>
								<ec:column property="otcSpotQty" title="����ѯ�۽�����" 
									headerStyle="text-align:center" style="text-align:left"/>
								<ec:column property="otcSpotAmt" title="����ѯ�۽��׽��" 
									headerStyle="text-align:center" style="text-align:right;"/>
								<ec:column property="otcForwartQty" title="Զ��ѯ�۽�����" 
									headerStyle="text-align:center" style="text-align:left"/>
								<ec:column property="otcForwartAmt" title="Զ��ѯ�۽��׽��" 
									headerStyle="text-align:center" style="text-align:right;"/>
								<ec:column property="otcSwapQty" title="����ѯ�۽�����" 
									headerStyle="text-align:center" style="text-align:left"/>
								<ec:column property="otcSwapAmt" title="����ѯ�۽��׽��" 
									headerStyle="text-align:center" style="text-align:right;"/>
							</ec:row>
					</ec:table>	
		   		</ww:if>
    		</div>
	  	</div>
  </body>
</html>
