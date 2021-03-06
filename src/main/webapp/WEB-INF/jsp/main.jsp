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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/common.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/type.css"/>
    <title><fmt:message key="page.main.title"/></title>
</head>

<body>
<jsp:include page="includeJsp/navbar.jsp"/>
<div class="page-content">
    <c:if test="${not empty requestScope.relevantVacancies}">
        <div class="vacancies-container">

            <form class="search"
                  action="${pageContext.request.contextPath}/controller?command=show_result_vacancy_search_page"
                  method="post">
                <label>
                    <input class="search" type="search" name="vacancySearch"
                           placeholder="<fmt:message key="page.main.input.vacancySearch"/>"/>
                </label>
                <button class="search" type="submit"><i class="fa fa-search"></i></button>
            </form>

            <c:forEach var="vacancy" items="${requestScope.relevantVacancies}">
                <div class="badge">
                    <div class="header">
                        <a class="badge-link"
                           href="${pageContext.request.contextPath}/controller?command=show_vacancy_page&id=${vacancy.vacancyId}">
                            <c:out value="${vacancy.vacancyName}"/>
                        </a>
                    </div>
                    <div class="description"><c:out value="${vacancy.description}"/></div>
                    <div class="secondary">
                        <div class="secondary-input">
                            <c:out value="${vacancy.companyName}"/></div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:if>
</div>

<jsp:include page="includeJsp/popupCookie.jsp"/>
<jsp:include page="includeJsp/footer.jsp"/>
</body>
</html>
