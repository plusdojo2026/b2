<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ココロノナミ|マイページ</title>
</head>
<body>
<h2>マイページ</h2>

<form method="POST" action="${pageContext.request.contextPath}/MypageServlet">
ログインID<input type="text" name="userName" value="${user.userName}"><br> 
パスワード<input type="password" name="pw" value="${user.pw}"><br>
パスワード（確認用）<input type="password" name="password"><br> 
<input type="submit" name="submit" value="更新"><br> 
<input type="submit" name="submit" value="戻る"><br> 
<input type="submit" name="submit" value="ログアウト"><br> 
</form>
</body>
</html>