<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" uri="/WEB-INF/tags/dataTag.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 8/24/2022
  Time: 6:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:choose>
    <c:when test="${user.language=='ua'}">
        <fmt:setLocale value="ua" scope="session"/>
        <fmt:setBundle basename="com.enterprise.rental.service.locale.BungleUa" var="lang"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="en" scope="session"/>
        <fmt:setBundle basename="com.enterprise.rental.service.locale.BungleEn" var="lang"/>
    </c:otherwise>
</c:choose>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="author" content="Pasha">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv=Content-Type content="text/html; charset=windows-1251">
    <link rel="shortcut icon" href="<c:url value="/upload?wheel.ico"/>" type="image/x-icon">
    <title><fmt:message key="title.cars" bundle="${lang}"/></title>
</head>

<style>
    <%@include file="../classes/templates/css/modal.css"%>
    <%@include file="../classes/templates/css/form.css"%>
    <%@include file="../classes/templates/css/tag.css"%>

    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }

</style>
<body>
<nav>
    <div style="position: absolute;top:55vh; left: 1vw;">
        <jsp:include page="tag.jsp"/>
    </div>
</nav>
<jsp:include page="nav.jsp"/>
<div class="container">
    <div class="row m-0">
        <div class="col-lg-7 pe-lg-5">
            <jsp:include page="label.jsp"/>
        </div>
        <jsp:include page="car.jsp"/>
    </div>
    <hr>
    <jsp:include page="cart.jsp"/>
<%--    <jsp:include page="flag.jsp"/>--%>

</div>
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

</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"></script>
</body>
</html>