<%@ page contentType="text/html;charset=GBK" %> 
<%@ taglib prefix="ww" uri="webwork"%>


<script type="text/javascript" src="resources/js/Common.js"></script>
<link rel="stylesheet" href="resources/css/style.css" type="text/css" media="screen, print" />
<style>
.drop0 {color:#0526D6;float:left;filter:DropShadow(Color=#ffffff, OffX=2, OffY=2, Positive=1);FONT-SIZE: 12px;}
</style>

<script language="javascript">
function printContent(){
	if(window.print){
		var printHtml = "";
		var win = parent.frames["center"];
		var origHtml = win.document.body.innerHTML;
		var printObj = win.document.all("printReport");

		//判断printReport是否为空
		if(printObj == null){
			//判断是否存在连续需要打印的报表
			var i = 1;
			var reportFlag = true;
			while(reportFlag) {
				var printObjII = win.document.all("printReport" + i);
				i++;
				if(printObjII != null) {
					printHtml = printHtml + printObjII.innerHTML + '<div class="PageNext"></div>';
				}else {
					if(i > 2)
					printHtml = printHtml.substring(0,printHtml.length - 28);
					reportFlag = false;
				}
			}
			
			if(i == 2)
				printHtml = origHtml;
		}else{
			printHtml = printObj.innerHTML;

		}
		
		win.document.body.innerHTML = printHtml;
		win.focus();
		win.print();
		win.document.body.innerHTML = origHtml;
	}
	

}

</script>

<body style="background:#cbe0f9" bgcolor="#A5AEDE" text="#000000" topmargin="0" leftmargin="0" >	 


	<div id="top">
		<table style="width:100%">
		<tr>
			<td align=left ><img style="margin-top:2px;margin-left:10px"" src="resources/image/newImages/banner.png"></img></td>
			<!-- <td class="top_td" align=right><a href="#" onClick="javascript:window.parent.close();" ><img src="resources/image/close.gif" alt="返回主页" /></a>&nbsp;&nbsp;<a href="/ekms/" target="_blank" ><img src="resources/image/zsk.gif" alt="知识库" /></a></td>
		 -->
		
		 </tr>
		 
		</table>	
    </div>
	<!--  
	<table cellSpacing=0 cellPadding=0 width=100% align=center border=0 >
	  <tr>
	  	
	    <td width=* align=center  height="50" bgcolor=#ffffff >  
			  <table  cellSpacing=0 cellPadding=0 width=100% align=center border=0>
					<tr> 
					  <td width="*" height="50" style="background:#cbe0f9" valign=bottom>&nbsp;</td>
		    		<td width="414"  align=right  bgcolor=#A5AEDE valign=bottom>
		    			<table style="background:#cbe0f9"  height=25 cellSpacing=0 cellPadding=0 width=100%  border=0>
		    				<tr>
								<td width="%">&nbsp;</td>
								<td width="%">&nbsp;</td>
								<!--  <td width="40"><a href="./jsp/global/public/main.jsp"  target="center"><img src="resources/image/house.gif" alt="主页"></a></td>
								<td width="40"><a href="#" onClick="parent.center.location.reload();"><img src="resources/image/reflash.gif" alt="刷新"></a></td>								
								<td width="40"><a href="#" onClick="window.history.back(-1)" ><img src="resources/image/back.gif" alt="后退"></a></td>
								<td width="40"><a href="#" onClick="printContent();"><img src="resources/image/print.gif" alt="打印"></a></td>
								<td width="40"><a href="#" onClick="parent.center.location='passwordModifyInit.action'"><img src="resources/image/key.gif" alt="密码修改" /></a></td>
								-->
								<!-- 
								<td width="40"></td>
							</tr> 
					    </table>
					  </td> 
					</tr>
			  </table>

   	</td></tr>
 	 </table>
-->

  

  
</body>

