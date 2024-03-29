<%@ page import="com.enterprise.rental.entity.User" %>
<%@ page import="java.util.Locale" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 10/9/2022
  Time: 3:40 PM
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
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/upload?wheel.ico" type="image/x-icon">

    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous"
    />
    <style>
        @import url('https://fonts.googleapis.com/css?family=Kaushan+Script|Saira&display=swap');

        <%@include file="../classes/templates/css/users.css"%>
        <%@include file="../classes/templates/css/check-box.css"%>
        <%@include file="../classes/templates/css/dice.css"%>
        <%@include file="../classes/templates/css/loader.css"%>

        body, .table td{
            text-align: center;
            margin: 0;
            padding: 8px 0 0;
        }
        h4 {
            margin: 40px;
        }

        .navigation ul {
            display: flex;
            width: 350px;
        }
    </style>
</head>
<body>
<div class="wrapper">
    <jsp:include page="nav.jsp"/>
    <div class="col-lg-12 mb-5">
        <h4 style="text-align: center"><fmt:message key="h5.orders" bundle="${lang}"/></h4>
    </div>

    <div class="col-lg-12">
        <table id="tabs" class="table cell-border" >
            <thead class="TableHead">
            <tr>
                <th><fmt:message key="th.user" bundle="${lang}"/></th>
                <th><fmt:message key="th.order" bundle="${lang}"/></th>
                <th><fmt:message key="th.car" bundle="${lang}"/></th>

                <th style="width: 230px"><fmt:message key="th.create" bundle="${lang}"/>
                    /
                    <fmt:message key="th.term" bundle="${lang}"/></th>
                <th><fmt:message key="th.damage" bundle="${lang}"/></th>
                <th><fmt:message key="th.reason" bundle="${lang}"/></th>
                <th><fmt:message key="th.payment" bundle="${lang}"/></th>
                <th><fmt:message key="th.reject" bundle="${lang}"/></th>
                <th><fmt:message key="th.close" bundle="${lang}"/></th>
                <th style="width: 50px"><fmt:message key="th.confirm" bundle="${lang}"/></th>
<%--                <th><fmt:message key="th.invoice" bundle="${lang}"/></th>--%>
                <th style="width: 50px"><fmt:message key="th.remove" bundle="${lang}"/></th>

            </tr>
            </thead>
            <tbody>
            <span id="exchangeRate" hidden><fmt:message key="exchange" bundle="${lang}"/></span>
            <c:forEach items="${orders}" var="order">
                <tr class="firstRow">
                    <form action="${pageContext.request.contextPath}/register" method="post">
                        <td><input style="text-align: center; width: 60px" value="${order.userId}" name="userId"></td>
                        <td><input style="text-align: center; width: 90px" value="${order.orderId}" name="orderId"></td>
                        <td><input style="text-align: center; width: 80px" value="${order.carId}" name="carId"></td>
                            <%--  <td><input style="width: 120px" value="${order.passport}" name="passport"></td>--%>
                        <td style="text-align: center;width: 180px">
                            <input name="created" value="${order.created}" style="width: 150px" hidden>
                            <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${order.created}"/>
                            <br>
                            <input name="term" value="${order.term}" style="width: 150px" hidden>
                                <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${order.term}"/>
                        </td>
                        <td><input value="${order.damage}" name="damage"></td>

                        <c:if test="${empty order.reason}">
                            <td><input style="width: 300px" value=" " name="reason"></td>
                        </c:if>
                        <c:if test="${not empty order.reason}">
                            <td><input style="width: 300px"
                                       value="${order.reason}"
                                       name="reason"
                            ></td>
                        </c:if>
                        <td>
                                <input name="payment"
                                       class="orderPayment"
                                       type="number"
                                       min="0"
                                       style="width: 100px"
                                       value="${order.payment}"
                                >
                            <span class="badge"><fmt:message key="exchange.sign" bundle="${lang}"/></span>
                            <input id="language"
                                   name="language"
                                   value="${user.language}"
                                   hidden
                            />
                        </td>
                        <td style="text-align: center">
                            <div class="toggle">
                                <input class="${order.rejected}"
                                       type="checkbox"
                                       name="rejected"
                                >
                            </div>
                        </td>
                        <td style="text-align: center">
                            <div class="toggle">
                                <input name="closed"
                                       type="checkbox"
                                       class="${order.closed}"
                                >
                            </div>
                        </td>

                        <td style="text-align: center">
                            <button
                                    id="accept-button"
                                    onclick="spinner()"
                                    class="btn btn-outline-success"
                                    type="submit"
                            >
                                <i class="fa fa-check"></i>
                            </button>
                        </td>
