<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>週間結果</title>
</head>
<body>
	週間結果
	<c:set var="e" value="${weekList[0]}" />
	<p>
		id：
		<c:out value="${e.weekRes_id}" />
		<br> 期間：
		<c:out value="${e.weeklyRes}" />
		<br> コメントid：
		<c:out value="${e.analysisCmt}" />
		<br> 平均ポジティブ率：
		<c:out value="${e.avgPositive}" />
		<br> 気分の浮き沈み：
		<c:out value="${e.moodType}" />
		<br> 登録日：
		<c:out value="${e.created_at}" />
		<br>
	</p>
	<h1>折れ線グラフ</h1>
	<canvas id="myLineChart"></canvas>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.bundle.js"></script>

	<script>
  	var ctx = document.getElementById("myLineChart");
  	var myLineChart = new Chart(ctx, {
    type: 'line',
    data: {
      labels: ['8月1日', '8月2日', '8月3日', '8月4日', '8月5日', '8月6日', '8月7日'],
      datasets: [
        {
          label: 'ポジティブ/ネガティブ率',
          data: [-62, 65, -93, 85, -51, 66, -47],
          borderColor: "rgba(255,0,0,1)",
          backgroundColor: "rgba(0,0,0,0)"
        }
      ],
    },
    options: {
      title: {
        display: true,
        text: '気温（8月1日~8月7日）'
      },
      scales: {
        yAxes: [{
          ticks: {
            suggestedMax: 40,
            suggestedMin: 0,
            stepSize: 10,
            callback: function(value, index, values){
              return  value +  '度'
            }
          }
        }]
      },
    }
  });
  </script>
  <h1>棒グラフ</h1>
  <canvas id="myBarChart"></canvas>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.bundle.js"></script>
  
  <script>
  var ctx = document.getElementById("myBarChart");
  var myBarChart = new Chart(ctx, {
    type: 'bar',
    data: {
      labels: ['8月1日', '8月2日', '8月3日', '8月4日', '8月5日', '8月6日', '8月7日'],
      datasets: [
        {
          label: 'ポジティブ/ネガティブ率',
          data: [-62, 65, -93, 85, -51, 66, -47],
          backgroundColor: "rgba(219,39,91,0.5)"
        }
      ]
    },
    options: {
      title: {
        display: true,
        text: 'ポジティブ/ネガティブ率'
      },
      scales: {
        yAxes: [{
          ticks: {
            suggestedMax: 100,
            suggestedMin: 0,
            stepSize: 10,
            callback: function(value, index, values){
              return  value +  '％'
            }
          }
        }]
      },
    }
  });
  </script>
</body>
</html>