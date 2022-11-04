<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 10/24/2022
  Time: 1:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders Review</title>
    <style>

        @import url('https://fonts.googleapis.com/css?family=Kaushan+Script|Saira&display=swap');

        <%@include file="../classes/templates/css/users.css"%>
        <%@include file="../classes/templates/css/check-box.css"%>

        body {
            font-family: 'Saira', sans-serif;
        }

        a {
            text-decoration: none;
        }

        input {
            border: none;
        }

        th, tr {
            text-transform: capitalize;
            font-family: 'Saira', sans-serif;
            text-align: center;
        }

    </style>
</head>
<body>
<html>
<heade>
    <meta name="description" content="contact form using bootstrap,">
    <meta name="keywords" content="HTML,CSS,XML,JavaScript,bootstrap">
    <meta name="author" content="Pasha">
    <title>Contact Form</title>
</heade>

<body>
<div class="container">
    <jsp:include page="nav.jsp"/>
    <div class="col-lg-12">
        <h3 style="text-align: center">List of Orders</h3>
    </div>

    <div class="col-lg-12">
        <table id="tabs" class="table" style="width:100%">
            <thead class="TableHead">
            <tr>
                <th>#order</th>
                <th>#user</th>
                <th>#car</th>
                <th>payment</th>
                <th>passport</th>
                <th>term</th>
                <th>created</th>
                <th>damage</th>
                <th>closed</th>
                <th>driver</th>
                <th>rejected</th>
                <th>Accept</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${orders}" var="order">
                <tr class="firstRow">
                    <form action="/register" method="post">
                        <td><input style="width: 80px" value="${order.orderId}" name="orderId"></td>
                        <td><input style="width: 80px" value="${order.userId}" name="userId"></td>
                        <td><input style="width: 60px" value="${order.carId}" name="carId"></td>
                        <td><input style="width: 100px" value="${order.payment}" name="payment"></td>
                        <td><input style="width: 120px" value="${order.passport}" name="passport"></td>
                        <td><input name="term" value="${order.term}" style="width: 100px" hidden>
                            <fmt:formatDate pattern="yyyy-MM-dd" value="${order.term}"/></input></td>
                        <td><input name="created" value="${order.created}" style="width: 100px" hidden>
                            <fmt:formatDate pattern="yyyy-MM-dd" value="${order.created}"/></td>
                        <td><input style="width: 320px" value="${order.damage}" name="damage"></td>
                        <td>

                            <div class="toggle">
                                <input class="${order.closed}" id="checkbox" type="checkbox" name="closed">
                                <label class="onButton" value="${order.closed}"></label>
                            </div>
                        <td>
                            <div class="toggle">
                                <input class="${order.driver}" value="${order.driver}" type="checkbox" name="driver">
                            </div>
                        </td>
                            <%--                            <div class="toggle">--%>
                            <%--                                <input style="width: 80px"--%>
                            <%--                                       class="${order.closed}"--%>
                            <%--                                       value=""--%>
                            <%--                                       id="closed"--%>
                            <%--                                       type="checkbox"--%>
                            <%--                                       name="closed">--%>
                            <%--                            </div>--%>
                        </td>
<%--                        <td><input type="checkbox" class="${user.closed}">${order.closed}</input></td>--%>
<%--                        <td><input type="checkbox" class="${order.rejected}">${order.rejected}</input></td>--%>
                        <td>
                            <div class="toggle">
                                <input class="${order.rejected}" value="${order.rejected}" id="rejected" type="checkbox"
                                       name="rejected">
                                <label class="onButton"></label>
                                    <%--                                <input class="${order.rejected}" style="width: 80px" value="${order.rejected}"--%>

                            </div>
                        </td>
                        <td>
                            <button
                                    id="accept-button"
                                    class="btn btn-outline-success"
<%--                                    onclick="update(${order.orderId}, ${order.userId}, ${order.damage}, ${order.rejected},${order.term}, ${order.payment})"--%>
                                    type="submit"
                            >&#10003;
                            </button>
                        </td>
                    </form>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script>
    document.querySelectorAll(".true").forEach(element => element.checked = true)
    let checkboxes = document.querySelectorAll('checkbox');
    let rejected = document.getElementById('rejected');

    checkboxes.forEach(checkbox => {
        checkbox.onchange = checkbox = (element) => {
            console.log("element" + element.target.checked);
            if (element.target.checked) {
                checkbox.value = "1";
            } else {
                checkbox.value = "0";
            }
        }
    })

    rejected.onchange = rejected = (element) => {
        console.log("rejected " + element.target.checked);
        if (element.target.checked) {
            rejected.value = "1";
        } else {
            rejected.value = "0";
        }
    }

    // let checkbox = document.getElementById('checkbox');
    //
    // checkbox.onchange = checkbox = (element) => {
    //     console.log("Closed " + element.target.checked);
    //     if (element.target.checked) {
    //         checkbox.value = "1";
    //     } else {
    //         checkbox.value = "0";
    //     }
    // }

    // function update(id, userId, damage, rejected, term, payment) {
    //
    //     let url = '/register' + '?orderId=' + id + '?userId=' + userId + '?damage=' + damage + '?rejected=' + rejected + '?closed=' + checkbox.value + '?term=' + term + '?payment=' + payment;
    //
    //     alert(url);
    //     fetch(url, {
    //         method: 'POST',
    //     }).then(response => {
    //         console.log('Ok:', response);
    //         window.location.href = "/contract.jsp";
    //     }).catch(err => {
    //         alert(err);
    //         // console.error(err)
    //     })
    // }

    // function popUp(id, userId, damage, rejected, term, payment) {
    //     let url = '/order' + '?orderId=' + id + '?userId=' + userId + '?damage=' + damage + '?rejected=' + rejected + '?term=' + term + '?payment=' + payment;
    //     fetch(url, {
    //         method: 'PUT',
    //     }).then(response => {
    //         console.log('Ok:', response);
    //         window.location.href = url;
    //     }).catch(err => {
    //         alert(url);
    //         console.error(err)
    //     })
    // }
</script>
</body>
</html>
