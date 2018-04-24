//
	
//检查学号是否已经存在，学号必须是唯一的
function checkStudentNum(){
	var studentNum  = $("#studentNum").val();
	$.post("checkStudentNum.action",{studentNum:studentNum},
				function(dat){
					 if(dat == "success"){
					}else{
						alert("学号已存在，请重新输入！");
					}
				}
				);
			}
			
			
//检查教师号是否已经存在，教师号必须是唯一的
function checkTeacherNum(){
	var teacherNum  = $("#teacherNum").val();
	var teacherName =$("#teacherName").val();
	var department =$("#department").val();
	var post=$("#posts").val();
	
	if(teacherNum==""||teacherNum=="请输入教工号"){
		alert("请输入教工号！");
		return false;
	}
	if(teacherName==""||teacherName=="请输入教师姓名"){
		alert("请输入教师姓名！");
		return false;
	}
	if(addCheck()){
    	
		$.post("checkTeacherNum.action",{teacherNum:teacherNum},
				function(dat){
					 if(dat == "success"){
					 $('#ec').attr("action","teacherInfoAdd.action");
					 $('#ec').submit();
					}else{
						alert("教师号已存在，请重新输入！");
						return false;
					}
				}
				);
	}
	}

function addCheck(){
  		var post2=document.getElementById('posts').value;
  		var department2=document.getElementById('department2').value;
  		var teacherName2=document.getElementById('teacherName').value;
  		var teacherNum2=document.getElementById('teacherNum').value;
  		var userFlag=document.getElementById('userFlag').value;
  		var hidSchoolNum=document.getElementById('hidSchoolNum').value;
  		var nowSchoolNum=document.getElementById('schoolNum').value;
  		
  		
  		if(hidSchoolNum!=nowSchoolNum){
  		alert("由于学校已换，请点查询后再添加！");
  		return false;
  		}
  		if(isChinese(teacherName2)==false){
  			alert("教师名称只能为中文或英文！");
  			document.getElementById('teacherName').focus();
  			return false;
  		}
  		if(isEnglish(teacherNum2)== false){
  			alert("教工号只能英文或数字！");
  			document.getElementById('teacherNum').focus();
  			return false;
  		}
  		
  		if(post2==''){
  			alert("请选择角色！");
  			return false;
  		}
  		if(department2==''&&post2.indexOf('M')<0){
  			alert("请选择系别！");
  			return false;
  		}
  		
  		return true;
  	}
 function isChinese(str){
	//var badChar ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	//badChar += "abcdefghijklmnopqrstuvwxyz";
	var badChar="";
	badChar += "0123456789";
	badChar += " "+"　";//半角与全角空格
	badChar += "`~!@#$%^&()-_=+]\\|:;\"\\'<,>?/";//不包含*或.的英文符号
	if(""==str){
		return false;
	}
	for(var i=0;i<str.length;i++){
		 var c = str.charAt(i);//字符串str中的字符
		if(badChar.indexOf(c) > -1){
		return false;
		}
		}
	return true;
	}
function isEnglish(str){

	var badChar ="ABCDEFGHIJKLMNOPQRSTUVWXYZ"
	badChar += "abcdefghijklmnopqrstuvwxyz";
	badChar += "0123456789";
	//badChar += " "+"　";//半角与全角空格
	//badChar += "`~!@#$%^&()-_=+]\\|:;\"\\'<,>?/";//不包含*或.的英文符号
	if(""==str){
		return false;
	}
	for(var i=0;i<str.length;i++){
		
		 var c = str.charAt(i);//字符串str中的字符
		if(badChar.indexOf(c) < 0){
		
		return false;
		}
		}
	return true;
}
  	
  				

function formSubmit(formName){
   // var depart1=$("#oldDepart").val();
	//var major1=$("#oldMajor").val();
	var depart2=$("#parent1").val();
	var major2=$("#child2").val();
	var grade=$("#child3").val();
	var studentName=$("#studentName").val();
	var classno=$("#child4").val();
	if(null==studentName||studentName==""){
		alert("学生姓名不能为空！");
		return;
	}
	if(!isChEn(studentName)){
		alert("学生姓名只能是中文或英文！");
		return;
	}
	if(null==depart2||depart2==""){
		alert("系别不能为空！");
		return;
	}
	if(null==grade||grade==""){
		alert("年级不能为空！");
		return;
	}
	if(null==classno||classno==""){
		alert("班级不能为空！");
		return;
	}
	  document.forms[formName].submit();
	processTip();
}

//判断是否为中文或英文
function isChEn(str){
	var badChar="";
	badChar += "0123456789";
	badChar += " "+"　";//半角与全角空格
	badChar += "`~!@#$%^&()-_=+]\\|:;\"\\'<,>?/";//不包含*或.的英文符号
	if(""==str){
		return false;
	}
	for(var i=0;i<str.length;i++){
	 	var c = str.charAt(i);//字符串str中的字符
		if(badChar.indexOf(c) > -1){
			return false;
		}
	}
	return true;
}



	function schoolChange(){
	 	var college='';
	 	var school=$('#schoolNum').val();
	 
		$.ajax({
			type:"post",
			url:"ajaxDepartmentQuery.action",
			data:{college:college,school:school},
			dataType:"json",
			success:function(data){
				var departList="<option value=''></option>";
				var collegeList="<option value=''></option>";
				var postList="<option value=''></option>";
				var arr=data.department;
				var arr1=data.college;
				var arr2=data.post;
				
				for(var i=0;i<arr.length;i++){
				departList=departList+"<option value='"+arr[i].code+"'>"+arr[i].name+"</option>";
				}
				for(var i=0;i<arr1.length;i++){
				collegeList=collegeList+"<option value='"+arr1[i].code+"'>"+arr1[i].name+"</option>";
				}
				for(var i=0;i<arr2.length;i++){
				postList=postList+"<option value='"+arr2[i].code+"'>"+arr2[i].name+"</option>";
				}
				$("#department").html(departList);
				$('#college').html(collegeList);
				$("#department2").html(departList);
				
				$("#posts").html(postList);
				
			}
			
		});
	}				
	
	function collegeChange(){
	 	var college=$('#college').val();
	 	var school=$('#schoolNum').val();
		$.ajax({
			type:"post",
			url:"ajaxDepartmentQuery.action",
			data:{college:college,school:school},
			dataType:"json",
			success:function(data){
				var departList="<option value=''></option>";
				var collegeList="<option value=''></option>";
				var arr=data.department;
				var arr1=data.college;
				for(var i=0;i<arr.length;i++){
				departList=departList+"<option value='"+arr[i].code+"'>"+arr[i].name+"</option>";
				}
				for(var i=0;i<arr1.length;i++){
				collegeList=collegeList+"<option value='"+arr1[i].code+"'>"+arr1[i].name+"</option>";
				}
				$("#department").html(departList);
				$("#department2").html(departList);
				
			}
			
		});
	}				
