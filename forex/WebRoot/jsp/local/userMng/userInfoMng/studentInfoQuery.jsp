<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ taglib prefix="ww" uri="webwork"%>
<%@ taglib uri="extremecomponents" prefix="ec"%>

<!-- create by zjh  2010.8.31 -->
<!-- 
 Amendment No : GJSY130009
 Modify By : CHENYUZHONG
 Date : 2013-07-18
 Description :修改ems功能菜单样式

 -->
<HTML>
	<HEAD>
		<title>学生信息查询</title>
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
				if(confirm('点击确定，该学生密码将重置为"888888"!')){
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
					<LEGEND>学生信息查询</LEGEND>
					<!-- 显示所有班级的查询条件 -->
					
						<div id="all_class" style=" margin-left: 20px;margin-top:20px; width: 750px; font-size: 12px; text-align: left;">
							<table style="width: 500px;" border="0" cellpadding="0" cellspacing="0">
								<tr >
									<td align="middle">
										系别:
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
										<span>&nbsp;&nbsp;&nbsp;专业：</span>
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
										<span>&nbsp;&nbsp;年级：</span>
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
										<span>&nbsp;&nbsp;&nbsp;选修班：</span>
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
						<!-- end 显示所有班级的查询条件 -->	
						
						<!-- begin 显示选修班级的查询条件 -->		
						<div id="edu_class" style="margin-left: 20px;margin-top:20px; width: 750px; font-size: 12px; text-align: left;display:none;">
							<table style="width: 500px;" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td colspan="4">
										<span>选修班级：</span>
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
										<!--  	显示所有班级-->
									</td>
									<td align="left">
										<input type="radio" name="see" id="see_edu" value="edu"  onClick="radioOnclick()" style="display:none;"/>
									<!--  	仅显示选修班级-->
									</td>
								</tr>
							</table>
						</div>
					
						<!-- end 显示选修班级的查询条件 -->	
						<div style="margin-right:0px;margin-top:-25px;width:60px;height:30px; font-size: 12px;float:right;">
							<input type="button" class="button" value="查询" onclick="checkForm();"/>
						</div>
					</FIELDSET>
				</form>
						<!--  查询表单结束  以下为查询结果显示列表 -->			
			
		
				<div id="stuList">
				<ww:if test="#request.studentInfoList!=null">
				<ww:if test="#request.studentInfoList .size > 0">
					<ec:table items="studentInfoList" var="post" width="100%" action="studentInfoQuery.action"
					border="" cellpadding="0" cellspacing="0" style="eXtremeTable" 
					retrieveRowsCallback="org.extremecomponents.table.callback.LimitCallback">
					<ec:row>
						<ec:column property="studentNum" title="学号" escapeAutoFormat="true"
							headerStyle="{text-align:center}" style="{text-align:center}"/>
						<ec:column property="studentName" title="姓名" sortable="false"
							headerStyle="{text-align:center}" style="{text-align:center}"/>
							<ec:column property="grade" title="年级"
							headerStyle="{text-align:center}" style="{text-align:center}"/>
						<ec:column property="departmentDescr" title="系别" sortable="false"
							headerStyle="{text-align:center}" style="{text-align:center}"/>
						<ec:column property="majorDescr" title="专业" sortable="false"
							headerStyle="{text-align:center}" style="{text-align:center}"/>
						<ec:column property="classNo" title="选修班" escapeAutoFormat="true"
							headerStyle="{text-align:center}" style="{text-align:center}"/>
						<ec:column property="statusDescr" sortable="false"
								title="状态" headerStyle="{text-align:center}" style="{text-align:center}"/>	
						<ec:column property="oper" sortable="false"
								title="操作" headerStyle="{text-align:center}" style="{text-align:center}"/>
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
						<ec:column property="studentNum" title="学号" escapeAutoFormat="true"
							headerStyle="{text-align:center}" style="{text-align:center}"/>
						<ec:column property="studentName" title="姓名" sortable="false"
							headerStyle="{text-align:center}" style="{text-align:center}"/>
							<ec:column property="grade" title="年级"
							headerStyle="{text-align:center}" style="{text-align:center}"/>
						<ec:column property="departmentDescr" title="系别" sortable="false"
							headerStyle="{text-align:center}" style="{text-align:center}"/>
						<ec:column property="majorDescr" title="专业" sortable="false"
							headerStyle="{text-align:center}" style="{text-align:center}"/>
						<ec:column property="classNo" title="班级" escapeAutoFormat="true"
							headerStyle="{text-align:center}" style="{text-align:center}"/>
							<ec:column property="eduClassNo" title="教学班" 
							headerStyle="{text-align:center}" style="{text-align:center}"/>
						<ec:column property="statusDescr" sortable="false"
								title="状态" headerStyle="{text-align:center}" style="{text-align:center}"/>	
						<ec:column property="oper" sortable="false"
								title="操作" headerStyle="{text-align:center}" style="{text-align:center}"/>
					</ec:row>
				</ec:table>
			</ww:if>	
			<ww:elseif test="#request.edustudentInfoList.size==0">
				<div style="margin-top: 10px; text-align: center;font-size:14px;color: #1E90FF;">
					暂时没有教学班信息
					<br/>
			</ww:elseif>
			</ww:if>
			</div>
		<input type="hidden" id="playForm" value='<ww:property value="#request.studentForm"/>'>
		<input type="hidden" id="aa" value='<ww:property value="#request.edustudentInfoList.size"/>'>
		</div>
	</body>
</HTML>

