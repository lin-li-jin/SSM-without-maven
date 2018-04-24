/*<!--

FileName  :check.js
Function  ��
 		text	password	textarea	select-one
��������	��	��		��		��
���ȼ��	��	��		��
�Ƿ��ַ����	��	��

Parameter:formName
Example  :
	  step1��
		��html��<head>��ǩ�м���������䣺
		<script language="JavaScript" src="[path]/check.js"></script>
		<script language="JavaScript" src="[path]/valid_input.js" ></script>
	  step2��
		��html��<form>��ǩ�м����������ԣ�
		onSubmit="return check('form1')";
Return  :true|false
Remark  :
	 ����formName�ǿ�ѡ�δ�ӵ��κδ���ʱ��Ĭ�ϼ�����Ϊ��ǰ�ĵ��ĵ�һ��Form;
	 ���������ֹ���������valid_input.jsʵ�֣�������ͬʱʹ�ã�ʹ�÷�������Example��
*/


function check(formName)
{
	var ob="",obi="",eName="",fieldTp="c",tp="",len="",decimal="",cName="",isNeed="",vlu="";
	if (formName)
		ob=document.forms[formName]; 
	else 
		ob=document.forms[0]; 
	for (var i=0;i<ob.elements.length;i++)
	{
		obi=ob.elements[i];
		eName=obi.name;

		if(eName){
			
			//tp=obi.type;
			tp=obi.getAttribute("type");
			
			//cName=obi.cName; dd
			cName=obi.getAttribute("cName");
			
			vlu=obi.value;
			//vlu=obi.getAttribute("value");
			
			//len=obi.len;
			len=obi.getAttribute("len");
			
			//isNeed=obi.isNeed; dd
			isNeed=obi.getAttribute("isNeed");
			
			var dataType = obi.getAttribute("dataType");
			
			decimal=obi.decimal;
			if (len==null || len=="")
				len=50;		//��Ĭ��ֵ
			if (isNeed==null || isNeed=="")
				isNeed="N";	//��Ĭ��ֵ
			if (decimal==null || decimal=="")
				decimal=2;	//��Ĭ��ֵ
			if (dataType=="date")	//c:char/i:������/f:��С��/d:date(YYYY.MM.DD)/m:mail
				fieldTp="d";
			else if (dataType=="UpperLetterNum")
				fieldTp="A";
			else if (dataType=="date2")
				fieldTp="d2";
			else if (dataType=="time")
				fieldTp="t";
			else if (dataType=="time2")
				fieldTp="t2";				
			else if (dataType=="float")
				fieldTp="f";
			else if (dataType=="mail")
				fieldTp="m";
			else if (dataType=="int")
				fieldTp="i";
			else if (dataType=="zhengInt")
				fieldTp="z";
			//sunyan add
			else if (dataType=="amount")
				fieldTp="j";
			//sunyan add
			else if (dataType=="path")
				fieldTp="p";
			else if (dataType=="en")
				fieldTp="en";		
			else
				fieldTp="c";
					
			if ( (isNeed=="Y" || isNeed=="y") && 
				(vlu==null || vlu=="" || vlu=="null" ) &&
				(tp=="text" || tp=="password" || tp=="textarea" || tp=="select-one"))
			{
				alert(cName+"�Ǳ����"); 
				obi.focus();
				return false;
			}
		
			if (vlu!="" && vlu!="null") 
			{ 
				if ((vlu.length>len) && (tp=="text" || tp=="password" || tp=="textarea"))
				{
					alert (cName+"���Ȳ��ܴ���" + len + "λ!")
					obi.focus();
					return false;
				} 
					if ( (tp=="text"||tp=="password") && (fieldTp!="c") )
				{
					if (!valid_input(obi,cName,fieldTp,len,decimal))
					{
						obi.focus();
						return false;
					}
				}
			}
		}
	}//End for
	return true;
}//End function
// -->

/*
<!--

FileName  : valid_input.js
Function  : �Ƿ����ݸ�ʽ�ж�
Parameters:
	   eName:����Ӣ����	   cName:����������	   fieldTp:�ֶ�����,c:char/i:������������/f:��С��/d:date(YYYY.MM.DD)/m:mail/ d2:date(YYYYMMDD)/z��������
	   len�����������	   decimal���������С��λ��Example  : onclick="return valid_input("username","�û���","c",20,0)";
Return   : true|false
Remark   �����������жϱ�������Խ��յ���ֵһ�ɲ������������档�жϱ�����������Ӧ���Ƚ���elementNotNull()��������*/


