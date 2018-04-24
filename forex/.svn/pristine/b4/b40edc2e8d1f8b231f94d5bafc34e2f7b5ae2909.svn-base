var  u=60, I="";
var gInputSearch = 10;
var  gInputQuery = null, gBtnSearch = null;
var gInputQueryFocused = false,  gInputQueryBlured = false, gInputQueryValue = null, gInputQueryKeycode = "";

var gKeyword = "",   gKeywordPre = "", gKeywordToDisplayCompStrings = "";
var gCompDiv = null,  gCompIFrame = null;
var gCompDivBorderLeftWidth = 1, gCompDivBorderTopWidth = 1;
var gSubmitUrl;

var gCompleteStrings = {};

var gNotResponsedResquestNum = 0;
var gTimerHideCompDiv = null;
var gDivTagName = "div";
var gSpanTagName = "span";

var gCompStringDivNum = 5;
var gCompStringDivNumber = -1;
var gCompStringDivs = null;
var gSelectedCompStringDiv = null, gSelectedCompStringDivIndex = -1;

var gSelectionValue = null, gSelectionStart = null, gSelectionLength = null;
var gMousePositionX = -1, gMousePositionY = -1;

var gDocumentKeycode = -1;

var gSubmitting = false;
var gTimeFactor = 1;

function JSONscriptRequest(fullUrl) {
    this.fullUrl = fullUrl; 
    this.noCacheIE = '&noCacheIE=' + (new Date()).getTime();
    this.headLoc = document.getElementsByTagName("head").item(0);
    this.scriptId = 'JscriptId' + JSONscriptRequest.scriptCounter++;
}

JSONscriptRequest.scriptCounter = 1;
JSONscriptRequest.prototype.buildScriptTag = function () 
{
	var CharSet = "&cs=ISO8859_1";	
    this.scriptObj = document.createElement("script");  
    this.scriptObj.setAttribute("type", "text/javascript");
    this.scriptObj.setAttribute("src", this.fullUrl + CharSet);
}
JSONscriptRequest.prototype.removeScriptTag = function () {
    this.headLoc.removeChild(this.scriptObj);  
}
JSONscriptRequest.prototype.addScriptTag = function () {
    this.headLoc.appendChild(this.scriptObj);
}

function addCompleteStrings(keyword, completeStrings, displayStrings)
{
	gCompleteStrings[keyword] = new Array(completeStrings, displayStrings);
}

setupAC = function(inputQuery, inputSearchWidth, submitUrl)
{
	gInputQuery = inputQuery;
	//gBtnSearch   = btnSearch;
	gInputSearch = inputSearchWidth;
	gSubmitUrl = submitUrl;
	initAC();
};

function initAC()
{
    gInputQuery.autocomplete = "off";
    gInputQuery.onblur = inputQueryOnblur;
    gInputQuery.onfocus = inputQueryOnfocus;
	
    if (gInputQuery.createTextRange)
        gInputQuery.onkeyup = new Function("return inputQueryOnkeyup(event); ");
    else
        gInputQuery.onkeyup = inputQueryOnkeyup;
    gInputQuery.onsubmit = inputQueryOnsubmit;
	
    gKeyword = gInputQuery.value;
    gKeywordPre = gKeyword;
	
    gCompDiv = document.createElement("DIV");
    gCompDiv.id = "completeDiv";
	
    gCompDivBorderLeftWidth = 1;
    gCompDivBorderTopWidth = 1;
    gCompDiv.style.borderRight = "black " + gCompDivBorderLeftWidth + "px solid";
    gCompDiv.style.borderLeft = "black " + gCompDivBorderLeftWidth + "px solid";
    gCompDiv.style.borderTop = "black " + gCompDivBorderTopWidth + "px solid";
    gCompDiv.style.borderBottom = "black " + gCompDivBorderTopWidth + "px solid";
	
    gCompDiv.style.zIndex = "2";
    gCompDiv.style.paddingRight = "0";
    gCompDiv.style.paddingLeft = "0";
    gCompDiv.style.paddingTop = "0";
    gCompDiv.style.paddingBottom = "0";
    gCompDiv.style.visibility = "hidden";
    gCompDiv.style.position = "absolute";
    gCompDiv.style.backgroundColor = "white";
	
    gCompIFrame = document.createElement("IFRAME");
    gCompIFrame.id = "completeIFrame";
    gCompIFrame.style.zIndex = "1";
    gCompIFrame.style.position = "absolute";
    gCompIFrame.style.display = "block";	
    gCompIFrame.style.visibility = "hidden";
    gCompIFrame.style.borderRightWidth = "0";
    gCompIFrame.style.borderLeftWidth = "0";
    gCompIFrame.style.borderTopWidth = "0";
    gCompIFrame.style.borderBottomWidth = "0";
	
    setCompDivPosition();
	
    document.body.appendChild(gCompDiv);
    document.body.appendChild(gCompIFrame);

    addCompleteStrings("", [], []);

    ua();
    setStyle(gCompDiv, "mAutoComplete")
			
    window.onresize = windowOnresize;
    //document.onkeydown = documentOnkeydown;
    document.onkeyPress = documentOnkeydown;
    gb();
  
    setTimeout("inputQueryValueChange()", 10*gTimeFactor);    
}

