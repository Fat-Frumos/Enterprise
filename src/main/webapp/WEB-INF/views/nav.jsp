<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 8/28/2022
  Time: 6:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <title>Navbar</title>
    <style>
        .bar {
            position: absolute;
            top: 3vh;
            left: 2.8vw;
            font-weight: 500;
        }
        div.bar > a:hover{
            text-decoration: none;
            color: #59238F;
        }
    </style>
</head>
<body>
<div class="bar">
    <a href="/cars">Cars</a> |
    <a href="/user">Users</a> |
    <a href="/card">Card</a> |
    <a style="text-transform: capitalize" href="/login">LogOut(${user})</a>
</div>
</body>
</html>