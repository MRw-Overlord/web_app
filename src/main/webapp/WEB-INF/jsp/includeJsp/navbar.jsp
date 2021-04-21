<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="lang" />
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/navbar.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/type.css" />
</head>
<body>
<div class="navbar-body">
    <div class="navbar-logo">
        <a href="${pageContext.request.contextPath}/controller?command=show_main_page"
           class="navbar-link"><fmt:message key="common.navbar.logo" /></a>
    </div>
    <div class="navbar-content">
        <c:choose>
            <c:when test="${not empty sessionScope.login}">
                <c:if test="${sessionScope.role eq 'ADMIN'}">
                    <a href="${pageContext.request.contextPath}/controller?command=show_admin_page"
                       class="navbar-link"><fmt:message key="common.navbar.admin" /></a>
                </c:if>
                <c:if test="${sessionScope.role eq 'HR'}">
                    <a href="${pageContext.request.contextPath}/controller?command=show_recruiter_page"
                       class="navbar-link"><fmt:message key="common.navbar.recruiter" /></a>
                </c:if>
                <a href="${pageContext.request.contextPath}/profile"
                   class="navbar-link"><fmt:message key="common.navbar.profile" /></a>
                <a href="${pageContext.request.contextPath}/controller?command=logout"
                   class="navbar-link"><fmt:message key="common.navbar.logout" /></a>
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/controller?command=show_login_page"
                   class="navbar-link"><fmt:message key="common.navbar.login" /></a>
                <a href="${pageContext.request.contextPath}/controller?command=show_signup_page"
                   class="navbar-link"><fmt:message key="common.navbar.signup" /></a>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>