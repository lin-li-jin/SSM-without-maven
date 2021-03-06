<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %> 
<!-- 
	页面发送数据：当前页数、每页显示行数
	page 1
	rows 10
	
	后台返回:json格式:数据总条数、数据
	{                                                      
	"total":2,	
	"rows":[ 
			{"classId":"001","classNo":"1415451","className":"对外贸易一班"},
			{"classId":"002","classNo":"1415452","className":"对外贸易二班"}
		]
	}
 -->
<html>
  <head>
    <title>班级详细信息</title>
	<script src="/forex/resources/js/jquery-1.8.3.js" type="text/javascript"></script>
	<script src="/forex/resources/js/jquery.validate.js" type="text/javascript"></script>
	<script src="/forex/resources/ponycss/jquery_validate.css" type="text/javascript"></script>
	<script src="/forex/resources/js/jquery.easyui.min.js" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css" href="/forex/resources/css/easyui.css">
    <style type="text/css">
    	label.error{
				padding-left: 5px;
				font-family:georgia;
				font-size: 13px;
				font-style: normal;
				color: red;
			}
    </style>
    
    <script type="text/javascript">
    	$(function(){
    		var param = location.search;//获取班级列表传递的班级id
    		var classNo = param.split("=")[1];//根据"="将字符串分割
    		document.getElementById("classNo").value=classNo;
    		//展示学生列表的数据表格
			//将普通表单table变为datagrid样式
			$("#showStudentTable").datagrid({
				fit : true,
				border : true,
				rownumbers : true,
				striped : true,
				singleSelect : true,
				pagination : true,
				//pageList: [30,50,100],
				url : "/forex/queryClassDetail.action?tClass.classNo="+classNo,
				idField : 'id',
				columns : [[
				{
					field : 'id',
					title : 'ID',
					width : 100,
					align : 'center'
				},{
					field : 'userNum',
					title : '学号',
					width : 200,
					align : 'center'
				},{
					field : 'name',
					title : '姓名',
					width : 200,
					align : 'center'
				}
				]]
			}); 
			
		});
</script>

  </head>
  <body class="easyui-layout">
  		<div data-options="region:'north'" style="width:1024px;height:55px;">
	  			<br>
				&nbsp;&nbsp;班级号:&nbsp;&nbsp;<input type="text" id="classNo" readonly="readonly">&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="返回" id="back" onclick="window.location='/forex/jsp/local/classMngModule/classModule.jsp'">
  		</div>
		<div data-options="region:'center'" style="width:1024px;height:500px;">
			<table id="showStudentTable"></table>
		</div>
  </body>
</html>
