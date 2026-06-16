<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>週間結果</title>
</head>
<body>
週間結果
<c:forEach var="e" items="${weekList}" >
<p>
1${e.weekRes_id}<br>
2${e.weeklyRes}<br>
3${e.analysisCmt}<br>
4${e.avgPositive}<br>
5${e.moodType}<br>
6${e.created_at}<br>
</p>
</c:forEach>
</body>
</html>