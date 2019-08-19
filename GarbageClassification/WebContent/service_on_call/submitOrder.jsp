<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>确认订单页面</title>
	<link href="<c:url value='/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css">
    <script src="<c:url value='/js/jquery-3.4.1.min.js'/>" type="text/javascript"></script>
    <script src="<c:url value='/js/bootstrap.min.js'/>" type="text/javascript"></script>
    <script type="text/javascript" src="<c:url value='/js/ajax-lib.js'/>"></script>
	
</head>
<body>
<div class="jumbotron" style="margin-top: 10%">
    <div  style="width: 200px; height: 200px;border-radius: 50%; background-color: #76d876;display: inline-block;position: relative;margin-left: 2vw">
        <p class="glyphicon glyphicon-ok" style="color: white;font-size: 90px;width:200px;text-align: center;line-height: 200px;vertical-align: middle"></p></div>
    <h1 style="position: absolute;top: 32vh; left: 16vw">您的订单提交成功<span style="font-size: 20px;margin-left: 1vw">订单号为：${oid}</span></h1>
    <p style="position: absolute;top: 47vh; left: 29vw;font-size: 24px">Your order was submitted successfully</p>
    <br/><br/><br/><p style="margin-left: 3vw;display: inline-block">预计等待接收时间为：60s</p>
    <p style="display: inline-block;margin-left: 2vw" id="goback">您已等待时间：</p>
</div>
<h3 id="time" style="position: absolute;bottom: 2vh;right: 2vw;color: #4c4c4c">年月日时分秒 PM 星期1</h3>
</body>
<script type="text/javascript">
    /*定时器显示时间*/
    function disptime() {
        var time = new Date();
        var year = time.getFullYear();
        var month = time.getMonth();
        var day = time.getDate();
        var hour24 = time.getHours();
        var minute = time.getMinutes();
        var second = time.getSeconds();
        var AM_PM;
        var weekday = time.getDay();
        if(hour24 < 12){
            AM_PM = "AM";
        }else {
            AM_PM = "PM";
            var hour = hour24 - 12;
        }
        var week;
        switch(weekday){
            case 0: week = "星期一";break;
            case 1: week = "星期二";break;
            case 2: week = "星期三";break;
            case 3: week = "星期四";break;
            case 4: week = "星期五";break;
            case 5: week = "星期六";break;
            case 6: week = "星期日";break;
        }
        document.getElementById("time").innerHTML = year + "年" + (month+1) + "月" + day + "日" + "&nbsp&nbsp&nbsp&nbsp&nbsp" +  hour24 + ":" + minute +":" + second + "&nbsp&nbsp&nbsp&nbsp&nbsp" + AM_PM + "&nbsp&nbsp&nbsp&nbsp&nbsp" + week
    }
    document.getElementById("time").innerHTML = "" + setInterval("disptime()",1000);
    
	/*预计等待时间*/
    var goback = document.getElementById("goback");
    var seconds = 0;
    function change() {
        seconds ++;
        goback.innerHTML = "您已等待时间：" + seconds + "s";
    }
    var wait = setInterval("change()",1000);
    
    //返回其他页面
    function back(){
    	location.href="/GarbageClassification/service_on_call/user.jsp";
    }
    
    
	/* 检查数据库状态是否改变 */
	function checkState(){
		var oid = "${oid}";
		ajax({
			url:"/GarbageClassification/CallFormServlet?method=checkState&oid="+oid,
			params:null,
			type:"TEXT",
			callback:function(data){
				if(data == "true"){
				//alert("if进来了");
					clearInterval(wait);
					goback.innerHTML="您的订单已被受理，即将返回首页！！";
					setTimeout("back()",3000);
				}
			}
		})
	}
	setInterval("checkState()",10000);
</script>
</html>