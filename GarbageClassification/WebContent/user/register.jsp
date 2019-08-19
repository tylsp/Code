<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css'/>">
<%-- <link rel="stylesheet" href="<c:url value='/user/css/register.css'/>"> --%>
<script src="<c:url value='/js/ajax-lib.js'/>"></script>
<title>Register</title>
</head>
<body>
<style type="text/css">
@charset "UTF-8";

* {
	margin: 0;
	padding: 0;
	font-family: 'montserrat', sans-serif; /*字体*/
}

html,body{
	margin:0;
	padding:0;
	}

body {
	margin: 0;
	padding: 0;
}

/* .top {
	background-image: url("../image/brand.png");
	background-size: 22%;
	background-repeat: no-repeat;
	background-position: left; 位置在左
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

.login-box {
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

.login {
	width: 300px;
	height: auto;
	margin: 0 auto;
	margin-top: 30px;
	position: relative;
}

.login1 {
	position: absolute;
	
}

.login-font {
	color:white;
	font-weight: 400;
	text-align: center;
}

.txt {
	width: 300px;
	background: #dbdbdb;
	border: 1px solid white;
	padding: 5px 10px;
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
	padding: 8px;
	margin-top: 20px;
	border-radius: 6px;
	cursor: pointer;
}
a.{
	margin-left:200px;
}

.login-existing{	
	float: left;
	font-weight: 200;
	font-size: 16px;
	margin-top: 10px;
	color: rgba(0, 0, 0, 0);
}

.back1{
	width: 50px;
	height: 25px;
	background: #2c3e50;
	border-radius: 4px;
	margin-top: 10px;
	float: right;
	color:white;
	font-size: 14px;
}
</style>
	<%--  <!-- 头部栏 -->
	<div class="top">
		<div>
			<ul class="nav nav-tabs">
				<li role="presentation" class="home"><a
					href="<c:url value='/main/home.jsp'/>">首页</a></li>
				<li role="presentation" class="mycenter"><a href="#">个人中心</a></li>
				<li role="presentation" class=""><a href="#">商城</a></li>
			</ul>
		</div>
	</div> --%>
	<div class="login-box">
		<div class="login">
			<div class="login1">
			
				<div class="login-font">
					<h2>用户注册</h2>
				</div>
				
				<form action="<c:url value='/UserServlet'/>" method="post">
				
				<input type="hidden" name="method" value="addUser" />
				
				<input class="txt" type="text" name="username" id="username" maxlength="10" placeholder="用户名：长度1-10的汉字、字母或数字" />
				<span class=a id=mesUser></span> 
				
				<input class="txt" type="password" name="password1" id="password1" maxlength="15" placeholder="密码：首位为字母，长度为6-15" /> 
				<span class="a" id="mesPass"></span> 
				
				<input class="txt" type="password" name="password2" id="password2" maxlength="15" placeholder="确认密码" /> 
				<span class="a" id="mesPass2"></span> 
				
				<input class="txt" type="text" name="phone" id="phone" maxlength="11" placeholder="手机号" />
				<span class="a" id="mesPhone"></span> 
				
				<input class="txt" type="text" name="verification" placeholder="验证码" /> 
				
				<input class="login-dl" type="button" value="获取验证码" id="getVerification" />
				<span class="a" id="verificationHint">${messageCode}</span> 
								
				<input class="login-dl" type="submit" id="button" value="立即注册" /> 
				
				<input class="back1" type="button" value="返回" onclick="javascript:history.back()" />
				
				<div class="login-existing">
					<a href="login.jsp" style="color: white">已有账号？点击登陆</a>
				</div>

				</form>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	/*
	 *手机号处理
	 */
	//获得手机号
	var buttonVerification = document.getElementById("getVerification");//获取验证码的按钮
	var phone = document.getElementById("phone");
	//设置正则表达式
	var reg = /^1([38]\d|5[0-35-9]|7[3678])\d{8}$/;
	var mesPhone = document.getElementById("mesPhone");
	var button = document.getElementById("button");
	//判断手机号是否输入正确
	phone.onblur = function() {
		/*
		检验手机号码是否被注册过
		 */
		ajax({
			url : "<c:url value='/UserServlet?method=getUserByPhone&phone='/>"
					+ phone.value,

			params : null,
			callback : function(result) {
				mesPhone.style.color = "red";
				mesPhone.style.fontSize = "12px";
				mesPhone.innerHTML = result;
				if (result == "") {
					if (reg.test(phone.value)) {
						mesPhone.style.color = "cornflowerblue";
						mesPhone.style.fontSize = "12px";
						mesPhone.innerHTML = "OK！";
						button.disabled = false;
						buttonVerification.disabled = false;
					} else {
						mesPhone.style.color = "red";
						mesPhone.style.fontSize = "12px";
						mesPhone.innerHTML = "手机号码错误！";
						button.disabled = true;
						buttonVerification.disabled = true;
					}
				} else {
					button.disabled = true;
				}
			}
		});
	};
	/*
	 密码处理
	 */
	var password = document.getElementById("password1");
	var regPass = /^[a-zA-Z]\w{5,15}$/;
	var mesPass = document.getElementById("mesPass");
	password.onblur = function() {
		if (regPass.test(password.value)) {
			mesPass.style.color = "cornflowerblue";
			mesPass.style.fontSize = "12px";
			mesPass.innerHTML = "OK！";
			button.disabled = false;
		} else {
			mesPass.style.color = "red";
			mesPass.style.fontSize = "12px";
			mesPass.innerHTML = "密码格式不正确！";
			button.disabled = true;
		}
	};

	/*
	 处理用户的确认密码
	 */
	var pssword2 = document.getElementById("password2");
	var mesPass2 = document.getElementById("mesPass2");
	pssword2.onblur = function() {
		if (password.value == pssword2.value && password.value != "") {
			mesPass2.style.color = "cornflowerblue";
			mesPass2.style.fontSize = "12px";
			mesPass2.innerHTML = "OK！";
			button.disabled = false;
		} else {
			mesPass2.style.color = "red";
			mesPass2.style.fontSize = "12px";
			mesPass2.innerHTML = "确认密码错误！";
			button.disabled = true;
		}
	};

	/*
	 对用户名进行处理
	 */
	var username = document.getElementById("username");
	//用户名的规定
	var regUser =/^[\u4e00-\u9fa5_a-zA-Z0-9]{1,10}$/;
	var mesUser = document.getElementById("mesUser");
	username.onblur = function() {
		/*
		对用户名的注册进行验证
		 */
		ajax({
			url : "<c:url value='/UserServlet?method=getUserByUsername&username='/>"
					+ username.value,
			params : null,
			callback : function(result) {
				mesUser.style.color = "red";
				mesUser.style.fontSize = "12px";
				mesUser.innerHTML = result;
				if (result == "") {
					if (regUser.test(username.value)) {
						mesUser.style.color = "cornflowerblue";
						mesUser.style.fontSize = "12px";
						mesUser.innerHTML = "OK！";
						button.disabled = false;
					} else {
						mesUser.style.color = "red";
						mesUser.style.fontSize = "12px";
						mesUser.innerHTML = "用户名格式不正确！";
						button.disabled = true;
					}
				} else {
					button.disabled = true;
				}

			}

		});
	}

	/*
	 验证码按钮的处理
	 */
	var wait = 60;
	var verificationHint = document.getElementById("verificationHint");//验证码提示对象
	buttonVerification.onclick = function() {
		//发送短信
		ajax({
			url : "<c:url value='/UserServlet?method=sendMessage&phone='/>"
					+ phone.value,
			params : null,
			callback : function(result) {
				verificationHint.style.color = "white";
				verificationHint.style.fontSize = "12px";
				verificationHint.innerHTML = result;
			}
		});
		time(this);
	}
	function time(ele) {
		//判断时间
		if (wait == 0) {
			buttonVerification.disabled = false;
			buttonVerification.value = "获取验证码";
			wait = 60;
		} else {
			buttonVerification.disabled = true;
			buttonVerification.value = wait + "秒后可获取验证码";
			wait--;
			setTimeout(function() {
				time(ele);
			}, 1000);
		}
	}
</script>
</html>