<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width">
<title>振り返りページ</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/review.css">
</head>
<body>
<div class="flexbox">
		<div class="leftScreen"></div>
		<!-- メインコンテンツ -->
		<div class="rightScreen">
			<!-- 週間結果 -->
			<c:set var="e" value="${weekList[0]}" />
			<h1 class="period">
				<c:out value="${e.weeklyRes}" />
			</h1>

			<div class="chart-container">
				<canvas id="myLineChart"></canvas>
			</div>
			<script
				src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.bundle.js"></script>

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
          }
        }
      }]
    }
  }
});
</script>

			<div class="weekList">
				<c:forEach var="week" items="${weekList}">
					<table class="weekTable">

						<tr>
							<td class="weekPeriod"><a
								href="WeeklyServlet?weeklyRes=${week.weeklyRes}">
									${week.weeklyRes} </a></td>

							<td class="weekComment">
								${week.analysisCmt.substring(0,20)}...</td>
						</tr>
					</table>
				</c:forEach>
			</div>
			<div class="pagination">
				<c:if test="${currentPage > 1}">
    &lt; 前へ
  </c:if>
				<c:forEach var="i" begin="1" end="${totalPage}">

					<c:choose>
						<c:when test="${i == currentPage}">
							<span>${i}</span>
						</c:when>

						<c:otherwise>
        ${i}
      </c:otherwise>

					</c:choose>

				</c:forEach>
				<c:if test="${currentPage < totalPage}">
    次へ &gt;
  </c:if>

			</div>
		</div>
	</div>

</body>
</html>