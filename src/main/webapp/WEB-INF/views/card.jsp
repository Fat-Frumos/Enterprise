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

        .fw-900 {
            font-weight: 900;
        }

    </style>
</head>
<body>
<div>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"
            integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
            crossorigin="anonymous"></script>

    <div class="container">

        <div class="row ml-5" id="ads">
            <c:forEach items="${cars}" var="cars">
                <div class="col-md-4">
                    <div class="card rounded">
                        <div class="card-image fw-900">
                            <span class="card-notify-badge m-2">${cars.brand} ${cars.name}</span>
                            <span class="card-notify-year">${cars.year}</span>
                            <img class="img-fluid"
                                 src="${cars.path}"
                                 alt="Text"/>
                        </div>
                        <div class="card-image-overlay m-auto">
                            <span class="card-detail-badge">Used</span>
                            <span class="card-detail-badge">$${cars.price}</span>
<%--                            <span class="card-detail-badge">13000 Kms</span>--%>
                        </div>
                        <div class="card-body text-center">
                            <div class="ad-title m-auto">
                                <h5>Honda Accord LX</h5>
                            </div>
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