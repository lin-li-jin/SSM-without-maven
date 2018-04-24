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
  </head>
  
  <body>
	  	<div id="all">
	  		<div id="top">
	    		<span style="margin-left: 10px;">账户损益情况:</span>
		    </div>
		    <div id="button">
		    	<input type="button" value="账户交易流水统计" style="margin-left: 15px; height: 25px" onclick="window.location.href ='accountFlowPageInit.action'">
		    	<input type="button" value="账户损益情况" style="margin-left: 50px; width: 150px; height: 25px" onclick="window.location.href ='stuaccountStatementPageInit.action'">
		    	<input type="button" value="账户排名管理" style="margin-left: 50px; width: 150px; height: 25px" onclick="window.location.href='accountNumberPageInit.action'">
		    	<input type="button" value="账户余额管理" style="margin-left: 50px; width: 150px; height: 25px" onclick="window.location.href='accountBalancePageInit.action'">
		    
		    </div>
		    
		    
		   <div id="ectable">
		   		<ww:if test="#request.accountModelList != null">
					<ec:table items="accountModelList" var="accountModel" action="accountStatementPageInit.action">
				<ec:export view="xls" fileName="账户损益情况列表.xls" imageName="xls"
					viewResolver="xls" tooltip="导出 Excel" />

				<ec:row>
					<ec:column property="account" title="交易员号"
						headerStyle="text-align:center"
						style="text-align:left;width:100px" />
						
					<ec:column property="stringAccountType" title="账户类别"
						headerStyle="text-align:center"
						style="text-align:left;width:100px" />
					
					<ec:column property="stringExactAccountBalance" title="各账户现有金额"
						headerStyle="text-align:center"
						style="text-align:left;width:100px" />
				
					<ec:column property="stringExactInitBalance" title="各账户初始金额"
						headerStyle="text-align:center"
						style="text-align:left;width:100px" />
					
					<ec:column property="stringSelfProfitAndLossBalance" title="各账户盈亏金额"
						headerStyle="text-align:center"
						style="text-align:left;width:100px" />
				              	
					<ec:column property="stringSelfProfitAndLoss" title="各账户盈亏率"
						headerStyle="text-align:center"
						style="text-align:left;width:100px" />
			</ec:row>
			</ec:table>
			<br />
			<br />
		</ww:if>
		   		
		   		
		   	
    		</div>
	  	</div>
  </body>
</html>