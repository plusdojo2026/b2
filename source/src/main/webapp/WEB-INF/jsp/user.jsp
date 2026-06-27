<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ココロノナミ|新規登録</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>ココロノナミ|ログインページ</title>
		<link rel="stylesheet" href="css/user.css" type="text/css">
</head>
<body>

<header id="head">
			<div class ="logoWrap">
				<img src ="/b2/img/kokorononamiyoko.png" class="logo-pc"> 
				<img src ="/b2/img/kokorononamisumaho.png" class="logo-sf"> 
			</div>
		</header>

<main>
<h1>新規登録</h1>
		<form method="POST" action="${pageContext.request.contextPath}/UserServlet">
		<div class="form">
			ログインID<input type="text" name="userName"><br> 
			パスワード<input type="password" name="pw"><br> 
			</div>
			<a href="/b2/TopServlet" class="btn--red btn--cubic btn--radius">TOPへ</a>
			<input type="submit" name="login" value="新規登録" class="btn--red btn--cubic btn--radius">
		</form>
</main>
<c:if test="${not empty newUserRegisterror}">
<script>
    document.addEventListener("DOMContentLoaded", function() {
        alert("${newUserRegisterror}");
    });
</script>
</c:if>

		</body>
</html>