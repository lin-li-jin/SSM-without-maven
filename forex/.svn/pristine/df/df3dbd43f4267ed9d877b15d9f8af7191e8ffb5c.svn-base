<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib uri="extremecomponents" prefix="ec"%>
<%@ taglib uri="webwork" prefix="ww"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    <title>人民币交易</title>  
	<style type="text/css">
		#all{ margin: 0; padding:0; width: 1003px; height: auto;}
		.colorSet{color: red; font-size: 16px;font-weight: bolder;}
		#top{margin: 0; padding:3px 5px; height: 24px; width:840px; line-height: 24px; background-image: url("images/b.png"); color: white;}
		#center{margin: 0; padding:0; height: 320px; width:850px; font-size: 14px;margin-top: 10px; overflow-y:auto; }
		.container{margin: 0; padding: 0; height: 140px; width: 100%;  margin-bottom: 15px;}
		.modules{margin: 0; padding: 0; height:125px; width:248px;float: left; margin-right: 40px;font-size: 14px; }
		.modulesTopRed{margin: 0; padding: 0; height:25px;width: 242px; background-color: #ce3231; float: left; margin-bottom: 2px;}
		.modulesTopBlue{margin: 0; padding: 0; height:25px;width: 242px; background-image: url("images/b.png"); float: left;margin-bottom: 2px;}
		.red{margin: 0; padding: 0; height: 100px;width:119px !important; width:121px;background-color:#ffdbe7 ;float: left;
		 border: 1px #CC3333 solid; text-align: center; padding-top: 10px;cursor:pointer}
		.blue{margin: 0; padding: 0; height: 100px;width:119px !important;width:121px;background-color: #dee7ff; float:left; 
		border: 1px #428BC2 solid; 
		text-align: center;padding-top: 10px;cursor:pointer}
		.modules span { line-height: 25px; font-size: 16px; color: white; margin-left: 10px; font-weight: bolder;}
		.modules label {  font-size: 16px; color: black; font-weight: bolder;cursor:pointer}
		#ectable{margin: 0; padding: 0; width: 860px; height: auto; }
	</style>
	<link rel="stylesheet" href="resources/css/extremecomponents.css"	type="text/css" />
	<script src="resources/ponyjs/jquery.js" type="text/javascript"></script>
	<script language="JavaScript" src="jsp/local/tradeModule/home/js/homepage.js"></script>
	
	<script type="text/javascript" language="javascript">
		$(function(){
			$('input:checkbox').click(function () {
					this.blur();   
					this.focus(); 
			});
		});
		
		
		
		function selectTradeType(direction,ccy,bid,ask){
			window.location.href = "stopLossTradePageInit.action?direct="+direction+"&ccy1="+ccy+"&ccy2=CNY&bid="+bid+"&ask="+ask;
		}

		function selectAskTradeType(direction,ccy,price){
			//window.location.href = "spotTradePageInit.action?direct="+direction+"&ccy1="+ccy+"&ccy2=CNY"+"&price="+price;
			
 			pageURL="spotTradePageInit.action?direct="+direction+"&ccy1="+ccy+"&ccy2=CNY"+"&price="+price;
 			window.open(pageURL);
		}
		
		$(document).ready(function(){
			//延迟10秒调用ajaxRefreshModule
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
				//删除原来各个模块数据
				$(".blue").remove();
				$(".red").remove();
				$(".modulesTopBlue").remove();
				$(".modulesTopRed").remove();
				//添加各个模块数据
				addModule("BUSD",objB.BUSD,"USD","B");
				addModule("BEUR",objB.BEUR,"EUR","B");
				addModule("BGBP",objB.BGBP,"GBP","B");
				addModule("BAUD",objB.BAUD,"AUD","B");
				addModule("BJPY",objB.BJPY,"JPY","B");
				addModule("BCAD",objB.BCAD,"CAD","B");
				addModule("AUSD",objA.AUSD,"USD","A");
				addModule("AEUR",objA.AEUR,"EUR","A");
				addModule("AGBP",objA.AGBP,"GBP","A");
				addModule("AAUD",objA.AAUD,"AUD","A");
				addModule("AJPY",objA.AJPY,"JPY","A");
				addModule("ACAD",objA.ACAD,"CAD","A");
			});
			//设置刷新时间为10秒
			var t=setTimeout("ajaxRefreshModule()",10000);
		}
		
		function addModule(id,obj,ccy,type2){
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
				$("#"+id).append("<div class='modulesTopBlue'><span>"+ccy+"/CNY&nbsp;SPOT</span></div>");
				$("#"+id).append("<div class='blue' onclick=selectTradeType('0','" + ccy + "',"+obj.bidValue+","+obj.askValue+") > <label>I SELL "+ccy+"</label><br/><label>"+obj.bidValue+"</label><br/><br/>"+bidImg+"</div>");
				$("#"+id).append("<div class='blue' onclick=selectTradeType('1','" + ccy + "',"+obj.bidValue+","+obj.askValue+") > <label>I BUY "+ccy+"</label><br/><label>"+obj.askValue+"</label><br/><br/>"+askImg+"</div>");
			}else{
				$("#"+id).append("<div class='modulesTopRed'><span>"+ccy+"/CNY&nbsp;SPOT</span></div>");
				$("#"+id).append("<div class='red' onclick=selectAskTradeType('0','" + ccy + "',"+obj.askValue+") > <label>I SELL "+ccy+"</label><br/><label>"+obj.askValue+"</label><br/><br/>"+askImg+"</div>");
				$("#"+id).append("<div class='red' onclick=selectAskTradeType('1','" + ccy + "',"+obj.bidValue+") > <label>I BUY "+ccy+"</label><br/><label>"+obj.bidValue+"</label><br/><br/>"+bidImg+"</div>");
			}
		}
	</script>
  </head>
  
  <body>
  <div id="all">
    <form action="">
  	 <div id="top">
    	一口价交易<input type="checkbox" name="oneClick" onchange="window.location.href='CNYOneClickPageInit.action'"> 
    	<span style="margin-left: 30px;">交易账户:</span>
    	<label>人民币账户</label><label>042166581207130</label>
    </div>
    <div id="center">
    	<div class="container">
    		<div class="modules" id="BUSD">
	    		<div class="modulesTopBlue">
	    			<span>USD/CNY&nbsp;SPOT</span>
	    		</div>
    			<div class="blue" onclick="selectTradeType('0','USD','<ww:property value='#request.BUSD.bidValue' />','<ww:property value='#request.BUSD.askValue' />')">
    				<label>I SELL USD</label><br>
    				<label><ww:property value="#request.BUSD.bidValue" /></label><br><br>
    				<ww:if test="#request.BUSD.bidFlag == 1"><img alt="" src="resources/image/up.png"></ww:if><ww:elseif test="#request.BUSD.bidFlag == -1"><img alt="" src="resources/image/down.png"></ww:elseif></div>
	    		<div class="blue" onclick="selectTradeType('1','USD','<ww:property value='#request.BUSD.bidValue' />','<ww:property value='#request.BUSD.askValue' />')">
	    			<label>I BUY USD</label><br>
	    			<label><ww:property value="#request.BUSD.askValue" /></label><br><br>
	    			<ww:if test="#request.BUSD.askFlag == 1"><img alt="" src="resources/image/up.png"></ww:if><ww:elseif test="#request.BUSD.askFlag == -1"><img alt="" src="resources/image/down.png"></ww:elseif></div>
	    	</div>
	    	<div class="modules" id="BEUR">
	    		<div class="modulesTopBlue">
	    			<span>EUR/CNY&nbsp;SPOT</span>
	    		</div>
	    		<div class="blue" onclick="selectTradeType('0','EUR','<ww:property value='#request.BEUR.bidValue' />','<ww:property value='#request.BEUR.askValue' />')">
	    			<label>I SELL EUR</label><br>
	    			<label><ww:property value="#request.BEUR.bidValue" /></label><br><br>
	    			<ww:if test="#request.BEUR.bidFlag == 1"><img alt="" src="resources/image/up.png"></ww:if><ww:elseif test="#request.BEUR.bidFlag == -1"><img alt="" src="resources/image/down.png"></ww:elseif></div>
	    		<div class="blue" onclick="selectTradeType('1','EUR','<ww:property value='#request.BEUR.bidValue' />','<ww:property value='#request.BEUR.askValue' />')">
	    			<label>I BUY EUR</label><br>
	    			<label><ww:property value="#request.BEUR.askValue" /></label><br><br>
	    			<ww:if test="#request.BEUR.askFlag == 1"><img alt="" src="resources/image/up.png"></ww:if><ww:elseif test="#request.BEUR.askFlag == -1"><img alt="" src="resources/image/down.png"></ww:elseif></div>
	    	</div>
	    	<div class="modules" style="margin-right: 0;" id="BGBP">
	    		<div class="modulesTopBlue"><span>GBP/CNY&nbsp;SPOT</span></div>
	    		<div class="blue" onclick="selectTradeType('0','GBP','<ww:property value='#request.BGBP.bidValue' />','<ww:property value='#request.BGBP.askValue' />')">
	    			<label>I SELL GBP</label><br>
	    			<label><ww:property value="#request.BGBP.bidValue" /></label><br><br>
	    			<ww:if test="#request.BGBP.bidFlag == 1"><img alt="" src="resources/image/up.png"></ww:if><ww:elseif test="#request.BGBP.bidFlag == -1"><img alt="" src="resources/image/down.png"></ww:elseif></div>
	    		<div class="blue" onclick="selectTradeType('1','GBP','<ww:property value='#request.BGBP.bidValue' />','<ww:property value='#request.BGBP.askValue' />')">
	    			<label>I BUY GBP</label><br>
	    			<label><ww:property value="#request.BGBP.askValue" /></label><br><br>
	    			<ww:if test="#request.BGBP.askFlag == 1"><img alt="" src="resources/image/up.png"></ww:if><ww:elseif test="#request.BGBP.askFlag == -1"><img alt="" src="resources/image/down.png"></ww:elseif></div>
	    	</div>
    	</div>
    	<div class="container">
    		<div class="modules" id="BAUD">
	    		<div class="modulesTopBlue"><span>AUD/CNY&nbsp;SPOT</span></div>
	    		<div class="blue" onclick="selectTradeType('0','AUD','<ww:property value='#request.BAUD.bidValue' />','<ww:property value='#request.BAUD.askValue' />')">
	    			<label>I SELL AUD</label><br>
	    			<label><ww:property value="#request.BAUD.bidValue" /></label><br><br>
	    			<ww:if test="#request.BAUD.bidFlag == 1"><img alt="" src="resources/image/up.png"></ww:if><ww:elseif test="#request.BAUD.bidFlag == -1"><img alt="" src="resources/image/down.png"></ww:elseif></div>
		    	<div class="blue" onclick="selectTradeType('1','AUD','<ww:property value='#request.BAUD.bidValue' />','<ww:property value='#request.BAUD.askValue' />')">
		    		<label>I BUY AUD</label><br>
		    		<label><ww:property value="#request.BAUD.askValue" /></label><br><br>
		    		<ww:if test="#request.BAUD.askFlag == 1"><img alt="" src="resources/image/up.png"></ww:if><ww:elseif test="#request.BAUD.askFlag == -1"><img alt="" src="resources/image/down.png"></ww:elseif></div>
	    	</div>
	    	<div class="modules" id="BJPY">
	    		<div class="modulesTopBlue"><span>JPY/CNY&nbsp;SPOT</span></div>
	    		<div class="blue" onclick="selectTradeType('0','JPY','<ww:property value='#request.BJPY.bidValue' />','<ww:property value='#request.BJPY.askValue' />')">
	    			<label>I SELL JPY</label><br>
	    			<label><ww:property value="#request.BJPY.bidValue" /></label><br><br>
	    			<ww:if test="#request.BJPY.bidFlag == 1"><img alt="" src="resources/image/up.png"></ww:if><ww:elseif test="#request.BJPY.bidFlag == -1"><img alt="" src="resources/image/down.png"></ww:elseif></div>
		    	<div class="blue" onclick="selectTradeType('1','JPY','<ww:property value='#request.BJPY.bidValue' />','<ww:property value='#request.BJPY.askValue' />')">
		    		<label>I BUY JPY</label><br>
		    		<label><ww:property value="#request.BJPY.askValue" /></label><br><br>
		    		<ww:if test="#request.BJPY.askFlag == 1"><img alt="" src="resources/image/up.png"></ww:if><ww:elseif test="#request.BJPY.askFlag == -1"><img alt="" src="resources/image/down.png"></ww:elseif></div>
	    	</div>
	    	<div class="modules"  style="margin-right: 0;" id="BCAD">
	    		<div class="modulesTopBlue"><span>CAD/CNY&nbsp;SPOT</span></div>
	    		<div class="blue" onclick="selectTradeType('0','CAD','<ww:property value='#request.BCAD.bidValue' />','<ww:property value='#request.BCAD.askValue' />')">
	    			<label>I SELL CAD</label><br>
	    			<label><ww:property value="#request.BCAD.bidValue" /></label><br><br>
	    			<ww:if test="#request.BCAD.bidFlag == 1"><img alt="" src="resources/image/up.png"></ww:if><ww:elseif test="#request.BCAD.bidFlag == -1"><img alt="" src="resources/image/down.png"></ww:elseif></div>
		    	<div class="blue" onclick="selectTradeType('1','CAD','<ww:property value='#request.BCAD.bidValue' />','<ww:property value='#request.BCAD.askValue' />')">
		    		<label>I BUY CAD</label><br>
		    		<label><ww:property value="#request.BCAD.askValue" /></label><br><br>
		    		<ww:if test="#request.BCAD.askFlag == 1"><img alt="" src="resources/image/up.png"></ww:if><ww:elseif test="#request.BCAD.askFlag == -1"><img alt="" src="resources/image/down.png"></ww:elseif></div>
	    	</div>
    	</div>
    	<div class="container">
	    	<div class="modules" id="AUSD">
	    		<div class="modulesTopRed">
	    			<span>USD/CNY&nbsp;SPOT</span>
	    		</div>
	    		<div class="red" onclick="selectAskTradeType('0','USD','<ww:property value="#request.AUSD.askValue" />')">
	    			<label>I SELL USD</label><br>
	    			<label><ww:property value="#request.AUSD.askValue" /></label><br><br>
	    			<ww:if test="#request.AUSD.askFlag == 1"><img alt="" src="resources/image/up.png"></ww:if><ww:elseif test="#request.AUSD.askFlag == -1"><img alt="" src="resources/image/down.png"></ww:elseif></div>
		    	<div class="red" onclick="selectAskTradeType('1','USD','<ww:property value="#request.AUSD.bidValue" />')">
		    		<label>I BUY USD</label><br>
		    		<label><ww:property value="#request.AUSD.bidValue" /></label><br><br>
		    		<ww:if test="#request.AUSD.bidFlag == 1"><img alt="" src="resources/image/up.png"></ww:if><ww:elseif test="#request.AUSD.bidFlag == -1"><img alt="" src="resources/image/down.png"></ww:elseif></div>
	    	</div>
	    	<div class="modules" id="AEUR">
	    		<div class="modulesTopRed">
	    			<span>EUR/CNY&nbsp;SPOT</span>
	    		</div>
	    		<div class="red" onclick="selectAskTradeType('0','EUR','<ww:property value="#request.AEUR.askValue" />')">
	    			<label>I SELL EUR</label><br>
	    			<label><ww:property value="#request.AEUR.askValue" /></label><br><br>
	    			<ww:if test="#request.AEUR.askFlag == 1"><img alt="" src="resources/image/up.png"></ww:if><ww:elseif test="#request.AEUR.askFlag == -1"><img alt="" src="resources/image/down.png"></ww:elseif></div>
		    	<div class="red" onclick="selectAskTradeType('1','EUR','<ww:property value="#request.AEUR.bidValue" />')">
		    		<label>I BUY EUR</label><br>
		    		<label><ww:property value="#request.AEUR.bidValue" /></label><br><br>	
		    		<ww:if test="#request.AEUR.bidFlag == 1"><img alt="" src="resources/image/up.png"></ww:if><ww:elseif test="#request.AEUR.bidFlag == -1"><img alt="" src="resources/image/down.png"></ww:elseif></div>
	    	</div>
	    	<div class="modules" style="margin-right: 0;" id="AGBP">
	    		<div class="modulesTopRed"><span>GBP/CNY&nbsp;SPOT</span></div>
	    		<div class="red" onclick="selectAskTradeType('0','GBP','<ww:property value="#request.AGBP.askValue" />')">
	    		<label>I SELL GBP</label><br>
	    			<label><ww:property value="#request.AGBP.askValue" /></label><br><br>
	    			<ww:if test="#request.AGBP.askFlag == 1"><img alt="" src="resources/image/up.png"></ww:if><ww:elseif test="#request.AGBP.askFlag == -1"><img alt="" src="resources/image/down.png"></ww:elseif></div>
		    	<div class="red" onclick="selectAskTradeType('1','GBP','<ww:property value="#request.AGBP.bidValue" />')">
		    	<label>I BUY GBP</label><br>
		    		<label><ww:property value="#request.AGBP.bidValue" /></label><br><br>
		    		<ww:if test="#request.AGBP.bidFlag == 1"><img alt="" src="resources/image/up.png"></ww:if><ww:elseif test="#request.AGBP.bidFlag == -1"><img alt="" src="resources/image/down.png"></ww:elseif></div>
	    	</div>
    	</div>
    	<div class="container">
	    	<div class="modules" id="AAUD">
	    		<div class="modulesTopRed">
	    			<span>AUD/CNY&nbsp;SPOT</span>
	    		</div>
	    		<div class="red" onclick="selectAskTradeType('0','AUD','<ww:property value="#request.AAUD.askValue" />')">
	    			<label>I SELL AUD</label><br>
	    			<label><ww:property value="#request.AAUD.askValue" /></label><br><br>
	    			<ww:if test="#request.AAUD.askFlag == 1"><img alt="" src="resources/image/up.png"></ww:if><ww:elseif test="#request.AAUD.askFlag == -1"><img alt="" src="resources/image/down.png"></ww:elseif></div>
		    	<div class="red" onclick="selectAskTradeType('1','AUD','<ww:property value="#request.AAUD.bidValue" />')">
		    		<label>I BUY AUD</label><br>
		    		<label><ww:property value="#request.AAUD.bidValue" /></label><br><br>
		    		<ww:if test="#request.AAUD.bidFlag == 1"><img alt="" src="resources/image/up.png"></ww:if><ww:elseif test="#request.AAUD.bidFlag == -1"><img alt="" src="resources/image/down.png"></ww:elseif></div>
	    	</div>
	    	<div class="modules" id="AJPY">
	    		<div class="modulesTopRed">
	    			<span>JPY/CNY&nbsp;SPOT</span>
	    		</div>
	    		<div class="red" onclick="selectAskTradeType('0','JPY','<ww:property value="#request.AJPY.askValue" />')">
	    			<label>I SELL JPY</label><br>
	    			<label><ww:property value="#request.AJPY.askValue" /></label><br><br>
	    			<ww:if test="#request.AJPY.askFlag == 1"><img alt="" src="resources/image/up.png"></ww:if><ww:elseif test="#request.AJPY.askFlag == -1"><img alt="" src="resources/image/down.png"></ww:elseif></div>
		    	<div class="red" onclick="selectAskTradeType('1','JPY','<ww:property value="#request.AJPY.bidValue" />')">
		    		<label>I BUY JPY</label><br>
		    		<label><ww:property value="#request.AJPY.bidValue" /></label><br><br>
		    		<ww:if test="#request.AJPY.bidFlag == 1"><img alt="" src="resources/image/up.png"></ww:if><ww:elseif test="#request.AJPY.bidFlag == -1"><img alt="" src="resources/image/down.png"></ww:elseif></div>
	    	</div>
	    	<div class="modules" style="margin-right: 0;" id="ACAD">
	    		<div class="modulesTopRed"><span>CAD/CNY&nbsp;SPOT</span></div>
	    		<div class="red" onclick="selectAskTradeType('0','CAD','<ww:property value="#request.ACAD.askValue" />')">
	    			<label>I SELL CAD</label><br>
	    			<label><ww:property value="#request.ACAD.askValue" /></label><br><br>
	    			<ww:if test="#request.ACAD.askFlag == 1"><img alt="" src="resources/image/up.png"></ww:if><ww:elseif test="#request.ACAD.askFlag == -1"><img alt="" src="resources/image/down.png"></ww:elseif></div>
		    	<div class="red" onclick="selectAskTradeType('1','CAD','<ww:property value="#request.ACAD.bidValue" />')">
		    		<label>I BUY CAD</label><br>
		    		<label><ww:property value="#request.ACAD.bidValue" /></label><br><br>
		    		<ww:if test="#request.ACAD.bidFlag == 1"><img alt="" src="resources/image/up.png"></ww:if><ww:elseif test="#request.ACAD.bidFlag == -1"><img alt="" src="resources/image/down.png"></ww:elseif></div>
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
						headerStyle="text-align:center" style="text-align:right;" format="number"/>
					<ec:column property="tradePrice" title="交易价格" width="100"
						headerStyle="text-align:center" style="text-align:right;"/>
					<ec:column property="promptDate" title="交割(到期)日期" width="125"
						headerStyle="text-align:center" style="text-align:left"/>
					<ec:column property="provider" title="交易对手方" width="105"
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
