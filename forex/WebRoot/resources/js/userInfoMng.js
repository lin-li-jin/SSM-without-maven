//
	
//���ѧ���Ƿ��Ѿ����ڣ�ѧ�ű�����Ψһ��
function checkStudentNum(){
	var studentNum  = $("#studentNum").val();
	$.post("checkStudentNum.action",{studentNum:studentNum},
				function(dat){
					 if(dat == "success"){
					}else{
						alert("ѧ���Ѵ��ڣ����������룡");
					}
				}
				);
			}
			
			
//����ʦ���Ƿ��Ѿ����ڣ���ʦ�ű�����Ψһ��
function checkTeacherNum(){
	var teacherNum  = $("#teacherNum").val();
	var teacherName =$("#teacherName").val();
	var department =$("#department").val();
	var post=$("#posts").val();
	
	if(teacherNum==""||teacherNum=="������̹���"){
		alert("������̹��ţ�");
		return false;
	}
	if(teacherName==""||teacherName=="�������ʦ����"){
		alert("�������ʦ������");
		return false;
	}
	if(addCheck()){
    	
		$.post("checkTeacherNum.action",{teacherNum:teacherNum},
				function(dat){
					 if(dat == "success"){
					 $('#ec').attr("action","teacherInfoAdd.action");
					 $('#ec').submit();
					}else{
						alert("��ʦ���Ѵ��ڣ����������룡");
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
  		alert("����ѧУ�ѻ�������ѯ������ӣ�");
  		return false;
  		}
  		if(isChinese(teacherName2)==false){
  			alert("��ʦ����ֻ��Ϊ���Ļ�Ӣ�ģ�");
  			document.getElementById('teacherName').focus();
  			return false;
  		}
  		if(isEnglish(teacherNum2)== false){
  			alert("�̹���ֻ��Ӣ�Ļ����֣�");
  			document.getElementById('teacherNum').focus();
  			return false;
  		}
  		
  		if(post2==''){
  			alert("��ѡ���ɫ��");
  			return false;
  		}
  		if(department2==''&&post2.indexOf('M')<0){
  			alert("��ѡ��ϵ��");
  			return false;
  		}
  		
  		return true;
  	}
 function isChinese(str){
	//var badChar ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	//badChar += "abcdefghijklmnopqrstuvwxyz";
	var badChar="";
	badChar += "0123456789";
	badChar += " "+"��";//�����ȫ�ǿո�
	badChar += "`~!@#$%^&()-_=+]\\|:;\"\\'<,>?/";//������*��.��Ӣ�ķ���
	if(""==str){
		return false;
	}
	for(var i=0;i<str.length;i++){
		 var c = str.charAt(i);//�ַ���str�е��ַ�
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
	//badChar += " "+"��";//�����ȫ�ǿո�
	//badChar += "`~!@#$%^&()-_=+]\\|:;\"\\'<,>?/";//������*��.��Ӣ�ķ���
	if(""==str){
		return false;
	}
	for(var i=0;i<str.length;i++){
		
		 var c = str.charAt(i);//�ַ���str�е��ַ�
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
		alert("ѧ����������Ϊ�գ�");
		return;
	}
	if(!isChEn(studentName)){
		alert("ѧ������ֻ�������Ļ�Ӣ�ģ�");
		return;
	}
	if(null==depart2||depart2==""){
		alert("ϵ����Ϊ�գ�");
		return;
	}
	if(null==grade||grade==""){
		alert("�꼶����Ϊ�գ�");
		return;
	}
	if(null==classno||classno==""){
		alert("�༶����Ϊ�գ�");
		return;
	}
	  document.forms[formName].submit();
	processTip();
}

//�ж��Ƿ�Ϊ���Ļ�Ӣ��
function isChEn(str){
	var badChar="";
	badChar += "0123456789";
	badChar += " "+"��";//�����ȫ�ǿո�
	badChar += "`~!@#$%^&()-_=+]\\|:;\"\\'<,>?/";//������*��.��Ӣ�ķ���
	if(""==str){
		return false;
	}
	for(var i=0;i<str.length;i++){
	 	var c = str.charAt(i);//�ַ���str�е��ַ�
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
