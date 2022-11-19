<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 10/9/2022
  Time: 3:40 PM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<head>
    <title>Users</title>
    <meta charset="UTF-8">
    <meta name="author" content="Pasha">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv=Content-Type content="text/html; charset=windows-1251">
    <link rel="shortcut icon" href="<c:url value="/upload?wheel.ico"/>" type="image/x-icon">

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

    </style>
</head>
<body>
<div class="wrapper">
    <jsp:include page="nav.jsp"/>
    <div class="col-lg-12">
        <h5 style="text-align: center">List of Orders</h5>
    </div>

    <div class="col-lg-12">
        <table id="tabs" class="table cell-border" style="width:100%">
            <thead class="TableHead">
            <tr>
                <th>User#</th>
                <th>Order#</th>
                <th>Car#</th>
                <%--<th>passport</th>--%>
                <th>Created/Term</th>
                <th>Damage</th>
                <th>Reason</th>
                <th>Payment</th>
                <th>Rejected</th>
                <th>Closed</th>
                <th>Confirm</th>
                <th>Remove</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${orders}" var="order">
                <tr class="firstRow">
                    <form action="${pageContext.request.contextPath}/register" method="post">
                        <td><input style="width: 60px" value="${order.userId}" name="userId"></td>
                        <td><input style="width: 90px" value="${order.orderId}" name="orderId"></td>
                        <td><input style="width: 80px" value="${order.carId}" name="carId"></td>
                            <%--                        <td><input style="width: 120px" value="${order.passport}" name="passport"></td>--%>
                        <td style="width: 225px">
                            <input name="created" value="${order.created}" style="width: 150px" hidden>
                            <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${order.created}"/>
                            <br>
                            <input name="term" value="${order.term}" style="width: 150px" hidden>
                                <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${order.term}"/></input>
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
                                   type="number"
                                   min="0"
                                   pattern="0.00"
                                   style="width: 100px"
                                   value="${order.payment}"
                            >
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
                    </form>
                    <td style="text-align: center">
                        <button
                                name="remove"
                                value=""
                                class="btn btn-outline-danger">
                            onclick="remove(${order.orderId})"
                            <i class="fa fa-trash"></i>
                        </button>
                    </td>
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
    <!-- partial:index.partial.html -->
    <span class="content">
  <!-- <h1 contenteditable data-text="Grow">Grow</h1> -->
</span>

    <script src="https://cdn.jsdelivr.net/npm/TagCloud@2.2.0/dist/TagCloud.min.js"></script>
</div>
<script>
    document.querySelectorAll(".true").forEach(element => element.checked = true)

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

        // setTimeout(function () {
        //     dice.hidden = false;
        // }, 1000);

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
