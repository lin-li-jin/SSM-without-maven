<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生列表</title>
</head>
<script>
//打开新增学生窗口
function openAddWindow(){
	window.location.href = "toAddStudent.action";
}
//打开修改窗口
function openModifyWindow(id){
	window.location.href = "toModifyStudent.action?id=" + id;
}

//删除学生信息
function delStudent(id){
	if(window.confirm("您确定要删除吗？")){
		window.location.href = 'removeStudent.action?id=' + id;
	}
}
</script>
<body>
<strong>学生信息管理:</strong><a href="#" style="margin-left: 20px;" onclick="openAddWindow()">新增学生</a><br>
<!-- 显示学生列表的表格 -->
<table align="left" border="1" width="100%" style="margin-top: 10px">
	<tr>
		<th width="5%">序号</th>
		<th width="15%">学生姓名</th>
		<th width="10%">性别</th>
		<th width="10%">年龄</th>
		<th >家庭住址</th>
		<th width="10%">所属班级</th>
		<th width="15%">操作</th>
	</tr>
	<c:forEach var="student" items="${studentList}" varStatus="status">
		<tr align="center">
			<td>${status.index+1}</td>
			<td>${student.name}</td>
			<td>${student.sexDescr}</td>
			<td>${student.age}</td>
			<td>${student.address}</td>
			<td>${student.classNo}</td>
			<td><a href=# onclick="openModifyWindow(${student.id})">修改
			 </a>/<a href="javascript: delStudent('${student.id}')"> 删除</a></td>
		</tr>
	</c:forEach>
</table>
</body>
</html>