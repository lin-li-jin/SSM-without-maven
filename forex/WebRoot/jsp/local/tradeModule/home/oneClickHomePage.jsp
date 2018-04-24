<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib uri="extremecomponents" prefix="ec"%>
<%@ taglib uri="webwork" prefix="ww"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <!-- <meta http-equiv="refresh" content="10"> -->
    <title>人民币交易</title>
	<style type="text/css">
		#all{ margin: 0; padding:0; width: 1003px; height: auto;}
		.colorSet{color: red; font-size: 16px;font-weight: bolder;}
		#top{margin: 0; padding:3px 5px; height: 24px; width:840px; line-height: 24px; background-image: url("images/b.png"); color: white;}
		#center{margin: 0; padding:0; height: auto; width:850px; font-size: 14px;margin-top: 10px;border-bottom:1px solid black;}
		.container{margin: 0; padding: 0; height: 165px; width: 100%; margin-bottom: 5px;}
		.modules{margin: 0; padding: 0; height:165px; width:248px;float: left; margin-right: 40px;font-size: 14px; }
		.amount{margin: 0; padding: 0; height:30px; width:248px;float: left; margin-top: 5px;}
		.modulesTopRed{margin: 0; padding: 0; height:25px;width: 242px; background-color: #ce3231; 
		float: left; margin-bottom: 2px;}
		
		.modulesTopBlue{margin: 0; padding: 0; height:25px;width: 242px; background-image: url("images/b.png");
		float: left;margin-bottom: 2px;}
		
		.blue{margin: 0; padding: 0; height: 100px !important; height:110px; width:119px !important; width:121px;background-color: #dee7ff; 
		float:left; border: 1px #428BC2 solid; text-align: center;cursor:pointer;padding-top: 10px;}
		.modules span { line-height: 25px; font-size: 16px; color: white; margin-left: 10px; font-weight: bolder;}
		.modules label {  font-size: 16px; color: black; font-weight: bolder;cursor:pointer}
		#ectable{margin: 0; padding: 0; width: 860px; height: auto; }
	</style>
	<link rel="stylesheet" href="resources/css/extremecomponents.css"	type="text/css" />
	<script src="resources/ponyjs/jquery.js" type="text/javascript"></script>
	<script language="JavaScript" src="jsp/local/tradeModule/home/js/homepage.js"></script>
	
	<script type="text/javascript">
		$(function(){
			$('input:checkbox').click(function () {
					this.blur();   
					this.focus(); 
			});
		});
		function selectOneClickTradeType(direction,ccy,id,price){
			var amount = document.getElementById(id).value;
			if (amount == '' || amount%100 != 0){
				alert("请输入100整数倍交易金额！");
				return false;
			}
			window.location.href = "oneClickOneTradeRecordAdd.action?direct="+direction+"&ccy1="+ccy+"&ccy2=CNY"+"&amount="+amount+"&price="+price;
		}
		
		$(document).ready(function(){
			var t=setTimeout("ajaxRefreshModule()",10000);
		})
		
		function ajaxRefreshModule(){
			$.post("CNYPageRefresh.action",function(data){
				//alert(data);
				var aData=data.split("$");
				var data1=aData[0];
				var data2=aData[1];
				//alert(data1);
				//alert(data2);
				var objB=eval("("+data1+")");
				var objA=eval("("+data2+")");
				$(".blue").remove();
				$(".modulesTopBlue").remove();
				addModule("BUSD",objB.BUSD,"USD","B","UAmount");
				addModule("BEUR",objB.BEUR,"EUR","B","EAmount");
				addModule("BGBP",objB.BGBP,"GBP","B","GAmount");
				addModule("BAUD",objB.BAUD,"AUD","B","AAmount");
				addModule("BJPY",objB.BJPY,"JPY","B","JAmount");
				addModule("BCAD",objB.BCAD,"CAD","B","CAmount");
			});
			//设置刷新时间为10秒
			var t=setTimeout("ajaxRefreshModule()",10000);
		}
		
		function addModule(id,obj,type,type2,amountID){
			//$("#"+id).empty();
			var bidImg="";
			var askImg="";
			if(obj.bidFlag==1){
				bidImg="<img alt='' src='resources/image/up.png'>";
			}else if(obj.bidFlag==-1){
				bidImg="<img alt='' src='resources/image/down.png'>";
			}
			if(obj.askFlag==1){
				askImg="<img alt='' src='resources/image/up.png'>";
			}else if(obj.askFlag==-1){
				askImg="<img alt='' src='resources/image/down.png'>";
			}
			if(type2=='B'){
				$("#"+id).prepend("<div class='blue' onclick=selectOneClickTradeType('1','USD','"+amountID+"',"+obj.bidValue+") > <label>I BUY "+type+"</label><br/><label>"+obj.askValue+"</label><br/><br/>"+askImg+"</div>");
				$("#"+id).prepend("<div class='blue' onclick=selectOneClickTradeType('0','USD','"+amountID+"',"+obj.bidValue+") > <label>I SELL "+type+"</label><br/><label>"+obj.bidValue+"</label><br/><br/>"+bidImg+"</div>");
				$("#"+id).prepend("<div class='modulesTopBlue'><span>"+type+"/CNY&nbsp;SPOT</span></div>");
			}
			
		}
	</script>
  </head>
  
  <body>
  <div id="all">
    <form action="">
  	 <div id="top">
    	一口价交易<input type="checkbox" name="oneClick" checked="checked" onchange="window.location.href='CNYPageInit.action'"> 
    	<span style="margin-left: 30px;">交易账户:</span>
    	<label>人民币账户</label><label>042166581207130</label>
    </div>
    <div id="center">
    	<div class="container">
	    	<div class="modules" id="BUSD">
	    		<div class="modulesTopBlue"><span>USD/CNY&nbsp;SPOT</span></div>
	    		<div class="blue" onclick="selectOneClickTradeType('0','USD','UAmount','<ww:property value='#request.USD.bidValue' />')">
	    			<label>I SELL USD</label><br>
	    			<label><ww:property value="#request.USD.bidValue" /></label>
	    			<ww:if test="#request.USD.bidFlag == 1"><br><br><img alt="" src="resources/image/up.png"></ww:if>
    				<ww:elseif test="#request.USD.bidFlag == -1"><br><br><img alt="" src="resources/image/down.png"></ww:elseif>
	    		</div>
	    		<div class="blue" onclick="selectOneClickTradeType('1','USD','UAmount','<ww:property value='#request.USD.askValue' />')">
	    			<label>I BUY USD</label><br>
	    			<label><ww:property value="#request.USD.askValue" /></label>
	    			<ww:if test="#request.USD.askFlag == 1"><br><br><img alt="" src="resources/image/up.png"></ww:if>
    				<ww:elseif test="#request.USD.askFlag == -1"><br><br><img alt="" src="resources/image/down.png"></ww:elseif>
	    		</div>
	    		<div class="amount">
	    			AMOUNT:<input type="text" id="UAmount" name="amount" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"> 
	    		</div>
	    	</div>
	    	<div class="modules" id="BEUR">
	    		<div class="modulesTopBlue">
	    			<span>EUR/CNY&nbsp;SPOT</span>
	    		</div>
	    		<div class="blue" onclick="selectOneClickTradeType('0','EUR','EAmount','<ww:property value='#request.EUR.bidValue' />')">
	    			<label>I SELL EUR</label><br>
	    			<label><ww:property value="#request.EUR.bidValue" /></label>
	    			<ww:if test="#request.EUR.bidFlag == 1"><br><br><img alt="" src="resources/image/up.png"></ww:if>
    				<ww:elseif test="#request.EUR.bidFlag == -1"><br><br><img alt="" src="resources/image/down.png"></ww:elseif>
	    		</div>
	    		<div class="blue" onclick="selectOneClickTradeType('1','EUR','EAmount','<ww:property value='#request.EUR.askValue' />')">
	    			<label>I BUY EUR</label><br>
	    			<label><ww:property value="#request.EUR.askValue" /></label>
	    			<ww:if test="#request.EUR.askFlag == 1"><br><br><img alt="" src="resources/image/up.png"></ww:if>
    				<ww:elseif test="#request.EUR.askFlag == -1"><br><br><img alt="" src="resources/image/down.png"></ww:elseif>
	    		</div>
	    		<div class="amount">
	    			AMOUNT:<input type="text" id="EAmount" name="amount" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"> 
	    		</div>
	    	</div>
	    	<div class="modules" style="margin-right: 0;" id="BGBP">
	    		<div class="modulesTopBlue">
	    			<span>GBP/CNY&nbsp;SPOT</span>
	    		</div>
    			<div class="blue" onclick="selectOneClickTradeType('0','GBP','GAmount','<ww:property value='#request.GBP.bidValue' />')">
    				<label>I SELL GBP</label><br>
    				<label><ww:property value="#request.GBP.bidValue" /></label>
    				<ww:if test="#request.GBP.bidFlag == 1"><br><br><img alt="" src="resources/image/up.png"></ww:if>
    				<ww:elseif test="#request.GBP.bidFlag == -1"><br><br><img alt="" src="resources/image/down.png"></ww:elseif>
    			</div>
	    		<div class="blue" onclick="selectOneClickTradeType('1','GBP','GAmount','<ww:property value='#request.GBP.askValue' />')">
	    			<label>I BUY GBP</label><br>
	    			<label><ww:property value="#request.GBP.askValue" /></label>
	    			<ww:if test="#request.GBP.askFlag == 1"><br><br><img alt="" src="resources/image/up.png"></ww:if>
    				<ww:elseif test="#request.GBP.askFlag == -1"><br><br><img alt="" src="resources/image/down.png"></ww:elseif>
	    		</div>
	    		<div class="amount">
	    			AMOUNT:<input type="text" id="GAmount" name="amount" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"> 
	    		</div>
	    	</div>
    	</div>
    	<div class="container">
	    	
	    	<div class="modules" id="BAUD">
	    		<div class="modulesTopBlue">
	    			<span>AUD/CNY&nbsp;SPOT</span>
	    		</div>
	    		<div class="blue" onclick="selectOneClickTradeType('0','AUD','AAmount','<ww:property value='#request.AUD.bidValue' />')">
	    			<label>I SELL AUD</label><br>
	    			<label><ww:property value="#request.AUD.bidValue" /></label>
	    			<ww:if test="#request.AUD.bidFlag == 1"><br><br><img alt="" src="resources/image/up.png"></ww:if>
    				<ww:elseif test="#request.AUD.bidFlag == -1"><br><br><img alt="" src="resources/image/down.png"></ww:elseif>
	    		</div>
		    	<div class="blue" onclick="selectOneClickTradeType('1','AUD','AAmount','<ww:property value='#request.AUD.askValue' />')">
		    		<label>I BUY AUD</label><br>
		    		<label><ww:property value="#request.AUD.askValue" /></label>
		    		<ww:if test="#request.AUD.askFlag == 1"><br><br><img alt="" src="resources/image/up.png"></ww:if>
    				<ww:elseif test="#request.AUD.askFlag == -1"><br><br><img alt="" src="resources/image/down.png"></ww:elseif>
		    	</div>
		    	<div class="amount">
	    			AMOUNT:<input type="text" id="AAmount" name="amount" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"> 
	    		</div>
	    	</div>
	    	<div class="modules" id="BJPY">
	    		<div class="modulesTopBlue">
	    			<span>JPY/CNY&nbsp;SPOT</span>
	    		</div>
	    		<div class="blue" onclick="selectOneClickTradeType('0','JPY','JAmount','<ww:property value='#request.JPY.bidValue' />')">
	    			<label>I SELL JPY</label><br>
	    			<label><ww:property value="#request.JPY.bidValue" /></label>
	    			<ww:if test="#request.JPY.bidFlag == 1"><br><br><img alt="" src="resources/image/up.png"></ww:if>
    				<ww:elseif test="#request.JPY.bidFlag == -1"><br><br><img alt="" src="resources/image/down.png"></ww:elseif>
	    		</div>
		    	<div class="blue" onclick="selectOneClickTradeType('1','JPY','JAmount','<ww:property value='#request.JPY.askValue' />')">
		    		<label>I BUY JPY</label><br>
		    		<label><ww:property value="#request.JPY.askValue" /></label>
		    		<ww:if test="#request.JPY.askFlag == 1"><br><br><img alt="" src="resources/image/up.png"></ww:if>
    				<ww:elseif test="#request.JPY.askFlag == -1"><br><br><img alt="" src="resources/image/down.png"></ww:elseif>
		    	</div>
		    	<div class="amount">
	    			AMOUNT:<input type="text" id="JAmount" name="amount" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"> 
	    		</div>
	    	</div>
	    	<div class="modules" style="margin-right: 0;" id="BCAD">
	    		<div class="modulesTopBlue">
	    			<span>CAD/CNY&nbsp;SPOT</span>
	    		</div>
	    		<div class="blue" onclick="selectOneClickTradeType('0','CAD','CAmount','<ww:property value='#request.CAD.bidValue' />')">
	    			<label>I SELL CAD</label><br>
	    			<label><ww:property value="#request.CAD.bidValue" /></label>
	    			<ww:if test="#request.CAD.bidFlag == 1"><br><br><img alt="" src="resources/image/up.png"></ww:if>
    				<ww:elseif test="#request.CAD.bidFlag == -1"><br><br><img alt="" src="resources/image/down.png"></ww:elseif>
	    		</div>
		    	<div class="blue" onclick="selectOneClickTradeType('1','CAD','CAmount','<ww:property value='#request.CAD.askValue' />')">
		    		<label>I BUY CAD</label><br>
		    		<label><ww:property value="#request.CAD.askValue" /></label>
		    		<ww:if test="#request.CAD.askFlag == 1"><br><br><img alt="" src="resources/image/up.png"></ww:if>
    				<ww:elseif test="#request.CAD.askFlag == -1"><br><br><img alt="" src="resources/image/down.png"></ww:elseif>
		    	</div>
		    	<div class="amount">
	    			AMOUNT:<input type="text" id="CAmount" name="amount" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"> 
	    		</div>
	    	</div>
    	</div>

    </div><!-- end the centerDiv -->
  </form>
    <div id="ectable">
    	<form action="CNYtradeQuery.action" method="post" style="margin:0;">
	    	<label style="margin-right: 50px; font-weight: bolder;">交易记录</label>
	    	交易类型:<select name="tradeType">
	    	<option value=""></option>
	    	<ww:iterator value="#request.tradeTypeList">
	    		<option value="<ww:property value='id.codeVal' />"<ww:if test="#request.tradeType == id.codeVal">selected</ww:if>>
	    			<ww:property value="descr"/>
	    		</option>
	    	</ww:iterator>
	    	
	    	</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    	交易状态:<select name="tradeStatus">
	    	<option value=""></option>
	    	<ww:iterator value="#request.tradeStatusList">
	    		<option value="<ww:property value='id.codeVal' />"<ww:if test="#request.tradeStatus == id.codeVal">selected</ww:if>>
	    			<ww:property value="descr"/>
	    		</option>
	    	</ww:iterator>
	    	</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    	<input id="button" type="submit" value="搜  索" />
	    	<input type="hidden" name="page" value="OC"> 
	    	<br>
	    </form>
   		<ec:table items="collection" var="c" action=""
				border="" cellpadding="0" cellspacing="0" style="eXtremeTable" >
				<ec:row>
					<ec:column property="tradeNo" title="交易序号" width="100"
						headerStyle="text-align:center" style="text-align:left" viewsAllowed="html" >
					</ec:column>
					<ec:column property="tradeType" title="交易类型" width="140"
						headerStyle="text-align:center" style="text-align:left"/>
					<ec:column property="currencyBuy" title="买入货币" width="100"
						headerStyle="text-align:center" style="text-align:left"/>
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
					     document.write('<a href="CNYtradeDetail.action?table='+'${c.tableName }'+'&tradeNo='+'${c.tradeNo }'+'&tradeStatus='+'${c.tradeStatus }'+'">查看</a>');
					 </script>
					 </ec:column>
			</ec:row>
		</ec:table>	
    	</div>
  </div>

  </body>
</html>