function inputQueryTemporaryBlur()
{
    gInputQueryBlured = true;
    gInputQuery.blur();
    setTimeout("inputQueryFocus();", 10*gTimeFactor)
}

function gb()
{
    if (document.createEventObject)
    {
        var a = document.createEventObject();
        a.ctrlKey = true;
        a.keyCode = 70;
        document.fireEvent("onkeypress", a)
    }
}

function documentOnkeydown(keydownEvent)
{
    if (!keydownEvent && window.event)
        keydownEvent = window.event;
    if (keydownEvent)
        gDocumentKeycode = keydownEvent.keyCode;
    if (keydownEvent && keydownEvent.keyCode == 8)
    {
    	//
    }
}

function windowOnresize()
{
    setCompDivPosition()
}

function setCompDivPosition()
{
    if (gCompDiv)
    {
        gCompDiv.style.left = absoluteLocation(gInputQuery, "offsetLeft") + 2 + "px";
        gCompDiv.style.top = absoluteLocation(gInputQuery, "offsetTop") + gInputQuery.offsetHeight + 2 +"px";
        gCompDiv.style.width = adjustOffsetWidth() + "px";
        if (gCompIFrame)
        {
            gCompIFrame.style.left = gCompDiv.style.left;
            gCompIFrame.style.top = gCompDiv.style.top;
            gCompIFrame.style.width = gCompDiv.style.width;
            gCompIFrame.style.height = gCompDiv.style.height
        }
    }
}

function adjustOffsetWidth()
{
    /*if (navigator && navigator.userAgent.toLowerCase().indexOf("msie") ==  - 1)
    {*/
        return gInputQuery.offsetWidth + gInputSearch;
    /*}
    else
    {
        return gInputQuery.offsetWidth
    }*/
}


function  inputQueryOnfocus(a)
{
    gInputQueryFocused = true
}

function inputQueryOnblur(blurEvent)
{
    //window.status=new Date()
	//alert()
	gInputQueryFocused = false;
    if (!blurEvent && window.event)
    {
        blurEvent = window.event;
    }
	
    if (!gInputQueryBlured)
    {
        hideCompleteDiv();
        if (gDocumentKeycode == 9) 
        {
            
			gBtnSearch.focus();
            gDocumentKeycode = -1;
        }
    }
    gInputQueryBlured = false;
	
}

inputQueryOnkeyup = function(keyEvent)
{
    gInputQueryKeycode = keyEvent.keyCode;
    gInputQueryValue =  gInputQuery.value;
    processUserInput()
};


inputQueryFocus = function()
{
    gInputQuery.focus()
};


function trimStr(str)
{
    for (var i = 0, trimedStr = "", crlf = "\n\r"; i < str.length; i++)
        if (crlf.indexOf(str.charAt(i)) ==  - 1)
            trimedStr += str.charAt(i);
        else
            trimedStr += " ";
    return trimedStr
}

function separateCompStringDiv(compStringDiv, styleClassName)
{
    var compStringSpan = compStringDiv.getElementsByTagName(gSpanTagName);
    if (compStringSpan)
    {
        for (var i = 0; i < compStringSpan.length; ++i)
        {
            if (compStringSpan[i].className == styleClassName)
            {
                var f = compStringSpan[i].innerHTML;
                if (f == "&nbsp;")
                    return "";
                else
                {
                    var str = trimStr(f);
                    return str
                }
            }
        }
    }
    else
    {
        return ""
    }
}

function separateCompStringDivC(compStringDiv)
{
    if (!compStringDiv)
        return null;
    return separateCompStringDiv(compStringDiv, "cAutoComplete")
}

function separateCompStringDivD(compStringDiv)
{
    if (!compStringDiv)
        return null;
    return separateCompStringDiv(compStringDiv, "dAutoComplete")
}

function hideCompleteDiv()
{
    document.getElementById("completeDiv").style.visibility = "hidden";
    document.getElementById("completeIFrame").style.visibility = "hidden"
}

