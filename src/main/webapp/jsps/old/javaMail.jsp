
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JavaMail</title>
</head>
<body>
<form action="MailServlet" method="POST">
    <table>
        <tr>
            <td>Send to:</td>
            <td><input type="tex" name="subject"/></td>
        </tr>
    </table>
    <hr/>
    <textarea type="text" name="body"  cols="45" rows="5">Message text</textarea>
    <br/><br/>
    <input type="submit" value="Send message!"/>
</form>
</body>
</html>
