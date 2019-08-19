<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fm" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
#item{
 padding-left:5px;
 width:100%;
 height:30px;
 border-radius:10px;
 background-color: #ffc35a;
}
#author{
color:#ffffff;
font-size: 14px;
}
#comment{
font-size: 16px;
}
#time{
color:#ffffff;
font-size: 12px;
}
a{
 text-decoration: none;
 font-weight: bold;
}
</style>
<title>评论页面</title>
</head>
<body>

	<c:if test="${not empty commentList}">
		<c:forEach items="${commentList}" var="comment">
		<div id="item">
			<p><a href="#"><span id="author">${comment.user.username}</span></a>&nbsp;&nbsp;<span id="comment">${comment.comment}</span> &nbsp;&nbsp;&nbsp;&nbsp;<span id="time">${comment.time}</span></p>
		</div>
		</c:forEach>
	</c:if>

</body>
</html>