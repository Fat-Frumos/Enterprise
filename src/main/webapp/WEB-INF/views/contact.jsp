<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        input{
            border: none;
        }
        th {
            text-align: left;
        }

    </style>
</head>
<body>
<html>
<heade>
    <meta name="description" content="contact form using bootstrap,">
    <meta name="keywords" content="HTML,CSS,XML,JavaScript,bootstrap">
    <meta name="author" content="Pasha">
    <title>Responsive Contact Form</title>
</heade>

<body>
<div class="container">
    <div class="col-lg-12">
        <h5>List of Jobs</h5>
    </div>

    <div class="col-lg-12">
        <table id="tabs" class="table" style="width:100%">
            <thead class="TableHead">
            <tr>
                <th>payment</th>
                <th>passport</th>
                <th>damage</th>
                <th>driver</th>
                <th>closed</th>
                <th>rejected</th>
                <th>Accept</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${orders}" var="order">
                <tr class="firstRow">
                    <form>
                        <td><input value="${order.payment}" name="payment"></td>
                        <td><input value="${order.passport}" name="passport"></td>
                        <td><input value="${order.damage}" name="damage"></td>
                        <td><input value="${order.driver}" name="driver"></td>
                        <td><input value="${order.closed}" name="closed"></td>
                        <td>
                            <div class="toggle">
                                <input class="${order.rejected}" type="checkbox" name="rejected">
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
