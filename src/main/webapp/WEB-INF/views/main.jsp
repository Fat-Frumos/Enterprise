<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Rental Car</title>
</head>

<style>
    <%@include file="../classes/static/css/car.css"%>
</style>

<body>
<jsp:include page="nav.jsp"/>
<jsp:include page="modal.jsp"/>
<div class="container">
    <div class="row m-0">
        <div class="col-lg-7 pb-5 pe-lg-5">
            <jsp:include page="label.jsp"/>
        </div>
        <jsp:include page="car.jsp"/>
        <jsp:include page="card.jsp"/>
    </div>
</div>
<div>
</div>
<script>
    $(document).ready(function () {
        $('#main').click(function () {
            $('#Mymodal').modal('show')
        });
    });
</script>
</body>
</html>

