<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file = "./include/header.jsp" %>

<h1> 로그인 정보 : ${user }</h1>
<h1> homepage </h1>

<h1> 이게 맞나?</h1>
<h1> 체크 페이지</h1>
<h3> <a href = "/list">게시판으로 이동</a> </h3>
<h3> <a href = "/register">글쓰기</a> </h3>
<h3> <a href = "/chatting">채팅방으로 이동</a></h3>

<%@ include file = "./include/footer.jsp" %>