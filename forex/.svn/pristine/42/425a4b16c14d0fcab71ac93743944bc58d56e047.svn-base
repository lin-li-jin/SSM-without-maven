<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib uri="extremecomponents" prefix="ec"%>
<%@ taglib uri="webwork" prefix="ww"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <%--<meta http-equiv="refresh" content="10">--%>
  <title>外币对交易</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
		#all{ margin: 0; padding:0; width: 1003px; height: auto;}
		.colorSet{color: red; font-size: 16px;font-weight: bolder;}
		#top{margin: 0; padding:3px 5px; height: 24px; width:840px; line-height: 24px; background-image: url("images/b.png"); color: white;}
		#center{margin: 0; padding:0; height: 340px !important; height:320px; width:850px; font-size: 14px;margin-top: 10px;border-bottom:1px solid black;}
		.container{margin: 0; padding: 0; height: 170px !important;height:160px; width: 100%;  }
		.modules{margin: 0; padding: 0; height:125px; width:248px;float: left; margin-right: 40px;font-size: 14px; }
		.amount{margin: 0; padding: 0; height:30px; width:248px;float: left; margin-top: 5px;}
		.modulesTopRed{margin: 0; padding: 0; height:25px;width: 242px; background-color: #ce3231; float: left; margin-bottom: 2px;}
		.modulesTopBlue{margin: 0; padding: 0; height:25px;width: 242px; background-image: url("images/b.png"); float: left;margin-bottom: 2px;}
		.blue{margin: 0; padding: 0; height: 100px;width:119px !important; width:121px;background-color: #dee7ff; float:left; border: 1px #428BC2 solid; text-align: center; padding-top: 10px;cursor:pointer}
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
		function oneClickTrade(direction,ccy1,ccy2,id,price){
			var amount = document.getElementById(id).value;
			if (amount == '' || amount%100 != 0){
			alert("请输入100整数倍交易金额！");
			return false;
			}
			window.location.href = "oneClickOneTradeRecordAdd.action?direct="+direction+"&ccy1="+ccy1+"&ccy2="+ccy2+"&amount="+amount+"&price="+price;
		}
		
		$(document).ready(function(){
			var t=setTimeout("ajaxRefreshModule()",10000);
		})
		
		function ajaxRefreshModule(){
			$.post("foreignPageRefresh.action",function(data){
				//alert(data);
				var aData=data.split("$");
				var data1=aData[0];
				//alert(data1);
				//alert(data2);
				var objB=eval("("+data1+")");
				$(".blue").remove();
				$(".modulesTopBlue").remove();
				addModule("BEURUSD",objB.BEURUSD,"EUR","USD","EAmount");
				addModule("BGBPUSD",objB.BGBPUSD,"GBP","USD","GAmount");
				addModule("BAUDUSD",objB.BAUDUSD,"AUD","USD","AAmount");
				addModule("BUSDJPY",objB.BUSDJPY,"USD","JPY","JAmount");
				addModule("BUSDCAD",objB.BUSDCAD,"USD","CAD","CAmount");
			});
			//设置刷新时间为10秒
			var t=setTimeout("ajaxRefreshModule()",10000);
		}
		
		function addModule(id,obj,type3,type4,amountID){
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
			$("#"+id).prepend("<div class='blue' onclick=oneClickTrade('1','"+type3+"','"+type4+"','"+amountID+"',"+obj.bidValue+") > <label>I BUY "+type3+"</label><br/><label>"+obj.askValue+"</label><br/><br/>"+askImg+"</div>");
			$("#"+id).prepend("<div class='blue' onclick=oneClickTrade('0','"+type3+"','"+type4+"','"+amountID+"',"+obj.bidValue+") > <label>I SELL "+type3+"</label><br/><label>"+obj.bidValue+"</label><br/><br/>"+bidImg+"</div>");
			$("#"+id).prepend("<div class='modulesTopBlue'><span>"+type3+"/"+type4+"&nbsp;SPOT</span></div>");
		}
	</script>
  </head>
  
  <body>
  <div id="all">
    <form action="">
  	 <div id="top">
    	一口价交易<input type="checkbox" name="oneClick" checked="checked" onchange="window.location.href='foreignPageInit.action'"> 
    	<span style="margin-left: 30px;">交易账户:</span>
    	<label>外币账户</label><label>042166581207131</label>
    </div>
    <div id="center">
    	<div class="container">
    		<div class="modules" id="BEURUSD">
	    		<div class="modulesTopBlue">
	    			<span>EUR/USD&nbsp;SPOT</span>
	    		</div>
	    		<div class="blue" onclick="oneClickTrade('0','EUR','USD','EAmount','<ww:property value='#request.EURUSD.bidValue' />')">
	    			<label>I SELL EUR</label><br>
	    			<label><ww:property value="#request.EURUSD.bidValue" /></label>
	    			<ww:if test="#request.EURUSD.bidFlag == 1"><br><br><img alt="" src="resources/image/up.png"></ww:if>
    				<ww:elseif test="#request.EURUSD.bidFlag == -1"><br><br><img alt="" src="resources/image/down.png"></ww:elseif>
	    		</div>
	    		<div class="blue" onclick="oneClickTrade('1','EUR','USD','EAmount','<ww:property value='#request.EURUSD.askValue' />')">
	    			<label>I BUY EUR</label><br>
	    			<label><ww:property value="#request.EURUSD.askValue" /></label>
	    			<ww:if test="#request.EURUSD.askFlag == 1"><br><br><img alt="" src="resources/image/up.png"></ww:if>
    				<ww:elseif test="#request.EURUSD.askFlag == -1"><br><br><img alt="" src="resources/image/down.png"></ww:elseif>
	    		</div>
	    		<div class="amount">
	    			AMOUNT:<input type="text" id="EAmount" name="amount" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"> 
	    		</div>
	    	</div>
	    	<div class="modules" id="BGBPUSD">
	    		<div class="modulesTopBlue">
	    			<span>GBP/USD&nbsp;SPOT</span>
	    		</div>
	    		<div class="blue" onclick="oneClickTrade('0','GBP','USD','GAmount','<ww:property value='#request.GBPUSD.bidValue' />')">
	    			<label>I SELL GBP</label><br>
	    			<label><ww:property value="#request.GBPUSD.bidValue" /></label>
	    			<ww:if test="#request.GBPUSD.bidFlag == 1"><br><br><img alt="" src="resources/image/up.png"></ww:if>
    				<ww:elseif test="#request.GBPUSD.bidFlag == -1"><br><br><img alt="" src="resources/image/down.png"></ww:elseif>
	    		</div>
	    		<div class="blue" onclick="oneClickTrade('1','GBP','USD','GAmount','<ww:property value='#request.GBPUSD.askValue' />')">
	    			<label>I BUY GBP</label><br>
	    			<label><ww:property value="#request.GBPUSD.askValue" /></label>
	    			<ww:if test="#request.GBPUSD.askFlag == 1"><br><br><img alt="" src="resources/image/up.png"></ww:if>
    				<ww:elseif test="#request.GBPUSD.askFlag == -1"><br><br><img alt="" src="resources/image/down.png"></ww:elseif>
	    		</div>
	    		<div class="amount">
	    			AMOUNT:<input type="text" id="GAmount" name="amount" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"> 
	    		</div>
	    	</div>
	    	<div class="modules" style="margin-right: 0;" id="BAUDUSD">
	    		<div class="modulesTopBlue">
	    			<span>AUD/USD&nbsp;SPOT</span>
	    		</div>
	    		<div class="blue" onclick="oneClickTrade('0','AUD','USD','AAmount','<ww:property value='#request.AUDUSD.binValue' />')">
	    			<label>I SELL AUD</label><br>
	    			<label><ww:property value="#request.AUDUSD.bidValue" /></label>
	    			<ww:if test="#request.AUDUSD.bidFlag == 1"><br><br><img alt="" src="resources/image/up.png"></ww:if>
    				<ww:elseif test="#request.AUDUSD.bidFlag == -1"><br><br><img alt="" src="resources/image/down.png"></ww:elseif>
	    		</div>
		    	<div class="blue" onclick="oneClickTrade('1','AUD','USD','AAmount','<ww:property value='#request.AUDUSD.askValue' />')">
		    		<label>I BUY AUD</label><br>
		    		<label><ww:property value="#request.AUDUSD.askValue" /></label>
		    		<ww:if test="#request.AUDUSD.askFlag == 1"><br><br><img alt="" src="resources/image/up.png"></ww:if>
    				<ww:elseif test="#request.AUDUSD.askFlag == -1"><br><br><img alt="" src="resources/image/down.png"></ww:elseif>
		    	</div>
		    	<div class="amount">
	    			AMOUNT:<input type="text" id="AAmount" name="amount" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"> 
	    		</div>
	    	</div>
    	</div>
    	<div class="container">
	    	<div class="modules" id="BUSDJPY">
	    		<div class="modulesTopBlue">
	    			<span>USD/JPY&nbsp;SPOT</span>
	    		</div>
	    		<div class="blue" onclick="oneClickTrade('0','USD','JPY','JAmount','<ww:property value='#request.USDJPY.bidValue' />')">
	    			<label>I SELL USD</label><br>
	    			<label><ww:property value="#request.USDJPY.bidValue" /></label>
	    			<ww:if test="#request.USDJPY.bidFlag == 1"><br><br><img alt="" src="resources/image/up.png"></ww:if>
    				<ww:elseif test="#request.USDJPY.bidFlag == -1"><br><br><img alt="" src="resources/image/down.png"></ww:elseif>
	    		</div>
		    	<div class="blue" onclick="oneClickTrade('1','USD','JPY','JAmount','<ww:property value='#request.USDJPY.askValue' />')">
		    		<label>I BUY USD</label><br>
		    		<label><ww:property value="#request.USDJPY.askValue" /></label>
		    		<ww:if test="#request.USDJPY.askFlag == 1"><br><br><img alt="" src="resources/image/up.png"></ww:if>
    				<ww:elseif test="#request.USDJPY.askFlag == -1"><br><br><img alt="" src="resources/image/down.png"></ww:elseif>
		    	</div>
		    	<div class="amount">
	    			AMOUNT:<input type="text" id="JAmount" name="amount" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"> 
	    		</div>
	    	</div>
	    	<div class="modules" id="BUSDCAD">
	    		<div class="modulesTopBlue">
	    			<span>USD/CAD&nbsp;SPOT</span>
	    		</div>
	    		<div class="blue" onclick="oneClickTrade('0','USD','CAD','CAmount','<ww:property value='#request.USDCAD.bidValue' />')">
	    			<label>I SELL USD</label><br>
	    			<label><ww:property value="#request.USDCAD.bidValue" /></label>
	    			<ww:if test="#request.USDCAD.bidFlag == 1"><br><br><img alt="" src="resources/image/up.png"></ww:if>
    				<ww:elseif test="#request.USDCAD.bidFlag == -1"><br><br><img alt="" src="resources/image/down.png"></ww:elseif>
	    		</div>
	    		<div class="blue" onclick="oneClickTrade('1','USD','CAD','CAmount','<ww:property value='#request.USDCAD.askValue' />')">
	    			<label>I BUY USD</label><br>
	    			<label><ww:property value="#request.USDCAD.askValue" /></label>
	    			<ww:if test="#request.USDCAD.askFlag == 1"><br><br><img alt="" src="resources/image/up.png"></ww:if>
    				<ww:elseif test="#request.USDCAD.askFlag == -1"><br><br><img alt="" src="resources/image/down.png"></ww:elseif>
	    		</div>
	    		<div class="amount">
	    			AMOUNT:<input type="text" id="CAmount" name="amount" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"> 
	    		</div>
	    	</div>
    	</div>

    </div><!-- end the centerDiv -->
  </form>
    <div id="ectable">
    	<form action="foreignTradeQuery.action" method="post" style="margin:0;">
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
					    document.write('<a href="foreignTradeDetail.action?table='+'${c.tableName }'+'&tradeNo='+'${c.tradeNo }'+'&tradeStatus='+'${c.tradeStatus }'+'">查看</a>');
					 </script>
					 </ec:column>
			</ec:row>
		</ec:table>	
    	</div>
  </div>

  </body>
</html>
