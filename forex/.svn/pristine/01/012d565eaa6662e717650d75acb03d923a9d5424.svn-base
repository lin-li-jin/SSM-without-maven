<%@ page language="java" contentType="text/html;charset=GBK" import="Coalesys.PanelBar.*"%>
<%@ page import="com.talent.auth.bean.model.*"%>
<%@ page import="com.talent.menu.*"%>

<%
	UserModel user = (UserModel)session.getAttribute("loginUserModel");
	//String sMenuFile = "";
	//if(user.getUserType().equals("2")){
	//	sMenuFile = "/orgMenu.xml";
	//}
	//else if(user.getUserType().equals("3")){
	///	sMenuFile = "/bankMenu.xml";
	//}
	//else{
	String	sMenuFile = "/menu.xml";
//	}*/
%>
<%
	// Create the PanelBar object
	PanelBar objPB = new PanelBar(); 
	// Register the PanelBar object
	objPB.setUserData("your key goes here");

	// Set for browser auto detect
	objPB.setUserAgent(request.getHeader("User-Agent"));

	// Set the PanelBar properties
	objPB.setBackgroundColor("#d6dff7");
	objPB.setBackgroundImage("./images/xpblue/panelbackground.gif");
	objPB.setBorderSize(0);
	objPB.setButtonBackgroundImage("./images/xpblue/buttonbackground.gif");
	objPB.setButtonBorderSize(0);
	objPB.setButtonColor("#c0c0c0");
	objPB.setButtonHeight(25);
	objPB.setButtonOuterHighlightColor("#dfdfdf");
	objPB.setButtonOuterShadowColor("#000000");
	objPB.setClearPixelImage("./images/clearpixel.gif");
	objPB.setExpandMode(ExpandModeConstants.MultiGroupContentHeight);
	objPB.setGroupSpacing(16);
	objPB.setGroupSpacingColor("#A7C0E8");
	objPB.setHeight(200);
	objPB.setIconHeight(16);
	objPB.setIconPosition(IconPositionConstants.Left);
	objPB.setIconWidth(16);
	objPB.setInnerHighlightColor("#dfdfdf");
	objPB.setInnerShadowColor("#000000");
	objPB.setItemSpacing(5);
	objPB.setScrollDownActiveImage("./images/scroll_down_enable.gif");
	objPB.setScrollDownDisabledImage("./images/scroll_disable.gif");
	objPB.setScrollDownEnabledImage("./images/scroll_down_active.gif");
	objPB.setScrollUpActiveImage("./images/scroll_up_enable.gif");
	objPB.setScrollUpDisabledImage("./images/scroll_disable.gif");
	objPB.setScrollUpEnabledImage("./images/scroll_up_active.gif");
	objPB.setSelectedButtonBackgroundImage("./images/xpblue/selectedbuttonbackground.gif");
	objPB.setWidth(135);

	//(new MenuConfig()).initMenu(objPB, sMenuFile);
	//(new MenuConfig()).initMenuWithPermission(objPB, sMenuFile, user.getPermisions());
	//(new MenuConfig()).initLeftMenuWithPermission(objPB, sMenuFile, user.getPermissions());
	(new MenuConfig()).initLeftMenu(objPB, sMenuFile);

	objPB.getButtonFont().setAlignment("left");
	objPB.getButtonFont().setColor("#2311E1");
	objPB.getButtonFont().setFamily("Arial, Helvetica, sans-serif");
	objPB.getButtonFont().setPaddingLeft(15);
	objPB.getButtonFont().setWeight("bold");
	objPB.getButtonHoverFont().setColor("#DC4900");
	objPB.getButtonHoverFont().setFamily("Arial, Helvetica, sans-serif");
	objPB.getButtonHoverFont().setWeight("bold");
	objPB.getItemFont().setAlignment("left");
	objPB.getItemFont().setColor("#215dc6");
	objPB.getItemFont().setFamily("Arial, Helvetica, sans-serif");
	objPB.getItemFont().setPaddingLeft(15);
	objPB.getItemHoverFont().setColor("#DC4900");
	objPB.getItemHoverFont().setFamily("Arial, Helvetica, sans-serif");
%>
<script language="javascript">
var online= new Array();
if (!document.layers)
document.write('<div id="menu" style="position:absolute">')
</script>

<html>
	<head>
		<title>Untitled</title>
		<link rel="stylesheet" href="../../resources/css/blueMenuLink.css"
			type="text/css" />
		<style type="text/css">
		<%=objPB.GenerateStyleSheet()%>
               .style29 {color: #FFFFFF; font-weight: bold; font-family: Arial, Helvetica, sans-serif; font-size: 10pt; }
     	
		BODY {
			COLOR: #2610DD; 
			FONT-FAMILY: Arial; 
			FONT-SIZE: 11.5pt; 
			LINE-HEIGHT: 12pt; table,: 9pt; 
			SCROLLBAR-FACE-COLOR: #ffffff; 	
			SCROLLBAR-HIGHLIGHT-COLOR: #ffffff; 
			SCROLLBAR-3DLIGHT-COLOR: #ffffff; 
			SCROLLBAR-ARROW-COLOR: #000000; 
			SCROLLBAR-TRACK-COLOR: #ffffff; 
			SCROLLBAR-DARKSHADOW-COLOR: #ffffff; 
			SCROLLBAR-SHADOW-COLO: #2610DD 	
			font-family: "Geneva", "Arial", "Helvetica", "san-serif"
		}
  </style>

		<script language="JavaScript" type="text/javascript">
		<%=objPB.GenerateJavaScript(0)%>
</script>

	</head>

	<body onLoad="<%=objPB.GenerateOnLoadEvent()%>"
		onResize="<%=objPB.GenerateOnResizeEvent()%>" bgcolor="#F1F4FA"
		leftmargin=10 topmargin=5 marginheight=0 marginwidth=0>

		<layer name="menu" left="46" id="menu">
		<table border=1 width=100% height=100%>
			<tr>
				<table border=0 bgcolor=#A7C0E8 cellpadding="0" cellspacing="0">
					<tr>
						<td width=100% align=center>
							<img src='../../../resources/image/frame/mainMenuTop.jpg'>
						</td>
					</tr>
				</table>
				<table border=0 width=100% height=100% bgcolor=#A7C0E8
					cellpadding="0" cellspacing="3">
					<tr>
						<td width=100% height=100%>

							<center>
								<%=objPB.GeneratePanelBar(0)%>
							</center>
						</td>
					</tr>
				</table>

				<table border=0 width=100% height=100% bgcolor=#ffffff
					cellpadding="0" cellspacing="0">
					<tr>
						<td width=* height=12 align=center colspan=0
							background='../../../resources/image/frame/mainMenuBottom.jpg'>

						</td>
					</tr>
				</table>
			</tr>
		</table>

		</layer>


	</body>
</html>
