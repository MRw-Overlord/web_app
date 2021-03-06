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
<jsp:include page="includeJsp/navbar.jsp"/>
<div class="page-content">
    <div class="profile-content">
        <div class="user-info">
            <div class="avatar-info">
                <div class="avatar-container">
                    <img class="avatar-image" src="data:image/jpeg;base64,${user.avatarImg}"/>
                </div>
                <div>
                    <form enctype="multipart/form-data" method="post"
                          action="${pageContext.request.contextPath}/controller?command=upload_avatar&login=${user.login}">
                        <p><input type="file" name="photo" accept="image/jpeg,image/png">
                            <input type="submit" value="<fmt:message key="page.profile.word.uploadAvatar"/>"></p>
                    </form>
                </div>
            </div>
        </div>
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
                <jsp:include page="includeJsp/editProfilePopup.jsp"/>
            </div>
        </div>
    </div>
</div>
<jsp:include page="includeJsp/popupCookie.jsp"/>
<jsp:include page="includeJsp/footer.jsp"/>
</body>
</html>