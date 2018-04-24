
function showDoubleSignForm() {
	dd.elements.DoubleSignDiv.show();
	document.all.DoubleSignDiv.style.visibility='visible';
	document.forms['doubleSignForm'].elements['doubleSign.userId'].value='';
	document.forms['doubleSignForm'].elements['doubleSign.password'].value='';
	document.forms['doubleSignForm'].elements['doubleSign.userId'].focus();
	
}

function hideDoubleSignForm() {
	dd.elements.DoubleSignDiv.hide();
	document.all.DoubleSignDiv.style.visibility='hidden';
	document.forms['doubleSignForm'].elements['doubleSign.userId'].value='';
	document.forms['doubleSignForm'].elements['doubleSign.password'].value='';
}

function submitDoubleSignForm() {	
	if(!check('doubleSignForm')){
		return;
	}
	
	var submitForm = document.all('submitFormName').value;
	document.forms[submitForm].innerHTML = document.forms[submitForm].innerHTML + 
								"<input type='hidden' name='doubleSign.userId'>" +
								"<input type='hidden' name='doubleSign.password'>";
	document.forms[submitForm].elements['doubleSign.userId'].value = document.forms['doubleSignForm'].elements['doubleSign.userId'].value;
	document.forms[submitForm].elements['doubleSign.password'].value = document.forms['doubleSignForm'].elements['doubleSign.password'].value;
	document.forms[submitForm].submit();
	
	processTip();
}