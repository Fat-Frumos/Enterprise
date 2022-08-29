<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 8/24/2022
  Time: 6:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<head>
    <title>Login</title>
</head>
<style>
    <%@include file="../classes/static/css/login.css"%>
</style>
<body>

<div class="loginBox">
    <img class="user" height="100px" width="100px"
         src="https://www.pngitem.com/pimgs/m/560-5603874_product-image-logo-avatar-minimalist-flat-line-hd.png">
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