function valid_input(obj,cName,fieldTp,len,decimal)
{
 var str=obj.value;
 var msgName=cName;
 if (str!="")
 {
 	var maxLength=len; 
 	if (fieldTp=="f")//�����������ݵ�С���㲻ռλ��ʱ	
    maxLength++;
 	var msg="������� ! \n* * * * * * * \n\n";
 	var Criteria0=/[^A-Z0-9]/;    //��Сд��ĸ������
 	var Criteria1=/[^a-zA-Z0-9\-\=\_\~\$\%\^\&\*\+\|\!\@\{\}\-\`\_\:\\\.\;\?\,\#\/\[\]\(\)\x20]/;    //��Сд��ĸ������
 	var Criteria2=/[^0-9\-]/;		//���ּ���-�� 
 	var Criteria3=/[^0-9]/;		//������ 
 	var Criteria456=/[^0-9\.\-]/;		//���ּ�С���㼰���� 
 	var Criteria7=/[^\.]/; 
 	var Criteria8="^(([0-9a-zA-Z]+)|([0-9a-zA-Z]+[_.0-9a-zA-Z-]*[0-9a-zA-Z]+))@([a-zA-Z0-9-]+[.])+([a-zA-Z]{2}|net|NET|com|COM|gov|GOV|mil|MIL|org|ORG|edu|EDU|int|INT)$";// E-mail�����ʽ�� 
 	var Criteria9=/[^\-]/; 
	var Criteria10=/[a-zA-Z]\:(\\[a-zA-Z0-9]+)*/; //·��
	var Criteria11=/[^a-zA-Z\s]/;    //����ĸ
	var Criteria12=/[^0-9\,]/;    //����ĸ
 	if (str.length>maxLength) 
	{
		alert (msg+msgName+"��λ�����ԣ��������"+maxLength+"λ��");
		obj.focus();
		return false;
	}
	
	if (fieldTp=="A")
  {
  	
		var A2=Criteria0.exec(str);
		if(A2)
		{
			alert(msg + "[" + str + "]" + "����һ���Ϸ����ַ�!\n"+msgName+"ֻ���ǰ��������ֺʹ�д��ĸ��ɡ�");
			obj.focus();
			return false;
		}
  }

 	if (fieldTp=="z")
  {
		var A2=Criteria3.exec(str.substr(0));
		if(A2)
		{
			alert(msg + A2[0] + "����һ���Ϸ����ַ�!\n"+msgName+"ֻ���ǰ�����������ɡ�");
			obj.focus();
			return false;
		}
  }
 	
 	if (fieldTp=="j")
 	  {
 			var A2=Criteria12.exec(str.substr(0));
 			if(A2)
 			{
 				alert(msgName+'ֻ���ǰ��������ֺ�","��ɡ�');
 				obj.focus();
 				return false;
 			}
 	  }

 	if (fieldTp=="i")
	{
		var A2=Criteria2.exec(str.substr(0));
		if(A2)
		{
			alert(msg + A2[0] + "����һ���Ϸ����ַ�!\n"+msgName+"ֻ���ǰ��������֡�������ɡ�");
			obj.focus();
			return false;
		}


		var r=null;
		if (str.indexOf("-")!=-1)
		{
			if (str.indexOf("-")!=0)
			{
				alert(msg + str + "����һ���Ϸ����ַ�!\n"+msgName+"�ĸ���ֻ�ܷŵ�һλ��")
				obj.focus();
				return false;
			}
			r=str.indexOf("-")+1;
			if (str.indexOf("-",r)!=-1)
			{
				alert(msg + str + "����һ���Ϸ����ַ�!\n"+msgName+"�ĸ���ֻ����һ����") 
				obj.focus();
				return false;
			}  
		}	
 	}//End if (fieldTp=="i")


 	if (fieldTp=="f")
	{
		var k=maxLength-decimal-1;//�������λ��	var t=0;//ʵ������λ��
		var A4=Criteria456.exec(str.substr(0));
		if (A4)
		{
			alert(msg + A4[0] + "����һ���Ϸ����ַ�!\n"+msgName+"ֻ���ɰ��������֡�С���㡢������ɡ�");
			obj.focus();
      return false;
		}

		var r=null;
		if (str.indexOf("-")!=-1)
		{
			if (str.indexOf("-")!=0)
			{
				alert(msg + str + "����һ���Ϸ����ַ�!\n"+msgName+"�ĸ���ֻ�ܷŵ�һλ��")
				obj.focus();
				return false;
			}
			r=str.indexOf("-")+1;
			if (str.indexOf("-",r)!=-1)
			{
				alert(msg + str + "����һ���Ϸ����ַ�!\n"+msgName+"�ĸ���ֻ����һ����")  
				obj.focus();
				return false;
			}
	  }

		var s=null;
		if (str.indexOf(".")!=-1)
		{
			s=str.indexOf(".")+1;
			if (str.indexOf(".",s)!=-1)
			{
				alert(msg + str + "����һ���Ϸ����ַ�!\n"+msgName+"�в��ܺ���һ������С���㡣")
				obj.focus();
				return false;
			}  
			if (str.substr(0,1)==".")
			{
				alert(msgName+"�ĵ�һλ������С����");
				obj.focus();
				return false;        
			}
			var i=str.indexOf(".");
			var j=str.length-1;
			if ((i==j) || (i==1 && str.substr(0,1)=="-"))
			{
				alert(msg + str + "����һ���Ϸ����ַ�!\n"+msgName+"�е�С�����λ�÷Ƿ���")
				obj.focus();
				return false;
			}  
			if (j-i>decimal)
			{
				alert(msg + str + "����һ���Ϸ����ַ�!\n"+msgName+"�������"+decimal+"λС����")
				obj.focus();
				return false;
			}
			t=i;
		}
		else 
		{
			t=str.length;
		}
		if (t>k)
		{
			alert(msg + str + "����һ���Ϸ����ַ�!\n"+msgName+"�������"+k+"λ������")
			obj.focus();
			return false;
		}		

	}//End  if (fieldTp=="f")


 	if (fieldTp=="d")  
	{
   	if (str.length!=10) 
	 	{
	 		alert (msg+msgName+"��λ�����ԣ�ֻ����10λ��");
      obj.focus();
	 		return false;
	 	}

		var  A5=Criteria3.exec(str.substr(0,4));//��ȡ���
		var  B5=Criteria9.exec(str.substr(4,1));
		var  C5=Criteria3.exec(str.substr(5,2));//��ȡ�·�
		var  D5=Criteria9.exec(str.substr(7,1));
		var  E5=Criteria3.exec(str.substr(8,2));//��ȡ����
		var  Char_year=str.substr(0,4);
		var  Char_month=str.substr(5,2);
		var  Char_day=str.substr(8,2);
		var  daymax=0;
		var  monthDays = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);


		if (A5)
		{
			alert(msg + A5[0] + "����һ���Ϸ����ַ�!\n"+msgName+"�����ֻ����4λ������������ɡ�");
			obj.focus();
			return false;
		}
		if (Char_year<1900)
		{
			alert (msg+msgName+"����ݲ��Ϸ�������С���1900��");
			obj.focus();
			return false;
		}

		if (B5) 
		{
			alert(msg + B5[0] + "����һ���Ϸ����ַ�!\n"+msgName+"��5λֻ����'-'");
			obj.focus();
			return false;
		}


		if (C5)//�ж��·�
		{
			lert(msg + C5[0] + "����һ���Ϸ����ַ�!\n"+msgName+"���·�ֻ����2λ������������ɡ�");
			obj.focus();
			return false;
		}

		if (Char_month>12 || Char_month<=0)
		{
			alert (msg +msgName+"���·ݲ��Ϸ���ֻ����1-12��Χ�ڡ�");
			obj.focus();
			return false;
		}

		if (D5)
		{
			alert(msg + D5[0] + "����һ���Ϸ����ַ�!\n"+msgName+"��8λֻ����'-'");
			obj.focus();
			return false;
		}

		if (E5)
		{
			alert(msg + E5[0] + "����һ���Ϸ����ַ�!\n"+msgName+"������ֻ����2λ������������ɡ�");
			obj.focus();
			return false;
		}

		if (((Char_year % 4 == 0) && (Char_year % 100 != 0)) || (Char_year % 400 == 0)) monthDays[1] = 29;
			var month=Char_month-1;

		if ((Char_day<=0) || (Char_day > monthDays[month])) 
		{
			alert(msg+msgName+"�������Ϸ�����������ֻ����1-"+monthDays[month]+"��");
			obj.focus();
			return (false);
		}
		//alert (Char_year+"��"+Char_month+"��"+Char_day+"��");
	} //End if (fieldTp=="d")
	
	 if (fieldTp=="d2")  
	{
   	if (str.length!=8) 
	 	{
	 		alert (msg+msgName+"��λ�����ԣ�ֻ����8λ��");
      obj.focus();
	 		return false;
	 	}

		var  A5=Criteria3.exec(str.substr(0,4));//��ȡ���
		var  C5=Criteria3.exec(str.substr(4,2));//��ȡ�·�
		var  E5=Criteria3.exec(str.substr(6,2));//��ȡ����
		var  Char_year=str.substr(0,4);
		var  Char_month=str.substr(4,2);
		var  Char_day=str.substr(6,2);
		var  daymax=0;
		var  monthDays = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);


		if (A5)
		{
			alert(msg + A5[0] + "����һ���Ϸ����ַ�!\n"+msgName+"�����ֻ����4λ������������ɡ�");
			obj.focus();
			return false;
		}
		if (Char_year<1900)
		{
			alert (msg+msgName+"����ݲ��Ϸ�������С���1900��");
			obj.focus();
			return false;
		}


		if (C5)//�ж��·�
		{
			lert(msg + C5[0] + "����һ���Ϸ����ַ�!\n"+msgName+"���·�ֻ����2λ������������ɡ�");
			obj.focus();
			return false;
		}

		if (Char_month>12 || Char_month<=0)
		{
			
			alert (msg +msgName+"���·ݲ��Ϸ���ֻ����1-12��Χ�ڡ�");
			obj.focus();
			return false;
		}

		if (E5)
		{
			alert(msg + E5[0] + "����һ���Ϸ����ַ�!\n"+msgName+"������ֻ����2λ������������ɡ�");
			obj.focus();
			return false;
		}

		if (((Char_year % 4 == 0) && (Char_year % 100 != 0)) || (Char_year % 400 == 0)) monthDays[1] = 29;
			var month=Char_month-1;

		if ((Char_day<=0) || (Char_day > monthDays[month])) 
		{
			alert(msg+msgName+"�������Ϸ�����������ֻ����1-"+monthDays[month]+"��");
			obj.focus();
			return (false);
		}
		//alert (Char_year+"��"+Char_month+"��"+Char_day+"��");
	} //End if (fieldTp=="d")
	
	
	if(fieldTp =="t")
	{
		if(str.length != 6){
			alert(msg + msgName + "�ĳ��ȱ���Ϊ6λ��");
			obj.focus();
			return false;
		}
		A5 = Criteria3.exec(str);
		if(A5)
		{
			alert(msg + A5 +"������Ч���ַ���\n" + msgName + "��ʱ����ֻ����������ɡ�");
			obj.focus();
			return false;
		}

		var hour = Number(str.substring(0,2));
		var minute = Number(str.substring(2,4));
		var sec = Number(str.substring(4,6));
		
		if(hour >= 24){
			alert(msg + msgName + "��ʱ���ܴ��ڵ���24");
			obj.focus();
			return false;
		}
		if(minute >=60){
			alert(msg + msgName + "�ķֲ��ܴ��ڵ���60");
			obj.focus();
			return false;
		}
		if(sec >=60){
			alert(msg + msgName + "���벻�ܴ��ڵ���60");
			obj.focus();
			return false;
		}
	}
	
	if(fieldTp =="t2")
	{
		if(str.length != 8){
			alert(msg + msgName + "�ĳ��ȱ���Ϊ8λ��");
			obj.focus();
			return false;
		}


		var hour = Number(str.substring(0,2));
		var minute = Number(str.substring(3,5));
		var sec = Number(str.substring(6,8));
		
		var timeStr = hour + minute + sec;
		
		A5 = Criteria3.exec(timeStr);
		if(A5)
		{
			alert(msg + A5 +"������Ч���ַ���\n" + msgName + "��ʱ����ֻ����������ɡ�");
			obj.focus();
			return false;
		}
		
		if(hour >= 24){
			alert(msg + msgName + "��ʱ���ܴ��ڵ���24");
			obj.focus();
			return false;
		}
		if(minute >=60){
			alert(msg + msgName + "�ķֲ��ܴ��ڵ���60");
			obj.focus();
			return false;
		}
		if(sec >=60){
			alert(msg + msgName + "���벻�ܴ��ڵ���60");
			obj.focus();
			return false;
		}
	}	

 	if (fieldTp=="p")
	{
		var path = new RegExp(Criteria10); 
		if (!(str.search(path) != -1)) 
		{ 
			alert ("������Ϸ���·��!");
			obj.focus();
			return false;
		} 
	}//End  if (fieldTp=="p")
	
 	if (fieldTp=="m")
	{
		var mail = new RegExp(Criteria8); 
		if (!(str.search(mail) != -1)) 
		{ 
			alert ("������Ϸ���E-mail��ַ!");
			obj.focus();
			return false;
		} 
	}//End  if (fieldTp=="m")
	
	if( fieldTp=="en" ){
		if( Criteria11.exec( str ) ){
			alert (msgName+" ֻ������ĸ��ɣ�");
			obj.focus();
			return false;
		}
	}
	

 }//End  if (str!="")
	return true; 
}//End function
// -->


function timesliceCheck(startObj,endObj){
	var start = startObj.value;
	var end = endObj.value;
	var startName = startObj.cName;
	var endName = endObj.cName;
		
	if(start !="" && end ==""){
		alert("������" + endName + "!");
		endObj.focus();
		return false;
	}
	
	if(end !="" && start ==""){
		alert("������" + startName + "!");
		startObj.focus();
		return false;
	}
	
	if(start != "" && end !="" && start > end){
		alert(startName + "���ܴ���" + endName + "��");
		startObj.focus();
		return false;
	}
	
	return true;
}


function trim(str){
	return str.replace(/^\s+|\s+$/g,"");
}
 