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
        <%@include file="../classes/templates/css/car.css"%>
        <%@include file="../classes/templates/css/slider.css"%>
        img {
            border-radius: 20px;
        }

        img:hover {
            transform: scale(1.2);
            cursor: pointer;
        }

        button {
            background-color: #5d5d5d;
            width: 20px;
            height: 20px;
            float: left;
            border: none;
            border-radius: 10px;
            margin-right: 20px;
            cursor: pointer;
            transition: 0.2s ease width;
            text-align: center;
            font-family: Verdana, Roboto, sans-serif;
            font-size: 14px;
            color: #ffffff;
            line-height: 15px;
        }

        .hide {
            opacity: .5;
            /*display: none;*/
        }

        .img:hover + .hide {
            /*display: block;*/
            /*color: red;*/
        }

    </style>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
</head>

<body>

<div class="carousel">

    <button class="mySlides fade form-popup" id="myForm" onclick="closeForm()"></button>
    <c:forEach items="${cars}" var="cars">
        <figure class="carousel-item">
            <img class="" onclick="openForm(`${cars.brand} | ${cars.name} | ${cars.model}`)" src="${cars.path}"
                 alt="${cars.brand} | ${cars.name} | ${cars.model}">
        </figure>

    </c:forEach>
    <%--    <button onclick="history.back()">•</button>--%>
</div>
<script>

        let htmlElement = document.getElementById("myForm");
        htmlElement.innerText = document.getElementsByTagName('img')[0].alt;

        $(document).ready(function () {
            let car = document.getElementsByTagName('img')[0].alt
            let htmlElement = document.getElementById("myForm");
            htmlElement.innerText = car;
            $('.carousel').carousel();
            htmlElement.style.display = "block";


        });

        function openForm(car) {
            console.log(car)
            htmlElement.innerText = car;
        }

        function closeForm() {
            htmlElement.style.display = "none";
            window.location.href = "/cars";
        }


    </script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>

