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
    
    <title>My JSP 'studentFlowPage.jsp' starting page</title>
    
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
		#ectable{margin: 0; padding: 0; width: 850px; height: auto; }
	</style>

  </head>
  
  <body>
	  	<div id="all">
	  		<div id="top">
	    		<span style="margin-left: 10px;">账户管理:</span>
		    </div>
		    <div id="button">
		    	<input type="button" value="账户交易流水统计" style="margin-left: 15px; height: 25px" onclick="window.location.href='accountFlowPageInit.action'">
		    	<input type="button" value="账户损益情况" style="margin-left: 50px; width: 150px; height: 25px" onclick="window.location.href='stuaccountStatementPageInit.action'">
		    	<input type="button" value="账户排名管理" style="margin-left: 50px; width: 150px; height: 25px"onclick="window.location.href='accountNumberPageInit.action'">
		    	<input type="button" value="账户余额管理" style="margin-left: 50px; width: 150px; height: 25px" onclick="window.location.href='accountBalancePageInit.action'">
		    </div>
		    <div class="modules">
				<span>组别:</span><label><ww:property value="#session.loginUserModel.groupOneId"/></label>
				<span>交易员代码:</span><label><ww:property value="#session.loginUserModel.userId"/></label>
		    </div>
		    <div id="ectable">
		   		<ec:table items="accountBalanceList" var="c" action=""
						border="" cellpadding="0" cellspacing="0" style="eXtremeTable" >
						<ec:row>
							<ec:column property="realAccType" title="账户类型" width="100"
								headerStyle="text-align:center" style="text-align:left" viewsAllowed="html" >
							</ec:column>
							<ec:column property="ccy" title="账户币种" width="160"
								headerStyle="text-align:center" style="text-align:left"/>
							<ec:column property="originalAmt" title="币种初始金额" width="100"
								headerStyle="tex t-align:center" style="text-align:right;"/>
							<ec:column property="amount" title="币种余额" width="100"
								headerStyle="text-align:center" style="text-align:right;"/>
					</ec:row>
				</ec:table>	
    		</div>
	  	</div>
  </body>
</html>
