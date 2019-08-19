<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fm" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="-1">
<style type="text/css">
* {
	margin: 5 auto;

}

#item {
	pading-left:8px;
	font-size: 16px;
	width: 680px;
	height: 350px;
	border-radius:2px;
	background-color: #ffc35a;
}

a {
	text-decoration: none;
	font-weight: bold;
	color: red;
}

#comment {
	width: 400px;
	height: 30px;
	border: none;
	border-radius: 5px;
	box-sizing: border-box;
	color: #808080;
}

#submit {
	width: 50px;
	height: 30px;
	border-radius: 5px;
	background-color: #59a7ff;
	color: white;
}

#author {
color:#ffffff;
font-size: 14px;
}

#time {
color:#ffffff;
font-size: 14px;
}
#p{
margin-top:10px;
}
</style>


<title>显示帖子页面</title>
</head>
<body>
	<c:if test="${not empty postingList}">
		
			<c:forEach items="${postingList}" var="posting">
				<div id="item">

					<p>
						<a target="content2"
							href="<c:url value='/ForumServlet?method=selectCommentByPid&pid=${posting.pid}'/>">${posting.title}</a>
					</p>
					<p>
						作者：<a href="#"><span id="author">${posting.user.username}</span></a>&nbsp;&nbsp;&nbsp;&nbsp;发帖时间：<span
							id="time"><fm:formatDate value="${posting.time}"
								pattern="yyyy-MM-dd HH:mm:ss" /></span>
					</p>
					<div>${posting.content}</div>
					<form action="<c:url value='/ForumServlet'/>" target="content2">
					<p id="p">
						<input type="hidden" name="method" value="addComment" /> <input
							type="hidden" name="pid" value="${posting.pid}" /> <input
							type="text" name="comment" id="comment" /> <input type="submit"
							value="评论" id="submit" />
					</p>
					</form>
				</div>
			</c:forEach>
		
	</c:if>
</body>
</html>