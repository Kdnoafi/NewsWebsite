<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="articleRepo" class="raf.rs.WEB_Projekat.repositories.vest.MySqlVestRepository"/>
<jsp:useBean id="userRepo" class="raf.rs.WEB_Projekat.repositories.korisnik.MySqlKorisnikRepository"/>

<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <%@include file="styles.jsp"%>

    <style>
        a:link {
            color: black;
            background-color: transparent;
            text-decoration: none;
        }
        a:visited {
            color: black;
            background-color: transparent;
            text-decoration: none;
        }
        a:hover {
            color: black;
            background-color: transparent;
            text-decoration: underline;
        }
        a:active {
            color: black;
            background-color: transparent;
            text-decoration: none;
        }

        .pagination {
            display: inline-block;
        }

        .pagination a {
            color: black;
            float: left;
            padding: 8px 16px;
            text-decoration: none;
            transition: background-color .3s;
            border: 1px solid #ddd;
        }

        .pagination a.active {
            background-color: #4CAF50;
            color: white;
            border: 1px solid #4CAF50;
        }

        .pagination a:hover:not(.active) {background-color: #ddd;}
    </style>
</head>
<body>
<%@ include file="header.jsp" %>

<table style="border: 1px solid grey;">
    <c:set var="articles" value="${articleRepo.allArticles()}" />
    <c:forEach var="article" items="${articles}">
        <c:set var="user" value="${userRepo.findUser(article.autorId)}" />
        <tr style="border: 1px solid grey;">
            <td style="border: 1px solid grey;">
                <h4><a href="<%=application.getContextPath()%>/articles/${article.id}">${article.naslov}</a></h4>
                <h6>${user}</h6>
                <h6>${article.vremeKreiranja}</h6>
                <p>${article.tekst}</p>
                <a href="<%=application.getContextPath()%>/editArticle/${article.id}">Edit</a>
                <a href="<%=application.getContextPath()%>/deleteArticleServlet/${article.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table><br>

<form action="<%=application.getContextPath()%>/new-article.jsp">
    <button type="submit">New article</button>
</form><br>

<div class="pagination">
    <a href="<%=application.getContextPath()%>">&laquo;</a>
    <a href="<%=application.getContextPath()%>">1</a>
    <a href="<%=application.getContextPath()%>">2</a>
    <a href="<%=application.getContextPath()%>">3</a>
    <a href="<%=application.getContextPath()%>">&raquo;</a>
</div>
</body>
</html>