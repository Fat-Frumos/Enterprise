<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 8/27/2022
  Time: 12:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <style>
        <%@include file="../classes/templates/css/modal.css"%>
    </style>
</head>
<body>
<div class="modal fade" id="Mymodal">
    <div class="modal-dialog wrap">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"></button>
            </div>
            <form class="popup" id="MyForm" action="/" method="post">
                <label>Enter Name</label>
                <input type="text" name="name" placeholder="Enter your name"/><br><label>Enter Password</label>
                <input type="password" name="password" placeholder="Enter your password"/><br>
                <input type="number" name="price" value="50000.0"/>
                <input type="hidden" name="path"
                       value="https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-21.jpg"/>
                <input type="hidden" name="brand" value="Jeep"/><br>
                <input type="hidden" name="model" value="Hammer"/><br>
                <input type="hidden" name="year" value="2020"/><br>
                <input type="submit" class="btn btn-default" name="submit" value="Submit"/>
            </form>
        </div>
    </div>
</div>
</body>
</html>