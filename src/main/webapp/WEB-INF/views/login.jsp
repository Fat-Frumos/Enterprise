<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 8/24/2022
  Time: 6:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <title>Login JSP</title>
</head>
<style>
    body {
        margin: 0;
        padding: 0;
        height: 100vh;
        font-family: Verdana, Roboto, sans-serif;
        background: url(https://image.winudf.com/v2/image/Y29tLmZyZWUud2FsbHBhcGVycy5jYXIuaGRfc2NyZWVuc2hvdHNfMF8zNGU4N2FmMw/screen-0.jpg?fakeurl=1&type=.webp) no-repeat center;
        background-size: cover;
        overflow: hidden;
    }

    @media screen and (max-width: 600px) {
        body {

            background-size: cover;
        }
    }


    .loginBox {
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        width: 350px;
        min-height: 200px;
        background: #000000;
        border-radius: 10px;
        padding: 40px;
        box-sizing: border-box
    }

    .user {
        display: block;
        margin: 0 auto 20px;
        border-radius: 50px;
    }

    h3 {
        margin: 0;
        padding: 0 0 20px;
        color: #59238F;
        text-align: center
    }

    .loginBox input {
        width: 100%;
        margin-bottom: 20px
    }

    .loginBox input[type="text"], .loginBox input[type="password"] {
        border: none;
        border-bottom: 2px solid #262626;
        outline: none;
        height: 40px;
        color: #fff;
        background: transparent;
        font-size: 16px;
        padding-left: 20px;
        box-sizing: border-box
    }

    .loginBox input[type="text"]:hover, .loginBox input[type="password"]:hover {
        color: #42F3FA;
        border: 1px solid #42F3FA;
        box-shadow: 0 0 5px rgba(0, 255, 0, .3), 0 0 10px rgba(0, 255, 0, .2), 0 0 15px rgba(0, 255, 0, .1), 0 2px 0 black;
    }

    .loginBox input[type="text"]:focus, .loginBox input[type="password"]:focus {
        border-bottom: 2px solid #42F3FA;
    }

    .inputBox {
        position: relative;
    }

    .inputBox span {
        position: absolute;
        top: 10px;
        color: #262626;
    }

    .loginBox input[type="submit"] {
        border: none;
        outline: none;
        height: 40px;
        font-size: 16px;
        background: #59238F;
        color: #fff;
        border-radius: 20px;
        cursor: pointer;
    }

    .loginBox a {
        color: #262626;
        font-size: 14px;
        font-weight: bold;
        text-decoration: none;
        text-align: center;
        display: block;
    }

    a:hover {
        color: #00ffff;
    }

    p {
        color: #0000ff;
    }
</style>
<body>

<div class="loginBox">
    <img class="user" height="100px" width="100px" src="https://www.pngitem.com/pimgs/m/560-5603874_product-image-logo-avatar-minimalist-flat-line-hd.png">
    <p><span style="color: red;">${errorMessage}</span></p>
    <h3>Sign In</h3>
    <form action="/login" method="post">
        <div class="inputBox">
            <label for="name"></label><input id="name" type="text" name="name" placeholder="Username">
            <label for="pass"></label><input id="pass" type="password" name="password" placeholder="Password"></div>
        <input type="submit" name="" value="Login">
    </form>
    <a href="#">Forget Password<br> </a>
    <div class="text-center">
        <p style="color: #59238F;">Sign-Up</p>
    </div>
</div>
</body>
</html>
