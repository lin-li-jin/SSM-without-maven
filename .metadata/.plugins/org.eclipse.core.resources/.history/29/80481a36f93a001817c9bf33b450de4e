<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>大额详细</title>
</head>
<script>
</script>
<body>
<div style="background-color:#AEEEEE; text-align:center">
	<fieldset>
		<legend class="mylegend" id="90">
			普通境内汇兑业务详细
		</legend>
		 <table cellspacing="0" cellpadding="5" width="80%" align="center"> 
			<tr>
				<td style="text-align: right; width: 15%">
					业务类型编码：
				</td>
				<td style="text-align: left; width: 35%" id="txnTypeTd">
					<c:if test="${model.txnType eq 'A' }">A-普通贷记业务</c:if>
					<c:if test="${model.txnType eq 'B' }">B-普通借记业务</c:if>
					<c:if test="${model.txnType eq 'C' }">C-实时贷记业务</c:if>
					<c:if test="${model.txnType eq 'C' }">D-实时借记业务</c:if>
				</td>
				<td style="text-align: right; width: 15%">
					业务种类编码：
				</td>
				<td style="text-align: left; width: 35%">
					<c:if test="${model.tranType eq '02101' }">02101-现金汇款</c:if>
					<c:if test="${model.tranType eq '02102' }">02102-普通汇兑</c:if>
					<c:if test="${model.tranType eq '02103' }">02103-网银支付</c:if>
					<c:if test="${model.tranType eq '02104' }">02104-外汇清算</c:if>
				</td>
			</tr>
			<tr>										
				<td style="text-align: right;">
					业务优先级：
				</td>
				<td style="text-align: left">
					<c:if test="${model.priority eq 'A' }">普通</c:if>
					<c:if test="${model.priority eq 'B' }">紧急</c:if>
					<c:if test="${model.priority eq 'C' }">特急</c:if>
				</td>
				<td style="text-align: right;">
					行内流水号：
				</td>
				<td style="text-align: left" >
					${model.endToEndId}
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">
					币种：
				</td>
				<td style="text-align: left">
					<c:if test="${model.curency eq 'CNY' }">人民币</c:if>
				</td>
				<td style="text-align: right;">
					金额：
				</td>
				<td style="text-align: left">
					${model.amt}
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">
					客户/金融机构：
				</td>
				<td style="text-align: left">
					<c:if test="${model.ugFlag eq 'U' }">客户</c:if>
					<c:if test="${model.ugFlag eq 'G' }">金融机构</c:if>
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">
					付款行行号：
				</td>
				<td style="text-align: left">
					${model.payerBrnchBankId}
				</td>
				<td style="text-align: right;">
					付款行行名：
				</td>
				<td style="text-align: left">
					${model.payerBrnchBankName}
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">
					付款行清算行号：
				</td>
				<td style="text-align: left">
					${model.payerSttlmBankId}
				</td>
				<td style="text-align: right;">
					付款行清算行名：
				</td>
				<td style="text-align: left">
					${model.payerSttlmBankName}
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">
					付款人开户行行号：
				</td>
				<td style="text-align: left">
					${model.payerAcctBankId}
				</td>
				<td style="text-align: right;">
					付款人开户行行名：
				</td>
				<td style="text-align: left">
					${model.payerAcctBankName}
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">
					付款人账号：
				</td>
				<td style="text-align: left">
					${model.payerAcctNo}
				</td>
				<td style="text-align: right;">
					付款人名称：
				</td>
				<td style="text-align: left">
					${model.payerName}
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">
					付款人地址：
				</td>
				<td style="text-align: left" colspan="3">
					${model.payerAddr}
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">
					收款行开户行行号：
				</td>
				<td style="text-align: left">
					${model.payeeAcctBankId}
				</td>
				<td style="text-align: right;">
					收款行开户行行名：
				</td>
				<td style="text-align: left">
					${model.payeeAcctBankName}
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">
					收款行行号：
				</td>
				<td style="text-align: left">
					${model.payeeBrnchBankId}
				</td>
				<td style="text-align: right;">
					收款行行名：
				</td>
				<td style="text-align: left">
					${model.payeeBrnName}
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">
					收款行清算行号：
				</td>
				<td style="text-align: left" colspan="3">
					${model.payeeSttlmBankId}
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">
					收款人账号：
				</td>
				<td style="text-align: left">
					${model.payeeAcctNo}
				</td>
				<td style="text-align: right;">
					收款人名称：
				</td>
				<td style="text-align: left">
					${model.payeeName}
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">
					收款人地址：
				</td>
				<td style="text-align: left" colspan="3">
					${model.payeeAddr}
				</td>
			</tr>
			<tr>	
				<td style="text-align: right;">
					备注：
				</td>
				<td style="text-align: left" colspan="3">
					${model.rmk}
				</td>
			</tr>
			<tr>	
				<td style="text-align: right;">
					附言：
				</td>
				<td style="text-align: left" colspan="3">
					${model.addtn}
				</td>
			</tr>
		</table> 
		<div style="color: green">${retrunMsg }</div>
	</form>
</div>
</body>
</html>