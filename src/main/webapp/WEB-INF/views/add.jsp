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
    <form action="/user" method="post">
        <div class="inputBox">
            <label for="name"></label><input id="name" type="text" name="name" placeholder="Username" autocomplete="on">
            <label for="email"></label><input id="email" type="email" name="email" placeholder="Email" autocomplete="on">
            <input type="submit" name="" value="signUp">
            <a href="/login">Login</a>
        </div>
    </form>
</main>
<br>
</body>
</html>
