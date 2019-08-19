<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fm" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/garbageDetails.css">
<title>垃圾分类详情</title>
<style>
#body{
	position: relative;
}
.list-wrapper {

	padding-left: 80px;
	background-color: #ffffff;
	overflow-y: auto;
	-webkit-overflow-scrolling: touch;
}

.section {

	width:1250px;
	display: none;
	margin: 0px;
	padding-top: 120px;
	padding-left:70px;
	padding-bottom: 34px;

}

.cat-intro-wrapper {
	margin: 0px 12px;
	padding-top: 15px;
	padding-bottom: 8px;
	border-radius: 10px;
}

.cat-intro-wrapper-0 {
	background-color: rgba(102, 64, 53, 0.1);
}

.section-icon {
	display: block;
	vertical-align: top;
	margin: 0px auto;
	width: 74px;
	height: 74px;
	border-radius: 5px;
}

.cat-intro {
	margin-top: 8px;
	padding: 0px 15px;
}

.cat-description {
	font-size: 14px;
	line-height: 1.8em;
	text-indent: 2em;
}

.cat-description .b {
	font-weight: bold;
}

.throw-tips-title {
	display: block;
	font-size: 14px;
	font-weight: bold;
	line-height: 1.8em;
}

.throw-tip {
	position: relative;
	display: block;
	padding-left: 1em;
	font-size: 14px;
	line-height: 1.8em;
}

.throw-tip::before {
	content: "";
	position: absolute;
	left: 4px;
	top: 1em;
	width: 4px;
	height: 4px;
	border-radius: 2px;
	background-color: #000000;
	transform: translateY(-50%);
}

.item {
	padding-left: 1em;
	line-height: 3em;
	font-size: 15px;
}

.item:nth-child(odd) {
	background-color: #fafafa;
}

.item-hover {
	background-color: #fafafa;
}
</style>
</head>
<body id="body">

	<div id="section0" class="section" style="display: block;">
		<div class="cat-intro-wrapper cat-intro-wrapper-0">
		<c:if test="${not empty type}">
			<img src="/GarbageClassification/search/${type.image}" class="section-icon" />
			<div class="cat-intro">
				<p class="cat-description">
					<span class="b">${type.name} </span>${type.define}
				</p>
				<p class="throw-tips-title">投放要求：</p>
				<p class="throw-tip">${type.require}</p>
			</div>
		</c:if>
		</div>
	</div>

	<div class="list-wrapper">
		<c:if test="${not empty garbagelist}">
			<c:forEach items="${garbagelist}" var="garbage">
				<div class="item">${garbage.name}</div>
			</c:forEach>
		</c:if>
	</div>
</body>
<script type="text/javascript">

</script>
</html>