<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fm" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="<c:url value='/js/ajax-lib.js'/>"></script>
<style type="text/css">
.aitem {
	margin-left: 170px;
	color: white;
	text-decoration: none;
	text-align: center;
	line-height: 75px;
	vertical-align: middle;
}
#item {
	margin-left: 0px;
}
#side {
	position:absolute;
	left: 0px;
	top: 85px;
	bottom: 8px;
	width: 100px;
	background-color: #f7f7f7;
	z-index: 888;
}

.cat {
	position: relative;
	width:100px;
	margin-bottom: 3px;
	height: 50px;
	text-align: center;
	box-sizing: border-box;
	line-height: 50px;
}

.cat::before {
	content: "";
	position: absolute;
	left: 0px;
	top: 0px;
	width: 3px;
	height: 100%;
	transition: all 150ms;
}

#cat1::before {
	background-color: #956c58;
}

#cat2::before {
	background-color: #2d2926;
}

#cat3::before {
	background-color: #003c71;
}

#cat4::before {
	background-color: #f9423a;
}

#cat5::before {
	background-color: #88ae39;
}

.cat.active::before {
	width: 100%;
}

.cat span {
	position: relative;
	color: #999999;
	font-size: 15px;
	transition: all 150ms;
}

#side .active span a {
	text-decoration: none;
	color: #ffffff;
	font-weight: bold;
}

.iframe {
	display: inline-block;
	border: 1px solid #eaeaea;
	border-radius: 15px;
	background-color: #f5f5f5;
	width: 760px;
	height: 700px;
}

#iframe1 {
	margin-left: 100px;
}

#iframe2 {
	margin-left: 2px;
}
</style>
<title>论坛首页</title>
</head>
<body>
	<div id="header">
		<div id="navigation"
			style="width: 100vw; height: 75px; background-color: #ffc35a;">
			<a href="<c:url value='/main/home.jsp'/>" class="aitem">首页</a>
		</div>
	</div>
	<div id="side">
		<div id="cat1" class="cat active">
			<span id="zero"></span>
		</div>
		<div id="cat2" class="cat active">
			<span id="one"></span>
		</div>
		<div id="cat3" class="cat active">
			<span id="two"></span>
		</div>
		<div id="cat4" class="cat active">
			<span id="three"></span>
		</div>
		<div id="cat5" class="cat active">
			<span id="four"></span>
		</div>
	</div>
	<iframe name="content" class="iframe" id="iframe1"></iframe>
	<iframe name="content2" class="iframe" id="iframe2"></iframe>
</body>
<script type="text/javascript">
    var zero =document.getElementById("zero"),
            one =document.getElementById("one"),
            two =document.getElementById("two"),
            three =document.getElementById("three");
    four =document.getElementById("four");
    var items = new Array();
    window.onload = function(){
        ajax({
            url:"/GarbageClassification/ForumServlet?method=getAllTopic",
            params:null,
            type:"JSON",
            callback:function(data){
                for(var item in data){
                    items[item] = "<a target='content' href='/GarbageClassification/ForumServlet?method=getPostingByTid&tid="+data[item].tid+"'>"+data[item].tname+"</a>";
                }
                one.innerHTML = items[0];
                two.innerHTML = items[1];
                three.innerHTML = items[2];
                zero.innerHTML =  "<a target='content' href='/GarbageClassification/ForumServlet?method=getAllPosting'>所有帖子</a>";
                four.innerHTML =  "<a target='content' href='/GarbageClassification/forum/addPosting.jsp'>我要发帖</a>";
            }
        });
    }
</script>
</html>