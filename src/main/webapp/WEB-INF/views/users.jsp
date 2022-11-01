<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 10/9/2022
  Time: 3:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <title>Users</title>
    <style>
        @import url('https://fonts.googleapis.com/css?family=Kaushan+Script|Saira&display=swap');

        <%@include file="../classes/templates/css/users.css"%>
        <%@include file="../classes/templates/css/check-box.css"%>
    </style>
</head>
<title>Users</title>
</head>
<body>
<div class="container">
    <div class="col-lg-12">
        <h5>List of Jobs</h5>
    </div>

    <div class="col-lg-12">
        <table id="tabs" class="table cell-border" style="width:100%">
            <thead class="TableHead">
            <tr>
                <th>Name</th>
                <th>Email</th>
                <th>Role</th>
                <th>Status</th>
                <th>Confirm</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="user">
                <tr class="firstRow">
                    <form>
                        <td><input value="${user.name}" name="name"></td>
                        <td><input value="${user.email}" name="email"></td>
                        <td><input value="${user.role}" name="role"></td>
                        <td>
                            <div class="toggle">
                                <input class="${user.active}" type="checkbox" name="active">
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

<link
        rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
        integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
        crossorigin="anonymous"
/>
<script>
    document.querySelectorAll(".true").forEach(element => element.checked = true)

    function editCar(id) {
        let url = '/user' + '?id=' + id;
        console.log(url);
        fetch(url, {
            method: 'GET',
        }).then(response => {
            console.log('Ok:', response);
        }).catch(err => {
            console.error(err)
        })
    }
</script>
</body>
</html>
