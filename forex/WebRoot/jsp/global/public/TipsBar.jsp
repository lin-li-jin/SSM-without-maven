<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@taglib prefix="ww" uri="webwork" %>
<HTML>
	<HEAD>
		<TITLE>操作提示条</TITLE>
		<META http-equiv=Content-Type content="text/html; charset=GBK">
		<META http-equiv=Pragma content=no-cache>
		<META http-equiv=Expires content=-1>
		<LINK href="../../../resources/css/main-action.css" type=text/css rel=stylesheet>
	</HEAD>
	
<BODY>

<TABLE class=infoMacro1 cellSpacing=0 cellPadding=0 width="100%" border=0>
     <TR><TD width=16 height="30"><IMG alt="" 
    		src="../../../resources/image/tips.gif" 
        	align=absMiddle border=0></TD>
        <TD><SPAN class=code-tag> <SPAN class=code-quote> 
	        	<font style="{font-size:10pt}">
		        	<ww:property value="#session.loginUserModel.name"/>
		        	[<ww:property value="#session.loginUserModel.unitId"/>&nbsp;<ww:property value="#session.loginUserModel.unitName"/>]
				</font>
			</SPAN></SPAN>
		</TD>
    </TR>
</TABLE>

</BODY></HTML>
