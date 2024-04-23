<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file = "./include/header.jsp" %>

<h1> Board.jsp </h1>
<h2> 회원 정보 : ${loginMember } </h2>


<%-- ${boardList.size() } --%>
viewUpdateStatus : ${viewUpdateStatus }
<div class="content">

	<div class="box">
		<div class="box-header with-border">
			<h3 class="box-title">게시판 목록</h3>
		</div>

		<div class="box-body">
			<table class="table table-bordered">
				<tbody>
					<tr>
						<th style="width: 10px">bno</th>
						<th>title</th>
						<th>writer</th>
					</tr>
					
					<c:forEach var="bVO" items="${boardList }">
						<tr>
							<td>${bVO.bno }</td>
							<td>
								<a href="/read?bno=${bVO.bno }">${bVO.title }</a>
							</td>
							<td>
								${bVO.writer }
							</td>
						</tr>
					</c:forEach>
				
				</tbody>
			</table>
		</div>
		
		<h1><a href = "/register">글쓰기</a></h1>



<%@ include file = "./include/footer.jsp" %>