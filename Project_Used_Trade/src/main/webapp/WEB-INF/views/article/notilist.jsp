<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>

<section>
	<h2>공지사항</h2>
	<ul>
		<c:forEach items="${NotiList}" var="notice">
			<li><a href="/article/articles/${notice.anumber}">${notice.anumber}</a></li>
		</c:forEach>
	</ul>
</section>

<%@ include file="../include/footer.jsp"%></html>