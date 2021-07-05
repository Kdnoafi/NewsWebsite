<%--
  Created by IntelliJ IDEA.
  User: David
  Date: 5/20/2021
  Time: 3:08 PM
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

<!-- OVDE TREBA JOS DA STOJE TRENUTNE VREDNOSTI USERA


-->

<!-- ideja za tagove i kategorije: ime taga i ime kategorije u bazi da budu unique -->

<form action="<%=application.getContextPath()%>/editCategoryServlet/<%=request.getPathInfo().substring(1)%>" method="post">
    <label for="ime">Name</label>
    <input type="text" id="ime" name="ime"><br>
    <label for="opis">Description</label>
    <input type="text" id="opis" name="opis"><br>
    <button type="submit">Save Changes</button>
</form>
</body>
</html>
