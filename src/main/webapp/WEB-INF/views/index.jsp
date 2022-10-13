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

        /*html{*/
        /*    background: radial-gradient(ellipse at top, #C0C0C0, transparent), radial-gradient(ellipse at bottom, #e1e1e1, transparent);*/
        /*}*/

        body {
            margin: 0;
            padding: 0;
            background: #e1e1e1;
        }
        .carousel{
            height: 400px;
            height: 850px;
            perspective: 250px;
            /*overflow: hidden;*/
        }

        .carousel img {
            /*border-radius: 20px;*/

            /*background: linear-gradient(transparent, gray);*/
            /*-webkit-mask-image: -webkit-gradient(radial, left top, left bottom, from(rgba(169,208,113,0)), to(rgba(136,173,215,1)));*/
            /*-webkit-mask-image: -webkit-gradient(linear, left top, left bottom, from(rgba(0,0,0,1)), to(rgba(0,0,0,0)));*/
        }


        .carousel .carousel-item{
            width: 600px;
        }

        .carousel:hover img {
            opacity: 1;
        }

    </style>

</head>

<body>

<div class="carousel">
    <c:forEach items="${cars}" var="cars">
        <a class="carousel-item" href="login"><img src="${cars.path}" alt=""></a>
    </c:forEach>
</div>

<%--<div class="carousel" id="slider">--%>
<%--    <c:forEach items="${cars}" var="cars">--%>
<%--                <div class="card-image fw-900" onclick="flip(${cars.path})">--%>
<%--                    <span class="card-detail-badge m-2">${cars.brand} ${cars.name} ${cars.model}</span>--%>
<%--                    <figure onclick="addCar(${cars.id})">--%>
<%--                        <img class="img-fluid"--%>
<%--                             src="${cars.path}"--%>
<%--                             alt="url">--%>
<%--                        <figcaption></figcaption>--%>
<%--                    </figure>--%>
<%--                </div>--%>
<%--                <div class="card-image-overlay m-auto">--%>
<%--                    <span class="badge">Used</span>--%>
<%--                    <span class="card-detail-badge">$${cars.price}</span>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </c:forEach>--%>
<%--</div>--%>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        let elems = document.querySelectorAll('.carousel');
        let instances = M.Carousel.init(elems);
        console.log(instances);
    });
</script>
<script src = "https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js" >

    // Or with jQuery
    //     <script
    //   src="https://code.jquery.com/jquery-3.6.1.slim.min.js"
    //   integrity="sha256-w8CvhFs7iHNVUtnSP0YKEg00p9Ih13rlL9zGqvLdePA="
    //   crossorigin="anonymous">
    //   $(document).ready(function(){
    //     $('.carousel').carousel();
    //   });

</script>
</body>

</html>