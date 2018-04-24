/*<!--

FileName  :check.js
Function  ：
 		text	password	textarea	select-one
必填项检测	√	√		√		√
长度检测	√	√		√
非法字符检测	√	√

Parameter:formName
Example  :
	  step1：
		在html的<head>标签中加入以下语句：
		<script language="JavaScript" src="[path]/check.js"></script>
		<script language="JavaScript" src="[path]/valid_input.js" ></script>
	  step2：
		在html的<form>标签中加入以下属性：
		onSubmit="return check('form1')";
Return  :true|false
Remark  :
	 参数formName是可选项，未接到任何传参时，默认检测对象为当前文档的第一个Form;
	 本函数部分功能依赖于valid_input.js实现，请两者同时使用，使用方法：见Example。
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
				len=50;		//设默认值
			if (isNeed==null || isNeed=="")
				isNeed="N";	//设默认值
			if (decimal==null || decimal=="")
				decimal=2;	//设默认值
			if (dataType=="date")	//c:char/i:纯整数/f:带小数/d:date(YYYY.MM.DD)/m:mail
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
				alert(cName+"是必填项！"); 
				obi.focus();
				return false;
			}
		
			if (vlu!="" && vlu!="null") 
			{ 
				if ((vlu.length>len) && (tp=="text" || tp=="password" || tp=="textarea"))
				{
					alert (cName+"长度不能大于" + len + "位!")
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
Function  : 非法数据格式判断
Parameters:
	   eName:变量英文名	   cName:变量中文名	   fieldTp:字段类型,c:char/i:正负整数和零/f:带小数/d:date(YYYY.MM.DD)/m:mail/ d2:date(YYYYMMDD)/z：正整数
	   len：最大允许长度	   decimal：最大允许小数位数Example  : onclick="return valid_input("username","用户名","c",20,0)";
Return   : true|false
Remark   ：本函数不判断必填项，所以接收到空值一律不作处理，返回真。判断必填项的情况，应事先交给elementNotNull()函数处理。*/