function showCompleteDiv()
{
    document.getElementById("completeDiv").style.visibility = "visible";
    document.getElementById("completeIFrame").style.visibility = "visible";
    setCompDivPosition();
}

compRequest = function() 
{
    if (gKeywordPre != gKeyword)
    {
        if (!gSubmitting)
        {
            var escaped_keyword = encodeAndEscape(gKeyword), completeStrings = gCompleteStrings[gKeyword];                                                 
            if (completeStrings)
            {
                compResp( gKeyword, completeStrings[0], completeStrings[1])
            }
            else
            {
				gNotResponsedResquestNum++;
				var req  = gSubmitUrl + escaped_keyword; 
				var jsonReq = new JSONscriptRequest(req); 
				jsonReq.buildScriptTag(); 
				jsonReq.addScriptTag();
            }
            gInputQuery.focus()
        }
        gSubmitting = false
    }
    gKeywordPre = gKeyword;
    setTimeout("compRequest()", calcNextTimeoutInterval(gNotResponsedResquestNum));
    return true
};

compResp = function( keyword, completeStrings, displayStrings)
{
    if (gNotResponsedResquestNum > 0) 
        gNotResponsedResquestNum--;

    addCompleteStrings(keyword, completeStrings, displayStrings);
    if (keyword == gKeyword)
    {
        if (gTimerHideCompDiv)
        {
            clearTimeout(gTimerHideCompDiv);
            gTimerHideCompDiv = null;
        }
        gKeywordToDisplayCompStrings = keyword;
    }
    var completeDiv = gCompDiv;
    completeDiv.completeStrings = completeStrings;
    completeDiv.displayStrings = displayStrings;

    fillCompDiv(completeDiv, completeDiv.completeStrings, completeDiv.displayStrings);
    displayCompDiv(completeDiv, separateCompStringDivC);
    if (gCompStringDivNum > 0)
    {
        completeDiv.height = 16 * gCompStringDivNum + 4;
        gCompIFrame.height = completeDiv.height - 4
    }
    else
    {
        hideCompleteDiv()
    }
};


hideCompDiv = function()
{
    hideCompleteDiv();
    gTimerHideCompDiv = null
};

function processUserInput()
{
    if (gInputQueryKeycode == 40 || gInputQueryKeycode == 38)
        inputQueryTemporaryBlur();
    var length = selectionLength(gInputQuery), start = selectionStart(gInputQuery), inputValue = gInputQuery.value;

    //9=VK_TAB , 13=VK_RETURN , 16=VK_SHIFT ,17=VK_CONTROL ,18=VK_MENU(ALT),19=VK_PAUSE ,20=VK_CAPITAL 
    //27=VK_ESCAPE ,33=VK_PAGEUP ,34=VK_PAGEDOWN, 35=VK_END, 36=VK_HOME, 37=VK_LEFT, 38=VK_UP 
    //39=VK_RIGHT, 40=VK_DOWN, 44=VK_SNAPSHOT(PRINT SCREEN key), 112-123=F1-F12
    if ( gInputQueryKeycode != 9 
		&& gInputQueryKeycode != 13 
		&& !(gInputQueryKeycode >= 16 && gInputQueryKeycode <= 20) 
		&& gInputQueryKeycode != 27 
		&& !(gInputQueryKeycode >= 33 && gInputQueryKeycode<= 38) 
		&& gInputQueryKeycode != 40 && gInputQueryKeycode != 44 
		&& !(gInputQueryKeycode >= 112 && gInputQueryKeycode <= 123))
    {
        gKeyword = inputValue;
        if (gInputQueryKeycode != 39)//39=VK_RIGHT
        {
        	ia = inputValue
        }
    }

    if (notCompStringDivsNavigation(gInputQueryKeycode)
	&& gInputQueryKeycode != 0 
	&& gKeywordToDisplayCompStrings == gKeyword)
    {
        displayCompDiv(gCompDiv, separateCompStringDivC);
    }
	
    if (gKeywordToDisplayCompStrings != gKeyword && !gTimerHideCompDiv)
    {
	gTimerHideCompDiv = setTimeout("hideCompDiv()", 500*gTimeFactor)
    }
}

function inputQueryOnsubmit()
{
    gSubmitting = true;
    hideCompleteDiv();
    return true
}

inputQueryValueChange = function(a)
{
        if (gInputQueryFocused)
        {
            getSelectionInfo()
        }
        var curValue = gInputQuery.value;
        if (curValue != gInputQueryValue)   
        {
            gInputQueryKeycode = 0;
            processUserInput()
        }
        gInputQueryValue = curValue;
        setTimeout("inputQueryValueChange()", 10*gTimeFactor)
};

