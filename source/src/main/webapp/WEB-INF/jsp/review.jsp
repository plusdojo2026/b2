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
		
<div class="tab-3">

  <input type="radio" id="tab-daily" name="tab-3" checked>
  <label for="tab-daily">毎日記録</label>

  <input type="radio" id="tab-week" name="tab-3">
  <label for="tab-week">週間結果</label>

  <input type="radio" id="tab-quick" name="tab-3">
  <label for="tab-quick">簡易記録</label>

<!-- 毎日記録 -->
  <div class="content-daily"></div>

<!-- 週間結果 -->
  <div class="content-week">
			<c:set var="e" value="${latestWeek[0]}" />
			<h1 class="period">
				<c:out value="${e.weeklyRes}" />
			</h1>
			<!-- グラフ表示 -->
			<div class="chart-container"><canvas id="myLineChart"></canvas></div>
			<script>
				  const labels = [
				    <c:forEach var="d" items="${e.dailyList}" varStatus="s">
				      '${e.weeklyRes.substring(5,7)}月${(8 + s.index)}日'${!s.last ? ',' : ''}
				    </c:forEach>
				  ];
				  const positive = [
				    <c:forEach var="d" items="${e.dailyList}" varStatus="s">
				      ${d.positiveRate * 100}${!s.last ? ',' : ''}
				    </c:forEach>
				  ];
				  const negative = [
				    <c:forEach var="d" items="${e.dailyList}" varStatus="t">
				      ${d.negativeRate * 100}${!t.last ? ',' : ''}
				    </c:forEach>
				  ];
			</script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.bundle.js"></script>
			<script src="${pageContext.request.contextPath}/js/review.js"></script>
			<!-- グラフ表示終わり -->
			<!-- 週間結果リスト -->
			<div class="weekList">
				<c:forEach var="week" items="${weekList}">
					<table class="weekTable">
						<tr><td class="weekPeriod"><a href="WeeklyServlet?weeklyRes=${week.weeklyRes}">
									${week.weeklyRes} </a></td>

								<td class="weekComment">
									${week.analysisCmt.substring(0,20)}...</td></tr></table>
				</c:forEach></div>
				<!-- 週間ページング -->
				<div class="pagination">
				
						<c:if test="${weekPage > 1}">
							<a href="?weekPage=${weekPage - 1}">← 前へ</a>
						</c:if>

						<c:forEach var="i" begin="1" end="${totalWeekPage}">
							<a href="?weekPage=${i}"
								style="${i == weekPage ? 'font-weight:bold;' : ''}"> ${i} </a>
						</c:forEach>

						<c:if test="${weekPage < totalWeekPage}">
							<a href="?weekPage=${weekPage + 1}">次へ →</a>
						</c:if>

					</div>
				</div>


  <!-- 簡易記録 -->
  <div class="content-quick">
  <c:forEach var="e" items="${quickList}" >
  <div class="content-quick2">
  <c:if test="${not empty e.event}"><div class=record-item ><label>出来事:</label><input type="text" name="event" value="${e.event}" style="border: none;"></div></c:if>
  <c:if test="${not empty e.belief}"><div class=record-item ><label>信念:</label><input type="text" name="belief" value="${e.belief}" style="border: none;"></div></c:if>
  <c:if test="${not empty e.result}"><div class=record-item ><label>結果:</label><input type="text" name="result" value="${e.result}" style="border: none;"></div></c:if>
  <c:if test="${not empty e.reframe}"><div class=record-item ><label>ポジティブに変換:</label><input type="text" name="reframe" value="${e.reframe}" style="border: none;"></div></c:if>
  <c:if test="${not empty e.txtFree}"><div class=record-item ><label>自由入力:</label><input type="text" name="" value="${e.txtFree}" style="border: none;"></div></c:if>
  <div class=record-item ><label>感情:</label>
  <c:if test="${e.emotion_id eq 1}">
  怒り
  </c:if>
    <c:if test="${e.emotion_id eq 2}">
 悲しい
  </c:if>
   <c:if test="${e.emotion_id eq 3}">
 悔しい
  </c:if>
  <c:if test="${e.emotion_id eq 4}">
 焦り
  </c:if>
  <c:if test="${e.emotion_id eq 5}">
 楽しい
  </c:if>
  <c:if test="${e.emotion_id eq 6}">
 嬉しい
  </c:if>
  <c:if test="${e.emotion_id eq 7}">
 幸せ
  </c:if>
  <c:if test="${e.emotion_id eq 8}">
 好き
  </c:if>
  </div>
  </div>
  </c:forEach>
</div>
</div>
</div>
</div>

</body>
</html>