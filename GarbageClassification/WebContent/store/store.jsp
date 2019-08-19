<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="-1">
<!-- <link type="text/css" href="storeCss/storeCss.css" rel="stylesheet" /> -->

<script type="text/javascript" src="../js/ajax-lib.js"></script>
<style type="text/css">
/*整体背景*/
body {
	background-color: #bb1a39;
}
/*右侧菜单*/
#aside {
	margin-right: 2vw;
	width: 8vw;
	text-align: right;
	position: fixed;
	top: 50vh;
	right: 0;
}

#aside>a {
	margin-top: 2vh;
	display: block;
}

#aside>a:hover {
	text-decoration: none;
}

#aside>a>span {
	color: #fcfcff;
	font-size: 12px;
	display: inline;
}

#aside>a>span>img {
	border-radius: 50%;
	width: 40px;
	height: 40px;
}

#aside>a>span:hover {
	color: rgba(0, 0, 0, 0.68);
}

#aside>a>label {
	margin-bottom: 2vh;
	color: black;
	font-size: 12px;
}

.btn-group>.btn:first-child {
	margin-left: 10vw;
	border-radius: 0%;
}

.btn-group>.btn:nth-child(2) {
	margin-left: 5vw;
	border-radius: 0%;
}
/*商品*/
table {
	margin: 0 auto;
}

td {
	margin: 20px 0 0 20px;
	padding: 5px;
	display: inline-block;
	border: 1px solid black;
	width: 580px;
	height: 550px;
	background-color: white;
	text-align: center;
}

td>p {
	margin: 0 auto;
	text-align: center;
	font-size:10px;
	width: 10vw
}

td>h4{
	margin: 5px auto;
}

td button:first-of-type {
	margin: 1vh 4vw 0 4vw;
	
}
td button{
	color: red;
	
} 
</style>
<title>商城</title>
</head>
<body>
	<!--右侧导航-->
	<header class="bs-docs-nav navbar navbar-static-top" id="top"
		style="min-height: 0px; margin-bottom: 0px;"></header>
	<div id="aside">

		<a href="#top" id="backtop"> <span id="topItem"></span> <span><img
				src="Image/backtop.png"></span>
		</a> 
		<a href="<c:url value='/main/home.jsp' />" id="home"> <span id="homeItem"></span> <span><img
				src="Image/home.png"></span>
		</a> 
		
		
		<a href="<c:url value='/ProvinceAndCityServlet?method=myProvince' />" id="cart"> <span id="cartItem"></span> <span><img
				src="Image/cart.png"></span>
		</a> <a href="<c:url value='/store/userOrdernav.jsp' />" id="person"> <span id="personItem"></span> <span><img
				src="Image/person.png"></span>
		</a>
	</div>
	<!--商品-->
	<div>
		<table id="table">
			
		</table>
	</div>
</body>
<script type="text/javascript">
/*<!--右侧导航-->*/
var backtop = document.getElementById("backtop");
var topItem = document.getElementById("topItem"); 
var home = document.getElementById("home");
var homeItem = document.getElementById("homeItem"); 
var cart = document.getElementById("cart");
var cartItem = document.getElementById("cartItem");
var person = document.getElementById("person");
var personItem = document.getElementById("personItem");
backtop.onmouseover = function() {
	topItem.innerHTML = "返回顶部";
}
backtop.onmouseout = function() {
	topItem.innerHTML = "";
}
home.onmouseover = function() {
	homeItem.innerHTML = "返回首页";
}
home.onmouseout = function() {
	homeItem.innerHTML = "";
}

cart.onmouseover = function() {
	cartItem.innerHTML = "购物车"
}
cart.onmouseout = function() {
	cartItem.innerHTML = ""
}

person.onmouseover = function() {
	personItem.innerHTML = "个人中心"
}
person.onmouseout = function() {
	personItem.innerHTML = ""
}

 
    
window.onload = function() {
	var table = document.getElementById("table");
	var tr = document.createElement("tr");
	
	ajax({
		url : "/GarbageClassification/GoodsServlet?method=getAllGoods",
		params : null,
		type : "JSON",
		callback : function(data) {
			var count = data.length;
			for (var i = 0; i < data.length; i++) {
				
				var tr = document.createElement("tr");
				for (var j = 0; j < 2; j++) {
					var td = document.createElement("td");
					var h4 = document.createElement("h4");
					var p = document.createElement("p");
					var purchase = document.createElement("span");
					var exchange = document.createElement("span");
					
					if(data[i] != null){	
					td.innerHTML = "<img src='" + data[i].image + "' width='405' height='440'/>";
					h4.innerHTML = data[i].gname;
					p.innerHTML = data[i].detial;
					var text1 = "<button onclick=\"window.location.href='<c:url value='/ProvinceAndCityServlet?method=province&count=1&gid="+ data[i].gid + "' />'\">加入购物车</button>";
					var text2 = "<button onclick=\"window.location.href='<c:url value='/ProvinceAndCityServlet?method=buy&count=1&gid="+ data[i].gid + "' />'\">立即购买</button>";
					purchase.innerHTML = text1;
					exchange.innerHTML = text2;
					i++;
					td.appendChild(h4);
					td.appendChild(p);
					td.appendChild(exchange);
					td.appendChild(purchase);
					tr.appendChild(td);			
					}
				}
					table.appendChild(tr);
					i--;
					
			}
		}
	});
}

</script>
</html>