<%@ page contentType="text/html;charset=GBK" %> 
<%@ taglib prefix="ww" uri="webwork" %>
<%@ taglib uri="extremecomponents" prefix="ec" %>

<html>
<head>
<script language="JavaScript" src="jsp/user/js/User.js"></script>
<script language="JavaScript" src="resources/js/check.js"></script>
<link rel="stylesheet" href="/forex/resources/css/talent.css" type="text/css" />
<link rel="stylesheet" href="/forex/resources/css/talent_query.css" type="text/css" media="screen, print" />		
<link rel="stylesheet" href="/forex/resources/css/talent_tips.css" type="text/css" media="screen, print" />
<link rel="stylesheet" href="/forex/resources/css/extremecomponents.css" type="text/css" />
<title>�û���ϸ��Ϣ</title>
	
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
							<font  style="{size:3}" color="#000000">�û���ϸ��Ϣ</font>
						</div>
					<b class="b4"></b><b class="b3"></b><b class="b2"></b><b class="b1"></b>
				</div>					
			</div>
		</LEGEND>
		<div class="content position left">
			<div>
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
				<div>
					<label class="right">���һ�ε�½ʱ�䣺</label>
					<span class="left">
						<ww:property value='#session.userInfoModel.lastLoginTimeFmt'/>
					</span>				
				</div>
				<div>
					<label class="right">��½ʧ�ܴ�����</label>
					<span class="left">
						<ww:property value='#session.userInfoModel.failureTime'/>
					</span>				
				</div>
			</div>
			<div>
				<div>
					<label class="right">��Ȩ������</label>
					<span class="left">
						<ww:property value='#session.userInfoModel.authOperTypeDescr'/>
					</span>				
				</div>
				<div>
					<label class="right">��Ȩ״̬��</label>
					<span class="left">
						<ww:property value='#session.userInfoModel.authStatusDescr'/>
					</span>				
				</div>
				<div>
					<label class="right">��Ȩʱ�䣺</label>
					<span class="left">
						<ww:property value='#session.userInfoModel.authTimeFmt'/>
					</span>				
				</div>
			</div>	
			<div>
				<label class="right floatLeft" style="margin-top: 6px">��ɫ�б�</label>
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
				<input type="button" class="button" name="backBt" value="�� ��" onClick="window.history.back()">
			</center>
			<br />
			<br />
	</div>
</body>
