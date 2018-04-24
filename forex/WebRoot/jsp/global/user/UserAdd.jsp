<%@ page contentType="text/html;charset=GBK" %> 
<%@ taglib prefix="ww" uri="webwork" %>
<%@ taglib uri="extremecomponents" prefix="ec" %>

<html>
<head>
<script language="JavaScript" src="jsp/global/user/js/User.js"></script>
<script language="JavaScript" src="resources/js/check.js"></script>
<script language="JavaScript" src="resources/js/Common.js"></script>
<link rel="stylesheet" href="/forex/resources/css/talent.css" type="text/css" />
<link rel="stylesheet" href="/forex/resources/css/talent_query.css" type="text/css" media="screen, print" />		
<link rel="stylesheet" href="/forex/resources/css/talent_tips.css" type="text/css" media="screen, print" />
<link rel="stylesheet" href="/forex/resources/css/extremecomponents.css" type="text/css" />
<title>用户增加</title>
	
</head>



<body>
<div id="body">
	<%--<jsp:include page="../public/TipsBar.jsp"/>
	--%><FIELDSET style="WIDTH:100%" align="center">
		<LEGEND>
			<div style="width:300px;">	 
				<div class="curved">
					<b class="b1"></b><b class="b2"></b><b class="b3"></b><b class="b4"></b>
						<div class="boxcontent">
							<font  style="{size:3}" color="#000000">用户增加</font>
						</div>
					<b class="b4"></b><b class="b3"></b><b class="b2"></b><b class="b1"></b>
				</div>					
			</div>
		</LEGEND>
		<div class="content position left">
				<form name="userAddForm" action="userAdd.action" method="post">
					<div>
						<div>							
							<label class="right">用户类型：</label>
	   						<span class="left">
								<select name="userInfo.userType" isNeed="y" cName="用户类型">
									<option value=''>请选择</option>
						 			<option value="0" >
											中心用户
									</option>
									<option value="1" >
											代理用户
									</option>		
									<option value="2" >
											机构用户
									</option>	
									<option value="3" >
											银行用户
									</option>
								</select><font color="red">*</font>
	   						</span>
						</div>
						<div>
							<label class="right">用户代码：</label>
							<span class="left">
								<input type="text" name="userInfo.userId" isNeed="y" cName="用户代码" len="20" maxLength="20" size="20" onFocus="nextfield='userInfo.name'" value="<ww:property value='#session.userInfoModel.userId'/>"><font color="red">*</font>
							</span>				
						</div>
						<div>
							<label class="right">用户名称：</label>
							<span class="left">
								<input type="text" name="userInfo.name" cName="用户名称" isNeed="y" len="50" maxLength="50" size="60" onFocus="nextfield='userInfo.unitId'" value="<ww:property value='#session.userInfoModel.name'/>"><font color="red">*</font>
							</span>				
						</div>
						<div>
							<label class="right">密码有效期：</label>
							<span class="left">
								<select name="userInfo.pwdValidTime" isNeed="y" cName="密码有效期">
									<option value=""></option>
									<option value="7">7日</option>
									<option value="30">30日</option>
									<option value="365">365日</option>
									<option value="0">永不过期</option>
								</select><font color="red">*</font>
							</span>				
						</div>
						<div>
							<label class="right">所属单位代码：</label>
							<span class="left">
								<input type="text" name="userInfo.unitId" cName="所属单位代码" len="20" maxLength="20" size="20" onFocus="nextfield='userInfo.sex'" value="<ww:property value='#session.userInfoModel.unitId'/>">
							</span>				
						</div>
						<div>
							<label class="right">所属单位名称：</label>
							<span class="left">
								<input type="text" name="userInfo.unitName" cName="所属单位代码" len="60" maxLength="60" size="20" onFocus="nextfield='userInfo.sex'" value="<ww:property value='#session.userInfoModel.unitName'/>">
							</span>				
						</div>
						<div>
							<label class="right">性别：</label>
							<span class="left">
								<input type="radio" name="userInfo.sex" cName="性别" value="m" <ww:if test='#session.userInfoModel.sex == "m"'>checked</ww:if> onFocus="nextfield='userInfo.address'">男&nbsp;
								<input type="radio" name="userInfo.sex" cName="性别" value="f" <ww:if test='#session.userInfoModel.sex == "f"'>checked</ww:if> onFocus="nextfield='userInfo.address'">女
							</span>				
						</div>
						<div>
							<label class="right">通讯地址：</label>
							<span style="left">
								<input type="text" name="userInfo.address" cName="通讯地址" len="100" maxLength="100" size="60" onFocus="nextfield='userInfo.postCode'" value="<ww:property value='#session.userInfoModel.address'/>">
							</span>				
						</div>
						<div>
							<label class="right">邮编：</label>
							<span class="left">
								<input type="text" name="userInfo.postCode" cName="邮编" dataType="zhengInt" len="10" maxLength="10" size="20" onFocus="nextfield='userInfo.tel'" value="<ww:property value='#session.userInfoModel.postCode'/>">
							</span>				
						</div>
						<div>
							<label class="right">固定电话：</label>
							<span class="left">
								<input type="text" name="userInfo.tel" cName="固定电话" len="20" maxLength="20" size="20" onFocus="nextfield='userInfo.mobile'" value="<ww:property value='#session.userInfoModel.tel'/>">
							</span>				
						</div>
						<div>
							<label class="right">手机：</label>
							<span class="left">
								<input type="text" name="userInfo.mobile" cName="手机" len="20" maxLength="20" size="20" onFocus="nextfield='userInfo.email'" value="<ww:property value='#session.userInfoModel.mobile'/>">
							</span>				
						</div>
						<div>
							<label class="right">电子邮件：</label>
							<span class="left">
								<input type="text" name="userInfo.email" cName="电子邮件" dataType="mail" len="50" maxLength="50" size="60" value="<ww:property value='#session.userInfoModel.email'/>">
							</span>				
						</div>
					</div>
					<input type="hidden" name="rolesIds">
					<input type="hidden" name="userInfo.scope" value="ALL">
				</form>
				<div><hr></div>
				<div>
						<label class="right floatLeft" style="margin-top: 6px">角色列表：</label>
						<span class="left">
								<ec:table 
									items="rolesList"
									var="roles"  
									showPagination="false"
									showStatusBar="false"
									>
																		
									<ec:row>
										<ec:column property="select" title="选择" headerStyle="{text-align:center}" />
										<ec:column property="name" title="名称" headerStyle="{text-align:center}" />
										<ec:column property="descr" title="描述" headerStyle="{text-align:center}"/>
									</ec:row>
								</ec:table>	
						</span>									
				</div>
		</div>					
	</FIELDSET>		
		
	<br />
	<br />
	<center>
		<input type="button" class="button" name="submitBt" value="提 交" onclick="userAddSubmit('userAddForm')">
		<input type="button" class="button" name="backBt" value="返 回" onClick="window.location='userReQuery.action'">
	</center>
	<br />
	<br />
	</div>
</body>
