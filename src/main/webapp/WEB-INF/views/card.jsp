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
        <%@include file="../classes/templates/css/card.css"%>
        <%@include file="../classes/templates/css/car.css"%>


    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
          integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
</head>
<body>
<main class="m-3">
    <div>
        </form>
        <div class="cars">
            <jsp:include page="nav.jsp"/>
            <jsp:include page="popup.jsp"/>
            <div class="car" id="ads">
                <c:forEach items="${cars}" var="cars">
                    <ul class="col-md-4">
                        <li class="card rounded">
                            <div class="card-image fw-900" onclick="flip(${cars.path})">
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
                        </li>
                    </ul>
                </c:forEach>
            </div>
        </div>
    </div>

</main>
<%--    <a class="button" href="?page=${page}">${page}</a>--%>
<%--    <a class="button" href="?page=${page+1}">${page+1}</a>--%>
<nav aria-label="Navigation for countries">
    <%--    <c:forEach begin="1" end="${noOfPages}" var="i">--%>
    <%--        <c:choose>--%>
    <%--            <c:when test="${page eq i}">--%>
    <%--                <li class="page-item active"><a class="page-link">--%>
    <%--                        ${i} <span class="sr-only">(current)</span></a>--%>
    <%--                </li>--%>
    <%--            </c:when>--%>
    <%--            <c:otherwise>--%>
    <%--                <li class="page-item"><a class="page-link"--%>
    <%--                                         href="?recordsPerPage=${recordsPerPage}&page=${i}">${i}</a>--%>
    <%--                </li>--%>
    <%--            </c:otherwise>--%>
    <%--        </c:choose>--%>
    <%--    </c:forEach>--%>
    <%--    <ul class="pagination">--%>
    <%--        <c:if test="${page != 1}">--%>
    <%--            <li class="page-item"><a class="page-link"--%>
    <%--                                     href="?recordsPerPage=${recordsPerPage}?Page=${page-1}">Previous</a>--%>
    <%--            </li>--%>
    <%--        </c:if>--%>

    <%--        <c:forEach begin="1" end="${noOfPages}" var="i">--%>
    <%--            <c:choose>--%>
    <%--                <c:when test="${page eq i}">--%>
    <%--                    <li class="page-item active"><a class="page-link">--%>
    <%--                            ${i} <span class="sr-only">(current)</span></a>--%>
    <%--                    </li>--%>
    <%--                </c:when>--%>
    <%--                <c:otherwise>--%>
    <%--                    <li class="page-item"><a class="page-link"--%>
    <%--                                             href="?recordsPerPage=${recordsPerPage}?Page=${i}">${i}</a>--%>
    <%--                    </li>--%>
    <%--                </c:otherwise>--%>
    <%--            </c:choose>--%>
    <%--        </c:forEach>--%>

    <%--        <c:if test="${page lt noOfPages}">--%>
    <%--            <li class="page-item"><a class="page-link"--%>
    <%--                                     href="?recordsPerPage=${recordsPerPage}?Page=${page+1}">Next</a>--%>
    <%--            </li>--%>
    <%--        </c:if>--%>
    <%--    </ul>--%>
</nav>
<script>
    // console.log(document.querySelectorAll("selected"));

    function update() {
        let auto = document.getElementById('brand');
        let text = document.getElementById('text');
        let button = document.getElementsByClassName('button');
        let price = document.getElementById('price');
        let value = document.getElementById('value');

        let url = `?${value}=` + value;

        console.log(url);
        console.log(auto);
        console.log(price);
        console.log(value);
        console.log(button);

        // fetch(url, {
        // method: 'GET',
        // }).then(response => {
        // console.log('Ok:', response);
        // }).catch(err => {
        // console.error(err)
        // })
    }

    update();

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

    $(document).ready(function () {
        $('#ads').click(function () {
            $('#myPopup').modal('show')
        });
    });

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