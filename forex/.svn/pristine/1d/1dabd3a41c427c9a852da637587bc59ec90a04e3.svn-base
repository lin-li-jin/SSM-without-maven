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
		#ectable{margin: 0; padding: 0; width: 855px; height: auto; }
	</style>

  </head>
  
  <body>
	  	<div id="all">
	  		<div id="top">
	    		<span style="margin-left: 10px;">学生[<ww:property value="userNum"/>]交易记录:</span>
		    </div>
		    <div id="ectable">
		   		<ec:table items="tradeList" var="c" action="getTradeListByUserNum.action"
						border="" cellpadding="0" cellspacing="0" style="eXtremeTable" >
						<ec:row>
							<ec:column property="tradeNo" title="交易序号" width="100"
								headerStyle="text-align:center" style="text-align:left" viewsAllowed="html" >
							</ec:column>
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
