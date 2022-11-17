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

        .loader {
            color: black;
            position: absolute;
            top: calc(50% - 32px);
            left: calc(50% - 32px);
            width: 64px;
            height: 64px;
            border-radius: 50%;
            perspective: 800px;
            animation: rotate 5s linear infinite;
        }

        .inner {

            position: absolute;
            box-sizing: border-box;
            width: 100%;
            height: 100%;
            border-radius: 50%;

        }

        .inner.one {
            left: 0%;
            top: 0%;
            animation: rotate-one 1s linear infinite;
            border-bottom: 3px solid black;
        }

        .inner.two {
            right: 0%;
            top: 0%;
            animation: rotate-two 1s linear infinite;
            border-right: 3px solid black;
        }

        .inner.three {
            right: 0%;
            bottom: 0%;
            animation: rotate-three 1s linear infinite;
            border-top: 3px solid black;
        }

        .nucleus {
            border-radius: 50%;
            width: 15px;
            height: 15px;
            background: black;
            top: calc(100% - 40px);
            left: calc(100% - 40px);
            position: absolute;
        }

        @keyframes rotate {
            0% {
                transform: rotateZ(0deg);
            }
            100% {
                transform: rotateZ(360deg);
            }
        }

        @keyframes rotate-one {
            0% {
                transform: rotateX(35deg) rotateY(-45deg) rotateZ(0deg);
            }
            100% {
                transform: rotateX(35deg) rotateY(-45deg) rotateZ(360deg);
            }
        }

        @keyframes rotate-two {
            0% {
                transform: rotateX(50deg) rotateY(10deg) rotateZ(0deg);
            }
            100% {
                transform: rotateX(50deg) rotateY(10deg) rotateZ(360deg);
            }
        }

        @keyframes rotate-three {
            0% {
                transform: rotateX(35deg) rotateY(55deg) rotateZ(0deg);
            }
            100% {
                transform: rotateX(35deg) rotateY(55deg) rotateZ(360deg);
            }
        }

    </style>
</head>
<title>Users</title>
</head>
<body>
<jsp:include page="nav.jsp"/>

<div class="container">
    <div class="col-lg-12">
        <h5 style="text-align: center">List of Users</h5>
    </div>

    <div class="col-lg-12">
        <table id="tabs" class="table cell-border" style="width:100%">
            <thead class="TableHead">
            <tr>
                <th>Name</th>
                <th>Email</th>
                <th>Role</th>
                <th>Enable</th>
                <th>Closed</th>
                <th>Confirm</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="user">
                <tr class="firstRow">
                    <form action="${pageContext.request.contextPath}/register" method="get">
                        <td><input value="${user.name}" name="name"></td>
                        <td><input value="${user.email}" name="email"></td>
                        <td><input value="${user.role}" name="role"></td>
                        <td>
                            <div class="toggle">
                                <input class="${user.active}" type="checkbox" name="active">
                            </div>
                        </td>
                        <td>
                            <div class="toggle">
                                <input class="${user.closed}" type="checkbox" name="closed">
                            </div>
                        </td>
                        <td>
                            <button id="accept-button" onclick="spinner()" class="btn btn-outline-success" type="submit">&#10003;
                            </button>
                        </td>
                    </form>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<div id="nucleus" class="loader" hidden>
    <div class="inner one"></div>
    <div class="inner two"></div>
    <div class="inner three"></div>
    <div class="nucleus"></div>
</div>
<%--<div class="loading">--%>
<%--    <div class="outer">--%>
<%--        <span></span>--%>
<%--        <span></span>--%>
<%--        <span></span>--%>
<%--    </div>--%>
<%--    <div class="inner">--%>
<%--        <span></span>--%>
<%--        <span></span>--%>
<%--        <span></span>--%>
<%--    </div>--%>
<%--</div>--%>
<h5 style="left: 90vw; top: 0; position: absolute"><ct:today format="MMMM dd yyyy"/></h5>
<link
        rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
        integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
        crossorigin="anonymous"
/>
<script>
    function spinner(){
        document.getElementById("nucleus").hidden = false;
    }

    document.querySelectorAll(".true").forEach(element => element.checked = true)

</script>
</body>
</html>
