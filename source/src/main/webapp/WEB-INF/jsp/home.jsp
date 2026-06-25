<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>ココロノナミ|ホームページ</title>
		<link rel="stylesheet" href="css/home.css" type="text/css">
		<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.bundle.js"></script>
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
							<img src ="/b2/img/fun_clione.png" class ="pic">
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
			<div class="sp-menu-buttons sp-only">
    			<button class="sp-btn" onclick="location.href='/b2/QuickServlet';">簡易記録</button>
    			<button id="leftMenuBtn" class="sp-btn sp-only ">ボーナス</button>
			</div>
				<p id = "greeting">${greeting}</p> <%--時間帯コメント --%>
				<div class="homereview">
					<p>本日の入力：</p>
					<ul class ="icon-list">
						<li><img src="/b2/img/happa2.png"></li>
						<li><img src="/b2/img/kinyuu2.png"></li>
						<li><img src="/b2/img/syasinn2.png"></li>
						<li><img src="/b2/img/sakana2.png"></li>
					</ul>
					<button onclick="location.href='/b2/DailyServlet';">記録する</button>
				</div>
				<div class="homereview">
					<p>週間レポート</p>
					<div class="weekly">
							<div class= "weekly-box">
								<c:if test="${not empty weekData}">
							        <c:set var="e" value="${weekData[0]}" />
							        <div class="chart-container">
							            <canvas id="myLineChart"></canvas>
							        </div>
							    </c:if>
							</div>
							<div class="info-area">
								<c:if test="${not empty e}">
							        週の期間：<c:out value="${e.weeklyRes}" /><br>
							    </c:if>
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
							<li><a href="/b2/ReviewServlet">振り返り</a></li>
							<li><a href="/b2/BonusServlet">ボーナス</a></li>
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
						<button onclick="location.href='/b2/DailyServlet';">記録する</button>
					</div>
					
					<div class="slidehomereview">
					<p>週間レポート</p>
					        <c:if test="${not empty weekData}">
							    <c:set var="slideE" value="${weekData[0]}" />
							</c:if>
							
							<div class="weekly-box">
							    <div class="chart-container">
							        <p class="weekly-value">
							            ポジティブ率：
							            <fmt:formatNumber value="${slideE.avgPositive}" maxFractionDigits="1" />%
							        </p>
							    </div>
							</div>
							
							<div class="info-area">
							    <c:if test="${not empty slideE}">
							        週の期間：<c:out value="${slideE.weeklyRes}" /><br>
							    </c:if>
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
					<li><a href="/b2/ReviewServlet">振り返り</a></li>
					<li><a href="/b2/BonusServlet">ボーナス</a></li>
					<li><a href="/b2/MypageServlet">マイページ</a></li>
				</ul>
			</nav>
		</footer>
		
	<script>
			var ctx = document.getElementById("myLineChart");

			var myLineChart = new Chart(ctx, {
  			type: 'line',
			  data: {
				  labels: [
				    <c:forEach var="d" items="${e.dailyList}" varStatus="s">
				      '${e.weeklyRes.substring(5,7)}月${(8 + s.index)}日'${!s.last ? ',' : ''}
				    </c:forEach>
				  ],
			
			    datasets: [
			      {
			        label: 'ポジティブ率',
			        data: [
			          <c:forEach var="d" items="${e.dailyList}" varStatus="s">
			            ${d.positiveRate}${!s.last ? ',' : ''}
			          </c:forEach>
			        ],
			        borderColor: "rgba(255,0,0,1)",
			        backgroundColor: "rgba(0,0,0,0)"
			      },
			      {
			        label: 'ネガティブ率',
			        data: [
			          <c:forEach var="d" items="${e.dailyList}" varStatus="t">
			            ${d.negativeRate}${!t.last ? ',' : ''}
			          </c:forEach>
			        ],
			        borderColor: "rgba(0,0,255,1)",
			        backgroundColor: "rgba(0,0,0,0)"
			      }
			    ]
			  },
			  options: {title: {display: false,},legend: {display: false},
			    responsive: true,
			    maintainAspectRatio: false, 
			    scales: {
			      yAxes: [{
			        ticks: {
			          suggestedMax: 100,
			          suggestedMin: 0,
			          stepSize: 10,
			          callback: function(value){
			            return value + '%'
			          }}}]}}});
	</script>
	<script src="/b2/js/home.js"></script>
	</body>
</html>