<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>logout.jsp</h1>
	
	<form action="/user/logout" method="post">
		
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		
		<input type="button" value="로그아웃" id="button">
		
	</form>
	
	<script type="text/javascript">
		$("#button").on("click",function(e){
			e.preventDefault();
			$("form").submit();
		})
	
	</script>

</body>
</html>