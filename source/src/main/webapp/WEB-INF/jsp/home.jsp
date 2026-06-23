<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ココロノナミ|ホームページ</title>
		<link rel="stylesheet" href="/b2/css/home.css">
	</head>
	
	<body>
		<header id="head">
			<div class ="logoWrap">
				<img src ="/b2/img/kokorononamiyoko.png" id="logo"> 
				<p>むりなく続く、ココロの記録</p>
			</div>
		</header>
		
		<div class="homelayout">
			<aside>
				<div class ="flex">
					<div class = "quickrecord">
						<p>簡易記録にジャンプ</p>
						<a href="/b2/QuickServlet">
							<img src ="/b2/img/Snail.png" class ="pic">
						</a>
					</div>
					
					<div class="loginbonus">
						<p>ログインボーナス</p>
						<ul>
							<li>通算ログイン：${user.daysTotalLogin}日目</li>
							<li>連続ログイン：${user.loginStreak}日目</li>
						</ul>
						<p id ="loginbonus">水深：${user.depthCurrent}ｍ</p>
					</div>
				</div>
			</aside>
			
			<main>
				<p>${greeting}</p> <%--時間帯コメント --%>
				<div class="homereview">
					<p>本日の入力：</p>
					
					<button onclick="location.href='/b2/DailyServlet';">記録する！</button>
				</div>
				<div class="homereview">
					<p>週間レポート</p>
					<button onclick="location.href='/b2/ReviewServlet';">VIEW ALL</button>
				</div>
				<div class="homereview">
					<p>TIPS：</p>
				</div>
			</main>
			
			<aside> <%--右側にあるナビ --%>
				<img src ="/b2/img/Snail.png" class ="pic" id="slideBtn"> 
				<div id="slideNav">
					<nav>
						<ul>
							<li><a href="/b2/QuickServlet">簡易記録</a></li>
							<li><a href="/b2/DailyServlet">毎日記録</a></li>
							<li><a href="/b2/ReviewServlet">振り返り機能</a></li>
							<li><a href="/b2/BonusServlet">ログインボーナス</a></li>
							<li><a href="/b2/MypageServlet">マイページ</a></li>
						</ul>
					</nav>
					<div class="homereview">
						<p>本日の入力：</p>
						<button onclick="location.href='/b2/DailyServlet';">記録する！</button>
					</div>
					<div class="homereview">
						<p>週間レポート</p>
						<button onclick="location.href='/b2/ReviewServlet';">VIEW ALL</button>
					</div>
					<button>アプリについて</button>
				</div>
			</aside>
		</div>
		
		
		<footer>  
			<nav>
				<ul>
					<li><a href="/b2/QuickServlet">簡易記録</a></li>
					<li><a href="/b2/DailyServlet">毎日記録</a></li>
					<li><a href="/b2/ReviewServlet">振り返り機能</a></li>
					<li><a href="/b2/BonusServlet">ログインボーナス</a></li>
					<li><a href="/b2/MypageServlet">マイページ</a></li>
				</ul>
			</nav>
		</footer>
	<script src="/b2/js/home.js"></script>
	</body>
</html>