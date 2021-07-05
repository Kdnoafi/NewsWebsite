<%--
  Created by IntelliJ IDEA.
  User: David
  Date: 5/20/2021
  Time: 8:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Category</title>
    <%@include file="styles.jsp"%>
</head>
<body>
<%@ include file="header.jsp" %>

<!-- OVDE TREBA JOS DA STOJE TRENUTNE VREDNOSTI ARTICLA


-->

<form action="<%=application.getContextPath()%>/editArticleServlet/<%=request.getPathInfo().substring(1)%>" method="post">
    <label for="naslov">Title</label>
    <input type="text" id="naslov" name="naslov"><br>
    <label for="kategorija">Category</label>
    <input type="text" id="kategorija" name="kategorija"><br>
    <label for="tekst">Article text</label>
    <input type="text" id="tekst" name="tekst"><br>
    <label for="tagovi">Tags</label>
    <input type="text" id="tagovi" name="tagovi"><br>
    <button type="submit">Save changes</button>
</form>
</body>
</html>
