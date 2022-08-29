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
        input {
            margin-bottom: 5px;
        }

        .wrap {
            width: 20vw;
        }

        .popup {
            margin: 10px 10px;
            background: #fff;
            border-radius: 5px;
            width: 90%;
            position: relative;
            transition: all 5s ease-in-out;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
        }
    </style>
</head>
<body>
<div class="modal fade" id="Mymodal">
    <div class="modal-dialog wrap">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"></button>
            </div>
            <form class="popup" id="MyForm" action="/cars" method="post">
                <label>Enter Name</label>
                <input type="text" name="name" placeholder="Enter your name"/><br><label>Enter Password</label>
                <input type="password" name="password" placeholder="Enter your password"/><br>
                <input type="hidden" name="path" value="https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-21.jpg"/>
                <input type="number" name="path" value="50000.0"/>
                <input type="submit" class="btn btn-default" name="submit" value="Submit"/>
            </form>
        </div>
    </div>
</div>
</body>
</html>