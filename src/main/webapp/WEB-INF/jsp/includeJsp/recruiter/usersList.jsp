<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="lang"/>
<html>
<head>
    <title>UserList</title>
</head>
<body>
<c:if test="${not empty users}">
    <c:forEach var="user" items="${users}">
        <c:if test="${user.status == 'ACTIVE'}">
            <div class="admin-list-item">
                <p>
                    <fmt:message key="page.admin.option.activeUser"/>
                </p>
                <div class="header admin-item-header">${user.login}</div>
                <div class="admin-item-options">
                    <%--<a class="common-button-inline"
                       href="${pageContext.request.contextPath}/controller?command=appoint_recruiter&login=${user.login}">></a>
                    <a class="common-button-inline attention"
                       href="${pageContext.request.contextPath}/controller?command=ban_user&login=${user.login}"></a>--%>
                </div>
            </div>
        </c:if>
    </c:forEach>
    <c:forEach var="user" items="${users}">
        <c:if test="${user.status == 'HIDDEN'}">
            <div class="admin-list-item">
                <p>
                    <fmt:message key="page.admin.option.hiddenUser"/>
                </p>
                <div class="header admin-item-header">${user.login}</div>
                <div class="admin-item-options">
                    <a class="common-button-inline"
                       href="${pageContext.request.contextPath}/controller?command=unban_user&login=${user.login}"><fmt:message
                            key="page.admin.button.unbanUser"/></a>
                </div>
            </div>
        </c:if>
    </c:forEach>
</c:if>
</body>
</html>