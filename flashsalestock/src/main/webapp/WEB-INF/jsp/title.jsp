<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/4/20
  Time: 19:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%if (session.getAttribute("user")==null) {%>
<a href="/login" >请登录</a>
<%} else {%>
<a href="/main" >${sessionScope.user.user_name}</a>
<%}%>
</body>
</html>
