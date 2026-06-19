<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ココロノナミ|簡易記録入力</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/quick.css">
<style>

</style>
</head>
<body>
<h1>簡易記録</h1>

<p>感情タグ[必須]：</p>
<form method="post" action="${pageContext.request.contextPath}/QuickServlet">
	<input type="radio" name="emotion_id" value="1"> 怒り<br>
	<input type="radio" name="emotion_id" value="2"> 悲しみ<br>
	<input type="radio" name="emotion_id" value="3"> 悔しい<br>
	<input type="radio" name="emotion_id" value="4"> 焦り<br>
	<input type="radio" name="emotion_id" value="5"> 楽しい<br>
	<input type="radio" name="emotion_id" value="6"> 嬉しい<br>
	<input type="radio" name="emotion_id" value="7"> 幸せ<br>
	<input type="radio" name="emotion_id" value="8"> 好き<br>
	
	<div class="tab-2">
	
	    <input type="radio" id="tab-fact" name="tab-2" checked>
	    <label for="tab-fact">事実整理</label>
	
	
	    <input type="radio" id="tab-free" name="tab-2">
	    <label for="tab-free">とにかく記入</label>   
	
		<div class="content-fact">
			<h3>1.出来事</h3>何が起こったのか<br><input type="text" name="event"placeholder="ex)知らない人が私を見て笑った"><br>
			<h3>2.信念</h3>その出来事をどう捉えた？<br><input type="text" name="belief"placeholder="ex)自分の容姿を笑われたのではないか"><br>
			<h3>3.結果</h3>その時どんな気持ちになった？<br><input type="text" name="result"placeholder="ex)惨めな気持ちになった。"><br>
			<h3>4.ポジティブに変換（ネガティブな出来事のみ）</h3>前向きに捉えなおしてみよう<br><input type="text" name="reframe"placeholder="ex)たまたまこちらを見たタイミングで笑っただけで、私の容姿を笑ったわけではない可能性が高い。"><br>
		</div>
		
		<div  class="content-free">
			<h3>今の気持ちを書いてみよう</h3><input type="text" name="txtFree"><br>
		</div>
	</div>
	
	<input type="submit" name="regist" value="登録"><br>
</form>

</body>
</html>