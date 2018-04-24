(function($){ 
var keyCode={ 
BACKSPACE: 8, 
CAPS_LOCK: 20, 
COMMA: 188, 
CONTROL: 17, 
DELETE: 46, 
DOWN: 40, 
END: 35, 
ENTER: 13, 
ESCAPE: 27, 
HOME: 36, 
INSERT: 45, 
LEFT: 37, 
// NUMPAD_ADD: 107, 
// NUMPAD_DECIMAL: 110, 
// NUMPAD_DIVIDE: 111, 
// NUMPAD_ENTER: 108, 
// NUMPAD_MULTIPLY: 106, 
// NUMPAD_SUBTRACT: 109, 
PAGE_DOWN: 34, 
PAGE_UP: 33, 
PERIOD: 190, 
RIGHT: 39, 
SHIFT: 16, 
SPACE: 32, 
TAB: 9, 
UP: 38 
} 
var isControlCode=function(key){ 
for(name in keyCode){ 
if(keyCode[name]==key) 
return true; 
} 
return false; 
} 
var number=function(event){ 
var t=this; 
if(event.shiftKey) 
return false; 
var key=event.keyCode; 
if(isControlCode(key)) 
return; 
if(key<48 || key>105) 
return false; 
if(key>57 && key<96) 
return false; 
} 
var ControlList={}; 
var control=Class.extend({ 
init:function(obj){ 
this.Event=[]; 
this.target=$(obj); 
}, 
Add:function(settings){ 
var t=this; 
t.Event.push(settings); 
return t; 
}, 
Bind:function(){ 
var t=this; 
if(!t.target){ 
return; 
} 
t.target.each(function(){ 
var b=$(this); 
b.bind("keydown",t.event.bind(t)).bind("keyup",t.event.bind(t)); 
if(jQuery.browser.msie) 
{ 
b.bind("paste",t.event.bind(t)); 
} 
else{ 
b.bind("input",t.event.bind(t)); 
} 
}); 
}, 
getmethods:function(type){ 
var t=this; 
var list=[]; 
$(t.Event).each(function(i,item){ 
if(!item.type || !item.method) 
return; 
if(item.type=="all"){ 
list.push(item.method); 
}else 
{ 
if(item.type==type){ 
list.push(item.method); 
} 
} 
}); 
return list; 
}, 
IsSuccess:function(list,event,obj){ 
var istrue=true; 
for(var i=0;i<list.length;i++){ 
if(list[i].call(obj,event)===false) 
{ 
istrue=false; 
break; 
} 
} 
return istrue; 
}, 
event:function(ev){ 
var t=this; 
var event=ev.originalEvent; 
var obj=ev.target; 
return t.IsSuccess(t.getmethods(ev.type),event,obj); 
} 
}); 
control.Add=function(obj,settings){ 
var con= ControlList[this.selector]; 
if(!con) 
{ 
con=new control(obj); 
ControlList[this.selector]=con; 
con.Bind(); 
} 
con.Add(settings); 
return control; 
} 
$.fn.numberable=function(){ 
$(this).css("imeMode","disabled"); 
control.Add(this,{type:"keydown",method:number}).Add(this,{type:"keyup",method:number}); 
function repnumber(event){ 
var o= $(this); 
if(o.val()){ 
o.val(o.val().replace(/[^\d]/g,"")); 
} 
} 
if(jQuery.browser.msie){ 
control.Add(this,{type:"paste",method:function(event){var o=this; 
setTimeout(repnumber.bind(o,event),0); 
} 
}); 
} 
else 
{ 
control.Add(this,{type:"input",method:repnumber}); 
} 
return this; 
} 
})(jQuery); 
