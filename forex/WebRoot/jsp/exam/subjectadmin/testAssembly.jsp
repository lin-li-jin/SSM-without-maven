<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ page contentType="text/html;charset=gbk" %> 
<%@ taglib prefix="ww" uri="webwork" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>组卷</title>
	<script src="/forex/resources/js/jquery-1.8.3.js" type="text/javascript"></script>
	<script src="/forex/resources/js/jquery.validate.js" type="text/javascript"></script>
	<script src="/forex/resources/ponycss/jquery_validate.css" type="text/javascript"></script>
	<script src="/forex/resources/js/jquery.easyui.min.js" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css" href="/forex/resources/css/easyui.css">
	
	<script type="text/javascript">
		function haha(obj,v) {
		    if ($(obj).prop("checked")) {
		        //修改所有以item+v开头的不可编辑状态
		        $("input[name^='item"+v+"']").removeAttr("readonly")
		    } else {
		        $("input[name^='item"+v+"']").attr("readonly","readonly")
		    }
		
		}
		
		$(function(){
			$("#form1").validate({
		        rules: {
		        		item1_cNumber: {
		                        required: true,
		                        digits:true
		                }
		                
		        },
		        messages: {
		        		item1_cNumber: {
		                    required: "题目数不能为空!",
		                    digits:"请输入整数"
		                }    
		        },
		        errorElement: "label", //用来创建错误提示信息标签
		        success: function (label) { //验证成功后的执行的回调函数
		            //label指向上面那个错误提示信息标签label
		            label.text(" ") //清空错误提示消息
		                .addClass("success"); //加上自定义的success类
		        },
		        errorPlacement: function (error, element) { //错误信息位置设置方法
		            error.addClass("error")
		            error.appendTo(element.parent()); //这里的element是录入数据的对象
		        },
		    });
		
		    $("#item1_cNumber").blur(function(){
		    	$("#form1").valid();
		        $("#item1_cNumber").removeClass("error")
		    });
			$("#form2").validate({
		        rules: {
		        		item2_wNumber: {
		                        required: true,
		                        digits:true
		                }
		                
		        },
		        messages: {
		        		item2_wNumber: {
		                    required: "题目数不能为空!",
		                    digits:"请输入整数"
		                }    
		        },
		        errorElement: "label", //用来创建错误提示信息标签
		        success: function (label) { //验证成功后的执行的回调函数
		            //label指向上面那个错误提示信息标签label
		            label.text(" ") //清空错误提示消息
		                .addClass("success"); //加上自定义的success类
		        },
		        errorPlacement: function (error, element) { //错误信息位置设置方法
		            error.addClass("error")
		            error.appendTo(element.parent()); //这里的element是录入数据的对象
		        },
		    });
		
		    $("#item2_wNumber").blur(function(){
		    	$("#form2").valid();
		        $("#item2_wNumber").removeClass("error")
		    });
			$("#form3").validate({
		        rules: {
		        		item3_bNumber: {
		                        required: true,
		                        digits:true
		                }
		                
		        },
		        messages: {
		        		item3_bNumber: {
		                    required: "题目数不能为空!",
		                    digits:"请输入整数"
		                }    
		        },
		        errorElement: "label", //用来创建错误提示信息标签
		        success: function (label) { //验证成功后的执行的回调函数
		            //label指向上面那个错误提示信息标签label
		            label.text(" ") //清空错误提示消息
		                .addClass("success"); //加上自定义的success类
		        },
		        errorPlacement: function (error, element) { //错误信息位置设置方法
		            error.addClass("error")
		            error.appendTo(element.parent()); //这里的element是录入数据的对象
		        },
		    });
		
		    $("#item3_bNumber").blur(function(){
		    	$("#form3").valid();
		        $("#item3_bNumber").removeClass("error")
		    });
		});
	
	</script>
</head>
<body>
	<div data-options="region:'north'" style="width:1024px;height:150px;">
		<h4 align="center">组卷页面</h4>
		<div>
			试卷名称：<input type="text" name="testName"/>
		</div>
		<br>
		<br>
		<div>
			考核知识点：
		</div>
		<form id="form1">
		<div style="margin-top: 3px;margin-left: 2px;margin-right: 2px;height: 40px;line-height: 40px;">
			<input type="checkbox" name="checkAcc"  onchange="haha(this,1)"/>人民币交易&nbsp;&nbsp;&nbsp;&nbsp;
			题目数量：<input type = "text" name="item1_cNumber" id="item1_cNumber" readonly/>
		</div>
		</form>
		<form id="form2">
		<div style="margin-top: 3px;margin-left: 2px;margin-right: 2px;height: 40px;line-height: 40px;">
			<input type="checkbox" name="checkAcc" onchange="haha(this,2)"/>外币对交易&nbsp;&nbsp;&nbsp;&nbsp;
			题目数量：<input type = "text" name="item2_wNumber" id="item2_wNumber" readonly/>
		</div>
		</form>
		<form id="form3">
		<div style="margin-top: 3px;margin-left: 2px;margin-right: 2px;height: 40px;line-height: 40px;">
			<input type="checkbox" name="checkAcc" onchange="haha(this,3)"/>保证金交易&nbsp;&nbsp;&nbsp;&nbsp;
			题目数量：<input type = "text" name="item3_bNumber" id="item3_bNumber" readonly/>
		</div>
		</form>
	</div>
</body>
</html>