<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file = "../include/header.jsp" %>

<div class="container py-4">
    <header class="text-center mb-4">
		<h1> 페이 충전 성공! </h1>
    </header>
    <div class="row justify-content-center">
        <div class="col-md-6">
        	<div class="card">
        	    <div class="card-body text-center">
        	    	<h2 class="card-title">충전 후 페이 금액 : ${pResultVO.PAY_BALANCE }원</h2>
					<a href="/goods/goodsMain" class="btn btn-block btn-success"> 홈으로 이동 </a>
				</div>
			</div>
        </div>
    </div>
</div>

<%@ include file = "../include/footer.jsp" %>