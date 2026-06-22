<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ココロノナミ|ホームページ</title>
		<header>
			<h1>ココロノナミ</h1>
			<p>むりなく続く、ココロの記録</p>
		</header>
	</head>
	
	<body>
		<h2>ホームページ</h2>
		
		<aside>
			<div class = "quickrecord">
				<p>簡易記録にジャンプ</p>
			</div>
			
			<div class="loginbonus">
				<p>ログインボーナス</p>
				<ul>
					<li>通算ログイン：${user.daysTotalLogin}日目</li>
					<li>連続ログイン：${user.loginStreak}日目</li>
				</ul>
				<p>水深：${user.depthCurrent}ｍ</p>
			</div>
		</aside>
		
		<main>
		<p>時間コメント表示</p>
		</main>
		
		<footer>
			<button>簡易記録</button>
			<button>毎日記録</button>
			<button>振り返り機能</button>
			<button>ログインボーナス</button>
			<button>マイページ</button>
		</footer>
	</body>
</html>