<%--
  Created by IntelliJ IDEA.
  User: tanieska
  Date: 9/4/21
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Log out</title>
</head>
<body>
<%
    session.invalidate();
    response.sendRedirect("/jss7-management-console");
%>
User has been Logged out sucessfully!!!!
</body>
</html>
