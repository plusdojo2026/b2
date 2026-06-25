<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width">
<title>週間結果</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/weeklyRev.css">
</head>
<body>
	<div class="flexbox">
		<div class="leftScreen"></div>

		<div class="rightScreen">
			<c:set var="e" value="${weekList[0]}" />
			<h1 class="period">
				<c:out value="${e.weeklyRes}" />のココロノナミ
			</h1>

			<div class="chart-container"><canvas id="myLineChart"></canvas></div>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.bundle.js"></script>
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
			  options: {
			    title: {
			      display: true,
			      text: 'ポジティブ率とネガティブ率の遷移'
			    },
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
			<div class="container">
				<div class="box large">
					<span class="boxTitle">分析コメント</span> <span class="analysisCmt">
						<c:out value="${e.analysisCmt}" />
					</span>
				</div>
				<div class="box">
					<span class="boxTitle">平均ポジティブ率：</span> <span class="boxContent">
						<fmt:formatNumber value="${e.avgPositive}" maxFractionDigits="1" />%
					</span>
				</div>
				<div class="box">
					<span class="boxTitle">気分の浮き沈み：</span> <span class="boxContent">
						<c:out value="${e.moodType}" />
					</span>
				</div>
			</div>
		</div>
	</div>

</body>
</html>