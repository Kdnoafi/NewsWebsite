<%--
  Created by IntelliJ IDEA.
  User: David
  Date: 5/13/2021
  Time: 6:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Log In</title>
    <%@include file="styles.jsp"%>
</head>
<body>
<%@ include file="header.jsp" %>

<c:choose>
    <c:when test="${sessionScope.lEmail==1}">
        <p style="color: red;">User not found</p>
    </c:when>
    <c:when test="${sessionScope.lPass==1}">
        <p style="color: red;">Wrong password</p>
    </c:when>
    <c:when test="${sessionScope.status==1}">
        <p style="color: red;">User is not active</p>
    </c:when>
</c:choose>
<form action="<%=application.getContextPath()%>/login" method="post">
    <label for="email">Email</label>
    <input type="text" id="email" name="email"><br>
    <label for="lozinka">Password</label>
    <input type="text" id="lozinka" name="lozinka"><br>
    <button type="submit">Log In</button>
</form>
</body>
</html>
