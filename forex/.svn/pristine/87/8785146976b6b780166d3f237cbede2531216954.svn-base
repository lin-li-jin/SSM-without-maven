<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib uri="extremecomponents" prefix="ec"%>
<%@ taglib uri="webwork" prefix="ww"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>未处理的交易</title>
	<style type="text/css">
		#all{ margin: 0; padding:0; width: 1003px; height: auto;}
		.colorSet{color: red; font-size: 16px;font-weight: bolder;}
		#top{margin: 0; padding:3px 5px; height: 24px; width:840px; line-height: 24px; background-image: url("images/b.png"); color: white;}
		#center{margin: 0; padding:0; height: 320px; width:850px; font-size: 14px;margin-top: 10px; overflow-y:auto; }
		.container{margin: 0; padding: 0; height: 140px; width: 100%;  margin-bottom: 15px;}
		.modules{margin: 0; padding: 0; height:125px; width:248px;float: left; margin-right: 40px;font-size: 14px; }
		.modulesTopRed{margin: 0; padding: 0; height:25px;width: 242px; background-color: #ce3231; float: left; margin-bottom: 2px;}
		.modulesTopBlue{margin: 0; padding: 0; height:25px;width: 242px; background-image: url("images/b.png"); float: left;margin-bottom: 2px;}
		.red{margin: 0; padding: 0; height: 100px;width:119px !important; width:121px;background-color:#ffdbe7 ;float: left;
		 border: 1px #CC3333 solid; text-align: center; padding-top: 10px;cursor:pointer}
		.blue{margin: 0; padding: 0; height: 100px;width:119px !important;width:121px;background-color: #dee7ff; float:left; 
		border: 1px #428BC2 solid; 
		text-align: center;padding-top: 10px;cursor:pointer}
		.modules span { line-height: 25px; font-size: 16px; color: white; margin-left: 10px; font-weight: bolder;}
		.modules label {  font-size: 16px; color: black; font-weight: bolder;cursor:pointer}
		#ectable{margin: 0; padding: 0; width: 860px; height: auto; }
	</style>
	<link rel="stylesheet" href="resources/css/extremecomponents.css"	type="text/css" />
	<script src="resources/ponyjs/jquery.js" type="text/javascript"></script>
	<script language="JavaScript" src="jsp/local/tradeModule/home/js/homepage.js"></script>
	
	<script type="text/javascript" language="javascript">
		$(function(){
			$('input:checkbox').click(function () {
					this.blur();   
					this.focus(); 
			});
		});
		
	</script>
  </head>
  
  <body>
  <div id="all">
  	 <div id="top">
  	 	<label>未处理的交易</label>
    </div>
    <div id="ectable">
    	<form action="tradeMessageQuery.action" method="post" style="margin:0;">
	    	<label style="margin-right: 50px; font-weight: bolder;">交易记录</label>
	    	交易类型:<select name="tradeType">
		    	<option value="0" <ww:if test="#request.tradeType == 0">selected</ww:if>>即期询价交易</option>
		    	<option value="1" <ww:if test="#request.tradeType == 1">selected</ww:if>>远期询价交易</option>
		    	<option value="2" <ww:if test="#request.tradeType == 2">selected</ww:if>>掉期询价交易</option>
	    	</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    	<input id="button" type="submit" value="搜  索" />
	    	<br>
	    </form>
   		<ec:table items="collection" var="c" action="tradeMessageQuery.action"
				border="" cellpadding="0" cellspacing="0" style="eXtremeTable" >
				<ec:row>
					<ec:column property="tranNo" title="交易序号" width="100"
						headerStyle="text-align:center" style="text-align:left" viewsAllowed="html" >
					</ec:column>
					<ww:if test="c.tranType=='C'">
					<ec:column property="tranType" title="交易类型" width="80"
						headerStyle="text-align:center" style="text-align:left"/>
					</ww:if>
					<ec:column property="anaCcy" title="买入货币" width="100"
						headerStyle="text-align:center" style="text-align:left"/>
					<ec:column property="weCcy" title="卖出货币" width="100"
						headerStyle="text-align:center" style="text-align:left"/>
					<ec:column property="provider" title="交易对手方" width="105"
						headerStyle="text-align:center" style="text-align:left"/>
					<ec:column property="statue" title="交易状态" width="80"
						headerStyle="text-align:center" style="text-align:left"/>
					<ec:column property="oper" title="操作" headerStyle="text-align:center" 
						style="text-align:left" width="50" viewsAllowed="html" sortable="false">
						<script type="text/javascript">
					     document.write('<a href="tradeMessageDetail.action?tradeType='+'${tradeType }'+'&tranNo='+'${c.tranNo }'+'&provider='+'${c.provider }'+'">查看</a>');
						 </script>
					 </ec:column>
			</ec:row>
		</ec:table>	
    </div>
  </div>
  </body>
</html>
