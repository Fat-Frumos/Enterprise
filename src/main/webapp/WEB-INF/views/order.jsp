<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 10/24/2022
  Time: 1:57 PM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Orders Review</title>
    <meta name="description" content="contact form using bootstrap,">
    <meta name="keywords" content="HTML,CSS,XML,JavaScript,bootstrap">
    <meta name="author" content="Pasha">
    <title>Responsive Contact Form</title>

    <style>

        @import url('https://fonts.googleapis.com/css?family=Kaushan+Script|Saira&display=swap');

        <%@include file="../classes/templates/css/users.css"%>
        <%@include file="../classes/templates/css/check-box.css"%>

        body {
            font-family: 'Saira', sans-serif;
        }

        input {
            border: none;
        }

        th {
            text-align: left;
        }

    </style>
</head>
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
                <th>#car</th>
                <th>payment</th>
                <th>passport</th>
                <th>term</th>
                <th>created</th>
                <th>damage</th>
                <th>closed</th>
                <th>rejected</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${orders}" var="order">
                <tr class="firstRow">
                    <td><input>${order.orderId}</input></td>
                    <td><input>${order.carId}</input></td>
                    <td><input>${order.payment}</input></td>
                    <td><input>${order.passport}</input></td>
                    <td><input>${order.term}<fmt:formatDate pattern="yyyy-MM-dd" value="${order.term}"/></input></td>
                    <td><input>${order.created}<fmt:formatDate pattern="yyyy-MM-dd" value="${order.created}"/></input></td>
                    <td><input>${order.damage}</input></td>
                    <td><input type="checkbox" class="${user.closed}">${order.closed}</input></td>
                    <td><input type="checkbox" class="${order.rejected}">${order.rejected}</input></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</section>
</body>
</html>