<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib prefix="ww" uri="webwork" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>oneClick����ҳ��</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="resources/ponyjs/jquery.js" type="text/javascript"></script>
	<style type="text/css">
		#top{margin: 0; padding:3px 5px; height: 24px; width:820px; line-height: 24px; background-image: url("images/b.png"); color: white; text-align: center;}
		#container{margin: 0;padding: 0; height: 250px; width: 830px !important; width:820px; background-color: #E5F2F8; 
		margin-top: 5px;  margin-bottom: 5px;  float: left;}
		
		.modules{margin: 0px; padding:0px 0px 0px 50px !important; width: 205px !important; 
		padding:0px 0px 0px  20px; width: 255px; height: 250px;  margin-right: 10px; float: left; }
		
		.modules2{margin: 0px; padding:0px 0px 0px 50px !important; width: 215px !important; 
		padding:0px 0px 0px  20px; width: 265px; height: 250px;  margin-right: 10px; float: left;  }
	</style>
	<script type="text/javascript" src="jsp/local/tradeModule/bid/js/bidtrade.js"></script> 
	<script type="text/javascript" language="javascript">
		$(function(){
			var time = '<ww:property value="#request.oneClickInfo.time" />';
			var date = '<ww:property value="#request.oneClickInfo.createDatetime" />';
			document.getElementById("tradeDate").innerHTML = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8) + " " + time.substring(0, 2) + ":" + time.substring(2, 4);;
			document.getElementById("createDate").innerHTML = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8)+ " "+ date.substring(8, 10) + ":" + date.substring(10, 12);
			if ('<ww:property value="#request.oneClickInfo.direction" />' == 0){
				document.getElementById("direct").innerHTML = "��";
				document.getElementById("ccy").innerHTML = '<ww:property value="#request.oneClickInfo.weCcy"/>' + "/" + '<ww:property value="#request.oneClickInfo.anaCcy"/>';
				document.getElementById("label1").innerHTML = '<ww:property value="#request.oneClickInfo.weCcy"/>' + "���׽�";
				document.getElementById("label2").innerHTML = '<ww:property value="#request.oneClickInfo.anaCcy"/>' + "���׽�";
				document.getElementById("hidden").value = '<ww:property value="#request.oneClickInfo.anaCcy"/>';
			}
			else{
				document.getElementById("direct").innerHTML = "��";
				document.getElementById("ccy").innerHTML = '<ww:property value="#request.oneClickInfo.anaCcy"/>' + "/" + '<ww:property value="#request.oneClickInfo.weCcy"/>';
				document.getElementById("label1").innerHTML = '<ww:property value="#request.oneClickInfo.anaCcy"/>' + "���׽�";
				document.getElementById("label2").innerHTML = '<ww:property value="#request.oneClickInfo.weCcy"/>' + "���׽�";
				document.getElementById("hidden").value = '<ww:property value="#request.oneClickInfo.weCcy"/>';
			}		
		});
	</script>
  </head>
  
  <body>
  	<div id="top">OneClick����</div>
  	<form action="">
  		<div id="container">
    	<div class="modules">
    		<br><br>
    		<label>������ţ�</label><span><ww:property value="#request.oneClickInfo.tranNo" /></span><br><br><br>
    		<label id="label1"></label><span><ww:property value="#request.oneClickInfo.amount" /></span><br><br><br>
    		<label>�ύ���ڣ�</label><span id="createDate"></span><br><br><br>
    		<label>����״̬��</label><span>DONE</span>
    	</div>
    	<div class="modules2">
    	    <br><br>
    		<label>�������ͣ�</label><span>һ�ڼ۽���</span><br><br><br>
    		<label id="label2"></label><span><ww:property value="#request.oneClickInfo.LAmount"/></span><br><br><br>
    		<label>����ʱ�䣺</label><span id="tradeDate"></span><br><br><br>
    		<label>���ַ�������</label><span></span><br><br><br>
    	</div>
    	<div class="modules">
    	    <br><br>
    		<label>���ױ��֣�</label><span id="ccy"></span><br><br><br>
    		<label>�ɽ��۸�</label><span><ww:property value="#request.oneClickInfo.price" /></span><br><br><br>
    		<label>���׷���</label><span id="direct"></span><br><br><br>
    		<label>����������</label><span></span><br>
    	</div>
    	</div>
    	
    	<div id="top"><input type="button" value="ȷ��" style="width: 120px; height:24px;" onclick="backToAction()"></div>
    	<input type="hidden" id="hidden" >
  	</form>

  </body>
</html>
