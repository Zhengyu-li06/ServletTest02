<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jp.co.aforce.beans.Tweet" %>
<%@ page import="jp.co.aforce.dao.TweetDAO" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ツイート一覧</title>
<link rel="stylesheet" type="text/css" href="CSS/styles.css">
</head>
<body>
    <div class="container">
        <h1>ツイート一覧</h1>
        <%-- 新規投稿 --%>
        <p>
            <a href="new_tweet.jsp">新規投稿</a>
        </p>

        <%-- ツイート一覧の表示 --%>
        <ul class="tweet-list">
            <% 
                try {
                    TweetDAO tweetDAO = new TweetDAO();
                    List<Tweet> tweets = tweetDAO.getAllTweets();
                    for (Tweet tweet : tweets) {
            %>
            <li>
                <div class="tweet-content">
                    <p><%= tweet.getContent() %></p>
                    <p class="tweet-info">投稿者: <%= tweet.getAuthor() %> - 投稿日時: <%= tweet.getPostedAt() %></p>
                    <%-- 削除 --%>
                    <form action="delete_tweet" method="post" style="display: inline;">
                        <input type="hidden" name="tweetId" value="<%= tweet.getId() %>">
                        <input type="submit" value="削除">
                    </form>
                </div>
            </li>
            <% 
                    }
                } catch (Exception e) {
                    e.printStackTrace();
            %>
            <li>
                <div class="tweet-content">
                    <p>エラーが発生しました: <%= e.getMessage() %></p>
                </div>
            </li>
            <% } %>
        </ul>
    </div>
