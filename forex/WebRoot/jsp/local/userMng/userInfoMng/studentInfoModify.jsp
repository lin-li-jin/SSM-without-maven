<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ taglib prefix="ww" uri="webwork"%>
<%@ taglib uri="extremecomponents" prefix="ec"%>



<HTML>
	<HEAD>
		<title>�޸�ѧ����Ϣ</title>
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
			<!-- form�� -->
			<tr><td>
				<form name="studentInfoModifyForm" action="studentInfoModify.action" method="post">
					<table width="100%" border="0" height="">
						<tr><td align="center">
							<FIELDSET align="center"><LEGEND>�޸�ѧ����Ϣ</LEGEND>
								<div id="queryConditDiv">
									<table width="100%" border="0">
										<tr><td class="query">
											<TABLE border="0" cellpadding="0" cellspacing="0"
													style="width:700px" align="center"
													onKeyDown="javascript:keyHandle();">	
													<tr>
														<td style="text-align:right;">
															 <span class="bold">ѧ��:</span>
														</td>
														<td class="left" colspan="1">
															
															<input type="text" isNeed="y" cName="ѧ��" style="border:0px;" readonly name="studentNum" value="<ww:property value='#request.student.studentNum'/>">
														   
														</td>
									                  	<td style="text-align: right">
															 <span class="bold">����:</span>
														</td>
														<td class="left" colspan="1">
															<input type="text" isNeed="y" cName="����" id="studentName" name="student.studentName" value="<ww:property value='#request.student.studentName'/>">
														   
														</td>	
														<td style="text-align: right">
															<font color="red">*</font><span class="bold">ϵ��:</span>
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
															 <font color="red">*</font><span class="bold">רҵ:</span>
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
															<span class="bold">�꼶:</span>
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
															<!-- input type="text" cName="�꼶" name="student.grade" value="<ww:property value='#request.student.grade'/>"-->
														</td>	
													
													<td style="text-align: right">
															<span class="bold">ѡ�ް�:</span>
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
															<!-- input type="text" cName="�༶" name="student.classNo" value="<ww:property value='#request.student.classNo'/>"-->
														<input type="hidden" cName="״̬" name="student.status" value="<ww:property value='#request.student.status'/>"/>
														<input type="hidden" cName="ϵ��" id="oldDepart" value="<ww:property value='#request.student.department'/>"/>
														<input type="hidden" cName="רҵ" id="oldMajor" value="<ww:property value='#request.student.major'/>"/>
														<input type="hidden" name="oldClassNo" cName="ԭ�༶" id="oldClassNo" value="<ww:property value='#request.student.classNo'/>"/>
														</td>
<!--													<td style="text-align: right">-->
<!--															<span class="bold">״̬:</span>-->
<!--														</td>-->
<!--														<td class="left" colspan="1">-->
<!--															<input type="text" isNeed="y" cName="״̬" name="student.status" value="<ww:property value='#request.student.status'/>">-->
<!--														    <font color="red">*</font>-->
<!--														</td>-->
<!--														<td style="text-align: right">-->
<!--															<span class="bold">��������:</span>-->
<!--														</td>-->
<!--														<td class="left" colspan="1">-->
<!--															<input type="text" cName="��������" name="student.expire" value="<ww:property value='#request.student.expire'/>" maxLength="3">-->
<!--														</td>-->
													</tr>																			
												</TABLE>												
										</td></tr>
										<tr><td align="center">
											<div id="queryButtonDiv">
											<input type="hidden" name="student.id" value="<ww:property value='#request.student.id'/>">
											<input type="hidden" name="student.studentNum" value="<ww:property value='#request.student.studentNum'/>">													
												<input class="button" type="button"	value="�޸�" onClick="formSubmit('studentInfoModifyForm')">
												<!-- <input class="button" type="reset" value="����"> -->
												<input class="button" type="button" value="����" onClick="window.history.back()">													
											</div>
										</td></tr>
										<tr>
										   <font color="red">ע������ѧ���޸ĵ��Ѿ�����Ⱥ��İ༶</font>
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

