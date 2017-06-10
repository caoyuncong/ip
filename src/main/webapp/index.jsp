<%--
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
    <style>
        * {
            margin: 0 auto;
        }

        #counter {
            background: gray;
            height: 500px;
        }

        #logo {
            font-size: 50px;
            color: pink;
            height: 200px;
            background: yellow;
        }

        p {

        }
    </style>
</head>
<body>
<%--<div id="counter">--%>
<%--<div id="logo">--%>
<%--<p>IP SEARCH</p>--%>
<%--</div>--%>

<%--</div>--%>
<form action="index" method="post">
    <input type="text" name="ip" placeholder="请输入要查询的ip地址">
    <input type="submit" value="查询">
</form>
<br>
<%
    String ip = request.getParameter("ip");
    if (ip != null) {
        out.print(ip);
    }
    String message = (String) request.getAttribute("message");
    if (message != null) {
        out.print(message);
    }
%>
</body>
</html>
