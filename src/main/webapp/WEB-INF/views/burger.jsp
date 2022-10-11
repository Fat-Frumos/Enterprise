<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 9/1/2022
  Time: 12:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Navigation</title>
    <style>
        <%@include file="../classes/templates/css/burger.css"%>
    </style>
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</head>
</head>
<body>
<div class="navigation">
        <span style="--i:0;--x:-1;--y:0">
            <a href="/login"><ion-icon name="camera-outline"></ion-icon></a>
        </span>
    <span style="--i:1;--x:1;--y:0">

        <a href=""><ion-icon name="image-outline"></ion-icon></a>
        </span>
    <span style="--i:2;--x:0;--y:-1">

        <a href="/main"><ion-icon name="chatbox-ellipses-outline"></ion-icon></a>
        </span>
    <span style="--i:3;--x:0;--y:1">

        <a href="/cars"><ion-icon name="call-outline"></ion-icon></a>
        </span>
    <span style="--i:4;--x:1;--y:1">

        <a href=""><ion-icon name="game-controller-outline"></ion-icon></a>
        </span>
    <span style="--i:5;--x:-1;--y:-1">
            <ion-icon name="videocam-outline"></ion-icon>
        </span>
    <span style="--i:6;--x:0;--y:0">
            <ion-icon name="alarm-outline"></ion-icon>
        </span>
    <span style="--i:7;--x:-1;--y:1">
            <ion-icon name="paper-plane-outline"></ion-icon>
        </span>
    <span style="--i:8;--x:1;--y:-1">
            <ion-icon name="at-circle-outline"></ion-icon>
        </span>
</div>
<script>
    let navigation = document.querySelector('.navigation');
    console.log(navigation);

    navigation.onclick = function () {
        navigation.classList.toggle('active')
    }
</script>
</body>
</html>
