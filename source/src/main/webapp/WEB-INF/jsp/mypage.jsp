<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
			<form method="POST" action="${pageContext.request.contextPath}/MypageServlet">
			<div class="form">
				ログインID<input type="text" name="userName" value="${user.userName}"><br> 
				パスワード<input type="password" id="pw" name="pw" value="${user.pw}" onclick="this.type='text'" onblur="this.type='password'">
			</div>	
				<a href="/b2/HomeServlet" class="btn--red btn--cubic btn--radius">戻る</a>
				<input type="submit" name="submit" value="更新" class="btn--red btn--cubic btn--radius"><br> 
				<a href="/b2/TopServlet" id="logout">ログアウト</a>
			</form>
			</main>
<script>
			'use strict';
			
				document.getElementById('logout').onclick = function(event){
			    event.preventDefault();
			    let ans =confirm('ログアウトしますか？');
			    if(ans === false){
			        event.preventDefault();
			    }else{
			        window.location.replace('/b2/TopServlet'); /*→location：元の画面に戻る,replace:戻るボタンを押しても戻らない*/ 
			    }
			};
		</script>   		
	</body>
</html>