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
            /*border-radius: 10px;*/
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

        /*.active {*/
        /*    background: black;*/
        /*}*/

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
                    <img class="" onclick="openForm(`${cars.brand}, ${cars.name}, ${cars.model}`)" src="${cars.path}"
                         alt="${cars.brand} | ${cars.name} | ${cars.model}">
                </div>
            </div>
        </figure>
    </c:forEach>
    <%--    <button onclick="history.back()">•</button>--%>
</div>

<script type="text/javascript">
    // function openForm(brand, name, model, price, cost) {
    //     $("#" + id).modal('show');
    //     let url = '/cart' + '?id=' + id;
    //     fetch(url, {
    //         method: 'PUT',
    //     }).then(response => {
    //         console.log('Ok:', response);
    //         window.location.href = url;
    //     }).catch(err => {
    //         console.error(err)
    //     })

    let index = 0;
    let slides = document.querySelectorAll(".slides");
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


