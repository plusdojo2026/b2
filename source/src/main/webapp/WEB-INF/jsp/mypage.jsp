<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>ココロノナミ|マイページ</title>
		<link rel="stylesheet" href="css/mypage.css" type="text/css">
	</head>
	<body>
		<header id="head">
			<div class ="logoWrap">
				<img src ="/b2/img/kokorononamiyoko.png" class="logo-pc"> 
				<img src ="/b2/img/kokorononamisumaho.png" class="logo-sf"> 
			</div>
		</header>
		
	<main>
		<h1>マイページ</h1>
		<hr>
			<form method="POST" action="${pageContext.request.contextPath}/MypageServlet">
				ログインID<input type="text" name="userName" value="${user.userName}"><br> 
				パスワード<input type="password" name="pw" value="${user.pw}"><br>
				<input type="submit" name="submit" value="更新" class="btn--red btn--cubic btn--radius"><br> 
				<a href="/b2/HomeServlet" class="btn--red btn--cubic btn--radius">戻る</a>
				<a href="/b2/TopServlet" class="btn--red btn--cubic btn--radius">ログアウト</a>
			</form>
			</main>	
	</body>
</html>