<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="-1">
<style type="text/css">
.list-wrapper {
	padding-left: 80px;
	padding-top:100px;
	background-color: #ffffff;
	overflow-y: auto;
	-webkit-overflow-scrolling: touch;
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
<title>垃圾查询结果页面</title>
</head>
<body>
<div class="list-wrapper">
		<c:if test="${not empty garbageList}">
			<c:forEach items="${garbageList}" var="garbage">
				<div class="item">${garbage.name}&nbsp;[${garbage.cname}]</div>
			</c:forEach>
		</c:if>
</div>
</body>
</html>