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

						<p>感情タグ[必須]：</p>
						<div class="emotion-radio-group">
							<input type="radio" name="emotion_id" value="1" id=emo1> <label
								for="emo1"> <img
								src="${pageContext.request.contextPath}/img/angry_clione.png">
								<span>怒り</span>
							</label> <input type="radio" name="emotion_id" value="2" id=emo2> <label
								for="emo2"> <img
								src="${pageContext.request.contextPath}/img/sad_clione.png">
								<span>悲しい</span>
							</label> <input type="radio" name="emotion_id" value="3" id=emo3> <label
								for="emo3"> <img
								src="${pageContext.request.contextPath}/img/frustrated_clione.png">
								<span>悔しい</span>
							</label> <input type="radio" name="emotion_id" value="4" id=emo4> <label
								for="emo4"> <img
								src="${pageContext.request.contextPath}/img/panic_clione.png">
								<span>焦り</span>
							</label> <input type="radio" name="emotion_id" value="5" id=emo5> <label
								for="emo5"> <img
								src="${pageContext.request.contextPath}/img/fun_clione.png">
								<span>楽しい</span>
							</label> <input type="radio" name="emotion_id" value="6" id=emo6> <label
								for="emo6"> <img
								src="${pageContext.request.contextPath}/img/glad_clione.png">
								<span>嬉しい</span>
							</label> <input type="radio" name="emotion_id" value="7" id=emo7> <label
								for="emo7"> <img
								src="${pageContext.request.contextPath}/img/happy_clione.png">
								<span>幸せ</span>
							</label> <input type="radio" name="emotion_id" value="8" id=emo8> <label
								for="emo8"> <img
								src="${pageContext.request.contextPath}/img/love_clione.png">
								<span>好き</span>
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

										<c:if test="${status.last}">
											<button type="button"
													class="next-tab"
													onclick="checkQuestionAndNext()">
												次へ
											</button>
										</c:if>
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