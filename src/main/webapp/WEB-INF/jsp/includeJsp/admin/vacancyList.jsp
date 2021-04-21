<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ctg" uri="customtags"%>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="lang" />
<html>
<head>
    <title>VacancyList</title>
</head>
<body>
<c:if test="${not empty vacancies}">
    <c:forEach var="vacancy" items="${vacancies}">
        <div class="admin-list-item">
            <a class="badge-link" href="${pageContext.request.contextPath}/controller?command=show_vacancy_page&id=${vacancy.vacancyId}">
                <div class="header admin-item-header"><c:out value="${vacancy.vacancyName}" /></div>
            </a>
            <div class="admin-item-options">
                <a class="common-button-inline" href="${pageContext.request.contextPath}/controller?command=show_edit_vacancy_page&id=${vacancy.vacancyId}"><fmt:message key="page.admin.button.edit" /></a>
                <a class="common-button-inline" href="${pageContext.request.contextPath}/controller?command=delete_vacancy&id=${vacancy.vacancyId}"><fmt:message key="page.admin.button.delete" /></a>
                <a class="common-button-inline" href="${pageContext.request.contextPath}/controller?command=show_user_responses&id=${vacancy.vacancyId}"><fmt:message key="page.admin.button.showUsersResponses" /></a>
            </div>
        </div>
    </c:forEach>
</c:if>
</body>
</html>