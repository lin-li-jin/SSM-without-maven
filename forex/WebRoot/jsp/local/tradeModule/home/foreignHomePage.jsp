<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib uri="extremecomponents" prefix="ec"%>
<%@ taglib uri="webwork" prefix="ww"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <!-- <meta http-equiv="refresh" content="10"> -->
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
		#center{margin: 0; padding:0; height: 320px; width:850px; font-size: 14px;margin-top: 10px; overflow-y:auto; }
		.container{margin: 0; padding: 0; height: 140px; width: 100%;  margin-bottom: 15px;}
		.modules{margin: 0; padding: 0; height:125px; width:248px;float: left; margin-right: 40px;font-size: 14px; }
		.modulesTopRed{margin: 0; padding: 0; height:25px;width: 242px; background-color: #ce3231; float: left; margin-bottom: 2px;}
		.modulesTopBlue{margin: 0; padding: 0; height:25px;width: 242px; background-image: url("images/b.png"); float: left;margin-bottom: 2px;}
		.red{margin: 0; padding: 0; height: 100px;width:119px !important; width:121px;background-color:#ffdbe7 ;float: left; border: 1px #CC3333 solid; text-align: center; padding-top: 10px;}
		.blue{margin: 0; padding: 0; height: 100px;width:119px !important;width:121px;background-color: #dee7ff; float:left; border: 1px #428BC2 solid; text-align: center;padding-top: 10px;cursor:pointer}
		.modules span { line-height: 25px; font-size: 16px; color: white; margin-left: 10px; font-weight: bolder;}
		.modules label {  font-size: 16px; color: black; font-weight: bolder;cursor:pointer}
		#ectable{margin: 0; padding: 0; width: 860px; height: auto; }
	</style>
	<link rel="stylesheet" href="resources/css/extremecomponents.css"	type="text/css" />
	<script src="resources/ponyjs/jquery.js" type="text/javascript"></script>
	
	<script type="text/javascript">
	
		function selectTradeType(direction,ccy1,ccy2,bid,ask){
			window.location.href = "stopLossTradePageInit.action?direct="+direction+"&ccy1="+ccy1+"&ccy2="+ccy2+"&bid="+bid+"&ask="+ask;
		}
		$(function(){
			$('input:checkbox').click(function () {
					this.blur();   
					this.focus(); 
			});
			/* alert("clientWidth:" + document.body.clientWidth);
			alert("clientHeight:" + document.body.clientHeight);
			alert("offsetWidth:" + document.body.offsetWidth);
			alert("offsetHeight:" + document.body.offsetHeight);
			alert("scrollWidth:" + document.body.scrollWidth);
			alert("scrollHeight:" + document.body.scrollHeight); */
		});
		function selectAskTradeType(direction,ccy1,ccy2,price){
			//window.location.href = "spotTradePageInit.action?direct="+direction+"&ccy1="+ccy+"&ccy2=USD"+"&price="+price;
			pageURL="spotTradePageInit.action?direct="+direction+"&ccy1="+ccy1+"&ccy2="+ccy2+"&price="+price;
 			window.open(pageURL);
		}
		
		$(document).ready(function(){
			var t=setTimeout("ajaxRefreshModule()",10000);
		})
		
		function ajaxRefreshModule(){
			$.post("foreignPageRefresh.action",function(data){
				//alert(data);
				var aData=data.split("$");
				var data1=aData[0];
				var data2=aData[1];
				//alert(data1);
				//alert(data2);
				var objB=eval("("+data1+")");
				var objA=eval("("+data2+")");
				$(".blue").remove();
				$(".red").remove();
				$(".modulesTopBlue").remove();
				$(".modulesTopRed").remove();
				//addModule("BUSD",objB.BUSD,"USD","B");
				addModule("BEURUSD",objB.BEURUSD,"EUR","B","EUR","USD");
				addModule("BGBPUSD",objB.BGBPUSD,"GBP","B","GBP","USD");
				addModule("BAUDUSD",objB.BAUDUSD,"AUD","B","AUD","USD");
				addModule("BUSDJPY",objB.BUSDJPY,"JPY","B","USD","JPY");
				addModule("BUSDCAD",objB.BUSDCAD,"CAD","B","USD","CAD");
				//addModule("AUSD",objA.AUSD,"USD","A");
				addModule("AUSDCAD",objA.AUSDCAD,"EUR","A","USD","CAD");
				addModule("AEURUSD",objA.AEURUSD,"GBP","A","EUR","USD");
				addModule("AGBPUSD",objA.AGBPUSD,"AUD","A","GBP","USD");
				addModule("AAUDUSD",objA.AAUDUSD,"JPY","A","AUD","USD");
				addModule("AUSDJPY",objA.AUSDJPY,"CAD","A","USD","JPY");
			});
			//设置刷新时间为10秒
			var t=setTimeout("ajaxRefreshModule()",10000);
		}
		
		function addModule(id,obj,type2,type,ccy1,ccy2){
			//type2无用处
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
			if(type=='B'){
				$("#"+id).append("<div class='modulesTopBlue'><span>"+ccy1+"/"+ccy2+"&nbsp;SPOT</span></div>");
				$("#"+id).append("<div class='blue' onclick=selectTradeType('0','"+ccy1+"','"+ccy2+"',"+obj.bidValue+","+obj.askValue+") > <label>I SELL "+ccy1+"</label><br/><label>"+obj.askValue+"</label><br/><br/>"+askImg+"</div>");
				$("#"+id).append("<div class='blue' onclick=selectTradeType('1','"+ccy1+"','"+ccy2+"',"+obj.bidValue+","+obj.askValue+") > <label>I BUY "+ccy1+"</label><br/><label>"+obj.bidValue+"</label><br/><br/>"+bidImg+"</div>");
			}else{
				$("#"+id).append("<div class='modulesTopRed'><span>"+ccy1+"/"+ccy2+"&nbsp;SPOT</span></div>");
				$("#"+id).append("<div class='red' onclick=selectAskTradeType('0','"+ccy1+"','"+ccy2+"',"+obj.askValue+") > <label>I SELL "+ccy1+"</label><br/><label>"+obj.askValue+"</label><br/><br/>"+askImg+"</div>");
				$("#"+id).append("<div class='red' onclick=selectAskTradeType('1','"+ccy1+"','"+ccy2+"',"+obj.bidValue+") > <label>I BUY "+ccy1+"</label><br/><label>"+obj.bidValue+"</label><br/><br/>"+bidImg+"</div>");
			}
		}
	</script>
  </head>
  
  <body>
  <div id="all">
    <form action="">
  	 <div id="top">
    	一口价交易<input type="checkbox" name="oneClick" onchange="window.location.href='foreignOneClickPageInit.action'"> 
    	<span style="margin-left: 30px;">交易账户:</span>
    	<label>外币账户</label><label>042166581207131</label>
    </div>
    <div id="center">
    	<div class="container">
    		<div class="modules"  id="BEURUSD">
	    		<div class="modulesTopBlue">
	    			<span>EUR/USD&nbsp;SPOT</span>
	    		</div>
	    		<div class="blue" onclick="selectTradeType('0','EUR','USD','<ww:property value='#request.BEURUSD.bidValue' />','<ww:property value='#request.BEURUSD.askValue' />')">
	    			<label>I SELL EUR</label><br>
	    			<label><ww:property value="#request.BEURUSD.bidValue" /></label><br><br>
	    			<ww:if test="#request.BEURUSD.askFlag == 1"><img alt="" src="resources/image/up.png"></ww:if><ww:elseif test="#request.BEURUSD.askFlag == -1"><img alt="" src="resources/image/down.png"></ww:elseif></div>
	    		<div class="blue" onclick="selectTradeType('1','EUR','USD','<ww:property value='#request.BEURUSD.bidValue' />','<ww:property value='#request.BEURUSD.askValue' />')">
	    			<label>I BUY EUR</label>
	    			<label><ww:property value="#request.BEURUSD.askValue" /></label><br><br>
	    			<ww:if test="#request.BEURUSD.bidFlag == 1"><img alt="" src="resources/image/up.png"></ww:if><ww:elseif test="#request.BEURUSD.bidFlag == -1"><img alt="" src="resources/image/down.png"></ww:elseif></div>
	    	</div>
	    	<div class="modules"  id="BGBPUSD">
	    		<div class="modulesTopBlue">
	    			<span>GBP/USD&nbsp;SPOT</span>
	    		</div>
	    		<div class="blue" onclick="selectTradeType('0','GBP','USD','<ww:property value='#request.BGBPUSD.bidValue' />','<ww:property value='#request.BGBPUSD.askValue' />')">
	    			<label>I SELL GBP</label><br>
	    			<label><ww:property value="#request.BGBPUSD.bidValue" /></label><br><br>
	    			<ww:if test="#request.BGBPUSD.askFlag == 1"><img alt="" src="resources/image/up.png"></ww:if><ww:elseif test="#request.BGBPUSD.askFlag == -1"><img alt="" src="resources/image/down.png"></ww:elseif></div>
	    		<div class="blue" onclick="selectTradeType('1','GBP','USD','<ww:property value='#request.BGBPUSD.bidValue' />','<ww:property value='#request.BGBPUSD.askValue' />')">
	    			<label>I BUY GBP</label>
	    			<label><ww:property value="#request.BGBPUSD.askValue" /></label><br><br>
	    			<ww:if test="#request.BGBPUSD.bidFlag == 1"><img alt="" src="resources/image/up.png"></ww:if><ww:elseif test="#request.BGBPUSD.bidFlag == -1"><img alt="" src="resources/image/down.png"></ww:elseif></div>
	    	</div>
	    	<div class="modules" style="margin-right: 0;"  id="BAUDUSD">
	    		<div class="modulesTopBlue">
	    			<span>AUD/USD&nbsp;SPOT</span>
	    		</div>
	    		<div class="blue" onclick="selectTradeType('0','AUD','USD','<ww:property value='#request.BAUDUSD.bidValue' />','<ww:property value='#request.BAUDUSD.askValue' />')">
	    			<label>I SELL AUD</label><br>
	    			<label><ww:property value="#request.BAUDUSD.bidValue" /></label><br><br>
	    			<ww:if test="#request.BAUDUSD.askFlag == 1"><img alt="" src="resources/image/up.png"></ww:if><ww:elseif test="#request.BAUDUSD.askFlag == -1"><img alt="" src="resources/image/down.png"></ww:elseif></div>
		    	<div class="blue" onclick="selectTradeType('1','AUD','USD','<ww:property value='#request.BAUDUSD.bidValue' />','<ww:property value='#request.BAUDUSD.askValue' />')">
		    		<label>I BUY AUD</label>
		    		<label><ww:property value="#request.BAUDUSD.askValue" /></label><br><br>
		    		<ww:if test="#request.BAUDUSD.bidFlag == 1"><img alt="" src="resources/image/up.png"></ww:if><ww:elseif test="#request.BAUDUSD.bidFlag == -1"><img alt="" src="resources/image/down.png"></ww:elseif></div>
	    	</div>
    	</div>
    	<div class="container">
    		<div class="modules"  id="BUSDJPY">
	    		<div class="modulesTopBlue">
	    			<span>USD/JPY&nbsp;SPOT</span>
	    		</div>
	    		<div class="blue" onclick="selectTradeType('0','USD','JPY','<ww:property value='#request.BUSDJPY.bidValue' />','<ww:property value='#request.BUSDJPY.askValue' />')">
	    			<label>I SELL USD</label><br>
	    			<label><ww:property value="#request.BUSDJPY.bidValue" /></label><br><br>
	    			<ww:if test="#request.BUSDJPY.askFlag == 1"><img alt="" src="resources/image/up.png"></ww:if><ww:elseif test="#request.BUSDJPY.askFlag == -1"><img alt="" src="resources/image/down.png"></ww:elseif></div>
	    		<div class="blue" onclick="selectTradeType('1','USD','JPY','<ww:property value='#request.BUSDJPY.bidValue' />','<ww:property value='#request.BUSDJPY.askValue' />')">
	    			<label>I BUY USD</label>
	    			<label><ww:property value="#request.BUSDJPY.askValue" /></label><br><br>
	    			<ww:if test="#request.BUSDJPY.bidFlag == 1"><img alt="" src="resources/image/up.png"></ww:if><ww:elseif test="#request.BUSDJPY.bidFlag == -1"><img alt="" src="resources/image/down.png"></ww:elseif></div>
	    	</div>
	    	<div class="modules" id="BUSDCAD">
	    		<div class="modulesTopBlue">
	    			<span>USD/CAD&nbsp;SPOT</span>
	    		</div>
	    		<div class="blue" onclick="selectTradeType('0','USD','CAD','<ww:property value='#request.BUSDCAD.bidValue' />','<ww:property value='#request.BUSDCAD.askValue' />')">
	    			<label>I SELL USD</label><br>
	    			<label><ww:property value="#request.BUSDCAD.bidValue" /></label><br><br>
	    			<ww:if test="#request.BUSDCAD.askFlag == 1"><img alt="" src="resources/image/up.png"></ww:if><ww:elseif test="#request.BUSDCAD.askFlag == -1"><img alt="" src="resources/image/down.png"></ww:elseif></div>
	    		<div class="blue" onclick="selectTradeType('1','USD','CAD','<ww:property value='#request.BUSDCAD.bidValue' />','<ww:property value='#request.BUSDCAD.askValue' />')">
	    			<label>I BUY USD</label>
	    			<label><ww:property value="#request.BUSDCAD.askValue" /></label><br><br>
	    			<ww:if test="#request.BUSDCAD.bidFlag == 1"><img alt="" src="resources/image/up.png"></ww:if><ww:elseif test="#request.BUSDCAD.bidFlag == -1"><img alt="" src="resources/image/down.png"></ww:elseif></div>
	    	</div>
	    	<div class="modules" style="margin-right: 0;" id="AUSDCAD">
	    		<div class="modulesTopRed">
	    			<span>USD/CAD&nbsp;SPOT</span>
	    		</div>
	    		<div class="red" onclick="selectAskTradeType('0','USD','CAD','<ww:property value="#request.AUSDCAD.askValue" />')">
	    			<label>I SELL USD</label><br>
	    			<label><ww:property value="#request.AUSDCAD.bidValue" /></label><br><br>
	    			<ww:if test="#request.AUSDCAD.askFlag == 1"><img alt="" src="resources/image/up.png"></ww:if><ww:elseif test="#request.AUSDCAD.askFlag == -1"><img alt="" src="resources/image/down.png"></ww:elseif></div>
	    		<div class="red" onclick="selectAskTradeType('1','USD','CAD','<ww:property value="#request.AUSDCAD.bidValue" />')">
	    			<label>I BUY USD</label>
	    			<label><ww:property value="#request.AUSDCAD.askValue" /></label><br><br>
	    			<ww:if test="#request.AUSDCAD.bidFlag == 1"><img alt="" src="resources/image/up.png"></ww:if><ww:elseif test="#request.AUSDCAD.bidFlag == -1"><img alt="" src="resources/image/down.png"></ww:elseif></div>
	    	</div>
    	</div>
    	<div class="container">
	    	<div class="modules" id="AEURUSD">
	    		<div class="modulesTopRed">
	    			<span>EUR/USD&nbsp;SPOT</span>
	    		</div>
    			<div class="red" onclick="selectAskTradeType('0','EUR','USD','<ww:property value="#request.AEURUSD.askValue" />')">
    				<label>I SELL EUR</label><br>
    				<label><ww:property value="#request.AEURUSD.bidValue" /></label><br><br>
    				<ww:if test="#request.AEURUSD.askFlag == 1"><img alt="" src="resources/image/up.png"></ww:if><ww:elseif test="#request.AEURUSD.askFlag == -1"><img alt="" src="resources/image/down.png"></ww:elseif></div>
	    		<div class="red" onclick="selectAskTradeType('1','EUR','USD','<ww:property value="#request.AEURUSD.bidValue" />')">
	    			<label>I BUY EUR</label>
	    			<label><ww:property value="#request.AEURUSD.askValue" /></label><br><br>
	    			<ww:if test="#request.AEURUSD.bidFlag == 1"><img alt="" src="resources/image/up.png"></ww:if><ww:elseif test="#request.AEURUSD.bidFlag == -1"><img alt="" src="resources/image/down.png"></ww:elseif></div>
	    	</div>
	    	<div class="modules" id="AGBPUSD">
	    		<div class="modulesTopRed">
	    			<span>GBP/USD&nbsp;SPOT</span>
	    		</div>
	    		<div class="red" onclick="selectAskTradeType('0','GBP','USD','<ww:property value="#request.AGBPUSD.askValue" />')">
	    			<label>I SELL GBP</label><br>
	    			<label><ww:property value="#request.AGBPUSD.bidValue" /></label><br><br>
	    			<ww:if test="#request.AGBPUSD.askFlag == 1"><img alt="" src="resources/image/up.png"></ww:if><ww:elseif test="#request.AGBPUSD.askFlag == -1"><img alt="" src="resources/image/down.png"></ww:elseif></div>
		    	<div class="red" onclick="selectAskTradeType('1','GBP','USD','<ww:property value="#request.AGBPUSD.bidValue" />')">
		    		<label>I BUY GBP</label>
		    		<label><ww:property value="#request.AGBPUSD.askValue" /></label><br><br>
		    		<ww:if test="#request.AGBPUSD.bidFlag == 1"><img alt="" src="resources/image/up.png"></ww:if><ww:elseif test="#request.AGBPUSD.bidFlag == -1"><img alt="" src="resources/image/down.png"></ww:elseif></div>
	    	</div>
	    	<div class="modules" style="margin-right: 0;" id="AAUDUSD">
	    		<div class="modulesTopRed">
	    			<span>AUD/USD&nbsp;SPOT</span>
	    		</div>
	    		<div class="red" onclick="selectAskTradeType('0','AUD','USD','<ww:property value="#request.AAUDUSD.askValue" />')">
	    			<label>I SELL AUD</label><br>
	    			<label><ww:property value="#request.AAUDUSD.bidValue" /></label><br><br>
	    			<ww:if test="#request.AAUDUSD.askFlag == 1"><img alt="" src="resources/image/up.png"></ww:if><ww:elseif test="#request.AAUDUSD.askFlag == -1"><img alt="" src="resources/image/down.png"></ww:elseif></div>
		    	<div class="red" onclick="selectAskTradeType('1','AUD','USD','<ww:property value="#request.AAUDUSD.bidValue" />')">
		    		<label>I BUY AUD</label>
		    		<label><ww:property value="#request.AAUDUSD.askValue" /></label><br><br>
		    		<ww:if test="#request.AAUDUSD.bidFlag == 1"><img alt="" src="resources/image/up.png"></ww:if><ww:elseif test="#request.AAUDUSD.bidFlag == -1"><img alt="" src="resources/image/down.png"></ww:elseif></div>
	    	</div>
    	</div>
    	<div class="container">
	    	<div class="modules" id="AUSDJPY">
	    		<div class="modulesTopRed">
	    			<span>USD/JPY&nbsp;SPOT</span>
	    		</div>
	    		<div class="red" onclick="selectAskTradeType('0','USD','JPY','<ww:property value="#request.AUSDJPY.askValue" />')">
	    			<label>I SELL USD</label><br>
	    			<label><ww:property value="#request.AUSDJPY.bidValue" /></label><br><br>
	    			<ww:if test="#request.AUSDJPY.askFlag == 1"><img alt="" src="resources/image/up.png"></ww:if><ww:elseif test="#request.AUSDJPY.askFlag == -1"><img alt="" src="resources/image/down.png"></ww:elseif></div>
		    	<div class="red" onclick="selectAskTradeType('1','USD','JPY','<ww:property value="#request.AUSDJPY.bidValue" />')">
		    		<label>I BUY USD</label>
		    		<label><ww:property value="#request.AUSDJPY.askValue" /></label><br><br>
		    		<ww:if test="#request.AUSDJPY.bidFlag == 1"><img alt="" src="resources/image/up.png"></ww:if><ww:elseif test="#request.AUSDJPY.bidFlag == -1"><img alt="" src="resources/image/down.png"></ww:elseif></div>
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
	    	<br>
	    </form>
   		<ec:table items="collection" var="c" action=""
				border="" cellpadding="0" cellspacing="0" style="eXtremeTable" >
				<ec:row>
					<ec:column property="tradeNo" title="交易序号" width="100"
						headerStyle="text-align:center" style="text-align:left" viewsAllowed="html" >
					</ec:column>
					<ec:column property="tradeType" title="交易类型" width="160"
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
