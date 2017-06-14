<%@ page import="servlet.QueryServlet" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/6/10
  Time: 8:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body onload="ip()">
<form id="form" action="ip" method="post">
    <input type="text" name="ip" placeholder="请输入要查询的ip地址">
    <input type="submit" value="查询">
</form>
${sessionScope.geo}
<%
    if (session.getAttribute("geo") == null) {
        String ip = request.getRemoteAddr();
        out.print(ip + "<br>");
        out.print(QueryServlet.getGeo(ip));
    }
%>
</body>
</html>
