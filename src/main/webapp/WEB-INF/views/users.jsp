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
    <style>
        <%@include file="../classes/templates/css/users.css"%>
    </style>
</head>

<%--<script--%>
<%--        src="https://code.jquery.com/jquery-3.2.1.slim.min.js"--%>
<%--        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"--%>
<%--        crossorigin="anonymous"--%>
<%--></script>--%>
<%--<script--%>
<%--        src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"--%>
<%--        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"--%>
<%--        crossorigin="anonymous"--%>
<%--></script>--%>

<title>Users</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-lg-12">
            <div class="row mt-5">
                <div class="col-lg-12">
                    <h5>List of Users</h5>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <table id="tabs" class="table cell-border " style="width:100%">
                        <thead class="TableHead">
                        <tr>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Role</th>
                            <th>Status</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${users}" var="user">
                            <tr class="firstRow">
                                <td>${user.name}</td>
                                <td>${user.email}</td>
                                <td>${user.role}</td>
                                <td>
                                    <div class="toggle">
                                        <input type="checkbox">
                                        <label class="onButton">${user.active}</label>
                                        <label class="onButton">${user.active}</label>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<%--<script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>--%>
<%--<script src="https://cdn.datatables.net/1.12.1/js/dataTables.bootstrap4.min.js"></script>--%>

<link
        rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
        integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
        crossorigin="anonymous"
/>
<script>

    $(document).ready(function () {
        $('#example').DataTable({
            "bPaginate": false,
            "bLengthChange": false,
            "bFilter": false,
            "bInfo": false,
            "bAutoWidth": false
        });
    });

    function addCar(id) {
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
