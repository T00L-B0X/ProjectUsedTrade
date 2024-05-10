<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="/resources/article/css/topic.css" />
<script type="text/javascript" src="/resources/article/js/topic.js"></script>

<%@ include file="../include/header.jsp"%>

<section class="topic">
	${user }

	<input type="hidden" id="csrfToken" value="${_csrf.token}">
	<input type="hidden" id="userid" value="${user.userid}">

	<select id="location" name="LOCATNS">
		<option value="전국">전국</option>
		<option value="강원권">강원권</option>
		<option value="광주권">광주권</option>
		<option value="대구권">대구권</option>
		<option value="대전권">대전권</option>
		<option value="부산울산권">부산울산권</option>
		<option value="제주권">제주권</option>
		<option value="수도권">수도권</option>
	</select>

	<br>
	<input type="text" id="title" name="ARTITLE" placeholder="제목을 입력하세요.">

	<br>
	<textarea rows="10" cols="100" id="content" name="CONTENT" placeholder="내용을 입력하세요."></textarea>

	<br>
	<button onclick="getArticle();">작성하기</button>
	<button onclick="href">취소하기</button>

</section>

<%@ include file="../include/footer.jsp"%>