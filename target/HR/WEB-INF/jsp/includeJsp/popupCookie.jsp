<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="lang" />
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/common.css"/>
</head>
<body>
<script src="popup.js"></script>
<a class="popup-link" href="#popup"> Files cookie in use!</a>
<div id="popup" class="popup">
    <div class="popup_body body">
        <div class="popup_content">
            <a href="#" class="popup_close .close-popup">X</a>
            <div class="popup_title"><fmt:message key="common.popup.cookie.title"/></div>
            <div class="popup_text"><fmt:message key="common.popup.cookie"/></div>
        </div>
    </div>
</div>
</body>
</html>