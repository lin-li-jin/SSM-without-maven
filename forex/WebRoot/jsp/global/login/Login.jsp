<%@ page contentType="text/html;charset=GBK" %>
<%@ taglib prefix="ww" uri="webwork" %>
<html>

<head>
	<title>���ģ��Ͷ��ϵͳ</title>
	<link rel="stylesheet" href="/forex/resources/css/talent.css" type="text/css" media="screen, print" />	
	<meta http-equiv="content-type" content="text/html; charset=GBK" />	
  	<script language="JavaScript" src="/forex/resources/js/check.js"></script>	
	<script language="JavaScript" src="/forex/resources/js/Common.js"></script>
	<link rel="stylesheet" href="/forex/resources/css/style.css" type="text/css" media="screen, print" />	  		
<style>
.drop0 {color:#001AA6;float:left;filter:DropShadow(Color=#ffffff, OffX=2, OffY=2, Positive=1);FONT-SIZE: 12px;}
</style>	
<script language="JavaScript">

var nextfield="";
function keyHandle(){
	if(event){
		if(event.keyCode==13){
			if(nextfield != "" && document.all(nextfield)){
				document.all(nextfield).focus();
				return;
			}else{
				login();
			}
		}
	}
	return;
}

function login(){
	document.loginForm.submit();
	processTip();
}

//<ww:if test="#request.tipModel.tip != '' || #request.tipModel.tip != null">
	//alert(' <ww:i18n name="message"><ww:text name="%{#request.tipModel.tip}" /></ww:i18n>');
	//window.location='/forex';
//</ww:if>
</SCRIPT>
<!--  
<SCRIPT>
/*
function init(){
	if(top.location != self.location) { 
		top.location=self.location; 
	} 
	document.loginForm('userInfo.userId').focus();
}
*/
</SCRIPT>
-->	
</head>

<body topmargin="0" leftmargin="0" class="main"  style="background:#cbe0f9;text-align:center" >

<div id="container" >

	  <div id="login" >
	    <h1>&nbsp;</h1>
		  <form class="formLogin" method="post" action="/forex/login.action" name="loginForm">
			<div onKeyDown="javascript:keyHandle();">
	          	  <div>
	                <div style="height:10px;"></div>
	              </div>
	              <div>
	                <span>�û���</span>
	                <span><input type="text" name="userModel.userId" isNeed="y" cName="�û�����" size="30" maxlength="20" onFocus="this.select();nextfield='userModel.groupId'" style="width: 150px;"></span>
	              </div>
	              <div>
	                <div ></div>
	              </div>
	             <!-- <div>
	                <div>Ⱥ��ţ�</div>
	                <div><input type="text" name="userModel.groupId" isNeed="y" cName="Ⱥ���" size="30" maxlength="12" onFocus="this.select();nextfield='userModel.password'"></div>
	              </div>
	              <div>
	                <div></div>
	              </div> --> 
	              <div>
	                <span>���룺</span>
	                <span><input type="password" name="userModel.password" isNeed="y" cName="�û����к�" size="30" maxlength="10" style="width: 150px;"></span>
	              </div>
	              <div>
	              	<ww:if test="#session.checkResult.equals(\"false\")">
	                <span style="color:red">�û��Ż���������</span>
	                </ww:if>
	                <ww:elseif test="#session.checkResult.equals(\"notActive\")">
	                <span style="color:red">�û��ű��������ҹ���Ա������</span>
	                </ww:elseif>
	              </div>       
	             <!--   <div>
	                <div align="right">��½ʶ���룺</div>
	                <div align="left" style="{text-align:left;}"><input type="text" name="userInfo.challenge" isNeed="y" cName="��½ʶ����" size="30" maxlength="6" onFocus="this.select();nextfield=''">
	                &nbsp;&nbsp;&nbsp;
	                <img name="challenge" src="changeChallenge.action">&nbsp;&nbsp;<a href="#" onClick="document.loginForm.challenge.src='changeChallenge.action'">�����壬��һ��</a></div>
	              </div>
	              <div>
	                <div height=20></div>
	              </div>
	             -->     
	              <div>
	               <!--   <div colspan=2 ><img src='resources/image/loginButton.jpg' usemap='#MapMap' onMouseOver="this.style.cursor='hand'" a href="test.jsp"></div>-->
	                <div style="margin-left: 100px">
	                	<input id="button" class="btn" type="submit" usemap='#MapMap' onMouseOver="this.style.cursor='hand'" a href="test.jsp" value="��  ��" />
	                </div>
	              </div>
	              <map name="MapMap" >
	                <area shape="rect" coords="7,4,110,25" onClick="login()" >
	              </map> 
	              <div>
	                <div id="Errdiv" colspan=2 style="color:red; font-size:15px}">
	                 
					</div>
	              </div>
				  <div>
				    <div colspan=3></div>
				    <div></div>
				  </div>
				  <div>
				    <div colspan=3 align=center><font color="red">
				    <!--  <img src="resources/image/icon_message.gif"/>&nbsp;&nbsp;�����½ҳ��ͣ��̫�ã����Ȼ�һ�ŵ�½ʶ�����ٵ�½��-->
				   </font></div>
				    <div></div>
				  </div>                        
	           </div>
          </form>
          </div>
          <!-- <span style="margin-top:0px;" id="copyright" class="textcolor">�㶫����ѧԺ ���ݽ��Ƽ���˾&nbsp;&nbsp;&nbsp;</span> -->
		</div>










</body>

</html>