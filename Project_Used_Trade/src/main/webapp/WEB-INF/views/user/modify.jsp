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

	<h1>modify.jsp</h1>
	
	${user} <br>
	
	소개글 : <textarea rows="5" cols="20"></textarea> <br>
	이름 : <br>
	아이디 : <br>
	비밀번호 : <br>
	생년월일 : <br>
	성별 : <br>
	휴대폰 번호 : <br>
	이메일 : <br>
	인증코드 : <br>
	
	<input type="submit" value="수정하기">
	
	
<%@include file="../include/footer.jsp" %>
</body>
</html>