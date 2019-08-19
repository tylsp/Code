<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fm" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
* {
	padding: 0px;
	margin: 0px auto;
}

.aitem {
	margin-left: 170px;
	color: white;
	text-decoration: none;
	text-align: center;
	line-height: 75px;
	vertical-align: middle;
}

#item {
	margin-left: 0px;
}

#section {
	position: relative;
	width: 65vw;
	margin: auto;
}

.img {
	margin: auto;
}

#donor {
	position: absolute;
	left: 750px;
	top: 350px;
}
/*捐款总列表*/
#donatelist {
	width: 690px;
	border: 1px solid #eaeaea;
	border-radius: 15px;
	background-color: #f5f5f5;
	margin: 10px 0px;
}

#donatelist>div {
	width: 690px;
	position: relative;
}

#donatelist>div img {
	padding: 10px;
}

#donatelist>div div:nth-child(2) span:nth-child(1) {
	color: red;
	font: 24px bolder;
}

#donatelist>div div:nth-child(3) {
	position: absolute;
	left: 280px;
	top: 20px;
}

.donate-btn {
	position: absolute;
	left: 320px;
	top: 130px;
	color: white;
	background-color: #ef512c;
	border-radius: 20%;
	width: 80px;
	height: 30px;
}
</style>
<link type="text/css" rel="stylesheet"
	href="<c:url value='/css/bootstrap.min.css'/>">
<script src="<c:url value='/js/jquery-3.4.1.min.js'/>"
	type="text/javascript"></script>
<script src="<c:url value='/js/bootstrap.min.js'/>"
	type="text/javascript"></script>
<script type="text/javascript" src="<c:url value='/js/ajax-lib.js'/>"></script>
<title>公益捐款</title>
</head>
<body>
	<div id="header">
		<div id="navigation"
			style="width: 100vw; height: 75px; background-color: #ffc35a;">
			<a href="<c:url value='/main/home.jsp'/>" class="aitem">首页</a> <a	 href="<c:url value='/user/login.jsp'/>" class="aitem">登录</a>|<a href="<c:url value='/user/register.jsp'/>" id="item" class="aitem">注册</a>
		</div>
	</div>
	<div id="section">
		<!--轮播图-->
		<div id="myCarousel" class="carousel slide">
			<!-- 轮播指标 -->
			<ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="2" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="3" class="active"></li>
			</ol>
			<!-- 轮播项目 -->
			<div class="carousel-inner">
				<div class="item active">
					<img src="image/gy1.jpg" alt="First slide" class="img">
				</div>
				<div class="item">
					<img src="image/gy2.jpg" alt="Second slide" class="img">
				</div>
				<div class="item">
					<img src="image/gy3.jpg" alt="Third slide" class="img">
				</div>
				<div class="item">
					<img src="image/gy4.jpg" alt="Fourth slide" class="img">
				</div>
			</div>
			<!-- 轮播导航 -->
			<a class="left carousel-control" href="#myCarousel" role="button"
				data-slide="prev"> <span
				class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
				<span class="sr-only">Previous</span>
			</a> <a class="right carousel-control" href="#myCarousel" role="button"
				data-slide="next"> <span
				class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				<span class="sr-only">Next</span>
			</a>
		</div>
		<!--捐款人列表轮播-->
		<div id="donor">
			<div class="header">
				<span class="bold primary text title"> 信息公开 </span> <span
					class="grey text subtitle"> 透明公益 人人参与 </span>
			</div>
			<div class="donor_content">
				<div class="table-container">
					<iframe src="<c:url value='/DonorServlet?method=getAllDonor'/>"
						width="300" height="250"></iframe>
				</div>
				<a class="primary text float-right link-btn"
					href="<c:url value='/DonorServlet?method=getAllDonor'/>">查询捐赠
					&gt;&gt;</a>
			</div>
		</div>
		<!-- 捐款项目列表 -->
		<div id="donatelist"></div>
		<!-- 模态框 -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true"
			data-backdrop="static">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">捐款信息</h4>
					</div>
					<div class="modal-body">
						<form action="<c:url value='/DonorServlet'/>" method="get">
							<input type="hidden" name="method" value="addDonor" /> <input
								type="hidden" name="cid" id="cid" />
							<p>
								捐款金额：<input type="text" name="money" id="money" /><span id="msg"></span>
							</p>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
								<input type="submit" class="btn btn-primary" value="确认捐款" />
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>


	<div id="footer">
		<p></p>
	</div>
</body>
<script type="text/javascript">
	var money = document.getElementById("money");
	var msg = document.getElementById("msg");
	money.onchange = function(){
		if(isNaN(money.value)){
			msg.style.color="red";
			msg.innerHTML="请输入数字";
		}else{
			msg.innerHTML="";
		}
	}
	
	var get = document.getElementById("cid");
	function add(cid) {
		get.value = cid;
	}
	window.onload = function() {
		var donatelist = document.getElementById("donatelist");

		ajax({
			url : "/GarbageClassification/CommonGoodServlet?method=findAll",
			params : null,
			type : "JSON",
			callback : function(data) {
				for (var i = 0; i < data.length; i++) {
					var item = data[i];
					var itemdiv = document.createElement("div");
					var imagediv = document.createElement("div");
					imagediv.innerHTML = "<img src='/GarbageClassification/common_good/"+item.cimage+"'>";
					itemdiv.appendChild(imagediv);

					var moneydiv = document.createElement("div");
					var moneyspan = document.createElement("span");
					moneyspan.innerHTML = item.ctotal;
					var fontspan = document.createElement("span");
					fontspan.innerHTML = "元善款已捐";
					moneydiv.appendChild(moneyspan);
					moneydiv.appendChild(fontspan);
					itemdiv.appendChild(moneydiv);

					var contentdiv = document.createElement("div");
					var namespan = document.createElement("span");
					namespan.innerHTML = "<a href='/GarbageClassification/CommonGoodServlet?method=selectByCid&cid="
							+ item.cid
							+ "' target='_blank'>"
							+ item.cname
							+ "</a>";
					var summaryp = document.createElement("p");
					summaryp.innerHTML = item.summary;
					var hiddenp = document.createElement("p");
					hiddenp.innerHTML = "<input type='hidden' name='cid' value='"+item.cid+"'/>";
					var buttonp = document.createElement("p");
					buttonp.innerHTML = "<button type='button' class='donate-btn' data-toggle='modal' data-target='#myModal' onclick='add("
							+ item.cid + ")'>我要支持</button>";
					contentdiv.appendChild(namespan);
					contentdiv.appendChild(summaryp);
					contentdiv.appendChild(hiddenp);
					contentdiv.appendChild(buttonp);
					itemdiv.appendChild(contentdiv);
					donatelist.appendChild(itemdiv);
				}

			}
		});
	}
</script>
</html>