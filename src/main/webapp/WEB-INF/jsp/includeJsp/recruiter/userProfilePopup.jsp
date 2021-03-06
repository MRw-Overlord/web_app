<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/profile.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/type.css"/>
    <title><fmt:message key="common.navbar.profile"/></title>
</head>
<body>

<script src="../../../../static/popup.js"></script>
<a href="#openModal" class="btn">
    <fmt:message key="common.navbar.profile"/>
</a>
<div id="openModal" class="modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title"><fmt:message key="page.profile.word.editProfile"/></h3>
                <a href="#close" title="Close" class="close">X</a>
            </div>
            <div class="avatar-container">
                <img class="avatar-image" src="${user.avatarPath}"/>
            </div>
            <div class="modal-body">
                <div class="user-info">
                    <div class="header"><c:out value="${user.login}"/>

                        <div class="description">
                            <fmt:message key="page.profile.word.name"/>
                            <c:if test="${not empty user.firstName}">
                                <c:out value="${user.firstName}"/>
                            </c:if>
                            <c:if test="${empty user.firstName}">
                                <c:out value="<Emty>"/>
                            </c:if>
                        </div>
                        <div class="description">
                            <fmt:message key="page.profile.word.lastName"/>
                            <c:if test="${not empty user.lastName}">
                                <c:out value="${user.lastName}"/>
                            </c:if>
                            <c:if test="${empty user.lastName}">
                                <c:out value="<Empty>"/>
                            </c:if>
                        </div>
                        <div class="description">
                            <fmt:message key="page.profile.word.email"/>
                            <c:if test="${not empty user.email}">
                                <c:out value="${user.email}"/>
                            </c:if>
                            <c:if test="${empty user.email}">
                                <c:out value="<Empty>"/>
                            </c:if>
                        </div>
                        <div class="description">
                            <fmt:message key="page.profile.word.age"/>
                            <c:if test="${not empty user.age}">
                                <c:out value="${user.age}"/>
                            </c:if>
                            <c:if test="${empty user.age}">
                                <c:out value="<Empty>"/>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>