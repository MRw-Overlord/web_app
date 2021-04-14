<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="lang" />
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/footer.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/type.css" />
</head>
<body>
<div class="footer-body">
    <div class="language-select">
        <div class="language-header"><fmt:message key="common.footer.languages" />:</div>
        <form class="language-wrapper" method="post" action="">
            <button class="language" name="cookieLocale" value="en-US">English</button>
            <button class="language" name="cookieLocale" value="ru-RU">Русский</button>
            <button class="language" name="cookieLocale" value="ja-JP">日本語</button>
        </form>
    </div>
</div>
</body>
</html>