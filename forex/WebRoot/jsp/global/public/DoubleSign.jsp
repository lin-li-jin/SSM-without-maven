<%@ page contentType="text/html;charset=GBK"%>

<script type="text/javascript" src="resources/js/check.js"></script>
<script type="text/javascript" src="resources/js/Common.js"></script>
<script type="text/javascript" src="jsp/global/public/js/wz_dragdrop.js"></script>
<script type="text/javascript" src="jsp/global/public/js/doublesign.js"></script>
<link rel="stylesheet" href="resources/css/talent.css" type="text/css" />
<link rel="stylesheet" href="resources/css/talent_query.css" type="text/css" media="screen, print" />		
<link rel="stylesheet" href="resources/css/talent_tips.css" type="text/css" media="screen, print" />
<script>
var nextfield = "";
function keyHandle(){
	if(event){
		if(event.keyCode == 13){
			if(nextfield != "" && document.all(nextfield)){
				document.all(nextfield).focus();
				return;
			}
		}
	}
	return;
}	
</script>
<div id="DoubleSignDiv" class="MoveFormDiv" >

	<iframe id="DoubleSignIframe"  src="javascript:false" scrolling="no" frameborder="0" style="z-index:-1;position:absolute; top:0px; left:0px;width:300px;height:200px;">
</iframe>
 
<style type="text/css">
<!--
.MoveFormDiv{
	border: thin outset;
	visibility:hidden;
	position: absolute;
	left:200px;
	top:100px;
	background-color:white;
}

-->
</style>
	<FIELDSET align="center" style="{width:300px;height:200px}">
		
		<form name="doubleSignForm">
			<table width="100%" height="100%" border="0" class="detail" cellpadding="0" cellspacing="0" onkeydown="keyHandle();">
	   			
	   			<tr><td align="center" colspan="2">   				
	   				<image src="resources/image/key.jpg">双签授权</td>
	   			</tr>
	   			
	   			<tr><td style="{text-align:right}">用户代码：</td>
	   					<td style="{text-align:left}"><input type="text" value="" name="doubleSign.userId" size="10"  maxlength="20" cName="用户代码" isNeed="y" onfocus="this.select();nextfield='doubleSign.password';"></td>
	   			</tr>
	   			<tr><td style="{text-align:right}">用户密码：</td>
	   					<td style="{text-align:left}"><input type="password" value="" name="doubleSign.password" size="10"  maxlength="12" cName="用户密码" isNeed="y" onfocus="this.select();nextfield='SubmitDoubleSignBtn';"></td>
	   			</tr>
	   			
	   			<tr><td style="{text-align:center}" colspan="2">
	   					<input type="button" class="button" name="SubmitDoubleSignBtn" value="提 交" onclick="javascript:submitDoubleSignForm();" >
						<input type="button" class="button" value="关 闭"  onclick="javascript:hideDoubleSignForm();">
					</td>
	   			</tr>
	   				
	   		</table>
	   		<input type="hidden" name="submitFormName">
	   	</form>
   		
	</FIELDSET>

</div>

<script type="text/javascript">
<!--
SET_DHTML("DoubleSignDiv");
function killErrors() {
	return true;
}
window.onerror = killErrors;
//-->
</script>