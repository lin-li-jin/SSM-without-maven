<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib uri="webwork" prefix="ww"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>保证金期权交易页面</title>
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
		.modules1{margin: 0;padding: 0;; height: 290; width: 800px !important;width:790px;  
		margin-top: 2px; line-height: 20px; }
		.modules1left{margin:0 ;padding: 5px 0 0 60px; height: 290px; width: 340px; float:left; }
		 .modules1right{margin: 0;padding: 5px 0 0 0px; height: 280px; width: 180px;  float:left; }
		.xiegan{font-family:Arial;font-weight:bolder;font-style:normal;text-decoration:none;
		color:#333333;font-size: 18px; }
		.button1{width: 80px;}
		.button2{ width: 80px; height: 24px;}
		.part{margin: 0; padding: 0; width: auto; height: auto; line-height: 24px; }
		.a{margin-left: 130px;}
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
		
		function beFactor(accountAmount){
			var factor = document.getElementById("factor").value;
			document.getElementById("dealAmount").value=factor*accountAmount;
			document.getElementById("premium").value=accountAmount*0.02;
		}
		
		function tijiao(){
			var amount = document.getElementById("accountAmount").value;
			if(!isNumberBy100(amount)){
				alert("accountAmount只能是100的整数");
				return;
			}else{
				document.optionCashForm.submit();
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
    	<form action="optionCashPageAdd.action" name="optionCashForm">
    	<div id="top">
	    	<input type="radio" name="cashType" value="0" onchange="selectTradeType('<ww:property value="#request.direction"/>','<ww:property value="#request.price"/>','<ww:property value="#request.ccy"/>')" <ww:if test="#request.radioValue == 0">checked</ww:if>>保证金远期交易
	    	<input type="radio" name="cashType" value="1" onchange="selectTradeType('<ww:property value="#request.direction"/>','<ww:property value="#request.price"/>','<ww:property value="#request.ccy"/>')" <ww:if test="#request.radioValue == 1">checked</ww:if> >保证金期权交易
			<input type="radio" name="cashType" value="2" onchange="selectTradeType('<ww:property value="#request.direction"/>','<ww:property value="#request.price"/>','<ww:property value="#request.ccy"/>')" <ww:if test="#request.radioValue == 2">checked</ww:if>>保证金即期交易
    	</div>
    	<div id="container">
    		<div id="wrapOne">
    			<ww:if test="#request.direction==1">
    				<label >I BUY <ww:property value="#request.anaCcy"/> vs <ww:property value="#request.weCcy"/></label>
    			</ww:if>
	  			<ww:else>
	  				<label >I SELL <ww:property value="#request.weCcy"/> vs <ww:property value="#request.anaCcy"/></label>
	  			</ww:else>
	  			<!-- 暂时没有数据  先用静态的数据提交到action -->
	  			<input type="hidden" name="moim.weCcy" value="<ww:property value='#request.weCcy'/>">
	  			<input type="hidden" name="moim.anaCcy" value="<ww:property value='#request.anaCcy'/>">
  			</div>
  			
  			<div class="modules1">
  				<div class="modules1left">
  				  	<span style="margin-right: 25px;">Option Type:</span>
  				  	<ww:if test="#request.direction==1">
    					<label>Call</label>
    					<input type="hidden" value="call" name="moim.optionType">
    				</ww:if>
	  				<ww:else>
	  					<label>Put</label>
	  					<input type="hidden" value="put" name="moim.optionType">
	  				</ww:else>
	  				
  					<br><br>
  					<span style="margin-right: 32px;">Maturity:</span>
  					<select name="moim.valueDate">
  						<option value="5d" selected="selected">5 days</option>
  						<option value="14d">14 days</option>
  						<option value="30d">30 days</option>
  						<option value="60d">60 days</option>
  					</select>
  					<br><br>
  					
  					<span>Enlargement Factor:</span>
					<span>
						<select id="factor">
						 	<option value="5" selected>5</option>
							<option value="10">10</option>
							<option value="20">20</option>
					</select>
					<input id="factor" type="hidden" name="moim.factor" value="<ww:property value='#request.factor'/>" />
  					<br><br>
  					
  					<span>Account Amount:</span>
  					<input id="accountAmount" name="moim.accountAmount" type="text" size="15" onkeyup="this.value=this.value.replace(/\D/g,'');beFactor(this.value);">
  					<br><br>
  					
  					<span>Premium:</span>
  					<input type="text" id="premium" name="moim.premium" size="15" readonly="readonly" style="margin-left: 57px;"  />
  					<br><br>

  					
  					<span style="margin-right: 25px;">Deal Amount:</span>
 					<input id="dealAmount" name="moim.dealAmount" type="text" size="15" readonly="readonly" >
  					<br><br>

  					<span>Price:</span>
 					<input id="price" name="moim.price" type="text" size="10" readonly="readonly" value="<ww:property value='#request.price'/>" style="margin-left: 30px;">
 					<input type="button" value="Market Price" style="width: 100px;" onclick="refreshPrice()">
  				</div>
  				<div class="modules1right">
  					<!-- 买卖方向 -->
  					<input type="hidden" name="moim.direction" value="<ww:property value='#request.direction'/>"> 				
  					Account:<label style="font-size: 16px;"><ww:property value="#request.accnoB" /></label>
  					<div class="part">
  					<br>
  					<span>Account Balance:</span><br>
  					<span>账户余额:</span><br>
  					<input type="hidden" name="moim.account" value="<ww:property value='#request.accnoB'/>" />
					<ww:iterator value="#request.accInfoListB">
					  <ww:property value="ccy"/>:<ww:property value="amount"/><br>
					</ww:iterator>
  					</div>
  				</div>
  				<div class="modules1right">
  					<div class="part">
  					<br><br><br>
  					<span>可用余额:</span>
  					<br>
					<ww:iterator value="#request.valueAmountList">
					  <ww:property value="ccy"/>:<ww:property value="returnAmount"/>
					  <br>
					</ww:iterator>
  					</div>
  				</div>
  			</div>
    </div>
      <div id="top" style="margin-top: 5px;">
			<input type="button" value="submit" style="margin-right: 150px;" class="button2" onclick="tijiao()">
			<input type="button" value="withdraw" class="button2" onclick="window.location.href = 'cashQueryInit.action'">
    	</div>
    	</form>
    	
    </div>
  </body>
</html>
