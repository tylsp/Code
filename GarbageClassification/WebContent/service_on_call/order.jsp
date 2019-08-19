<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fm" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单</title>
    <%-- <%
    String path = request.getContextPath();
	%> --%>
	<link href="<c:url value='/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css">
    <script src="<c:url value='/js/jquery-3.4.1.min.js'/>" type="text/javascript"></script>
    <script src="<c:url value='/js/bootstrap.min.js'/>" type="text/javascript"></script>
    <script type="text/javascript" src="<c:url value='/js/ajax-lib.js'/>"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=3.0&ak=7YRm5aOEdgn2TRHvVixzK2oTUPhuF8X5"></script>
	<style type="text/css">
		* {
			margin: 0;
			padding: 0;
		}
		
		#subject {
			width: 65vw;
			margin: 0 auto;
		}
		
		table {
			/* width: 700px; */
			font-size: .938em;S
			border-collapse: collapse; /*边框会合并为一个单一的边框*/
		}
		
		th {
			text-align: left;
			padding: 0.5em 0.6em;
			font-size:19px;
			font-weight: bold;
			background: rgba(102, 103, 124, 0.7);
			color: #fff;
		}
		
		td {
			padding: .5em .5em;
			border-bottom: solid 1px #ccc;
			background: rgba(255, 255, 255, 0.7);
		}
		
		table, table tr th, table tr td {
			border: 1px solid #0094ff;
		} /*设置边框的*/
	</style>
</head>
<body>
<div class="jumbotron" style="width: 100vw;margin-left: 0;padding-left: 10vw">
    <h1>订单信息</h1>
    <p>Door-to-door Service Order Information</p>
</div>

<!-- Modal -->
<div style="position: relative">
    <form action="CallFormServlet" method="post">
        <input type="hidden" name="method" value="submitOrder"/>
        <input type="hidden" name="oid" value="${CallOrder.oid}"/>
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">上门回收</h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label><span style="color: red">* </span>姓名</label>
                            <input type="text" class="form-control" name="oname">
                        </div>
                        <div class="form-group">
                            <label><span style="color: red">* </span>电话号码</label>&nbsp;&nbsp;&nbsp;<span id="telMsg"></span>
                            <input type="text" class="form-control" name="otel" id="tel">
                        </div>
                        <div class="form-group">
                            <label><span style="color: red">* </span>上门取件时间</label>
                            <input type="date" class="form-control" name="gotime">
                        </div>
                        <div class="form-group">
                            <div id="allmap"></div>
                            <label><span style="color: red">* </span>上门取件地址 &nbsp;<input style="border: none;color: red;font-weight: 100" size="12" type="text" name="cityPlace" id="cityName" value="当前位置：未定位" readonly>
                                <span id="dingwei" class="glyphicon glyphicon-map-marker" style="text-indent: 17.2em;border: none;background-color: white;color: red;">点击获取地址</span></label>
                            <input type="text" class="form-control" name="xiangxi" required placeholder="请输入详细地址">
                        </div>
                        <div class="form-group">
                            <label>备注</label>
                            <textarea class="form-control" rows="3" name="remark"></textarea>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="submit" class="btn btn-primary">提交</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>

