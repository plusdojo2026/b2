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
        <meta name="viewport" content="width=device-width">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/daily.css">
    </head>
    <body>
        <div class="flexbox">
            <div class="leftScreen"></div>

            <div class="rightScreen">
					<h1>毎日記録</h1>
					<form method="POST" action="${pageContext.request.contextPath}/DailyServlet" id="form">
						<input type="hidden" name="user_id" value="1">

						<div>
							<label class="label">感情タグ</label><br>
							<label>
								<input type="radio" name="emotion_id" value="1">怒り
							</label>
							<label>
								<input type="radio" name="emotion_id" value="2">悲しみ		
							</label>
							<label>
								<input type="radio" name="emotion_id" value="3">悔しい
							</label>
							<label>
								<input type="radio" name="emotion_id" value="4">焦り
							</label>
							<label>
								<input type="radio" name="emotion_id" value="5">楽しい
							</label>
							<label>
								<input type="radio" name="emotion_id" value="6">嬉しい
							</label>
							<label>
								<input type="radio" name="emotion_id" value="7">幸せ
							</label>
							<label>
								<input type="radio" name="emotion_id" value="8">好き
							</label>
						</div>

						<div class="tab">

							<!-- タブボタン -->
							<input type="radio" id="tab1" name="tab" checked>
							<label for="tab1">質問</label>

							<input type="radio" id="tab2" name="tab">
							<label for="tab2">自由入力</label>

							<input type="radio" id="tab3" name="tab">
							<label for="tab3">写真</label>

							<input type="radio" id="tab4" name="tab">
							<label for="tab4">ポジティブ</label>

							<!-- コンテンツ -->
							<div class="content1">
								<div class="progress-area">

									<div class="progress-text">
										<span id="answered-count">0</span> / ${qList.size()}問
									</div>

									<div class="progress-bar">
										<div class="progress-fill" id="progress-fill"></div>
									</div>

								</div>
								<div class="question-list">

								<c:forEach var="q" items="${qList}" varStatus="status">

									<div class="question-card">

										<input type="hidden"
											name="qType_${status.count}"
											value="${q.qType}">

										<div class="question-number">
											Q${status.count}
										</div>

										<div class="question-text">
											${q.question}
										</div>

										<div class="answer-group">

											<label class="answer">
												<input type="radio" name="q_${status.count}" value="0">
												<span class="answer-card">
													<span class="answer-number">1</span>
													<span class="answer-text">全くそう思わない</span>
												</span>
											</label>

											<label class="answer">
												<input type="radio" name="q_${status.count}" value="1">
												<span class="answer-card">
													<span class="answer-number">2</span>
													<span class="answer-text">あまりそう思わない</span>
												</span>
											</label>

											<label class="answer">
												<input type="radio" name="q_${status.count}" value="2">
												<span class="answer-card">
													<span class="answer-number">3</span>
													<span class="answer-text">どちらともいえない</span>
												</span>
											</label>

											<label class="answer">
												<input type="radio" name="q_${status.count}" value="3">
												<span class="answer-card">
													<span class="answer-number">4</span>
													<span class="answer-text">ややそう思う</span>
												</span>
											</label>

											<label class="answer">
												<input type="radio" name="q_${status.count}" value="4">
												<span class="answer-card">
													<span class="answer-number">5</span>
													<span class="answer-text">とてもそう思う</span>
												</span>
											</label>

										</div>

									</div>

								</c:forEach>

								</div>
							</div>

							<div class="content2">
								<label class="label">自由入力</label><br>
								<input type="text" name="freeForm" class="textfield" placeholder="ランダムテーマに沿って自由に入力" >
							</div>

							<div class="content3">
								<label class="label">写真入力</label><br>
								<input type="text" name="photo" class="daily">
							</div>

							<div class="content4">
								<label class="label">ポジティブ内容入力</label><br>
								<input type="text" name="positive" class="textfield" placeholder="ポジティブな出来事を入力" >
							</div>

						</div>


						<input type="submit" name="submit" value="登録">
					</form>
            </div>
        </div>
        <script src="daily.js"></script>
    </body>
</html>