<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="lang"/>
<html>
<head>
    <link rel="apple-touch-icon" sizes="180x180"
          href="${pageContext.request.contextPath}static/favicon/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32"
          href="${pageContext.request.contextPath}static/favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16"
          href="${pageContext.request.contextPath}static/favicon/favicon-16x16.png">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/common.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/type.css"/>
    <title><fmt:message key="page.main.title"/></title>
</head>

<body>
<jsp:include page="navbar.jsp"/>

<div class="page-content">
    <div class="container">
        <div class="container-badge">
            <div class="header">
                <fmt:message key="page.profile.word.avatarUploadedSuccssesfully"/>
            </div>
        </div>
    </div>
</div>

<jsp:include page="popupCookie.jsp"/>
<jsp:include page="footer.jsp"/>
</body>
</html>