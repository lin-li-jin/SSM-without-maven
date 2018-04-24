<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ taglib prefix="ww" uri="webwork"%>
<%@ taglib uri="extremecomponents" prefix="ec"%>

<!-- create by zjh  2010.8.31 -->
<!-- 
 Amendment No : GJSY130009
 Modify By : CHENYUZHONG
 Date : 2013-07-18
 Description :�޸�ems���ܲ˵���ʽ

 -->
<HTML>
	<HEAD>
		<title>ѧ����Ϣ��ѯ</title>
		<link rel="stylesheet" href="resources/css/style.css" type="text/css"
			media="screen, print" />
		<link rel="stylesheet" href="resources/css/extremecomponents.css"
			type="text/css" />
		<script src="/ems/resources/js/batchOper.js" type="text/javascript"></script>
		<script src="/ems/resources/ponyjs/jquery.js" type="text/javascript"></script>
		<script src="/ems/resources/ponyjs/jquery_validate.js"
			type="text/javascript"></script>
		<script src="/ems/resources/ponyjs/jquery.ui.draggable.js"
			type="text/javascript"></script>
		<script src="/ems/resources/ponyjs/jquery.alerts.js"
			type="text/javascript"></script>
			<script src="/ems/resources/js/linkedSelect.js"
			type="text/javascript"></script>
			<script src="/ems/resources/js/Common.js"
			type="text/javascript"></script>
		<meta http-equiv=Pragma content=no-cache>
		<script language="javascript">			
			$(document).ready(function(){
			var moshi_=$("#playForm").val();
			if(moshi_=="1" ){
				$("#see_all").attr("checked",true);
				$("#see_edu").attr("checked",false);
				$("#all_class").css("display","block");
				document.getElementById("edu_class").style.display="none";
				document.getElementById("selectRadio").value="all";
			}else if(moshi_=="2"){
				$("#see_all").attr("checked",false);
				$("#see_edu").attr("checked",true);
				$("#edu_class").css("display","block");
				document.getElementById("all_class").style.display="none";
				
				document.getElementById("selectRadio").value="edu";
			}
			
		});
			function radioOnclick(){
				var tar_ = $("input[@type=radio][@name=see][@checked]").val();
				document.getElementById("selectRadio").value=tar_;
				if(tar_=="all"){
				$("#all_class").css("display","block");
				document.getElementById("edu_class").style.display="none";
				//document.getElementById("qunzuDiv").style.display="none";
				}
				if(tar_=="edu"){
				$("#edu_class").css("display","block");
				document.getElementById("all_class").style.display="none";
				//document.getElementById("qunzuDiv").style.display="none";
				}
				
			}
			function setPwd(stuId){
				if(confirm('���ȷ������ѧ�����뽫����Ϊ"888888"!')){
				window.location="setPwdAgainDone.action?stuId="+stuId;
				
				}
			}
			function checkForm(){
				var form = document.forms.stuQueryForm;
				form.submit();
				processTip();
			}
		</script>

	</HEAD>

	<body>
		<div id="body">
			<form name="stuQueryForm" style="text-align: center;" action="studentInfoQuery.action" method="post">
				<FIELDSET align="center">
					<LEGEND>ѧ����Ϣ��ѯ</LEGEND>
					<!-- ��ʾ���а༶�Ĳ�ѯ���� -->
					
						<div id="all_class" style=" margin-left: 20px;margin-top:20px; width: 750px; font-size: 12px; text-align: left;">
							<table style="width: 500px;" border="0" cellpadding="0" cellspacing="0">
								<tr >
									<td align="middle">
										ϵ��:
										<SELECT name="student.department" id="parent1"
												onchange="selectChange('parent1','child2','1')">
												<option></option>
												<ww:iterator value="#session.orginfoList" id="orginfo"
													status="status">
													<option VALUE='<ww:property value="#orginfo.code"/>'
													<ww:if test="#request.student.department == code">selected</ww:if>>
														<ww:property value="#orginfo.name" />
													</option>
												</ww:iterator>
										</SELECT>
									</td>
									<td>
										<span>&nbsp;&nbsp;&nbsp;רҵ��</span>
										<SELECT NAME="student.major" id="child2"
												onchange="selectChange('child2','child3','2')">
												<option>
												</option>
												<ww:iterator value="#session.majorList" id="major">
													<option value="<ww:property value='code'/>"
													<ww:if test="#request.student.major == code">selected</ww:if>>
														<ww:property value="name" />
													</option>
												</ww:iterator>
										</SELECT>
									</td>
									<td align="middle">
										<span>&nbsp;&nbsp;�꼶��</span>
										<select name="student.grade" id="child3"
											onchange="selectChange2('child2','child3','child4','3')">
											<option>
											</option>
											<ww:iterator value="#session.gradeList" id="grade">
												<option value="<ww:property value='code'/>"
												<ww:if test="#request.student.grade == code">selected</ww:if>>
												<ww:property value="code" />
												</option>
											</ww:iterator>
										</select>
									</td>
									<td align="middle">
										<span>&nbsp;&nbsp;&nbsp;ѡ�ްࣺ</span>
										<SELECT NAME="student.classNo" id="child4"
											onchange="stuQuery('parent1','child2','child3','child4');">
											<option>
											</option>
											<ww:iterator value="#session.classNoList" id="classNo">
												<option value="<ww:property value='code'/>"
												<ww:if test="#request.student.classNo == code">selected</ww:if>>
												<ww:property value="code" />
												</option>
											</ww:iterator>
										</SELECT>
									</td>
								</tr>
								
							</table>
						</div>
						<!-- end ��ʾ���а༶�Ĳ�ѯ���� -->	
						
						<!-- begin ��ʾѡ�ް༶�Ĳ�ѯ���� -->		
						<div id="edu_class" style="margin-left: 20px;margin-top:20px; width: 750px; font-size: 12px; text-align: left;display:none;">
							<table style="width: 500px;" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td colspan="4">
										<span>ѡ�ް༶��</span>
										<SELECT NAME="eduClassNo" id="eduClassNo">
											<option>
											</option>
											<ww:iterator value="#session.eduClassNoList" id="eduClassNo">
												<option value="<ww:property value='code'/>"
												<ww:if test="#request.eduClassNo == code">selected</ww:if>>
												<ww:property value="code" />
												</option>
											</ww:iterator>
										</SELECT>
									</td>
								</tr>
							</table>
						</div>
						<input type="hidden" name="selectRadio" id="selectRadio" />
						<!--  
						GJSY130009
						 begin
						<div style="position:absolute;left:300px;top:30px;width:220px;background-color:#ddeefe; >
						
						-->
							<div style="position:absolute;left:280px;top:20px;width:260px;background-color:#ddeefe; >
						<!-- 
						  GJSY130009
						  end
						 -->
							<table style="width: 500px;" border="0" cellpadding="0" cellspacing="0"">
								<tr>
									<td align="right">
										<input type="radio" name="see" id="see_all" value="all" onClick="radioOnclick()" style="display:none;"/>
										<!--  	��ʾ���а༶-->
									</td>
									<td align="left">
										<input type="radio" name="see" id="see_edu" value="edu"  onClick="radioOnclick()" style="display:none;"/>
									<!--  	����ʾѡ�ް༶-->
									</td>
								</tr>
							</table>
						</div>
					
						<!-- end ��ʾѡ�ް༶�Ĳ�ѯ���� -->	
						<div style="margin-right:0px;margin-top:-25px;width:60px;height:30px; font-size: 12px;float:right;">
							<input type="button" class="button" value="��ѯ" onclick="checkForm();"/>
						</div>
					</FIELDSET>
				</form>
						<!--  ��ѯ������  ����Ϊ��ѯ�����ʾ�б� -->			
			
		
				<div id="stuList">
				<ww:if test="#request.studentInfoList!=null">
				<ww:if test="#request.studentInfoList .size > 0">
					<ec:table items="studentInfoList" var="post" width="100%" action="studentInfoQuery.action"
					border="" cellpadding="0" cellspacing="0" style="eXtremeTable" 
					retrieveRowsCallback="org.extremecomponents.table.callback.LimitCallback">
					<ec:row>
						<ec:column property="studentNum" title="ѧ��" escapeAutoFormat="true"
							headerStyle="{text-align:center}" style="{text-align:center}"/>
						<ec:column property="studentName" title="����" sortable="false"
							headerStyle="{text-align:center}" style="{text-align:center}"/>
							<ec:column property="grade" title="�꼶"
							headerStyle="{text-align:center}" style="{text-align:center}"/>
						<ec:column property="departmentDescr" title="ϵ��" sortable="false"
							headerStyle="{text-align:center}" style="{text-align:center}"/>
						<ec:column property="majorDescr" title="רҵ" sortable="false"
							headerStyle="{text-align:center}" style="{text-align:center}"/>
						<ec:column property="classNo" title="ѡ�ް�" escapeAutoFormat="true"
							headerStyle="{text-align:center}" style="{text-align:center}"/>
						<ec:column property="statusDescr" sortable="false"
								title="״̬" headerStyle="{text-align:center}" style="{text-align:center}"/>	
						<ec:column property="oper" sortable="false"
								title="����" headerStyle="{text-align:center}" style="{text-align:center}"/>
					</ec:row>
				</ec:table>
			</ww:if></ww:if>
			</div>
			
			<div id="eduStuList">
			<ww:if test="#request.edustudentInfoList!=null">
				<ww:if test="#request.edustudentInfoList.size > 0">
					<ec:table items="edustudentInfoList" var="post" width="100%" action="studentInfoQuery.action"
					border="" cellpadding="0" cellspacing="0" style="eXtremeTable">
					<ec:row>
						<ec:column property="studentNum" title="ѧ��" escapeAutoFormat="true"
							headerStyle="{text-align:center}" style="{text-align:center}"/>
						<ec:column property="studentName" title="����" sortable="false"
							headerStyle="{text-align:center}" style="{text-align:center}"/>
							<ec:column property="grade" title="�꼶"
							headerStyle="{text-align:center}" style="{text-align:center}"/>
						<ec:column property="departmentDescr" title="ϵ��" sortable="false"
							headerStyle="{text-align:center}" style="{text-align:center}"/>
						<ec:column property="majorDescr" title="רҵ" sortable="false"
							headerStyle="{text-align:center}" style="{text-align:center}"/>
						<ec:column property="classNo" title="�༶" escapeAutoFormat="true"
							headerStyle="{text-align:center}" style="{text-align:center}"/>
							<ec:column property="eduClassNo" title="��ѧ��" 
							headerStyle="{text-align:center}" style="{text-align:center}"/>
						<ec:column property="statusDescr" sortable="false"
								title="״̬" headerStyle="{text-align:center}" style="{text-align:center}"/>	
						<ec:column property="oper" sortable="false"
								title="����" headerStyle="{text-align:center}" style="{text-align:center}"/>
					</ec:row>
				</ec:table>
			</ww:if>	
			<ww:elseif test="#request.edustudentInfoList.size==0">
				<div style="margin-top: 10px; text-align: center;font-size:14px;color: #1E90FF;">
					��ʱû�н�ѧ����Ϣ
					<br/>
			</ww:elseif>
			</ww:if>
			</div>
		<input type="hidden" id="playForm" value='<ww:property value="#request.studentForm"/>'>
		<input type="hidden" id="aa" value='<ww:property value="#request.edustudentInfoList.size"/>'>
		</div>
	</body>
</HTML>

