<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fm" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
<title>看板</title>
<link rel="stylesheet" type="text/css" href="css/reset.css" />
<link rel="stylesheet" type="text/css" href="css/scanboard.css" />
<link rel="stylesheet" type="text/css" href="css/animsition.css" />
<link rel="stylesheet" type="text/css" href="css/jquery.shCircleLoader.css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.shCircleLoader-min.js"></script>
<link rel="stylesheet" type="text/css" href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.css">
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div id="loader"></div>
	<script type="text/javascript">
		$('#loader').shCircleLoader({color: "#00deff"});
	</script>
	<!--Top Start!-->
	<div class="scanboardWp animsition">
		<div id="top">
			<div class="wp clearfix">
				<button type="button" class="btn-toggle-fullwidth btn-toggle-mx" style="margin-left: 5vw;margin-top: 1.5vh"><img src="images/left.png" height="40px" alt=""></button>
				<!--<div class="center topLogo" style="margin-left: 0">
					<a href="#"><img src="images/logo.png"></a>
				</div>-->
				<div class="right topBar" style="margin-top: 1.2vh">
					<div class="topTime">时间加载中...</div>
				</div>
			</div>
		</div>
		<!--Top End!-->

		<!--Main Start!-->
		<div id="main" class="wp clearfix" style="padding: 0;width: 100%">
			<div id="loading">
				<div></div>
				<div></div>
				<span></span>
			</div>
			<!-- WRAPPER -->
			<div id="wrapper">
				<div id="sidebar-nav" class="sidebar" style="background-color:rgba(255,255,255,0)">
					<div class="sidebar-scroll">
						<nav>
							<ul class="nav">							
								<li><a href="<c:url value='/store/background/nav.jsp' />" target="_blank" class="iframe_link" style="background-color: rgba(91,192,222,0)"><span>商城订单</span></a></li>
								<li><a href="<c:url value='/DonorServlet?method=getAllDonor'/>" target="_blank" class="iframe_link" style="background-color: rgba(91,192,222,0)"><span>捐款列表</span></a></li>
								<li><a href="<c:url value='/service_on_call/callControl.jsp'/>" target="_blank" class="iframe_link" style="background-color: rgba(91,192,222,0)"><span>上门服务订单</span></a></li>
								<li><a href="<c:url value='/forum/forummanage.jsp'/>" target="_blank" class="iframe_link" style="background-color: rgba(91,192,222,0)"><span>论坛发帖审核</span></a></li>
							</ul>
						</nav>
					</div>
				</div>

				<div class="main">
					<div class="main-content" style="height: 100%;">
						<iframe src="" class="iframe_mx uicss-cn"></iframe>
					</div>
				</div>
			</div>

		</div>

	</div>

	<div class="filterbg"></div>
	<canvas id="canvas" style="position: absolute;top: 0;left: 0;"></canvas>
</body>
<script type="text/javascript" src="js/bg.js"></script>
<script type="text/javascript" src="js/scanboard.js"></script>
<script type="text/javascript" src="js/fontscroll.js"></script>
<script type="text/javascript" src="js/jquery.animsition.js"></script>
<script type="text/javascript" src="js/jquery.nicescroll.js"></script>
<script type="text/javascript" src="js/echarts.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.slimscroll.min.js"></script>
<script src="js/klorofil-common.js" ></script>
</html>
