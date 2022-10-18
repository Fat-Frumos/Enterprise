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
</head>
<body>
<main>
    <form>
        <a href="/login">Login</a>
        <div class="inputBox">
            <br>
            <label for="name"></label><input id="name" type="text" name="name" placeholder="Username" autocomplete="on" required>
            <br>
            <br>
            <label for="email"></label><input id="email" type="email" name="email" placeholder="Email" autocomplete="on" required>
            <br>
            <br>
            <input onclick="sendEmail()" type="submit" name="sign" value="Submit">
            <br>
            <br>
        </div>
    </form>
</main>
<br>
<script>
    function sendEmail() {
        let name = document.getElementById("name").value
        let url = '/user' + '?name=' + name;
        console.log(url);
        fetch(url, {
            method: 'PUT',
        }).then(response => {
            console.log('Ok:', response);
        }).catch(err => {
            console.error(err)
        })
        window.location.href = "/login";
    }
</script>
</body>
</html>
