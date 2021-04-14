<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="lang" />
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/common.css"/>
</head>
<body>
<div class="b-popup">
    <div class="b-popup-content">
        <fmt:message key="common.popup.cookie"/>
    </div>
</div>
</body>