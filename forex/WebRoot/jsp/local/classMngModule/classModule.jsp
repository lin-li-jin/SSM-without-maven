<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ page contentType="text/html;charset=gbk" %>
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
	<title>新增班级页面</title>
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
            //对查询班级的表单添加校验规则
            $("#searchClassForm").validate({
                rules:{
                    searchClassNo:{
                        //required:true,
                        englishAndNumber:true,
                    }
                },
                messages:{
                    searchClassNo:{
                        //required:"班级号不能为空!",
                        englishAndNumber:"班级号只能由字母或数字组成",
                    }
                },
                errorElement:"label", //用来创建错误提示信息标签
                success: function(label) { //验证成功后的执行的回调函数
                    //label指向上面那个错误提示信息标签label
                    label.text(" ") //清空错误提示消息
                        .addClass("success"); //加上自定义的success类
                },
                errorPlacement:function(error, element) { //错误信息位置设置方法
                    error.appendTo( element.parent() ); //这里的element是录入数据的对象
                },
            });

            //对添加班级的表单添加校验规则
            $("#addClassForm").validate({
                rules:{
                    classNo:{
                        required:true,
                        minlength:2,
                        englishAndNumber:true,
                    },
                    className:{
                        required:true,
                        minlength:2,
                    }
                },
                messages:{
                    classNo:{
                        required:"班级号不能为空!",
                        minlength:"班级号不得少于2个字符!",
                        englishAndNumber:"班级号只能由字母或数字组成",
                    },
                    className:{
                        required:"班级名称不能为空!",
                        minlength:"班级名称不得少于2个字符!",
                    }
                },
                errorElement:"label", //用来创建错误提示信息标签
                success: function(label) { //验证成功后的执行的回调函数
                    //label指向上面那个错误提示信息标签label
                    label.text(" ") //清空错误提示消息
                        .addClass("success"); //加上自定义的success类
                },
                errorPlacement:function(error, element) { //错误信息位置设置方法
                    error.appendTo( element.parent() ); //这里的element是录入数据的对象
                },
            });

            //展示班级列表的数据表格
            //将普通表单table变为datagrid样式
            $("#showClassTable").datagrid({
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
                            row : {operation:"<a href=\"/forex/jsp/local/classMngModule/classInfo.jsp?tClass.classNo="+row.classNo+"\">查看班级信息</a>"}
                        });
                    }
                },
				/* loadFilter : function(data){
				 for(var i=0;i<data.rows.length;i++){
				 var row = data.rows[i];
				 data.rows[i] = data.rows[i]+"\"operation\":\"查看班级信息\"";
				 }
				 return data;
				 }, */
                //pageList: [30,50,100],
                url : "/forex/queryClass.action",
                idField : 'classId',
                columns : [[
                    {
                        field : 'classId',
                        title : 'ID',
                        width : 100,
                        align : 'center'
                    },{
                        field : 'classNo',
                        title : '班级号',
                        width : 200,
                        align : 'center'
                    },{
                        field : 'className',
                        title : '班级名称',
                        width : 200,
                        align : 'center'
                    },{
                        field : 'operation',
                        title : '操作',
                        width : 170,
                        align : 'center'
                    }
                ]]
            });

            //点击查询按钮
            $("#search").click(function(){
                //校验表单
                var v = $("#searchClassForm").valid();
                if(v){
                    //获取输入的班级号和班级名称,作为条件调用datagrid数据表格的reload方法重新加载数据
                    var classNo = $("#searchClassNo").val();
                    //var className = $("#searchClassName").val();
                    //value = {"tClass.classNo":classNo,"tClass.className":className};
                    value = {"tClass.classNo":classNo};
                    $("#showClassTable").datagrid("reload",value);
                }
            });

            //点击添加按钮
            //{"result":"success","msg":"success","dataList":[{"message":"添加数据成功","success":true}]}
            //{"result":"fail","msg":"已存在该账号记录，请检查后再次添加","dataList":"null"}
            $("#add").click(function(){
                //校验表单
                var v = $("#addClassForm").valid();

                if(v){
                    //获取输入的班级号和班级名称
                    var classNo = $("#classNo").val();
                    var className = $("#className").val();
                    //表单校验通过
                    $.ajax({
                        url:"/forex/insertClass.action",
                        data:{"tClass.classNo":classNo,"tClass.className":className},
                        dataType:"json",
                        type:"get",
                        async:true,
                        success:function(data){
                            if("success"==(data.result)){
                                console.log(data.dataList[0].message);
                                $.messager.alert(data.dataList[0].message,"info");
                                //调用datagrid数据表格的reload方法重新加载数据,参数为null则会自动使用上次查询的参数查询
                                $("#showClassTable").datagrid("reload",null);
                            }else if("fail"==(data.result)){
                                $.messager.alert("提示","已存在该账号记录，请检查后再次添加","warning");
                            }
                        }

                    });
                }
            });


        });
	</script>

</head>
<body class="easyui-layout">
<div data-options="region:'north'" style="width:1024px;height:150px;">
	<form action="" id="searchClassForm" method="post">
		<br>
		&nbsp;班级号:<input id="searchClassNo" name="searchClassNo" type="text" placeholder="请输入...">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<!-- &nbsp;班级名称:<input id="searchClassName" name="searchClassName" type="text" placeholder="请输入...">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
		<input type="button" value="查询" id="search">
	</form>
	&nbsp;————————————————————————————————————————————————————————————————————————————————————————————————————<br><br>
	<form action="" id="addClassForm" method="post">
		<br>
		&nbsp;班级号:<input id="classNo" name="classNo" type="text" placeholder="请输入...">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;班级名称:<input id="className" name="className" type="text" placeholder="请输入...">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="添加" id="add">
	</form>
</div>
<div data-options="region:'center'" style="width:1024px;height:500px;">
	<table id="showClassTable"></table>
</div>
</body>
</html>
