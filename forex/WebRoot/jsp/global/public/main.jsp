<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ taglib prefix="ww" uri="webwork"%>


<head>
	<title></title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Robots" content="index,follow" />
	<meta name="author" content="zhouyf" />
	<meta name="Description" content="description" />
	<meta name="Keywords" content="keywords" />	
	<link rel="stylesheet" href="../../../resources/css/style.css" type="text/css" media="screen, print" />
	<script type="text/javascript" src=""></script>
</head>
<script type="text/javascript">
		function changeEM(value){
			window.location.href="../../../stuTestPaperInit.action?testPaperAssign.paperFor="+value;
		}
	</script>
<body>
	<div style="margin:15px;border:#0a5fa0 1px solid; width:98%; height:93%; background:url(../../../resources/image/background.jpg) #f2f6fd bottom right no-repeat">
	</div>
	<div id="body">		
<ww:if test='#session.loginUserModel.userType == "T"'>
		<!--<span style="text-align:center;font-size:16px;display:block;margin-top:10px;font-weight:blod"><img src="../../../resources/image/Introducetitle.jpg"></img></span>
			<div class="mainC">
				
				<div class="mainCon">
				<h2>考核管理</h2>
				<ul>
					<li>课程查询：查询课程的相关信息<br>试卷管理：试卷的查询，修改，审批和分配<br>考核参数管理：考核参数的查询和增加<br></li>
				</ul>
				<h2>题库管理</h2> 组卷<br>自动组卷：教师填写相关的参数，系统自动抽取题目生成一份试卷，并提供试卷预览和修改<br>手动组卷：由教师指定各个考核点、试题等组成一份试卷，并提供试卷预览和修改<br><br>题库查询：依据条件查询到对应的试题和试题的采分点<br><br>
				<ul>
					<ul><li>模板管理</li><li>非综合题模板管理：新增非综合题模板<br>综合题模板管理：新增综合题模板</li><li><br></li><li>组题</li><li>非综合题组题：由模板生成题目，组成题目的数据来源于情景包<br>综合题组题：由模板生成题目，组成题目的数据来源于情景包<br></li></ul>
				</ul>
				<h2>采分点管理</h2>
				<ul>
					<li>模板采分点：指定该模板要考核的要点，形成答案<br>采分点查询：查询非综合试题模板的采分点，并可对其修改</li>
				</ul>
				<h2>实验报告管理</h2>
				<ul>
					<li>实验报告模板管理：新增实验报告模板<br>实验报告管理：实验报告查询和打印</li>
				</ul>
				<h2>成绩管理</h2>
				<ul>
					<li>成绩查询：查询成绩并可连接到报告查阅和考核情况查阅</li>
				</ul>
				<h2>实验项目管理</h2>
				<ul>
					<li>实验项目管理：新增实验项目并指定实验项目的课堂练习题目</li>
				</ul>
				</div>
			</div>-->
			
</ww:if>

<ww:else>
			<!--<span style="text-align:center;font-size:16px;display:block;margin-top:10px;font-weight:blod"><img src="../../../resources/image/Introducetitle.jpg"></img></span>
			<div class="mainC">
				
				<div class="mainCon">
				<h2>成绩查询</h2>
				<ul>
					<li>查询实验考核成绩</li>
				</ul>
				<h2>实验报告</h2>
				<ul>
					<li>查阅考核以后系统自动生成的实验考核报告</li>
				</ul>
				<h2>考核回顾</h2>
				<ul>
					<li>查阅考核后，对应试卷的题目和答案</li>
				</ul>
				<h2>课堂练习</h2>
				<ul>
					<li>依据课程和实验项目进行做练习</li>
				</ul>
				<h2>模拟考核</h2>
				<ul>
					<li>依据试卷进行考核，考核后会出实验报告</li>
				</ul>
				<h2>正式考核</h2>
				<ul>
					<li>依据试卷进行考核，考核后会出实验报告</li>
				</ul>
				</div>
			</div>-->
			
			
</ww:else>			


</body>
</html>
