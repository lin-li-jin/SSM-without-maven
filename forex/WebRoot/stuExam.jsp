<%@ page contentType="text/html;charset=GBK"%>
<%@ taglib prefix="ww" uri="webwork"%>
<ww:if test="#session.loginUserModel == null">
	<%System.out.println("-----------"+request.getContextPath());%>
	<%request.getRequestDispatcher("/jsp/global/login/Login.jsp").forward(request,response);%>
</ww:if>
<script language="JavaScript1.2">
<!--hide
var isnMonth = new
Array("1��","2��","3��","4��","5��","6��","7��","8��","9��","10��","11��","12��");
var isnDay = new
Array("������","����һ","���ڶ�","������","������","������","������","������");
today = new Date () ;
Year=today.getYear();
Date=today.getDate();
if (document.all)
document.title="ʵ�����п���ϵͳ ������: "+Year+"��"+isnMonth[today.getMonth()]+Date+"��"+isnDay[today.getDay()]
//--hide-->
</script>
<head>
<title></title>
</head>
<ww:if test='#session.loginUserModel.userType == "S"'>
<frameset   rows="61,20,*,35"  frameSpacing="0" frameborder="NO" border="0" framespacing="0">
		<frame src="banner.jsp" name="bannerFrame" noresize scrolling="no">
		<frame src="jsp/global/public/hDivision.jsp"  scrolling="no">
		<frameset rows="*" cols="200,11,*" name="centerFrame" framespacing="0"
			frameborder="NO" border="0">
			<frame src="jsp/global/menu/treeMenu.jsp" name="controlFrame" scrolling="no" noresize>
		     <frame src="jsp/global/public/vDivision.jsp" noresize scrolling="no">
			<!-- <frame name="center" id="center" src="/eems/stuTestPaperInit.action?paperFor=11&rand=212121" border=0 noresize> -->
		<frame name="center" id="center" src="jsp/global/public/main.jsp">
		</frameset>
		<frame  src="bottom.html" scrolling="no"  frameborder="NO"></frame>
</frameset>
</ww:if>
<noframes>
	<body>
	</body>
</noframes>

