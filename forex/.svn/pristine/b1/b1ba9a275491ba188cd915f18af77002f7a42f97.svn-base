<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="ww" uri="webwork"%>

<!-- Amendment No:GJSY130004
Amendment Descr:学号定制信息取消
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
		<title>批量数据导入</title>
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
	    		<span style="margin-left: 10px;">学生信息导入:</span>
		    </div>
		<div>
			<input type="button" value="排名参数设置" onclick="changeTab('0')">
			<input type="button" value="学生信息导入" onclick="changeTab('1')">
			<input type="button" value="密码重置与账户解锁" onclick="changeTab('2')">
			<input type="button" value="交易员账户管理" onclick="changeTab('3')">
		</div>
		<div id="student">
			<table cellspacing="0" cellpadding="0" width=100%>
				<tr>
					<td style="text-align: center">
						<FIELDSET align="center">
							<LEGEND>
							学生信息批量导入
							</LEGEND>
							<form style="text-align:center"  class="form" name="dataImportForm" action="fileImport.action"
								method="post" enctype="MULTIPART/FORM-DATA">
								  <div id="dblFileDiv1" style="width:100%">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="hidden" name="fileUploadModel.fileType" id="fileUploadModel.fileType" value="student">
										<div id="attachment0">
										<span style="color:red">*</span><span class="font22">请浏览加入excel文件&nbsp;&nbsp;</span>
										<input style="height:22px;" id="fileArray" size="40"type="file"  name="fileUploadModel.fileArray" cName="文件路径" isNeed="Y"/>&nbsp;&nbsp;
											<!--  <input class="button" type='button' value='删除'
												onClick="delAttachment('attachment0')">-->
										<input class="button" type="button" value="确定" onClick="javascript:importSubmitCheck('dataImportForm')">
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
		<div style="margin-top: 10px; text-align: left;font-size:14px;color: #1E90FF;">
		上传文件注意：<br/>
		<OL>		
			<li>
				上传文件必须是OFFICE（2003） EXCEL 文件，可以点击“文件模版下载”下载模板。
			</li>
			<li>请按照如下格式在文件中录入学生信息:</li>
			<li><div style="color:red">如果学校管理员已经建立了学号规则（即从第几位到第几位表示系，第几位表示班），则年级、系、专业、班级栏不用填写。</div></li>
			<li><div style="color:red">如果学校管理员未学号规则，则所有栏均为必填项，其中系、专业可以为数字或汉字，但要与管理员创建的系、专业名称相一致。</div></li>
									
		</OL>
			
		</div>
		<div id="fileModel" style="text-align:left;font-size:12px;margin-top:5px;margin-left:10px;">
			<!-- <table border="1" width="500" bgcolor="#dfe2e7"　bordercolor="red" cellspacing="0" cellpadding="0" style="font-size:12px;"　> -->
				<table class="pn-ftable"  cellspacing="1">
				<tr>
					<td style="text-align: center" class="pn-flabel"><font color="red">*必填项(必须是数字)</font></td>
					<td style="text-align: center" class="pn-flabel"><font color="red">*必填项(必须是英文或汉字)</font></td>
					<td style="text-align: center" class="pn-flabel"><font color="red">*必填项</font></td>
					<td style="text-align: center" class="pn-flabel"><font color="red">*必填项</font></td>
					<td style="text-align: center" class="pn-flabel"><font color="red">*必填项</font></td>
					<td style="text-align: center" class="pn-flabel"><font color="red">*必填项</font></td>
				</tr>
				<tr>
					<td style="text-align: center" class="pn-flabel">学号</td>
					<td style="text-align: center" class="pn-flabel">姓名</td>
					<td style="text-align: center" class="pn-flabel">年级</td>
					<td style="text-align: center" class="pn-flabel">系</td>
					<td style="text-align: center" class="pn-flabel">专业</td>
					<td style="text-align: center" class="pn-flabel">教学班号</td>
				</tr>
				<tr>
					<td style="text-align: center" class="pn-flabel">091512102</td>
					<td style="text-align: center" class="pn-flabel">李华</td>
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
