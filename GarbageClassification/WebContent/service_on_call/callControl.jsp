<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
    <script src="<c:url value="/js/jquery-3.4.1.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/js/bootstrap.min.js"/>" type="text/javascript"></script>
</head>
<body>
<div style="width: 90vw;margin: 10vh auto">
    <h2>上门服务订单</h2>
    <button type="button" class="btn btn-default" style="margin-top: 0.7vh" onclick="location.href='<c:url value='/CallControlServlet?method=showOrder&pageSize=5&btn=1'/>'">查看所有订单</button>

    <button type="button" class="btn btn-warning" style="margin-left: 2vw" onclick="location.href='<c:url value='/CallControlServlet?method=showOrder&pageSize=5&btn=2'/>'">查看待确认订单</button>

    <button type="button" class="btn btn-danger" style="margin-left: 2vw" onclick="location.href='<c:url value='/CallControlServlet?method=showOrder&pageSize=5&btn=3'/>'">查看待受理订单</button>

    <button type="button" class="btn btn-success" style="margin-left: 2vw" onclick="location.href='<c:url value='/CallControlServlet?method=showOrder&pageSize=5&btn=4'/>'">查询已受理订单</button>

    <button type="button" class="btn btn-info" style="margin-left: 2vw" onclick="location.href='<c:url value='/CallControlServlet?method=showOrder&pageSize=5&btn=5'/>'">查询已完成订单</button>

    <div class="panel panel-primary" style="margin-top: 2vh">
        <div class="panel-heading" style="margin-top: -1px">所有订单</div>
        <div class="panel-body" align="center">
            <table class="table table-hover">
                <!-- On rows -->
                <tr class="active">
                    <th>订单号</th>
                    <th>下单时间</th>
                    <th>姓名</th>
                    <th>电话</th>
                    <th>上门时间</th>
                    <th>上门地址</th>
                    <th>备注</th>
                    <th>总计</th>
                    <th>当前状态</th>
                    <th>操作</th>
                </tr>
                <!-- On cells (`td` or `th`) -->
                <c:if test="${not empty page}">
                	<c:if test="${not empty page.list}">
                	<c:forEach items="${page.list}" var="item">
                	<%String msg = "待收货";%>
                	<%String style = "class='warning'";%>
                	<c:choose>	
                	<c:when test="${item.state eq 1}">
                			<% style = "class='warning'";%>
                			<% msg = "待收货"; %>
                		</c:when>
                		<c:when test="${item.state eq 2}">
                			<% style = "class='danger'"; %>
                			<% msg = "待受理"; %>
                		</c:when>
                		<c:when test="${item.state eq 3}">
                			<% style = "class='success'"; %>
                			<% msg = "已受理"; %>
                		</c:when>
                		<c:otherwise>
                			<% style = "class='info'"; %>
                			<% msg = "订单完成"; %>
                		</c:otherwise>
                		</c:choose>
                		<tr <%=style%>>
	                    <td>${item.oid}</td>
	                    <td>${item.ordertime}</td>
	                    <td>${item.oname}</td>
	                    <td>${item.otel}</td>
	                    <td>${item.gotime}</td>
	                    <td>${item.address}</td>
	                    <td>${item.remark}</td>
	                    <td>${item.ctotal}</td>
	                    <td><%=msg %></td>
	                    <!-- <c:url value='/CallControlServlet?method=updateState&oid=${item.oid}&update=1'/> -->
	                    <td><button class="btn btn-danger btn-xs" style="padding: 1px 2px;font-size: 14px" data-toggle ="popover" title="状态修改" data-container="body" data-placement="bottom" data-trigger="focus hover" data-html="true"
                           data-content="<p>
                           <form action='<c:url value='/CallControlServlet'/>' method='get'>
                           <input type='hidden' name='method' value='updateState'>
    					   <input type='hidden' name='oid' value='${item.oid}'>
   						   <input type='hidden' name='update' value='1'>
	                       <button type='submit' class='btn btn-primary btn-sm'>已经受理</button></form><br/>
	                       <form action='<c:url value='/CallControlServlet'/>' method='get'>
	                       <input type='hidden' name='method' value='updateState'>
    					   <input type='hidden' name='oid' value='${item.oid}'>
   						   <input type='hidden' name='update' value='2'>
	                       <button type='submit' class='btn btn-default btn-sm'>订单完成</button></form>
	                       </p>">修改状态</button></td>
	                </tr>
	                </c:forEach>
	                </c:if>
                </c:if>
            </table>
            <c:if test="${not empty page}">
            <c:choose>
            	<c:when test="${page.countPage <= 5}">
            		<c:set var="begin" value="1"></c:set>
            		<c:set var="end" value="${page.countPage}"></c:set>
            	</c:when>
            	<c:otherwise>
            		<c:set var="begin" value="${page.currentPage - 2}"></c:set>
            		<c:set var="end" value="${page.currentPage + 2}"></c:set>
            		<c:if test="${begin <= 1}">
            			<c:set var="begin" value="1"></c:set>
            			<c:set var="end" value="5"></c:set>
            		</c:if>
            		<c:if test="${end >= page.countPage}">
            			<c:set var="begin" value="${page.countPage - 4}"></c:set>
            			<c:set var="end" value="${page.countPage}"></c:set>
            		</c:if>
            	</c:otherwise>
            </c:choose>
            <nav style="margin: 0 auto">
                <ul class="pagination" style="margin: 0">
                    <li <c:if test="${page.currentPage eq 1}">class="disabled"</c:if>>
                        <a href="<c:url value='/CallControlServlet?method=showOrder&pageSize=5&currentPage=${page.currentPage - 1}&btn=${btnid}'/>" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    <c:forEach var="i" begin="${begin}" end="${end}">
                    	<c:if test="${i == page.currentPage}">
							<li class="active"><a href="#">${i}<span class="sr-only">(current)</span></a></li>
						</c:if>
						<c:if test="${i != page.currentPage}">
							<li><a href="<c:url value='/CallControlServlet?method=showOrder&pageSize=5&currentPage=${i}&btn=${btnid}'/>" aria-label="Previous">${i}</a></li>
						</c:if>
                    </c:forEach>
                    <li <c:if test="${page.currentPage eq page.countPage}">class="disabled"</c:if>>
                        <a href="<c:url value='/CallControlServlet?method=showOrder&pageSize=5&currentPage=${page.currentPage + 1}&btn=${btnid}'/>" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </ul>
            </nav>
            </c:if>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    $(function () {
        $('[data-toggle="popover"]').popover()
    });
</script>
</html>