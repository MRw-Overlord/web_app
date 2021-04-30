<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="lang"/>
<html>
<head>
    <link rel="apple-touch-icon" sizes="180x180"
          href="${pageContext.request.contextPath}/static/favicon/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32"
          href="${pageContext.request.contextPath}/static/favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16"
          href="${pageContext.request.contextPath}/static/favicon/favicon-16x16.png">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/common.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/vacancy.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/type.css"/>
    <title><fmt:message key="page.vacancy.word.vacancy"/> | <c:out value="${vacancy.vacancyName}"/></title>
</head>
<body>
<jsp:include page="includeJsp/navbar.jsp"/>
<div class="page-content">
    <div class="vacancy-content">
        <div class="vacancy-badge">
            <div class="secondary-vacancy"><fmt:message key="page.vacancy.word.vacancy"/>
                <div class="header"><c:out value="${vacancy.vacancyName}"/></div>
            </div>
            <div class="secondary-vacancy"><fmt:message key="page.vacancy.word.description"/></div>
            <div class="description"><c:out value="${vacancy.description}"/></div>
            <div class="secondary-vacancy"><fmt:message key="page.admin.input.vacancySkillsDescription"/></div>
            <div class="description"><c:out value="${vacancy.skillsDescription}"/></div>
            <div class="secondary-vacancy"></div>
            <c:if test="${not empty sessionScope.login}">
                <c:if test="${sessionScope.role eq 'GUEST'}">
                    <a class="common-button"
                       href="${pageContext.request.contextPath}/controller?command=apply_vacancy&userLogin=${sessionScope.login}&vacancyId=${vacancy.vacancyId}">
                        <fmt:message key="page.vacancy.button.respond"/>
                    </a>
                </c:if>
            </c:if>
        </div>
    </div>
</div>
<jsp:include page="includeJsp/popupCookie.jsp"/>
<jsp:include page="includeJsp/footer.jsp"/>
</body>
</html>