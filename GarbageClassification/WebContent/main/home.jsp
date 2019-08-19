<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
     <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>首页</title>
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/bootstrap.css'/>">
    <script src="<c:url value='/js/jquery-3.4.1.min.js'/>" type="text/javascript"></script>
    <script src="<c:url value='/js/bootstrap.min.js'/>" type="text/javascript"></script>
</head>
<body style="background-color: white">
<div id="carousel-example-generic" class="carousel slide" data-ride="carousel" style="height: 48vh">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
        <li data-target="#carousel-example-generic" data-slide-to="2"></li>
    </ol>

    <ul class="nav nav-pills" style="position: absolute;top: 1.5vw;left: 15%;z-index: 100;background-color: rgba(0,0,0,0.8);width: 65vw;border-radius: 5px">
        <li role="presentation" class="active"><a href="/GarbageClassification/user/center.jsp">个人中心</a></li>
        <li role="presentation"><a href="<c:url value='/store/store.jsp'/>">商城</a></li>
        <li role="presentation"><a href="<c:url value='/service_on_call/user.jsp'/>">上门服务</a></li>
        <li role="presentation"><a href="<c:url value='/common_good/PublicBenefit.jsp'/>">公益服务</a></li>
        <li role="presentation" style="margin-left: 38vw"><a href="<c:url value='/user/login.jsp'/>">登录</a></li>
        <li role="presentation"><a href="<c:url value='/search/garbage.jsp'/>">搜索</a></li>
    </ul>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
        <div class="item active" style="height: 48vh">
            <img src="image/a1.png" alt="..." >
            <div class="carousel-caption">
                <h3>俯仰之间，美德立现</h3>
                <p>When you refuse to reuse it’s our Earth you abuse</p>
            </div>
        </div>
        <div class="item" style="height: 48vh">
            <img src="image/a4.png" alt="...">
            <div class="carousel-caption">
                <h3>保护环境，就是爱惜生命</h3>
                <p>To protect the environment is to cherish life</p>
            </div>
        </div>
        <div class="item" style="height: 48vh">
            <img src="image/a3.png" alt="...">
            <div class="carousel-caption">
                <h3>给我一片绿，还你一片荫</h3>
                <p>Give me a piece of green, and give you a shade</p>
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
<br/>
<div class="row" style="display: inline-block;width: 300px;padding: 10px;margin-left: 13vw">
    <div class="col-sm-3 col-md-7">
        <div class="thumbnail" style="border: none">
            <img src="image/login.png" alt="..." style="display: inline-block;border-radius: 30%">
            <div class="caption" style="text-align: center">
                <h3 style="font-size: 20px">登录</h3>
                <p style="font-size: 12px;">登录：您可选择用户登录或者管理员登录，以方便您选择服务</p>
                <p><a href="<c:url value='/user/login.jsp'/>" class="btn btn-primary" role="button">前往登录</a></p>
            </div>
        </div>
    </div>
</div>

<div class="row" style="display: inline-block;width: 300px;padding: 10px">
    <div class="col-sm-3 col-md-7">
        <div class="thumbnail" style="border: none">
            <img src="image/shopping.png" alt="..." style="display: inline-block">
            <div class="caption" style="text-align: center">
                <h3 style="font-size: 20px">商城</h3>
                <p style="font-size: 12px;">商城：您可以选择需要购买的物品或是积分兑换本服务的商品</p>
                <p><a href="<c:url value='/store/store.jsp'/>" class="btn btn-primary" role="button">前往商城</a></p>
            </div>
        </div>
    </div>
</div>

<div class="row" style="display: inline-block;width: 300px;padding: 10px">
    <div class="col-sm-3 col-md-7">
        <div class="thumbnail" style="border: none">
            <img src="image/gongyi.png" alt="..." style="display: inline-block;border-radius: 50%">
            <div class="caption" style="text-align: center">
                <h3 style="font-size: 20px">公益活动</h3>
                <p style="font-size: 12px;">公益活动：您可以通过本页面了解需要帮助的群体并且可以为他们捐款</p>
                <p><a href="<c:url value='/common_good/PublicBenefit.jsp'/>" class="btn btn-primary" role="button">公益活动</a></p>
            </div>
        </div>
    </div>
</div>

<div class="row" style="display: inline-block;width: 300px;padding: 10px">
    <div class="col-sm-3 col-md-7">
        <div class="thumbnail" style="border: none">
            <img src="image/gohome.png" alt="..." style="display: inline-block;border-radius: 50%">
            <div class="caption" style="text-align: center">
                <h3 style="font-size: 20px">上门服务</h3>
                <p style="font-size: 12px;">上门服务：您可以通过本页面来回收家里的垃圾，可以获得积分呦</p>
                <p><a href="<c:url value='/service_on_call/user.jsp'/>" class="btn btn-primary" role="button">上门服务</a></p>
            </div>
        </div>
    </div>
</div>
<div class="row" style="display: inline-block;width: 300px;padding: 10px">
    <div class="col-sm-3 col-md-7">
        <div class="thumbnail" style="border: none">
            <img src="image/luntan.png" alt="..." style="display: inline-block;border-radius: 50%">
            <div class="caption" style="text-align: center">
                <h3 style="font-size: 20px">论坛</h3>
                <p style="font-size: 12px;">论坛：在这里你可以咨询以前的用户，将你所遇到的问题已发帖的形式展示</p>
                <p><a href="<c:url value='/forum/forummain.jsp'/>" class="btn btn-primary" role="button">论坛</a></p>
            </div>
        </div>
    </div>
</div>
</body>
</html>