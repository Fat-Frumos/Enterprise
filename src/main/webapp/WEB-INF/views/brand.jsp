<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 8/27/2022
  Time: 8:18 PM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<head>
    <title>Brand</title>
</head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">

<body>
<div>
    <div class="cars">
        <div class="car" id="ads">
            <c:forEach items="${cars}" var="cars">
                <div class="col-md-4">
                    <div class="cart rounded">
                        <div class="cart-image fw-900">
                            <span class="cart-notify-badge m-2">${cars.brand} ${cars.name} ${cars.model}</span>
                            <span class="cart-notify-year">${cars.year}</span>
                            <figure>
                                <img class="img-fluid"
                                     src="${cars.path}"
                                     alt="${cars.brand} ${cars.name} ${cars.model}">
                                <figcaption></figcaption>
                            </figure>
                        </div>
                        <div class="cart-image-overlay m-auto">
                            <span class="cart-detail-badge">Used</span>
                            <span class="cart-detail-badge">$${cars.price}</span>
                            <span class="cart-detail-badge">$${cars.cost}</span>
                        </div>
                        <div class="cart-body text-center">
                            <a class="ad-btn" href="#">View</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>
