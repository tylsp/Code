<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fm" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="-1">
<style type="text/css">
body{
	background-color: white;
}
h2{
	height: 50px;
	text-align: center;
	line-height: 50px;
	vertical-align: middle;
}

h2>a{
	display:inline-block;
	border: 1px solid gray;
	margin: 0 10px;
	text-decoration: none;
	width: 120px;
	color: black;
	background-color: gold;
}
 	table{
	border: 1px solid black;
	border-collapse: collapse;	
	text-align: center;
	margin: 0 auto;
	} 
	th {
	border: 1px solid black;
	
	background-color: olive;
	color: white;
	}
	td{
	width: 80px;
	padding: 0.3em 0;
	border: 1px solid black;
	
}
#unpaid td{
	color: white;
}

td>a{
	display:inline-block;
	width:60px;
	height:20px;
	text-align:center;
	line-height:20px;
	vertical-align:middle;
	border: 1px solid gray;
	text-decoration: none;
	background-color: white;
	
	color: black;
}

a:hover {
	background-color: #bb1a39;
	color: white;
}
</style>
<title>Insert title here</title>
</head>
<body>
<h2>
	<a href="<c:url value='/PageServlet?method=findAll&pageSize=5&ostate=-1' />">所有订单</a>
	<a href="<c:url value='/PageServlet?method=findAll&pageSize=5&ostate=0' />">待支付订单</a>
	<a href="<c:url value='/PageServlet?method=findAll&pageSize=5&ostate=1' />">已支付订单</a>

</h2>

	<c:choose>
		<c:when test="${not empty pages}">
		<table>
		<tr>
			<th>序号</th>
			<th>订单号</th>
			<th>交易时间</th>
			<th>金额</th>
			<th>用户ID</th>
			<th>电话</th>
			<th>状态</th>
			<th>选项</th>
		</tr>
			 <c:if test="${not empty pages.list}"> 
			<c:forEach items="${pages.list}" var="page" varStatus="status">	
				<c:choose>
					<c:when test="${page.ostate eq 0}">
						<tr style="background-color: red;opacity: 0.8" id="unpaid">
							<td>${status.index + 1}</td>
							<td>${page.oid}</td>
							<td style="width: 200px;"><fm:formatDate value="${page.ordertime}" pattern="yyyy-MM-dd hh:mm:ss" /></td>
							<td>${page.ototal}</td>
							<td>${page.uid}</td>
							<td>${page.phone}</td>
							<td>待支付</td>
							<td></td>
							
						</tr>
					</c:when>
					<c:when test="${page.ostate eq 1}">
						<tr>
							<td>${status.index + 1}</td>
							<td>${page.oid}</td>
							<td style="width: 200px;"><fm:formatDate value="${page.ordertime}" pattern="yyyy-MM-dd hh:mm:ss" /></td>
							<td>${page.ototal}</td>
							<td>${page.uid}</td>
							<td>${page.phone}</td>
							<td>已支付</td>
							<td>
								<a href="<c:url value='/StoreOrderServlet?method=deleteOrder&oid=${page.oid}'/>" >删除</a>
							</td>
						</tr>
					</c:when>
				</c:choose>
			</c:forEach>
			 </c:if>
			
		</table>
		</c:when>
		<c:otherwise>
		
		</c:otherwise>
	</c:choose>
<c:if test="${not empty pages}">
      <c:choose>
         <c:when test="${pages.countPage<10}">
             <c:set var="begin" value="1"></c:set>
             <c:set var="end" value="${pages.countPage}"></c:set> 
         </c:when>
         <c:otherwise>
              <c:set var="begin" value="${pages.currentPageNo-5}"></c:set>
              <c:set var="end" value="${pages.currentPageNo+4}"></c:set>
              <c:if test="${begin<=1}">
                   <c:set var="begin" value="1"></c:set>
                    <c:set var="end" value="10"></c:set>
              </c:if>
              <c:if test="${end>pages.countPage}">
                   <c:set var="begin" value="${pages.countPage-9}"></c:set> 
                   <c:set var="end" value="${pages.countPage}"></c:set> 
              </c:if>
         </c:otherwise>
      </c:choose>
      <p align="center">
      <c:forEach var="i" begin="${begin}" end="${end}">
          <c:if test="${i==pages.currentPageNo}">
             [${i}]
          </c:if>
          <c:if test="${i!=pages.currentPageNo}">
             <a href="<c:url value='/PageServlet?method=findAll&pageSize=5&ostate=${ostate}&currentPageNo=${i}'/>">[${i}]</a>
          </c:if>
      </c:forEach>
      </p>
 </c:if>
</body>
</html>