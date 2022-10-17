<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 8/25/2022
  Time: 4:06 PM
  To change this template use File | Settings | File Templates.
--%>

<!DOCTYPE html>
<head>
    <title>Cars</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
          integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">

    <style>
        <%@include file="../classes/templates/css/card.css"%>
        <%@include file="../classes/templates/css/car.css"%>
        input {
            margin-bottom: 5px;
        }

        .wrap {
            margin: 10px 10px;
            max-width: 40vw !important;
            top: 10vh;
            left: 25vw;
            border-radius: 5px;
            transform: perspective(500px) translateY(-5px);
            position: relative;
            transition: all 5s ease-in-out;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
        }

    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"></script>
    <link rel="shortcut icon" href="https://iconarchive.com/download/i18444/iconshock/global-warming/wheel.ico" type="image/x-icon">
</head>
<body>
<main class="m-3">
    <div>
        </form>
        <div class="cars">
            <jsp:include page="nav.jsp"/>
            <div class="car" id="ads">
                <c:forEach items="${cars}" var="cars">
                    <ul class="col-md-4">
                        <li class="card rounded">
                            <div class="modal fade" id="${cars.id}">
                                <div class="modal-dialog wrap popup">
                                    <div class="modal-content">
                                        <img src="${cars.path}" alt="${cars.name}">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal">x</button>
                                        </div>

                                    </div>
                                </div>
                            </div>
                            <div class="card-image fw-900"
                                 onclick="showCar(`${cars.id}`, `${cars.path}`,`${cars.brand}`, `${cars.name}`, `${cars.model}`)">
                                <span class="card-detail-badge m-2">${cars.brand} ${cars.name} ${cars.model}</span>
                                <figure>
                                    <img class="img-fluid"
                                         src="${cars.path}"
                                         alt="url">
                                    <figcaption></figcaption>
                                </figure>
                            </div>
                            <div class="card-image-overlay m-auto">
                                <span class="badge">Used</span>
                                <span class="card-detail-badge">$${cars.price}</span>
                            </div>
                        </li>
                    </ul>
                </c:forEach>
            </div>
        </div>
    </div>

</main>
</nav>
<script>
    // console.log(document.querySelectorAll("selected"));

    <%--function update() {--%>
    <%--    let auto = document.getElementById('brand');--%>
    <%--    let text = document.getElementById('text');--%>
    <%--    let button = document.getElementsByClassName('button');--%>
    <%--    let price = document.getElementById('price');--%>
    <%--    let value = document.getElementById('value');--%>

    <%--    let url = `?${value}=` + value;--%>

    <%--    console.log(url);--%>
    <%--    console.log(auto);--%>
    <%--    console.log(price);--%>
    <%--    console.log(value);--%>
    <%--    console.log(button);--%>

    <%--    // fetch(url, {--%>
    <%--    // method: 'GET',--%>
    <%--    // }).then(response => {--%>
    <%--    // console.log('Ok:', response);--%>
    <%--    // }).catch(err => {--%>
    <%--    // console.error(err)--%>
    <%--    // })--%>
    <%--}--%>

    function showCar(id, path, brand, name, price) {
        console.log(path);
        console.log(brand);
        console.log(name);
        console.log(price);
        $("#" + id).modal('show')
    }

    <%--    //     $('#ads').click(function () {--%>
    <%--    //         $('#myPopup').modal('show')--%>
    <%--    //     });--%>

    <%--    let ads = document.getElementById('myPopup');--%>
    <%--    console.log(ads);--%>
    <%--    $('#myPopup').modal('show');--%>

    <%--    let text = document.getElementById('text');--%>
    <%--    let button = document.getElementsByClassName('button');--%>
    <%--    let price = document.getElementById('price');--%>
    <%--    let value = document.getElementById('value');--%>

    <%--    let url = `?${value}=` + value;--%>

    <%--    console.log(url);--%>

    <%--    console.log(price);--%>
    <%--    console.log(value);--%>
    <%--    console.log(button);--%>

    <%--    // fetch(url, {--%>
    <%--    // method: 'GET',--%>
    <%--    // }).then(response => {--%>
    <%--    // console.log('Ok:', response);--%>
    <%--    // }).catch(err => {--%>
    <%--    // console.error(err)--%>
    <%--    // })--%>
    <%--}--%>

    // update();

    function addCar(id) {
        let url = '/' + '?id=' + id;
        console.log(url);
        fetch(url, {
            method: 'GET',
        }).then(response => {
            console.log('Ok:', response);
        }).catch(err => {
            console.error(err)
        })
    }

    // $(document).ready(function () {
    //     $('.img-fluid').click(function () {
    //         $('#myPopup').modal('show')
    //     });
    // });

    // $("#myPopup").load("b.html");


    // function selectedTrue() {
    //     document.getElementById("selected").selected = "true";
    // }
    // $(function () {
    // var button = $(".button");
    // function switchToNext() {
    // var _this = $(this);
    // if (_this.hasClass("active")) {
    // return false;
    // } else {
    // $(".button.active").removeClass("active");
    // _this.addClass("active");
    // }
    // }
    // button.on("click", switchToNext);
    // });

</script>
</body>
</html>