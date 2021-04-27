<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <c:set var="count" value="0" scope="page"/>
            <c:forEach var="user" items="${users}">
                <c:if test="${user.status == 'ACTIVE'}">
                    <div class="admin-list-item">
                        <p>
                            <fmt:message key="page.admin.option.activeUser"/>
                        </p>
                        <div class="header admin-item-header">${user.login}</div>
                        <div class="admin-item-options">
                            <a class="common-button-inline" href="${pageContext.request.contextPath}/controller?command=show_user_profile_page&login=${user.login}"><fmt:message key="common.navbar.profile" /></a>
                            <%--<c:set var="user" value="${user}" scope="request"/>--%>
                            <%--<c:import url="userProfilePopup.jsp"/>--%>
                            <script src="../../../../static/popup.js"></script>
                            <a href="#openModal${count}"  class="btn">
                                <fmt:message key="common.navbar.profile"/>
                            </a>
                            <div id="openModal${count}" class="modal">
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
                                                <div class="header"><c:out value="${user.login}" />

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
                        </div>
                    </div>
                    <s:set var="count" value="${count + 1}" scope="page"/>
                </c:if>
            </c:forEach>
        </c:if>
        <c:if test="${empty users}">
            <p>
                <fmt:message key="page.recruiter.oopsNoOnElseYet"/>
            </p>
        </c:if>
    </div>
</div>
<jsp:include page="../footer.jsp"/>
</body>
</html>
