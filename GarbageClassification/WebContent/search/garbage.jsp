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
#header {
    position: fixed;
    left: 0px;
    top: 0px;
    width: 100%;
    height: 50px;
    line-height: 20px;
    background-color: #ffffff;
    z-index: 1000;
    text-align: center;
}

#header img {
    margin-top: 15px;
    margin-right: 5px;
    height: 30px;
    line-height: 20px;
     vertical-align: middle;
}

#header span {
    display: inline-block;
    margin-top: 21px;
    font-size: 18px;
    font-weight: bold;
    line-height: 20px;
    vertical-align: middle;
}

#searchWrapper {
	position: fixed;
    left: 0px;
    top: 50px;
	width: 100%;
	height: 60px;
	background-color: #ffffff;
    box-shadow: 0px 0px 5px 2px rgba(0, 0, 0, 0.08);
    box-sizing: border-box;
    z-index: 999;
}

#cancelSearch {
	position: absolute;
	left: 15px;
	top: 50%;
	width: 12px;
	height: 12px;
	border-left: 3px solid #cccccc;
	border-bottom: 3px solid #cccccc;
	transform: rotate(45deg) translateY(-50%);
	opacity: 0;
    transition: all 300ms;
}

#cancelSearch.active {
	opacity: 1;
}

#searchInputWrapper {
    position: absolute;
    left: 10px;
    top: 10px;
    right: 60px;
    bottom: 10px;
    transition: all 200ms;
}

#searchInputWrapper form {
	width: 100%;
	height: 100%;
}

#search {
	padding: 0px 0px 0px 38px;
	width: 100%;
	height: 100%;
	border: none;
	border-radius: 20px;
	background-color: #f5f5f5;
	box-sizing: border-box;
	color: #808080;
	font-size: 16px;
}
#search::-webkit-search-cancel-button {
	display: none;
}
#clearSearchText {
	display: none;
    position: absolute;
    right: 12px;
    top: 12px;
    width: 16px;
    height: 16px;
    border-radius: 50%;
    background-color: #cccccc;
}
#clearSearchText::before, #clearSearchText::after {
    content: "";
    position: absolute;
    left: 50%;
    top: 50%;
    width: 64%;
    height: 2px;
    border-radius: 1px;
    background-color: #f5f5f5;
}

#clearSearchText::before {
    transform: translate(-50%, -50%) rotate(45deg);
}

#clearSearchText::after {
    transform: translate(-50%, -50%) rotate(-45deg);
}

/*搜索图标*/
#magnifier {
    position: absolute;
    left: 12px;
    top: 12px;
    width: 16px;
    height: 16px;
}
/*搜索按钮*/
#searchBtn {
	position: absolute;
    top: 20px;
    right: 10px;
	width: 40px;
	height: 20px;
	color: #333333;
	font-size: 16px;
	line-height: 20px;
	text-align: center;
    transition: all 300ms;
}
#side {
    position: fixed;
    left: 0px;
    top: 110px;
    bottom: 0px;
    width: 80px;
    background-color: #f7f7f7;
    z-index: 888;
}

.cat {
	position: relative;
	width: 100%;
	margin-bottom: 1px;
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

.cat.active::before {
    width: 100%;
}

.cat span {
	position: relative;
	color: #999999;
	font-size: 15px;
	transition: all 150ms;
}

#side .active span a{
	text-decoration:none;
	color: #ffffff;
	font-weight: bold;
}
</style>
<title>垃圾分类详情</title>
</head>
<body>
<!-- 标题 -->
<div id="header">
    <img src="image/tong.jpg">
    <span>垃圾分类指南</span>
</div>

<!-- 搜索 -->
<div id="searchWrapper">
	<span id="searchBtn">搜索</span>
    <div id="searchInputWrapper">
		<form id="searchForm" action="<c:url value='/GarbageServlet'/>" target="content">	
			<input type="hidden" name="method" value="selectByName">
		    <input type="search" placeholder="搜索垃圾查看对应分类" autocomplete="off" name="search" id="search">
            <img src="image/search.jpg" id="magnifier" />
            <span id="clearSearchText"></span>
        </form>
	</div>
</div>
<div id="side">
	<div id="cat1" class="cat active">
        <span id="one"></span>
    </div>
    <div id="cat2" class="cat active">
        <span id="two"></span>
    </div>
    <div id="cat3" class="cat active">
        <span id="three"></span>
    </div>
    <div id="cat4" class="cat active">
        <span id="four"></span>
    </div>
</div>
<iframe name="content"  width="100%" height="780"></iframe>
</body>
<script type="text/javascript">
 var one =document.getElementById("one"),
     two =document.getElementById("two"),
     three =document.getElementById("three"),
     four =document.getElementById("four");
  var items = new Array();
 window.onload = function(){
	 ajax({
         url:"/GarbageClassification/GarbageServlet?method=getAllType",
         params:null,
         type:"JSON",
         callback:function(data){
               for(var item in data){
            	  items[item] = "<a  target='content' href='/GarbageClassification/GarbageServlet?method=selectByType&tid="+data[item].tid+"'>"+data[item].name+"</a>";
               }
              one.innerHTML = items[0];
              two.innerHTML = items[1];
              three.innerHTML = items[2];
              four.innerHTML = items[3];
             }
        });
 }
 var searchBtn = document.querySelector('#searchBtn'),
 searchForm = document.querySelector('#searchForm');
 searchBtn.onclick = function(){
	    searchForm.submit();
}
</script>
</html>