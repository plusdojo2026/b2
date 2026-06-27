<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>ココロノナミ|ログインページ</title>
		<link rel="stylesheet" href="css/login.css" type="text/css">
	</head>
	<body>
		<header id="head">
			<div class ="logoWrap">
				<img src ="/b2/img/kokorononamiyoko.png" class="logo-pc"> 
				<img src ="/b2/img/kokorononamisumaho.png" class="logo-sf"> 
			</div>
		</header>
		
		<main>
		<h1>ログイン</h1>
			
					<form method="POST" action="${pageContext.request.contextPath}/LoginServlet">
						<div class="form">
							ログインID<input type="text" name="userName" placeholder="kokorononami73"><br> 
							パスワード<input type="password" name="pw" placeholder="QAwSeDrftgyHUjiKolp"><br> 
						</div>
						<a href="/b2/UserServlet" class="btn--red btn--cubic btn--radius">新規登録はこちら</a>
						<input type="submit" name="login" value="ログイン" class="btn--red btn--cubic btn--radius">
						
					</form>
					
		</main>
<c:if test="${not empty loginError}">
<script>
    document.addEventListener("DOMContentLoaded", function() {
        alert("${newUserRegisterror}");
    });
</script>
</c:if>		
			
	</body>
</html>