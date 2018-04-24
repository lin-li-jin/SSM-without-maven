<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ page contentType="text/html;charset=gbk" %> 
<%@ taglib prefix="ww" uri="webwork" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>ʵѵ֪ʶ��ά��ҳ��</title>
	<script src="/forex/resources/js/jquery-1.8.3.js" type="text/javascript"></script>
	<script src="/forex/resources/js/jquery.validate.js" type="text/javascript"></script>
	<script src="/forex/resources/ponycss/jquery_validate.css" type="text/javascript"></script>
	<script src="/forex/resources/js/jquery.easyui.min.js" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css" href="/forex/resources/css/easyui.css">
	<style type="text/css">
	table.examContentTable {
		font-family: verdana,arial,sans-serif;
		font-size:11px;
		color:#333333;
		border-width: 1px;
		border-color: #666666;
		border-collapse: collapse;
	}
	table.examContentTable th {
		border-width: 1px;
		padding: 8px;
		border-style: solid;
		border-color: #666666;
		background-color: #dedede;
	}
	table.examContentTable td {
		border-width: 1px;
		padding: 8px;
		border-style: solid;
		border-color: #666666;
		background-color: #ffffff;
	}
	</style>
    
    <script type="text/javascript">
    	//ɾ����Ŀ
		function del(examNo){
			var flag = confirm("ȷ��ɾ������Ŀ��");
			if(flag){
				$.ajax({
					type:"get",
					url:"/forex/deleteSubject.action",
					data:{"examId":examNo},
					dataType:"json",
					success:function(examResult){
						var message = eval(examResult);
						if(message[0].error == "ɾ���ɹ�"){
							alert(message[0].error);
							window.location.reload(true);
						}
					}
				});
			}
		}
			
		$(function(){
			$('body').on('click','#query',function query(){
				var subjectType = $("#subjectType").val();
				$.ajax({
					url:"/forex/queryBySubjectType.action",
					data:{"subjectType":subjectType},  
                    type: "get",
                    dataType:"json",
                    success: function(result){  
                        var item = eval(result);
                        $(".examContentTable  tr:not(:first)").empty("");//���֮ǰ�Ĳ�ѯ����
                        $.each(item[0].message, function(i, result) {
                            if(result['accType'] == "C"){
                            	result['accType'] = "����ҽ���";
                            }else if(result['accType'] == "W"){
                            	result['accType'] = "��ҶԽ���";
                            }else{
                            	result['accType'] = "��֤����";
                            }  
                            item = "<tr><td align='center'>" + result['examNo'] + "</td><td align='center'>" 
                            + result['accType'] + "</td><td>" + result['examContent'] +"</td><td align='center'>"
                            +"<a href=''>�༭</a>"+"</td><td align='center'>"+"<a href='#' onclick='del("+result['examNo']+")'>ɾ��</a>"+"</td></tr>";  
                            $('.examContentTable').append(item);  
						});
					}
				});
			});
			
			//ת�������Ŀ��ҳ��
			$("#add").click(function(){
				var accType = $("#accType option:selected").val();
				if(accType == "select"){
					alert("��ѡ��������");
				}else{
					var accDetail = $("#accDetail option:selected").text();
					if(accDetail == "һ�ڼ۽���"){
						accDetail = "oneclick";
					}else if(accDetail == "stop loss����"){
						accDetail = "stoploss";
					}else if(accDetail == "take profit����"){
						accDetail = "takeprofit";
					}else if(accDetail == "oco����"){
						accDetail = "oco";
					}else if(accDetail == "market breakout����"){
						accDetail = "marketbreakout";
					}else if(accDetail == "spot����"){
						accDetail = "spot";
					}else if(accDetail == "forward����"){
						accDetail = "forward";
					}else if(accDetail == "swap����"){
						accDetail = "swap";
					}else if(accDetail == "��֤���ڽ���"){
						accDetail = "marginspot";
					}else if(accDetail == "��֤��Զ�ڽ���"){
						accDetail = "marginforward";
					}else{
						accDetail = "marginoption";
					}
					window.location.href = "/forex/addExamPage.action?accType="+accType+"&accDetail="+accDetail;
				}
			});
			
			
			var C_detail = ["һ�ڼ۽���", "stop loss����", "take profit����","oco����","market breakout����","spot����","forward����","swap����"];              
            var W_detail = ["һ�ڼ۽���", "stop loss����", "take profit����","oco����","market breakout����","spot����","forward����","swap����"];  
            var B_detail = ["��֤���ڽ���", "��֤��Զ�ڽ���","��֤����Ȩ����"];  
              
            $("select[name='accType']").change(function() {  
                //��ѡ�е�option  
                var selected_value = $(this).val();  
                  
                if(selected_value == "select"){  
                    $("select[name='accDetail']").empty();  
                      
                }else if(selected_value == "C"){ //����ҽ���
                    $("select[name='accDetail']").empty();  
                    //ѭ�����  
                    for(var i = 0; i < C_detail.length; i++) {  
                        var option = $("<option>").val(C_detail[i]).text(C_detail[i]);  
                        $("select[name='accDetail']").append(option);  
                    }  
                      
                }else if(selected_value == "W"){ //��ҶԽ���
                    $("select[name='accDetail']").empty();  
                    //ѭ�����  
                    for(var i = 0; i < W_detail.length; i++) {  
                        var option = $("<option>").val(W_detail[i]).text(W_detail[i]);  
                        $("select[name='accDetail']").append(option);  
                    }  
                      
                }else if(selected_value == "B"){ //��֤���� 
                    $("select[name='accDetail']").empty();  
                    //ѭ�����  
                    for(var i = 0; i < B_detail.length; i++) {  
                        var option = $("<option>").val(B_detail[i]).text(B_detail[i]);  
                        $("select[name='accDetail']").append(option);  
                    }  
                }  
            }); 
			
		});
		
</script>

  </head>
  <body class="easyui-layout">
  		<div  data-options="region:'north'" style="float:left;width:512px;height:150px;">
	  		<form id="searchExamContentForm" method="post">
	  			<br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				�������ͣ�<select name="AccType" id="subjectType">
					<option value="0">ȫ��</option>
					<option value="1">����ҽ���</option>
					<option value="2">��ҶԽ���</option>
					<option value="3">��֤����</option>
				</select>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="��ѯ��Ŀ" id="query">
			</form>
	  		<br><hr><br>
			<form id="addExamContentForm" method="post">
			    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				�������ͣ�<select name="accType" id="accType">
					<option value="select">��ѡ��...</option>
					<option value="C">����ҽ���</option>
					<option value="W">��ҶԽ���</option>
					<option value="B">��֤����</option>
				</select>&nbsp;&nbsp;&nbsp;&nbsp;
				���彻�ף�<select name="accDetail" id="accDetail"></select>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="�����Ŀ" id="add">
			</form>
  		</div>
		<div data-options="region:'center'" style="width:1024px;height:500px;">
			<table class="examContentTable" >
				<thead>
					<tr>
						<th width="80" align="center">���</th>
						<th width="100" align="center">��������</th>
						<th width="180" align="center">��Ŀ����</th>
						<th width="60" align="center">�༭</th>
						<th width="60" align="center">ɾ��</th>
					</tr>
				</thead>
			</table>
		</div>
  </body>
</html>
