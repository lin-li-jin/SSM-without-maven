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
Array("1��","2��","3��","4��","5��","6��","7��","8��","9��","10��","11��","12��");
var isnDay = new
Array("������","����һ","���ڶ�","������","������","������","������","������");
today = new Date () ;
Year=today.getYear();
Date=today.getDate();
if (document.all)
document.title="ʵ�������ۺϹ���ƽ̨ ������: "+Year+"��"+isnMonth[today.getMonth()]+Date+"��"+isnDay[today.getDay()]
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
<frameset   rows="60,18,*,30"  frameSpacing="0" frameborder="NO"  border="0" framespacing="0">
		<frame src="banner.jsp" name="bannerFrame" noresize scrolling="no">
		<frame src="jsp/global/public/hDivision.jsp"  scrolling="no">
		<frameset rows="*,240" cols="*" name="centerFrame" framespacing="0"
			frameborder="NO" border="0">
			<frame src="jsp/global/public/center.jsp" scrolling="no">
			<frameset rows="*" cols="50,50,50,50" name="centerFrame" framespacing="0"
				frameborder="NO" border="0">
				<frame>
				<frame class="framestyle"  id="titleBar" name="titleBar" src="jsp/global/menu/titleBar.jsp" scrolling="no">
				<frame class="framestyle" id="treeMenu" name="treeMenu" src="jsp/global/menu/treeMenu.jsp" name="controlFrame" scrolling="yes" noresize>
				<frame>
			</frameset>
		</frameset>
		<frame  src="bottom.html" scrolling="no"  frameborder="NO">
</frameset>

<noframes>
	<body>
	</body>
</noframes>
