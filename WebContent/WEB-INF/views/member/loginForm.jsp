<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 폼</title>
</head>
<body>
<div class="container">
<h1>로그인 폼(member)</h1>
	<form action="login.do" method="post">
		<div class="form-group">
			<label for="id">아이디</label>
			<input name="id" id="id" class="form-control" autocomplete="on" />
		</div>
		<div class="form-group">
			<label for="pw">비밀번호</label>
			<input name="pw" id="pw" class="password form-control" />
		</div>
		<button class="btn btn-default">로그인</button>
		<a href="writeForm.do" class="btn btn-default">회원가입</a>
	</form>
</div>
</body>
</html>