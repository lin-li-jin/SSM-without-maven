<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="ww" uri="webwork"%>
<html>
	<head>
        <script language="JavaScript" src="jsp/local/dataInput/js/dataInput.js"></script>
		<script language="JavaScript" src="resources/js/check.js"></script>
		<script language="JavaScript" src="resources/js/Common.js"></script>
		<script type="text/javascript" src="resources/js/validate.js"></script>
		<link rel="stylesheet" href="resources/css/style.css" type="text/css" />	
		<title>�������ݵ���</title>
		<script type="text/javascript">
		function downLoad(){
		//window.open("/ems/jsp/local/dataInput/DownLoad.jsp");
		window.open("/ems/resources/fileModel/excelFiles.rar");
}
		
		</script>
	</head>
	<body style="padding-top:5px;">
		<div id="body">
		<div id="student">
			<table cellspacing="0" cellpadding="0" width=100%>
				<tr>
					<td style="text-align: center">
						<FIELDSET align="center">
							<LEGEND>
							�������ݵ���
							</LEGEND>
							<form style="text-align:center"  class="form" name="dataImportForm" action="dataInput.action"
								method="post" enctype="MULTIPART/FORM-DATA">
								  <div id="dblFileDiv1" style="width:100%">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<div style="width:700px; height:30px;font-size:12px">
									<span> &nbsp;�ļ����ͣ�</span>
		   								<select name="fileUploadModel.fileType" cName="�ļ�����">									
											<option value="student" <ww:if test="#session.fileUploadModel.fileType == 'student'">selected</ww:if> >������ѧ����Ϣ</option>
											<option value="student4Edu" <ww:if test="#session.fileUploadModel.fileType == 'student4Edu'">selected</ww:if> >��ѧ��ѧ����Ϣ</option>
											<option value="teacher" <ww:if test="#session.fileUploadModel.fileType == 'teacher'">selected</ww:if> >��ʦ��Ϣ</option>
										</select> 
		   					</div>
									<div id="attachment0">&nbsp;&nbsp;<input style="height:22px;" size="50"type="file"  name="fileUploadModel.fileArray" cName="�ļ�·��" isNeed="y"/>&nbsp;&nbsp;
									<!--  <input class="button" type='button' value='ɾ��'
											onClick="delAttachment('attachment0')">-->
									<input class="button" type="button" value="ȷ��" onClick="document.forms.dataImportForm.submit();">
									</div>
								</div>
								
							</form>
					</td>
				</tr>
			</table>
		</div>
		<div id="download" style="text-align:left; font-size:14px;margin-top:10px; ">
		<span>
		<a style="color:red;  text-decoration:underline;" href="#"  onClick="downLoad();" >�ļ�ģ������</a>
		<!-- <a style="color:red;  text-decoration:underline;" href="\ems\resources\fileModel\excelFiles.rar"   >�ļ�ģ������</a>-->
		<!--  <a style="color:red;  text-decoration:underline;" href=""  onClick="location.assign('downLoad.action');return false;" >�ļ�ģ������</a>		-->
		</span><br>	
		</div>
		<div style="margin-top: 10px; text-align: left;color: #A9A9A9;">
		<font color="#708090��">�ϴ��ļ�ע�⣺</font><br/>
		<OL>		
			<li>
				�ϴ��ļ�������OFFICE��2003�� EXCEL �ļ���
			</li>
			<li>
				�ϴ��ļ���ѡ��"���������ļ�������",����Ҫ�ϸ����ϴ������ļ���ʽ��
			</li>
		</OL>
			
		</div>
		</div>
	</body>
</HTML>
