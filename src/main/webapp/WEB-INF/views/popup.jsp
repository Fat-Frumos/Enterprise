<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 8/28/2022
  Time: 8:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<title>Car</title>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Car</title>
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
<div class="modal fade" id="myPopup">
    <div class="modal-dialog wrap">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"></button>
            </div>
            <form class="popup" id="post" action="/cars" method="post">
                <div class="col-12 p-5">
                    <figure>
                        <img class="img-fluid"
                             id="main"
                             src="https://raw.githubusercontent.com/Fat-Frumos/Cars/master/main.jpg"
                             alt="main">
                        <figcaption></figcaption>
                    </figure>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>