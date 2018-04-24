<%--
 * Amendment No.: FOEXAS009
 * Create By    : atggdsaiDONG 
 * Description  : 交易首页
 * Modify Date  : 2014-07-22
 --%>

<%@ taglib prefix="ww" uri="webwork"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title> </title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
		#all{margin: 0; padding: 0; width: 800px; height: auto;}
		.colorSet{color: red; font-size: 16px;font-weight: bolder;}
		#top{margin: 0; padding: 3px 5px; height: 24px; width:790px; line-height: 24px; 
		background-image: url("images/b.png"); color: white;}
		#center{margin: 0; padding:0; height: 260px; width:100%; font-size: 14px;margin-top: 15px; }
		.modules{margin: 0; padding: 15px 0px 0 35px; height:230px !important; width:190px !important;height:245px; width:225px;  float: left; margin-right: 55px;font-size: 14px; 
		background-color: #E5F2F8; border: 1px solid red;}
		.message{margin: 0; padding: 0; float: right;font-size: 16px;}
	</style>
	<script type="text/javascript">
		function openTradeMessagePage(){
			window.location.href = "tradeMessageInit.action";
		}
	</script>
  </head>
  
  <body>
  <div id="all">
	  <div id="top">
			当前日期：             	
			<script language="JavaScript" type="text/javascript">
	                          
	                                var enabled = 0;  
	                                var today = new Date();
	                                var day;
	                                var date;
	                                var centry ;
	                                var year;
	                                if(today.getDay()==0)      day = "星期日"
	                                if(today.getDay()==1)      day = "星期一"
	                                if(today.getDay()==2)      day = "星期二"
	                                if(today.getDay()==3)      day = "星期三"
	                                if(today.getDay()==4)      day = "星期四"
	                                if(today.getDay()==5)      day = "星期五"
	                                if(today.getDay()==6)      day = "星期六"
	                                document.fgColor = " 000000";
	                                centry="";
	                                if   (today.getYear()<2000 )   year=today.getYear()+1900;   // centry = "1900" ;
	                                else year=today.getYear()
	                                date1 = year   + "年" + (today.getMonth() + 1 ) + "月" + today.getDate() + "日   " ;
	                                date2 = "" +   day;
	                                document.write( date1+"&nbsp;"+date2);         
	                     
	        </script><!--日期改变的样式-->
	        <div class="message" onclick="openTradeMessagePage()">
	        	待处理交易(<font color="pink"><ww:property value='#request.message' /></font>)
	        </div>
	    </div><!-- end the topDiv -->
	    
	    <div id="center">
	    	<div class="modules">
	    		人民币对外币交易账号：<br>
	    		<label class="colorSet"><ww:property value="#request.accnoC" /></label><br><br>
				人民币对外币交易账户余额：<br>
				<ww:iterator value="#request.accInfoListC">
				  <ww:property value="ccy"/>:<ww:property value="amount"/><br>
				</ww:iterator>
	    	</div>
	    	
	    	<div class="modules">
				外币对交易账号：<br>
				<label class="colorSet"><ww:property value="#request.accnoW" /></label><br><br>
				外币对交易账户余额：<br>
				<ww:iterator value="#request.accInfoListW">
				  <ww:property value="ccy"/>:<ww:property value="amount"/><br>
				</ww:iterator>
	    	</div>
	    	
	 	    <div class="modules" style="margin-right: 0;">
				保证金交易账号：<br>
				<label class="colorSet"><ww:property value="#request.accnoB" /></label><br><br>
				保证金交易账户余额：<br>
				<ww:iterator value="#request.accInfoListB">
				  <ww:property value="ccy"/>:<ww:property value="amount"/><br>
				</ww:iterator>
	    	</div>
	    </div><!-- end the centerDiv -->
	    <div>
	    	市场资讯:<br>
	    	<SELECT id="u5" size="5" class="u5"  multiple style="width: 800px;">
				<OPTION  value="14:40 [快讯] 德国商业银行：美元兑日元仍然有望测试100.75的下方低位">14:40 [快讯] 德国商业银行：美元兑日元仍然有望测试100.75的下方低位</OPTION>
				<OPTION  value="14:34 [快讯] 报道：卢甘斯克民兵组织宣称，将解除乌克兰政府军在当地驻军的武装">14:34 [快讯] 报道：卢甘斯克民兵组织宣称，将解除乌克兰政府军在当地驻军的武装</OPTION>
				<OPTION  value="14:33 [快讯] 高盛：预期澳洲联储将在9月降息，此前预计时间为7月">14:33 [快讯] 高盛：预期澳洲联储将在9月降息，此前预计时间为7月</OPTION>
				<OPTION  value="14:30 [快讯] 拉詹：政府可能会对经济表达其自己的观点，但同时也会尊重央行的货币政策">14:30 [快讯] 拉詹：政府可能会对经济表达其自己的观点，但同时也会尊重央行的货币政策</OPTION>
				<OPTION  value="14:28 [快讯] 印度央行行长拉詹：政府和央行都强调在目前的情况下需降低通胀，均尊重经济疲软事实">14:28 [快讯] 印度央行行长拉詹：政府和央行都强调在目前的情况下需降低通胀，均尊重经济疲软事实</OPTION>
			</SELECT>
	    </div>
  </div><!-- end the allDiv -->
    
  </body>
</html>
