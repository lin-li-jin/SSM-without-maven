<%@ page language="java" contentType="text/html;charset=GBK" %>
<HTML>
	<HEAD>
		<TITLE></TITLE>
		<META http-equiv=Content-Type content="text/html; charset=GBK">	
	</HEAD>

<BODY>
<jsp:include page="../public/TipsBar.jsp"/>	
<br><br><br><br><br>
<jsp:include page="../public/Result.jsp"/>	
	
<center>
	<br>
	<input type="button" value="�����޸�" onclick="window.history.back()">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" value="�� ��" onclick="window.location = 'logout.action'">

</center>

</BODY></HTML>
