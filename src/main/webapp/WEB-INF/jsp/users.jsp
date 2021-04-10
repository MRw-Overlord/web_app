<%--
  Created by IntelliJ IDEA.
  User: artem
  Date: 2/18/2021
  Time: 10:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Vacancies list</title>
</head>
<body>
<h2>Vacancies list</h2>
<c:if test="${not empty requestScope.users}">
    <h2>Columns</h2>
    <ul>
        <c:forEach var="user" items="${requestScope.users}">
            <li>${user.firstName} login: ${user.login} </li>
        </c:forEach>
    </ul>
</c:if>
</body>
</html>
