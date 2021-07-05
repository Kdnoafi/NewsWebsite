<%--
  Created by IntelliJ IDEA.
  User: David
  Date: 5/20/2021
  Time: 3:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit User</title>
    <%@include file="styles.jsp"%>
</head>
<body>
<%@ include file="header.jsp" %>

<!-- OVDE TREBA JOS DA STOJE TRENUTNE VREDNOSTI USERA


-->

<form action="<%=application.getContextPath()%>/editUserServlet/<%=request.getPathInfo().substring(1)%>" method="post">
    <label for="email">Email</label>
    <input type="text" id="email" name="email"><br>
    <label for="ime">First Name</label>
    <input type="text" id="ime" name="ime"><br>
    <label for="prezime">Last Name</label>
    <input type="text" id="prezime" name="prezime"><br>
    <label for="tip">Type</label>
    <input type="text" id="tip" name="tip"><br>
    <button type="submit">Save changes</button>
</form>
</body>
</html>
