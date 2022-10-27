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
         src="https://raw.githubusercontent.com/Fat-Frumos/Cars/master/ava.jpg" alt="avatar">
    <p><span style="color: red;">${errorMessage}</span></p>
    <h3>Sign in</h3>
    <form action="/" method="post">
        <div class="inputBox">
            <input type="text" pattern="[a-zA-Z0-9]+" minlength="3" maxlength="10" placeholder="Enter Username" name="name" autocomplete="on" required>
            <input type="password" placeholder="Enter Password" name="password" minlength="2" maxlength="16" autocomplete="on" required>
            <%--pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{2,12}$"--%>
        </div>
        <br>
        <input type="submit" name="" value="Login">
    </form>


    <a onclick="document.getElementById('id01').style.display='block'" class="sign" style="width:auto;">Sign Up</a>

    <div id="id01" class="modal">
        <form class="loginBox" action="/login" method="post">
            <img class="user" height="100px" width="100px"
                 src="https://raw.githubusercontent.com/Fat-Frumos/Cars/master/ava.jpg">
            <div class="container">
                <input type="text" placeholder="Enter Username" name="name" pattern="[a-zA-Z0-9]+" autocomplete="on" autofocus required>
                <input type="email" placeholder="Enter Email" name="email" pattern="[^@\s]+@[^@\s]+\.[^@\s]+" autocomplete="on" required>
                <input type="password" placeholder="Enter Password" name="password" pattern="[a-zA-Z0-9]+" autocomplete="on" required>
                <button type="submit">Sign Up</button>
            </div>
            <div class="forget">
                <a class="btn-cancel" onclick="document.getElementById('id01').style.display='none'">Cancel</a>
                <a onclick="toCard()">Forgot password?</a>
            </div>
        </form>
    </div>
</div>
<a onclick="document.getElementById('id01').style.display='none'" style="text-decoration: none" class="close" title="Close Modal" href="/">&times;</a>
<script>

    function toCard() {
        let url = '/user';
        fetch(url, {
            method: 'GET',
        }).then(response => {
            console.log('Ok:', response);
            window.location.href = url;
        }).catch(err => {
            console.error(err)
        })
    }

    let modal = document.getElementById('id01');

    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
</script>
</body>
</html>