function valid_input(obj,cName,fieldTp,len,decimal)
{
 var str=obj.value;
 var msgName=cName;
 if (str!="")
 {
 	var maxLength=len; 
 	if (fieldTp=="f")//当浮点型数据的小数点不占位数时	
    maxLength++;
 	var msg="输入错误 ! \n* * * * * * * \n\n";
 	var Criteria0=/[^A-Z0-9]/;    //大小写字母及数字
 	var Criteria1=/[^a-zA-Z0-9\-\=\_\~\$\%\^\&\*\+\|\!\@\{\}\-\`\_\:\\\.\;\?\,\#\/\[\]\(\)\x20]/;    //大小写字母及数字
 	var Criteria2=/[^0-9\-]/;		//数字及“-” 
 	var Criteria3=/[^0-9]/;		//仅数字 
 	var Criteria456=/[^0-9\.\-]/;		//数字及小数点及负号 
 	var Criteria7=/[^\.]/; 
 	var Criteria8="^(([0-9a-zA-Z]+)|([0-9a-zA-Z]+[_.0-9a-zA-Z-]*[0-9a-zA-Z]+))@([a-zA-Z0-9-]+[.])+([a-zA-Z]{2}|net|NET|com|COM|gov|GOV|mil|MIL|org|ORG|edu|EDU|int|INT)$";// E-mail邮箱格式用 
 	var Criteria9=/[^\-]/; 
	var Criteria10=/[a-zA-Z]\:(\\[a-zA-Z0-9]+)*/; //路径
	var Criteria11=/[^a-zA-Z\s]/;    //仅字母
	var Criteria12=/[^0-9\,]/;    //仅字母
 	if (str.length>maxLength) 
	{
		alert (msg+msgName+"的位数不对，最多允许"+maxLength+"位！");
		obj.focus();
		return false;
	}
	
	if (fieldTp=="A")
  {
  	
		var A2=Criteria0.exec(str);
		if(A2)
		{
			alert(msg + "[" + str + "]" + "不是一个合法的字符!\n"+msgName+"只能是阿拉伯数字和大写字母组成。");
			obj.focus();
			return false;
		}
  }

 	if (fieldTp=="z")
  {
		var A2=Criteria3.exec(str.substr(0));
		if(A2)
		{
			alert(msg + A2[0] + "不是一个合法的字符!\n"+msgName+"只能是阿拉伯数字组成。");
			obj.focus();
			return false;
		}
  }
 	
 	if (fieldTp=="j")
 	  {
 			var A2=Criteria12.exec(str.substr(0));
 			if(A2)
 			{
 				alert(msgName+'只能是阿拉伯数字和","组成。');
 				obj.focus();
 				return false;
 			}
 	  }

 	if (fieldTp=="i")
	{
		var A2=Criteria2.exec(str.substr(0));
		if(A2)
		{
			alert(msg + A2[0] + "不是一个合法的字符!\n"+msgName+"只能是阿拉伯数字、负号组成。");
			obj.focus();
			return false;
		}


		var r=null;
		if (str.indexOf("-")!=-1)
		{
			if (str.indexOf("-")!=0)
			{
				alert(msg + str + "不是一个合法的字符!\n"+msgName+"的负号只能放第一位！")
				obj.focus();
				return false;
			}
			r=str.indexOf("-")+1;
			if (str.indexOf("-",r)!=-1)
			{
				alert(msg + str + "不是一个合法的字符!\n"+msgName+"的负号只能有一个！") 
				obj.focus();
				return false;
			}  
		}	
 	}//End if (fieldTp=="i")


 	if (fieldTp=="f")
	{
		var k=maxLength-decimal-1;//最大整数位数	var t=0;//实际整数位数
		var A4=Criteria456.exec(str.substr(0));
		if (A4)
		{
			alert(msg + A4[0] + "不是一个合法的字符!\n"+msgName+"只能由阿拉伯数字、小数点、负号组成。");
			obj.focus();
      return false;
		}

		var r=null;
		if (str.indexOf("-")!=-1)
		{
			if (str.indexOf("-")!=0)
			{
				alert(msg + str + "不是一个合法的字符!\n"+msgName+"的负号只能放第一位！")
				obj.focus();
				return false;
			}
			r=str.indexOf("-")+1;
			if (str.indexOf("-",r)!=-1)
			{
				alert(msg + str + "不是一个合法的字符!\n"+msgName+"的负号只能有一个！")  
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
				alert(msg + str + "不是一个合法的字符!\n"+msgName+"中不能含有一个以上小数点。")
				obj.focus();
				return false;
			}  
			if (str.substr(0,1)==".")
			{
				alert(msgName+"的第一位不能是小数点");
				obj.focus();
				return false;        
			}
			var i=str.indexOf(".");
			var j=str.length-1;
			if ((i==j) || (i==1 && str.substr(0,1)=="-"))
			{
				alert(msg + str + "不是一个合法的字符!\n"+msgName+"中的小数点的位置非法。")
				obj.focus();
				return false;
			}  
			if (j-i>decimal)
			{
				alert(msg + str + "不是一个合法的字符!\n"+msgName+"最多允许"+decimal+"位小数。")
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
			alert(msg + str + "不是一个合法的字符!\n"+msgName+"最多允许"+k+"位整数。")
			obj.focus();
			return false;
		}		

	}//End  if (fieldTp=="f")


 	if (fieldTp=="d")  
	{
   	if (str.length!=10) 
	 	{
	 		alert (msg+msgName+"的位数不对，只能是10位！");
      obj.focus();
	 		return false;
	 	}

		var  A5=Criteria3.exec(str.substr(0,4));//获取年份
		var  B5=Criteria9.exec(str.substr(4,1));
		var  C5=Criteria3.exec(str.substr(5,2));//获取月份
		var  D5=Criteria9.exec(str.substr(7,1));
		var  E5=Criteria3.exec(str.substr(8,2));//获取天数
		var  Char_year=str.substr(0,4);
		var  Char_month=str.substr(5,2);
		var  Char_day=str.substr(8,2);
		var  daymax=0;
		var  monthDays = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);


		if (A5)
		{
			alert(msg + A5[0] + "不是一个合法的字符!\n"+msgName+"的年份只能由4位阿拉伯数字组成。");
			obj.focus();
			return false;
		}
		if (Char_year<1900)
		{
			alert (msg+msgName+"的年份不合法！限最小年份1900年");
			obj.focus();
			return false;
		}

		if (B5) 
		{
			alert(msg + B5[0] + "不是一个合法的字符!\n"+msgName+"第5位只能是'-'");
			obj.focus();
			return false;
		}


		if (C5)//判断月份
		{
			lert(msg + C5[0] + "不是一个合法的字符!\n"+msgName+"的月份只能由2位阿拉伯数字组成。");
			obj.focus();
			return false;
		}

		if (Char_month>12 || Char_month<=0)
		{
			alert (msg +msgName+"的月份不合法，只能在1-12范围内。");
			obj.focus();
			return false;
		}

		if (D5)
		{
			alert(msg + D5[0] + "不是一个合法的字符!\n"+msgName+"第8位只能是'-'");
			obj.focus();
			return false;
		}

		if (E5)
		{
			alert(msg + E5[0] + "不是一个合法的字符!\n"+msgName+"的天数只能由2位阿拉伯数字组成。");
			obj.focus();
			return false;
		}

		if (((Char_year % 4 == 0) && (Char_year % 100 != 0)) || (Char_year % 400 == 0)) monthDays[1] = 29;
			var month=Char_month-1;

		if ((Char_day<=0) || (Char_day > monthDays[month])) 
		{
			alert(msg+msgName+"天数不合法！当月天数只能在1-"+monthDays[month]+"内");
			obj.focus();
			return (false);
		}
		//alert (Char_year+"年"+Char_month+"月"+Char_day+"日");
	} //End if (fieldTp=="d")
	
	 if (fieldTp=="d2")  
	{
   	if (str.length!=8) 
	 	{
	 		alert (msg+msgName+"的位数不对，只能是8位！");
      obj.focus();
	 		return false;
	 	}

		var  A5=Criteria3.exec(str.substr(0,4));//获取年份
		var  C5=Criteria3.exec(str.substr(4,2));//获取月份
		var  E5=Criteria3.exec(str.substr(6,2));//获取天数
		var  Char_year=str.substr(0,4);
		var  Char_month=str.substr(4,2);
		var  Char_day=str.substr(6,2);
		var  daymax=0;
		var  monthDays = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);


		if (A5)
		{
			alert(msg + A5[0] + "不是一个合法的字符!\n"+msgName+"的年份只能由4位阿拉伯数字组成。");
			obj.focus();
			return false;
		}
		if (Char_year<1900)
		{
			alert (msg+msgName+"的年份不合法！限最小年份1900年");
			obj.focus();
			return false;
		}


		if (C5)//判断月份
		{
			lert(msg + C5[0] + "不是一个合法的字符!\n"+msgName+"的月份只能由2位阿拉伯数字组成。");
			obj.focus();
			return false;
		}

		if (Char_month>12 || Char_month<=0)
		{
			
			alert (msg +msgName+"的月份不合法，只能在1-12范围内。");
			obj.focus();
			return false;
		}

		if (E5)
		{
			alert(msg + E5[0] + "不是一个合法的字符!\n"+msgName+"的天数只能由2位阿拉伯数字组成。");
			obj.focus();
			return false;
		}

		if (((Char_year % 4 == 0) && (Char_year % 100 != 0)) || (Char_year % 400 == 0)) monthDays[1] = 29;
			var month=Char_month-1;

		if ((Char_day<=0) || (Char_day > monthDays[month])) 
		{
			alert(msg+msgName+"天数不合法！当月天数只能在1-"+monthDays[month]+"内");
			obj.focus();
			return (false);
		}
		//alert (Char_year+"年"+Char_month+"月"+Char_day+"日");
	} //End if (fieldTp=="d")
	
	
	if(fieldTp =="t")
	{
		if(str.length != 6){
			alert(msg + msgName + "的长度必须为6位！");
			obj.focus();
			return false;
		}
		A5 = Criteria3.exec(str);
		if(A5)
		{
			alert(msg + A5 +"不是有效的字符！\n" + msgName + "的时分秒只能由数字组成。");
			obj.focus();
			return false;
		}

		var hour = Number(str.substring(0,2));
		var minute = Number(str.substring(2,4));
		var sec = Number(str.substring(4,6));
		
		if(hour >= 24){
			alert(msg + msgName + "的时不能大于等于24");
			obj.focus();
			return false;
		}
		if(minute >=60){
			alert(msg + msgName + "的分不能大于等于60");
			obj.focus();
			return false;
		}
		if(sec >=60){
			alert(msg + msgName + "的秒不能大于等于60");
			obj.focus();
			return false;
		}
	}
	
	if(fieldTp =="t2")
	{
		if(str.length != 8){
			alert(msg + msgName + "的长度必须为8位！");
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
			alert(msg + A5 +"不是有效的字符！\n" + msgName + "的时分秒只能由数字组成。");
			obj.focus();
			return false;
		}
		
		if(hour >= 24){
			alert(msg + msgName + "的时不能大于等于24");
			obj.focus();
			return false;
		}
		if(minute >=60){
			alert(msg + msgName + "的分不能大于等于60");
			obj.focus();
			return false;
		}
		if(sec >=60){
			alert(msg + msgName + "的秒不能大于等于60");
			obj.focus();
			return false;
		}
	}	

 	if (fieldTp=="p")
	{
		var path = new RegExp(Criteria10); 
		if (!(str.search(path) != -1)) 
		{ 
			alert ("请输入合法的路径!");
			obj.focus();
			return false;
		} 
	}//End  if (fieldTp=="p")
	
 	if (fieldTp=="m")
	{
		var mail = new RegExp(Criteria8); 
		if (!(str.search(mail) != -1)) 
		{ 
			alert ("请输入合法的E-mail地址!");
			obj.focus();
			return false;
		} 
	}//End  if (fieldTp=="m")
	
	if( fieldTp=="en" ){
		if( Criteria11.exec( str ) ){
			alert (msgName+" 只能由字母组成！");
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
		alert("请输入" + endName + "!");
		endObj.focus();
		return false;
	}
	
	if(end !="" && start ==""){
		alert("请输入" + startName + "!");
		startObj.focus();
		return false;
	}
	
	if(start != "" && end !="" && start > end){
		alert(startName + "不能大于" + endName + "！");
		startObj.focus();
		return false;
	}
	
	return true;
}


function trim(str){
	return str.replace(/^\s+|\s+$/g,"");
}
 