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
        .navigation {
            position: absolute;
            top: 10px;
            left: 80vw;
            width: 50px;
            height: 50px;
            background-color: #212532;
            border-radius: 10px;
            cursor: pointer;
            opacity: .7;
            display: flex;
            align-items: center;
            justify-content: center;
            transition: .5s;
            transition-delay: .8s;
        }

        .navigation.active {
            width: 200px;
            height: 200px;
            transition-delay: 0s;

        }

        .navigation span {
            position: absolute;
            width: 7px;
            height: 7px;
            display: flex;
            align-items: center;
            justify-content: center;
            background: #fff;
            border-radius: 50%;
            transform: translate(calc(12px * var(--x)), calc(12px * var(--y)));
            transition: transform .5s, width .5s, height .5s, background .5s;
            transition-delay: calc(0.1s * var(--i));
        }

        ion-icon {
            opacity: 0;
        }

        /*span {*/
        /*    background-color: #212532;*/
        /*}*/

        .navigation.active span {
            width: 45px;
            height: 45px;
            transition: .5s;
            background: #333849;
            transform: translate(calc(60px * var(--x)), calc(60px * var(--y)));
        }

        .navigation.active span ion-icon {
            opacity: 1;
            font-size: 1.35em;
            color: #fff;
        }

        .navigation.active span:hover ion-icon {
            color: #2dfc52;
            filter: drop-shadow(0 0 2px #2dfc52) drop-shadow(0 0 5px #2dfc52) drop-shadow(0 0 15px #2dfc52);
            animation: heart 1.5s forwards;
        }
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
