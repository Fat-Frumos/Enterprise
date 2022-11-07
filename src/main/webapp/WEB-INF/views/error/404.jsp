<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 8/27/2022
  Time: 6:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>404</title>
    <style>
        <%@include file="../../classes/templates/css/404.css"%>
    </style>
    <link rel="shortcut icon" href="https://raw.githubusercontent.com/Fat-Frumos/Cars/master/wheel.ico"
          type="image/x-icon">
</head>
<body>
<div class="scene">
    <div class="box">
        <div class="box__face front">4</div>
        <div class="box__face back">0</div>
        <div class="box__face right">4</div>
        <div class="box__face left">0</div>
        <div class="box__face top">0</div>
        <div class="box__face bottom">0</div>
    </div>
    <div class="shadow"></div>
</div>
<div class="desc">
    <h2>Oops page not found!</h2>
    <form action="<c:url value="/"/>">
        <button type="submit">BACK TO MAIN PAGE</button>
    </form>
</div>
</body>
</html>
