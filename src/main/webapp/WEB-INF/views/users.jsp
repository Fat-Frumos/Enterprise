<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 10/9/2022
  Time: 3:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<div>
    <div class="users">
        <jsp:include page="nav.jsp"/>
        <jsp:include page="popup.jsp"/>
        <div class="user" id="ads">
            <c:forEach items="${users}" var="users">
                <div class="col-md-4">
                    <div class="user rounded">
                        <div class="user-image fw-900" onclick="flip(${users.path})">
                            <span class="user-detail-badge m-2">${users.brand} ${users.name} ${users.model}</span>
                            <figure onclick="addCar(${users.id})">
                                <img class="img-fluid"
                                     src="${users.path}"
                                     alt="url">
                                <figcaption></figcaption>
                            </figure>
                        </div>
                        <div class="user-image-overlay m-auto">
                            <span class="badge">Used</span>
                            <span class="user-detail-badge">$${users.price}</span>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>
