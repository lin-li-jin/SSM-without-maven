<%@ page contentType="text/html;charset=GBK"%>
<%@ taglib prefix="ww" uri="webwork"%>
<%@ taglib uri="extremecomponents" prefix="ec"%>
<html>
	<head>
        <script language="JavaScript" src="jsp/local/dataInput/js/dataInput.js"></script>
		<script language="JavaScript" src="resources/js/check.js"></script>
		<script language="JavaScript" src="resources/js/Common.js"></script>
		<link rel="stylesheet" href="resources/css/talent.css" type="text/css" />
		<link rel="stylesheet" href="resources/css/talent_query.css"
			type="text/css" media="screen, print" />
		<link rel="stylesheet" href="resources/css/talent_tips.css"
			type="text/css" media="screen, print" />
		<link rel="stylesheet" href="resources/css/extremecomponents.css"
			type="text/css" />
		<link rel="stylesheet" href="resources/css/style.css" type="text/css" />
		
		<title>���ݳ�ʼ��</title>

	</head>

	<body>
		<div id="body">
		<div id="student">
			<table cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td style="text-align: center">
						<FIELDSET align="center">
							<LEGEND>
							��Ϣ��ʼ������
							</LEGEND>
							<form class="form" name="dataImportForm" action="FileImport.action"
								method="post" ENCTYPE="multipart/form-data">
								  <div id="dblFileDiv1" style="text-align: left">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<div style="text-align:left">
									<span style="{text-align:left}">&nbsp;�ļ����ͣ�</span>
		   								<select name="fileUploadModel.fileType" cName="�ļ�����">																			
											<option value="groupUser" <ww:if test="#session.fileUploadMode.fileType == 'groupUser'">selected</ww:if> >Ⱥ���û���Ϣ</option>
										</select> 
		   					</div>
									<div id="attachment0">&nbsp;&nbsp;<input style="height:22px;" size="50" type="file" name="fileUploadModel.fileArray" cName="�ļ�·��" isNeed="y">&nbsp;&nbsp;
									<!--  <input class="button" type='button' value='ɾ��'
											onClick="delAttachment('attachment0')">-->
									<input type="button" value="ȷ��"
							onclick="importSubmitCheck('dataImportForm')">
									</div>
								</div>
								
							</form>
					</td>
				</tr>
			</table>
		</div>
		</div>
	</body>
</HTML>
