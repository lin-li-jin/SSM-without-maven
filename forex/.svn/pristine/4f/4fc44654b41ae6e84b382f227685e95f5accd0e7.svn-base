<%--
 * Amendment No.: FOEXAS011
 * Create By    : atggdsaiDong
 * Description  : 保证金交易首页
 * Modify Date  : 2014-07-28
 --%>

<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib uri="extremecomponents" prefix="ec"%>
<%@ taglib uri="webwork" prefix="ww"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%--<meta http-equiv="refresh" content="10">--%>
<title></title>
<link rel="stylesheet" href="resources/css/extremecomponents.css"	type="text/css" />
<script src="resources/ponyjs/jquery.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript"
	src="resources/calendar/js/My97DatePicker/WdatePicker.js">
</script>

<script type="text/javascript">

	function forwardBuy(ccy,price){
		if(price!=""){
			window.location.href="forwardCashPageInit.action?direction=1&ccy="+ccy+"&price="+price;
		}
	}
	function forwardSell(ccy,price){
		if(price!=""){
			window.location.href="forwardCashPageInit.action?direction=0&ccy="+ccy+"&price="+price;
		}
	}
	function optionBuy(ccy,price){
		if(price!=""){
			window.location.href="optionCashPageInit.action?direction=1&ccy="+ccy+"&price="+price;
		}
	}
	function optionSell(ccy,price){
		if(price!=""){
			window.location.href="optionCashPageInit.action?direction=0&ccy="+ccy+"&price="+price;
		}
	}
	
	$(document).ready(function(){
		var t=setTimeout("ajaxRefreshModule()",10000);
	});
	
	var arrayUSDCcy;
	var arrayUSDaskValue
	var arrayUSDbidValue;
	var arrayEURCcy;
	var arrayEURaskValue
	var arrayEURbidValue;
	var arrayAUDCcy;
	var arrayAUDaskValue
	var arrayAUDbidValue;
	var arrayGBPCcy;
	var arrayGBPaskValue
	var arrayGBPbidValue;
	var arrayJPYCcy;
	var arrayJPYaskValue
	var arrayJPYbidValue;
	var arrayCADCcy;
	var arrayCADaskValue
	var arrayCADbidValue;
	var aCcy=new Array("USD","EUR","AUD","GBP","JPY","CAD");
	function ajaxRefreshModule(){
		$.post("cashPageRefresh.action",function(data){
			//alert(data);
			var obj=eval("("+data+")");
			
			 arrayUSDCcy=new Array(obj.USDEUR.ccy,obj.USDAUD.ccy,obj.USDGBP.ccy,obj.USDJPY.ccy,obj.USDCAD.ccy);
			 arrayUSDaskValue=new Array(obj.USDEUR.askValue,obj.USDAUD.askValue,obj.USDGBP.askValue,obj.USDJPY.askValue,obj.USDCAD.askValue);
			 arrayUSDbidValue=new Array(obj.USDEUR.bidValue,obj.USDAUD.bidValue,obj.USDGBP.bidValue,obj.USDJPY.bidValue,obj.USDCAD.bidValue);
			
			 arrayEURCcy=new Array(obj.EURUSD.ccy,obj.EURAUD.ccy,obj.EURGBP.ccy,obj.EURJPY.ccy,obj.EURCAD.ccy);
			 arrayEURaskValue=new Array(obj.EURUSD.askValue,obj.EURAUD.askValue,obj.EURGBP.askValue,obj.EURJPY.askValue,obj.EURCAD.askValue);
			 arrayEURbidValue=new Array(obj.EURUSD.bidValue,obj.EURAUD.bidValue,obj.EURGBP.bidValue,obj.EURJPY.bidValue,obj.EURCAD.bidValue);
			
			 arrayAUDCcy=new Array(obj.AUDUSD.ccy,obj.AUDEUR.ccy,obj.AUDGBP.ccy,obj.AUDJPY.ccy,obj.AUDCAD.ccy);
			 arrayAUDaskValue=new Array(obj.AUDUSD.askValue,obj.AUDEUR.askValue,obj.AUDGBP.askValue,obj.AUDJPY.askValue,obj.AUDCAD.askValue);
			 arrayAUDbidValue=new Array(obj.AUDUSD.bidValue,obj.AUDEUR.bidValue,obj.AUDGBP.bidValue,obj.AUDJPY.bidValue,obj.AUDCAD.bidValue);
			
			 arrayGBPCcy=new Array(obj.GBPUSD.ccy,obj.GBPEUR.ccy,obj.GBPAUD.ccy,obj.GBPJPY.ccy,obj.GBPCAD.ccy);
			 arrayGBPaskValue=new Array(obj.GBPUSD.askValue,obj.GBPEUR.askValue,obj.GBPAUD.askValue,obj.GBPJPY.askValue,obj.GBPCAD.askValue);
			 arrayGBPbidValue=new Array(obj.GBPUSD.bidValue,obj.GBPEUR.bidValue,obj.GBPAUD.bidValue,obj.GBPJPY.bidValue,obj.GBPCAD.bidValue);
			
			 arrayJPYCcy=new Array(obj.JPYUSD.ccy,obj.JPYEUR.ccy,obj.JPYAUD.ccy,obj.JPYGBP.ccy,obj.JPYCAD.ccy);
			 arrayJPYaskValue=new Array(obj.JPYUSD.askValue,obj.JPYEUR.askValue,obj.JPYAUD.askValue,obj.JPYGBP.askValue,obj.JPYCAD.askValue);
			 arrayJPYbidValue=new Array(obj.JPYUSD.bidValue,obj.JPYEUR.bidValue,obj.JPYAUD.bidValue,obj.JPYGBP.bidValue,obj.JPYCAD.bidValue);
			
			 arrayCADCcy=new Array(obj.CADUSD.ccy,obj.CADEUR.ccy,obj.CADAUD.ccy,obj.CADGBP.ccy,obj.CADJPY.ccy);
			 arrayCADaskValue=new Array(obj.CADUSD.askValue,obj.CADEUR.askValue,obj.CADAUD.askValue,obj.CADGBP.askValue,obj.CADJPY.askValue);
			 arrayCADbidValue=new Array(obj.CADUSD.bidValue,obj.CADEUR.bidValue,obj.CADAUD.bidValue,obj.CADGBP.bidValue,obj.CADJPY.bidValue);
			
			addModule("forwardTransactionsAsk","远期交易(买价)","ask");
			addModule("OptionsTradingAsk","期权交易(买价)","ask");
			/*addModule("forwardTransactionsBid","远期交易(卖价)","bid");
			addModule("OptionsTradingBid","期权交易(卖价)","bid");*/
		});
		//设置定时刷新时间
		var t=setTimeout("ajaxRefreshModule()",10000);
	}
	function addModule(id,label,tranType){
			
			$("#"+id).empty();
			$("#"+id).append("<label>"+label+"</label>");
			$("#"+id).append("<table class='gridtable'><tr><th></th><th>USD</th><th>EUR</th><th>AUD</th><th>GBP</th><th>JPY</th><th>CAD</th></tr></table>");
			for(var j=0;j<6;j++){
				var arrayCcy=new Array();
				var arrayaskValue=new Array();
				var arraybidValue=new Array();
				if(j==0){
					 arrayCcy=arrayUSDCcy.slice();
					 arrayaskValue=arrayUSDaskValue.slice();
					 arraybidValue=arrayUSDbidValue.slice();
				}
				if(j==1){
					 arrayCcy=arrayEURCcy.slice();
					 arrayaskValue=arrayEURaskValue.slice();
					 arraybidValue=arrayEURbidValue.slice();
				}
				if(j==2){
					 arrayCcy=arrayAUDCcy.slice();
					 arrayaskValue=arrayAUDaskValue.slice();
					 arraybidValue=arrayAUDbidValue.slice();
				}
				if(j==3){
					 arrayCcy=arrayGBPCcy.slice();
					 arrayaskValue=arrayGBPaskValue.slice();
					 arraybidValue=arrayGBPbidValue.slice();
				}
				if(j==4){
					 arrayCcy=arrayJPYCcy.slice();
					 arrayaskValue=arrayJPYaskValue.slice();
					 arraybidValue=arrayJPYbidValue.slice();
				}
				if(j==5){
					 arrayCcy=arrayCADCcy.slice();
					 arrayaskValue=arrayCADaskValue.slice();
					 arraybidValue=arrayCADbidValue.slice();
				}
				$("#"+id+">table").append("<tr></tr>");
				$("#"+id+" table tr").eq(j+1).append("<th>"+aCcy[j]+"</th>");
				for(var i=0;i<arrayCcy.length;i++){
					if(i==j){
						$("#"+id+" table tr").eq(j+1).append("<td>1.0000</td>");
						//$("#"+id+" table tr").eq(j+1).append("<td onclick='forwardBuy('"+arrayCcy[i]+"','"+arrayaskValue[i]+"')'>"+arrayaskValue[i]+"</td>");
					}
					
					if(tranType=='ask'){
						$("#"+id+" table tr").eq(j+1).append("<td onclick=forwardBuy('"+arrayCcy[i]+"','"+arrayaskValue[i]+"')>"+arrayaskValue[i]+"</td>");
					}else{
						$("#"+id+" table tr").eq(j+1).append("<td onclick=forwardBuy('"+arrayCcy[i]+"','"+arraybidValue[i]+"')>"+arraybidValue[i]+"</td>");
					}
					
					if(i==arrayCcy.length-1&&j==5){
						$("#"+id+" table tr").eq(j+1).append("<td>1.0000</td>");
					}
				}
			}
			
	}
