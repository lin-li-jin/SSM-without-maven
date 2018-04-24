<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib uri="webwork" prefix="ww"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>保证金即期交易页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="resources/ponyjs/jquery.js" type="text/javascript"></script>
	<script language="javascript" type="text/javascript"
	src="resources/calendar/js/My97DatePicker/WdatePicker.js"></script>
	<style type="text/css">
		#all{margin: 0;padding: 0; height: auto; width: 800px;}
		#top{margin: 0; padding:3px 5px; height: 24px; width:790px; line-height: 24px; 
		background-image: url("images/b.png"); color: white; text-align: center; }
		#container{margin: 0;padding: 0; height: auto; width: 800px !important;width:790px; background-color: #E5F2F8;
		margin-top: 5px; margin-bottom: 5px;}
		#wrapOne{margin: 0;padding: 0; height: 50px; width: 800px !important;width:790px;  margin-bottom: 3px; 
		line-height: 50px; text-align: center;}
		label { font-size: 24px; font-weight: bolder; color: red;} 
		.modules1{margin: 0;padding: 0;; height: 290px; width: 800px !important;width:790px; 
		margin-top: 2px; line-height: 20px;}
		.modules1left{margin:0 ;padding: 5px 0 0 60px; height: 290px; width: 340px; float:left;
		 }
		 .modules1right{margin: 0;padding: 5px 0 0 0px; height: 280px; width: 180px;  float:left; 
		 }
		.xiegan{font-family:Arial;font-weight:bolder;font-style:normal;text-decoration:none;
		color:#333333;font-size: 18px; }
		.button1{width: 80px;}
		.button2{ width: 80px; height: 24px;}
		.part{margin: 0; padding: 0; width: auto; height: auto; line-height: 24px; }
		.a{margin-left: 130px;}
		.left{display: inline;margin-right: 80px;padding-right: 80px;}
		.right{display: inline;}
	</style>
	<script type="text/javascript">
		$(function(){
			$('input:radio').click(function () {
					this.blur();   
					this.focus(); 
			});
		});
		function selectTradeType(direction,price,ccy){
			var temp = document.getElementsByName("cashType");
			for (var i=0;i<temp.length;i++){
				if (temp[i].checked)
					var value = temp[i].value;
			}
			switch(value){
				case "0":
					window.location.href = "forwardCashPageInit.action?direction="+direction+"&price="+price+"&ccy="+ccy;
					break;
				case "1":
					window.location.href = "optionCashPageInit.action?direction="+direction+"&price="+price+"&ccy="+ccy;
					break;
				case "2":
					window.location.href="spotCashPageInit.action?direction="+direction+"&price="+price+"&ccy="+ccy;
					break;
		}
		}
	
		function isNumberBy100(ssn) {
			 var re = /^[0-9]*[0-9]$/i;       //校验是否为数字
			 if(re.test(ssn) && ssn%100==0) {
			  return true;
			 }else {
			  return false;
			 }
		}

        function toPoint(percent){
            var str=percent.replace("%","");
            str= str/100;
            return str;
        }
		
		function beFactor(accountAmount){
			var factor = document.getElementById("rate").value;
			factor=toPoint(factor);
			document.getElementById("dealAmount").value=factor*accountAmount;
		}
		
		function tijiao(){
			var amount = document.getElementById("accountAmount").value;
			if(!isNumberBy100(amount)){
				alert("accountAmount只能是100的整数");
				return;
			}else{
				document.forwardCashForm.submit();
			}
		}
		
		function refreshPrice(){
			var direction = '<ww:property value="#request.direction" />';
			var ccy = '<ww:property value="#request.ccy" />';
				$.post("freshPrice.action",{direction:direction,ccy:ccy},
			function(dat){
				document.getElementById("price").value =dat;
			});	
		}
	</script>
  </head>
  
  <body>
    <div id="all">
    	<form action="spotCashPageAdd.action" name="forwardCashForm">
    	<div id="top">
	    	<input type="radio" name="cashType" value="0" onchange="selectTradeType('<ww:property value="#request.direction"/>','<ww:property value="#request.price"/>','<ww:property value="#request.ccy"/>')" <ww:if test="#request.radioValue == 0">checked</ww:if>>保证金远期交易
	    	<input type="radio" name="cashType" value="1" onchange="selectTradeType('<ww:property value="#request.direction"/>','<ww:property value="#request.price"/>','<ww:property value="#request.ccy"/>')" <ww:if test="#request.radioValue == 1">checked</ww:if>>保证金期权交易
			<input type="radio" name="cashType" value="2" onchange="selectTradeType('<ww:property value="#request.direction"/>','<ww:property value="#request.price"/>','<ww:property value="#request.ccy"/>')" <ww:if test="#request.radioValue == 2">checked</ww:if>>保证金即期交易
		</div>
			<div id="container">
				<div id="wrapOne">
					<div class="left">
						<span>保证金账户余额:</span>
						<%--目前只考虑买，当买入时，查询使用来买入的保证金余额--%>
						<span><ww:property value="#request.amount"/></span>
						<%--使用来买入的货币类型--%>
						<span><ww:property value="#request.weCcy"/></span>
					</div>
					<div class="right">
						<span>保证金比例</span>
						<select name="rate" id="rate" style="width: 80px;">
							<option value="200%">200%</option>
						</select>
					</div>
				</div>
			<div id="wrapOne">
				<input type="hidden" name="msim.direction" value="<ww:property value='#request.direction'/>">
				<input type="hidden" name="msim.weCcy" value="<ww:property value='#request.weCcy'/>">
				<input type="hidden" name="msim.anaCcy" value="<ww:property value='#request.anaCcy'/>">
				<ww:if test="#request.direction==1">
					<label >I BUY <ww:property value="#request.anaCcy"/> vs <ww:property value="#request.weCcy"/></label>
				</ww:if>
				<ww:else>
					<label >I SELL <ww:property value="#request.weCcy"/> vs <ww:property value="#request.anaCcy"/></label>
				</ww:else>
			</div>
			<div style="display:inline;margin-top: 20px;margin-left: 60px;">
				<span>
					<ww:if test="#request.direction==1">
						I BUY <ww:property value="#request.anaCcy"/>
					</ww:if>
					<ww:else>
						I SELL <ww:property value="#request.weCcy"/>
					</ww:else>
				</span>
						<span>Amount:</span>
						<input id="accountAmount" type="text" style="width: 80px;" name="msim.accAmount" onkeyup="this.value=this.value.replace(/\D/g,'');beFactor(this.value);"/>
			</div>
				<div style="display: inline;margin-left: 100px">
					Deal Amount:
					<input id="dealAmount" type="text" style="width: 80px;" readonly="readonly"  name="msim.dealAmt"/>
				</div>

			<div style="margin-top: 20px;margin-left: 60px;padding-bottom: 10px;">

				<span>price:</span>
				<input id="price" name="msim.price" type="text" size="10" readonly="readonly" value="<ww:property value='#request.price'/>" style="margin-left: 30px;">
				<input type="button" value="flip" style="width: 100px;" onclick="refreshPrice()">

			</div>
			</div>
			<div id="top" style="margin-top: 5px;">
				<input type="button" value="active" style="margin-right: 150px;" class="button2" onclick="tijiao()">
				<input type="button" value="delete" class="button2" onclick="window.location.href = 'cashQueryInit.action'">
			</div>
    	</form>

    </div>
  </body>
</html>
