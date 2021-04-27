<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="lang"/>
<html>
<head>
    <title>User Responses</title>
    <link rel="apple-touch-icon" sizes="180x180"
          href="${pageContext.request.contextPath}/static/favicon/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32"
          href="${pageContext.request.contextPath}/static/favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16"
          href="${pageContext.request.contextPath}/static/favicon/favicon-16x16.png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/common.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/admin.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/type.css"/>
</head>
<body>
<jsp:include page="../navbar.jsp"/>
<div class="page-content">
    <div class="admin-content">
        <c:if test="${not empty users}">
            <c:forEach var="user" items="${users}">
                <c:if test="${user.status == 'ACTIVE'}">
                    <div class="admin-list-item">
                        <p>
                            <fmt:message key="page.admin.option.activeUser"/>
                        </p>
                        <div class="header admin-item-header">${user.login}</div>
                        <div class="admin-item-options">
                            <c:set scope="request" value="${user}" var="user"/>
                            <jsp:include page="userProfilePopup.jsp"/>
                        </div>
                    </div>
                </c:if>
            </c:forEach>
        </c:if>
    </div>
</div>
<jsp:include page="../footer.jsp"/>
</body>
</html>