</script>

<style type="text/css">
table.gridtable {
	font-family: verdana, arial, sans-serif;font-size: 11px;color: #333333;border-width: 1px;border-color: #666666;
	border-collapse: collapse;width: 485px;margin-top: 10px;
}

table.gridtable th {font-size:12px;border-width: 1px;padding: 8px;
	border-style: solid;border-color: #666666;background-color: #ddeeff;
}

table.gridtable td {width: 100px; text-align: center;word-wrap: break-word;font-size:12px;line-height: 24px;
	border-width: 1px;padding: 4px;border-style: solid; border-color: #666666;background-color: #ffffff;cursor:pointer;
}

#all {margin: 0;padding: 0;height: auto;width: 1003px;}

#top { margin: 0;padding: 3px 5px;height: 24px;width: 990px;line-height: 24px;font-weight: bolder;
	background-image: url("images/b.png");color: white;text-align:center;float:left;
}
#top1 { margin: 0;padding: 3px 5px;height: 24px;width: 990px;line-height: 24px;font-weight: bolder;
	color: black; text-align: center;float: left;
}

#container {margin: 0;padding: 0;height: auto;width:1000px !important;width: 990px;background-color: #E5F2F8;
		margin-top: 5px;float: left; }

.modules {margin: 0px;padding: 10 0 0 8px;height: 275px;width: 490px;float: left;text-align: center;}
#ectable{float: left; margin-top: 30px; width: 1000px !important;width: 990px;}
</style>
</head>

