<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="lang" />
<html>
<head>
    <link rel="apple-touch-icon" sizes="180x180" href="${pageContext.request.contextPath}/static/favicon/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="${pageContext.request.contextPath}/static/favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/static/favicon/favicon-16x16.png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/common.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/admin.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/type.css" />
    <title><fmt:message key="common.title.recruiterPage" /></title>
</head>
<body>
<jsp:include page="includeJsp/navbar.jsp" />
<div class="page-content">
    <div class="admin-content">
        <form method="post" accept-charset="UTF-8">
            <select class="description" name="recruiterListType" onchange="this.form.submit();">
                <option value="vacancies" ${recruiterListType == "vacancies" ? 'selected="selected"' : ''}><fmt:message key="page.admin.option.vacancies" /></option>
                <option value="users" ${recruiterListType == "users" ? 'selected="selected"' : ''}><fmt:message key="page.admin.option.users" /></option>
            </select>
        </form>
        <c:choose>
            <c:when test="${recruiterListType == 'vacancies'}">
                <c:import url="includeJsp/recruiter/vacancyList.jsp" />
            </c:when>
            <c:when test="${recruiterListType == 'users'}">
                <c:import url="includeJsp/recruiter/usersList.jsp" />
            </c:when>
        </c:choose>
    </div>
</div>
<jsp:include page="includeJsp/footer.jsp" />
</body>
</html>
