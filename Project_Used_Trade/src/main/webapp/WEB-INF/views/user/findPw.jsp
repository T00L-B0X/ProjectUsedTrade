<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>findPw.jsp</h1>
	
	<fieldset>
		<legend>비밀번호 찾기</legend>
		<form action="" method="POST">
			아이디 : <input type="text" name="id"> <br>
			이메일 : <input type="text" name="email"> <br>
			
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
			<input type="submit" value="비밀번호 찾기">
		</form>
	</fieldset>
	


</body>
</html>