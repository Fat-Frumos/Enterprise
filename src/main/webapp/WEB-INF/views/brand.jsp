<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 8/27/2022
  Time: 8:18 PM
  To change this template use File | Settings | File Templates.
--%>
<html>
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
                    <div class="card rounded">
                        <div class="card-image fw-900">
                            <span class="card-notify-badge m-2">${cars.brand} ${cars.name} ${cars.model}</span>
                            <span class="card-notify-year">${cars.year}</span>
                            <figure>
                                <img class="img-fluid"
                                     src="${cars.path}"
                                     alt="url">
                                <figcaption></figcaption>
                            </figure>
                        </div>
                        <div class="card-image-overlay m-auto">
                            <span class="card-detail-badge">Used</span>
                            <span class="card-detail-badge">$${cars.price}</span>
                        </div>
                        <div class="card-body text-center">
                            <a class="ad-btn" href="#">View</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>
