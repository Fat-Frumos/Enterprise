<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 9/1/2022
  Time: 12:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    <%@include file="../classes/templates/css/burger.css"%>
</style>

<div class="navigation">
        <span style="--i:0;--x:-1;--y:0">
            <a id="apple" href="<c:url value="/"/>">
                <ion-icon name="camera-outline"></ion-icon></a>
        </span>

    <span style="--i:1;--x:1;--y:0">
        <a href="<c:url value="/login"/>">
            <ion-icon name="image-outline"></ion-icon></a>
        </span>

    <span style="--i:2;--x:0;--y:-1">
        <a id="github" href="<c:url value="/user"/>">
            <ion-icon name="chatbox-ellipses-outline"></ion-icon></a>
        </span>

    <span style="--i:3;--x:0;--y:1">
        <a id="twitter" href="<c:url value="/cars"/>">
            <ion-icon name="call-outline"></ion-icon></a>
        </span>

    <span style="--i:4;--x:1;--y:1">
        <a href="<c:url value="/order"/>">
            <ion-icon name="game-controller-outline"></ion-icon></a>
        </span>
    <span id="facebook" style="--i:5;--x:-1;--y:-1">
        <a id="" href="<c:url value="/"/>">
            <ion-icon name="videocam-outline"></ion-icon></a>
        </span>
    <span style="--i:6;--x:0;--y:0">
        <a href="<c:url value="/register"/>">
            <ion-icon name="alarm-outline"></ion-icon>
        </a>
        </span>
    <span style="--i:7;--x:-1;--y:1">
        <a href="<c:url value="/login"/>">
            <ion-icon name="paper-plane-outline"></ion-icon>
        </a>
        </span>
    <span style="--i:8;--x:1;--y:-1">
        <a href="<c:url value="/cart"/>">
            <ion-icon name="at-circle-outline"></ion-icon>
        </a>
        </span>
</div>
<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
<script>

    let navigation = document.querySelector('.navigation');
    navigation.onclick = function () {
        navigation.classList.toggle('active')
    }
</script>