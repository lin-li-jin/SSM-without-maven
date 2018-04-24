$(document).ready(function () {
		$(window).ajaxStart(function(){    
			$("#uploadprogressbar").attr("style","display:block"); 
			document.onmousedown=function(){
				if (event.button>=1){return false;}
			}
		}); 
		$(window).ajaxStop(function(){       	 
			 $("#uploadprogressbar").attr("style","display:none"); 
			document.onmousedown=function(){
				if (event.button>=1){return false;}
			}
		});
});


