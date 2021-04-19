<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="lang"/>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/common.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/navbar.css">
</head>
<body>
<a href="#openModal" ><img src="styles/img/Cookies-Logo.jpg" width="90" height="50"></a>
<div id="openModal" class="modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title"><fmt:message key="common.popup.cookie.title"/></h3>
                <a href="#close" title="Close" class="close">X</a>
            </div>
            <div class="modal-body">
                <p><fmt:message key="common.popup.cookie"/></p>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/static/popup.js"></script>
</body>
</html>
