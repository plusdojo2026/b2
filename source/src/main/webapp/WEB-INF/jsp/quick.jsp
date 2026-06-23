<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ココロノナミ|簡易記録入力</title>
<meta name="viewport" content="width=device-width">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/quick.css">
<style>
</style>
</head>
<body>
	<div class="flexbox">
		<div class="leftScreen"></div>


		<div class="rightScreen">
			<form method="post"
				action="${pageContext.request.contextPath}/QuickServlet">
				<h1>簡易記録</h1>
				<p>感情タグ[必須]：</p>
				<div class="emotion-radio-group">
					<input type="radio" name="emotion_id" value="1" id=emo1> <label
						for="emo1"> <img
						src="${pageContext.request.contextPath}/img/angry_clione.png">
						<span>怒り</span>
					</label> <input type="radio" name="emotion_id" value="2" id=emo2> <label
						for="emo2"> <img
						src="${pageContext.request.contextPath}/img/sad_clione.png">
						<span>悲しい</span>
					</label> <input type="radio" name="emotion_id" value="3" id=emo3> <label
						for="emo3"> <img
						src="${pageContext.request.contextPath}/img/frustrated_clione.png">
						<span>悔しい</span>
					</label> <input type="radio" name="emotion_id" value="4" id=emo4> <label
						for="emo4"> <img
						src="${pageContext.request.contextPath}/img/panic_clione.png">
						<span>焦り</span>
					</label> <input type="radio" name="emotion_id" value="5" id=emo5> <label
						for="emo5"> <img
						src="${pageContext.request.contextPath}/img/fun_clione.png">
						<span>楽しい</span>
					</label> <input type="radio" name="emotion_id" value="6" id=emo6> <label
						for="emo6"> <img
						src="${pageContext.request.contextPath}/img/glad_clione.png">
						<span>嬉しい</span>
					</label> <input type="radio" name="emotion_id" value="7" id=emo7> <label
						for="emo7"> <img
						src="${pageContext.request.contextPath}/img/happy_clione.png">
						<span>幸せ</span>
					</label> <input type="radio" name="emotion_id" value="8" id=emo8> <label
						for="emo8"> <img
						src="${pageContext.request.contextPath}/img/love_clione.png">
						<span>好き</span>
					</label>
				</div>
				<div class="tab-2">

					<input type="radio" id="tab-fact" name="tab-2" checked> <label
						for="tab-fact">事実整理</label> <input type="radio" id="tab-free"
						name="tab-2"> <label for="tab-free">とにかく記入</label>

					<div class="content-fact">
						<h3>1.出来事</h3>
						何が起こったのか<br>
						<input type="text" name="event" required
							placeholder="ex)知らない人が私を見て笑った"><br>
						<h3>2.信念</h3>
						その出来事をどう捉えた？<br>
						<input type="text" name="belief" required
							placeholder="ex)自分の容姿を笑われたのではないか"><br>
						<h3>3.結果</h3>
						その時どんな気持ちになった？<br>
						<input type="text" name="result" required
							placeholder="ex)惨めな気持ちになった。"><br>
						<h3>4.ポジティブに変換（ネガティブな出来事のみ）</h3>
						前向きに捉えなおしてみよう<br>
						<textarea name="reframe" cols="70" rows="4" required
							placeholder="ex)たまたまこちらを見たタイミングで笑っただけで、私の容姿を笑ったわけではない可能性が高い。"></textarea>
						<br>
					</div>

					<div class="content-free">
						<h3>今の気持ちを書いてみよう</h3>
						<textarea name="txtFree" cols="70" rows="4" 　required></textarea>
						<br>
					</div>
				</div>

				<input type="submit" name="regist" value="登録"
					class="btn--red btn--cubic btn--radius"><br>
			</form>

		</div>
		<script src="bonus.js"></script>
</body>
</html>