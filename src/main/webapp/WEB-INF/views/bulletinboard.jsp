<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>掲示板</title>
</head>
<body>
<h1>掲示板アプリケーション</h1>

<%--ここに記事投稿フォーム --%>

<c:forEach var="article" items="${articles}">

投稿ID:<c:out value="${article.id}"></c:out><br>
投稿者名:<c:out value="${article.name}"></c:out><br>
投稿内容:<c:out value="${article.content}"></c:out><br><br>

<%--ここに記事削除ボタン --%>

<c:forEach var="comment" items="${article.commentList}">
コメントID:<c:out value="${comment.id}"></c:out><br>
コメント者名:<c:out value="${comment.name}"></c:out><br>
コメント内容:<c:out value="${comment.content}"></c:out><br><br>

<%--ここにコメント削除ボタン --%>

</c:forEach>

<%--ここにコメント投稿フォーム --%>

<hr>
</c:forEach>
</body>
</html>