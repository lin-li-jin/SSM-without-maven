<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="ww" uri="webwork"%>

<!-- Amendment No:GJSY130004
Amendment Descr:ѧ�Ŷ�����Ϣȡ��
Amendment By:duzhichen
Amendment Date:2013.7.17
 -->
<html>
	<head>
        <script language="JavaScript" src="jsp/local/userMng/dataInput/js/dataInput.js"></script>
		<script language="JavaScript" src="resources/js/check.js"></script>
		<script language="JavaScript" src="resources/js/Common.js"></script>
		<script type="text/javascript" src="resources/js/validate.js"></script>
		<script src="resources/ponyjs/jquery.js" type="text/javascript"></script>
		<script src="resources/ponyjs/jquery_validate.js"
			type="text/javascript"></script>
		<script src="resources/ponyjs/jquery.ui.draggable.js"
			type="text/javascript"></script>
		<script src="resources/ponyjs/jquery.alerts.js"
			type="text/javascript"></script>
		<!-- <link rel="stylesheet" href="resources/css/style.css" type="text/css" /> -->	
		<link rel="stylesheet" href="/forex/resources/css/font_styleY.css" type="text/css" media="screen, print" />
		<title>�������ݵ���</title>
		<style type="text/css">
			#top{margin: 0; padding:3px 5px; height: 24px; width:1200px; line-height: 24px; background-image: url("images/b.png"); color: white;}
		</style>
		<script type="text/javascript">
		function downLoad(){
		//window.open("/ems/jsp/local/dataInput/DownLoad.jsp");
		//window.open("/ems/resources/fileModel/StudentModel.xls");
			window.location.href="fileDownLoad.action"
		}
		function fileModel(){
			
			if(document.getElementById("fileModel").style.display=="none"){
				$("#fileModel").css("display","block");
			}
			else{
				$("#fileModel").css("display","none");
			}
		}
		</script>
		<script language="JavaScript">
			function changeTab(tabIndex){
				if(tabIndex=="0"){
					window.location.href = "qryParamMngInit.action";
				}
				if(tabIndex=="1"){
					window.location.href = "stuDataInputInit.action";
				}
				if(tabIndex=="2"){
					window.location.href = "pwResetAaccUnlockInit.action";
				}
				if(tabIndex=="3"){
					window.location.href = "accParamSetInit.action";
				}
			}
		</script>
	</head>
	<body>
		<div id="body">
		<div id="top">
	    		<span style="margin-left: 10px;">ѧ����Ϣ����:</span>
		    </div>
		<div>
			<input type="button" value="������������" onclick="changeTab('0')">
			<input type="button" value="ѧ����Ϣ����" onclick="changeTab('1')">
			<input type="button" value="�����������˻�����" onclick="changeTab('2')">
			<input type="button" value="����Ա�˻�����" onclick="changeTab('3')">
		</div>
		<div id="student">
			<table cellspacing="0" cellpadding="0" width=100%>
				<tr>
					<td style="text-align: center">
						<FIELDSET align="center">
							<LEGEND>
							ѧ����Ϣ��������
							</LEGEND>
							<form style="text-align:center"  class="form" name="dataImportForm" action="fileImport.action"
								method="post" enctype="MULTIPART/FORM-DATA">
								  <div id="dblFileDiv1" style="width:100%">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="hidden" name="fileUploadModel.fileType" id="fileUploadModel.fileType" value="student">
										<div id="attachment0">
										<span style="color:red">*</span><span class="font22">���������excel�ļ�&nbsp;&nbsp;</span>
										<input style="height:22px;" id="fileArray" size="40"type="file"  name="fileUploadModel.fileArray" cName="�ļ�·��" isNeed="Y"/>&nbsp;&nbsp;
											<!--  <input class="button" type='button' value='ɾ��'
												onClick="delAttachment('attachment0')">-->
										<input class="button" type="button" value="ȷ��" onClick="javascript:importSubmitCheck('dataImportForm')">
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
		<div style="margin-top: 10px; text-align: left;font-size:14px;color: #1E90FF;">
		�ϴ��ļ�ע�⣺<br/>
		<OL>		
			<li>
				�ϴ��ļ�������OFFICE��2003�� EXCEL �ļ������Ե�����ļ�ģ�����ء�����ģ�塣
			</li>
			<li>�밴�����¸�ʽ���ļ���¼��ѧ����Ϣ:</li>
			<li><div style="color:red">���ѧУ����Ա�Ѿ�������ѧ�Ź��򣨼��ӵڼ�λ���ڼ�λ��ʾϵ���ڼ�λ��ʾ�ࣩ�����꼶��ϵ��רҵ���༶��������д��</div></li>
			<li><div style="color:red">���ѧУ����Աδѧ�Ź�������������Ϊ���������ϵ��רҵ����Ϊ���ֻ��֣���Ҫ�����Ա������ϵ��רҵ������һ�¡�</div></li>
									
		</OL>
			
		</div>
		<div id="fileModel" style="text-align:left;font-size:12px;margin-top:5px;margin-left:10px;">
			<!-- <table border="1" width="500" bgcolor="#dfe2e7"��bordercolor="red" cellspacing="0" cellpadding="0" style="font-size:12px;"��> -->
				<table class="pn-ftable"  cellspacing="1">
				<tr>
					<td style="text-align: center" class="pn-flabel"><font color="red">*������(����������)</font></td>
					<td style="text-align: center" class="pn-flabel"><font color="red">*������(������Ӣ�Ļ���)</font></td>
					<td style="text-align: center" class="pn-flabel"><font color="red">*������</font></td>
					<td style="text-align: center" class="pn-flabel"><font color="red">*������</font></td>
					<td style="text-align: center" class="pn-flabel"><font color="red">*������</font></td>
					<td style="text-align: center" class="pn-flabel"><font color="red">*������</font></td>
				</tr>
				<tr>
					<td style="text-align: center" class="pn-flabel">ѧ��</td>
					<td style="text-align: center" class="pn-flabel">����</td>
					<td style="text-align: center" class="pn-flabel">�꼶</td>
					<td style="text-align: center" class="pn-flabel">ϵ</td>
					<td style="text-align: center" class="pn-flabel">רҵ</td>
					<td style="text-align: center" class="pn-flabel">��ѧ���</td>
				</tr>
				<tr>
					<td style="text-align: center" class="pn-flabel">091512102</td>
					<td style="text-align: center" class="pn-flabel">�</td>
					<td style="text-align: center" class="pn-flabel">09</td>
					<td style="text-align: center" class="pn-flabel">151</td>
					<td style="text-align: center" class="pn-flabel">1512</td>
					<td style="text-align: center" class="pn-flabel">0915121</td>
				</tr>
			</table>
		</div>
		</div>
	</body>
</HTML>
