<%--
 * Amendment No.: FOEXAS009
 * Create By    : atggdsaiDONG 
 * Description  : ������ҳ
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
			��ǰ���ڣ�             	
			<script language="JavaScript" type="text/javascript">
	                          
	                                var enabled = 0;  
	                                var today = new Date();
	                                var day;
	                                var date;
	                                var centry ;
	                                var year;
	                                if(today.getDay()==0)      day = "������"
	                                if(today.getDay()==1)      day = "����һ"
	                                if(today.getDay()==2)      day = "���ڶ�"
	                                if(today.getDay()==3)      day = "������"
	                                if(today.getDay()==4)      day = "������"
	                                if(today.getDay()==5)      day = "������"
	                                if(today.getDay()==6)      day = "������"
	                                document.fgColor = " 000000";
	                                centry="";
	                                if   (today.getYear()<2000 )   year=today.getYear()+1900;   // centry = "1900" ;
	                                else year=today.getYear()
	                                date1 = year   + "��" + (today.getMonth() + 1 ) + "��" + today.getDate() + "��   " ;
	                                date2 = "" +   day;
	                                document.write( date1+"&nbsp;"+date2);         
	                     
	        </script><!--���ڸı����ʽ-->
	        <div class="message" onclick="openTradeMessagePage()">
	        	��������(<font color="pink"><ww:property value='#request.message' /></font>)
	        </div>
	    </div><!-- end the topDiv -->
	    
	    <div id="center">
	    	<div class="modules">
	    		����Ҷ���ҽ����˺ţ�<br>
	    		<label class="colorSet"><ww:property value="#request.accnoC" /></label><br><br>
				����Ҷ���ҽ����˻���<br>
				<ww:iterator value="#request.accInfoListC">
				  <ww:property value="ccy"/>:<ww:property value="amount"/><br>
				</ww:iterator>
	    	</div>
	    	
	    	<div class="modules">
				��ҶԽ����˺ţ�<br>
				<label class="colorSet"><ww:property value="#request.accnoW" /></label><br><br>
				��ҶԽ����˻���<br>
				<ww:iterator value="#request.accInfoListW">
				  <ww:property value="ccy"/>:<ww:property value="amount"/><br>
				</ww:iterator>
	    	</div>
	    	
	 	    <div class="modules" style="margin-right: 0;">
				��֤�����˺ţ�<br>
				<label class="colorSet"><ww:property value="#request.accnoB" /></label><br><br>
				��֤�����˻���<br>
				<ww:iterator value="#request.accInfoListB">
				  <ww:property value="ccy"/>:<ww:property value="amount"/><br>
				</ww:iterator>
	    	</div>
	    </div><!-- end the centerDiv -->
	    <div>
	    	�г���Ѷ:<br>
	    	<SELECT id="u5" size="5" class="u5"  multiple style="width: 800px;">
				<OPTION  value="14:40 [��Ѷ] �¹���ҵ���У���Ԫ����Ԫ��Ȼ��������100.75���·���λ">14:40 [��Ѷ] �¹���ҵ���У���Ԫ����Ԫ��Ȼ��������100.75���·���λ</OPTION>
				<OPTION  value="14:34 [��Ѷ] ������¬��˹�������֯���ƣ�������ڿ����������ڵ���פ������װ">14:34 [��Ѷ] ������¬��˹�������֯���ƣ�������ڿ����������ڵ���פ������װ</OPTION>
				<OPTION  value="14:33 [��Ѷ] ��ʢ��Ԥ�ڰ�����������9�½�Ϣ����ǰԤ��ʱ��Ϊ7��">14:33 [��Ѷ] ��ʢ��Ԥ�ڰ�����������9�½�Ϣ����ǰԤ��ʱ��Ϊ7��</OPTION>
				<OPTION  value="14:30 [��Ѷ] ��ղ���������ܻ�Ծ��ñ�����Լ��Ĺ۵㣬��ͬʱҲ���������еĻ�������">14:30 [��Ѷ] ��ղ���������ܻ�Ծ��ñ�����Լ��Ĺ۵㣬��ͬʱҲ���������еĻ�������</OPTION>
				<OPTION  value="14:28 [��Ѷ] ӡ�������г���ղ�����������ж�ǿ����Ŀǰ��������轵��ͨ�ͣ������ؾ���ƣ����ʵ">14:28 [��Ѷ] ӡ�������г���ղ�����������ж�ǿ����Ŀǰ��������轵��ͨ�ͣ������ؾ���ƣ����ʵ</OPTION>
			</SELECT>
	    </div>
  </div><!-- end the allDiv -->
    
  </body>
</html>