<%--                        <td style="text-align: center">--%>
<%--                            <div--%>
<%--                                    name="invoice"--%>
<%--                                    value=""--%>
<%--                                    class="btn btn-outline-warning"--%>
<%--                                    onclick="addInvoice(`${order.orderId}`, `${order.userId}`, `${order.carId}`, `${order.damage}`, `${order.payment}`, `${order.reason}`, `${order.passport}`, `${order.phone}`)"--%>
<%--                            >--%>
<%--                                <i class="fa fa-credit-card"></i>--%>
<%--                            </div>--%>
<%--                        </td>--%>
                        <td style="text-align: center">
                            <button
                                    name="remove"
                                    value=""
                                    class="btn btn-outline-danger"
                                    onclick="remove(${order.orderId})">
                                <i class="fa fa-trash"></i>
                            </button>
                        </td>
                    </form>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div id="ui" hidden>
        <div class="dice">
            <ol class="dice_list">
                <li class="dice_list_item"></li>
                <li class="dice_list_item"></li>
                <li class="dice_list_item"></li>
                <li class="dice_list_item"></li>
                <li class="dice_list_item"></li>
                <li class="dice_list_item"></li>
            </ol>
        </div>
        <div class="dice">
            <ol class="dice_list">
                <li class="dice_list_item"></li>
                <li class="dice_list_item"></li>
                <li class="dice_list_item"></li>
                <li class="dice_list_item"></li>
                <li class="dice_list_item"></li>
                <li class="dice_list_item"></li>
            </ol>
        </div>
        <div class="dice">
            <ol class="dice_list">
                <li class="dice_list_item"></li>
                <li class="dice_list_item"></li>
                <li class="dice_list_item"></li>
                <li class="dice_list_item"></li>
                <li class="dice_list_item"></li>
                <li class="dice_list_item"></li>
            </ol>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/TagCloud@2.2.0/dist/TagCloud.min.js"></script>
</div>
<script>

        let exchangeRate = parseFloat(document.getElementById("exchangeRate").innerHTML).toFixed(2);

        let rates = document.getElementsByClassName("orderPayment");

        for (let i = 0; i < rates.length; i++) {
            rates[i].value = "" + (rates[i].value * exchangeRate).toFixed(0);
    }


let navigation = document.querySelector('.navigation');
    console.log(navigation);

    navigation.onclick = function () {
        navigation.classList.toggle('active')
    }

    document.querySelectorAll(".true").forEach(element => element.checked = true)

    function addInvoice(orderId, userId, carId, damage, payment, reason, passport, phone) {
        let url = '/order' + '?userId=' + userId + '&carId=' + carId + '&damage=' + damage + '&payment=' + payment + '&reason=' + reason + '&passport=' + passport + '&phone=' + phone;
        console.log(url);
        fetch(url, {
            method: 'PUT',
        }).then(response => {
            window.location.href = url;
            console.log('Ok:', response);
        }).catch(err => {
            console.error(err)
        })
    }

    function spinner() {
        let dice = document.getElementById("ui");
        dice.hidden = false;

        setTimeout(function () {
            dice.hidden = true;
        }, 2500);
    }

    function remove(id) {
        let dice = document.getElementById("ui");
        dice.hidden = false;

        let url = '/order' + '?orderId=' + id;
        // let url = '/cars' + '?id=' + id;
        console.log(url);
        if (confirm("Do you want to remove the order?")) {
            fetch(url, {
                method: 'DELETE',
            }).then(response => {
                console.log('Ok:', response);
                window.location.href = "/user";
            }).catch(err => {
                console.error(err)
            })
        }
        setTimeout(function () {
            dice.hidden = true;
        }, 5000);
    }

</script>
</body>
</html>
