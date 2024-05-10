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

 	user = ${user} <br> 
 	${userid }
	
	
	<div>
	<a href="http://localhost:8088/user/login">로그인창</a>
	
	<form action="/customLogout" method="post">
		
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		
		<input type="submit" value="로그아웃" id="button">
		
	</form>
	</div>
	
	<a href="http://localhost:8088/user/mypage">마이페이지</a>
	
	
	
	<%@include file="../include/footer.jsp" %>
</body>
</html>