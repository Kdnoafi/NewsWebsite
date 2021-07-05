<%@ page import="java.time.LocalDate" %><%--
  Created by IntelliJ IDEA.
  User: David
  Date: 5/20/2021
  Time: 10:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="articleRepo" class="raf.rs.WEB_Projekat.repositories.vest.MySqlVestRepository"/>
<jsp:useBean id="userRepo" class="raf.rs.WEB_Projekat.repositories.korisnik.MySqlKorisnikRepository"/>
<jsp:useBean id="commentRepo" class="raf.rs.WEB_Projekat.repositories.komentar.MySqlCommentRepository"/>
<html>
<head>
    <title>Article</title>
    <%@include file="styles.jsp"%>
</head>
<body>
<%@ include file="header.jsp" %>

<c:set var="article" value="${articleRepo.findArticle(sessionScope.postId)}" />
<h1>${article.naslov}</h1>
<p>${article.vremeKreiranja}</p>
<h5>${userRepo.findUser(article.autorId)}</h5>
<p>${article.tekst}</p><br>
<h3>Comments</h3>

<c:set var="comments" value="${commentRepo.allCommentsForArticle(article.id)}" />
<c:forEach var="comment" items="${comments}">
    <h5>${comment.imeAutora}</h5>
    <p>${comment.tekst}</p>
</c:forEach>
</body>
</html>
