<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ taglib prefix="ww" uri="webwork"%>
<%@ taglib uri="extremecomponents" prefix="ec"%>



<HTML>
	<HEAD>
		<title>修改学生信息</title>
		<link rel="stylesheet" href="resources/css/style.css" type="text/css" media="screen, print" />
		<link rel="stylesheet" href="resources/css/extremecomponents.css" type="text/css" />
		<script src="/ems/resources/js/batchOper.js" type="text/javascript"></script>
		<script src="/ems/resources/ponyjs/jquery.js" type="text/javascript"></script>
		<script src="/ems/resources/ponyjs/jquery_validate.js" type="text/javascript"></script>
		<script src="/ems/resources/ponyjs/jquery.ui.draggable.js" type="text/javascript"></script>
		<script src="/ems/resources/ponyjs/jquery.alerts.js" type="text/javascript"></script>	
		<script src="/ems/resources/js/check.js" type="text/javascript"></script>
		<script src="/ems/jsp/local/userMng/userInfoMng/js/userInfoMng.js" type="text/javascript"></script>
		<script src="/ems/resources/js/linkedSelect.js"
			type="text/javascript"></script>	
		<meta http-equiv=Pragma content=no-cache>		
		
	</HEAD>

	<body id="body">
		<div id="body">
		<table WIDTH=100%>
			<!-- form表单 -->
			<tr><td>
				<form name="studentInfoModifyForm" action="studentInfoModify.action" method="post">
					<table width="100%" border="0" height="">
						<tr><td align="center">
							<FIELDSET align="center"><LEGEND>修改学生信息</LEGEND>
								<div id="queryConditDiv">
									<table width="100%" border="0">
										<tr><td class="query">
											<TABLE border="0" cellpadding="0" cellspacing="0"
													style="width:700px" align="center"
													onKeyDown="javascript:keyHandle();">	
													<tr>
														<td style="text-align:right;">
															 <span class="bold">学号:</span>
														</td>
														<td class="left" colspan="1">
															
															<input type="text" isNeed="y" cName="学号" style="border:0px;" readonly name="studentNum" value="<ww:property value='#request.student.studentNum'/>">
														   
														</td>
									                  	<td style="text-align: right">
															 <span class="bold">姓名:</span>
														</td>
														<td class="left" colspan="1">
															<input type="text" isNeed="y" cName="姓名" id="studentName" name="student.studentName" value="<ww:property value='#request.student.studentName'/>">
														   
														</td>	
														<td style="text-align: right">
															<font color="red">*</font><span class="bold">系别:</span>
														</td>
														<td class="left" colspan="1">
															
														    <select name="student.department" id="parent1" onchange="selectChange('parent1','child2','1')">
																			
																			<ww:iterator value="#request.orginfoList" id="orginfo">
																			<option value="<ww:property value='code'/>"
																			<ww:if test="#request.student.department == code">selected</ww:if>>
																			<ww:property value="name" />
																			</option>
																			</ww:iterator>
																		</select>
														    
														</td>
														</tr>
														<tr>
													<td style="text-align: right">
															 <font color="red">*</font><span class="bold">专业:</span>
														</td>
														<td class="left" colspan="1">
															
														    <select name="student.major" id="child2" >
																			
																			<ww:iterator value="#request.majorList" id="orginfo">
																			<option value="<ww:property value='code'/>"
																			<ww:if test="#request.student.major == code">selected</ww:if>>
																			<ww:property value="name" />
																			</option>
																			</ww:iterator>
																		</select>
														   
														</td>
															<td style="text-align: right">
															<span class="bold">年级:</span>
														</td>
														<td class="left" colspan="1">
														<select name="student.grade" id="child3" >
																	<option>
																			</option>
																			
																			<ww:iterator value="#request.gradeList" id="grade">
																			<option value="<ww:property value='code'/>"
																			<ww:if test="#request.student.grade == code">selected</ww:if>>
																			<ww:property value="code" />
																			</option>
																			</ww:iterator>	
																	</select>
															<!-- input type="text" cName="年级" name="student.grade" value="<ww:property value='#request.student.grade'/>"-->
														</td>	
													
													<td style="text-align: right">
															<span class="bold">选修班:</span>
														</td>
														<td class="left" colspan="1">
														<select name="student.classNo" id="child4">
																		<option>
																			</option>
																			
																			<ww:iterator value="#request.classNoList" id="classNo">
																			<option value="<ww:property value='code'/>"
																			<ww:if test="#request.student.classNo == code">selected</ww:if>>
																			<ww:property value="code" />
																			</option>
																			</ww:iterator>	
																		</select>
															<!-- input type="text" cName="班级" name="student.classNo" value="<ww:property value='#request.student.classNo'/>"-->
														<input type="hidden" cName="状态" name="student.status" value="<ww:property value='#request.student.status'/>"/>
														<input type="hidden" cName="系别" id="oldDepart" value="<ww:property value='#request.student.department'/>"/>
														<input type="hidden" cName="专业" id="oldMajor" value="<ww:property value='#request.student.major'/>"/>
														<input type="hidden" name="oldClassNo" cName="原班级" id="oldClassNo" value="<ww:property value='#request.student.classNo'/>"/>
														</td>
<!--													<td style="text-align: right">-->
<!--															<span class="bold">状态:</span>-->
<!--														</td>-->
<!--														<td class="left" colspan="1">-->
<!--															<input type="text" isNeed="y" cName="状态" name="student.status" value="<ww:property value='#request.student.status'/>">-->
<!--														    <font color="red">*</font>-->
<!--														</td>-->
<!--														<td style="text-align: right">-->
<!--															<span class="bold">密码周期:</span>-->
<!--														</td>-->
<!--														<td class="left" colspan="1">-->
<!--															<input type="text" cName="密码周期" name="student.expire" value="<ww:property value='#request.student.expire'/>" maxLength="3">-->
<!--														</td>-->
													</tr>																			
												</TABLE>												
										</td></tr>
										<tr><td align="center">
											<div id="queryButtonDiv">
											<input type="hidden" name="student.id" value="<ww:property value='#request.student.id'/>">
											<input type="hidden" name="student.studentNum" value="<ww:property value='#request.student.studentNum'/>">													
												<input class="button" type="button"	value="修改" onClick="formSubmit('studentInfoModifyForm')">
												<!-- <input class="button" type="reset" value="重置"> -->
												<input class="button" type="button" value="返回" onClick="window.history.back()">													
											</div>
										</td></tr>
										<tr>
										   <font color="red">注：请勿将学生修改到已经分配群组的班级</font>
										</tr>
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

