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
    </style>
</head>

<body>

<div class="carousel">
    <div class="form-popup" id="myForm" onclick="closeForm()"></div>
    <c:forEach items="${cars}" var="cars">
        <figure class="carousel-item"><img onclick="openForm(`${cars.brand} • ${cars.name} • ${cars.model}`)" src="${cars.path}" alt="">
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
        window.location.href = "/login";

    }

</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>


<%--<script src="https://code.jquery.com/jquery-3.6.1.slim.min.js" integrity="sha256-w8CvhFs7iHNVUtnSP0YKEg00p9Ih13rlL9zGqvLdePA="crossorigin="anonymous">--%>

<%--    $(document).ready(function(){--%>
<%--        $('.carousel').carousel();--%>
<%--      });--%>
<%--    $('.carousel-item').ready(function(){--%>
<%--        console.log--%>
<%--      });--%>
<%--</script>--%>

</body>
</html>