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
    
    <title>账户流水统计</title>
    
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
		    	<input type="button" value="账户损益情况" style="margin-left: 50px; width: 150px; height: 25px" onclick="window.location.href ='stuaccountStatementPageInit.action'">
		    	<!-- <input type="button" value="账户排名管理" style="margin-left: 50px; width: 150px; height: 25px" onclick="window.location.href='accountNumberPageInit.action'"> -->
		    	<input type="button" value="账户余额管理" style="margin-left: 50px; width: 150px; height: 25px" onclick="window.location.href='accountBalancePageInit.action'">
		    </div>
		    <div class="modules">
		    	<form action="accountFlowTradeRecordSearch.action" method="post">
			    	<span>交易类型:</span><select name="tradeType">
			    			<option value=""></option>
				    		<ww:iterator value="#request.tradeTypeList">
				    		<option value="<ww:property value='id.codeVal' />"<ww:if test="#request.tradeType == id.codeVal">selected</ww:if>>
				    			<ww:property value="descr"/>
				    		</option>
				    		</ww:iterator>
				    		</select>&nbsp;
				    <span>组别:</span><label><ww:property value="#session.loginUserModel.groupOneId"/></label>
				    <span>交易员代码:</span><label><ww:property value="#session.loginUserModel.userId"/></label>
				    <input type="submit" value="搜索" style="margin-left: 80px;width:100px">
			    </form>
		    </div>
		    <div id="ectable">
		   		<ec:table items="collection" var="c" action=""
						border="" cellpadding="0" cellspacing="0" style="eXtremeTable" >
						<ec:export view="xls" fileName="交易列表.xls" imageName="xls" viewResolver="xls" tooltip="导出 Excel" />
						<ec:row>
							<ec:column property="tradeNo" title="交易序号" width="100" 
								headerStyle="text-align:center" style="text-align:left" viewsAllowed="html" />
							<ec:column property="tradeType" title="交易类型" width="160"
								headerStyle="text-align:center" style="text-align:left"/>
							<ec:column property="currencyBuy" title="买入货币" width="100"
								headerStyle="tex t-align:center" style="text-align:left"/>
							<ec:column property="currencySell" title="卖出货币" width="100"
								headerStyle="text-align:center" style="text-align:left"/>
							<ec:column property="tradeSum" title="交易金额" width="100"
								headerStyle="text-align:center" style="text-align:right;"/>
							<ec:column property="tradePrice" title="交易价格" width="100"
								headerStyle="text-align:center" style="text-align:right;"/>
							<ec:column property="promptDate" title="交割(到期)日期" width="120"
								headerStyle="text-align:center" style="text-align:left"/>
							<ec:column property="provider" title="交易对手方" width="100"
								headerStyle="text-align:center" style="text-align:left"/>
							<ec:column property="realStatus" title="交易状态" width="80"
								headerStyle="text-align:center" style="text-align:left"/>
							<ec:column property="oper" title="操作" headerStyle="text-align:center" 
								style="text-align:left" width="50" viewsAllowed="html" sortable="false">
							<script type="text/javascript">
								document.write('<a href="totalTradeDetail.action?table='+'${c.tableName }'+'&tradeNo='+'${c.tradeNo }'+'&tradeStatus='+'${c.tradeStatus }'+'">查看</a>');
							</script>
							</ec:column>
					</ec:row>
				</ec:table>	
    		</div>
	  	</div>
  </body>
</html>
