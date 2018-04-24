<%@ page contentType="text/html;charset=GBK"%>
<%@ taglib prefix="ww" uri="webwork"%>
<%@ taglib uri="extremecomponents" prefix="ec"%>

<html>
	<head>
		<script language="JavaScript" src="jsp/global/user/js/User.js"></script>
		<script language="JavaScript" src="../../../resources/js/check.js"></script>
		<script language="JavaScript" src="../../../resources/js/Common.js"></script>		
		<script language="JavaScript" src="../../../resources/calendar/js/Calendar.js"></script>
		<link rel="stylesheet" href="/forex/resources/css/talent.css" type="text/css" />
		<link rel="stylesheet" href="/forex/resources/css/talent_query.css"
			type="text/css" media="screen, print" />
		<link rel="stylesheet" href="/forex/resources/css/talent_tips.css"
			type="text/css" media="screen, print" />
		<link href="/forex/resources/calendar/css/calendar-blue.css" rel="stylesheet" />
		<link rel="stylesheet" href="/forex/resources/css/extremecomponents.css"
			type="text/css" />
		<title>用户管理</title>

	</head>



	<body>
		<%--
	<jsp:include page="../public/TipsBar.jsp"/>
	--%>
		<div id="body">
			<form name="userQueryForm" action="userQuery.action" method="post">
			<FIELDSET align="center">
			<LEGEND>
				<div style="width:300px;">	 
					<div class="curved">
						<b class="b1"></b><b class="b2"></b><b class="b3"></b><b class="b4"></b>
						<div class="boxcontent">
							<font  style="{size:3}" color="#000000">用户查询</font>
						</div>
						<b class="b4"></b><b class="b3"></b><b class="b2"></b><b class="b1"></b>
					</div>					
				</div>
			</LEGEND>
			<div class="content position left">
				<div  style="background-color:#F7F7F7">
					<div>
						<!-- td style="{text-align:right}">
							userInfoId：
						</td>
						<td style="{text-align:left}">
							<input type="text" name="userInfo.userInfoId" cName="userInfoId"
								len="20" size="18" maxLength="20"
								value="<ww:property value='#session.userInfoQueryModel.userInfoId'/>">
						</td-->
						<label class="right">用户代码：</label>
						<span class="left">
							<input type="text" name="userInfo.userId" cName="用户代码"
								len="20" size="18" maxLength="20"
								value="<ww:property value='#session.userInfoQueryModel.userId'/>">
						</span>
						<label class="right">用户姓名：</label>
						<span class="left">
							<input type="text" name="userInfo.name" cName="用户姓名" len="50"
								maxLength="50"
								value="<ww:property value='#session.userInfoQueryModel.name'/>">
						</span>
					</div>
					<div>										
						<label class="right">用户状态：</label>
						<span class="left">
							<select name="userInfo.status">
								<option value=''>请选择</option>
			 					<option value="A" >
									新增
								</option>
								<option value="B" >
									正常
								</option>		
								<option value="C" >
									冻结
								</option>	
								<option value="D" >
									注销
								</option>
								<option value="E" >
									修改
								</option>		
							</select>
						</span>
						<label class="right" style="margin-left: 62px">角色：</label>
						<span>
							<select name="roles.rolesId">
								<option value=""></option>
								<ww:iterator value="#session.rolesList">
									<option value="<ww:property value='rolesId' />"
										<ww:if test="#session.roles.rolesId == rolesId">selected</ww:if>>
										<ww:property value="name" />
									</option>
								</ww:iterator>
							</select>
						</span>	   							
					</div>
					<div>
						<label class="right">授权状态：</label>
						<span class="left">
							<select name="userInfo.authStatus">
								<option value=''>请选择</option>
			 					<option value="A" >
									待授权
								</option>
								<option value="B" >
									同意
								</option>		
								<option value="C" >
									拒绝
								</option>			
							</select>
						</span>	
						<label class="right" style="margin-left: 62px">授权操作：</label>
						<span class="left">
							<select name="userInfo.authOperType">
								<option value=''>请选择</option>
			 					<option value="A" >
									新增用户
								</option>
								<option value="B" >
									冻结用户
								</option>		
								<option value="C" >
									注销用户
								</option>	
								<option value="D" >
									重置密码
								</option>
								<option value="E" >
									解冻用户
								</option>	
								<option value="F" >
									修改用户
								</option>
								<option value="G" >
									强制退出用户
								</option>
							</select>
						</span>
					</div>
					<div>
						<label class="right">所属机构编号：</label>
						<span class="left">
								<input type="text" name="userInfo.unitId" cName="所属机构编号"
									len="20" size="18" maxLength="20"
									value="<ww:property value='#session.userInfoQueryModel.unitId'/>">
						</span>
						<label class="right">创建时间段：</label>
						<span class="left">
							<input type="text" name="timeSliceModel.beginDate"
								value='<ww:property value="#session.timeSliceModel.beginDate"/>'
								size="10" cName="创建开始时间" dataType="date" />
							<a
								onClick="return showCalendar('timeSliceModel.beginDate', 'y-mm-dd');"
								href="#"> <img src="resources/calendar/image/Button.gif"
									id="imgid" align="absMiddle" border="0" />
							</a>至
							<input type="text" name="timeSliceModel.endDate"
								value='<ww:property value="#session.timeSliceModel.endDate"/>'
								size="10" cName="创建结束时间" dataType="date" />
							<a
								onClick="return showCalendar('timeSliceModel.endDate', 'y-mm-dd');"
								href="#"> <img src="resources/calendar/image/Button.gif"
									id="imgid" align="absMiddle" border="0" /> </a>
						</span>
					</div>
				</div>
					<div id="queryButtonDiv" class="center">
						<input type="button" class="button" name="submitBt" value="查 询"
							onclick="userQuery('userQueryForm')">
						&nbsp;
						<input type="reset" class="button" name="resetBt" value="重 置">
						&nbsp;
						<input type="button" class="button" name="addBt" value="增 加"
							onclick="window.location='userAddInit.action'">
					</div>
			</div>
			</FIELDSET>
			</form>

			<ww:if test="#session.userList != null">
				<ec:table items="userList" var="userInfo"
					action="ecUserQuery.action">
					<ec:export view="xls" fileName="用户列表.xls" imageName="xls"
						viewResolver="xls" tooltip="导出 Excel" />

					<ec:row>
						<ec:column property="select" title="选择"
							headerStyle="{text-align:center}" style="{text-align:left}" />
						<ec:column property="userIdDescr" title="操作代码"
							headerStyle="{text-align:center}" style="{text-align:left}" />
						<ec:column property="name" title="操作员姓名"
							headerStyle="{text-align:center}" style="{text-align:left}" />
						<ec:column property="unitName" title="所属单位"
							headerStyle="{text-align:center}" style="{text-align:left}" />
						<ec:column property="createTimeFmt" title="创建时间"
							headerStyle="{text-align:center}" style="{text-align:left}" />
						<ec:column property="statusDescr" title="状态"
							headerStyle="{text-align:center}" style="{text-align:left}" />
						<ec:column property="onlineStatus" title="在线状态"
							headerStyle="{text-align:center}" style="{text-align:left}" />
						<ec:column property="authOperTypeDescr" title="授权操作"
							headerStyle="{text-align:center}" style="{text-align:left}" />
						<ec:column property="authStatusDescr" title="授权状态"
							headerStyle="{text-align:center}" style="{text-align:left}" />
					</ec:row>
				</ec:table>
				<br/><br/>
				<form name="userModifyForm" method="post">
					<table cellspacing="0" cellpadding="0" width="100%" align="center">
						<tr>
							<td class="altbg1" style="{text-align:center}" colspan="6">
								<div class="option">
									<div class="submitbutton">
										<input type="button" class="button" name="modifyBt" value="修 改" disabled
											onClick="userModifyInit('userModifyForm')">
										<input type="hidden" name="userInfo.userInfoId" value="<ww:property value='#session.userInfoModel.userInfoId' />">
										<input type="hidden" name="userInfo.authOperType" value="<ww:property value='#session.userInfo.authOperType' />">
										<input type="button" class="button" name="resetPwdBt" value="重置密码" disabled
											onClick="userPwdReset('userModifyForm')">
										<input type="button" class="button" name="freezeBt" value="冻 结" disabled
											onClick="userFreeze('userModifyForm')">
										<input type="button" class="button" name="thawBt" value="解 冻" disabled
											onClick="userThaw('userModifyForm')">
										<input type="button" class="button" name="cancelBt" value="注 销" disabled
											onClick="userCancel('userModifyForm')">
										<input type="button" class="button" name="logoutUserBt" value="强制退出" disabled
											onClick="logoutUser('userModifyForm')">
										<input type="button" class="button" name="authBt" value="授 权" disabled
											onClick="userAuthInit('userModifyForm')">
											
									</div>
								</div>
							</td>
						</tr>
					</table>

	
				</form>
			</ww:if>
		</div>
	</body>
</html>