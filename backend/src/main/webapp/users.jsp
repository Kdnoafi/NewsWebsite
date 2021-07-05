<%--
  Created by IntelliJ IDEA.
  User: David
  Date: 5/13/2021
  Time: 1:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="userRepo" class="raf.rs.WEB_Projekat.repositories.korisnik.MySqlKorisnikRepository"/>

<html>
<head>
    <title>Users</title>
    <%@include file="styles.jsp"%>
</head>
<body>
<%@ include file="header.jsp" %>

<table style="border: 1px solid grey;">
    <c:set var="users" value="${userRepo.alUsers()}" />
    <c:forEach var="user" items="${users}">
        <tr style="border: 1px solid grey;">
            <td style="border: 1px solid grey;">
                <p>${user.email}</p>
                <c:choose>
                    <c:when test="${user.status=='1'}">
                        <p>User status: active</p>
                    </c:when>
                    <c:otherwise>
                        <p>User status: inactive</p>
                    </c:otherwise>
                </c:choose>
                <a href="<%=application.getContextPath()%>/editUser/${user.id}">Edit</a>
                <a href="<%=application.getContextPath()%>/activateUserServlet/${user.id}">Activate/Deactivate</a>
            </td>
        </tr>
    </c:forEach>
</table><br>

<form action="prijava.jsp">
    <button type="submit">New user</button>
</form>
</body>
</html>
