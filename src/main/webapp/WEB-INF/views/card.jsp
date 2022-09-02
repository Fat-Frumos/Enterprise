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
    <style>
        <%@include file="../classes/static/css/card.css"%>
        <%@include file="../classes/static/css/car.css"%>

    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
          integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
</head>
<body>
<div>
    <div class="cars">
        <jsp:include page="nav.jsp"/>
        <jsp:include page="popup.jsp"/>
        <div class="car" id="ads">
            <c:forEach items="${cars}" var="cars">
                <div class="col-md-4">
                    <div class="card rounded">
                        <div class="card-image fw-900">
                            <span class="card-detail-badge m-2">${cars.brand} ${cars.name} ${cars.model}</span>
                            <figure onclick="addCar(${cars.id})">
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
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        $('#ads').click(function () {
            $('#myPopup').modal('show').text($.param())
        });
    });

    function addCar(id) {
        let url = '/cars' + '?id=' + id;
        console.log(url);
        fetch(url, {
            method: 'POST',
        }).then(response => {
            console.log('Ok:', response);
        }).catch(err => {
            console.error(err)
        })
    }
</script>
</body>
</html>