<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ taglib prefix="ww" uri="webwork"%>
<%@ taglib uri="extremecomponents" prefix="ec"%>



<HTML>
	<HEAD>
		<title>修改密码</title>
		<link rel="stylesheet" href="resources/css/style.css" type="text/css" media="screen, print" />
		<link rel="stylesheet" href="resources/css/extremecomponents.css" type="text/css" />
		<script src="/ems/resources/js/batchOper.js" type="text/javascript"></script>
		<script src="/ems/resources/ponyjs/jquery.js" type="text/javascript"></script>
		<script src="/ems/resources/ponyjs/jquery_validate.js" type="text/javascript"></script>
		<script src="/ems/resources/ponyjs/jquery.ui.draggable.js" type="text/javascript"></script>
		<script src="/ems/resources/ponyjs/jquery.alerts.js" type="text/javascript"></script>	
		<script src="/ems/resources/js/check.js" type="text/javascript"></script>	
		<script src="/ems/jsp/local/userMng/userInfoMng/js/userInfoMng.js" type="text/javascript"></script>
		<script src="/ems/jsp/local/teaGroupMng/js/teaGroup.js"
			type="text/javascript"></script>		

	</HEAD>

	<body>
		<div id="body">
		<table WIDTH=100%>
			<!-- form表单 -->
			<tr><td>
				<form name="pwdModifyForm" action="pwdModify.action" method="post">
					<table width="100%" border="0" height="">
						<tr><td align="center">
							<FIELDSET align="center"><LEGEND>修改密码</LEGEND>
								<div id="queryConditDiv">
									<table width="100%" border="0">
										<tr><td class="query">
											<TABLE border="0" cellpadding="2" cellspacing="3"
													style="width:700px" align="center"
													 height="100">	
													<tr>
													<td style="text-align: right">
															<span class="bold">姓名:</span>
														</td>
														<td class="left" colspan="1">
															<input readonly type="text" isNeed="y"  cName="姓名" value="<ww:property value="#session.userModel.name"/>">
														    <font color="red">*</font>
														</td>	
														</tr>
														<tr>
														<td style="text-align: right">
															<span class="bold">用户号:</span>
														</td>
														<td class="left" colspan="1">
															<input readonly type="text" isNeed="y" cName="用户号" value="<ww:property value="#session.userModel.userId"/>">
														    <font color="red">*</font>
														</td>
														</tr>
														<tr>
														
									                    <td style="text-align: right">
															<span class="bold">原始密码:</span>
														</td>
														<td class="left" colspan="1">
															<input type="text" isNeed="y" cName="密码"  value="<ww:property value="#session.userModel.password"/>">
														    <font color="red">*</font>
														</td>
														</tr>
														<tr>
														<td style="text-align: right">
															<span class="bold">新密码:</span>
														</td>
														<td class="left" colspan="1">
															<input type="password" isNeed="y" name="userModel.password" id="pwd1"cName="密码" maxLength="6">
														    <font color="red">*</font>
														</td>
														</tr>
														<tr>
														 <td style="text-align: right">
															<span class="bold">确认密码:</span>
														</td>
														<td class="left" colspan="1">
															<input type="password" isNeed="y" id="pwd2"cName="密码" maxLength="6" >
														    <font color="red">*</font>
														</td>
														
																											
													</tr>
																								
												</TABLE>												
										</td></tr>
										<tr><td align="center">
											<div id="queryButtonDiv">													
												<input class="button" type="button"	value="修改" onClick="formSubmit('pwdModifyForm')" >
												<input class="button" type="button" value="返回" onClick="window.history.back()">													
											</div>
										</td></tr>
									</table>
								</div>
							</FIELDSET>
						</td></tr>
					</table>
				</form>
			</td></tr>
			
		</table>
		</div>
	</body>
</HTML>
