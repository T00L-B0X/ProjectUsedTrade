<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file = "../include/header.jsp" %>

<h1> 환불 신청 성공! </h1>

<h2> 관리자가 확인 후 환불 (예상일 : 1~3일) </h2>

<h2> 환불 후 예상 페이 금액 : ${pResultVO.PAY_BALANCE }원</h2>

<a href="payRefund"> 홈으로 이동(페이환불 페이지) </a>

<%@ include file = "../include/footer.jsp" %>