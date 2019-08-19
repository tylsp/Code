<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fm" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link href="<c:url value='/css/bootstrap.min.css'/>" rel="stylesheet"
	type="text/css">
<script src="<c:url value='/js/jquery-3.4.1.min.js'/>"
	type="text/javascript"></script>
<script src="<c:url value='/js/bootstrap.min.js'/>"
	type="text/javascript"></script>
<title>个人中心</title>
</head>
<body>
	<!--头部导航栏-->
	<div class="navbar navbar-inverse top1">
		<div class="top2">
			<div class="navbar-header">
				<a class="navbar-brand" href="<c:url value='/main/home.jsp'/>">首页</a>
			</div>
			<div class="navbar-collapse top3">
				<ul class="nav navbar-nav navbar-right">
					<li><a
						href="<c:url value='/store/userOrdernav.jsp' />"><i
							class="glyphicon glyphicon-list-alt"></i> 商城订单</a></li>
					<li><a href="<c:url value='/service_on_call/myOrder.jsp'/>"><i
							class="glyphicon glyphicon-list-alt"></i> 服务订单</a></li>
					<li><a
						href="<c:url value='/ProvinceAndCityServlet?method=myProvince' />"><i
							class="glyphicon glyphicon-shopping-cart"></i> 我的购物车</a></li>
					<li><a
						href="<c:url value='/DonorServlet?method=getDonorByUid'/>"><i
							class="glyphicon glyphicon-heart"></i> 爱心捐款</a></li>
				</ul>
			</div>
		</div>
	</div>
	<!--左侧菜单栏-->
	<div class="container-fluid">
		<div class="row-fluie">
			<div class="col-md-2">
				<ul id="main-nav" class="nav nav-tabs nav-stacked">
					<!--  
       <li>
            <a href="#a" class="nav-header menu collapsed" data-toggle="collapse">
                <i class="glyphicon glyphicon-user"></i>用户管理
                <span class="pull-right glyphicon glyphicon-chevron-down"></span>
            </a>
            
            <ul id="a" class="nav nav-list collapse menu2">
                <li><a href="#" target="iframe1"><i class="glyphicon glyphicon-edit"></i>修改密码</a></li>
            </ul>
            
        </li>
 -->
					<li><a href="#b" class="nav-header menu collapsed"
						data-toggle="collapse"> <i
							class="glyphicon glyphicon-list-alt"></i>我的订单 <span
							class="pull-right glyphicon glyphicon-chevron-down"></span>
					</a>
						<ul id="b" class="nav nav-list collapse menu2">
							<li><a
								href="<c:url value='/store/userOrdernav.jsp' />"
								target="iframe1"><i
									class="glyphicon glyphicon-triangle-right"></i>商城订单</a></li>
							<li><a href="<c:url value='/service_on_call/myOrder.jsp'/>"
								target="iframe1"><i
									class="glyphicon glyphicon-triangle-right"></i>服务订单</a></li>
						</ul></li>

					<li><a
						href="<c:url value='/ProvinceAndCityServlet?method=myProvince'/>"
						target="iframe1" class="menu"> <i
							class="glyphicon glyphicon-shopping-cart"></i>我的购物车
					</a></li>

					<!--<li>
            <a href="#" target="iframe1" class="menu">
                <i class="glyphicon glyphicon-usd"></i>我的积分
            </a>
        </li>-->

					<li><a
						href="<c:url value='/ForumServlet?method=getPostingByUid'/>"
						target="iframe1"><i class="glyphicon glyphicon-edit"></i>我的帖子</a>

					</li>

					<li><a
						href="<c:url value='/DonorServlet?method=getDonorByUid'/>"
						target="iframe1" class="menu"> <i
							class="glyphicon glyphicon-heart"></i>爱心捐款
					</a></li>
				</ul>
			</div>

			<iframe src="" name="iframe1" frameborder="0" width="1350"
				height="560"></iframe>
		</div>
	</div>
</body>

</html>
