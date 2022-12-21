<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 8/24/2022
  Time: 6:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<meta charset="UTF-8">
<meta name="author" content="Pasha">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv=Content-Type content="text/html; charset=windows-1251">
<link rel="shortcut icon" href="<c:url value="/upload?wheel.ico"/>" type="image/x-icon">

<head>
    <title>Login</title>
</head>
<style>
    <%@include file="../classes/templates/css/login.css"%>
    <%@include file="../classes/templates/css/neon.css"%>
    h3:hover {
        color: #FFFFFF;
    }
</style>
<body>

<div class="loginBox">
    <img class="user" height="100px" width="100px"
         src="<c:url value="/upload?ava.jpg"/>" alt="avatar">
    <p><span style="color: red;">${errorMessage}</span></p>
    <h3>Sign In</h3>
    <form action="${pageContext.request.contextPath}/" method="post">
        <div class="inputBox">
            <label>
                <input name="name"
                       type="text"
                       pattern="[a-zA-Z0-9]+"
                       placeholder="Enter Username"
                       minlength="3"
                       maxlength="10"
                       autocomplete="on"
                       required
                >
            </label>
            <label>
                <input name="password"
                       type="password"
                       placeholder="Enter Password"
                       minlength="2"
                       maxlength="16"
                       autocomplete="on"
                       required
                >
                <%--pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{2,12}$"--%>
            </label>
        </div>
        <button type="submit" class='glowing-btn'>
            <span class='glowing-txt'>L<span class='faulty-letter'>o</span><span class='faulty-letter'>g</span><span class='faulty-letter'>in</span>
            </span>
        </button>
        <%--        <input type="submit" name="" value="Login">--%>
    </form>
    <br>
    <a onclick="document.getElementById('id01').style.display='block'"
       class="sign"
       style="width:auto;">
        Register
    </a>

    <div id="id01" class="modal">
        <form class="loginBox"
              action="<c:url value="/login"/>"
              method="post">

<%--            <label for="flag"></label>--%>
<%--            <input id="flag" name="language" hidden>--%>

            <img class="user"
                 height="100px"
                 width="100px"
                 value="en"
                 alt=""
                 src="<c:url value="/upload?ava.jpg"/>">

<%--            <jsp:include page="flag.jsp"/>--%>

            <div class="container">

                <label>
                    <input name="name"
                           type="text"
                           pattern="[a-zA-Z0-9]+"
                           placeholder="Enter Username"
                           autocomplete="on"
                           autofocus
                           required
                    >
                </label>
                <label>
                    <input name="email"
                           type="email"
                           pattern="[^@\s]+@[^@\s]+\.[^@\s]+"
                           placeholder="Enter Email"
                           autocomplete="on"
                           required
                    >
                </label>
                <label>
                    <input name="password"
                           type="password"
                           pattern="[a-zA-Z0-9]+"
                           placeholder="Enter Password"
                           autocomplete="on"
                           required
                    >
                </label>
                <button type="submit">
                    Sign Up
                </button>
            </div>
            <div class="forget">
                <a class="btn-cancel"
                   onclick="document.getElementById('id01').style.display='none'">
                    Cancel
                </a>
                <br>
                <a href="<c:url value="/user"/>" class="sign glowing-txt" onclick="toCard()">
                    Forgot password?
                </a>
                <br>
            </div>
        </form>
    </div>
</div>

<a onclick="document.getElementById('id01').style.display='none'" style="text-decoration: none" class="close"
   title="Close Modal" href="<c:url value="/"/>">&times;</a>
<script>

    function toCard() {
        let url = '/user';
        fetch(url, {
            method: 'GET',
        }).then(response => {
            console.log('Ok:', response);
        }).catch(err => {
            console.error(err)
        })
        // window.location.href = url;
    }

    let modal = document.getElementById('id01');

    window.onclick = function (event) {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    }
</script>
</body>
</html>