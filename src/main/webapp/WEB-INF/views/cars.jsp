<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 10/9/2022
  Time: 9:28 PM
  Custom Tag  | Jspl Templates.
--%>
<%@ taglib uri="/WEB-INF/dataTag.tld" prefix="ct" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
<title>Cars</title>
<style>
    <%@include file="../classes/templates/css/cart.css"%>
    <%@include file="../classes/templates/css/car.css"%>

    .day {
        position: absolute;
        top: 3vh;
        left: 90vw;
    }


</style>
<link rel="shortcut icon" href="https://iconarchive.com/download/i18444/iconshock/global-warming/wheel.ico" type="image/x-icon">
</head>
<body>
    <jsp:include page="option.jsp"/>
    <jsp:include page="cart.jsp"/>
    <jsp:include page="nav.jsp"/>
    <div class="day">
        <h6><ct:today format="MMMM dd yyyy"/></h6>
        <a style="text-transform: capitalize" href="/user/${user}">(${user})</a>
    </div>
</div>
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
