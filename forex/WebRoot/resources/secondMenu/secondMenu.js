var tagNum;

function setTagNum(num) {
	tagNum = num;
}

function switchTag(tag,content)
{
	for(i=1; i <tagNum + 1; i++)
	{
		if ("tag"+i==tag)
		{
			document.getElementById(tag).getElementsByTagName("a")[0].className="selectli";
			document.getElementById(tag).getElementsByTagName("a")[0].getElementsByTagName("span")[0].className="selectspan";
		}else{
			document.getElementById("tag"+i).getElementsByTagName("a")[0].className="";
			document.getElementById("tag"+i).getElementsByTagName("a")[0].getElementsByTagName("span")[0].className="";
		}
	}
}