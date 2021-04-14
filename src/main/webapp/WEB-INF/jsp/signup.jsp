
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="lang" />
<html>
<head>
    <link rel="apple-touch-icon" sizes="180x180" href="${pageContext.request.contextPath}/static/favicon/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="${pageContext.request.contextPath}/static/favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/static/favicon/favicon-16x16.png">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/common.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/auth.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/type.css" />
    <title><fmt:message key="page.signup.title" /></title>
</head>
<body>
<jsp:include page="includeJsp/navbar.jsp" />
<div class="page-content">
    <div class="auth-form">
        <div class="auth-header"><fmt:message key="page.signup.title" /></div>
        <div class="auth-error">
            <c:if test="${not empty signupError}">
                <c:choose>
                    <c:when test="${signupError eq 'login'}">
                        <fmt:message key="page.signup.error.login" />
                    </c:when>
                    <c:when test="${signupError eq 'password'}">
                        <fmt:message key="page.signup.error.password" />
                    </c:when>
                    <c:otherwise></c:otherwise>
                </c:choose>
            </c:if>
        </div>
        <fmt:message key="page.auth.input.login" var="loginPlaceholder" />
        <fmt:message key="page.auth.input.password" var="passwordPlaceholder" />
        <fmt:message key="page.signup.input.title.login" var="loginTitle" />
        <fmt:message key="page.signup.input.title.password" var="passwordTitle" />
        <form class="auth-input-form" method="post" action="${pageContext.request.contextPath}/controller?command=signup">
            <input class="auth-input" type="text" name="login" placeholder="${loginPlaceholder}"
                   pattern="[A-Za-z0-9_]{1,15}" title="${loginTitle}" required>
            <input class="auth-input" type="password" name="password" placeholder="${passwordPlaceholder}"
                   pattern=".{6,30}"
                   title="${passwordTitle}" required>
            <button class="common-button" type="submit"><fmt:message key="page.signup.button" /></button>
        </form>
    </div>
</div>
<jsp:include page="includeJsp/footer.jsp" />
</body>
</html>