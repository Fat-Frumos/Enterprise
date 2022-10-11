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
                <p class="h4 m-0"><span class="pe-1">Bugatti</span><span class="pe-1">Chiron</span>
                <P class="ps-3 muted">1L</P>
            </div>
            <div class="d-flex justify-content-between mb-2">
                <p class="muted">Qty</p>
                <p class="fs-14 fw-bold">1</p>
            </div>
            <div class="d-flex justify-content-between mb-2">
                <p class="muted">Subtotal</p>
                <p class="fs-14 fw-bold"><span class="fas fa-dollar-sign pe-1"></span>1,450</p>
            </div>
            <div class="d-flex justify-content-between mb-2">
                <p class="muted">Shipping</p>
                <p class="fs-14 fw-bold">Free</p>
            </div>
            <div class="d-flex justify-content-between mb-2">
                <p class="muted">Promo code</p>
                <p class="fs-14 fw-bold">-<span class="fas fa-dollar-sign px-1"></span>100</p>
            </div>
            <div class="d-flex justify-content-between mb-3">
                <p class="muted fw-bold">Total</p>
                <div class="d-flex align-text-top ">
                    <span class="fas fa-dollar-sign mt-1 pe-1 fs-14 "></span><span class="h4">1,350</span>
                </div>
            </div>
        </div>
        <div class="col-12 px-0">
            <div class="row bg-light m-0">
                <div class="col-12 px-4 my-4">
                    <p class="fw-bold">Payment detail</p>
                </div>
                <div class="col-12 px-4">
                    <div class="d-flex  mb-4">
                                    <span class="">
                                        <span class="text-muted">Card number</span>
                                        <input class="form-control" type="text" value="4485 6888 2359 1498"
                                               placeholder="1234 5678 9012 3456">
                                    </span>
                        <div class=" w-100 d-flex flex-column align-items-end">
                            <p class="text-muted">Expires</p>
                            <input class="form-control2" type="text" value="02/2022" placeholder="MM/YYYY">
                        </div>
                    </div>
                    <div class="d-flex mb-5">
                                    <span class="me-5">
                                        <span class="text-muted">Cardholder name</span>
                                        <input class="form-control" type="text" value="David J.Frias"
                                               placeholder="Name">
                                    </span>
                        <div class="w-100 d-flex flex-column align-items-end">
                            <span class="text-muted">CVC</span>
                            <input class="form-control3" type="text" value="630" placeholder="XXX">
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
