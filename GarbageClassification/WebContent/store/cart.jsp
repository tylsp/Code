<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
table {
	border: 1px solid black;
	border-collapse: collapse;	
	text-align: center;
	margin: 0 auto;
}

tr th {
	border: 1px solid black;
	width: 200px;
	background-color: olive;
	color: silver;
}
td{
	width: 200px;
	padding: 0.3em 0;
	border: 1px solid black;
	
}

#colspan{
	width: 1216px;
	height: 50px;
	color: gold
}

label{
	width: 200px;
	height: 100px;
}

 #check{
	position: relative;
}
#check input:first-of-type{
	display: inline-block;
	width: 15px;
	height: 15px;
	border:1px solid red;
	border-radius:50%;
	position: absolute;
	top:40%;
	left:10%;
	background-color: red;
} 

#choose>a{
	display:inline-block;
	width:20px;
	height:20px;
	text-align:center;
	line-height:20px;
	vertical-align:middle;
	border: 1px solid gray;
	text-decoration: none;
}

#choose>a:hover {
	background-color: #bb1a39;
	color: white;
}
#choose input{
border: 1px solid gray;
}
#choose input:hover {
	background-color: #bb1a39;
	color: white;
	border: 1px solid gray;
}
#deaddress{
	width: 340px;
	height: 30px
}
select {
	text-align: center;
	text-align-last: center;
	width: 10%;
	height: 30px;
}
#clear>a{
	display:inline-block;
	width:100px;
	height:20px;
	text-align:center;
	line-height:20px;
	vertical-align:middle;
	border: 1px solid gray;
	text-decoration: none;
	background-color: gray;
	color: white;
}

#clear>a:hover {
	background-color: #bb1a39;
	color: white;
}
#address,#phone{
border: border: 1px solid gray;
margin-left: 5px；
}
</style>
</head>
<body>
	<h1 style="text-align: center;">购物车<span></span> </h1>
	<form action="<c:url value='/StoreOrderServlet'/>">
	
	<input type="hidden" name="method" value="addStoreOrder" />
		<table>
			<tr>
				<th>商品图片</th>
				<th>商品名称</th>
				<th>商品价格</th>
				<th>商品小计</th>
				<th>商品数量</th>
				<th>选择</th>
			</tr>
			<c:if test="${not empty cart}">
			<input type="hidden" name="cartid" value="${cart.cartid}" />
					<c:if test="${not empty cart.cartItems}">
						<c:forEach items="${cart.cartItems}" var="item" varStatus="status">
							<tr>								
								<td id="check">								
								<label>
									<input class="cb" type="checkbox" name="gid" value="${item.goods.gid}"/>
									<img alt="" src="store/${item.goods.image}" width="90" height="98">
								</label>
								</td>
								<td>${item.goods.gname }</td>
								<td>${item.goods.gprice }</td>
								<td>${item.ctotal }</td>
								<td>${item.count }</td>
								<td id="choose">
									<a href="<c:url value='/ProvinceAndCityServlet?method=province&count=1&gid=${item.goods.gid}'/>">+</a>
									<a href="<c:url value='/ProvinceAndCityServlet?method=province&count=-1&gid=${item.goods.gid}'/>">-</a>
									
								</td>
							</tr>
						</c:forEach>
					</c:if>
			</c:if>
			<tr>
				<td colspan="4" id="total">总计${cart.total + 0.0 }</td>
				
				<td>
				<label id="writeaddress" style="color: red">*点击填写地址</label>
			 	<input id="sub" type="submit" value="结算" /> 
				</td>
				<c:choose>
				<c:when test="${empty cart}">
					<td><label>没有商品</label> </td>
				</c:when>
				<c:when test="${cart.total != 0.0}">
					<td id="clear"><a href="<c:url value='/CartServlet?method=clearAllItem'/>">清空购物车</a></td>
				</c:when>
				<c:otherwise>
					<td><label>没有商品</label> </td>
				</c:otherwise>
				</c:choose>
			</tr>
		</table>
		<table  style="background-color: graytext;">
			<tr style="display: none" id="address">
				<td colspan="7" id="colspan">
				<select id="provice" name="provice">
                        <option id="pro">---请选择省份---</option>
                        <c:forEach items="${plist}" var="item">
                        <option>${item.province}</option>
                        </c:forEach>
                    </select>
                    <select id="city" name="city">
                        <option id="ci">---请选择城市---</option>
                        
                    </select>
                    <!--  area对象  value="" -->
                    <select name="area" id="area">
                        <option id="ar">---请选择地区---</option>
                       
                    </select>
                    <label style="margin: 0 20px;">电话号码<input id="phone" type="text" name="phone" required><span id="mess"></span></label>
                    <label style="margin: 0 10px;">详细地址<input id="deaddress" type="text" name="deaddress" required></label>
                    
				</td>
			</tr>
			<tr>
			
			</tr>
		</table>
	</form>
</body>
<script type="text/javascript">
	var total = document.getElementById("total");
	var arr = document.getElementsByClassName("cb");

	
	

	var total = document.getElementById("total");
	var sub = document.getElementById("sub");
	var text = "总计0.0";
	var provice = document.getElementById("provice");
	var city = document.getElementById("city"); 
	var area =  document.getElementById("area");
	var writeaddress = document.getElementById("writeaddress");
	var address = document.getElementById("address");
	var colspan = document.getElementById("colspan");
	var mess = document.getElementById("mess");
	sub.disabled = true;
	
	var phone = document.getElementById("phone");
	phone.onchange = function(){
	if(!(/^[1][3,4,5,7,8][0-9]{9}$/.test(phone.value))){ 
        mess.innerHTML = "请正确输入电话";
        mess.style.color="white"; 
    }else{
    	mess.innerHTML = "";
    }
		
	}
	
	writeaddress.onclick = function(){
		address.style.display="inline-block";
		
	}
	area.onchange = function(){
		if (total.innerHTML != text) {
			sub.disabled = false;
		}
	}
	
	 window.onload = function() {
		var provice = document.getElementById("provice");
		var city = document.getElementById("city");
		var area = document.getElementById("area");
		provice.onchange = function() {
			ajax({
				url : "ProvinceAndCityServlet?method=City&provice=" + provice.value,
				method : "GET",
				param : null,
				type : "JSON",
				callback : function(data) {
					city.innerHTML = "";
					for ( var i in data) {
						var option = document.createElement("option");
						option.innerHTML = data[i].city;
						city.appendChild(option);
					}
				}
			});

		}
		city.onchange = function() {
			ajax({
				url : "ProvinceAndCityServlet?method=Area&city=" + city.value,
				method : "GET",
				param : null,
				type : "JSON",
				callback : function(data) {
					area.innerHTML = "";
					for ( var i in data) {
						var option = document.createElement("option");
						option.innerHTML = data[i].area;
						area.appendChild(option);
					}
				}
			});
		}
		
	} 
	 
	 
</script>
</html>