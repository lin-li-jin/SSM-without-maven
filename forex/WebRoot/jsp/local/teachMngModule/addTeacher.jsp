<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ page contentType="text/html;charset=gbk" %>
<%@ taglib prefix="ww" uri="webwork" %>
<!--
页面发送数据：当前页数、每页显示行数
page 1
rows 10

后台返回:json格式:数据总条数、数据
{
"total":3,
"rows":[
{"id":001,"userNum":"1415451","name":"张教师"},
{"id":002,"userNum":"1415452","name":"王教师"},
{"id":003,"userNum":"1415453","name":"李教师"}
]
}
-->
<html>
<head>
	<title>新增教师页面</title>
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
            //对查询教师的表单添加校验规则
            $("#searchTeacherForm").validate({
                rules:{
                    searchUserNum:{
                        //required:true,
                        //minlength:2,
                        englishAndNumber:true,
                    }
                },
                messages:{
                    searchUserNum:{
                        //required:"教工号不能为空!",
                        //minlength:"教工号不得少于2个字符!",
                        englishAndNumber:"教工号只能由字母或数字组成",
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

            //对添加教师的表单添加校验规则
            $("#addTeacherForm").validate({
                rules:{
                    userNum:{
                        required:true,
                        minlength:2,
                        englishAndNumber:true,
                    },
                    name:{
                        required:true,
                        minlength:2
                    },
                    password:{
                        required:true,
                        minlength:4,
                        englishAndNumber:true,
                    }
                },
                messages:{
                    userNum:{
                        required:"教工号不能为空!",
                        minlength:"教工号不得少于2个字符!",
                        englishAndNumber:"教工号只能由字母或数字组成",
                    },
                    name:{
                        required:"姓名不能为空!",
                        minlength:"姓名长度不得低于2位!"
                    },
                    password:{
                        required:"密码不能为空!",
                        minlength:"密码不得少于4个字符!",
                        englishAndNumber:"密码只能由字母或数字组成",
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

            //展示教师列表的数据表格
            //将普通表单table变为datagrid样式
            $("#showTeacherTable").datagrid({
                fit : true,
                border : true,
                rownumbers : true,
                striped : true,//隔行变色
                pagination : true,
                //pageList: [30,50,100],
                url : "/forex/pageQueryTeacher.action",
                idField : 'id',
                columns : [[
                    {
                        field : 'id',
                        title : 'ID',
                        width : 100,
                        align : 'center'
                    },{
                        field : 'userNum',
                        title : '教工号',
                        width : 140,
                        align : 'center'
                    },{
                        field : 'name',
                        title : '姓名',
                        width : 120,
                        align : 'center'
                    }
                ]]
            });

            //点击查询按钮
            $("#search").click(function(){
                //校验表单
                var v = $("#searchTeacherForm").valid();
                if(v){
                    //获取输入的教工号，作为条件调用datagrid数据表格的reload方法重新加载数据
                    var value = $("#searchUserNum").val();
                    value = {"userNum":value};
                    $("#showTeacherTable").datagrid("reload",value);
                }
            });

            //点击添加按钮
            //{"result":"success","msg":"success","dataList":[{"message":"添加数据成功","success":true}]}
            //{"result":"fail","msg":"已存在该账号记录，请检查后再次添加","dataList":"null"}
            $("#add").click(function(){
                //校验表单
                var v = $("#addTeacherForm").valid();

                if(v){
                    //获取输入的教工号、姓名、密码
                    var userNum = $("#userNum").val();
                    var name = $("#name").val();
                    var password = $("#password").val();
                    //表单校验通过
                    $.ajax({
                        url:"/forex/insertTeacher.action",
                        data:{"users.userNum":userNum,"users.name":name,"users.password":password},
                        dataType:"json",
                        type:"get",
                        async:true,
                        success:function(data){
                            if("success"==(data.result)){
                                $.messager.alert("提示","添加数据成功!","info");
                                //调用datagrid数据表格的reload方法重新加载数据,参数为null则会自动使用上次查询的参数查询
                                $("#showTeacherTable").datagrid("reload",null);
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
	<form action="/forex/pageQueryTeacher.action" id="searchTeacherForm" method="post">
		<br>
		&nbsp;&nbsp;&nbsp;教工号:<input id="searchUserNum" name="searchUserNum" type="text" placeholder="请输入...">&nbsp;&nbsp;&nbsp;
		<input type="button" value="查询" id="search">
	</form>
	&nbsp;————————————————————————————————————————————————————————————————————————————————————————————————————<br><br>
	<form action="/forex/insertTeacher.action" id="addTeacherForm" method="post">
		&nbsp;&nbsp;&nbsp;教工号:<input id="userNum" name="userNum" type="text" placeholder="请输入...">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;  姓名:<input id="name" name="name" type="text" placeholder="请输入...">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;  密码:<input id="password" name="password" type="password" placeholder="请输入...">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="添加" id="add">
	</form>
	&nbsp;————————————————————————————————————————————————————————————————————————————————————————————————————<br>
</div>
<div data-options="region:'center'" style="width:1024px;height:500px;">
	<table id="showTeacherTable"></table>
</div>
</body>
</html>
