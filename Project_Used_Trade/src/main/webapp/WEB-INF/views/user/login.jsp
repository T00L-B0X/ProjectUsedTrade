<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
    <%@include file="../include/header.jsp" %>
    
<html>
<head>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>

    <title>홈페이지 로그인</title>
    
    
</head>
<body>
<script type="text/javascript">
	const message = '${message}';
	
	if (message !== '') {
		alert(message);
	}
</script>

<h1>login.jsp</h1>
	
	<fieldset>
		<legend>로그인</legend>
		<form action="/login" method="POST">
		
			아이디 : <input type="text" name="username" id="id" autofocus="autofocus"> <br>
			비밀번호 : <input type="password" name="password"> <br>
			
			
  			<input type="checkbox" name="remember-me">아이디 기억하기<br><a href="/user/findId">아이디 찾기</a> <a href="/user/findPw">비밀번호 찾기</a> 
			<br>
			

			
			
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
			<input type="submit" value="로그인"> <a href="/user/join">회원가입</a>

			
		</form>
	</fieldset>
	<span> 
    <c:if test="${error}">
        <p id="valid" class="alert alert-danger">${exception}</p>
    </c:if>
</span>




<%@include file="../include/footer.jsp" %>

</body>
</html>