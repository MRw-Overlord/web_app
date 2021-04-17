<%
    String message = pageContext.getException().getMessage();
    String exception = pageContext.getException().getClass().toString();
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>! Java Exception !</title>
    <link href="${pageContext.request.contextPath}/error/page/css/404.css" rel="stylesheet">

</head>
<body>
<h1>Exception occurred while processing the request</h1>
<p>Type: <%= exception%></p>
<p>Message: <%= message%></p>
</body>
</html>
