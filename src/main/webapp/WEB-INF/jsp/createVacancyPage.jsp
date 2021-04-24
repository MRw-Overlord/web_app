<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%request.setCharacterEncoding("UTF-8");%>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/admin.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/type.css"/>

    <title><fmt:message key="page.vacancy.button.createVacancy"/></title>
</head>
<body>
<jsp:include page="includeJsp/navbar.jsp"/>
<div class="page-content">
    <fmt:message key="page.admin.input.vacancyName" var="vacancyName"/>
    <fmt:message key="page.admin.input.vacancyDescription" var="vacancyDescription"/>
    <fmt:message key="page.admin.input.vacancySkillsDescription" var="vacancySkillsDescription"/>
    <fmt:message key="page.admin.input.companyName" var="companyName"/>
    <fmt:message key="page.vacancy.button.createVacancy" var="createVacancy"/>
    <fmt:message key="page.admin.input.title.name" var="nameTitle"/>
    <form method="post" action="${pageContext.request.contextPath}/controller?command=create_vacancy" class="create-vacancy-form" accept-charset="UTF-8">
        <input class="create-form-input secondary" type="text" name="${vacancyNameParam}"
                placeholder="${vacancyName}" pattern=".{1,30}" title="${nameTitle}" required/>
        <input class="create-form-input secondary" type="text" name="${companyNameParam}"
               placeholder="${companyName}" pattern=".{1,30}" title="${nameTitle}" required/>
        <input class="create-form-input secondary create-form-textarea" name="${vacancyDescriptionParam}"
                  placeholder="${vacancyDescription}" required/>
        <input class="create-form-input secondary create-form-textarea" name="${vacancySkillsDescriptionParam}"
                  placeholder="${vacancySkillsDescription}" required/>

        <input class="common-button" type="submit" value="${createVacancy}">
    </form>
</div>
<jsp:include page="includeJsp/footer.jsp"/>
</body>
</html>