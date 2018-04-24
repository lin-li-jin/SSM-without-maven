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
    
    <title>�˻���ˮͳ��</title>
    
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
	    		<span style="margin-left: 10px;">�˻�����:</span>
		    </div>
		    <div id="button">
		    	<input type="button" value="�˻�������ˮͳ��" style="margin-left: 15px; height: 25px" onclick="window.location.href='accountFlowPageInit.action'">
		    	<input type="button" value="�˻��������" style="margin-left: 50px; width: 150px; height: 25px" onclick="window.location.href ='stuaccountStatementPageInit.action'">
		    	<!-- <input type="button" value="�˻���������" style="margin-left: 50px; width: 150px; height: 25px" onclick="window.location.href='accountNumberPageInit.action'"> -->
		    	<input type="button" value="�˻�������" style="margin-left: 50px; width: 150px; height: 25px" onclick="window.location.href='accountBalancePageInit.action'">
		    </div>
		    <div class="modules">
		    	<form action="accountFlowTradeRecordSearch.action" method="post">
			    	<span>��������:</span><select name="tradeType">
			    			<option value=""></option>
				    		<ww:iterator value="#request.tradeTypeList">
				    		<option value="<ww:property value='id.codeVal' />"<ww:if test="#request.tradeType == id.codeVal">selected</ww:if>>
				    			<ww:property value="descr"/>
				    		</option>
				    		</ww:iterator>
				    		</select>&nbsp;
				    <span>���:</span><label><ww:property value="#session.loginUserModel.groupOneId"/></label>
				    <span>����Ա����:</span><label><ww:property value="#session.loginUserModel.userId"/></label>
				    <input type="submit" value="����" style="margin-left: 80px;width:100px">
			    </form>
		    </div>
		    <div id="ectable">
		   		<ec:table items="collection" var="c" action=""
						border="" cellpadding="0" cellspacing="0" style="eXtremeTable" >
						<ec:export view="xls" fileName="�����б�.xls" imageName="xls" viewResolver="xls" tooltip="���� Excel" />
						<ec:row>
							<ec:column property="tradeNo" title="�������" width="100" 
								headerStyle="text-align:center" style="text-align:left" viewsAllowed="html" />
							<ec:column property="tradeType" title="��������" width="160"
								headerStyle="text-align:center" style="text-align:left"/>
							<ec:column property="currencyBuy" title="�������" width="100"
								headerStyle="tex t-align:center" style="text-align:left"/>
							<ec:column property="currencySell" title="��������" width="100"
								headerStyle="text-align:center" style="text-align:left"/>
							<ec:column property="tradeSum" title="���׽��" width="100"
								headerStyle="text-align:center" style="text-align:right;"/>
							<ec:column property="tradePrice" title="���׼۸�" width="100"
								headerStyle="text-align:center" style="text-align:right;"/>
							<ec:column property="promptDate" title="����(����)����" width="120"
								headerStyle="text-align:center" style="text-align:left"/>
							<ec:column property="provider" title="���׶��ַ�" width="100"
								headerStyle="text-align:center" style="text-align:left"/>
							<ec:column property="realStatus" title="����״̬" width="80"
								headerStyle="text-align:center" style="text-align:left"/>
							<ec:column property="oper" title="����" headerStyle="text-align:center" 
								style="text-align:left" width="50" viewsAllowed="html" sortable="false">
							<script type="text/javascript">
								document.write('<a href="totalTradeDetail.action?table='+'${c.tableName }'+'&tradeNo='+'${c.tradeNo }'+'&tradeStatus='+'${c.tradeStatus }'+'">�鿴</a>');
							</script>
							</ec:column>
					</ec:row>
				</ec:table>	
    		</div>
	  	</div>
  </body>
</html>