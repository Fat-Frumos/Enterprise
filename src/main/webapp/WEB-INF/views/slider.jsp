<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 10/18/2022
  Time: 11:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Slider</title>
    <style>

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        @keyframes fade {
            from {
                opacity: 0.4;
            }
            to {
                opacity: 1;
            }
        }

        body {
            background: #eeeeee;
        }

        #slider {
            margin: 0 auto;
            width: 80%;
            /*overflow: hidden;*/
        }

        .slides {
            overflow: hidden;
            animation-name: fade;
            animation-duration: 1s;
            display: none;
        }

        img {
            width: 100%;
        }

        #dot {
            margin: 0 auto;
            text-align: center;
        }

        .dot {
            display: inline-block;
            border-radius: 50%;
            background: #d3d3d3;
            padding: 8px;
            margin: 10px 5px;
        }

        .active {
            background: black;
        }

        @media (max-width: 567px) {
            #slider {
                width: 100%;

            }
        }


    </style>
</head>
<body>
<div class="carousel">

    <button class="mySlides fade form-popup" id="myForm" onclick="closeForm()"></button>
    <c:forEach items="${cars}" var="cars">
        <figure class="carousel-item">
            <div id="slider">
                <div class="slides">
                    <img class="" onclick="openForm(`${cars.brand} | ${cars.name} | ${cars.model}`)" src="${cars.path}"
                         alt="${cars.brand} | ${cars.name} | ${cars.model}">
                </div>
            </div>
        </figure>

    </c:forEach>
    <%--    <button onclick="history.back()">•</button>--%>
</div>

<script type="text/javascript">
    var index = 0;
    var slides = document.querySelectorAll(".slides");
    // var dot = document.querySelectorAll(".dot");

    function changeSlide() {

        if (index < 0) {
            index = slides.length - 1;
        }

        if (index > slides.length - 1) {
            index = 0;
        }

        for (let i = 0; i < slides.length; i++) {
            slides[i].style.display = "none";
            // dot[i].classList.remove("active");
        }

        slides[index].style.display = "block";
        // dot[index].classList.add("active");

        index++;

        setTimeout(changeSlide, 5000);
    }

    changeSlide();
</script>
</body>
</html>

<%--<html>--%>
<%--<head>--%>
<%--    <title>Slider</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="carousel">--%>

<%--    <button class="mySlides fade form-popup" id="myForm" onclick="closeForm()"></button>--%>
<%--    <c:forEach items="${cars}" var="cars">--%>
<%--        <figure class="carousel-item">--%>
<%--            <div class="mySlides fade">--%>
<%--                <img class="" onclick="openForm(`${cars.brand} | ${cars.name} | ${cars.model}`)" src="${cars.path}"--%>
<%--                     alt="${cars.brand} | ${cars.name} | ${cars.model}">--%>
<%--                <a class="prev" onclick="plusSlides(-1)">&#10094;</a>--%>
<%--                <a class="next" onclick="plusSlides(1)">&#10095;</a>--%>
<%--            </div>--%>
<%--            <div style="text-align:center">--%>
<%--                <span class="dot" onclick="currentSlide(1)"></span>--%>
<%--                <span class="dot" onclick="currentSlide(2)"></span>--%>
<%--                <span class="dot" onclick="currentSlide(3)"></span>--%>
<%--            </div>--%>
<%--        </figure>--%>

<%--    </c:forEach>--%>
<%--    &lt;%&ndash;    <button onclick="history.back()">•</button>&ndash;%&gt;--%>
<%--</div>--%>
<%--<script>--%>
<%--    var slideIndex = 1;--%>
<%--    showSlides(slideIndex);--%>

<%--    function plusSlides(n) {--%>
<%--        showSlides(slideIndex += n);--%>
<%--    }--%>

<%--    function currentSlide(n) {--%>
<%--        showSlides(slideIndex = n);--%>
<%--    }--%>

<%--    function showSlides(n) {--%>
<%--        var i;--%>
<%--        var slides = document.getElementsByClassName("mySlides");--%>
<%--        var dots = document.getElementsByClassName("dot");--%>

<%--        if (n > slides.length) {--%>
<%--            slideIndex = 1;--%>
<%--        }--%>
<%--        if (n < 1) {--%>
<%--            slideIndex = slides.length;--%>
<%--        }--%>
<%--        for (i = 0; i < slides.length; i++) {--%>
<%--            slides[i].style.display = "none";--%>
<%--        }--%>
<%--        for (i = 0; i < dots.length; i++) {--%>
<%--            dots[i].className = dots[i].className.replace(" active", "");--%>
<%--        }--%>
<%--        slides[slideIndex - 1].style.display = "block";--%>
<%--        dots[slideIndex - 1].className += " active";--%>
<%--    }--%>

<%--    // let slideIndex = 0;--%>
<%--    showSlides();--%>

<%--    function showSlides() {--%>
<%--        var i;--%>
<%--        let slides = document.getElementsByClassName("mySlides");--%>
<%--        let dots = document.getElementsByClassName("dot");--%>
<%--        for (i = 0; i < slides.length; i++) {--%>
<%--            slides[i].style.display = "none";--%>
<%--        }--%>
<%--        slideIndex++;--%>
<%--        if (slideIndex > slides.length) {--%>
<%--            slideIndex = 1;--%>
<%--        }--%>
<%--        for (i = 0; i < dots.length; i++) {--%>
<%--            dots[i].className = dots[i].className.replace(" active", "");--%>
<%--        }--%>
<%--        slides[slideIndex - 1].style.display = "block";--%>
<%--        dots[slideIndex - 1].className += " active";--%>
<%--        setTimeout(showSlides, 4000)--%>
<%--    };--%>
<%--</script>--%>
<%--</body>--%>
<%--</html>--%>
