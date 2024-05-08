<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>showId.jsp</h1>
	
	<c:choose>
        <c:when test="${empty result}">
            조회결과가 없습니다. <br>
        </c:when>
        
        <c:otherwise>
            아이디는 ${result.userid} 입니다. <br>
        </c:otherwise>
    </c:choose>
    
	<a href="/user/login">로그인 하러가기</a>
	
</body>
</html>