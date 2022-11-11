<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" uri="/WEB-INF/dataTag" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 8/24/2022
  Time: 6:36 PM
  To change this template use File | Settings | File Templates.
--%>
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">

<style>
    <%@include file="../classes/templates/css/form.css"%>
    <%@include file="../classes/templates/css/check-box.css"%>
    <%@include file="../classes/templates/css/rental.css"%>
</style>
<div class="col-lg-5 p-0 ps-lg-4">
    <form action="${pageContext.request.contextPath}/order" method="post">
        <div style="background: white" class="row m-0">
            <div class="col-12 px-0">
                <div class="row bg-light m-0">
                    <div class="col-12 px-4 mb-5">
                        <div class="d-flex mb-4">
                        <span class="me-5">
                            <input id="carId" name="carId" value="${auto.id}" hidden>
                            <input id="userId" name="userId" value="${user.userId}" hidden>
                            <br>
                                    <span class="text-muted">Passport</span>
                                    <input class="form-control"
                                           style="width: 180px"
                                           name="passport" type="text"
                                           value=""
                                           placeholder="AA 123 456 789"
                                           pattern="[a-zA-Z]{2}?[- ][0-9]{3}?[- ][0-9]{3}?[- ][0-9]{3}"
                                           required
                                    >
                                    </span>
                            <div class=" w-100 d-flex flex-column align-items-end">
                                <br>
                                <p class="text-muted">Term</p>
                                <input id="term"
                                       class="form-control2"
                                       style="width: 110px"
                                       type="date"
                                       name="term"
                                       pattern="yyyy-mm-dd"
                                       value="${order.term}" min=""
                                       max="2025-02-25">
                            </div>
                        </div>

                        <div class="d-flex mb-4">
                        <span class="me-5">
                                    <span class="text-muted">Phone</span>
                                    <input class="form-control"
                                           style="width: 180px"
                                           type="tel"
                                           name="phone"
                                           value=""
                                           pattern="\+\d{12}"
                                           placeholder="+380987654321"
                                           required
                                    >
                                    </span>
                            <div class=" w-100 d-flex flex-column align-items-end">
                                <span class="text-muted">Driver</span>
                                <div class="w-100 d-flex flex-column align-items-end">
                                    <div class="toggle">
                                        <input id="checkbox" type="checkbox" name="driver">
                                        <label class="onButton" value=""></label>
                                    </div>
                                </div>
                                <span class="me-5">
                                </span>
                            </div>
                        </div>
                        <jsp:include page="picker.jsp"/>
                        <div class="d-flex mb-4">
                        <span class="me-5">
                                    <span class="text-muted">Payment</span>
                            <input id="price" name="price" value="${auto.price}" style="width: 100px" hidden>

                                    <input class="form-control"
                                           style="width: 100px"
                                           id="payment"
                                           name="payment"
                                           type="number"
                                           min="0"
                                           pattern="0.00"
                                           step=".01"
                                           readonly="readonly"
                                           value="${auto.price}"/>

                                    </span>
                            <div class=" w-100 d-flex flex-column align-items-end">
                                <p class="text-muted"></p>
                            </div>
                        </div>
                        <div class="d-flex mb-4">
                            <span class="me-5">
                            </span>
                        </div>
                    </div>
                </div>
                <div class="row m-0">
                    <div class="col-12 mb-4 p-0">
                        <input type="submit" id="purchase" name="submit" value="Purchase" class="submit"/>
                        <div class="fa fa-check done"></div>
                        <div class="fa fa-close failed"></div>
                    </div>
                </div>
                <br>
                <br>
            </div>
        </div>

    </form>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

    <script>
        $(document).ready(function () {
            $(".submit").click(function () {
                $(".submit").addClass("purchase");
                setTimeout(function () {
                    $(".submit").addClass("hide-loading");
                    // $(".done").addClass("finish");
                }, 1000);
                setTimeout(function () {
                    $(".submit").removeClass("purchase").removeClass("hide-loading");
                    // $(".done").removeClass("finish");
                    // $(".failed").removeClass("finish");
                }, 2000);
            })
        });

        let day = 1;
        let datePicker = document.getElementById('term');
        datePicker.min = datePicker.value = new Date().toISOString().substring(0, 10);
        let payment = document.getElementById('payment');
        let price = document.getElementById('price');
        let checkbox = document.getElementById('checkbox');

        checkbox.onchange = checkbox = (element) => {
            if (element.target.checked) {
                payment.value = (50 + parseFloat(price.value)) * day;
            } else {
                payment.value = (parseFloat(price.value)) * day;
            }
            console.log("with driver " + element.target.checked + ", payment: " + payment.value);
        }

        datePicker.onchange = datePicker = (el) => {
            let checkbox = document.getElementById('checkbox');
            let diff = new Date(el.target.value) - new Date();
            day = Math.ceil(Math.abs(diff) / (1000 * 60 * 60 * 24));
            if (checkbox.checked) {
                payment.value = (50 + parseFloat(price.value)) * day;
            } else {
                payment.value = (parseFloat(price.value)) * day;
            }
        }
    </script>
</div>
