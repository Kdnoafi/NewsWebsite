<%--
  Created by IntelliJ IDEA.
  User: David
  Date: 5/13/2021
  Time: 2:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New category</title>
    <%@include file="styles.jsp"%>
</head>
<body>
<%@ include file="header.jsp" %>

<form action="<%=application.getContextPath()%>/novaKategorija" method="post">
    <label for="ime">Name</label>
    <input type="text" id="ime" name="ime"><br>
    <label for="opis">Description</label>
    <input type="text" id="opis" name="opis"><br>
    <button type="submit">Add</button>
</form>
</body>
</html>
