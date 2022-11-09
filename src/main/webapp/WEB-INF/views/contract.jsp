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
    <style>
        @import url('https://fonts.googleapis.com/css?family=Kaushan+Script|Saira&display=swap');

        <%@include file="../classes/templates/css/users.css"%>
        <%@include file="../classes/templates/css/check-box.css"%>
        .wrapper {
            margin: 5px;
            width: 100%;
        }
    </style>

</head>
<title>Users</title>
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
<%--                <th>passport</th>--%>
                <th>Created/Term</th>
<%--                <th>Created</th>--%>
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
                        <td style="width: 225px"><input name="term" value="${order.term}" style="width: 150px" hidden>
                            <fmt:formatDate pattern="yyyy-MM-dd" value="${order.term}"/></input>
                            /
                            <input name="created" value="${order.created}" style="width: 150px" hidden>
                            <fmt:formatDate pattern="yyyy-MM-dd" value="${order.created}"/></td>
                        <td><input value="${order.damage}" name="damage"></td>

                        <c:if test="${empty order.reason}">
                            <td><input style="width: 300px" value=" " name="reason"></td>
                        </c:if>
                        <c:if test="${not empty order.reason}">
                            <td><input style="width: 300px" value="${order.reason}" name="reason"></td>
                        </c:if>
                        <td><input
                                style="width: 100px"
                                value="${order.payment}"
                                type="number"
                                min="0"
                                pattern="0.00"
                                name="payment"
                            >
                        </td>
                        <td>
                            <div class="toggle">
                                <input class="${order.rejected}" type="checkbox" name="rejected">
                            </div>
                        </td>
                        <td>
                            <div class="toggle">
                                <input class="${order.closed}" type="checkbox" name="closed">
                            </div>
                        </td>
                        <td>
                            <button
                                    id="accept-button" class="btn btn-outline-success" type="submit">&#10003;
                            </button>
                        </td>
                        <td>
                            <a
                                    id="rm-button" onclick="remove(${order.orderId})" class="btn btn-outline-danger">&#10003;
                            </a>
                        </td>
                    </form>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<link
        rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
        integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
        crossorigin="anonymous"
/>
<script>
    function remove(id){
        let url = '/order' + '?orderId=' + id;
        // let url = '/cars' + '?id=' + id;
        console.log(url);
        fetch(url, {
            method: 'DELETE',
        }).then(response => {
            console.log('Ok:', response);
            window.location.href = url;
        }).catch(err => {
            console.error(err)
        })
    }

    document.querySelectorAll(".true").forEach(element => element.checked = true)
</script>
</body>
</html>
