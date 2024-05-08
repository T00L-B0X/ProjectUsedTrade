<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>findId.jsp</h1>
	
	<fieldset>
		<legend>아이디 찾기</legend>
		<form action="" method="POST">
			이름 : <input type="text" name="name" > <br>
			이메일 : <input type="text" name="email"> <br>
			
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
			<input type="submit" value="아이디 찾기">
		</form>
	</fieldset>
	

</body>
</html>