<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ココロノナミ|新規登録</title>
</head>
<body>
<h1>新規登録</h1>
<hr>

		<form method="POST" action="${pageContext.request.contextPath}/UserServlet">
			ID<input type="text" name="userName"><br> 
			PW<input type="password" name="pw"><br> 
			<input type="submit" name="login" value="ログイン">
		</form>
		</body>
</html>