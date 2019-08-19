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
 <link type="text/css" rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
    <script src="<c:url value="/js/jquery-3.4.1.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/js/bootstrap.min.js"/>" type="text/javascript"></script>
<title>所有捐款单列表</title>
</head>
<body>

	<div style="width: 90vw; margin: 10vh auto">
		<h2>所有捐款单</h2>
		<div class="panel panel-primary" style="margin-top: 2vh">
			<div class="panel-heading" style="margin-top: -1px"></div>
			<div class="panel-body" align="center">
				<form action="<c:url value='/DonorServlet?method=getAllDonor'/>">
					<table class="table table-hover">
						<c:if test="${not empty donorlist}">
							<!-- On rows -->
							<tr class="active">
								<th>用户名</th>
								<th>捐款金额</th>
								<th>捐款时间</th>
							</tr>
							<c:forEach items="${donorlist}" var="donor">
								<c:if test="${not empty donor}">
									<tr>
										<td>${donor.user.username}</td>
										<td>${donor.money}</td>
										<td><fm:formatDate value="${donor.donatetime}"
												pattern="yyyy-MM-dd hh:mm:ss" /></td>
									</tr>
								</c:if>
							</c:forEach>
						</c:if>
					</table>
				</form>
			</div>
		</div>
	</div>


</body>
</html>