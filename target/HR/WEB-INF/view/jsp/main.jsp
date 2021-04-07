<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: artem
  Date: 2/19/2021
  Time: 1:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main page</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/WEB-INF/view/jsp/style/main_page.css"/>">
</head>
<body>
<h1>You are on Main page!)</h1>
<ul class="menu">
    <li>Go to the page with users</li>
</ul>
<a href="${pageContext.request.contextPath}/controller?command=SHOW_USERS">go to page with users</a>
<br>
<ul class="menu">
    Authorization
    <li><a href="${pageContext.request.contextPath}/controller?command=LOGIN">authorization</a></li>
</ul>
<hr>
<a href="${pageContext.request.contextPath}/jsps/timeNow.jsp">Go to the page with time</a>

</body>
</html>
