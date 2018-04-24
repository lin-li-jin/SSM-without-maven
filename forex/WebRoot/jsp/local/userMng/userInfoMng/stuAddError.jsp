<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@taglib prefix="ww" uri="webwork"%>
<HTML xmlns:v>
	<HEAD>
		<TITLE></TITLE>
		<META http-equiv=Content-Type content="text/html; charset=GBK">
		<link rel="stylesheet" href="resources/css/talent.css" type="text/css" />
		<LINK href="resources/css/main-action.css" type=text/css
			rel=stylesheet>
		<link rel="stylesheet" href="resources/css/talent.css" type="text/css"
			media="screen, print" />
		<link rel="stylesheet" href="resources/css/talent_query.css"
			type="text/css" media="screen, print" />
		<link rel="stylesheet" href="resources/css/talent_tips.css"
			type="text/css" media="screen, print" />
			<link rel="stylesheet" href="resources/css/style.css" type="text/css"
			media="screen, print" />
		<style>
				v\:*{behavior: url(#default#VML);}
		</style>
	</HEAD>

	<BODY>

		<center>
			<div id="body">
				<table width="80%" style="{margin-top:100px;border:2px solid #C3BEF4;}">
					
						<TR bgcolor="#F4F9FE">
							<TD align="right" width="100">
								<IMG alt="" 
									src="resources/image/forbidden.gif" align=absMiddle border=0>
							</TD>
							<TD
								style="{text-align:center;width:550;height:180;word-break:break-all;}">
								<font color="red" style="{font-size:20}"> <ww:i18n
										name="message">
										<ww:text name="aa">
											<span style="font-size:40px;color:red;">文件导入失败！</span>
										</ww:text>
									</ww:i18n> 
							</font>
							
							
									
							</TD>
						<TR>
						<tr>
							<td colspan="2">
								<font color="red" style="{font-size:20}">
									<ww:property value='#request.errorResult'/>
								</font>
							</td>
						</tr>
				</TABLE>
				<br><br>
					<div>
					<center>
				<input type="button" class="button"
					 value="重新上传" onClick="window.history.back()">
		
	</center>
	</div>	
				<br>
			</div>
		</center>
	</BODY>
</HTML>

