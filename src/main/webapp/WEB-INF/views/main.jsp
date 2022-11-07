<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" uri="/WEB-INF/dataTag" %>
<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 8/24/2022
  Time: 6:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Rental Car</title>
    <link rel="shortcut icon" href="https://raw.githubusercontent.com/Fat-Frumos/Cars/master/wheel.ico"
          type="image/x-icon">
</head>

<style>
    <%@include file="../classes/templates/css/modal.css"%>
    <%@include file="../classes/templates/css/car.css"%>
    <%@include file="../classes/templates/css/form.css"%>

</style>
<body>
<div class="container">
    <jsp:include page="nav.jsp"/>
    <div class="row m-0">
        <div class="col-lg-7 pb-5 pe-lg-5">
            <jsp:include page="label.jsp"/>
        </div>
        <jsp:include page="car.jsp"/>
    </div>
    <hr>
    <jsp:include page="cart.jsp"/>
<%--        <jsp:include page="modal.jsp"/>--%>
</div>
<jsp:include page="burger.jsp"/>
<div class="day">
    <h6><ct:today format="MMMM dd yyyy"/></h6>
    <a style="text-transform: capitalize" href="/user/${user}">(${user})</a>
</div>
<div class="cf-hidden">
    <input type="text" id="start-date">
    <input type="text" id="end-date">
</div>
<div id="dp-island"></div>
<button id="search">Search</button>
<div id="output"></div>

<script>
    window.addEventListener('contextmenu', (event) => {
        event.preventDefault()
        window.history.back();
    })
    $(document).ready(function () {
        $('#main').click(function () {
            $('#Mymodal').modal('show')
        });
    });
</script>
</body>
</html>