function encodeAndEscape(a)
{
    if (encodeURIComponent)
        return encodeURIComponent(a);
    if (escape)
    return escape(a)
}

function calcNextTimeoutInterval(notResponsedResquestNum)
{
    //return 20*gTimeFactor;
    var interval = 100*gTimeFactor;
    for (var i = 1; i <= (notResponsedResquestNum - 2) / 2; i++)
    {
        interval = interval * 2
    }
    interval = interval + 50*gTimeFactor;
    return interval
}

setTimeout("compRequest()", 10*gTimeFactor);

var compStringDivOnmousedown = function()
{
    setInputQueryValue(separateCompStringDivC(this));
   	//inputQueryOnsubmit()
	gSubmitting=true;
	hideCompleteDiv();
	//getSearchUrl();
	//document.getElementById("btnSearch").focus();
}

var compStringDivOnmousemove = function()
{
    if (window.event)
    {
        var x = window.event.x, y = window.event.y;
        if (x == gMousePositionX && y == gMousePositionY)
        {
            return
        }
        gMousePositionX = x;
        gMousePositionY = y
    }
    if (gSelectedCompStringDiv)
        setStyle(gSelectedCompStringDiv, "aAutoComplete");
    setStyle(this, "bAutoComplete");
    gSelectedCompStringDiv = this;
    for (var i = 0; i < gCompStringDivNumber; i++)
    {
        if (gCompStringDivs[i] == gSelectedCompStringDiv)
        {
            gSelectedCompStringDivIndex = i;
            break
        }
    }
}

var compStringDivOnmouseout = function()
{
    setStyle(this, "aAutoComplete")
};

function hilightCompStringDiv(compStringDivIndex)
{
    gKeyword = I;
    setInputQueryValue(I);

    if (!gCompStringDivs || gCompStringDivNumber <= 0)
        return ;
    showCompleteDiv();
    if (compStringDivIndex >= gCompStringDivNumber)
    {
        compStringDivIndex = gCompStringDivNumber - 1
    }
    if (gSelectedCompStringDivIndex !=  - 1 && compStringDivIndex != gSelectedCompStringDivIndex)
    {
        setStyle(gSelectedCompStringDiv, "aAutoComplete");
        gSelectedCompStringDivIndex =  - 1
    }
    if (compStringDivIndex < 0)
    {
        gSelectedCompStringDivIndex =  - 1;
        gInputQuery.focus();
        return
    }
    gSelectedCompStringDivIndex = compStringDivIndex;
    gSelectedCompStringDiv = gCompStringDivs.item(compStringDivIndex);
    setStyle(gSelectedCompStringDiv, "bAutoComplete");
    gKeyword = I;

    setInputQueryValue(separateCompStringDivC(gSelectedCompStringDiv))
}

function notCompStringDivsNavigation(keycode)
{
    if (keycode == 40) //VK_DOWN
    {
        hilightCompStringDiv(gSelectedCompStringDivIndex + 1);
        return false
    }
    else if (keycode == 38) //VK_UP
    {
        hilightCompStringDiv(gSelectedCompStringDivIndex - 1);
        return false
    }
    else if (keycode == 13 || keycode == 3)
    {
		if(window.navigator.userAgent.toLowerCase().indexOf("firefox")!=-1)
       		inputQueryOnsubmit(); hideCompleteDiv();

        return false
    }
    return true
}

function displayCompDiv(completeDiv, separateCompString) 
{
    var c = gInputQuery, hasStringToSelect = false;
    gSelectedCompStringDivIndex =  - 1;
    var compStringDivs = completeDiv.getElementsByTagName(gDivTagName), compStringDivNum = compStringDivs.length;
    gCompStringDivNumber = compStringDivNum;
    gCompStringDivs = compStringDivs;
    gCompStringDivNum = compStringDivNum;
    I = gKeyword;
    if (gKeyword == "" || compStringDivNum == 0)
    {
        hideCompleteDiv()
    }
    else
    {
        showCompleteDiv()
    }

    for (var i = 0; i < compStringDivNum; i++)
    {
        setStyle(compStringDivs.item(i), "aAutoComplete");
    }
	
    gSelectedCompStringDivIndex =  - 1;
    gSelectedCompStringDiv = null

}

function absoluteLocation(element, offset)
{
    var c = 0;
    while (element)
    {
        c += element[offset];
        element = element.offsetParent
    }
    return c
}

