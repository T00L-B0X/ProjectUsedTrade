<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file = "./include/header.jsp" %>

<!-- 임시 로그인 화면 : 기능 완성 시 삭제 -->
<h1>/naver/login.jsp</h1>
	
	<fieldset>
	  <legend>회원 로그인</legend>
	  <form action="" method="post">
	  	 아이디 : <input type="text" name="userid"><br>
	  	 비밀번호 : <input type="password" name="user_pw"><br>
	  	 
	  	 <input type="submit" value="로그인">
	  	 <input type = "button" value = "회원가입" 
	  	 onclick="location.href = '/naver/memberjoin';">
	  	 <!--컨트롤러 - memberLoginPOST(MemberVO,HttpSession) 호출 --> 
	  </form>
	</fieldset>

<%@ include file = "./include/footer.jsp" %>