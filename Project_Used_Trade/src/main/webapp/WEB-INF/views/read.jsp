<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file = "./include/header.jsp" %>

<h3>회원정보 : ${user } <br> </h3>
<h3>bno : ${bno } </h3>

<div class="content">

	<h1>read.jsp</h1>
	<form role="form" action="" method="get" class="fm">
	   <input type="hidden" name="bno" value="${vo.bno }">
	   
	</form>
	

	<div class="box box-primary">
		<div class="box-header with-border">
			<h3 class="box-title">게시판 본문내용</h3>
		</div>
		
			<div class="box-body">
				<div class="form-group">
					<label for="exampleInputEmail1">제 목</label> 
					<input type="text" class="form-control" 
					 name="title" id="exampleInputEmail1" 
					 value="${vo.title }" readonly>
				</div>
				<div class="form-group">
					<label>작성자 아이디</label>
					<input type="text" class="form-control" name="writer" 
					value="${vo.writer }" readonly>
				</div>
				<div class="form-group">
					<label>작성자 아이디</label>
					<input type="text" class="form-control" name="writer" 
					value="${user.user_name }" readonly>
				</div>


				<div class="form-group">
					<label>내 용</label>
					<textarea class="form-control" rows="3" 
					name="content" readonly>${vo.content }</textarea>
				</div>


			</div>

			<div class="box-footer">
				<button type="submit" class="btn btn-success" onclick = "location.href='/list'">목록이동</button>
				<button type="submit" class="btn btn-success" id = "joinBtn">이 사람이랑 채팅하기</button>
			</div>
	</div>


</div>
<script type="text/javascript">
	$(function() {
		var joinBtn = $("#joinBtn");
		joinBtn.on("click", function() {
			var chat_no = $(this).attr("id");
			var ChatObject = {
				"userid" : "${vo.writer}",
				"user_name" : "${user.user_name}",
				"chat_title" : "${vo.title}",
				"auth_role" : "채팅인원"
			}
			console.log(ChatObject);
			$.ajax({
				type : "post",
				url : "read/joinChat",
				data : JSON.stringify(ChatObject),
				contentType : "application/json; charset=utf-8",
				success : function(response) {
					if (confirm("채팅방이 생성되었습니다. 추가 동작을 수행하시겠습니까?")) {
						// 확인을 누르면 추가적인 ajax 실행
						// 확인을 누름과 동시에 채팅방에 작성자와 채팅 연결
						$.ajax({
							type : "post",
							url : "read/connectChat",
							data : JSON.stringify({}),
							contentType : "application/json; charset=utf-8",
							success : function(response) {
								// 추가 동작이 성공했을 때 처리할 내용을 여기에 작성합니다.
							},
							error : function(xhr, status, error) {
								// 추가 동작이 실패했을 때 처리할 내용을 여기에 작성합니다.
								console.error("추가 동작이 실패했습니다.");
								console.error(xhr.responseText);
							}
						});
					}
				},
				error : function(xhr, status, error) {
					// 채팅방 생성에 실패했을 때 처리할 내용을 여기에 작성합니다.
					console.error("채팅방 생성에 실패했습니다.");
					console.error(xhr.responseText);
				}
			});
		});
	});
</script>



<%@ include file = "./include/footer.jsp" %>