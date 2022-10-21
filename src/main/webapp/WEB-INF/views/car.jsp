<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 8/24/2022
  Time: 6:36 PM
  To change this template use File | Settings | File Templates.
--%>

<!DOCTYPE html>
<head>
    <title>Rental Car</title>
    <style>
        <%@include file="../classes/templates/css/form.css"%>
    </style>
</head>

<body>
<div class="col-lg-5 p-0 ps-lg-4">
    <div style="background: white" class="row m-0">
        <div class="col-12 px-4">
            <div class="d-flex align-items-end mt-4 mb-2">
                <p class="h4 m-0"><span class="pe-1">${auto.brand} ${auto.name}</span>
            </div>
            <div class="d-flex justify-content-between mb-2">
                <p class="muted">Total</p>
                <p class="fs-14 fw-bold"><span class="fas fa-dollar-sign pe-1"></span>1,450</p>
            </div>
            <div class="d-flex justify-content-between mb-2">
                <p class="muted">With Driver</p>
                <p class="fs-14 fw-bold">Free</p>
            </div>
            <div class="d-flex justify-content-between mb-3">
                <p class="muted fw-bold">Total</p>
                <div class="d-flex align-text-top ">
                    <span class="fas fa-dollar-sign mt-1 pe-1 fs-14 "></span><span class="h4">${auto.price}</span>
                </div>
            </div>
        </div>
        <div class="col-12 px-0">
            <div class="row bg-light m-0">
                <div class="col-12 px-4">
                    <div class="d-flex mb-4">
                        <span class="me-5">
                                    <span class="text-muted">Card</span>
                                    <input class="form-control" style="width: 180px" type="text" value="4485 6888 2359 1498"
                                           placeholder="1234 5678 9012 3456">
                                    </span>
                        <div class=" w-100 d-flex flex-column align-items-end">
                            <p class="text-muted">Expires</p>
                            <input class="form-control2" style="width: 80px" type="text" value="22/02/2022" placeholder="MM/YYYY">
                        </div>

                    </div>
                    <div class="d-flex mb-5">
                                    <span class="me-5">
                                        <span class="text-muted">Name</span>
                                        <input class="form-control" type="text" value="Paul J.Smith" placeholder="Name" autocomplete="on">
                                    </span>
                        <div class="w-100 d-flex flex-column align-items-end">
                            <span class="text-muted">CVC</span>
                            <input class="form-control3" type="text" value="123" placeholder="XXX">
                        </div>
                    </div>
                </div>
            </div>
            <div class="row m-0">
                <div class="col-12 mb-4 p-0">
                    <input type="submit" id="purchase" name="brand" value="Purchase" class="btn btn-primary">
                    <span class="fas fa-arrow-right ps-2"></span>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
