<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ page contentType="text/html;charset=gbk" %> 
<%@ taglib prefix="ww" uri="webwork" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>实训知识点维护页面</title>
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
    	//删除题目
		function del(examNo){
			var flag = confirm("确定删除该题目吗？");
			if(flag){
				$.ajax({
					type:"get",
					url:"/forex/deleteSubject.action",
					data:{"examId":examNo},
					dataType:"json",
					success:function(examResult){
						var message = eval(examResult);
						if(message[0].error == "删除成功"){
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
                        $(".examContentTable  tr:not(:first)").empty("");//清空之前的查询数据
                        $.each(item[0].message, function(i, result) {
                            if(result['accType'] == "C"){
                            	result['accType'] = "人民币交易";
                            }else if(result['accType'] == "W"){
                            	result['accType'] = "外币对交易";
                            }else{
                            	result['accType'] = "保证金交易";
                            }  
                            item = "<tr><td align='center'>" + result['examNo'] + "</td><td align='center'>" 
                            + result['accType'] + "</td><td>" + result['examContent'] +"</td><td align='center'>"
                            +"<a href=''>编辑</a>"+"</td><td align='center'>"+"<a href='#' onclick='del("+result['examNo']+")'>删除</a>"+"</td></tr>";  
                            $('.examContentTable').append(item);  
						});
					}
				});
			});
			
			//转到添加题目的页面
			$("#add").click(function(){
				var accType = $("#accType option:selected").val();
				if(accType == "select"){
					alert("请选择交易类型");
				}else{
					var accDetail = $("#accDetail option:selected").text();
					if(accDetail == "一口价交易"){
						accDetail = "oneclick";
					}else if(accDetail == "stop loss交易"){
						accDetail = "stoploss";
					}else if(accDetail == "take profit交易"){
						accDetail = "takeprofit";
					}else if(accDetail == "oco交易"){
						accDetail = "oco";
					}else if(accDetail == "market breakout交易"){
						accDetail = "marketbreakout";
					}else if(accDetail == "spot交易"){
						accDetail = "spot";
					}else if(accDetail == "forward交易"){
						accDetail = "forward";
					}else if(accDetail == "swap交易"){
						accDetail = "swap";
					}else if(accDetail == "保证金即期交易"){
						accDetail = "marginspot";
					}else if(accDetail == "保证金远期交易"){
						accDetail = "marginforward";
					}else{
						accDetail = "marginoption";
					}
					window.location.href = "/forex/addExamPage.action?accType="+accType+"&accDetail="+accDetail;
				}
			});
			
			
			var C_detail = ["一口价交易", "stop loss交易", "take profit交易","oco交易","market breakout交易","spot交易","forward交易","swap交易"];              
            var W_detail = ["一口价交易", "stop loss交易", "take profit交易","oco交易","market breakout交易","spot交易","forward交易","swap交易"];  
            var B_detail = ["保证金即期交易", "保证金远期交易","保证金期权交易"];  
              
            $("select[name='accType']").change(function() {  
                //被选中的option  
                var selected_value = $(this).val();  
                  
                if(selected_value == "select"){  
                    $("select[name='accDetail']").empty();  
                      
                }else if(selected_value == "C"){ //人民币交易
                    $("select[name='accDetail']").empty();  
                    //循环添加  
                    for(var i = 0; i < C_detail.length; i++) {  
                        var option = $("<option>").val(C_detail[i]).text(C_detail[i]);  
                        $("select[name='accDetail']").append(option);  
                    }  
                      
                }else if(selected_value == "W"){ //外币对交易
                    $("select[name='accDetail']").empty();  
                    //循环添加  
                    for(var i = 0; i < W_detail.length; i++) {  
                        var option = $("<option>").val(W_detail[i]).text(W_detail[i]);  
                        $("select[name='accDetail']").append(option);  
                    }  
                      
                }else if(selected_value == "B"){ //保证金交易 
                    $("select[name='accDetail']").empty();  
                    //循环添加  
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
				交易类型：<select name="AccType" id="subjectType">
					<option value="0">全部</option>
					<option value="1">人民币交易</option>
					<option value="2">外币对交易</option>
					<option value="3">保证金交易</option>
				</select>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="查询题目" id="query">
			</form>
	  		<br><hr><br>
			<form id="addExamContentForm" method="post">
			    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				交易类型：<select name="accType" id="accType">
					<option value="select">请选择...</option>
					<option value="C">人民币交易</option>
					<option value="W">外币对交易</option>
					<option value="B">保证金交易</option>
				</select>&nbsp;&nbsp;&nbsp;&nbsp;
				具体交易：<select name="accDetail" id="accDetail"></select>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="添加题目" id="add">
			</form>
  		</div>
		<div data-options="region:'center'" style="width:1024px;height:500px;">
			<table class="examContentTable" >
				<thead>
					<tr>
						<th width="80" align="center">题号</th>
						<th width="100" align="center">交易类型</th>
						<th width="180" align="center">题目内容</th>
						<th width="60" align="center">编辑</th>
						<th width="60" align="center">删除</th>
					</tr>
				</thead>
			</table>
		</div>
  </body>
</html>
