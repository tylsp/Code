<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>模态框</title>
    <link type="text/css" rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.4.1.min.js" type="text/javascript"></script>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>
</head>
<body>
<div class="jumbotron" style="margin-top: 10%">
    <div  style="width: 200px; height: 200px;border-radius: 50%; background-color: #76d876;display: inline-block;position: relative;margin-left: 2vw">
        <p class="glyphicon glyphicon-ok" style="color: white;font-size: 90px;width:200px;text-align: center;line-height: 200px;vertical-align: middle"></p></div>
    <h1 style="position: absolute;top: 32vh; left: 16vw">捐款成功，感谢您的支持</h1>
    <p style="position: absolute;top: 44.5vh; left: 27vw;font-size: 24px">Donating successfully</p>
    <p style="text-align: center;" id="goback">5秒后返回捐款界面</p>
</div>

</body>
<script type="text/javascript">
    var goback = document.getElementById("goback");
    var seconds = 5;
    function chang() {
        seconds --;
        goback.innerHTML = seconds + "秒后返回服务界面";
        if(seconds === 0){
            location.href="common_good/PublicBenefit.jsp";
        }
    }
    setInterval("chang()",1000);
</script>
</html>
