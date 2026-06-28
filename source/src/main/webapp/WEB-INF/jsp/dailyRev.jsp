<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/dailyRev.css">
    </head>
    <body>
        <c:set var="t" value="${todayRev}"></c:set>
    
        <div class="flexbox">
            <div class="leftScreen"></div>

            <div class="rightScreen">
                <div class="result-container">

                    <!-- ① -->
                    <div class="result-date">
                        本日のあなたは
                    </div>

                    <div class="result-type">
                        ${t.type_id}
                    </div>

                    <div class="result-character">
                        <img src="${pageContext.request.contextPath}/img/manbo.png"
                            alt="診断キャラクター">
                    </div>

                    <div class="result-comment">
                        心が弱ってしまっているようです<br>
                        自分を大事にしてくださいね
                    </div>

                    <div class="result-emotion">
                        <img src="${pageContext.request.contextPath}/img/sad_clione.png"
                            alt="感情アイコン">
                    </div>

                    <div class="result-main">

                        <div class="photo-area">

                            <div class="section-title">
                                今日の一枚
                            </div>

                            <div class="photo-box">

                                <c:choose>
                                    <c:when test="${not empty t.photo}">
                                        <img src="${pageContext.request.contextPath}/img/syasinn.png">
                                    </c:when>

                                    <c:otherwise>
                                        <img src="${pageContext.request.contextPath}/img/syasinn2.png">
                                    </c:otherwise>
                                </c:choose>

                            </div>

                        </div>

                        <div class="memo-area">

                            <div class="section-title">
                                今日の出来事
                            </div>

                            <div class="memo-box">
                                ${t.freeForm}
                            </div>

                            <div class="section-title">
                                今日あったいいこと
                            </div>

                            <div class="memo-box">
                                ${t.positive}
                            </div>

                        </div>

                    </div>

                    <!-- ⑨ -->
                    <div class="back-area">
                        <a href="${pageContext.request.contextPath}/HomeServlet"
                        class="back-btn">
                            一覧に戻る
                        </a>
                    </div>

                </div>
            </div>
        </div>
        <script src="bonus.js"></script>
    </body>
</html>