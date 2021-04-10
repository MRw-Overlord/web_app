<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ctg" uri="customtags"%>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="lang" />
<html>
<head>
    <link rel="apple-touch-icon" sizes="180x180" href="${pageContext.request.contextPath}/static/favicon/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="${pageContext.request.contextPath}/static/favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/static/favicon/favicon-16x16.png">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/common.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/type.css" />
    <title><fmt:message key="page.main.title" /></title>
</head>
<body>
<jsp:include page="includeJsp/navbar.jsp" />
<div class="page-content">
    <c:if test="${not empty requestScope.relevantRaces}">
        <div class="races-container">
            <c:forEach var="race" items="${requestScope.relevantRaces}">
                <a class="badge-link" href="${pageContext.request.contextPath}/controller?command=show_race_page&id=${race.id}">
                    <div class="badge">
                        <div class="header">
                            <c:out value="${race.name}" />
                        </div>
                        <div class="description"><c:out value="${race.description}" /></div>
                        <div class="secondary"><ctg:dateTime localDateTime="${race.finishDate}"/></div>
                    </div>
                </a>
            </c:forEach>
        </div>
    </c:if>
</div>
<jsp:include page="includeJsp/footer.jsp" />
</body>
</html>
