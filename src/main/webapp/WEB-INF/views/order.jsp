<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ct" uri="/WEB-INF/tags/dataTag.tld" %>
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
<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 10/24/2022
  Time: 1:57 PM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<head>
    <title><fmt:message key="title.orders" bundle="${lang}"/></title>
    <meta charset="UTF-8">
    <meta name="author" content="Pasha">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv=Content-Type content="text/html; charset=windows-1251">
    <link rel="shortcut icon" href="<c:url value="/upload?wheel.ico"/>" type="image/x-icon">
</head>
<style>
    @import url('https://fonts.googleapis.com/css?family=Kaushan+Script|Saira&display=swap');

    <%@include file="../classes/templates/css/users.css"%>
    <%@include file="../classes/templates/css/check-box.css"%>

    body {
        font-family: 'Saira', sans-serif;
        /*margin-left: 10px;*/
    }

    input {
        border: none;
    }

    th {
        text-align: center;
    }

    p {
        text-align: center;
        margin: 0;
    }

    tr:nth-child(even) {
        background-color: rgba(127, 127, 127, .1);
    }
    div.navigation > ul {
        margin: 0;
        padding: 0;

    }

</style>
<jsp:include page="nav.jsp"/>
<body>

<div class="container">
    <div class="col-lg-12">
        <h2 style="margin-bottom: 50px; text-align: center"><fmt:message key="h5.orders" bundle="${lang}"/></h2>
    </div>
    <div class="col-lg-12">
        <table id="tabs" class="table-striped" style="width:100%">
            <caption></caption>
            <thead class="TableHead">
            <tr>
                <th style="width: 50px"><fmt:message key="th.order" bundle="${lang}"/></th>
                <th style="width: 30px"><fmt:message key="th.car" bundle="${lang}"/></th>
                <th style="width: 120px"><fmt:message key="th.create" bundle="${lang}"/></th>
                <th style="width: 120px"><fmt:message key="th.term" bundle="${lang}"/></th>
                <th style="width: 50px"><fmt:message key="th.payment" bundle="${lang}"/></th>
                <th style="width: 150px"><fmt:message key="th.damage" bundle="${lang}"/></th>
                <th style="width: 350px"><fmt:message key="th.reason" bundle="${lang}"/></th>
                <th style="width: 50px"><fmt:message key="th.reject" bundle="${lang}"/></th>
                <th style="width: 50px"><fmt:message key="th.close" bundle="${lang}"/></th>
            </tr>
            </thead>
            <tbody>
            <span id="exchangeRate" hidden><fmt:message key="exchange" bundle="${lang}"/></span>
            <c:forEach items="${orders}" var="order">
                <tr class="firstRow">
                    <td><p>${order.orderId}</p></td>
                    <td><p>${order.carId}</p></td>
                    <td><p><fmt:formatDate pattern="yyyy-MM-dd" value="${order.created}"/></p></td>
                    <td><p><fmt:formatDate pattern="yyyy-MM-dd" value="${order.term}"/></p></td>
                    <td><p><span class="ratePrice">${order.payment}</span>
                        <span class="badge"><fmt:message key="exchange.sign" bundle="${lang}"/></span>
                    </p></td>
                    <td><p>${order.damage}</p></td>
                    <td><p>${order.reason}</p></td>
                    <td><p type="checkbox" class="${order.rejected}">${order.rejected}</p></td>
                    <td><p type="checkbox" class="${order.closed}">${order.closed}</p></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <script>

            let exchangeRateUa = parseFloat(document.getElementById("exchangeRate").innerHTML).toFixed(2);

            let ratePrices = document.getElementsByClassName("ratePrice");

            for (let i = 0; i < ratePrices.length; i++) {
                ratePrices[i].innerHTML = "" + (ratePrices[i].innerHTML * exchangeRateUa).toFixed(0);
            }

            window.addEventListener('contextmenu', (event) => {

                event.preventDefault()
                window.history.back();
            })

        </script>

    </div>
    <h5 style="left: 90vw; top: 0; position: absolute"><ct:today format="dd MMMM yyyy"/></h5>
</div>
</body>