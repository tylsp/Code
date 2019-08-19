<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fm" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>服务订单</title>
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
			width: 90vw;
			margin: 0 auto;
		}
		
		table {
			/* width: 700px; */
			font-size: .938em;
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
		
		#title{
            background-color: gray;
            font-size: 15px;
        }
	</style>
</head>
<body>
<div class="page-header" style="margin-top: 20px">
  <h1 style="margin-left:3vw">服务订单<small style="margin-left:3vw">My Order</small></h1>
</div>
<!-- Modal -->
<div style="position: relative">
    <form action="<c:url value='/CallFormServlet'/>" method="post">
        <input type="hidden" name="method" value="submitOrder"/>
        <input type="hidden" name="oid" id="oid"/>
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
                            <<label><span style="color: red">* </span>电话号码</label>&nbsp;&nbsp;&nbsp;<span id="telMsg"></span>
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
	<table border="1" width="100%" cellspacing="0" id="table">
	</table>
</div>
<form action="CallFormServlet" method="get">
<input type="hidden" name="method" value="cancelOrder"/>
<input type="hidden" name="oid" value="${CallOrder.oid}"/>
<!-- <nav class="navbar navbar-default navbar-fixed-bottom">
    <div class="container" style="line-height: 60px;vertical-align: middle">
        <span style="font-size: 20px">您预计获得的收益为：<span id="total" style="font-size:22px;color: red"></span></span>
        <button type="button" class="btn btn-danger btn-lg" data-toggle="modal" data-target="#myModal" style="float: right;margin-top: 10px;margin-left: 4vw;border-radius: 0">提交订单</button>
        <button type="submit" class="btn btn-warning btn-lg" style="float: right;margin-top: 10px;border-radius: 0">取消订单</button>
    </div>
</nav> -->
</form>
</body>
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
		
	var table = document.getElementById("table");
	var oid = document.getElementById("oid");
	
	function fn(id){
		//alert(id);
		oid.value=id;
	}
	
	window.onload = function(){
		ajax({
			url:"/GarbageClassification/CallFormServlet?method=getMyOrder&state=-1",
			param:null,
			type:"JSON",
			callback:function(list){
				for(var index in list){
					var tr = document.createElement("tr");
					tr.setAttribute("bgcolor", "gray");
					var th = document.createElement("th");
					th.setAttribute("colspan", "5");
					var msg = "";
					if(list[index].state === 1){
						msg = "当前状态：<span style='color: #d9534f'>待确认！</span>"
					}else if(list[index].state === 2){
						msg = "当前状态：<span style='color: orange'>待受理!</span>"
					}else if(list[index].state === 3){
						msg = "当前状态：<span style='color: #5cb85c'>已受理!</span>"
					}else{
						msg = "当前状态：<span style='color: cornflowerblue'>订单完成!</span>"
					}
					Date.prototype.toLocaleString = function() {
         			return this.getFullYear() + "/" + (this.getMonth() + 1) + "/" + this.getDate() + " " + this.getHours() + ":" + this.getMinutes() + ":" + this.getSeconds();};
					var unixTimestamp = new Date(list[index].ordertime);
					commonTime = unixTimestamp.toLocaleString();
					/* alert(commonTime); */
					th.innerHTML="订单编号:"+list[index].oid+"&nbsp;&nbsp;成交时间:"+commonTime+" &nbsp;&nbsp;"+msg;
					table.appendChild(th);
					var tr1 = document.createElement("tr");
					tr1.setAttribute("align", "center");
					tr1.setAttribute("id", "title");
					tr1.innerHTML="<td>回收类型</td><td>服务</td><td>单价(/kg)</td><td>预估重量</td><td>小计</td>"
					table.appendChild(tr1);
					for(var i in list[index].corderitemlist){
						var tr2 = document.createElement("tr");
						tr2.setAttribute("align", "center");
						tr2.innerHTML="<td><img src='<c:url value='/service_on_call/"+list[index].corderitemlist[i].callItem.cimage+"'/>' height='75'></td><td>"+list[index].corderitemlist[i].callItem.cname+"</td><td>"+list[index].corderitemlist[i].callItem.cprice+"</td><td>"+list[index].corderitemlist[i].iweight+"</td><td>"+list[index].corderitemlist[i].subtotal+"</td>";
						table.appendChild(tr2);
					}
					
					var tr3 = document.createElement("tr");
					if(list[index].state === 1){
						tr3.innerHTML="<td style='font-size: 18px;color: red;padding: 5px;' colspan='5'><span style='padding-top:5px;display:inline-block;'>此单的收益为：￥"+list[index].ctotal+"</span><a href='<c:url value='/CallFormServlet?method=cancelOrder&oid="+list[index].oid+"'/>' class='btn btn-warning btn-default' style='float: right;border-radius: 0'>取消订单</a><button type='button' class='btn btn-danger btn-default' data-toggle='modal' data-target='#myModal' style='float: right;margin-right: 0.4vw;border-radius: 0' onclick=\"fn('"+list[index].oid+"')\">确认订单</button></td>";
						/* var btn = document.getElementById("btn");
						alert(btn);
						btn.onclick = function () {
					        oid.value="dsvv";
					    }; */
					    
					}else if(list[index].state === 4){
						tr3.innerHTML="<td style='font-size: 18px;color: red;padding: 5px;' colspan='5'><span style='padding-top:5px;display:inline-block;'>此单的收益为：￥"+list[index].ctotal+"</span><span style='padding-top:5px;display:inline-block;float:right;margin-right:2px;color:cornflowerblue'>订单已完成<span></td>";
					}else{
						tr3.innerHTML="<td style='font-size: 18px;color: red;padding: 5px;' colspan='5'><span style='padding-top:5px;display:inline-block;'>此单的收益为：￥"+list[index].ctotal+"</span><a href=<c:url value='/CallFormServlet?method=cancelOrder&oid="+list[index].oid+"'/>' class='btn btn-warning btn-default' style='float: right;border-radius: 0'>取消订单</a></td>";
					}
					table.appendChild(tr3);
				}
			}
		});
	}
	
	// 百度地图API功能
    var cityname = document.getElementById("cityName");
    document.getElementById("dingwei").onclick = function () {
        var map = new BMap.Map("allmap");
        var point = new BMap.Point(116.331398,39.897445);
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