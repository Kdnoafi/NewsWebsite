<%--
  Created by IntelliJ IDEA.
  User: David
  Date: 5/13/2021
  Time: 6:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="categoryRepo" class="raf.rs.WEB_Projekat.repositories.kategorija.MySqlKategorijaRepository"/>
<html>
<head>
    <title>New Article</title>
    <%@include file="styles.jsp"%>
</head>
<body>
<%@ include file="header.jsp" %>

<form action="<%=application.getContextPath()%>/novaVest" method="post">
    <label for="naslov">Title</label>
    <input type="text" id="naslov" name="naslov"><br>
    <label for="kategorija">Category</label>
    <select id="kategorija" name="kategorija">
        <c:set var="categories" value="${categoryRepo.allCategories()}" />
        <c:forEach var="category" items="${categories}">
            <option value="${category.ime}">${category.ime}</option>
        </c:forEach>
    </select><br>
    <label for="tekst">Article text</label>
    <input type="text" id="tekst" name="tekst"><br>
    <label for="tagovi">Tags</label>
    <input type="text" id="tagovi" name="tagovi"><br>
    <button type="submit">Add</button>
</form>
</body>
</html>
