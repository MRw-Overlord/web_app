<%
    String message = pageContext.getException().getMessage();
    String exception = pageContext.getException().getClass().toString();
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="lang"/>
<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="page.error.java.message.title"/></title>
    <link href="${pageContext.request.contextPath}/error/page/css/404.css" rel="stylesheet">
</head>
<body>
<h1><fmt:message key="page.error.java.message"/></h1>
<p><fmt:message key="page.error.java.type"/> <%= exception%></p>
<p><fmt:message key="page.error.java.description"/> <%= message%></p>
</body>
</html>
