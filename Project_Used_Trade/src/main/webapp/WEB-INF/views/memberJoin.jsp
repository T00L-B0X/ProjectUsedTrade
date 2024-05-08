<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file = "./include/header.jsp" %>

	
	<!-- 임시 회원가입 화면 , 기능 완성 시 삭제 -->
	<h1>/naver/memberJoin.jsp</h1><hr>
	
	<h1>첫 방문을 환영합니다.</h1>
	<fieldset>
		<legend>회원가입</legend>
		<form action="" method = "post">
			아이디 : <input type = "text" name = "userid"><br>
			비밀번호 : <input type = "password" name= "user_pw"><br>
			이름 : <input type = "text" name = "user_name"><br>
			이메일 : <input type = "email" name = "user_email"><br>
			
			<input type = "submit" value = "회원가입">
		</form>
	</fieldset>

<%@ include file = "./include/footer.jsp" %>