<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>トップページ</title>
</head>
<body>
<h1>トップページ</h1>
<img src="${pageContext.request.contextPath}/img/.png">
<form method="POST" action="${pageContext.request.contextPath}/TopServlet">
	<input type="submit" name="register" value="新規登録">
	<input type="submit" name="login" value="ログイン"><br>
	<img src="${pageContext.request.contextPath}/img/.png">			
</form>

<p>
	こころの状態を記録、管理。<br>
	その日の精神状態を記録、分析。<br>
	瞬間的な感情を書き出すことで安心。<br>
	認知行動療法により考え方をかえてみよう。<br>
</p>
<p>
ココロノナミとは？<br>
〇選べる2つの記録モード<br>
簡易記録：好きなタイミングで自由にメモ。認知行動療法に基づいた「ABCモデル」のテンプレートも使えます。<br>
毎日記録：その日の感情を選び、質問に答えたり、ポジティブな出来事を書き留めたりして記録します。<br>
〇充実の振り返り機能<br>
日別・週別レビュー：過去の記録をいつでも簡単に見直せます。<br>
感情のグラフ化：週別ページでは、1週間の気持ちの浮き沈みがグラフで視覚的に分かります。<br>
</p>
</body>
</html>