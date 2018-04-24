<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>大额录入</title>
</head>
<script>
</script>
<body>
<div style="background-color:#AEEEEE; text-align:center">
	<form name="hvpsCreditForm" action="packageMessage.action" method="post">
		<fieldset>
			<legend class="mylegend" id="90">
				普通境内汇兑业务录入
			</legend>
			 <table cellspacing="0" cellpadding="5" width="80%" align="center"> 
				<tr>
					<td style="text-align: right; width: 15%">
						业务类型编码：
					</td>
					<td style="text-align: left; width: 35%"" id="txnTypeTd">
						<select name="txnType" id="txnType" style="width: 150px;">
							<option value="A">A-普通贷记业务</option>
							<option value="B">B-普通借记业务</option>
							<option value="C">C-实时贷记业务</option>
							<option value="D">D-实时借记业务</option>
						</select>
						<font color="red">*</font>
					</td>
					<td style="text-align: right; width: 15%">
						业务种类编码：
					</td>
					<td style="text-align: left; width: 35%"">
						<select name="tranType" id="tranType" style="width: 150px;">
							<option value="02101">02101-现金汇款</option>
							<option value="02102">02102-普通汇兑</option>
							<option value="02103">02103-网银支付</option>
							<option value="02104">02104-外汇清算</option>
						</select>
						<font color="red">*</font>
					</td>
				</tr>
				<tr>										
					<td style="text-align: right;">
						业务优先级：
					</td>
					<td style="text-align: left">
						<select name="priority" id="priority" style="width: 150px;">
							<option value="A">普通</option>
							<option value="B">紧急</option>
							<option value="C">特急</option>
						</select>
						<font color="red">*</font>
					</td>
					<td style="text-align: right;">
						行内流水号：
					</td>
					<td style="text-align: left" >
						<input type="text" name="endToEndId" id="endToEndId" />
						<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">
						币种：
					</td>
					<td style="text-align: left">
						<select name="curency" id="curency" style="width: 150px;">
							<option value="CNY">人民币</option>
						</select>
						<font color="red">*</font>
					</td>
					<td style="text-align: right;">
						金额：
					</td>
					<td style="text-align: left">
						<input type="text" name="amt" id="amt" maxLength="20"/>
						<font color="red">***.**元</font>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">
						客户/金融机构：
					</td>
					<td style="text-align: left">
						<select name="ugFlag" id="ugFlag" style="width: 150px;">
							<option value="U">客户</optiRHon>
							<option value="G">金融机构</option>
						</select>
						<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">
						付款行行号：
					</td>
					<td style="text-align: left">
						<input type="text" name="payerBrnchBankId" id="payerBrnchBankId"/>
						<font color="red">*</font>
					</td>
					<td style="text-align: right;">
						付款行行名：
					</td>
					<td style="text-align: left">
						<input type="text" name="payerBrnchBankName" id="payerBrnchBankName"/>
						<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">
						付款行清算行号：
					</td>
					<td style="text-align: left">
						<input type="text" name="payerSttlmBankId" id="payerSttlmBankId"/>
						<font color="red">*</font>
					</td>
					<td style="text-align: right;">
						付款行清算行名：
					</td>
					<td style="text-align: left">
						<input type="text" name="payerSttlmBankName" id="payerSttlmBankName"/>
						<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">
						付款人开户行行号：
					</td>
					<td style="text-align: left">
						<input type="text" name="payerAcctBankId" id="payerAcctBankId"/>
					</td>
					<td style="text-align: right;">
						付款人开户行行名：
					</td>
					<td style="text-align: left">
						<input type="text" name="payerAcctBankName" id="payerAcctBankName"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">
						付款人账号：
					</td>
					<td style="text-align: left">
						<input type="text" name="payerAcctNo" id="payerAcctNo" maxLength="32"/>
						<font color="red">*</font>
					</td>
					<td style="text-align: right;">
						付款人名称：
					</td>
					<td style="text-align: left">
						<input type="text" name="payerName" id="payerName" maxLength="20"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">
						付款人地址：
					</td>
					<td style="text-align: left" colspan="3">
						<input type="text" name="payerAddr" id="payerAddr" maxLength="70" style="width: 700px;" />
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">
						收款行开户行行号：
					</td>
					<td style="text-align: left">
						<input type="text" name="payeeAcctBankId" id="payeeAcctBankId"/>
					</td>
					<td style="text-align: right;">
						收款行开户行行名：
					</td>
					<td style="text-align: left">
						<input type="text" name="payeeAcctBankName" id="payeeAcctBankName"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">
						收款行行号：
					</td>
					<td style="text-align: left">
						<input type="text" name="payeeBrnchBankId" id="payeeBrnchBankId"/>
					</td>
					<td style="text-align: right;">
						收款行行名：
					</td>
					<td style="text-align: left">
						<input type="text" name="payeeBrnName" id="payeeBrnName"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">
						收款行清算行号：
					</td>
					<td style="text-align: left" colspan="3">
						<input type="text" name="payeeSttlmBankId" id="payeeSttlmBankId"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">
						收款人账号：
					</td>
					<td style="text-align: left">
						<input type="text" name="payeeAcctNo" id="payeeAcctNo" maxLength="32"/> 
						<font color="red">*</font>
					</td>
					<td style="text-align: right;">
						收款人名称：
					</td>
					<td style="text-align: left">
						<input type="text" name="payeeName" id="payeeName" maxLength="60"/> 
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">
						收款人地址：
					</td>
					<td style="text-align: left" colspan="3">
						<input type="text" name="payeeAddr" id="payeeAddr" maxLength="70"/>
					</td>
				</tr>
				<tr>	
					<td style="text-align: right;">
						备注：
					</td>
					<td style="text-align: left" colspan="3">
						<input type="text" name="rmk" id="rmk" maxLength="135" style="width: 700px;">
					</td>
				</tr>
				<tr>	
					<td style="text-align: right;">
						附言：
					</td>
					<td style="text-align: left" colspan="3">
						<input type="text" id="addtn" name="addtn" maxLength="135" style="width: 700px;"/>
					</td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<input type="submit" name="subBtn" value="提交"/>
					    <input type="reset" value="重置"/>
					</td>
				</tr>
			</table> 
			<div style="color: green">${retrunMsg }</div>
		</fieldset>
	</form>
</div>
</body>
</html>