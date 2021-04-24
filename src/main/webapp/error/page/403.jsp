<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="lang"/>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="page.error.forbidden.message.title"/></title>
    <link href="${pageContext.request.contextPath}/error/page/css/404.css" rel="stylesheet">
</head>
<body>

<h1><fmt:message key="page.error.forbidden.message"/></h1>

</body>
</html>
