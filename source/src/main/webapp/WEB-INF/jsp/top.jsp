<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/top.css">
<title>トップページ</title>
</head>
<body>
<img src="${pageContext.request.contextPath}/img/logo.png" class="logo">
<div class="hero">
<img src="${pageContext.request.contextPath}/img/top_clione.png" class="top_clione">	

<div class="content">

<%if (session.getAttribute("user") == null) {%>

<form method="POST" action="${pageContext.request.contextPath}/TopServlet">
	<input type="submit" name="register" value="新規登録" class="btn--red btn--cubic btn--radius">
	<input type="submit" name="login" value="ログイン" class="btn--red btn--cubic btn--radius"><br>			
</form>

<%}%>
<p>
ココロノナミとは？<br>

<br>〇選べる2つの記録モード<br>
簡易記録では好きなタイミングで自由にメモ。<br>認知行動療法に基づいた「ABCモデル」のテンプレートも使えます。<br>
毎日記録ではその日の感情を選び、質問に答えたり、<br>
ポジティブな出来事を書き留めたりして記録します。<br>
<br>〇充実の振り返り機能<br>
日別・週別レビューでは過去の記録をいつでも簡単に見直せます。<br>
感情のグラフ化で1週間の気持ちの浮き沈みがグラフで視覚的に分かります。<br>
</p>
</div>

</div>
</body>
</html>