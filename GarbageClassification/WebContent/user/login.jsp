<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/bootstrap.min.css'/>">
<%-- <link rel="stylesheet" type="text/css" href="<c:url value='/user/css/login.css'/>"> --%>
<script type="text/javascript" src="<c:url value='/js/ajax-lib.js'/>"></script>
<title>Login</title>
</head>
<body>
<style>
@charset "UTF-8";

* {
	margin: 0;
	padding: 0;
	font-family: 'montserrat', sans-serif; /*字体*/
}

body {
	margin: 0;
	padding: 0;
}

/* .top {
	background-image: url("../image/brand.png");
	background-size: 22%;
	background-repeat: no-repeat;
	background-position: left; 位置再左
	background-color: black;
	width: 1518px;
	height: 60px;
}

.nav-tabs {
	float: right;
	font-size: 25px;
	border: 0;
	padding-bottom: 0px;
} */

.login-box1 {
	position: absolute;
	width:100%;
	height:100%;
	background-image:linear-gradient(45deg, #9fbaa8, #31354c);
	/*设置颜色渐变 方向(0deg垂直向上) 起点颜色 终点颜色*/
	transition: 0.4s;
	/*过度效果  property duration timing-function delay 默认属性:all 0 ease 0*/
	background-color:rgba(255, 255, 255, 0.6);
	backgroud-size:100%;
	
}

.login1 {
	width: 300px;
	height: auto;
	margin: 0 auto;
	margin-top: 100px;
	position: relative;
}

.login11 {
	position: absolute;
	color: white;
}

.login-font {
	font-weight: 400;
	text-align: center;
}

.loginz {
	font-weight: 300;
	font-size: 23px;
	text-align: center;
}

.txt {
	width: 300px;
	background: #ffffff28;
	border: 1px solid white;
	padding: 10px 20px;
	color: white;
	outline: none;
	margin: 10px 0;
	border-radius: 6px;
	text-align: center;
}

.login-dl {
	width: 300px;
	background: #2c3e50;
	border: 0;
	color: white;
	padding: 13px;
	margin-top: 20px;
	border-radius: 6px;
	cursor: pointer;
}

/* .login-forget {
	display: block;
	float: left;
	font-weight: 200;
	font-size: 17px;
	margin-top: 10px;
	color: rgba(0, 0, 0, 0);
} */

.login-ze {
	float: right;
	margin-top: 8px;
	font-size: 15px;
}

</style>
	<%-- <div class="top">
    <div>
        <ul class="nav nav-tabs">
            <li role="presentation" class="home"><a href="<c:url value='/main/home.jsp'/>">首页</a></li>
            <li role="presentation" class="person"><a href="#">个人中心</a></li>
            <li role="presentation" class=""><a href="#">商城</a></li>
        </ul>
    </div>
</div> --%>
	<div class="login-box1">
		<div class="login1">
			<div class="login11">
				<div class="login-font">
				
					<h1>用户登录</h1>
					
				</div>
				
				<div class="loginz">			
					<a href="#" id="zhlogin" style="color:cornflowerblue" type="button">账号登录</a> 
					
					<a href="#" id="plogin" style="color:cornflowerblue"  type="button">手机登录</a>
				</div>
				
                <!--用户名登陆 -->
                
				<form action="<c:url value='/UserServlet?method=loginByUsername'/>" method="post" id="userlogin" style="display: block">
				
					<input type="hidden" name="method" value="loginByUsername" /> 
					
					<input class="txt" type="text" name="username" placeholder="用户名" />					
					<span style="color:red">${msg}</span>
					
					<input class="txt" type="password" name="password" placeholder="密码" />
					 					
					<input class="login-dl" type="submit" value="登录" /> 
					
					<div class="login-ze">
					
					<a href="register.jsp" style="color: white">没有账号？点击注册</a>
					
					</div>										
					
				<!-- <div class="login-forget">
					 <a href="#" style="color: white">忘记密码？</a>
					 </div>  -->
				</form>
				
                <!-- 手机验证码登陆 -->
                
				<form action="<c:url value='/UserServlet?method=loginByPhone'/>" method="post" id="phonelogin" style="display: none">
				
					<input type="hidden" name="method" value="loginByPhone" /> 
					
					<input class="txt" type="text" name="phone" id="phone" placeholder="手机号" />
					<span style="color:red">${pmsg}</span>
					
					<input class="txt" type="text" name="verificode" placeholder="验证码" />
					
					<input class="login-dl" type="button" value="获取验证码" id="vCode" />
					<span id="message"></span>
					
					<input class="login-dl" type="submit" value="登录" /> 
					
					<div class="login-ze">
					
					    <a href="register.jsp" style="color: white">没有账号？点击注册</a>
					    
					</div>
					
					<!-- <div class="login-forget">
						<a href="#" style="color: white">忘记密码？</a>
					</div> -->
					
				</form>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
var waitTime = 60;
var vCode = document.getElementById("vCode");
var phone = document.getElementById("phone");
var message = document.getElementById("message");
vCode.onclick = function() {
	var value = phone.value;
	ajax({
		url : "<c:url value='/VerificodeServlet?method=getVerifiy&phone='/>" + value,
		params : null,
		
		callback : function(data) {
		message.innerHTML = data;
		}
	});
	time(this, value);
}
function time(ele, value) {
	if (waitTime == 0) {
		ele.disabled = false;
		ele.value = "获取验证码";
		waitTime = 60;// 恢复计时
	} else {
		ele.disabled = true;
		ele.value = waitTime + "秒后可以重新发送";
		waitTime--;
		setTimeout(function() {
			time(ele)// 关键处-定时循环调用
		}, 1000)
	}
}
	var zhlogin = document.getElementById("zhlogin");
	var plogin = document.getElementById("plogin");
	var userlogin = document.getElementById("userlogin");
	var phonelogin = document.getElementById("phonelogin");

	zhlogin.onclick = function() {
		userlogin.style.display = "block";
		phonelogin.style.display = "none";
	}
	plogin.onclick = function() {
		userlogin.style.display = "none";
		phonelogin.style.display = "block";
	}
</script>
</html>