<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ココロノナミ|ホームページ</title>
</head>
<body>
<h2>ホームページ</h2>
<div>通算ログイン：${user.daysTotalLogin}</div>
<div>連続ログイン：${user.loginStreak}</div>
<div>水深：${user.depthCurrent}</div>
</body>
</html>