<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fm" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet"
	href="<c:url value="/css/bootstrap.min.css"/>">
<script src="<c:url value="/js/jquery-3.4.1.min.js"/>"
	type="text/javascript"></script>
<script src="<c:url value="/js/bootstrap.min.js"/>"
	type="text/javascript"></script>

<title>论坛后台管理</title>
</head>
<body>
	<div>

		<div style="width: 90vw; margin: 10vh auto">
			<h2>论坛帖子操作</h2>
			<button type="button" class="btn btn-default"
				style="margin-top: 0.7vh"
				onclick="location.href='<c:url value='/ForumServlet?method=getPostingToCheck'/>'">查看待审核的帖子</button>

			<button type="button" class="btn btn-warning"
				style="margin-left: 2vw"
				onclick="location.href='<c:url value='/ForumServlet?method=getPostings'/>'">查看所有帖子</button>



			<div class="panel panel-primary" style="margin-top: 2vh">
				<div class="panel-heading" style="margin-top: -1px">所有帖子</div>
				<div class="panel-body" align="center">
					<table class="table table-hover">
						<!-- On rows -->
						<tr class="active">
							<th>帖子标题</th>
							<th>作者</th>
							<th>发帖时间</th>
							<th>帖子内容</th>
							<th>操作</th>
						</tr>
						<c:if test="${not empty postingList}">
							<c:forEach items="${postingList}" var="posting">
								<c:if test="${not empty posting}">
									<tr>
										<td>${posting.title}</td>
										<td>${posting.user.username}</td>
										<td><fm:formatDate value="${posting.time}"
												pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<td>${posting.content}</td>
										<td>
											<form action="<c:url value='/ForumServlet'/>" target="_blank">
												<input type="hidden" name="method"
													value="updatePostingStatus" /> <input type="hidden"
													name="pid" value="${posting.pid}" />
												<p id="p">
													<select name="status">
														<option selected="selected">请决定帖子是否通过</option>
														<option value="1">帖子通过</option>
														<option value="2">帖子不通过</option>
													</select> <input type="submit" value="提交审核结果" />
												</p>
											</form>
										</td>
									</tr>
								</c:if>
							</c:forEach>
						</c:if>
						<c:if test="${not empty postinglist}">
							<c:forEach items="${postinglist}" var="posting">
								<c:if test="${not empty posting}">
									<tr>
										<td>${posting.title}</td>
										<td>${posting.user.username}</td>
										<td><fm:formatDate value="${posting.time}"
												pattern="yyyy-MM-dd hh:mm:ss" /></td>
										<td>${posting.content}</td>
										<td>
											<button class="glyphicon glyphicon-trash" onclick="window.location.href='<c:url value='/ForumServlet?method=deletePosting&pid=${posting.pid}'/>'">删除</button>
										</td>
									</tr>
								</c:if>
							</c:forEach>
						</c:if>
					</table>
				</div>
			</div>
		</div>

	</div>
</body>
</html>