<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 10/16/2022
  Time: 3:12 PM
  To change this template use File | Settings | File Templates.
--%>
<!doctype html>
<html lang="en">
<head>
    <title>User</title>
    <style>
        <%@include file="../classes/templates/css/user.css"%>
    </style>
</head>
<body>
<main>
    <div>
        <a class="button" href="/login">Login</a>
        <br>
        <br>
        <br>
        <br>
        <div class="mastercart">
            <br>
            <div class="mastercart__part red"></div>
            <div class="mastercart__part orange"></div>
            <div class="mastercart__copy">
                <span>Rental Car</span>
            </div>
        </div>
        <form>
            <br>
            <div class="inputBox">
                <br>
                <label for="username"></label>
                <input id="username"
                       type="text"
                       name="username"
                       placeholder="Username"
                       pattern="[a-zA-Z0-9]+"
                       autocomplete="on"
                       required>
                <br>
                <br>
                <label for="email"></label>
                <input id="email" type="email"
                       name="email"
                       placeholder="Email"
                       autocomplete="on"
                       required>
                <br>
                <br>
                <input onclick="sendEmail()" type="submit" name="sign" value="Submit">
                <br>
                <br>
            </div>
        </form>
    </div>
</main>
<br>
<script>
    function sendEmail() {
        let name = document.getElementById("username").value
        let url = '/user' + '?username=' + name;
        console.log(url);
        fetch(url, {
            method: 'PUT',
        }).then(response => {
            console.log('Ok:', response);
            window.location.href = "/login";
        }).catch(err => {
            console.error(err)
        })
        window.location.href = "/login";
    }
    window.addEventListener('contextmenu', (event) => {
        event.preventDefault()
        window.history.back();
    })
</script>
</body>
</html>