function selectionLength(inputQuery)
{
    var length =  - 1;
    if (inputQuery.createTextRange)
    {
        var textRange = document.selection.createRange().duplicate();
        length = textRange.text.length
    }
    else if (inputQuery.setSelectionRange)
    {
        length = inputQuery.selectionEnd - inputQuery.selectionStart
    }
    return length
}

function selectionStart(inputQuery)
{
    var start = 0;
    if (inputQuery.createTextRange)//IE
    {
        var textRange = document.selection.createRange().duplicate();
        textRange.moveEnd("textedit", 1);
        start = inputQuery.value.length - textRange.text.length
    }
    else if (inputQuery.setSelectionRange)//Firefox
    {
        start = inputQuery.selectionStart
    }
    else
    {
        start =  - 1
    }
    return start
}

function selectionEnd(inputQuery)
{
    if (inputQuery.createTextRange)//IE
    {
        var textRange = inputQuery.createTextRange();
        textRange.moveStart("character", inputQuery.value.length);
        textRange.select()
    }
    else if (inputQuery.setSelectionRange)//Friefox
    {
        inputQuery.setSelectionRange(inputQuery.value.length, inputQuery.value.length)
    }
}

function setStyle(element, styleClassName)
{
    ua();
    element.className = styleClassName;

    switch (styleClassName.charAt(0))
    {
        case "m":
            element.style.fontSize = "13px";
            element.style.fontFamily = "arial,sans-serif";
            element.style.wordWrap = "break-word";
            break;
        case "l":
            element.style.display = "block";
            element.style.paddingLeft = "3";
            element.style.paddingRight = "3";
            element.style.height = "16px";
            element.style.overflow = "hidden";
            break;
        case "a":
            element.style.backgroundColor = "white";
            element.style.color = "black";
            if (element.displaySpan)
            {
                element.displaySpan.style.color = "blue"
            }
            break;
        case "b":
            element.style.backgroundColor = "#3366cc";
            element.style.color = "white";
            if (element.displaySpan)
            {
                element.displaySpan.style.color = "white"
            }
            break;
			
        case "c":
            element.style.width = 50 + "%";
            if(window.navigator.userAgent.toLowerCase().indexOf("firefox")!=-1)
                	element.style.cssFloat = "left";
	     	else
            		element.style.styleFloat = "left";
            element.style.whiteSpace = "nowrap";
            element.style.overflow = "hidden";
            element.style.textOverflow = "ellipsis";
	     	element.style.fontSize = "12px";
	     	element.style.textAlign = "left";
            break;
        case "d":
            if(window.navigator.userAgent.toLowerCase().indexOf("firefox")!=-1)
                	element.style.cssFloat = "right";
	     else
            		element.style.styleFloat = "right";
            		
            element.style.width = 50 + "%";

            element.style.fontSize = "12px";
            element.style.textAlign = "right";
            element.style.color = "green";
            element.style.paddingTop = "2px"

            break
            
       
    }
}

function ua()
{
    u = 65;
		var a = 110, b = adjustOffsetWidth(), c = (b - a) / b * 100;
    u = c
}

function fillCompDiv(completeDiv, completeStrings, displayStrings)
{

    while (completeDiv.childNodes.length > 0)
        completeDiv.removeChild(completeDiv.childNodes[0]);
    for (var e = 0; e < completeStrings.length; ++e)
    {
        var f = document.createElement("DIV");
        setStyle(f, "aAutoComplete");
        f.onmousedown = compStringDivOnmousedown;
        f.onmousemove = compStringDivOnmousemove;
        f.onmouseout = compStringDivOnmouseout;
        var i = document.createElement("SPAN");
        setStyle(i, "lAutoComplete");

        i.style.height = gInputQuery.offsetHeight ;
		
        var h = document.createElement("SPAN");
        h.innerHTML = completeStrings[e];
        var l = document.createElement("SPAN");
        setStyle(l, "dAutoComplete");

        setStyle(h, "cAutoComplete");
        f.displaySpan = l;
        l.innerHTML = displayStrings[e];
        i.appendChild(h);
        i.appendChild(l);
        f.appendChild(i);
        completeDiv.appendChild(f)
    }
}


function setInputQueryValue(value)
{
    gInputQuery.value = value;
    gInputQueryValue = value;
}


function selectionNotChanged()
{
    var value= gInputQuery.value, start = selectionStart(gInputQuery), length = selectionLength(gInputQuery);
    return length == gSelectionStart && c == gSelectionLength && value == gSelectionValue
}

function getSelectionInfo()
{
    gSelectionValue = gInputQuery.value;
    gSelectionStart = selectionStart(gInputQuery);
    gSelectionLength = selectionLength(gInputQuery)
}

