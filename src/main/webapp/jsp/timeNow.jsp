<%--
  Created by IntelliJ IDEA.
  User: artem
  Date: 4/5/2021
  Time: 12:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Time</title>
</head>
<body>
<jsp:useBean id="calendar" scope="page" class="java.util.GregorianCalendar"/>
    Directive
    <%@ include file="time.jsp"%>
<br>
</body>
</html>
