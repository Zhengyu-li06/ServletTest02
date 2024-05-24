<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>エラー</title>
    <link rel="stylesheet" type="text/css" href="CSS/styles.css">
</head>
<body>
    <div class="container">
        <h1>エラー</h1>
        <p style="color: red;"><%= request.getAttribute("errorMessage") %></p>
        <a href="new_tweet.jsp">戻る</a>
    </div>
</body>
</html>
