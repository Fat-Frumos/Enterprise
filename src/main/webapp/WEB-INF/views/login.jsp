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
    <%@include file="../classes/templates/css/login.css"%>
</style>
<body>

<div class="loginBox">
    <img class="user" height="100px" width="100px"
         src="https://raw.githubusercontent.com/Fat-Frumos/Cars/master/ava.jpg">
    <p><span style="color: red;">${errorMessage}</span></p>
    <h3>Sign in</h3>
    <form action="/login" method="post">
        <div class="inputBox">
            <label for="name"></label><input id="name" type="text" name="name" placeholder="Username" autocomplete="on">
            <label for="pass"></label><input id="pass" type="password" name="password" placeholder="Password" autocomplete="on"></div>
        <br>
        <input type="submit" name="" value="Login">
    </form>



    <a onclick="document.getElementById('id01').style.display='block'" class="sign" style="width:auto;">Sign Up</a>

    <div id="id01" class="modal">
        <form class="loginBox" action="/user" method="post">
            <img class="user" height="100px" width="100px"
                 src="https://raw.githubusercontent.com/Fat-Frumos/Cars/master/ava.jpg">
                <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
            <div class="container">
                <input type="text" placeholder="Enter Username" name="name" required>
                <input type="text" placeholder="Enter Email" name="email" required>
                <input type="text" placeholder="Enter Password" name="password" required>
                <button type="submit">Sign Up</button>
            </div>

            <div class="forget">
                <a class="btn-cancel" onclick="document.getElementById('id01').style.display='none'">Cancel</a>
                <a href="/user">Forgot password?</a>
            </div>
        </form>
    </div>
</div>
<script>

    let modal = document.getElementById('id01');

    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
</script>
</body>
</html>
