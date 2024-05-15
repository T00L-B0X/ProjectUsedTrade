<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import="java.time.LocalDate" %>
<c:set var="today" value="<%= java.time.LocalDate.now() %>" />

<link rel="stylesheet" href="/resources/article/css/list.css" />
<script type="text/javascript" src="/resources/article/js/list.js"></script>

<%@ include file="../include/header.jsp"%>

<section>
	<%@ include file="../include/slider.jsp" %>
	
<!-- 	<a href="/article/notilist">공지리스트</a> -->
	<select id="locatns" name="locatns" onchange="changeLocatns()">
		<option value="" <c:if test="${cri.locatns eq '' }">selected</c:if>>전국</option>
		<option value="강원권" <c:if test="${cri.locatns eq '강원권' }">selected</c:if>>강원권</option>
		<option value="광주권" <c:if test="${cri.locatns eq '광주권' }">selected</c:if>>광주권</option>
		<option value="대구권" <c:if test="${cri.locatns eq '대구권' }">selected</c:if>>대구권</option>
		<option value="대전권" <c:if test="${cri.locatns eq '대전권' }">selected</c:if>>대전권</option>
		<option value="부산울산권" <c:if test="${cri.locatns eq '부산울산권' }">selected</c:if>>부산울산권</option>
		<option value="제주권" <c:if test="${cri.locatns eq '제주권' }">selected</c:if>>제주권</option>
		<option value="수도권" <c:if test="${cri.locatns eq '수도권' }">selected</c:if>>수도권</option>
	</select>
	
	소통해요 > <a href="/article/list">전체</a> <a href="/article/notilist">공지</a>

    <table class="table">
		<tr style="background-color: #87CEFA">
    		<th>글번호</th>
    		<th>글제목</th>
    		<th>작성자</th>
    		<th>작성일</th>
    		<th>조회수</th>
    		<th>좋아요</th>
    	</tr>
    	<c:forEach items="${NotiList5}" var="notice">
    		<tr style="background-color: #ccc">
    			<td>${notice.anumber }</td>
	    		<td><a href="/article/articles/${notice.anumber }">${notice.artitle }</a></td>
	    		<td>${notice.userid }</td>
	    		<td>
	    			<c:choose>
					    <c:when test="${fn:substring(notice.regdate, 0, 10) == today}">
					        ${fn:substring(notice.regdate, 11, 16)}
					    </c:when>
					    <c:otherwise>
					        ${fn:substring(notice.regdate, 5, 10)}
					    </c:otherwise>
					</c:choose>
	    			<c:if test="${notice.regdate ne notice.edtdate }">(수정됨)</c:if>
	    		</td>
	    		<td>${notice.viewcnt }</td>
	    		<td>${notice.likecnt }</td>
	    	</tr>
    	</c:forEach>
    	<c:set var="adCount" value="0" />
		<c:set var="adList" value="${[ad0, ad1, ad2]}" />
    	<c:forEach items="${ArticleList }" var="article" varStatus="loop">
	    	<tr style="background-color: #fff">
	    		<td>${article.anumber }</td>
	    		<td><a href="/article/articles/${article.anumber }">${article.artitle }</a></td>
	    		<td>${article.userid }</td>
	    		<td>
	    			<c:choose>
					    <c:when test="${fn:substring(article.regdate, 0, 10) == today}">
					        ${fn:substring(article.regdate, 11, 16)}
					    </c:when>
					    <c:otherwise>
					        ${fn:substring(article.regdate, 5, 10)}
					    </c:otherwise>
					</c:choose>
	    			<c:if test="${article.regdate ne article.edtdate }">(수정됨)</c:if>
	    		</td>
	    		<td>${article.viewcnt }</td>
	    		<td>${article.likecnt }</td>
	    	</tr>
	    	<c:if test="${loop.index % 3 == 2 && adCount < 3 }">
	    		<tr style="background-color: #eee">
	    			<td class="ad" colspan="1">광고</td>
	    			<c:set var="maxLength" value="20" />
					<c:set var="content" value="${adList[adCount].artitle}" />
	    			<td class="ad" colspan="6">
	    				<c:choose>
						    <c:when test="${fn:length(content) > maxLength}">
						        <a href="/article/articles/${adList[adCount].anumber }">${fn:substring(content, 0, maxLength)}...</a>
						    </c:when>
						    <c:otherwise>
						        <a href="/article/articles/${adList[adCount].anumber }">${content}</a>
						    </c:otherwise>
						</c:choose>
	    			</td>
            		<c:set var="adCount" value="${adCount + 1}"/>
	    		</tr>
	    	</c:if>
    	</c:forEach>
    </table>
	
	<div class="row">
	    <div class="col-sm-5">
	        <div>Showing ${pageCri.startPage } to ${pageCri.endPage } of ${pageCri.total } entries</div>
	    </div>
	    <div class="col-sm-7">
	        <nav aria-label="Page navigation">
	            <ul class="pagination justify-content-end">
	                <c:if test="${pageCri.prev }">
	                    <li class="page-item">
	                        <a class="page-link" href="/article/list?search=${cri.search }&keyword=${cri.keyword }&locatns=${cri.locatns }&page=${pageCri.startPage - 1 }">Previous</a>
	                    </li>
	                </c:if>
	                <c:forEach var="i" begin="${pageCri.startPage }" end="${pageCri.endPage }">
	                    <li class="page-item ${pageCri.cri.page == i ? 'active' : '' }">
	                        <a class="page-link" href="/article/list?search=${cri.search }&keyword=${cri.keyword }&locatns=${cri.locatns }&page=${i }">${i }</a>
	                    </li>
	                </c:forEach>
	                <c:if test="${pageCri.next }">
	                    <li class="page-item">
	                        <a class="page-link" href="/article/list?search=${cri.search }&keyword=${cri.keyword }&locatns=${cri.locatns }&page=${pageCri.endPage + 1 }">Next</a>
	                    </li>
	                </c:if>
	            </ul>
	        </nav>
	    </div>
	</div>
    
    <div class="searchField">
	    <select id="search" name="search">
			<option value="ARTICLE" <c:if test="${cri.search eq 'ARTICLE' }">selected</c:if>>제목</option>
			<option value="USERID" <c:if test="${cri.search eq 'USERID' }">selected</c:if>>작성자</option>
			<option value="CONTENT" <c:if test="${cri.search eq 'CONTENT' }">selected</c:if>>내용</option>
		</select>
		<input type="text" id="keyword" />
		<button onclick="search()">검색</button>
    </div>
    <div class="writeField">
	    <button onclick="moveArticle(`${memberVO.userid}`);">글 쓰기</button>
	    <c:if test="${not empty memberVO}">
		    <sec:authorize access="hasRole('ROLE_ADMIN')">
		    	<button onclick="location.href='/article/notices';">공지 쓰기</button>
			</sec:authorize>
	    </c:if>
    </div>
</section>

<%@ include file="../include/footer.jsp"%>