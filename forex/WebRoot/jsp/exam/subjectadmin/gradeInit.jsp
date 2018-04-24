<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ page contentType="text/html;charset=gbk" %>

<html>
<head>
	<title>成绩计算页面</title>
	 <meta http-equiv="content-type" content="text/html;charset=gbk" />
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

            //展示成绩列表的数据表格
            //将普通表单table变为datagrid样式
            $("#showGradeTable").datagrid({
                fit : true,
                border : true,
                rownumbers : true,
                striped : true,
                singleSelect : true,
                pagination : true,
                onLoadSuccess : function(data){//数据加载成功后,遍历每一条数据,加上查看班级信息
                    for(var i=0;i<data.rows.length;i++){
                        var row = data.rows[i];
                        $("#showClassTable").datagrid('updateRow',{
                            index : i,
                            row : {operation:"<a href=\"/forex/jsp/local/classMngModule/classInfo.jsp?tClass.classNo="+row.classNo+"\">计算成绩</a>"}
                        });
                    }
                },
               // url : "/forex/queryGrade.action",
                columns : [[
                    {
                        field : 'classId',
                        title : '试卷名称',
                        width : 200,
                        align : 'center'
                    },{
                        field : 'classNo',
                        title : '分配班级',
                        width : 200,
                        align : 'center'
                    },{
                        field : 'operation',
                        title : '操作',
                        width : 200,
                        align : 'center'
                    }
                ]]
            });

        });
	</script>

</head>
<body class="easyui-layout">
<div data-options="region:'north'" style="width:1024px;height:80px;">
	<center><h2>考试列表</h2></center>
</div>
<div data-options="region:'center'" style="width:1024px;height:500px;">
	<table id="showGradeTable"></table>
</div>
</body>
</html>
