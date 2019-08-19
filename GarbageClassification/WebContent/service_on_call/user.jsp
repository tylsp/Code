<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>上门服务</title>
    <link type="text/css" rel="stylesheet" href="../css/bootstrap.min.css">
    <script src="../js/jquery-3.4.1.min.js" type="text/javascript"></script>
    <script src="../js/bootstrap.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=3.0&ak=7YRm5aOEdgn2TRHvVixzK2oTUPhuF8X5"></script>
    <script type="text/javascript" src="../js/ajax-lib.js"></script>
    <style type="text/css">
        *{
            margin: 0;
            padding: 0;
        }
        input[type="checkbox"] + label {
            cursor: pointer;
            font-size: 1em;
        }
        [id^="checkbox-"] + label {
            background-color: #ffffff;
            border: 1px solid #666666;
            box-shadow: 0 1px 2px rgba(0,0,0,0.05), inset 0 -15px 10px -12px rgba(0, 0, 0, 0.05);
            padding: 9px;
            border-radius: 3px;
            display: inline-block;
            vertical-align: middle;
        }
        [id^="checkbox-"] + label:active {
            box-shadow: 0 1px 2px rgba(0,0,0,0.05), inset 0 1px 3px rgba(0,0,0,0.1);
        }
        td>input:checked + label{
            background-color: #5bc0de;
            border: 1px solid #428bca;
        }
        #logo:after{
            content: "";
            display: block;
            clear: both;
        }
        #subject{
            width: 85vw;
            margin: 0 auto;
        }
        .tit{
            height:35px; line-height:35px; font-size:16px; color:#333; overflow:hidden;text-align: center;
        }
        td{
            padding: 30px 40px;
        }
    </style>
</head>
<body>
<div id="logo">
    <img src="image/sure.png" width="200px" height="200px" style="margin-left: 9vw;float: left">
    <p style="float: left;line-height: 200px;vertical-align: middle;font-size: 40px;font-weight: bold;color: cornflowerblue">上门服务</p>
    <p style="float: left;line-height: 200px;vertical-align: middle;font-size: 23px;padding-left: 40vw"><span style="color: orangered">全国：</span>173-5881-3391</p>
</div>
<div id="daohang">
    <ul class="nav nav-pills" style="background-color: rgba(217,83,79,0.85);color: white">
        <li role="presentation"><a href="<c:url value='/main/home.jsp'/>" style="color: white;margin-left: 3vw">返回首页</a></li>
        <li role="presentation" class="active" style="color: white;padding-left: 10vw"><a href="#" style="background-color:rgb(205, 80, 76)">服务首页</a></li>
        <li role="presentation"><a href="#" style="color: white;margin-left: 3vw">关于我们</a></li>
        <li role="presentation"><a href="#" style="color: white;margin-left: 3vw">成功案例</a></li>
        <li role="presentation"><a href="#" style="color: white;margin-left: 3vw">联系我们</a></li>
    </ul>
</div>
<div id="carousel-example-generic" class="carousel slide" data-ride="carousel" style="height: 46vh;opacity: 0.95">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
    </ol>
    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
        <div class="item active" style="height: 46vh">
            <img src="image/a1.png" alt="..." >
            <div class="carousel-caption">
                <h3></h3>
                <p></p>
            </div>
        </div>
        <div class="item" style="height: 46vh">
            <img src="image/a4.png" alt="...">
            <div class="carousel-caption">
                <h3></h3>
                <p></p>
            </div>
        </div>
    </div>

    <!-- Controls -->
    <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>


<div id="subject" style="position: relative">
    <p align="center" style="font-size: 23px;font-weight: bold;margin-top: 2.5vh">请选择服务</p><br/>
    <img src="image/sm1.jpg" width="510px" height="390px" style="float: left;">
    <form action="<c:url value='/CallFormServlet'/>" method="post">
    <input type="hidden" name="method" value="CreateOrder">
    <table border="1" cellspacing="0" id="table" style="border: 1px solid rgba(169,169,169,0.5);">
        
    </table>
        <button type="submit" id="button" disabled="disabled" class="btn btn-primary" style="position: absolute; top: 1vh;left: 73.6vw">确认选择</button>
    </form>
</div>
<br/>
</body>
<script type="text/javascript">
	window.onload = function(){
		var table = document.getElementById("table");
		ajax({
			url:"/GarbageClassification/CallItemServlet?method=getAllCallItems",
			param:null,
			type:"JSON",
			callback:function(list){
				var count = 0;
				if(list.length % 4 != 0){
					count = Math.ceil(list.length / 4);
				}else{
					count = list.length / 4;
				}
				for(var i = 0; i < count; i ++){
					//alert(i);
					var tr = document.createElement("tr");
					for(var index = i*4; index < (i+1)*4; index ++){
						var td = document.createElement("td");
						td.innerHTML = "<input type='checkbox' class='box' name="+list[index].cid+" id='checkbox-"+index+"' style='display: none;'><label for='checkbox-"+index+"'style='margin-left: -25px;margin-top: -105px'></label><img src='"+list[index].cimage+"' width='100' height='100'><div class='tit'>"+list[index].cname+"</div>";
						tr.appendChild(td);
					}
					table.appendChild(tr);
				}
			}
		});
	};
	
	function fn(){
		var arr = document.getElementsByClassName("box");
		var btn = document.getElementById("button");
		for(var i = 0; i < arr.length; i++){
			arr[i].onclick = function(){
				for(var i = 0; i < arr.length; i++){
					if(arr[i].checked){
					//alert("gaibian");
						btn.disabled = false;
					}			
				}
			}
		}
	}
	
	setTimeout("fn()",1000);
</script>
</html>