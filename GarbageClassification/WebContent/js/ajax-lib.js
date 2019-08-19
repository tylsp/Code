function getXMLHttpRequest() {
	var xmlhttp;
	try {
		xmlhttp = new XMLHttpRequest();
	} catch (e) {
		try {
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		} catch (e) {
			alert("当前浏览器版本过低，请更换其他高版本的浏览器！");
		}
	}
	return xmlhttp;
}

function ajax(option) {
	var result;
	var xmlhttp = getXMLHttpRequest();
	if (option.method == undefined) {
		option.method = "GET";
	}
	if (!option.aysc) {
		option.aysc = true;
	}
	xmlhttp.open(option.method, option.url, option.aysc);
	if (option.method == "POST") {
		xmlhttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
	}
	xmlhttp.send(option.param);
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			if (option.type == undefined || option.type == ""
					|| option.type == "TEXT") {
				result = xmlhttp.responseText;
			} else if (option.type == "JSON".trim()) {
				result = eval('(' + xmlhttp.responseText + ')');
			} else if (option.type == "XML") {
				result = xmlhttp.responseXML;
			}
			option.callback(result);
		}
	}
}