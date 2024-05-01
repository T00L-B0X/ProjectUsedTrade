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
				<button type="submit" class="btn btn-success" id = "joinBtn" onclick = "location.href='#'">이 사람이랑 채팅하기</button>
			</div>
	</div>


</div>
<script type = "text/javascript">
$(function() {
	var joinBtn = $("#joinBtn");
	
	var ChatObject = {
			"userid" : "${vo.writer}",
			"user_name" : "${user.user_name}",
			"chat_title" : "${vo.title}",
			"auth_role" : "채팅인원"
	}
	console.log(ChatObject);
	
	joinBtn.on("click", function() {
		alert("클릭하셨습니다");
	})
})

</script>



<%@ include file = "./include/footer.jsp" %>