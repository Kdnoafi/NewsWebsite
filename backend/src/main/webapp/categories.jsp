<%--
  Created by IntelliJ IDEA.
  User: David
  Date: 5/13/2021
  Time: 2:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="categoryRepo" class="raf.rs.WEB_Projekat.repositories.kategorija.MySqlKategorijaRepository"/>

<html>
<head>
    <title>Categories</title>
    <%@include file="styles.jsp"%>
</head>
<body>
<%@ include file="header.jsp" %>

<table style="border: 1px solid grey;">
    <c:set var="categories" value="${categoryRepo.allCategories()}" />
    <c:forEach var="category" items="${categories}">
        <tr style="border: 1px solid grey;">
            <td style="border: 1px solid grey;">
                <h5>${category.ime}</h5>
                ${category.opis}<br>
                <a href="<%=application.getContextPath()%>/editCategory/${category.id}">Edit</a>
                <a href="<%=application.getContextPath()%>/deleteCategoryServlet/${category.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table><br>

<form action="<%=application.getContextPath()%>/new-category.jsp">
    <button type="submit">Add category</button>
</form>
</body>
</html>
