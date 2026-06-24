<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>ココロノナミ|ホームページ</title>
		<link rel="stylesheet" href="css/home.css" type="text/css">
	</head>
	
	<body>
		<header id="head">
			<div class ="logoWrap">
				<img src ="/b2/img/kokorononamiyoko.png" id="logo"> 
			</div>
		</header>
		
		<div class="homelayout">
			<aside class="left-menu">
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
							<li>通算ログイン</li>
							<li>${user.daysTotalLogin}日目</li>
							<li>連続ログイン</li>
							<li>${user.loginStreak}日目</li>
						</ul>
						<p id ="loginbonus">水深：${user.depthCurrent}ｍ</p>
					</div>
				</div>
			</aside>
			
			
			<main>
			<button id="leftMenuBtn" class="sp-only">メニュー</button>
				<p id = "greeting">${greeting}</p> <%--時間帯コメント --%>
				<div class="homereview">
					<p>本日の入力：</p>
					<ul class ="icon-list">
						<li><img src="/b2/img/happa2.png"></li>
						<li><img src="/b2/img/kinyuu2.png"></li>
						<li><img src="/b2/img/syasinn2.png"></li>
						<li><img src="/b2/img/sakana2.png"></li>
					</ul>
					<button onclick="location.href='/b2/DailyServlet';">記録する！</button>
				</div>
				<div class="homereview">
					<p>週間レポート</p>
					<div class="weekly">
							<div class= "weekly-box">
								グラフ
							</div>
							<div class="info-area">
								グラフの情報
							</div>
						</div>
					<button onclick="location.href='/b2/ReviewServlet';">VIEW ALL</button>
				</div>
				<div class="homereview">
					<p>TIPS：${randomTip.tips}</p>
				</div>
			</main>
			
			<aside> <%--右側にあるナビ --%>
				<img src ="/b2/img/yajirusi1.png" class ="pic" id="slideBtn"> 
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
						<ul class ="icon-list">
							<li><img src="/b2/img/happa2.png"></li>
							<li><img src="/b2/img/kinyuu2.png"></li>
							<li><img src="/b2/img/syasinn2.png"></li>
							<li><img src="/b2/img/sakana2.png"></li>
						</ul>
						<button onclick="location.href='/b2/DailyServlet';">記録する！</button>
					</div>
					<div class="slidehomereview">
						<p>週間レポート</p>
						<div class="slideweekly">
							<div class= "weekly-box">
								グラフ
							</div>
							<div class="info-area">
								グラフの情報
							</div>
						</div>
						<button onclick="location.href='/b2/ReviewServlet';">VIEW ALL</button>
					</div>
					<button onclick="location.href='/b2/TopServlet';">アプリについて</button>
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