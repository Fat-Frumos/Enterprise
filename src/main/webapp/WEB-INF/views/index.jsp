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

        .carousel {
            overflow: visible;
            caret-color: transparent;
        }
    </style>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</head>

<body>
<div class="carousel">
    <c:forEach items="${cars}" var="cars">
        <figure class="carousel-item">
            <img class="fade" onclick="closeForm(${cars.id})" src="${cars.path}"
                 alt=${cars.id}>
            <div class="mySlides fade form-popup" id="myForm">
            </div>
        </figure>
    </c:forEach>
</div>
<%--onclick="openForm(`${cars.brand} | ${cars.name} | ${cars.model}`)"--%>
<script>

//    window.addEventListener('contextmenu', (event) => {
//        event.preventDefault()
//        window.history.back();
//    })

    $(document).on('contextmenu', function (event){
        if (!$(event.target).hasClass("carousel-item")){
            event.preventDefault()
            // alert("Car removed")
            window.history.back();
        }
    })
    let htmlElement = document.getElementsByClassName("carousel-item");

    // // htmlElement.innerText = document.getElementsByTagName('img')[0].alt;
    //

    $(document).ready(function () {
        $('.carousel').carousel();

        // let car = document.getElementsByTagName('img')[0].alt
        // let htmlElement = document.getElementById("myForm");
        // htmlElement.innerText = car;
        // htmlElement.style.display = "block";


    });

    function openForm(car) {
        // window.location.href = "/cars";
        console.log(car)
        // htmlElement.innerText = car;
    }

    function closeForm(id) {
        let url = '/order?id=' + id;
        fetch(url, {
            method: 'GET',
        }).then(response => {
            console.log('Ok:', response);
            alert("Success")
            window.location.href = url;
        }).catch(err => {
            console.error(err)
        })
            // window.location.href = "/cars";
         // saveCar(id);
        // let element = document.querySelector("body > div.carousel > figure.carousel-item.active");
        // htmlElement.style.display = "none";
    }
</script>

</body>
</html>

