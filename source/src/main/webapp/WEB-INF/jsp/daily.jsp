<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- QuestionDAOによる設問取得 -->
<%@ page import="java.util.List" %>
<%@ page import="dto.QuestionDTO" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>毎日記録</title>
</head>
<body>
	<h1>毎日記録</h1>
    <form method="POST" action="/b2/DailyServlet" id="form">
		<div>
			<label class="label">感情タグ</label><br>
		    <input type="radio" name="emotion_id" class="daily">
		</div>

		<div>			
			<table border="1">
				<c:forEach var="q" items="${qList}" varStatus="status">
					<tr>
						<input type="hidden" name="${q.qType}" value="${q.qType}">
						<input type="hidden" name="user_id" value="testUser">
						<td>Q.${status.count}</td>
						<td>${q.question}</td>
						<td>
							<label>
								<input type="radio" name=q_"${status.count}" value="0">1
							</label>
							<label>
								<input type="radio" name=q_"${status.count}" value="1">2
							</label>
							<label>
							<input type="radio" name=q_"${status.count}" value="2">3
							</label>
							<label>
								<input type="radio" name=q_"${status.count}" value="3">4
							</label>
							<label>
								<input type="radio" name=q_"${status.count}" value="4">5
							</label>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>

<%@ page import="java.util.List" %>
<%@ page import="dto.QuestionDTO" %>

<%
List<QuestionDTO> qList =
    (List<QuestionDTO>)request.getAttribute("qList");
%>


	    <div>
			<label class="label">自由入力</label><br>
			<input type="text" name="freeForm" class="daily" placeholder="ランダムテーマに沿って自由に入力" >
		</div>
		
		<div>
			<label class="label">写真入力(仮)</label><br>
			<input type="text" name="photo" class="daily">
		</div>

	    <div>
			<label class="label">ポジティブ内容入力</label><br>
			<input type="text" name="positive" class="daily" placeholder="ポジティブな出来事を入力" >
		</div>

		<input type="submit" name="submit" value="登録">
	</form>
</body>
</html>