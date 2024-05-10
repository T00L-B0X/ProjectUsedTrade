<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>record</title>
</head>
<style>
</style>
<body>
<div class="header">
	<h3>경매 기록 조회</h3>
</div>

<div>
<table border="1">
<tbody>
	<tr>
		<th>입찰자</th>
		<th>입찰가</th>
		<th>입찰시간</th>	
	</tr>
	<c:forEach var="rcvo" items="${recordList}">
		<tr>
			<td>${rcvo.ar_userid}</td>
			<td>${rcvo.bid_price }</td>
			<td>${rcvo.bid_time }</td>
		</tr>
	</c:forEach>
</tbody>
</table>
</div>

<div class="footer">
</div>
	
</body>
</html>