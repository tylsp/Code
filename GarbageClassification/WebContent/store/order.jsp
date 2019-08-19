<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fm" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="-1">
<title>Insert title here</title>
<script type="text/javascript" src="<c:url value='/js/ajax-lib.js' />"></script>
<style type="text/css">
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
#pay>a{
	display:inline-block;
	width:60px;
	height:20px;
	text-align:center;
	line-height:20px;
	vertical-align:middle;
	border: 1px solid gray;
	text-decoration: none;
	background-color: gray;
	color: white;
}

#pay>a:hover {
	background-color: #bb1a39;
	color: white;
}
</style>
</head>
<body>
	
	<c:choose>
	<c:when test="${not empty message}">
			 	<h1 style="text-align: center;">${message}<span id="time" style="color: red">5</span>秒钟后返回上一步</h1>
	</c:when>
	<c:otherwise>
	<c:if test="${not empty order}">
		<h1>订单</h1>
		<table>
			<tr>
				<th colspan="5" id="th">
					<b>订单编号:<span id="oid">${order.oid}</span> </b> 
					交易时间:<fm:formatDate value="${order.ordertime}" pattern="yyyy-MM-dd hh:mm:ss" /> 
					<b style="color: white; ">金额:${order.ototal} </b>
					<span id="state" style="color: white;margin-right: 1px; ">待支付</span>
				</th>
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
				<tr>
				<td colspan="4">地址:${order.address}${order.deaddress}</td>
				<td id="pay">
				<a href="<c:url value='/StoreOrderServlet?method=updateState&oid=${order.oid}'/>" id="a">支付</a>
				<a href="<c:url value='/StoreOrderServlet?method=deleteOrder&oid=${order.oid}'/>" id="a">取消</a>
				</td>
				</tr>
			</c:if>
		</table>
	</c:if>
	</c:otherwise>
	</c:choose>
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
			 history.go(-1);
		 }
	 }
	}
	
	var pay = document.getElementById("pay");
	var oid = document.getElementById("oid"); 
	var state = document.getElementById("state");
	function fun(id){
		alert(id);
		ajax({
			url : "StoreOrderServlet?method=getMyOrder&oid=" + id,
			method : "GET",
			param : null,
			type : "TEXT",
			callback : function(data){
				state.innerHTML = "";
				state.innerHTML = data;
			}
		});
	}
</script>
</html>