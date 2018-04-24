<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ page contentType="text/html;charset=gbk" %> 
<%@ taglib prefix="ww" uri="webwork" %>
<%
	String subjectpath=request.getContextPath();
%>
<html>
  <head>
    <title>组卷功能</title>

	  <script src="<%=subjectpath%>/resources/js/jquery-1.8.3.js" type="text/javascript"></script>
	  <script src="<%=subjectpath%>/resources/js/jquery.validate.js" type="text/javascript"></script>
	  <script src="<%=subjectpath%>/resources/js/additional-methods.js"/>
	  <script src="<%=subjectpath%>/resources/ponycss/jquery_validate.css" type="text/javascript"></script>
	  <link rel="stylesheet" type="text/css" href="css/managepaper.css">
	  <script type="text/javascript">
		  function isnul(data) {
			  if (!data){
			      data=0;
			  }
			  return data;
          }

          //W 外币对交易
          //C 人民币交易
          //B 保证金交易
          function manage(){
              console.log('正在组卷...')
              var valite=$("#managepaperform").valid();
              //验证是否通过
              if (!valite){
				  console.log("组卷参数验证不通过")
                  return;
			  }
			  w=isnul($("#w_num").val())
			  b=isnul($("#b_num").val())
			  c=isnul($("#c_num").val())

			  title=$("#exam_name").val()
              $.ajax({
                  url: '<%=subjectpath%>/managePaper.action',
                  type: 'get',
                  dataType: 'json',
                  data: {
                      'examRule.titleScale.W':w,
					  'examRule.titleScale.B':b,
					  'examRule.titleScale.C':c,
					  'examRule.paperTitle':title
				  }
              })
                  .done(function(data) {
                      if (data.code===-1)
                          $.messager.show({title:'组卷参数错误',msg:data.error,timeout:2000,showType:'slide'})
                      else if (data.code===0)
                          console.log("success")
                  })
                  .fail(function() {
                      console.log("error");
                  })
                  .always(function() {
                      console.log("complete");
                  });

          }



          $(document).ready(function() {
              $("#managepaperform").validate({
                  rules:{
                      exam_name:{
                          required:true
                      },
                      b_num:{
                          required:true,
                          digits:true
                      },
                      c_num:{
                          required:true,
                          digits:true
                      },
                      w_num:{
                          required:true,
                          digits:true

                      }
                  },
                  messages:{
                      exam_name:{
                          required:"试卷名称不能为空"
                      },
                      b_num:{
                          required: "题目数量不能为空!",
                          digits: "题目数量只能是整数"
                      },
                      c_num:{
                          required: "题目数量不能为空!",
                          digits: "题目数量只能是整数"
                      },
                      w_num:{
                          required: "题目数量不能为空!",
                          digits: "题目数量只能是整数"
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
                  }
              })
          });
	  </script>
  </head>
  <body>
  <div>
	  <form id="managepaperform">
		  <div class="model">
			  <span>试卷名称:&nbsp;&nbsp;&nbsp;</span>
			  <input type="input" name="exam_name">
		  </div>
		  <div>
			  <span>考核知识点</span>
			  <div class="model">
				  <input type="checkbox" name="accMountcheckbox" value="checkbox" onchange="haha(this,1)">
				  人民币交易 &nbsp;&nbsp;&nbsp;
				  <div style="display: inline">
					  <label for="c_num">题目数量 : </label>
					  <input type="input" name="c_num" id="c_num" style="width: 80px;"> &nbsp;&nbsp;&nbsp;
				  </div>
			  </div>
			  <div class="model">
				  <input type="checkbox" name="accMountcheckbox" value="checkbox" onchange="haha(this,1)">
				  外币对交易 &nbsp;&nbsp;&nbsp;
				  <div style="display: inline">
					  <label for="w_num">题目数量 : </label>
					  <input type="input"  name="w_num" id="w_num" style="width: 80px;"> &nbsp;&nbsp;&nbsp;
				  </div>
			  </div>
			  <div class="model">
				  <input type="checkbox" name="accMountcheckbox" value="checkbox" onchange="haha(this,1)">
				  保证金交易 &nbsp;&nbsp;&nbsp;
				  <div style="display: inline">
					  <label for="b_num">题目数量 : </label>
					  <input type="input"  name="b_num" id="b_num" style="width: 80px;"> &nbsp;&nbsp;&nbsp;
				  </div>
			  </div>
		  </div>
		  <input type="button" class="button2" value="组卷" onclick="manage()">
	  </form>
  </div>
  </body>
</html>
