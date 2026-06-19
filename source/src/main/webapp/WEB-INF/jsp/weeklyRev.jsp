<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>週間結果</title>
</head>
<body>
	<c:set var="e" value="${weekList[0]}" />
	<h1>
		<c:out value="${e.weeklyRes}" />
		の週間結果
	</h1>

	<h1>折れ線グラフ</h1>
	<canvas id="myLineChart"></canvas>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.bundle.js"></script>
	
<script>
var ctx = document.getElementById("myLineChart");

var myLineChart = new Chart(ctx, {
  type: 'line',
  data: {
    labels: [
      <c:forEach var="d" items="${e.dailyList}" varStatus="s">
        '${d.created_at.substring(5,7) + 0}月${d.created_at.substring(8,10) + 0}日'
        ${!s.last ? ',' : ''}
      </c:forEach>
    ],
    datasets: [
      {
        label: 'ポジティブ率',
        data: [
          <c:forEach var="d" items="${e.dailyList}" varStatus="s">
            ${d.positiveRate * 100}${!s.last ? ',' : ''}
          </c:forEach>
        ],
        borderColor: "rgba(255,0,0,1)",
        backgroundColor: "rgba(0,0,0,0)"
      },
      {
        label: 'ネガティブ率',
        data: [
          <c:forEach var="d" items="${e.dailyList}" varStatus="t">
            ${d.negativeRate * 100}${!t.last ? ',' : ''}
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
      text: 'ポジティブ率'
    },
    scales: {
      yAxes: [{
        ticks: {
          suggestedMax: 100,
          suggestedMin: 0,
          stepSize: 10,
          callback: function(value){
            return value + '%'
          }
        }
      }]
    }
  }
});
</script>
	<p>
		<br> 分析コメント：
		<c:out value="${e.analysisCmt}" />
		<br> 平均ポジティブ率：
		<c:out value="${e.avgPositive}" />
		<br> 気分の浮き沈み：
		<c:out value="${e.moodType}" />
	</p>


	<h1>棒グラフ</h1>
	<canvas id="myBarChart"></canvas>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.bundle.js"></script>

	<script>
  var ctx = document.getElementById("myBarChart");
  var myBarChart = new Chart(ctx, {
    type: 'bar',
    data: {
    	labels: [
    	      <c:forEach var="d" items="${e.dailyList}" varStatus="s">
    	        '${d.created_at.substring(5,7) + 0}月${d.created_at.substring(8,10) + 0}日'
    	        ${!s.last ? ',' : ''}
    	      </c:forEach>
    	    ],
      datasets: [
        {
          label: 'ポジティブ率',
          data: [<c:forEach var="d" items="${e.dailyList}" varStatus="s">
            ${d.positiveRate * 100}${!s.last ? ',' : ''}
          </c:forEach>],
          backgroundColor: "rgba(219,39,91,0.5)"
        },
        {
            label: 'ネガティブ率',
            data: [<c:forEach var="d" items="${e.dailyList}" varStatus="s">
              ${d.negativeRate * -100}${!s.last ? ',' : ''}
            </c:forEach>],
            backgroundColor: "rgba(130,201,169,0.5)"
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