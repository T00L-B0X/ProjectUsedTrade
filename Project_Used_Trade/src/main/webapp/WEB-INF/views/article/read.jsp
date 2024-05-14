<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link rel="stylesheet" href="/resources/article/css/read.css" />
<script type="text/javascript" src="/resources/article/js/read.js"></script>

<%@ include file="../include/header.jsp"%>

<script type="text/javascript">
	$(function() {
	    var joinBtn = $("#joinBtn");
	    joinBtn.on("click", function() {
	        var chat_no = $(this).attr("id");
	        var anumber = ${articleVO.anumber};
	        var csrfHeaderName = `${_csrf.headerName}`;
			var csrfTokenValue = `${_csrf.token}`;
	        var ChatObject = {
	            "userid" : `${articleVO.userid}`,
	            "usernm" : `${memberVO.usernm}`,
	            "chat_title" : `${articleVO.artitle}`,
	            "auth_role" : "채팅인원",
	            "chat_type" : 1
	        };
	
	        // 채팅방 생성 Ajax 요청
	        $.ajax({
	            type: "post",
	            url: "/article/joinChat",
	            beforeSend : function(xhr) {
					// CSRF 헤더 설정
					xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
				},
	            data: JSON.stringify(ChatObject),
	            contentType: "application/json; charset=utf-8",
	            success: function(response) {
	                // 채팅방 생성이 성공적으로 완료된 경우 추가 작업을 수행하는 Ajax 요청
	                $.ajax({
	                    type: "post",
	                    url: "/article/connectChat/" + anumber,
	                    beforeSend : function(xhr) {
							// CSRF 헤더 설정
							xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
						},
	                    contentType: "application/json; charset=utf-8",
	                    data: JSON.stringify(ChatObject),
	                    success: function(response) {
	                    	alert(" 채팅이 연결되었습니다. ")
	                        // 성공적으로 처리된 경우의 동작
	                    },
	                    error: function(xhr, status, error) {
	                        // 요청이 실패한 경우의 동작
	                    }
	                });
	            },
	            error: function(xhr, status, error) {
	                // 채팅방 생성에 실패한 경우 처리
	                console.error("채팅방 생성에 실패했습니다.");
	                console.error(error);
	            }
	        });
	    });
	});
	
	
</script>

<section class="article">
	<div class="head">
		<div class="title">
			<input type="hidden" id="csrfToken" value="${_csrf.token }">
			<input type="hidden" id="userid" value="${memberVO.userid }">
			<input type="hidden" id="anumber" value="${articleVO.anumber }">

			<span class="artitle"> ${articleVO.artitle } <c:if test="${articleVO.regdate ne articleVO.edtdate }">
					수정됨
				</c:if>
			</span> <span class="locatns">지역: ${articleVO.locatns }</span> <span class="cnt">조회수: ${articleVO.viewcnt } 좋아요: ${articleVO.likecnt }</span>
			<c:if test="${not empty memberVO and (memberVO.userid eq articleVO.userid or fn:contains(memberVO.authList[0].auth, 'ROLE_ADMIN'))}">
				<button onclick="location.href=`/article/modify/${articleVO.anumber}`;">수정하기</button>
				<button onclick="deleteArticle();">삭제하기</button>
			</c:if>

		</div>
		<div class="writer">
			<span>${articleVO.userid }</span> <span>${articleVO.edtdate }</span>
		</div>
	</div>
	<div class="content">${articleVO.content }</div>


	<div class="like">
		${like }
		<c:if test="${like eq 1 }">
			<button onclick="dislike()">하트</button>
		</c:if>
		<c:if test="${like eq 0 }">
			<button onclick="like()">빈하트</button>
		</c:if>
	</div>

	<button onclick="location.href='/article/list';">목록으로</button>
	<button id="joinBtn">실시간 채팅</button>
	<div class="comment">
		<textarea id="commentContent" rows="5" cols="100"></textarea>
		<button onclick="addComment();">댓글 등록</button>
		<hr>

	<c:forEach items="${commentVO}" var="comment">
	    <li id="comment_${comment.cnumber}">
	        <span>${comment.cnumber}</span>
	        <span id="commentContent_${comment.cnumber}">
	            <c:choose>
	                <c:when test="${comment.deleted == 1}">삭제된 댓글입니다.</c:when>
	                <c:otherwise>${comment.content}, ${comment.userid }</c:otherwise>
	            </c:choose>
	        </span>
	        <c:if test="${memberVO.userid eq comment.userid}">
	            <button id="editButton_${comment.cnumber}" onclick="editComment(${comment.cnumber}, '${comment.content}');">수정하기</button>
	            <c:if test="${comment.deleted eq '0' }">
		            <button id="deleteButton_${comment.cnumber}" onclick="deleteComment(${comment.cnumber});">삭제하기</button>
	            </c:if>
	        </c:if>
	    </li>
	</c:forEach>
	</div>
</section>

<%@ include file="../include/footer.jsp"%>
