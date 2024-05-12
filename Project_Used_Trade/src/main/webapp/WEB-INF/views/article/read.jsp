<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link rel="stylesheet" href="/resources/article/css/read.css" />
<script type="text/javascript" src="/resources/article/js/read.js"></script>

<%@ include file="../include/header.jsp"%>

<section class="article">
	<div class="head">
		<div class="title">
			<input type="hidden" id="csrfToken" value="${_csrf.token }">
			<input type="hidden" id="userid" value="${memberVO.userid }">
			<input type="hidden" id="anumber" value="${articleVO.anumber }">
		
			<span class="artitle">${articleVO.artitle }</span>
			<span class="locatns">지역: ${articleVO.locatns }</span>
			<span class="cnt">조회수: ${articleVO.viewcnt } 좋아요: ${articleVO.likecnt }</span>
			<c:if test="${memberVO.userid eq articleVO.userid or fn:contains(memberVO.authList[0].auth, 'ROLE_ADMIN')}">
				<button onclick="location.href=`/article/modify/${articleVO.anumber}`;">수정하기</button>
				<button onclick="deleteArticle();">삭제하기</button>
			</c:if>
		</div>
		<div class="writer">
			<span>${articleVO.userid }</span>
			<span>${articleVO.edtdate }</span>
		</div>
	</div>
	<div class="content">
		${articleVO.content }
	</div>
	
	
	<div class="like">
		<c:if test="${like eq 1 }">
			<button onclick="dislike()">하트</button>
		</c:if>
		<c:if test="${like eq 0 }">
			<button onclick="like()">빈하트</button>
		</c:if>
	</div>
	
	<button>목록으로</button>
	<button>신고하기</button>
	
	<div class="comment">
		댓글 목록
	</div>
</section>

<%@ include file="../include/footer.jsp"%>
