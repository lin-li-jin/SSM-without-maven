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
<title>�û��޸�</title>
	
</head>



<body><%--
	<jsp:include page="../public/TipsBar.jsp"/>
	--%>
	<div id="body">
	<FIELDSET style="WIDTH:100%" align="center">
		<LEGEND>
			<div style="width:300px;">	 
				<div class="curved">
					<b class="b1"></b><b class="b2"></b><b class="b3"></b><b class="b4"></b>
						<div class="boxcontent">
							<font  style="{size:3}" color="#000000">�û��޸�</font>
						</div>
					<b class="b4"></b><b class="b3"></b><b class="b2"></b><b class="b1"></b>
				</div>					
			</div>
		</LEGEND>
		<div class="content position left">
			<div>
				<form name="userModifyForm" action="userModify.action" method="post">
				<div>
					<label class="right">�û����룺</label>
					<span class="left">
						<ww:property value='#session.userInfoModel.userId'/>
					</span>				
				</div>
				<div>
					<label class="right">�û����ͣ�</label>
					<span class="left">
						<select name="userInfo.userType" isNeed="y" cName="�û�����">
							<ww:iterator value="#application.codeTableUtil.userTypeList">
								<option value="<ww:property value='id.codeVal' />" <ww:if test="#session.userInfoModel.userType == id.codeVal">selected</ww:if>><ww:property value="descr" /></option>
							</ww:iterator>
						</select><font color="red">*</font>
					</span>
				</div>
				<div>
					<label class="right">�û����ƣ�</label>
					<span class="left">
						<input type="text" name="userInfo.name" cName="�û�����" isNeed="y" len="50" maxLength="50" size="60" onFocus="nextfield='userInfo.unitId'" value="<ww:property value='#session.userInfoModel.name'/>"><font color="red">*</font>
					</span>				
				</div>
				<div>
					<label class="right">������Ч�ڣ�</label>
					<span class="left">
						<select name="userInfo.pwdValidTime">
							<option value="7" <ww:if test="#session.userInfoModel.pwdValidTime == 7">selected</ww:if>>һ��</option>
							<option value="30" <ww:if test="#session.userInfoModel.pwdValidTime == 30">selected</ww:if>>һ����</option>
							<option value="365" <ww:if test="#session.userInfoModel.pwdValidTime == 365">selected</ww:if>>һ��</option>
							<option value="0" <ww:if test="#session.userInfoModel.pwdValidTime == 0">selected</ww:if>>��������</option>
						</select><font color="red">*</font>
					</span>				
				</div>
				<div>
					<label class="right">������λ���룺</label>
					<span class="left">
						<input type="text" name="userInfo.unitId" cName="������λ����" len="20" maxLength="20" size="20" onFocus="nextfield='userInfo.sex'" value="<ww:property value='#session.userInfoModel.unitId'/>">
					</span>				
				</div>
				<div>
					<label class="right">������λ���ƣ�</label>
					<span class="left">
						<input type="text" name="userInfo.unitName" cName="������λ����" len="60" maxLength="60" size="20" onFocus="nextfield='userInfo.sex'" value="<ww:property value='#session.userInfoModel.unitName'/>">
					</span>				
				</div>
				<div>
					<label class="right">�Ա�</label>
					<span class="left">
						<input type="radio" name="userInfo.sex" cName="�Ա�" value="m" <ww:if test='#session.userInfoModel.sex == "m"'>checked</ww:if> onFocus="nextfield='userInfo.address'">��&nbsp;
						<input type="radio" name="userInfo.sex" cName="�Ա�" value="f" <ww:if test='#session.userInfoModel.sex == "f"'>checked</ww:if> onFocus="nextfield='userInfo.address'">Ů
					</span>				
				</div>
				<div>
					<label class="right">ͨѶ��ַ��</label>
					<span class="left">
						<input type="text" name="userInfo.address" cName="ͨѶ��ַ" len="100" maxLength="100" size="60" onFocus="nextfield='userInfo.postCode'" value="<ww:property value='#session.userInfoModel.address'/>">
					</span>				
				</div>
				<div>
					<label class="right">�ʱࣺ</label>
					<span class="left">
						<input type="text" name="userInfo.postCode" cName="�ʱ�" dataType="zhengInt" len="10" maxLength="10" size="20" onFocus="nextfield='userInfo.tel'" value="<ww:property value='#session.userInfoModel.postCode'/>">
					</span>				
				</div>
				<div>
					<label class="right">�̶��绰��</label>
					<span class="left">
						<input type="text" name="userInfo.tel" cName="�̶��绰" len="20" maxLength="20" size="20" onFocus="nextfield='userInfo.mobile'" value="<ww:property value='#session.userInfoModel.tel'/>">
					</span>				
				</div>
				<div>
					<label class="right">�ֻ���</label>
					<span class="left">
						<input type="text" name="userInfo.mobile" cName="�ֻ�" len="20" maxLength="20" size="20" onFocus="nextfield='userInfo.email'" value="<ww:property value='#session.userInfoModel.mobile'/>">
					</span>				
				</div>
				<div>
					<label class="right">�����ʼ���</label>
					<span class="left">
						<input type="text" name="userInfo.email" cName="�����ʼ�" dataType="mail" len="50" maxLength="50" size="60" value="<ww:property value='#session.userInfoModel.email'/>">
					</span>				
				</div>	
				<input type="hidden" name="rolesIds">
				<input type="hidden" name="userInfo.userInfoId" value="<ww:property value='#session.userInfoModel.userInfoId' />">
				<input type="hidden" name="userInfo.userId" value="<ww:property value='#session.userInfoModel.userId' />">
				<input type="hidden" name="userInfo.scope" value="ALL">
				</form>
			</div>
			<div><hr></div>
			<div>
				<label class="right floatLeft" style="margin-top: 6px">��ɫ�б���</label>
				<span class="left">
						<ec:table 
							items="rolesList"
							var="roles"  
							showPagination="false"
							showStatusBar="false"
							sortable="false"
							>
																
							<ec:row>
								<ec:column property="select" title="ѡ��" headerStyle="{text-align:center}" />
								<ec:column property="name" title="����" headerStyle="{text-align:center}" />
								<ec:column property="descr" title="����" headerStyle="{text-align:center}"/>
							</ec:row>
						</ec:table>	
				</span>										
			</div>
		</div>					
	</FIELDSET>		
		
	<br />
	<br />
	<center>
		<input type="button" class="button" name="submitBt" value="�� ��" onclick="userModifySubmit('userModifyForm')">
		<input type="button" class="button" name="backBt" value="�� ��" onClick="window.location='userReQuery.action'">
	</center>
	<br/>
	<br/>
	</div>
</body>