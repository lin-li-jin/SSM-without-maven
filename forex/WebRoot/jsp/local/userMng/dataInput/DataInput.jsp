<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="ww" uri="webwork"%>
<html>
	<head>
        <script language="JavaScript" src="jsp/local/dataInput/js/dataInput.js"></script>
		<script language="JavaScript" src="resources/js/check.js"></script>
		<script language="JavaScript" src="resources/js/Common.js"></script>
		<script type="text/javascript" src="resources/js/validate.js"></script>
		<link rel="stylesheet" href="resources/css/style.css" type="text/css" />	
		<title>批量数据导入</title>
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
							批量数据导入
							</LEGEND>
							<form style="text-align:center"  class="form" name="dataImportForm" action="dataInput.action"
								method="post" enctype="MULTIPART/FORM-DATA">
								  <div id="dblFileDiv1" style="width:100%">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<div style="width:700px; height:30px;font-size:12px">
									<span> &nbsp;文件类型：</span>
		   								<select name="fileUploadModel.fileType" cName="文件类型">									
											<option value="student" <ww:if test="#session.fileUploadModel.fileType == 'student'">selected</ww:if> >行政班学生信息</option>
											<option value="student4Edu" <ww:if test="#session.fileUploadModel.fileType == 'student4Edu'">selected</ww:if> >教学班学生信息</option>
											<option value="teacher" <ww:if test="#session.fileUploadModel.fileType == 'teacher'">selected</ww:if> >教师信息</option>
										</select> 
		   					</div>
									<div id="attachment0">&nbsp;&nbsp;<input style="height:22px;" size="50"type="file"  name="fileUploadModel.fileArray" cName="文件路径" isNeed="y"/>&nbsp;&nbsp;
									<!--  <input class="button" type='button' value='删除'
											onClick="delAttachment('attachment0')">-->
									<input class="button" type="button" value="确定" onClick="document.forms.dataImportForm.submit();">
									</div>
								</div>
								
							</form>
					</td>
				</tr>
			</table>
		</div>
		<div id="download" style="text-align:left; font-size:14px;margin-top:10px; ">
		<span>
		<a style="color:red;  text-decoration:underline;" href="#"  onClick="downLoad();" >文件模板下载</a>
		<!-- <a style="color:red;  text-decoration:underline;" href="\ems\resources\fileModel\excelFiles.rar"   >文件模板下载</a>-->
		<!--  <a style="color:red;  text-decoration:underline;" href=""  onClick="location.assign('downLoad.action');return false;" >文件模板下载</a>		-->
		</span><br>	
		</div>
		<div style="margin-top: 10px; text-align: left;color: #A9A9A9;">
		<font color="#708090　">上传文件注意：</font><br/>
		<OL>		
			<li>
				上传文件必须是OFFICE（2003） EXCEL 文件。
			</li>
			<li>
				上传文件请选择"导入数据文件的类型",并需要严格按照上传数据文件格式。
			</li>
		</OL>
			
		</div>
		</div>
	</body>
</HTML>
