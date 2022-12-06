<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 10/11/2022
  Time: 9:34 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="author" content="Pasha">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <meta http-equiv=Content-Type content="text/html; charset=windows-1251">
    <link rel="shortcut icon" href="<c:url value="/upload?wheel.ico"/>" type="image/x-icon">

    <title>443</title>
    <style>
        <%@include file="../../classes/templates/css/404.css"%>
    </style>
</head>
<body>
<div class="scene">
    <div class="box">
        <div class="box__face front">4</div>
        <div class="box__face back">0</div>
        <div class="box__face right">3</div>
        <div class="box__face top">4</div>
        <div class="box__face left">0</div>
        <div class="box__face bottom">3</div>
    </div>
    <div class="shadow"></div>
</div>
<div class="desc">
    <h2>Oops Access denied!</h2>
    <form action="<c:url value="/login"/>">
        <button type="submit">BACK TO LOGIN PAGE</button>
    </form>
</div>
</body>
</html>
