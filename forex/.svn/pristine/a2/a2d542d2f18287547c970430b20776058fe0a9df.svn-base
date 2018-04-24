<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@taglib prefix="ww" uri="webwork"%>
<%@ page import="com.talent.forex.bean.model.TipModel,java.util.*"%>

<%
	Iterator it = (new ArrayList()).iterator();
	if (request.getAttribute("tipModel") != null) {
		Collection tipParams = ((TipModel) request
		.getAttribute("tipModel")).getParams();
		it = tipParams.iterator();
	}
%>
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
		<style>
				v\:*{behavior: url(#default#VML);}
		</style>
	</HEAD>

	<BODY>

		<center>
			<div id="body">
				<table width="80%" style="{margin-top:100px;border:2px solid #C3BEF4;}">
					<ww:if test='#request.tipModel.processCode=="00"'>

						<TR bgcolor="#F4F9FE">
							<TD align="center" width="100">
								<IMG alt=""  src="../../../resources/image/check.gif"
									align=absMiddle border=0>
							</TD>
							<TD
								style="{text-align:center;width:500;height:180;word-break:break-all;}">
								<font color="green" style="{font-size:20}">
								 <ww:i18n name="message">
										<ww:text name="%{#request.tipModel.tip}">
											<%
											while (it.hasNext()) {
											%>

											<ww:param>
												<%=(it.next())%>
											</ww:param>

											<%
											}
											%>
										</ww:text>
									</ww:i18n> </font>
									
							</TD>
						<TR>
					</ww:if>
					<ww:elseif test='#request.tipModel.processCode=="10"'>
						<TR bgcolor="#F4F9FE">
							<TD align="center" width="100">
								<IMG alt="" 
									src="../../../resources/image/forbidden.gif" align=absMiddle border=0>
							</TD>
							<TD
								style="{text-align:center;width:550;height:180;word-break:break-all;}">
								<font color="red" style="{font-size:20}"> <ww:i18n
										name="message">
										<ww:text name="%{#request.tipModel.tip}">
											<%
											while (it.hasNext()) {
											%>

											<ww:param>
												<%=(it.next())%>
											</ww:param>

											<%
											}
											%>
										</ww:text>
									</ww:i18n> </font>
									
							</TD>
						<TR>
					</ww:elseif>

					<ww:else>
						<TR bgcolor="#F4F9FE">
							<TD align="center" width="100">
								<IMG alt="" 
									src="../../../resources/image/forbidden.gif" align=absMiddle border=0>
							</TD>
							<TD style="{text-align:center;height:180}" bgcolor="#FDF9F9">
								<font color="red" style="{font-size:20}"> 
								<ww:i18n name="message">
										<ww:text name="%{#session.error_msg}" />
								</ww:i18n> </font>	
							</TD>
						<TR>
					</ww:else>

				</TABLE>

				<br>
			</div>
		</center>
	</BODY>
</HTML>
