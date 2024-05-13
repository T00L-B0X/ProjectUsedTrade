<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            text-align: center;
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.1);
        }
        h1 {
            color: #007bff;
            margin-bottom: 20px;
        }
        .result-message {
            font-size: 18px;
            margin-bottom: 20px;
        }
        .login-link {
            color: #007bff;
            text-decoration: none;
        }
        .login-link:hover {
            text-decoration: underline;
        }
    </style>

</head>
<body>

	<div class="container">
        <h1>Show ID</h1>
        <c:choose>
            <c:when test="${empty result}">
                <p class="result-message">조회 결과가 없습니다.</p>
            </c:when>
            <c:otherwise>
                <p class="result-message">아이디는 ${result.userid} 입니다.</p>
            </c:otherwise>
        </c:choose>
        <a href="/user/login" class="login-link">로그인 하러가기</a>
    </div>
</body>
</html>