<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="-1">
<title>Insert title here</title>
</head>
<body>

</body>
<script type="text/javascript">

window.onload=function jump(){ 
		window.setTimeout("window.location.href='<c:url value='/PageServlet?method=findAll&pageSize=5&ostate=-1' />';",0 )	
	}
</script>

</html>