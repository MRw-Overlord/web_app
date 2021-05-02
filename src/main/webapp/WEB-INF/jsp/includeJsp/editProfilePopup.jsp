<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="lang"/>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/common.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/navbar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/editProfilePopup.css"/>
</head>
<body>
<script src="../../../static/popup.js"></script>
<a href="#openModal" class="btn">
    <fmt:message key="page.profile.word.editProfile"/>
</a>
<div id="openModal" class="modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title"><fmt:message key="page.profile.word.editProfile"/></h3>
                <a href="#close" title="Close" class="close">X</a>
            </div>
            <div class="modal-body">
                <form class="auth-input-form" method="post"
                      action="${pageContext.request.contextPath}/controller?command=edit_profile">
                    <p>
                        <input class="auth-input" type="text" name="name"
                               placeholder="<fmt:message key="page.profile.word.name"/>" required
                               pattern="{1,30}">
                    </p>
                    <p>
                        <input class="auth-input" type="text" name="lastName"
                               placeholder="<fmt:message key="page.profile.word.lastName"/>" required
                               pattern="{1,30}">
                    </p>
                    <p>
                        <input class="auth-input" type="text" name="email"
                               placeholder="<fmt:message key="page.profile.word.email"/>" required
                               pattern="{1,30}">
                    </p>
                    <p>
                        <input class="auth-input" type="text" name="age"
                               placeholder="<fmt:message key="page.profile.word.age"/>" required
                               pattern="[0-9]{1,3}" title="<fmt:message key="page.profile.patternAge"/>">
                    </p>
                    <%--<p>
                        <input class="auth-input" type="file" name="avatar" placeholder="<fmt:message key="page.profile.word.uploadAvatar"/>" required/>
                    </p>--%>
                    <button class="common-button" type="submit">
                        <fmt:message key="page.profile.word.confirm"/>
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
