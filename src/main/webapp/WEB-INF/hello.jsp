<%--
  Created by IntelliJ IDEA.
  User: qotn0
  Date: 2025-01-03
  Time: 오후 5:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
    %> <!-- 스크립틀릿 -->
    <h1>Hello Servlet</h1>
    <h2>이름: <%=name %></h2>
    <h2>나이: <%=age %></h2>
</body>
</html>
