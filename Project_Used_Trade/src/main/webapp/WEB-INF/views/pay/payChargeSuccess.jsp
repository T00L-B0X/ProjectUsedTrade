<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file = "../include/header.jsp" %>

<h1> 충전 성공! </h1>

<h2> 충전 후 페이 금액 : ${pResultVO.PAY_BALANCE }원</h2>

<a href="payCharge"> 홈으로 이동(페이충전 페이지) </a>

<%@ include file = "../include/footer.jsp" %>