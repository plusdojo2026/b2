<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ココロノナミ|ログインページ</title>
</head>
<body>
<h1>ログインページ</h1>
<p>ココロノナミ
<h2>ログイン</h2>

<hr>

		<form method="POST" action="${pageContext.request.contextPath}/LoginServlet">
			ログインID<input type="text" name="userName" placeholder="kokorononami73"><br> 
			パスワード<input type="password" name="pw" placeholder="QAwSeDrftgyHUjiKolp"><br> 
			<input type="submit" name="register" value="アカウントを作成">
			<input type="submit" name="login" value="ログイン">
			
		</form>
		
</body>
</html>