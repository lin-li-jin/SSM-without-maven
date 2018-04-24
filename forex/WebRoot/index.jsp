<%@ page contentType="text/html;charset=GBK"%>
<%@ taglib prefix="ww" uri="webwork"%>
<ww:if test="#session.loginUserModel == null">
	<%System.out.println("-----------"+request.getContextPath());%>
	<%request.getRequestDispatcher("/jsp/global/login/Login.jsp").forward(request,response);%>
</ww:if>
<script src="resources/ponyjs/jquery.js" type="text/javascript"></script>
	<script src="resources/ponyjs/jquery_validate.js" type="text/javascript"></script>
	<script src="resources/ponyjs/jquery.ui.draggable.js" type="text/javascript"></script>
	<script src="resources/ponyjs/jquery.alerts.js"	type="text/javascript"></script>
<script language="JavaScript1.2">
<!--hide
var isnMonth = new
Array("1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月");
var isnDay = new
Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六","星期日");
today = new Date () ;
Year=today.getYear();
Date=today.getDate();
if (document.all)
document.title="银行外汇投资模拟系统  今天是: "+Year+"年"+isnMonth[today.getMonth()]+Date+"日"+isnDay[today.getDay()]
//--hide-->
</script>
<head>
<title></title>
<!-- <link rel="shortcut icon" href="images/favicon.ico" /> -->
</head>

<script>
	$(function(){
	 	var addr="<ww:property value='#session.addr'/>";
 	 	if(null!=addr&&addr!=""&&addr!="0"){
   	  document.getElementById("center").src=addr;
  		 $.post("closeAddr.action",{addr:""},
				function(dat){
				
		});	
  	 
  	}


  });
</script>
<frameset   rows="61,20,*,35"  frameSpacing="0" frameborder="NO"  border="0" framespacing="0">
		<frame src="banner.jsp" name="bannerFrame" noresize scrolling="no">
		<!-- <frame src="jsp/global/public/hDivision.jsp"  scrolling="no"> -->
		<!-- <frame id="titleBar" name="titleBar" src="jsp/global/menu/titleBar.jsp"  scrolling="no"> -->
		<frame src="jsp/global/public/hDivision.jsp"  scrolling="no">
		<frameset rows="*" cols="210,11,*" name="centerFrame" framespacing="0"
			frameborder="NO" border="0">
		  	<frame id="treeMenu" name="treeMenu" src="jsp/global/menu/treeMenu.jsp" name="controlFrame" scrolling="no" noresize>
		    <frame src="jsp/global/public/vDivision.jsp" noresize scrolling="no">
			<frame name="center" id="center" src="homePageInit.action" border=0 noresize>
		</frameset>
		<frame  src="bottom.html" scrolling="no"  frameborder="NO">
</frameset>

<noframes>
	<body>
	</body>
</noframes>
