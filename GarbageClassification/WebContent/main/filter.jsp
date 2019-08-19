<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
     <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>返回登录</title>
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/bootstrap.css'/>">
    <script src="<c:url value='/js/jquery-3.4.1.min.js'/>" type="text/javascript"></script>
    <script src="<c:url value='/js/bootstrap.min.js'/>" type="text/javascript"></script>
</head>
<body>
<div class="jumbotron" style="margin-top: 20vh">
    <h1 style="margin-left: 5vw">请先登录</h1>
    <p style="margin-left: 5vw">您尚未登录，<span id="back">3秒后返回登录界面！</span></p>
</div>
</body>
<script type="text/javascript">
    var back = document.getElementById("back");
    var seconds = 3;
    function chang() {
        seconds --;
        back.innerHTML = seconds + "秒后返回登录界面！";
        if(seconds === 0){
            location.href="/GarbageClassification/user/login.jsp";
        }
    }
    setInterval("chang()",1000);
</script>
</html>