<div id="subject">
	<table border="1" width="100%" cellspacing="0">
		<c:if test="${not empty CallOrder}">
			<tr bgcolor="gray" bordercolor="gray" align="center">
				<th colspan="5">订单编号：${CallOrder.oid} &nbsp;&nbsp;
				成交时间：<fm:formatDate value="${CallOrder.ordertime}" pattern="yyyy-MM-dd hh:mm:ss"/> &nbsp;&nbsp;
				<c:if test="${CallOrder.state eq 1}">
				当前状态：待确认！</th>
				</c:if>
				<c:if test="${CallOrder.state eq 2}">
				当前状态：待接单！</th>
				</c:if>
				<c:if test="${CallOrder.state eq 3}">
				当前状态：订单已接收</th>
				</c:if>
				<c:if test="${CallOrder.state eq 4}">
				当前状态：订单完成</th>
				</c:if>
			</tr>
			<tr style="background-color: gray;font-size: 15px" align="center">
				<td>回收类型</td>
				<td>服务</td>
				<td>单价(/kg)</td>
				<td>请输入预估重量：</td>
				<td>小计</td>
			</tr>
			<c:if test="${not empty CallOrder.corderitemlist}">
				<c:forEach items="${CallOrder.corderitemlist}" var="item" varStatus="status">
					<tr bordercolor="gray" align="center">
						<td width="15%">
							<div>
								<img src="<c:url value='/service_on_call/${item.callItem.cimage}'/>" height="75">
							</div>
						</td>
						<td>${item.callItem.cname}</td>
						<td>${item.callItem.cprice}</td>
						<td><!-- var td = document.getElementById("") -->
							<input type="text" class="form-control" placeholder="weight(kg)" 
							id="weight${status.index}" onblur="setsubtotal('${status.index}','${item.callItem.cprice}','${item.iid}','${CallOrder.oid}')">
						</td>
						<td id="subtotal${status.index}">0.0</td>
					</tr>
				</c:forEach>
				<!-- <tr style="font-size: 16px" align="">
					<td colspan="2">您预计获得的收益为：<span style="font-size:17px;color: red"></span></td>
					<td colspan="1">取消订单</td>
					<td colspan="2">提交订单</td>
				</tr> -->
			</c:if>
		</c:if>
	</table>
</div>
<form action="CallFormServlet" method="get">
<input type="hidden" name="method" value="cancelOrder"/>
<input type="hidden" name="oid" value="${CallOrder.oid}"/>
<nav class="navbar navbar-default navbar-fixed-bottom">
    <div class="container" style="line-height: 60px;vertical-align: middle">
        <span style="font-size: 20px">您预计获得的收益为：<span id="total" style="font-size:22px;color: red"></span></span>
        <button type="button" class="btn btn-danger btn-lg" data-toggle="modal" data-target="#myModal" style="float: right;margin-top: 10px;margin-left: 4vw;border-radius: 0">提交订单</button>
        <button type="submit" class="btn btn-warning btn-lg" style="float: right;margin-top: 10px;border-radius: 0">取消订单</button>
    </div>
</nav>
</form>
</body>
<script type="text/javascript">
	var regix = /^1([38]\d|5[0-35-9]|7[3678])\d{8}$/;
	var tel = document.getElementById("tel");
	var telMsg = document.getElementById("telMsg");
	tel.onblur = function(){
		if(regix.test(tel.value)){
		telMsg.style.color = "cornflowerblue";
			telMsg.innerHTML="OK！";
		}else{
			telMsg.style.color = "red";
			telMsg.innerHTML="手机号码错误！";
		}
	}
	
	var count = 0.0;
	var total = document.getElementById("total");
	function setsubtotal(id,price,iid,oid){
		var weight = document.getElementById("weight"+id).value;
		var subtotal = document.getElementById("subtotal"+id);
		ajax({
			url:"CallFormServlet?method=getSubtotal&weight="+weight+"&price="+price+"&iid="+iid+"&oid="+oid,
			param:null,
			type:"TEXT",
			callback:function(data){
				subtotal.innerHTML= weight * price;
				//count += Number(data);
				total.innerHTML="￥" + data;
			}
		})
	}
	
	// 百度地图API功能
    var cityname = document.getElementById("cityName");
    document.getElementById("dingwei").onclick = function () {
        // cityname.value = "qascac";
        var map = new BMap.Map("allmap");
        var point = new BMap.Point(115.331398,40.897445);
        map.centerAndZoom(point,12);

        function myFun(result){
            var cityName = result.name;
            map.setCenter(cityName);
            cityname.value = "当前位置:"+cityName;
            //alert("当前定位城市:"+cityName);
        }
        var myCity = new BMap.LocalCity();
        myCity.get(myFun);
    };
</script>
</html>