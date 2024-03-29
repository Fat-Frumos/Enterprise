<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 10/13/2022
  Time: 11:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="author" content="Pasha">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv=Content-Type content="text/html; charset=UTF-8">
    <link rel="shortcut icon" href="<c:url value="/upload?wheel.ico"/>" type="image/x-icon">
    <title>Main</title>
    <style>
        <%@include file="../classes/templates/css/car.css"%>
        <%@include file="../classes/templates/css/slider.css"%>
    </style>
</head>

<body>
<div class="carousel">
    <c:forEach items="${cars}" var="cars">
        <figure class="carousel-item">
            <img class="fade"
                 onclick="openForm(${cars.id})"
                 src="${cars.path}"
                 alt="${cars.id}">
        </figure>
    </c:forEach>
</div>
<%--onclick="openForm(`${cars.brand} | ${cars.name} | ${cars.model}`)"--%>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<script>

    window.addEventListener('contextmenu', (event) => {
        event.preventDefault()
        window.history.back();
    })

    $(document).ready(function () {
        $('.carousel').carousel();
    });

    function openForm(id) {
        if ("${user.role}" === "admin" || "${user.role}" === "manager" || "${user.role}" === "user") {
            window.location.href =
                "/order?id=" + id;
        } else {
            window.location.href =
                "/login";
        }
    }

</script>

</body>
</html>

