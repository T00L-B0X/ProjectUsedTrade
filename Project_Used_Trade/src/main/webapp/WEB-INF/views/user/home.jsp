<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="../include/header.jsp" %>
    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>home.jsp</h1>

	user = ${user}

	<div>
	<a href="http://localhost:8088/user/login">로그인창</a>
	
	<a href="/user/logout">로그아웃</a>
	</div>
	
	<a href="http://localhost:8088/user/mypage">마이페이지</a>
	
	
	
	<%@include file="../include/footer.jsp" %>
</body>
</html>