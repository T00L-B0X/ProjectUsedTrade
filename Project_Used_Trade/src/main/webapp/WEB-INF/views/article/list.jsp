<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ include file="../include/header.jsp"%>

<script type="text/javascript">
	function moveArticle() {		
		if(`${memberVO.userid}`) {
			location.href="/article/articles";
		} else {
			const confirmed = confirm('로그인이 필요한 서비스입니다.\n이동하시겠습니까?');
			
			if (confirmed) {
	            location.href="/user/login";
	        }
		}
	}
</script>

<section>
	<a href="/article/notilist">공지리스트</a>
	<h2>공지사항</h2>
    <ul>
        <c:forEach items="${NotiList5}" var="notice">
            <li><a href="/article/articles/${notice.anumber}">${notice.anumber}</a></li>
        </c:forEach>
    </ul>

    <h2>동네소식</h2>
    <ul>
        <c:set var="adCount" value="0" />
        <c:forEach items="${ArticleList}" var="article" varStatus="loop">
            <li><a href="/article/articles/${article.anumber}">${article.anumber}</a></li>
            <c:if test="${loop.index % 3 == 2 && adCount < 3}">
                <li class="ad">${ad1.artitle} (광고)</li>
                <c:set var="adCount" value="${adCount + 1}" />
            </c:if>
        </c:forEach>
    </ul>
    <button onclick="moveArticle();">글 쓰기</button>
    <c:if test="${not empty memberVO}">
	    <sec:authorize access="hasRole('ROLE_ADMIN')">
	    	<button onclick="location.href='/article/notices';">공지 쓰기</button>
		</sec:authorize>
    </c:if>
</section>

<%@ include file="../include/footer.jsp"%>