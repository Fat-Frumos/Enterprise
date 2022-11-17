<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ct" uri="/WEB-INF/dataTag" %>
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
    <link rel="shortcut icon" href="https://raw.githubusercontent.com/Fat-Frumos/Cars/master/wheel.ico"
          type="image/x-icon">
    <title>Responsive Order table</title>

    <style>

        @import url('https://fonts.googleapis.com/css?family=Kaushan+Script|Saira&display=swap');

        <%@include file="../classes/templates/css/users.css"%>
        <%@include file="../classes/templates/css/check-box.css"%>

        body {
            font-family: 'Saira', sans-serif;
            margin-left: 10px;
            margin-top: 10px;
        }

        input {
            border: none;
        }

        th {
            text-align: left;
        }

        tr {
            padding: 2px;
        }

        tr:nth-child(even) {
            background-color: rgba(127, 127, 127, .1);
        }

    </style>
</head>
<body>
<div class="container">
    <div class="col-lg-12">
        <jsp:include page="nav.jsp"/>

        <h3 style="text-align: center">List of Orders</h3>
    </div>
    <div class="col-lg-12">
        <table id="tabs" class="table-striped" style="width:100%">
            <thead class="TableHead">
            <tr>
                <th>#order</th>
                <th>#car</th>
                <th>created</th>
                <th>term</th>
                <th>payment</th>
                <th>damage</th>
                <th>reason</th>
                <th>rejected</th>
                <th>closed</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${orders}" var="order">
                <tr class="firstRow">
                    <td><p style="width: 85px">${order.orderId}</p></td>
                    <td><p>${order.carId}</p></td>
                    <td><p style="width: 90px"><fmt:formatDate pattern="yyyy-MM-dd" value="${order.created}"/></p></td>
                    <td><p style="width: 90px"><fmt:formatDate pattern="yyyy-MM-dd" value="${order.term}"/></p></td>
                    <td><p>${order.payment}</p></td>
                    <td><p style="width: 180px">${order.damage}</p></td>
                    <td><p style="width: 280px">${order.reason}</p></td>
                        <%--                    <td><p style="width: 120px">${order.passport}</p></td>--%>
                    <td><p style="width: 30px" type="checkbox" class="${order.rejected}">${order.rejected}</p></td>
                    <td><p style="width: 30px" type="checkbox" class="${order.closed}">${order.closed}</p></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <h5 style="left: 90vw; top: 0; position: absolute"><ct:today format="dd MMMM yyyy"/></h5>
</div>
</section>
</body>
</html>