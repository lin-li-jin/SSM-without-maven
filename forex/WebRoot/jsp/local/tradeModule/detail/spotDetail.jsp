<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib prefix="ww" uri="webwork"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>��֤���ڽ��׵�</title>
	<script src="resources/ponyjs/jquery.js" type="text/javascript"></script>
	<style type="text/css">
		#top{margin: 0; padding:3px 5px; height: 24px; width:820px; line-height: 24px; 
		background-image: url("images/b.png"); color: white; text-align: center; float: left;}
		
		#container{margin: 0;padding: 0; height: 300px; width: 830px !important; width:820px; background-color: #E5F2F8; 
		margin-top: 5px;  margin-bottom: 5px;  float: left;}
		
		.modules{margin: 0px; padding:0px 0px 0px 50px !important; width: 205px !important; 
		padding:0px 0px 0px  20px; width: 255px; height: 250px;  margin-right: 10px; float: left; }
		
		.modules2{margin: 0px; padding:0px 0px 0px 50px !important; width: 215px !important; 
		padding:0px 0px 0px  20px; width: 265px; height: 250px;  margin-right: 10px; float: left;  }
	</style> 
	<script type="text/javascript">
		function execute(){
			window.location.href = "executeOption.action?tradeNo="+'<ww:property value="#request.optionDetail.tranNo" />'; 
		}
	</script>
  </head>
  
  <body>
  	<div id="top">��֤���ڽ��׵�</div>
  	<form action="">
  		<div id="container">
    	<div class="modules2">
    		<br><br>
    		<label>������ţ�</label><span><ww:property value="#request.optionDetail.tranNo"/></span><br><br><br>
    		<label id="label1"><ww:property value="#request.optionDetail.weCcy"/> ���׽��:</label><span><ww:property value="#request.optionDetail.dealAmt"/></span><br><br><br>
    		<label>�ύʱ�䣺</label><span><ww:property value="#request.optionDetail.createDatatime"/></span><br><br><br>
    		<label>����״̬��</label><span><ww:property value="#request.optionDetail.statue"/></span><br>

    	</div>
    	<div class="modules2">
    	    <br><br>
    		<label>�������ͣ�</label><span>���ڽ���</span><br><br><br>
    		<label id="label2"><ww:property value="#request.optionDetail.anaCcy"/> ���׽��:</label><span><ww:property value="#request.enlarge"/></span><br><br><br>
    		<label>����ʱ�䣺</label><span><ww:property value="#request.createDatetime"/> <ww:property value=""/></span><br><br><br>
			<label>ʵʱ�۸�</label><span><ww:property value="#request.currentPrice"/></span>
    	</div>
    	<div class="modules">
    	    <br><br>
    		<label>���ױ��֣�</label><ww:property value="#request.optionDetail.anaCcy"/>/<ww:property value="#request.optionDetail.weCcy"/><br><br><br>
    		<label>�ɽ��۸�</label><span><ww:property value="#request.optionDetail.price"/></span><br><br><br>
    		<label>���׷���</label><span>
			<ww:if test="#request.optionDetail.direction==1">
				��
			</ww:if>
			<ww:else>
				��
			</ww:else>
			</span><br><br><br>
			<label>ӯ���</label>
			<span>
				<ww:property value="#request.profitAndLoss"/>
				<ww:property value="#request.optionDetail.weCcy"/>
			</span><br><br><br>
    		<label>ӯ���ʣ�</label><span> %</span>
    	</div>
    	</div>
    
    	<div id="top">
    		<input type="button" value="ȷ��" style="width: 120px; height:24px;" onclick="history.back();">
    		<ww:if test="#request.optionDetail.statusDescr=='������'">
	    		<input type="button" value="ִ�н���" style="width: 120px; height:24px; margin-left: 150px;" onclick="execute();">
	    	</ww:if>
    	</div>
  	</form>

  </body>
</html>