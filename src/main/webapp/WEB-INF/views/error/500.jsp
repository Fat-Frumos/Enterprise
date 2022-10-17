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
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Main</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <style>
        <%@include file="../../classes/templates/css/car.css"%>
        <%@include file="../../classes/templates/css/slider.css"%>

        body > div.carousel > figure.carousel-item.active > img:hover{
            transform: scale(1.2);
            cursor: pointer;
        }
    </style>
</head>

<body>

<div class="carousel">
    <button class="form-popup" id="myForm" onclick="closeForm()"></button>
    <c:forEach items="${cars}" var="cars">
        <figure class="carousel-item">
            <img onclick="openForm(`${cars.brand} • ${cars.name} • ${cars.model}`)" src="${cars.path}" alt="${cars.brand}">
        </figure>
    </c:forEach>

</div>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        let elems = document.querySelectorAll('.carousel');
        M.Carousel.init(elems);

    });


    function openForm(car) {
        console.log(car)
        document.getElementById("myForm").innerText = car;
        document.getElementById("myForm").style.display = "block";
    }

    function closeForm() {
        document.getElementById("myForm").style.display = "none";
        window.location.href = "/cars";

    }
</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>