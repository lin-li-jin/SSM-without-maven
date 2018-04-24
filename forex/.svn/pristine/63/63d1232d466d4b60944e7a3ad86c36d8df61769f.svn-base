function ajaxLodingTip(){
	var style = "position:absolute;"+
				"left:0px;"+
				"top:0px;"+
				"background-color: #97CBFF;"+
				"width:100%;"+
				"height:100%;"+
				"filter:alpha(opacity=30);"+
				"-moz-opacity:0.3;"+
				"opacity: 0.3;"+
				"z-Index:100;";
	
	var html = "<div id='processTip' style='"+style+"'></div>"+
			   "<div style='z-Index:200;position:absolute;top:0px; left:200px;' id='tipTest' >"+
			   "<ul><li><img src='resources/image/index/step.gif' width='858' height='600' usemap='#MapMap' border='0'/>&nbsp;&nbsp;</li></ul>"+
			   "</div>"+
			   "<map name='MapMap'><area shape='rect' coords='97,523,205,557' href='login.jsp' ></map>";
	$(document.body).append(html);
	document.getElementById("tipSee").value="n";
}