<body>
	<div id="all">
		<form action="" style="margin: 0; padding:0;">
			<div id="top">
				保证金交易
			</div>
			<div id="container">
				<div id="top1"><span>汇率比为（1单位行货币:1单位列货币）</span></div>
				<div class="modules" id="forwardTransactionsAsk">
					<label>远期交易(买价)</label>
					<table class="gridtable" id="priceTable">
						<tr>
							<th></th>
							<th>USD</th>
							<th>EUR</th>
							<th>AUD</th>
							<th>GBP</th>
							<th>JPY</th>
							<th>CAD</th>
						</tr>

						<tr>
							<th>USD</th>
							<td>1.0000</td>
							<td onclick="forwardBuy('<ww:property value="#request.USDEUR.Ccy"/>','<ww:property value="#request.USDEUR.askValue"/>')"><ww:property value="#request.USDEUR.askValue"/></td>
							<td onclick="forwardBuy('<ww:property value="#request.USDAUD.Ccy"/>','<ww:property value="#request.USDAUD.askValue"/>')"><ww:property value="#request.USDAUD.askValue"/></td>
							<td onclick="forwardBuy('<ww:property value="#request.USDGBP.Ccy"/>','<ww:property value="#request.USDGBP.askValue"/>')"><ww:property value="#request.USDGBP.askValue"/></td>
							<td onclick="forwardBuy('<ww:property value="#request.USDJPY.Ccy"/>','<ww:property value="#request.USDJPY.askValue"/>')"><ww:property value="#request.USDJPY.askValue"/></td>
							<td onclick="forwardBuy('<ww:property value="#request.USDCAD.Ccy"/>','<ww:property value="#request.USDCAD.askValue"/>')"><ww:property value="#request.USDCAD.askValue"/></td>
						</tr>
						
						<tr>
							<th>EUR</th>
							<td onclick="forwardBuy('<ww:property value="#request.EURUSD.Ccy"/>','<ww:property value="#request.EURUSD.askValue"/>')"><ww:property value="#request.EURUSD.askValue"/></td>
							<td >1.0000</td>
							<td onclick="forwardBuy('<ww:property value="#request.EURAUD.Ccy"/>','<ww:property value="#request.EURAUD.askValue"/>')"><ww:property value="#request.EURAUD.askValue"/></td>
							<td onclick="forwardBuy('<ww:property value="#request.EURGBP.Ccy"/>','<ww:property value="#request.EURGBP.askValue"/>')"><ww:property value="#request.EURGBP.askValue"/></td>
							<td onclick="forwardBuy('<ww:property value="#request.EURJPY.Ccy"/>','<ww:property value="#request.EURJPY.askValue"/>')"><ww:property value="#request.EURJPY.askValue"/></td>
							<td onclick="forwardBuy('<ww:property value="#request.EURCAD.Ccy"/>','<ww:property value="#request.EURCAD.askValue"/>')"><ww:property value="#request.EURCAD.askValue"/></td>
						</tr>
						<tr>
							<th>AUD</th>
							<td onclick="forwardBuy('<ww:property value="#request.AUDUSD.Ccy"/>','<ww:property value="#request.AUDUSD.askValue"/>')"><ww:property value="#request.AUDUSD.askValue"/></td>
							<td onclick="forwardBuy('<ww:property value="#request.AUDEUR.Ccy"/>','<ww:property value="#request.AUDEUR.askValue"/>')"><ww:property value="#request.AUDEUR.askValue"/></td>
							<td >1.0000</td>
							<td onclick="forwardBuy('<ww:property value="#request.AUDGBP.Ccy"/>','<ww:property value="#request.AUDGBP.askValue"/>')"><ww:property value="#request.AUDGBP.askValue"/></td>
							<td onclick="forwardBuy('<ww:property value="#request.AUDJPY.Ccy"/>','<ww:property value="#request.AUDJPY.askValue"/>')"><ww:property value="#request.AUDJPY.askValue"/></td>
							<td onclick="forwardBuy('<ww:property value="#request.AUDCAD.Ccy"/>','<ww:property value="#request.AUDCAD.askValue"/>')"><ww:property value="#request.AUDCAD.askValue"/></td>
						</tr>
						<tr>
							<th>GBP</th>
							<td onclick="forwardBuy('<ww:property value="#request.GBPUSD.Ccy"/>','<ww:property value="#request.GBPUSD.askValue"/>')"><ww:property value="#request.GBPUSD.askValue"/></td>
							<td onclick="forwardBuy('<ww:property value="#request.GBPEUR.Ccy"/>','<ww:property value="#request.GBPEUR.askValue"/>')"><ww:property value="#request.GBPEUR.askValue"/></td>
							<td onclick="forwardBuy('<ww:property value="#request.GBPAUD.Ccy"/>','<ww:property value="#request.GBPAUD.askValue"/>')"><ww:property value="#request.GBPAUD.askValue"/></td>
							<td>1.0000</td>
							<td onclick="forwardBuy('<ww:property value="#request.GBPJPY.Ccy"/>','<ww:property value="#request.GBPJPY.askValue"/>')"><ww:property value="#request.GBPJPY.askValue"/></td>
							<td onclick="forwardBuy('<ww:property value="#request.GBPCAD.Ccy"/>','<ww:property value="#request.GBPCAD.askValue"/>')"><ww:property value="#request.GBPCAD.askValue"/></td>
						</tr>
						<tr>
							<th>JPY</th>
							<td onclick="forwardBuy('<ww:property value="#request.JPYUSD.Ccy"/>','<ww:property value="#request.JPYUSD.askValue"/>')"><ww:property value="#request.JPYUSD.askValue"/></td>
							<td onclick="forwardBuy('<ww:property value="#request.JPYEUR.Ccy"/>','<ww:property value="#request.JPYEUR.askValue"/>')"><ww:property value="#request.JPYEUR.askValue"/></td>
							<td onclick="forwardBuy('<ww:property value="#request.JPYAUD.Ccy"/>','<ww:property value="#request.JPYAUD.askValue"/>')"><ww:property value="#request.JPYAUD.askValue"/></td>
							<td onclick="forwardBuy('<ww:property value="#request.JPYGBP.Ccy"/>','<ww:property value="#request.JPYGBP.askValue"/>')"><ww:property value="#request.JPYGBP.askValue"/></td>
							<td>1.0000</td>
							<td onclick="forwardBuy('<ww:property value="#request.JPYCAD.Ccy"/>','<ww:property value="#request.JPYCAD.askValue"/>')"><ww:property value="#request.JPYCAD.askValue"/></td>
						</tr>
						<tr>
							<th>CAD</th>
							<td onclick="forwardBuy('<ww:property value="#request.CADUSD.Ccy"/>','<ww:property value="#request.CADUSD.askValue"/>')"><ww:property value="#request.CADUSD.askValue"/></td>
							<td onclick="forwardBuy('<ww:property value="#request.CADEUR.Ccy"/>','<ww:property value="#request.CADEUR.askValue"/>')"><ww:property value="#request.CADEUR.askValue"/></td>
							<td onclick="forwardBuy('<ww:property value="#request.CADAUD.Ccy"/>','<ww:property value="#request.CADAUD.askValue"/>')"><ww:property value="#request.CADAUD.askValue"/></td>
							<td onclick="forwardBuy('<ww:property value="#request.CADGBP.Ccy"/>','<ww:property value="#request.CADGBP.askValue"/>')"><ww:property value="#request.CADGBP.askValue"/></td>
							<td onclick="forwardBuy('<ww:property value="#request.CADJPY.Ccy"/>','<ww:property value="#request.CADJPY.askValue"/>')"><ww:property value="#request.CADJPY.askValue"/></td>
							<td>1.0000</td>
						</tr>
					</table>
				</div>
				<div class="modules" id="OptionsTradingAsk">
					<label>期权交易(买价)</label>
					<table class="gridtable">
						<tr>
							<th></th>
							<th>USD</th>
							<th>EUR</th>
							<th>AUD</th>
							<th>GBP</th>
							<th>JPY</th>
							<th>CAD</th>
						</tr>

						<tr>
							<th>USD</th>
							<td>1.0000</td>
							<td onclick="optionBuy('<ww:property value="#request.USDEUR.Ccy"/>','<ww:property value="#request.USDEUR.askValue"/>')"><ww:property value="#request.USDEUR.askValue"/></td>
							<td onclick="optionBuy('<ww:property value="#request.USDAUD.Ccy"/>','<ww:property value="#request.USDAUD.askValue"/>')"><ww:property value="#request.USDAUD.askValue"/></td>
							<td onclick="optionBuy('<ww:property value="#request.USDGBP.Ccy"/>','<ww:property value="#request.USDGBP.askValue"/>')"><ww:property value="#request.USDGBP.askValue"/></td>
							<td onclick="optionBuy('<ww:property value="#request.USDJPY.Ccy"/>','<ww:property value="#request.USDJPY.askValue"/>')"><ww:property value="#request.USDJPY.askValue"/></td>
							<td onclick="optionBuy('<ww:property value="#request.USDCAD.Ccy"/>','<ww:property value="#request.USDCAD.askValue"/>')"><ww:property value="#request.USDCAD.askValue"/></td>
						</tr>
						
						<tr>
							<th>EUR</th>
							<td onclick="optionBuy('<ww:property value="#request.EURUSD.Ccy"/>','<ww:property value="#request.EURUSD.askValue"/>')"><ww:property value="#request.EURUSD.askValue"/></td>
							<td >1.0000</td>
							<td onclick="optionBuy('<ww:property value="#request.EURAUD.Ccy"/>','<ww:property value="#request.EURAUD.askValue"/>')"><ww:property value="#request.EURAUD.askValue"/></td>
							<td onclick="optionBuy('<ww:property value="#request.EURGBP.Ccy"/>','<ww:property value="#request.EURGBP.askValue"/>')"><ww:property value="#request.EURGBP.askValue"/></td>
							<td onclick="optionBuy('<ww:property value="#request.EURJPY.Ccy"/>','<ww:property value="#request.EURJPY.askValue"/>')"><ww:property value="#request.EURJPY.askValue"/></td>
							<td onclick="optionBuy('<ww:property value="#request.EURCAD.Ccy"/>','<ww:property value="#request.EURCAD.askValue"/>')"><ww:property value="#request.EURCAD.askValue"/></td>
						</tr>
						<tr>
							<th>AUD</th>
							<td onclick="optionBuy('<ww:property value="#request.AUDUSD.Ccy"/>','<ww:property value="#request.AUDUSD.askValue"/>')"><ww:property value="#request.AUDUSD.askValue"/></td>
							<td onclick="optionBuy('<ww:property value="#request.AUDEUR.Ccy"/>','<ww:property value="#request.AUDEUR.askValue"/>')"><ww:property value="#request.AUDEUR.askValue"/></td>
							<td >1.0000</td>
							<td onclick="optionBuy('<ww:property value="#request.AUDGBP.Ccy"/>','<ww:property value="#request.AUDGBP.askValue"/>')"><ww:property value="#request.AUDGBP.askValue"/></td>
							<td onclick="optionBuy('<ww:property value="#request.AUDJPY.Ccy"/>','<ww:property value="#request.AUDJPY.askValue"/>')"><ww:property value="#request.AUDJPY.askValue"/></td>
							<td onclick="optionBuy('<ww:property value="#request.AUDCAD.Ccy"/>','<ww:property value="#request.AUDCAD.askValue"/>')"><ww:property value="#request.AUDCAD.askValue"/></td>
						</tr>
						<tr>
							<th>GBP</th>
							<td onclick="optionBuy('<ww:property value="#request.GBPUSD.Ccy"/>','<ww:property value="#request.GBPUSD.askValue"/>')"><ww:property value="#request.GBPUSD.askValue"/></td>
							<td onclick="optionBuy('<ww:property value="#request.GBPEUR.Ccy"/>','<ww:property value="#request.GBPEUR.askValue"/>')"><ww:property value="#request.GBPEUR.askValue"/></td>
							<td onclick="optionBuy('<ww:property value="#request.GBPAUD.Ccy"/>','<ww:property value="#request.GBPAUD.askValue"/>')"><ww:property value="#request.GBPAUD.askValue"/></td>
							<td>1.0000</td>
							<td onclick="optionBuy('<ww:property value="#request.GBPJPY.Ccy"/>','<ww:property value="#request.GBPJPY.askValue"/>')"><ww:property value="#request.GBPJPY.askValue"/></td>
							<td onclick="optionBuy('<ww:property value="#request.GBPCAD.Ccy"/>','<ww:property value="#request.GBPCAD.askValue"/>')"><ww:property value="#request.GBPCAD.askValue"/></td>
						</tr>
						<tr>
							<th>JPY</th>
							<td onclick="optionBuy('<ww:property value="#request.JPYUSD.Ccy"/>','<ww:property value="#request.JPYUSD.askValue"/>')"><ww:property value="#request.JPYUSD.askValue"/></td>
							<td onclick="optionBuy('<ww:property value="#request.JPYEUR.Ccy"/>','<ww:property value="#request.JPYEUR.askValue"/>')"><ww:property value="#request.JPYEUR.askValue"/></td>
							<td onclick="optionBuy('<ww:property value="#request.JPYAUD.Ccy"/>','<ww:property value="#request.JPYAUD.askValue"/>')"><ww:property value="#request.JPYAUD.askValue"/></td>
							<td onclick="optionBuy('<ww:property value="#request.JPYGBP.Ccy"/>','<ww:property value="#request.JPYGBP.askValue"/>')"><ww:property value="#request.JPYGBP.askValue"/></td>
							<td>1.0000</td>
							<td onclick="optionBuy('<ww:property value="#request.JPYCAD.Ccy"/>','<ww:property value="#request.JPYCAD.askValue"/>')"><ww:property value="#request.JPYCAD.askValue"/></td>
						</tr>
						<tr>
							<th>CAD</th>
							<td onclick="optionBuy('<ww:property value="#request.CADUSD.Ccy"/>','<ww:property value="#request.CADUSD.askValue"/>')"><ww:property value="#request.CADUSD.askValue"/></td>
							<td onclick="optionBuy('<ww:property value="#request.CADEUR.Ccy"/>','<ww:property value="#request.CADEUR.askValue"/>')"><ww:property value="#request.CADEUR.askValue"/></td>
							<td onclick="optionBuy('<ww:property value="#request.CADAUD.Ccy"/>','<ww:property value="#request.CADAUD.askValue"/>')"><ww:property value="#request.CADAUD.askValue"/></td>
							<td onclick="optionBuy('<ww:property value="#request.CADGBP.Ccy"/>','<ww:property value="#request.CADGBP.askValue"/>')"><ww:property value="#request.CADGBP.askValue"/></td>
							<td onclick="optionBuy('<ww:property value="#request.CADJPY.Ccy"/>','<ww:property value="#request.CADJPY.askValue"/>')"><ww:property value="#request.CADJPY.askValue"/></td>
							<td>1.0000</td>
						</tr>
					</table>
				</div>
				<%--<div class="modules" id="forwardTransactionsBid">
					<label>远期交易(卖价)</label>
					<table class="gridtable">
						<tr>
							<th></th>
							<th>USD</th>
							<th>EUR</th>
							<th>AUD</th>
							<th>GBP</th>
							<th>JPY</th>
							<th>CAD</th>
						</tr>
						<tr>
							<th>USD</th>
							<td>1.0000</td>
							<td onclick="forwardSell('<ww:property value="#request.USDEUR.Ccy"/>','<ww:property value="#request.USDEUR.bidValue"/>')"><ww:property value="#request.USDEUR.bidValue"/></td>
							<td onclick="forwardSell('<ww:property value="#request.USDAUD.Ccy"/>','<ww:property value="#request.USDAUD.bidValue"/>')"><ww:property value="#request.USDAUD.bidValue"/></td>
							<td onclick="forwardSell('<ww:property value="#request.USDGBP.Ccy"/>','<ww:property value="#request.USDGBP.bidValue"/>')"><ww:property value="#request.USDGBP.bidValue"/></td>
							<td onclick="forwardSell('<ww:property value="#request.USDJPY.Ccy"/>','<ww:property value="#request.USDJPY.bidValue"/>')"><ww:property value="#request.USDJPY.bidValue"/></td>
							<td onclick="forwardSell('<ww:property value="#request.USDCAD.Ccy"/>','<ww:property value="#request.USDCAD.bidValue"/>')"><ww:property value="#request.USDCAD.bidValue"/></td>
						</tr>
						
						<tr>
							<th>EUR</th>
							<td onclick="forwardSell('<ww:property value="#request.EURUSD.Ccy"/>','<ww:property value="#request.EURUSD.bidValue"/>')"><ww:property value="#request.EURUSD.bidValue"/></td>
							<td >1.0000</td>
							<td onclick="forwardSell('<ww:property value="#request.EURAUD.Ccy"/>','<ww:property value="#request.EURAUD.bidValue"/>')"><ww:property value="#request.EURAUD.bidValue"/></td>
							<td onclick="forwardSell('<ww:property value="#request.EURGBP.Ccy"/>','<ww:property value="#request.EURGBP.bidValue"/>')"><ww:property value="#request.EURGBP.bidValue"/></td>
							<td onclick="forwardSell('<ww:property value="#request.EURJPY.Ccy"/>','<ww:property value="#request.EURJPY.bidValue"/>')"><ww:property value="#request.EURJPY.bidValue"/></td>
							<td onclick="forwardSell('<ww:property value="#request.EURCAD.Ccy"/>','<ww:property value="#request.EURCAD.bidValue"/>')"><ww:property value="#request.EURCAD.bidValue"/></td>
						</tr>
						<tr>
							<th>AUD</th>
							<td onclick="forwardSell('<ww:property value="#request.AUDUSD.Ccy"/>','<ww:property value="#request.AUDUSD.bidValue"/>')"><ww:property value="#request.AUDUSD.bidValue"/></td>
							<td onclick="forwardSell('<ww:property value="#request.AUDEUR.Ccy"/>','<ww:property value="#request.AUDEUR.bidValue"/>')"><ww:property value="#request.AUDEUR.bidValue"/></td>
							<td >1.0000</td>
							<td onclick="forwardSell('<ww:property value="#request.AUDGBP.Ccy"/>','<ww:property value="#request.AUDGBP.bidValue"/>')"><ww:property value="#request.AUDGBP.bidValue"/></td>
							<td onclick="forwardSell('<ww:property value="#request.AUDJPY.Ccy"/>','<ww:property value="#request.AUDJPY.bidValue"/>')"><ww:property value="#request.AUDJPY.bidValue"/></td>
							<td onclick="forwardSell('<ww:property value="#request.AUDCAD.Ccy"/>','<ww:property value="#request.AUDCAD.bidValue"/>')"><ww:property value="#request.AUDCAD.bidValue"/></td>
						</tr>
						<tr>
							<th>GBP</th>
							<td onclick="forwardSell('<ww:property value="#request.GBPUSD.Ccy"/>','<ww:property value="#request.GBPUSD.bidValue"/>')"><ww:property value="#request.GBPUSD.bidValue"/></td>
							<td onclick="forwardSell('<ww:property value="#request.GBPEUR.Ccy"/>','<ww:property value="#request.GBPEUR.bidValue"/>')"><ww:property value="#request.GBPEUR.bidValue"/></td>
							<td onclick="forwardSell('<ww:property value="#request.GBPAUD.Ccy"/>','<ww:property value="#request.GBPAUD.bidValue"/>')"><ww:property value="#request.GBPAUD.bidValue"/></td>
							<td>1.0000</td>
							<td onclick="forwardSell('<ww:property value="#request.GBPJPY.Ccy"/>','<ww:property value="#request.GBPJPY.bidValue"/>')"><ww:property value="#request.GBPJPY.bidValue"/></td>
							<td onclick="forwardSell('<ww:property value="#request.GBPCAD.Ccy"/>','<ww:property value="#request.GBPCAD.bidValue"/>')"><ww:property value="#request.GBPCAD.bidValue"/></td>
						</tr>
						<tr>
							<th>JPY</th>
							<td onclick="forwardSell('<ww:property value="#request.JPYUSD.Ccy"/>','<ww:property value="#request.JPYUSD.bidValue"/>')"><ww:property value="#request.JPYUSD.bidValue"/></td>
							<td onclick="forwardSell('<ww:property value="#request.JPYEUR.Ccy"/>','<ww:property value="#request.JPYEUR.bidValue"/>')"><ww:property value="#request.JPYEUR.bidValue"/></td>
							<td onclick="forwardSell('<ww:property value="#request.JPYAUD.Ccy"/>','<ww:property value="#request.JPYAUD.bidValue"/>')"><ww:property value="#request.JPYAUD.bidValue"/></td>
							<td onclick="forwardSell('<ww:property value="#request.JPYGBP.Ccy"/>','<ww:property value="#request.JPYGBP.bidValue"/>')"><ww:property value="#request.JPYGBP.bidValue"/></td>
							<td>1.0000</td>
							<td onclick="forwardSell('<ww:property value="#request.JPYCAD.Ccy"/>','<ww:property value="#request.JPYCAD.bidValue"/>')"><ww:property value="#request.JPYCAD.bidValue"/></td>
						</tr>
						<tr>
							<th>CAD</th>
							<td onclick="forwardSell('<ww:property value="#request.CADUSD.Ccy"/>','<ww:property value="#request.CADUSD.bidValue"/>')"><ww:property value="#request.CADUSD.bidValue"/></td>
							<td onclick="forwardSell('<ww:property value="#request.CADEUR.Ccy"/>','<ww:property value="#request.CADEUR.bidValue"/>')"><ww:property value="#request.CADEUR.bidValue"/></td>
							<td onclick="forwardSell('<ww:property value="#request.CADAUD.Ccy"/>','<ww:property value="#request.CADAUD.bidValue"/>')"><ww:property value="#request.CADAUD.bidValue"/></td>
							<td onclick="forwardSell('<ww:property value="#request.CADGBP.Ccy"/>','<ww:property value="#request.CADGBP.bidValue"/>')"><ww:property value="#request.CADGBP.bidValue"/></td>
							<td onclick="forwardSell('<ww:property value="#request.CADJPY.Ccy"/>','<ww:property value="#request.CADJPY.bidValue"/>')"><ww:property value="#request.CADJPY.bidValue"/></td>
							<td>1.0000</td>
						</tr>
					</table>
				</div>
				<div class="modules"  id="OptionsTradingBid">
					<label>期权交易(卖价)</label>
					<table class="gridtable">
						<tr>
							<th></th>
							<th>USD</th>
							<th>EUR</th>
							<th>AUD</th>
							<th>GBP</th>
							<th>JPY</th>
							<th>CAD</th>
						</tr>

						<tr>
							<th>USD</th>
							<td>1.0000</td>
							<td onclick="optionSell('<ww:property value="#request.USDEUR.Ccy"/>','<ww:property value="#request.USDEUR.bidValue"/>')"><ww:property value="#request.USDEUR.bidValue"/></td>
							<td onclick="optionSell('<ww:property value="#request.USDAUD.Ccy"/>','<ww:property value="#request.USDAUD.bidValue"/>')"><ww:property value="#request.USDAUD.bidValue"/></td>
							<td onclick="optionSell('<ww:property value="#request.USDGBP.Ccy"/>','<ww:property value="#request.USDGBP.bidValue"/>')"><ww:property value="#request.USDGBP.bidValue"/></td>
							<td onclick="optionSell('<ww:property value="#request.USDJPY.Ccy"/>','<ww:property value="#request.USDJPY.bidValue"/>')"><ww:property value="#request.USDJPY.bidValue"/></td>
							<td onclick="optionSell('<ww:property value="#request.USDCAD.Ccy"/>','<ww:property value="#request.USDCAD.bidValue"/>')"><ww:property value="#request.USDCAD.bidValue"/></td>
						</tr>
						
						<tr>
							<th>EUR</th>
							<td onclick="optionSell('<ww:property value="#request.EURUSD.Ccy"/>','<ww:property value="#request.EURUSD.bidValue"/>')"><ww:property value="#request.EURUSD.bidValue"/></td>
							<td >1.0000</td>
							<td onclick="optionSell('<ww:property value="#request.EURAUD.Ccy"/>','<ww:property value="#request.EURAUD.bidValue"/>')"><ww:property value="#request.EURAUD.bidValue"/></td>
							<td onclick="optionSell('<ww:property value="#request.EURGBP.Ccy"/>','<ww:property value="#request.EURGBP.bidValue"/>')"><ww:property value="#request.EURGBP.bidValue"/></td>
							<td onclick="optionSell('<ww:property value="#request.EURJPY.Ccy"/>','<ww:property value="#request.EURJPY.bidValue"/>')"><ww:property value="#request.EURJPY.bidValue"/></td>
							<td onclick="optionSell('<ww:property value="#request.EURCAD.Ccy"/>','<ww:property value="#request.EURCAD.bidValue"/>')"><ww:property value="#request.EURCAD.bidValue"/></td>
						</tr>
						<tr>
							<th>AUD</th>
							<td onclick="optionSell('<ww:property value="#request.AUDUSD.Ccy"/>','<ww:property value="#request.AUDUSD.bidValue"/>')"><ww:property value="#request.AUDUSD.bidValue"/></td>
							<td onclick="optionSell('<ww:property value="#request.AUDEUR.Ccy"/>','<ww:property value="#request.AUDEUR.bidValue"/>')"><ww:property value="#request.AUDEUR.bidValue"/></td>
							<td >1.0000</td>
							<td onclick="optionSell('<ww:property value="#request.AUDGBP.Ccy"/>','<ww:property value="#request.AUDGBP.bidValue"/>')"><ww:property value="#request.AUDGBP.bidValue"/></td>
							<td onclick="optionSell('<ww:property value="#request.AUDJPY.Ccy"/>','<ww:property value="#request.AUDJPY.bidValue"/>')"><ww:property value="#request.AUDJPY.bidValue"/></td>
							<td onclick="optionSell('<ww:property value="#request.AUDCAD.Ccy"/>','<ww:property value="#request.AUDCAD.bidValue"/>')"><ww:property value="#request.AUDCAD.bidValue"/></td>
						</tr>
						<tr>
							<th>GBP</th>
							<td onclick="optionSell('<ww:property value="#request.GBPUSD.Ccy"/>','<ww:property value="#request.GBPUSD.bidValue"/>')"><ww:property value="#request.GBPUSD.bidValue"/></td>
							<td onclick="optionSell('<ww:property value="#request.GBPEUR.Ccy"/>','<ww:property value="#request.GBPEUR.bidValue"/>')"><ww:property value="#request.GBPEUR.bidValue"/></td>
							<td onclick="optionSell('<ww:property value="#request.GBPAUD.Ccy"/>','<ww:property value="#request.GBPAUD.bidValue"/>')"><ww:property value="#request.GBPAUD.bidValue"/></td>
							<td>1.0000</td>
							<td onclick="optionSell('<ww:property value="#request.GBPJPY.Ccy"/>','<ww:property value="#request.GBPJPY.bidValue"/>')"><ww:property value="#request.GBPJPY.bidValue"/></td>
							<td onclick="optionSell('<ww:property value="#request.GBPCAD.Ccy"/>','<ww:property value="#request.GBPCAD.bidValue"/>')"><ww:property value="#request.GBPCAD.bidValue"/></td>
						</tr>
						<tr>
							<th>JPY</th>
							<td onclick="optionSell('<ww:property value="#request.JPYUSD.Ccy"/>','<ww:property value="#request.JPYUSD.bidValue"/>')"><ww:property value="#request.JPYUSD.bidValue"/></td>
							<td onclick="optionSell('<ww:property value="#request.JPYEUR.Ccy"/>','<ww:property value="#request.JPYEUR.bidValue"/>')"><ww:property value="#request.JPYEUR.bidValue"/></td>
							<td onclick="optionSell('<ww:property value="#request.JPYAUD.Ccy"/>','<ww:property value="#request.JPYAUD.bidValue"/>')"><ww:property value="#request.JPYAUD.bidValue"/></td>
							<td onclick="optionSell('<ww:property value="#request.JPYGBP.Ccy"/>','<ww:property value="#request.JPYGBP.bidValue"/>')"><ww:property value="#request.JPYGBP.bidValue"/></td>
							<td>1.0000</td>
							<td onclick="optionSell('<ww:property value="#request.JPYCAD.Ccy"/>','<ww:property value="#request.JPYCAD.bidValue"/>')"><ww:property value="#request.JPYCAD.bidValue"/></td>
						</tr>
						<tr>
							<th>CAD</th>
							<td onclick="optionSell('<ww:property value="#request.CADUSD.Ccy"/>','<ww:property value="#request.CADUSD.bidValue"/>')"><ww:property value="#request.CADUSD.bidValue"/></td>
							<td onclick="optionSell('<ww:property value="#request.CADEUR.Ccy"/>','<ww:property value="#request.CADEUR.bidValue"/>')"><ww:property value="#request.CADEUR.bidValue"/></td>
							<td onclick="optionSell('<ww:property value="#request.CADAUD.Ccy"/>','<ww:property value="#request.CADAUD.bidValue"/>')"><ww:property value="#request.CADAUD.bidValue"/></td>
							<td onclick="optionSell('<ww:property value="#request.CADGBP.Ccy"/>','<ww:property value="#request.CADGBP.bidValue"/>')"><ww:property value="#request.CADGBP.bidValue"/></td>
							<td onclick="optionSell('<ww:property value="#request.CADJPY.Ccy"/>','<ww:property value="#request.CADJPY.bidValue"/>')"><ww:property value="#request.CADJPY.bidValue"/></td>
							<td>1.0000</td>
						</tr>
					</table>
				</div>--%>

			</div>
		</form>
		   
		 <div id="ectable">
	    	<form action="recordToEctableByParams.action" method="post" style="margin:0;">
		    	<label style="margin-right: 50px; font-weight: bolder;">交易记录</label>
		    	交易类型:
		    	<select name="tradeType">
		    		<option value="0" <ww:if test="#request.tradeType=='0'">selected</ww:if>></option>
			    	<ww:iterator value="#request.tradeTypeList">
			    		<option value="<ww:property value='id.codeVal' />"<ww:if test="#request.tradeType == id.codeVal">selected</ww:if>>
			    			<ww:property value="descr"/>
			    		</option>
			    	</ww:iterator>
		    	</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    	交易状态:<select name="tradeStatus">
		    		<option value="0"></option>
		    		<ww:iterator value="#request.tradeStatusList">
		    			<option value="<ww:property value='id.codeVal' />"<ww:if test="#request.tradeStatus == id.codeVal">selected</ww:if>>
		    				<ww:property value="descr"/>
		    			</option>
	    			</ww:iterator>
		    	</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    	<input id="button" type="submit" value="搜  索"  />
		    	<br>
		    </form>
	   		<ec:table items="recordList" var="c" action=""
					border="" cellpadding="0" cellspacing="0" style="eXtremeTable">
				<ec:export view="xls" fileName="交易列表.xls" imageName="xls"
					viewResolver="xls" tooltip="导出 Excel" />
				<ec:row>
					<ec:column property="tradeNo" title="交易序号" width="100"
						headerStyle="text-align:center" style="text-align:left" viewsAllowed="html" >
					</ec:column>
					<ec:column property="tradeType" title="交易类型" width="100"
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
					<ec:column property="realStatus" title="交易状态" width="80"
						headerStyle="text-align:center" style="text-align:left"/>
					<ec:column property="oper" title="操作" headerStyle="text-align:center" 
						style="text-align:left" width="50" viewsAllowed="html" sortable="false">
					<script type="text/javascript">
					     document.write('<a href="queryDetail.action?tradeNo='+'${c.tradeNo }'+'">查看</a>');
					 </script>
					 </ec:column>
			</ec:row>
			</ec:table>	
    	</div>
	</div>


</body>
</html>
