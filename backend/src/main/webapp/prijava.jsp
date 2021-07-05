<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: David
  Date: 5/13/2021
  Time: 1:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
    <%@include file="styles.jsp"%>
</head>
<body>
<%@ include file="header.jsp" %>

<div>
    <c:if test="${sessionScope.email=='1'}">
        <p style="color: red;">Email already exists</p>
        <c:set var="email" value="0" scope="session" />
    </c:if>
    <!-- DA LI JE POLJE PRAZNO, TAKODJE TREBA DA SE PROVERI NA BACKENDU




    -->
    <form action="<%=application.getContextPath()%>/provera" method="post">
        <label for="email">Email</label>
        <input type="text" id="email" name="email"><br>
        <label for="ime">First Name</label>
        <input type="text" id="ime" name="ime"><br>
        <label for="prezime">Last Name</label>
        <input type="text" id="prezime" name="prezime"><br>
        <label for="lozinka">Password</label>
        <input type="text" id="lozinka" name="lozinka"><br>
        <button type="submit">Sign Up</button>
    </form>
</div>
</body>
</html>
