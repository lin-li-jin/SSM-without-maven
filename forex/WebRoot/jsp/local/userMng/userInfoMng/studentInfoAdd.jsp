<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ taglib prefix="ww" uri="webwork"%>
<%@ taglib uri="extremecomponents" prefix="ec"%>



<HTML>
	<HEAD>
		<title>新增学生信息</title>
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
				<form name="studentInfoAddForm" action="studentInfoAdd.action" method="post">
					<table width="100%" border="0" height="">
						<tr><td align="center">
							<FIELDSET align="center"><LEGEND>新增学生信息</LEGEND>
								<div id="queryConditDiv">
									<table width="100%" border="0">
										<tr><td class="query">
											<TABLE border="0" cellpadding="2" cellspacing="3"
													style="width:600px" align="center"
													onKeyDown="javascript:keyHandle();">	
													<tr>
														<td align=right>
															<span class="bold">学号:</span>
														</td>
														<td style="text-align:left; width:200px" colspan="1">
															<input type="text" isNeed="y" cName="学号" id="studentNum" name="student.studentNum" onblur="checkStudentNum()">
														    <font color="red">*</font>
														</td>
									                    <td align=right>
															<span class="bold">姓名:</span>
														</td>
														<td class="left" colspan="1">
															<input type="text" isNeed="y" name="student.studentName" cName="姓名" >
														    <font color="red">*</font>
														</td>																									
													</tr>
													<tr>
														<td align=right>
															<span class="bold">密码:</span>
														</td>
														
														<td class="left" style="text-align:left; width:200px"  colspan="1">
															<input type="password" isNeed="y" name="student.pwd" id="pwd1"cName="密码" >
														    <font color="red">*</font>
														</td>
														 <td align=right>
															<span class="bold">确认密码:</span>
														</td>
														<td class="left" style="text-align:left; width:200px"  colspan="1">
															<input type="password" isNeed="y" id="pwd2"cName="密码" >
														    <font color="red">*</font>
														</td>	
													</tr>
													<tr>
													    <td align=right>
																		<span class="bold">系别:</span>
																	</td>
																	<td>
																		<select name="student.department" id="parent1"
																			onchange="selectChange('parent1','child2','1')">
																			<option>
																			</option>
																			<ww:iterator value="#request.orginfoList" id="orginfo">
																				<option value="<ww:property value='code'/>">
																					<ww:property value="name" />
																				</option>
																			</ww:iterator>
																		</select>
																	</td>
																	<td align=right>
																		<span class="bold">专业:</span>
																	</td>
																	<td>
																		<select name="student.major" id="child2"
																			onchange="selectChange('child2','child3','2')">
																			<option>
																			</option>
																			
																			<ww:iterator value="#request.majorList" id="major">
																			<option value="<ww:property value='code'/>">
																			<ww:property value="name" />
																			</option>
																			</ww:iterator>
																		</select>
																	</td>
																	
													</tr>
													<tr>
													   <td align=right>
																		<span class="bold">年级:</span>
																	</td>
																	<td>
																	<select name="student.grade" id="child3"
																		onchange="selectChange2('child2','child3','child4','3')">
																		<option>
																			</option>
																			
																			<ww:iterator value="#request.gradeList" id="grade">
																			<option value="<ww:property value='code'/>">
																			<ww:property value="code" />
																			</option>
																			</ww:iterator>	
																	</select>
													</td>
													   <td align=right>
																		<span class="bold">班级:</span>
																	</td>

																	<td colspan=7>
																		<select name="student.classNo" id="child4"
																			onchange="stuQuery('parent1','child2','child3','child4');">
																			<option>
																			</option>
																			
																			<ww:iterator value="#request.classNoList" id="classNo">
																			<option value="<ww:property value='code'/>">
																			<ww:property value="code" />
																			</option>
																			</ww:iterator>	
																		</select>
																	</td>
<!--														<td style="text-align: right">-->
<!--															<span class="bold">密码周期:</span>-->
<!--														</td>-->
<!--														<td class="left" colspan="1">-->
<!--															<input type="text" name="student.expire" cName="密码周期" maxLength="3">-->
<!--														</td>-->
													</tr>																			
												</TABLE>												
										</td></tr>
										<tr><td align="center">
											<div id="queryButtonDiv">													
												<input class="button" type="button"	value="增加" onClick="formSubmit('studentInfoAddForm')" >
												<input class="button" type="reset" value="重置">
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
