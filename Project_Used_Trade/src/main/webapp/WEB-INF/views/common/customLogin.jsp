<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/login" method="post">
		<div class="form-group has-feedback">
			<input type="text" class="form-control" name="username" placeholder="사원번호"> <span class="glyphicon glyphicon glyphicon-user form-control-feedback"></span>
		</div>
		<div class="form-group has-feedback">
			<input type="password" class="form-control" name="password" placeholder="비밀번호"> <span class="glyphicon glyphicon-lock form-control-feedback"></span>
		</div>
		<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
		<div class="row">
			<div class="col-xs-4 col-xs-offset-4">
				<!-- col-xs-offset-4 클래스 추가 -->
				<button type="submit" class="btn btn-primary btn-block btn-flat">Login</button>
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-xs-12 text-center">
				<!-- col-xs-12 클래스 추가하고 텍스트 중앙 정렬 -->
				Manager E-mail : bshrdmanager@gmail.com Manager tel : 010-XXXX-XXX
			</div>
		</div>
	</form>
</body>
</html>