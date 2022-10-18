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
        figure {
            overflow: hidden;
        }

        .modal-content:hover {
            cursor: pointer;
        }
        img {
            overflow: hidden;
        }
    </style>
    <link rel="shortcut icon" href="https://iconarchive.com/download/i18444/iconshock/global-warming/wheel.ico" type="image/x-icon">
</head>
<body>
<main class="m-3">
    <div>
        </form>
        <div class="cars">
            <div class="car" id="ads">
                <c:forEach items="${cars}" var="cars">
                    <ul class="col-md-4">
                        <li class="card rounded">
                            <div class="modal fade" id="${cars.id}">
                                <div class="modal-dialog wrap popup">
                                    <div class="modal-content">
                                        <img onclick="toCard(${cars.id})" src="${cars.path}" alt="${cars.name}">
                                        <div class="modal-header">
<%--                                            <button  type="button" class="add" data-dismiss="modal">V</button>--%>
<%--                                            <button type="button" class="close" data-dismiss="modal">x</button>--%>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="card-image fw-900"
                                 onclick="showCar(`${cars.id}`, `${cars.path}`,`${cars.brand}`, `${cars.name}`, `${cars.price}`)">
                                <span class="card-detail-badge m-2">${cars.brand} ${cars.name} ${cars.model}</span>
                                <figure>
                                    <img class="img-fluid"
                                         src="${cars.path}"
                                         alt="`${cars.id}`, `${cars.path}`,`${cars.brand}`, `${cars.name}`, `${cars.price}`">
                                    <figcaption></figcaption>
                                </figure>
                            </div>
                            <div class="card-image-overlay m-auto">
                                <span class="badge">Rent:</span>
                                <span class="card-detail-badge">$${cars.price}</span>
                                <span class="badge">| Price:</span>
                                <span class="card-detail-badge">$${cars.cost}</span>
                            </div>
                        </li>
                    </ul>
                </c:forEach>
            </div>
        </div>
    </div>
</main>
</nav>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"></script>
<script>

    function toCard(id) {
        let url = '/card' + '?id=' + id;
            fetch(url, {
            method: 'POST',
            }).then(response => {
            console.log('Ok:', response);
            }).catch(err => {
            console.error(err)
            })
        window.location.href = url;
    }
    function showCar(id, path, brand, name, price) {
        console.log(path);
        console.log(brand);
        console.log(name);
        console.log(price);
        $("#" + id).modal('show');
        // window.location.href = "/";
    }


    // console.log(document.querySelectorAll("selected"));

    <%--function update() {--%>
    <%--    let auto = document.getElementById('brand');--%>
    <%--    let text = document.getElementById('text');--%>
    <%--    let button = document.getElementsByClassName('button');--%>
    <%--    let price = document.getElementById('price');--%>
    <%--    let value = document.getElementById('value');--%>

    <%--    let url = `?${value}=` + value;--%>

    <%--    //     $('#ads').click(function () {--%>
    <%--    //         $('#myPopup').modal('show')--%>
    <%--    //     });--%>

    <%--    let ads = document.getElementById('myPopup');--%>
    <%--    console.log(ads);--%>
    <%--    $('#myPopup').modal('show');--%>


    // $(document).ready(function () {
    //     $('.img-fluid').click(function () {
    //         $('#myPopup').modal('show')
    //     });
    // });

    // $("#myPopup").load("b.html");


</script>
</body>
</html>