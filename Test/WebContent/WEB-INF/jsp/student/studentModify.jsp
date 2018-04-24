<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生修改</title>
</head>
<script>
//修改界面--修改按钮
function modify(){
	var name = document.getElementById("name").value;
	if(null == name || "" == name){
		alert("姓名不能为空！");
		document.getElementById("name").focus();
		return false;
	}
	
	if(window.confirm("您确定要修改吗？")){
		document.getElementById("modifyStudentForm").submit();
	}
}
</script>
<body>
<div style="POSITION:absolute; left:50%; top:50%; width:600px; height:200px; margin-left:-300px; 
	margin-top:-200px; background-color:#AEEEEE; text-align:center">
	<div><strong>修改学生信息</strong></div>
	<form action="modifyStudent.action" id="modifyStudentForm" method="post">
		<input type="hidden" name="id" id="modifyID" value="${student.id}">
		<table width="100%">
			<tr>
				<td style="text-align: right;width: 40%">姓	名:</td>
				<td style="text-align: left;">
					<input type="text" name="name" id="name" maxlength="32" value="${student.name }">
				</td>
			</tr>
			<tr>
				<td style="text-align: right;width: 40%">性	别:</td>
				<td style="text-align: left;">
					<input type="radio" name="sex" id="sex1" value="1" <c:if test="${student.sex eq '1' }">checked</c:if> >男
					<input type="radio" name="sex" id="sex2" value="2" <c:if test="${student.sex eq '2' }">checked</c:if>>女
				</td>
			</tr>
			<tr>
				<td style="text-align: right;width: 40%">年	龄:</td>
				<td style="text-align: left;"><input type="text" name="age" id="age" maxlength="8" value="${student.age }"></td>
			</tr>
			<tr>
				<td style="text-align: right;width: 40%">家庭住址:</td>
				<td style="text-align: left;">
					<input type="text" name="address" id="address" maxlength="256" value="${student.address }">
				</td>
			</tr>
			<tr>
				<td style="text-align: right;width: 40%">所属班级:</td>
				<td style="text-align: left;">
					<input type="text" name="classNo" id="classNo" maxlength="8" value="${student.classNo }">
				</td>
			</tr>
			<tr>
				<td style="text-align: center;" colspan="2">
					<input type="button" onclick="modify()" value="修改">
					<input type="button" onclick="javascript: history.back(-1);" value="取消">
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>