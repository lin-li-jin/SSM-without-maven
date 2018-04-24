<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
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
<title>用户授权</title>
	
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
							<font  style="{size:3}" color="#000000">用户授权</font>
						</div>
					<b class="b4"></b><b class="b3"></b><b class="b2"></b><b class="b1"></b>
				</div>					
			</div>
		</LEGEND>
		<div class="content position left">
			<div><form>
						
						<div>
							<label class="right">用户代码：</label>
							<span class="left">
								<ww:property value='#session.userInfoModel.userId'/>
							</span>				
						</div>
						<div>
							<label class="right">用户名称：</label>
							<span class="left">
								<ww:property value='#session.userInfoModel.name'/>
							</span>				
						</div>
						<div>
							<label class="right">用户类型：</label>
							<span class="left">
								<ww:property value='#session.userInfoModel.userTypeDescr'/>
							</span>
						</div>
						<div>
							<label class="right">密码有效期：</label>
							<span class="left">
								<ww:property value='#session.userInfoModel.pwdValidTimeDescr'/>
							</span>
						</div>
						<div>
							<label class="right">所属单位代码：</label>
							<span class="left">
								<ww:property value='#session.userInfoModel.unitId'/>
							</span>				
						</div>
						<div>
							<label class="right">所属单位名称：</label>
							<span class="left">
								<ww:property value='#session.userInfoModel.unitName'/>
							</span>				
						</div>
						<div>
							<label class="right">性别：</label>
							<span class="left">
								<ww:property value='#session.userInfoModel.sexDescr'/>
							</span>				
						</div>
						<div>
							<label class="right">通讯地址：</label>
							<span class="left">
								<ww:property value='#session.userInfoModel.address'/>
							</span>				
						</div>
						<div>
							<label class="right">邮编：</label>
							<span class="left">
								<ww:property value='#session.userInfoModel.postCode'/>
							</span>				
						</div>
						<div>
							<label class="right">固定电话：</label>
							<span class="left">
								<ww:property value='#session.userInfoModel.tel'/>
							</span>				
						</div>
						<div>
							<label class="right">手机：</label>
							<span class="left">
								<ww:property value='#session.userInfoModel.mobile'/>
							</span>				
						</div>
						<div>
							<label class="right">电子邮件：</label>
							<span class="left">
								<ww:property value='#session.userInfoModel.email'/>
							</span>				
						</div>
						<div>
							<label class="right">创建时间：</label>
							<span class="left">
								<ww:property value='#session.userInfoModel.createTimeFmt'/>
							</span>				
						</div>
						<div>
							<label class="right">用户状态：</label>
							<span class="left">
								<font color="red"><ww:property value='#session.userInfoModel.statusDescr'/></font>
							</span>				
						</div>
						<div>
							<label class="right">修改时间：</label>
							<span class="left">
								<ww:property value='#session.userInfoModel.modifyTimeFmt'/>
							</span>				
						</div>						
		</form></div>
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
        <div><hr/></div>	
		<div>
			<form name="userAuthForm" action="userAuth.action" method="post">
					<div>
						<label class="right">授权操作：</label>
						<span class="left">
							<font color="red"><ww:property value='#session.userInfoModel.authOperTypeDescr'/></font>
						</span>				
					</div>
					<div>
						<label class="right">授权状态：</label>
						<span class="left">
							<select name="userInfo.authStatus" isNeed="y" cName="授权状态">
								<option value=""></option>
								<option value="B">同意</option>
								<option value="C">拒绝</option>
							</select>
						</span>				
					</div>		
				<input type="hidden" name="userInfo.userInfoId" value="<ww:property value='#session.userInfoModel.userInfoId' />" />
				<input type="hidden" name="userInfo.authOperType" value="<ww:property value='#session.userInfoModel.authOperType' />" />
			</form>
		</div>
	</div>					
	</FIELDSET>			
	<br />
	<br />
	<center>
		<input type="button" class="button" name="submitBt" value="提 交" onclick="userAuthSubmit('userAuthForm')" />
		<input type="button" class="button" name="backBt" value="返 回" onClick="window.location='userReQuery.action'" />
	</center> 
	<br />
	<br />
	</div>
</body>
