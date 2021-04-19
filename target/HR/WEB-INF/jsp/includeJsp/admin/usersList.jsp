<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="lang" />
<html>
<head>
    <title>UserList</title>
</head>
<body>
<c:if test="${not empty users}">
    <c:forEach var="user" items="${users}">
        <div class="admin-list-item">
            <div class="header admin-item-header">${user.login}</div>
            <div class="admin-item-options">
                <c:choose>
                    <c:when test="${user.status eq 'HR'}">
                        <a class="common-button-inline" href="${pageContext.request.contextPath}/controller?command=ban_recruiter&login=${user.login}"><fmt:message key="page.admin.button.banRecruiter" /></a>
                    </c:when>
                    <c:otherwise>
                        <a class="common-button-inline" href="${pageContext.request.contextPath}/controller?command=appoint_recruiter&login=${user.login}"><fmt:message key="page.admin.button.appointRecruiter" /></a>
                        <a class="common-button-inline" href="${pageContext.request.contextPath}/controller?command=ban_user&login=${user.login}"><fmt:message key="page.admin.button.banUser" /></a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </c:forEach>
</c:if>
</body>
</html>