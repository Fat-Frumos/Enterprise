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
                <th>order#</th>
                <th>user#</th>
                <th>car#</th>
                <th>payment</th>
                <th>passport</th>
                <th>term</th>
                <th>created</th>
                <th>damage</th>
                <th>closed</th>
                <th>rejected</th>
                <th>Accept</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${orders}" var="order">
                <tr class="firstRow">
                    <form>
                        <td><input style="width: 80px" value="${order.orderId}" name="orderId"></td>
                        <td><input style="width: 80px" value="${order.userId}" name="userId"></td>
                        <td><input style="width: 60px" value="${order.carId}" name="cardId"></td>
                        <td><input style="width: 100px" value="${order.payment}" name="payment"></td>
                        <td><input style="width: 120px" value="${order.passport}" name="passport"></td>
                        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${order.term}"/></td>
                        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${order.created}"/></td>
                        <td><input style="width: 320px" value="${order.damage}" name="damage"></td>
                        <td>
                            <div class="toggle">
                                <input style="width: 80px" value="${order.closed}" type="checkbox" name="closed">
                            </div>
                        </td>

                        <td>
                            <div class="toggle">
                                <input style="width: 80px" class="${order.rejected}" type="checkbox" name="rejected">
                            </div>
                        </td>
                        <td>
                            <button id="accept-button" class="btn btn-outline-success" type="submit">&#10003;</button>
                        </td>
                    </form>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</section>
</body>
</html>
</body>
</html>
