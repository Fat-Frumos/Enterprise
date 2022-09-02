<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 8/28/2022
  Time: 10:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Enterprise</title>
    <style>
        <%@include file="../classes/static/css/carousel.css"%>
        <%@include file="../classes/static/css/style.css"%>
    </style>
    <script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
          integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
</head>
<body>
<div class="slide_style_bottom">
    <a class="carousel-control-prev" href="#myCarousel" role="button" data-slide="prev"> <span
            class="carousel-control-prev-icon" aria-hidden="true"></span> <span class="sr-only">Previous</span> </a>
    <a class="carousel-control-next" href="#myCarousel" role="button" data-slide="next"> <span
            class="carousel-control-next-icon" aria-hidden="true"></span> <span class="sr-only">Next</span> </a>
</div>
<div class="top-content">
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
            <li data-target="#myCarousel" data-slide-to="3"></li>
        </ol>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img class="d-block w-100"
                     src="https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-47.jpg"
                     alt=" ">
                <div class="slide_style_left text-center">
                    <div class="row">
                        <div class="col-12 slide-text text-left">
                            <h1 class="animate__animated animate__lightSpeedInLeft">Rental Car</h1>
                            <p class="animate__animated animate__fadeInUp"><small>one touch</small></p>
                            <a href="/login" class="btn btn-primary animate__animated animate__zoomIn">
                                select one
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="carousel-item">
                <img class="d-block w-100"
                     src="https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-49.jpg"
                     alt="First slide">

                <!-- Slide Text Layer -->
                <div class="slide_style_right">
                    <div class="row">
                        <div class="col-12 slide-text text-right">
                            <h1 class="animate__animated animate__lightSpeedInRight">Bugatti</h1>
                            <p class="animate__animated animate__fadeInUp">now touch enable</p>
                            <a href="/login" class="btn btn-warning animate__animated animate__fadeInUp">
                                select one
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="carousel-item">
                <img class="d-block w-100"
                     src="https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-40.jpg"
                     alt="Second slide">
                <!-- Slide Text Layer -->
                <div class="slide_style_left text-center">
                    <div class="row">
                        <div class="col-12 slide-text text-left">
                            <h1 class="animate__animated animate__lightSpeedInLeft">Need For Speed</h1>
                            <p class="animate__animated animate__fadeInUp"><small>one touch</small></p>
                            <a href="/login" class="btn btn-danger animate__animated animate__fadeInUp">
                                select two
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="carousel-item">
                <img class="d-block w-100"
                     src="https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-41.jpg"
                     alt=" ">
                <!-- Slide Text Layer -->
                <div class="slide_style_left">
                    <div class="row">
                        <div class="col-12 slide-text text-left">
                            <h2 class="animate__animated animate__lightSpeedInLeft slide-heading">Prior</h2>
                            <p class="animate__animated animate__fadeInUp"><small>now touch enable </small></p>
                            <a href="/login" class="btn btn-warning  animate__animated animate__fadeInUp">
                                select one</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
