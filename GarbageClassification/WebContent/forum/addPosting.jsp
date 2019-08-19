<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fm" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="-1">
<style type="text/css">
*{
    margin:0 auto;
    padding:0px;
}

/*下拉列表*/
#select{
 height:40px;
}
#search {
	padding: 0px 0px 0px 38px;
	margin: 10px 0px;
	width: 100%;
	height: 50px;
	border: none;
	border-radius: 20px;
	box-sizing: border-box;
	color: #808080;
	font-size: 18px;
}

#content{
 margin: 0px 10px;
 font-size: 18px;
}
#submit{
 width:100px;
 height:40px;
 border-radius:10px;
 background-color:#59a7ff;
 color:white;
 float:right;
}
select option{
  font-size: 18px;
}
</style>
<title>发帖页面</title>
</head>
<body>
<form action="<c:url value='/ForumServlet'/>" target="_blank">
	<input type="hidden" name="method" value="insertPosting"/>
	<p>所属板块：
	<select name="tid" id="select">
		<option selected="selected">请选择发帖主题</option>
		<option value="1">公益</option>
		<option value="2">商城</option>
		<option value="3">垃圾分类</option>
	</select>
	</p>
	<p><input type="text" name="title" placeholder="请输入帖子标题" id="search"/></p>
	<textarea rows="25" cols="82" name="content" id="content"></textarea>
	<p><input type="submit" value="提交" id="submit"/></p>
</form>
</body>
</html>