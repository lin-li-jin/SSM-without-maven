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
<title>�û���Ȩ</title>
	
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
							<font  style="{size:3}" color="#000000">�û���Ȩ</font>
						</div>
					<b class="b4"></b><b class="b3"></b><b class="b2"></b><b class="b1"></b>
				</div>					
			</div>
		</LEGEND>
		<div class="content position left">
			<div><form>
						
						<div>
							<label class="right">�û����룺</label>
							<span class="left">
								<ww:property value='#session.userInfoModel.userId'/>
							</span>				
						</div>
						<div>
							<label class="right">�û����ƣ�</label>
							<span class="left">
								<ww:property value='#session.userInfoModel.name'/>
							</span>				
						</div>
						<div>
							<label class="right">�û����ͣ�</label>
							<span class="left">
								<ww:property value='#session.userInfoModel.userTypeDescr'/>
							</span>
						</div>
						<div>
							<label class="right">������Ч�ڣ�</label>
							<span class="left">
								<ww:property value='#session.userInfoModel.pwdValidTimeDescr'/>
							</span>
						</div>
						<div>
							<label class="right">������λ���룺</label>
							<span class="left">
								<ww:property value='#session.userInfoModel.unitId'/>
							</span>				
						</div>
						<div>
							<label class="right">������λ���ƣ�</label>
							<span class="left">
								<ww:property value='#session.userInfoModel.unitName'/>
							</span>				
						</div>
						<div>
							<label class="right">�Ա�</label>
							<span class="left">
								<ww:property value='#session.userInfoModel.sexDescr'/>
							</span>				
						</div>
						<div>
							<label class="right">ͨѶ��ַ��</label>
							<span class="left">
								<ww:property value='#session.userInfoModel.address'/>
							</span>				
						</div>
						<div>
							<label class="right">�ʱࣺ</label>
							<span class="left">
								<ww:property value='#session.userInfoModel.postCode'/>
							</span>				
						</div>
						<div>
							<label class="right">�̶��绰��</label>
							<span class="left">
								<ww:property value='#session.userInfoModel.tel'/>
							</span>				
						</div>
						<div>
							<label class="right">�ֻ���</label>
							<span class="left">
								<ww:property value='#session.userInfoModel.mobile'/>
							</span>				
						</div>
						<div>
							<label class="right">�����ʼ���</label>
							<span class="left">
								<ww:property value='#session.userInfoModel.email'/>
							</span>				
						</div>
						<div>
							<label class="right">����ʱ�䣺</label>
							<span class="left">
								<ww:property value='#session.userInfoModel.createTimeFmt'/>
							</span>				
						</div>
						<div>
							<label class="right">�û�״̬��</label>
							<span class="left">
								<font color="red"><ww:property value='#session.userInfoModel.statusDescr'/></font>
							</span>				
						</div>
						<div>
							<label class="right">�޸�ʱ�䣺</label>
							<span class="left">
								<ww:property value='#session.userInfoModel.modifyTimeFmt'/>
							</span>				
						</div>						
		</form></div>
		<div>
			<label class="right floatLeft" style="margin-top: 6px">��ɫ�б�</label>
			<span class="left">
					<ec:table 
						items="rolesList"
						var="roles"  
						showPagination="false"
						showStatusBar="false"
						>
															
						<ec:row>
							<ec:column property="select" title="ѡ��" headerStyle="{text-align:center}" />
							<ec:column property="name" title="����" headerStyle="{text-align:center}" />
							<ec:column property="descr" title="����" headerStyle="{text-align:center}"/>
						</ec:row>
					</ec:table>	
			</span>									
		</div>
        <div><hr/></div>	
		<div>
			<form name="userAuthForm" action="userAuth.action" method="post">
					<div>
						<label class="right">��Ȩ������</label>
						<span class="left">
							<font color="red"><ww:property value='#session.userInfoModel.authOperTypeDescr'/></font>
						</span>				
					</div>
					<div>
						<label class="right">��Ȩ״̬��</label>
						<span class="left">
							<select name="userInfo.authStatus" isNeed="y" cName="��Ȩ״̬">
								<option value=""></option>
								<option value="B">ͬ��</option>
								<option value="C">�ܾ�</option>
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
		<input type="button" class="button" name="submitBt" value="�� ��" onclick="userAuthSubmit('userAuthForm')" />
		<input type="button" class="button" name="backBt" value="�� ��" onClick="window.location='userReQuery.action'" />
	</center> 
	<br />
	<br />
	</div>
</body>
