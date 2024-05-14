<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../include/header.jsp" %>

<html>
<head>
    <title>홈페이지 로그인</title>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
    
    <style>
        body {
            padding-top: 50px;
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
        }

        .a {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 59vh;
            margin : 20px ;
        }

        .login-box {
            max-width: 400px;
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.1);
            text-align: center;
        }

        .login-box legend {
            font-size: 24px;
            font-weight: bold;
            margin-bottom: 20px;
        }

        .login-box form input[type="text"],
        .login-box form input[type="password"] {
            width: calc(100% - 22px);
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }

        .login-box form input[type="checkbox"] {
            margin-right: 5px;
        }

        .login-box form input[type="submit"],
        .login-box form a {
            display: inline-block;
            padding: 10px 20px;
            margin-top: 10px;
            text-decoration: none;
            color: #fff;
            background-color: #007bff;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
        }

        .login-box form a {
            margin-left: 10px;
            background-color: #6c757d;
        }

        .login-box span p {
            color: #dc3545;
            font-size: 14px;
            margin-top: 10px;
        }

        .login-box a {
            color: #007bff;
            text-decoration: none;
        }
    </style>
</head>
<body>

    <script type="text/javascript">
        const message = '${message}';
        
        if (message !== '') {
            alert(message);
        }
    </script>

    <div class="a">
        <div class="login-box">
            <fieldset>
                <legend>로그인</legend>
                <form action="/login" method="POST">
                    아이디 : <input type="text" name="username" id="id" autofocus="autofocus"> <br>
                    비밀번호 : <input type="password" name="password"> <br>
<!--                     <input type="checkbox" name="remember-me">아이디 기억하기 -->
                    <br>
                    <input type="submit" value="로그인"> <a href="/user/join">회원가입</a> 
                    <br>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <a href="/user/findId">아이디 찾기</a> <a href="/user/findPw">비밀번호 찾기</a>
                </form>
            </fieldset>
            <span> 
                <c:if test="${error}">
                    <p id="valid" class="alert alert-danger">${exception}</p>
                </c:if>
            </span>
        </div>
    </div>


    <%@ include file="../include/footer.jsp" %>
</body>
</html>