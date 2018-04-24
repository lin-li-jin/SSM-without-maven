<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="tt" uri="talent-tag"%>
<%@ taglib prefix="ww" uri="webwork"%>
<%@ taglib uri="extremecomponents" prefix="ec"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<!-- 
/**
 * Amendment No : forex130005
 * Modify By : du zhichen
 * Date : 2013-07-09
 * Decription : 会计分录
 */
 -->
  <head>

    
    <title>会计分录显示页面</title>
    
	<link rel="stylesheet" href="resources/css/style.css" type="text/css" media="screen, print" />
	<link rel="stylesheet" href="resources/css/extremecomponents.css" type="text/css" />
	
	<script language="JavaScript" src="resources/calendar/js/Calendar.js"></script>
	<script type="text/javascript" src="resources/js/jquery-1.4.2.js"></script>		
		<script src="resources/ponyjs/jquery.js" type="text/javascript"></script>
		<script src="resources/ponyjs/jquery_validate.js" type="text/javascript"></script>
		<script src="resources/ponyjs/jquery.ui.draggable.js" type="text/javascript"></script>
		<script src="resources/ponyjs/jquery.alerts.js" type="text/javascript"></script>	
		<script src="resources/js/check.js" type="text/javascript"></script>	
		<script language="JavaScript" src="resources/js/Common.js"></script>
		<script type="text/javascript" src="resources/js/check.js"></script>
		<script type="text/javascript" src="resources/js/validate.js"></script>
  </head>

  <body style="padding-top:10px"> 
  
  
  <FIELDSET align="center">
		<LEGEND>会计分录		
		</LEGEND>
  <form action="accountingEntry" method="post">
    <div >
   		<table style="width:700px;margin:0 auto">
   			<ww:iterator value="#request.actEntry" >
   			<tr>
   				<td align=left style="width:500px">
   				<ww:bean name="com.opensymphony.webwork.util.Counter" id="counter">
					<ww:param name="first" value="1"/>  
					<ww:param name="last" value="catSeqNo"/>
				</ww:bean>
				<ww:iterator value="#counter">
   				&nbsp;&nbsp;&nbsp;&nbsp;
   				</ww:iterator>
   					<ww:property value="cdFlg"/>
   					&nbsp;&nbsp;&nbsp;&nbsp;
   					<ww:property value="actName"/>
   					&nbsp;&nbsp;
   					<ww:property value="actNo" />
   				</td>
   				<td align=right style="width:100px">
   					<ww:property value="amount"/>
   				</td>
   				<td>
   					<ww:property value="ccy" />	
 				</td>
   			</tr>
   			</ww:iterator>
   		</table>
   		
    </div>   
    <div >
    	<input type="button" class="button" value="<tt:label name="button_cancel"/>" title="返回" onClick="window.history.back();">
  
    </div>
    </form>
    
  </FIELDSET>
  </body>
</html>
