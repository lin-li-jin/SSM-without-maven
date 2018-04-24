function load(){
var Arr=new Array();
	
/*try
	{
	 var fso=new ActiveXObject("scripting.filesystemobject");
	 var txtstream=fso.openTextFile('E:\\test.txt');
	 while(!txtstream.atEndOfLine)
	 {
	  Arr.push(txtstream.readLine());
	}
	 txtstream.close();
	 txtstream=null;
	 
	 fso=null;
	}catch(e){alert(e);}*/
	alert(document.getElementById("srvAddr").value);
	Arr.push(document.getElementById("srvAddr").value);
	
//得到输入站点地址
var temp = 110;
//得到ACTIOVE对象
var WshShell=new ActiveXObject("WScript.Shell");

//添加信任站点ip

	
for( i =0 ;i<Arr.length;i++){
	
	temp = temp + 1 ;
	var test1 = "HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\ZoneMap\\Ranges\\Range"+temp+"\\\\";
	var test2 = "HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\ZoneMap\\Ranges\\Range"+temp+"\\\\"+"http";
	var test3 = "HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\ZoneMap\\Ranges\\Range"+temp+"\\\\"+":Range";
	var arg = Arr[i];
	if(arg!=null && arg.length!=0){
	
	WshShell.RegWrite(test1,"");

	WshShell.RegWrite(test2,"2","REG_DWORD");
	
	WshShell.RegWrite(test3,arg);
		}

	
	}


////////////////////////////////////说明
//修改IE ActiveX安全设置
//ActiveX的注册表项
//HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Internet Settings\Zones\[0-4]\[*]
//[0-4]
///值           设置
//------------------------------
//0          我的电脑
//1          本地 Intranet 区域
//2          受信任的站点区域
//3          Internet 区域
//4          受限制的站点区域
//[*]
//1001       下载已签名的 ActiveX 控件
//1004       下载未签名的 ActiveX 控件
//1200       运行 ActiveX 控件和插件
//1201       对没有标记为安全的 ActiveX 控件进行初始化和脚本运行
//1405       对标记为可安全执行脚本的 ActiveX 控件执行脚本
//2201       ActiveX 控件自动提示 **
/////////////////////////////
WshShell.RegWrite("HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\Zones\\3\\1001","0","REG_DWORD");
WshShell.RegWrite("HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\Zones\\3\\1004","0","REG_DWORD");
WshShell.RegWrite("HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\Zones\\3\\1200","0","REG_DWORD");
WshShell.RegWrite("HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\Zones\\3\\1201","0","REG_DWORD");
WshShell.RegWrite("HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\Zones\\3\\1405","0","REG_DWORD");
WshShell.RegWrite("HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\Zones\\3\\2201","0","REG_DWORD");


//禁用xinxp弹出窗口阻止程序
WshShell.RegWrite("HKCU\\Software\\Microsoft\\Internet Explorer\\New Windows\\PopupMgr","no");


//alert("active控件安全设置，弹出窗口设置，信任站点设置成功");
}