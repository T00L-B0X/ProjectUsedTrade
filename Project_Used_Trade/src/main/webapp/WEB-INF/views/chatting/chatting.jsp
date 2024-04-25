<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file = "../include/header.jsp" %>

<h1> 채팅방 </h1>
<h2> 사용자 정보 : ${user }</h2>
<h2> userid : ${sessionScope.user.userid }</h2>
<h2> user_name : ${sessionScope.user.user_name }</h2>
chatList : ${chatList }
<h2> 페이지에 저장된 모든 Session : ${sessionScope }</h2>

<h2> userid : ${userid }</h2>
<div style="margin-left: 20px;">
		<h2>채팅방 생성</h2>
		<form method="post">
			채팅방 제목 : <input id="chat_title" type="text" name="chat_title">
			<input type="hidden" name="userid" value="${sessionScope.user.userid }">
			<input type="hidden" name="user_name" value="${sessionScope.user.user_name }">
			<input id="subBtn" type="button" value="생성하기">
		</form>
	</div>
	<hr>
	<div>
		<table border="1">
			<tr>
				<td align="center" width="80">번호</td>
				<td align="center" width="100">작성자</td>
				<td align="center" width="320">제목</td>
				<td align="center" width="180">작성일</td>
				<td align="center" width="100">신청</td>
			</tr>
			<c:choose>
				<c:when test="${empty chatList }">
					<tr>
						<td align="center" colspan="5">조회하실 게시물이 존재하지 않습니다.</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${chatList }" var="chat">
						<tr>
							<td align="center">${chat.chat_no }</td>
							<td align="center">${chat.user_name }</td>
							<td align="center">${chat.chat_title }</td>
							<td align="center">${chat.chat_date }</td>
							<td align="center"><input type="button" class="joinBtn" value="가입하기" id="${chat.chat_no }"></td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>	
	</div>
<script type="text/javascript">
$(function(){
	var	joinBtn = $(".joinBtn");
	
	joinBtn.on("click", function(){
		var chatNo = $(this).attr("id");
		var chatObject = {
			"userid" : "${sessionScope.user.userid}",
			"user_name" : "${sessionScope.user.user_name}",
			"chat_no" : stNo,
			"auth_role" : "채팅인원"
		}
		console.log(studyObject);
		$.ajax({
			type : "post",
			url : "/joinChat",
			data : JSON.stringify(studyObject),
			contentType : "application/json; charset=utf-8",
			success : function(res){
				if(res === "SUCCESS"){
					alert("가입에 성공하였습니다.")
				}else{
					alert("가입에 실패하였습니다. 다시 시도해주세요.")
				}
			}
		})
		
	})
	
	$("#subBtn").on("click", function(){
		var stTitle = $("#chat_title").val();
		
		if(stTitle == ""){
			alert("제목을 입력하세요")
			return false;
		}
		
		$("form").submit();
	})
	
})
</script>




<%@ include file = "../include/footer.jsp" %>