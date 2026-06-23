<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>毎日記録結果表示ページ</title>
</head>
<body>
    <c:set var="t" value="${todayRev}">
    </c:set>
    <p>${todayRev}</p>
    <p>${t.id}</p>
    <p>${t.freeForm}</p>
    <p>${t.photo}</p>
    <p>${t.positive}</p>
    <p>${t.emotion_id}</p>
    <p>${t.type_id}</p>
    <p>${t.negativeRate}</p>
    <p>${t.positiveRate}</p>
    <p>${t.activeIndex}</p>
    <p>${t.yearWeek}</p>
</body>
</html>