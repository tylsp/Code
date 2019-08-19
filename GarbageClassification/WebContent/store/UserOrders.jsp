<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fm" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="-1">
<title>Insert title here</title>
<style type="text/css">


h4 {
	text-align: center;
}
h4>a{
	display: inline-block;
	margin: 10px;
}
	table{
	border: 1px solid black;
	border-collapse: collapse;	
	text-align: center;
	margin: 0 auto;
	}
	#th {
	border: 1px solid black;
	
	background-color: olive;
	color: white;
	
}
#th>b:first-of-type{
	margin: 0 10% 0 0;
}
#th>b:last-of-type{
	margin: 0 5% 0 5%;
}
td{
	width: 200px;
	padding: 0.3em 0;
	border: 1px solid black;
	
}

a{
	display:inline-block;
	width:120px;
	height:20px;
	text-align:center;
	line-height:20px;
	vertical-align:middle;
	border: 1px solid gray;
	text-decoration: none;
	background-color: gray;
	color: white;
}

a:hover {
	background-color: #bb1a39;
	color: white;
}
#pay>a{
	width:60px;
}
p>a{
	width:20px;
	display: inline-block;
	margin: 0 5px;
}
</style>
</head>
<body>
	<c:choose>
			<c:when test="${empty orders }">
		<h1 style="text-align: center;">您还没有订单<span id="time" style="color: red">5</span>秒钟后返回商城首页</h1>
	</c:when> 
	<c:otherwise>
		<h4>
			<a href="<c:url value='/PageServlet?method=findUserOrder&pageSize=3&ostate=-1' />">所有订单</a>
			<a href="<c:url value='/PageServlet?method=findUserOrder&pageSize=3&ostate=1' />">已支付订单</a>
			<a href="<c:url value='/PageServlet?method=findUserOrder&pageSize=3&ostate=0' />">待支付订单</a>
			<a href="<c:url value='store/store.jsp' />">返回商城</a>
		</h4>
			 <table>
			 <c:if test="${empty orders.list}">
			 	<h1 style="text-align: center;">您没有此类订单<span id="time" style="color: red">5</span>秒钟后返回商城首页</h1>
			 </c:if>
			<c:if test="${not empty orders.list}">
				 <c:forEach items="${orders.list}" var="order" varStatus="status">
			 	<tr >
			 		<c:choose>
			 		<c:when test="${order.ostate==0}">
			 			<th colspan="5" id="th" style="background-color: red;">
			 			<span>${status.index + 1}</span>
			 			<b>订单编号:<span id="oid">${order.oid}</span></b> 
			 				交易时间:<fm:formatDate value="${order.ordertime}" pattern="yyyy-MM-dd hh:mm:ss" /> <b style="color: white;">金额:${order.ototal}</b> 

							<span id="state" style="color: white; margin-right: 1px;">待支付</span>
						
					</th>
					</c:when>
					<c:when test="${order.ostate==1}">
					<th colspan="5" id="th" >
						<span>${status.index + 1}</span>
						<b>订单编号:<span id="oid">${order.oid}</span></b> 
						交易时间:<fm:formatDate value="${order.ordertime}" pattern="yyyy-MM-dd hh:mm:ss" /> <b style="color: white;">金额:${order.ototal}</b> 

							<span id="state" style="color: white; margin-right: 1px;">已支付</span>
					</th>
					</c:when>
					</c:choose>
				</tr>
				<c:if test="${not empty order.list}">
				<tr>
					<th>商品图片</th>
					<th>商品名称</th>
					<th>商品价格</th>
					<th>商品小计</th>
					<th>商品数量</th>
				</tr>
					<c:forEach items="${order.list}" var="item">
						<tr>
							<td><img alt="" src="store/${item.goods.image}" width="90" height="98"></td>
							<td>${item.goods.gname }</td>
							<td>${item.goods.gprice }</td>
							<td>${item.osubtotal }</td>
							<td>${item.count }</td>
						</tr>
					</c:forEach>
						<tr style="margin: 10px;">
							<c:if test="${order.ostate==1}">
								<td colspan="4">地址:${order.address}${order.deaddress}</td>
								<td><a href="<c:url value='/StoreOrderServlet?method=deleteOrder&oid=${order.oid}'/>" >删除此订单</a></td>
							</c:if>
							<c:if test="${order.ostate==0}">
								<td colspan="4" >地址:${order.address}${order.deaddress}</td>
								<td id="pay">
									<a href="<c:url value='/StoreOrderServlet?method=updateState&oid=${order.oid}'/>" >支付</a>
									<a href="<c:url value='/StoreOrderServlet?method=deleteOrder&oid=${order.oid}'/>" >取消</a>		
								</td>
							</c:if>
						</tr>
				</c:if>
			</c:forEach>
			</c:if>
		</table>
	</c:otherwise>
	</c:choose>
	
	<c:if test="${not empty orders}">
      <c:choose>
         <c:when test="${orders.countPage<10}">
             <c:set var="begin" value="1"></c:set>
             <c:set var="end" value="${orders.countPage}"></c:set> 
         </c:when>
         <c:otherwise>
              <c:set var="begin" value="${orders.currentPageNo-5}"></c:set>
              <c:set var="end" value="${orders.currentPageNo+4}"></c:set>
              <c:if test="${begin<=1}">
                   <c:set var="begin" value="1"></c:set>
                    <c:set var="end" value="10"></c:set>
              </c:if>
              <c:if test="${end>orders.countPage}">
                   <c:set var="begin" value="${orders.countPage-9}"></c:set> 
                   <c:set var="end" value="${orders.countPage}"></c:set> 
              </c:if>
         </c:otherwise>
      </c:choose>
      <p align="center">
      <c:forEach var="i" begin="${begin}" end="${end}">
          <c:if test="${i==orders.currentPageNo}">
             [${i}]
          </c:if>
          <c:if test="${i!=orders.currentPageNo}">
             <a href="<c:url value='/PageServlet?method=findUserOrder&pageSize=3&ostate=${ostate}&currentPageNo=${i}'/>">[${i}]</a>
          </c:if>
      </c:forEach>
      </p>
 </c:if>
</body>
<script type="text/javascript">
var time = document.getElementById("time");
if(time != null){
var count = 5;
 window.setInterval("refresh()", 1000);
 function refresh(){
	 if (count > 1 ){
		 time.innerHTML = --count;
	 }else{
		 location.href="store/store.jsp";
	 }
 }
}
</script>
</html>