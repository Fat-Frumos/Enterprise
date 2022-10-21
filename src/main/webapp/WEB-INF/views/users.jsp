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
        .TableHead {
            box-sizing: border-box;
            width: 1302px;
            height: 50px;
            background: #F9F9F9;
            border: 1px solid #DDE0E2;
            border-style: solid !important;
            border-width: thin !important;
        }

        th {
            border-top: 1px solid #dddddd;
            border-bottom: 1px solid #dddddd;
            border-right: 1px solid #dddddd;
        }

        th:first-child {
            border-left: 1px solid #dddddd;
        }

        tbody:before {
            content: "@";
            display: block;
            line-height: 10px;
            text-indent: -99999px;
        }

        .firstRow {
            box-sizing: border-box;
            /* position: absolute; */
            width: 1302.49px;
            height: 55px;
            left: 309.54px;
            top: 404.06px;
            background: #FFFFFF;
            border-width: 1px 1px 1px 1px !important;
            border-style: solid !important;
            border-color: #DDE0E2 !important;
        }

        /* Button Style */
        h1 {
            font-size: 42px;
            color: #2c3e50;
        }

        input[type="checkbox"] {
            position: relative;
            width: 80px;
            height: 30px;
            -webkit-appearance: none;
            border-radius: 20px;
            outline: none;
            transition: .4s;
            box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
            cursor: pointer;
        }

        input:checked[type="checkbox"] {
            background: green;
        }

        input[type="checkbox"]::before {
            z-index: 2;
            position: absolute;
            content: "";
            left: 0;
            width: 30px;
            height: 30px;
            background: #8E9AA0;
            border-radius: 50%;
            transform: scale(1.1);
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
            transition: .4s;
        }

        input:checked[type="checkbox"]::before {
            left: 50px;
            background: #FFFFFF;
        }

        .toggle {
            position: relative;
            display: inline;
        }

        label {
            position: absolute;
            color: #fff;
            font-weight: 600;
            pointer-events: none;
        }

        .onbtn {
            bottom: 0px;
            left: 11px;
        }

        .ofbtn {
            bottom: 0px;
            right: 8px;
            color: #8E9AA0;
        }
    </style>
</head>
<link
        rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
        integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
        crossorigin="anonymous"
/>
<script
        src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"
></script>
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
                                        <label class="onbtn">${user.active}</label>
                                        <label class="ofbtn">${user.active}</label>
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
<script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.12.1/js/dataTables.bootstrap4.min.js"></script>

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
